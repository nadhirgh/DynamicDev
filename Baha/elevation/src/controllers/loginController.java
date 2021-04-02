/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.user;
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
    
        user u=new user();
    
        try {
            Statement stm=cnx.createStatement();
            ResultSet queryResult =stm.executeQuery(verifylogin);
            
            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
    String req= "SELECT * from elevation.user WHERE username= '"+tfusername.getText()+"' AND password = '"+tfpassword.getText()+"'";
    Statement stm1=cnx.createStatement();
            ResultSet rs =stm1.executeQuery(req);
            while(rs.next()){
                u.setId_etud(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setPassword(rs.getString(5));
            }
                    loginmessage.setText("Congratilations");
                    
                  FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Examsview.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ExamsviewController iec=Loader.getController();
        iec.us=u;
        iec.setuserNamer(u.getNom());
                Parent p=Loader.getRoot();
                Stage ins=new Stage();
                Scene scene = new Scene(p);
                ins.setScene(scene);
                ins.show();
                     
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
