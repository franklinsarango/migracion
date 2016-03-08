/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CoordenadaCotaDao;
import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import ec.gob.arcom.migracion.servicio.CoordenadaCotaServicio;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CoordenadaCotaServicio")
public class CoordenadaCotaServicioImpl extends GenericServiceImpl<CoordenadaCota, Long>
        implements CoordenadaCotaServicio {
    
    @EJB
    private CoordenadaCotaDao coordenadaCotaDao;

    @Override
    public GenericDao<CoordenadaCota, Long> getDao() {
        return coordenadaCotaDao;
    }

    @Override
    public List<CoordenadaCota> obtenerPorContratoOrden(Long codigoContrato, BigInteger orden) {
        return coordenadaCotaDao.obtenerPorContratoOrden(codigoContrato, orden);
    }

    @Override
    public List<CoordenadaCota> findByCodigoContrato(Long codigoContrato) {
        return coordenadaCotaDao.findByCodigoContrato(codigoContrato);
    }

}
