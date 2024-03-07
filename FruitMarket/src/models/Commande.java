package models;

import java.sql.Date;

public class Commande {
    private int quantite,id;
    private Produit produit;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Commande(int quantite, int id, Produit produit, Date date) {
        this.quantite = quantite;
        this.id = id;
        this.produit = produit;
        this.date = date;
    }
    public Commande(Produit produit,int quantite, Date date) {
        this.quantite = quantite;
        this.produit = produit;
        this.date = date;
    }

    public Commande(int quantite, int id, Produit produit) {
        this.quantite = quantite;
        this.id = id;
        this.produit = produit;
    }
    public Commande( Produit produit,int quantite) {
        this.quantite = quantite;
        this.produit = produit;
    }

    public Commande() {

    }

    public Commande(int quantite, Produit produit) {
        this.quantite = quantite;
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
