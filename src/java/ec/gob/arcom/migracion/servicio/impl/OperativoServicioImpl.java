/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.OperativoDao;
import ec.gob.arcom.migracion.modelo.Operativo;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.servicio.OperativoServicio;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "OperativoServicio")
public class OperativoServicioImpl extends GenericServiceImpl<Operativo, Long> implements OperativoServicio {
    @EJB
    private OperativoDao operativoDao;
    
    @Override
    public GenericDao<Operativo, Long> getDao() {
        return operativoDao;
    }

    @Override
    public List<Operativo> listar() {
        return operativoDao.list();
    }
}
