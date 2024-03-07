package Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservatuinDateController implements Initializable {
    @FXML
    private Button btnSignIn;

    @FXML
    private DatePicker datepicker;

    @FXML
    void annuler(ActionEvent event) {

    }


    @FXML
    private ChoiceBox<Integer> CB1;
    @FXML
    private Button buttonok;
    @FXML
    private ChoiceBox<Integer> CB2;

        // Method to validate the selected date
        @FXML
        private Stage stage;
        private Scene scene;
        private Parent root;
        private void validateDate(Event event) throws Exception {
            LocalDate selectedDate =     datepicker.getValue();

            if (selectedDate == null) {
                // No date selected, show an alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a date.");
                alert.showAndWait();
            } else {
                // Date is selected, you can perform further validation if needed
                // For example, you can check if the selected date is in the past or future
                LocalDate today = LocalDate.now();
                if (selectedDate.isBefore(today)) {




                    // Date is in the past
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("S'il vous plaits  selectionner une date valide.");
                    alert.showAndWait();                } else {
                    /*
                    root = FXMLLoader.load(getClass().getResource("../views/RESERVATIONTable.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();*/
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/RESERVATIONTable.fxml"));
                    Parent root = loader.load();
                    ReservationTableController controller = loader.getController();
                    String time=(CB1.getValue())+":"+(CB2.getValue()).toString();

// Set your variable here
                    controller.setVariable(selectedDate,  time);


                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    // Get the controller instance

                    // Set the variable in the controller

                }
                    // Date is in the future

                }
            }


    @FXML
    void suivant(ActionEvent event) throws  Exception {
        validateDate(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datepicker.setValue(LocalDate.now());
        for (int i=8;i<23;i++) {
            CB1.getItems().add(i);
        }
        for (int i=0;i<60;i++) {
            CB2.getItems().add(i);
        }
        CB2.setValue(9);
        CB1.setValue(8);

    }






}
