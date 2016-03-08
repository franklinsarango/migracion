/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.AreaMineraDao;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "AreaMineraServicio")
public class AreaMineraServicioImpl extends GenericServiceImpl<AreaMinera, Long>
        implements AreaMineraServicio {
    
    @EJB
    private AreaMineraDao areaMineraDao;

    @Override
    public GenericDao<AreaMinera, Long> getDao() {
        return areaMineraDao;
    }

    @Override
    public AreaMinera obtenerPorConcesionMinera(Long codigoConcesion) {
        return areaMineraDao.obtenerPorConcesionMinera(codigoConcesion);
    }

    @Override
    public void actualizarAreaMinera(AreaMinera areaMinera) throws Exception {
        areaMineraDao.actualizarAreaMinera(areaMinera);
    }

}
