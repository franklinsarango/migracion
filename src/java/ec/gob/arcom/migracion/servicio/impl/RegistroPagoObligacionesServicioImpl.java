/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.RegistroPagoObligacionesDao;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.RegistroPagoDetalle;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import ec.gob.arcom.migracion.servicio.RegistroPagoDetalleServicio;
import ec.gob.arcom.migracion.servicio.RegistroPagoObligacionesServicio;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegistroPagoObligacionesServicio")
public class RegistroPagoObligacionesServicioImpl extends GenericServiceImpl<RegistroPagoObligaciones, Long>
        implements RegistroPagoObligacionesServicio {

    @EJB
    private RegistroPagoObligacionesDao registroPagoObligacionesDao;
    @EJB
    private RegistroPagoDetalleServicio registroPagoDetalleServicio;

    @Override
    public GenericDao<RegistroPagoObligaciones, Long> getDao() {
        return registroPagoObligacionesDao;
    }

    @Override
    public RegistroPagoObligaciones obtenerPorCodigoRegistroPagoObligaciones(Long codigoRegistroPagoObligaciones) {
        return registroPagoObligacionesDao.obtenerPorCodigoRegistroPagoObligaciones(codigoRegistroPagoObligaciones);
    }

    @Override
    public void actualizarRegistroPagoObligaciones(RegistroPagoObligaciones registroPagoObligaciones) throws Exception {
        registroPagoObligacionesDao.actualizarRegistroPagoObligaciones(registroPagoObligaciones);
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerPRUNacional() {
        return registroPagoObligacionesDao.obtenerPRUNacional();
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerPorNumeroComprobanteArcom(String numeroComprobanteArcom) {
        return registroPagoObligacionesDao.obtenerPorCodigoComprobanteArcom(numeroComprobanteArcom);
    }

    @Override
    public List<RegistroPagoObligaciones> obtenerRegistrosAutogestion(Date fechaInicio, Date fechaFin, String numeroComprobanteArcom, 
            String cedula, String codigoDerechoMinero, String prefijoRegionalParam) {
        return registroPagoObligacionesDao.obtenerListaAutogestion(fechaInicio, fechaFin, numeroComprobanteArcom, 
                cedula, codigoDerechoMinero, prefijoRegionalParam);
    }

    @Override
    public void guardarTodo(RegistroPagoObligaciones registroPagoObligaciones, List<DerechoMineroDto> derechosMineros, Long codigoUsuario) {
        this.create(registroPagoObligaciones);
        System.out.println("registroPagoObligaciones.getCodigoRegistro(): " + registroPagoObligaciones.getCodigoRegistro());
        if (derechosMineros != null) {
            this.guardarDerechosMineros(registroPagoObligaciones, derechosMineros, codigoUsuario);
        }
    }

    @Override
    public void actualizarTodo(RegistroPagoObligaciones registroPagoObligaciones, List<DerechoMineroDto> derechosMineros, Long codigoUsuario) throws Exception {
        this.actualizarRegistroPagoObligaciones(registroPagoObligaciones);
        List<RegistroPagoDetalle> pagosAnteriores = registroPagoDetalleServicio.obtenerPorCodigoRegistroPago(registroPagoObligaciones.getCodigoRegistro());
        if (pagosAnteriores != null) {
            for (RegistroPagoDetalle pagoAnterior : pagosAnteriores) {
                this.registroPagoDetalleServicio.delete(pagoAnterior.getCodigoRegistroPagoDetalle());
            }
        }
        if (derechosMineros != null) {
            guardarDerechosMineros(registroPagoObligaciones, derechosMineros, codigoUsuario);
        }
    }

    private void guardarDerechosMineros(RegistroPagoObligaciones registroPagoObligaciones, List<DerechoMineroDto> derechosMineros, Long codigoUsuario) {
        for (DerechoMineroDto derechoMinero : derechosMineros) {
            RegistroPagoDetalle pago = new RegistroPagoDetalle();
            pago.setCodigoRegistroPago(registroPagoObligaciones);
            pago.setValorPagado(derechoMinero.getValorPagoDerechoMinero());
            if (derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                    || derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                    || derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_MA_PEQ_MIN.getCodigo())
                    || derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())
                    || derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_PEQ_MIN.getCodigo())) {
                ConcesionMinera cm = new ConcesionMinera();
                cm.setCodigoConcesion(derechoMinero.getId());
                pago.setCodigoConcesion(cm);
            }
            if (derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
                LicenciaComercializacion lc = new LicenciaComercializacion();
                lc.setCodigoLicenciaComercializacion(derechoMinero.getId());
                pago.setCodigoLicenciaComercializacion(lc);
            }
            if (derechoMinero.getCodigoTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
                PlantaBeneficio pb = new PlantaBeneficio();
                pb.setCodigoPlantaBeneficio(derechoMinero.getId());
                pago.setCodigoPlantaBeneficio(pb);
            }
            pago.setFechaCreacion(new Date());
            pago.setUsuarioCreacion(BigInteger.valueOf(codigoUsuario));
            pago.setEstadoRegistro(Boolean.TRUE);
            this.registroPagoDetalleServicio.create(pago);
        }
    }

}
