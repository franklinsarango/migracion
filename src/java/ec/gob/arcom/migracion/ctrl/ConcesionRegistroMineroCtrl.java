/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.ConcesionMineraDaoLocal;
import ec.gob.arcom.migracion.dao.LocalidadDao;
import ec.gob.arcom.migracion.dao.PersonaNaturalDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@SessionScoped
public class ConcesionRegistroMineroCtrl extends BaseCtrl {
    @EJB
    private ConcesionMineraDao concesionMineraDao;
    @EJB
    private ConcesionMineraDaoLocal cmDao;
    @EJB
    private LocalidadDao lDao;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private PersonaNaturalDao pNaturalDao;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private AreaMineraServicio areaMineraServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    private ConcesionMinera concesionMinera;
    private ConcesionMinera concesionMineraAnterior;
    private AreaMinera areaMinera;
    private AreaMinera areaMineraAnterior;
    private List<ConcesionMinera> concesiones;
    private List<ConcesionMineraDto> listaRegistros;
    
    private String codigoFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;
    
    @PostConstruct
    private void cargarListas() {
        cargarConcesiones();
    }
    
    public ConcesionRegistroMineroCtrl() {
        concesiones= new ArrayList<>();
    }
    
    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public void buscar() {
        listaRegistros = null;
        getListaRegistros();
    }
    
    public List<ConcesionMineraDto> getListaRegistros() {
        if (listaRegistros == null) {
            listaRegistros = presentarListaRegistros();
        }
        return listaRegistros;
    }
    
    public List<ConcesionMineraDto> presentarListaRegistros() {
        if ((codigoFiltro == null || codigoFiltro.trim().isEmpty())
                && (cedulaTitularFiltro == null || cedulaTitularFiltro.trim().isEmpty())
                && (nombreAreaFiltro == null || nombreAreaFiltro.trim().isEmpty())) {
            List<ConcesionMineraDto> listaFinal = new ArrayList<>();
            return listaFinal;
        }

        return concesionMineraServicio.obtenerRegistrosPorFiltros(codigoFiltro, cedulaTitularFiltro, nombreAreaFiltro);
    }
    
    public void editAction(String codigoArcomArea) {
        System.out.println("codigoArcomArea:" + codigoArcomArea);
        this.concesionMinera = cmDao.findByCodigo(codigoArcomArea).get(0);
        this.concesionMineraAnterior = cmDao.findByCodigo(codigoArcomArea).get(0);
        this.areaMinera = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
        this.areaMineraAnterior = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
        RequestContext.getCurrentInstance().execute("PF('dlgConcesionMinera').show()");
    }
    
    public ConcesionMinera getConcesionMinera() {
        if (concesionMinera != null) {
            if (concesionMinera.getDocumentoConcesionarioPrincipal() != null) {
                PersonaDto persona = pNaturalDao.obtenerPersonaPorNumIdentificacion(concesionMinera.getDocumentoConcesionarioPrincipal());
                String nombreTitutlar = "";
                if (persona.getNombres() != null) {
                    nombreTitutlar = persona.getNombres();
                }
                if (persona.getApellidos() != null && !persona.getApellidos().isEmpty()) {
                    nombreTitutlar += " " + persona.getApellidos();
                }
                concesionMinera.setNombreTitular(nombreTitutlar);
            }
            if (concesionMinera.getCodigoProvincia() != null) {
            Localidad provincia = localidadServicio.findByPk(concesionMinera.getCodigoProvincia().longValue());
            if (provincia != null) {
                concesionMinera.setProvinciaString(provincia.getNombre());
            }
        }
        if (concesionMinera.getCodigoCanton() != null) {
            Localidad canton = localidadServicio.findByPk(concesionMinera.getCodigoCanton().longValue());
            if (canton != null) {
                concesionMinera.setCantonString(canton.getNombre());
            }
        }
        if (concesionMinera.getCodigoParroquia() != null) {
            Localidad parroquia = localidadServicio.findByPk(concesionMinera.getCodigoParroquia().longValue());
            if (parroquia != null) {
                concesionMinera.setParroquiaString(parroquia.getNombre());
            }
        }
        }
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public List<ConcesionMinera> getConcesiones() {
        //cargarConcesiones();
        return concesiones;
    }

    public void setConcesiones(List<ConcesionMinera> concesiones) {
        this.concesiones = concesiones;
    }
    
    public void actualizarConcesion() {
        try {
            System.out.println("Paso1");
            //SE CONTROLA QUE LA CONCESION NO SE IGUAL A NULL
            if (concesionMinera == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error: No existe ninguna Concesion Seleccionada", null));
                return;
            }

            System.out.println("Paso2");
            //SE CONTROLA QUE ESTE SELECCIONADA LA OPCION HABILITAR SOLICITUD
            if (!(concesionMinera.getMigrada() != null && concesionMinera.getMigrada() == true)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error: Debe seleccionar la Opción Habilitar Solicitud", null));
                return;
            }

            System.out.println("Paso3");

            Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());

            System.out.println("Paso4");
            //SE ACTUALIZA LA CONCESION MINERA
            System.out.println("concesionMinera.getMigrada()" + concesionMinera.getMigrada().toString());
            System.out.println("concesionMineraAnterior.getMigrada()" + concesionMineraAnterior.getMigrada().toString());
            concesionMinera.setFechaModificacion(getCurrentTimeStamp());
            concesionMinera.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
            concesionMineraServicio.actualizarConcecionMinera(concesionMinera);
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("UPDATE");
            auditoria.setDetalleAnterior(concesionMineraAnterior.toString());
            auditoria.setDetalleCambios(concesionMinera.toString());
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setNombreTabla(ConstantesEnum.TABLA_CONCESION_MINERA.getDescripcion());
            auditoria.setCodigoTabla(concesionMinera.getCodigoConcesion().toString());
            auditoriaServicio.create(auditoria);

            RequestContext.getCurrentInstance().execute("PF('dlgConcesionMinera').hide()");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Datos Guardados Correctamente", null));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al guardar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
    }
    
    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }
    
    private HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }
    
    private void cargarConcesiones() {
        this.concesiones= cmDao.list();
    }
    
    public String cargarLocalidad(Long pk) {
        return lDao.findByPk(pk).getNombre();
    }

    /**
     * @return the areaMinera
     */
    public AreaMinera getAreaMinera() {
        return areaMinera;
    }

    /**
     * @param areaMinera the areaMinera to set
     */
    public void setAreaMinera(AreaMinera areaMinera) {
        this.areaMinera = areaMinera;
    }
    
    /**
     * @return the cedulaTitularFiltro
     */
    public String getCedulaTitularFiltro() {
        return cedulaTitularFiltro;
    }

    /**
     * @param cedulaTitularFiltro the cedulaTitularFiltro to set
     */
    public void setCedulaTitularFiltro(String cedulaTitularFiltro) {
        this.cedulaTitularFiltro = cedulaTitularFiltro;
    }

    /**
     * @return the nombreAreaFiltro
     */
    public String getNombreAreaFiltro() {
        return nombreAreaFiltro;
    }

    /**
     * @param nombreAreaFiltro the nombreAreaFiltro to set
     */
    public void setNombreAreaFiltro(String nombreAreaFiltro) {
        this.nombreAreaFiltro = nombreAreaFiltro;
    }
}
