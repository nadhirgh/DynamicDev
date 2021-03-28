/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;



import java.util.List;

/**
 *
 * @author trabe
 * @param <Seance>
 */
public interface IServiceSeance<Seance> {
    public void ajouter(Seance t);
    public void supprimer(Seance t);
    public void modifier(Seance t);
    public List<Seance> afficher();
    public List<Seance> triasc();
    public List<Seance> tridesc();
     public void delete(int id);
}
