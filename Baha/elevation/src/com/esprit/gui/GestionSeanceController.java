/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.Bounce;
import com.esprit.models.Seance;
import com.esprit.services.SeanceServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class GestionSeanceController implements Initializable {

    @FXML
    private TableView<Seance> table;
    @FXML
    private TableColumn<Seance, String> colclasse;
    @FXML
    private TableColumn<Seance, String> colmatiere;
    @FXML
    private TableColumn<Seance, String> colens;
    @FXML
    private TextField tid;
    @FXML
    private TextField rechercher;

    @FXML
    private ComboBox<String> cnomclasse;

    @FXML
    private ComboBox<String> cnomenseignant;
    @FXML
    private ComboBox<String> cmatiere;

    @FXML
    private TableColumn<Seance, String> colheure;
    @FXML
    private TableColumn<Seance, String> coldate;
    @FXML
    private ComboBox<String> cheure;
    @FXML
    private ComboBox<String> cdate;
    @FXML
    private CheckBox checkclasse;
    @FXML
    private CheckBox checkens;
    @FXML
    private CheckBox checkdate;
    int tri;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatusMini;
    @FXML
    private Button btn;

    public void view() {
        try {
            SeanceServices sc = new SeanceServices();

            ArrayList<Seance> lv = (ArrayList<Seance>) sc.afficher();

            ObservableList<Seance> data = FXCollections.observableArrayList(lv);
            colclasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
            colmatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            colens.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
            colheure.setCellValueFactory(new PropertyValueFactory<>("heure"));

            coldate.setCellValueFactory(new PropertyValueFactory<>("dateSeance"));

            table.setItems(data);

        } catch (Exception ex) {
            Logger.getLogger(GestionSeanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewtri(List l) {
        try {

            ObservableList<Seance> data = FXCollections.observableArrayList(l);
            colclasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
            colmatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            colens.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
            colheure.setCellValueFactory(new PropertyValueFactory<>("heure"));

            coldate.setCellValueFactory(new PropertyValueFactory<>("dateSeance"));

            table.setItems(data);

        } catch (Exception ex) {
            Logger.getLogger(GestionSeanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tri = 0;
        new Bounce(pnlStatus).play();
        ObservableList data = FXCollections.observableArrayList("08:00-10:00", "10:00-12:00", "14:00-16:00", "16:00-18:00");
        cheure.setItems(data);

        ObservableList data1 = FXCollections.observableArrayList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi");
        cdate.setItems(data1);

        view();
        viewNomClasse();
        viewNomEnseignant();
        viewNomMatiere();
        view();

        search_user();
    }

    @FXML
    private void supprimer2(ActionEvent event) {
        SeanceServices sc = new SeanceServices();

        if (table.getSelectionModel() != null) {
            sc.delete(table.getSelectionModel().getSelectedItem().getIdSeance());
        }
        ArrayList lv;

        lv = (ArrayList) sc.afficher();
        ObservableList data = FXCollections.observableArrayList(lv);
        //List<Seance> list = sc.afficher();
        table.setItems(data);
        clearSelection();
    }

    @FXML
    void clearSelection() {
        table.getSelectionModel().clearSelection();
        cnomclasse.setValue("Classe");
        cnomenseignant.setValue("Enseignant");
        cmatiere.setValue("matiere");
        cheure.setValue("heure");
        cdate.setValue("Date");

    }

    @FXML
    private void modifier(ActionEvent event) {
//        if(verifUserChampsmodifier()){
        SeanceServices sc = new SeanceServices();

        String sd = tid.getText();
        int si = Integer.parseInt(sd);

        String s = cnomclasse.getValue();

        String s2 = cmatiere.getValue();

        String s3 = cnomenseignant.getValue();

        String s4 = cheure.getValue();

        String s5 = cdate.getValue();

        sc.modifier(new Seance(si, s, s5, s4, s2, s3));

        JOptionPane.showMessageDialog(null, "Seance modifiée !");
        view();
    }

    @FXML

    private void Ajouter(ActionEvent event) {
//        if(verifUserChampsajouter()){
        SeanceServices sc = new SeanceServices();

        String s = cnomclasse.getValue();

        String s2 = cmatiere.getValue();

        String s3 = cnomenseignant.getValue();

        String s4 = cheure.getValue();

        String s5 = cdate.getValue();

        sc.ajouter(new Seance(s, s5, s4, s2, s3));

//        JOptionPane.showMessageDialog(null, "Seance ajoutée !");
        TrayNotification notif = new TrayNotification();
        notif.setAnimationType(AnimationType.POPUP);
        notif.setTitle("SUCCESS");
        notif.setMessage("Seance ajoutée ");
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(Duration.seconds(6));
        view();

    }

    @FXML
    private void modify(MouseEvent event) {
        Seance r = table.getSelectionModel().getSelectedItem();

        String id = Integer.toString(r.getIdSeance());
        tid.setText(id);
        String mat = r.getMatiere();
        cmatiere.setValue(mat);
        String nomclasse = r.getNomClasse();
        cnomclasse.setValue(nomclasse);
        String nomEnseignant = r.getNomEnseignant();
        cnomenseignant.setValue(nomEnseignant);
        String date = r.getDateSeance();
        cdate.setValue(date);
        String h = r.getHeure();
        cheure.setValue(h);

    }

    public void viewNomClasse() {
        SeanceServices sc = new SeanceServices();

        ArrayList lv;

        lv = (ArrayList) sc.afficherNomClasse();
        ObservableList data = FXCollections.observableArrayList(lv);
        cnomclasse.setItems(data);

    }

    public void viewNomMatiere() {
        SeanceServices sc = new SeanceServices();

        ArrayList lv;

        lv = (ArrayList) sc.afficherNomMatiere();

        ObservableList data = FXCollections.observableArrayList(lv);
        cmatiere.setItems(data);

    }

    public void viewNomEnseignant() {
        SeanceServices sc = new SeanceServices();

        ArrayList lv;

        lv = (ArrayList) sc.afficherNomEnseignant();

        ObservableList data = FXCollections.observableArrayList(lv);
        cnomenseignant.setItems(data);

    }
//       public boolean verifUserChampsajouter() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//   
//       
//        cnomclasse.setStyle(styledefault);
//        lmatiere.setStyle(styledefault);
//        cnomenseignant.setStyle(styledefault);
//
//        
//        if (cnomclasse.getValue().equals("")) {
//            cnomclasse.setStyle(style);
//            verif = 1;
//        }
//      
//        if (lmatiere.getText().equals("")) {
//            lmatiere.setStyle(style);
//            verif = 1;
//        }
//         if (cnomenseignant.getValue().equals("")) {
//            cnomenseignant.setStyle(style);
//            verif = 1;
//        }
//          
//        if (verif == 0) {
//            return true;
//        }
//        JOptionPane.showMessageDialog(null, "Verfie!");
//        return false;
//    }

//     public boolean verifUserChampsmodifier() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//   
//       
//          cnomclasse.setStyle(styledefault);
//        lmatiere.setStyle(styledefault);
//        cnomenseignant.setStyle(style
//        
//        if (lclasse1.getText().equals("")) {
//            lclasse1.setStyle(style);
//            verif = 1;
//        }
//       
//       
//        if (lmatiere1.getText().equals("")) {
//            lmatiere1.setStyle(style);
//            verif = 1;
//        }
//           if (lens1.getText().equals("")) {
//            lens1.setStyle(style);
//            verif = 1;
//        }
//            if (tid.getText().equals("")) {
//            tid.setStyle(style);
//            verif = 1;
//        }
//        if (verif == 0) {
//            return true;
//        }
//        JOptionPane.showMessageDialog(null, "Verfie!");
//        return false;
//    }
    void search_user() {
        SeanceServices sc = new SeanceServices();

        ArrayList<Seance> lv;
        lv = (ArrayList<Seance>) sc.triasc();
        ObservableList<Seance> data = FXCollections.observableArrayList(lv);
        colclasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
        colmatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        colens.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
        colheure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        coldate.setCellValueFactory(new PropertyValueFactory<>("dateSeance"));
        table.setItems(data);

        FilteredList<Seance> filteredData = new FilteredList<>(data, b -> true);

        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Seance person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getMatiere().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username

                } //                 else if (Integer.valueOf(person.getId_local()).equals(searchTerm.toLowerCase()) ) {
                //                    return true;// Filter matches email
                //              }
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Seance> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    public List<Seance> trie(int n) throws SQLException {
        // 1 pour classe
        // 2 pour ens
        // 4 pour date
        String requete = null;
        if (n == 0) {
            requete = "select * from seance";
        }
        if (n == 1) {
            requete = "select * from seance order by nomClasse";
        }
        if (n == 2) {
            requete = "select * from seance order by nomEnseignant";
        }
        if (n == 3) {
            requete = "select * from seance order by nomClasse asc,nomEnseignant asc";
        }
        if (n == 4) {
            requete = "select * from seance order by dateSeance";
        }
        if (n == 5) {
            requete = "select * from seance order by nomClasse asc,dateSeance asc";
        }
        if (n == 6) {
            requete = "select * from seance order by dateSeance,nomEnseignant asc";
        }
        if (n == 7) {
            requete = "select * from seance order by nomClasse asc,nomEnseignant asc,dateSeance asc";
        }
        SeanceServices sc = new SeanceServices();
        return sc.triasc1(requete);
    }

    @FXML
    private void trieclasse(MouseEvent event) throws SQLException {

        if (checkclasse.isSelected()) {
            tri += 1;

            viewtri(trie(tri));

        } else {
            tri -= 1;
            viewtri(trie(tri));

        }
        System.out.println(tri);
    }

    @FXML
    private void trieens(MouseEvent event) throws SQLException {

        if (checkens.isSelected()) {
            tri += 2;
            viewtri(trie(tri));

        } else {
            tri -= 2;
            viewtri(trie(tri));

        }
        System.out.println(tri);

    }

    @FXML
    private void triedate(MouseEvent event) throws SQLException {
        if (checkdate.isSelected()) {
            tri += 4;
            viewtri(trie(tri));

        } else {
            tri -= 4;
            viewtri(trie(tri));

        }
        System.out.println(tri);

    }

}
