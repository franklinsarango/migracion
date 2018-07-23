/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.dto.UsuarioDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface UsuarioDao extends GenericDao<Usuario, Long> {
    
    Usuario obtenerPorLogin(String login);
    
    void actualizarUsuario(Usuario usuario);
    
    List<Usuario> findByTipoUsuarioCampoReservado3(String tipoUsuario);

    public Usuario findByDocumento(String documento);

    public List<Usuario> listar();

    public List<UsuarioDto> listarUsuariosInternoExterno(String nombre,String numeroDocumento, int codigoDepartamento);

    public List<Usuario> listarUsuariosInternos(Long codigoRegional);
}
