package models;

public class Produit {

        private int id,quantite;
        private String nom,type;
        float prix;
        User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Produit(int quantite, String nom, String type, float prix, User user, String imagePath) {
        this.quantite = quantite;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.user = user;
        this.imagePath = imagePath;
    }

    public Produit(int id, int quantite, String nom, String type, float prix, User user, String imagePath) {
        this.id = id;
        this.quantite = quantite;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.user = user;
        this.imagePath = imagePath;
    }

    private String imagePath;

    public Produit(){
    }

    public Produit(int id, String nom, String type, float prix, int quantite, String imagePath) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.imagePath = imagePath;
    }

    public Produit(int id, String nom, String type, float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Produit(String imagePath) {
        this.imagePath = imagePath;
    }

    public Produit(float prix, String nom, String type) {
        this.prix = prix;
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                '}';
    }
}
