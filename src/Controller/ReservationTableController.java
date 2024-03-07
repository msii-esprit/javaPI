package Controller;

import entities.Reservation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceReservation;
import utils.Mailer;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationTableController implements Initializable {
LocalDate localDate;

String time;
    @FXML
    private ChoiceBox<Integer> TableChoser;

    @FXML
    private TextField txtPrenom;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void retour(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("../views/RESE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void suivantButton(ActionEvent event) {
        Mailer mailer=new Mailer();
        Mailer.send("validation.message@gmail.com","vejkhqcpenyubtev",txtPrenom.getText(),"nouveau Reservation ","vous avez un nouveau reservation pour le restaurant koujina \n la date de votre rendez vous est "+ localDate+" a " +time+" heure " );

    }
    public void setVariable(LocalDate localDate,String time) {
this.localDate=localDate;
        this.time=time;
        System.out.println(localDate);
        System.out.println(time);
        ArrayList<Integer> numberOfTables = new ArrayList<>();

        ServiceReservation rs=new ServiceReservation();
        ArrayList<Integer> list= rs.getTableReserved(new Reservation(localDate,time));
        for (int i = 1; i <= 15; i++) {
            numberOfTables.add(i);
        }

        System.out.println("ppppppppppppppppppppppppp");
        System.out.println(list.size());

        ArrayList<Integer> differences = new ArrayList<>();

        for(int i:numberOfTables){
       if (!list.contains(i)) {
                differences.add(i);
            }
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(differences.size());
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(differences));

        // Setting default value (optional)
        choiceBox.setValue(differences.get(0));
        // Add items to the ChoiceBox
        TableChoser.getItems().addAll(differences);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
