/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front.Eleve;

import Entities.Utilisiateur;
import Utils.Session;
import Utils.Translator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilEleveController implements Initializable {

    @FXML
    private Pane PageContent;
    @FXML
    private Button recbtn;
    @FXML
    private Button paibtn;
    @FXML
    private Button disconbtn;
    @FXML
    private Button tradBtn;

    private int count ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        count = 0 ;
    }    

    private void handleRecBtn(ActionEvent event) throws IOException {
        // load new pane
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Eleve/Recommendation.fxml"));

    // get children of parent of secPane (the VBox)
    List<Node> parentChildren = ((Pane)PageContent.getParent()).getChildren();

    // replace the child that contained the old secPane
    parentChildren.set(parentChildren.indexOf(PageContent), newPane);

    // store the new pane in the secPane field to allow replacing it the same way later
    PageContent = newPane;
    }

    @FXML
    private void handlePaimentBtn(ActionEvent event) throws IOException {
        // load new pane
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Eleve/Paiment.fxml"));

    // get children of parent of secPane (the VBox)
    List<Node> parentChildren = ((Pane)PageContent.getParent()).getChildren();

    // replace the child that contained the old secPane
    parentChildren.set(parentChildren.indexOf(PageContent), newPane);

    // store the new pane in the secPane field to allow replacing it the same way later
    PageContent = newPane;
    }

    @FXML
    private void HandleReclamationBtn(ActionEvent event) throws IOException {
        // load new pane
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Eleve/Reclamation.fxml"));

    // get children of parent of secPane (the VBox)
    List<Node> parentChildren = ((Pane)PageContent.getParent()).getChildren();

    // replace the child that contained the old secPane
    parentChildren.set(parentChildren.indexOf(PageContent), newPane);

    // store the new pane in the secPane field to allow replacing it the same way later
    PageContent = newPane;
    }

    @FXML
    private void handleDisconnectBtn(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Controller/Front/Menu.fxml"));
        Stage stage1 = (Stage) PageContent.getScene().getWindow();
        stage1.close();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Utilisiateur u = new Utilisiateur();
        Session.setConnectedUser(u);
    }

    @FXML
    private void handleTraduireBtn(ActionEvent event) throws IOException {
        String langFrom = "en" ;
        String langTo = "fr" ;
         tradBtn.setText("Traduire en Francais");
        if(count % 2 == 0)
        {
            langFrom = "fr";
                    langTo = "en";
             tradBtn.setText("Traduire en Anglais");

        
        }
        paibtn.setText(Translator.translate(langFrom, langTo, paibtn.getText()));
        recbtn.setText(Translator.translate(langFrom, langTo,recbtn.getText()));
        disconbtn.setText(Translator.translate(langFrom, langTo,disconbtn.getText()));
        count++ ;
    }

    
}
