/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConceptoPago;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.CostoServicios;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.RegistroPagoDetalle;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.CostoServiciosServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.RegistroPagoDetalleServicio;
import ec.gob.arcom.migracion.servicio.RegistroPagoObligacionesServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.SujetoMineroServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean
@ViewScoped
public class RegistroPagoObligacionesCtrl extends BaseCtrl {

    @EJB
    private RegistroPagoObligacionesServicio registroPagoObligacionesServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private LicenciaComercializacionServicio licenciaComercializacionServicio;
    @EJB
    private PlantaBeneficioServicio plantaBeneficioServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private SujetoMineroServicio sujetoMineroServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @EJB
    private LocalidadRegionalServicio localidadRegionalServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private CostoServiciosServicio costoServiciosServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private RegistroPagoDetalleServicio registroPagoDetalleServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    private RegistroPagoObligaciones registroPagoObligacionesAutoGestion;
    private RegistroPagoObligaciones registroPagoObligacionesAutoGestionAnterior;
    private List<RegistroPagoObligaciones> listaRegistrosAutoGestion;
    private String codigoFiltro;

    private boolean sujetoMinero;

    private ConcesionMinera concesionMineraPopup;
    private LicenciaComercializacion licenciaComercializacionPopup;
    private PlantaBeneficio plantaBeneficioPopup;

    private Localidad provincia;
    private Localidad canton;
    private Localidad parroquia;

    private SujetoMinero sujetoMineroPopUp;

    private ConcesionMinera concesionMineraPopupAnterior;
    private LicenciaComercializacion licenciaComercializacionPopupAnterior;
    private PlantaBeneficio plantaBeneficioPopupAnterior;
    private SujetoMinero sujetoMineroPopUpAnterior;

    private String identificacionSujetoMinero;

    private List<RegistroPagoObligaciones> registrosPRUNacional;

    private Secuencia secuenciaComPago;
    private List<SelectItem> tipoServicios;
    private List<SelectItem> conceptosPago;

    private boolean generacionComprobante;
    private boolean aplicaCantidad;

    private Date fechaDesdeFiltro;
    private Date fechaHastaFiltro;
    private String numeroComprobanteArcomFiltro;
    private String codigoArcomFiltro;

    private String urlReporte;

    private List<SelectItem> provincias;

    private String numIdentificacionBusqueda;
    private PersonaDto personaDto;

    private List<DerechoMineroDto> derechosMineros;

    private BigDecimal valorPagoDerechoMinero;

    public RegistroPagoObligacionesCtrl() {
        listaRegistrosAutoGestion = null;
    }

    public void buscar() {
        listaRegistrosAutoGestion = null;
        getListaRegistrosAutoGestion();
    }

    public RegistroPagoObligaciones getRegistroPagoObligacionesAutoGestion() {
        if (registroPagoObligacionesAutoGestion == null) {
            String registroPagoObligacionesId = getHttpServletRequestParam("idItem");
            Long idRegistroPagoObligaciones = null;
            if (registroPagoObligacionesId != null) {
                idRegistroPagoObligaciones = Long.parseLong(registroPagoObligacionesId);
            }
            if (idRegistroPagoObligaciones == null) {
                registroPagoObligacionesAutoGestion = new RegistroPagoObligaciones();
                registroPagoObligacionesAutoGestion.setCodigoConceptoPago(new ConceptoPago());
                registroPagoObligacionesAutoGestion.setCodigoBanco(new CatalogoDetalle());
                //secuenciaComPago = secuenciaServicio.obtenerPorNemonico("SETCOMPAGORGL" + login.getPrefijoRegional());
                //generarCodigoComprobante();
                registroPagoObligacionesAutoGestion.setCodigoTipoServicio(new CatalogoDetalle());
                registroPagoObligacionesAutoGestion.setCantidad(1);
                registroPagoObligacionesAutoGestion.setLugarEmisionPago(new Localidad());
                this.generacionComprobante = true;
            } else {
                registroPagoObligacionesAutoGestion = registroPagoObligacionesServicio.obtenerPorCodigoRegistroPagoObligaciones(idRegistroPagoObligaciones);
                registroPagoObligacionesAutoGestionAnterior = registroPagoObligacionesServicio.obtenerPorCodigoRegistroPagoObligaciones(idRegistroPagoObligaciones);
                registroPagoObligacionesAutoGestion.getCodigoConcesion();
                registroPagoObligacionesAutoGestion.getCodigoLicenciaComercializacion();
                registroPagoObligacionesAutoGestion.getCodigoPlantaBeneficio();
                if (registroPagoObligacionesAutoGestion.getCodigoConceptoPago() == null) {
                    registroPagoObligacionesAutoGestion.setCodigoConceptoPago(new ConceptoPago());
                }
                if (registroPagoObligacionesAutoGestion.getCodigoBanco() == null) {
                    registroPagoObligacionesAutoGestion.setCodigoBanco(new CatalogoDetalle());
                }
                if (registroPagoObligacionesAutoGestion.getCodigoTipoServicio() == null) {
                    registroPagoObligacionesAutoGestion.setCodigoTipoServicio(new CatalogoDetalle());
                }
                obtenerValorConceptoPago();
                if (registroPagoObligacionesAutoGestion.getCantidad() == null
                        || registroPagoObligacionesAutoGestion.getCantidad().equals(0)) {
                    aplicaCantidad = false;
                } else {
                    aplicaCantidad = true;
                }
                if (registroPagoObligacionesAutoGestion.getLugarEmisionPago() == null) {
                    registroPagoObligacionesAutoGestion.setLugarEmisionPago(new Localidad());
                }
                if (registroPagoObligacionesAutoGestion != null) {
                    SujetoMinero sm = sujetoMineroServicio.obtenerPorIdentificacion(identificacionSujetoMinero);
                    if (registroPagoObligacionesAutoGestion.getCodigoConcesion() == null
                            && registroPagoObligacionesAutoGestion.getCodigoLicenciaComercializacion() == null
                            && registroPagoObligacionesAutoGestion.getCodigoPlantaBeneficio() == null
                            && sm != null) {
                        registroPagoObligacionesAutoGestion.setCodigoSujetoMinero(sm);
                    }
                }
                if (registroPagoObligacionesAutoGestion.getDocumentoPersonaPago() != null) {
                    PersonaDto pDto = personaNaturalServicio
                            .obtenerPersonaPorNumIdentificacion(registroPagoObligacionesAutoGestion.getDocumentoPersonaPago());
                    if (pDto != null) {
                        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(pDto.getIdentificacion());
                        registroPagoObligacionesAutoGestion.setNombrePersonaPago(pDto.getNombres());
                        registroPagoObligacionesAutoGestion.setApellidoPersonaPago(pDto.getApellidos());
                    }
                }
                List<RegistroPagoDetalle> pagos = registroPagoDetalleServicio
                        .obtenerPorCodigoRegistroPago(registroPagoObligacionesAutoGestion.getCodigoRegistro());
                List<DerechoMineroDto> listaTmp = new ArrayList<>();
                if (pagos != null) {
                    for (RegistroPagoDetalle pago : pagos) {
                        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
                        derechoMineroDto.setValorPagoDerechoMinero(pago.getValorPagado());
                        if (pago.getCodigoConcesion() != null) {
                            derechoMineroDto.setCodigo(pago.getCodigoConcesion().getCodigoArcom());
                            derechoMineroDto.setCodigoTipoSolicitud(pago.getCodigoConcesion().getCodigoTipoMineria().getCodigoTipoMineria());
                            derechoMineroDto.setTipoSolicitud(pago.getCodigoConcesion().getCodigoTipoMineria().getNombreTipoMineria());
                            derechoMineroDto.setTipoDerechoMinero(pago.getCodigoConcesion().getCodigoTipoMineria().getNombreTipoMineria());
                            derechoMineroDto.setId(pago.getCodigoConcesion().getCodigoConcesion());
                        }
                        if (pago.getCodigoLicenciaComercializacion() != null) {
                            derechoMineroDto.setCodigo(pago.getCodigoLicenciaComercializacion().getCodigoArcom());
                            derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo());
                            derechoMineroDto.setTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getDescripcion());
                            derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getDescripcion());
                            derechoMineroDto.setId(pago.getCodigoLicenciaComercializacion().getCodigoLicenciaComercializacion());
                        }
                        if (pago.getCodigoPlantaBeneficio() != null) {
                            derechoMineroDto.setCodigo(pago.getCodigoPlantaBeneficio().getCodigoArcom());
                            derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo());
                            derechoMineroDto.setTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion());
                            derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion());
                            derechoMineroDto.setId(pago.getCodigoPlantaBeneficio().getCodigoPlantaBeneficio());
                        }
                        listaTmp.add(derechoMineroDto);
                    }
                    derechosMineros = listaTmp;
                    sujetoMinero = false;
                } else {
                    sujetoMinero = true;
                }
                cambiarPopUp();
            }
        }
        return registroPagoObligacionesAutoGestion;
    }

    public void generarCodigoComprobante() {
        secuenciaComPago = secuenciaServicio.obtenerPorNemonico("SETCOMPAGORGL" + login.getPrefijoRegional());
        registroPagoObligacionesAutoGestion.setNumeroComprobanteArcom(
                formarCodigoComprobante(login.getPrefijoRegional(), secuenciaComPago.getValor()));
    }

    public void setRegistroPagoObligacionesAutoGestion(RegistroPagoObligaciones registroPagoObligacionesAutoGestion) {
        this.registroPagoObligacionesAutoGestion = registroPagoObligacionesAutoGestion;
    }

    public RegistroPagoObligaciones getRegistroPagoObligacionesAutoGestionAnterior() {
        return registroPagoObligacionesAutoGestionAnterior;
    }

    public void setRegistroPagoObligacionesAutoGestionAnterior(RegistroPagoObligaciones registroPagoObligacionesAutoGestionAnterior) {
        this.registroPagoObligacionesAutoGestionAnterior = registroPagoObligacionesAutoGestionAnterior;
    }

    public String editarRegistro() {
        RegistroPagoObligaciones registroPagoObligacionesItem = (RegistroPagoObligaciones) getExternalContext().getRequestMap().get("reg");
        return "autogestionform?faces-redirect=true&idItem=" + registroPagoObligacionesItem.getCodigoRegistro();
    }

    public String guardarRegistroAutoGestion() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            if (!isAplicaCantidad()) {
                registroPagoObligacionesAutoGestion.setCantidad(1);
            }
            if (generacionComprobante) {
                generarCodigoComprobante();
            }
            CatalogoDetalle cd = new CatalogoDetalle();
            cd.setCodigoCatalogoDetalle(574L);
            registroPagoObligacionesAutoGestion.setEstadoPago(cd);
            if (registroPagoObligacionesAutoGestion.getCodigoConcesion() != null) {
                registroPagoObligacionesAutoGestion.setCodigoLicenciaComercializacion(null);
                registroPagoObligacionesAutoGestion.setCodigoPlantaBeneficio(null);
                registroPagoObligacionesAutoGestion.setCodigoSujetoMinero(null);
            }
            if (registroPagoObligacionesAutoGestion.getCodigoLicenciaComercializacion() != null) {
                registroPagoObligacionesAutoGestion.setCodigoConcesion(null);
                registroPagoObligacionesAutoGestion.setCodigoPlantaBeneficio(null);
                registroPagoObligacionesAutoGestion.setCodigoSujetoMinero(null);
            }
            if (registroPagoObligacionesAutoGestion.getCodigoPlantaBeneficio() != null) {
                registroPagoObligacionesAutoGestion.setCodigoConcesion(null);
                registroPagoObligacionesAutoGestion.setCodigoLicenciaComercializacion(null);
                registroPagoObligacionesAutoGestion.setCodigoSujetoMinero(null);
            }
            if (registroPagoObligacionesAutoGestion.getCodigoSujetoMinero() != null) {
                registroPagoObligacionesAutoGestion.setCodigoConcesion(null);
                registroPagoObligacionesAutoGestion.setCodigoLicenciaComercializacion(null);
                registroPagoObligacionesAutoGestion.setCodigoPlantaBeneficio(null);
            }
            if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro() != null) {
                if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.SUJETO_MINERO.getCodigo())
                        || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO.getCodigo())) {
                    derechosMineros = null;
                } else if (derechosMineros == null || derechosMineros.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ingrese al menos un derecho minero: ", null));
                    return null;
                }
            }
            if (registroPagoObligacionesAutoGestion.getCodigoRegistro() == null) {
                System.out.println("entra create");
                if (registroPagoObligacionesAutoGestion.getNumeroComprobanteArcom() != null) {
                    List<RegistroPagoObligaciones> autogestiones = registroPagoObligacionesServicio
                            .obtenerPorNumeroComprobanteArcom(registroPagoObligacionesAutoGestion.getNumeroComprobanteArcom());
                    if (autogestiones != null && !autogestiones.isEmpty()) {
                        autogestiones.get(0).getCodigoConcesion();
                        autogestiones.get(0).getCodigoLicenciaComercializacion();
                        autogestiones.get(0).getCodigoPlantaBeneficio();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "El número de comprobante ya se encuentra atado al derecho minero de código: "
                                + autogestiones.get(0).getCodigoDerechoMinero(), null));
                        return null;
                    }
                }
                registroPagoObligacionesAutoGestion.setEstadoRegistro(true);
                registroPagoObligacionesAutoGestion.setFechaCreacion(new Date());
                registroPagoObligacionesAutoGestion.setFechaRevisionAnalistaEconomico(new Date());
                registroPagoObligacionesAutoGestion.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                System.out.println("registroPagoObligacionesAutoGestion.getCodigoDerechoMinero(): " + registroPagoObligacionesAutoGestion.getCodigoDerechoMinero());
                /*if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                 || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                 || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
                 System.out.println("entra if concesion");
                 ConcesionMinera cm = new ConcesionMinera();
                 cm.setCodigoConcesion(registroPagoObligacionesAutoGestion.getCodigoDerechoMinero());
                 registroPagoObligacionesAutoGestion.setCodigoConcesion(cm);
                 }
                 if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
                 System.out.println("entra if licencia");
                 LicenciaComercializacion lc = new LicenciaComercializacion();
                 lc.setCodigoLicenciaComercializacion(registroPagoObligacionesAutoGestion.getCodigoDerechoMinero());
                 registroPagoObligacionesAutoGestion.setCodigoLicenciaComercializacion(lc);
                 }
                 if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
                 System.out.println("entra if planta");
                 PlantaBeneficio pb = new PlantaBeneficio();
                 pb.setCodigoPlantaBeneficio(registroPagoObligacionesAutoGestion.getCodigoDerechoMinero());
                 registroPagoObligacionesAutoGestion.setCodigoPlantaBeneficio(pb);
                 }*/
                //Secuencia secuencia = secuenciaServicio.obtenerPorNemonico(codigoFiltro)

                /*if (registroPagoObligacionesAutoGestion.getCodigoConcesion() != null) {
                 ConcesionMinera concesionMineraNueva = new ConcesionMinera();
                 concesionMineraNueva.setCodigoConcesion(registroPagoObligacionesAutoGestion.getCodigoConcesion().getCodigoConcesion());
                 registroPagoObligacionesAutoGestion.setCodigoConcesion(null);
                 registroPagoObligacionesAutoGestion.setCodigoConcesion(concesionMineraNueva);
                 }*/
                //registroPagoObligacionesServicio.create(registroPagoObligacionesAutoGestion);
                registroPagoObligacionesServicio.guardarTodo(registroPagoObligacionesAutoGestion, derechosMineros, us.getCodigoUsuario());
                secuenciaComPago.setValor(secuenciaComPago.getValor() + 1);
                secuenciaServicio.update(secuenciaComPago);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(registroPagoObligacionesAutoGestion.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito", null));
            } else {
                System.out.println("entra update");
                if (registroPagoObligacionesAutoGestion.getNumeroComprobanteArcom() != null) {
                    List<RegistroPagoObligaciones> autogestiones = registroPagoObligacionesServicio
                            .obtenerPorNumeroComprobanteArcom(registroPagoObligacionesAutoGestion.getNumeroComprobanteArcom());
                    if (autogestiones != null && autogestiones.size() > 1) {
                        autogestiones.get(0).getCodigoConcesion();
                        autogestiones.get(0).getCodigoLicenciaComercializacion();
                        autogestiones.get(0).getCodigoPlantaBeneficio();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "El número de comprobante ya se encuentra atado al derecho minero de código: "
                                + autogestiones.get(0).getCodigoDerechoMinero(), null));
                        return null;
                    }
                }
                registroPagoObligacionesAutoGestion.setFechaModificacion(new Date());
                registroPagoObligacionesAutoGestion.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                //registroPagoObligacionesServicio.actualizarRegistroPagoObligaciones(registroPagoObligacionesAutoGestion);
                registroPagoObligacionesServicio.actualizarTodo(registroPagoObligacionesAutoGestion, derechosMineros, us.getCodigoUsuario());
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(registroPagoObligacionesAutoGestionAnterior.toString());
                auditoria.setDetalleCambios(registroPagoObligacionesAutoGestion.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro actualizado con éxito", null));
            }
            listaRegistrosAutoGestion = null;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
        return "autogestion";
    }

    public List<RegistroPagoObligaciones> getListaRegistrosAutoGestion() {
        if (listaRegistrosAutoGestion == null) {
            listaRegistrosAutoGestion = registroPagoObligacionesServicio
                    .obtenerRegistrosAutogestion(fechaDesdeFiltro, fechaHastaFiltro, numeroComprobanteArcomFiltro, null,
                            codigoArcomFiltro, login.getPrefijoRegional());
        }
        return listaRegistrosAutoGestion;
    }

    public void setListaRegistrosAutoGestion(List<RegistroPagoObligaciones> listaRegistrosAutoGestion) {
        this.listaRegistrosAutoGestion = listaRegistrosAutoGestion;
    }

    public void buscarRegistro() {
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Antes de buscar por código debe elegir un tipo de registro", null));
            return;
        }
        if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            System.out.println("codigoFiltro: " + codigoFiltro);
            concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            concesionMineraPopupAnterior = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            System.out.println("concesionMineraPopup: " + concesionMineraPopup);
            if (concesionMineraPopup != null) {
                provincia = localidadServicio.findByPk(concesionMineraPopup.getCodigoProvincia().longValue());
                canton = localidadServicio.findByPk(concesionMineraPopup.getCodigoCanton().longValue());
                parroquia = localidadServicio.findByPk(concesionMineraPopup.getCodigoParroquia().longValue());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "La concesión no existe", null));
            }
        } else if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
            licenciaComercializacionPopup = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            licenciaComercializacionPopupAnterior = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (licenciaComercializacionPopup != null) {
                provincia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoProvincia().longValue());
                canton = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoCanton().longValue());
                parroquia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoParroquida().longValue());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "La licencia no existe", null));
            }
        } else if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
            plantaBeneficioPopup = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            plantaBeneficioPopupAnterior = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (plantaBeneficioPopup != null) {
                provincia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoProvincia().longValue());
                canton = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoCanton().longValue());
                parroquia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoParroquida().longValue());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "La planta no existe", null));
            }
        }
    }

    public void cambiarPopUp() {
        if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro() != null) {
            if (registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(ConstantesEnum.SUJETO_MINERO.getCodigo())
                    || registroPagoObligacionesAutoGestion.getCodigoTipoRegistro().equals(1000L)) {
                sujetoMinero = true;
            } else {
                sujetoMinero = false;
            }
        }
        valorPagoDerechoMinero = null;
    }

    public boolean isSujetoMinero() {
        return sujetoMinero;
    }

    public void setSujetoMinero(boolean sujetoMinero) {
        this.sujetoMinero = sujetoMinero;
    }

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public ConcesionMinera getConcesionMineraPopup() {
        return concesionMineraPopup;
    }

    public void setConcesionMineraPopup(ConcesionMinera concesionMineraPopup) {
        this.concesionMineraPopup = concesionMineraPopup;
    }

    public LicenciaComercializacion getLicenciaComercializacionPopup() {
        return licenciaComercializacionPopup;
    }

    public void setLicenciaComercializacionPopup(LicenciaComercializacion licenciaComercializacionPopup) {
        this.licenciaComercializacionPopup = licenciaComercializacionPopup;
    }

    public PlantaBeneficio getPlantaBeneficioPopup() {
        return plantaBeneficioPopup;
    }

    public void setPlantaBeneficioPopup(PlantaBeneficio plantaBeneficioPopup) {
        this.plantaBeneficioPopup = plantaBeneficioPopup;
    }

    public Localidad getProvincia() {
        return provincia;
    }

    public void setProvincia(Localidad provincia) {
        this.provincia = provincia;
    }

    public Localidad getCanton() {
        return canton;
    }

    public void setCanton(Localidad canton) {
        this.canton = canton;
    }

    public Localidad getParroquia() {
        return parroquia;
    }

    public void setParroquia(Localidad parroquia) {
        this.parroquia = parroquia;
    }

    public void seleccionarConcesion() {
//        registroPagoObligacionesAutoGestion.setCodigoConcesion(concesionMineraPopup);
//        registroPagoObligacionesAutoGestion.getCodigoConcesion();
//        registroPagoObligacionesAutoGestion.setNombrePersonaPago(concesionMineraPopup.getNombreConcesionarioPrincipal());
//        registroPagoObligacionesAutoGestion.setApellidoPersonaPago(concesionMineraPopup.getApellidoConcesionarioPrincipal());
//        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(concesionMineraPopup.getDocumentoConcesionarioPrincipal());
//        PersonaDto pDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(concesionMineraPopup.getDocumentoConcesionarioPrincipal());
//        if (pDto != null) {
//            registroPagoObligacionesAutoGestion.setNombrePersonaPago(pDto.getNombres());
//            registroPagoObligacionesAutoGestion.setApellidoPersonaPago(pDto.getApellidos());
//        }
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(concesionMineraPopup.getCodigoConcesion());
        derechoMineroDto.setCodigo(concesionMineraPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(concesionMineraPopup.getCodigoTipoMineria().getNombreTipoMineria());
        derechoMineroDto.setCodigoTipoSolicitud(concesionMineraPopup.getCodigoTipoMineria().getCodigoTipoMineria());
        System.out.println("valorPagoDerechoMinero: " + valorPagoDerechoMinero);
        derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public void seleccionarLicencia() {
//        registroPagoObligacionesAutoGestion.setCodigoLicenciaComercializacion(licenciaComercializacionPopup);
//        registroPagoObligacionesAutoGestion.setNombrePersonaPago(licenciaComercializacionPopup.getNombre());
//        registroPagoObligacionesAutoGestion.setApellidoPersonaPago(licenciaComercializacionPopup.getApellido());
//        registroPagoObligacionesAutoGestion.getCodigoLicenciaComercializacion();
//        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(licenciaComercializacionPopup.getNumeroDocumento());
//        PersonaDto pDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(licenciaComercializacionPopup.getNumeroDocumento());
//        if (pDto != null) {
//            registroPagoObligacionesAutoGestion.setNombrePersonaPago(pDto.getNombres());
//            registroPagoObligacionesAutoGestion.setApellidoPersonaPago(pDto.getApellidos());
//        }
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(licenciaComercializacionPopup.getCodigoLicenciaComercializacion());
        derechoMineroDto.setCodigo(licenciaComercializacionPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getDescripcion());
        derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo());
        derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public void seleccionarPlanta() {
//        registroPagoObligacionesAutoGestion.setCodigoPlantaBeneficio(plantaBeneficioPopup);
//        registroPagoObligacionesAutoGestion.setNombrePersonaPago(plantaBeneficioPopup.getNombreRepresentanteLegal());
//        registroPagoObligacionesAutoGestion.setApellidoPersonaPago(plantaBeneficioPopup.getApellidoRepresentanteLegal());
//        registroPagoObligacionesAutoGestion.getCodigoPlantaBeneficio();
//        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(plantaBeneficioPopup.getNumeroDocumentoRepresentanteLegal());
//        PersonaDto pDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(plantaBeneficioPopup.getNumeroDocumentoRepresentanteLegal());
//        if (pDto != null) {
//            registroPagoObligacionesAutoGestion.setNombrePersonaPago(pDto.getNombres());
//            registroPagoObligacionesAutoGestion.setApellidoPersonaPago(pDto.getApellidos());
//        }
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(plantaBeneficioPopup.getCodigoPlantaBeneficio());
        derechoMineroDto.setCodigo(plantaBeneficioPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion());
        derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo());
        derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public SujetoMinero getSujetoMineroPopUp() {
        return sujetoMineroPopUp;
    }

    public void setSujetoMineroPopUp(SujetoMinero sujetoMineroPopUp) {
        this.sujetoMineroPopUp = sujetoMineroPopUp;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public ConcesionMinera getConcesionMineraPopupAnterior() {
        return concesionMineraPopupAnterior;
    }

    public void setConcesionMineraPopupAnterior(ConcesionMinera concesionMineraPopupAnterior) {
        this.concesionMineraPopupAnterior = concesionMineraPopupAnterior;
    }

    public LicenciaComercializacion getLicenciaComercializacionPopupAnterior() {
        return licenciaComercializacionPopupAnterior;
    }

    public void setLicenciaComercializacionPopupAnterior(LicenciaComercializacion licenciaComercializacionPopupAnterior) {
        this.licenciaComercializacionPopupAnterior = licenciaComercializacionPopupAnterior;
    }

    public PlantaBeneficio getPlantaBeneficioPopupAnterior() {
        return plantaBeneficioPopupAnterior;
    }

    public void setPlantaBeneficioPopupAnterior(PlantaBeneficio plantaBeneficioPopupAnterior) {
        this.plantaBeneficioPopupAnterior = plantaBeneficioPopupAnterior;
    }

    public String getIdentificacionSujetoMinero() {
        return identificacionSujetoMinero;
    }

    public void setIdentificacionSujetoMinero(String identificacionSujetoMinero) {
        this.identificacionSujetoMinero = identificacionSujetoMinero;
    }

    public void buscarSujetoMinero() {
        sujetoMineroPopUp = sujetoMineroServicio.obtenerPorIdentificacion(identificacionSujetoMinero);
        sujetoMineroPopUpAnterior = sujetoMineroServicio.obtenerPorIdentificacion(identificacionSujetoMinero);
        if (sujetoMineroPopUp == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sujeto minero no existe", null));
        } else {
            //registroPagoObligacionesAutoGestion.getCodigoSujetoMinero();
        }
    }

    public void seleccionarSujetoMinero() {
        registroPagoObligacionesAutoGestion.setCodigoSujetoMinero(sujetoMineroPopUp);
        registroPagoObligacionesAutoGestion.getCodigoSujetoMinero();
        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(sujetoMineroPopUp.getNumeroDocumento());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqSujMin').hide()");
    }

    public SujetoMinero getSujetoMineroPopUpAnterior() {
        return sujetoMineroPopUpAnterior;
    }

    public void setSujetoMineroPopUpAnterior(SujetoMinero sujetoMineroPopUpAnterior) {
        this.sujetoMineroPopUpAnterior = sujetoMineroPopUpAnterior;
    }

    protected String formarCodigoComprobante(String prefijoComprobante, Long secuencial) {
        //prefijoComprobante es el prefijo de la regional
        String codigo = secuencial.toString();
        while (codigo.length() < 8) {
            codigo = "0" + codigo;
        }
        codigo = "A-" + prefijoComprobante + codigo;
        return codigo;
    }

    public List<RegistroPagoObligaciones> getRegistrosPRUNacional() {
        if (registrosPRUNacional == null) {
            registrosPRUNacional = registroPagoObligacionesServicio.obtenerPRUNacional();
        }
        return registrosPRUNacional;
    }

    public void setRegistrosPRUNacional(List<RegistroPagoObligaciones> registrosPRUNacional) {
        this.registrosPRUNacional = registrosPRUNacional;
    }

    public List<SelectItem> getTipoServicios() {
        if (tipoServicios == null) {
            tipoServicios = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("LISTSERV");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tipoServicios.add(new SelectItem(catDet.getCodigoCatalogoDetalle(), catDet.getNombre()));
                }
            }

        }
        return tipoServicios;
    }

    public void setTipoServicios(List<SelectItem> tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public List<SelectItem> getConceptosPago() {
        if (conceptosPago == null) {
            conceptosPago = new ArrayList<>();
            if (registroPagoObligacionesAutoGestion.getCodigoTipoServicio() != null
                    && registroPagoObligacionesAutoGestion.getCodigoTipoServicio().getCodigoCatalogoDetalle() != null) {
                List<CostoServicios> costoServicios = costoServiciosServicio
                        .obtenerPorTipoServicio(registroPagoObligacionesAutoGestion.getCodigoTipoServicio().getCodigoCatalogoDetalle());
                if (costoServicios != null) {
                    for (CostoServicios costoServicio : costoServicios) {
                        conceptosPago.add(new SelectItem(costoServicio.getCodigoConceptoPago().getCodigoConceptoPago(),
                                costoServicio.getCodigoConceptoPago().getDescripcionConceptoPago()));
                    }
                }

            }
        }
        return conceptosPago;
    }

    public void setConceptosPago(List<SelectItem> conceptosPago) {
        this.conceptosPago = conceptosPago;
    }

    public void cargarConceptosPago() {
        conceptosPago = null;
        getConceptosPago();
        registroPagoObligacionesAutoGestion.setValorReferenciaEntregaImpresa(null);
    }

    public void obtenerValorConceptoPago() {
        if (registroPagoObligacionesAutoGestion.getCodigoConceptoPago() != null
                && registroPagoObligacionesAutoGestion.getCodigoConceptoPago().getCodigoConceptoPago() != null) {
            CostoServicios costoServicios = costoServiciosServicio
                    .obtenerPorCodigoConceptoPago(registroPagoObligacionesAutoGestion.getCodigoConceptoPago().getCodigoConceptoPago());
            registroPagoObligacionesAutoGestion.setValorReferenciaEntregaImpresa(costoServicios.getValorReferenciaEntregaImpresa());
        }
    }

    public boolean isGeneracionComprobante() {
        return generacionComprobante;
    }

    public void setGeneracionComprobante(boolean generacionComprobante) {
        this.generacionComprobante = generacionComprobante;
    }

    public void generarComprobanteArcom() {
        if (generacionComprobante) {
            generarCodigoComprobante();
        } else {
            registroPagoObligacionesAutoGestion.setNumeroComprobanteArcom(null);
        }
    }

    public boolean isAplicaCantidad() {
        return aplicaCantidad;
    }

    public void setAplicaCantidad(boolean aplicaCantidad) {
        this.aplicaCantidad = aplicaCantidad;
    }

    public Date getFechaDesdeFiltro() {
        return fechaDesdeFiltro;
    }

    public void setFechaDesdeFiltro(Date fechaDesdeFiltro) {
        this.fechaDesdeFiltro = fechaDesdeFiltro;
    }

    public Date getFechaHastaFiltro() {
        return fechaHastaFiltro;
    }

    public void setFechaHastaFiltro(Date fechaHastaFiltro) {
        this.fechaHastaFiltro = fechaHastaFiltro;
    }

    public String getNumeroComprobanteArcomFiltro() {
        return numeroComprobanteArcomFiltro;
    }

    public void setNumeroComprobanteArcomFiltro(String numeroComprobanteArcomFiltro) {
        this.numeroComprobanteArcomFiltro = numeroComprobanteArcomFiltro;
    }

    public String getCodigoArcomFiltro() {
        return codigoArcomFiltro;
    }

    public void setCodigoArcomFiltro(String codigoArcomFiltro) {
        this.codigoArcomFiltro = codigoArcomFiltro;
    }

    public void descargaPDF() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        UsuarioRol usRol = usuarioRolServicio.obtenerPorCodigoUsuuario(us.getCodigoUsuario());
        RegistroPagoObligaciones registroPagoObligacionesItem = (RegistroPagoObligaciones) getExternalContext().getRequestMap().get("reg");
        if (registroPagoObligacionesItem.getCodigoTipoRegistro() != null) {
            if (registroPagoObligacionesItem.getCodigoTipoRegistro().equals(ConstantesEnum.SUJETO_MINERO.getCodigo())
                    || registroPagoObligacionesItem.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO.getCodigo())) {
                urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                        + "/birt/frameset?__report=report/ComprobatesPago/Comprobante-estandar.rptdesign&codigo_registro="
                        + registroPagoObligacionesItem.getCodigoRegistro() + "&nombre_funcionario=" + us.getNombresCompletos()
                        + "&cargo_funcionario=" + usRol.getRol().getDescripcion() + "&__format=pdf";
            } else {
                urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                        + "/birt/frameset?__report=report/ComprobatesPago/Respuesta-solicitud.rptdesign&codigo_registro="
                        + registroPagoObligacionesItem.getCodigoRegistro() + "&nombre_funcionario=" + us.getNombresCompletos()
                        + "&cargo_funcionario=" + usRol.getRol().getDescripcion() + "&__format=pdf";
            }
        }
        // nombre_funcionario cargo_funcionario
        /*if (registroPagoObligacionesItem.getComprobanteElectronico() != null) {
         urlReporte = urlBase + "/birt/frameset?__report=report/comprobante-ingreso-recaudacion/comprobante-ingreso-recaudacion.rptdesign&numero_comprobante=" + registroPagoObligacionesItem.getComprobanteElectronico() + "&nombre_funcionario=" + usuarioLogin.getNombreCompleto() + "&cargo_funcionario=" + descripcionUsuarioRol + "&__format=pdf";
         }*/
        System.out.println("URL del Comprobante: " + this.urlReporte);
    }

    public String getUrlReporte() {
        return urlReporte;
    }

    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia.getCodigoLocalidad().toString(), provincia.getNombre().toUpperCase()));
            }
        }
        return provincias;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public void resetearCodigoDerechoMinero() {

    }

    public void buscarPersona() {
        personaDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(numIdentificacionBusqueda);
        if (personaDto == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No existe persona", null));
            return;
        }
    }

    public void seleccionarPersona() {
        registroPagoObligacionesAutoGestion.setDocumentoPersonaPago(personaDto.getIdentificacion());
        registroPagoObligacionesAutoGestion.setNombrePersonaPago(personaDto.getNombres());
        registroPagoObligacionesAutoGestion.setApellidoPersonaPago(personaDto.getApellidos());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqPersona').hide()");
    }

    public String getNumIdentificacionBusqueda() {
        return numIdentificacionBusqueda;
    }

    public void setNumIdentificacionBusqueda(String numIdentificacionBusqueda) {
        this.numIdentificacionBusqueda = numIdentificacionBusqueda;
    }

    public PersonaDto getPersonaDto() {
        return personaDto;
    }

    public void setPersonaDto(PersonaDto personaDto) {
        this.personaDto = personaDto;
    }

    public void agregarDerechoMinero(DerechoMineroDto derechoMineroDto) {
        if (derechosMineros == null) {
            derechosMineros = new ArrayList<>();
        }
        derechosMineros.add(derechoMineroDto);
    }

    public void eliminarDerechoMinero() {
        DerechoMineroDto derechoMineraItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        System.out.println("derechoMineraItem: " + derechoMineraItem.getCodigo());
        derechosMineros.remove(derechoMineraItem);
    }

    public List<DerechoMineroDto> getDerechosMineros() {
        return derechosMineros;
    }

    public void setDerechosMineros(List<DerechoMineroDto> derechosMineros) {
        this.derechosMineros = derechosMineros;
    }

    public BigDecimal getValorPagoDerechoMinero() {
        return valorPagoDerechoMinero;
    }

    public void setValorPagoDerechoMinero(BigDecimal valorPagoDerechoMinero) {
        this.valorPagoDerechoMinero = valorPagoDerechoMinero;
    }

}
