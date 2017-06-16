package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.FichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.CatalogoWrapper;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
public class ActividadMineraCtrl extends BaseCtrl {
    public static final String TIPOINSTITUCION= "TIPOINFINSTPART";
    public static final String TIPODETENIDO= "TIPOINFDETOPE";
    public static final String TIPODEPOSITARIO= "TIPOINFDEPMAQ";
    public static final String INSERT= "INSERT";
    public static final String UPDATE=  "UPDATE";
    public static final String DELETE= "DELETE";
    
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
    
    private Date fechaMaxima;
    
    private List<FichaTecnica> fichasTecnicas;
    private FichaTecnica fichaTecnica;
    
    private List<DetalleFichaTecnica> detallesFichaTecnica;
    private DetalleFichaTecnica detalleFichaTecnica;
    
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
    private List<CatalogoWrapper> infraestruturasWrapper;
    private List<CatalogoDetalle> maquinarias;
    private List<CatalogoWrapper> maquinariasWrapper;
    private List<Catalogo> tiposMaterial;
    private List<CatalogoDetalle> mineralesInteres;
    private List<CatalogoDetalle> formasExplotacion;
    private List<CatalogoDetalle> sistemasExplotacion;
    private List<CatalogoDetalle> operacionesMineras;
    private List<CatalogoWrapper> operacionesMinerasWrapper;
    
    private boolean edit= false;
    private boolean showDetalleTipoTerreno= false;
    private boolean codigoCensal= false;
    private boolean derechoMinero= false;
    private boolean showSubterraneo= false;
    private boolean showCieloAbierto= false;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    /**
     * Creates a new instance of OperativoCtrl
     */
    public ActividadMineraCtrl() {
        fichaTecnica= new FichaTecnica();
        coordenadas= new ArrayList();
        contratos= new ArrayList();
    }
    
    @PostConstruct
    private void inicializar() {
        cargarFichasTecnicas();
        //Esta línea es para pruebas, se debe quitar para poner en producción
        login.setCodigoUsuario((long) 1689);
    }
    
    ///////////////////////////////////////////////////////

    public Date getFechaMaxima() {
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

    public boolean isDerechoMinero() {
        return derechoMinero;
    }

    public void setDerechoMinero(boolean derechoMinero) {
        this.derechoMinero = derechoMinero;
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
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOTERR");
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
    
    public List<CatalogoWrapper> getInfraestruturasWrapper() {
        infraestructuras= getInfraestructuras();
        infraestruturasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : infraestructuras) {
            CatalogoWrapper cw= new CatalogoWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            infraestruturasWrapper.add(cw);
        }
        return infraestruturasWrapper;
    }

    public void setInfraestruturasWrapper(List<CatalogoWrapper> infraestruturasWrapper) {
        this.infraestruturasWrapper = infraestruturasWrapper;
    }

    public List<CatalogoWrapper> getMaquinariasWrapper() {
        maquinarias= getMaquinarias();
        maquinariasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : maquinarias) {
            CatalogoWrapper cw= new CatalogoWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            maquinariasWrapper.add(cw);
        }
        return maquinariasWrapper;
    }

    public void setMaquinariasWrapper(List<CatalogoWrapper> maquinariasWrapper) {
        this.maquinariasWrapper = maquinariasWrapper;
    }

    public List<CatalogoWrapper> getOperacionesMinerasWrapper() {
        operacionesMineras= getOperacionesMineras();
        operacionesMinerasWrapper= new ArrayList<>();
        for(CatalogoDetalle i : operacionesMineras) {
            CatalogoWrapper cw= new CatalogoWrapper();
            cw.setCatalogoDetalle(i);
            cw.setOpcion(Boolean.FALSE);
            operacionesMinerasWrapper.add(cw);
        }
        return operacionesMinerasWrapper;
    }

    public void setOperacionesMinerasWrapper(List<CatalogoWrapper> operacionesMinerasWrapper) {
        this.operacionesMinerasWrapper = operacionesMinerasWrapper;
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
    
    public void newFichaTecnicaAction() {
        
    }
    
    private void cargarFichasTecnicas() {
        fichasTecnicas= fichaTecnicaServicio.listar();
    }
}
