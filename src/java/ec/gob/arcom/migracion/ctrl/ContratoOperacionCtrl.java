/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.constantes.ConversionEstadosEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaCotaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
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
public class ContratoOperacionCtrl extends BaseCtrl {

    @EJB
    private ContratoOperacionServicio contratoOperacionServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private CoordenadaCotaServicio coordenadaCotaServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private ContratoOperacion contratoOperacion;
    private List<ContratoOperacion> contratosOperacion;

    private ConcesionMinera concesionMineraPopup;
    private PersonaDto personaDtoPopup;

    private PersonaDto personaDto;

    private String codigoFiltro;

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;

    private String codigoArcomFiltro;
    private String numDocumentoFiltro;

    private String numDocPersonaPopupFiltro;
    
    private String coordenadaX;
    private String coordenadaY;
    
    private List<CoordenadaCota> coordenadasPorContrato;
    
    private Boolean usuarioRegistrador;
    private boolean mostrarCoordenadas = false;
    private Secuencia secuenciaContratoOperacion;
    
    private int longitudCoordenadas;
    private ContratoOperacion contratoOperacionAnterior;
    
    protected int numeroPagina = 0;
    protected int desplazamiento;
    protected int totalPaginas;
    protected static final int tamanoPagina = 10;
    private Integer paginaSeleccionada;
    private ArrayList<Integer> listaPaginas;
    private String urlEditarContrato;
    private boolean registrosPorRegional;
    @PostConstruct
    public void init() {
        try {
//            Usuario uBd = usuarioDao.obtenerPorLogin(login.getUserName());
//            if (uBd != null) {
//                if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("RM")) {
//                    usuarioRegistrador = true;
//                }else{
//                    usuarioRegistrador = false;
//                }
//            }   
            
            if(login.isRegistroMinero() == true || login.isRegistroMineroNacional() == true){
                usuarioRegistrador = true;
                setRegistrosPorRegional(true);
            }else{
                usuarioRegistrador = false;
                setRegistrosPorRegional(false);
            }
            
            String codigo = getHttpServletRequestParam("codigo");
            System.out.println("CodigoFiltro" + codigo);
            if(codigo != null && codigo.equals("") == false){
                codigoArcomFiltro = codigo;
                buscar();
            }
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    public ContratoOperacion getContratoOperacion() {
        if (contratoOperacion == null) {
            String contratoId = getHttpServletRequestParam("idItem");
            Long idContrato = null;
            if (contratoId != null) {
                idContrato = Long.parseLong(contratoId);
            }
            if (idContrato == null) {
                contratoOperacion = new ContratoOperacion();
                contratoOperacion.setCodigoConcesion(new ConcesionMinera());
                contratoOperacion.setTipoContrato(new CatalogoDetalle());
                contratoOperacion.setEstadoContrato(new CatalogoDetalle());
            } else {
                contratoOperacion = contratoOperacionServicio.findByPk(idContrato);
                contratoOperacionAnterior = contratoOperacionServicio.findByPk(idContrato);
                if (contratoOperacion.getCodigoConcesion() == null) {
                    contratoOperacion.setCodigoConcesion(new ConcesionMinera());
                }
                if (contratoOperacion.getCodigoProvincia() == null) {
                    contratoOperacion.setCodigoProvincia(new Localidad());
                }
                if (contratoOperacion.getCodigoCanton() == null) {
                    contratoOperacion.setCodigoCanton(new Localidad());
                }
                if (contratoOperacion.getCodigoParroquia() == null) {
                    contratoOperacion.setCodigoParroquia(new Localidad());
                }
                if (contratoOperacion.getTipoContrato() == null) {
                    contratoOperacion.setTipoContrato(new CatalogoDetalle());
                }
                if (contratoOperacion.getEstadoContrato() == null) {
                    contratoOperacion.setEstadoContrato(new CatalogoDetalle());
                }
                PersonaDto personaDto = personaNaturalServicio
                        .obtenerPersonaPorNumIdentificacion(contratoOperacion.getNumeroDocumento());
                if (personaDto != null) {
                    contratoOperacion.setNombrePersona(personaDto.getNombres());
                    contratoOperacion.setApellidoPersona(personaDto.getApellidos());
                    contratoOperacion.setEmailPersona(personaDto.getEmail());
                }
                obtenerInformacionGeofrafica(contratoOperacion.getCodigoConcesion());
                mostrarCoordenadas = true;
            }
        }
        return contratoOperacion;
    }

    public void setContratoOperacion(ContratoOperacion contratoOperacion) {
        this.contratoOperacion = contratoOperacion;
    }

    public void buscar() {
        contratosOperacion = null;
        //getContratosOperacion();
        mostrarDatos("btn_buscar");
    }

//    public String editarRegistro() {
//        mostrarCoordenadas = true;
//        ContratoOperacion contratoItem = (ContratoOperacion) getExternalContext().getRequestMap().get("reg");
//        return "contratoform?faces-redirect=true&idItem=" + contratoItem.getCodigoContratoOperacion();
//    }
    
    public String editarRegistro() {
        mostrarCoordenadas = true;
        ContratoOperacion contratoItem = (ContratoOperacion) getExternalContext().getRequestMap().get("reg");
        urlEditarContrato = ConstantesEnum.URL_APP_PROD.getDescripcion() + "/migracion/web/contratoform.xhtml?idItem=" + contratoItem.getCodigoContratoOperacion();
        System.out.println("urlEditarContrato: " + urlEditarContrato);
        return null;
    }
    
    public String nuevoRegistro() {
        mostrarCoordenadas = false;
        urlEditarContrato = ConstantesEnum.URL_APP_PROD.getDescripcion() + "/migracion/web/contratoform.xhtml?";
        System.out.println("urlNuevoContrato: " + urlEditarContrato);
        RequestContext.getCurrentInstance().execute("PF('visorEditarContrato').show();");
        return null;
    }

    public String verRegistro() {
        mostrarCoordenadas = true;
        ContratoOperacion contratoItem = (ContratoOperacion) getExternalContext().getRequestMap().get("reg");
        return "contratoview?faces-redirect=true&idItem=" + contratoItem.getCodigoContratoOperacion();
    }
    
    public void guardarContrato() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        /*CatalogoDetalle cd = new CatalogoDetalle();
        cd.setCodigoCatalogoDetalle(ConversionEstadosEnum.OTORGADO.getCodigo19());
        contratoOperacion.setEstadoContrato(cd);*/
        contratoOperacion.setEstadoRegistro(Boolean.TRUE);
        if (!contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle()
                .equals(ConstantesEnum.TIPO_CONTRATO_CESION_DERECHOS.getCodigo())) {
            contratoOperacion.setPorcentaje(null);
        }
        try {
            if (contratoOperacion.getCodigoContratoOperacion() == null) {
                if (mostrarCoordenadas == false) {
                    mostrarCoordenadas = true;
                }
                contratoOperacion.setFechaCreacion(new Date());
                contratoOperacion.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                contratoOperacionServicio.create(contratoOperacion);
                System.out.println("contratoOperacion.getCodigoContratoOperacion(): " + contratoOperacion.getCodigoContratoOperacion());
                generarCodigoArcomContrato();
                contratoOperacionServicio.actualizarContratoOperacion(contratoOperacion);
                //contratoOperacionServicio.guardarTodo(contratoOperacion);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(contratoOperacion.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito con código " + contratoOperacion.getCodigoArcom(), null));
            } else {
                if(contratoOperacionAnterior != null){
                    contratoOperacion.setFechaModificacion(new Date());
                    contratoOperacion.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                    contratoOperacionServicio.actualizarContratoOperacion(contratoOperacion);
                    Auditoria auditoria = new Auditoria();
                    auditoria.setAccion("UPDATE");
                    auditoria.setDetalleAnterior(contratoOperacionAnterior.toString());
                    auditoria.setDetalleCambios(contratoOperacion.toString());
                    auditoria.setFecha(getCurrentTimeStamp());
                    auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoriaServicio.create(auditoria);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado con éxito", null));
                    
                    String urlPagina = ConstantesEnum.URL_APP_PROD.getDescripcion() + "/migracion/web/contratos.xhtml?codigo=" + contratoOperacion.getCodigoConcesion().getCodigoArcom();
                    RequestContext.getCurrentInstance().execute("javascript:window.parent.location.href = '" + urlPagina + "'");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro ya guardado con éxito con código " + contratoOperacion.getCodigoArcom(), null));
                    
                    String urlPagina = ConstantesEnum.URL_APP_PROD.getDescripcion() + "/migracion/web/contratos.xhtml?codigo=" + contratoOperacion.getCodigoConcesion().getCodigoArcom();
                    RequestContext.getCurrentInstance().execute("javascript:window.parent.location.href = '" + urlPagina + "'");
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: " + ex.getMessage(), ex.getMessage()));
            ex.printStackTrace();
//            return null;
        }
        //return "contratos";
    }

    public List<ContratoOperacion> getContratosOperacion() {
        /*if (contratosOperacion == null) {
            contratosOperacion = contratoOperacionServicio.obtenerContratosOperacion(codigoArcomFiltro, numDocumentoFiltro, login.getUserName());
        }*/
        return contratosOperacion;
    }

    public void setContratosOperacion(List<ContratoOperacion> contratosOperacion) {
        this.contratosOperacion = contratosOperacion;
    }

    public ConcesionMinera getConcesionMineraPopup() {
        return concesionMineraPopup;
    }

    public void setConcesionMineraPopup(ConcesionMinera concesionMineraPopup) {
        this.concesionMineraPopup = concesionMineraPopup;
    }

    public PersonaDto getPersonaDtoPopup() {
        return personaDtoPopup;
    }

    public void setPersonaDtoPopup(PersonaDto personaDtoPopup) {
        this.personaDtoPopup = personaDtoPopup;
    }

    public void buscarRegistro() {
        concesionMineraPopup = null;
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(codigoFiltro);
        if (concesionMineraPopup != null) {
            Localidad provincia = localidadServicio
                    .findByPk(concesionMineraPopup.getCodigoProvincia().longValue());
            concesionMineraPopup.setProvinciaString(provincia.getNombre());
            contratoOperacion.setCodigoProvincia(provincia);
            Localidad canton = localidadServicio
                    .findByPk(concesionMineraPopup.getCodigoCanton().longValue());
            concesionMineraPopup.setCantonString(canton.getNombre());
            contratoOperacion.setCodigoCanton(canton);
            Localidad parroquia = localidadServicio
                    .findByPk(concesionMineraPopup.getCodigoParroquia().longValue());
            concesionMineraPopup.setParroquiaString(parroquia.getNombre());
            contratoOperacion.setCodigoParroquia(parroquia);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La concesión no existe", null));
        }
    }

    public void buscarPersona() {
        System.out.println("entra buscarPersona");
        System.out.println("contratoOperacion.getNumeroDocumento(): " + numDocPersonaPopupFiltro);
        personaDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(numDocPersonaPopupFiltro);
        System.out.println("personaDto: " + personaDto);
        if (personaDto == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No existe persona", null));
        }
    }

    public PersonaDto getPersonaDto() {
        return personaDto;
    }

    public void setPersonaDto(PersonaDto personaDto) {
        this.personaDto = personaDto;
    }

    public void seleccionarConcesion() {
        PersonaDto pDto = personaNaturalServicio.
                obtenerPersonaPorNumIdentificacion(concesionMineraPopup.getDocumentoConcesionarioPrincipal());
        concesionMineraPopup.setNombreConcesionarioPrincipal(pDto.getNombres());
        System.out.println("pDto.getNombres(): " + pDto.getNombres());
        if (pDto.getApellidos() != null) {
            concesionMineraPopup.setApellidoConcesionarioPrincipal(pDto.getApellidos());
        }
        System.out.println("pDto.getApellidos(): " + pDto.getApellidos());
        obtenerInformacionGeofrafica(concesionMineraPopup);
        contratoOperacion.setCodigoConcesion(concesionMineraPopup);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
    }
    
    public void obtenerInformacionGeofrafica(ConcesionMinera cm) {
        Localidad provincia = localidadServicio.findByPk(cm.getCodigoProvincia().longValue());
        if (provincia != null) {
            cm.setProvinciaString(provincia.getNombre());
        }
        Localidad canton = localidadServicio.findByPk(cm.getCodigoCanton().longValue());
        if (canton != null) {
            cm.setCantonString(canton.getNombre());
        }
        Localidad parroquia = localidadServicio.findByPk(cm.getCodigoParroquia().longValue());
        if (parroquia != null) {
            cm.setParroquiaString(parroquia.getNombre());
        }
    }

    public void seleccionarPersona() {
        contratoOperacion.setNumeroDocumento(personaDto.getIdentificacion());
        contratoOperacion.setNombrePersona(personaDto.getNombres());
        contratoOperacion.setApellidoPersona(personaDto.getApellidos());
        contratoOperacion.setEmailPersona(personaDto.getEmail());
        RequestContext.getCurrentInstance().execute("PF('dlgBusqPersona').hide()");
    }

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
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
            if (contratoOperacion.getCodigoProvincia() == null
                    || contratoOperacion.getCodigoProvincia().getCodigoLocalidad() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(contratoOperacion.getCodigoProvincia().getCodigoLocalidad().toString()));
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

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (contratoOperacion.getCodigoCanton() == null
                    || contratoOperacion.getCodigoCanton().getCodigoLocalidad() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(contratoOperacion.getCodigoCanton().getCodigoLocalidad().toString()));
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

    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        contratoOperacion.getCodigoCanton().setCodigoLocalidad(null);
        getCantones();
        getParroquias();
    }

    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }

    public String getCodigoArcomFiltro() {
        return codigoArcomFiltro;
    }

    public void setCodigoArcomFiltro(String codigoArcomFiltro) {
        this.codigoArcomFiltro = codigoArcomFiltro;
    }

    public String getNumDocumentoFiltro() {
        return numDocumentoFiltro;
    }

    public void setNumDocumentoFiltro(String numDocumentoFiltro) {
        this.numDocumentoFiltro = numDocumentoFiltro;
    }

    public String getNumDocPersonaPopupFiltro() {
        return numDocPersonaPopupFiltro;
    }

    public void setNumDocPersonaPopupFiltro(String numDocPersonaPopupFiltro) {
        this.numDocPersonaPopupFiltro = numDocPersonaPopupFiltro;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
    
    public void guardarCoordenadas() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        int orden = 0;
        if (coordenadasPorContrato.size() > 0) {
            orden = coordenadasPorContrato.size();
        }
        CoordenadaCota cc = new CoordenadaCota();
        cc.setUtmEste(coordenadaX);
        cc.setUtmNorte(coordenadaY);
        cc.setOrden(BigInteger.valueOf(orden));
        if (orden == 0) {
            cc.setInicial(true);
        } else {
            cc.setInicial(false);
        }
        cc.setCodigoContratoOperacion(contratoOperacion);
        cc.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
        cc.setFechaCreacion(new Date());
        cc.setEstadoRegistro(true);
        try {
            coordenadaCotaServicio.create(cc);
            Auditoria auditoria2 = new Auditoria();
            auditoria2.setAccion("INSERT");
            auditoria2.setFecha(getCurrentTimeStamp());
            auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria2.setDetalleAnterior(cc.toString());
            auditoriaServicio.create(auditoria2);
            FacesContext
                    .getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Registro guardado correctamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
        }
        coordenadasPorContrato = null;
        getCoordenadasPorContrato();
    }
    
    public void eliminarCoordenadas() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            CoordenadaCota ccItem = (CoordenadaCota) getExternalContext().getRequestMap().get("crd");
            coordenadaCotaServicio.delete(ccItem.getCodigoCoordenadaCota());
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("DELETE");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(ccItem.toString());
            auditoriaServicio.create(auditoria);
            coordenadasPorContrato = null;
            getCoordenadasPorContrato();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Coordenadas eliminadas", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo eliminar el registro", ex.getMessage()));
        }
    }

    public List<CoordenadaCota> getCoordenadasPorContrato() {
        if (coordenadasPorContrato == null) {
            coordenadasPorContrato = coordenadaCotaServicio.findByCodigoContrato(contratoOperacion.getCodigoContratoOperacion());
        }
        return coordenadasPorContrato;
    }

    public void setCoordenadasPorContrato(List<CoordenadaCota> coordenadasPorContrato) {
        this.coordenadasPorContrato = coordenadasPorContrato;
    }

    public boolean isMostrarCoordenadas() {
        return mostrarCoordenadas;
    }

    public void setMostrarCoordenadas(boolean mostrarCoordenadas) {
        this.mostrarCoordenadas = mostrarCoordenadas;
    }
    
    public void generarCodigoArcomContrato() {
        secuenciaContratoOperacion = secuenciaServicio.obtenerPorNemonico("CONTRATO_OP_RGL" + login.getPrefijoRegional());
        contratoOperacion.setCodigoArcom(
                formarCodigoComprobante(secuenciaContratoOperacion.getValor()));
        long secuenciaSiguiente = secuenciaContratoOperacion.getValor() + 1;
        secuenciaContratoOperacion.setValor(secuenciaSiguiente);
        secuenciaServicio.update(secuenciaContratoOperacion);
    }
    
    protected String formarCodigoComprobante(Long secuencial) {
        //prefijoComprobante es el prefijo de la regional
        String tipoContrato = "";
        if (contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle()
                .equals(ConstantesEnum.TIPO_CONTRATO_OPERACION.getCodigo())) {
            tipoContrato = "CO";
        } else if (contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle()
                .equals(ConstantesEnum.TIPO_CONTRATO_CESION_DERECHOS.getCodigo())) {
            tipoContrato = "CD";
        } else if (contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle()
                .equals(ConstantesEnum.TIPO_CONTRATO_CESION_ACCIONES.getCodigo())) {
            tipoContrato = "CA";
        } else if (contratoOperacion.getTipoContrato().getCodigoCatalogoDetalle()
                .equals(ConstantesEnum.TIPO_CONTRATO_EXPLOTACION.getCodigo())) {
            tipoContrato = "CX";
        }
        String codigo = secuencial.toString();
        while (codigo.length() < 6) {
            codigo = "0" + codigo;
        }
        codigo = contratoOperacion.getCodigoConcesion().getCodigoArcom() + tipoContrato + codigo;
        return codigo;
    }

    public Secuencia getSecuenciaContratoOperacion() {
        return secuenciaContratoOperacion;
    }

    public void setSecuenciaContratoOperacion(Secuencia secuenciaContratoOperacion) {
        this.secuenciaContratoOperacion = secuenciaContratoOperacion;
    }
    
    public int getLongitudCoordenadas() {
        longitudCoordenadas = getCoordenadasPorContrato().size();
        return longitudCoordenadas;
    }

    public void setLongitudCoordenadas(int longitudCoordenadas) {
        this.longitudCoordenadas = longitudCoordenadas;
    }

    public ContratoOperacion getContratoOperacionAnterior() {
        return contratoOperacionAnterior;
    }

    public void setContratoOperacionAnterior(ContratoOperacion contratoOperacionAnterior) {
        this.contratoOperacionAnterior = contratoOperacionAnterior;
    }

    /**
     * @return the usuarioRegistrador
     */
    public Boolean getUsuarioRegistrador() {
        return usuarioRegistrador;
    }

    /**
     * @param usuarioRegistrador the usuarioRegistrador to set
     */
    public void setUsuarioRegistrador(Boolean usuarioRegistrador) {
        this.usuarioRegistrador = usuarioRegistrador;
    }
    
    /**
     * PAGINACION LISTA DE CONTRATOS
     */
    public void mostrarDatos(String strTipoRecorrido) {
        if (strTipoRecorrido.equals("siguiente")) {
            if (numeroPagina < totalPaginas) {
                numeroPagina = numeroPagina + 1;
            }
        }
        if (strTipoRecorrido.equals("anterior")) {
            if (numeroPagina == 1) {
            }
            if (numeroPagina > 1) {
                numeroPagina = numeroPagina - 1;
            }
        }
        if (strTipoRecorrido.equals("btn_buscar")) {
            numeroPagina = 1;
        }
        if (strTipoRecorrido.equals("cmb_paginador")) {
            numeroPagina = getPaginaSeleccionada();
        }
        desplazamiento = tamanoPagina * (numeroPagina - 1);
        setPaginaSeleccionada(numeroPagina);

        if (strTipoRecorrido.equals("btn_buscar")) {
            cargarListaPaginas();
        }
        
        if (contratoOperacionServicio.countByContratoOperacionTabla(login.getUserName(), codigoArcomFiltro, numDocumentoFiltro, isRegistrosPorRegional(), tamanoPagina, desplazamiento) != null) {
            if (contratosOperacion != null) {
                contratosOperacion.clear();
            } else {
                contratosOperacion = new ArrayList<>();
            }
            List<ContratoOperacion> listContratoOperacion = contratoOperacionServicio.countByContratoOperacionTabla(login.getUserName(), codigoArcomFiltro, numDocumentoFiltro, isRegistrosPorRegional(), tamanoPagina, desplazamiento);
            for (ContratoOperacion contratoOp : listContratoOperacion) {
                contratosOperacion.add(contratoOp);
            }
        } else {
            //ponerMensajeInfo("", "No existen Contratos de Operacion con código ARCOM ");
            contratosOperacion.clear();
        }
    }

    public void cargarListaPaginas() {
        if(listaPaginas == null){
            listaPaginas = new ArrayList<>();
        }
        int paginas = contratoOperacionServicio.countByContratoOperacionTablaTotal(login.getUserName(), codigoArcomFiltro, numDocumentoFiltro, isRegistrosPorRegional());
        totalPaginas = paginas / tamanoPagina;
        if (paginas % tamanoPagina != 0) {
            totalPaginas++;
        }
        getListaPaginas().clear();
        for (int i = 0; i < totalPaginas; i++) {
            getListaPaginas().add(i + 1);
        }
    }
    
    /**
     * @return the paginaSeleccionada
     */
    public Integer getPaginaSeleccionada() {
        return paginaSeleccionada;
    }

    /**
     * @param paginaSeleccionada the paginaSeleccionada to set
     */
    public void setPaginaSeleccionada(Integer paginaSeleccionada) {
        this.paginaSeleccionada = paginaSeleccionada;
    }

    /**
     * @return the listaPaginas
     */
    public ArrayList<Integer> getListaPaginas() {
        return listaPaginas;
    }

    /**
     * @param listaPaginas the listaPaginas to set
     */
    public void setListaPaginas(ArrayList<Integer> listaPaginas) {
        this.listaPaginas = listaPaginas;
    }
    
    /**
     * @return the numeroPagina
     */
    public int getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * @param numeroPagina the numeroPagina to set
     */
    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    /**
     * @return the desplazamiento
     */
    public int getDesplazamiento() {
        return desplazamiento;
    }

    /**
     * @param desplazamiento the desplazamiento to set
     */
    public void setDesplazamiento(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    /**
     * @return the totalPaginas
     */
    public int getTotalPaginas() {
        return totalPaginas;
    }

    /**
     * @param totalPaginas the totalPaginas to set
     */
    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    /**
     * @return the urlEditarContrato
     */
    public String getUrlEditarContrato() {
        return urlEditarContrato;
    }

    /**
     * @param urlEditarContrato the urlEditarContrato to set
     */
    public void setUrlEditarContrato(String urlEditarContrato) {
        this.urlEditarContrato = urlEditarContrato;
    }

    /**
     * @return the registrosPorRegional
     */
    public boolean isRegistrosPorRegional() {
        return registrosPorRegional;
    }

    /**
     * @param registrosPorRegional the registrosPorRegional to set
     */
    public void setRegistrosPorRegional(boolean registrosPorRegional) {
        this.registrosPorRegional = registrosPorRegional;
    }
}
