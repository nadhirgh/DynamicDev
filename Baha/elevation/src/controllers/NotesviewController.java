/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import Entities.note;
import Entities.user;
import Service.ServiceExamen;
import Service.ServiceNote;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author infoplus
 */
public class NotesviewController implements Initializable {

    
    @FXML
    private Button tri;
    @FXML
    private Button refresh;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView searchIcon;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    public user us;
    public note nt;
    private final List<note> notes = new ArrayList<>();
    private final List<note> notesTrier = new ArrayList<>();
     private final List<examen> exam = new ArrayList<>();
    @FXML
    private HBox examtView;
    @FXML
    private Text iconUserDef;
    
    
   public void setuserNamer(String nom){
        this.iconUserDef.setText(nom);}
    
    public void afficherAll2() throws SQLException{
        ServiceNote sn= new ServiceNote();
        ServiceExamen se= new ServiceExamen();
        
        notes.addAll(sn.AfficherNote().stream().collect(Collectors.toList()));
        exam.addAll(se.AfficherExamen().stream().collect(Collectors.toList()));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <notes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Notes.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                NotesController noteController = fxmlLoader.getController();
                noteController.setData1(notes.get(i));
                noteController.setData(exam.get(i));
                if (column == 2) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                grid.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.afficherAll2();
        } catch (SQLException ex) {
            Logger.getLogger(NotesviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void returnhome(MouseEvent event) {
          Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/FXMLHome.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void returntohome(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/HOME.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }


    @FXML
    private void refreshPage(ActionEvent event) {
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void trierLesNotes(ActionEvent event) {
    }

    @FXML
    private void movetoexam(MouseEvent event) {
        user u=new user();
        
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Examsview.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(NotesviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ExamsviewController ien=Loader.getController();
        ien.us=u;
        ien.setuserNamer(u.getNom());
                Parent ap=Loader.getRoot();
                //Stage ins=new Stage();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(ap);
                stage.setScene(scene);
               stage.show();
    }
    
}
