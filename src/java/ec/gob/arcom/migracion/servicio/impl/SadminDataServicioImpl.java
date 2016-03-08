/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SadminDataDao;
import ec.gob.arcom.migracion.modelo.SadminData;
import ec.gob.arcom.migracion.servicio.SadminDataServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SadminDataServicio")
public class SadminDataServicioImpl extends GenericServiceImpl<SadminData, Long>
        implements SadminDataServicio {
    
    @EJB
    private SadminDataDao sadminDataDao;

    @Override
    public GenericDao<SadminData, Long> getDao() {
        return sadminDataDao;
    }

    @Override
    public void actualizarRegistros(SadminData sadminData) throws Exception{
        sadminDataDao.actualizarRegistros(sadminData);
    }
    
}
