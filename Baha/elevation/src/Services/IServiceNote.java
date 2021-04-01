/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.examen;
import Entities.note;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author infoplus
 */
public interface IServiceNote {
    public void AddNote(note n);
    public List<note> AfficherNote()throws SQLException;
    public void updateNote(int id,note n);
    public void deletNote(int id);
   
}
