package entities;// ReservationController.java
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class ReservationController {
    @FXML
    private DatePicker arrivalDatePicker;

    @FXML
    private DatePicker departureDatePicker;

    public void initialize() {
        // Initial setup or event handling logic here
        // For example, you can disable dates that are already booked
        // or ensure departure date is after arrival date.
    }
}
