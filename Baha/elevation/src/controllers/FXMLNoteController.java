/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import Entities.note;
import Service.ServiceExamen;
import Service.ServiceNote;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author infoplus
 */
public class FXMLNoteController implements Initializable {

    @FXML
    private TextField tfnotecc;
    @FXML
    private TextField tfnoteex;
    @FXML
    private TableView<note> tableId2;
    @FXML
    private TableColumn<note, Integer> tb_ex_id1;
    @FXML
    private TableColumn<note, Integer> tb_mt_id1;
    @FXML
    private TableColumn<examen, String> tb_nm_mt1;
    @FXML
    private TableColumn<note, String> tb_nt_cc;
    @FXML
    private TableColumn<note, String> tb_nt_ex;
    
    private ServiceNote sn = new ServiceNote();
    private ServiceExamen se = new ServiceExamen();
    
   private int selectedId2;
   boolean canModify = false;
    @FXML
    private TextField tfexamenid;
    @FXML
    private HBox Exams;
    private String n1=null;
    private String n2=null;
    private String n3=null;
    
    
       private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            tb_ex_id1.setCellValueFactory(new PropertyValueFactory< note , Integer >("id_examen"));
            tb_mt_id1.setCellValueFactory(new PropertyValueFactory< note , Integer >("id_note"));
            tb_nm_mt1.setCellValueFactory(new PropertyValueFactory< examen,String>("nom_matiere"));
            tb_nt_cc.setCellValueFactory(new PropertyValueFactory< note,String>("note_cc"));
            tb_nt_ex.setCellValueFactory(new PropertyValueFactory< note,String>("note_ex"));
             
            tableId2.setItems(sn.AfficherNote());
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableId2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId2.getSelectionModel().getSelectedItem() != null) {
                    note selectedNote = tableId2.getSelectionModel().getSelectedItem();
                    tfexamenid.setText(String.valueOf(selectedNote.getId_examen()));
                    tfnotecc.setText(selectedNote.getNote_cc());
                    tfnoteex.setText(selectedNote.getNote_ex());
                    
                    
                    selectedId2 = selectedNote.getId_note();
                    canModify = true;
                }
            }
        });

    }    

    @FXML
    private void AjouterNote(ActionEvent event) {
         note n= new note();
         
        n.setId_examen(Integer.valueOf(tfexamenid.getText()));
        n.setNote_cc(tfnotecc.getText());
        n.setNote_ex(tfnoteex.getText());
        
        n2=tfnotecc.getText();
        n3=tfnoteex.getText();
        if (n2.length()==0){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                        a1.setTitle("Alert");
                        a1.setHeaderText("Alert (saisie) ");
                        a1.setContentText("le champ du Note Controle continue est vide");
                        a1.showAndWait();
        }
        else if (n3.length()==0){
         Alert a1 = new Alert(Alert.AlertType.INFORMATION); 
                        a1.setTitle("Alert");
                        a1.setHeaderText("Alert (saisie) ");
                        a1.setContentText("le champ du Note examen est vide");
                        a1.showAndWait();
        }
        else {
        notifajout();
       sn.AddNote(n);
        }
       try {
            tableId2.setItems(sn.AfficherNote());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ModifierNote(ActionEvent event) {
        if (canModify) {
            note n = new note();
           n.setId_examen(Integer.valueOf(tfexamenid.getText()));
        n.setNote_cc(tfnotecc.getText());
        n.setNote_ex(tfnoteex.getText());
           notifmod();
            sn.updateNote(selectedId2, n);
            try {
                tableId2.setItems(sn.AfficherNote());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't modify please select an item form the table");
    }

    @FXML
    private void deleteAction2(ActionEvent event) {
        if (canModify) {
            notifsup();
        sn.deletNote(selectedId2);
        try {
                tableId2.setItems(sn.AfficherNote());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't delete please select an item form the table");
    }

 
    

    @FXML
    private void movetoexamen(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

  

    @FXML
    private void movetofront(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/HOME.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void movetonote(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/FXMLNote.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    private void notifajout(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Note Ajoutée")
                    .text("Felicitation ! Votre note a été ajoutée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }
    private void notifmod(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Note Modifiée")
                    .text("Felicitation ! Votre note a été Modifiée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }
    private void notifsup(){
            Notifications notificationBuilder = Notifications.create()
                    .title("Note Supprimée")
                    .text("Felicitation ! Votre note a été Supprimée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .hideCloseButton()
                    .darkStyle()
                    .position(Pos.BOTTOM_RIGHT);
                  
            notificationBuilder.show();
    }

    }
    

