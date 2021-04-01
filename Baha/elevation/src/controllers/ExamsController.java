/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import static Service.Constants.projectPath;
import Service.ServiceExamen;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author infoplus
 */
public class ExamsController implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private ImageView eventPic;
    @FXML
    private Text nbrParticip;
    @FXML
    private Button inscritButton;
    @FXML
    private Button annulerButton;
    @FXML
    private TextField heure;
    @FXML
    private Text nomMatiere;
    @FXML
    private ImageView QRimages;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private examen e;
    public void setData(examen exam){
        this.e=exam;
        ServiceExamen se= new ServiceExamen();
        nomMatiere.setText(e.getNom_matiere());
        dateLabel.setText(e.getDate_ex().toString());
        heure.setText(e.getDuree_ex());
        
        nbrParticip.setText(String.valueOf(e.getInscription()));
        
        Image img = new Image("file:///"+projectPath + "\\src\\images\\QRExamen\\" + e.getNom_matiere()+e.getDuree_ex()+e.getDate_ex().toString() + ".jpg");
         QRimages.setImage(img);
        
        
        
    }
    
    @FXML
    private void toSinscrireForm(ActionEvent event) {
        
//        
FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/inscription.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(ExamsviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               InscriptionController iec=Loader.getController();
               iec.setNameExam(this.e.getNom_matiere(),this.e.getId_examen(),String.valueOf(this.e.getDate_ex()));
                Parent p=Loader.getRoot();
                Stage ins=new Stage();
                Scene scene = new Scene(p);
                ins.setScene(scene);
                ins.show();
                ServiceExamen se=new ServiceExamen();
        try {
            se.increment(this.e.getId_examen());
        } catch (SQLException ex) {
            Logger.getLogger(ExamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void annulerInscrit(ActionEvent event) {
        ServiceExamen se=new ServiceExamen();
        try {
            se.dec(this.e.getId_examen());
        } catch (SQLException ex) {
            Logger.getLogger(ExamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
