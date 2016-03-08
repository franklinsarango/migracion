/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SolicitudDao;
import ec.gob.arcom.migracion.modelo.Solicitud;
import ec.gob.arcom.migracion.servicio.SolicitudServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SolicitudServicio")
public class SolicitudServicioImpl extends GenericServiceImpl<Solicitud, Long>
        implements SolicitudServicio {
    
    @EJB
    private SolicitudDao solicitudDao;
    
    @Override
    public GenericDao<Solicitud, Long> getDao() {
        return solicitudDao;
    }
    
    @Override
    public Solicitud obtenerPorSecuenciaSolicitud(Long secuenciaSolicitud) {
        return solicitudDao.obtenerPorSecuenciaSolicitud(secuenciaSolicitud);
    }
    
    @Override
    public Solicitud obtenerPorCodigoArcom(String codigoArcom) {
        return solicitudDao.obtenerPorCodigoArcom(codigoArcom);
    }

    @Override
    public void actualizarServicio(Solicitud solicitud) throws Exception {
        solicitudDao.actualizarSolicitud(solicitud);
    }
    
}
