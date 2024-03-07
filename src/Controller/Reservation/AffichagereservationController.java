package Controller.Reservation;

import Controller.ItemController;
import Main.MainJavaFX;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Reservation;
import entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceMenu;
import services.ServiceReservation;
import services.ServiceReservation;
import utils.MyListener;
import utils.MyListenerReservation;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffichagereservationController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button commandeButton;
    @FXML
    private TextField QuantitField;
    @FXML
    private Label labelNomReservation;

    @FXML
    private TextField fruitPriceLabel;
    @FXML
    private TextField searchbox;
    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Reservation> fruits = new ArrayList<>();
    private Image image;
    private MyListenerReservation myListener;
    private Reservation reservation;
    ServiceMenu sm =new ServiceMenu();


    @FXML
    void rechercher() {
       System.out.println("ahhhhhhhhhhh");
        ObservableList<Reservation> obslist = FXCollections.observableList(fruits);


        FilteredList<Reservation> filterData = new FilteredList<>(obslist, p -> true);



       searchbox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {

            filterData.setPredicate(Menu -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (String.valueOf(Menu.getCalendrier()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Reservation > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);

            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < sortedList.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemReservationController itemController = fxmlLoader.getController();
                    itemController.setData(sortedList.get(i),myListener);
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




   /* @FXML
    void onClickCommande(ActionEvent event) throws Exception {
   Menu.setQuantite(produit.getQuantite()-Integer.parseInt(QuantitField.getText()));
ps.modifier(produit);
        LocalDate currentDate = LocalDate.now();

        // Convert LocalDate to java.sql.Date
        Date sqlDate = Date.valueOf(currentDate);

        cs.ajouter(new Commande(produit,Integer.parseInt(QuantitField.getText()),sqlDate));
        update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation added successfully!");
        alert.showAndWait();

    }*/
/////////////////////////////



    ////////////////////////////

    @FXML
    void ModifierAction(ActionEvent event) {
        ServiceReservation sr=new ServiceReservation();
        reservation.setTemps(fruitPriceLabel.getText());
        sr.modifier(reservation);
        fruits.clear();

        update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Reservation supprimee avec succes ");
        alert.showAndWait();
    }



    @FXML
    void supprimerAction(ActionEvent event) {
ServiceReservation sr=new ServiceReservation();
        sr.supprimer(reservation.getIdReservation());
        fruits.clear();

        update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Reservation supprimee avec succes ");
        alert.showAndWait();
    }

    private void setChosenFruit(Reservation fruit) {
        this.reservation=fruit;
        labelNomReservation.setText(String.valueOf(  fruit.getCalendrier()));
        fruitPriceLabel.setText(fruit.getTemps());

        String imagePath = "../img/Restaurant.jpg" ;
        File file = new File(imagePath);


        //image = new Image(getClass().getResourceAsStream("../img/chicken.jpg"));
        Image image = new Image("/img/Restaurant.jpg");

        fruitImg.setImage(image);

        chosenFruitCard.setStyle("-fx-background-color: #" + "F16C31" + ";\n" +
                "    -fx-background-radius: 30;");
    }
    private Stage stage;
    private Scene scene;






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
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Liste des reservation ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(3);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            System.out.println("heelo");

            cell = new PdfPCell(new Phrase("date", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            cell = new PdfPCell(new Phrase("temps", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("Numero Table", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            for (Reservation ca : fruits) {
                System.out.println("kkkkkkkkkkkkkkk");
                tabpdf.addCell(String.valueOf( ca.getCalendrier()));
                tabpdf.addCell(String.valueOf( ca.getTemps()));
                tabpdf.addCell(String.valueOf( ca.getNumTable()));


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


        } catch (DocumentException  | IOException e) {
            System.out.println("ERROR PDF");
        }
    }






    void  update(){
        grid.getChildren().clear();
        ServiceReservation ps=new ServiceReservation();
        try {
            fruits.clear();
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            System.out.println(ps.getAll().size());
            fruits.addAll(ps.getAll());
        } catch (Exception e) {
            System.out.println(e);        }
        if (!fruits.isEmpty()) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListenerReservation() {
                @Override
                public void onClickListener(Reservation fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../views/itemReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemReservationController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

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
