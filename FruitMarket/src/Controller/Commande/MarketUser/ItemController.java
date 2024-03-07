package Controller.Commande.MarketUser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import models.Produit;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }

    private Produit produit;
    @FXML
    private Label quantite;
    private MyListener myListener;

    public void setData(Produit produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getNom());
        quantite.setText(String.valueOf(produit.getQuantite()));
        priceLable.setText(Main.CURRENCY + produit.getPrix());
        Image image = new Image(getClass().getResourceAsStream(produit.getImagePath()));
        img.setImage(image);
    }
}
