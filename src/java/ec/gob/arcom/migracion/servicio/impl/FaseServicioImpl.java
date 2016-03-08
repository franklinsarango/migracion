/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.FaseDao;
import ec.gob.arcom.migracion.modelo.Fase;
import ec.gob.arcom.migracion.servicio.FaseServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "FaseServicio")
public class FaseServicioImpl extends GenericServiceImpl<Fase, Long>
        implements FaseServicio {
    
    @EJB
    private FaseDao faseDao;
    
    @Override
    public GenericDao<Fase, Long> getDao() {
        return faseDao;
    }
    
    @Override
    public Fase findByNemonico(String nemonico) {
        return faseDao.findByNemonico(nemonico);
    }
    
    @Override
    public Fase findByNombre(String nombre) {
        return faseDao.findByNombre(nombre);
    }
    
    @Override
    public List<Fase> obtenerFasesLikeDescripcion(String descripcion) {
        return faseDao.obtenerFaseLikeDescripcion(descripcion);
    }
    
}
