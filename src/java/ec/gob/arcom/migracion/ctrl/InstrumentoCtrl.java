/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Instrumento;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.InstrumentoServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.SujetoMineroServicio;
import ec.gob.arcom.migracion.util.CedulaValidator;
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
public class InstrumentoCtrl extends BaseCtrl {

    @EJB
    private InstrumentoServicio instrumentoServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private LicenciaComercializacionServicio licenciaComercializacionServicio;
    @EJB
    private PlantaBeneficioServicio plantaBeneficioServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private SujetoMineroServicio sujetoMineroServicio;
    @EJB
    private LocalidadRegionalServicio localidadRegionalServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private Instrumento instrumento;
    private List<Instrumento> listaRegistros;
    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;
    private Instrumento instrumentoAnterior;

    private String codigoFiltro;
    private ConcesionMinera concesionMineraPopup;
    private LicenciaComercializacion licenciaComercializacionPopup;
    private PlantaBeneficio plantaBeneficioPopup;
    private Localidad provincia;
    private Localidad canton;
    private Localidad parroquia;
    private boolean sujetoMinero;
    private SujetoMinero sujetoMineroPopUp;
    private String identificacionSujetoMinero;
    private ConcesionMinera concesionMineraPopupAnterior;
    private LicenciaComercializacion licenciaComercializacionPopupAnterior;
    private PlantaBeneficio plantaBeneficioPopupAnterior;
    private SujetoMinero sujetoMineroPopUpAnterior;

    public Instrumento getInstrumento() {
        if (instrumento == null) {
            String instrumentoId = getHttpServletRequestParam("idItem");
            Long idInstrumento = null;
            if (instrumentoId != null) {
                idInstrumento = Long.parseLong(instrumentoId);
            }
            if (idInstrumento == null) {
                instrumento = new Instrumento();
                instrumento.setClaseInscripcion(new CatalogoDetalle());
                //instrumento.setCodigoArea(new AreaMinera());
                instrumento.setCodigoTipoMineria(new TipoMineria());
                instrumento.setLibroInscripcion(new CatalogoDetalle());
            } else {
                instrumento = instrumentoServicio.obtenerPorCodigoInstrumento(idInstrumento);
                instrumentoAnterior = instrumentoServicio.obtenerPorCodigoInstrumento(idInstrumento);
                instrumento.getConcesionMinera();
                instrumento.getLicenciaComercializacion();
                instrumento.getPlantaBeneficio();
                if (instrumento.getClaseInscripcion() == null) {
                    instrumento.setClaseInscripcion(new CatalogoDetalle());
                }
//                if (instrumento.getCodigoArea() == null) {
//                    instrumento.setCodigoArea(new AreaMinera());
//                }
                if (instrumento.getCodigoTipoMineria() == null) {
                    instrumento.setCodigoTipoMineria(new TipoMineria());
                }
                if (instrumento.getLibroInscripcion() == null) {
                    instrumento.setLibroInscripcion(new CatalogoDetalle());
                }
                cambiarPopUp();
            }
        }
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    public String editarRegistro() {
        Instrumento instrumentoItem = (Instrumento) getExternalContext().getRequestMap().get("reg");
        return "instrumentoform?faces-redirect=true&idItem=" + instrumentoItem.getCodigoInstrumento();
    }

    public List<Instrumento> getListaRegistros() {
        if (listaRegistros == null) {
            listaRegistros = instrumentoServicio.findAll();
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<Instrumento> listaRegistros) {
        this.listaRegistros = listaRegistros;
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

    public List<SelectItem> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList<>();
            if (instrumento.getCodigoProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(instrumento.getCodigoProvincia().toString()));
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
        instrumento.setCodigoCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (instrumento.getCodigoCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(instrumento.getCodigoCanton().toString()));
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

    public String guardarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        if (instrumento.getConcesionMinera() != null) {
            if (concesionMineraPopup == null) {
                concesionMineraPopup = instrumento.getConcesionMinera();
                concesionMineraPopupAnterior = instrumento.getConcesionMinera();
            }
            concesionMineraPopup.setFechaOtorga(instrumento.getFechaOtorgamiento());
            concesionMineraPopup.setFechaInscribe(instrumento.getFechaInscripcion());
            concesionMineraPopup.setCodigoProvincia(instrumento.getCodigoProvincia());
            concesionMineraPopup.setCodigoCanton(instrumento.getCodigoCanton());
            concesionMineraPopup.setCodigoParroquia(instrumento.getCodigoParroquia());
        }
        if (instrumento.getLicenciaComercializacion() != null) {
            if (licenciaComercializacionPopup == null) {
                licenciaComercializacionPopup = instrumento.getLicenciaComercializacion();
                licenciaComercializacionPopupAnterior = instrumento.getLicenciaComercializacion();
            }
            licenciaComercializacionPopup.setFechaOtorga(instrumento.getFechaOtorgamiento());
            licenciaComercializacionPopup.setFechaInscribe(instrumento.getFechaInscripcion());
            licenciaComercializacionPopup.setCodigoProvincia(instrumento.getCodigoProvincia());
            licenciaComercializacionPopup.setCodigoCanton(instrumento.getCodigoCanton());
            licenciaComercializacionPopup.setCodigoParroquida(instrumento.getCodigoParroquia());
        }
        if (instrumento.getPlantaBeneficio() != null) {
            if (plantaBeneficioPopup == null) {
                plantaBeneficioPopup = instrumento.getPlantaBeneficio();
                plantaBeneficioPopupAnterior = instrumento.getPlantaBeneficio();
            }
            plantaBeneficioPopup.setFechaOtorga(instrumento.getFechaOtorgamiento());
            plantaBeneficioPopup.setFechaInscribe(instrumento.getFechaInscripcion());
            plantaBeneficioPopup.setCodigoProvincia(instrumento.getCodigoProvincia());
            plantaBeneficioPopup.setCodigoCanton(instrumento.getCodigoCanton());
            plantaBeneficioPopup.setCodigoParroquida(instrumento.getCodigoParroquia());
        }
        if (instrumento.getSujetoMinero() != null) {
            if (sujetoMineroPopUp == null) {
                sujetoMineroPopUp = instrumento.getSujetoMinero();
                sujetoMineroPopUpAnterior = instrumento.getSujetoMinero();
            }
            sujetoMineroPopUp.setNombreCompareciente(instrumento.getNombreCompareciente());
            sujetoMineroPopUp.setApellidoCompareciente(instrumento.getApellidoCompareciente());
            sujetoMineroPopUp.setNombreOtorgante(instrumento.getNombreOtorgante());
            sujetoMineroPopUp.setApellidoOtorgante(instrumento.getApellidoOtorgante());
            sujetoMineroPopUp.setFechaOtorgacionDerecho(instrumento.getFechaOtorgamiento());
            sujetoMineroPopUp.setFechaInscripcion(instrumento.getFechaInscripcion());
            sujetoMineroPopUp.setCodigoProvincia(instrumento.getCodigoProvincia());
            sujetoMineroPopUp.setCodigoCanton(instrumento.getCodigoCanton());
            sujetoMineroPopUp.setCodigoParroquia(instrumento.getCodigoParroquia());
        }
        try {
            if (instrumento.getCodigoInstrumento() == null) {
                System.out.println("entra guardar instrumento");
                System.out.println("datos actualizados?: " + instrumento.isDatosActualizados());
                instrumento.setFechaCreacion(new Date());
                instrumento.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                if (!instrumento.isDatosActualizados()) {
                    instrumentoServicio.create(instrumento);
                    Auditoria auditoria = new Auditoria();
                    auditoria.setAccion("INSERT");
                    auditoria.setDetalleAnterior(instrumento.toString());
                    auditoria.setDetalleCambios(null);
                    auditoria.setFecha(getCurrentTimeStamp());
                    auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoriaServicio.create(auditoria);
                } else {
                    instrumentoServicio.guardarTodo(instrumento, concesionMineraPopup, licenciaComercializacionPopup, plantaBeneficioPopup, sujetoMineroPopUp);
                    Auditoria auditoria1 = new Auditoria();
                    auditoria1.setAccion("UPDATE");
                    auditoria1.setDetalleAnterior(instrumentoAnterior.toString());
                    auditoria1.setDetalleCambios(instrumento.toString());
                    auditoria1.setFecha(getCurrentTimeStamp());
                    auditoria1.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoriaServicio.create(auditoria1);
                    if (instrumento.getConcesionMinera() != null) {
                        Auditoria auditoria2 = new Auditoria();
                        auditoria2.setAccion("UPDATE");
                        auditoria2.setDetalleAnterior(concesionMineraPopupAnterior.toString());
                        auditoria2.setDetalleCambios(concesionMineraPopup.toString());
                        auditoria2.setFecha(getCurrentTimeStamp());
                        auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria2);
                    }
                    if (instrumento.getLicenciaComercializacion() != null) {
                        Auditoria auditoria3 = new Auditoria();
                        auditoria3.setAccion("UPDATE");
                        auditoria3.setDetalleAnterior(licenciaComercializacionPopupAnterior.toString());
                        auditoria3.setDetalleCambios(licenciaComercializacionPopup.toString());
                        auditoria3.setFecha(getCurrentTimeStamp());
                        auditoria3.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria3);
                    }
                    if (instrumento.getPlantaBeneficio() != null) {
                        Auditoria auditoria4 = new Auditoria();
                        auditoria4.setAccion("UPDATE");
                        auditoria4.setDetalleAnterior(plantaBeneficioPopupAnterior.toString());
                        auditoria4.setDetalleCambios(plantaBeneficioPopup.toString());
                        auditoria4.setFecha(getCurrentTimeStamp());
                        auditoria4.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria4);
                    }
                    if (instrumento.getSujetoMinero() != null) {
                        Auditoria auditoria5 = new Auditoria();
                        auditoria5.setAccion("UPDATE");
                        auditoria5.setDetalleAnterior(plantaBeneficioPopupAnterior.toString());
                        auditoria5.setDetalleCambios(plantaBeneficioPopup.toString());
                        auditoria5.setFecha(getCurrentTimeStamp());
                        auditoria5.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria5);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito", null));
            } else {
                instrumento.setFechaModificacion(new Date());
                instrumento.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                System.out.println("entra actualizar instrumento");
                System.out.println("datos actualizados?: " + instrumento.isDatosActualizados());
                if (!instrumento.isDatosActualizados()) {
                    instrumentoServicio.actualizarInstrumento(instrumento);
                    Auditoria auditoria = new Auditoria();
                    auditoria.setAccion("UPDATE");
                    auditoria.setDetalleAnterior(instrumentoAnterior.toString());
                    auditoria.setDetalleCambios(instrumento.toString());
                    auditoria.setFecha(getCurrentTimeStamp());
                    auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoriaServicio.create(auditoria);
                } else {
                    instrumentoServicio.actualizarTodo(instrumento, concesionMineraPopup, licenciaComercializacionPopup, plantaBeneficioPopup, sujetoMineroPopUp);
                    Auditoria auditoria1 = new Auditoria();
                    auditoria1.setAccion("UPDATE");
                    auditoria1.setDetalleAnterior(instrumentoAnterior.toString());
                    auditoria1.setDetalleCambios(instrumento.toString());
                    auditoria1.setFecha(getCurrentTimeStamp());
                    auditoria1.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoriaServicio.create(auditoria1);
                    if (instrumento.getConcesionMinera() != null) {
                        Auditoria auditoria2 = new Auditoria();
                        auditoria2.setAccion("UPDATE");
                        auditoria2.setDetalleAnterior(concesionMineraPopupAnterior.toString());
                        auditoria2.setDetalleCambios(concesionMineraPopup.toString());
                        auditoria2.setFecha(getCurrentTimeStamp());
                        auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria2);
                    }
                    if (instrumento.getLicenciaComercializacion() != null) {
                        Auditoria auditoria3 = new Auditoria();
                        auditoria3.setAccion("UPDATE");
                        auditoria3.setDetalleAnterior(licenciaComercializacionPopupAnterior.toString());
                        auditoria3.setDetalleCambios(licenciaComercializacionPopup.toString());
                        auditoria3.setFecha(getCurrentTimeStamp());
                        auditoria3.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria3);
                    }
                    if (instrumento.getPlantaBeneficio() != null) {
                        Auditoria auditoria4 = new Auditoria();
                        auditoria4.setAccion("UPDATE");
                        auditoria4.setDetalleAnterior(plantaBeneficioPopupAnterior.toString());
                        auditoria4.setDetalleCambios(plantaBeneficioPopup.toString());
                        auditoria4.setFecha(getCurrentTimeStamp());
                        auditoria4.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria4);
                    }
                    if (instrumento.getSujetoMinero() != null) {
                        Auditoria auditoria5 = new Auditoria();
                        auditoria5.setAccion("UPDATE");
                        auditoria5.setDetalleAnterior(plantaBeneficioPopupAnterior.toString());
                        auditoria5.setDetalleCambios(plantaBeneficioPopup.toString());
                        auditoria5.setFecha(getCurrentTimeStamp());
                        auditoria5.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                        auditoriaServicio.create(auditoria5);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro actualizado con éxito", null));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
        return "instrumentos";
    }

    public Instrumento getInstrumentoAnterior() {
        return instrumentoAnterior;
    }

    public void setInstrumentoAnterior(Instrumento instrumentoAnterior) {
        this.instrumentoAnterior = instrumentoAnterior;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public void buscarRegistro() {
        System.out.println("entra buscarRegistro");
        System.out.println("instrumento.getCodigoTipoMineria().getCodigoTipoMineria(): " + instrumento.getCodigoTipoMineria().getCodigoTipoMineria());
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Antes de buscar por código debe elegir un tipo de registro", null));
            return;
        }
        if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            System.out.println("codigoFiltro: " + codigoFiltro);
            concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            concesionMineraPopupAnterior = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
            System.out.println("concesionMineraPopup: " + concesionMineraPopup);
            if (concesionMineraPopup != null) {
                LocalidadRegional localidadRegionalConcesion = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(concesionMineraPopup.getCodigoProvincia().longValue());
                LocalidadRegional localidadRegionalUsuario = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(us.getCodigoProvincia().longValue());
                if (localidadRegionalConcesion.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {
                    provincia = localidadServicio.findByPk(concesionMineraPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(concesionMineraPopup.getCodigoCanton().longValue());
                    parroquia = localidadServicio.findByPk(concesionMineraPopup.getCodigoParroquia().longValue());
                } else {
                    concesionMineraPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La concesión existe pero no pertenece a su regional", null));
                }
            }
        } else if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
            licenciaComercializacionPopup = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            licenciaComercializacionPopupAnterior = licenciaComercializacionServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (licenciaComercializacionPopup != null) {
                LocalidadRegional localidadRegionalLicencia = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(licenciaComercializacionPopup.getCodigoProvincia().longValue());
                LocalidadRegional localidadRegionalUsuario = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(us.getCodigoProvincia().longValue());
                if (localidadRegionalLicencia.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {
                    provincia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoCanton().longValue());
                    parroquia = localidadServicio.findByPk(licenciaComercializacionPopup.getCodigoParroquida().longValue());
                } else {
                    licenciaComercializacionPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La licencia existe pero no pertenece a su regional", null));
                }
            }
        } else if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
            plantaBeneficioPopup = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            plantaBeneficioPopupAnterior = plantaBeneficioServicio.obtenerPorCodigoArcom(codigoFiltro);
            if (plantaBeneficioPopup != null) {
                LocalidadRegional localidadRegionalPlanta = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(plantaBeneficioPopup.getCodigoProvincia().longValue());
                LocalidadRegional localidadRegionalUsuario = localidadRegionalServicio
                        .obtenerPorCodigoLocalidad(us.getCodigoProvincia().longValue());
                if (localidadRegionalPlanta.getRegional().getCodigoRegional()
                        .equals(localidadRegionalUsuario.getRegional().getCodigoRegional())) {
                    provincia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoProvincia().longValue());
                    canton = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoCanton().longValue());
                    parroquia = localidadServicio.findByPk(plantaBeneficioPopup.getCodigoParroquida().longValue());
                } else {
                    plantaBeneficioPopup = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "La planta existe pero no pertenece a su regional", null));
                }
            }
        }
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
        instrumento.setConcesionMinera(concesionMineraPopup);
        instrumento.getConcesionMinera();
        instrumento.setDocumentoTitular(concesionMineraPopup.getDocumentoConcesionarioPrincipal());
        System.out.println("instrumento.getCodigoArcom(): " + instrumento.getCodigoArcomTransient());
        System.out.println("");
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
    }

    public void seleccionarLicencia() {
        instrumento.setLicenciaComercializacion(licenciaComercializacionPopup);
        instrumento.getLicenciaComercializacion();
        instrumento.setDocumentoTitular(licenciaComercializacionPopup.getNumeroDocumento());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
    }

    public void seleccionarPlanta() {
        instrumento.setPlantaBeneficio(plantaBeneficioPopup);
        instrumento.getPlantaBeneficio();
        instrumento.setDocumentoTitular(plantaBeneficioPopup.getNumeroDocumentoRepresentanteLegal());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
    }

    public void cambiarPopUp() {
        if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria() != null) {
            if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.SUJETO_MINERO.getCodigo())) {
                sujetoMinero = true;
            } else {
                sujetoMinero = false;
            }
            if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                    || instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                    || instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
                instrumento.setLicenciaComercializacion(null);
                instrumento.setPlantaBeneficio(null);
                instrumento.setSujetoMinero(null);
            }
            if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
                instrumento.setConcesionMinera(null);
                instrumento.setPlantaBeneficio(null);
                instrumento.setSujetoMinero(null);
            }
            if (instrumento.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
                instrumento.setConcesionMinera(null);
                instrumento.setLicenciaComercializacion(null);
                instrumento.setSujetoMinero(null);
            }
        }
        /*instrumento.setConcesionMinera(null);
         instrumento.setLicenciaComercializacion(null);
         instrumento.setPlantaBeneficio(null);
         instrumento.setSujetoMinero(null);
         instrumento.setCodigoArcomTransient(null);
         instrumento.setDocumentoTitular(null);*/
    }

    public boolean isSujetoMinero() {
        return sujetoMinero;
    }

    public void setSujetoMinero(boolean sujetoMinero) {
        this.sujetoMinero = sujetoMinero;
    }

    public SujetoMinero getSujetoMineroPopUp() {
        return sujetoMineroPopUp;
    }

    public void setSujetoMineroPopUp(SujetoMinero sujetoMineroPopUp) {
        this.sujetoMineroPopUp = sujetoMineroPopUp;
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
            instrumento.setDocumentoTitular(sujetoMineroPopUp.getNumeroDocumento());
        }
    }

    public void seleccionarSujetoMinero() {
        instrumento.setDocumentoTitular(sujetoMineroPopUp.getNumeroDocumento());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqSujMin').hide()");
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

    public SujetoMinero getSujetoMineroPopUpAnterior() {
        return sujetoMineroPopUpAnterior;
    }

    public void setSujetoMineroPopUpAnterior(SujetoMinero sujetoMineroPopUpAnterior) {
        this.sujetoMineroPopUpAnterior = sujetoMineroPopUpAnterior;
    }

    public void validarCedulaCompareciente() {
        if (instrumento.getNumeroDocumentoCompareciente() != null) {
            if (instrumento.getNumeroDocumentoCompareciente().length() >= 10) {
                if (instrumento.getNumeroDocumentoCompareciente().length() == 13) {
                    String nuevaCed = instrumento.getNumeroDocumentoCompareciente().substring(
                            0, instrumento.getNumeroDocumentoCompareciente().length() - 3);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return;
                    } else {
                        instrumento.setNumeroDocumentoCompareciente(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return;
                    }
                }
                if (CedulaValidator.validate(instrumento.getNumeroDocumentoCompareciente())) {
                    //cedulaValida = true;
                } else {
                    //cedulaValida = false;
                    instrumento.setNumeroDocumentoCompareciente(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                instrumento.setNumeroDocumentoCompareciente(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }
    }

}
