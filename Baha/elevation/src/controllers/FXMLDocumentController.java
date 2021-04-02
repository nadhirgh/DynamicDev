/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import Entities.user;
import static Service.Constants.projectPath;
import Service.ServiceExamen;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.controlsfx.control.Notifications;

/**
 *
 * @author infoplus
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private DatePicker tfdateex;
    @FXML
    private TextField tfmatiere;
    @FXML
    private TextField tfdureeex;
    @FXML
    private TableView<examen> tableId;
    @FXML
    private TableColumn<examen, Integer> tb_ex_id;
    @FXML
    private TableColumn<examen, Integer> tb_mt_id;
    @FXML
    private TableColumn<examen, String> tb_nm_mt;
    @FXML
    private TableColumn<examen, String> tb_ex_du;
    @FXML
    private TableColumn<examen, java.sql.Date> tb_dt_ex;

    private ServiceExamen se = new ServiceExamen();
    private int selectedId;
    boolean canModify = false;
    @FXML
    private TextArea listview;
    @FXML
    private Button AjouterExamen;
    @FXML
    private Button addfile;
    @FXML
    private HBox Exams;
    private String n1=null;
    private String n2=null;
    

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            tb_ex_id.setCellValueFactory(new PropertyValueFactory< examen, Integer>("id_examen"));
            tb_mt_id.setCellValueFactory(new PropertyValueFactory< examen, Integer>("id_matiere"));
            tb_nm_mt.setCellValueFactory(new PropertyValueFactory<examen, String>("nom_matiere"));
            tb_ex_du.setCellValueFactory(new PropertyValueFactory<examen, String>("duree_ex"));
            tb_dt_ex.setCellValueFactory(new PropertyValueFactory<examen, java.sql.Date>("date_ex"));

            tableId.setItems(se.AfficherExamen());

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId.getSelectionModel().getSelectedItem() != null) {
                    examen selectedExamen = tableId.getSelectionModel().getSelectedItem();
                    tfmatiere.setText(selectedExamen.getNom_matiere());
                    tfdureeex.setText(selectedExamen.getDuree_ex());
                    tfdateex.setValue(selectedExamen.getDate_ex().toLocalDate());

                    selectedId = selectedExamen.getId_examen();
                    canModify = true;
                }
            }
        });

    }

    @FXML
    private void AjouterExamen(ActionEvent event) throws IOException {

        examen e = new examen();
        e.setNom_matiere(tfmatiere.getText());
        e.setDuree_ex(tfdureeex.getText());
        e.setDate_ex(java.sql.Date.valueOf(tfdateex.getValue()));
        e.setPDF(listview.getText());
        n1=tfmatiere.getText();
        n2=tfdureeex.getText();
        if ((n1.length()==0)|| (n1.length() > 4)){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                        a1.setTitle("Alert");
                        a1.setHeaderText("Alert (saisie) ");
                        a1.setContentText("le nom de matiere doit etre compris entre 0 et 4 lettres");
                        a1.showAndWait();
        }
        else if (n2.length()==0){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                        a1.setTitle("Alert");
                        a1.setHeaderText("Alert (saisie) ");
                        a1.setContentText("le champ du duree examen est vide");
                        a1.showAndWait();
        }
        else {
        notifajout();
        QRcode();
        se.AddExamen(e);
        }
        try {
            tableId.setItems(se.AfficherExamen());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierExamen(ActionEvent event) {

        if (canModify) {
            examen e = new examen();
            e.setNom_matiere(tfmatiere.getText());
            e.setDuree_ex(tfdureeex.getText());
            e.setDate_ex(java.sql.Date.valueOf(tfdateex.getValue()));
            String m = tfmatiere.getText();
            e.setPDF(listview.getText());
            notifmod();
            se.updateExamen(selectedId, e);
            try {
                tableId.setItems(se.AfficherExamen());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't modify please select an item form the table");
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        if (canModify) {
            notifsup();
            se.deleteExamen(selectedId);
            try {
                tableId.setItems(se.AfficherExamen());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't delete please select an item form the table");
        }
    }

    private void returntohome(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHome.fxml"));
//                Parent root=loader.load();
//                FXMLHomeController s2 = loader.getController();
//                Stage stage =new Stage();
//                
//                stage.setScene(new Scene(root));
//                stage.show();
//                ((Node) (event.getSource())).getScene().getWindow().hide();

        Parent page1 = null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/FXMLHome.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AddPDF(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String s = "C:\\Users\\infoplus\\Desktop\\Exams";
        fc.setInitialDirectory(new File("C:\\Users\\infoplus\\Desktop\\Exams"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            //listview.setText(String.valueOf(selectedFile));
            listview.setText(String.valueOf(selectedFile).substring(s.length() - 0));
        } else {
            System.out.println("file is not valid");
        }

    }


    @FXML
    private void movetonote(MouseEvent event) {
        Parent page1 = null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/FXMLNote.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void movetofront(MouseEvent event) {
        user u=new user();
        
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Examsview.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(NotesviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ExamsviewController ien=Loader.getController();
        ien.us=u;
        ien.setuserNamer(u.getNom());
                Parent ap=Loader.getRoot();
                //Stage ins=new Stage();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(ap);
                stage.setScene(scene);
               stage.show();
    }

    @FXML
    private void movetoexamen(MouseEvent event) {
        Parent page1 = null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void QRcode() throws FileNotFoundException, IOException {
        String contenue = "Nom matiére: " + tfmatiere.getText() + "\n" + "Durée: " + tfdureeex.getText() + "\n" + "date: " + java.sql.Date.valueOf(tfdateex.getValue());
        ByteArrayOutputStream out = QRCode.from(contenue).to(ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\images\\QRExamen\\" + tfmatiere.getText()+tfdureeex.getText()+java.sql.Date.valueOf(tfdateex.getValue()) + ".jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();

    }
    private void notifajout(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Examen Ajoutée")
                    .text("Felicitation ! Votre examen a été ajoutée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }
    private void notifmod(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Examen Modifiée")
                    .text("Felicitation ! Votre examen a été Modifiée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }
    private void notifsup(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Examen Supprimée")
                    .text("Felicitation ! Votre examen a été Supprimée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }

}
