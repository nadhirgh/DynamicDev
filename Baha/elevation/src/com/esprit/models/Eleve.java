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
public class Eleve {
    private int idEleve;
    private String nomEleve;
    private String classeEleve;
    private int niveau;

    public Eleve(int idEleve, String nomEleve, String classeEleve, int niveau) {
        this.idEleve = idEleve;
        this.nomEleve = nomEleve;
        this.classeEleve = classeEleve;
        this.niveau = niveau;
    }

    public Eleve(String nomEleve, String classeEleve, int niveau) {
        this.nomEleve = nomEleve;
        this.classeEleve = classeEleve;
        this.niveau = niveau;
    }

    public Eleve() {
    }

    public int getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getClasseEleve() {
        return classeEleve;
    }

    public void setClasseEleve(String classeEleve) {
        this.classeEleve = classeEleve;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Eleve{" + "idEleve=" + idEleve + ", nomEleve=" + nomEleve + ", classeEleve=" + classeEleve + ", niveau=" + niveau + '}';
    }
   
}
