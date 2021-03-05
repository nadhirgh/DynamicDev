/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Depense;
import Services.DepenseService;
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
public class DepenseController implements Initializable {

    @FXML
    private TextField tfValeur;
    @FXML
    private TextField tfSource;
    @FXML
    private TextField tfJustificatif;
    @FXML
    private TextField tfDescription;
    
    DepenseService ds ;
    @FXML
    private TableView<Depense> tb_depense ;
    @FXML
    private TableColumn<Depense, Integer> tc_id_finance;
    @FXML
    private TableColumn<Depense, Double> tc_valeur;
    @FXML
    private TableColumn<Depense, Date> tc_date;
    @FXML
    private TableColumn<Depense, String> tc_source;
    @FXML
    private TableColumn<Depense, String> tc_description;
    @FXML
    private TableColumn<Depense, String> tc_justificatif;
    @FXML
    private TextField tf_recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ds = new DepenseService();
        tc_valeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_id_finance.setCellValueFactory(new PropertyValueFactory<>("id_finance"));
        tc_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tc_justificatif.setCellValueFactory(new PropertyValueFactory<>("justificatif"));
        tc_source.setCellValueFactory(new PropertyValueFactory<>("source"));
        tb_depense.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        List<Depense> list = ds.afficher();
        tb_depense.getItems().addAll(list);
        
        
        tb_depense.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
            @Override
            public void handle(MouseEvent event) {
               if(!tb_depense.getSelectionModel().isEmpty())
                   plainSelection(tb_depense.getSelectionModel().getSelectedItem());
            }
                  
                });
        
        
    }    

    @FXML
    private void AjouterDepense(ActionEvent event) {
        
        Depense dep = new Depense();
      
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        dep.setDate(sqlDate);
        dep.setId_finance(0);
        dep.setJustificatif(tfJustificatif.getText());
        dep.setSource(tfSource.getText());
        dep.setDescription(tfDescription.getText());
        ds.ajouter(dep);
        refresh_list(event);
        clearSelection();
    }

    @FXML
    private void refresh_list(ActionEvent event) {
        
        List<Depense> list = ds.afficher();
        tb_depense.getItems().setAll(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
        if(tb_depense.getSelectionModel() != null)
        ds.supprimer(tb_depense.getSelectionModel().getSelectedItem().getId()) ;
        List<Depense> list = ds.afficher();
        tb_depense.getItems().setAll(list);
        clearSelection();
    }

    @FXML
    private void ModifierDepense(ActionEvent event) {
        Depense dep = new Depense();
        if(tb_depense.getSelectionModel() != null)
        {    dep.remplirDepense(tb_depense.getSelectionModel().getSelectedItem()) ;
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
        tb_depense.getSelectionModel().clearSelection();
        tfDescription.setText("");
       tfJustificatif.setText("");
       tfValeur.setText("");
       tfSource.setText("");

    }
    

    void plainSelection(Depense dep)
    {
         if(tb_depense.getSelectionModel() != null)
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
            List<Depense> list = ds.Recherche(tf_recherche.getText());
            if(list != null)
            tb_depense.getItems().setAll(list);
        }else 
        {
            List<Depense> list = ds.afficher();
            tb_depense.getItems().setAll(list);
        }
    }

   
}
