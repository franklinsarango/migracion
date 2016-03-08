/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.UsuarioRolDao;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.modelo.UsuarioRolPK;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "UsuarioRolServicio")
public class UsuarioRolServicioImpl extends GenericServiceImpl<UsuarioRol, UsuarioRolPK>
        implements UsuarioRolServicio {
    
    @EJB
    private UsuarioRolDao usuarioRolDao;

    @Override
    public GenericDao<UsuarioRol, UsuarioRolPK> getDao() {
        return usuarioRolDao;
    }

    @Override
    public UsuarioRol obtenerPorCodigoUsuuario(Long codigoUsuario) {
        return usuarioRolDao.obtenerPorCodigoUsuario(codigoUsuario);
    }

}
