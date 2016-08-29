/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.EncuestaDao;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.servicio.EncuestaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "EncuestaServicio")
public class EncuestaServicioImpl extends GenericServiceImpl<Encuesta, Long>
        implements EncuestaServicio {

    @EJB
    private EncuestaDao encuestaDao;
    @EJB
    private EncuestaServicio encuestaServicio;

    @Override
    public GenericDao<Encuesta, Long> getDao() {
        return encuestaDao;
    }

    @Override
    public Encuesta obtenerPorCodigoEncuesta(Long codigoRegistroPagoObligaciones) {
        return encuestaDao.obtenerPorCodigoEncuesta(codigoRegistroPagoObligaciones);
    }
    
    @Override
    public Encuesta findByCodigoEncuesta(Long codigoEncuesta) {
        return encuestaDao.findByCodigoEncuesta(codigoEncuesta);
    }
}
