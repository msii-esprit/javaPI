package entities;

public class Plat {
    private int id ;
    private String nom ;
    private float Prix ;
    private String ingredient,imagePath  ;

    public Plat(int id, String nom, float prix, String ingredient, String imagePath) {
        this.id = id;
        this.nom = nom;
        Prix = prix;
        this.ingredient = ingredient;
        this.imagePath = imagePath;
    }
    public Plat( String nom, float prix, String ingredient, String imagePath) {
        this.nom = nom;
        Prix = prix;
        this.ingredient = ingredient;
        this.imagePath = imagePath;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float prix) {
        Prix = prix;
    }

    public Plat(int id, String nom, String ingredient, String imagePath) {
        this.id = id;
        this.nom = nom;
        this.imagePath=imagePath;
        this.ingredient = ingredient;
    }
    public Plat(String nom, String ingredient,String imagePath) {
        this.id = id;
        this.nom = nom;
        this.imagePath=imagePath;
        this.ingredient = ingredient;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
