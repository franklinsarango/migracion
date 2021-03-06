/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.EncuestaPreguntaDao;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.EncuestaPregunta;
import ec.gob.arcom.migracion.servicio.EncuestaPreguntaServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "EncuestaPreguntaServicio")
public class EncuestaPreguntaServicioImpl extends GenericServiceImpl<EncuestaPregunta, Long>
        implements EncuestaPreguntaServicio {

    @EJB
    private EncuestaPreguntaDao preguntaDao;
    @EJB
    private EncuestaPreguntaServicio preguntaServicio;

    @Override
    public GenericDao<EncuestaPregunta, Long> getDao() {
        return preguntaDao;
    }

    @Override
    public List<EncuestaPregunta> findByCodigoEncuesta(Encuesta encuesta) {
        return preguntaDao.findByCodigoEncuesta(encuesta);
    }
}
