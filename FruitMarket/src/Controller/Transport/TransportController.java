package Controller.Transport;

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
import services.VehiculeService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransportController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label dure;
    @FXML
    private Button commandeButton;
    @FXML
    private TextField QuantitField;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label destination;
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


    @FXML
    void rechercher(KeyEvent event)  {

        ObservableList<Vehicule> obslist = FXCollections.observableList(fruits);


        FilteredList<Vehicule> filterData = new FilteredList<>(obslist, p -> true);



        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {

            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (String.valueOf(candida.getNom()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Vehicule > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);

            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < sortedList.size(); i++) {

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
    void onClickResevation(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("../../views/Mobilite/AddReservation.fxml"));
            DialogPane  detailPage=loader.load();
            AddReservation addReservation=loader.getController();
            addReservation.setValues(vehicule);
                    addReservation.setAddListner(new AddReservation.AddListener() {
                @Override
                public void onInfoSentAdd( Boolean var) throws SQLException {
                    if (var) {
                        update();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText(" Reservation ajouter avec succes *  ");
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
/////////////////////////////



    ////////////////////////////


    private void setChosenFruit(Vehicule vehicule) {
        this.vehicule=vehicule;
        System.out.println("eeeeeeeeeeeeeeee");
        System.out.println(vehicule);
        fruitNameLable.setText(vehicule.getNom());
        fruitPriceLabel.setText(Main.CURRENCY + vehicule.getPrix());
        dure.setText(String.valueOf( vehicule.getTemps()));
        destination.setText(vehicule.getdestination());
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
    @FXML
    void switchPage(ActionEvent event) {


        try {
            Parent root=FXMLLoader.load(getClass().getResource("../views/Mobilite/MetroBus.fxml"));
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
    void switchPageAdmin(ActionEvent event) {


        try {
            Parent root=FXMLLoader.load(getClass().getResource("../views/Market/marketAdmin.fxml"));
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

            stage.show();
            // Set the stage's scene to the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void  update(){
        VehiculeService ps=new VehiculeService();
        try {
            fruits.clear();

            fruits.addAll(ps.recuperer());
        } catch (SQLException e) {
            System.out.println(e);        }
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
