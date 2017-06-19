/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.FichaTecnicaDao;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.servicio.FichaTecnicaServicio;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "FichaTecnicaServicio")
public class FichaTecnicaServicioImpl extends GenericServiceImpl<FichaTecnica, Long> implements FichaTecnicaServicio {
    @EJB
    private FichaTecnicaDao fichaTecnicaDao;
    
    @Override
    public GenericDao<FichaTecnica, Long> getDao() {
        return fichaTecnicaDao;
    }

    @Override
    public List<FichaTecnica> listar() {
        return fichaTecnicaDao.listar();
    }

    @Override
    public List<FichaTecnica> listarPorUsuarioCreacion(Long codigoUsuario) {
        return fichaTecnicaDao.listarPorUsuarioCreacion(codigoUsuario);
    }
}
