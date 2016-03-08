/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CostoServiciosDao;
import ec.gob.arcom.migracion.modelo.CostoServicios;
import ec.gob.arcom.migracion.servicio.CostoServiciosServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CostoServiciosServicio")
public class CostoServiciosServicioImpl extends GenericServiceImpl<CostoServicios, Long>
        implements CostoServiciosServicio {
    
    @EJB
    private CostoServiciosDao costoServiciosDao;

    @Override
    public GenericDao<CostoServicios, Long> getDao() {
        return costoServiciosDao;
    }

    @Override
    public List<CostoServicios> obtenerPorTipoServicio(Long codigoTipoServicio) {
        return costoServiciosDao.obtenerPorTipoServicio(codigoTipoServicio);
    }

    @Override
    public CostoServicios obtenerPorCodigoConceptoPago(Long codigoConceptoPago) {
        return costoServiciosDao.obtenerPorCodigoConceptoPago(codigoConceptoPago);
    }

}
