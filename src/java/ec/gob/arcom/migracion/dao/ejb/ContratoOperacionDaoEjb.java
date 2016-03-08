/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ContratoOperacionDao;
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
        sql += "codigo_provincia=" + contratoOperacion.getCodigoProvincia().getCodigoLocalidad() + ", ";
        sql += "codigo_canton=" + contratoOperacion.getCodigoCanton().getCodigoLocalidad() + ", ";
        sql += "codigo_parroquia=" + contratoOperacion.getCodigoParroquia().getCodigoLocalidad() + ", ";
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
        if (contratoOperacion.getCodigoArcom() != null) {
            sql += "codigo_arcom='" + contratoOperacion.getCodigoArcom() + "' \n";
        } else {
            sql += "codigo_arcom=" + null + " \n";
        }
        sql += "WHERE codigo_contrato_operacion=" + contratoOperacion.getCodigoContratoOperacion();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<ContratoOperacion> obtenerContratosOperacion(String codigoArcom, String numDocumento) {
        String jpql = "select co from ContratoOperacion co where 1=1 \n";
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            jpql += "and co.codigoConcesion.codigoArcom = :codigoArcom \n";
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            jpql += "and co.numeroDocumento = :numDocumento";
        }
        System.out.println("jpql: " + jpql);
        Query query = em.createQuery(jpql);
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            query.setParameter("codigoArcom", codigoArcom);
        }
        if (numDocumento != null && !numDocumento.isEmpty()) {
            query.setParameter("numDocumento", numDocumento);
        }
        return query.getResultList();
    }

}
