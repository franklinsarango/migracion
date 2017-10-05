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
import ec.gob.arcom.migracion.modelo.Activo;
import ec.gob.arcom.migracion.modelo.CatalogoInv;
import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Movimiento;
import ec.gob.arcom.migracion.modelo.RegionalInv;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.ActivoServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoInvServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaCotaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.MovimientoServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.RegionalInvServicio;
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
public class ActivoCtrl extends BaseCtrl {

    @EJB
    private ActivoServicio activoServicio;
    @EJB
    private CatalogoInvServicio catalogoInvServicio;
    @EJB
    private MovimientoServicio movimientoServicio;
    @EJB
    private RegionalInvServicio regionalInvServicio;    
    @EJB
    private UsuarioDao usuarioDao;    
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private List<Activo> listaActivos;

    private Long filtroId;
    private String filtroDescripcion;
    private String filtroTagArcom;
    
    private boolean bolMostrar;
    private boolean bolMostrarEditar;
    private boolean bolMostrarEditarMov;

    private String numDocPersonaPopupFiltro;
  

    protected int numeroPagina = 0;
    protected int desplazamiento;
    protected int totalPaginas;
    protected static final int tamanoPagina = 10;
    private Integer paginaSeleccionada;
    private ArrayList<Integer> listaPaginas;
    
    
    private String mensaje_salida;
    private Activo activo = new Activo();
    private Activo activoEditar = new Activo();
    private List<CatalogoInv> listaTipoActivo;
    private List<CatalogoInv> listaMarca;
    private List<CatalogoInv> listaModelo;
    private List<RegionalInv> listaRegionalInv;
    private List<CatalogoInv> listaUbicacion;
    private List<CatalogoInv> listaEstado;
    private int intPagina;
    private int intUltimaPagina;


    private Movimiento movimiento = new Movimiento();
    private Movimiento movimientoEditar = new Movimiento();
    private List<Movimiento> listaMovimiento;
    private CatalogoInv catalogoEdit = new CatalogoInv();
    
    public static String strBDInventario = "inventario";
    public static String strBDSistemas = "sistemas";
    public static String strCatalogoTipoActivo = "TIPACTIVO";
    public static String strCatalogoMarca = "CTGMARC";
    public static String strCatalogoModelo = "CTGMODEL";    
    public static String strCatalogoUbicacion = "CTGUBICAC";
    public static String strCatalogoEstadosActivos = "ESTACT";
    
    
    
    @PostConstruct
    public void init() {
        try {
            listaActivos = null;
            //getListaActivos();
            mostrarDatos("btn_buscar");
            this.setBolMostrar(false);
            cargarCatalogos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mostrarDatosMov() {
        Activo activoItem = (Activo) getExternalContext().getRequestMap().get("reg");
        setActivo(new Activo());
        setActivo(activoItem);
        List<Movimiento> lista_movimientos = movimientoServicio.listaMovimientos(activo);
        setListaMovimiento(lista_movimientos);
        habilitar_componentes("DEFAULT_MOVIMIENTO");
    }

    public void cancelarMovimiento() {
        habilitar_componentes("DEFAULT_MOVIMIENTO");
    }

    public void nuevoMovimiento() {
        Movimiento movimiento_ = new Movimiento();
        movimiento_.setId(0L);

        movimiento_.setFkActivo(activo);
        movimiento_.setPersonaEntrega("");
        movimiento_.setPersonaRecibe("");
        movimiento_.setFecha(new Date());
        movimiento_.setObservacion("");

        setMovimientoEditar(movimiento_);

        this.setBolMostrarEditarMov(true);
    }

    public void editarMovimiento() {
        habilitar_componentes("DEFAUL_MOVIMIENTO");
        if (getMovimiento() != null) {
            this.setBolMostrarEditarMov(true);
            this.setMovimientoEditar(getMovimiento());
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro");
        }
    }

    public void guardarMovimiento() {
        if (getMovimientoEditar() != null) {
            if (getMovimientoEditar().getId() != 0) {
                movimientoServicio.actualizar(getMovimientoEditar());
            } else {
                movimientoServicio.registrar(getMovimientoEditar());
            }

            habilitar_componentes("DEFAULT_MOVIMIENTO");
            List<Movimiento> lista_movimientos = movimientoServicio.listaMovimientos(activo);
            setListaMovimiento(lista_movimientos);
            habilitar_componentes("DEFAULT_MOVIMIENTO");
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro - OPC GUARDAR");
        }
    }

    public void eliminarMovimiento() {
        if (getMovimiento() != null) {
            movimientoServicio.delete(getMovimiento().getId());

            habilitar_componentes("DEFAULT_MOVIMIENTO");
            List<Movimiento> lista_movimientos = movimientoServicio.listaMovimientos(activo);
            setListaMovimiento(lista_movimientos);
            habilitar_componentes("DEFAULT_MOVIMIENTO");
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro - OPC GUARDAR");
        }
    }
    
    public void mostrarActivo() {               
        Activo activoItem = (Activo) getExternalContext().getRequestMap().get("reg");
        setActivo(new Activo());
        setActivo(activoItem);
        habilitar_componentes("DEFAULT");
        if (getActivo() != null) {
            this.setBolMostrar(true);
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro");
        }
    }    

    public void buscar() {
        listaActivos = null;
        //getListaActivos();
        mostrarDatos("btn_buscar");
    }
    
    public void cancelarActivo() {
        habilitar_componentes("DEFAULT");
    }

    public void habilitar_componentes(String str_estado) {
        if (str_estado == "DEFAULT") {
            this.setBolMostrar(false);
            this.setBolMostrarEditar(false);
        }

        if (str_estado == "DEFAULT_MOVIMIENTO") {
//            this.setBolMostrarMovimiento(true);
            this.setBolMostrarEditarMov(false);
        }
    }
    
    public void nuevoActivo() {        
        habilitar_componentes("DEFAULT");
        this.setBolMostrarEditar(true);
        Activo activo_ = new Activo();
        activo_.setId(0L);

        CatalogoInv tipoactivo = new CatalogoInv();
        tipoactivo.setId(1L);
        tipoactivo.setNombre("S/N");
        activo_.setTipoactivo(tipoactivo);

        CatalogoInv marca = new CatalogoInv();
        marca.setId(1L);
        marca.setNombre("S/N");
        activo_.setMarca(marca);

        CatalogoInv modelo = new CatalogoInv();
        modelo.setId(1L);
        modelo.setNombre("S/N");
        activo_.setModelo(modelo);

        activo_.setTagArcom("");
        activo_.setNumSerie("");
        activo_.setDescripcion("");

        RegionalInv regional = new RegionalInv();
        regional.setId(1L);
        regional.setNombre("S/N");
        activo_.setRegional(regional);

        CatalogoInv ubicacion = new CatalogoInv();
        ubicacion.setId(1L);
        ubicacion.setNombre("S/N");
        activo_.setUbicacion(ubicacion);

        CatalogoInv estado = new CatalogoInv();
        estado.setId(1L);
        estado.setNombre("S/N");
        activo_.setEstado(estado);

        activo_.setVigencia(BigInteger.valueOf(1));
        setActivoEditar(activo_);
    }
    
    public void editarActivo() {
        Activo activoItem = (Activo) getExternalContext().getRequestMap().get("reg");
        setActivo(new Activo());
        setActivo(activoItem);
        habilitar_componentes("DEFAULT");
        if (getActivo() != null) {
            this.setBolMostrarEditar(true);
            this.setActivoEditar(getActivo());
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro");
        }
    }
    
    public void guardarActivo() {
        if (getActivoEditar() != null) {
            this.setBolMostrarEditar(true);            
            if (getActivoEditar().getId() != 0) {
                activoServicio.actualizar(getActivoEditar());
            } else {
                /*******INCIO SE VERIFICA QUE SOLO EXISTA UN SOLO TAG ARCOM **************/
                List<Activo> listaActivos = activoServicio.findByTagArcom(getActivoEditar().getTagArcom());
                if(listaActivos != null && listaActivos.size() > 0){
                    ponerMensajeInfo("", "Error: El TAG ARCOM ya existe...");
                    return;
                }
                /*******FIN SE VERIFICA QUE SOLO EXISTA UN SOLO TAG ARCOM *****************/
                activoServicio.registrar(getActivoEditar());
            }

            btnBuscar();
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro - OPC GUARDAR");
        }
    }

    public void btnBuscar() {
        cargarCatalogos();
        mostrarDatos("btn_buscar");
        this.habilitar_componentes("DEFAULT");
    }
    
    public void cargarCatalogos() {
        if (getListaTipoActivo() == null) {
            this.loadCataloTipoActivo();
        }
        if (getListaMarca() == null) {
            this.loadCataloMarca();
        }
        if (getListaModelo() == null) {
            this.loadCataloModelo();
        }
        if (getListaUbicacion() == null) {
            this.loadCataloUbicacion();
        }
        if (getListaRegionalInv() == null) {
            this.loadCataloRegionalInv();
        }
        if (getListaEstado() == null) {
            this.loadCataloEstadosActivos();
        }
    }
    
    public void loadCataloTipoActivo() {
        List<CatalogoInv> lista_catalogo = catalogoInvServicio.findByNemonico(strCatalogoTipoActivo);
        setListaTipoActivo(lista_catalogo);
    }

    public void loadCataloMarca() {
        System.out.println("loadCataloMarca");
        List<CatalogoInv> lista_catalogo = catalogoInvServicio.findByNemonico(strCatalogoMarca);
        setListaMarca(lista_catalogo);
    }

    public void loadCataloModelo() {
        System.out.println("loadCataloModelo");
        List<CatalogoInv> lista_catalogo = catalogoInvServicio.findByNemonico(strCatalogoModelo);
        setListaModelo(lista_catalogo);
    }

    public void loadCataloRegionalInv() {
        List<RegionalInv> lista_catalogo = regionalInvServicio.listaRegionales();
        setListaRegionalInv(lista_catalogo);
    }

    public void loadCataloUbicacion() {
        List<CatalogoInv> lista_catalogo = catalogoInvServicio.findByNemonico(strCatalogoUbicacion);
        setListaUbicacion(lista_catalogo);
    }

    public void loadCataloEstadosActivos() {
        List<CatalogoInv> lista_catalogo = catalogoInvServicio.findByNemonico(strCatalogoEstadosActivos);
        setListaEstado(lista_catalogo);
    }

    /**
     * Funciones para agregar Tipo de Activo
     */
    public void nuevoTipoActivo() {
        catalogoEdit = new CatalogoInv();
        catalogoEdit.setId(0L);
        catalogoEdit.setFkCatalogo(BigInteger.valueOf(5));
        catalogoEdit.setNombre("");
        catalogoEdit.setDescripcion("");
        catalogoEdit.setVigencia(BigInteger.valueOf(1));
    }
    
    public void nuevaMarca() {
        catalogoEdit = new CatalogoInv();
        catalogoEdit.setId(0L);
        catalogoEdit.setFkCatalogo(BigInteger.valueOf(35));
        catalogoEdit.setNombre("");
        catalogoEdit.setDescripcion("");
        catalogoEdit.setVigencia(BigInteger.valueOf(1));
    }
    
    public void nuevoModelo() {
        catalogoEdit = new CatalogoInv();
        catalogoEdit.setId(0L);
        catalogoEdit.setFkCatalogo(BigInteger.valueOf(67));
        catalogoEdit.setNombre("");
        catalogoEdit.setDescripcion("");
        catalogoEdit.setVigencia(BigInteger.valueOf(1));
    }

    public void guardarCatalogo() {
        if (catalogoEdit != null) {
            
//            if(catalogoEdit.getId()!=0)
//                catalogoInvServicio.actualizar(movimientoEditar);
//            else
            
            catalogoInvServicio.registrar(catalogoEdit);

//            habilitar_componentes("DEFAULT_MOVIMIENTO");
//            mostrarDatosMov();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Guardado Correctamente !!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            
            RequestContext.getCurrentInstance().execute("PF('documentDialogCatalogo').hide()");            
            
            if(catalogoEdit.getFkCatalogo().equals(BigInteger.valueOf(5))) {                
                loadCataloTipoActivo();
            } else if (catalogoEdit.getFkCatalogo().equals(BigInteger.valueOf(35))) {                
                loadCataloMarca();
            } else if (catalogoEdit.getFkCatalogo().equals(BigInteger.valueOf(67))) {                
                loadCataloModelo();
            }
            
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro - OPC GUARDAR");
        }
    }
    
    public void eliminarActivo() {
        Activo activoItem = (Activo) getExternalContext().getRequestMap().get("reg");
        setActivo(new Activo());
        setActivo(activoItem);
        if (getActivo() != null) {
            activoServicio.delete(activo.getId());

            btnBuscar();
        } else {
            ponerMensajeInfo("", "Por favor seleccione un Registro - OPC GUARDAR");
        }
    }    

    public List<Activo> getListaActivos() {       
        return listaActivos;
    }

    public void setListaActivos(List<Activo> listaActivos) {
        this.listaActivos = listaActivos;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public Long getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Long filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroDescripcion() {
        return filtroDescripcion;
    }

    public void setFiltroDescripcion(String filtroDescripcion) {
        this.filtroDescripcion = filtroDescripcion;
    }

    public String getNumDocPersonaPopupFiltro() {
        return numDocPersonaPopupFiltro;
    }

    public void setNumDocPersonaPopupFiltro(String numDocPersonaPopupFiltro) {
        this.numDocPersonaPopupFiltro = numDocPersonaPopupFiltro;
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
        
        if (listaActivos != null) {
            listaActivos.clear();
        } else {
            listaActivos = new ArrayList<>();
        }
        
        List<Activo> listContratoOperacion = activoServicio.listaActivos(tamanoPagina, desplazamiento, filtroId, filtroDescripcion, filtroTagArcom);
        if (listContratoOperacion != null) {
            for (Activo contratoOp : listContratoOperacion) {
                listaActivos.add(contratoOp);
            }
        } else {
            //ponerMensajeInfo("", "No existen Contratos de Operacion con código ARCOM ");
            listaActivos.clear();
        }
    }

    public void cargarListaPaginas() {
        if(listaPaginas == null){
            listaPaginas = new ArrayList<>();
        }
        int paginas = activoServicio.listaActivosTotal(filtroId,filtroDescripcion,filtroTagArcom);
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
     * @return the filtroTagArcom
     */
    public String getFiltroTagArcom() {
        return filtroTagArcom;
    }

    /**
     * @param filtroTagArcom the filtroTagArcom to set
     */
    public void setFiltroTagArcom(String filtroTagArcom) {
        this.filtroTagArcom = filtroTagArcom;
    }

    /**
     * @return the bolMostrar
     */
    public boolean isBolMostrar() {
        return bolMostrar;
    }

    /**
     * @param bolMostrar the bolMostrar to set
     */
    public void setBolMostrar(boolean bolMostrar) {
        this.bolMostrar = bolMostrar;
    }

    /**
     * @return the bolMostrarEditar
     */
    public boolean isBolMostrarEditar() {
        return bolMostrarEditar;
    }

    /**
     * @param bolMostrarEditar the bolMostrarEditar to set
     */
    public void setBolMostrarEditar(boolean bolMostrarEditar) {
        this.bolMostrarEditar = bolMostrarEditar;
    }

    /**
     * @return the bolMostrarEditarMov
     */
    public boolean isBolMostrarEditarMov() {
        return bolMostrarEditarMov;
    }

    /**
     * @param bolMostrarEditarMov the bolMostrarEditarMov to set
     */
    public void setBolMostrarEditarMov(boolean bolMostrarEditarMov) {
        this.bolMostrarEditarMov = bolMostrarEditarMov;
    }

    /**
     * @return the activo
     */
    public Activo getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    /**
     * @return the activoEditar
     */
    public Activo getActivoEditar() {
        return activoEditar;
    }

    /**
     * @param activoEditar the activoEditar to set
     */
    public void setActivoEditar(Activo activoEditar) {
        this.activoEditar = activoEditar;
    }

    /**
     * @return the listaTipoActivo
     */
    public List<CatalogoInv> getListaTipoActivo() {
        return listaTipoActivo;
    }

    /**
     * @param listaTipoActivo the listaTipoActivo to set
     */
    public void setListaTipoActivo(List<CatalogoInv> listaTipoActivo) {
        this.listaTipoActivo = listaTipoActivo;
    }

    /**
     * @return the listaMarca
     */
    public List<CatalogoInv> getListaMarca() {
        return listaMarca;
    }

    /**
     * @param listaMarca the listaMarca to set
     */
    public void setListaMarca(List<CatalogoInv> listaMarca) {
        this.listaMarca = listaMarca;
    }

    /**
     * @return the listaModelo
     */
    public List<CatalogoInv> getListaModelo() {
        return listaModelo;
    }

    /**
     * @param listaModelo the listaModelo to set
     */
    public void setListaModelo(List<CatalogoInv> listaModelo) {
        this.listaModelo = listaModelo;
    }

    /**
     * @return the listaRegionalInv
     */
    public List<RegionalInv> getListaRegionalInv() {
        return listaRegionalInv;
    }

    /**
     * @param listaRegionalInv the listaRegionalInv to set
     */
    public void setListaRegionalInv(List<RegionalInv> listaRegionalInv) {
        this.listaRegionalInv = listaRegionalInv;
    }

    /**
     * @return the listaUbicacion
     */
    public List<CatalogoInv> getListaUbicacion() {
        return listaUbicacion;
    }

    /**
     * @param listaUbicacion the listaUbicacion to set
     */
    public void setListaUbicacion(List<CatalogoInv> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    /**
     * @return the listaEstado
     */
    public List<CatalogoInv> getListaEstado() {
        return listaEstado;
    }

    /**
     * @param listaEstado the listaEstado to set
     */
    public void setListaEstado(List<CatalogoInv> listaEstado) {
        this.listaEstado = listaEstado;
    }

    /**
     * @return the movimiento
     */
    public Movimiento getMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * @return the movimientoEditar
     */
    public Movimiento getMovimientoEditar() {
        return movimientoEditar;
    }

    /**
     * @param movimientoEditar the movimientoEditar to set
     */
    public void setMovimientoEditar(Movimiento movimientoEditar) {
        this.movimientoEditar = movimientoEditar;
    }

    /**
     * @return the listaMovimiento
     */
    public List<Movimiento> getListaMovimiento() {
        return listaMovimiento;
    }

    /**
     * @param listaMovimiento the listaMovimiento to set
     */
    public void setListaMovimiento(List<Movimiento> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }

    /**
     * @return the catalogoEdit
     */
    public CatalogoInv getCatalogoEdit() {
        return catalogoEdit;
    }

    /**
     * @param catalogoEdit the catalogoEdit to set
     */
    public void setCatalogoEdit(CatalogoInv catalogoEdit) {
        this.catalogoEdit = catalogoEdit;
    }
}
