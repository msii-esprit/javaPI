package Controller.Transport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListenerVehicule;
import models.Produit;
import models.Vehicule;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        System.out.println("lllllllll");
        System.out.println(vehicule.getdestination());
        MyListenerVehicule.onClickListener(vehicule);
    }

    private Vehicule vehicule;
    @FXML
    private Label quantite;
    private MyListenerVehicule MyListenerVehicule;

    public void setData(Vehicule vehicule, MyListenerVehicule MyListenerVehicule) {
        this.vehicule = vehicule;
        this.MyListenerVehicule = MyListenerVehicule;
        nameLabel.setText(vehicule.getNom());
        quantite.setText(String.valueOf(vehicule.getdestination()));
        priceLable.setText(Main.CURRENCY + vehicule.getPrix());
        Image image;
        if(vehicule.getType().equals("metro"))

            image = new Image(getClass().getResourceAsStream("/img/metro.png"));
        else
            image = new Image(getClass().getResourceAsStream("/img/bus.png"));

        img.setImage(image);
    }
}
