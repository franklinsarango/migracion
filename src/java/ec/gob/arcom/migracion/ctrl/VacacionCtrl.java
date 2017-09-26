/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Licencia;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.DateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
    
    
    private Licencia licencia;
    private Date fechaMinimaInicio;
    private Date fechaMinimaFin;
    private List<SelectItem> tiposFormulario;
    private List<SelectItem> tiposLicencia;
    private String motivoLicencia;
    private boolean showVacacionPanel;
    private boolean showCalamidadPanel;
    private boolean showInstitucionalPanel;
    private boolean showButtonPanel;
    private boolean showHour;
    
    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    /**
     * Creates a new instance of VacacionCtrl
     */
    public VacacionCtrl() {
        showVacacionPanel= false;
        showCalamidadPanel= false;
        showInstitucionalPanel= false;
        showButtonPanel= false;
        showHour= false;
    }
    
    @PostConstruct
    private void inicializar() {
        login.setCodigoUsuario(1689L);
    }
    
    ///////////////////
    
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

    public Date getFechaMinimaInicio() {
        fechaMinimaInicio= Calendar.getInstance().getTime();
        return fechaMinimaInicio;
    }

    public Date getFechaMinimaFin() {
        fechaMinimaFin= Calendar.getInstance().getTime();
        return fechaMinimaFin;
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
    
    public boolean isShowHour() {
        return showHour;
    }

    public void setShowHour(boolean showHour) {
        this.showHour = showHour;
    }
    
    ///////////////////
    
    public List<SelectItem> getTiposFormulario() {
        if (tiposFormulario == null) {
            tiposFormulario = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPOFOR");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tiposFormulario.add(new SelectItem(catDet, catDet.getNombre()));
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
    
    ///////////////////
    
    public String cancelAction() {
        return resetAction();
    }
    
    public String newSolicitudAction() {
        licencia= new Licencia();
        licencia.setNumeroSolicitud("00028");
        licencia.setUsuario(usuarioServicio.findByPk(login.getCodigoUsuario()));
        licencia.setFechaSolicitud(Calendar.getInstance().getTime());
        showVacacionPanel= false;
        showButtonPanel= false;
        return "vacaciones-licencia-frm";
    }
    
    private String resetAction() {
        licencia= null;
        return "vacaciones-home";
    }
    
    ///////////////////
    
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
        } else if (licencia.getTipoLicencia().getValor().equals("GRUPO_2")) {
            showVacacionPanel= false;
            showCalamidadPanel= true;
            showInstitucionalPanel= false;
            showButtonPanel= true;
        } else {
            showVacacionPanel= false;
            showCalamidadPanel= false;
            showInstitucionalPanel= true;
            showButtonPanel= true;
        }
    }
    
    public void showHourFields() {
        if(licencia.getTipoFormulario().getNemonico().equals("TIPOFORSOLIC")) {
            showHour= false;
        } else {
            showHour= true;
        }
    }
    
    public String obtenerFechaConFormato(Date f) {
        return DateUtil.obtenerFechaConFormato(f);
    }
}
