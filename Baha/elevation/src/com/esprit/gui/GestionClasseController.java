/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.BounceIn;
import animatefx.animation.Flash;
import animatefx.animation.Pulse;
import animatefx.animation.RollOut;
import animatefx.animation.Shake;
import animatefx.animation.Swing;
import com.esprit.models.Classe;
import com.esprit.models.Seance;
import com.esprit.services.ClasseServices;
import com.esprit.services.SeanceServices;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class GestionClasseController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tnbr;
    @FXML
    private Button btn;
    @FXML
    private TableView<Classe> table;

    @FXML
    private TextField tid;
  
   
    private TextField tniveau;
    
    @FXML
    private TextField rechercher;
    private TableColumn<Classe, Integer> idClasse;
    @FXML
    private TableColumn<Classe,String> nomClasse;
    @FXML
    private TableColumn<Classe, Integer> nbr;
    @FXML
    private TableColumn<Classe, Integer> niveau;
    @FXML
    private ComboBox<String> cniveau;

    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatusMini;
    @FXML
    private CheckBox checkclasse;
    @FXML
    private CheckBox checkens;
    @FXML
    private CheckBox checkdate;
    int tri;

    public void view(){
         try {
          ClasseServices sc = new ClasseServices();
        

           ArrayList<Classe> lv= (ArrayList<Classe>) sc.afficher();

       
            ObservableList<Classe> data = FXCollections.observableArrayList(lv);
                  nomClasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
                  nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
                  niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            table.setItems(data);
          
        } catch (Exception ex) {
            Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      public void viewtri(List l){
         try {
        

         

       
                  ObservableList<Classe> data = FXCollections.observableArrayList(l);

                   nomClasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
                  nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
                  niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));

                  table.setItems(data);
          
        } catch (Exception ex) {
            Logger.getLogger(GestionSeanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               new Swing(pnlStatus).play();

       
            
           ObservableList data = FXCollections.observableArrayList("1","2","3","4","5","6");
        cniveau.setItems(data);

        view();
        view();
        search_user();
    }    
//    @FXML
//    private void supprimer(ActionEvent event) {
//          ClasseServices sc = new ClasseServices();
//          Classe l = new Classe();
//
//
//        l.setIdClasse((int) id_sup.getSelectionModel().getSelectedItem());
//        
//        sc.supprimer(l);
//        view();
//    }
 @FXML
    private void supprimer2(ActionEvent event) {
          ClasseServices sc = new ClasseServices();


        if(table.getSelectionModel() != null)
        sc.delete(table.getSelectionModel().getSelectedItem().getIdClasse()) ;
         ArrayList lv;
       
            lv = (ArrayList) sc.afficher();
            ObservableList data = FXCollections.observableArrayList(lv);
        table.setItems(data);
        clearSelection();
    }
    @FXML
    void clearSelection()
    {
        table.getSelectionModel().clearSelection();
        tnom.setText("");
       tnbr.setText("");
       cniveau.setValue("");
       
    }
    @FXML
    private void modifier(ActionEvent event) {
//        if(verifUserChampsmodifier()){
          ClasseServices sc = new ClasseServices();
          String s=tid.getText();
          int si=Integer.parseInt(s);
          String s1=tnbr.getText();
          int si1=Integer.parseInt(s1);
          String s0=cniveau.getValue();
          int si2=Integer.parseInt(s0);
          String s2;
            s2 = tnom.getText();
          
        sc.modifier(new Classe(si,s2,si1,si2));
        
        JOptionPane.showMessageDialog(null, "Abonnement modifiée !");
        view();}

    @FXML
    private void AjouterClasse(ActionEvent event) {
//        if(verifUserChampsajouter()){
            ClasseServices sc = new ClasseServices();
             String s2;
            s2 = tnom.getText();
          String s3=tnbr.getText();
          int si4=Integer.parseInt(s3);
        String s4=cniveau.getValue();
          int si5=Integer.parseInt(s4);
          
        sc.ajouter(new Classe(s2,si4,si5));
        
        JOptionPane.showMessageDialog(null, "Classe ajoutée !");
  view();
  //viewid();
//        }
  
    }

    @FXML
    private void modify(MouseEvent event) {
        Classe r = table.getSelectionModel().getSelectedItem();
        String id  = Integer.toString(r.getIdClasse());
        tid.setText(id);
        String nom = r.getNomClasse();
        tnom.setText(nom);
        String nbrr  = Integer.toString(r.getNbr());
        tnbr.setText(nbrr);
        String niveauu = Integer.toString(r.getNiveau());
        cniveau.setValue(niveauu);
        

    }

//       public boolean verifUserChampsajouter() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//   
//       
//        tnom.setStyle(styledefault);
//        cniveau.setStyle(styledefault);
//        tnbr.setStyle(styledefault);
//        
//        if (tnom.getText().equals("")) {
//            tnom.setStyle(style);
//            verif = 1;
//        }
//       
//        if (cniveau.getValue().equals("")) {
//            cniveau.setStyle(style);
//            verif = 1;
//        }
//        if (tnbr.getText().equals("")) {
//            tnbr.setStyle(style);
//            verif = 1;
//        }
//          
//        if (verif == 0) {
//            return true;
//        }
//        JOptionPane.showMessageDialog(null, "Verfie!");
//        return false;
//    }
//
//     public boolean verifUserChampsmodifier() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//   
//       
//        tnom1.setStyle(styledefault);
//        cniveau1.setStyle(styledefault);
//        tnbr1.setStyle(styledefault);
//        
//        if (tnom1.getText().equals("")) {
//            tnom1.setStyle(style);
//            verif = 1;
//        }
//       
//        if (cniveau1.getValue().equals("")) {
//            cniveau1.setStyle(style);
//            verif = 1;
//        }
//        if (tnbr1.getText().equals("")) {
//            tnbr1.setStyle(style);
//            verif = 1;
//        }
//          
//        if (verif == 0) {
//            return true;
//        }
//        JOptionPane.showMessageDialog(null, "Verfie!");
//        return false;
//    }

//    @FXML
//    private void menu(ActionEvent event) throws IOException {
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));/* Exception */
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
       void search_user() {
ClasseServices sc = new ClasseServices();
        

           ArrayList<Classe> lv;
            lv = (ArrayList<Classe>) sc.triasc();
            ObservableList<Classe> data = FXCollections.observableArrayList(lv);
                  nomClasse.setCellValueFactory(new PropertyValueFactory<>("nomClasse"));
                  nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
                  niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        table.setItems(data);
       
        FilteredList<Classe> filteredData = new FilteredList<>(data, b -> true);
        
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Classe person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNomClasse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                }
               
//                 else if (Integer.valueOf(person.getId_local()).equals(searchTerm.toLowerCase()) ) {
//                    return true;// Filter matches email
//              }
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Classe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
 public List<Classe> trie(int n) throws SQLException
{
    // 1 pour classe
    // 2 pour nbr
    // 4 pour niveau
    String requete = null;
    if(n==0)
    {
      requete="select * from classe";
    }
    if(n==1)
    {
      requete="select * from classe order by nomClasse";
    }
    if (n==2)
    {
      requete="select * from classe order by nbr";
    }
    if(n==3)
    {
       requete="select * from classe order by nomClasse asc,nbr asc";
    }
    if (n==4)
    {
        requete="select * from classe order by niveau";
    }
    if (n==5)
    {
         requete="select * from classe order by nomClasse asc,niveau asc";
    }
    if (n==6)
    {
        requete="select * from classe order by niveau,nbr asc";
    }
    if (n==7)
    {
         requete="select * from classe order by nomClasse asc,nbr asc,niveau asc";
    }
ClasseServices sc = new ClasseServices();
    return sc.triasc1(requete);
}

    @FXML
    private void trieclasse(MouseEvent event) throws SQLException {
       
        if(checkclasse.isSelected()){
        tri+=1;
        
        viewtri(trie(tri));
        
        }else{
        tri-=1;
        viewtri(trie(tri));

        }
         System.out.println(tri);   
    }

    @FXML
    private void trieens(MouseEvent event) throws SQLException {
        
         if(checkens.isSelected()){
        tri+=2; 
        viewtri(trie(tri));
        
        }else{
        tri-=2;
        viewtri(trie(tri));

        }
                  System.out.println(tri);   

    }

    @FXML
    private void triedate(MouseEvent event) throws SQLException {
         if(checkdate.isSelected()){
        tri+=4; 
        viewtri(trie(tri));
        
        }else{
        tri-=4;
        viewtri(trie(tri));

        }
                  System.out.println(tri);   

    }
}
