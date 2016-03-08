/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.TipoMaquinariaDao;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.servicio.TipoMaquinariaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "TipoMaquinariaServicio")
public class TipoMaquinariaServicioImpl extends GenericServiceImpl<TipoMaquinaria, Long>
        implements TipoMaquinariaServicio {
    
    @EJB
    private TipoMaquinariaDao tipoMaquinariaDao;

    @Override
    public GenericDao<TipoMaquinaria, Long> getDao() {
        return tipoMaquinariaDao;
    }

}
