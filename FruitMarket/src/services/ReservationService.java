package services;
import models.Commande;
import models.Produit;
import models.Reservation;
import models.Vehicule;
import utils.MyDabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IService<Reservation>{

   private Connection connection;
   public ReservationService(){
       connection = MyDabase.getInstance().getConnection();
   }
    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String sql = "insert into reservation ( nom,email,idVehicule) " +
                "values('" + reservation.getNom() + "','" + reservation.getEmail() + "','"+reservation.getVehicule().getId()+"')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {

        String sql = "update reservation set nom = ?, email = ? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reservation.getNom());
        preparedStatement.setString(2, reservation.getEmail());
        preparedStatement.setInt(3, reservation.getId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
String req = "DELETE FROM reservation WHERE id=?";
PreparedStatement preparedStatement = connection.prepareStatement(req);
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
    }

    @Override
    public List<Reservation> recuperer() throws SQLException {
       String sql = "SELECT * FROM reservation  INNER JOIN vehicule  ON reservation.idVehicule = vehicule.id";
       Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> list = new ArrayList<>();
        while (rs.next()){
            Reservation r = new Reservation();
            Vehicule v=new Vehicule();

            r.setId(rs.getInt("reservation.id"));
            r.setEmail(rs.getString("email"));
            r.setNom(rs.getString("reservation.nom"));
            v.setPrix(rs.getFloat("prix"));
            v.setType(rs.getString("type"));
            v.setdestination(rs.getString("destination"));
            v.setTemps(rs.getFloat("temps"));
            if (v.getType().equals("metro"))
            v.setImagePath("/img/metro.png");
            else
                v.setImagePath("/img/bus.png");


            v.setNom(rs.getString("vehicule.nom"));
r.setVehicule(v);          ;
            list.add(r);

        }


        return list;
   }

}
