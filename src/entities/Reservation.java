package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Reservation {
    private int idReservation;
    private String temps;
    private int numTable;
    private LocalDate calendrier;
    private int idUser;

    public Reservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    // Constructeur

    public Reservation(int idReservation, int numTable, LocalDate date,String temp) {
        this.idReservation = idReservation;
        this.numTable = numTable;
        this.calendrier = date;
        this.temps=temp;
    }

    public Reservation(LocalDate calendrier, String temps) {

        this.calendrier = calendrier;
        this.temps = temps;
    }

    // Getters et Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }



    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }



    public LocalDate getCalendrier() {
        return calendrier;
    }
    public void setCalendrier(LocalDate calendrier) {
        this.calendrier = calendrier;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
