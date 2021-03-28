/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class GestionEmploiController implements Initializable {

    @FXML
    private Button btn;
//  
//       GridPane GPane = new  GridPane();
//       
//    Text txtheade = new Text("test");
//    
//   GPane.add(txtheade,1,1);
    @FXML
    private GridPane grid;
    @FXML
    private Button add;
    

//    /**
//     * Initializes the controller class.
//     * @param url
//     * @param rb
//     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Text text1 = new Text("nchl");
//        grid.add(text1,1,1);
//    GridPane GPane = new  GridPane();
//       
//       Text txtheade = new Text("test");
//    
//       GPane.add(txtheade,1,1);
//      Scene scene = new Scene(GPane);
//        Stage primaryStage = null;
//        
//        
//       
//       primaryStage.setScene(scene);
//        primaryStage.show();

    }  
    @FXML
    private void gridp (ActionEvent event) throws IOException{
           
        
     
           Text text1 = new Text("l");
         grid.add(text1,1,2);

        
             

    }


    @FXML
    private void menu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}

   