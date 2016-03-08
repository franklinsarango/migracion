/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ResolucionDao;
import ec.gob.arcom.migracion.modelo.Resolucion;
import ec.gob.arcom.migracion.servicio.ResolucionServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ResolucionServicio")
public class ResolucionServicioImpl extends GenericServiceImpl<Resolucion, Long>
        implements ResolucionServicio {
    
    @EJB
    private ResolucionDao resolucionDao;

    @Override
    public GenericDao<Resolucion, Long> getDao() {
        return resolucionDao;
    }

}
