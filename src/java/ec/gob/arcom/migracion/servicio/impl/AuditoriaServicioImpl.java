/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.AuditoriaDao;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "AuditoriaServicio")
public class AuditoriaServicioImpl extends GenericServiceImpl<Auditoria, Long>
        implements AuditoriaServicio {
    
    @EJB
    private AuditoriaDao auditoriaDao;

    @Override
    public GenericDao<Auditoria, Long> getDao() {
        return auditoriaDao;
    }

}
