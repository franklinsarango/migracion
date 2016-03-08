/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CatalogoDetalleDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CatalogoDetalleServicio")
public class CatalogoDetalleServicioImpl extends GenericServiceImpl<CatalogoDetalle, Long>
        implements CatalogoDetalleServicio {
    
    @EJB
    private CatalogoDetalleDao catalogoDetalleDao;

    @Override
    public GenericDao<CatalogoDetalle, Long> getDao() {
        return catalogoDetalleDao;
    }

    @Override
    public List<CatalogoDetalle> obtenerPorCatalogo(Long codigoCatalogo) {
        return catalogoDetalleDao.findByCatalogo(codigoCatalogo);
    }

    @Override
    public List<CatalogoDetalle> obtenerPorNemonico(String nemonico) {
        return catalogoDetalleDao.obtenerPorNemonico(nemonico);
    }

    @Override
    public CatalogoDetalle obtenerPorCodigoCatalogoDetalle(Long codigoCatalogoDetalle) {
        return catalogoDetalleDao.obtenerPorCodigoCatalogoDetalle(codigoCatalogoDetalle);
    }
    
}
