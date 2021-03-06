package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.servicio.DetalleFichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.FichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.DateUtil;
import ec.gob.arcom.migracion.util.DetalleFichaTecnicaWrapper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

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
@SessionScoped
public class ActividadMineraCtrl extends BaseCtrl {
    public static final String INSERT= "INSERT";
    public static final String UPDATE=  "UPDATE";
    public static final String DELETE= "DELETE";
    
    @EJB
    private LocalidadRegionalServicio localidadRegionalServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private FichaTecnicaServicio fichaTecnicaServicio;
    @EJB
    private DetalleFichaTecnicaServicio detalleFichaTecnicaServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private CoordenadaAreaServicio coordenadaAreaServicio;
    @EJB
    private AreaMineraServicio areaMineraServicio;
    @EJB
    private ContratoOperacionServicio contratoOperacionServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    
    private Date fechaMaxima;
    
    private List<FichaTecnica> fichasTecnicas;
    private FichaTecnica fichaTecnica;
    
    private List<CatalogoDetalle> zonasGeograficas;
    private List<CatalogoDetalle> etnias;
    private List<Localidad> provincias;
    private List<Localidad> cantones;
    private List<Localidad> parroquias;
    private List<CatalogoDetalle> tiposTerreno;
    private List<CatalogoDetalle> condicionesLaborMinera;
    private List<CoordenadaArea> coordenadas;
    private List<ContratoOperacion> contratos;
    private List<CatalogoDetalle> modalidadesTrabajo;
    private List<CatalogoDetalle> infraestructuras;
    private List<CatalogoDetalle> tiposEnergia;
    private List<CatalogoDetalle> tiposAgua;
    private List<DetalleFichaTecnicaWrapper> infraestruturasWrapper;
    private List<CatalogoDetalle> maquinarias;
    private List<DetalleFichaTecnicaWrapper> maquinariasWrapper;
    private List<Catalogo> tiposMaterial;
    private List<CatalogoDetalle> mineralesInteres;
    private List<CatalogoDetalle> formasExplotacion;
    private List<CatalogoDetalle> sistemasExplotacion;
    private List<CatalogoDetalle> operacionesMineras;
    private List<DetalleFichaTecnicaWrapper> operacionesMinerasWrapper;
    private List<DetalleFichaTecnica> sociosLaborMinera;
    
    private boolean edit= false;
    private boolean showDetalleTipoTerreno= false;
    private boolean codigoCensal= false;
    private boolean showDerechoMinero= false;
    private boolean showSubterraneo= false;
    private boolean showCieloAbierto= false;
    private Map<String, String> secuenciasPorCoordinacion;
    private String tipoPersona= "";
    
    private String codigoArcom= "";
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    /**
     * Creates a new instance of OperativoCtrl
     */
    public ActividadMineraCtrl() {
        fichaTecnica= new FichaTecnica();
        coordenadas= new ArrayList();
        contratos= new ArrayList();
        secuenciasPorCoordinacion= new HashMap<>();
    }
    
    @PostConstruct
    private void inicializar() {
        cargarFichasTecnicas();
    }
    
    public boolean isEdit() {
        return edit;
    }

    ///////////////////////////////////////////////////////
    public void setEdit(boolean edit) {    
        this.edit = edit;
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

    public List<FichaTecnica> getFichasTecnicas() {
        return fichasTecnicas;
    }

    public void setFichasTecnicas(List<FichaTecnica> fichasTecnicas) {
        this.fichasTecnicas = fichasTecnicas;
    }

    public FichaTecnica getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(FichaTecnica fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public boolean isShowDetalleTipoTerreno() {
        return showDetalleTipoTerreno;
    }

    public void setShowDetalleTipoTerreno(boolean showDetalleTipoTerreno) {
        this.showDetalleTipoTerreno = showDetalleTipoTerreno;
    }

    public boolean isCodigoCensal() {
        return codigoCensal;
    }

    public void setCodigoCensal(boolean codigoCensal) {
        this.codigoCensal = codigoCensal;
    }

    public List<CoordenadaArea> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<CoordenadaArea> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public List<ContratoOperacion> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoOperacion> contratos) {
        this.contratos = contratos;
    }

    public boolean isShowDerechoMinero() {
        return showDerechoMinero;
    }

    public void setShowDerechoMinero(boolean showDerechoMinero) {
        this.showDerechoMinero = showDerechoMinero;
    }
    
    public boolean isShowSubterraneo() {
        return showSubterraneo;
    }

    public void setShowSubterraneo(boolean showSubterraneo) {
        this.showSubterraneo = showSubterraneo;
    }

    public boolean isShowCieloAbierto() {
        return showCieloAbierto;
    }

    public void setShowCieloAbierto(boolean showCieloAbierto) {
        this.showCieloAbierto = showCieloAbierto;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }
    
    public List<CatalogoDetalle> getZonas() {
        if (zonasGeograficas == null) {
            zonasGeograficas = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("ZONGEO");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    zonasGeograficas.add(catDet);
                }
            }
        }
        return zonasGeograficas;
    }
    
    public List<CatalogoDetalle> getEtnias() {
        if (etnias == null) {
            etnias = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOETNIA");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    etnias.add(catDet);
                }
            }

        }
        return etnias;
    }
    
    public List<CatalogoDetalle> getTiposTerreno() {
        if (tiposTerreno == null) {
            tiposTerreno = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOTERR");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposTerreno.add(catDet);
                }
            }

        }
        return tiposTerreno;
    }
    
    public List<CatalogoDetalle> getTiposEnergia() {
        if (tiposEnergia == null) {
            tiposEnergia = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOENER");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposEnergia.add(catDet);
                }
            }

        }
        return tiposEnergia;
    }
    
    public List<CatalogoDetalle> getTiposAgua() {
        if (tiposAgua == null) {
            tiposAgua = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOAGUA");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposAgua.add(catDet);
                }
            }

        }
        return tiposAgua;
    }
    
    public List<CatalogoDetalle> getCondicionesLaborMinera() {
        if (condicionesLaborMinera == null) {
            condicionesLaborMinera = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOCON");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    condicionesLaborMinera.add(catDet);
                }
            }

        }
        return condicionesLaborMinera;
    }
    
    public List<CatalogoDetalle> getInfraestructuras() {
        if (infraestructuras == null) {
            infraestructuras = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("INFACTMIN");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    infraestructuras.add(catDet);
                }
            }
        }
        return infraestructuras;
    }
    
    public List<CatalogoDetalle> getMaquinarias() {
        if (maquinarias == null) {
            maquinarias = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("MAQACTMIN");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    maquinarias.add(catDet);
                }
            }
        }
        return maquinarias;
    }
    
    public List<CatalogoDetalle> getOperacionesMineras() {
        if (operacionesMineras == null) {
            operacionesMineras = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOPER");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    operacionesMineras.add(catDet);
                }
            }
        }
        return operacionesMineras;
    }
    
    public List<Catalogo> getTiposMaterial() {
        if (tiposMaterial == null) {
            tiposMaterial = new ArrayList<>();
            
            Catalogo catalogo = catalogoServicio.findByNemonico("MINSMET");
            tiposMaterial.add(catalogo);
            
            catalogo= catalogoServicio.findByNemonico("MINSNMET");
            tiposMaterial.add(catalogo);
        }
        return tiposMaterial;
    }
    
    public List<CatalogoDetalle> getMineralesInteres() {
        if (mineralesInteres == null) {
            mineralesInteres = new ArrayList<>();
            Catalogo catalogo = fichaTecnica.getTipoMaterial();
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    mineralesInteres.add(catDet);
                }
            }
        }
        return mineralesInteres;
    }
    
    public List<CatalogoDetalle> getFormasExplotacion() {
        if (formasExplotacion == null) {
            formasExplotacion = new ArrayList<>();
            
            CatalogoDetalle catdet = catalogoDetalleServicio.obtenerPorNemonico("SECIEABIRODU").get(0);
            formasExplotacion.add(catdet);
            
            catdet= catalogoDetalleServicio.obtenerPorNemonico("SESUBTE").get(0);
            formasExplotacion.add(catdet);
        }
        return formasExplotacion;
    }
    
    public List<CatalogoDetalle> getSistemasExplotacion() {
        if (sistemasExplotacion == null) {
            sistemasExplotacion = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("SISTEXPLOTACION");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    if(fichaTecnica.getFormaExplotacion()!=null) {
                        if(catDet.getValor().equals(fichaTecnica.getFormaExplotacion().getValor())) {
                            sistemasExplotacion.add(catDet);
                        }
                    }
                }
            }
        }
        return sistemasExplotacion;
    }
    
    public List<DetalleFichaTecnicaWrapper> getInfraestruturasWrapper() {
        return infraestruturasWrapper;
    }

    public void setInfraestruturasWrapper(List<DetalleFichaTecnicaWrapper> infraestruturasWrapper) {
        this.infraestruturasWrapper = infraestruturasWrapper;
    }

    public List<DetalleFichaTecnicaWrapper> getMaquinariasWrapper() {
        return maquinariasWrapper;
    }

    public void setMaquinariasWrapper(List<DetalleFichaTecnicaWrapper> maquinariasWrapper) {
        this.maquinariasWrapper = maquinariasWrapper;
    }

    public List<DetalleFichaTecnicaWrapper> getOperacionesMinerasWrapper() {
        return operacionesMinerasWrapper;
    }

    public void setOperacionesMinerasWrapper(List<DetalleFichaTecnicaWrapper> operacionesMinerasWrapper) {
        this.operacionesMinerasWrapper = operacionesMinerasWrapper;
    }

    public List<DetalleFichaTecnica> getSociosLaborMinera() {
        return sociosLaborMinera;
    }

    public void setSociosLaborMinera(List<DetalleFichaTecnica> sociosLaborMinera) {
        this.sociosLaborMinera = sociosLaborMinera;
    }
    
    public List<CatalogoDetalle> getModalidadesTrabajo() {
        if (modalidadesTrabajo == null) {
            modalidadesTrabajo = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("MODT");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    modalidadesTrabajo.add(catDet);
                }
            }
        }
        return modalidadesTrabajo;
    }
    
    public List<Localidad> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(provincia);
            }
        }
        return provincias;
    }
    
    public List<Localidad> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList<>();
            if (fichaTecnica.getProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(fichaTecnica.getProvincia().getCodigoLocalidad().toString()));
            if (catalogoCanton == null || (catalogoCanton != null && catalogoCanton.getCodigoLocalidad() == null)) {
                return cantones;
            }
            List<Localidad> cantonCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoCanton.getCodigoLocalidad()));

            for (Localidad canton : cantonCat) {
                cantones.add(canton);
            }
        }
        return cantones;
    }
    
    public List<Localidad> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (fichaTecnica.getCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(fichaTecnica.getCanton().getCodigoLocalidad().toString()));
            if (catalogoParroquia == null || (catalogoParroquia != null && catalogoParroquia.getCodigoInternacional() == null)) {
                return parroquias;
            }
            List<Localidad> parroquiaCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoParroquia.getCodigoLocalidad()));

            for (Localidad parroquia : parroquiaCat) {
                parroquias.add(parroquia);
            }
        }
        return parroquias;
    }
    
    public void cargarCantones() {
        cantones = null;
        parroquias = null;
        fichaTecnica.setCanton(null);
        getCantones();
        getParroquias();
    }
    
    public void cargarParroquias() {
        parroquias = null;
        getParroquias();
    }
    
    public void cargarMinerales() {
        mineralesInteres= null;
        getMineralesInteres();
    }
    
    public void cargarSistemasExplotacion() {
        sistemasExplotacion= null;
        getSistemasExplotacion();
        if(fichaTecnica.getFormaExplotacion().getValor().equals("SECIEABIRODU")) {
            showCieloAbierto= true;
            showSubterraneo= false;
        } else if(fichaTecnica.getFormaExplotacion().getValor().equals("SESUBTE")) {
            showCieloAbierto= false;
            showSubterraneo= true;
        } else {
            showCieloAbierto= false;
            showSubterraneo= false;
        }
    }
    
    public void changeShowDetalleTipoTerreno() {
        showDetalleTipoTerreno = fichaTecnica.getTipoTerreno().getNombre().equals("OTRO");
    }
    
    public String obtenerNombreCatalogoDetalle(CatalogoDetalle catdet) {
        return catdet.getNombre();
    }
    
    ///////////////////////////////////////////////////////
    
    public String newFichaTecnicaAction() {
        edit= false;
        this.fichaTecnica= new FichaTecnica();
        this.secuenciasPorCoordinacion= new HashMap<>();
        resetAction();
        Usuario usr= usuarioServicio.findByPk(login.getCodigoUsuario());
        this.fichaTecnica.setUsuarioElaboracion(usr);
        //this.fichaTecnica.setConcesionMinera(new ConcesionMinera());
        this.sociosLaborMinera= new ArrayList<>();
        cargarInfraestructuraWrapper();
        cargarMaquinariasWrapper();
        cargarOperacionesMinerasWrapper();
        return "fichafrm";
    }
    
    public String saveFichaTecnicaAction() {
        if(edit) {
            if(actualizar()) {
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ficha actualizada correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al actualizar");
            }
        } else {
            if(guardar()) {
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ficha guardada correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al guardar");
            }
        }
        return endAction();
    }
    
    public String obtenerFecha(Date fecha) {
        if(fecha!=null) {
            return DateUtil.obtenerFechaConFormato(fecha);
        }
        return "";
    }
    
    public String cancelAction() {
        return endAction();
    }
    
    public void addSocioAction() {
        DetalleFichaTecnica dft= new DetalleFichaTecnica();
        sociosLaborMinera.add(dft);
    }
    
    public String editAction(FichaTecnica ft) {
        edit= true;
        resetAction();
        this.fichaTecnica= ft;
        getCantones();
        getParroquias();
        getMineralesInteres();
        getSistemasExplotacion();
        
        if(fichaTecnica.getCodigoCensal()!=null && fichaTecnica.getCodigoCensal().length()>0) {
            this.codigoCensal= true;
        } else {
            this.codigoCensal= false;
        }
        
        if(fichaTecnica.getTipoTerreno().getNombre().equals("OTRO")) {
            this.showDetalleTipoTerreno= true;
        } else {
            this.showDetalleTipoTerreno= false;
        }
        
        if(fichaTecnica.getConcesionMinera() != null && fichaTecnica.getConcesionMinera().getCodigoConcesion() != null) {
            this.showDerechoMinero= true;
            this.codigoArcom= fichaTecnica.getConcesionMinera().getCodigoArcom();
            buscarPersona();
            this.coordenadas= coordenadaAreaServicio.findByCodigoArea(fichaTecnica.getConcesionMinera().getCodigoConcesion());
            this.contratos= contratoOperacionServicio.listarPorCodigoConcesion(fichaTecnica.getConcesionMinera().getCodigoConcesion());
        } else {
            this.showDerechoMinero= false;
        }
        
        if(this.fichaTecnica.getFormaExplotacion().getNemonico().equals("SECIEABIRODU")) {
            this.showCieloAbierto= true;
            this.showSubterraneo= false;
        } else if(this.fichaTecnica.getFormaExplotacion().getNemonico().equals("SESUBTE")) {
            this.showCieloAbierto= false;
            this.showSubterraneo= true;
        } else {
            this.showCieloAbierto= false;
            this.showSubterraneo= false;
        }
        
        //Cargar infraestructuras - maquinarias - operaciones mineras
        List<DetalleFichaTecnica> detallesFichaTecnica= detalleFichaTecnicaServicio.listarPorFichaTecnica(fichaTecnica.getCodigoFichaTecnica());
        infraestruturasWrapper= new ArrayList<>();
        maquinariasWrapper= new ArrayList<>();
        operacionesMinerasWrapper= new ArrayList<>();
        for(DetalleFichaTecnica dft : detallesFichaTecnica) {
            DetalleFichaTecnicaWrapper cw= new DetalleFichaTecnicaWrapper();
            if(dft.getCodigoTipoInformacionRegistro().getNemonico().equals("TIPOINFINFACT")) {
                cw.setCatalogoDetalle(dft.getCodigoCatalogo());
                cw.setOpcion(dft.isCodigoOpcion());
                cw.setCodigoDetalleFichaTecnica(dft.getCodigoDetalleFichaTecnica());
                infraestruturasWrapper.add(cw);
            } else if(dft.getCodigoTipoInformacionRegistro().getNemonico().equals("TIPOINFEQUMAQ")) {
                cw.setCatalogoDetalle(dft.getCodigoCatalogo());
                cw.setOpcion(dft.isCodigoOpcion());
                cw.setCantidad(dft.getCantidad());
                cw.setCodigoDetalleFichaTecnica(dft.getCodigoDetalleFichaTecnica());
                maquinariasWrapper.add(cw);
            } else if(dft.getCodigoTipoInformacionRegistro().getNemonico().equals("TIPOINFPORMIN")) {
                cw.setCatalogoDetalle(dft.getCodigoCatalogo());
                cw.setOpcion(dft.isCodigoOpcion());
                cw.setCodigoDetalleFichaTecnica(dft.getCodigoDetalleFichaTecnica());
                operacionesMinerasWrapper.add(cw);
            }
        }
        
        //Cargar socios
        sociosLaborMinera= detalleFichaTecnicaServicio.listarSociosPorFichaTecnica(fichaTecnica.getCodigoFichaTecnica());
        return "fichafrm";
    }
    
    private void cargarFichasTecnicas() {
        //fichasTecnicas= fichaTecnicaServicio.listarPorUsuarioCreacion(login.getCodigoUsuario());
        fichasTecnicas= fichaTecnicaServicio.listarPorUsuarioCreacion(login.getCodigoUsuario(), catalogoDetalleServicio.obtenerPorNemonico("TIPOINFFICTEC").get(0));
    }
    
    private void cargarInfraestructuraWrapper() {
        infraestructuras= getInfraestructuras();
        infraestruturasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : infraestructuras) {
            DetalleFichaTecnicaWrapper cw= new DetalleFichaTecnicaWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            infraestruturasWrapper.add(cw);
        }
    }
    
    private void cargarMaquinariasWrapper() {
        maquinarias= getMaquinarias();
        maquinariasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : maquinarias) {
            DetalleFichaTecnicaWrapper cw= new DetalleFichaTecnicaWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            cw.setCantidad((long)0);
            maquinariasWrapper.add(cw);
        }
    }
    
    private void cargarOperacionesMinerasWrapper() {
        operacionesMineras= getOperacionesMineras();
        operacionesMinerasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : operacionesMineras) {
            DetalleFichaTecnicaWrapper cw= new DetalleFichaTecnicaWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            operacionesMinerasWrapper.add(cw);
        }
    }
    
    private void resetAction() {
        this.codigoCensal= false;
        this.showDerechoMinero= false;
        this.showCieloAbierto= false;
        this.showSubterraneo= false;
        this.codigoArcom= "";
        this.coordenadas= new ArrayList<>();
        this.contratos= new ArrayList<>();
        this.provincias= null;
        this.cantones= null;
        this.parroquias= null;
        this.mineralesInteres= null;
        this.sistemasExplotacion= null;
    }
    
    private String endAction() {
        fichaTecnica= new FichaTecnica();
        cargarFichasTecnicas();
        return "actividadesmineras";
    }
    
    ////////////////////////////////////////////////////////
    
    public void mostrarMensaje(FacesMessage.Severity s, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, "", msg));
    }
    
    private boolean guardar() {
        return guardarFichaTecnica() && guardarInfraestructuras() && guardarMaquinarias() && guardarOperacionesMineras() && guardarSocios();
    }
    
    private void comprobarCondicionales() {
        if(!isShowDerechoMinero()) {
            fichaTecnica.setConcesionMinera(null);
        }
        if(!isCodigoCensal()) {
            fichaTecnica.setCodigoCensal("");
        }
        if(!fichaTecnica.getTipoTerreno().getNombre().equals("OTRO")) {
            fichaTecnica.setDetalleTipoTerreno("");
        }
        if(fichaTecnica.getFormaExplotacion().getNemonico().equals("SECIEABIRODU")) {
            fichaTecnica.setLongitudSubterranea(null);
            fichaTecnica.setAnchoSubterranea(null);
            fichaTecnica.setAltoSubterranea(null);
            fichaTecnica.setRumboSubterranea(null);
        } else {
            fichaTecnica.setLongitudCieloAbierto(null);
            fichaTecnica.setAnchoCieloAbierto(null);
            fichaTecnica.setAltoCieloAbierto(null);
        }
    }
    
    private boolean guardarFichaTecnica() {
        comprobarCondicionales();
        fichaTecnica.setUtmEste(fichaTecnica.getUtmEste().replace(".", ","));
        fichaTecnica.setUtmNorte(fichaTecnica.getUtmNorte().replace(".", ","));
        fichaTecnica.setTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFFICTEC").get(0));
        fichaTecnica.setEstadoRegistro(Boolean.TRUE);
        fichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
        fichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
        fichaTecnicaServicio.create(fichaTecnica);
        return true;
    }
    
    private boolean guardarSocios() {
        for(DetalleFichaTecnica detFichaTecnica : sociosLaborMinera) {
            detFichaTecnica.setCodigoTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFSOCLAB").get(0));
            detFichaTecnica.setFichaTecnica(fichaTecnica);
            detFichaTecnica.setEstadoRegistro(Boolean.TRUE);
            detFichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.create(detFichaTecnica);
        }
        return true;
    }
    
    private boolean guardarInfraestructuras() {
        for(DetalleFichaTecnicaWrapper catWrapper : infraestruturasWrapper) {
            DetalleFichaTecnica detFichaTecnica= new DetalleFichaTecnica();
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setCodigoTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFINFACT").get(0));
            
            detFichaTecnica.setFichaTecnica(fichaTecnica);
            detFichaTecnica.setEstadoRegistro(Boolean.TRUE);
            detFichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.create(detFichaTecnica);
        }
        return true;
    }
    
    private boolean guardarMaquinarias() {
        for(DetalleFichaTecnicaWrapper catWrapper : maquinariasWrapper) {
            DetalleFichaTecnica detFichaTecnica= new DetalleFichaTecnica();
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setCantidad(catWrapper.getCantidad());
            detFichaTecnica.setCodigoTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFEQUMAQ").get(0));
            
            detFichaTecnica.setFichaTecnica(fichaTecnica);
            detFichaTecnica.setEstadoRegistro(Boolean.TRUE);
            detFichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.create(detFichaTecnica);
        }
        return true;
    }
    
    private boolean guardarOperacionesMineras() {
        for(DetalleFichaTecnicaWrapper catWrapper : operacionesMinerasWrapper) {
            DetalleFichaTecnica detFichaTecnica= new DetalleFichaTecnica();
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setCodigoTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFPORMIN").get(0));
            
            detFichaTecnica.setFichaTecnica(fichaTecnica);
            detFichaTecnica.setEstadoRegistro(Boolean.TRUE);
            detFichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.create(detFichaTecnica);
        }
        return true;
    }
    
    public void buscarDerechoMinero() {
        if(codigoArcom.length()>0) {
            ConcesionMinera cm= concesionMineraServicio.obtenerPorCodigoArcom(codigoArcom);
            if(cm!=null) {
                fichaTecnica.setConcesionMinera(cm);
                AreaMinera am= areaMineraServicio.obtenerPorConcesionMinera(cm.getCodigoConcesion());
                this.coordenadas= coordenadaAreaServicio.findByCodigoArea(am.getCodigoAreaMinera());
                this.contratos= contratoOperacionServicio.listarPorCodigoConcesion(cm.getCodigoConcesion());
                buscarPersona();
            }
            RequestContext.getCurrentInstance().update("derechoMineroPanel");
        }
    }
    
    private boolean actualizar() {
        return actualizarFichaTecnica() && actualizarInfraestructuras() && actualizarMaquinarias() && actualizarOperacionesMineras() && actualizarSocios();
    }
    
    private boolean actualizarFichaTecnica() {
        comprobarCondicionales();
        fichaTecnica.setUtmEste(fichaTecnica.getUtmEste().replace(".", ","));
        fichaTecnica.setUtmNorte(fichaTecnica.getUtmNorte().replace(".", ","));
        fichaTecnica.setFechaModificacion(Calendar.getInstance().getTime());
        fichaTecnica.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
        fichaTecnicaServicio.update(fichaTecnica);
        return true;
    }
    
    private boolean actualizarSocios() {
        for(DetalleFichaTecnica detFichaTecnica : sociosLaborMinera) {
            if(detFichaTecnica.getCodigoDetalleFichaTecnica()==null) {
                detFichaTecnica.setCodigoTipoInformacionRegistro(catalogoDetalleServicio.obtenerPorNemonico("TIPOINFSOCLAB").get(0));
                detFichaTecnica.setFichaTecnica(fichaTecnica);
                detFichaTecnica.setEstadoRegistro(Boolean.TRUE);
                detFichaTecnica.setFechaCreacion(Calendar.getInstance().getTime());
                detFichaTecnica.setUsuarioCreacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
                detalleFichaTecnicaServicio.create(detFichaTecnica);
            } else {
                detFichaTecnica.setFechaModificacion(Calendar.getInstance().getTime());
                detFichaTecnica.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
                detalleFichaTecnicaServicio.update(detFichaTecnica);
            }
        }
        return true;
    }
    
    private boolean actualizarInfraestructuras() {
        for(DetalleFichaTecnicaWrapper catWrapper : infraestruturasWrapper) {
            DetalleFichaTecnica detFichaTecnica= detalleFichaTecnicaServicio.findByPk(catWrapper.getCodigoDetalleFichaTecnica());
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setFechaModificacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.update(detFichaTecnica);
        }
        return true;
    }
    
    private boolean actualizarMaquinarias() {
        for(DetalleFichaTecnicaWrapper catWrapper : maquinariasWrapper) {
            DetalleFichaTecnica detFichaTecnica= detalleFichaTecnicaServicio.findByPk(catWrapper.getCodigoDetalleFichaTecnica());
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setCantidad(catWrapper.getCantidad());
            detFichaTecnica.setFechaModificacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.update(detFichaTecnica);
        }
        return true;
    }
    
    private boolean actualizarOperacionesMineras() {
        for(DetalleFichaTecnicaWrapper catWrapper : operacionesMinerasWrapper) {
            DetalleFichaTecnica detFichaTecnica= detalleFichaTecnicaServicio.findByPk(catWrapper.getCodigoDetalleFichaTecnica());
            
            detFichaTecnica.setCodigoCatalogo(catWrapper.getCatalogoDetalle());
            detFichaTecnica.setCodigoOpcion(catWrapper.isOpcion());
            detFichaTecnica.setFechaModificacion(Calendar.getInstance().getTime());
            detFichaTecnica.setUsuarioModificacion(usuarioServicio.findByPk(login.getCodigoUsuario()));
            detalleFichaTecnicaServicio.update(detFichaTecnica);
        }
        return true;
    }
    
    public void deleteSocioAction(DetalleFichaTecnica socio) {
        if(socio.getCodigoDetalleFichaTecnica()== null) {
            sociosLaborMinera.remove(socio);
        } else {
            sociosLaborMinera.remove(socio);
            detalleFichaTecnicaServicio.delete(socio.getCodigoDetalleFichaTecnica());
        }
    }
    
    public void establecerRegional() {
        fichaTecnica.setRegional(obtenerRegional(fichaTecnica.getProvincia().getCodigoLocalidad()));
    }
    
    public Regional obtenerRegional(Long codigoProvincia) {
        LocalidadRegional localidadRegional = localidadRegionalServicio.obtenerPorCodigoLocalidad(codigoProvincia);
        return regionalServicio.findByPk(localidadRegional.getLocalidadRegionalPK().getCodigoRegional());
    }
    
    public Long obtenerSecuenciaFichaTecnica(String prefijoRegional) {
        Secuencia secuenciaConcesion = secuenciaServicio.obtenerPorTabla("SECACTMINORGL" + prefijoRegional);
        Long codigoConcesionSiguiente = secuenciaConcesion.getValor();
        //fichaTecnica.setNumeroFormulario(formarCodigoFormulario(prefijoRegional, codigoConcesionSiguiente));
        secuenciaConcesion.setValor(codigoConcesionSiguiente + 1);
        secuenciaServicio.update(secuenciaConcesion);
        return codigoConcesionSiguiente;
    }
    
    public String obtenerPrefijoTxt(String prefijoRegional) {
        String prefijoTxt= "";
        if(prefijoRegional.equals("01")) {
            prefijoTxt= "CUEN";
        } else if(prefijoRegional.equals("02")) {
            prefijoTxt= "RIOB";
        } else if(prefijoRegional.equals("03")) {
            prefijoTxt= "MACH";
        } else if(prefijoRegional.equals("04")) {
            prefijoTxt= "IBAR";
        } else if(prefijoRegional.equals("05")) {
            prefijoTxt= "ZAMO";
        } else if(prefijoRegional.equals("06")) {
            prefijoTxt= "LOJA";
        } else if(prefijoRegional.equals("07")) {
            prefijoTxt= "GUAY";
        } else if(prefijoRegional.equals("09")) {
            prefijoTxt= "MACAS";
        } else if(prefijoRegional.equals("10")) {
            prefijoTxt= "TENA";
        }
        return prefijoTxt;
    }
    
    protected String formarCodigoFormulario(String prefijoTxt, Long secuencial) {
        String codigo = secuencial.toString();
        while (codigo.length() < 4) {
            codigo = "0" + codigo;
        }
        codigo = prefijoTxt + codigo;
        return codigo;
    }
    
    public void establecerNumeroFormulario() {
        String prefijoRegional= obtenerRegional(fichaTecnica.getProvincia().getCodigoLocalidad()).getPrefijoCodigo();
        String prefijoTxt= obtenerPrefijoTxt(prefijoRegional);
        String numFormulario= "";
        numFormulario= secuenciasPorCoordinacion.get(prefijoTxt);
        
        if(numFormulario == null) {
            Long secuenciaNumeroFormulario= obtenerSecuenciaFichaTecnica(prefijoRegional);
            numFormulario= formarCodigoFormulario(prefijoTxt, secuenciaNumeroFormulario);
            fichaTecnica.setNumeroFormulario(numFormulario);
            secuenciasPorCoordinacion.put(prefijoTxt, numFormulario);
        } else {
            fichaTecnica.setNumeroFormulario(numFormulario);
        }
    }
    
    public void llamarListeners() {
        cargarCantones();
        establecerRegional();
        establecerNumeroFormulario();
    }
    
    private void buscarPersona() {
        PersonaNatural personaNatural = personaNaturalServicio.findByNumeroDocumento(fichaTecnica.getConcesionMinera().getDocumentoConcesionarioPrincipal());
        if (personaNatural != null) {
            tipoPersona = "N";
            fichaTecnica.getConcesionMinera().setPersonaNaturalTransient(personaNatural);
        } else {
            PersonaJuridica personaJuridica = personaJuridicaServicio.findByRuc(fichaTecnica.getConcesionMinera().getDocumentoConcesionarioPrincipal());
            if (personaJuridica != null) {
                tipoPersona = "J";
                fichaTecnica.getConcesionMinera().setPersonaJuridicaTransient(personaJuridica);
            }
        }
    }
    
    public String obtenerTelefono() {
        if(tipoPersona.equals("N")) {
            try {
                return fichaTecnica.getConcesionMinera().getPersonaNaturalTransient().getTelefono();
            } catch(Exception ex) {}
        } else if(tipoPersona.equals("J")) {
            try {
                return fichaTecnica.getConcesionMinera().getPersonaJuridicaTransient().getTelefono();
            } catch(Exception ex) {}
        }
        return "";
    }
    
    public String obtenerCelular() {
        if(tipoPersona.equals("N")) {
            try {
                return fichaTecnica.getConcesionMinera().getPersonaNaturalTransient().getCelular();
            } catch(Exception ex) {}
        } else if(tipoPersona.equals("J")) {
            try {
                return fichaTecnica.getConcesionMinera().getPersonaJuridicaTransient().getCelular();
            } catch(Exception ex) {}
        }
        return "";
    }
    
    public String obtenerRepresentanteLegal() {
        if(tipoPersona.equals("N")) {
            return "";
        } else if(tipoPersona.equals("J")) {
            try {
                return fichaTecnica.getConcesionMinera().getPersonaJuridicaTransient().getNombreRepresentanteLegal() + " " + 
                    fichaTecnica.getConcesionMinera().getPersonaJuridicaTransient().getApellidoRepresentanteLegal();
            } catch(Exception ex) {}
        }
        return "";
    }
    
    private String changePoint4Coma(String value) {
        return value.replace(".", ",");
    }
}
