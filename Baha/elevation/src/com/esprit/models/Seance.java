/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.sql.Date;

/**
 *
 * @author NADHIR
 */
public class Seance {

 
    
    private int idSeance;
    private String nomClasse;
    private String dateSeance;
    private String heure;
    private String matiere;
    private String nomEnseignant;
    
       public Seance(int idSeance, String nomClasse, String dateSeance, String heure, String matiere, String nomEnseignant) {
        this.idSeance = idSeance;
        this.nomClasse = nomClasse;
        this.dateSeance = dateSeance;
        this.heure = heure;
        this.matiere = matiere;
        this.nomEnseignant = nomEnseignant;
    }

    public Seance(String nomClasse, String dateSeance, String heure, String matiere, String nomEnseignant) {
        this.nomClasse = nomClasse;
        this.dateSeance = dateSeance;
        this.heure = heure;
        this.matiere = matiere;
        this.nomEnseignant = nomEnseignant;
    }

    public Seance(String dateSeance, String heure, String matiere, String nomEnseignant) {
        this.dateSeance = dateSeance;
              this.heure = heure;

        this.matiere = matiere;
        this.nomEnseignant = nomEnseignant;
    }

//  public Seance(String dateSeance, String heure, String matiere, String nomClasse) {
//        this.dateSeance = dateSeance;
//              this.heure = heure;
//
//        this.matiere = matiere;
//        this.nomClasse = nomClasse;
//    }
    public Seance(int idSeance, String nomClasse, String matiere, String nomEnseignant) {
        this.idSeance = idSeance;
        this.matiere = matiere;
        this.nomClasse = nomClasse;
        this.nomEnseignant = nomEnseignant;
    }


   

    public String getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(String dateSeance) {
        this.dateSeance = dateSeance;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    

  
 

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public Seance( String dateSeance, String heure, String nomClasse) {
        this.nomClasse = nomClasse;
        this.dateSeance = dateSeance;
        this.heure = heure;
    }



    public Seance(int idSeance, String matiere, String nomClasse) {
        this.idSeance = idSeance;
        this.matiere = matiere;
        this.nomClasse = nomClasse;
    }

   


    public Seance(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }


  
    public Seance(int idSeance) {
        this.idSeance = idSeance;
    }

    public Seance() {
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }


    
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Seance{" + "idSeance=" + idSeance + ", nomClasse=" + nomClasse + ", dateSeance=" + dateSeance + ", heure=" + heure + ", matiere=" + matiere + ", nomEnseignant=" + nomEnseignant + '}';
    }


 
   
}
