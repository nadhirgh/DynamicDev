/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front.Eleve;

import Entities.Reclamation;
import Services.ReclamationService;
import Services.RecommendationService;
import Utils.PopUp;
import Utils.Session;
import Utils.TextToSpeach;
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
import javafx.scene.control.Label;
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
public class ReclamationController implements Initializable {

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
    @FXML
    private TextField tfSujet;
    @FXML
    private TextArea tfComment;
    @FXML
    private TextField tf_recherche;
    
    ReclamationService rs ;
    RecommendationService recomms;
    @FXML
    private Label nbrLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rs = new ReclamationService();
        recomms = new RecommendationService();
        tc_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tc_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        
        tb_table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       
        refresh_list();
        
        
        tb_table.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
            @Override
            public void handle(MouseEvent event) {
               if(!tb_table.getSelectionModel().isEmpty())
                   plainSelection(tb_table.getSelectionModel().getSelectedItem());
            }
                  
                });
        
        nbrLabel.setText(String.valueOf(recomms.RecommendationsByUser().size()));
        TextToSpeach.speak("You have " + nbrLabel.getText() + "Recommendations !" );
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
        Reclamation rec = new Reclamation();
        if(tb_table.getSelectionModel() != null)
        {    rec.remplirReclamation(tb_table.getSelectionModel().getSelectedItem()) ;
            rec.setSujet(tfSujet.getText());
            rec.setComment(tfComment.getText());
            rs.modifier(rec);
            refresh_list();
            clearSelection(event);
        }
    }

    @FXML
    private void clearSelection(ActionEvent event) {
        
        tb_table.getSelectionModel().clearSelection();
        tfSujet.setText("");
       tfComment.setText("");
       
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        Reclamation rec = new Reclamation();
        rec.setComment(tfComment.getText());
        rec.setSujet(tfSujet.getText());
        rec.setEtat("En cours de traitement");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        rec.setDate(sqlDate);
         if(tfComment.getText().isEmpty())
            PopUp.Error("Champ Justificatif est vide");
        else if(tfSujet.getText().isEmpty())
            PopUp.Error("Champ  vide");
                 else 
         {
             rs.ajouter(rec);
             PopUp.Success("Ajout avec Succes");
         }
        refresh_list();
        clearSelection(event);
    }

    @FXML
    private void refresh_list() {
         List<Reclamation> list = rs.ReclamationsByUser(Session.getConnectedUser());
        tb_table.getItems().setAll(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
         if(tb_table.getSelectionModel() != null)
        rs.supprimer(tb_table.getSelectionModel().getSelectedItem().getId()) ;
        refresh_list();
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
            refresh_list();
        }
    }
     void plainSelection(Reclamation dep)
    {
         if(tb_table.getSelectionModel() != null)
        {
       tfSujet.setText(dep.getSujet());
       tfComment.setText(dep.getComment());
        }
    }
    
}
