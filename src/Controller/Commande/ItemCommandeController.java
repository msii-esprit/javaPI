package Controller.Commande;

import Main.MainJavaFX;
import entities.Commande;
import entities.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.MyListener;
import utils.MyListenerCandidature;

public class ItemCommandeController {
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

    private Commande produit;
    @FXML
    private Label quantite;
    private MyListenerCandidature myListener;

    public void setData(Commande produit, MyListenerCandidature myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getProduit().getNom_produit());
        priceLable.setText(String.valueOf(produit.getDate()));

        Image image = new Image(getClass().getResourceAsStream("../../img/"+produit.getProduit().getImage_produit()));
        img.setImage(image);
    }
}
