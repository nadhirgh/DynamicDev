/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.examen;
import Entities.note;
import Services.IServiceNote;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author infoplus
 */
public class ServiceNote implements IServiceNote{
    
   Connection cnx; 
   
   public ServiceNote(){
   cnx= Maconnexion.getInstance().getConnection();
}
 
   @Override
   public void AddNote(note n){
       try {
           Statement stm=cnx.createStatement();
           
           String query="INSERT INTO note (id_examen, note_cc, note_ex,nom_matiere) VALUES ('"+n.getId_examen()+"','"+n.getNote_cc()+"','"+n.getNote_ex()+"' ,(select nom_matiere from examen where examen.id_examen ="+n.getId_examen()+"))";
           System.out.println(query);
           stm.executeUpdate(query);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   @Override
   public ObservableList<note> AfficherNote()throws SQLException{
       
            Statement stm= cnx.createStatement();
        
        String query="select * from note";
        ResultSet rst =stm.executeQuery(query);
        ObservableList<note>notes= FXCollections.observableArrayList();
        while (rst.next())
        {
        note n= new note();
        n.setId_examen(rst.getInt("id_examen"));
        n.setId_note(rst.getInt("id_note"));
        n.setNom_matiere(rst.getString("nom_matiere"));
        n.setNote_cc(rst.getString("note_cc"));
        n.setNote_ex(rst.getString("note_ex"));
        
        notes.add(n);
        
        }
        
        
        return notes;
    }
   
   @Override
   public void updateNote(int id,note n){
        try {
            Statement stm=cnx.createStatement();
            String query;
            query="update note set note_cc = '"+n.getNote_cc()+"', note_ex = '"+n.getNote_ex()+"' where id_note = "+id+" ";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @Override
    public void deletNote(int id) {
 try {
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from note where id_note = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
    
    

    
}