package Controller.Transport.admin;

import Controller.Transport.ItemController;
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
import main.MyListenerVehicule;
import models.Vehicule;
import services.CommandeService;
import services.ProduitService;
import services.VehiculeService;

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

    private List<Vehicule> fruits = new ArrayList<>();
    private Image image;
    private MyListenerVehicule myListener;
    private Vehicule vehicule;
    ProduitService ps =new ProduitService();
    CommandeService cs =new CommandeService();

/////////////////////////////



    ////////////////////////////


    private void setChosenFruit(Vehicule fruit) {
        this.vehicule=fruit;
      ProduitNom.setText(fruit.getNom());
        PrixField.setText(String.valueOf( fruit.getPrix()));
        QuantitField.setText(String.valueOf( fruit.getdestination()));
        if (vehicule.getType().equals("metro"))
            image = new Image("/img/metro.png");
        else
            image = new Image("/img/bus.png");
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

        ObservableList<Vehicule> obslist = FXCollections.observableList(fruits);
        System.out.println("ooooooooooooo1");

        FilteredList<Vehicule> filterData = new FilteredList<>(obslist, p -> true);

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

            SortedList<Vehicule > sortedList = new SortedList<>(filterData);
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
                        fxmlLoader.setLocation(getClass().getResource("/views/Mobilite/item.fxml"));
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
            loader.setLocation(getClass().getResource("../../../views/Mobilite/AddVehicule.fxml"));
            DialogPane  detailPage=loader.load();
            AddVehicule addProduct=loader.getController();
            addProduct.setAddListner(new AddVehicule.AddListener() {
                @Override
                public void onInfoSentAdd( Boolean var) throws SQLException {
                    if (var) {
                            update();
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText(" vehicule ajouter avec succes *  ");
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
        vs.supprimer(vehicule.getId());

        // Convert LocalDate to java.sql.Date

        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Vehicule supprimer avec succes ");
        alert.showAndWait();
    }
    @FXML
    private TextField PrixField;
VehiculeService vs=new VehiculeService();
    @FXML
    void onClickModifier(ActionEvent event)  throws Exception{
       vehicule.setNom(  ProduitNom.getText());
        vehicule.setPrix(Float.parseFloat(PrixField.getText()));
        vehicule.setdestination(QuantitField.getText());
        vs.modifier(vehicule);

        // Convert LocalDate to java.sql.Date

        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Vehicule modifier avec succes !");
        alert.showAndWait();

    }
    void  update(){
        VehiculeService vs=new VehiculeService();
        try {
            fruits.clear();

            fruits.addAll(vs.recuperer());
        } catch (SQLException e) {
            System.out.println("lena");
            System.out.println(e);        }
        grid.getChildren().clear();

        if (!fruits.isEmpty()) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListenerVehicule() {
                @Override
                public void onClickListener(Vehicule vehicule) {
                    setChosenFruit(vehicule);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Mobilite/item.fxml"));
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
