package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.alfresco.bean.AlfrescoDocumentBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFileBean;
import ec.gob.arcom.migracion.alfresco.util.AlfrescoFileUtil;
import ec.gob.arcom.migracion.alfresco.AlfrescoMimeType;
import ec.gob.arcom.migracion.alfresco.service.AlfrescoService;
import ec.gob.arcom.migracion.constantes.RolEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.modelo.Adjunto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.DetalleOperativo;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Rol;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.AdjuntoServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.DetalleOperativoServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.MaquinariaConcesionServicio;
import ec.gob.arcom.migracion.servicio.OperativoServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.RolServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.TipoMaquinariaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mejiaw
 */
@ManagedBean
@ViewScoped
public class OperativoCtrl extends BaseCtrl {
    public static final String TIPOINSTITUCION= "TIPOINFINSTPART";
    public static final String TIPODETENIDO= "TIPOINFDETOPE";
    public static final String TIPODEPOSITARIO= "TIPOINFDEPMAQ";
    public static final String INSERT= "INSERT";
    public static final String UPDATE=  "UPDATE";
    public static final String DELETE= "DELETE";
    
    
    @EJB
    private OperativoServicio operativoServicio;
    @EJB
    private DetalleOperativoServicio detalleOperativoServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private RolServicio rolServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private TipoMaquinariaServicio tipoMaquinariaServicio;
    @EJB
    private MaquinariaConcesionServicio maquinariaConcesionServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private AdjuntoServicio adjuntoServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    
    private Date fechaMaxima;
    
    private List<Operativo> operativos;
    private List<Operativo> filteredOperativos;
    private Operativo operativo;
    
    private List<DetalleOperativo> detallesOperativo;
    private DetalleOperativo detalleOperativo;
    private MaquinariaConcesion maquinaria;
    private List<MaquinariaConcesion> maquinarias;
    
    private List<SelectItem> zonas;
    private List<SelectItem> tiposOperativo;
    private List<SelectItem> formasExplotacion;
    private List<SelectItem> tiposMaterial;
    private List<SelectItem> regionales;
    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;
    private List<SelectItem> tecnicos;
    private List<SelectItem> legales;
    private List<SelectItem> tiposInstitucion;
    private List<SelectItem> tiposMaquinaria;
    private List<SelectItem> estadosMaquinaria;
    private List<SelectItem> tiposDepositario;
    private List<SelectItem> tiposSello;
    private List<SelectItem> estadosProcedimiento;
    
    private boolean edit= false;
    
    
    private List<Adjunto> archivosCargados;
    private List<UploadedFile> archivosParaCargar;
    private boolean showUploadPanel= false;
    
    private List<DataTableColumn> columns;
    private List<ReportData> rows;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    /**
     * Creates a new instance of OperativoCtrl
     */
    public OperativoCtrl() {
        operativo= new Operativo();
        detallesOperativo= new ArrayList<>();
        detalleOperativo= new DetalleOperativo();
        maquinaria= new MaquinariaConcesion();
        maquinaria.setEstadoMaquinaria(BigInteger.ZERO);
        columns= new ArrayList<>();
        rows= new ArrayList<>();
    }
    
    @PostConstruct
    private void inicializar() {
        obtenerOperativos();
    }

    public List<Operativo> getFilteredOperativos() {
        return filteredOperativos;
    }

    public void setFilteredOperativos(List<Operativo> filteredOperativos) {
        this.filteredOperativos = filteredOperativos;
    }

    public List<DataTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTableColumn> columns) {
        this.columns = columns;
    }

    public List<ReportData> getRows() {
        return rows;
    }

    public void setRows(List<ReportData> rows) {
        this.rows = rows;
    }

    public List<Operativo> getOperativos() {
        return operativos;
    }

    public void setOperativos(List<Operativo> operativos) {
        this.operativos = operativos;
    }

    public Operativo getOperativo() {
        return operativo;
    }

    public void setOperativo(Operativo operativo) {
        this.operativo = operativo;
    }

    public DetalleOperativo getDetalleOperativo() {
        return detalleOperativo;
    }

    public void setDetalleOperativo(DetalleOperativo detalleOperativo) {
        this.detalleOperativo = detalleOperativo;
    }

    public MaquinariaConcesion getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(MaquinariaConcesion maquinaria) {
        this.maquinaria = maquinaria;
    }
    
    public List<MaquinariaConcesion> getMaquinarias() {
        return maquinarias;
    }

    public void setMaquinarias(List<MaquinariaConcesion> maquinarias) {
        this.maquinarias = maquinarias;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }
    
    ////////////////////////
    public void obtenerOperativos() {
        this.operativos= operativoServicio.listar();
    }
    
    public Date getFechaMaxima() {
        this.fechaMaxima= Calendar.getInstance().getTime();
        return fechaMaxima;
    }
    
    public List<SelectItem> getTiposInstitucion() {
        if (tiposInstitucion == null) {
            tiposInstitucion = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOINSTOPE");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposInstitucion.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return tiposInstitucion;
    }
    
    public List<SelectItem> getZonas() {
        if (zonas == null) {
            zonas = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("ZONGEO");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    zonas.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return zonas;
    }
    
    public List<SelectItem> getTiposOperativo() {
        if (tiposOperativo == null) {
            tiposOperativo = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOPERA");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposOperativo.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return tiposOperativo;
    }
    
    public List<SelectItem> getFormasExplotacion() {
        if (formasExplotacion == null) {
            formasExplotacion = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("SISTEXPLOTA");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    formasExplotacion.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return formasExplotacion;
    }
    
    public List<SelectItem> getTiposMaterial() {
        if (tiposMaterial == null) {
            tiposMaterial = new ArrayList<>();
            
            Catalogo catalogo = catalogoServicio.findByNemonico("MINSMET");
            tiposMaterial.add(new SelectItem(catalogo, catalogo.getNombre()));
            
            catalogo= catalogoServicio.findByNemonico("MINSNMET");
            tiposMaterial.add(new SelectItem(catalogo, catalogo.getNombre()));
            
            catalogo= catalogoServicio.findByNemonico("MINMATCONST");
            tiposMaterial.add(new SelectItem(catalogo, catalogo.getNombre()));
        }
        return tiposMaterial;
    }
    
    public List<SelectItem> getTiposMaquinaria() {
        if (tiposMaquinaria == null) {
            tiposMaquinaria = new ArrayList<>();
            
            for (TipoMaquinaria tm : tipoMaquinariaServicio.findAll()) {
                tiposMaquinaria.add(new SelectItem(tm, tm.getDescripcion()));
            }
        }
        return tiposMaquinaria;
    }
    
    public List<SelectItem> getEstadosMaquinaria() {
        if (estadosMaquinaria == null) {
            estadosMaquinaria = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("ESTMAQOPE");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    estadosMaquinaria.add(new SelectItem(catDet.getCodigoCatalogoDetalle(), catDet.getNombre()));
                }
            }
        }
        return estadosMaquinaria;
    }
    
    public List<SelectItem> getTiposDepositario() {
        if (tiposDepositario == null) {
            tiposDepositario = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPODEPS");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposDepositario.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return tiposDepositario;
    }
    
    public List<SelectItem> getTiposSello() {
        if (tiposSello == null) {
            tiposSello = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOSELLO");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposSello.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return tiposSello;
    }
    
    public List<SelectItem> getEstadosProcedimiento() {
        if (estadosProcedimiento == null) {
            estadosProcedimiento = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("ESTPROADM");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    estadosProcedimiento.add(new SelectItem(catDet, catDet.getNombre()));
                }
            }

        }
        return estadosProcedimiento;
    }
    
    public List<SelectItem> getRegionales() {
        if (regionales == null) {
            regionales = new ArrayList<>();
            List<Regional> rgnls = regionalServicio.findActivos();
            for (Regional rgnl : rgnls) {
                regionales.add(new SelectItem(rgnl, rgnl.getDescripcionRegional()));
            }
        }
        return regionales;
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
            if (operativo.getProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(operativo.getProvincia().getCodigoLocalidad().toString()));
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
            if (operativo.getCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(operativo.getCanton().getCodigoLocalidad().toString()));
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
        operativo.setCanton(null);
        operativo.setParroquia(null);
        getCantones();
        getParroquias();
    }
    
    public void cargaParroquias() {
        parroquias = null;
        operativo.setParroquia(null);
        getParroquias();
    }
    
    public List<SelectItem> getTecnicos() {
        if (tecnicos == null) {
            tecnicos = new ArrayList<>();
            Rol rol= obtenerRol("TECNICO");
            List<UsuarioRol> usuariosRol= usuarioRolServicio.listByRol(rol);            
            
            rol= obtenerRol("TECNICOCAT");
            List<UsuarioRol> usuariosRol02= usuarioRolServicio.listByRol(rol);
            
            rol= obtenerRol("COORDREGIO");
            List<UsuarioRol> usuariosRol03= usuarioRolServicio.listByRol(rol);
            rol= obtenerRol("ESPECOREG");
            List<UsuarioRol> usuariosRol04= usuarioRolServicio.listByRol(rol);
            rol= obtenerRol("TECCATNAC");
            List<UsuarioRol> usuariosRol05= usuarioRolServicio.listByRol(rol);
            rol= obtenerRol("ECONAC");
            List<UsuarioRol> usuariosRol06= usuarioRolServicio.listByRol(rol);            
            
            List<Usuario> usuarios= new ArrayList<>();
            for(UsuarioRol ur : usuariosRol) {
                usuarios.add(ur.getUsuario());
            }
            for(UsuarioRol ur : usuariosRol02) {
                usuarios.add(ur.getUsuario());
            }
            for(UsuarioRol ur : usuariosRol03) {
                usuarios.add(ur.getUsuario());
            }
            for(UsuarioRol ur : usuariosRol04) {
                usuarios.add(ur.getUsuario());
            }
            for(UsuarioRol ur : usuariosRol05) {
                usuarios.add(ur.getUsuario());
            }
            for(UsuarioRol ur : usuariosRol06) {
                usuarios.add(ur.getUsuario());
            }
            
            //SE AGREGA LOS USUARIOS TECNICOS ASIGNADOS ESPECIFICAMENTE DESDE LA BASE DE DATOS
            List<Usuario> usuarios07= usuarioServicio.findByTipoUsuarioCampoReservado3(RolEnum.ROL_TECNICO.getNemonico());
            for(Usuario u : usuarios07) {
                usuarios.add(u);
            }
            ordenarListaUsuarios(usuarios);
            
            for (Usuario u : usuarios) {
                tecnicos.add(new SelectItem(u, u.getNombresCompletos().toUpperCase()));
            }
        }
        return tecnicos;
    }
    
    // Inicio del metodo de ordenamiento de la Burbuja
    public List<Usuario> ordenarListaUsuarios(List<Usuario> listaUsuariosA) {        
        for (int i = 1; i <= listaUsuariosA.size(); i++) {
            for (int j = 0; j < listaUsuariosA.size() - i; j++) {
                int valor = j + 1;
                if (listaUsuariosA.get(j).getNombresCompletos().compareTo(listaUsuariosA.get(valor).getNombresCompletos()) > 0) {
                    Usuario usuarioAux = new Usuario();
                    usuarioAux = listaUsuariosA.get(j);
                    Usuario usuarioTemp = new Usuario();
                    usuarioTemp = listaUsuariosA.get(valor);
                    listaUsuariosA.set(j, usuarioTemp);
                    listaUsuariosA.set(valor, usuarioAux);
                }
            }
        }
        return listaUsuariosA;
    }
    // Fin del metodo de ordenamiento de la Burbuja
    
    public List<SelectItem> getLegales() {
        if (legales == null) {
            legales = new ArrayList<>();
            Rol rol= obtenerRol("ABOGADO");
            List<UsuarioRol> usuariosRol= usuarioRolServicio.listByRol(rol);
            
            rol= obtenerRol("RGRGNL");
            List<UsuarioRol> usuariosRol02= usuarioRolServicio.listByRol(rol);
            
            List<Usuario> usuarios= new ArrayList<>();
            for(UsuarioRol ur : usuariosRol) {
                usuarios.add(ur.getUsuario());
            }
            
            for(UsuarioRol ur : usuariosRol02) {
                usuarios.add(ur.getUsuario());
            }
            
            //SE AGREGA LOS USUARIOS ABOGADOS ASIGNADOS ESPECIFICAMENTE DESDE LA BASE DE DATOS
            List<Usuario> usuarios03= usuarioServicio.findByTipoUsuarioCampoReservado3(RolEnum.ROL_ABOGADO.getNemonico());
            for(Usuario u : usuarios03) {
                usuarios.add(u);
            }
            
            ordenarListaUsuarios(usuarios);
            
            for (Usuario u : usuarios) {
                legales.add(new SelectItem(u, u.getNombresCompletos().toUpperCase()));
            }
        }
        return legales;
    }
    
    public Rol obtenerRol(String nemonico) {
        return rolServicio.findByNemonico(nemonico);
    }
    
    ////////////////////////
    public List<DetalleOperativo> obtenerInformacionPorTipo(String nemonico) {
        CatalogoDetalle catDet= catalogoDetalleServicio.obtenerPorNemonico(nemonico).get(0);
        List<DetalleOperativo> filtrados= new ArrayList();
        for(DetalleOperativo detOperativo : detallesOperativo) {
            if(detOperativo.getTipoInformacionRegistro().equals(catDet)) {
                filtrados.add(detOperativo);
            }
        }
        return filtrados;
    }
    
    public List<DetalleOperativo> obtenerInformacionInstituciones() {
        return obtenerInformacionPorTipo(TIPOINSTITUCION);
    }
    public List<DetalleOperativo> obtenerInformacionDetenidos() {
        return obtenerInformacionPorTipo(TIPODETENIDO);
    }
    public List<DetalleOperativo> obtenerInformacionDepositarios() {
        return obtenerInformacionPorTipo(TIPODEPOSITARIO);
    }
    
    ////////////////////////
    
    public void newOperativoAction() {
        if(login.getCodigoUsuario()!=null) {
            operativo= new Operativo();
            detallesOperativo= new ArrayList<>();
            detalleOperativo= new DetalleOperativo();
            maquinarias= new ArrayList<>();
            archivosParaCargar= new ArrayList<>();
        
            Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("operativoform:operativowiz");
            wizard.setStep("tab01");
            RequestContext.getCurrentInstance().update("operativoform");
            RequestContext.getCurrentInstance().execute("PF('operativofrmwg').show();");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Debe estar logueado para realizar esta acción");
        }
    }
    
    public void setOperativoEliminarAction(Operativo ope) {
        if(login.getCodigoUsuario()!=null) {
            operativo= ope;
            RequestContext.getCurrentInstance().execute("PF('deloperativodlgwg').show();");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Debe estar logueado para realizar esta acción");
        }
    }
    
    public void deleteOperativoAction() {
        if(operativo!=null) {
            deleteDetalles(operativo);
            operativoServicio.delete(operativo.getCodigoOperativo());
            saveAuditoria(DELETE, operativo, new Operativo());
            obtenerOperativos();
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo eliminado correctamente");
        }
    }
    
    private void deleteDetalles(Operativo o) {
        List<DetalleOperativo> detalles= detalleOperativoServicio.listarPorOperativo(o);
        for(DetalleOperativo detop : detalles) {
            System.out.println("Detalle operativo= " + detop.getCodigoDetalleOperativo());
            detalleOperativoServicio.delete(detop.getCodigoDetalleOperativo());
        }
    }
    
    public void editOperativoAction(Operativo ope) {
        if(login.getCodigoUsuario()!=null) {
            edit= true;
            resetAction();
            operativo= ope;
            operativo.setUtmEste(operativo.getUtmEste().replace(",", "."));
            operativo.setUtmNorte(operativo.getUtmNorte().replace(",", "."));
            getCantones();
            getParroquias();
            detallesOperativo= detalleOperativoServicio.listarPorOperativo(operativo);
            maquinarias= maquinariaConcesionServicio.obtenerMaquinariasPorOperativo(operativo);
            archivosCargados= obtenerArchivosCargados(operativo);
            archivosParaCargar= new ArrayList<>();

            Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("operativoform:operativowiz");
            wizard.setStep("tab01");
            RequestContext.getCurrentInstance().update("operativoform");
            RequestContext.getCurrentInstance().execute("PF('operativofrmwg').show();");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Debe estar logueado para realizar esta acción");
        }
    }
    
    public void newInstitucionAction() {
        this.detalleOperativo= new DetalleOperativo();
    }
    
    public void addInstitucionAction() {
        detalleOperativo.setTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico(TIPOINSTITUCION).get(0));
        this.detallesOperativo.add(detalleOperativo);
    }
    
    public void deleteInstitucionAction(DetalleOperativo detalle) {
        detallesOperativo.remove(detalle);
        if(detalle.getCodigoDetalleOperativo()!=null) {
            detalleOperativoServicio.delete(detalle.getCodigoDetalleOperativo());
            saveAuditoria(DELETE, detalle, new DetalleOperativo());
        }
    }
    
    public void newDetenidoAction() {
        this.detalleOperativo= new DetalleOperativo();
    }
    
    public void addDetenidoAction() {
        detalleOperativo.setTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico(TIPODETENIDO).get(0));
        this.detallesOperativo.add(detalleOperativo);
    }
    
    public void deleteDetenidoAction(DetalleOperativo detalle) {
        detallesOperativo.remove(detalle);
        if(detalle.getCodigoDetalleOperativo()!=null) {
            detalleOperativoServicio.delete(detalle.getCodigoDetalleOperativo());
            saveAuditoria(DELETE, detalle, new DetalleOperativo());
        }
    }
    
    public void newMaquinariaAction() {
        this.maquinaria= new MaquinariaConcesion();
    }
    
    public void addMaquinariaAction() {
        if(maquinarias!=null) {
            maquinarias.add(maquinaria);
        }
    }
    
    public void deleteMaquinariaAction(MaquinariaConcesion maq) {
        maquinarias.remove(maq);
        if(maq.getCodigoMaquinaria()!=null) {
            maquinariaConcesionServicio.delete(maq.getCodigoMaquinaria());
            saveAuditoria(DELETE, maq, new MaquinariaConcesion());
        }
    }
    
    public void newDepositarioAction() {
        this.detalleOperativo= new DetalleOperativo();
    }
    
    public void addDepositarioAction() {
        detalleOperativo.setTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico(TIPODEPOSITARIO).get(0));
        this.detallesOperativo.add(detalleOperativo);
    }
    
    public void deleteDepositarioAction(DetalleOperativo detalle) {
        detallesOperativo.remove(detalle);
        if(detalle.getCodigoDetalleOperativo()!=null) {
            detalleOperativoServicio.delete(detalle.getCodigoDetalleOperativo());
            saveAuditoria(DELETE, detalle, new DetalleOperativo());
        }
    }
    
    public String obtenerNombreCatalogoDetalle(Long pk) {
        return catalogoDetalleServicio.findByPk(pk).getNombre();
    }
    
    public void saveOperativoAction() {
        if(edit) {
            if(actualizar()) {
                if(archivosParaCargar!=null && archivosParaCargar.size()>0) {
                    guardarAdjuntos();
                }
                finalizarGuardado();
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo actualizado correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al actualizar");
            }
        } else {
            if(guardar()) {
                if(archivosParaCargar!=null && archivosParaCargar.size()>0) {
                    guardarAdjuntos();
                }
                finalizarGuardado();
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo guardado correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al guardar");
            }
        }
    }
    
    private boolean guardar() {
        if(login.getCodigoUsuario()!=null) {
            if(guardarOperativo() && guardarMaquinaria()) {
                return true;
            }
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Debe estar logueado para realizar esta acción");
        }
        return false;
    }
                
    private boolean guardarOperativo() {
        Usuario usrCreacion= usuarioServicio.findByPk(login.getCodigoUsuario());
        operativo.setUtmEste(operativo.getUtmEste().replace(".", ","));
        operativo.setUtmNorte(operativo.getUtmNorte().replace(".", ","));
        operativo.setEstadoRegistro(Boolean.TRUE);
        operativo.setFechaCreacion(Calendar.getInstance().getTime());
        operativo.setUsuarioCreacion(usrCreacion);
        if(!operativo.getExpedienteAdministrativo()) {
            operativo.setNumeroExpedienteAdministrativo(null);
            operativo.setEstadoAdministrativo(null);
            operativo.setNumeroResolucionAdministrativo(null);
            operativo.setFechaResolucionAdministrativo(null);
        }
        operativoServicio.create(operativo);
        saveAuditoria(INSERT, operativo, new Operativo());
        
        for(DetalleOperativo detope : detallesOperativo) {
            detope.setOperativo(operativo);
            detope.setEstadoRegistro(Boolean.TRUE);
            detope.setFechaCreacion(Calendar.getInstance().getTime());
            detope.setUsuarioCreacion(usrCreacion);
            detalleOperativoServicio.create(detope);
            saveAuditoria(INSERT, detope, new DetalleOperativo());
        }
        return true;
    }
    
    private boolean guardarMaquinaria() {
        for(MaquinariaConcesion mc : maquinarias) {
            mc.setEstadoRegistro(Boolean.TRUE);
            mc.setFechaCreacion(Calendar.getInstance().getTime());
            mc.setUsuarioCreacion(BigInteger.valueOf(login.getCodigoUsuario()));
            mc.setOperativo(operativo);
            maquinariaConcesionServicio.create(mc);
            saveAuditoria(INSERT, mc, new MaquinariaConcesion());
        }
        return true;
    }
    
    private boolean actualizar() {
        if(login.getCodigoUsuario()!=null) {
            if(actualizarOperativo() && actualizarMaquinaria()) {
                return true;
            }
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Debe estar logueado para realizar esta acción");
        }
        return false;
    }
    
    private boolean actualizarOperativo() {
        Usuario usr= usuarioServicio.findByPk(login.getCodigoUsuario());
        Operativo anterior= operativoServicio.findByPk(operativo.getCodigoOperativo());
        operativo.setUtmEste(operativo.getUtmEste().replace(".", ","));
        operativo.setUtmNorte(operativo.getUtmNorte().replace(".", ","));
        operativo.setFechaModificacion(Calendar.getInstance().getTime());
        operativo.setUsuarioModificacion(usr);
        if(!operativo.getExpedienteAdministrativo()) {
            operativo.setNumeroExpedienteAdministrativo(null);
            operativo.setEstadoAdministrativo(null);
            operativo.setNumeroResolucionAdministrativo(null);
            operativo.setFechaResolucionAdministrativo(null);
        }
        operativoServicio.update(operativo);
        saveAuditoria(UPDATE, operativo, anterior);
        
        for(DetalleOperativo detope : detallesOperativo) {
            detope.setOperativo(operativo);
            detope.setEstadoRegistro(Boolean.TRUE);
            if(detope.getFechaCreacion()!=null) {
                DetalleOperativo detopeAnterior= detalleOperativoServicio.findByPk(detope.getCodigoDetalleOperativo());
                detope.setFechaModificacion(Calendar.getInstance().getTime());
                detope.setUsuarioModificacion(usr);
                detalleOperativoServicio.update(detope);
                saveAuditoria(UPDATE, detope, detopeAnterior);
            } else {
                detope.setFechaCreacion(Calendar.getInstance().getTime());
                detope.setUsuarioCreacion(usr);
                detalleOperativoServicio.create(detope);
                saveAuditoria(INSERT, detope, new DetalleOperativo());
            }
        }
        return true;
    }
    
    private boolean actualizarMaquinaria() {
        for(MaquinariaConcesion mc : maquinarias) {
            mc.setEstadoRegistro(Boolean.TRUE);
            mc.setOperativo(operativo);
            if(mc.getFechaCreacion()!=null) {
                MaquinariaConcesion mcAnterior= maquinariaConcesionServicio.findByPk(mc.getCodigoMaquinaria());
                mc.setFechaModificacion(Calendar.getInstance().getTime());
                mc.setUsuarioModificacion(BigInteger.valueOf(login.getCodigoUsuario()));
                maquinariaConcesionServicio.update(mc);
                saveAuditoria(UPDATE, mc, mcAnterior);
            } else {
                mc.setFechaCreacion(Calendar.getInstance().getTime());
                mc.setUsuarioCreacion(BigInteger.valueOf(login.getCodigoUsuario()));
                maquinariaConcesionServicio.create(mc);
                saveAuditoria(INSERT, mc, new MaquinariaConcesion());
            }
        }
        return true;
    }
    
    private void saveAuditoria(String accion, Operativo nuevo, Operativo anterior) {
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion(accion);
        auditoria.setFecha(Calendar.getInstance().getTime());
        auditoria.setUsuario(BigInteger.valueOf(login.getCodigoUsuario()));
        auditoria.setDetalleAnterior(nuevo.toString());
        if(accion.equals(UPDATE)) {
            auditoria.setDetalleCambios(anterior.toString());
        } else {
            auditoria.setDetalleCambios("");
        }
        auditoriaServicio.create(auditoria);
    }
    
    private void saveAuditoria(String accion, DetalleOperativo nuevo, DetalleOperativo anterior) {
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion(accion);
        auditoria.setFecha(Calendar.getInstance().getTime());
        auditoria.setUsuario(BigInteger.valueOf(login.getCodigoUsuario()));
        auditoria.setDetalleAnterior(nuevo.toString());
        if(accion.equals(UPDATE)) {
            auditoria.setDetalleCambios(anterior.toString());
        } else {
            auditoria.setDetalleCambios("");
        }
        auditoriaServicio.create(auditoria);
    }
    
    private void saveAuditoria(String accion, MaquinariaConcesion nuevo, MaquinariaConcesion anterior) {
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion(accion);
        auditoria.setFecha(Calendar.getInstance().getTime());
        auditoria.setUsuario(BigInteger.valueOf(login.getCodigoUsuario()));
        auditoria.setDetalleAnterior(nuevo.toString());
        if(accion.equals(UPDATE)) {
            auditoria.setDetalleCambios(anterior.toString());
        } else {
            auditoria.setDetalleCambios("");
        }
        auditoriaServicio.create(auditoria);
    }
    
    private void finalizarGuardado() {
        RequestContext.getCurrentInstance().execute("PF('operativofrmwg').hide();");
        showUploadPanel= false;
        obtenerOperativos();
    }
    
    public void mostrarMensaje(Severity s, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, "", msg));
    }
    
    public String obtenerFechaConFormato(Date fecha) {
        return obtenerFechaConFormato("dd-MM-yyyy", fecha);
    }
    
    public String obtenerFechaConFormato(String formato, Date fecha) {
        SimpleDateFormat sdf= new SimpleDateFormat(formato);
        if(fecha!=null) {
            return sdf.format(fecha);
        }
        return "";
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
    
    //Show hide carga de archivos
    public void changeFileUploadState() {
        showUploadPanel= !showUploadPanel;
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
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "El archivo ya se encuentra en la lista");
        }
    }
    
    private void guardarAdjuntos() {
        List<Adjunto> filesForSave= new ArrayList();
        for(UploadedFile f : archivosParaCargar) {
            Adjunto adj= subirArchivoRepositorio(f, "OPERATIVO", String.valueOf(operativo.getCodigoOperativo()), "OPERATIVO");
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
    
    private List<Adjunto> obtenerArchivosCargados(Operativo o) {
        return adjuntoServicio.findByOperativo(o);
    }
    
    private void resetAction() {
        this.cantones= null;
        this.parroquias= null;
    }
    
    public void generarReporteMensual() {
        List<String> filas= Arrays.asList("CUENCA", "GUAYAQUIL", "IBARRA", "LOJA", "MACAS", "MACHALA", "RIOBAMBA", "TENA", "ZAMORA");
        Calendar c= Calendar.getInstance();
        int mesActual= c.get(Calendar.MONTH);
        System.out.println("mesActual: " + mesActual);
        String mesString= "";
        columns= new ArrayList<>();
        columns.add(new DataTableColumn("COORDINACIÓN REGIONAL DIRECCIÓN TÉCNICA ", "nombreRegional"));
        for(int i=0; i<=mesActual; i++) {
            switch (i) {
                case 0:  mesString = "ENERO";
                         break;
                case 1:  mesString = "FEBRERO";
                         break;
                case 2:  mesString = "MARZO";
                         break;
                case 3:  mesString = "ABRIL";
                         break;
                case 4:  mesString = "MAYO";
                         break;
                case 5:  mesString = "JUNIO";
                         break;
                case 6:  mesString = "JULIO";
                         break;
                case 7:  mesString = "AGOSTO";
                         break;
                case 8:  mesString = "SEPTIEMBRE";
                         break;
                case 9: mesString = "OCTUBRE";
                         break;
                case 10: mesString = "NOVIEMBRE";
                         break;
                case 11: mesString = "DICIEMBRE";
                         break;
            }
            columns.add(new DataTableColumn(mesString, "cantidadDeOperativos"));
        }
        rows= new ArrayList<>();
        for(String fila : filas) {
            List<Long> cantOperativos= new ArrayList();
            Long codigoRegional= obtenerCodigoRegional(fila);
            
            List<Operativo> cantidadPorRegional= operativoServicio.obtenerPorRegional(regionalServicio.findByPk(codigoRegional));
            for(int i= 0; i<=columns.size();i++) {
                Long cantidadPorMes= (long) contarPorMes(cantidadPorRegional, i);
                cantOperativos.add(cantidadPorMes);
            }
            rows.add(new ReportData(fila, cantOperativos));
            System.out.println("Mes: " + fila);
            System.out.println("cantOperativos: " + cantOperativos);
        }
    }
    
    public String obtenerValorPresentar(ReportData row, int col) {
        if(col==0) {
            return row.getNombreRegional();
        }
        return row.getCantidadDeOperativos().get(col-1).toString();
    }
    
    public class DataTableColumn {
        String header;
        String property;
        
        public DataTableColumn(String h, String p) {
            header= h;
            property= p;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
        
    }
    
    public class ReportData {
        private String nombreRegional;
        private List<Long> cantidadDeOperativos;
        
        public ReportData(String name, List<Long> numberOfOperativos) {
            nombreRegional= name;
            cantidadDeOperativos= numberOfOperativos;
        }

        public String getNombreRegional() {
            return nombreRegional;
        }

        public void setNombreRegional(String nombreRegional) {
            this.nombreRegional = nombreRegional;
        }

        public List<Long> getCantidadDeOperativos() {
            return cantidadDeOperativos;
        }

        public void setCantidadDeOperativos(List<Long> cantidadDeOperativos) {
            this.cantidadDeOperativos = cantidadDeOperativos;
        }
        
    }
   
    public Long obtenerCodigoRegional(String nombreRegional) {
        Long codigo = (long) 0;
        switch (nombreRegional) {
            case "CUENCA":  codigo = (long) 1;
                     break;
            case "GUAYAQUIL":  codigo = (long) 7;
                     break;
            case "IBARRA":  codigo = (long) 4;
                     break;
            case "LOJA":  codigo = (long) 6;
                     break;
            case "MACAS":  codigo = (long) 9;
                     break;
            case "MACHALA":  codigo = (long) 3;
                     break;
            case "RIOBAMBA":  codigo = (long) 2;
                     break;
            case "TENA":  codigo = (long) 10;
                     break;
            case "ZAMORA":  codigo = (long) 5;
                     break;
        }
        return codigo;
    }
    
    public int contarPorMes(List<Operativo> operativosPorRegional, int mesAContar) {
        int resultado= 0;
        for(Operativo ope : operativosPorRegional) {
            Calendar c= Calendar.getInstance();
            c.setTime(ope.getFechaOperativo());
            int mesOperativo= c.get(Calendar.MONTH);
            if(mesOperativo==mesAContar) {
                resultado++;
            }
        }
        return resultado;
    }
}