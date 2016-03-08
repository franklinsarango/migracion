/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Local;

/**
 *
 * @author saviasoft
 */
@Local
public interface RecursoServicio {
    
    Connection obtenerConnection() throws SQLException;
    
}
