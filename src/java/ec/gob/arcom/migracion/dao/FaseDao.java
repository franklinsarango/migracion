/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.Fase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface FaseDao extends GenericDao<Fase, Long> {
    
    Fase findByPk(Long id);
    
    Fase findByNemonico(String nemonico);

    Fase findByNombre(String nombre);

    List<Fase> obtenerFaseLikeDescripcion(String descripcion);
    
}
