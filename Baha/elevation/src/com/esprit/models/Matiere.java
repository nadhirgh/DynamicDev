/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author NADHIR
 */
public class Matiere {
       private int idMatiere;
    private String nomMatiere;
    private int niveau;

    public Matiere(int idMatiere, String nomMatiere, int niveau) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
        this.niveau = niveau;
    }

    public Matiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Matiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Matiere() {
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Matiere{" + "idMatiere=" + idMatiere + ", nomMatiere=" + nomMatiere + ", niveau=" + niveau + '}';
    }
    
}
