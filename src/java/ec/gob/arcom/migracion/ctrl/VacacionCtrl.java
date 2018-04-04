/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.alfresco.AlfrescoMimeType;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoDocumentBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFileBean;
import ec.gob.arcom.migracion.alfresco.service.AlfrescoService;
import ec.gob.arcom.migracion.alfresco.util.AlfrescoFileUtil;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.mail.MailSender;
import ec.gob.arcom.migracion.modelo.Adjunto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Departamento;
import ec.gob.arcom.migracion.modelo.GestionVacacion;
import ec.gob.arcom.migracion.modelo.Licencia;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Rol;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.AdjuntoServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.ContratoServicio;
import ec.gob.arcom.migracion.servicio.DepartamentoServicio;
import ec.gob.arcom.migracion.servicio.GestionVacacionServicio;
import ec.gob.arcom.migracion.servicio.LicenciaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.RolServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.DateUtil;
import ec.gob.arcom.migracion.util.FacesUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@SessionScoped
public class VacacionCtrl {
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private DepartamentoServicio departamentoServicio;
    @EJB
    private LicenciaServicio licenciaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @EJB
    private AdjuntoServicio adjuntoServicio;
    @EJB
    private RolServicio rolServicio;
    @EJB
    private ContratoServicio contratoServicio;
    @EJB
    private GestionVacacionServicio gestionVacacionServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    
    private Licencia licencia;
    private Date fechaMinima;
    private Date fechaMaxima;
    private List<SelectItem> tiposFormulario;
    private List<SelectItem> tiposLicencia;
    private List<SelectItem> tiposCargo;
    private List<SelectItem> tiposContrato;
    private List<SelectItem> regimenesContratacion;
    private List<SelectItem> usuarios;
    private List<SelectItem> tiposModalidadContrato;
    private List<SelectItem> unidadesAdministrativas;
    private List<SelectItem> horariosAlmuerzo;
    private List<SelectItem> departamentosFilter;
    private List<Departamento> departamentos;
    private List<Contrato> contratos;
    private Contrato contrato;
    private Departamento departamento;
    private String motivoLicencia;
    private boolean showVacacionPanel;
    private boolean showCalamidadPanel;
    private boolean showInstitucionalPanel;
    private boolean showButtonPanel;
    private boolean showButtonPanel02;
    private boolean showHour;
    private boolean showDatosPersonalesPanel;
    private List<Usuario> funcionarios;
    private List<Usuario> funcionariosBk;
    
    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    //Edicion de usuario
    private String documentoBuscar;
    private Usuario usuarioEditar;
    //
    private List<Licencia> historial;
    private List<Licencia> tareas;
    private List<Licencia> tramitesAtendidos;
    private List<Licencia> tramitesAtendidosTH;
    private List<Licencia> saldos;
    private String saldoActual;
    private Long jefatura;
    private boolean aprobado= true;
    private boolean subsanar= false;
    //
    private List<Adjunto> archivosCargados;
    private List<UploadedFile> archivosParaCargar;
    private boolean showUploadPanel= false;
    //
    private String urlFormatoImprimir;
    private String observacionDesistir;
    private BigDecimal saldoVacacionContrato;
    private String calendarPattern;
    
    //Buscar en la tabla de funcionarios
    private String textoBuscar;
    
    /**
     * Creates a new instance of VacacionCtrl
     */
    public VacacionCtrl() {
        showVacacionPanel= false;
        showCalamidadPanel= false;
        showInstitucionalPanel= false;
        showButtonPanel= false;
        showButtonPanel02= false;
        showHour= false;
        showDatosPersonalesPanel= false;
    }
    
    public void checkPageUpdate() {
        System.out.println(Calendar.getInstance().getTime() + " : Pagina actualizada");
        if(login.getCodigoUsuario()!=null) {
            cargarTareas();
            cargarHistorial();
            saldoActual= obtenerSaldoActual(login.getCodigoUsuario());
        }
    }
    
    @PostConstruct
    private void inicializar() {
        //login.setCodigoUsuario(1689L);
        //login.setCodigoUsuario(1754L);
        cargarTareas();
        cargarHistorial();
        saldoActual= obtenerSaldoActual(login.getCodigoUsuario());
    }
    
    private void redirectToLogin() {
        ExternalContext ec= FacesUtil.getFacesContext().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/web/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(VacacionCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///////////////////    
    public List<Licencia> getSaldos() {
        return saldos;
    }

    public void setSaldos(List<Licencia> saldos) {    
        this.saldos = saldos;
    }

    public String getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(String saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }
    
    public void setTextoBuscar(String textoBuscar) {    
        this.textoBuscar = textoBuscar;
    }
    
    public List<Usuario> getFuncionarios() {
        return funcionarios;
    }
    
    public void setFuncionarios(List<Usuario> funcionarios) {    
        this.funcionarios = funcionarios;
    }

    public List<SelectItem> getDepartamentosFilter() {
        departamentosFilter= obtenerDepartamentos();
        return departamentosFilter;
    }
    
    public void setDepartamentosFilter(List<SelectItem> departamentosFilter) {    
        this.departamentosFilter = departamentosFilter;
    }

    public String getCalendarPattern() {
        return calendarPattern;
    }

    public void setCalendarPattern(String calendarPattern) {
        this.calendarPattern = calendarPattern;
    }
    
    public BigDecimal getSaldoVacacionContrato() {
        return saldoVacacionContrato;
    }

    public void setSaldoVacacionContrato(BigDecimal saldoVacacionContrato) {
        this.saldoVacacionContrato = saldoVacacionContrato;
    }
    
    public String getMotivoLicencia() {
        return motivoLicencia;
    }
    
    public void setMotivoLicencia(String motivoLicencia) {
        this.motivoLicencia = motivoLicencia;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public Date getFechaMaxima() {
        fechaMaxima= Calendar.getInstance().getTime();
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public boolean isShowVacacionPanel() {
        return showVacacionPanel;
    }

    public void setShowVacacionPanel(boolean showVacacionPanel) {
        this.showVacacionPanel = showVacacionPanel;
    }

    public boolean isShowCalamidadPanel() {
        return showCalamidadPanel;
    }

    public void setShowCalamidadPanel(boolean showCalamidadPanel) {
        this.showCalamidadPanel = showCalamidadPanel;
    }

    public boolean isShowInstitucionalPanel() {
        return showInstitucionalPanel;
    }

    public void setShowInstitucionalPanel(boolean showInstitucionalPanel) {
        this.showInstitucionalPanel = showInstitucionalPanel;
    }

    public boolean isShowButtonPanel() {
        return showButtonPanel;
    }

    public void setShowButtonPanel(boolean showButtonPanel) {
        this.showButtonPanel = showButtonPanel;
    }

    public boolean isShowButtonPanel02() {
        return showButtonPanel02;
    }

    public void setShowButtonPanel02(boolean showButtonPanel02) {
        this.showButtonPanel02 = showButtonPanel02;
    }
    
    public boolean isShowHour() {
        return showHour;
    }

    public void setShowHour(boolean showHour) {
        this.showHour = showHour;
    }

    public boolean isShowDatosPersonalesPanel() {
        return showDatosPersonalesPanel;
    }

    public void setShowDatosPersonalesPanel(boolean showDatosPersonalesPanel) {
        this.showDatosPersonalesPanel = showDatosPersonalesPanel;
    }

    public List<Departamento> getDepartamentos() {
        departamentos= departamentoServicio.listar();
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Licencia> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Licencia> historial) {
        this.historial = historial;
    }

    public List<Licencia> getTareas() {
        return tareas;
    }

    public void setTareas(List<Licencia> tareas) {
        this.tareas = tareas;
    }

    public List<Licencia> getTramitesAtendidos() {
        return tramitesAtendidos;
    }

    public void setTramitesAtendidos(List<Licencia> tramitesAtendidos) {
        this.tramitesAtendidos = tramitesAtendidos;
    }

    public List<Licencia> getTramitesAtendidosTH() {
        return tramitesAtendidosTH;
    }

    public void setTramitesAtendidosTH(List<Licencia> tramitesAtendidosTH) {
        this.tramitesAtendidosTH = tramitesAtendidosTH;
    }
    
    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public boolean isSubsanar() {
        return subsanar;
    }

    public void setSubsanar(boolean subsanar) {
        this.subsanar = subsanar;
    }

    public List<Adjunto> getArchivosCargados() {
        return archivosCargados;
    }

    public void setArchivosCargados(List<Adjunto> archivosCargados) {
        this.archivosCargados = archivosCargados;
    }

    public List<UploadedFile> getArchivosParaCargar() {
        return archivosParaCargar;
    }

    public void setArchivosParaCargar(List<UploadedFile> archivosParaCargar) {
        this.archivosParaCargar = archivosParaCargar;
    }

    public boolean isShowUploadPanel() {
        return showUploadPanel;
    }

    public void setShowUploadPanel(boolean showUploadPanel) {
        this.showUploadPanel = showUploadPanel;
    }

    public String getUrlFormatoImprimir() {
        return urlFormatoImprimir;
    }

    public void setUrlFormatoImprimir(String urlFormatoImprimir) {
        this.urlFormatoImprimir = urlFormatoImprimir;
    }

    public String getObservacionDesistir() {
        return observacionDesistir;
    }

    public void setObservacionDesistir(String observacionDesistir) {
        this.observacionDesistir = observacionDesistir;
    }
    
    ///////////////////
    public List<SelectItem> obtenerDepartamentos() {
        List<Departamento> depList= departamentoServicio.listar();
        List<SelectItem> depSI= new ArrayList<>();
        if(depList!=null) {
            for(Departamento d : depList) {
                depSI.add(new SelectItem(d.getCodigoDepartamento(), d.getNombre()));
            }
        }
        return depSI;
    }
    public List<SelectItem> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
            
            List<Usuario> usrs= usuarioServicio.listarUsuariosInternos();
            
            if (usrs != null) {
                for (Usuario usr : usrs) {
                    usuarios.add(new SelectItem(usr, usr.getNombre().toUpperCase() + " " + usr.getApellido().toUpperCase()));
                }
            }
        }
        return usuarios; 
    }
    
    public List<SelectItem> getTiposFormulario() {
        if (tiposFormulario == null) {
            tiposFormulario = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOFOR");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    if(catDet.getNemonico().equals("TIPOFORSOLIC")) {
                        tiposFormulario.add(new SelectItem(catDet, catDet.getNombre()+ " - 3 días en adelante"));
                    } else {
                        tiposFormulario.add(new SelectItem(catDet, catDet.getNombre() + " - minutos, horas hasta 2 días"));
                    }
                }
            }
        }
        return tiposFormulario;
    }
    
    public List<SelectItem> getTiposLicencia() {
        if (tiposLicencia == null) {
            tiposLicencia = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("MOTPER");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposLicencia.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return tiposLicencia;
    }
    
    public List<SelectItem> getTiposCargo() {
        if (tiposCargo == null) {
            tiposCargo = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOCAR");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposCargo.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return tiposCargo;
    }
    
    public List<SelectItem> getRegimenesContratacion() {
        if (regimenesContratacion == null) {
            regimenesContratacion = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("REGCONT");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    regimenesContratacion.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return regimenesContratacion;
    }
    
    public List<SelectItem> getTiposContrato() {
        if (tiposContrato == null) {
            tiposContrato = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOCONT");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposContrato.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return tiposContrato;
    }
    
    public List<SelectItem> getTiposModalidadContrato() {
        if (tiposModalidadContrato == null) {
            tiposModalidadContrato = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOMODCONT");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposModalidadContrato.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return tiposModalidadContrato;
    }
    
    public List<SelectItem> getUnidadesAdministrativas() {
        if (unidadesAdministrativas == null) {
            unidadesAdministrativas = new ArrayList<>();
            
            List<Departamento> deps= departamentoServicio.listar();
            
            if (deps != null) {
                for (Departamento dep : deps) {
                    unidadesAdministrativas.add(new SelectItem(dep, dep.getNombre()));
                }
            }
        }
        return unidadesAdministrativas;
    }
    
    public List<SelectItem> getHorariosAlmuerzo() {
        if (horariosAlmuerzo == null) {
            horariosAlmuerzo = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("HORALM");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    horariosAlmuerzo.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }
        }
        return horariosAlmuerzo;
    }
    
    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia, provincia.getNombre().toUpperCase()));
            }
        }
        return provincias;
    }
    
    public List<SelectItem> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList<>();
            if (licencia.getProvinciaComision() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(licencia.getProvinciaComision().getCodigoLocalidad().toString()));
            if (catalogoCanton == null || (catalogoCanton != null && catalogoCanton.getCodigoLocalidad() == null)) {
                return cantones;
            }
            List<Localidad> cantonCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoCanton.getCodigoLocalidad()));

            for (Localidad canton : cantonCat) {
                cantones.add(new SelectItem(canton, canton.getNombre().toUpperCase()));
            }
        }
        return cantones;
    }
    
    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (licencia.getCantonComision() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(licencia.getCantonComision().getCodigoLocalidad().toString()));
            if (catalogoParroquia == null || (catalogoParroquia != null && catalogoParroquia.getCodigoInternacional() == null)) {
                return parroquias;
            }
            List<Localidad> parroquiaCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoParroquia.getCodigoLocalidad()));

            for (Localidad parroquia : parroquiaCat) {
                parroquias.add(new SelectItem(parroquia, parroquia.getNombre().toUpperCase()));
            }
        }
        return parroquias;
    }
    
    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        licencia.setCantonComision(null);
        licencia.setParroquiaComision(null);
        getCantones();
        getParroquias();
    }
    
    public void cargaParroquias() {
        parroquias = null;
        licencia.setParroquiaComision(null);
        getParroquias();
    }
    
    public String getDocumentoBuscar() {
        return documentoBuscar;
    }

    public void setDocumentoBuscar(String documentoBuscar) {    
        this.documentoBuscar = documentoBuscar;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }
    
    ///////////////////
    
    public String cancelAction() {
        return resetAction();
    }
    
    public String newSolicitudAction() {
        if(login.getCodigoUsuario()!=null) {
            List<Contrato> contratosUsuario= contratoServicio.listarPorUsuario(usuarioServicio.findByPk(login.getCodigoUsuario()));
            
            if(contratosUsuario.size()>0) {
                Usuario usr= usuarioServicio.findByPk(login.getCodigoUsuario());
                licencia= new Licencia();
                licencia.setUsuario(usr);
                licencia.setNumeroSolicitud(obtenerSecuenciaSolicitud());
                licencia.setDiasDisponibles(obtenerDiasDisponibles(login.getCodigoUsuario()));
                licencia.setFechaSolicitud(Calendar.getInstance().getTime());
                contrato= contratoServicio.listarPorUsuario(usr).get(0);
                showVacacionPanel= false;
                showCalamidadPanel= false;
                showInstitucionalPanel= false;
                showHour= false;
                calendarPattern= "dd-MM-yyyy";
                showButtonPanel= false;
                archivosCargados= new ArrayList<>();
                archivosParaCargar= new ArrayList<>();
                return "vacaciones-licencia-frm";
            } else {
                FacesUtil.showErrorMessage("Error", "No tiene asignado un contrato, pongase en contacto con Talento Humano");
            }
        } else {
            redirectToLogin();
        }
        return null;
    }
    
    private String resetAction() {
        licencia= null;
        cargarTareas();
        cargarHistorial();
        saldoActual= obtenerSaldoActual(login.getCodigoUsuario());
        return "vacaciones-home";
    }
    
    public String obtenerNombreLocalidad(Long codigo) {
        if(codigo!=null)
            return localidadServicio.findByPk(codigo).getNombre().toUpperCase();
        return "";
    }
    
    public void showFormPanel() {
        motivoLicencia= licencia.getTipoLicencia().getNombre();
        if(licencia.getTipoLicencia().getValor().equals("GRUPO_1")) {
            showVacacionPanel= true;
            showCalamidadPanel= false;
            showInstitucionalPanel= false;
            showButtonPanel= true;
            fechaMinima= Calendar.getInstance().getTime();
            licencia.setFechaHoraSalida(null);
            licencia.setFechaHoraRetorno(null);
            licencia.setDiasLicencia(null);
            licencia.setSaldoVacaciones(null);
        } else if (licencia.getTipoLicencia().getValor().equals("GRUPO_2")) {
            showVacacionPanel= false;
            showCalamidadPanel= true;
            showInstitucionalPanel= false;
            showButtonPanel= true;
            Calendar c= Calendar.getInstance();
            if(licencia.getTipoLicencia().getNemonico().equals("MOTPERENF") || licencia.getTipoLicencia().getNemonico().equals("MOTPERCAL")) {
                c.add(Calendar.DAY_OF_MONTH, -15);
            }
            fechaMinima= c.getTime();
        } else {
            showVacacionPanel= false;
            showCalamidadPanel= false;
            showInstitucionalPanel= true;
            showButtonPanel= true;
            Calendar c= Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -15);
            fechaMinima= c.getTime();
        }
    }
    
    public void showHourFields() {
        if(licencia.getTipoFormulario().getNemonico().equals("TIPOFORSOLIC")) {
            calendarPattern= "dd-MM-yyyy";
        } else {
            calendarPattern= "dd-MM-yyyy HH:mm";
        }
    }
    
    public String obtenerFechaConFormato(Date f) {
        return DateUtil.obtenerFechaConFormato(f);
    }
    
    public String obtenerFechaHoraConFormato(Date f) {
        return DateUtil.obtenerFechaHoraConFormato(f);
    }
    
    public String editUsuarioAction() {
        documentoBuscar= "";
        showButtonPanel02= false;
        usuarioEditar= null;
        contratos= new ArrayList<>();
        contrato= null;
        return "vacaciones-usuario-frm";
    }
    
    public String editDepartamentoAction() {
        return "vacaciones-departamentos";
    }
    
    public void buscarUsuario() {
        if(documentoBuscar.trim().length()>0) {
            usuarioEditar= usuarioServicio.findByDocumento(documentoBuscar.trim());
            if(usuarioEditar!=null) {
                contratos= contratoServicio.listarPorUsuario(usuarioEditar);
                if(contratos.size()>0) {
                    contrato= contratos.get(0);
                } else {
                    contrato= null;
                }
                showButtonPanel02= true;
                FacesUtil.showInfoMessage("Busqueda correcta", "Se encontro el usuario");
            } else {
                FacesUtil.showWarnMessage("Busqueda finalizada", "No se encontro un usuario con este número de documento");
                showButtonPanel02= false;
                usuarioEditar= null;
                contrato= null;
            }
        } else {
            inicializarListasBusquedaFuncionario();
            RequestContext.getCurrentInstance().execute("PF('busquedafrmwg').show();");
        }
        
    }
    
    public String updateUsuarioAction() {
        if(usuarioEditar!=null) {
            Usuario anterior= usuarioServicio.findByPk(usuarioEditar.getCodigoUsuario());
            usuarioServicio.update(usuarioEditar);
            Auditoria a= new Auditoria(Auditoria.UPDATE, usuarioEditar, anterior, login.getCodigoUsuario());
            auditoriaServicio.create(a);
            if(contrato!=null) {
                if(contrato.getCodigoContrato()!=null) {
                    contrato.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
                    contrato.setFechaModificacion(Calendar.getInstance().getTime());
                    Contrato cAnterior= contratoServicio.findByPk(contrato.getCodigoContrato());
                    contratoServicio.update(contrato);
                    a= new Auditoria(Auditoria.UPDATE, contrato, cAnterior, login.getCodigoUsuario());
                    auditoriaServicio.create(a);
                } else {
                    contrato.setUsuario(usuarioEditar);
                    contrato.setEstadoRegistro(true);
                    contrato.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
                    contrato.setFechaCreacion(Calendar.getInstance().getTime());
                    contratoServicio.create(contrato);
                    a= new Auditoria(Auditoria.INSERT, contrato, new Contrato(), login.getCodigoUsuario());
                    auditoriaServicio.create(a);
                    licenciaServicio.inicializarVacaciones(usuarioEditar.getCodigoUsuario(), saldoVacacionContrato);
                }
            }
            FacesUtil.showInfoMessage("Actualización correcta", "Usuario actualizado");
            return resetAdminAction();
        } else {
            FacesUtil.showErrorMessage("Error", "Primero debe buscar un usuario");
        }
        return null;
    }
    
    private String resetAdminAction() {
        usuarioEditar= null;
        documentoBuscar= "";
        licencia= null;
        cargarTareas();
        cargarHistorial();
        saldoActual= obtenerSaldoActual(login.getCodigoUsuario());
        return "vacaciones-admin";
    }
    
    public String cancelAdminAction() {
        return resetAdminAction();
    }
    
    public void editDepartamentoAction(Departamento dep) {
        departamento= dep;
        RequestContext.getCurrentInstance().execute("PF('departamentofrmwg').show();");
    }
    
    public void updateDepartamentoAction() {
        departamentoServicio.update(departamento);
    }
    
    private BigDecimal obtenerDiasDisponibles(Long codigoUsuario) {
        Date f= Calendar.getInstance().getTime();
        String result= licenciaServicio.obtenerDiasDisponibles(codigoUsuario, DateUtil.obtenerFechaHoraConFormato(f), DateUtil.obtenerFechaHoraConFormato(f));
        System.out.println(result);
        if(result!=null) {
            String[] resultados= result.split("\\|");
            String[] disponibles= resultados[0].split(":");
            return new BigDecimal(disponibles[1].trim());
        }
        return BigDecimal.ZERO;
    }
    
    private void obtenerDiasDisponibles(String resultado) {
        System.out.println("Resultado: " + resultado);
        String[] resultados= resultado.split("\\|");
        String[] disponibles= resultados[0].split(":");
        String[] descontar= resultados[1].split(":");
        
        BigDecimal saldoActual= new BigDecimal(disponibles[1].trim());
        BigDecimal diasLicencia= new BigDecimal(descontar[1].trim());
        licencia.setDiasLicencia(diasLicencia);
        licencia.setSaldoVacaciones(saldoActual.subtract(diasLicencia));
        if(licencia.getSaldoVacaciones().compareTo(BigDecimal.ZERO)==-1) {
            licencia.setFechaHoraRetorno(null);
            FacesUtil.showErrorMessage("Error", "El saldo de vacaciones no puede ser menor a cero: " + obtenerFormatoDecimal(licencia.getSaldoVacaciones()));
            licencia.setDiasLicencia(null);
            licencia.setSaldoVacaciones(null);
        }
    }
    
    public void obtenerSaldoVacaciones() {
        Calendar cs= Calendar.getInstance();
        cs.setTime(licencia.getFechaHoraSalida());
        if(cs.get(Calendar.HOUR_OF_DAY)==0) {
            cs.set(Calendar.HOUR_OF_DAY, 8);
            licencia.setFechaHoraSalida(cs.getTime());
        }
        
        Calendar cr= Calendar.getInstance();
        cr.setTime(licencia.getFechaHoraRetorno());
        if(cr.get(Calendar.HOUR_OF_DAY)==0) {
            cr.set(Calendar.HOUR_OF_DAY, 17);
            licencia.setFechaHoraRetorno(cr.getTime());
        }
        
        if(licencia.getTipoFormulario().equals(catalogoDetalleServicio.obtenerPorNemonico("TIPOFORPEROCA").get(0))) {
            Calendar ci= Calendar.getInstance();
            Calendar cf= Calendar.getInstance();
            ci.setTime(licencia.getFechaHoraSalida());
            cf.setTime(licencia.getFechaHoraRetorno());
            Long fechaI= licencia.getFechaHoraSalida().getTime();
            Long fechaF= licencia.getFechaHoraRetorno().getTime();
            
            if(cf.get(Calendar.MONTH) < ci.get(Calendar.MONTH)) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor o igual a la fecha de inicio");
            } else if(cf.get(Calendar.MONTH) == ci.get(Calendar.MONTH)) {
                if(cf.get(Calendar.DAY_OF_MONTH) < ci.get(Calendar.DAY_OF_MONTH)) {
                    licencia.setFechaHoraRetorno(null);
                    FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor o igual a la fecha de inicio");
                }
            }
            
            Long dias= (fechaF-fechaI)/(1000*60 *60*24);
            BigDecimal days= new BigDecimal(dias);
            if(days.compareTo(licencia.getDiasDisponibles())==1) {
               licencia.setFechaHoraRetorno(null);
               FacesUtil.showErrorMessage("Error", "Los días no pueden exceder del total disponible");
            } else if(days.compareTo(new BigDecimal(1))==1) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "Los días no pueden exceder de 2 para permiso ocasional");
            } else {
               String result= licenciaServicio.obtenerDiasDisponibles(licencia.getUsuario().getCodigoUsuario(),
                       DateUtil.obtenerFechaHoraConFormato(licencia.getFechaHoraSalida()),
                       DateUtil.obtenerFechaHoraConFormato(licencia.getFechaHoraRetorno()));
               if(result!=null) {
                   obtenerDiasDisponibles(result);
               }
            }
            
        } else {
            Long fechaI= licencia.getFechaHoraSalida().getTime();
            Long fechaF= licencia.getFechaHoraRetorno().getTime();
            if(fechaF <= fechaI) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor a la fecha de inicio");
            } else {
                Long dias= (fechaF-fechaI)/(1000*60 *60*24);
                BigDecimal days= new BigDecimal(dias);
                if(days.compareTo(licencia.getDiasDisponibles())==1) {
                    licencia.setFechaHoraRetorno(null);
                    FacesUtil.showErrorMessage("Error", "Los días no pueden exceder del total disponible");
                } else if(days.compareTo(new BigDecimal(2))==-1) {
                    licencia.setFechaHoraRetorno(null);
                    FacesUtil.showErrorMessage("Error", "Los días no pueden ser menores de 3 para licencia");
                } else {
                    String result= licenciaServicio.obtenerDiasDisponibles(licencia.getUsuario().getCodigoUsuario(),
                            DateUtil.obtenerFechaHoraConFormato(licencia.getFechaHoraSalida()),
                            DateUtil.obtenerFechaHoraConFormato(licencia.getFechaHoraRetorno()));
                    if(result!=null) {
                        obtenerDiasDisponibles(result);
                    }
                }
            }
        }
    }
    
    public void validarFechas() {
        if(licencia.getTipoFormulario().equals(catalogoDetalleServicio.obtenerPorNemonico("TIPOFORPEROCA").get(0))) {
            Calendar ci= Calendar.getInstance();
            Calendar cf= Calendar.getInstance();
            ci.setTime(licencia.getFechaHoraSalida());
            cf.setTime(licencia.getFechaHoraRetorno());
            
            if(cf.get(Calendar.MONTH) < ci.get(Calendar.MONTH)) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor o igual a la fecha de inicio");
            } else if(cf.get(Calendar.MONTH) == ci.get(Calendar.MONTH)) {
                if(cf.get(Calendar.DAY_OF_MONTH) < ci.get(Calendar.DAY_OF_MONTH)) {
                    licencia.setFechaHoraRetorno(null);
                    FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor o igual a la fecha de inicio");
                }
            }
        } else {
            Long fechaI= licencia.getFechaHoraSalida().getTime();
            Long fechaF= licencia.getFechaHoraRetorno().getTime();
            if(fechaF <= fechaI) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "La fecha de fin debe ser mayor a la fecha de inicio");
            }
        }
    }
    
    public String saveSolicitudAction() {
        Calendar c= Calendar.getInstance();
        c.setTime(licencia.getFechaHoraSalida());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 8);
            licencia.setFechaHoraSalida(c.getTime());
        }

        c.setTime(licencia.getFechaHoraRetorno());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 17);
            licencia.setFechaHoraRetorno(c.getTime());
        }
        
        Long fechaI= licencia.getFechaHoraSalida().getTime();
        Long fechaF= licencia.getFechaHoraRetorno().getTime();
        if(fechaF <= fechaI) {
            licencia.setFechaHoraRetorno(null);
            FacesUtil.showErrorMessage("Error", "La fecha y hora de fin debe ser mayor a la fecha y hora de inicio");
            return null;
        }
        
        if(licencia.getTipoFormulario().equals(catalogoDetalleServicio.obtenerPorNemonico("TIPOFORSOLIC").get(0))) {        
            if(existeRangoFechas()) {
                FacesUtil.showErrorMessage("Error", "Ya existe una solicitud dentro de este rango de fechas");
                return null;
            }
        } else {
            Long dias= (fechaF-fechaI)/(1000*60 *60*24);
            BigDecimal days= new BigDecimal(dias);
            if(days.compareTo(licencia.getDiasDisponibles())==1) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "Los días no pueden exceder del total disponible");
                return null;
            } else if(days.compareTo(new BigDecimal(1))==1) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "Los días no pueden exceder de 2 para permiso ocasional");
                return null;
            }
        }
        
        if(licencia.getTipoLicencia().getValor().equals("GRUPO_1")) {
            if(licencia.getDiasLicencia()==null && licencia.getSaldoVacaciones()==null) {
                FacesUtil.showErrorMessage("No se calculó el saldo de vacaciones", "Vuelva a seleccionar las fechas para realizar el cálculo");
                return null;
            }
        }
            
        licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0));
        licencia.setEstadoRegistro(true);
        licencia.setFechaCreacion(Calendar.getInstance().getTime());
        licencia.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));

        licenciaServicio.create(licencia);
        Auditoria a= new Auditoria(Auditoria.INSERT, licencia, new Licencia(), login.getCodigoUsuario());
        auditoriaServicio.create(a);
        try {
            guardarAdjuntos();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al cargar los archivos: " + ex.toString());
        }
        FacesUtil.showInfoMessage("Aviso", "Solicitud enviada correctamente");
        sendNewTaskMsg(contrato.getDepartamento().getJefe(), licencia);
        return resetAction();
    }
    
    private void cargarHistorial() {
        if(login.getCodigoUsuario()!=null ){
            historial= licenciaServicio.listarSolicitudesExcluyendoEstado(login.getCodigoUsuario(), catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0));
        }
    }
    
    private void cargarTareas() {
        if(login.getCodigoUsuario()!=null) {
            boolean esJefe= comprobarEsJefe();
            if(esJefe) {
                tareas= licenciaServicio.listarTareasJefe(login.getCodigoUsuario(), catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0));
            } else {
                boolean esAsistenteTH= comprobarEsAsistenteTH();
                if(esAsistenteTH) {
                    tareas= licenciaServicio.listarTareasTH(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0));
                    for (Licencia tarea : tareas) {
                        tarea.setContrato(contratoServicio.listarPorUsuario(tarea.getUsuario()).get(0));
                    }
                } else {
                    tareas= licenciaServicio.listarTareasFuncionario(login.getCodigoUsuario(), catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0));
                }
            }
        } else {
            redirectToLogin();
        }
    }
    
    private boolean comprobarEsJefe() {
        if(login.getCodigoUsuario()!=null) {
            List<Departamento> oficinas= departamentoServicio.listar();
            for(Departamento o : oficinas) {
                if(login.getCodigoUsuario().compareTo(o.getJefe().getCodigoUsuario())==0) {
                    jefatura= o.getCodigoDepartamento();
                    return true;
                }
            }
        } 
        jefatura= (long) 0;
        return false;
    }
    
    private boolean comprobarEsAsistenteTH() {
        UsuarioRol ur= usuarioRolServicio.obtenerPorCodigoUsuuario(login.getCodigoUsuario());
        return ur!=null && ur.getRol().getNemonico().equals("ASISTH");
    }
    
    public String visualizarTarea(Licencia l) {
        this.licencia= l;
        this.cantones= null;this.parroquias= null;
        getCantones();getParroquias();
        motivoLicencia= licencia.getTipoLicencia().getNombre();
        contrato= contratoServicio.listarPorUsuario(licencia.getUsuario()).get(0);
        archivosCargados= obtenerArchivosCargados(licencia);
        archivosParaCargar= new ArrayList<>();
        if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERVAC").get(0))) {
            showVacacionPanel= true;
            showCalamidadPanel= false;
            showInstitucionalPanel= false;
            fechaMinima= Calendar.getInstance().getTime();
        } else if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERINS").get(0))) {
            showVacacionPanel= false;
            showCalamidadPanel= false;
            showInstitucionalPanel= true;
            Calendar c= Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -15);
            fechaMinima= c.getTime();
        } else {
            showVacacionPanel= false;
            showCalamidadPanel= true;
            showInstitucionalPanel= false;
            Calendar c= Calendar.getInstance();
            if(licencia.getTipoLicencia().getNemonico().equals("MOTPERENF") || licencia.getTipoLicencia().getNemonico().equals("MOTPERCAL")) {
                c.add(Calendar.DAY_OF_MONTH, -15);
            }
            fechaMinima= c.getTime();
        }
        
        if(licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0))) {
            this.aprobado= true;
            this.subsanar= false;
            return "vacaciones-licencia-jefe-frm";
        } else if(licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0))) {
            this.aprobado= false;
            this.subsanar= true;
            if(licencia.getTipoFormulario().getNemonico().equals("TIPOFORSOLIC")) {
                calendarPattern= "dd-MM-yyyy";
            } else {
                calendarPattern= "dd-MM-yyyy HH:mm";
            }
            return "vacaciones-licencia-subsanar-frm";
        } else {
            this.aprobado= true;
            this.subsanar= false;
        }
        return "vacaciones-licencia-th-frm";
    }
    
    public String saveAprobacionJefeAction() {
        Licencia anterior= licenciaServicio.findByPk(licencia.getCodigoLicencia());
        if(aprobado) {
            licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0));
            sendNewTaskMsgTH(licencia);
            sendNotificationMsg(licencia.getUsuario(), licencia, "APROBADA");
        } else if(!aprobado && subsanar) {
            licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0));
            if(licencia.getObservacionesJefatura()==null || licencia.getObservacionesJefatura().length()==0) {
                FacesUtil.showErrorMessage("Error", "Debe ingresar una observación");
                return null;
            }
            sendNewTaskMsg(licencia.getUsuario(), licencia);
        } else {
            licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTARCHIV").get(0));
            if(licencia.getObservacionesJefatura()==null || licencia.getObservacionesJefatura().length()==0) {
                FacesUtil.showErrorMessage("Error", "Debe ingresar una observación");
                return null;
            }
            sendNotificationMsg(licencia.getUsuario(), licencia, "RECHAZADA");
        }
        Usuario u= usuarioServicio.findByPk(login.getCodigoUsuario());
        licencia.setUsuarioAprobacion(u);
        licencia.setFechaModificacion(Calendar.getInstance().getTime());
        licencia.setUsuarioModificacion(u);
        licenciaServicio.update(licencia);
        Auditoria a= new Auditoria(Auditoria.UPDATE, licencia, anterior, u.getCodigoUsuario());
        auditoriaServicio.create(a);
        FacesUtil.showInfoMessage("Aviso", "Tarea realizada correctamente");
        return resetAction();
    }
    
    public String obtenerNombreEstado(CatalogoDetalle estado) {
        if(estado.equals(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0))) {
            return "TRAMITE";
        } else if(estado.equals(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0))) {
            return "APROBADO";
        } else if(estado.equals(catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0))) {
            return "SUBSANACIÓN";
        } else if(estado.equals(catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0))) {
            return "FINALIZADO";
        }else {
            return "NO APROBADO";
        }
    }
    
    public String saveSubsanacionFuncionarioAction() {
        Licencia anterior= licenciaServicio.findByPk(licencia.getCodigoLicencia());
        Long fechaI= licencia.getFechaHoraSalida().getTime();
        Long fechaF= licencia.getFechaHoraRetorno().getTime();
            
        if(fechaF <= fechaI) {
            licencia.setFechaHoraRetorno(null);
            FacesUtil.showErrorMessage("Error", "La fecha y hora de fin debe ser mayor a la fecha y hora de inicio");
            return null;
        }
        
        if(existeRangoFechas()) {
            FacesUtil.showErrorMessage("Error", "Ya existe una solicitud dentro de este rango de fechas");
            return null;
        }
        
        licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0));
        licencia.setFechaModificacion(Calendar.getInstance().getTime());
        licencia.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
        
        Calendar c= Calendar.getInstance();
        c.setTime(licencia.getFechaHoraSalida());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 8);
            licencia.setFechaHoraSalida(c.getTime());
        }

        c.setTime(licencia.getFechaHoraRetorno());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 17);
            licencia.setFechaHoraRetorno(c.getTime());
        }
        licenciaServicio.update(licencia);
        Auditoria a= new Auditoria(Auditoria.UPDATE, licencia, anterior, login.getCodigoUsuario());
        auditoriaServicio.create(a);
        try {
            guardarAdjuntos();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al cargar los archivos: " + ex.toString());
        }
        FacesUtil.showInfoMessage("Aviso", "Tarea realizada correctamente");
        sendNewTaskMsg(contrato.getDepartamento().getJefe(), licencia);
        return resetAction();
    }
    
    private boolean existeRangoFechas() {
        List<CatalogoDetalle> estadosExcluir= new ArrayList<>();
        estadosExcluir.add(catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0));
        estadosExcluir.add(catalogoDetalleServicio.obtenerPorNemonico("ESTARCHIV").get(0));
        List<Licencia> solicitudesVigentes= licenciaServicio.listarSolicitudesExcluyendoEstado(login.getCodigoUsuario(), estadosExcluir);

        for(Licencia l : solicitudesVigentes) {
            if((licencia.getFechaHoraSalida().getTime() >= l.getFechaHoraSalida().getTime() && licencia.getFechaHoraSalida().getTime() <= l.getFechaHoraRetorno().getTime()) || 
                    (licencia.getFechaHoraRetorno().getTime() >= l.getFechaHoraSalida().getTime() && licencia.getFechaHoraRetorno().getTime() <= l.getFechaHoraRetorno().getTime())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean allowPrint(Licencia l) {
        return l.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0));
    }
    
    public String obtenerFormatoDecimal(BigDecimal valor) {
        DecimalFormat df= new DecimalFormat("#.##");
        if(valor!=null) {
            return df.format(valor);
        }
        return "";
    }
    
    private Long obtenerSecuenciaSolicitud() {
        Secuencia secuenciaLicencia= secuenciaServicio.obtenerPorNemonico("SOLICITUD_VACACIONES");
        Long codigoLicenciaSiguiente = secuenciaLicencia.getValor();
        secuenciaLicencia.setValor(codigoLicenciaSiguiente + 1);
        secuenciaServicio.update(secuenciaLicencia);
        return codigoLicenciaSiguiente;
    }
    
    public void addArchivos(FileUploadEvent event) {
        showUploadPanel= true;
        boolean existe= false;
        for(UploadedFile f : archivosParaCargar) {
            if(f.getFileName().equals(event.getFile().getFileName())) {
                existe= true;
            }
        }
        if(!existe) {
            archivosParaCargar.add(event.getFile());
        } else {
            FacesUtil.showErrorMessage("Error", "El archivo ya se encuentra en la lista");
        }
        RequestContext.getCurrentInstance().execute("PF('archivosfrmwg').hide();");
    }
    
    public String saveLegalizacionTHAction() {
        Licencia anterior= licenciaServicio.findByPk(licencia.getCodigoLicencia());
        if(archivosParaCargar.size()>0) {
            licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0));
            licencia.setFechaModificacion(Calendar.getInstance().getTime());
            licencia.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            licenciaServicio.update(licencia);
            Auditoria a= new Auditoria(Auditoria.UPDATE, licencia, anterior, login.getCodigoUsuario());
            auditoriaServicio.create(a);
            if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERVAC").get(0))) {
                Usuario usrlogged= usuarioServicio.findByPk(login.getCodigoUsuario());
                GestionVacacion gvOld= gestionVacacionServicio.findByUser(licencia.getUsuario().getCodigoUsuario());
                GestionVacacion gvAnterior= gestionVacacionServicio.findByUser(licencia.getUsuario().getCodigoUsuario());
                GestionVacacion gv= gvOld;
                gvOld.setEstadoRegistro(false);
                gvOld.setFechaModificacion(Calendar.getInstance().getTime());
                gvOld.setUsuarioModificacion(usrlogged);
                gestionVacacionServicio.update(gvOld);
                a= new Auditoria(Auditoria.UPDATE, gvOld, gvAnterior, login.getCodigoUsuario());
                auditoriaServicio.create(a);
                
                gv.setSaldoAnterior(gv.getSaldoActual());
                //gv.setSaldoActual(licencia.getSaldoVacaciones());
                //Restar los dias de la licencia del saldo actual de gestion vacacion a la fecha.
                gv.setSaldoActual(gv.getSaldoActual().subtract(licencia.getDiasLicencia()));
                gv.setLicencia(licencia);
                gv.setDiasIncrementados(null);
                gv.setDiasDecrementados(licencia.getDiasLicencia());
                gv.setEstadoRegistro(true);
                gv.setFechaCreacion(Calendar.getInstance().getTime());
                gv.setUsuarioCreacion(usrlogged);
                gestionVacacionServicio.create(gv);
                a= new Auditoria(Auditoria.INSERT, gv, new GestionVacacion(), login.getCodigoUsuario());
                auditoriaServicio.create(a);
            }
            try {
                guardarAdjuntos();
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al cargar los archivos: " + ex.toString());
            }
            FacesUtil.showInfoMessage("Aviso", "Tarea realizada correctamente");
            sendNotificationMsg(licencia.getUsuario(), licencia, "LEGALIZADA Y FINALIZADA");
            return resetAdminAction();
        }
        FacesUtil.showErrorMessage("Error", "Debe adjuntar almenos un archivo");
        return null;
    }
    
    private void guardarAdjuntos() {
        List<Adjunto> filesForSave= new ArrayList();
        for(UploadedFile f : archivosParaCargar) {
            Adjunto adj= subirArchivoRepositorio(f, "LICENCIA_VACACION", String.valueOf(licencia.getCodigoLicencia()), "LICENCIA_VACACION");
            if(adj!=null) {
                filesForSave.add(adj);
            }
        }
        
        if(filesForSave.size()>0) {
            for(Adjunto a : filesForSave) {
                Secuencia secuenciaAdjunto= secuenciaServicio.obtenerPorTabla("ADJUNTO");
                Long value= secuenciaAdjunto.getValor();
                a.setCodigoAdjunto(value);
                adjuntoServicio.create(a);
                secuenciaAdjunto.setValor(value + 1);
                secuenciaServicio.update(secuenciaAdjunto);
            }
        }
    }
    
    public Adjunto subirArchivoRepositorio(UploadedFile upFile, String tipoTramite, String idTramite, String tramite) {
        Adjunto adjunto = null;
        if (upFile != null) {
            try {
                //Invocar a Alfresco                
                List<String> carpetas = new ArrayList<>();
                carpetas.add(tipoTramite);
                carpetas.add(idTramite);

                AlfrescoMimeType mt = AlfrescoFileUtil.getAlfrescoMimeType(upFile.getContentType());

                AlfrescoFileBean afb = new AlfrescoFileBean();
                File f = AlfrescoFileUtil.streamToFile(upFile.getInputstream(), upFile.getFileName(), mt.getCode());
                afb.setStream(upFile.getInputstream());
                afb.setMimeType(upFile.getContentType());
                afb.setName(upFile.getFileName().replace("%", ""));
                afb.setFullName(upFile.getFileName().replace("%", ""));
                afb.setFile(f);

                AlfrescoDocumentBean adb = AlfrescoService.uploadDocument(carpetas, afb);

                adjunto = new Adjunto();
                adjunto.setFechaCreacion(Calendar.getInstance().getTime());
                adjunto.setUsuarioCreacion(login.getCodigoUsuario());
                adjunto.setEstadoRegistro(Boolean.TRUE);
                adjunto.setExtensionAdjunto(mt.getCode());
                adjunto.setNombreAdjunto(upFile.getFileName().replace("%", ""));
                adjunto.setUrlDocumento(adb.getDocumentUrl());
                adjunto.setIdDocumento(adb.getDocumentId());
                adjunto.setTipoDocumento(tipoTramite);
                adjunto.setCodigoTramite(new Long(idTramite));
                adjunto.setTramite(tramite);
            } catch (Exception ex) {
                System.out.println("Ocurrio un error: ");
                System.out.println(ex.toString());
            }
        }
        return adjunto;
    }
    
    public void showSolicitudAction(Licencia l) {
        this.licencia= l;
        motivoLicencia= licencia.getTipoLicencia().getNombre();
        showHourFields();
        
        if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERVAC").get(0))) {
            showVacacionPanel= true;
            showCalamidadPanel= false;
            showInstitucionalPanel= false;
        } else if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERINS").get(0))) {
            showVacacionPanel= false;
            showCalamidadPanel= false;
            showInstitucionalPanel= true;
        } else {
            showVacacionPanel= false;
            showCalamidadPanel= true;
            showInstitucionalPanel= false;
        }
        
        if(licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0))) {
            this.aprobado= false;
            this.subsanar= false;
        } else if(licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0)) ||
                  licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0))) {
            this.aprobado= true;
            this.subsanar= false;
        } else if(licencia.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTNOTOR").get(0))) {
            this.aprobado= false;
            this.subsanar= true;
        } else {
            this.aprobado= false;
            this.subsanar= false;
        }
        
        archivosCargados= obtenerArchivosCargados(licencia);
        RequestContext.getCurrentInstance().execute("PF('historialfrmwg').show();");
    }
    
    private List<Adjunto> obtenerArchivosCargados(Licencia l) {
        return adjuntoServicio.findByLicencia(l);
    }
    
    public void imprimirSolicitudPDF(Licencia l) {
        urlFormatoImprimir = ConstantesEnum.URL_PROD_REPORTES.getDescripcion();
        if (l != null && l.getTipoLicencia().getValor().equals("GRUPO_1")) {
            urlFormatoImprimir = urlFormatoImprimir + "/birt/frameset?__report=report/vacaciones/solicitud-vacacion.rptdesign&codigo_licencia="
                    + l.getCodigoLicencia() + "&__format=pdf";
        } else if (l != null && l.getTipoLicencia().getValor().equals("GRUPO_2")) {
            urlFormatoImprimir = urlFormatoImprimir + "/birt/frameset?__report=report/vacaciones/solicitud-calamidad-domestica.rptdesign&codigo_licencia="
                    + l.getCodigoLicencia() + "&__format=pdf";
        } else if (l != null && l.getTipoLicencia().getValor().equals("GRUPO_3")) {
            urlFormatoImprimir = urlFormatoImprimir + "/birt/frameset?__report=report/vacaciones/solicitud-asunto-institucional.rptdesign&codigo_licencia="
                    + l.getCodigoLicencia() + "&__format=pdf";
        }
        System.out.println(urlFormatoImprimir);
    }

    public boolean showDesistirOption(Licencia l) {
        if(l.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTENTRA").get(0)) ||
                l.getEstadoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("ESTOTOR").get(0))) {
            return true;
        }
        return false;
    }
    
    public void showDesistirAction(Licencia l) {
        this.licencia= l;
        this.observacionDesistir= "";
        RequestContext.getCurrentInstance().execute("PF('desistirfrmwg').show();");
    }
    
    public void saveDesistimientoAction() {
        Licencia anterior= licenciaServicio.findByPk(licencia.getCodigoLicencia());
        Usuario usr= usuarioServicio.findByPk(login.getCodigoUsuario());
        String observacionFinal= licencia.getObservaciones() + " \n \n Desistimiento por: \n " + observacionDesistir + " \n Funcionario: \n " + usr.getNumeroDocumento() + " \n " + usr.getNombresCompletos();
        this.licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTARCHIV").get(0));
        this.licencia.setObservaciones(observacionFinal);
        licenciaServicio.update(licencia);
        Auditoria a= new Auditoria(Auditoria.UPDATE, licencia, anterior, login.getCodigoUsuario());
        auditoriaServicio.create(a);
        RequestContext.getCurrentInstance().execute("PF('desistirfrmwg').hide();");
        FacesUtil.showInfoMessage("Aviso", "Desistimiento realizado correctamente");
    }
    
    public boolean showTramitesAtendidosMenu() {
        return comprobarEsJefe();
    }
    
    public void showTramitesAtendidosAction() {
        tramitesAtendidos= licenciaServicio.listarTramitesAtendidos(login.getCodigoUsuario());
    }
    
    public void showTramitesAtendidosTHAction() {
        tramitesAtendidos= licenciaServicio.listarTramitesAtendidosTH(catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0));
    }
    
    private void sendNewTaskMsg(Usuario destinatario, Licencia l) {
        MailSender ms= new MailSender();
        try {
            ms.sendMailHTML("Notificación nueva tarea", ms.getNewTaskMsg(destinatario.getNombresCompletos(), l.getNumeroSolicitud().toString(), l.getUsuario().getNombresCompletos()), destinatario);
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al enviar el correo: " + ex.toString());
        }
    }
    
    private void sendNewTaskMsgTH(Licencia l) {
        Rol rol= rolServicio.findByNemonico("ASISTH");
        List<UsuarioRol> usuariosRol= usuarioRolServicio.listByRol(rol);
        for(UsuarioRol ur : usuariosRol) {
            sendNewTaskMsg(ur.getUsuario(), l);
        }
    }
    
    private void sendNotificationMsg(Usuario destinatario, Licencia l, String estadoSolicitud) {
        MailSender ms= new MailSender();
        try {
            ms.sendMailHTML("Notificación solicitud", ms.getNotificationMsg(destinatario.getNombresCompletos(), l.getNumeroSolicitud().toString(), estadoSolicitud), destinatario);
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al enviar el correo: " + ex.toString());
        }
    }
    
    public String newIngresoLicenciaAction() {
        if(login.getCodigoUsuario()!=null) {
            documentoBuscar= "";
            licencia= new Licencia();
            licencia.setNumeroSolicitud(obtenerSecuenciaSolicitud());
            showVacacionPanel= false;
            showCalamidadPanel= false;
            showInstitucionalPanel= false;
            showHour= false;
            showButtonPanel= false;
            showDatosPersonalesPanel= false;
            archivosCargados= new ArrayList<>();
            archivosParaCargar= new ArrayList<>();
            return "vacaciones-licencia-ingreso-th-frm";
        } else {
            redirectToLogin();
        }
        return null;
    }
    
    public void buscarUsuarioIngresarSolicitud() {
        if(documentoBuscar.trim().length()>0) {
            Usuario usuarioIngresarSolicitud= usuarioServicio.findByDocumento(documentoBuscar.trim());
            if(usuarioIngresarSolicitud!=null) {
                contratos= contratoServicio.listarPorUsuario(usuarioIngresarSolicitud);
                if(contratos.size()>0) {
                    setFuncionarioNuevaLicenciaTH(usuarioIngresarSolicitud);
                } else  {
                    FacesUtil.showErrorMessage("Error", "El funcionario aun no tiene un contrato");
                }
            } else {
                FacesUtil.showWarnMessage("Busqueda finalizada", "No se encontro un usuario con este número de documento");
                licencia.setUsuario(null);
                showDatosPersonalesPanel= false;
            }
        } else {
            inicializarListasBusquedaFuncionario();
            RequestContext.getCurrentInstance().execute("PF('busquedafrmwg').show();");
        }
    }
    
    private void setFuncionarioNuevaLicenciaTH(Usuario usr) {
        licencia.setUsuario(usr);
        licencia.setDiasDisponibles(obtenerDiasDisponibles(usr.getCodigoUsuario()));
        contrato= contratoServicio.listarPorUsuario(usr).get(0);
        FacesUtil.showInfoMessage("Busqueda correcta", "Se encontro el usuario");
        showDatosPersonalesPanel= true;
    }
    
    public String saveIngresoLicenciaAction() {
        Calendar c= Calendar.getInstance();
        c.setTime(licencia.getFechaHoraSalida());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 8);
            licencia.setFechaHoraSalida(c.getTime());
        }

        c.setTime(licencia.getFechaHoraRetorno());
        if(c.get(Calendar.HOUR_OF_DAY)==0) {
            c.set(Calendar.HOUR_OF_DAY, 17);
            licencia.setFechaHoraRetorno(c.getTime());
        }
        
        Long fechaI= licencia.getFechaHoraSalida().getTime();
        Long fechaF= licencia.getFechaHoraRetorno().getTime();
        if(fechaF <= fechaI) {
            licencia.setFechaHoraRetorno(null);
            FacesUtil.showErrorMessage("Error", "La fecha y hora de fin debe ser mayor a la fecha y hora de inicio");
            return null;
        }
        
        if(licencia.getTipoFormulario().equals(catalogoDetalleServicio.obtenerPorNemonico("TIPOFORSOLIC").get(0))) {        
            if(existeRangoFechas()) {
                FacesUtil.showErrorMessage("Error", "Ya existe una solicitud dentro de este rango de fechas");
                return null;
            }
        } else {
            Long dias= (fechaF-fechaI)/(1000*60 *60*24);
            BigDecimal days= new BigDecimal(dias);
            if(days.compareTo(licencia.getDiasDisponibles())==1) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "Los días no pueden exceder del total disponible");
                return null;
            } else if(days.compareTo(new BigDecimal(1))==1) {
                licencia.setFechaHoraRetorno(null);
                FacesUtil.showErrorMessage("Error", "Los días no pueden exceder de 2 para permiso ocasional");
                return null;
            }
        }
        
        licencia.setEstadoLicencia(catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0));
        licencia.setEstadoRegistro(true);
        licencia.setFechaCreacion(Calendar.getInstance().getTime());
        licencia.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
        licenciaServicio.create(licencia);
        Auditoria a= new Auditoria(Auditoria.INSERT, licencia, new Licencia(), login.getCodigoUsuario());
        auditoriaServicio.create(a);
        if(licencia.getTipoLicencia().equals(catalogoDetalleServicio.obtenerPorNemonico("MOTPERVAC").get(0))) {
            Usuario usrlogged= usuarioServicio.findByPk(login.getCodigoUsuario());
            GestionVacacion gvOld= gestionVacacionServicio.findByUser(licencia.getUsuario().getCodigoUsuario());
            GestionVacacion gvAnterior= gestionVacacionServicio.findByUser(licencia.getUsuario().getCodigoUsuario());
            GestionVacacion gv= gvOld;
            gvOld.setEstadoRegistro(false);
            gvOld.setFechaModificacion(Calendar.getInstance().getTime());
            gvOld.setUsuarioModificacion(usrlogged);
            gestionVacacionServicio.update(gvOld);
            a= new Auditoria(Auditoria.UPDATE, gvOld, gvAnterior, login.getCodigoUsuario());
            auditoriaServicio.create(a);

            gv.setSaldoAnterior(gv.getSaldoActual());;
            gv.setSaldoActual(licencia.getSaldoVacaciones());
            gv.setLicencia(licencia);
            gv.setDiasIncrementados(null);
            gv.setDiasDecrementados(licencia.getDiasLicencia());
            gv.setEstadoRegistro(true);
            gv.setFechaCreacion(Calendar.getInstance().getTime());
            gv.setUsuarioCreacion(usrlogged);
            gestionVacacionServicio.create(gv);
            a= new Auditoria(Auditoria.INSERT, gv, new GestionVacacion(), login.getCodigoUsuario());
            auditoriaServicio.create(a);
        }
        
        try {
            guardarAdjuntos();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al cargar los archivos: " + ex.toString());
        }
        FacesUtil.showInfoMessage("Aviso", "Solicitud creada correctamente");
        return resetAdminAction();
    }
    
    public void newContratoAction() {
        saldoVacacionContrato= new BigDecimal(0);
        if(usuarioEditar!=null && usuarioEditar.getCodigoUsuario()!=null) {
            if(contratos.isEmpty()) {
                this.contrato= new Contrato();
                RequestContext.getCurrentInstance().execute("PF('contratofrmwg').show();");
            } else {
                FacesUtil.showErrorMessage("Error", "El usuario ya tiene un contrato");
            }
        } else {
            FacesUtil.showErrorMessage("Error", "Primero debe buscar un usuario");
        }
    }
    
    public void addContratoAction() {
        if(contratos==null) {
            contratos= new ArrayList<>();
        }
        contratos.add(contrato);
        RequestContext.getCurrentInstance().execute("PF('contratofrmwg').hide();");
        FacesUtil.showInfoMessage("Aviso", "Contrato agregado");
        System.out.println("Saldo vacaciones: " + saldoVacacionContrato);
    }
    
    public void deleteContratoAction(Contrato c) {
        if(c.getCodigoContrato()!=null) {
            FacesUtil.showWarnMessage("Error", "No puede eliminar este contrato, en su lugar puede finalizarlo");
        } else {
            contratos.clear();
            contrato= null;
        }
    }
    
    private void inicializarListasBusquedaFuncionario() {
        textoBuscar= "";
        funcionarios= usuarioServicio.listarUsuariosInternos();
        funcionariosBk= usuarioServicio.listarUsuariosInternos();
        for (Usuario func : funcionarios) {
            List<Contrato> contracts= contratoServicio.listarPorUsuario(func);
            if(contracts.size()>0) {
                func.setContrato(contracts.get(0));
            }
        }
        for (Usuario func : funcionariosBk) {
            List<Contrato> contracts= contratoServicio.listarPorUsuario(func);
            if(contracts.size()>0) {
                func.setContrato(contracts.get(0));
            }
        }
    }
    
    public void showVacacionesPorFuncionarioAction() {
        inicializarListasBusquedaFuncionario();
    }
    
    public void viewSaldosAction(Usuario funcionario) {
        saldos= licenciaServicio.listarLicenciasFinalizadasPorFuncionario(funcionario.getCodigoUsuario(), catalogoDetalleServicio.obtenerPorNemonico("ESTINSC").get(0));
        GestionVacacion gv= gestionVacacionServicio.findByUser(funcionario.getCodigoUsuario());
        if(gv!=null) {
            saldoActual= obtenerFormatoDecimal(gv.getSaldoActual());
            RequestContext.getCurrentInstance().execute("PF('saldosviewfrmwg').show();");        
        } else {
            FacesUtil.showErrorMessage("Error", "El funcionario aun no tiene un contrato");
        }
    }
    
    public void filtrarFuncionarios() {
        try {
            aplicarFiltro();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public String obtenerSaldoActualPorFuncionario(Usuario funcionario) {
        return obtenerSaldoActual(funcionario.getCodigoUsuario());
    }
    
    public String obtenerSaldoActual(Long codigoUsuario) {
        GestionVacacion gv= gestionVacacionServicio.findByUser(codigoUsuario);
        if(gv!=null) {
            return obtenerFormatoDecimal(gv.getSaldoActual());
        }
        return "";
    }
    
    private void aplicarFiltro() {
        funcionarios.clear();
        if(textoBuscar!=null && textoBuscar.length()>0) {
           for (Usuario func : funcionariosBk) {
                if(func.getNombresCompletos().toUpperCase().contains(textoBuscar.toUpperCase())) {
                    funcionarios.add(func);
                }
            }
        } else {
            for (Usuario func : funcionariosBk) {
                funcionarios.add(func);
            }
        }
    }
    
    public void setFuncionarioEditar(Usuario usr) {
        usuarioEditar= usr;
        contratos= contratoServicio.listarPorUsuario(usuarioEditar);
        if(contratos.size()>0) {
            contrato= contratos.get(0);
        } else {
            contrato= null;
        }
        showButtonPanel02= true;
        FacesUtil.showInfoMessage("Busqueda correcta", "Se encontro el usuario");
        RequestContext.getCurrentInstance().execute("PF('busquedafrmwg').hide();");
    }
    
    public void setFuncionarioIngresarLicencia(Usuario usr) {
        contratos= contratoServicio.listarPorUsuario(usr);
        if(contratos.size()>0) {
            setFuncionarioNuevaLicenciaTH(usr);
            RequestContext.getCurrentInstance().execute("PF('busquedafrmwg').hide();");
        } else  {
            FacesUtil.showErrorMessage("Error", "El funcionario aun no tiene un contrato");
        }
    }
    
    public void finalizarContratoAction() {
        if(contrato.getCodigoContrato()!=null) {
            //Obtener los estados a excluir: 
            List<CatalogoDetalle> estadosExcluir= new ArrayList<>();
            
            Catalogo catalogo = catalogoServicio.findByNemonico("ESTAREA");
            if (catalogo != null) {
                List<CatalogoDetalle> catalogos = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for(CatalogoDetalle catdet : catalogos) {
                    String nemonico= catdet.getNemonico();
                    if(nemonico.equals("ESTINSC") ||  nemonico.equals("ESTARCHIV")) {
                        estadosExcluir.add(catdet);
                    }
                }
            }
            List<Licencia> solicitudesVigentes= licenciaServicio.listarSolicitudesExcluyendoEstado(contrato.getUsuario().getCodigoUsuario(), estadosExcluir);
            
            if(solicitudesVigentes.size()>0) {
                FacesUtil.showErrorMessage("Error", "No fuede finalizar este contrato, mientras aun hay solicitudes vigentes");
            } else {
                GestionVacacion gv= gestionVacacionServicio.findByUser(usuarioEditar.getCodigoUsuario());
                contrato.setSaldoVacaciones(gv.getSaldoActual());
                RequestContext.getCurrentInstance().execute("PF('endcontratofrmwg').show();");
            }
        } else {
            FacesUtil.showErrorMessage("Error", "No fuede finalizar este contrato, en su lugar puede eliminarlo");
        }
    }
    
    public void confirmarEliminarContrato() {
        RequestContext.getCurrentInstance().execute("PF('endcontratofrmwg').hide();");
        RequestContext.getCurrentInstance().execute("PF('enddlgwg').show();");
    }
    
    public void finalizarContrato() {
        Contrato cAnterior= contratoServicio.findByPk(contrato.getCodigoContrato());
        contrato.setEstadoRegistro(false);
        GestionVacacion gv= gestionVacacionServicio.findByUser(usuarioEditar.getCodigoUsuario());
        GestionVacacion gvAnterior= gestionVacacionServicio.findByUser(usuarioEditar.getCodigoUsuario());
        gv.setEstadoRegistro(false);
        contratoServicio.update(contrato);
        gestionVacacionServicio.update(gv);
        Auditoria a= new Auditoria(Auditoria.UPDATE, contrato, cAnterior, login.getCodigoUsuario());
        auditoriaServicio.create(a);
        a= new Auditoria(Auditoria.UPDATE, gv, gvAnterior, login.getCodigoUsuario());
        auditoriaServicio.create(a);
        
        contratos= contratoServicio.listarPorUsuario(usuarioEditar);
        if(contratos.size()>0) {
            contrato= contratos.get(0);
        } else {
            contrato= null;
        }
    }
}
