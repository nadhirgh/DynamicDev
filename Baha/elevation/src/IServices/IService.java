/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;

/**
 *
 * @author Abn
 */
public interface IService<T> {
    
    void ajouter(T entite);
    void supprimer(int id);
    void modifier(T entite);
    List<T> afficher();
    List<T> Recherche(String val);
}
