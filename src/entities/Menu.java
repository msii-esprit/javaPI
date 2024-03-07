//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package entities;


import java.util.List;
import java.util.Objects;


public class Menu {
    private int id_menu;
    private List<Produit> listProduit;
    private int nbr_page;
    private String categorie;
    private String origine;

    public Menu() {
    }
    public Menu(int id_menu) {
        this.id_menu=id_menu;
    }


    public Menu(int nbr_page, String categorie, String origine) {
        this.nbr_page = nbr_page;
        this.categorie = categorie;
        this.origine = origine;
    }

    public int getId_menu() {
        return this.id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public List<Produit> getListProduit() {
        return this.listProduit;
    }

    public void setListProduit(List<Produit> listProduit) {
        this.listProduit = listProduit;
    }
    public int getNbr_page() {
        return this.nbr_page;
    }

    public void setNbr_page(int nbr_page) {
        this.nbr_page = nbr_page;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getOrigine() {
        return this.origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return getId_menu() == menu.getId_menu() && getNbr_page() == menu.getNbr_page() && Objects.equals(getListProduit(), menu.getListProduit()) && Objects.equals(getCategorie(), menu.getCategorie()) && Objects.equals(getOrigine(), menu.getOrigine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_menu(), getListProduit(), getNbr_page(), getCategorie(), getOrigine());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id_menu=" + id_menu +
                ", listProduit=" + listProduit +
                ", nbr_page=" + nbr_page +
                ", categorie='" + categorie + '\'' +
                ", origine='" + origine + '\'' +
                '}';
    }
}
