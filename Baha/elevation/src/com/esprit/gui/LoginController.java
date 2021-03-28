/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class LoginController implements Initializable {

    @FXML
    private Label lblCreateAccount;
    @FXML
    private TabPane tabPaneLogin;
    @FXML
    private Tab tabAdmin;
    @FXML
    private Tab tabEnseignant;
    @FXML
    private Pane sidingPane;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblEnseignant;
    @FXML
    private Label lblStatus;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblEleve;
    @FXML
    private Tab tabEleve;

    int emplacement = 1;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField tmdp;
    @FXML
    private TextField temail;
    public static int id_ens = 0;
    public static int id_eleve = 0;
    public static int id_admin = 0;
    @FXML
    private Button btnLogin1;
    @FXML
    private PasswordField tmdp1;
    @FXML
    private TextField temail1;
    @FXML
    private Button btnLogin2;
    @FXML
    private PasswordField tmdp2;
    @FXML
    private TextField temail2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OpenAdminTab(MouseEvent event) {
        System.out.println("first" + emplacement);

        if (emplacement == 3 || emplacement == 2) {
            TranslateTransition toLeftAnimation = new TranslateTransition(new javafx.util.Duration(500), lblStatus);
            toLeftAnimation.setToX(sidingPane.getTranslateX());

            toLeftAnimation.play();
            toLeftAnimation.setOnFinished((ActionEvent event2) -> {
                lblStatus.setText("Admin");

            });
        }
        tabPaneLogin.getSelectionModel().select(tabAdmin);

        emplacement = 1;
        System.out.println("last" + emplacement);

    }

    @FXML
    private void OpenEnseignantTab(MouseEvent event) {
        System.out.println("first" + emplacement);

        if (emplacement == 1) {
            TranslateTransition toRightAnimation = new TranslateTransition(new javafx.util.Duration(500), lblStatus);
            toRightAnimation.setToX(lblStatus.getPrefWidth());
            toRightAnimation.play();
            toRightAnimation.setOnFinished((ActionEvent event1) -> {
                lblStatus.setText("Enseignant");

            });
        } else if (emplacement == 3) {
            TranslateTransition toLeftAnimation = new TranslateTransition(new javafx.util.Duration(500), lblStatus);
            toLeftAnimation.setToX(lblStatus.getPrefWidth());
            toLeftAnimation.play();
            toLeftAnimation.setOnFinished((ActionEvent event3) -> {
                lblStatus.setText("Enseignant");

            });

        }
        tabPaneLogin.getSelectionModel().select(tabEnseignant);

        emplacement = 2;
        System.out.println("last" + emplacement);

    }

    @FXML
    private void OpenEleveTab(MouseEvent event) {
        System.out.println("first" + emplacement);
        if (emplacement == 1) {
            TranslateTransition toRightAnimation = new TranslateTransition(new javafx.util.Duration(500), lblStatus);
            toRightAnimation.setToX(lblStatus.getPrefWidth() * 2);
            toRightAnimation.play();
            toRightAnimation.setOnFinished((ActionEvent event4) -> {
                lblStatus.setText("Eleve");

            });
        } else if (emplacement == 2) {
            TranslateTransition toRightAnimation = new TranslateTransition(new javafx.util.Duration(500), lblStatus);
            toRightAnimation.setToX(lblStatus.getPrefWidth() * 2);
            toRightAnimation.play();
            toRightAnimation.setOnFinished((ActionEvent event5) -> {
                lblStatus.setText("Eleve");

            });
        }
        tabPaneLogin.getSelectionModel().select(tabEleve);
        emplacement = 3;
        System.out.println("last" + emplacement);

    }

    @FXML
    private void OpenCreateAccountScene(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../gui/register.fxml"));
        Scene loginScene = lblAdmin.getScene();
        root.translateYProperty().set(loginScene.getHeight());
        rootPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timeline.setOnFinished((ActionEvent event2) -> {
            rootPane.getChildren().remove(anchorPane);
        });

    }

    @FXML
    private void Login(ActionEvent event) throws SQLException, IOException {


        String email = temail.getText();
        String mdp = tmdp.getText();
        String email1 = temail1.getText();
        String mdp1 = tmdp1.getText();
        String email2 = temail2.getText();
        String mdp2 = tmdp2.getText();
        Connection cnx = DataSource.getInstance().getCnx();

        //        if (txtLogin.getText().equals("") && txtPassword.getText().equals("")) {
//            System.out.println("mailll and mmmm");
//            et_mail.setVisible(true);
//            ch_ml.setVisible(true);
//
//            et_pass.setVisible(true);
//            ch_md.setVisible(true);
//        } else if (txtPassword.getText().equals("")) {
//            System.out.println("mmmm");
//            et_mail.setVisible(false);
//            ch_ml.setVisible(false);
//            et_pass.setVisible(true);
//            ch_md.setVisible(true);
//        } else if (txtLogin.getText().equals("")) {
//            System.out.println("mailll");
//            et_mail.setVisible(true);
//            ch_ml.setVisible(true);
//            et_pass.setVisible(false);
//            ch_md.setVisible(false);
        //else 
//        try {
        if (emplacement == 1) {

            Statement st = cnx.createStatement();
            //ResultSet rs1; = ;
            //ResultSet rs2 = ;
            //ResultSet rs3 = ;

            if (st.executeQuery("SELECT * FROM `administrateur` WHERE `email`= '" + email + "' AND `mdp` = '" + mdp + "'").next()) {
                ResultSet rs = st.executeQuery("SELECT * FROM `administrateur` WHERE `email`= '" + email + "'");
                while (rs.next()) {
                    id_admin = rs.getInt("idAdmin");
                    System.out.println(" okkk" + id_admin);

                }
                //System.out.println(rs1.getInt(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/gui/Menu.fxml"));
                Parent root = loader.load();

                //ProfilAdminController pc = loader.getController();
                //pc.charger(txtLogin.getText());
                temail.getScene().setRoot(root);

            }else {
                    /*Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Wrong password or username");
                    alert.showAndWait();
                    System.out.println("nooo");*/
                    TrayNotification notif=new TrayNotification();
        notif.setAnimationType(AnimationType.POPUP);
        notif.setTitle("Erreur");
        notif.setMessage("Mots de passe ou login incorrecte");
        notif.setNotificationType(NotificationType.ERROR);
        notif.showAndDismiss(Duration.seconds(4));}

        } else if (emplacement == 2) {

            Statement st = cnx.createStatement();
            //ResultSet rs1; = ;
            //ResultSet rs2 = ;
            //ResultSet rs3 = ;

            if (st.executeQuery("SELECT * FROM `enseignant` WHERE `email`= '" + email1 + "' AND `mdp` = '" + mdp1 + "'").next()) {
                ResultSet rs = st.executeQuery("SELECT * FROM `enseignant` WHERE `email`= '" + email1 + "'");
                while (rs.next()) {
                    id_ens = rs.getInt("idEns");
                    System.out.println(" okkk" + id_ens);

                }
                //System.out.println(rs1.getInt(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/gui/MenuEleve.fxml"));
                Parent root = loader.load();

                //ProfilAdminController pc = loader.getController();
                //pc.charger(txtLogin.getText());
                temail1.getScene().setRoot(root);
            }else {
                    /*Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Wrong password or username");
                    alert.showAndWait();
                    System.out.println("nooo");*/
        TrayNotification notif=new TrayNotification();
        notif.setAnimationType(AnimationType.POPUP);
        notif.setTitle("Erreur");
        notif.setMessage("Mots de passe ou login incorrecte");
        notif.setNotificationType(NotificationType.ERROR);
        notif.showAndDismiss(Duration.seconds(4));}
        } else{          
            
            Statement st = cnx.createStatement();
        

            
            
            if (st.executeQuery("SELECT * FROM `eleve` WHERE `email`= '" + email2 + "' AND `mdp` = '" + mdp2 + "'").next()) {
            ResultSet rs = st.executeQuery("SELECT * FROM `eleve` WHERE `email`= '" + email2 + "'");
            while (rs.next()) {
                id_eleve = rs.getInt("idEleve");
                System.out.println(" okkk" + id_eleve);

            }

            //System.out.println(rs2.getInt(1));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/gui/MenuEleve.fxml"));
            Parent root = loader.load();

            //ProfilController pc = loader.getController();
            // pc.charger(id_ens);
            temail2.getScene().setRoot(root);
        }else {
                    /*Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Wrong password or username");
                    alert.showAndWait();
                    System.out.println("nooo");*/
        TrayNotification notif=new TrayNotification();
        notif.setAnimationType(AnimationType.POPUP);
        notif.setTitle("Erreur");
        notif.setMessage("Mots de passe ou login incorrecte");
        notif.setNotificationType(NotificationType.ERROR);
        notif.showAndDismiss(Duration.seconds(4));}
    }
}
}