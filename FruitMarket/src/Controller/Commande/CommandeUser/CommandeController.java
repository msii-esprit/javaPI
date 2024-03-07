package Controller.Commande.CommandeUser;


import java.awt.*;
import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.image.Image;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.CommandeMyListener;
import main.Main;
import models.Commande;
import services.CommandeService;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {

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
    private ImageView fruitImg;
    @FXML
    private Label quantite;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchBox;
    @FXML
    private GridPane grid;

    private List<Commande> fruits = new ArrayList<>();
    private Image image;
    private CommandeMyListener commandeMyListener;
    private Commande commande;
    ProduitService ps=new ProduitService();
    CommandeService cs=new CommandeService();


    @FXML
    void rechercher(KeyEvent event)  {
        System.out.println("oooooooo44ooooo");
        System.out.println(fruits.size());
        ObservableList<Commande> obslist = FXCollections.observableList(fruits);
        System.out.println("ooooooooooooo1");

        FilteredList<Commande> filterData = new FilteredList<>(obslist, p -> true);

        System.out.println("ooooooooooooo2");
        System.out.println(filterData.size());
        System.out.println(searchBox.getText());

        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            System.out.println("ooooooooooooo3");

            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();
                System.out.println("ooooooooooooo4");

                if (String.valueOf(candida.getProduit().getNom()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });

            grid.getChildren().clear();
            grid.getChildren().removeAll();

            SortedList<Commande > sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);
            System.out.println(sortedList.size());
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < sortedList.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/Commande/itemCommande.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemCommandeController itemController = fxmlLoader.getController();
                    itemController.setData(fruits.get(i),commandeMyListener);
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

            PdfPTable tabpdf = new PdfPTable(3);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            System.out.println("heelo");

            cell = new PdfPCell(new Phrase("nom", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            cell = new PdfPCell(new Phrase("prix", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("quatite", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            for (Commande Candidature : fruits) {
                System.out.println("kkkkkkkkkkkkkkk");
                tabpdf.addCell(Candidature.getProduit().getNom());
                tabpdf.addCell(String.valueOf( Candidature.getProduit().getPrix()*Candidature.getQuantite()));
                tabpdf.addCell(String.valueOf( Candidature.getQuantite()));


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

            commande.getProduit().setQuantite( commande.getProduit().getQuantite()+commande.getQuantite());
        ps.modifier(commande.getProduit());
        cs.supprimer(commande.getId());
        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Commande supprimer avec succes ");
        alert.showAndWait();
    }

    @FXML
    void onClickCommande(ActionEvent event) throws Exception {
       commande.setQuantite(Integer.parseInt(QuantitField.getText()));
       if (commande.getQuantite()>Integer.parseInt(QuantitField.getText()))
       commande.getProduit().setQuantite( commande.getProduit().getQuantite()+(commande.getQuantite()-Integer.parseInt(QuantitField.getText())));
       else
           commande.getProduit().setQuantite( commande.getProduit().getQuantite()-(Integer.parseInt(QuantitField.getText())-commande.getQuantite()));


        ps.modifier(commande.getProduit());
        cs.modifier(commande);
        update();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Commande modififer avec succe");
        alert.showAndWait();

    }
/////////////////////////////



    ////////////////////////////


    private void setChosenFruit(Commande commande) {

        this.commande=commande;

        fruitNameLable.setText(commande.getProduit().getNom());
        fruitPriceLabel.setText(Main.CURRENCY + (commande.getProduit().getPrix())*commande.getQuantite());
        QuantitField.setText(String.valueOf( commande.getQuantite()));

        image = new Image (getClass().getResourceAsStream(commande.getProduit().getImagePath()));
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
            fruits.addAll(cs.recuperer());
            System.out.println(fruits.size());

        } catch (SQLException e) {
            System.out.println(e);        }
        if (!fruits.isEmpty()) {
            setChosenFruit(fruits.get(0));
            commandeMyListener = new CommandeMyListener() {
                @Override
                public void onClickListener(Commande commande) {
                    setChosenFruit(commande);
                }
            };
        }



        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();

            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Commande/itemCommande.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCommandeController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),commandeMyListener);

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
