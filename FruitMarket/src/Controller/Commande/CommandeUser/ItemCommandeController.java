package Controller.Commande.CommandeUser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.CommandeMyListener;
import main.Main;
import main.MyListener;
import models.Commande;
import models.Produit;

public class ItemCommandeController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {

        commandeMyListener.onClickListener(commande);
    }

    private Commande commande;
    @FXML
    private Label quantite;
    private CommandeMyListener commandeMyListener;

    public void setData(Commande commande, CommandeMyListener commandeMyListener) {
        this.commande = commande;
        this.commandeMyListener = commandeMyListener;
        nameLabel.setText(commande.getProduit().getNom());
        quantite.setText(String.valueOf(commande.getQuantite()));
        priceLable.setText(Main.CURRENCY + (commande.getProduit().getPrix())*commande.getQuantite());
        Image image = new Image(getClass().getResourceAsStream(commande.getProduit().getImagePath()));
        img.setImage(image);
    }
}
