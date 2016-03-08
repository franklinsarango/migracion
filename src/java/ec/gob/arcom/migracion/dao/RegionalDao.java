/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.Regional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface RegionalDao extends GenericDao<Regional, Long> {
    
    Regional findByPk(Long id);
    
    Regional findByNemonico(String nemonico);

    Regional findByNombre(String nombre);

    String[] findByCedulaRuc(String cedulaRuc);

    List<Regional> findActivos();
    
}
