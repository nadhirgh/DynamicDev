package com.esprit.models;



public class Enseignant {
    private int idClasse;
    private String nomEnseignant;
    private int matiere;

    public Enseignant(int idClasse, String nomEnseignant, int matiere) {
        this.idClasse = idClasse;
        this.nomEnseignant = nomEnseignant;
        this.matiere = matiere;
    }

    public Enseignant(String nomEnseignant, int matiere) {
        this.nomEnseignant = nomEnseignant;
        this.matiere = matiere;
    }

    public Enseignant() {
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public int getMatiere() {
        return matiere;
    }

    public void setMatiere(int matiere) {
        this.matiere = matiere;
    }

    public Enseignant(int matiere) {
        this.matiere = matiere;
    }

    public Enseignant(String nomEnsaignant) {
        this.nomEnseignant = nomEnsaignant;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "idClasse=" + idClasse + ", nomEnseignant=" + nomEnseignant + ", matiere=" + matiere + '}';
    }
    
    
    
}
