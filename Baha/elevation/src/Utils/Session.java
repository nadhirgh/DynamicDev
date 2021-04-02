/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Utilisiateur;

/**
 *
 * @author user
 */
public class Session {
    
    static Utilisiateur connectedUser;
    
    public static Utilisiateur getConnectedUser()
    {
        return connectedUser;
    }
    
    public static Utilisiateur setConnectedUser(Utilisiateur u)
    {
        connectedUser = u ; 
        return connectedUser;
    }
    
}
