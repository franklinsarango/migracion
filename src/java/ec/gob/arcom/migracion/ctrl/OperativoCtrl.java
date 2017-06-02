package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.DetalleOperativo;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Rol;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.DetalleOperativoServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.MaquinariaConcesionServicio;
import ec.gob.arcom.migracion.servicio.OperativoServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.RolServicio;
import ec.gob.arcom.migracion.servicio.TipoMaquinariaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
@ViewScoped
public class OperativoCtrl {
    public static final String TIPOINSTITUCION= "TIPOINFINSTPART";
    public static final String TIPODETENIDO= "TIPOINFDETOPE";
    public static final String TIPODEPOSITARIO= "TIPOINFDEPMAQ";
    
    
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
    
    private Date fechaMaxima;
    
    private List<Operativo> operativos;
    private Operativo operativo;
    
    private List<DetalleOperativo> detallesOperativo;
    private DetalleOperativo detalleOperativo;
    private MaquinariaConcesion maquinaria;
    private List<MaquinariaConcesion> maquinarias;
    
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
    
    /**
     * Creates a new instance of OperativoCtrl
     */
    
    public OperativoCtrl() {
        operativo= new Operativo();
        detallesOperativo= new ArrayList<>();
        detalleOperativo= new DetalleOperativo();
        //operativo.setDetallesOperativo(new ArrayList<DetalleOperativo>());
        maquinaria= new MaquinariaConcesion();
        maquinaria.setEstadoMaquinaria(BigInteger.ZERO);
    }
    
    @PostConstruct
    private void inicializar() {
        obtenerOperativos();
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
                regionales.add(new SelectItem(rgnl, rgnl.getNombreRegional()));
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
        System.out.println("0");
        if (cantones == null) {
            cantones = new ArrayList<>();
            System.out.println("01");
            if (operativo.getProvincia() == null) {
                System.out.println("02");
                return cantones;
            }
            System.out.println("03");
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
        getCantones();
        getParroquias();
    }
    
    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }
    
    public List<SelectItem> getTecnicos() {
        if (tecnicos == null) {
            tecnicos = new ArrayList<>();
            Rol rol= obtenerRol("TECNICO");
            List<UsuarioRol> usuariosRol= new ArrayList();
            usuariosRol= usuarioRolServicio.listByRol(rol);
            
            List<Usuario> usuarios= new ArrayList<>();
            for(UsuarioRol ur : usuariosRol) {
                usuarios.add(ur.getUsuario());
            }
            
            for (Usuario u : usuarios) {
                tecnicos.add(new SelectItem(u, u.getNombresCompletos()));
            }
        }
        return tecnicos;
    }
    
    public List<SelectItem> getLegales() {
        if (legales == null) {
            legales = new ArrayList<>();
            Rol rol= obtenerRol("ABOGADO");
            List<UsuarioRol> usuariosRol= new ArrayList();
            usuariosRol= usuarioRolServicio.listByRol(rol);
            
            List<Usuario> usuarios= new ArrayList<>();
            for(UsuarioRol ur : usuariosRol) {
                usuarios.add(ur.getUsuario());
            }
            
            for (Usuario u : usuarios) {
                legales.add(new SelectItem(u, u.getNombresCompletos()));
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
        operativo= new Operativo();
        detallesOperativo= new ArrayList<>();
        detalleOperativo= new DetalleOperativo();
        maquinarias= new ArrayList<>();
    }
    
    public void setOperativoEliminarAction(Integer row) {
        operativo= operativos.get(row);
    }
    
    public void deleteOperativoAction() {
        operativoServicio.delete(operativo.getCodigoOperativo());
        obtenerOperativos();
        mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo eliminado correctamente");
    }
    
    public void editOperativoAction(Integer row) {
        edit= true;
        operativo= operativos.get(row);
        detallesOperativo= detalleOperativoServicio.listarPorOperativo(operativo);
        maquinarias= maquinariaConcesionServicio.obtenerMaquinariasPorOperativo(operativo);
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
        }
    }
    
    public String obtenerNombreCatalogoDetalle(Long pk) {
        return catalogoDetalleServicio.findByPk(pk).getNombre();
    }
    
    public void saveOperativoAction() {
        if(edit) {
            if(actualizar()) {
                finalizarGuardado();
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo actualizado correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al actualizar");
            }
        } else {
            if(guardar()) {
                finalizarGuardado();
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Operativo guardado correctamente");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al guardar");
            }
        }
    }
    
    private boolean guardar() {
        if(guardarOperativo() && guardarMaquinaria()) {
            return true;
        }
        return false;
    }
    
    private boolean guardarOperativo() {
        operativo.setEstadoRegistro(Boolean.TRUE);
        operativo.setFechaCreacion(Calendar.getInstance().getTime());
        operativoServicio.create(operativo);
        for(DetalleOperativo detope : detallesOperativo) {
            detope.setOperativo(operativo);
            detope.setEstadoRegistro(Boolean.TRUE);
            detope.setFechaCreacion(Calendar.getInstance().getTime());
            detalleOperativoServicio.create(detope);
        }
        return true;
    }
    
    private boolean guardarMaquinaria() {
        for(MaquinariaConcesion mc : maquinarias) {
            mc.setEstadoRegistro(Boolean.TRUE);
            mc.setFechaCreacion(Calendar.getInstance().getTime());
            mc.setOperativo(operativo);
            maquinariaConcesionServicio.create(mc);
        }
        return true;
    }
    
    private boolean actualizar() {
        if(actualizarOperativo() && actualizarMaquinaria()) {
            return true;
        }
        return false;
    }
    
    private boolean actualizarOperativo() {
        operativo.setEstadoRegistro(Boolean.TRUE);
        operativo.setFechaModificacion(Calendar.getInstance().getTime());
        operativoServicio.update(operativo);
        for(DetalleOperativo detope : detallesOperativo) {
            detope.setOperativo(operativo);
            detope.setEstadoRegistro(Boolean.TRUE);
            if(detope.getFechaCreacion()!=null) {
                detope.setFechaModificacion(Calendar.getInstance().getTime());
                detalleOperativoServicio.update(detope);
            } else {
                detope.setFechaCreacion(Calendar.getInstance().getTime());
                detalleOperativoServicio.create(detope);
            }
        }
        return true;
    }
    
    private boolean actualizarMaquinaria() {
        for(MaquinariaConcesion mc : maquinarias) {
            mc.setEstadoRegistro(Boolean.TRUE);
            mc.setOperativo(operativo);
            if(mc.getFechaCreacion()!=null) {
                mc.setFechaModificacion(Calendar.getInstance().getTime());
                maquinariaConcesionServicio.update(mc);
            } else {
                mc.setFechaCreacion(Calendar.getInstance().getTime());
                maquinariaConcesionServicio.create(mc);
            }
        }
        return true;
    }
    
    
    private void finalizarGuardado() {
        RequestContext.getCurrentInstance().execute("PF('operativofrmwg').hide();");
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
}
