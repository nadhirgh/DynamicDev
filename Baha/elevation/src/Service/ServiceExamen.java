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
        String replace = e.getPDF().replace("\\", "/");
        String query="INSERT INTO examen ( inscription,PDF,nom_matiere, duree_ex, date_ex , id_matiere) VALUES ( '"+0+"','"+e.getPDF()+"','"+e.getNom_matiere()+"','"+e.getDuree_ex()+"','"+e.getDate_ex()+"',(select id_matiere from matiere where matiere.nom_matiere ='"+e.getNom_matiere()+"'))";
        stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceExamen.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public void increment(int id) throws SQLException{
         Statement stm= cnx.createStatement();
        int c=0;
        String query="select * from examen where id_examen='"+id+"'";
        ResultSet rst =stm.executeQuery(query);
        List<examen>examens= new ArrayList();
        while (rst.next())
        {
        examen e= new examen();
        e.setInscription(rst.getInt(7));
        examens.add(e);  
        }
        c=examens.get(0).getInscription();
        c++;
        String req1="update examen set inscription='"+c+"' where id_examen='"+id+"'";
         try {
             stm.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void dec(int id) throws SQLException{
         Statement stm= cnx.createStatement();
        int c=0;
        String query="select * from examen where id_examen='"+id+"'";
        ResultSet rst =stm.executeQuery(query);
        List<examen>examens= new ArrayList();
        while (rst.next())
        {
        examen e= new examen();
        e.setInscription(rst.getInt(7));
        examens.add(e);  
        }
        c=examens.get(0).getInscription();
        if(c!=0)
        c--;
        String req1="update examen set inscription='"+c+"' where id_examen='"+id+"'";
         try {
             stm.executeUpdate(req1);
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
        
        e.setNom_matiere(rst.getString("nom_matiere"));
        e.setDuree_ex(rst.getString("duree_ex"));
        e.setDate_ex(rst.getDate("date_ex"));
        e.setInscription(rst.getInt(7));
        
        examens.add(e);
        
        }
        
        
        return examens;
    }
    
    public List<examen> displayAllAfterSearch(String s) {
        String req="select * from examen where nom_matiere like '%"+s+"%' ";
        List<examen> list=new ArrayList<>();       
        
        try {
            Statement stm= cnx.createStatement();
            ResultSet rst =stm.executeQuery(req);
            while(rst.next()){
                examen e=new examen();
               e.setId_examen(rst.getInt("id_examen"));
        e.setId_matiere(rst.getInt("id_matiere"));
        
        e.setNom_matiere(rst.getString("nom_matiere"));
        e.setDuree_ex(rst.getString("duree_ex"));
        e.setDate_ex(rst.getDate("date_ex"));
        e.setInscription(rst.getInt(7));
        
        list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    @Override
    public void updateExamen(int id,examen e){
        try {
            String replace = e.getPDF().replace("\\", "/");
            Statement stm=cnx.createStatement();
            String query;
            query="update examen set PDF='"+e.getPDF()+"' ,  nom_matiere = '"+e.getNom_matiere()+"',id_matiere = (select id_matiere from matiere where matiere.nom_matiere ='"+e.getNom_matiere()+"'), duree_ex = '"+e.getDuree_ex()+"', date_ex = '"+e.getDate_ex()+"' where id_examen = "+id+" ";
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
