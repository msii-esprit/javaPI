    package services;



    import entities.Reservation;
    import utils.DataSource;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.time.ZoneId;
    import java.util.ArrayList;
    import java.util.List;

    public class ServiceReservation implements IService<Reservation> {

        Connection cnx = DataSource.getInstance().getCnx();

        @Override
        public void ajouter(Reservation r) {
            try {
                String req = "INSERT INTO reservation (num_table,date,id_user,temp) VALUES (" + r.getNumTable() + ", '" + r.getCalendrier() + "', 1,'" + r.getTemps() + "')";
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Reservation created !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }


        public ArrayList<Integer> getTableReserved( Reservation r) {
            ArrayList<Integer> list = new ArrayList<>();
            try {
                System.out.println("zzzzzzzzzzzzzzzzzzzzzzz");
                System.out.println(r.getCalendrier());
                System.out.println(r.getTemps());
                String req = "select num_table from reservation where date ='"+r.getCalendrier()+"'  and tempsReservation='"+r.getTemps()+"'";
                Statement st = cnx.createStatement();

;
                ResultSet rs = st.executeQuery(req);



                while (rs.next()) {
                    list.add(rs.getInt("num_table"));

                }


                System.out.println("Reservation created !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return list;
        }

        @Override
        public void supprimer(int id) {
            try {
                String req = "DELETE FROM reservation WHERE id_reservation = " + id;
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Reservation deleted !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void modifier(Reservation r) {
            try {
                String req = "UPDATE reservation SET tempsReservation='"+r.getTemps()+"', num_table = " + r.getNumTable() + ", date = '" + r.getCalendrier()  +"' WHERE id_reservation = " + r.getIdReservation();
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Reservation updated !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public List<Reservation> getAll() {


            List<Reservation> list = new ArrayList<>();
            try {

                String req = "SELECT * FROM reservation";
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    Reservation r = new Reservation(rs.getInt("id_reservation"), rs.getInt("num_table"),

                            rs.getDate("date").toLocalDate()
                        , rs.getString("tempsReservation"));
                    list.add(r);
                }

            }catch (Exception e){
                e.printStackTrace();

            }
            return list;
        }
        @Override
        public Reservation getOneById(int id) {
            Reservation r = null;
            try {
                String req = "SELECT * FROM reservation WHERE id_reservation = " + id;
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                if (rs.next()) {
                    r = new Reservation(rs.getInt("id_reservation"), rs.getInt("num_table"), rs.getDate("date").toLocalDate(),rs.getString("tempsReservation"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return r;
        }

    }
