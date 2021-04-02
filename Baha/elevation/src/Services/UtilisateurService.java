/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IService;
import Entities.Revenu;
import Entities.Utilisiateur;
import Utils.Maconnexion;
import Utils.Session;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abn
 */
public class UtilisateurService {

    
    private Statement stm = null;
    final Maconnexion cnx ;
    private PreparedStatement preStm;

    private final String reqGet = " SELECT id ,Nom , Prenom ,role From utilisateur ";

    public UtilisateurService() {
        this.cnx = Maconnexion.getInstance();
    }

    public List<Utilisiateur> getUtilisiateurs() {
        List<Utilisiateur> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet);
            while(rs.next())
            {
                Utilisiateur d = new Utilisiateur();
                d.setId(rs.getInt(1));
                d.setNom(rs.getString(2));
                d.setPrenom(rs.getString(3));
                d.setRole(rs.getInt(4));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les users " + ex.getMessage());
        }
        return null;
    }
    
    
    
}
