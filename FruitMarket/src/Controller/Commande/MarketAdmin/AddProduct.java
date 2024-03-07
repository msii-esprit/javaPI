package Controller.Commande.MarketAdmin;
import java.text.SimpleDateFormat;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Produit;
import models.User;
import services.ProduitService;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class AddProduct implements Initializable {
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
    private TextField Inputquant;
    @FXML
    void clickAdd(ActionEvent event) throws SQLException {
        ProduitService rs=new ProduitService();
          rs.ajouter(new Produit(Integer.parseInt(Inputquant.getText()), InputNom.getText(),"fruit",Float.parseFloat( InputPrix.getText()),new User(1),"/img/kiwi.png"));
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