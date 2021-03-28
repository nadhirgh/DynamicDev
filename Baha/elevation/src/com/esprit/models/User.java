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
public class User {

    private int idUser;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private String emailParent;
    private int numeroParent;
    private String sexe;
    private String classe;
    private int niveau;
    private int numero;
    private Date Datedenaissance;
    private String matiere;
    private String status;
    private String image;

    public User(String nom, String prenom, String mdp, String email, String sexe, int numero, Date Datedenaissance, String matiere, String status, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.sexe = sexe;
        this.numero = numero;
        this.Datedenaissance = Datedenaissance;
        this.matiere = matiere;
        this.status = status;
        this.image = image;
    }

    public User(int idUser, String nom, String prenom, String mdp, String email, String sexe, int numero, Date Datedenaissance, String matiere, String status, String image) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.sexe = sexe;
        this.numero = numero;
        this.Datedenaissance = Datedenaissance;
        this.matiere = matiere;
        this.status = status;
        this.image = image;
    }

    public User(String nom, String prenom, String mdp, String email, String emailParent, int numeroParent, String sexe, String classe, int niveau, Date Datedenaissance, String status, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.emailParent = emailParent;
        this.numeroParent = numeroParent;
        this.sexe = sexe;
        this.classe = classe;
        this.niveau = niveau;
        this.Datedenaissance = Datedenaissance;
        this.status = status;
        this.image = image;
    }

  
    public User(int idUser, String nom, String prenom, String mdp, String email, String emailParent, int numeroParent, String sexe, String classe, int niveau, Date Datedenaissance, String status, String image) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.emailParent = emailParent;
        this.numeroParent = numeroParent;
        this.sexe = sexe;
        this.classe = classe;
        this.niveau = niveau;
        this.Datedenaissance = Datedenaissance;
        this.status = status;
        this.image = image;
    }
  public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public int getNumeroParent() {
        return numeroParent;
    }

    public void setNumeroParent(int numeroParent) {
        this.numeroParent = numeroParent;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDatedenaissance() {
        return Datedenaissance;
    }

    public void setDatedenaissance(Date Datedenaissance) {
        this.Datedenaissance = Datedenaissance;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", emailParent=" + emailParent + ", numeroParent=" + numeroParent + ", sexe=" + sexe + ", classe=" + classe + ", niveau=" + niveau + ", numero=" + numero + ", Datedenaissance=" + Datedenaissance + ", matiere=" + matiere + ", status=" + status + ", image=" + image + '}';
    }

   

}
