/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.Hinge;
import animatefx.animation.Jello;
import animatefx.animation.Wobble;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import com.esprit.services.SeanceServices;

import com.esprit.models.Seance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class EmploiEnseignantController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private GridPane grid;
    @FXML
    private Button add;
    @FXML
    private ComboBox<String> censeignant;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatusMini;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 new Jello(pnlStatus).play();

        viewNomEnseignant();
    }  
        public void viewNomEnseignant(){
   SeanceServices sc = new SeanceServices();
        
   
   

           ArrayList lv;
       
            lv = (ArrayList) sc.afficherNomEnseignant();
            ObservableList data = FXCollections.observableArrayList(lv);
        censeignant.setItems(data);
        }
    public String Getnomclasse(){
             String s =censeignant.getValue();
        return s;
    }
    @FXML
    private void gridp(ActionEvent event) {
           int i=0;
           int j=0;
           int colone=1;
           int ligne=1;
           String resultat=null;
           String enseignant=Getnomclasse();
           Text text1= new Text("");
         List<String> list = Arrays.asList(new String[]{"Lundi", "Mardi","Mercredi","Jeudi","Vendredi","Samedi"});
             List<String> list1 = Arrays.asList(new String[]{"08:00-10:00", "10:00-12:00","Pause","14:00-16:00","16:00-18:00"});
        System.out.println(enseignant);
        SeanceServices sc = new SeanceServices();
          for(i = 0; i < 6; i++){
             for(j = 0; j < 5; j++){
                 resultat=sc.AjouterSeanceAEmploiEns(list.get(i), list1.get(j), enseignant);
                 
                 System.out.println(resultat);
                 text1= new Text(resultat);
                 grid.add(text1, colone, ligne);
                 colone++;         
                 System.out.println("colone");
                 System.out.println(colone);

             } System.out.println("ligne");
                                               System.out.println(ligne);
              colone=1;
             ligne++;
          }
              
              
              
              
              
              
              
              
              
//        String s=sc.AjouterSeanceAEmploiEns("Lundi","08:00-10:00","foulen ben foulen4");
//        
//        for()
//        Text text1 = new Text(s);
//        grid.add(text1,1,1);
//        text1 = new Text("bonjour");
//        grid.add(text1,1,2);
       
    }
    
   
}

//    @FXML
//    private void gridp(ActionEvent event) {
//    }

