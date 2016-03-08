/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "UsuarioDao")
public class UsuarioDaoEjb extends GenericDaoEjbEl<Usuario, Long> implements
        UsuarioDao {

    public UsuarioDaoEjb() {
        super(Usuario.class);
    }

    @Override
    public Usuario obtenerPorLogin(String login) {
        try {
            StringBuilder hql = new StringBuilder(100);
            hql.append("select u from Usuario u where ");
            hql.append("u.login = :login ");

            Query query = em.createQuery(hql.toString());
            query.setParameter("login", login);

            Usuario usuario = (Usuario) query.getSingleResult();
            this.refresh(usuario);
            return usuario;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

}
