package Controller.Transport.Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.CommandeMyListener;
import main.Main;
import main.ReservationMyListener;
import models.Commande;
import models.Reservation;

public class itemReservationController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {

        reservationMyListener.onClickListener(reservation);
    }

    private Reservation reservation;
    @FXML
    private Label quantite;
    private ReservationMyListener reservationMyListener;

    public void setData(Reservation  reservation, ReservationMyListener commandeMyListener) {
        this.reservation = reservation;
        this.reservationMyListener = commandeMyListener;
        nameLabel.setText(reservation.getVehicule().getNom());
        quantite.setText(String.valueOf(reservation.getVehicule().getPrix()));
        priceLable.setText(reservation.getNom());
        Image image;
        if(reservation.getVehicule().getType().equals("metro"))
         image = new Image(getClass().getResourceAsStream("/img/metro.png"));
       else
            image = new Image(getClass().getResourceAsStream("/img/car.png"));

        img.setImage(image);
    }
}
