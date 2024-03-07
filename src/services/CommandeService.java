package services;



import entities.Commande;
import entities.Produit;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO Commande (id_produit, date) VALUES ("+ c.getProduit().getId_produit() + ", '"
                    + c.getDate() +"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande créée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM Commande WHERE idCommande = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commande c) {
      /*  try {
            String req = "UPDATE Commande SET  = " + c.getNumTable() + ", idUser = " + c.getIdUser() + ", dateHeure = '" + c.getDateHeure() + "', prixTotale = " + c.getPrixTotale() + " WHERE idCommande = " + c.getIdCommande();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande mise à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    @Override
    public List<Commande> getAll() {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Commande c inner join produit p on p.id_produit=c.id_produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Commande c = new Commande(rs.getInt("idCommande"), new Produit(rs.getInt("id_produit"),rs.getString("nom_produit"),rs.getString("image_produit")),rs.getDate("date").toLocalDate());
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Commande getOneById(int id) {
        /*Commande c = null;
        try {
            String req = "SELECT * FROM Commande WHERE idCommande = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                c = new Commande(rs.getInt("idCommande"), rs.getInt("numTable"), rs.getInt("idUser"), rs.getString("dateHeure"), rs.getDouble("prixTotale"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
*/
        return null;
    }
}
