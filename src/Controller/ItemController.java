package Controller;

import Main.MainJavaFX;
import entities.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.MyListener;

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
        nameLabel.setText(produit.getNom_produit());
        priceLable.setText(MainJavaFX.CURRENCY + produit.getPrix_produit());

        Image image = new Image(getClass().getResourceAsStream("../img/"+produit.getImage_produit()));
        img.setImage(image);
    }
}
