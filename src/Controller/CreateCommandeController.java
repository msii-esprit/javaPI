package Controller;
import Main.MainJavaFX;
import entities.Commande;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import services.CommandeService;
import services.ServiceMenu;
import services.ServiceProduit;
import services.ServiceReservation;
import utils.MyListener;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateCommandeController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button commandeButton;
    @FXML
    private TextField QuantitField;
    @FXML
    private Label labelNomProduit;

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
    ServiceMenu sm =new ServiceMenu();

   @FXML
    void rechercher(KeyEvent event)  {

        ObservableList<Produit> obslist = FXCollections.observableList(fruits);


        FilteredList<Produit> filterData = new FilteredList<>(obslist, p -> true);



        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {

            filterData.setPredicate(Menu -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (String.valueOf(produit.getNom_produit()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Produit > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);

            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < sortedList.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
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




   /* @FXML
    void onClickCommande(ActionEvent event) throws Exception {
   Menu.setQuantite(produit.getQuantite()-Integer.parseInt(QuantitField.getText()));
ps.modifier(produit);
        LocalDate currentDate = LocalDate.now();

        // Convert LocalDate to java.sql.Date
        Date sqlDate = Date.valueOf(currentDate);

        cs.ajouter(new Commande(produit,Integer.parseInt(QuantitField.getText()),sqlDate));
        update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation added successfully!");
        alert.showAndWait();

    }*/
/////////////////////////////



    ////////////////////////////



    @FXML
    void ajoutCommande(ActionEvent event) {
        CommandeService cs=new CommandeService();


cs.ajouter(new Commande(produit, LocalDate.now()));
        update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("commande ajouter avec succes ");
        alert.showAndWait();
    }

    private void setChosenFruit(Produit fruit) {
        this.produit=fruit;
        labelNomProduit.setText(fruit.getNom_produit());
        fruitPriceLabel.setText(MainJavaFX.CURRENCY + fruit.getPrix_produit());
        System.out.println("zzzzzzzz");
        System.out.println(fruit.getImage_produit());
        String imagePath = "../img/" + fruit.getImage_produit();
        File file = new File(imagePath);

        System.out.println("iiiiiii");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("../img/" + fruit.getImage_produit());
        //image = new Image(getClass().getResourceAsStream("../img/chicken.jpg"));
        Image image = new Image("/img/"+fruit.getImage_produit());

        fruitImg.setImage(image);

        chosenFruitCard.setStyle("-fx-background-color: #" + "F16C31" + ";\n" +
                "    -fx-background-radius: 30;");
    }
    private Stage stage;
    private Scene scene;


    void  update(){
        ServiceProduit ps=new ServiceProduit();
        try {
            fruits.clear();

            fruits.addAll(ps.afficherProduits());
        } catch (Exception e) {
            System.out.println(e);        }
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
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
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
