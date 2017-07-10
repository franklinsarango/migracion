/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dao.SadminDataDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.SadminData;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import java.math.BigDecimal;
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
public class ActualizacionActosAdministrativosCtrl extends BaseCtrl {

    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private SadminDataDao sadminDataDao;
    @EJB
    private RegimenServicio regimenServicio;
    @EJB
    private LicenciaComercializacionServicio licenciaComercializacionServicio;
    @EJB
    private PlantaBeneficioServicio plantaBeneficioServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private ConcesionMineraDao concesionMineraDao;
    @EJB
    private PlantaBeneficioDao plantaBeneficioDao;
    @EJB
    private AreaMineraServicio areaMineraServicio;
    @EJB
    private CoordenadaAreaServicio coordenadaAreaServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    
//    @ManagedProperty(value = "#{loginCtrl}")
//    private LoginCtrl login;
    
    private String codigo;
    private String nombreDerechoMinero;
    private Long codigoRegional;
    private Long codigoProvincia;
    private Long codigoFase;
    private Long codigoEstado;
    private String tipoSolicitudNemonico;
    private String beneficiarioPrincipal;
    private String tipoPersona;
    private Date fecha;
    private String numDocumento;
    private String urlCotitulares;
    private String urlReporte;
    private String userName;

    private boolean mostrarLista = false;
    private List<DerechoMineroDto> listaRegistros;

    private List<SelectItem> provincias;

    private SadminData sadminData;
    private ConcesionMinera concesionMinera;
    private ConcesionMinera concesionMineraAnterior;
    private LicenciaComercializacion licenciaComercializacion;
    private PlantaBeneficio plantaBeneficio;
    private PlantaBeneficio plantaBeneficioAnterior;
    private List<CoordenadaArea> coordenadasPorArea;

    @PostConstruct
    public void init() {
        try {           
            String usuario = getHttpServletRequestParam("usuario");
            System.out.println("usuario" + usuario);
            if(usuario != null && usuario.equals("") == false){
                userName = usuario;
            }
//            else{
//                login.getUserName()
//            }                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public void buscar() {
        mostrarLista = true;
        listaRegistros = null;
        getListaRegistros();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    public void setNombreDerechoMinero(String nombreDerechoMinero) {
        this.nombreDerechoMinero = nombreDerechoMinero;
    }

    public Long getCodigoRegional() {
        return codigoRegional;
    }

    public void setCodigoRegional(Long codigoRegional) {
        this.codigoRegional = codigoRegional;
    }

    public Long getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Long codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Long getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(Long codigoFase) {
        this.codigoFase = codigoFase;
    }

    public Long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Long codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getTipoSolicitudNemonico() {
        return tipoSolicitudNemonico;
    }

    public void setTipoSolicitudNemonico(String tipoSolicitudNemonico) {
        this.tipoSolicitudNemonico = tipoSolicitudNemonico;
    }

    public String getBeneficiarioPrincipal() {
        return beneficiarioPrincipal;
    }

    public void setBeneficiarioPrincipal(String beneficiarioPrincipal) {
        this.beneficiarioPrincipal = beneficiarioPrincipal;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isMostrarLista() {
        return mostrarLista;
    }

    public void setMostrarLista(boolean mostrarLista) {
        this.mostrarLista = mostrarLista;
    }

    public List<DerechoMineroDto> getListaRegistros() {
        if (listaRegistros == null) {
            System.out.println(codigo + " " + nombreDerechoMinero + " " + codigoRegional
                    + " " + codigoProvincia + " " + codigoFase + " " + codigoEstado + " " + tipoSolicitudNemonico
                    + " " + beneficiarioPrincipal + " " + tipoPersona + " " + fecha + " " + numDocumento);
            listaRegistros = concesionMineraServicio.obtenerDerechosMinerosNacional(codigo, nombreDerechoMinero, codigoRegional,
                    codigoProvincia, codigoFase, codigoEstado, tipoSolicitudNemonico, beneficiarioPrincipal, tipoPersona, fecha, numDocumento);
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<DerechoMineroDto> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia.getCodigoLocalidad(), provincia.getNombre().toUpperCase()));
            }
        }
        return provincias;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public String verDerechoMinero() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        if (derechoMineroDtoItem.getTipoDerechoMinero().equals("C")) {
            return "concesionmineraformro?faces-redirect=true&idItem=" + derechoMineroDtoItem.getId();
        } else if (derechoMineroDtoItem.getTipoDerechoMinero().equals("S")) {
            return "areamineraformro?faces-redirect=true&idItem=" + derechoMineroDtoItem.getId();
        } else if (derechoMineroDtoItem.getTipoDerechoMinero().equals("L")) {
            return "licenciacomercializacionformro?faces-redirect=true&idItem=" + derechoMineroDtoItem.getId();
        } else if (derechoMineroDtoItem.getTipoDerechoMinero().equals("P")) {
            return "plantabeneficioformro?faces-redirect=true&idItem=" + derechoMineroDtoItem.getId();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se encuentra el registro seleccionado", null));
            return null;
        }
    }

    public SadminData getSadminData() {
        return sadminData;
    }

    public void setSadminData(SadminData sadminData) {
        this.sadminData = sadminData;
    }

    public ConcesionMinera getConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public LicenciaComercializacion getLicenciaComercializacion() {
        return licenciaComercializacion;
    }

    public void setLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) {
        this.licenciaComercializacion = licenciaComercializacion;
    }

    public PlantaBeneficio getPlantaBeneficio() {
        return plantaBeneficio;
    }

    public void setPlantaBeneficio(PlantaBeneficio plantaBeneficio) {
        this.plantaBeneficio = plantaBeneficio;
    }
    
    public String verCotitulares() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        if(derechoMineroDtoItem.getTipoDerechoMinero().equals("C")){
            urlCotitulares = ConstantesEnum.URL_APP_PROD.getDescripcion() + "/migracion/web/cotitularview.xhtml?idItem=" + derechoMineroDtoItem.getCodigo();
            System.out.println("urlCotitulares: " + urlCotitulares);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Los Cotitulares solo aplica para Concesiones !!!", null));
        }
        return null;
    }
    
    public String verListainformes() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/informes/lista_informes.rptdesign&codigoArcom=" + derechoMineroDtoItem.getCodigo();
        System.out.println("urlReporte: " + urlReporte);

        return null;
    }

    public void verConcesionMinera() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        concesionMinera = concesionMineraDao.findByPk(derechoMineroDtoItem.getId());
        concesionMineraAnterior = concesionMineraDao.findByPk(derechoMineroDtoItem.getId());
        
        if (concesionMinera.getCodigoZona() != null) {
            System.out.println("zona: " + concesionMinera.getCodigoZona().getCodigoCatalogoDetalle() 
                    + " " + concesionMinera.getCodigoZona().getNombre());
        }
        System.out.println("superficie: " + concesionMinera.getNumeroHectareasConcesion());
        System.out.println("volumen: " + concesionMinera.getVolumenTotalExplotacion());
        if (concesionMinera.getDocumentoConcesionarioPrincipal() != null) {
            if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 10) {
                PersonaNatural personaNatural = personaNaturalServicio
                        .findByNumeroDocumento(concesionMinera.getDocumentoConcesionarioPrincipal());
                if (personaNatural != null) {
                    concesionMinera.setPersonaNaturalTransient(personaNatural);
                    concesionMinera.getPersonaNaturalTransient();
                }
            } else if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
                PersonaJuridica personaJuridica = personaJuridicaServicio
                        .findByRuc(concesionMinera.getDocumentoConcesionarioPrincipal());
                if (personaJuridica != null) {
                    concesionMinera.setPersonaJuridicaTransient(personaJuridica);
                    concesionMinera.getPersonaJuridicaTransient();
                }
            }
        }
        if (concesionMinera.getDocumentoConcesionarioPrincipal() != null
                && concesionMinera.getDocumentoConcesionarioPrincipal().length() == 10) {
            concesionMinera.setTipoPersona("N");
        } else if (concesionMinera.getDocumentoConcesionarioPrincipal() != null
                && concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
            concesionMinera.setTipoPersona("J");
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
        if (concesionMinera.getCodigoZona() != null) {
            System.out.println("zona222222: " + concesionMinera.getCodigoZona().getCodigoCatalogoDetalle() 
                    + " " + concesionMinera.getCodigoZona().getNombre());
        }
        RequestContext.getCurrentInstance().execute("PF('dlgConcesionMinera').show()");
    }

    public void verPlantaBeneficio() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        plantaBeneficio = plantaBeneficioDao.findByPk(derechoMineroDtoItem.getId());
        plantaBeneficioAnterior = plantaBeneficioDao.findByPk(derechoMineroDtoItem.getId());
        //SE FIJA LOS DATOS DE LA PERSONA
        if (plantaBeneficio != null) {
            PersonaNatural personaNatural = personaNaturalServicio.findByNumeroDocumento(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
            if (personaNatural != null) {
                plantaBeneficio.setPersonaNaturalTransient(personaNatural);
                plantaBeneficio.getPersonaNaturalTransient();
            } else {
                PersonaJuridica personaJuridica = personaJuridicaServicio.findByRuc(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
                if (personaJuridica != null) {
                    plantaBeneficio.setPersonaJuridicaTransient(personaJuridica);
                    plantaBeneficio.getPersonaJuridicaTransient();
                }
            }
        }
        
        if (plantaBeneficio.getCodigoProvincia() != null) {
            Localidad provincia = localidadServicio.findByPk(plantaBeneficio.getCodigoProvincia().longValue());
            if (provincia != null && provincia.getNombre() != null) {
                plantaBeneficio.setProvinciaString(provincia.getNombre());
            }
        }
        if (plantaBeneficio.getCodigoCanton() != null) {
            Localidad canton = localidadServicio.findByPk(plantaBeneficio.getCodigoCanton().longValue());
            if (canton != null && canton.getNombre() != null) {
                plantaBeneficio.setCantonString(canton.getNombre());
            }
        }
        if (plantaBeneficio.getCodigoParroquida() != null) {
            Localidad parroquia = localidadServicio.findByPk(plantaBeneficio.getCodigoParroquida().longValue());
            if (parroquia != null && parroquia.getNombre() != null) {
                plantaBeneficio.setParroquiaString(parroquia.getNombre());
            }
        }
        RequestContext.getCurrentInstance().execute("PF('dlgPlantaBeneficio').show()");
    }

    
    public String actualizarConcesionMinera() {
        Usuario us = usuarioDao.obtenerPorLogin(userName);
        if(us == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al guardar, NO EXISTE USUARIO", null));
            return null;
        }
        concesionMinera.setFechaModificacion(new Date());
        concesionMinera.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
        try {
                concesionMineraServicio.update(concesionMinera);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(concesionMineraAnterior.toString());
                auditoria.setDetalleCambios(concesionMinera.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "El registro ha sido actualizado", null));
                RequestContext.getCurrentInstance().execute("PF('dlgConcesionMinera').hide()");
                return null;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al guardar el registro", ex.getMessage()));
            ex.printStackTrace();
            return null;
        }
    }
    
    public String actualizarPlantaBeneficio() {
        Usuario us = usuarioDao.obtenerPorLogin(userName);
        if (us == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al guardar, NO EXISTE USUARIO", null));
            return null;
        }
        try {
                System.out.println("entra actualizar pb");
                plantaBeneficio.setFechaModificacion(new Date());
                plantaBeneficio.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                plantaBeneficioServicio.actualizarPlantaBeneficio(plantaBeneficio);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(plantaBeneficioAnterior.toString());
                auditoria.setDetalleCambios(plantaBeneficio.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro actualizado con éxito", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPlantaBeneficio').hide()");
                return null;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
            ex.printStackTrace();
            return null;
        }
//        return "plantasbeneficio";
    }
    
    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return the urlCotitulares
     */
    public String getUrlCotitulares() {
        return urlCotitulares;
    }

    /**
     * @param urlCotitulares the urlCotitulares to set
     */
    public void setUrlCotitulares(String urlCotitulares) {
        this.urlCotitulares = urlCotitulares;
    }

    /**
     * @return the coordenadasPorArea
     */
    public List<CoordenadaArea> getCoordenadasPorArea() {
        if (concesionMinera != null) {
            AreaMinera areaMinera = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
            if (areaMinera != null) {
                System.out.println("codigoAreaMinera: " + areaMinera.getCodigoAreaMinera());
                coordenadasPorArea = coordenadaAreaServicio.findByCodigoArea(areaMinera.getCodigoAreaMinera());
            }
        }
        return coordenadasPorArea;
    }
    /**
     * @param coordenadasPorArea the coordenadasPorArea to set
     */
    public void setCoordenadasPorArea(List<CoordenadaArea> coordenadasPorArea) {
        this.coordenadasPorArea = coordenadasPorArea;
    }

    /**
     * @return the urlReporte
     */
    public String getUrlReporte() {
        return urlReporte;
    }

    /**
     * @param urlReporte the urlReporte to set
     */
    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

}
