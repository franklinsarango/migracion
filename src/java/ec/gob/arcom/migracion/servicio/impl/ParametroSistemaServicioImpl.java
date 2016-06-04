/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ParametroSistemaDao;
import ec.gob.arcom.migracion.modelo.ParametroSistema;
import ec.gob.arcom.migracion.servicio.ParametroSistemaServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ParametroSistemaServicio")
public class ParametroSistemaServicioImpl extends GenericServiceImpl<ParametroSistema, Long>
        implements ParametroSistemaServicio {
    
    @EJB
    private ParametroSistemaDao parametroSistemaDao;
    
    @Override
    public GenericDao<ParametroSistema, Long> getDao() {
        return parametroSistemaDao;
    }

    @Override
    public List<ParametroSistema> findByNemonicoLike(String nemonico) {
        return parametroSistemaDao.findByNemonicoLike(nemonico);
    }

    
    @Override
    public ParametroSistema findByNemonico(String nombre) {
        return parametroSistemaDao.findByNemonico(nombre);
    }
    /*
    @Override
    public List<Catalogo> findByCatalogoPadre(Long codigoCatalogoPadre) {
        return parametroSistemaDao.findByCatalogoPadre(codigoCatalogoPadre);
    }
    */
    
}
