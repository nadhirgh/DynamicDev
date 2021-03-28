/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import com.esprit.models.Classe;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NADHIR
 */

public class ClasseServices implements IServiceClasse<Classe>{
    
    Connection cnx = (Connection) DataSource.getInstance().getCnx();

    
    @Override
    public void ajouter(Classe c) {
        try {
            String requete = "INSERT INTO classe (nomClasse, nbr, niveau) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNomClasse());
            pst.setInt(2, c.getNbr());
            pst.setInt(3, c.getNiveau());
            pst.executeUpdate();
            System.out.println("Classe Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Classe c) {
        try {
            String requete = "DELETE FROM classe WHERE idClasse=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getIdClasse());
            pst.executeUpdate();
            System.out.println("Classe Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  @Override
    public void delete(int id){
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from classe where idClasse = "+id+"";
            stm.executeUpdate(query);
            System.out.println("classe Supprimée !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Classe c) {
        try {
            String requete = "UPDATE classe SET nomClasse=?, nbr=?, niveau=? WHERE idClasse=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setString(1, c.getNomClasse());
            pst.setInt(2, c.getNbr());
            pst.setInt(3, c.getNiveau());
            pst.setInt(4, c.getIdClasse());
            pst.executeUpdate();
            System.out.println("Classe Modfiée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Classe> afficher() {
      List<Classe> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM classe";
            PreparedStatement pst = cnx.prepareStatement(requete);          
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Classe(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
   
    @Override
    public List<Classe> triasc() {
        List<Classe> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM classe order by idClasse asc";
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
            list.add(new Classe(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Classe> tridesc() {
        List<Classe> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM classe order by idClasse desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
              list.add(new Classe(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    public List afficherid() {
        List list = new ArrayList<>();
        
        try {
            String requete = "SELECT idClasse FROM classe";
            PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Classe(rs.getInt(1)).getIdClasse()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
  public List<Classe> triasc1(String requete) {
        List<Classe> list = new ArrayList<>();
        
        try {
           
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
              list.add(new Classe(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

}
