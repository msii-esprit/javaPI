package services;



import entities.CommandeLine;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeLineService implements IService<CommandeLine> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(CommandeLine cl) {
        try {
            String req = "INSERT INTO CommandLine (idCommande, idProduit, quantite, prixTotale) VALUES (" + cl.getIdCommande() + ", "
                    + cl.getIdProduit() + ", " + cl.getQuantite() + ", " + cl.getPrixTotale() + ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ligne de commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM CommandLine WHERE idCommande = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ligne de commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CommandeLine cl) {
        try {
            String req = "UPDATE CommandLine SET idProduit = " + cl.getIdProduit() + ", quantite = " + cl.getQuantite() + ", prixTotale = " + cl.getPrixTotale() + " WHERE idCommande = " + cl.getIdCommande();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ligne de commande mise à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<CommandeLine> getAll() {
        List<CommandeLine> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM CommandLine";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                CommandeLine cl = new CommandeLine(rs.getInt("idCommande"), rs.getInt("idProduit"), rs.getInt("quantite"), rs.getDouble("prixTotale"));
                list.add(cl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public CommandeLine getOneById(int id) {
        CommandeLine cl = null;
        try {
            String req = "SELECT * FROM CommandLine WHERE idCommande = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                cl = new CommandeLine(rs.getInt("idCommande"), rs.getInt("idProduit"), rs.getInt("quantite"), rs.getDouble("prixTotale"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cl;
    }
}
