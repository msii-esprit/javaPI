package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Commande {
    private int idCommande;
    private Produit produit;
    private LocalDate date;

    public Commande(int idCommande, Produit produit, LocalDate date) {
        this.idCommande = idCommande;
        this.produit = produit;
        this.date = date;
    }   public Commande( Produit produit, LocalDate date) {
        this.produit = produit;
        this.date = date;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    // Constructeur

    }

