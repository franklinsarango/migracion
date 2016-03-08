/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.LicenciaComercializacionDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.LicenciaComercializacionDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
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
public class LicenciaComercializacionCtrl extends BaseCtrl {

    @EJB
    private LicenciaComercializacionServicio licenciaComercializacionServicio;
    @EJB
    private LicenciaComercializacionDao licenciaComercializacionDao;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private String codigoFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;

    private LicenciaComercializacion licenciaComercializacion;

    private List<LicenciaComercializacionDto> listaRegistros;
    private boolean existeCodigoArcom = true;
    private boolean cedulaValida;

    private List<SelectItem> tipoMaterial;
    private List<SelectItem> tipoMaterialDetalle;

    private boolean codigoArcomNull;

    private boolean otorgado;
    private boolean inscrito;

    private LicenciaComercializacion licenciaComercializacionAnterior;

    private boolean perNatural;

    private PersonaNatural personaNatural;
    private PersonaJuridica personaJuridica;

    public LicenciaComercializacion getLicenciaComercializacion() {
        if (licenciaComercializacion == null) {
            String licenciaComercializacionId = getHttpServletRequestParam("idItem");
            Long idLicenciaComercializacion = null;
            if (licenciaComercializacionId != null) {
                idLicenciaComercializacion = Long.parseLong(licenciaComercializacionId);
            }
            if (idLicenciaComercializacion == null) {
                licenciaComercializacion = new LicenciaComercializacion();
                licenciaComercializacion.setEstadoLicencia(new CatalogoDetalle());
                licenciaComercializacion.setCodigoTipoMineral(new CatalogoDetalle());
                licenciaComercializacion.setCodigoMineralInteres(new CatalogoDetalle());
                licenciaComercializacion.setTipoPersona("PNA");
            } else {
                licenciaComercializacion = licenciaComercializacionDao.findByPk(idLicenciaComercializacion);
                licenciaComercializacionAnterior = licenciaComercializacionDao.findByPk(idLicenciaComercializacion);
                if (licenciaComercializacion.getEstadoLicencia() == null) {
                    licenciaComercializacion.setEstadoLicencia(new CatalogoDetalle());
                }
                if (licenciaComercializacion.getCodigoTipoMineral() == null) {
                    licenciaComercializacion.setCodigoTipoMineral(new CatalogoDetalle());
                }
                if (licenciaComercializacion.getCodigoMineralInteres() == null) {
                    licenciaComercializacion.setCodigoMineralInteres(new CatalogoDetalle());
                }
                if (licenciaComercializacion.getTipoLicencia() == null) {
                    licenciaComercializacion.setTipoLicencia(new CatalogoDetalle());
                }
                System.out.println("licenciaComercializacion.getCodigoArcom(): " + licenciaComercializacion.getCodigoArcom());
                if (licenciaComercializacion.getCodigoArcom() == null || licenciaComercializacion.getCodigoArcom().trim().isEmpty()) {
                    codigoArcomNull = true;
                } else {
                    codigoArcomNull = false;
                }
                existeCodigoArcom = false;
            }
        }
        return licenciaComercializacion;
    }

    public void setLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) {
        this.licenciaComercializacion = licenciaComercializacion;
    }

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

    public void buscar() {
        listaRegistros = null;
        getListaRegistros();
    }

    public String editarRegistro() {
        LicenciaComercializacionDto licenciaComercializacionDtoItem = (LicenciaComercializacionDto) getExternalContext().getRequestMap().get("reg");
        System.out.println("licenciaComercializacionItem.getCodigoLicenciaComercializacion(): " + licenciaComercializacionDtoItem.getCodigoLicenciaComercializacion());
        return "licenciacomercializacionform?faces-redirect=true&idItem=" + licenciaComercializacionDtoItem.getCodigoLicenciaComercializacion();
    }
    
    public String verRegistro() {
        LicenciaComercializacionDto licenciaComercializacionDtoItem = (LicenciaComercializacionDto) getExternalContext().getRequestMap().get("reg");
        System.out.println("licenciaComercializacionItem.getCodigoLicenciaComercializacion(): " + licenciaComercializacionDtoItem.getCodigoLicenciaComercializacion());
        return "licenciacomercializacionformro?faces-redirect=true&idItem=" + licenciaComercializacionDtoItem.getCodigoLicenciaComercializacion();
    }

    public String guardarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        System.out.println("licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle(): " + licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle());
        /*if (licenciaComercializacion.getFechaOtorga() != null) {
            if (licenciaComercializacion.getFechaOtorga().after(licenciaComercializacion.getFechaInscribe())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Fecha de otorgamiento debe ser menor o igual a la fecha de inscripción", null));
                return null;
            }
        }*/
        if (licenciaComercializacion.getCodigoMineralInteres().getCodigoCatalogoDetalle().equals(1000L)) {
            licenciaComercializacion.setCodigoMineralInteres(null);
        }
        try {
            if (licenciaComercializacion.getCodigoLicenciaComercializacion() == null) {
                System.out.println("entra create");
                licenciaComercializacion.setEstadoRegistro(true);
                licenciaComercializacion.setFechaCreacion(new Date());
                licenciaComercializacion.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                licenciaComercializacion.setMigrada(true);
                licenciaComercializacionServicio.create(licenciaComercializacion);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(licenciaComercializacion.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito", null));
            } else {
                System.out.println("entra update");
                licenciaComercializacion.setFechaModificacion(new Date());
                licenciaComercializacion.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                licenciaComercializacionServicio.actualizarLicenciaComercializacion(licenciaComercializacion);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(licenciaComercializacionAnterior.toString());
                auditoria.setDetalleCambios(licenciaComercializacion.toString());
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
        return "licenciascomercializacion";
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
            if (licenciaComercializacion.getCodigoProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(licenciaComercializacion.getCodigoProvincia().toString()));
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
        licenciaComercializacion.setCodigoCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (licenciaComercializacion.getCodigoCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(licenciaComercializacion.getCodigoCanton().toString()));
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

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public List<LicenciaComercializacionDto> getListaRegistros() {
        if (listaRegistros == null) {
            listaRegistros = licenciaComercializacionServicio.obtenerListaLicencias(codigoFiltro, cedulaTitularFiltro, login.getUserName());
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<LicenciaComercializacionDto> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public boolean isExisteCodigoArcom() {
        System.out.println("entra isExisteCodigoArcom");
        return existeCodigoArcom;
    }

    public void setExisteCodigoArcom(boolean existeCodigoArcom) {
        this.existeCodigoArcom = existeCodigoArcom;
    }

    public void validarCodigoArcom() {
        //if (licenciaComercializacion.getCodigoLicenciaComercializacion() == null) {
        if (licenciaComercializacion.getCodigoArcom() != null) {
            LicenciaComercializacion lc = licenciaComercializacionDao.findByCodigoArcom(licenciaComercializacion.getCodigoArcom());
            if (lc == null) {
                existeCodigoArcom = false;
            } else {
                existeCodigoArcom = true;
                licenciaComercializacion.setCodigoArcom(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Código de licencia existente, por favor ingrese uno nuevo", null));
            }
        }
        //}
    }

    public boolean isCedulaValida() {
        if (licenciaComercializacion.getNumeroDocumento() != null) {
            if (licenciaComercializacion.getNumeroDocumento().length() >= 10) {
                if (licenciaComercializacion.getNumeroDocumento().length() == 13) {
                    String nuevaCed = licenciaComercializacion.getNumeroDocumento().substring(0, licenciaComercializacion.getNumeroDocumento().length() - 3);
                    //licenciaComercializacion.setNumeroDocumento(nuevaCed);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        licenciaComercializacion.setNumeroDocumento(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(licenciaComercializacion.getNumeroDocumento())) {
                    cedulaValida = true;
                } else {
                    cedulaValida = false;
                    licenciaComercializacion.setNumeroDocumento(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                licenciaComercializacion.setNumeroDocumento(null);
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

    public List<SelectItem> getTipoMaterial() {
        if (tipoMaterial == null) {
            tipoMaterial = new ArrayList<>();
            Catalogo catalogoTipoMaterial = catalogoServicio.findByNemonico("MATEXP");
            List<CatalogoDetalle> tipMatCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoMaterial.getCodigoCatalogo());

            for (CatalogoDetalle tipMat : tipMatCat) {
                tipoMaterial.add(new SelectItem(tipMat.getCodigoCatalogoDetalle(), tipMat.getNombre().toUpperCase()));
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

    public List<SelectItem> getTipoMaterialDetalle() {
        if (tipoMaterialDetalle == null) {
            /*CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("MATCONS").get(0);
             tipoMaterialDetalle = new ArrayList<>();
             System.out.println("licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle(): " + licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle());
             if (licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle() != null) {
             if (licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle().equals(cd.getCodigoCatalogoDetalle())) {
             return tipoMaterialDetalle;
             }
             } else {
             return tipoMaterialDetalle;
             }*/
            if (licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle() != null) {
                tipoMaterialDetalle = new ArrayList<>();
                System.out.println("licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle(): " + licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle());
                CatalogoDetalle catalogoDetalleTipoMaterial = catalogoDetalleServicio.findByPk(licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle());
                System.out.println("catalogoDetalleTipoMaterial.getNombre(): " + catalogoDetalleTipoMaterial.getNombre());
                Catalogo catalogo = catalogoServicio.findByNombre(catalogoDetalleTipoMaterial.getNombre());
                //System.out.println("catalogo.getCodigoCatalogo(): " + catalogo.getCodigoCatalogo());
                if (catalogo != null) {
                    List<CatalogoDetalle> tipMatCatDet = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());

                    for (CatalogoDetalle tipMat : tipMatCatDet) {
                        tipoMaterialDetalle.add(new SelectItem(tipMat.getCodigoCatalogoDetalle(), tipMat.getNombre().toUpperCase()));
                    }
                }
            }
        }
        return tipoMaterialDetalle;
    }

    public void setTipoMaterialDetalle(List<SelectItem> tipoMaterialDetalle) {
        this.tipoMaterialDetalle = tipoMaterialDetalle;
    }

    public boolean isCodigoArcomNull() {
        return codigoArcomNull;
    }

    public void setCodigoArcomNull(boolean codigoArcomNull) {
        this.codigoArcomNull = codigoArcomNull;
    }

    public boolean isOtorgado() {
        if (licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle() != null) {
            if (licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_OTORGADO.getCodigo())) {
                otorgado = true;
            } else {
                otorgado = false;
            }
            if (otorgado) {
                inscrito = true;
            }
        }
        return otorgado;
    }

    public void setOtorgado(boolean otorgado) {
        this.otorgado = otorgado;
    }

    public boolean isInscrito() {
        if (licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle() != null) {
            if (licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_INSCRITO.getCodigo())
                    || licenciaComercializacion.getEstadoLicencia().equals(ConstantesEnum.EST_OTORGADO.getCodigo())) {
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

    public LicenciaComercializacion getLicenciaComercializacionAnterior() {
        return licenciaComercializacionAnterior;
    }

    public void setLicenciaComercializacionAnterior(LicenciaComercializacion licenciaComercializacionAnterior) {
        this.licenciaComercializacionAnterior = licenciaComercializacionAnterior;
    }

    public boolean isPerNatural() {
        if (licenciaComercializacion.getTipoPersona() != null) {
            if (licenciaComercializacion.getTipoPersona().equals("PNA")) {
                perNatural = true;
            } else if (licenciaComercializacion.getTipoPersona().equals("PJU")) {
                perNatural = false;
            }
        }
        return perNatural;
    }

    public void setPerNatural(boolean perNatural) {
        this.perNatural = perNatural;
    }

    public void validarTipoPersona() {
        isPerNatural();
    }

    public void validarCedulaPersonaNatural() {
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
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de documento existente", null));
            }
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

    public void guardarPersonaNatural() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        licenciaComercializacion.setNumeroDocumento(personaNatural.getNumeroDocumento());
        licenciaComercializacion.setNombre(personaNatural.getNombre());
        licenciaComercializacion.setApellido(personaNatural.getApellido());
        licenciaComercializacion.setDireccion(personaNatural.getDireccion());
        licenciaComercializacion.setTelefono(personaNatural.getTelefono());
        licenciaComercializacion.setCorreoElectronico(personaNatural.getEmail());
        licenciaComercializacion.setCasilleroJudicial(personaNatural.getCasilleroJudicial());
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
        personaNatural.setNumeroDocumento(licenciaComercializacion.getNumeroDocumento());
        /*personaNatural.setNombre(licenciaComercializacion.getNombre());
         personaNatural.setApellido(licenciaComercializacion.getApellido());
         personaNatural.setCelular(licenciaComercializacion.getTelefono());
         personaNatural.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
         personaNatural.setDireccion(licenciaComercializacion.getDireccion());
         personaNatural.setEmail(licenciaComercializacion.getCorreoElectronico());*/
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

    public void validarCedulaPersonaJuridica() {
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
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Número de ruc existente", null));
        }
        //}
    }

    public void guardarPersonaJuridica() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());

        licenciaComercializacion.setNumeroDocumento(personaJuridica.getRuc());
        licenciaComercializacion.setNombre(personaJuridica.getNombreLegal());
        licenciaComercializacion.setApellido(personaJuridica.getNombreComercial());
        licenciaComercializacion.setDireccion(personaJuridica.getDireccion());
        licenciaComercializacion.setTelefono(personaJuridica.getCelular());
        licenciaComercializacion.setCorreoElectronico(personaJuridica.getEmail());
        licenciaComercializacion.setCasilleroJudicial(personaJuridica.getCasilleroJudicial());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        try {
            if (pj == null) {
                personaJuridica.setFechaCreacion(new Date());
                personaJuridica.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                localidad.setCodigoLocalidad(Long.valueOf("5"));
                personaJuridica.setCodigoLocalidad(null);
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
                localidad.setCodigoLocalidad(Long.valueOf("5"));
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
        personaJuridica.setRuc(licenciaComercializacion.getNumeroDocumento());
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

}
