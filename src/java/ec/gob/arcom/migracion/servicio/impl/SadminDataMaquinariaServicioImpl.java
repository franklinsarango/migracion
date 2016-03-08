/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SadminDataMaquinariaDao;
import ec.gob.arcom.migracion.modelo.SadminDataMaquinaria;
import ec.gob.arcom.migracion.servicio.SadminDataMaquinariaServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SadminDataMaquinariaServicio")
public class SadminDataMaquinariaServicioImpl extends GenericServiceImpl<SadminDataMaquinaria, Long>
        implements SadminDataMaquinariaServicio {
    
    @EJB
    private SadminDataMaquinariaDao sadminDataMaquinariaDao;

    @Override
    public GenericDao<SadminDataMaquinaria, Long> getDao() {
        return sadminDataMaquinariaDao;
    }

    @Override
    public List<SadminDataMaquinaria> obtenerMaquinariasPorCodigoSadmin(String codigoArcom) {
        return sadminDataMaquinariaDao.obtenerMaquinariasPorCodigoSadmin(codigoArcom);
    }

}
