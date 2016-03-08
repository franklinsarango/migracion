/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface UsuarioDao extends GenericDao<Usuario, Long> {
    
    Usuario obtenerPorLogin(String login);
    
}
