/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.MaquinariaConcesionDao;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.servicio.MaquinariaConcesionServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "MaquinariaConcesionServicio")
public class MaquinariaConcesionServicioImpl extends GenericServiceImpl<MaquinariaConcesion, Long>
        implements MaquinariaConcesionServicio {
    
    @EJB
    private MaquinariaConcesionDao maquinariaConcesionDao;

    @Override
    public GenericDao<MaquinariaConcesion, Long> getDao() {
        return maquinariaConcesionDao;
    }

    @Override
    public List<MaquinariaConcesion> obtenerMaquinariasPorConcesion(Long codigoConcesion) {
        return maquinariaConcesionDao.obtenerMaquinariasPorConcesion(codigoConcesion);
    }

}
