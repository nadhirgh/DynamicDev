/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.examen;
import Services.IServiceExamen;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author infoplus
 */
public class ServiceExamen implements IServiceExamen{
    
    Connection cnx;

    public ServiceExamen() {
        cnx= Maconnexion.getInstance().getConnection();
    }
    

    @Override
    public void AddExamen(examen e) {
        try {
            Statement stm=cnx.createStatement();
        
        String query="INSERT INTO examen ( nom_matiere, duree_ex, date_ex ) VALUES ( '"+e.getNom_matiere()+"','"+e.getDuree_ex()+"','"+e.getDate_ex()+"')";
        stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceExamen.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override
    public ObservableList<examen> AfficherExamen()throws SQLException{
       
            Statement stm= cnx.createStatement();
        
        String query="select * from examen";
        ResultSet rst =stm.executeQuery(query);
        ObservableList<examen>examens= FXCollections.observableArrayList();
        while (rst.next())
        {
        examen e= new examen();
        e.setId_examen(rst.getInt("id_examen"));
        e.setId_matiere(rst.getInt("id_matiere"));
        e.setId_note(rst.getInt("id_note"));
        e.setNom_matiere(rst.getString("nom_matiere"));
        e.setDuree_ex(rst.getString("duree_ex"));
        e.setDate_ex(rst.getDate("date_ex"));
        
        examens.add(e);
        
        }
        
        
        return examens;
    }
    @Override
    public void updateExamen(int id,examen e){
        try {
            Statement stm=cnx.createStatement();
            String query;
            query="update examen set nom_matiere = '"+e.getNom_matiere()+"', duree_ex = '"+e.getDuree_ex()+"', date_ex = '"+e.getDate_ex()+"' where id_examen = "+id+" ";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void deleteExamen(int id){
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from examen where id_examen = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}