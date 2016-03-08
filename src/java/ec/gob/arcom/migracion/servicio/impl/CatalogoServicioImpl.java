/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CatalogoDao;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CatalogoServicio")
public class CatalogoServicioImpl extends GenericServiceImpl<Catalogo, Long>
        implements CatalogoServicio {
    
    @EJB
    private CatalogoDao catalogoDao;

    @Override
    public GenericDao<Catalogo, Long> getDao() {
        return catalogoDao;
    }

    @Override
    public Catalogo findByNemonico(String nemonico) {
        return catalogoDao.findByNemonico(nemonico);
    }

    @Override
    public Catalogo findByNombre(String nombre) {
        return catalogoDao.findByNombre(nombre);
    }

    @Override
    public List<Catalogo> findByCatalogoPadre(Long codigoCatalogoPadre) {
        return catalogoDao.findByCatalogoPadre(codigoCatalogoPadre);
    }
    
}
