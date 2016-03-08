/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface AreaMineraDao extends GenericDao<AreaMinera, Long> {

    AreaMinera obtenerPorConcesionMinera(Long codigoConcesion);

    void actualizarAreaMinera(AreaMinera areaMinera) throws Exception;
    
}
