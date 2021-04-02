/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Front.Prof;

import Controller.Front.Eleve.*;
import Entities.Utilisiateur;
import Utils.Session;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilController implements Initializable {

    @FXML
    private Pane PageContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRecBtn(ActionEvent event) throws IOException {
        // load new pane
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Prof/Recommendation.fxml"));

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
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Prof/Paiment.fxml"));

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
    Pane newPane = FXMLLoader.load(getClass().getResource("/Controller/Front/Prof/Reclamation.fxml"));

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

    
}
