package Controller.Transport.admin;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Produit;
import models.User;
import models.Vehicule;
import services.ProduitService;
import services.VehiculeService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddVehicule implements Initializable {
    private  Boolean succes=false ;
    @FXML
    private Text erroraria;
    @FXML
    private ChoiceBox<Integer> CB1;
    @FXML
    private Button buttonok;

    Produit c;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "bus",
                "metro"

        );

        // Add items to the ChoiceBox
        boxType.setItems(items);

        // Set a default value if needed
        boxType.setValue("bus");
    }

    public interface AddListener {
        void onInfoSentAdd( Boolean var) throws SQLException;
    }
    private AddListener listener;

    public void setAddListner(AddListener listener) {
        this.listener = listener;
    }
    @FXML
    private TextField InputNom;

    @FXML
    private TextField InputPrix;



    @FXML
    private ChoiceBox<String> boxType;



    @FXML
    private TextField inputDestination;

    @FXML
    private TextField inputPrix;

    @FXML
    private TextField tinputtemp;
    @FXML
    private TextField Inputquant;
    @FXML
    void clickAdd(ActionEvent event) throws SQLException {
        VehiculeService rs=new VehiculeService();

            rs.ajouter(new Vehicule((boxType.getValue()),Float.parseFloat( inputPrix.getText()), inputDestination.getText(),Float.parseFloat( tinputtemp.getText()),InputNom.getText()));
                    listener.onInfoSentAdd(true);



                Stage stage = (Stage) buttonok.getScene().getWindow();
                stage.close();

    }

    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) buttonok.getScene().getWindow();
        stage.close();
    }





}