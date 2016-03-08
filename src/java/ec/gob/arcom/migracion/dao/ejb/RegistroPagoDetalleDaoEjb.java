/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.RegistroPagoDetalleDao;
import ec.gob.arcom.migracion.modelo.RegistroPagoDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegistroPagoDetalleDao")
public class RegistroPagoDetalleDaoEjb extends GenericDaoEjbEl<RegistroPagoDetalle, Long> implements
        RegistroPagoDetalleDao {

    public RegistroPagoDetalleDaoEjb() {
        super(RegistroPagoDetalle.class);
    }

    @Override
    public void actualizarRegistroPagoDetalle(RegistroPagoDetalle registroPagoDetalle) {
        String sql = "UPDATE catmin.registro_pago_detalle\n"
                + "SET "
                + "codigo_registro_pago=" + registroPagoDetalle.getCodigoRegistroPago().getCodigoRegistro() + ", "
                + "codigo_concesion=" + registroPagoDetalle.getCodigoConcesion().getCodigoConcesion() + ", "
                + "codigo_area=" + registroPagoDetalle.getCodigoArea().getCodigoAreaMinera() + ", "
                + "codigo_licencia_comercializacion=" + registroPagoDetalle.getCodigoLicenciaComercializacion().getCodigoLicenciaComercializacion() + ", "
                + "codigo_planta_beneficio=" + registroPagoDetalle.getCodigoPlantaBeneficio() + ", "
                + "valor_pagado=" + registroPagoDetalle.getValorPagado() + ", "
                + "numero_tramite=" + registroPagoDetalle.getNumeroTramite() + ", ";
        if (registroPagoDetalle.getEntidadTramite() != null) {
            sql += "entidad_tramite='" + registroPagoDetalle.getEntidadTramite() + "', ";
        } else {
            sql += "entidad_tramite=" + null + ", ";
        }
        if (registroPagoDetalle.getCampoReservado05() != null) {
            sql += "campo_reservado_05='" + registroPagoDetalle.getCampoReservado05() + "', ";
        } else {
            sql += "campo_reservado_05=" + null + ", ";
        }
        if (registroPagoDetalle.getCampoReservado04() != null) {
            sql += "campo_reservado_04='" + registroPagoDetalle.getCampoReservado04() + "', ";
        } else {
            sql += "campo_reservado_04=" + null + ", ";
        }
        if (registroPagoDetalle.getCampoReservado03() != null) {
            sql += "campo_reservado_03='" + registroPagoDetalle.getCampoReservado02() + "', ";
        } else {
            sql += "campo_reservado_03=" + null + ", ";
        }
        if (registroPagoDetalle.getCampoReservado02() != null) {
            sql += "campo_reservado_02='" + registroPagoDetalle.getCampoReservado02() + "', ";
        } else {
            sql += "campo_reservado_02=" + null + ", ";
        }
        if (registroPagoDetalle.getCampoReservado01() != null) {
            sql += "campo_reservado_01='" + registroPagoDetalle.getCampoReservado01() + "', ";
        } else {
            sql += "campo_reservado_01=" + null + ", ";
        }
        if (registroPagoDetalle.getEstadoRegistro() != null) {
            sql += "estado_registro=" + registroPagoDetalle.getEstadoRegistro() + ", ";
        }
        if (registroPagoDetalle.getFechaCreacion() != null) {
            sql += "fecha_creacion='" + registroPagoDetalle.getFechaCreacion() + "', ";
        } else {
            sql += "fecha_creacion=" + null + ", ";
        }
        sql += "usuario_creacion=" + registroPagoDetalle.getUsuarioCreacion() + ", ";
        if (registroPagoDetalle.getFechaModificacion() != null) {
            sql += "fecha_modificacion='" + registroPagoDetalle.getFechaModificacion() + "', ";
        } else {
            sql += "fecha_modificacion=" + null + ", ";
        }
        sql += "usuario_modificacion=" + registroPagoDetalle.getUsuarioModificacion() + "\n";
        sql += "WHERE codigo_registro_pago_detalle=" + registroPagoDetalle.getCodigoRegistroPagoDetalle();
    }

    @Override
    public List<RegistroPagoDetalle> obtenerPorCodigoRegistroPago(Long codigoRegistroPagoDetalle) {
        String jpql = "select rpd from RegistroPagoDetalle rpd where rpd.codigoRegistroPago.codigoRegistro = :codigoRegistroPagoDetalle";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoRegistroPagoDetalle", codigoRegistroPagoDetalle);
        List<RegistroPagoDetalle> pagos = query.getResultList();
        return pagos;
    }
}
