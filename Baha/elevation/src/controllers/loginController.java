/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.Servicelogin;
import Utils.Maconnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author infoplus
 */
public class loginController {

    @FXML
    private Label loginmessage;
    @FXML
    private Button loginbt;
    @FXML
    private Button cancelbt;
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;

    @FXML
    private void loginbtaction(ActionEvent event) {
        if (tfusername.getText().isEmpty()== false && tfpassword.getText().isEmpty()== false)
            validatelogin(event);
        
    
                else {
            loginmessage.setText("Please enter Username and Password");
        }
    }
public void validatelogin(ActionEvent event)
{
    Connection cnx;
    cnx= Maconnexion.getInstance().getConnection();
    
    Servicelogin sl=new Servicelogin(); 
    String verifylogin= "SELECT count(1) from elevation.user WHERE username= '"+tfusername.getText()+"' AND password = '"+tfpassword.getText()+"'";
        
    
        try {
            Statement stm=cnx.createStatement();
            ResultSet queryResult =stm.executeQuery(verifylogin);
            
            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    loginmessage.setText("Congratilations");
                    
                     Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/Examsview.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ExamsviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                     
                }
                    else{
                            loginmessage.setText("Invalid Login.please try again");
                            }
                }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
        
}
    @FXML
    private void cancelbtaction(ActionEvent event) {
        Stage stage= (Stage) cancelbt.getScene().getWindow();
        stage.close();
    }
    
}
