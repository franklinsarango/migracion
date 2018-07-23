/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.modelo.Rol;
import java.util.ArrayList;
import ec.gob.arcom.migracion.dto.UsuarioDto;
import java.util.Date;
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
    public List<UsuarioDto> listarUsuariosInternoExterno(String nombre, String numeroDocumento, int codigoDepartamento) {
        try {          
            if (numeroDocumento.equals("")){
                numeroDocumento = "-1";
            }
            if (codigoDepartamento == 0){
                codigoDepartamento = -1;
            }
            int estado_contrato = 573;
            Query query= em.createNativeQuery(""
                + "select  u.codigo_usuario, u.numero_documento, upper(u.nombre || ' ' || u.apellido) as funcionario, d.nombre as unidad_administrativa, c.fecha_ingreso, cat.nombre as estado, d.codigo_departamento\n" +
                "from catmin.usuario u, catmin.usuario_rol ur, catmin.rol r, arcom.contrato c, arcom.departamento d, catmin.catalogo_detalle cat\n" +
                "where u.codigo_usuario = ur.codigo_usuario \n" +
                "and c.codigo_usuario = u.codigo_usuario\n" +
                "and r.codigo_rol = ur.codigo_rol \n" +
                "and ur.estado_registro = true \n" +
                "and c.estado_registro = true\n" +
                "and c.estado_contrato = "+estado_contrato+"\n" +                    
                "and d.codigo_departamento = c.codigo_departamento\n" +
                "and cat.codigo_catalogo_detalle = c.estado_contrato\n" +
                "and (-1 = "+numeroDocumento+" or u.numero_documento = '"+numeroDocumento+"') \n" +
                "and (-1 = "+codigoDepartamento+" or d.codigo_departamento = "+codigoDepartamento+") \n" +
                "and ('' = '"+nombre+"' or upper(concat(u.nombre,' ',u.apellido)) like '%"+nombre.toUpperCase()+"%')\n" +
                "and r.nemonico not in ('ABGSRMN', 'ADMIN', 'GADADMI', 'ADMINGPS', 'AGEADU', 'GADAUDI', 'JEFETRANS', 'PROGPS', 'GADRECE', 'RCSBGN', 'GADRESP', 'SNACON', 'SNDESA', 'SUBSECREGION', 'USUARIO')\n" +
                "and u.estado_registro = true order by u.nombre asc;");
                List<Object[]> result = query.getResultList();
                List<UsuarioDto> usuarios = new ArrayList<>();
                for(Object[] fila : result ) {
                    UsuarioDto u= new UsuarioDto();
                    u.setCodigoUsuario((Long)fila[0]);                    
                    u.setIdentificacion(fila[1] != null ? fila[1].toString() : "");
                    u.setNombreFuncionario(fila[2] != null ? fila[2].toString() : "");
                    u.setUnidadAdministrativa(fila[3] != null ? fila[3].toString() : "");
                    u.setFechaIngreso((Date)fila[4]);
                    u.setEstadoContrato(fila[5] != null ? fila[5].toString() : "");
                    u.setCodigoDepartamento((Long) fila[6]);
                    usuarios.add(u);
                }
                return usuarios;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuariosInternos(Long codigoRegional) {
        try {
            Query query= em.createQuery("select u from Usuario u, UsuarioRol ur, Rol r, LocalidadRegional lr, Contrato c where u.codigoUsuario = ur.usuario.codigoUsuario "
                    + "and r.codigoRol = ur.rol.codigoRol "
                    + "and ur.estadoRegistro = true "
                    + "and r.nemonico not in ('UEXT', 'ABGSRMN', 'ADMIN', 'GADADMI', 'ADMINGPS', 'AGEADU', 'GADAUDI', 'JEFETRANS', 'PROGPS', 'GADRECE', 'RCSBGN', 'GADRESP', 'SNACON', 'SNDESA', 'SUBSECREGION', 'USUARIO') "
                    + "and u.estadoRegistro = true "
                    + "and u.codigoProvincia = lr.localidad.codigoLocalidad "
                    + "and lr.regional.codigoRegional = :codigoRegional "
                    + "and c.usuario.codigoUsuario = u.codigoUsuario and c.estadoRegistro = true "
                    + "order by u.nombre ASC");
            query.setParameter("codigoRegional", codigoRegional);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
