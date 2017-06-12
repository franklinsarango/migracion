/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.AdjuntoDao;
import ec.gob.arcom.migracion.modelo.Adjunto;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.AdjuntoServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "AdjuntoServicio")
public class AdjuntoServicioImpl extends GenericServiceImpl<Adjunto, Long> implements AdjuntoServicio {
    @EJB
    private AdjuntoDao adjuntoDao;
    
    @Override
    public GenericDao<Adjunto, Long> getDao() {
        return adjuntoDao;
    }

    @Override
    public List<Adjunto> findByOperativo(Operativo o) {
        return adjuntoDao.findByOperativo(o);
    }
}
