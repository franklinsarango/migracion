/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import ec.gob.arcom.migracion.servicio.RecursoServicio;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

/**
 *
 * @author saviasoft
 */
@Stateless(name = "RecursoServicio")
public class RecursoServicioImpl implements RecursoServicio {
    
    @Resource(mappedName = "jdbc/Migracionsadmin")
    private DataSource dataSource;

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Connection obtenerConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
}
