/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.TipoMineriaDao;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import ec.gob.arcom.migracion.servicio.TipoMineriaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "TipoMineriaServicio")
public class TipoMineriaServicioImpl extends GenericServiceImpl<TipoMineria, Long>
        implements TipoMineriaServicio {
    
    @EJB
    private TipoMineriaDao tipoMineriaDao;

    @Override
    public GenericDao<TipoMineria, Long> getDao() {
        return tipoMineriaDao;
    }

    @Override
    public TipoMineria findByNemonico(String nemonico) {
        return tipoMineriaDao.findByNemonico(nemonico);
    }

    @Override
    public TipoMineria findByNombre(String nombre) {
        return tipoMineriaDao.findByNombre(nombre);
    }
    
}
