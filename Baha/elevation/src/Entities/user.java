/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author infoplus
 */
public class user {
    private int id_etud;
    private String nom;
    private String prenom;
    private String username;
    private String password;

    public user(int id_etud) {
        this.id_etud = id_etud;
    }
    
    
    
    public user(){
        
    }

    public int getId_etud() {
        return id_etud;
    }

    public void setId_etud(int id_etud) {
        this.id_etud = id_etud;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" + "id_etud=" + id_etud + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
