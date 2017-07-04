/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.FichaTecnicaDao;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class FichaTecnicaDaoEjb extends GenericDaoEjbEl<FichaTecnica, Long> implements FichaTecnicaDao {
    public FichaTecnicaDaoEjb() {
        super(FichaTecnica.class);
    }

    @Override
    public List<FichaTecnica> listar() {
        try {
            Query query= em.createQuery("Select ficha from FichaTecnica ficha where ficha.estadoRegistro= :estado order by ficha.fechaCreacion ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<FichaTecnica> listarPorUsuarioCreacion(Long codigoUsuario) {
        try {
            Query query= em.createQuery("Select ficha from FichaTecnica ficha where ficha.estadoRegistro= :estado and ficha.usuarioCreacion.codigoUsuario= :codigo order by ficha.fechaCreacion ASC");
            query.setParameter("estado", true);
            query.setParameter("codigo", codigoUsuario);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Usuario> listarPorUsuariosDistintos() {
        try {
            //Query query= em.createNativeQuery("select catmin.llenar_tabla_concesion_pago_sri('" + anio + "')");
            //Query query= em.createNativeQuery("select distinct usuario_creacion from actmin.ficha_tecnica order by usuario_creacion ASC;");

            Query query= em.createQuery("Select distinct ficha.usuarioCreacion from FichaTecnica ficha where ficha.estadoRegistro= :estado order by ficha.usuarioCreacion");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public Long contarPorRegional(Regional r) {
        try {
            Query query= em.createQuery("Select count(ficha) from FichaTecnica ficha where ficha.estadoRegistro= :activo and ficha.regional.codigoRegional= :codigo");
            query.setParameter("activo", true);
            query.setParameter("codigo", r.getCodigoRegional());
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las fichas por regional: " + ex.toString());
        }
        return null;
    }

    @Override
    public Long contarPorUsuarioCreacion(Usuario u) {
        try {
            Query query= em.createQuery("Select count(ficha) from FichaTecnica ficha where ficha.estadoRegistro= :activo and ficha.usuarioCreacion.codigoUsuario= :codigo");
            query.setParameter("activo", true);
            query.setParameter("codigo", u.getCodigoUsuario());
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las fichas por usuarioCreacion: " + ex.toString());
        }
        return null;
    }

    @Override
    public Long contarPorProvincia(Localidad p) {
        try {
            Query query= em.createQuery("Select count(ficha) from FichaTecnica ficha where ficha.estadoRegistro= :activo and ficha.provincia.codigoLocalidad= :codigo");
            query.setParameter("activo", true);
            query.setParameter("codigo", p.getCodigoLocalidad());
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las fichas por provincia: " + ex.toString());
        }
        return null;
    }

    @Override
    public List<Localidad> listarProvinciasDistintas() {
        try {
            Query query= em.createQuery("Select distinct ficha.provincia from FichaTecnica ficha where ficha.estadoRegistro= :estado order by ficha.provincia.nombre");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Localidad> listarCantonesDistintosPorProvincia(Localidad l) {
        try {
            Query query= em.createQuery("Select distinct ficha.canton from FichaTecnica ficha where ficha.estadoRegistro= :estado and ficha.provincia.codigoLocalidad= :codigoProvincia order by ficha.canton.nombre");
            query.setParameter("estado", true);
            query.setParameter("codigoProvincia", l.getCodigoLocalidad());
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public Long contarPorCanton(Localidad c) {
        try {
            Query query= em.createQuery("Select count(ficha) from FichaTecnica ficha where ficha.estadoRegistro= :activo and ficha.canton.codigoLocalidad= :codigoCanton");
            query.setParameter("activo", true);
            query.setParameter("codigoCanton", c.getCodigoLocalidad());
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las fichas por canton: " + ex.toString());
        }
        return null;
    }
}
