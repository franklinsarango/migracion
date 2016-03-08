/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.RegimenDao;
import ec.gob.arcom.migracion.modelo.Regimen;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegimenServicio")
public class RegimenServicioImpl extends GenericServiceImpl<Regimen, Long>
        implements RegimenServicio {
    
    @EJB
    private RegimenDao regimenDao;

    @Override
    public GenericDao<Regimen, Long> getDao() {
        return regimenDao;
    }

}
