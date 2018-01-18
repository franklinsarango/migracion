/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.DetalleLicenciaDao;
import ec.gob.arcom.migracion.modelo.DetalleLicencia;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.servicio.DetalleLicenciaServicio;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "DetalleLicenciaServicio")
public class DetalleLicenciaServicioImpl extends GenericServiceImpl<DetalleLicencia, Long> implements DetalleLicenciaServicio {
    @EJB
    private DetalleLicenciaDao detalleLicenciaDao;
    
    @Override
    public GenericDao<DetalleLicencia, Long> getDao() {
        return detalleLicenciaDao;
    }

    @Override
    public List<DetalleLicencia> listarDetallesLicencia(Long codigoLicencia) {
        return detalleLicenciaDao.listarDetallesLicencia(codigoLicencia);
    }
}
