/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.examen;
import Service.ServiceExamen;
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
public class ExamsviewController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox eventsInEventView;
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

    private final List<examen> examens = new ArrayList<>();
    private final List<examen> examsTrier = new ArrayList<>();
    
    public void afficherAll() throws SQLException{
        ServiceExamen se = new ServiceExamen();
        
        examens.addAll(se.AfficherExamen().stream().collect(Collectors.toList()));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <examens.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Exams.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExamsController examController = fxmlLoader.getController();
                examController.setData(examens.get(i));
                 
                if (column == 2) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
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
            this.afficherAll();
        } catch (SQLException ex) {
            Logger.getLogger(ExamsviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


   

    @FXML
    private void recherche(KeyEvent event) {
        List<examen> examSearch=new ArrayList();
          ServiceExamen se= new ServiceExamen();
          if(!"".equals(searchBar.getText())){
              grid.getChildren().clear();
        examSearch= se.displayAllAfterSearch(searchBar.getText());
    }
          int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <examSearch.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Exams.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExamsController examController = fxmlLoader.getController();
                examController.setData(examSearch.get(i));
                 
                if (column == 2) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
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




    @FXML
    private void returntohome(MouseEvent event) {
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
    private void trierLesExams(ActionEvent event) {
        ServiceExamen se =new ServiceExamen();
        examsTrier.clear();
        try {
            examsTrier.addAll(se.AfficherExamen());
        } catch (SQLException ex) {
            Logger.getLogger(ExamsviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        examsTrier.sort((o1, o2) -> -(o1.getDate_ex().compareTo(o2.getDate_ex())));
       String s;
      
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <examsTrier.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Exams.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExamsController examController = fxmlLoader.getController();
                examController.setData(examsTrier.get(i));
                 
                if (column == 2) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
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

    @FXML
    private void refreshPage(ActionEvent event) {
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

    @FXML
    private void returnhome(MouseEvent event) {
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
   

    
}
