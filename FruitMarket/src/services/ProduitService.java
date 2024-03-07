package services;
import models.Produit;
import utils.MyDabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IService<Produit>{

   private Connection connection;
   public ProduitService(){
       connection = MyDabase.getInstance().getConnection();
   }
    @Override
    public void ajouter(Produit produit) throws SQLException {
        String sql = "insert into produit (nom, type,prix,imagePath,quantite,idUser) " +
                "values('" + produit.getNom() + "','" + produit.getType() + "'"
                +  "," + produit.getPrix() + ",'"+produit.getImagePath()+"',"+produit.getQuantite()+","+produit.getUser().getId()+")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(Produit produit) throws SQLException {
        String sql = "update produit set nom = ?, type = ?, imagePath = ? ,prix= ? ,quantite=? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, produit.getNom());
        preparedStatement.setString(2, produit.getType());
        preparedStatement.setString(3, produit.getImagePath());
        preparedStatement.setFloat(4,produit.getPrix());
        preparedStatement.setInt(5,produit.getQuantite());
        preparedStatement.setInt(6,produit.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
String req = "DELETE FROM `produit` WHERE id=?";
PreparedStatement preparedStatement = connection.prepareStatement(req);
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
    }

    @Override
    public List<Produit> recuperer() throws SQLException {
       String sql = "select * from produit where quantite > 0";
       Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<Produit> list = new ArrayList<>();
        while (rs.next()){
            Produit p = new Produit();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrix(rs.getFloat("prix"));
            p.setType(rs.getString("type"));
            p.setImagePath(rs.getString("imagePath"));
            p.setQuantite(rs.getInt("quantite"));
            list.add(p);

        }
        for( Produit v :list)
        {
            System.out.println(v.getNom());
        }
        return list;
   }

    public List<Produit> recupererAdmin() throws SQLException {
        String sql = "select * from produit  INNER JOIN  user  on user.id=produit.idUser  where quantite > 0";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<Produit> list = new ArrayList<>();
        while (rs.next()){
            Produit p = new Produit();
            p.setId(rs.getInt("produit.id"));
            p.setNom(rs.getString("produit.nom"));
            p.setPrix(rs.getFloat("prix"));
            p.setType(rs.getString("type"));
            p.setImagePath(rs.getString("imagePath"));
            p.setQuantite(rs.getInt("quantite"));
            list.add(p);

        }
        for( Produit v :list)
        {
            System.out.println(v.getNom());
        }
        return list;
    }

}
