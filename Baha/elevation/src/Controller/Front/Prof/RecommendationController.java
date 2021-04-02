/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front.Prof;

import Controller.Front.Eleve.*;
import Entities.Recommendation;
import Entities.Utilisiateur;
import Services.RecommendationService;
import Services.UtilisateurService;
import Utils.PopUp;
import Utils.Session;
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
import javafx.scene.control.Button;
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
 * @author user
 */
public class RecommendationController implements Initializable {
  @FXML
    private TableView<Utilisiateur> tb_table;
    
    private TextField tfSujet;
    @FXML
    private TextArea tfComment;
    @FXML
    private TextField tf_recherche;
    
    RecommendationService rs ;
    UtilisateurService us ;
    @FXML
    private TableColumn<Utilisiateur, Integer> idCol;
    @FXML
    private TableColumn<Utilisiateur, String> NomCol;
    @FXML
    private TableColumn<Utilisiateur, String> PrenomCol;
    @FXML
    private Button recommendBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rs = new RecommendationService();
        us = new UtilisateurService();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
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
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
        Recommendation rec = new Recommendation();
        if(tb_table.getSelectionModel() != null)
        {   
            rec.setSujet("prof recommendation");
            rec.setComment(tfComment.getText());
            rec.setUser(tb_table.getSelectionModel().getSelectedItem());
            rec.setEtat("Recmmended by : " + String.valueOf(Session.getConnectedUser().getId()));
             DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        rec.setDate(sqlDate);
        
          
        if(tfComment.getText().isEmpty())
            PopUp.Error("Champ est vide");
         else 
         {
        rs.ajouter(rec);
             PopUp.Success("Recommendé");
         }
           
            refresh_list();
            clearSelection(event);
        }
    }

    @FXML
    private void clearSelection(ActionEvent event) {
        
        tb_table.getSelectionModel().clearSelection();
       tfComment.setText("");
       
    }

    private void Ajouter(ActionEvent event) {
        Recommendation rec = new Recommendation();
        rec.setComment(tfComment.getText());
        rec.setSujet(tfSujet.getText());
        rec.setEtat("En cours de traitement");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        rec.setDate(sqlDate);
        
        if(tfComment.getText().isEmpty())
            PopUp.Error("Champ Justificatif est vide");
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
        List<Utilisiateur> list = us.getUtilisiateurs();
        tb_table.getItems().setAll(list);
        
        recommendBtn.setDisable(true);
    }

    @FXML
    private void supprimer(ActionEvent event) {
       
    }

    @FXML
    private void recherche(KeyEvent event) {
        
        if( !tf_recherche.getText().isEmpty() || tf_recherche.getText() != null )
        { 
            List<Utilisiateur> list = us.getUtilisiateurs();
            if(list != null)
            tb_table.getItems().setAll(list);
        }else 
        {
  List<Utilisiateur> list = us.getUtilisiateurs();
  tb_table.getItems().setAll(list);
        }
    }
     void plainSelection(Utilisiateur dep)
    {
         if(tb_table.getSelectionModel() != null)
        {
            recommendBtn.setDisable(false);
        }
    }
    
}
