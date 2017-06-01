/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.DetalleOperativoDao;
import ec.gob.arcom.migracion.modelo.DetalleOperativo;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.DetalleOperativoServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "DetalleOperativoServicio")
public class DetalleOperativoServicioImpl extends GenericServiceImpl<DetalleOperativo, Long> implements DetalleOperativoServicio {
    @EJB
    private DetalleOperativoDao detalleOperativoDao;
    
    @Override
    public GenericDao<DetalleOperativo, Long> getDao() {
        return detalleOperativoDao;
    }

    @Override
    public List<DetalleOperativo> listarPorOperativo(Operativo operativo) {
        return detalleOperativoDao.listarPorOperativo(operativo);
    }
}
