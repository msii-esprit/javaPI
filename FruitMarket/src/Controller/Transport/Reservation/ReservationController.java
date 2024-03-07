package Controller.Transport.Reservation;


import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.CommandeMyListener;
import main.Main;
import main.ReservationMyListener;
import models.Commande;
import models.Reservation;
import services.CommandeService;
import services.ProduitService;
import services.ReservationService;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button commandeButton;
    @FXML
    private TextField QuantitField;
    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;
    @FXML
    private TextField EmailField;

    @FXML
    private TextField NameField;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label quantite;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchBox;
    @FXML
    private GridPane grid;

    private List<Reservation> fruits = new ArrayList<>();
    private Image image;
    private ReservationMyListener reservationMyListener;
    private Reservation reservation;
    ProduitService ps=new ProduitService();
    ReservationService rs=new ReservationService();


    @FXML
    void rechercher(KeyEvent event)  {
        ObservableList<Reservation> obslist = FXCollections.observableList(fruits);

        FilteredList<Reservation> filterData = new FilteredList<>(obslist, p -> true);



        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {

            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();
                System.out.println("ooooooooooooo4");

                if (String.valueOf(candida.getVehicule().getNom()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Reservation > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);
            System.out.println(sortedList.size());
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < sortedList.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/Reservation/itemReservation.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    itemReservationController itemController = fxmlLoader.getController();
                    itemController.setData(fruits.get(i),reservationMyListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            // table.setItems(sortedList);


        });

    }


    @FXML
    private void createpdf() throws SQLException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc,new FileOutputStream("../pdf.pdf"));
            doc.open();

//       Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       doc.add(img);

            doc.add(new Paragraph(" "));
            Font font = new Font(FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Liste des commande ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(4);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            System.out.println("heelo");

            cell = new PdfPCell(new Phrase("nom", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            cell = new PdfPCell(new Phrase("email", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("nom Vehicule", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("prix Vehicule", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            for (Reservation reservation : fruits) {
                System.out.println("kkkkkkkkkkkkkkk");
                tabpdf.addCell(reservation.getNom());
                tabpdf.addCell(String.valueOf( reservation.getEmail()));
                tabpdf.addCell(String.valueOf( reservation.getVehicule().getNom()));
                tabpdf.addCell(String.valueOf( reservation.getVehicule().getPrix()));


            }


/*
for(int i =0;i<candidaturesListe.size();i++){
    cell = new PdfPCell(new Phrase(.getString("date_res"), FontFactory.getFont("Times New Roman", 11)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.WHITE);
    tabpdf.addCell(cell);

    cell = new PdfPCell(new Phrase(rs1.getString("prix_res"), FontFactory.getFont("Times New Roman", 11)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.WHITE);
    tabpdf.addCell(cell);
}
            for (candidature rowData : candidaturesListe) {
              //  for (String cellData : rowData) {
                    Cell nameCell = new Cell().add(new Text(rowData.getUtilisateur().getUsername()).setFont(font));
                    Cell ageCell = new Cell().add(new Text(Integer.toString(student.getAge())).setFont(font));
                    Cell cityCell = new Cell().add(new Text(student.getCity()).setFont(font));

                    table.addCell(nameCell);
                    table.addCell(ageCell);
                    table.addCell(cityCell);
                    //Cell cell = new Cell().add(new Text(cellData).setFont(font));
                    //table.addCell(cell);
                //}
            }
            while(rs1.next()){
                System.out.println(rs1.getString("date_res"));
                cell = new PdfPCell(new Phrase(rs1.getString("date_res"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs1.getString("prix_res"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);


            }
*/


            System.out.println(fruits.size());
            doc.add(tabpdf);
            doc.close();
            Desktop.getDesktop().open(new File("../pdf.pdf"));


        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onClickDelete(ActionEvent event) throws Exception {

        rs.supprimer(reservation.getId());
        rs.supprimer(reservation.getId());
        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Commande supprimer avec succes ");
        alert.showAndWait();
    }

    @FXML
    void onClickEdit(ActionEvent event) throws Exception {


reservation.setNom(NameField.getText());
reservation.setEmail(EmailField.getText());

        rs.modifier(reservation);
        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Reservation modififer avec succe");
        alert.showAndWait();

    }
/////////////////////////////



    ////////////////////////////


    @FXML
    private Label destinationLabel;

    private void setChosenFruit(Reservation reservation) {

        this.reservation=reservation;

        fruitNameLable.setText(reservation.getVehicule().getNom());
        fruitPriceLabel.setText(Main.CURRENCY + (reservation.getVehicule().getPrix()));
        destinationLabel.setText(reservation.getVehicule().getdestination());
        if(reservation.getVehicule().getType().equals("metro"))

        image = new Image (getClass().getResourceAsStream("/img/metro.png"));
        else
            image = new Image (getClass().getResourceAsStream("/img/bus.png"));
        NameField.setText(reservation.getNom());
        EmailField.setText(reservation.getEmail());
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + "F16C31" + ";\n" +
                "    -fx-background-radius: 30;");
    }
    private Stage stage;
    private Scene scene;

    @FXML
    void switchPage(ActionEvent event) {


        try {
            Parent root=FXMLLoader.load(getClass().getResource("../views/Market/market.fxml"));
stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

stage.show();
            // Set the stage's scene to the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    void  update(){
        fruits.clear();
        System.out.println("popoooo");
        System.out.println(fruits.size());

        try {
            fruits.addAll(rs.recuperer());
            System.out.println(fruits.size());

        } catch (SQLException e) {
            System.out.println(e);        }
        if (!fruits.isEmpty()) {
            setChosenFruit(fruits.get(0));
            reservationMyListener = new ReservationMyListener() {
                @Override
                public void onClickListener(Reservation reservation) {
                    setChosenFruit(reservation);
                }
            };
        }



        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();

            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Reservation/itemReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemReservationController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),reservationMyListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();
    }

}
