/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import com.esprit.models.Classe;
import com.esprit.models.Matiere;
import com.esprit.models.Seance;

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

public class SeanceServices implements IServiceSeance<Seance>{
    
    Connection cnx = (Connection) DataSource.getInstance().getCnx();

    
    @Override
    public void ajouter(Seance c) {
        try {
            String requete = "INSERT INTO Seance (nomClasse, matiere, nomEnseignant,heure,dateSeance) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNomClasse());
            pst.setString(2, c.getMatiere());
            pst.setString(3, c.getNomEnseignant());
            pst.setString(4, c.getHeure());
            pst.setString(5, c.getDateSeance());

            
            pst.executeUpdate();
            System.out.println("Seance Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Seance c) {
        try {
            String requete = "DELETE FROM seance WHERE idSeance=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getIdSeance());
            pst.executeUpdate();
            System.out.println("Seance Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  @Override
    public void delete(int id){
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from seance where idSeance = "+id+"";
            stm.executeUpdate(query);
            System.out.println("Seance Supprimée !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Seance c) {
        try {
            String requete = "UPDATE Seance SET nomClasse=?, matiere=?, nomEnseignant=?, dateSeance=?, heure=? WHERE idSeance=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNomClasse());
            pst.setString(2, c.getMatiere());
            pst.setString(3, c.getNomEnseignant());
            pst.setString(4, c.getDateSeance());
            pst.setString(5, c.getHeure());
            pst.setInt(6, c.getIdSeance());
            pst.executeUpdate();
            System.out.println("Seance Modfiée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Seance> afficher() {
      List<Seance> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM seance";
            PreparedStatement pst = cnx.prepareStatement(requete);          
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getInt("idSeance"),rs.getString("NomClasse"), rs.getString("dateSeance"), rs.getString("heure"), rs.getString("matiere"), rs.getString("nomEnseignant"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
   
    public List<Seance> triasc() {
        List<Seance> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM seance order by idSeance asc";
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getInt("idSeance"),rs.getString("NomClasse"), rs.getString("dateSeance"), rs.getString("heure"), rs.getString("matiere"), rs.getString("nomEnseignant"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
     public List<Seance> triasc1(String requete) {
        List<Seance> list = new ArrayList<>();
        
        try {
           
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getInt("idSeance"),rs.getString("NomClasse"), rs.getString("dateSeance"), rs.getString("heure"), rs.getString("matiere"), rs.getString("nomEnseignant"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Seance> tridesc() {
        List<Seance> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM seance order by idSeance desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
      
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getInt("idSeance"),rs.getString("NomClasse"), rs.getString("dateSeance"), rs.getString("heure"), rs.getString("matiere"), rs.getString("nomEnseignant"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    public List afficherid() {
        List list = new ArrayList<>();
        
        try {
            String requete = "SELECT idSeance FROM seance";
            PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getInt(1)).getIdSeance()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

   public List afficherNomClasse(){
   
   List list = new ArrayList<> ();
   
   try{
       String requete = "SELECT nomClasse FROM classe";
       
   PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Classe(rs.getString(1)).getNomClasse()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
   }
 public List afficherNomEnseignant(){
     
   List list = new ArrayList<> ();
   
   try{
       String requete = "SELECT nom FROM enseignant";
       
   PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getString(1)).getNomEnseignant()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
   }
    public List afficherNomMatiere(){
     
   List list = new ArrayList<> ();
   
   try{
       String requete = "SELECT nomMatiere FROM matiere";
       
   PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Matiere(rs.getString(1)).getNomMatiere()); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
   }
    
        public List<Seance> afficherEmploiEleve(String clh){
     
   List<Seance> list = new ArrayList<> ();
   
   try{
       String requete = "SELECT * FROM seance WHERE nomClasse='"+clh+"'";
       
        PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getString("dateSeance"),rs.getString("heure"), rs.getString("matiere"), rs.getString("NomEnseignant"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
   }
        
             public List<Seance> afficherEmploiEnseignant(String clh){
     
   List<Seance> list = new ArrayList<> ();
   
   try{
       String requete = "SELECT * FROM seance WHERE nomEnseignant='"+clh+"'";
       
        PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Seance(rs.getString("dateSeance"),rs.getString("heure"), rs.getString("NomClasse"))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
   }
             
     public String AjouterSeanceAEmploiEns (String jour,String heure,String c) 
     {
        String Seance="";
           try{
       String requete = "SELECT * FROM seance WHERE dateSeance='"+jour+"' AND heure='"+heure+"' AND nomEnseignant='"+c+"'";
       
        PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            rs.next();
            if (rs!=null){
                
               Seance=rs.getString("matiere")+"\n"+rs.getString("nomClasse");
               
            }
            
           }catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }

         
         return Seance;

}
     
      public String AjouterSeanceAEmploiEleve (String jour,String heure,String c) 
     {
        String Seance="";
           try{
       String requete = "SELECT * FROM seance WHERE dateSeance='"+jour+"' AND heure='"+heure+"' AND nomClasse='"+c+"'";
       
        PreparedStatement pst = cnx.prepareStatement(requete);   

            ResultSet rs = pst.executeQuery(requete);
            rs.next();
            if (rs!=null){
                
               Seance=rs.getString("matiere")+"\n"+rs.getString("nomEnseignant");
               
            }
            
           }catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }

         
         return Seance;

}
// where matiere="+mat


  

}
