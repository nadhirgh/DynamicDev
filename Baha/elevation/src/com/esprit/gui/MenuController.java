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
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    private ImageView exit;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane1, pane2;
    boolean status = false;
    private Text lien;
    @FXML
    private JFXButton btHome;
    @FXML
    private JFXButton btUser;
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
    private Label labelLien;
    @FXML
    private BorderPane bp;
    @FXML
    private JFXButton btnCompte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        exit.setOnMouseClicked((event) -> {
//            System.exit(0);
//        });
        pane1.setVisible(false);
        
            // bp.setAlignment(list, Pos.TOP_LEFT);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        menu.setOnMouseClicked((MouseEvent event) -> {
            if (status == false) {
                pane1.setVisible(true);
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(0.15);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
                translateTransition1.setByX(+600);
                translateTransition1.play();
                status = true;
            } else {

                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
                fadeTransition1.setFromValue(0.15);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();

                fadeTransition1.setOnFinished((event1) -> {
                    pane1.setVisible(false);

                });
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
                translateTransition1.setByX(-600);
                translateTransition1.play();
                status = false;

            }
        });

        pane1.setOnMouseClicked((event) -> {
            if (status == true) {
                
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
                fadeTransition1.setFromValue(0.15);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();

                fadeTransition1.setOnFinished((event1) -> {
                    pane1.setVisible(false);

                });
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
                translateTransition1.setByX(-600);
                translateTransition1.play();
                status=false;
                
            }
        });

    }

    @FXML
    private void homepage(ActionEvent event) {
          labelLien.setText("/Admin/Home/");
                          loadPage("Dashboard");


    }

    @FXML
    private void userpage(ActionEvent event) {
        labelLien.setText("/Admin/Utilisteur/");
    }

    @FXML
    private void emploipage(ActionEvent event) {
                labelLien.setText("/Admin/Emploi/");
                loadPage("MenuApp");

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
    }

}
