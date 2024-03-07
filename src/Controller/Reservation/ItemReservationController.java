package Controller.Reservation;

import Main.MainJavaFX;
import entities.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.MyListenerReservation;

public class ItemReservationController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(reservation);
    }

    private Reservation reservation;
    @FXML
    private Label quantite;
    private MyListenerReservation myListener;

    public void setData(Reservation reservation, MyListenerReservation myListener) {
        this.reservation = reservation;
        this.myListener = myListener;
        nameLabel.setText(String.valueOf(  reservation.getCalendrier()));
        priceLable.setText( reservation.getTemps());

        Image image = new Image(getClass().getResourceAsStream("../../img/Restaurant.jpg"));
        img.setImage(image);
    }
}
