/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front;

import Entities.Utilisiateur;
import Utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MenuController implements Initializable {

    @FXML
    private Button frontbtn;
    @FXML
    private Button frontbtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Controller/BackMenu.fxml"));
        Stage stage1 = (Stage) frontbtn.getScene().getWindow();
        stage1.close();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Utilisiateur u = new Utilisiateur();
        u.setId(3);
        Session.setConnectedUser(u);
    }

    @FXML
    private void handleFront(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Controller/Front/Prof/Acceuil.fxml"));
        Stage stage1 = (Stage) frontbtn.getScene().getWindow();
        stage1.close();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Utilisiateur u = new Utilisiateur();
        u.setId(4);
        Session.setConnectedUser(u);
    }

    @FXML
    private void handleEleve(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Controller/Front/Eleve/AcceuilEleve.fxml"));
        Stage stage1 = (Stage) frontbtn.getScene().getWindow();
        stage1.close();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Utilisiateur u = new Utilisiateur();
        u.setId(1);
        Session.setConnectedUser(u);
        
    }
    
}
