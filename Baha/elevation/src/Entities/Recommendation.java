/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Abn
 */
public class Recommendation {
    int id ;
    Date date ;
    String Sujet ;
    String Comment ;
    String Etat ;
    Utilisiateur User;

    public Utilisiateur getUser() {
        return User;
    }

    public void setUser(Utilisiateur User) {
        this.User = User;
    }
    
    public void remplirRecommendation(Recommendation d) {
        id = d.getId();
        Etat = d.getEtat();
        Sujet = d.getSujet();
        Comment = d.getComment();
        date = d.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }


    
    
}
