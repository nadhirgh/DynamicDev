/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author infoplus
 */
public class examen {
    private int id_examen;
    private int id_matiere;
    public String nom_matiere;
    private int id_note;
    private Date date_ex;
    private String duree_ex;
    private String PDF;
    private int inscription;

    public int getInscription() {
        return inscription;
    }

    public void setInscription(int inscription) {
        this.inscription = inscription;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public examen(int id_matiere, String nom_matiere, int id_note) {
        this.id_matiere = id_matiere;
        this.nom_matiere = nom_matiere;
        this.id_note = id_note;
    }
    
    

    public examen() {
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

   

    public Date getDate_ex() {
        return date_ex;
    }

    public void setDate_ex(Date date_ex) {
        this.date_ex = date_ex;
    }

    public String getDuree_ex() {
        return duree_ex;
    }

    public void setDuree_ex(String duree_ex) {
        this.duree_ex = duree_ex;
    }

    

    @Override
    public String toString() {
        return "examen{" + "id_examen=" + id_examen + ", id_matiere=" + id_matiere + ", nom_matiere=" + nom_matiere + ", id_note=" + id_note + ", date_ex=" + date_ex + ", duree_ex=" + duree_ex + "}\n";
    }

    

  
    
    
    
}
