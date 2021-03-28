/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class ModifieCompteEleveController implements Initializable {

    @FXML
    private AnchorPane anchorid;
    @FXML
    private Label btn_exit;
    @FXML
    private JFXTextField tnom;
    @FXML
    private JFXTextField tprenom;
    @FXML
    private JFXTextField tmail;
    @FXML
    private JFXTextField tmailparent;
    @FXML
    private JFXPasswordField tmdp;
    @FXML
    private JFXPasswordField tmdp1;
    @FXML
    private JFXTextField tnumeroparent;
    @FXML
    private JFXDatePicker dDate;
    @FXML
    private JFXButton bclear;
    @FXML
    private JFXButton bconfirmer;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton bimporter;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label lemail;
    @FXML
    private Label temailparent;
    @FXML
    private Label lnumeroparent;
    @FXML
    private Label lsexe;
    @FXML
    private Label ldate;
    @FXML
    private Label lniveau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }

    @FXML
    private void ajouterEleve(ActionEvent event) {
    }

    @FXML
    private void importerimage(ActionEvent event) {
    }
    
}
