/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front.Prof;

import Controller.Front.Eleve.*;
import Entities.Depense;
import Entities.Revenu;
import Services.DepenseService;
import Services.RevenuService;
import Utils.Session;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PaimentController implements Initializable {

    @FXML
    private Pane PageContent;
    @FXML
    private TableColumn<Revenu, Date> dateCol;
    @FXML
    private TableColumn<Revenu, Double> SommeCol;
    @FXML
    private TableColumn<Revenu, String> descriptionCol;
    @FXML
    private TableView<Revenu> table;

    RevenuService ds ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ds = new RevenuService();
        
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        SommeCol.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
     
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       
        refresh_list();
        
    }    
    
    
    @FXML
    private void refresh_list() {
         List<Revenu> list = ds.RevenusByUser(Session.getConnectedUser());
            table.getItems().setAll(list);
    }

    
}
