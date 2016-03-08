/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SolicitudDetalleDao;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.SolicitudDetalle;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.servicio.SolicitudDetalleServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SolicitudDetalleServicio")
public class SolicitudDetalleServicioImpl extends GenericServiceImpl<SolicitudDetalle, Long>
        implements SolicitudDetalleServicio {
    
    @EJB
    private SolicitudDetalleDao solicitudDetalleDao;
    @EJB
    private CoordenadaAreaServicio coordenadaAreaServicio;

    @Override
    public GenericDao<SolicitudDetalle, Long> getDao() {
        return solicitudDetalleDao;
    }

    @Override
    public void guardarTodo(SolicitudDetalle solicitudDetalle, CoordenadaArea coordenadaArea) throws Exception{
        //this.create(solicitudDetalle);
        coordenadaAreaServicio.create(coordenadaArea);
    }

    @Override
    public void eliminarTodo(Long codigoSolicitudDetalle, Long codigoCoordenadaArea) {
        //this.delete(codigoSolicitudDetalle);
        coordenadaAreaServicio.delete(codigoCoordenadaArea);
    }

}
