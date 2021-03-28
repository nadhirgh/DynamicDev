/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class RegisterController implements Initializable {

    @FXML
    private Label lbLogin;
    @FXML
    private Label lblCreateAccount1;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnCreate;
    @FXML
    private JFXComboBox<String> crole;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ObservableList data = FXCollections.observableArrayList("Admin","Enseignant","Eléve");
        crole.setItems(data);
    }

    @FXML
    private void OpenLoginScene(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../gui/login2.fxml"));
        Scene accountScene = lbLogin.getScene();
        root.translateYProperty().set(accountScene.getHeight());

        StackPane rootPane = (StackPane) accountScene.getRoot();

        rootPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timeline.setOnFinished((ActionEvent event2) -> {
            rootPane.getChildren().remove(anchorPane);
        });
    }

    @FXML
    private void register(ActionEvent event) {
                  Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        if(crole.getValue()==("Admin")){
            try {
          Parent root = FXMLLoader.load(getClass().getResource("AdminRegister.fxml"));
          Scene scene  = new Scene(root);
          stage.setScene(scene);
         } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // loadPage("adminRegister");
        
        }else if(crole.getValue()==("Eléve")){
              try {
          Parent root = FXMLLoader.load(getClass().getResource("registerEleve.fxml"));
          Scene scene  = new Scene(root);
          stage.setScene(scene);
         } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // loadPage("RegisterEleveController");
        }else{
          try {
          Parent root = FXMLLoader.load(getClass().getResource("registerformEnseignant.fxml"));
          Scene scene  = new Scene(root);
          stage.setScene(scene);
//          scene.
         } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       // loadPage("RegisterformEnseignantController");
        }
        
       
    }
//      public void loadPage (String page){
//        //Parent root = null;
//        Stage registerStage;
//        try {
//           // root = FXMLLoader.load(getClass().getResource(page+".fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(page+".fxml"));
//        Parent root = loader.load();
//        
//        Scene scene = new Scene(root);
//        
//        //registerStage.setTitle("Créer un compte");
//
//       registerStage.setScene(scene);
//       
//       //new FadeIn(root).play();
//        registerStage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       // bp.setCenter(root);
//    }
//public void start(Stage primaryStage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Menu.fxml"));
//        Parent root = loader.load();
//        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Gestion des emlpois");
//
//       primaryStage.setScene(scene);
//       
//       //new FadeIn(root).play();
//        primaryStage.show();
//}
}