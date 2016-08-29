/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.OpcionDao;
import ec.gob.arcom.migracion.modelo.Opcion;
import ec.gob.arcom.migracion.modelo.Pregunta;
import ec.gob.arcom.migracion.servicio.OpcionServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "OpcionServicio")
public class OpcionServicioImpl extends GenericServiceImpl<Opcion, Long>
        implements OpcionServicio {

    @EJB
    private OpcionDao opcionDao;
    @EJB
    private OpcionServicio opcionServicio;

    @Override
    public GenericDao<Opcion, Long> getDao() {
        return opcionDao;
    }

    @Override
    public List<Opcion> findByCodigoPregunta(Pregunta pregunta) {
        return opcionDao.findByCodigoPregunta(pregunta);
    }
}
