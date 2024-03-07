package models;

import java.sql.Date;

public class Reservation {

    private  int id;
    private String nom,email;
    private Vehicule vehicule;

    public Reservation(String nom, String email, Vehicule vehicule) {
        this.nom = nom;
        this.email = email;
        this.vehicule = vehicule;
    }

    public Reservation(int id, String nom, String email, Vehicule vehicule) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.vehicule = vehicule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
}
