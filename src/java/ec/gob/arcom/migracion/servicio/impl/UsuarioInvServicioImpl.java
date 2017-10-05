/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.UsuarioInvDao;
import ec.gob.arcom.migracion.modelo.UsuarioInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.UsuarioInvServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "UsuarioInvServicio")
public class UsuarioInvServicioImpl extends GenericServiceImpl<UsuarioInv, Long> implements UsuarioInvServicio {
    @EJB
    private UsuarioInvDao adjuntoDao;
    
    @Override
    public GenericDao<UsuarioInv, Long> getDao() {
        return adjuntoDao;
    }

    @Override
    public List<UsuarioInv> findByOperativo(Operativo o) {
        return adjuntoDao.findByOperativo(o);
    }

    @Override
    public List<UsuarioInv> findByFichaTecnica(FichaTecnica ft) {
        return adjuntoDao.findByFichaTecnica(ft);
    }
}
