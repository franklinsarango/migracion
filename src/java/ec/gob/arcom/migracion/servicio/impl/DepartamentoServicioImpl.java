/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.DepartamentoDao;
import ec.gob.arcom.migracion.modelo.Departamento;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.servicio.DepartamentoServicio;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "DepartamentoServicio")
public class DepartamentoServicioImpl extends GenericServiceImpl<Departamento, Long> implements DepartamentoServicio {
    @EJB
    private DepartamentoDao departamentoDao;
    
    @Override
    public GenericDao<Departamento, Long> getDao() {
        return departamentoDao;
    }

    @Override
    public List<Departamento> listar() {
        return departamentoDao.listar();
    }
}
