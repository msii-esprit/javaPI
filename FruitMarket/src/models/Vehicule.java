package models;

public class Vehicule {
    private int id;
    private String type;
    private String destination;
    private float temps;
    private float prix;
    private String nom;
    private String imagePath;

    public Vehicule(int id, String type, String destination, float temps, float prix, String nom, String imagePath) {
        this.id = id;
        this.type = type;
        this.destination = destination;
        this.temps = temps;
        this.prix = prix;
        this.nom = nom;
        this.imagePath = imagePath;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Vehicule(int id, String type, String destination, float temps, String nom, String imagePath) {
        this.id = id;
        this.type = type;
        this.destination = destination;
        this.temps = temps;
        this.nom = nom;
        this.imagePath = imagePath;
    }
    public Vehicule( String type, float prix,String destination, float temps, String nom) {
        this.type = type;
        this.prix = prix;
        this.destination = destination;
        this.temps = temps;
        this.nom = nom;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Vehicule(int id, String type, String destination, float temps, String nom) {
        this.id = id;
        this.type = type;
        this.destination = destination;
        this.temps = temps;
        this.nom = nom;
    } public Vehicule() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getdestination() {
        return destination;
    }

    public void setdestination(String destination) {
        this.destination = destination;
    }

    public float getTemps() {
        return temps;
    }

    public void setTemps(float temps) {
        this.temps = temps;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
