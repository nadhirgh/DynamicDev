/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IService;
import Entities.Reclamation;
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
public class ReclamationService implements IService<Reclamation>{

    
    private Statement stm = null;
    final Maconnexion cnx ;
    private PreparedStatement preStm;

    private final String reqGet = " SELECT id , Sujet , Comment , Etat , Date From Reclamation ";
    private final String reqInsert = "INSERT INTO `Reclamation`(Sujet , Comment , Etat , Date , idUtilisateur) VALUES (?,?,?,?,?)";
    private final String reqUpdate = "UPDATE `Reclamation` SET `Sujet`=?,`Comment`=?,`Etat`=? WHERE `id`= ? ";
    private final String reqDel = "delete from Reclamation where id=?";

    public ReclamationService() {
        this.cnx = Maconnexion.getInstance();
    }

    
    
    
    @Override
    public void ajouter(Reclamation entite) {
        
        try {
            preStm = cnx.getConnection().prepareStatement(reqInsert) ;
            preStm.setString(1, entite.getSujet());
            preStm.setString(2, entite.getComment());
            preStm.setString(3, entite.getEtat());
            preStm.setDate(4, (Date) entite.getDate());
            preStm.setInt(5,  Session.getConnectedUser().getId());
            preStm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            preStm=cnx.getConnection().prepareStatement(reqDel);
            preStm.setInt(1, id);
            preStm.executeUpdate();
            System.out.println( id + "Suppression effectuée avec succès");
            }
       
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'annonce " + ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet);
            while(rs.next())
            {
                Reclamation d = new Reclamation();
                d.setId(rs.getInt(1));
                d.setSujet(rs.getString(2));
               d.setComment(rs.getString(3));              
                d.setEtat(rs.getString(4));
                d.setDate(rs.getDate(5));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Reclamation> Recherche(String val) { 
        List<Reclamation> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet + " where ( comment like '%"+ val +"%'"
                    + "OR etat like '%"+ val +"%'"
                    + "OR sujet like '%"+ val +"%'"
                    + " )"
            );
            while(rs.next())
            {
                Reclamation d = new Reclamation();
                 d.setId(rs.getInt(1));
                 d.setSujet(rs.getString(2));
                d.setComment(rs.getString(3));              
                d.setEtat(rs.getString(4));
                d.setDate(rs.getDate(5));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void modifier(Reclamation entite) {
        try {
            preStm = cnx.getConnection().prepareStatement(reqUpdate) ;
            preStm.setString(1, entite.getSujet());
            preStm.setString(2, entite.getComment());
            preStm.setString(3, entite.getEtat());
            preStm.setInt(4, entite.getId());
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Reclamation> ReclamationsByUser(Utilisiateur u) {
        List<Reclamation> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet + " where idUtilisateur = " + u.getId());
            while(rs.next())
            {
                Reclamation d = new Reclamation();
                d.setId(rs.getInt(1));
                d.setSujet(rs.getString(2));
               d.setComment(rs.getString(3));              
                d.setEtat(rs.getString(4));
                d.setDate(rs.getDate(5));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }
    
    
    
}
