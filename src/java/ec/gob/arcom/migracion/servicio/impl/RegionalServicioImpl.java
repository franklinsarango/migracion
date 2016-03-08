/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.RegionalDao;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegionalServicio")
public class RegionalServicioImpl extends GenericServiceImpl<Regional, Long>
        implements RegionalServicio {
    
    @EJB
    private RegionalDao regionalDao;

    @Override
    public GenericDao<Regional, Long> getDao() {
        return regionalDao;
    }

    @Override
    public Regional findByNemonico(String nemonico) {
        return regionalDao.findByNemonico(nemonico);
    }

    @Override
    public Regional findByNombre(String nombre) {
        return regionalDao.findByNombre(nombre);
    }

    @Override
    public String[] findByCedulaRucUsuario(String cedulaRucUsuario) {
        return regionalDao.findByCedulaRuc(cedulaRucUsuario);
    }

    @Override
    public List<Regional> findActivos() {
        return regionalDao.findActivos();
    }

}
