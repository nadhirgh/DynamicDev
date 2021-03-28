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
public class Classe {
    private int idClasse;
    private String nomClasse;
    private int nbr;
    private int niveau;

    public Classe(String nomClasse) {
        this.nomClasse = nomClasse;
    }

   
    
    public Classe(int idClasse, String nomClasse, int nbr, int niveau) {
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
        this.nbr = nbr;
        this.niveau = niveau;
    }
    public Classe(String nomClasse, int nbr, int niveau) {
        this.nomClasse = nomClasse;
        this.nbr = nbr;
        this.niveau = niveau;
    }

    public Classe(int idClasse) {
        this.idClasse = idClasse;
    }

    public Classe() {
    }
    

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
     
    @Override
    public String toString() {
        return "Classe{" + "idClasse=" + idClasse + ", nomClasse=" + nomClasse + ", nbr=" + nbr + ", niveau=" + niveau + '}';
    }
    
    
}
