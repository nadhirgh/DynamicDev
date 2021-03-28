/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    
 // Image image =new Image("logo-emploi-du-temps-300x291");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
                bp.setCenter(ap);

    }
    @FXML

    private void page1(MouseEvent event) {
        loadPage("GestionSeance");
    }
    @FXML

    private void page2(MouseEvent event) {
         loadPage("GestionClasse");
         

    }
    @FXML

    private void page3(MouseEvent event) {
           loadPage("EmploiEnseignant");

    }
    @FXML

    private void page4(MouseEvent event) {
                 loadPage("EmploidEleve");

    }
      @FXML
    private void page5(MouseEvent event) {
                         loadPage("statistiqueClasse");

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

  
}
