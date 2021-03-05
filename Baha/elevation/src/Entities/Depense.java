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
public class Depense {
    int id ;
    int id_finance ;
    double valeur ;
    Date date ;
    String Source ;
    String Description ;
    String Justificatif ;

    
    public void remplirDepense(Depense d) {
        id = d.getId();
        Source = d.getSource();
        Description = d.getDescription();
        Justificatif = d.getJustificatif();
        id_finance = d.getId_finance();
        valeur = d.getValeur();
        date = d.getDate();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_finance() {
        return id_finance;
    }

    public void setId_finance(int id_finance) {
        this.id_finance = id_finance;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getJustificatif() {
        return Justificatif;
    }

    public void setJustificatif(String Justificatif) {
        this.Justificatif = Justificatif;
    }
    
    
}
