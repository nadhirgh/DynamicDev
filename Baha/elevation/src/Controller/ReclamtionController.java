/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Reclamation;
import Services.ReclamationService;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class ReclamtionController implements Initializable {

    @FXML
    private TableView<Reclamation> tb_table;
    @FXML
    private TableColumn<Reclamation, String> tc_sujet;
    @FXML
    private TableColumn<Reclamation, String> tc_comment;
    @FXML
    private TableColumn<Reclamation, Date> tc_date;
    @FXML
    private TableColumn<Reclamation, String> tc_etat;
    private TextField tfSujet;
    private TextArea tfComment;
    @FXML
    private TextField tf_recherche;
    
    ReclamationService rs ;
    @FXML
    private ComboBox<String> cbEtat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rs = new ReclamationService();
        
        tc_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tc_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        
        tb_table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       
        List<Reclamation> list = rs.afficher();
        tb_table.getItems().addAll(list);
        
        
        tb_table.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
            @Override
            public void handle(MouseEvent event) {
               if(!tb_table.getSelectionModel().isEmpty())
                   plainSelection(tb_table.getSelectionModel().getSelectedItem());
            }
                  
                });
        
        cbEtat.getItems().add("En cours de Traitement");
        cbEtat.getItems().add("Traité");
        cbEtat.getItems().add("Annulé");
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
        Reclamation rec = new Reclamation();
        if(tb_table.getSelectionModel() != null)
        {    rec.remplirReclamation(tb_table.getSelectionModel().getSelectedItem()) ;
            rec.setEtat(cbEtat.getSelectionModel().getSelectedItem());
            rs.modifier(rec);
            refresh_list(event);
            clearSelection(event);
        }
    }

    private void clearSelection(ActionEvent event) {
        
        tb_table.getSelectionModel().clearSelection();
        
       
    }

    private void Ajouter(ActionEvent event) {
        Reclamation rec = new Reclamation();
        rec.setComment(tfComment.getText());
        rec.setSujet(tfSujet.getText());
        rec.setEtat("En cours de traitement");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        rec.setDate(sqlDate);
        
        rs.ajouter(rec);
        refresh_list(event);
        clearSelection(event);
    }

    @FXML
    private void refresh_list(ActionEvent event) {
         List<Reclamation> list = rs.afficher();
        tb_table.getItems().setAll(list);
    }

    private void supprimer(ActionEvent event) {
         if(tb_table.getSelectionModel() != null)
        rs.supprimer(tb_table.getSelectionModel().getSelectedItem().getId()) ;
        List<Reclamation> list = rs.afficher();
        tb_table.getItems().setAll(list);
        clearSelection(event);
    }

    @FXML
    private void recherche(KeyEvent event) {
        
        if( !tf_recherche.getText().isEmpty() || tf_recherche.getText() != null )
        { 
            List<Reclamation> list = rs.Recherche(tf_recherche.getText());
            if(list != null)
            tb_table.getItems().setAll(list);
        }else 
        {
            List<Reclamation> list = rs.afficher();
            tb_table.getItems().setAll(list);
        }
    }
     void plainSelection(Reclamation dep)
    {
         if(tb_table.getSelectionModel() != null)
        {
            cbEtat.getSelectionModel().select(dep.getEtat());
        }
    }
    
}
