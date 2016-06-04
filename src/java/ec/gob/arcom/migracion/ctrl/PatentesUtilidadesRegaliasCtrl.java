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
import ec.gob.arcom.migracion.dto.RegistroPagoObligacionesDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.ParametroSistema;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.RegistroPagoObligacionesServicio;
import ec.gob.arcom.migracion.servicio.SujetoMineroServicio;
import ec.gob.arcom.migracion.servicio.ParametroSistemaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class PatentesUtilidadesRegaliasCtrl extends BaseCtrl {

    @EJB
    private RegistroPagoObligacionesServicio registroPagoObligacionesServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private ParametroSistemaServicio parametroSistemaServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
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
    private LocalidadRegionalServicio localidadRegionalServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    private RegistroPagoObligaciones patentesRegaliasUtilidades;
    private RegistroPagoObligaciones patentesRegaliasUtilidadesAnterior;
    private List<RegistroPagoObligacionesDto> listaPatentesRegaliasUtilidades;
    private String codigoFiltro;
    private Boolean usuarioEconomico;

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

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;
    
    private List<SelectItem> periodosList;
    private List<SelectItem> tipoSolicitudList;
    private List<DerechoMineroDto> derechosMineros;

    private BigInteger codigoImpuestoPatente;
    private BigInteger codigoImpuestoUtilidad;
    private BigInteger codigoImpuestoRegalia;
    private BigInteger numeroFormularioPatente;
    private BigInteger numeroFormularioUtilidad;
    private BigInteger numeroFormularioRegalia;
    
    private String nemonicoPatenteUtilidadRegalia;
    private String comprobanteElectronicoFiltro;
    private String documentoPersonaPagoFiltro;
    private String codigoArcomFiltro;
    private String urlReporte;
    
    @PostConstruct
    public void init() {
        try {
            System.out.println("ENTRA AL POST CONSTRUCT PATENTESUTILIDADESREGALIAS");
            codigoImpuestoPatente = new BigInteger(parametroSistemaServicio.findByNemonico("CODIMPPATENTE").getValorParametro());
            codigoImpuestoUtilidad = new BigInteger(parametroSistemaServicio.findByNemonico("CODIMPUTILIDAD").getValorParametro());
            codigoImpuestoRegalia = new BigInteger(parametroSistemaServicio.findByNemonico("CODIMPREGALIA").getValorParametro());
            numeroFormularioPatente = new BigInteger(parametroSistemaServicio.findByNemonico("NUMFORPATENTE").getValorParametro());
            numeroFormularioUtilidad = new BigInteger(parametroSistemaServicio.findByNemonico("NUMFORUTILIDAD").getValorParametro());
            numeroFormularioRegalia = new BigInteger(parametroSistemaServicio.findByNemonico("NUMFORREGALIA").getValorParametro());

            /*String userName = "1104212624001";
            if(login == null){                
                //Usuario uBd = usuarioDao.obtenerPorLogin(userName);
                login = new LoginCtrl();
                login.setUserName(userName);
                System.out.println("UserName:"+login.getUserName());
            }else{
                login.setUserName(userName);
                System.out.println("UserName:"+login.getUserName());
            }*/
            
            Usuario uBd = usuarioDao.obtenerPorLogin(login.getUserName());
            if (uBd != null) {
                if (uBd.getCampoReservado01() != null && (uBd.getCampoReservado01().equals("UE") || uBd.getCampoReservado01().equals("UEN"))) {
                    usuarioEconomico = true;
                }else{
                    usuarioEconomico = false;
                }
            }   
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public RegistroPagoObligaciones getPatentesRegaliasUtilidades() {
        if (patentesRegaliasUtilidades == null) {
            String registroPagoObligacionesId = getHttpServletRequestParam("idItem");
            Long idRegistroPagoObligaciones = null;
            if (registroPagoObligacionesId != null) {
                idRegistroPagoObligaciones = Long.parseLong(registroPagoObligacionesId);
            }
            if (idRegistroPagoObligaciones == null) {
                patentesRegaliasUtilidades = new RegistroPagoObligaciones();
                //patentesRegaliasUtilidades.setCodigoConceptoPago(new ConceptoPago());
                //patentesRegaliasUtilidades.setCodigoBanco(new CatalogoDetalle());
                patentesRegaliasUtilidades.setTipoPago(new CatalogoDetalle());
                patentesRegaliasUtilidades.setCodigoPeriodo(new ParametroSistema());
                patentesRegaliasUtilidades.setNumeroFormularioPago(new ParametroSistema());
            } else {
                patentesRegaliasUtilidades = registroPagoObligacionesServicio.obtenerPorCodigoRegistroPagoObligaciones(idRegistroPagoObligaciones);
                patentesRegaliasUtilidadesAnterior = registroPagoObligacionesServicio.obtenerPorCodigoRegistroPagoObligaciones(idRegistroPagoObligaciones);
                patentesRegaliasUtilidades.getCodigoConcesion();
                patentesRegaliasUtilidades.getCodigoLicenciaComercializacion();
                patentesRegaliasUtilidades.getCodigoPlantaBeneficio();
                if (patentesRegaliasUtilidades.getTipoPago() == null) {
                    patentesRegaliasUtilidades.setTipoPago(new CatalogoDetalle());
                }
            }
        }
        return patentesRegaliasUtilidades;
    }

    public void setPatentesRegaliasUtilidades(RegistroPagoObligaciones patentesRegaliasUtilidades) {
        this.patentesRegaliasUtilidades = patentesRegaliasUtilidades;
    }

    public RegistroPagoObligaciones getPatentesRegaliasUtilidadesAnterior() {
        return patentesRegaliasUtilidadesAnterior;
    }

    public void setPatentesRegaliasUtilidadesAnterior(RegistroPagoObligaciones patentesRegaliasUtilidadesAnterior) {
        this.patentesRegaliasUtilidadesAnterior = patentesRegaliasUtilidadesAnterior;
    }

    public String editarRegistro() {
        RegistroPagoObligacionesDto registroPagoObligacionesItem = (RegistroPagoObligacionesDto) getExternalContext().getRequestMap().get("reg");
        return "patentesUtilidadesRegaliasForm?faces-redirect=true&idItem=" + registroPagoObligacionesItem.getCodigoRegistro();
    }

    public String guardarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            CatalogoDetalle cd = new CatalogoDetalle();
            cd.setCodigoCatalogoDetalle(574L);
            patentesRegaliasUtilidades.setEstadoPago(cd);
            
            if(patentesRegaliasUtilidades.getEntidadTramite() == null)
                patentesRegaliasUtilidades.setEntidadTramite("REGISTRO_PAGO_OBLIGACIONES");
            
            if (derechosMineros == null || derechosMineros.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Ingrese al menos un derecho minero: ", null));
                return null;
            }

            patentesRegaliasUtilidades.setCodigoConcesion(null);
            patentesRegaliasUtilidades.setCodigoLicenciaComercializacion(null);
            patentesRegaliasUtilidades.setCodigoPlantaBeneficio(null);
            patentesRegaliasUtilidades.setCodigoSujetoMinero(null);
            patentesRegaliasUtilidades.setCodigoTipoRegistro(null);
           
            if (patentesRegaliasUtilidades.getCodigoRegistro() == null) {
                System.out.println("entra create");
                patentesRegaliasUtilidades.setEstadoRegistro(true);
                patentesRegaliasUtilidades.setFechaCreacion(new Date());
                patentesRegaliasUtilidades.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                System.out.println("patentesRegaliasUtilidades.getCodigoDerechoMinero(): " + patentesRegaliasUtilidades.getCodigoDerechoMinero());
                //registroPagoObligacionesServicio.create(patentesRegaliasUtilidades);
                registroPagoObligacionesServicio.guardarTodo(patentesRegaliasUtilidades, derechosMineros, us.getCodigoUsuario());
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(patentesRegaliasUtilidades.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito", null));
            } else {
                System.out.println("entra update");
                patentesRegaliasUtilidades.setFechaModificacion(new Date());
                patentesRegaliasUtilidades.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                registroPagoObligacionesServicio.actualizarRegistroPagoObligaciones(patentesRegaliasUtilidades);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(patentesRegaliasUtilidadesAnterior.toString());
                auditoria.setDetalleCambios(patentesRegaliasUtilidades.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro actualizado con éxito", null));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
        return "patentesUtilidadesRegalias";
    }

    public void buscar() {
        listaPatentesRegaliasUtilidades = null;
        getListaPatentesRegaliasUtilidades();
    }
    
    public List<RegistroPagoObligacionesDto> getListaPatentesRegaliasUtilidades() {
        if (listaPatentesRegaliasUtilidades == null) {
            //listaPatentesRegaliasUtilidades = registroPagoObligacionesServicio.findAll();
            Date fechaDesdeFiltro = null;
            Date fechaHastaFiltro = null;            
            listaPatentesRegaliasUtilidades = registroPagoObligacionesServicio
                    .obtenerRegistrosPatUtiReg(fechaDesdeFiltro, fechaHastaFiltro, comprobanteElectronicoFiltro, documentoPersonaPagoFiltro, getCodigoArcomFiltro(), null);
        }
        return listaPatentesRegaliasUtilidades;
    }

    public void descargaPDF() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        UsuarioRol usRol = usuarioRolServicio.obtenerPorCodigoUsuuario(us.getCodigoUsuario());
        RegistroPagoObligacionesDto registroPagoObligacionesItem = (RegistroPagoObligacionesDto) getExternalContext().getRequestMap().get("reg");
        //if (registroPagoObligacionesItem.getCodigoTipoRegistro() != null) {
                setUrlReporte(ConstantesEnum.URL_BASE_DESARROLLO.getDescripcion()
                        + "/birt/frameset?__report=report/ComprobatesPago/Patentes-utilidades-regalias.rptdesign&codigo_registro="
                        + registroPagoObligacionesItem.getCodigoRegistro() + "&nombre_funcionario=" + us.getNombresCompletos()
                        + "&cargo_funcionario=" + usRol.getRol().getDescripcion() + "&__format=pdf");
        //}
        System.out.println("URL del Comprobante: " + this.getUrlReporte());
    }
    
    public void setListaPatentesRegaliasUtilidades(List<RegistroPagoObligacionesDto> listaPatentesRegaliasUtilidades) {
        this.listaPatentesRegaliasUtilidades = listaPatentesRegaliasUtilidades;
    }

    public void buscarRegistro() {
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        if (patentesRegaliasUtilidades.getCodigoTipoRegistro() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Antes de buscar por código debe elegir un tipo de registro", null));
            return;
        }
        if (patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            System.out.println("codigoFiltro: " + codigoFiltro);
            concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            concesionMineraPopupAnterior = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (concesionMineraPopup != null) {
                /*if (localidadRegionalConcesion.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {*/
                    provincia = localidadServicio.findByPk(concesionMineraPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(concesionMineraPopup.getCodigoCanton().longValue());
                    parroquia = localidadServicio.findByPk(concesionMineraPopup.getCodigoParroquia().longValue());
                /*} else {
                    concesionMineraPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La concesión existe pero no pertenece a su regional", null));
                }*/
            }
        } else if (patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
            licenciaComercializacionPopup = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            licenciaComercializacionPopupAnterior = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (licenciaComercializacionPopup != null) {
                LocalidadRegional localidadRegionalLicencia = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(licenciaComercializacionPopup.getCodigoProvincia().longValue());
                LocalidadRegional localidadRegionalUsuario = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(us.getCodigoProvincia().longValue());
                /*if (localidadRegionalLicencia.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {*/
                    provincia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoCanton().longValue());
                    if (licenciaComercializacionPopup.getCodigoParroquida() != null) {
                        parroquia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoParroquida().longValue());
                    } else {
                        parroquia = new Localidad();
                    }
                /*} else {
                    licenciaComercializacionPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La licencia existe pero no pertenece a su regional", null));
                }*/
            }
        } else if (patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
            plantaBeneficioPopup = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            plantaBeneficioPopupAnterior = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (plantaBeneficioPopup != null) {
                LocalidadRegional localidadRegionalPlanta = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(plantaBeneficioPopup.getCodigoProvincia().longValue());
                LocalidadRegional localidadRegionalUsuario = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(us.getCodigoProvincia().longValue());
                /*if (localidadRegionalPlanta.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {*/
                    provincia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoCanton().longValue());
                    if (plantaBeneficioPopup.getCodigoParroquida() != null) {
                        parroquia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoParroquida().longValue());
                    } else {
                        parroquia = new Localidad();
                    }
                /*} else {
                    plantaBeneficioPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La planta existe pero no pertenece a su regional", null));
                }*/
            }
        }
    }

    public void eventChangeTipoRegistro() { 
        //Se resetea los campos para volver a buscar el Derecho Minero
        patentesRegaliasUtilidades.setCodigoDerechoMinero(null);
        derechosMineros = null;
        
        if (patentesRegaliasUtilidades.getCodigoTipoRegistro() != null) {
            if (patentesRegaliasUtilidades.getCodigoTipoRegistro().equals(ConstantesEnum.SUJETO_MINERO.getCodigo())) {
                sujetoMinero = true;
            } else {
                sujetoMinero = false;
            }
        }
    }

    public void cargarPopupBusqueda() { 
        codigoFiltro = null;
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }
    
    public void eventChangeTipoPago() {
        //Se resetea los campos para volver a buscar el Derecho Minero
        patentesRegaliasUtilidades.setCodigoDerechoMinero(null);
        derechosMineros = null;
        
        patentesRegaliasUtilidades.setCodigoDerechoMinero(null);
        if (patentesRegaliasUtilidades.getTipoPago()!= null) {
            //Patente
            if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_PATENTE.getCodigo())){
                patentesRegaliasUtilidades.setCodigoImpuesto(codigoImpuestoPatente);
                patentesRegaliasUtilidades.setNumeroFormulario(numeroFormularioPatente);
                
                this.nemonicoPatenteUtilidadRegalia = "PERPAT";
                periodosList = null;
                tipoSolicitudList = null;
                this.getPeriodosList();
                this.getTipoSolicitudList();
                //patentesRegaliasUtilidades.setc(patentesRegaliasUtilidades.getPeriodosPatente());
            } //Regalia
            else if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_REGALIA.getCodigo())){
                patentesRegaliasUtilidades.setCodigoImpuesto(codigoImpuestoRegalia);
                patentesRegaliasUtilidades.setNumeroFormulario(numeroFormularioRegalia);
                
                this.nemonicoPatenteUtilidadRegalia = "PERREG";
                periodosList = null;
                tipoSolicitudList = null;
                this.getPeriodosList();
                this.getTipoSolicitudList();
                //patentesRegaliasUtilidades.setListaPeriodo(patentesRegaliasUtilidades.getPeriodosRegalia());
            } //Utilidad
            else if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_UTILIDAD.getCodigo())){
                patentesRegaliasUtilidades.setCodigoImpuesto(codigoImpuestoUtilidad);
                patentesRegaliasUtilidades.setNumeroFormulario(numeroFormularioUtilidad);
                
                this.nemonicoPatenteUtilidadRegalia = "PERUTIL";
                periodosList = null;
                tipoSolicitudList = null;
                this.getPeriodosList();
                this.getTipoSolicitudList();
                //patentesRegaliasUtilidades.setListaPeriodo(patentesRegaliasUtilidades.getPeriodosUtilidad());
            }
        }
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
        derechosMineros = null;
        patentesRegaliasUtilidades.setCodigoConcesion(concesionMineraPopup);
        patentesRegaliasUtilidades.getCodigoConcesion();
              
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(concesionMineraPopup.getCodigoConcesion());
        derechoMineroDto.setCodigo(concesionMineraPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(concesionMineraPopup.getCodigoTipoMineria().getNombreTipoMineria());
        derechoMineroDto.setCodigoTipoSolicitud(concesionMineraPopup.getCodigoTipoMineria().getCodigoTipoMineria());
        //-->System.out.println("valorPagoDerechoMinero: " + valorPagoDerechoMinero);
        //-->derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public void seleccionarLicencia() {
        derechosMineros = null;
        patentesRegaliasUtilidades.setCodigoLicenciaComercializacion(licenciaComercializacionPopup);
        patentesRegaliasUtilidades.getCodigoLicenciaComercializacion();
        
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(licenciaComercializacionPopup.getCodigoLicenciaComercializacion());
        derechoMineroDto.setCodigo(licenciaComercializacionPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getDescripcion());
        derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo());
        //-->derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public void seleccionarPlanta() {
        derechosMineros = null;
        patentesRegaliasUtilidades.setCodigoPlantaBeneficio(plantaBeneficioPopup);
        patentesRegaliasUtilidades.getCodigoPlantaBeneficio();
        
        DerechoMineroDto derechoMineroDto = new DerechoMineroDto();
        derechoMineroDto.setId(plantaBeneficioPopup.getCodigoPlantaBeneficio());
        derechoMineroDto.setCodigo(plantaBeneficioPopup.getCodigoArcom());
        derechoMineroDto.setTipoDerechoMinero(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion());
        derechoMineroDto.setCodigoTipoSolicitud(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo());
        //-->derechoMineroDto.setValorPagoDerechoMinero(valorPagoDerechoMinero);
        agregarDerechoMinero(derechoMineroDto);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
        licenciaComercializacionPopup = null;
        plantaBeneficioPopup = null;
    }

    public void agregarDerechoMinero(DerechoMineroDto derechoMineroDto) {
        if (derechosMineros == null) {
            derechosMineros = new ArrayList<>();
        }
        derechosMineros.add(derechoMineroDto);
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
            patentesRegaliasUtilidades.getCodigoSujetoMinero();
        }
    }

    public SujetoMinero getSujetoMineroPopUpAnterior() {
        return sujetoMineroPopUpAnterior;
    }

    public void setSujetoMineroPopUpAnterior(SujetoMinero sujetoMineroPopUpAnterior) {
        this.sujetoMineroPopUpAnterior = sujetoMineroPopUpAnterior;
    }

    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia.getCodigoLocalidad(), provincia.getNombre().toUpperCase()));
            }
            cantones = null;
            parroquias = null;
        }
        return provincias;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public List<SelectItem> getCantones() {
        if (cantones == null || cantones.isEmpty()) {
            cantones = new ArrayList<>();
            if (patentesRegaliasUtilidades.getCodigoProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(patentesRegaliasUtilidades.getCodigoProvincia().toString()));
            if (catalogoCanton == null || (catalogoCanton != null && catalogoCanton.getCodigoLocalidad() == null)) {
                return cantones;
            }
            List<Localidad> cantonCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoCanton.getCodigoLocalidad()));

            for (Localidad canton : cantonCat) {
                cantones.add(new SelectItem(canton.getCodigoLocalidad().toString(), canton.getNombre().toUpperCase()));
            }
        }
        return cantones;
    }

    public void setCantones(List<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        patentesRegaliasUtilidades.setCodigoCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null || parroquias.isEmpty()) {
            parroquias = new ArrayList<>();
            if (patentesRegaliasUtilidades.getCodigoCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(patentesRegaliasUtilidades.getCodigoCanton().toString()));
            if (catalogoParroquia == null || (catalogoParroquia != null && catalogoParroquia.getCodigoInternacional() == null)) {
                return parroquias;
            }
            List<Localidad> parroquiaCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoParroquia.getCodigoLocalidad()));

            for (Localidad parroquia : parroquiaCat) {
                parroquias.add(new SelectItem(parroquia.getCodigoLocalidad().toString(), parroquia.getNombre().toUpperCase()));
            }
        }
        return parroquias;
    }

    public void setParroquias(List<SelectItem> parroquias) {
        this.parroquias = parroquias;
    }

    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }
    
    /**
     * @return the periodosList
     */
    public List<SelectItem> getPeriodosList() {
        if (periodosList == null) {
            periodosList = new ArrayList<>();
            List<ParametroSistema> parSistList = parametroSistemaServicio.findByNemonicoLike(nemonicoPatenteUtilidadRegalia);
            for (ParametroSistema parSist : parSistList) {
                periodosList.add(new SelectItem(parSist.getCodigoParametro(), parSist.getValorParametro() + " " +parSist.getDescripcionParametro()));
            }
            
        }
        return periodosList;
    }

    /**
     * @param periodosList the periodosList to set
     */
    public void setPeriodosList(List<SelectItem> periodosList) {
        this.periodosList = periodosList;
    }

    /**
     * @return the comprobanteElectronicoFiltro
     */
    public String getComprobanteElectronicoFiltro() {
        return comprobanteElectronicoFiltro;
    }

    /**
     * @param comprobanteElectronicoFiltro the comprobanteElectronicoFiltro to set
     */
    public void setComprobanteElectronicoFiltro(String comprobanteElectronicoFiltro) {
        this.comprobanteElectronicoFiltro = comprobanteElectronicoFiltro;
    }

    /**
     * @return the documentoPersonaPagoFiltro
     */
    public String getDocumentoPersonaPagoFiltro() {
        return documentoPersonaPagoFiltro;
    }

    /**
     * @param documentoPersonaPagoFiltro the documentoPersonaPagoFiltro to set
     */
    public void setDocumentoPersonaPagoFiltro(String documentoPersonaPagoFiltro) {
        this.documentoPersonaPagoFiltro = documentoPersonaPagoFiltro;
    }

    /**
     * @return the urlReporte
     */
    public String getUrlReporte() {
        return urlReporte;
    }

    /**
     * @param urlReporte the urlReporte to set
     */
    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

    /**
     * @return the tipoSolicitudList
     */
    public List<SelectItem> getTipoSolicitudList() {
        if (tipoSolicitudList == null) {
            tipoSolicitudList = new ArrayList<>();
            if (patentesRegaliasUtilidades != null && patentesRegaliasUtilidades.getTipoPago()!= null && patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle() != null) {
                //Patente
                if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_PATENTE.getCodigo())) {
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getDescripcion()));
                } //Regalia
                else if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_REGALIA.getCodigo())) {
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getDescripcion()));
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion()));
                } //Utilidad
                else if (patentesRegaliasUtilidades.getTipoPago().getCodigoCatalogoDetalle().equals(ConstantesEnum.PATUTIREG_UTILIDAD.getCodigo())) {
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getDescripcion()));
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getDescripcion()));
                    tipoSolicitudList.add(new SelectItem(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo(), ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getDescripcion()));
                }
            } else {
                for (ConstantesEnum ce : ConstantesEnum.tipoSolicitudes()) {
                    tipoSolicitudList.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
            }
        }
        return tipoSolicitudList;
    }

    /**
     * @param tipoSolicitudList the tipoSolicitudList to set
     */
    public void setTipoSolicitudList(List<SelectItem> tipoSolicitudList) {
        this.tipoSolicitudList = tipoSolicitudList;
    }

    /**
     * @return the derechosMineros
     */
    public List<DerechoMineroDto> getDerechosMineros() {
        return derechosMineros;
    }

    /**
     * @param derechosMineros the derechosMineros to set
     */
    public void setDerechosMineros(List<DerechoMineroDto> derechosMineros) {
        this.derechosMineros = derechosMineros;
    }

    /**
     * @return the codigoArcomFiltro
     */
    public String getCodigoArcomFiltro() {
        return codigoArcomFiltro;
    }

    /**
     * @param codigoArcomFiltro the codigoArcomFiltro to set
     */
    public void setCodigoArcomFiltro(String codigoArcomFiltro) {
        this.codigoArcomFiltro = codigoArcomFiltro;
    }

    /**
     * @return the usuarioEconomico
     */
    public Boolean getUsuarioEconomico() {
        return usuarioEconomico;
    }

    /**
     * @param usuarioEconomico the usuarioEconomico to set
     */
    public void setUsuarioEconomico(Boolean usuarioEconomico) {
        this.usuarioEconomico = usuarioEconomico;
    }
}
