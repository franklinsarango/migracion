/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.UsuarioDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "UsuarioServicio")
public class UsuarioServicioImpl extends GenericServiceImpl<Usuario, Long>
        implements UsuarioServicio {

    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private UsuarioServicio usuarioServicio;

    @Override
    public GenericDao<Usuario, Long> getDao() {
        return usuarioDao;
    }
    
    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.actualizarUsuario(usuario);
    }
    
    @Override
    public List<Usuario> findByTipoUsuarioCampoReservado3(String tipoUsuario) {
        return usuarioDao.findByTipoUsuarioCampoReservado3(tipoUsuario);
    }

    @Override
    public Usuario findByDocumento(String documento) {
        return usuarioDao.findByDocumento(documento);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    @Override
    public List<UsuarioDto> listarUsuariosInternoExterno(String nombre, String numeroDocumento, int codigoDepartamento) {
        return usuarioDao.listarUsuariosInternoExterno(nombre, numeroDocumento, codigoDepartamento);
    }

    @Override
    public List<Usuario> listarUsuariosInternos(Long codigoRegional) {
        return usuarioDao.listarUsuariosInternos(codigoRegional);
    }
}
