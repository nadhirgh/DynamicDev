/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.examen;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author infoplus
 */
public interface IServiceExamen {
    public void AddExamen(examen e);
    public List<examen> AfficherExamen()throws SQLException;
    public void updateExamen(int id,examen e);
    public void deleteExamen(int id);
}
