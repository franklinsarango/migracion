/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.SujetoMineroDao;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import ec.gob.arcom.migracion.servicio.SujetoMineroServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SujetoMineroServicio")
public class SujetoMineroServicioImpl extends GenericServiceImpl<SujetoMinero, Long>
        implements SujetoMineroServicio {
    
    @EJB
    private SujetoMineroDao sujetoMineroDao;

    @Override
    public GenericDao<SujetoMinero, Long> getDao() {
        return sujetoMineroDao;
    }

    @Override
    public SujetoMinero obtenerPorIdentificacion(String identificacion) {
        return sujetoMineroDao.obtenerPorIdentificacion(identificacion);
    }

    @Override
    public void actualizarSujetoMinero(SujetoMinero sujetoMinero) throws Exception {
        sujetoMineroDao.actualizarSujetoMinero(sujetoMinero);
    }

}
