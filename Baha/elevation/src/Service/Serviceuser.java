/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utils.Maconnexion;
import java.sql.Connection;

/**
 *
 * @author infoplus
 */
public class Serviceuser {
    Connection cnx;
    
    public Serviceuser() {
        cnx= Maconnexion.getInstance().getConnection();
    }
    
}
