/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.LicenciaComercializacionDao;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dao.RegistroPagoObligacionesDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegistroPagoObligacionesDao")
public class RegistroPagoObligacionesDaoEjb extends GenericDaoEjbEl<RegistroPagoObligaciones, Long> implements
        RegistroPagoObligacionesDao {

    @EJB
    private ConcesionMineraDao concesionMineraDao;
    @EJB
    private LicenciaComercializacionDao licenciaComercializacionDao;
    @EJB
    private PlantaBeneficioDao plantaBeneficioDao;

    public RegistroPagoObligacionesDaoEjb() {
        super(RegistroPagoObligaciones.class);
    }

    @Override
    public RegistroPagoObligaciones obtenerPorCodigoRegistroPagoObligaciones(Long codigoRegistroPagoObligaciones) {
        try {
            String hql = "select rpo from RegistroPagoObligaciones rpo where rpo.codigoRegistro = :codigoRegistroPagoObligaciones";
            Query query = em.createQuery(hql);
            query.setParameter("codigoRegistroPagoObligaciones", codigoRegistroPagoObligaciones);
            RegistroPagoObligaciones registroPagoObligaciones = (RegistroPagoObligaciones) query.getSingleResult();
            this.refresh(registroPagoObligaciones);
            return registroPagoObligaciones;
        } catch (NoResultException nrException) {
            return null;
        }
    }

    @Override
    public void actualizarRegistroPagoObligaciones(RegistroPagoObligaciones registroPagoObligaciones) throws Exception {
        String sql = "UPDATE catmin.registro_pago_obligaciones \n"
                + "SET codigo_registro = " + registroPagoObligaciones.getCodigoRegistro() + ", \n";
        /*if (registroPagoObligaciones.getCodigoConceptoPago() != null) {
         sql += "codigo_concepto_pago = " + registroPagoObligaciones.getCodigoConceptoPago().getCodigoConceptoPago() + ", \n";
         } else {
         sql += "codigo_concepto_pago = " + null + ", \n";
         }*/
        if (registroPagoObligaciones.getCodigoConcesion() != null) {
            sql += "codigo_concesion = " + registroPagoObligaciones.getCodigoConcesion().getCodigoConcesion() + ", \n";
        } else {
            sql += "codigo_concesion = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCodigoArea() != null) {
            sql += "codigo_area = " + registroPagoObligaciones.getCodigoArea().getCodigoAreaMinera() + ", \n";
        } else {
            sql += "codigo_area = " + null + ", \n";
        }
        sql += "codigo_informe = " + registroPagoObligaciones.getCodigoInforme() + ", \n";
        if (registroPagoObligaciones.getCodigoLicenciaComercializacion() != null) {
            sql += "codigo_licencia_comercializacion = " + registroPagoObligaciones.getCodigoLicenciaComercializacion().getCodigoLicenciaComercializacion() + ", \n";
        } else {
            sql += "codigo_licencia_comercializacion = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCodigoPlantaBeneficio() != null) {
            sql += "codigo_planta_beneficio = " + registroPagoObligaciones.getCodigoPlantaBeneficio().getCodigoPlantaBeneficio() + ", \n";
        } else {
            sql += "codigo_planta_beneficio = " + null + ", \n";
        }
        sql += "fecha_emision_pago = '" + registroPagoObligaciones.getFechaEmisionPago() + "', \n";
        if (registroPagoObligaciones.getCodigoBanco() != null) {
            sql += "codigo_banco = " + registroPagoObligaciones.getCodigoBanco().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "codigo_banco = " + null + ", \n";
        }
        if (registroPagoObligaciones.getLugarEmisionPago() != null) {
            sql += "lugar_emision_pago = " + registroPagoObligaciones.getLugarEmisionPago().getCodigoLocalidad() + ", \n";
        } else {
            sql += "lugar_emision_pago = " + null + ", \n";
        }
        sql += "valor_pago = " + registroPagoObligaciones.getValorPago() + ", \n";
        if (registroPagoObligaciones.getNumeroComprobanteBanco() != null) {
            sql += "numero_comprobante_banco = '" + registroPagoObligaciones.getNumeroComprobanteBanco() + "', \n";
        } else {
            sql += "numero_comprobante_banco = " + null + ", \n";
        }
        if (registroPagoObligaciones.getDocumentoPersonaPago() != null) {
            sql += "documento_persona_pago = '" + registroPagoObligaciones.getDocumentoPersonaPago() + "', \n";
        } else {
            sql += "documento_persona_pago = " + null + ", \n";
        }
        if (registroPagoObligaciones.getNombrePersonaPago() != null) {
            sql += "nombre_persona_pago = '" + registroPagoObligaciones.getNombrePersonaPago() + "', \n";
        } else {
            sql += "nombre_persona_pago = " + null + ", \n";
        }
        if (registroPagoObligaciones.getApellidoPersonaPago() != null) {
            sql += "apellido_persona_pago = '" + registroPagoObligaciones.getApellidoPersonaPago() + "', \n";
        } else {
            sql += "apellido_persona_pago = " + null + ", \n";
        }
        if (registroPagoObligaciones.getNumeroComprobanteArcom() != null) {
            sql += "numero_comprobante_arcom = '" + registroPagoObligaciones.getNumeroComprobanteArcom() + "', \n";
        } else {
            sql += "numero_comprobante_arcom = " + null + ", \n";
        }
        if (registroPagoObligaciones.getEstadoPago() != null) {
            sql += "estado_pago = " + registroPagoObligaciones.getEstadoPago().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "estado_pago = " + null + ", \n";
        }
        sql += "codigo_solicitud = " + registroPagoObligaciones.getCodigoSolicitud() + ", \n";
        sql += "numero_tramite = " + registroPagoObligaciones.getNumeroTramite() + ", \n";
        if (registroPagoObligaciones.getEntidadTramite() != null) {
            sql += "entidad_tramite = '" + registroPagoObligaciones.getEntidadTramite() + "', \n";
        } else {
            sql += "entidad_tramite = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCampoReservado05() != null) {
            sql += "campo_reservado_05 = '" + registroPagoObligaciones.getCampoReservado05() + "', \n";
        } else {
            sql += "campo_reservado_05 = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCampoReservado04() != null) {
            sql += "campo_reservado_04 = '" + registroPagoObligaciones.getCampoReservado04() + "', \n";
        } else {
            sql += "campo_reservado_04 = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCampoReservado03() != null) {
            sql += "campo_reservado_03 = '" + registroPagoObligaciones.getCampoReservado03() + "', \n";
        } else {
            sql += "campo_reservado_03 = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCampoReservado02() != null) {
            sql += "campo_reservado_02 = '" + registroPagoObligaciones.getCampoReservado02() + "', \n";
        } else {
            sql += "campo_reservado_02 = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCampoReservado01() != null) {
            sql += "campo_reservado_01 = '" + registroPagoObligaciones.getCampoReservado01() + "', \n";
        } else {
            sql += "campo_reservado_01 = " + null + ", \n";
        }
        sql += "estado_registro = " + registroPagoObligaciones.getEstadoRegistro() + ", \n";
        if (registroPagoObligaciones.getFechaCreacion() != null) {
            sql += "fecha_creacion = '" + registroPagoObligaciones.getFechaCreacion() + "', \n";
        } else {
            sql += "fecha_creacion = " + null + ", \n";
        }
        sql += "usuario_creacion = " + registroPagoObligaciones.getUsuarioCreacion() + ", \n";
        if (registroPagoObligaciones.getFechaModificacion() != null) {
            sql += "fecha_modificacion = '" + registroPagoObligaciones.getFechaModificacion() + "', \n";
        } else {
            sql += "fecha_modificacion = " + null + ", \n";
        }
        sql += "usuario_modificacion = " + registroPagoObligaciones.getUsuarioModificacion() + ", \n";
        sql += "cantidad = " + registroPagoObligaciones.getCantidad() + ", \n";
        sql += "costo = " + registroPagoObligaciones.getCosto() + ", \n";
        if (registroPagoObligaciones.getObservacionAnalistaEconomico() != null) {
            sql += "observacion_analista_economico = '" + registroPagoObligaciones.getObservacionAnalistaEconomico() + "', \n";
        } else {
            sql += "observacion_analista_economico = " + null + ", \n";
        }
        if (registroPagoObligaciones.getFechaRevisionAnalistaEconomico() != null) {
            sql += "fecha_revision_analista_economico = '" + registroPagoObligaciones.getFechaRevisionAnalistaEconomico() + "', \n";
        } else {
            sql += "fecha_revision_analista_economico = " + null + ", \n";
        }
        sql += "aprobado_analista_economico = " + registroPagoObligaciones.getAprobadoAnalistaEconomico() + ", \n";
        if (registroPagoObligaciones.getObservacionCoordinadorRegional() != null) {
            sql += "observacion_coordinador_regional = '" + registroPagoObligaciones.getObservacionCoordinadorRegional() + "', \n";
        } else {
            sql += "observacion_coordinador_regional = " + null + ", \n";
        }
        if (registroPagoObligaciones.getFechaRevisionCoordinadorRegional() != null) {
            sql += "fecha_revision_coordinador_regional = '" + registroPagoObligaciones.getFechaRevisionCoordinadorRegional() + "', \n";
        } else {
            sql += "fecha_revision_coordinador_regional = " + null + ", \n";
        }
        sql += "aprobado_coordinador_regional = " + registroPagoObligaciones.getAprobadoCoordinadorRegional() + ", \n";
        sql += "valor_adicional = " + registroPagoObligaciones.getValorAdicional() + ", \n";
        if (registroPagoObligaciones.getDescripcionMotivoValorAdicional() != null) {
            sql += "descripcion_motivo_valor_adicional = '" + registroPagoObligaciones.getDescripcionMotivoValorAdicional() + "', \n";
        } else {
            sql += "descripcion_motivo_valor_adicional = " + null + ", \n";
        }
        sql += "valor_total_pago = " + registroPagoObligaciones.getValorTotalPago() + ", \n";
        sql += "valor_pagado_usuario = " + registroPagoObligaciones.getValorPagadoUsuario() + ", \n";
        sql += "valor_concepto_pago = " + registroPagoObligaciones.getValorConceptoPago() + ", \n";
        sql += "subsanar_pago = " + registroPagoObligaciones.getSubsanarPago() + ", \n";
        sql += "presenta_documentos = " + registroPagoObligaciones.getPresentaDocumentos() + ", \n";
        if (registroPagoObligaciones.getFechaPresentacion() != null) {
            sql += "fecha_presentacion = '" + registroPagoObligaciones.getFechaPresentacion() + "', \n";
        } else {
            sql += "fecha_presentacion = " + null + ", \n";
        }
        if (registroPagoObligaciones.getTipoPago() != null) {
            sql += "tipo_pago = " + registroPagoObligaciones.getTipoPago().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "tipo_pago = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCodigoTipoServicio() != null
                && registroPagoObligaciones.getCodigoTipoServicio().getCodigoCatalogoDetalle() != null) {
            sql += "codigo_tipo_servicio = " + registroPagoObligaciones.getCodigoTipoServicio().getCodigoCatalogoDetalle() + ", \n";
        } else {
            sql += "codigo_tipo_servicio = " + null + ", \n";
        }
        if (registroPagoObligaciones.getCodigoConceptoPago() != null
                && registroPagoObligaciones.getCodigoConceptoPago().getCodigoConceptoPago() != null) {
            sql += "codigo_concepto_pago = " + registroPagoObligaciones.getCodigoConceptoPago().getCodigoConceptoPago() + ", \n";
        } else {
            sql += "codigo_concepto_pago = " + null + ", \n";
        }
        sql += "codigo_tipo_registro = " + registroPagoObligaciones.getCodigoTipoRegistro() + " \n";
        sql += "WHERE codigo_registro = " + registroPagoObligaciones.getCodigoRegistro();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerPRUNacional() {
        String jpql = "select rpo from RegistroPagoObligaciones rpo";
        Query query = em.createQuery(jpql);
        List<RegistroPagoObligaciones> registrosNacional = query.getResultList();
        return registrosNacional;
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerPorCodigoComprobanteArcom(String numeroComprobanteArcom) {
        String jpql = "select rpo from RegistroPagoObligaciones rpo where rpo.numeroComprobanteArcom = :numeroComprobanteArcom";
        Query query = em.createQuery(jpql);
        query.setParameter("numeroComprobanteArcom", numeroComprobanteArcom);
        List<RegistroPagoObligaciones> autogestiones = query.getResultList();
        return autogestiones;
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerListaAutogestion(Date fechaDesde, Date fechaHasta, String numeroComprobanteArcom,
            String cedula, String codigoDerechoMinero, String prefijoRegionalParam) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaD = "";
        String fechaH = "";
        ConcesionMinera concesionMinera = null;
        LicenciaComercializacion licenciaComercializacion = null;
        PlantaBeneficio plantaBeneficio = null;
        Long codigoDerMin = null;
        if (fechaDesde != null) {
            fechaD = sdf.format(fechaDesde);
            System.out.println("fechaD: " + fechaD);
        }
        if (fechaHasta != null) {
            fechaH = sdf.format(fechaHasta);
            System.out.println("fechaH: " + fechaH);
        }
        if (codigoDerechoMinero != null) {
            concesionMinera = concesionMineraDao.findByCodigoArcom(codigoDerechoMinero);
            licenciaComercializacion = licenciaComercializacionDao.findByCodigoArcom(codigoDerechoMinero);
            plantaBeneficio = plantaBeneficioDao.findByCodigoArcom(codigoDerechoMinero);
            if (concesionMinera != null) {
                codigoDerMin = concesionMinera.getCodigoConcesion();
            } else if (licenciaComercializacion != null) {
                codigoDerMin = licenciaComercializacion.getCodigoLicenciaComercializacion();
            } else if (plantaBeneficio != null) {
                codigoDerMin = plantaBeneficio.getCodigoPlantaBeneficio();
            }
        }
        String jpql = "select rpo from RegistroPagoObligaciones rpo where 1 = 1 and rpo.estadoPago.codigoCatalogoDetalle = 574 and rpo.entidadTramite = null";
        if (fechaDesde != null && fechaHasta != null) {
            jpql += " and rpo.fechaCreacion >= '" + fechaD + "' and rpo.fechaCreacion <= '" + fechaH + "'";
        }
        if (fechaDesde != null && fechaHasta == null) {
            jpql += " and rpo.fechaCreacion >= '" + fechaD + "'";
        }
        if (fechaDesde == null && fechaHasta != null) {
            jpql += " and rpo.fechaCreacion <= '" + fechaH + "'";
        }
        if (numeroComprobanteArcom != null && !numeroComprobanteArcom.isEmpty()) {
            jpql += " and rpo.numeroComprobanteArcom = :numeroComprobanteArcom";
        }
        if (codigoDerMin != null) {
            jpql += " and (rpo.codigoConcesion.codigoConcesion = :codigoDerMin "
                    + "or rpo.codigoLicenciaComercializacion.codigoLicenciaComercializacion = :codigoDerMin"
                    + " or rpo.codigoPlantaBeneficio.codigoPlantaBeneficio = :codigoDerMin)";
        }
        jpql += " order by rpo.fechaCreacion desc";
        Query query = em.createQuery(jpql);
        if (numeroComprobanteArcom != null && !numeroComprobanteArcom.isEmpty()) {
            query.setParameter("numeroComprobanteArcom", numeroComprobanteArcom);
        }
        if (codigoDerMin != null) {
            query.setParameter("codigoDerMin", codigoDerMin);
        }
        List<RegistroPagoObligaciones> listaTmp = query.getResultList();
        List<RegistroPagoObligaciones> listaFinal = new ArrayList<>();
        for (RegistroPagoObligaciones rpo : listaTmp) {
            if (rpo.getNumeroComprobanteArcom() != null) {
                String prefijoRegional = rpo.getNumeroComprobanteArcom().substring(2, 4);
                if (prefijoRegional.equals(prefijoRegionalParam)) {
                    this.refresh(rpo);
                    listaFinal.add(rpo);
                }
            }
        }
        return listaFinal;
    }

}
