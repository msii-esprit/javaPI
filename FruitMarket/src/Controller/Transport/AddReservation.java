package Controller.Transport;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Produit;
import models.Reservation;
import models.Vehicule;
import services.ReservationService;
import utils.Mailer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddReservation implements Initializable {
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

private Vehicule vehicule;
    @FXML
    private TextField InputNom;

    @FXML
    private TextField Inputemail;
    @FXML
    void clickAdd(ActionEvent event) throws SQLException {

        ReservationService rs=new ReservationService();
          rs.ajouter(new Reservation(InputNom.getText(),Inputemail.getText(),vehicule));

    Mailer mailer=new Mailer();
          Mailer.send("validation.message@gmail.com","vejkhqcpenyubtev",Inputemail.getText(),"nouveau Reservation ","vous avez un nouveau reservation pour le "+ vehicule.getType()+" " +vehicule.getNom()+" vous payer a l'entrer "+vehicule.getPrix() );
/*

         String to = "aziz.gadacha@esprit.tn";
          String from = "validation.message@gmail.com";
          String host = "smtp.example.com";
          String port = "587"; // SMTP port for your mail provider
          String username = "validation message";
          String password = "fttpjdgxydfvfrui";
          String subject = "Test Email";
          String body = "This is a test email sent from Java.";

          Properties props = new Properties();
          props.put("mail.smtp.host", host);
          props.put("mail.smtp.port", port);
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");

          Session session = Session.getInstance(props, new javax.mail.Authenticator() {
              protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                  return new javax.mail.PasswordAuthentication(username, password);
              }
          });


          try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(from));
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
              message.setSubject(subject);
              message.setText(body);

              Transport.send(message);

              System.out.println("Email sent successfully!");

          } catch (MessagingException e) {
              throw new RuntimeException(e);
          }
*/
          listener.onInfoSentAdd(true);



                Stage stage = (Stage) buttonok.getScene().getWindow();
                stage.close();

    }

    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) buttonok.getScene().getWindow();
        stage.close();
    }

    public void setValues(Vehicule vehicule){

        this.vehicule=vehicule;

    }



}