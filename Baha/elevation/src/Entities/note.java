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
public class note {
    private int id_note;
    private int id_examen;
    private String nom_matiere;
    private String note_cc;
    private String note_ex;
   private int id_etud;
   private String nom;
   private String prenom;

    public note(int id_examen, String nom_matiere, int id_etud, String nom, String prenom) {
        this.id_examen = id_examen;
        this.nom_matiere = nom_matiere;
        this.id_etud = id_etud;
        this.nom = nom;
        this.prenom = prenom;
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

   

    

    
    
    public note() {  
    }

    public int getId_etud() {
        return id_etud;
    }

    public void setId_etud(int id_etud) {
        this.id_etud = id_etud;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

    public String getNote_cc() {
        return note_cc;
    }

    public void setNote_cc(String note_cc) {
        this.note_cc = note_cc;
    }

    public String getNote_ex() {
        return note_ex;
    }

    public void setNote_ex(String note_ex) {
        this.note_ex = note_ex;
    }

    @Override
    public String toString() {
        return "note{" + "id_note=" + id_note + ", id_examen=" + id_examen + ", nom_matiere=" + nom_matiere + ", note_cc=" + note_cc + ", note_ex=" + note_ex + ", id_etud=" + id_etud + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

    

    
    
    
}
