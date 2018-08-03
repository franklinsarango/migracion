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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean
@ViewScoped
public class CotitularCtrl extends BaseCtrl {

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
    
    private List<CoordenadaCota> coordenadasPorContrato;
    
    private boolean mostrarDatosCotitular = false;
    private boolean disabledButtonEditaNuevo = false;
    private Secuencia secuenciaContratoOperacion;
    
    private int longitudCoordenadas;
    private ContratoOperacion contratoOperacionAnterior;
    
    public ContratoOperacion getContratoOperacion() {
        if (contratoOperacion != null) {
            if (contratoOperacion.getTipoProcurador()== null) {
                    contratoOperacion.setTipoProcurador(new CatalogoDetalle());
                }
        }
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
                contratoOperacion.setTipoProcurador(new CatalogoDetalle());
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
                if (contratoOperacion.getTipoProcurador()== null) {
                    contratoOperacion.setTipoProcurador(new CatalogoDetalle());
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
                
            }
        }
        return contratoOperacion;
    }

    public void setContratoOperacion(ContratoOperacion contratoOperacion) {
        this.contratoOperacion = contratoOperacion;
    }

    public void buscar() {
        contratosOperacion = null;
        getContratosOperacion();
    }    
    
    public String editarRegistro() {
        ContratoOperacion contratoItem = (ContratoOperacion) getExternalContext().getRequestMap().get("reg");
        System.out.println("Codigo Contrato: " + contratoItem.getCodigoArcom());
        disabledButtonEditaNuevo = true;
        contratoOperacion = contratoItem;
        contratoOperacionAnterior = contratoOperacionServicio.findByPk(contratoOperacion.getCodigoContratoOperacion());
        personaDto = personaNaturalServicio.obtenerPersonaPorNumIdentificacion(contratoOperacion.getNumeroDocumento());
        seleccionarPersona();
        this.setMostrarDatosCotitular(true);
        //return "cotitularform?faces-redirect=true&idItem=" + contratoItem.getCodigoContratoOperacion();
        return null;
    }

    public void eliminarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            ContratoOperacion contratoItem = (ContratoOperacion) getExternalContext().getRequestMap().get("reg");
            contratoOperacionServicio.delete(contratoItem.getCodigoContratoOperacion());
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("DELETE");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(contratoItem.toString());
            auditoriaServicio.create(auditoria);
            buscar();
            habilitarComponentes("DEFAULT");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cotitular eliminado con Exito !!!", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo eliminar el registro", ex.getMessage()));
        }
    }
    
    public void nuevoRegistro() {
        habilitarComponentes("NUEVO");
    }
    
    public void cancelar() {
        habilitarComponentes("DEFAULT");
    }
   
    public String guardarContrato() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        contratoOperacion.setCodigoConcesion(concesionMineraPopup);
        
        //Por defecto el estado es INSCRITA
        CatalogoDetalle cd = new CatalogoDetalle();
        cd.setCodigoCatalogoDetalle(ConversionEstadosEnum.INSCRITA.getCodigo19());
        contratoOperacion.setEstadoContrato(cd);
        
        //Por defecto Contrato de Cesion y Transferencia
        CatalogoDetalle ctgTipoContrato = new CatalogoDetalle();
        ctgTipoContrato.setCodigoCatalogoDetalle(ConstantesEnum.TIPO_CONTRATO_CESION_DERECHOS.getCodigo());
        contratoOperacion.setTipoContrato(ctgTipoContrato);
        
        contratoOperacion.setEstadoRegistro(Boolean.TRUE);
        
        System.out.println("Procurador comun" + contratoOperacion.getTipoProcurador().getNombre());
        
        if (contratoOperacion.getTipoProcurador() != null){
            if(contratoOperacion.getProcuradorComun() == false)
            contratoOperacion.setTipoProcurador(null);
        }
        
        try {
            if (contratoOperacion.getCodigoContratoOperacion() == null) {
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
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro actualizado con éxito", null));
                    
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro ya guardado con éxito con código " + contratoOperacion.getCodigoArcom(), null));
                    
                }
            }
            buscar();
            habilitarComponentes("DEFAULT");
            return null;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: " + ex.getMessage(), ex.getMessage()));
            ex.printStackTrace();
            return null;
        }
        //return "contratos";
    }

    public void habilitarComponentes(String strTipo){
        if(strTipo == "DEFAULT"){
            this.setMostrarDatosCotitular(false);
            this.contratoOperacion = null;
            this.personaDto = null;
            disabledButtonEditaNuevo = false;
        }
        if(strTipo == "NUEVO"){
            this.setMostrarDatosCotitular(true);
            this.contratoOperacion = null;
            this.personaDto = null;
            disabledButtonEditaNuevo = true;
        }
    }
    
    public List<ContratoOperacion> getContratosOperacion() {
        contratosOperacion = null;
        String concesionId = getHttpServletRequestParam("idItem");
        
            if (concesionId != null) {
                concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(concesionId); 
                System.out.println("Codigo Concesion: " + concesionMineraPopup.getCodigoArcom());
            }
        
        if (contratosOperacion == null) {
            contratosOperacion = contratoOperacionServicio.obtenerCotitulares(concesionMineraPopup);
        }
        
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
    
    public List<CoordenadaCota> getCoordenadasPorContrato() {
        if (coordenadasPorContrato == null) {
            coordenadasPorContrato = coordenadaCotaServicio.findByCodigoContrato(contratoOperacion.getCodigoContratoOperacion());
        }
        return coordenadasPorContrato;
    }

    public void setCoordenadasPorContrato(List<CoordenadaCota> coordenadasPorContrato) {
        this.coordenadasPorContrato = coordenadasPorContrato;
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

    public void selectTipoContratoListener(AjaxBehaviorEvent event) {
        Long codigoTipoContrato = (Long) event.getComponent().getAttributes().get("value");
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
     * @return the mostrarDatosCotitular
     */
    public boolean isMostrarDatosCotitular() {
        return mostrarDatosCotitular;
    }

    /**
     * @param mostrarDatosCotitular the mostrarDatosCotitular to set
     */
    public void setMostrarDatosCotitular(boolean mostrarDatosCotitular) {
        this.mostrarDatosCotitular = mostrarDatosCotitular;
    }

    /**
     * @return the disabledButtonEditaNuevo
     */
    public boolean isDisabledButtonEditaNuevo() {
        return disabledButtonEditaNuevo;
    }

    /**
     * @param disabledButtonEditaNuevo the disabledButtonEditaNuevo to set
     */
    public void setDisabledButtonEditaNuevo(boolean disabledButtonEditaNuevo) {
        this.disabledButtonEditaNuevo = disabledButtonEditaNuevo;
    }

}
