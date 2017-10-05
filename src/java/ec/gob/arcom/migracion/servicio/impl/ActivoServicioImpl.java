/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ActivoDao;
import ec.gob.arcom.migracion.modelo.Activo;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.ActivoServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "ActivoServicio")
public class ActivoServicioImpl extends GenericServiceImpl<Activo, Long> implements ActivoServicio {
    @EJB
    private ActivoDao activoDao;
    
    @Override
    public GenericDao<Activo, Long> getDao() {
        return activoDao;
    }

    @Override
    public int listaActivosTotal(Long filtroId, String filtroDescripcion, String filtroTagArcom) {
        return activoDao.listaActivosTotal(filtroId, filtroDescripcion, filtroTagArcom);
    }
    
    @Override
    public List<Activo> listaActivos(int tamanoPagina, int desplazamiento, Long filtroId, String filtroDescripcion, String filtroTagArcom) {
        return activoDao.listaActivos(tamanoPagina, desplazamiento, filtroId, filtroDescripcion, filtroTagArcom);
    }

    @Override
    public List<Activo> findByTagArcom(String tagArcom) {
        return activoDao.findByTagArcom(tagArcom);
    }
    
    @Override
    public void registrar(Activo activo) {
        Long id = activoDao.getMaxId() + 1L;
        activo.setId(id);
        this.create(activo);
    }
    
    @Override
    public void actualizar(Activo activo) {
        activoDao.actualizar(activo);
    }
}
