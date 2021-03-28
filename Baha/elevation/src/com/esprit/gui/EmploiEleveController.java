/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.Seance;
import com.esprit.services.SeanceServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class EmploiEleveController implements Initializable {

    @FXML
    private TableColumn<Seance, String> cdate;

    @FXML
    private TableColumn<Seance, String> censeignant;
    @FXML
    private TableColumn<Seance, String> cmatiere;
    @FXML
    private ComboBox<String> cnomclasse;
    @FXML
    private TableView<Seance> table;
    @FXML
    private TableColumn<Seance, String> cheure;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewNomClasse();
      
       
    } 
    @FXML
    public String Getnomclasse(){
                  String s =cnomclasse.getValue();

        try {
            
            
          SeanceServices sc = new SeanceServices();
          
                System.out.println(s);      
           ArrayList<Seance> lv;
           lv = (ArrayList<Seance>) sc.afficherEmploiEleve(s);

       
            ObservableList<Seance> data = FXCollections.observableArrayList(lv);
                  cdate.setCellValueFactory(new PropertyValueFactory<>("dateSeance"));
                  cheure.setCellValueFactory(new PropertyValueFactory<>("heure"));
                  
                  cmatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
                  censeignant.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));

                  
                  table.setItems(data);
          
        } catch (Exception ex) {
            Logger.getLogger(GestionSeanceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
      return s; 

      }

    
    
    @FXML
    public void viewNomClasse(){
   SeanceServices sc = new SeanceServices();
        
   
   

           ArrayList lv;
       
            lv = (ArrayList) sc.afficherNomClasse();
            ObservableList data = FXCollections.observableArrayList(lv);
        cnomclasse.setItems(data);
}

//
//    private void view(ActionEvent event ) {
//        try {
//          String s =Getnomclasse();
//            
//            
//          SeanceServices sc = new SeanceServices();
//          
//                System.out.println(s);      
//           ArrayList<Seance> lv;
//           lv = (ArrayList<Seance>) sc.afficherEmploiEleve(s);
//
//       
//            ObservableList<Seance> data = FXCollections.observableArrayList(lv);
//                cdate.setCellValueFactory(new PropertyValueFactory<>("dateSeance"));
//                  cheure.setCellValueFactory(new PropertyValueFactory<>("heure"));
//                  
//                  cmatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
//                  censeignant.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
//
//                  
//                  table.setItems(data);
//          
//        } catch (Exception ex) {
//            Logger.getLogger(GestionSeanceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    

    }
  
     
       