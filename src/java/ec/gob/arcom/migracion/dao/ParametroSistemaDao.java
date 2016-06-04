/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.ParametroSistema;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface ParametroSistemaDao extends GenericDao<ParametroSistema, Long> {
    
    //Catalogo findByPk(Long id);
    
    //Catalogo findByNemonico(String nemonico);
    List<ParametroSistema> findByNemonicoLike(String nemonico);

    ParametroSistema findByNemonico(String nombre);

    //List<Catalogo> findByCatalogoPadre(Long catalogoPadre);
    
}
