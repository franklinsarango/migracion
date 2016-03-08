/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SecuenciaDao;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SecuenciaServicio")
public class SecuenciaServicioImpl extends GenericServiceImpl<Secuencia, Long>
        implements SecuenciaServicio {
    
    @EJB
    private SecuenciaDao secuenciaDao;

    @Override
    public GenericDao<Secuencia, Long> getDao() {
        return secuenciaDao;
    }

    @Override
    public Secuencia obtenerPorTabla(String nombreTabla) {
        return secuenciaDao.obtenerPorTabla(nombreTabla);
    }

    @Override
    public Secuencia obtenerPorNemonico(String nemonico) {
        return secuenciaDao.obtenerPorNemonico(nemonico);
    }

}
