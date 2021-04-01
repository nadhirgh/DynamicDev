/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author infoplus
 */
public class inscription {
    private int id_inscri;
    private int id_ex;
    private int id_etud;
    private Timestamp date_in;
    
    public inscription(){
    
}
    public inscription(int id_inscri, int id_etud,int id_ex,Timestamp date_in){
    this.id_inscri = id_inscri;
    this.id_etud = id_etud;
    this.id_ex = id_ex;
    this.date_in = date_in;
    }

    public int getId_inscri() {
        return id_inscri;
    }

    public void setId_inscri(int id_inscri) {
        this.id_inscri = id_inscri;
    }

    public int getId_ex() {
        return id_ex;
    }

    public void setId_ex(int id_ex) {
        this.id_ex = id_ex;
    }

    public int getId_etud() {
        return id_etud;
    }

    public void setId_etud(int id_etud) {
        this.id_etud = id_etud;
    }

    public Timestamp getDate_in() {
        return date_in;
    }

    public void setDate_in(Timestamp date_in) {
        this.date_in = date_in;
    }

    @Override
    public String toString() {
        return "inscription{" + "id_inscri=" + id_inscri + ", id_ex=" + id_ex + ", id_etud=" + id_etud + ", date_in=" + date_in + '}';
    }
    
}