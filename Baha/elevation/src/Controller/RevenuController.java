/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Revenu;
import Entities.Utilisiateur;
import Services.RevenuService;
import Services.UtilisateurService;
import Utils.PopUp;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class RevenuController implements Initializable {

    @FXML
    private TextField tfValeur;
    @FXML
    private TextField tfSource;
    @FXML
    private TextField tfJustificatif;
    @FXML
    private TextField tfDescription;
    
    RevenuService ds ;
    @FXML
    private TableView<Revenu> tb_revenu ;
    @FXML
    private TableColumn<Revenu, Integer> tc_id_finance;
    @FXML
    private TableColumn<Revenu, Double> tc_valeur;
    @FXML
    private TableColumn<Revenu, Date> tc_date;
    @FXML
    private TableColumn<Revenu, String> tc_source;
    @FXML
    private TableColumn<Revenu, String> tc_description;
    @FXML
    private TableColumn<Revenu, String> tc_justificatif;
    @FXML
    private TextField tf_recherche;
    @FXML
    private ComboBox<Utilisiateur> cbUser;

        UtilisateurService us ;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ds = new RevenuService();
        us = new UtilisateurService();
        tc_valeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_id_finance.setCellValueFactory(new PropertyValueFactory<>("id_finance"));
        tc_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tc_justificatif.setCellValueFactory(new PropertyValueFactory<>("justificatif"));
        tc_source.setCellValueFactory(new PropertyValueFactory<>("source"));
        tb_revenu.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        List<Revenu> list = ds.afficher();
        tb_revenu.getItems().addAll(list);
        
        
        tb_revenu.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
            @Override
            public void handle(MouseEvent event) {
               if(!tb_revenu.getSelectionModel().isEmpty())
                   plainSelection(tb_revenu.getSelectionModel().getSelectedItem());
            }
                  
                });
        
             cbUser.getItems().setAll(us.getUtilisiateurs());

    }    

    @FXML
    private void AjouterRevenu(ActionEvent event) {
        
        Revenu dep = new Revenu();
      
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        dep.setDate(sqlDate);
        dep.setId_finance(0);
        dep.setJustificatif(tfJustificatif.getText());
        dep.setSource(tfSource.getText());
        dep.setDescription(tfDescription.getText());
        dep.setUser(cbUser.getSelectionModel().getSelectedItem());
        
         if(tfJustificatif.getText().isEmpty())
            PopUp.Error("Champ Justificatif est vide");
        else if(tfDescription.getText().isEmpty())
            PopUp.Error("Champ  vide");
         else if(tfSource.getText().isEmpty())
            PopUp.Error("Champ  vide");
         else 
         {
             ds.ajouter(dep);
             PopUp.Success("Ajout avec Succes");
         }
        
      
        refresh_list(event);
        clearSelection();
    }

    @FXML
    private void refresh_list(ActionEvent event) {
        
        List<Revenu> list = ds.afficher();
        tb_revenu.getItems().setAll(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
        if(tb_revenu.getSelectionModel() != null)
        ds.supprimer(tb_revenu.getSelectionModel().getSelectedItem().getId()) ;
        List<Revenu> list = ds.afficher();
        tb_revenu.getItems().setAll(list);
        clearSelection();
    }

    @FXML
    private void ModifierRevenu(ActionEvent event) {
        Revenu dep = new Revenu();
        if(tb_revenu.getSelectionModel() != null)
        {    dep.remplirRevenu(tb_revenu.getSelectionModel().getSelectedItem()) ;
            dep.setDescription(tfDescription.getText());
            dep.setJustificatif(tfJustificatif.getText());
            dep.setSource(tfSource.getText());
            dep.setValeur(Double.parseDouble(tfValeur.getText()));
            ds.modifier(dep);
            refresh_list(event);
            clearSelection();
            
        }
    }
    
    @FXML
    void clearSelection()
    {
        tb_revenu.getSelectionModel().clearSelection();
        tfDescription.setText("");
       tfJustificatif.setText("");
       tfValeur.setText("");
       tfSource.setText("");

    }
    

    void plainSelection(Revenu dep)
    {
         if(tb_revenu.getSelectionModel() != null)
        {
       tfDescription.setText(dep.getDescription());
       tfJustificatif.setText(dep.getJustificatif());
       tfValeur.setText(Double.toString(dep.getValeur()));
       tfSource.setText(dep.getSource());
        }
    }

    @FXML
    private void recherche(KeyEvent event) {
        
         if( !tf_recherche.getText().isEmpty() || tf_recherche.getText() != null )
        { 
            List<Revenu> list = ds.Recherche(tf_recherche.getText());
            if(list != null)
            tb_revenu.getItems().setAll(list);
        }else 
        {
            List<Revenu> list = ds.afficher();
            tb_revenu.getItems().setAll(list);
        }
    }

   
}
