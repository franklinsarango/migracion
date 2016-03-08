/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CoordenadaAreaDao;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CoordenadaAreaServicio")
public class CoordenadaAreaServicioImpl extends GenericServiceImpl<CoordenadaArea, Long>
        implements CoordenadaAreaServicio {
    
    @EJB
    private CoordenadaAreaDao coordenadaAreaDao;

    @Override
    public GenericDao<CoordenadaArea, Long> getDao() {
        return coordenadaAreaDao;
    }

    @Override
    public CoordenadaArea findByCodigoAreaOrden(Long codigoArea, BigInteger orden) {
        return coordenadaAreaDao.findByCodigoAreaOrden(codigoArea, orden);
    }

    @Override
    public List<CoordenadaArea> findByCodigoArea(Long codigoArea) {
        return coordenadaAreaDao.findByCodigoArea(codigoArea);
    }
    
    @Override
    public List<CoordenadaArea> list(Long codigoArea) {
        return coordenadaAreaDao.list(codigoArea);
    }

}
