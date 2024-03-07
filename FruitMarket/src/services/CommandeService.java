package services;
import models.Commande;
import models.Produit;
import utils.MyDabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande>{

   private Connection connection;
   public CommandeService(){
       connection = MyDabase.getInstance().getConnection();
   }
    @Override
    public void ajouter(Commande commande) throws SQLException {
        String sql = "insert into commande ( idProduit,quantite,dateCommande\t) " +
                "values(" + commande.getProduit().getId() + "," + commande.getQuantite() + ",'"+commande.getDate()+"')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(Commande commande) throws SQLException {
        System.out.println("ddddddzzzzzzzzzz");
        System.out.println(commande.getId());
        String sql = "update commande set idProduit = ?, quantite = ? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commande.getProduit().getId());
        preparedStatement.setInt(2, commande.getQuantite());
        preparedStatement.setInt(3, commande.getId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
String req = "DELETE FROM commande WHERE id=?";
PreparedStatement preparedStatement = connection.prepareStatement(req);
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
    }

    @Override
    public List<Commande> recuperer() throws SQLException {
       String sql = "SELECT * FROM commande  INNER JOIN produit  ON produit.id = commande.idProduit";
       Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<Commande> list = new ArrayList<>();
        while (rs.next()){
            Commande c = new Commande();
            Produit p=new Produit();
            System.out.println("kkkk");
            System.out.println(rs.getFloat("prix"));
            System.out.println(rs.getInt("commande.id"));
            System.out.println(rs.getInt("produit.id"));
            System.out.println(rs.getInt("quantite"));
            c.setId(rs.getInt("commande.id"));
            p.setId(rs.getInt("produit.id"));
            p.setNom(rs.getString("nom"));
            p.setPrix(rs.getFloat("prix"));
            p.setType(rs.getString("type"));
            p.setImagePath(rs.getString("imagePath"));
            p.setQuantite(rs.getInt("produit.quantite"));
            c.setProduit(p);
            c.setQuantite(rs.getInt("commande.quantite"));
            list.add(c);

        }
        for(Commande c : list){
            System.out.println(c);
            System.out.println("ffeef");
            System.out.println(c.getQuantite());
        }

        return list;
   }

}
