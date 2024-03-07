package main;

import models.Commande;
import models.Produit;

public interface CommandeMyListener {
    public void onClickListener(Commande commande);
}
