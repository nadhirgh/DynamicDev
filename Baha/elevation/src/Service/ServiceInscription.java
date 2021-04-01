/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.inscription;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author infoplus
 */
public class ServiceInscription {
    private static ServiceInscription instanceServiceInscription;

   
    private Statement st;
    private ResultSet rs;
    Connection cnx;
    private ServiceInscription(){
                cnx= Maconnexion.getInstance().getConnection();

        try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceInscription getInscription(){
        if(instanceServiceInscription==null)
            instanceServiceInscription=new ServiceInscription();
        return instanceServiceInscription;
    }
    public void insert(inscription i) throws SQLException{
        
        Timestamp t=Timestamp.valueOf(LocalDateTime.now());
        String req="insert into elevation.inscription(id_ex,id_etud,date_in) values('"+i.getId_ex()+"','"+i.getId_etud()+"','"+t+"') ";
        
            st.executeUpdate(req);
        
    }
    public List<inscription> displayAll() {
        String req="select * from elevation.inscription";
        List<inscription> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                inscription i=new inscription();
                i.setId_inscri(rs.getInt(1));
                i.setId_etud(rs.getInt(2));
                i.setId_ex(rs.getInt(3));
                i.setDate_in(rs.getTimestamp(4));
                
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}