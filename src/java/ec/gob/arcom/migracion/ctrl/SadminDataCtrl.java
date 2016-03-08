/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.SadminDataDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Fase;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Regimen;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.SadminData;
import ec.gob.arcom.migracion.modelo.SadminDataMaquinaria;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.FaseServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.SadminDataMaquinariaServicio;
import ec.gob.arcom.migracion.servicio.SadminDataServicio;
import ec.gob.arcom.migracion.util.CedulaValidator;
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
public class SadminDataCtrl extends BaseCtrl {

    @EJB
    private SadminDataDao sadminDataDao;
    @EJB
    private SadminDataServicio sadminDataServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private LocalidadRegionalServicio localidadRegionalServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private FaseServicio faseServicio;
    @EJB
    private RegimenServicio regimenServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private SadminDataMaquinariaServicio sadminDataMaquinariaServicio;

    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private SadminData sadminData;

    private List<SadminData> listaRegistros;

    private String codigoFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;

    private List<SelectItem> tipoMaterial;
    private List<SelectItem> tipoMaterialDetalle;

    private boolean cedulaValida;
    private boolean cedulaRepLegalValida;

    private List<SelectItem> fases;
    private String tipoPersona = "N";
    private boolean perNatural;

    private boolean otorgado;
    private boolean inscrito;

    private SadminData sadminDataAnterior;

    private boolean tipSolConcMin;

    private PersonaNatural personaNatural;
    private PersonaJuridica personaJuridica;

    private boolean tipoSolMineriaArtesanal;

    private Long codigoMaquinaria;
    private List<SadminDataMaquinaria> maquinariasPorCodigoSadmin;
    private int cantidadMaquinaria = 1;

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getCedulaTitularFiltro() {
        return cedulaTitularFiltro;
    }

    public void setCedulaTitularFiltro(String cedulaTitularFiltro) {
        this.cedulaTitularFiltro = cedulaTitularFiltro;
    }

    public String getNombreAreaFiltro() {
        return nombreAreaFiltro;
    }

    public void setNombreAreaFiltro(String nombreAreaFiltro) {
        this.nombreAreaFiltro = nombreAreaFiltro;
    }

    @PostConstruct
    public void prueba() {
        System.out.println("se imprime");
    }

    public SadminData getSadminData() {
        if (sadminData == null) {
            String sadminDataId = getHttpServletRequestParam("idItem");
            Long idSadminData = null;
            if (sadminDataId != null) {
                idSadminData = Long.parseLong(sadminDataId);
            }
            if (idSadminData == null) {
                sadminData = new SadminData();
                sadminData.setCodigoRegimen(new Regimen());
                sadminData.setCodigoModalidadTrabajo(new CatalogoDetalle());
                sadminData.setCodigoCasilleroLocalidad(new Localidad());
            } else {
                sadminData = sadminDataDao.findByPk(idSadminData);
                sadminDataAnterior = sadminDataDao.findByPk(idSadminData);
                if (sadminData.getCodigoRegimen() == null) {
                    sadminData.setCodigoRegimen(new Regimen());
                }
                if (sadminData.getCodigoModalidadTrabajo() == null) {
                    sadminData.setCodigoModalidadTrabajo(new CatalogoDetalle());
                } else if (sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() == null) {
                    sadminData.getCodigoModalidadTrabajo().setCodigoCatalogoDetalle(1000L);
                }
                if (sadminData.getCodigoCasilleroLocalidad() == null) {
                    sadminData.setCodigoCasilleroLocalidad(new Localidad());
                }
                if (sadminData.getProvincia1() != null && sadminData.getCodigoCasilleroLocalidad().getCodigoLocalidad() == null) {
                    Localidad provincia = localidadServicio.findByCodInternacional(sadminData.getProvincia1());
                    sadminData.setCodigoCasilleroLocalidad(provincia);
                }
                if (sadminData.getTipoPersona() != null) {
                    if (sadminData.getTipoPersona().equals("PNA")) {
                        tipoPersona = "N";
                    } else if (sadminData.getTipoPersona().equals("PJU")) {
                        tipoPersona = "J";
                    }
                }
                validarRegimenFase();
            }
        }
        return sadminData;
    }

    public void setSadminData(SadminData sadminData) {
        this.sadminData = sadminData;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public void buscar() {
        listaRegistros = null;
        getListaRegistros();
    }

    public List<SadminData> getListaRegistros() {
        if (listaRegistros == null) {
            listaRegistros = presentarListaRegistros();
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<SadminData> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public List<SadminData> presentarListaRegistros() {
        System.out.println("entra usuario.getUserName()");
        //HttpSession session = FacesUtil.getSession();
        //System.out.println("login: " + login.getUserName());
        //String userName = session.getAttribute("username").toString();
        //System.out.println("userName: " + userName);
        System.out.println("getLoggedUsername: " + getLoggedUsername());
        return sadminDataDao.obtenerRegistrosPorUsuario(login.getUserName(), codigoFiltro, cedulaTitularFiltro, nombreAreaFiltro);
    }

    public String editarRegistro() {
        SadminData sadminDataItem = (SadminData) getExternalContext().getRequestMap().get("reg");
        System.out.println("sadminDataItem: " + sadminDataItem.getId() + " " + sadminDataItem.getNombre() + " " + sadminDataItem.getCedulaRuc() + " " + sadminDataItem.getTitular());
        return "areamineraform?faces-redirect=true&idItem=" + sadminDataItem.getId();
    }

    public String actualizarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        Localidad provincia = localidadServicio.findByCodInternacional(sadminData.getProvincia1());
        System.out.println("provincia: " + provincia.getCodigoLocalidad());
        LocalidadRegional localidadRegional = localidadRegionalServicio.obtenerPorCodigoLocalidad(provincia.getCodigoLocalidad());
        System.out.println("localidadRegional.getLocalidadRegionalPK().getCodigoRegional(): " + localidadRegional.getLocalidadRegionalPK().getCodigoRegional());
        Regional regional = regionalServicio.findByPk(localidadRegional.getLocalidadRegionalPK().getCodigoRegional());
        System.out.println("regional.getNombreRegional(): " + regional.getNombreRegional());
        try {
            //sadminDataServicio.update(sadminData);
            //System.out.println("sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle(): " + sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle());
            if (sadminData.getCodigoRegimen() != null && sadminData.getCodigoRegimen().getCodigoRegimen() != null) {
                if (sadminData.getCodigoRegimen().getCodigoRegimen().equals(1000L)) {
                    sadminData.setCodigoRegimen(null);
                }
            }

            if (sadminData.getCodigoModalidadTrabajo() != null && sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() != null) {
                if (sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle().equals(1000L)) {
                    sadminData.setCodigoModalidadTrabajo(null);
                }
            }

            if (tipoPersona.equals("N")) {
                sadminData.setTipoPersona("PNA");
            } else if (tipoPersona.equals("J")) {
                sadminData.setTipoPersona("PJU");
            }
            sadminData.setRegional(regional.getNombreRegional());
            System.out.println("sadminData: " + sadminData.getFechaInscribe() + " " + sadminData.getFechaOtorga());

            /*if (sadminData.getFechaInforme() == null) {
             try {
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             String dateInString = "01/01/1800";
             Date date = formatter.parse(dateInString);
             sadminData.setFechaInforme(date);
             } catch (Exception e) {
             e.printStackTrace();
             }
             }*/

            /*if (sadminData.getFechaOtorga() == null) {
             try {
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             String dateInString = "01/01/1800";
             Date date = formatter.parse(dateInString);
             sadminData.setFechaOtorga(date);
             } catch (Exception e) {
             e.printStackTrace();
             }
             }*/
            /*if (sadminData.getFechaOtorga() != null) {
             if (sadminData.getFechaInforme().after(sadminData.getFechaOtorga())) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
             "Fecha de informe debe ser menor o igual a la fecha de otorgamiento", null));
             return null;
             }
             if (sadminData.getFechaInscribe() != null) {
             if (sadminData.getFechaOtorga().after(sadminData.getFechaInscribe())) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
             "Fecha de otorgamiento debe ser menor o igual a la fecha de inscripción", null));
             return null;
             }
             }
             }*/
            if (!sadminData.getTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getDescripcion())) {
                sadminData.getCodigoRegimen().setCodigoRegimen(null);
                sadminData.setFase(null);
            }
            sadminDataServicio.actualizarRegistros(sadminData);
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("UPDATE");
            auditoria.setDetalleAnterior(sadminDataAnterior.toString());
            auditoria.setDetalleCambios(sadminData.toString());
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoriaServicio.create(auditoria);
            listaRegistros = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "El registro se actualizó exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No se pudo actualizar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
        return "concesionesmineras?faces-redirect=false";
    }

    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia.getCodigoInternacional(), provincia.getNombre().toUpperCase()));
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
            if (sadminData.getProvincia1() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByCodInternacional(sadminData.getProvincia1());
            if (catalogoCanton == null || (catalogoCanton != null && catalogoCanton.getCodigoLocalidad() == null)) {
                return cantones;
            }
            List<Localidad> cantonCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoCanton.getCodigoLocalidad()));

            for (Localidad canton : cantonCat) {
                cantones.add(new SelectItem(canton.getCodigoInternacional(), canton.getNombre().toUpperCase()));
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
        sadminData.setCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (sadminData.getCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByCodInternacional(sadminData.getCanton());
            if (catalogoParroquia == null || (catalogoParroquia != null && catalogoParroquia.getCodigoInternacional() == null)) {
                return parroquias;
            }
            List<Localidad> parroquiaCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoParroquia.getCodigoLocalidad()));

            for (Localidad parroquia : parroquiaCat) {
                parroquias.add(new SelectItem(parroquia.getCodigoInternacional(), parroquia.getNombre().toUpperCase()));
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

    public List<SelectItem> getTipoMaterial() {
        if (tipoMaterial == null) {
            tipoMaterial = new ArrayList<>();
            Catalogo catalogoTipoMaterial = catalogoServicio.findByNemonico("MATEXP");
            List<CatalogoDetalle> tipMatCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoMaterial.getCodigoCatalogo());

            for (CatalogoDetalle tipMat : tipMatCat) {
                tipoMaterial.add(new SelectItem(tipMat.getNombre().toUpperCase(), tipMat.getNombre().toUpperCase()));
            }
        }
        return tipoMaterial;
    }

    public void setTipoMaterial(List<SelectItem> tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public void cargaTipoMaterialDetalle() {
        tipoMaterialDetalle = null;
        getTipoMaterialDetalle();
    }

    public boolean isCedulaValida() {
        if (sadminData.getCedulaRuc() != null) {
            if (sadminData.getCedulaRuc().length() >= 10) {
                if (sadminData.getCedulaRuc().length() == 13) {
                    String nuevaCed = sadminData.getCedulaRuc().substring(0, sadminData.getCedulaRuc().length() - 3);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        sadminData.setCedulaRuc(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(sadminData.getCedulaRuc())) {
                    cedulaValida = true;
                } else {
                    cedulaValida = false;
                    sadminData.setCedulaRuc(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                sadminData.setCedulaRuc(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }
        return cedulaValida;
    }

    public void setCedulaValida(boolean cedulaValida) {
        this.cedulaValida = cedulaValida;
    }

    public void validarCedula() {
        isCedulaValida();
    }

    public List<SelectItem> getTipoMaterialDetalle() {
        if (tipoMaterialDetalle == null) {
            tipoMaterialDetalle = new ArrayList<>();
            /*if (sadminData.getMineral() == null || sadminData.getMineral().equals("MATERIALES DE CONSTRUCCIO") || sadminData.getMineral().equals("MATERIAL DE CONSTRUCCION")) {
             return tipoMaterialDetalle;
             }*/
            System.out.println("sadminData.getMineral(): " + sadminData.getMineral());
            if (sadminData.getMineral() == null || sadminData.getMineral().equals("MATERIALES DE CONSTRUCCIO")) {
                return tipoMaterialDetalle;
            }
            System.out.println("sadminData.getMineral(): " + sadminData.getMineral());
            Catalogo catalogoTipoMaterial = catalogoServicio.findByNombre(sadminData.getMineral());
            if (catalogoTipoMaterial != null) {
                List<CatalogoDetalle> tipMatCatDet = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoMaterial.getCodigoCatalogo());

                for (CatalogoDetalle tipMat : tipMatCatDet) {
                    tipoMaterialDetalle.add(new SelectItem(tipMat.getNombre().toUpperCase(), tipMat.getNombre().toUpperCase()));
                }
            }
        }
        return tipoMaterialDetalle;
    }

    public void setTipoMaterialDetalle(List<SelectItem> tipoMaterialDetalle) {
        this.tipoMaterialDetalle = tipoMaterialDetalle;
    }

    public boolean isCedulaRepLegalValida() {
        if (sadminData.getCedulaRepresentante() != null) {
            if (sadminData.getCedulaRepresentante().length() >= 10) {
                if (sadminData.getCedulaRepresentante().length() == 13) {
                    String nuevaCed = sadminData.getCedulaRepresentante().substring(0, sadminData.getCedulaRepresentante().length() - 3);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        sadminData.setCedulaRepresentante(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(sadminData.getCedulaRepresentante())) {
                    cedulaRepLegalValida = true;
                } else {
                    cedulaRepLegalValida = false;
                    sadminData.setCedulaRepresentante(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                sadminData.setCedulaRepresentante(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }
        return cedulaRepLegalValida;
    }

    public void validarCedulaRepLegal() {
        isCedulaRepLegalValida();
    }

    public void setCedulaRepLegalValida(boolean cedulaRepLegalValida) {
        this.cedulaRepLegalValida = cedulaRepLegalValida;
    }

    public List<SelectItem> getFases() {
        if (fases == null) {
            fases = new ArrayList<>();
            List<Fase> fasesPorRegimen = null;
            if (sadminData.getCodigoRegimen().getCodigoRegimen() != null) {
                Regimen regimen = regimenServicio.findByPk(sadminData.getCodigoRegimen().getCodigoRegimen());
                if (regimen != null) {
                    if (regimen.getNemonico().equals(ConstantesEnum.GRAN_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.GRAN_MINERIA.getDescripcion());
                    } else if (regimen.getNemonico().equals(ConstantesEnum.MED_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.MED_MINERIA.getDescripcion());
                    } else if (regimen.getNemonico().equals(ConstantesEnum.PEQ_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.PEQ_MINERIA.getDescripcion());
                    }
                }
                if (sadminData.getCodigoRegimen().getCodigoRegimen().equals(1000L)) {
                    fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion("T");
                }
            } else {
                fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion("T");
            }
            if (fasesPorRegimen != null) {
                for (Fase f : fasesPorRegimen) {
                    fases.add(new SelectItem(f.getNombreFase().toUpperCase(), f.getNombreFase().toUpperCase()));
                }
            }
        }
        return fases;
    }

    public void setFases(List<SelectItem> fases) {
        this.fases = fases;
    }

    public void obtenerFasePorRegimen() {
        fases = null;
        getFases();
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isPerNatural() {
        if (tipoPersona.equals("N")) {
            perNatural = true;
        } else if (tipoPersona.equals("J")) {
            perNatural = false;
        }
        return perNatural;
    }

    public void setPerNatural(boolean perNatural) {
        this.perNatural = perNatural;
    }

    public void validarTipoPersona() {
        isPerNatural();
    }

    public boolean isOtorgado() {
        if (sadminData.getEstado() != null) {
            if (sadminData.getEstado().equals(ConstantesEnum.EST_OTORGADO.getDescripcion())
                    || sadminData.getEstado().equals(ConstantesEnum.EST_INSCRITO.getDescripcion())) {
                otorgado = true;
            } else {
                otorgado = false;
            }
        }
        return otorgado;
    }

    public void setOtorgado(boolean otorgado) {
        this.otorgado = otorgado;
    }

    public boolean isInscrito() {
        if (sadminData.getEstado() != null) {
            if (sadminData.getEstado().equals(ConstantesEnum.EST_INSCRITO.getDescripcion())) {
                inscrito = true;
            } else {
                inscrito = false;
            }
        }
        return inscrito;
    }

    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }

    public SadminData getSadminDataAnterior() {
        return sadminDataAnterior;
    }

    public void setSadminDataAnterior(SadminData sadminDataAnterior) {
        this.sadminDataAnterior = sadminDataAnterior;
    }

    public boolean isTipSolConcMin() {
        return tipSolConcMin;
    }

    public void setTipSolConcMin(boolean tipSolConcMin) {
        this.tipSolConcMin = tipSolConcMin;
    }

    public void validarRegimenFase() {
        System.out.println("entra validarRegimenFase");
        System.out.println("sadminData.getTipoSolicitud(): " + sadminData.getTipoSolicitud());
        if (sadminData.getTipoSolicitud() != null) {
            if (sadminData.getTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getDescripcion())) {
                tipSolConcMin = true;
            } else {
                tipSolConcMin = false;
            }
        }
        //muestraModalidadTrabajo();
    }

    public void validarCedulaPersonaNatural() {
        System.out.println("entra validarCedulaPersonaNatural");
        String numDocumento = personaNatural.getNumeroDocumento();
        if (personaNatural.getNumeroDocumento().length() == 10) {
            if (!CedulaValidator.validate(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
                return;
            }
        } else if (personaNatural.getNumeroDocumento().length() == 13) {
            if (!CedulaValidator.validacionRUC(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de ruc inválido", null));
                return;
            }
        } else {
            //personaNatural.setNumeroDocumento(null);
            personaNatural = null;
            getPersonaNatural();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de documento inválido", null));
            return;
        }

        if (personaNatural.getNumeroDocumento() != null) {
            System.out.println("entra else");
            personaNatural = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
            System.out.println("personaNatural: " + personaNatural);
            if (personaNatural == null) {
                getPersonaNatural();
                personaNatural.setNumeroDocumento(numDocumento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de documento no existente", null));
                System.out.println("personaNatural.getNumeroDocumento(): " + personaNatural.getNumeroDocumento());
            }
            /*else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
             "Número de documento existente", null));
             }*/
        }
    }

    public void guardarPersonaNatural() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        sadminData.setCedulaRuc(personaNatural.getNumeroDocumento());
        sadminData.setTitular(personaNatural.getApellido() + " " + personaNatural.getNombre());
        sadminData.setCasillero(personaNatural.getCasilleroJudicial());
        sadminData.setDireccion(personaNatural.getDireccion());
        sadminData.setTelefono(personaNatural.getTelefono());
        sadminData.setEmail(personaNatural.getEmail());
        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
        try {
            if (pn == null) {
                personaNatural.setFechaCreacion(new Date());
                personaNatural.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.create(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            } else {
                personaNatural.setFechaModificacion(new Date());
                personaNatural.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.actualizarPersonaNatural(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pn.toString());
                auditoria.setDetalleCambios(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
        }
    }

    public void llenaPopUpPersonaNatural() {
        getPersonaNatural();
        personaNatural.setNumeroDocumento(sadminData.getCedulaRuc());
    }

    public void validarCedulaPersonaJuridica() {
        System.out.println("entra validarCedulaPersonaJuridica");
        String numRuc = personaJuridica.getRuc();
        if (personaJuridica.getRuc().length() != 13) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Se debe ingresar 13 dígitos para el ruc", null));
            //personaJuridica.setRuc(null);
            personaJuridica = null;
            getPersonaJuridica();
            return;
        }
        /*if (!CedulaValidator.validacionRUC(personaJuridica.getRuc())) {
         //personaJuridica.setRuc(null);
         personaJuridica = null;
         getPersonaJuridica();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
         "Número de ruc inválido", null));
         } else {*/
        System.out.println("entra else");
        personaJuridica = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        System.out.println("personaJuridica: " + personaJuridica);
        if (personaJuridica == null) {
            getPersonaJuridica();
            personaJuridica.setRuc(numRuc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Número de ruc no existente", null));
        }
        /*else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
         "Número de ruc existente", null));
         }*/
        //}
    }

    public void guardarPersonaJuridica() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        sadminData.setCedulaRuc(personaJuridica.getRuc());
        sadminData.setTitular(personaJuridica.getNombreLegal());
        sadminData.setCasillero(personaJuridica.getCasilleroJudicial());
        sadminData.setDireccion(personaJuridica.getDireccion());
        sadminData.setTelefono(personaJuridica.getTelefono());
        sadminData.setEmail(personaJuridica.getEmail());
        sadminData.setCedulaRepresentante(personaJuridica.getDocumentoRepresentanteLegal());
        sadminData.setRepresentanteLegal(personaJuridica.getApellidoRepresentanteLegal() + " " + personaJuridica.getNombreRepresentanteLegal());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        try {
            if (pj == null) {
                personaJuridica.setFechaCreacion(new Date());
                personaJuridica.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                localidad.setCodigoLocalidad(Long.valueOf("5"));
                personaJuridica.setCodigoLocalidad(localidad);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.create(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            } else {
                personaJuridica.setFechaModificacion(new Date());
                personaJuridica.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                //localidad.setCodigoLocalidad(Long.valueOf(concesionMinera.getCodigoParroquia().toString()));
                personaJuridica.setCodigoLocalidad(null);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.actualizarPersonaJuridica(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pj.toString());
                auditoria.setDetalleCambios(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
            System.out.println("ex.getMessage()" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void llenarPopUpPersonaJuridica() {
        getPersonaJuridica();
        personaJuridica.setRuc(sadminData.getCedulaRuc());
    }

    public void validarCedulaRepresentanteLegal() {
        System.out.println("entra validarCedulaRepresentanteLegal");
        String numDocumento = personaJuridica.getDocumentoRepresentanteLegal();
        String nuevaCed = personaJuridica.getDocumentoRepresentanteLegal();
        if (personaJuridica.getDocumentoRepresentanteLegal().length() >= 10) {
            if (personaJuridica.getDocumentoRepresentanteLegal().length() == 13) {
                nuevaCed = personaJuridica.getDocumentoRepresentanteLegal().substring(0, personaJuridica.getDocumentoRepresentanteLegal().length() - 3);
            }
        }
        if (!CedulaValidator.validate(nuevaCed)) {
            personaJuridica.setDocumentoRepresentanteLegal(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de cédula inválido", null));
        }
    }

    public PersonaNatural getPersonaNatural() {
        if (personaNatural == null) {
            personaNatural = new PersonaNatural();
        }
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    public PersonaJuridica getPersonaJuridica() {
        if (personaJuridica == null) {
            personaJuridica = new PersonaJuridica();
        }
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public boolean isTipoSolMineriaArtesanal() {
        return tipoSolMineriaArtesanal;
    }

    public void setTipoSolMineriaArtesanal(boolean tipoSolMineriaArtesanal) {
        this.tipoSolMineriaArtesanal = tipoSolMineriaArtesanal;
    }

    public void muestraModalidadTrabajo() {
        System.out.println("entra muestraModalidadTrabajo");
        System.out.println("sadminData.getTipoSolicitud(): " + sadminData.getTipoSolicitud());
        if (sadminData.getTipoSolicitud() != null) {
            System.out.println("ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getDescripcion(): " + ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getDescripcion());
            if (sadminData.getTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getDescripcion())) {
                tipoSolMineriaArtesanal = true;
                sadminData.setCodigoModalidadTrabajo(null);
            } else {
                tipoSolMineriaArtesanal = false;
                if (sadminData.getCodigoModalidadTrabajo() == null) {
                    sadminData.setCodigoModalidadTrabajo(new CatalogoDetalle());
                }
            }
        }
    }

    public Long getCodigoMaquinaria() {
        return codigoMaquinaria;
    }

    public void setCodigoMaquinaria(Long codigoMaquinaria) {
        this.codigoMaquinaria = codigoMaquinaria;
    }

    public void guardarMaquinaria() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        System.out.println("codigoMaquinaria: " + codigoMaquinaria);
        try {
            SadminDataMaquinaria sadminDataMaquinaria = new SadminDataMaquinaria();
            sadminDataMaquinaria.setCodigoArcom(sadminData.getCodigo());
            sadminDataMaquinaria.setCodigoTipoMaquinaria(new TipoMaquinaria());
            sadminDataMaquinaria.getCodigoTipoMaquinaria().setCodigoTipoMaquinaria(codigoMaquinaria);
            sadminDataMaquinaria.setCantidadMaquinaria(cantidadMaquinaria);
            System.out.println("sadminDataMaquinaria: " + sadminDataMaquinaria);
            sadminDataMaquinariaServicio.create(sadminDataMaquinaria);
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("INSERT");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(sadminDataMaquinaria.toString());
            auditoriaServicio.create(auditoria);
            maquinariasPorCodigoSadmin = null;
            getMaquinariasPorCodigoSadmin();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Registro guardado correctamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
        }
    }

    public List<SadminDataMaquinaria> getMaquinariasPorCodigoSadmin() {
        if (maquinariasPorCodigoSadmin == null) {
            maquinariasPorCodigoSadmin = sadminDataMaquinariaServicio.obtenerMaquinariasPorCodigoSadmin(sadminData.getCodigo());
        }
        return maquinariasPorCodigoSadmin;
    }

    public void setMaquinariasPorCodigoSadmin(List<SadminDataMaquinaria> maquinariasPorCodigoSadmin) {
        this.maquinariasPorCodigoSadmin = maquinariasPorCodigoSadmin;
    }

    public void eliminarMaquinaria() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        SadminDataMaquinaria sadminDataMaquinariaItem = (SadminDataMaquinaria) getExternalContext().getRequestMap().get("maq");
        sadminDataMaquinariaServicio.delete(sadminDataMaquinariaItem.getCodigoSadminDataMaq());
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion("DELETE");
        auditoria.setFecha(getCurrentTimeStamp());
        auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
        auditoria.setDetalleAnterior(sadminDataMaquinariaItem.toString());
        auditoriaServicio.create(auditoria);
        maquinariasPorCodigoSadmin = null;
        getMaquinariasPorCodigoSadmin();
    }

    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

}
