//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package services;




import entities.Menu;
import entities.Produit;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduit {
    private Connection cnx = DataSource.getInstance().getCnx();

    public ServiceProduit() {
    }

    public void ajouterProduit(Produit p) throws SQLException {

        String query = "INSERT INTO produit(nom_produit,description_produit,image_produit,prix_produit, fk_menu) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = this.cnx.prepareStatement(query, 1);
        preparedStatement.setString(1, p.getNom_produit());
        preparedStatement.setString(2, p.getDescription_produit());
        preparedStatement.setString(3, p.getImage_produit());
        preparedStatement.setDouble(4, p.getPrix_produit());
        preparedStatement.setInt(5, p.getMenu().getId_menu());
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            p.setId_produit(rs.getInt(1));
        }

        preparedStatement.executeUpdate();
        System.out.print("succes!!!");

    }

    public void modifierProduit(Produit produit, int id) {
        String var10000 = produit.getNom_produit();
        String req = "UPDATE produit set nom_produit = '" + var10000 + "', description_produit = '" + produit.getDescription_produit() + "', image_produit = '" + produit.getImage_produit() + "', prix_produit = '" + produit.getPrix_produit() + "' WHERE  `produit`.`id_produit` = " + id;

        try {
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit modifié !");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

    }

    public void supprimerProduit(int id_produit) {
        try {
            String req = "DELETE FROM `produit` WHERE id_produit = " + id_produit;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit deleted !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Produit> afficherProduits() {
        List<Produit> listproduits = new ArrayList();

        try {
            String req = "SELECT * FROM produit";
            Statement st = this.cnx.createStatement();
            ResultSet CA = st.executeQuery(req);

            while(CA.next()) {
                Produit p = new Produit();
                p.setId_produit(CA.getInt("id_produit"));
                p.setNom_produit(CA.getString("nom_produit"));
                p.setDescription_produit(CA.getString("description_produit"));
                p.setImage_produit(CA.getString("image_produit"));
                p.setPrix_produit(CA.getDouble("prix_produit"));
                listproduits.add(p);
                System.out.println(p);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return listproduits;
    }
    public List<Produit> affichercat() {
        List<Produit> listproduits = new ArrayList<>();

        try {
            String req = "SELECT p.id_produit, p.nom_produit, p.description_produit, p.image_produit, p.prix_produit, m.categorie "
                    + "FROM Produit p "
                    + "INNER JOIN Menu m ON p.fk_menu = m.id_menu;";
            Statement st = this.cnx.createStatement();
            ResultSet CA = st.executeQuery(req);

            while (CA.next()) {
                Produit p = new Produit();
                p.setId_produit(CA.getInt("id_produit"));
                p.setNom_produit(CA.getString("nom_produit"));
                p.setDescription_produit(CA.getString("description_produit"));
                p.setImage_produit(CA.getString("image_produit"));
                p.setPrix_produit(CA.getDouble("prix_produit"));
                p.setCategorie(CA.getString("categorie")); // Nouvelle ligne pour récupérer la catégorie
                listproduits.add(p);
                System.out.println(p);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return listproduits;
    }


    public List<Produit> rechProduit(int id) {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `produit` WHERE fk_menu= " + id;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Produit r = new Produit();
                r.setId_produit(RS.getInt("id_produit"));
                r.setNom_produit(RS.getString("nom_produit"));
                r.setDescription_produit(RS.getString("description_produit"));
                r.setImage_produit(RS.getString("image_produit"));
                r.setPrix_produit(RS.getDouble("prix_produit"));
                int id_menu = RS.getInt("fk_menu");
                r.setMenu(new Menu(id_menu));


                list.add(r);
                System.out.println(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }
    public List<Produit> rechercherProduitsParNom(String nomProduit) {
        List<Produit> listeProduits = new ArrayList<>();
        try {
            String query = "SELECT * FROM produit WHERE nom_produit LIKE ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, "%" + nomProduit + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId_produit(resultSet.getInt("id_produit"));
                produit.setNom_produit(resultSet.getString("nom_produit"));
                produit.setDescription_produit(resultSet.getString("description_produit"));
                produit.setImage_produit(resultSet.getString("image_produit"));
                produit.setPrix_produit(resultSet.getDouble("prix_produit"));
                // Ajoutez d'autres attributs au besoin

                listeProduits.add(produit);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la recherche de produits par nom : " + ex.getMessage());
        }
        return listeProduits;
    }


}
