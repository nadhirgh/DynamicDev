/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class MenuEleveController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private Label labelLien;
    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane2;
    @FXML
    private JFXButton btHome;
    @FXML
    private JFXButton btEmploi;
    @FXML
    private JFXButton btExamen;
    @FXML
    private JFXButton btCours;
    @FXML
    private JFXButton btReclamation;
    @FXML
    private JFXButton btFinance;
    @FXML
    private JFXButton btLogout;
    @FXML
    private BorderPane bp;
    @FXML
    private JFXButton btnCompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        homepage();
    }    

      @FXML
    private void homepage() {
          labelLien.setText("/Admin/Home/");
                          loadPage("Dashboard");


    }

    private void userpage(ActionEvent event) {
        labelLien.setText("/Admin/Utilisteur/");
    }

    @FXML
    private void emploipage(ActionEvent event) {
                labelLien.setText("/Admin/Emploi/");
                loadPage("EmploidEleve");

    }
    @FXML
    
    private void examenpage(ActionEvent event) {
                labelLien.setText("/Admin/Examen/");

    }

    @FXML
    private void courspage(ActionEvent event) {
                labelLien.setText("/Admin/Cours/");

    }

    @FXML
    private void reclamationpage(ActionEvent event) {
                labelLien.setText("/Admin/Réclamation/");

    }

    @FXML
    private void financepage(ActionEvent event) {
                labelLien.setText("/Admin/Finance/");

    }

    @FXML
    private void sedeconnecterpage(ActionEvent event) {
              //  labelLien.setText("/Admin/Logout/");
            Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

         try {
          Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
          Scene scene  = new Scene(root);
          stage.setScene(scene);
//          scene.
         } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    


    }

   
     private void loadPage (String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void MonCompte(ActionEvent event) {
        
        
                loadPage("modifieCompteEleve");

    }
    
}
