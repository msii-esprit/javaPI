/*package Main;


import entities.Commande;
import entities.CommandeLine;
import entities.Reservation;
import services.CommandeLineService;
import services.CommandeService;
import services.ServiceReservation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String CURRENCY = "$";

    public static void main(String[] args) {
        ServiceReservation reservationService = new ServiceReservation();
        CommandeService commandeService = new CommandeService();
        CommandeLineService CommandeLineService = new CommandeLineService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter une réservation");
            System.out.println("2. Supprimer une réservation");
            System.out.println("3. Modifier une réservation");
            System.out.println("4. Ajouter une commande");
            System.out.println("5. Supprimer une commande");
            System.out.println("6. afficher tous les commandes");
            System.out.println("7. modifier une commande");
            System.out.println("8. Ajouter une ligne de commande");
            System.out.println("9. Supprimer une ligne de commande");
            System.out.println("10. afficher les lignes de commandes");
            System.out.println("11. modifier  ligne de commande");
            System.out.println("12. Afficher toutes les réservations");
            System.out.println("13. Afficher une réservation par ID");
            System.out.println("14. Quitter");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nom de la réservation : ");
                    String nomReservation = scanner.next();
                    System.out.print("Numéro de table : ");
                    int numTable = scanner.nextInt();
                    System.out.print("Numéro de téléphone : ");
                    String numTel = scanner.next();
                    System.out.print("Calendrier (YYYY-MM-DD) : ");
                    String dateString = scanner.next();
                    Date calendrier = java.sql.Date.valueOf(dateString);
                    System.out.print("ID de l'utilisateur : ");
                    int idUser = scanner.nextInt();

                    Reservation reservationToAdd = new Reservation(0, nomReservation, numTable, numTel, calendrier, idUser);
                    reservationService.ajouter(reservationToAdd);
                    break;

                case 2:
                    System.out.print("ID de la réservation à supprimer : ");
                    int idToDelete = scanner.nextInt();
                    reservationService.supprimer(idToDelete);

                    break;

                case 3:
                    System.out.print("ID de la réservation à modifier : ");
                    int idToUpdate = scanner.nextInt();
                    Reservation reservationToUpdate = reservationService.getOneById(idToUpdate);
                    if (reservationToUpdate != null) {
                        System.out.print("Nouveau nom de la réservation : ");
                        reservationToUpdate.setNomReservation(scanner.next());
                        System.out.print("Nouveau numéro de table : ");
                        reservationToUpdate.setNumTable(scanner.nextInt());
                        System.out.print("Nouveau numéro de téléphone : ");
                        reservationToUpdate.setNumTel(scanner.next());
                        System.out.print("Nouveau calendrier (YYYY-MM-DD) : ");
                        String newDateString = scanner.next();
                        reservationToUpdate.setCalendrier(java.sql.Date.valueOf(newDateString));
                        System.out.print("Nouvel ID de l'utilisateur : ");
                        reservationToUpdate.setIdUser(scanner.nextInt());

                        reservationService.modifier(reservationToUpdate);
                    } else {
                        System.out.println("Réservation non trouvée.");
                    }
                    break;

                case 4:
                    // Ajout d'une commande
                    System.out.print("Numéro de table : ");
                    int numTableCommande = scanner.nextInt();
                    System.out.print("ID de l'utilisateur : ");
                    int idUserCommande = scanner.nextInt();
                    System.out.print("Date et heure (YYYY-MM-DD HH:MM:SS) : ");
                    String dateTimeString = scanner.next();
                    System.out.print("Prix total : ");
                    double prixTotalCommande = scanner.nextDouble();

                    Commande commandeToAdd = new Commande(0, numTableCommande, idUserCommande, dateTimeString, prixTotalCommande);
                    commandeService.ajouter(commandeToAdd);
                    break;

                case 5:
                    // Suppression d'une commande
                    System.out.print("ID de la commande à supprimer : ");
                    int idToDeleteCommande = scanner.nextInt();
                    commandeService.supprimer(idToDeleteCommande);
                    break;
                case 6:
                    // Afficher tous les commandes
                    List<Commande> allCommandes = commandeService.getAll();
                    for (Commande cmd : allCommandes) {
                        System.out.println(cmd.getIdCommande() + ", " + cmd.getNumTable() + ", " + cmd.getIdUser() + ", " + cmd.getDateHeure() + ", " + cmd.getPrixTotale());
                    }
                    break;

                case 7:
                    // Modifier une commande
                    System.out.print("ID de la commande à modifier : ");
                    int idToUpdateCommande = scanner.nextInt();
                    Commande commandeToUpdate = commandeService.getOneById(idToUpdateCommande);
                    if (commandeToUpdate != null) {
                        System.out.print("Nouveau numéro de table : ");
                        commandeToUpdate.setNumTable(scanner.nextInt());
                        System.out.print("Nouvel ID de l'utilisateur : ");
                        commandeToUpdate.setIdUser(scanner.nextInt());
                        System.out.print("Nouvelle date et heure (YYYY-MM-DD HH:MM:SS) : ");
                        String newDateTimeString = scanner.next();
                        commandeToUpdate.setDateHeure(newDateTimeString);
                        System.out.print("Nouveau prix total : ");
                        commandeToUpdate.setPrixTotale(scanner.nextDouble());

                        commandeService.modifier(commandeToUpdate);
                    } else {
                        System.out.println("Commande non trouvée.");
                    }
                    break;


                case 8:
                    // Ajout d'une ligne de commande
                    System.out.print("ID de la commande : ");
                    int idCommandeCommandLine = scanner.nextInt();
                    System.out.print("ID du produit : ");
                    int idProduitCommandLine = scanner.nextInt();
                    System.out.print("Quantité : ");
                    int quantiteCommandLine = scanner.nextInt();
                    System.out.print("Prix total : ");
                    double prixTotalCommandLine = scanner.nextDouble();

                    CommandeLine commandLineToAdd = new CommandeLine(idCommandeCommandLine, idProduitCommandLine, quantiteCommandLine, prixTotalCommandLine);
                    CommandeLineService.ajouter(commandLineToAdd);
                    break;

                case 9:
                    // Suppression d'une ligne de commande
                    System.out.print("ID de la commande de la ligne à supprimer : ");
                    int idCommandeToDeleteCommandLine = scanner.nextInt();
                    CommandeLineService.supprimer(idCommandeToDeleteCommandLine);
                    break;
                case 10:
                    // Afficher toutes les lignes de commandes
                    List<CommandeLine> allCommandLines = CommandeLineService.getAll();
                    for (CommandeLine cl : allCommandLines) {
                        System.out.println(cl.getIdCommande() + ", " + cl.getIdProduit() + ", " + cl.getQuantite() + ", " + cl.getPrixTotale());
                    }
                    break;

                case 11:
                    // Modifier une ligne de commande
                    System.out.print("ID de la ligne de commande à modifier : ");
                    int idToUpdateCommandLine = scanner.nextInt();
                    CommandeLine commandLineToUpdate = CommandeLineService.getOneById(idToUpdateCommandLine);
                    if (commandLineToUpdate != null) {
                        System.out.print("Nouvel ID de la commande : ");
                        commandLineToUpdate.setIdCommande(scanner.nextInt());
                        System.out.print("Nouvel ID du produit : ");
                        commandLineToUpdate.setIdProduit(scanner.nextInt());
                        System.out.print("Nouvelle quantité : ");
                        commandLineToUpdate.setQuantite(scanner.nextInt());
                        System.out.print("Nouveau prix total : ");
                        commandLineToUpdate.setPrixTotale(scanner.nextDouble());

                        CommandeLineService.modifier(commandLineToUpdate);
                    } else {
                        System.out.println("Ligne de commande non trouvée.");
                    }
                    break;


                case 12:
                    List<Reservation> allReservations = reservationService.getAll();
                    for (Reservation r : allReservations) {
                        System.out.println(r.getIdReservation() + ", " + r.getNomReservation() + ", " + r.getNumTable() + ", " + r.getNumTel() + ", " + r.getCalendrier() + ", " + r.getIdUser());
                    }
                    break;

                case 13:
                    System.out.print("ID de la réservation à afficher : ");
                    int idToDisplay = scanner.nextInt();
                    Reservation reservationToDisplay = reservationService.getOneById(idToDisplay);
                    if (reservationToDisplay != null) {
                        System.out.println(reservationToDisplay.getIdReservation() + ", " + reservationToDisplay.getNomReservation() + ", " + reservationToDisplay.getNumTable() + ", " + reservationToDisplay.getNumTel() + ", " + reservationToDisplay.getCalendrier() + ", " + reservationToDisplay.getIdUser());
                    } else {
                        System.out.println("Réservation non trouvée.");
                    }
                    break;

                case 14:
                    exit = true;
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }
}
*/