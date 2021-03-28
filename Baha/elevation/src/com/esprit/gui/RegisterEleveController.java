/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.User;
import com.esprit.services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class RegisterEleveController implements Initializable {

    @FXML
    private JFXTextField tnom;
    @FXML
    private JFXTextField tmail;
    @FXML
    private JFXTextField tprenom;
    @FXML
    private JFXTextField tmailparent;
    @FXML
    private JFXTextField tnumeroparent;
    @FXML
    private JFXComboBox<String> cniveau;
    @FXML
    private JFXComboBox<String> csexe;
    @FXML
    private JFXButton bclear;
    @FXML
    private JFXButton bconfirmer;
    @FXML
    private Label btn_exit;
    @FXML
    private JFXDatePicker dDate;
    @FXML
    private JFXPasswordField tmdp;
    @FXML
    private JFXPasswordField tmdp1;
    @FXML
    private JFXButton bimporter;
    @FXML
    private AnchorPane anchorid;
    String filePath;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblemail;
    @FXML
    private Label lblemailparent;
    @FXML
    private Label lblmdp;
    @FXML
    private Label lblmdp2;
    @FXML
    private Label lblnumero;
    @FXML
    private Label lblsexe;
    @FXML
    private Label lblniveau;
    @FXML
    private Label lbldate;
    @FXML
    private ImageView image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ObservableList data = FXCollections.observableArrayList("1","2","3","4","5","6");
        cniveau.setItems(data);
                  ObservableList data1 = FXCollections.observableArrayList("Homme","Femme");
        csexe.setItems(data1);

    }    

    @FXML
    private void clear(ActionEvent event) {
    }

   
    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
   
 @FXML
    private void ajouterEleve(ActionEvent event) {
//    (String nom, String prenom, String mdp, String email, String emailParent, int numeroParent, String sexe, String classe, int niveau, String Datedenaissance, String status) {
     if (
              tnom.getText().isEmpty()
                || tprenom.getText().isEmpty()
                || tmail.getText().isEmpty()
                || tmailparent.getText().isEmpty()
                || tmdp.getText().isEmpty()
                || tmdp1.getText().isEmpty()
                || tnumeroparent.getText().isEmpty()
                || csexe.getSelectionModel().isEmpty()
                || cniveau.getSelectionModel().isEmpty()
               // || dDate.getValue().isEmpty()

                //|| datenaissancetxt.getValue().isEmpty()
                
                ) {
            Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("Erreur");
            fail.setHeaderText(null);
            fail.setContentText("Remplissez tous les champs svp ! ");
            fail.showAndWait();
       }else if (csexe.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider vos champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Vérifier le champ Sexe");
            alert1.showAndWait();
        } else if (cniveau.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider vos champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Vérifier le champ niveau");
            alert1.showAndWait();}
        else if (!tnom.getText().matches("^[a-zA-Z]+$")) {

            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre Nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Merci de bien vouloir vérifier votre Nom ");
            alert1.showAndWait();

        } else if (!tprenom.getText().matches("^[a-zA-Z]+$")) {

            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre prenom");
            alert1.setHeaderText(null);
            alert1.setContentText("Merci de bien vouloir vérifier votre Prenom ");
            alert1.showAndWait();

        } else if (!tmail.getText().matches("^[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre Email");
            alert1.setHeaderText(null);
            alert1.setContentText("Merci de bien vouloir vérifier votre Email ");
            alert1.showAndWait();

        } else if (!tmailparent.getText().matches("^[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre Email du parent");
            alert1.setHeaderText(null);
            alert1.setContentText("Merci de bien vouloir vérifier votre Email parent ");
            alert1.showAndWait();
        } else if (!tnumeroparent.getText().matches("^([1-9][0-9]{0,8}|1000)$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider le numéro de téléphone du parent");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier que les chiffres qui composent le numéro soient entre 1 et 9 ");
            alert1.showAndWait();
        } else if (dDate.getValue().isAfter(LocalDate.of(2015, 12, 31))) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Date de naissance erronée");
            alert1.setHeaderText("L'age minimum est 6 ans ");
            alert1.setContentText("Erreur");
            alert1.showAndWait();
        } else if (dDate.getValue().isBefore(LocalDate.of(2009, 12, 31))) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Date de naissance erronée");
            alert1.setHeaderText("L'age maximum est 12 ans ");
            alert1.setContentText("Erreur");
            alert1.showAndWait();
        }
     
     
     
     
     
     
     
     
     
     else{
            ServiceUser sc = new ServiceUser();
             String s2;
             String c="";

             String status="en attente";
            s2 = tnom.getText();
          String s3=tnumeroparent.getText();
          int si4=Integer.parseInt(s3);
           String s4=cniveau.getValue();
          int si5=Integer.parseInt(s4);
           LocalDate d1 = dDate.getValue();
            Date date = Date.valueOf(d1);
         //  c.setDate_de_naissance(date);

//            c.setNumero_parent(Integer.parseInt(num_p.getText()));
//            c.setNumero(Integer.parseInt(num.getText()));
//        String s4=cniveau.getValue();
//          int si5=Integer.parseInt(s4);
          
        sc.ajouterUserEleve(new User(tnom.getText(),tprenom.getText(),tmdp.getText(),tmail.getText(),tmailparent.getText(),si4,csexe.getValue(),c,si5,date,status,filePath));
       // System.out.println(new User(tnom.getText(),tprenom.getText(),tmdp.getText(),tmail.getText(),tmailparent.getText(),si4,csexe.getValue(),c,si5,date,status,filePath));
        JOptionPane.showMessageDialog(null, "Eleve ajoutée !");
//  view();
  //viewid();
     }
    }
////        }
//  
//    }

    @FXML
    private void importerimage(ActionEvent event) throws FileNotFoundException {
       
        final FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) anchorid.getScene().getWindow();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = filechooser.showOpenDialog(stage);
        System.out.println("Path ="+file);
        if(file!=null)
        {   
             filePath = file.getAbsolutePath();
             image.setImage(new Image(new FileInputStream(filePath)));
             System.out.println(filePath);
        }
        if(file==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Image Alert");
            alert.setContentText("Please select an image");
            alert.setHeaderText(null);
            alert.show();   
        }
    }
    

    
}
    

