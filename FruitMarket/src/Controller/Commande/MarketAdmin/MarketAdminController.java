package Controller.Commande.MarketAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.MyListener;
import models.Produit;
import services.CommandeService;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MarketAdminController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button commandeButton;
    @FXML
    private TextField QuantitField;
    @FXML
    private TextField ProduitNom;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private TextField searchBox;
    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Produit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private Produit produit;
    ProduitService ps =new ProduitService();
    CommandeService cs =new CommandeService();

/////////////////////////////



    ////////////////////////////


    private void setChosenFruit(Produit fruit) {
        this.produit=fruit;
      ProduitNom.setText(fruit.getNom());
        fruitPriceLabel.setText(Main.CURRENCY + fruit.getPrix());
        QuantitField.setText(String.valueOf( fruit.getQuantite()));
        image = new Image(getClass().getResourceAsStream(fruit.getImagePath()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + "F16C31" + ";\n" +
                "    -fx-background-radius: 30;");
    }
    private Stage stage;
    private Scene scene;
    public interface PopupListener {
        void onInfoSentChange( Boolean var) throws SQLException;
    }
    private PopupListener listener;

    public void setChangeListener(PopupListener listener) {
        this.listener = listener;
    }
int i=0;
    @FXML
    void rechercher(KeyEvent event)  {
        System.out.println("oooooooo44ooooo");
        System.out.println(i++);
        System.out.println(fruits.size());
        ObservableList<Produit> obslist = FXCollections.observableList(fruits);
        System.out.println("ooooooooooooo1");

        FilteredList<Produit> filterData = new FilteredList<>(obslist, p -> true);

        System.out.println("ooooooooooooo2");
        System.out.println(filterData.size());
        System.out.println(filterData.get(0).getNom());
        System.out.println(searchBox.getText());

        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            System.out.println("ooooooooooooo3");

            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();
                System.out.println("ooooooooooooo4");

                if (String.valueOf(candida.getNom()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Produit > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);
            System.out.println("qmmqmmqmqmqqmmm");
            System.out.println(sortedList.size());
            int column = 0;
            int row = 1;
                try {
                    for (int i = 0; i < sortedList.size(); i++) {
                        System.out.println("uuuuuuuuuuu");
                        System.out.println(sortedList.size());
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/views/Market/item.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        ItemController itemController = fxmlLoader.getController();
                        itemController.setData(sortedList.get(i),myListener);
                        if (column == 3) {
                            column = 0;
                            row++;
                        }
                        grid.add(anchorPane, column++, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            // table.setItems(sortedList);


        });

    }




  @FXML
  void onClickAjouter(ActionEvent event) {

        Stage detail =(Stage) ((Node)event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../views/Market/AddProduct.fxml"));
            DialogPane  detailPage=loader.load();
            AddProduct addProduct=loader.getController();
            addProduct.setAddListner(new AddProduct.AddListener() {
                @Override
                public void onInfoSentAdd( Boolean var) throws SQLException {
                    if (var) {
                            update();
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText(" produit ajouter avec succes *  ");
                            alert.showAndWait();
                                          }
                }

            });

            Dialog<ButtonType> dialog =new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);

            dialog.setDialogPane(detailPage);
            Optional<ButtonType> clickButtonp=dialog.showAndWait();
            dialog.setTitle("detail");


        }catch (Exception e){
            System.out.println("rani");
            System.out.println(e);
        }
    }


    @FXML
    void switchPage(ActionEvent event) {


        try {
            Parent root=FXMLLoader.load(getClass().getResource("../views/Market/market.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

            stage.show();
            // Set the stage's scene to the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void OnclickSupp(ActionEvent event)  throws Exception{
        ps.supprimer(produit.getId());

        // Convert LocalDate to java.sql.Date

        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Produit supprimer avec succes ");
        alert.showAndWait();
    }

    @FXML
    void onClickModifier(ActionEvent event)  throws Exception{
       produit.setNom(  ProduitNom.getText());
       produit.setQuantite(Integer.parseInt(QuantitField.getText()));
        ps.modifier(produit);

        // Convert LocalDate to java.sql.Date

        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Produit modifier avec succes !");
        alert.showAndWait();

    }
    void  update(){
        ProduitService ps=new ProduitService();
        try {
            fruits.clear();

            fruits.addAll(ps.recupererAdmin());
        } catch (SQLException e) {
            System.out.println("lena");
            System.out.println(e);        }
        grid.getChildren().clear();

        if (!fruits.isEmpty()) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Market/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();
    }

}
