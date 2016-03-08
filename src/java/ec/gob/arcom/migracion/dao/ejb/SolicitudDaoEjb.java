/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.SolicitudDao;
import ec.gob.arcom.migracion.modelo.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SolicitudDao")
public class SolicitudDaoEjb extends GenericDaoEjbEl<Solicitud, Long> implements
        SolicitudDao {

    public SolicitudDaoEjb() {
        super(Solicitud.class);
    }

    @Override
    public Solicitud obtenerPorSecuenciaSolicitud(Long secuenciaSolicitud) {
        try {
            String hql = "select s from Solicitud s where s.secuenciaSolicitud = :secuenciaSolicitud";
            Query query = em.createQuery(hql);
            query.setParameter("secuenciaSolicitud", secuenciaSolicitud);
            Solicitud solicitud = (Solicitud) query.getSingleResult();
            this.refresh(solicitud);
            return solicitud;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public Solicitud obtenerPorCodigoArcom(String codigoArcom) {
        try {
            String hql = "select s from Solicitud s where s.campoReservado10 = :codigoArcom";
            Query query = em.createQuery(hql);
            query.setParameter("codigoArcom", codigoArcom);
            Solicitud s = (Solicitud) query.getSingleResult();
            this.refresh(s);
            return s;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarSolicitud(Solicitud solicitud) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.solicitud\n"
                + "SET\n";
        if (solicitud.getCodigoSolicitud() != null) {
            sql = sql + "    codigo_solicitud = " + solicitud.getCodigoSolicitud() + ",\n";
        }
        sql = sql + "    secuencia_solicitud = " + solicitud.getSecuenciaSolicitud() + ",\n";
        sql = sql + "    codigo_informe = " + solicitud.getCodigoInforme() + ",\n";
        if (solicitud.getTipoSolicitud() != null) {
            sql = sql + "    tipo_solicitud = '" + solicitud.getTipoSolicitud() + "',\n";
        }
        if (solicitud.getFechaSolicitud() != null) {
            sql = sql + "    fecha_solicitud = '" + solicitud.getFechaSolicitud() + "',\n";
        }
        if (solicitud.getCodigoUsuario() != null) {
            sql = sql + "    codigo_usuario = " + solicitud.getCodigoUsuario() + ",\n";
        }
        if (solicitud.getDocumentoSolicitante() != null) {
            sql = sql + "    documento_solicitante = '" + solicitud.getDocumentoSolicitante() + "',\n";
        }
        if (solicitud.getTipoDocumento() != null) {
            sql = sql + "    tipo_documento = '" + solicitud.getTipoDocumento() + "',\n";
        }
        if (solicitud.getTipoPersona() != null) {
            sql = sql + "    tipo_persona = '" + solicitud.getTipoPersona() + "',\n";
        }
        if (solicitud.getNombreSolicitante() != null) {
            sql = sql + "    nombre_solicitante = '" + solicitud.getNombreSolicitante() + "',\n";
        }
        if (solicitud.getApellidoSolicitante() != null) {
            sql = sql + "    apellido_solicitante = '" + solicitud.getApellidoSolicitante() + "',\n";
        }
        if (solicitud.getFechaNacimientoSolicitante() != null) {
            sql = sql + "    fecha_nacimiento_solicitante = '" + solicitud.getFechaNacimientoSolicitante() + "',\n";
        }
        if (solicitud.getEdadSolicitante() != null) {
            sql = sql + "    edad_solicitante = " + solicitud.getEdadSolicitante() + ",\n";
        }
        if (solicitud.getDireccionSolicitante() != null) {
            sql = sql + "    direccion_solicitante = '" + solicitud.getDireccionSolicitante() + "',\n";
        }
        if (solicitud.getEmailSolicitante() != null) {
            sql = sql + "    email_solicitante = '" + solicitud.getEmailSolicitante() + "',\n";
        }
        if (solicitud.getTelefonoConvencionalSolicitante() != null) {
            sql = sql + "    telefono_convencional_solicitante = '" + solicitud.getTelefonoConvencionalSolicitante() + "',\n";
        }
        if (solicitud.getTelefonoCelularSolicitante() != null) {
            sql = sql + "    telefono_celular_solicitante = '" + solicitud.getTelefonoCelularSolicitante() + "',\n";
        }
        if (solicitud.getCiudadSolicitante() != null) {
            sql = sql + "    ciudad_solicitante = '" + solicitud.getCiudadSolicitante() + "',\n";
        }
        if (solicitud.getRucSolicitante() != null) {
            sql = sql + "    ruc_solicitante = '" + solicitud.getRucSolicitante() + "',\n";
        }
        if (solicitud.getNombreArea() != null) {
            sql = sql + "    nombre_area = '" + solicitud.getNombreArea() + "',\n";
        }
        if (solicitud.getNombreRazonSocial() != null) {
            sql = sql + "    nombre_razon_social = '" + solicitud.getNombreRazonSocial() + "',\n";
        }
        if (solicitud.getRepresentanteLegal() != null) {
            sql = sql + "    representante_legal = '" + solicitud.getRepresentanteLegal() + "',\n";
        }
        if (solicitud.getNombreRepresentanteLegal() != null) {
            sql = sql + "    nombre_representante_legal = '" + solicitud.getNombreRepresentanteLegal() + "',\n";
        }
        if (solicitud.getApellidoRepresentanteLegal() != null) {
            sql = sql + "    apellido_representante_legal = '" + solicitud.getApellidoRepresentanteLegal() + "',\n";
        }
        if (solicitud.getZona() != null) {
            sql = sql + "    zona = '" + solicitud.getZona() + "',\n";
        }
        if (solicitud.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + solicitud.getCodigoProvincia() + ",\n";
        }
        if (solicitud.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + solicitud.getCodigoCanton() + ",\n";
        }
        if (solicitud.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + solicitud.getCodigoParroquia() + ",\n";
        }
        if (solicitud.getTipoCoordenada() != null) {
            sql = sql + "    tipo_coordenada = '" + solicitud.getTipoCoordenada() + "',\n";
        }
        if (solicitud.getPlazoConcesion() != null) {
            sql = sql + "    plazo_concesion = " + solicitud.getPlazoConcesion() + ",\n";
        }
        if (solicitud.getNumeroHectareas() != null) {
            sql = sql + "    numero_hectareas = " + solicitud.getNumeroHectareas() + ",\n";
        }
        if (solicitud.getModalidad() != null) {
            sql = sql + "    modalidad = '" + solicitud.getModalidad() + "',\n";
        }
        if (solicitud.getTipoMaterial() != null) {
            sql = sql + "    tipo_material = '" + solicitud.getTipoMaterial() + "',\n";
        }
        if (solicitud.getFormaExplotacion() != null) {
            sql = sql + "    forma_explotacion = '" + solicitud.getFormaExplotacion() + "',\n";
        }
        if (solicitud.getMaterialInteres() != null) {
            sql = sql + "    material_interes = '" + solicitud.getMaterialInteres() + "',\n";
        }
        if (solicitud.getVolumenDiarioExplotacion() != null) {
            sql = sql + "    volumen_diario_explotacion = " + solicitud.getVolumenDiarioExplotacion() + ",\n";
        }
        if (solicitud.getVolumenTotalExplotacion() != null) {
            sql = sql + "    volumen_total_explotacion = " + solicitud.getVolumenTotalExplotacion() + ",\n";
        }
        if (solicitud.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + solicitud.getCasilleroJudicial() + "',\n";
        }
        if (solicitud.getDocumentoConcesionarioPrincipal() != null) {
            sql = sql + "    documento_concesionario_principal = '" + solicitud.getDocumentoConcesionarioPrincipal() + "',\n";
        }
        if (solicitud.getNombreConcesionarioPrincipal() != null) {
            sql = sql + "    nombre_concesionario_principal = '" + solicitud.getNombreConcesionarioPrincipal() + "',\n";
        }
        if (solicitud.getApellidoConcesionarioPrincipal() != null) {
            sql = sql + "    apellido_concesionario_principal = '" + solicitud.getApellidoConcesionarioPrincipal() + "',\n";
        }
        if (solicitud.getCodigoCensal() != null) {
            sql = sql + "    codigo_censal = '" + solicitud.getCodigoCensal() + "',\n";
        }
        if (solicitud.getAceptaCondiciones() != null) {
            sql = sql + "    acepta_condiciones = " + solicitud.getAceptaCondiciones() + " ,\n";
        }
        //+ "    xml_data = '" + solicitud.getx + "',\n"
        if (solicitud.getCampoReservado10() != null) {
            sql = sql + "    campo_reservado_10 = '" + solicitud.getCampoReservado10() + "',\n";
        }
        if (solicitud.getCampoReservado09() != null) {
            sql = sql + "    campo_reservado_09 = '" + solicitud.getCampoReservado09() + "',\n";
        }
        if (solicitud.getCampoReservado08() != null) {
            sql = sql + "    campo_reservado_08 = '" + solicitud.getCampoReservado08() + "',\n";
        }
        if (solicitud.getCampoReservado07() != null) {
            sql = sql + "    campo_reservado_07 = '" + solicitud.getCampoReservado07() + "',\n";
        }
        if (solicitud.getCampoReservado06() != null) {
            sql = sql + "    campo_reservado_06 = '" + solicitud.getCampoReservado06() + "',\n";
        }
        if (solicitud.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + solicitud.getCampoReservado05() + "',\n";
        }
        if (solicitud.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + solicitud.getCampoReservado04() + "',\n";
        }
        if (solicitud.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + solicitud.getCampoReservado03() + "',\n";
        }
        if (solicitud.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + solicitud.getCampoReservado02() + "',\n";
        }
        if (solicitud.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + solicitud.getCampoReservado01() + "',\n";
        }
        if (solicitud.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + solicitud.getEstadoRegistro() + ",\n";
        }
        if (solicitud.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + solicitud.getFechaCreacion() + "',\n";
        }
        if (solicitud.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + solicitud.getUsuarioCreacion() + ",\n";
        }
        if (solicitud.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + solicitud.getFechaModificacion() + "',\n";
        }
        if (solicitud.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + solicitud.getUsuarioModificacion() + ",\n";
        }
        if (solicitud.getEstadoSolicitud() != null) {
            sql = sql + "    estado_solicitud = " + solicitud.getEstadoSolicitud() + ",\n";
        }
        sql = sql + "    migrada = true\n"
                + "WHERE\n"
                + "    codigo_solicitud = " + solicitud.getCodigoSolicitud();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
