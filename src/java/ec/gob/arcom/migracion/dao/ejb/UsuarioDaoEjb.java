/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.modelo.Rol;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE catmin.usuario SET \n";
        if (usuario.getCampoReservado02() != null) {
            sql = sql + " campo_reservado_02 = " + usuario.getCampoReservado02() + "\n";
        }
        sql = sql +"WHERE numero_documento = '" + usuario.getNumeroDocumento() + "'";

        System.out.println("JSQL:"+sql);
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
    
    @Override
    public List<Usuario> findByTipoUsuarioCampoReservado3(String tipoUsuario) {
        try {
            Query query = em.createQuery("Select u from Usuario u where u.campoReservado03 like :tipoUsuario and u.estadoRegistro = true");
            query.setParameter("tipoUsuario", tipoUsuario);
            List<Usuario> listaFinal = query.getResultList();
            for (Usuario u : listaFinal) {
                this.refresh(u);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public Usuario findByDocumento(String documento) {
        try {
            Query query= em.createQuery("Select u from Usuario u where u.estadoRegistro= :estado and u.numeroDocumento= :documento");
            query.setParameter("estado", true);
            query.setParameter("documento", documento);
            return (Usuario) query.getResultList().get(0);
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        try {
            Query query= em.createQuery("Select u from Usuario u where u.estadoRegistro= :estado order by u.nombre ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    /*@Override
    public List<Usuario> listarUsuariosInternos() {
        try {
            Query query= em.createNativeQuery("select u.* from catmin.usuario u, catmin.usuario_rol ur, catmin.rol r where u.codigo_usuario = ur.codigo_usuario "
                    + "and r.codigo_rol = ur.codigo_rol "
                    + "and ur.estado_registro = true "
                    + "and r.nemonico not in ('UEXT', 'ABGSRMN', 'ADMIN', 'GADADMI', 'ADMINGPS', 'AGEADU', 'GADAUDI', 'JEFETRANS', 'PROGPS', 'GADRECE', 'RCSBGN', 'GADRESP', 'SNACON', 'SNDESA', 'SUBSECREGION', 'USUARIO') "
                    + "and u.estado_registro = true order by u.nombre ASC");
            List<Object[]> result= query.getResultList();
            List<Usuario> usuarios= new ArrayList<>();
            for(Object[] fila : result ) {
                Usuario u= new Usuario();
                u.setCodigoUsuario(fila[0] != null ? Long.valueOf(fila[0].toString()) : null);
                u.setTipoUsuario(fila[1] != null ? fila[1].toString() : null);
                u.setNumeroDocumento(fila[2] != null ? fila[2].toString() : null);
                u.setNombre(fila[3] != null ? fila[3].toString() : null);
                u.setApellido(fila[4] != null ? fila[4].toString() : null);
                usuarios.add(u);
            }
            return usuarios;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }*/
    
    @Override
    public List<Usuario> listarUsuariosInternos() {
        try {
            Query query= em.createQuery("select u from Usuario u, UsuarioRol ur, Rol r where u.codigoUsuario = ur.usuario.codigoUsuario "
                    + "and r.codigoRol = ur.rol.codigoRol "
                    + "and ur.estadoRegistro = true "
                    + "and r.nemonico not in ('UEXT', 'ABGSRMN', 'ADMIN', 'GADADMI', 'ADMINGPS', 'AGEADU', 'GADAUDI', 'JEFETRANS', 'PROGPS', 'GADRECE', 'RCSBGN', 'GADRESP', 'SNACON', 'SNDESA', 'SUBSECREGION', 'USUARIO') "
                    + "and u.estadoRegistro = true order by u.nombre ASC");
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
