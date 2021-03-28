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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class MenuAppController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
//
//    private void home(MouseEvent event) {
//        loadPage("home");
//    }
//
//    private void page1(MouseEvent event) {
//         loadPage("com/esprit/gui/GestionClasse");
//
//    }
//
//    private void page2(MouseEvent event) {
//           loadPage("com/esprit/gui/EmploiEleve");
//
//    }
//
//    private void page3(MouseEvent event) {
//                 loadPage("com/esprit/gui/Statistique");
//
//    }
//    
//    @FXML
//    private void home(ActionEvent event) {
//    }

    @FXML
    private void page1(ActionEvent event) {
                 loadPage("GestionClasse");

    }

    @FXML
    private void page2(ActionEvent event) {
                   loadPage("GestionSeance");

    }

    @FXML
    private void page3(ActionEvent event) {
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
