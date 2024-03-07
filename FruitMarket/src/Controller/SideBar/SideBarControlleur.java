package Controller.SideBar;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SideBarControlleur implements Initializable {

    @FXML
    private Button AcceptPostulation;

    @FXML
    private Button CandidatureButton;

    @FXML
    private Button RendezVousButton;

    @FXML
    private Button UserListeButton;

    @FXML
    private Button AnnonceButton;
    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox sidebar;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane leftanchorpane;
    @FXML
    private StackPane content;

    @FXML
    private VBox left;
    @FXML
    private Button ButtonVehicuile;

    @FXML
    private Button ButtonVehiculeAdmin;

    @FXML
    private Button ProduitAdmin;

    @FXML
    private Button ReservationButton;



    @FXML
    private Button loadProduit;



    @FXML
    void LoadProduitAdmin(ActionEvent event) throws Exception{
        loadPageToContent("../../views/Market/marketAdmin.fxml");
    }




    @FXML
    void loadProduit(ActionEvent event) throws Exception{
        loadPageToContent("../../views/Market/market.fxml");

    }

    @FXML
    void loadCommande(ActionEvent event)throws Exception {
        loadPageToContent("../../views/Commande/commande.fxml");

    }
    @FXML
    void loadReservation(ActionEvent event)throws Exception {
        loadPageToContent("../../views/Reservation/Reservation.fxml");

    }

    @FXML
    void loadVehicule(ActionEvent event)throws Exception {
        loadPageToContent("../../views/Mobilite/MetroBus.fxml");

    }

    @FXML
    void loadVehiculeAdmin(ActionEvent event) throws Exception {
        loadPageToContent("../../views/Mobilite/marketAdmin.fxml");

    }

    private Map<String, Node> pages = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            content.getChildren().add(loadPage("../../views/Market/marketAdmin.fxml"));

           // left.setAlignment();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

  /*  @FXML
    private void initialize() throws IOException {
        // Set the first page as the content

    }*/



    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();
    }



    private void loadPageToContent(String pageName) throws IOException {
        // Remove any existing content from the content region
        content.getChildren().clear();

        // Add the new content to the content region
        content.getChildren().add(loadPage(pageName));
    }

    private Node loadPage(String fxmlFileName) throws IOException {
        URL fxmlUrl = getClass().getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        return loader.load();
    }
}