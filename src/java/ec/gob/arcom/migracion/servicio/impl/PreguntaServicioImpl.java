/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.PreguntaDao;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.Pregunta;
import ec.gob.arcom.migracion.servicio.PreguntaServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PreguntaServicio")
public class PreguntaServicioImpl extends GenericServiceImpl<Pregunta, Long>
        implements PreguntaServicio {

    @EJB
    private PreguntaDao preguntaDao;
    @EJB
    private PreguntaServicio preguntaServicio;

    @Override
    public GenericDao<Pregunta, Long> getDao() {
        return preguntaDao;
    }

    @Override
    public Pregunta findByCodigoPregunta(Long codigoPregunta) {
        return preguntaDao.findByCodigoPregunta(codigoPregunta);
    }
    
    @Override
    public Pregunta findByNemonico(String nemonico) {
        return preguntaDao.findByNemonico(nemonico);
    }
}
