/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevation;

import Entities.examen;
import Service.ServiceExamen;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
   
   private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            tb_ex_id.setCellValueFactory(new PropertyValueFactory< examen , Integer >("id_examen"));
            tb_mt_id.setCellValueFactory(new PropertyValueFactory< examen , Integer >("id_matiere"));
            tb_nm_mt.setCellValueFactory(new PropertyValueFactory<examen,String>("nom_matiere"));
            tb_ex_du.setCellValueFactory(new PropertyValueFactory<examen,String>("duree_ex"));
            tb_dt_ex.setCellValueFactory(new PropertyValueFactory<examen,java.sql.Date>("date_ex"));
             
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
    private void AjouterExamen(ActionEvent event) {
       
        examen e= new examen();
        e.setNom_matiere(tfmatiere.getText());
        e.setDuree_ex(tfdureeex.getText());
        e.setDate_ex(java.sql.Date.valueOf(tfdateex.getValue()));
    
       se.AddExamen(e);
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
           
            se.updateExamen(selectedId, e);
            try {
                tableId.setItems(se.AfficherExamen());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't modify please select an item form the table");
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        if (canModify) {
        se.deleteExamen(selectedId);
        try {
                tableId.setItems(se.AfficherExamen());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't delete please select an item form the table");
    }
}
    

