/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ContratoOperacionDao;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ContratoOperacionDao")
public class ContratoOperacionDaoEjb extends GenericDaoEjbEl<ContratoOperacion, Long> implements
        ContratoOperacionDao {

    public ContratoOperacionDaoEjb() {
        super(ContratoOperacion.class);
    }

    @Override
    public ContratoOperacion findByPk(Long codigoContratoOperacion) {
        try {
            String jpql = "select co from ContratoOperacion co where co.codigoContratoOperacion = :codigoContratoOperacion";
            Query query = em.createQuery(jpql);
            query.setParameter("codigoContratoOperacion", codigoContratoOperacion);
            ContratoOperacion co = (ContratoOperacion) query.getSingleResult();
            this.refresh(co);
            return co;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarContratoOperacion(ContratoOperacion contratoOperacion) {
        String sql = "UPDATE catmin.contrato_operacion\n"
                + "SET codigo_concesion=" + contratoOperacion.getCodigoConcesion().getCodigoConcesion() + ", ";
        System.out.println("Query update contrato: " + sql);
        if (contratoOperacion.getCodigoArea() != null) {
            sql += "codigo_area=" + contratoOperacion.getCodigoArea().getCodigoAreaMinera() + ", ";
        } else {
            sql += "codigo_area=" + null + ", ";
        }
        if (contratoOperacion.getCodigoInforme() != null) {
            sql += "codigo_informe=" + contratoOperacion.getCodigoInforme().getCodigoInforme() + ", ";
        } else {
            sql += "codigo_informe=" + null + ", ";
        }
        if (contratoOperacion.getCodigoProvincia() != null) {
            sql += "codigo_provincia=" + contratoOperacion.getCodigoProvincia().getCodigoLocalidad() + ", ";
        } else {
            sql += "codigo_provincia=" + null + ", ";
        }
        if (contratoOperacion.getCodigoCanton() != null) {
            sql += "codigo_canton=" + contratoOperacion.getCodigoCanton().getCodigoLocalidad() + ", ";
        } else {
            sql += "codigo_canton=" + null + ", ";
        }
        if (contratoOperacion.getCodigoParroquia() != null) {
            sql += "codigo_parroquia=" + contratoOperacion.getCodigoParroquia().getCodigoLocalidad() + ", ";
        } else {
            sql += "codigo_parroquia=" + null + ", ";
        }
        if (contratoOperacion.getSector() != null) {
            sql += "sector='" + contratoOperacion.getSector() + "', ";
        } else {
            sql += "sector=" + null + ", ";
        }
        if (contratoOperacion.getNumeroDocumento() != null) {
            sql += "numero_documento='" + contratoOperacion.getNumeroDocumento() + "', ";
        } else {
            sql += "numero_documento=" + null + ", ";
        }
        sql += "estado_contrato=" + contratoOperacion.getEstadoContrato().getCodigoCatalogoDetalle() + ", ";
        if (contratoOperacion.getCampoReservado05() != null) {
            sql += "campo_reservado_05='" + contratoOperacion.getCampoReservado05() + "', ";
        } else {
            sql += "campo_reservado_05=" + null + ", ";
        }
        if (contratoOperacion.getCampoReservado04() != null) {
            sql += "campo_reservado_04='" + contratoOperacion.getCampoReservado04() + "', ";
        } else {
            sql += "campo_reservado_04=" + null + ", ";
        }
        if (contratoOperacion.getCampoReservado03() != null) {
            sql += "campo_reservado_03='" + contratoOperacion.getCampoReservado03() + "', ";
        } else {
            sql += "campo_reservado_03=" + null + ", ";
        }
        if (contratoOperacion.getCampoReservado02() != null) {
            sql += "campo_reservado_02='" + contratoOperacion.getCampoReservado02() + "', ";
        } else {
            sql += "campo_reservado_02=" + null + ", ";
        }
        if (contratoOperacion.getCampoReservado01() != null) {
            sql += "campo_reservado_01='" + contratoOperacion.getCampoReservado01() + "', ";
        } else {
            sql += "campo_reservado_01=" + null + ", ";
        }
        sql += "estado_registro=" + contratoOperacion.getEstadoRegistro() + ", ";
        if (contratoOperacion.getFechaCreacion() != null) {
            sql += "fecha_creacion='" + contratoOperacion.getFechaCreacion() + "', ";
        } else {
            sql += "fecha_creacion=" + null + ", ";
        }
        if (contratoOperacion.getUsuarioCreacion() != null) {
            sql += "usuario_creacion=" + contratoOperacion.getUsuarioCreacion() + ", ";
        } else {
            sql += "usuario_creacion=" + null + ", ";
        }
        if (contratoOperacion.getFechaModificacion() != null) {
            sql += "fecha_modificacion='" + contratoOperacion.getFechaModificacion() + "', ";
        } else {
            sql += "fecha_modificacion=" + null + ", ";
        }
        if (contratoOperacion.getUsuarioModificacion() != null) {
            sql += "usuario_modificacion=" + contratoOperacion.getUsuarioModificacion() + ", \n";
        } else {
            sql += "usuario_modificacion=" + null + ", \n";
        }
        if (contratoOperacion.getTipoContrato() != null) {
            sql += "tipo_contrato=" + contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "tipo_contrato=" + null + ", \n";
        }
        sql += "porcentaje=" + contratoOperacion.getPorcentaje() + ", \n";
        if (contratoOperacion.getFechaInscribe() != null) {
            sql += "fecha_inscribe='" + contratoOperacion.getFechaInscribe() + "', \n";
        } else {
            sql += "fecha_inscribe=" + null + ", \n";
        }
        if (contratoOperacion.getCotaMinima() != null) {
            sql += "cota_minima='" + contratoOperacion.getCotaMinima() + "', \n";
        } else {
            sql += "cota_minima=" + null + ", \n";
        }
        if (contratoOperacion.getCotaMaxima() != null) {
            sql += "cota_maxima='" + contratoOperacion.getCotaMaxima() + "', \n";
        } else {
            sql += "cota_maxima=" + null + ", \n";
        }
        if (contratoOperacion.getProcuradorComun()!= null) {
            sql += "procurador_comun=" + contratoOperacion.getProcuradorComun() + ", \n";
        } else {
            sql += "procurador_comun=" + null + ", \n";
        }
        if (contratoOperacion.getTipoProcurador()!= null) {
            sql += "tipo_procurador=" + contratoOperacion.getTipoProcurador().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "tipo_procurador=" + null + ", \n";
        }
        if (contratoOperacion.getPlazo()!= null) {
            sql += "plazo=" + contratoOperacion.getPlazo() + ", \n";
        } else {
            sql += "plazo=" + null + ", \n";
        }
        if (contratoOperacion.getObservacionGeneral()!= null) {
            sql += "observacion_general='" + contratoOperacion.getObservacionGeneral() + "', \n";
        } else {
            sql += "observacion_general=" + null + ", \n";
        }
        if (contratoOperacion.getCodigoArcom() != null) {
            sql += "codigo_arcom='" + contratoOperacion.getCodigoArcom() + "' \n";
        } else {
            sql += "codigo_arcom=" + null + " \n";
        }
        sql += "WHERE codigo_contrato_operacion=" + contratoOperacion.getCodigoContratoOperacion();

        System.out.println("Query update contrato: " + sql);
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<ContratoOperacion> obtenerContratosOperacion(String codigoArcom, String numDocumento, String loginDocumento) {
        String jpql = "select co from ContratoOperacion co where 1=1 and co.codigoArcom like :codigoArcomCO \n";
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            jpql += "and co.codigoArcom like :codigoArcom \n";
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            jpql += "and co.numeroDocumento = :numDocumento \n";
        }

        jpql += "order by co.fechaCreacion, co.codigoArcom desc ";

        System.out.println("jpql: " + jpql);
        Query query = em.createQuery(jpql);
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            query.setParameter("codigoArcom", codigoArcom + "%");
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            query.setParameter("numDocumento", numDocumento);
        }
        query.setParameter("codigoArcomCO", "%" + "CO" + "%");
        try {
            List<ContratoOperacion> listaFinal = query.getResultList();
            for (ContratoOperacion cop : listaFinal) {
                this.refresh(cop);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        //return query.getResultList();
        return null;
    }

    @Override
    public int countByContratoOperacionTablaTotal(String cedulaRuc, String codigoArcom, String numDocumento, boolean registrosPorRegional) {
        String jpql = "select co from ContratoOperacion co, ConcesionMinera cm where co.codigoConcesion.codigoConcesion = cm.codigoConcesion \n"
                + " and 1=1 and co.codigoArcom like :codigoArcomCO \n";
        if (registrosPorRegional == true) {
            jpql += "and cm.codigoProvincia in (select lcr.localidad.codigoLocalidad from LocalidadRegional lcr where lcr.regional.codigoRegional = \n"
                    + " (select r.codigoRegional from Regional r, LocalidadRegional lr, Usuario u where u.numeroDocumento = '" + cedulaRuc + "'\n"
                    + " and r.codigoRegional = lr.regional.codigoRegional and lr.localidad.codigoLocalidad = u.codigoProvincia)) \n";
        }
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            jpql += "and co.codigoArcom like :codigoArcom \n";
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            jpql += "and co.numeroDocumento = :numDocumento \n";
        }

        jpql += "order by co.fechaCreacion, co.codigoArcom desc ";

        System.out.println("jpql: " + jpql);
        Query query = em.createQuery(jpql);
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            query.setParameter("codigoArcom", codigoArcom + "%");
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            query.setParameter("numDocumento", numDocumento);
        }
        query.setParameter("codigoArcomCO", "%" + "CO" + "%");

        int count =  query.getResultList().size();
        return count;

    }
    
    @Override
    public List<ContratoOperacion> countByContratoOperacionTabla(String cedulaRuc, String codigoArcom, String numDocumento, boolean registrosPorRegional, int paramLimit, int paramOffset) {
        String jpql = "select co from ContratoOperacion co, ConcesionMinera cm where co.codigoConcesion.codigoConcesion = cm.codigoConcesion \n"
                + " and 1=1 and co.codigoArcom like :codigoArcomCO \n";
        if (registrosPorRegional == true) {
            jpql += "and cm.codigoProvincia in (select lcr.localidad.codigoLocalidad from LocalidadRegional lcr where lcr.regional.codigoRegional = \n"
                    + " (select r.codigoRegional from Regional r, LocalidadRegional lr, Usuario u where u.numeroDocumento = '" + cedulaRuc + "'\n"
                    + " and r.codigoRegional = lr.regional.codigoRegional and lr.localidad.codigoLocalidad = u.codigoProvincia)) \n";
        }
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            jpql += "and co.codigoArcom like :codigoArcom \n";
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            jpql += "and co.numeroDocumento = :numDocumento \n";
        }

        jpql += "order by co.fechaCreacion, co.codigoArcom desc ";

        System.out.println("jpql: " + jpql);
        Query query = em.createQuery(jpql);
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            query.setParameter("codigoArcom", codigoArcom + "%");
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            query.setParameter("numDocumento", numDocumento);
        }
        query.setParameter("codigoArcomCO", "%" + "CO" + "%");
        query.setFirstResult(paramOffset).setMaxResults(paramLimit);
        try {
            List<ContratoOperacion> listaFinal = query.getResultList();
            for (ContratoOperacion cop : listaFinal) {
                this.refresh(cop);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        //return query.getResultList();
        return null;
    }
    
    @Override
    public List<ContratoOperacion> obtenerCotitulares(ConcesionMinera concesionMinera) {
        String jpql = "select co from ContratoOperacion co where 1=1 and co.codigoConcesion = :concesionMinera and co.codigoArcom like :codigoArcom\n";
        jpql += "order by co.codigoArcom desc ";

        System.out.println("jpql: " + jpql);
        Query query = em.createQuery(jpql);

        query.setParameter("concesionMinera", concesionMinera);
        query.setParameter("codigoArcom", "%" + "CD" + "%");

        try {
            List<ContratoOperacion> listaFinal = query.getResultList();
            for (ContratoOperacion cop : listaFinal) {
                this.refresh(cop);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        //return query.getResultList();
        return null;
    }
}
