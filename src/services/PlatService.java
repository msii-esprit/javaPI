package services;



import entities.Plat;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlatService implements IService<Plat> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Plat plat) {
        try {
            String req = "INSERT INTO Plat (nom, ingredient, imagePath,prix) VALUES ('" + plat.getNom() + "', '"
                    +  plat.getIngredient() + "', '" + plat.getImagePath() + "',"+plat.getPrix()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Plat créée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM Plat WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("plat supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Plat plat) {
        try {
            String req = "UPDATE plat SET nom = '" + plat.getNom() + "', ingredient = '" + plat.getIngredient() + "', imagePath = '" + plat.getImagePath() + "', prix= " + plat.getPrix() + " WHERE id = " + plat.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande mise à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Plat> getAll() {
        List<Plat> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Plat";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Plat c = new Plat(rs.getInt("id"), rs.getString("nom"), rs.getFloat("prix"), rs.getString("ingredient"), rs.getString("imagePath"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Plat getOneById(int id) {
        Plat plat = null;
        try {
            String req = "SELECT * FROM plat WHERE id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                plat = new Plat(rs.getInt("id"), rs.getString("nom"), rs.getFloat("prix"), rs.getString("ingredient"), rs.getString("imagePath"));
            }//
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return plat;
    }
}
