/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ConcesionPagoSriDao;
import ec.gob.arcom.migracion.modelo.ConcesionPagoSri;
import ec.gob.arcom.migracion.servicio.ConcesionPagoSriServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "ConcesionPagoSriServicio")
public class ConcesionPagoSriServicioImpl extends GenericServiceImpl<ConcesionPagoSri, Long> implements ConcesionPagoSriServicio {
    @EJB
    private ConcesionPagoSriDao concesionPagoSriDao;
    
    @Override
    public GenericDao<ConcesionPagoSri, Long> getDao() {
        return concesionPagoSriDao;
    }

    @Override
    public Integer ejecutarFuncion(String anio) {
        return concesionPagoSriDao.ejecutarFuncion(anio);
    }

    @Override
    public List<ConcesionPagoSri> findByAnio(String anioFiscal) {
        return concesionPagoSriDao.findByAnio(anioFiscal);
    }
}
