/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.RolDao;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.Rol;
import ec.gob.arcom.migracion.servicio.RolServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RolServicio")
public class RolServicioImpl extends GenericServiceImpl<Rol, Long>
        implements RolServicio {
    
    @EJB
    private RolDao rolDao;

    @Override
    public GenericDao<Rol, Long> getDao() {
        return rolDao;
    }

}
