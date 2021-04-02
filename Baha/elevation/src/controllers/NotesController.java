/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import Entities.note;
import Service.ServiceExamen;
import Service.ServiceNote;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author infoplus
 */
public class NotesController implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private ImageView eventPic;
    @FXML
    private Button annulerButton;
    @FXML
    private TextField heure;
    @FXML
    private Text nomMatiere;
    @FXML
    private Text ntcc;
    @FXML
    private Text ntcex;

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
    }
    
    private note n;
    public void setData1(note notes){
        this.n=notes;
        ServiceNote sn= new ServiceNote();
        ntcc.setText(n.getNote_cc());
        ntcex.setText(n.getNote_ex());
        
        
        
    }    

    @FXML
    private void reclamer(ActionEvent event) {
    }
    
}
