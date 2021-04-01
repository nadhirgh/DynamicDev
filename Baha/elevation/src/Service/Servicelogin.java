/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;


/**
 *
 * @author infoplus
 */
public class Servicelogin {
    Connection cnx;

    public Servicelogin() {
        cnx= Maconnexion.getInstance().getConnection();
        
        
        
        
    }
    
}
