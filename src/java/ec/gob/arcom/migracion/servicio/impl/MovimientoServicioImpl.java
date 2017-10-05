/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.MovimientoDao;
import ec.gob.arcom.migracion.modelo.Activo;
import ec.gob.arcom.migracion.modelo.Movimiento;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.MovimientoServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "MovimientoServicio")
public class MovimientoServicioImpl extends GenericServiceImpl<Movimiento, Long> implements MovimientoServicio {
    @EJB
    private MovimientoDao movimientoDao;
    
    @Override
    public GenericDao<Movimiento, Long> getDao() {
        return movimientoDao;
    }

    @Override
    public List<Movimiento> listaMovimientos(Activo activo) {
        return movimientoDao.listaMovimientos(activo);
    }
    
    @Override
    public void registrar(Movimiento movimiento) {
        Long id = movimientoDao.getMaxId() + 1L;
        movimiento.setId(id);
        this.create(movimiento);
    }
    
    @Override
    public void actualizar(Movimiento movimiento) {
        movimientoDao.actualizar(movimiento);
    }

}
