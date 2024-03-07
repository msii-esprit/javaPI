package services;
import models.Produit;
import models.Vehicule;
import utils.MyDabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeService implements IService<Vehicule>{

   private Connection connection;
   public VehiculeService(){
       connection = MyDabase.getInstance().getConnection();
   }
    @Override
    public void ajouter(Vehicule vehicule) throws SQLException {
        String sql = "insert into vehicule (nom, type,prix,destination,temps) " +
                "values('" + vehicule.getNom() + "','" + vehicule.getType() + "'"
                +  "," + vehicule.getPrix() + ",'"+vehicule.getdestination()+"',"+vehicule.getTemps()+")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(Vehicule vehicule) throws SQLException {
        String sql = "update vehicule set nom = ?, type = ?, destination = ? ,temps= ? ,prix=? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, vehicule.getNom());
        preparedStatement.setString(2, vehicule.getType());
        preparedStatement.setString(3, vehicule.getdestination());
        preparedStatement.setFloat(4,vehicule.getTemps());
        preparedStatement.setFloat(5,vehicule.getPrix());
        preparedStatement.setInt(6,vehicule.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
String req = "DELETE FROM `vehicule` WHERE id=?";
PreparedStatement preparedStatement = connection.prepareStatement(req);
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
    }


    @Override
    public List<Vehicule> recuperer() throws SQLException {
        String sql = "select * from vehicule";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<Vehicule> list = new ArrayList<>();
        while (rs.next()){
            Vehicule p = new Vehicule();
            p.setId(rs.getInt("id"));
            p.setdestination(rs.getString("destination"));
            p.setTemps(rs.getFloat("temps"));
            p.setType(rs.getString("type"));
            p.setPrix(rs.getFloat("prix"));
            if(p.getType().equals("metro"))
            p.setImagePath("/img/metro.png");
            else
                p.setImagePath("/img/bus.png");

            p.setNom(rs.getString("nom"));
            list.add(p);

        }

        return list;
    }

}
