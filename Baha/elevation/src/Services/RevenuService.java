/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IService;
import Entities.Revenu;
import Utils.Maconnexion;
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
public class RevenuService implements IService<Revenu>{

    
    private Statement stm = null;
    final Maconnexion cnx ;
    private PreparedStatement preStm;

    private final String reqGet = " SELECT id , id_finance , valeur ,date ,source ,description , justificatif From revenu ";
    private final String reqInsert = "INSERT INTO `revenu`(`id_finance`, `valeur`, `date`, `source`, `description`, `justificatif`) VALUES (?,?,?,?,?,?)";
    private final String reqUpdate = "UPDATE `revenu` SET `valeur`=?,`date`=?,`source`=?,`description`=?,`justificatif`=? WHERE `id`= ? ";
    private final String reqDel = "delete from revenu where id=?";

    public RevenuService() {
        this.cnx = Maconnexion.getInstance();
    }

    
    
    
    @Override
    public void ajouter(Revenu entite) {
        
        try {
            preStm = cnx.getConnection().prepareStatement(reqInsert) ;
            preStm.setInt(1, entite.getId_finance());
            preStm.setDouble(2, entite.getValeur());
            preStm.setDate(3, (Date) entite.getDate());
            preStm.setString(4, entite.getSource());
            preStm.setString(5, entite.getDescription());
            preStm.setString(6, entite.getJustificatif());
            preStm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RevenuService.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Revenu> afficher() {
        List<Revenu> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet);
            while(rs.next())
            {
                Revenu d = new Revenu();
                d.setId(rs.getInt(1));
                d.setId_finance(rs.getInt(2));
                d.setValeur(rs.getDouble(3));
                d.setDate(rs.getDate(4));
                d.setSource(rs.getString(5));
                d.setDescription(rs.getString(6));
                d.setJustificatif(rs.getString(7));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Revenu> Recherche(String val) { 
        List<Revenu> Ann= new ArrayList<>();    
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet + " where ( source like '%"+ val +"%'"
                    + "OR description like '%"+ val +"%'"
                    + "OR justificatif like '%"+ val +"%'"
                    + "OR id_finance like '%"+ val +"%'"
                    + "OR valeur like '%"+ val +"%'"
                    + "OR date like '%"+ val +"%'"
                    + " )"
            );
            while(rs.next())
            {
                Revenu d = new Revenu();
                d.setId(rs.getInt(1));
                d.setId_finance(rs.getInt(2));
                d.setValeur(rs.getDouble(3));
                d.setDate(rs.getDate(4));
                d.setSource(rs.getString(5));
                d.setDescription(rs.getString(6));
                d.setJustificatif(rs.getString(7));
                Ann.add(d);}
                return Ann;
            } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void modifier(Revenu entite) {
        try {
            preStm = cnx.getConnection().prepareStatement(reqUpdate) ;
            preStm.setDouble(1, entite.getValeur());
            preStm.setDate(2, (Date) entite.getDate());
            preStm.setString(3, entite.getSource());
            preStm.setString(4, entite.getDescription());
            preStm.setString(5, entite.getJustificatif());
            preStm.setInt(6, entite.getId());
            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RevenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public double totalRevenu() {
        double total = 0;
        try {
            stm=cnx.getConnection().createStatement();
            ResultSet rs =stm.executeQuery(reqGet);
            while(rs.next())
            {
                total += rs.getDouble(3);
            }
            return total;
        }
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return total;
    }
    
    
    
    
}
