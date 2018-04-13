/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.GestionVacacionDao;
import ec.gob.arcom.migracion.modelo.GestionVacacion;
import ec.gob.arcom.migracion.servicio.GestionVacacionServicio;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "GestionVacacionServicio")
public class GestionVacacionServicioImpl extends GenericServiceImpl<GestionVacacion, Long> implements GestionVacacionServicio {
    @EJB
    private GestionVacacionDao gestionVacacionDao;
    
    @Override
    public GenericDao<GestionVacacion, Long> getDao() {
        return gestionVacacionDao;
    }

    @Override
    public GestionVacacion findByUser(Long codigoUsuario) {
        return gestionVacacionDao.findByUser(codigoUsuario);
    }

    @Override
    public GestionVacacion findByUserAndFechaCorte(Long codigoUsuario, Date fechaSalida) {
        return gestionVacacionDao.findByUserAndFechaCorte(codigoUsuario, fechaSalida);
    }
}
