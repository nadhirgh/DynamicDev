/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import java.util.List;

/**
 *
 * @author Iyed Zidi
 * @param <Classe>
 */
public interface IServiceClasse<Classe> {
    public void ajouter(Classe t);
    public void supprimer(Classe t);
    public void modifier(Classe t);
    public List<Classe> afficher();
    public List<Classe> triasc();
    public List<Classe> tridesc();
        public void delete(int id);
}
