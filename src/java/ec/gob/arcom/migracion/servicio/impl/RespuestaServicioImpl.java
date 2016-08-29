/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.RespuestaDao;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.Respuesta;
import ec.gob.arcom.migracion.servicio.RespuestaServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RespuestaServicio")
public class RespuestaServicioImpl extends GenericServiceImpl<Respuesta, Long>
        implements RespuestaServicio {

    @EJB
    private RespuestaDao encuestaPreguntaOpcionDao;
    @EJB
    private RespuestaServicio encuestaPreguntaOpcionServicio;

    @Override
    public GenericDao<Respuesta, Long> getDao() {
        return encuestaPreguntaOpcionDao;
    }

    @Override
    public List<Respuesta> findByEncuesta(Encuesta encuesta) {
        return encuestaPreguntaOpcionDao.findByEncuesta(encuesta);
    }
    
}
