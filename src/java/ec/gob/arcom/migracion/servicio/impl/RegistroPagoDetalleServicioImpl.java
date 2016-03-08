/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.RegistroPagoDetalleDao;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.RegistroPagoDetalle;
import ec.gob.arcom.migracion.servicio.RegistroPagoDetalleServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegistroPagoDetalleServicio")
public class RegistroPagoDetalleServicioImpl extends GenericServiceImpl<RegistroPagoDetalle, Long>
        implements RegistroPagoDetalleServicio {
    
    @EJB
    private RegistroPagoDetalleDao registroPagoDetalleDao;

    @Override
    public GenericDao<RegistroPagoDetalle, Long> getDao() {
        return registroPagoDetalleDao;
    }

    @Override
    public void actualizarRegistroPagoDetalle(RegistroPagoDetalle registroPagoDetalle) {
        registroPagoDetalleDao.actualizarRegistroPagoDetalle(registroPagoDetalle);
    }

    @Override
    public void guardarTodo(RegistroPagoDetalle registroPagoDetalle, List<DerechoMineroDto> derechosMineros) {
        this.create(registroPagoDetalle);
    }

    @Override
    public List<RegistroPagoDetalle> obtenerPorCodigoRegistroPago(Long codigoRegistroPago) {
        return registroPagoDetalleDao.obtenerPorCodigoRegistroPago(codigoRegistroPago);
    }

}
