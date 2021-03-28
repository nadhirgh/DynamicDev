/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.Swing;
import animatefx.animation.Wobble;
import com.esprit.services.SeanceServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class EmploidEleveController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private GridPane grid;
    @FXML
    private Button add;
    @FXML
    private ComboBox<String> cclasse;
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
         new Wobble(pnlStatus).play();

        viewNomClasse();
        
    }  
        public void viewNomClasse(){
   SeanceServices sc = new SeanceServices();
        

           ArrayList lv;
       
            lv = (ArrayList) sc.afficherNomClasse();
            ObservableList data = FXCollections.observableArrayList(lv);
        cclasse.setItems(data);
        
     }
    public String Getnomclasse(){
             String s =cclasse.getValue();
        return s;
    }
    @FXML
    private void gridp(ActionEvent event) {
           int i=0;
           int j=0;
           int colone=1;
           int ligne=1;
           String resultat=null;
           String classe=Getnomclasse();
           Text text1= new Text("");
           Label text2= new Label("");
           
         List<String> list = Arrays.asList(new String[]{"Lundi", "Mardi","Mercredi","Jeudi","Vendredi","Samedi"});
             List<String> list1 = Arrays.asList(new String[]{"08:00-10:00", "10:00-12:00","Pause","14:00-16:00","16:00-18:00"});
        System.out.println(classe);
        
        SeanceServices sc = new SeanceServices();
          for(i = 0; i < 6; i++){
             for(j = 0; j < 5; j++){
                 resultat=sc.AjouterSeanceAEmploiEleve(list.get(i), list1.get(j), classe);
                 
                 System.out.println(resultat);
                 text2= new Label(resultat);
               text2.setPrefSize(124, 59);
               text2.setAlignment(Pos.CENTER);
               
                   grid.add(text2, colone, ligne);
//                 grid.setPadding(new Insets(10,10,10,10));

                 //grid.setAlignment(Pos.CENTER);
                 //grid.setPadding(new Insets(10));
                 colone++;         
                 System.out.println("colone");
                 System.out.println(colone);

             } System.out.println("ligne");
                                               System.out.println(ligne);
              colone=1;
             ligne++;
          }
            // grid.setHgap(10);
              
              
              
              
              
              
              
              
 
    }
    
   
}   
    

