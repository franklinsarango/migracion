/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.CatalogoDetalleDao;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.SolicitudDetalleDao;
import ec.gob.arcom.migracion.dao.TipoMineriaDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.Fase;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Regimen;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.SolicitudDetalle;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.servicio.FaseServicio;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.MaquinariaConcesionServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.SolicitudDetalleServicio;
import ec.gob.arcom.migracion.util.CedulaValidator;
import java.math.BigDecimal;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean
@ViewScoped
public class ConcesionMineraCtrl extends BaseCtrl {

    @EJB
    private ConcesionMineraDao concesionMineraDao;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private AreaMineraServicio areaMineraServicio;
    //@EJB
    //private SolicitudServicio solicitudServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private LocalidadRegionalServicio localidadRegionalServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private TipoMineriaDao tipoMineriaDao;
    @EJB
    private CatalogoDetalleDao catalogoDetalleDao;
    @EJB
    private SolicitudDetalleDao solicitudDetalleDao;
    @EJB
    private SolicitudDetalleServicio solicitudDetalleServicio;
    @EJB
    private MaquinariaConcesionServicio maquinariaConcesionServicio;
    @EJB
    private RegimenServicio regimenServicio;
    @EJB
    private FaseServicio faseServicio;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private CoordenadaAreaServicio coordenadaAreaServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private List<ConcesionMineraDto> listaRegistros;

    private ConcesionMinera concesionMinera;
    private AreaMinera areaMinera;
    private CoordenadaArea coordenadaArea;
    //private Solicitud solicitud;
    private SolicitudDetalle solicitudDetalle;
    private PersonaJuridica personaJuridica;
    private PersonaNatural personaNatural;

    private String codigoFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;

    private List<SelectItem> tipoMaterial;
    private List<SelectItem> tipoMaterialDetalle;

    private boolean existeCodigoArcom = true;
    private boolean cedulaValida;
    private boolean cedulaRepLegalValida;
    private boolean perNatural;
    private String tipoPersona = "N";

    //private List<SolicitudDetalle> coordenadasSolicitud;
    private String coordenadaX;
    private String coordenadaY;
    private boolean mostrarCoordenadas = false;

    private Long codigoMaquinaria;
    private List<MaquinariaConcesion> maquinariasPorConcesion;
    private boolean mostrarMaquinaria = false;
    private int longitudCoordenadas;

    private List<SelectItem> fasesCodigoCatalogo;

    private boolean otorgado;
    private boolean inscrito;
    private boolean tipSolConcMin;

    private ConcesionMinera concesionMineraAnterior;
    //private Solicitud solicitudAnterior;
    private AreaMinera areaMineraAnterior;

    private boolean tipoSolMineriaArtesanal;
    private int cantidadMaquinaria = 1;
    //private Resolucion resolucion;
    
    private List<CoordenadaArea> coordenadasPorArea;

    public ConcesionMinera getConcesionMinera() {
        if (concesionMinera == null) {
            String concesionMineraId = getHttpServletRequestParam("idItem");
            Long idconcesionMinera = null;
            if (concesionMineraId != null) {
                idconcesionMinera = Long.parseLong(concesionMineraId);
            }
            if (idconcesionMinera == null) {
                System.out.println("entra if");
                concesionMinera = new ConcesionMinera();
                concesionMinera.setEstadoConcesion(new CatalogoDetalle());
                concesionMinera.setCodigoFase(new Fase());
                //concesionMinera.setCodigoRegional(new Regional());
                concesionMinera.setCodigoTipoMineria(new TipoMineria());
                concesionMinera.setCodigoRegimen(new Regimen());
                concesionMinera.setCodigoModalidadTrabajo(new CatalogoDetalle());
                concesionMinera.setCodigoFormaExplotacion(new CatalogoDetalle());
                concesionMinera.setCodigoCasilleroLocalidad(new Localidad());
                concesionMinera.setCodigoTipoMaterial(new Catalogo());
                concesionMinera.setCodigoMaterialInteres(new CatalogoDetalle());
                concesionMinera.setCodigoZona(new CatalogoDetalle());
                areaMinera = new AreaMinera();
                areaMinera.setCodigoLocalidad(new Localidad());
                //solicitud = new Solicitud();
            } else {
                System.out.println("entra else getConcesion");
                System.out.println("idconcesionMinera: " + idconcesionMinera);
                concesionMinera = concesionMineraDao.findByPk(idconcesionMinera);
                concesionMineraAnterior = concesionMineraDao.findByPk(idconcesionMinera);
                if (concesionMinera.getEstadoConcesion() == null) {
                    concesionMinera.setEstadoConcesion(new CatalogoDetalle());
                }
                if (concesionMinera.getCodigoRegimen() == null) {
                    concesionMinera.setCodigoRegimen(new Regimen());
                }
                if (concesionMinera.getCodigoModalidadTrabajo() == null) {
                    concesionMinera.setCodigoModalidadTrabajo(new CatalogoDetalle());
                } else if (concesionMinera.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() == null) {
                    concesionMinera.getCodigoModalidadTrabajo().setCodigoCatalogoDetalle(1000L);
                }
                if (concesionMinera.getCodigoFormaExplotacion() == null) {
                    concesionMinera.setCodigoFormaExplotacion(new CatalogoDetalle());
                } else if (concesionMinera.getCodigoFormaExplotacion().getCodigoCatalogoDetalle() == null) {
                    concesionMinera.getCodigoFormaExplotacion().setCodigoCatalogoDetalle(1000L);
                }
                if (concesionMinera.getCodigoFase() == null) {
                    concesionMinera.setCodigoFase(new Fase());
                }
                if (concesionMinera.getCodigoCasilleroLocalidad() == null) {
                    concesionMinera.setCodigoCasilleroLocalidad(new Localidad());
                }
                if (concesionMinera.getCodigoProvincia() != null && concesionMinera.getCodigoCasilleroLocalidad().getCodigoLocalidad() == null) {
                    Localidad provincia = new Localidad();
                    provincia.setCodigoLocalidad(concesionMinera.getCodigoProvincia().longValue());
                    concesionMinera.setCodigoCasilleroLocalidad(provincia);
                }
                //solicitud = solicitudServicio.obtenerPorCodigoArcom(concesionMinera.getCodigoArcom());
                //solicitudAnterior = solicitudServicio.obtenerPorCodigoArcom(concesionMinera.getCodigoArcom());
                if (concesionMinera.getDocumentoConcesionarioPrincipal() != null
                        && concesionMinera.getDocumentoConcesionarioPrincipal().length() == 10) {
                    tipoPersona = "N";
                    PersonaNatural personaNatural = personaNaturalServicio
                            .findByNumeroDocumento(concesionMinera.getDocumentoConcesionarioPrincipal());
                    concesionMinera.setPersonaNaturalTransient(personaNatural);
                    concesionMinera.getPersonaNaturalTransient();
                } else if (concesionMinera.getDocumentoConcesionarioPrincipal() != null
                        && concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
                    tipoPersona = "J";
                    PersonaJuridica personaJuridica = personaJuridicaServicio
                            .findByRuc(concesionMinera.getDocumentoConcesionarioPrincipal());
                    concesionMinera.setPersonaJuridicaTransient(personaJuridica);
                    concesionMinera.getPersonaJuridicaTransient();
                }
                if (concesionMinera.getCodigoTipoMaterial() == null) {
                    concesionMinera.setCodigoTipoMaterial(new Catalogo());
                }
                if (concesionMinera.getCodigoMaterialInteres() == null) {
                    concesionMinera.setCodigoMaterialInteres(new CatalogoDetalle());
                }
                if (concesionMinera.getCodigoZona() == null) {
                    concesionMinera.setCodigoZona(new CatalogoDetalle());
                }
                /*if (solicitud.getTipoPersona() != null) {
                 if (solicitud.getTipoPersona().equals("PNA")) {
                 tipoPersona = "N";
                 } else if (solicitud.getTipoPersona().equals("PJU")) {
                 tipoPersona = "J";
                 }
                 }*/
                System.out.println("concesionMinera.getCodigoConcesion(): " + concesionMinera.getCodigoConcesion());
                areaMinera = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
                areaMineraAnterior = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
                existeCodigoArcom = false;
                mostrarCoordenadas = true;
                mostrarMaquinaria = true;
                validarRegimenFase();
            }
        }
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public AreaMinera getAreaMinera() {
        return areaMinera;
    }

    public void setAreaMinera(AreaMinera areaMinera) {
        this.areaMinera = areaMinera;
    }

    public CoordenadaArea getCoordenadaArea() {
        return coordenadaArea;
    }

    public void setCoordenadaArea(CoordenadaArea coordenadaArea) {
        this.coordenadaArea = coordenadaArea;
    }

    /*public Solicitud getSolicitud() {
     return solicitud;
     }

     public void setSolicitud(Solicitud solicitud) {
     this.solicitud = solicitud;
     }*/
    public SolicitudDetalle getSolicitudDetalle() {
        return solicitudDetalle;
    }

    public void setSolicitudDetalle(SolicitudDetalle solicitudDetalle) {
        this.solicitudDetalle = solicitudDetalle;
    }

    public PersonaJuridica getPersonaJuridica() {
        if (personaJuridica == null) {
            personaJuridica = new PersonaJuridica();
        }
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public PersonaNatural getPersonaNatural() {
        if (personaNatural == null) {
            personaNatural = new PersonaNatural();
        }
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getCedulaTitularFiltro() {
        return cedulaTitularFiltro;
    }

    public void setCedulaTitularFiltro(String cedulaTitularFiltro) {
        this.cedulaTitularFiltro = cedulaTitularFiltro;
    }

    public String getNombreAreaFiltro() {
        return nombreAreaFiltro;
    }

    public void setNombreAreaFiltro(String nombreAreaFiltro) {
        this.nombreAreaFiltro = nombreAreaFiltro;
    }

    public List<ConcesionMineraDto> getListaRegistros() {
        if (listaRegistros == null) {
            listaRegistros = presentarListaRegistros();
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<ConcesionMineraDto> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public List<ConcesionMineraDto> presentarListaRegistros() {
        return concesionMineraServicio.obtenerRegistrosPorUsuario(login.getUserName(), codigoFiltro, cedulaTitularFiltro, nombreAreaFiltro);
    }

    public void buscar() {
        listaRegistros = null;
        getListaRegistros();
    }

    public String editarRegistro() {
        mostrarCoordenadas = true;
        existeCodigoArcom = true;
        ConcesionMineraDto concesionMineraDtoItem = (ConcesionMineraDto) getExternalContext().getRequestMap().get("reg");
        if (concesionMineraDtoItem.getTipoTabla().equals("C")) {
            return "concesionmineraform?faces-redirect=true&idItem=" + concesionMineraDtoItem.getCodigoConcesion();
        } else if (concesionMineraDtoItem.getTipoTabla().equals("S")) {
            return "areamineraform?faces-redirect=true&idItem=" + concesionMineraDtoItem.getCodigoConcesion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se encuentra el registro seleccionado", null));
            return null;
        }
    }

    public String verRegistro() {
        mostrarCoordenadas = true;
        existeCodigoArcom = true;
        ConcesionMineraDto concesionMineraDtoItem = (ConcesionMineraDto) getExternalContext().getRequestMap().get("reg");
        if (concesionMineraDtoItem.getTipoTabla().equals("C")) {
            return "concesionmineraformro?faces-redirect=true&idItem=" + concesionMineraDtoItem.getCodigoConcesion();
        } else if (concesionMineraDtoItem.getTipoTabla().equals("S")) {
            return "areamineraformro?faces-redirect=true&idItem=" + concesionMineraDtoItem.getCodigoConcesion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se encuentra el registro seleccionado", null));
            return null;
        }
    }

    public String guardarRegistro() {
        System.out.println("codigoRegimen: " + concesionMinera.getCodigoRegimen().getCodigoRegimen());
        System.out.println("codigoFase: " + concesionMinera.getCodigoFase().getCodigoFase());
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        LocalidadRegional localidadRegional = localidadRegionalServicio
                .obtenerPorCodigoLocalidad(Long.valueOf(concesionMinera.getCodigoProvincia().toString()));
        Regional regional = regionalServicio.findByPk(localidadRegional.getLocalidadRegionalPK().getCodigoRegional());
        //System.out.println("solicitud.getTipoSolicitud(): " + solicitud.getTipoSolicitud());
        //TipoMineria tipoMineria = tipoMineriaDao.findByNemonico(solicitud.getTipoSolicitud());
        //CatalogoDetalle codigoZona = catalogoDetalleDao.obtenerPorValor(String.valueOf(solicitud.getZona()));

        if (concesionMinera.getCodigoRegimen() != null && concesionMinera.getCodigoFase() != null) {
            //if (!solicitud.getTipoSolicitud().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getNemonico())) {
            if (concesionMinera.getCodigoTipoMineria() != null 
                    && concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria() != null)
            if (!concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria()
                    .equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())) {
                concesionMinera.setCodigoRegimen(null);
                concesionMinera.setCodigoFase(null);
            }
        }

        if (concesionMinera.getCodigoRegimen() != null && concesionMinera.getCodigoRegimen().getCodigoRegimen() != null) {
            if (concesionMinera.getCodigoRegimen().getCodigoRegimen().equals(1000L)) {
                concesionMinera.setCodigoRegimen(null);
            }
        } else {
            concesionMinera.setCodigoRegimen(null);
        }

        if (concesionMinera.getCodigoFase() != null && concesionMinera.getCodigoFase().getCodigoFase() != null) {
            if (concesionMinera.getCodigoFase().getCodigoFase().equals(1000L)) {
                concesionMinera.setCodigoFase(null);
            }
        } else {
            concesionMinera.setCodigoFase(null);
        }

        if (concesionMinera.getCodigoModalidadTrabajo() != null && concesionMinera.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() != null) {
            if (concesionMinera.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle().equals(1000L)) {
                concesionMinera.setCodigoModalidadTrabajo(null);
            }
        } else {
            concesionMinera.setCodigoModalidadTrabajo(null);
        }

        if (concesionMinera.getCodigoFormaExplotacion() != null && concesionMinera.getCodigoFormaExplotacion().getCodigoCatalogoDetalle() != null) {
            if (concesionMinera.getCodigoFormaExplotacion().getCodigoCatalogoDetalle().equals(1000L)) {
                concesionMinera.setCodigoFormaExplotacion(null);
            }
        } else {
            concesionMinera.setCodigoFormaExplotacion(null);
        }

        /*if (concesionMinera.getFechaInforme() == null) {
         try {
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         String dateInString = "01/01/1800";
         Date date = formatter.parse(dateInString);
         concesionMinera.setFechaInforme(date);
         } catch (Exception e) {
         e.printStackTrace();
         }
         }

         if (concesionMinera.getFechaInicioConcesion() != null) {
         if (concesionMinera.getFechaInforme().after(concesionMinera.getFechaOtorga())) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
         "Fecha de informe debe ser menor o igual a la fecha de otorgamiento", null));
         return null;
         }
         }*/
        /*if (concesionMinera.getFechaOtorga() != null) {
         if (concesionMinera.getFechaOtorga().after(concesionMinera.getFechaInscribe())) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
         "Fecha de otorgamiento debe ser menor o igual a la fecha de inscripción", null));
         return null;
         }
         }*/
        //solicitud.setNombreArea(concesionMinera.getNombreConcesion());
        //solicitud.setCasilleroJudicial(concesionMinera.getCasilleroJudicial());
        //solicitud.setDocumentoSolicitante(concesionMinera.getDocumentoConcesionarioPrincipal());
        /*if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 10) {
            solicitud.setTipoDocumento("Cedula");
        } else if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
            solicitud.setTipoDocumento("Ruc");
        }
        if (tipoPersona.equals("N")) {
            solicitud.setTipoPersona("PNA");
        } else if (tipoPersona.equals("J")) {
            solicitud.setTipoPersona("PJU");
        }*/
        concesionMinera.setNombreConcesionarioPrincipal(concesionMinera.getNombreTitular());
        concesionMinera.setApellidoConcesionarioPrincipal(concesionMinera.getApellidoTitular());
        //solicitud.setCampoReservado10(concesionMinera.getCodigoArcom());
        //solicitud.setPlazoConcesion(concesionMinera.getPlazoConcesion());
        //solicitud.setCodigoCensal(concesionMinera.getCodigoCensal());
        concesionMinera.setCodigoRegional(regional);
        //concesionMinera.setNumeroHectareasConcesion(solicitud.getNumeroHectareas());
        //concesionMinera.getCodigoTipoMineria().setCodigoTipoMineria(tipoMineria.getCodigoTipoMineria());
        //concesionMinera.setCodigoZona(codigoZona);
        concesionMinera.setFechaModificacion(new Date());
        concesionMinera.setUsuarioCreacion(BigInteger.valueOf(-1));
        concesionMinera.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
        concesionMinera.setMigrada(true);
        //solicitud.setTipoMaterial(concesionMinera.getTipoMaterial());
        //solicitud.setMaterialInteres(concesionMinera.getMaterialInteres());
        //solicitud.setCodigoProvincia(concesionMinera.getCodigoProvincia());
        //solicitud.setCodigoCanton(concesionMinera.getCodigoCanton());
        //solicitud.setCodigoParroquia(concesionMinera.getCodigoParroquia());
        //solicitud.setFechaModificacion(new Date());
        ///olicitud.setUsuarioCreacion(BigInteger.valueOf(-1));
        //solicitud.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
        //solicitud.setMigrada(true);
        //CatalogoDetalle estadoArea = new CatalogoDetalle();
        /*if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.CADUCADO.getCodigo4())) {
         estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.CADUCADO.getCodigo19());
         }*/
        /*if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.INSCRITA.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.INSCRITA.getCodigo19());
        }
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.OTORGADO.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.OTORGADO.getCodigo19());
        }*/
        /*if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.SOLICITUD_EXPIRADA.getCodigo4())) {
         estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.SOLICITUD_EXPIRADA.getCodigo19());
         }*/
        /*if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.TRAMITE.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.TRAMITE.getCodigo19());
        }
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.ACUMULADA.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.ACUMULADA.getCodigo19());
        }
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.ARCHIVADA.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.ARCHIVADA.getCodigo19());
        }
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConversionEstadosEnum.SUSPENDIDO.getCodigo4())) {
            estadoArea.setCodigoCatalogoDetalle(ConversionEstadosEnum.SUSPENDIDO.getCodigo19());
        }
        if (estadoArea.getCodigoCatalogoDetalle() == null) {
            estadoArea = null;
        }*/
        areaMinera.setEstadoArea(concesionMinera.getEstadoConcesion());
        areaMinera.setCodigoConcesion(concesionMinera);
        areaMinera.setNombreAreaMinera(concesionMinera.getNombreConcesion());
        areaMinera.setSuperficieAreaMinera(BigDecimal.valueOf(concesionMinera.getNumeroHectareasConcesion()));
        areaMinera.getCodigoLocalidad().setCodigoLocalidad(Long.valueOf(concesionMinera.getCodigoParroquia().toString()));
        areaMinera.setUsuarioCreacion(BigInteger.valueOf(-1));
        areaMinera.setUsuarioModificacion(BigInteger.ZERO);
        areaMinera.setFechaModificacion(new Date());
        areaMinera.setMigrada(true);

        try {
            if (concesionMinera.getCodigoConcesion() == null) {
                //Long codigoConcesionSiguiente = concesionMineraServicio.obtenerSiguienteCodigoConcesion();
                Secuencia secuenciaConcesion = secuenciaServicio.obtenerPorTabla("CONCESION_MINERA");
                Long codigoConcesionSiguiente = secuenciaConcesion.getValor();
                concesionMinera.setCodigoConcesion(codigoConcesionSiguiente);
                //solicitud.setSecuenciaSolicitud(codigoConcesionSiguiente);
                //mostrarCoordenadas = true;
                concesionMineraServicio.guardarTodo(concesionMinera, null, areaMinera, us);
                secuenciaConcesion.setValor(codigoConcesionSiguiente + 1);
                secuenciaServicio.update(secuenciaConcesion);

                if (mostrarCoordenadas == false) {
                    mostrarCoordenadas = true;
                }
                if (mostrarMaquinaria == false) {
                    mostrarMaquinaria = true;
                }
                System.out.println("concesionMinera.getCodigoConcesion(): " + concesionMinera.getCodigoConcesion());
                //System.out.println("solicitud.getCodigoSolicitud(): " + solicitud.getCodigoSolicitud());
                System.out.println("mostrarCoordenadas: " + mostrarCoordenadas);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(concesionMinera.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                /*Auditoria auditoria2 = new Auditoria();
                auditoria2.setAccion("INSERT");
                auditoria2.setDetalleAnterior(solicitud.toString());
                auditoria2.setDetalleCambios(null);
                auditoria2.setFecha(getCurrentTimeStamp());
                auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria2);*/
                Auditoria auditoria3 = new Auditoria();
                auditoria3.setAccion("INSERT");
                auditoria3.setDetalleAnterior(areaMinera.toString());
                auditoria3.setDetalleCambios(null);
                auditoria3.setFecha(getCurrentTimeStamp());
                auditoria3.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria3);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "El registro ha sido guardado", null));
                return null;
            } else {
                concesionMineraServicio.actualizarTodo(concesionMinera, null, areaMinera);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(getConcesionMineraAnterior().toString());
                auditoria.setDetalleCambios(concesionMinera.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                /*Auditoria auditoria2 = new Auditoria();
                auditoria2.setAccion("UPDATE");
                auditoria2.setDetalleAnterior(getSolicitudAnterior().toString());
                auditoria2.setDetalleCambios(solicitud.toString());
                auditoria2.setFecha(getCurrentTimeStamp());
                auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria2);*/
                Auditoria auditoria3 = new Auditoria();
                auditoria3.setAccion("UPDATE");
                auditoria3.setDetalleAnterior(getAreaMineraAnterior().toString());
                auditoria3.setDetalleCambios(areaMinera.toString());
                auditoria3.setFecha(getCurrentTimeStamp());
                auditoria3.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria3);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "El registro ha sido actualizado", null));
                return "concesionesmineras";
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al guardar el registro", ex.getMessage()));
            ex.printStackTrace();
            return null;
        }

        //return "concesionesmineras";
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
            if (concesionMinera.getCodigoProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(concesionMinera.getCodigoProvincia().toString()));
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

    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        concesionMinera.setCodigoCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (concesionMinera.getCodigoCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(concesionMinera.getCodigoCanton().toString()));
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

    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }

    public List<SelectItem> getTipoMaterial() {
        if (tipoMaterial == null) {
            tipoMaterial = new ArrayList<>();
            //Catalogo catalogoTipoMaterial = catalogoServicio.findByNemonico("MATEXP");
            List<Catalogo> catalogoTipoMaterial = catalogoServicio.findByCatalogoPadre(Long.valueOf(9));
            //List<CatalogoDetalle> tipMatCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoMaterial.getCodigoCatalogo());

            for (Catalogo tipMat : catalogoTipoMaterial) {
                tipoMaterial.add(new SelectItem(tipMat.getCodigoCatalogo(), tipMat.getNombre().toUpperCase()));
            }
        }
        return tipoMaterial;
    }

    public void setTipoMaterial(List<SelectItem> tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public void cargaTipoMaterialDetalle() {
        tipoMaterialDetalle = null;
        getTipoMaterialDetalle();
    }

    public List<SelectItem> getTipoMaterialDetalle() {
        if (tipoMaterialDetalle == null) {
            tipoMaterialDetalle = new ArrayList<>();
            /*if (concesionMinera.getTipoMaterial() == null || concesionMinera.getTipoMaterial().toUpperCase().equals("MATERIALES DE CONSTRUCCIO")
             || concesionMinera.getTipoMaterial().toUpperCase().equals("MATERIAL DE CONSTRUCCION")) {
             return tipoMaterialDetalle;
             }*/
            if (concesionMinera.getCodigoTipoMaterial() == null 
                    || concesionMinera.getCodigoTipoMaterial().getCodigoCatalogo() == null) {
                return tipoMaterialDetalle;
            }
            System.out.println("concesionMinera.getCodigoTipoMaterial(): " + concesionMinera.getCodigoTipoMaterial());
            //CatalogoDetalle catalogoDetalleTipoMaterial = catalogoDetalleServicio.findByPk(Long.valueOf(concesionMinera.getTipoMaterial()));
            Catalogo catalogo = catalogoServicio.findByPk(concesionMinera.getCodigoTipoMaterial().getCodigoCatalogo());
            if (catalogo != null) {
                List<CatalogoDetalle> tipMatCatDet = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());

                for (CatalogoDetalle tipMat : tipMatCatDet) {
                    tipoMaterialDetalle.add(new SelectItem(tipMat.getCodigoCatalogoDetalle(), tipMat.getNombre().toUpperCase()));
                }
            }
        }
        return tipoMaterialDetalle;
    }

    public void setTipoMaterialDetalle(List<SelectItem> tipoMaterialDetalle) {
        this.tipoMaterialDetalle = tipoMaterialDetalle;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public boolean isExisteCodigoArcom() {
        if (concesionMinera.getCodigoConcesion() == null) {
            if (concesionMinera.getCodigoArcom() != null) {
                ConcesionMinera cm = concesionMineraDao.findByCodigoArcom(concesionMinera.getCodigoArcom());
                if (cm == null) {
                    existeCodigoArcom = false;
                } else {
                    existeCodigoArcom = true;
                    concesionMinera.setCodigoArcom(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Código de licencia existente, por favor ingrese uno nuevo", null));
                }
            }
        }
        return existeCodigoArcom;
    }

    public void setExisteCodigoArcom(boolean existeCodigoArcom) {
        this.existeCodigoArcom = existeCodigoArcom;
    }

    public void validarCodigoArcom() {
        isExisteCodigoArcom();
    }

    public boolean isCedulaValida() {
        if (concesionMinera.getDocumentoConcesionarioPrincipal() != null) {
            if (concesionMinera.getDocumentoConcesionarioPrincipal().length() >= 10) {
                if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
                    String nuevaCed = concesionMinera.getDocumentoConcesionarioPrincipal().substring(0, concesionMinera.getDocumentoConcesionarioPrincipal().length() - 3);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        concesionMinera.setDocumentoConcesionarioPrincipal(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(concesionMinera.getDocumentoConcesionarioPrincipal())) {
                    cedulaValida = true;
                } else {
                    cedulaValida = false;
                    concesionMinera.setDocumentoConcesionarioPrincipal(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                concesionMinera.setDocumentoConcesionarioPrincipal(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }
        return cedulaValida;
    }

    public void setCedulaValida(boolean cedulaValida) {
        this.cedulaValida = cedulaValida;
    }

    public void validarCedula() {
        isCedulaValida();
    }

    public boolean isCedulaRepLegalValida() {
        /*if (solicitud.getRepresentanteLegal() != null) {
            if (solicitud.getRepresentanteLegal().length() >= 10) {
                if (solicitud.getRepresentanteLegal().length() == 13) {
                    String nuevaCed = solicitud.getRepresentanteLegal().substring(0, solicitud.getRepresentanteLegal().length() - 3);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        solicitud.setRepresentanteLegal(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(solicitud.getRepresentanteLegal())) {
                    cedulaRepLegalValida = true;
                } else {
                    cedulaRepLegalValida = false;
                    solicitud.setRepresentanteLegal(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                solicitud.setRepresentanteLegal(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }*/
        return cedulaRepLegalValida;
    }

    public void setCedulaRepLegalValida(boolean cedulaRepLegalValida) {
        this.cedulaRepLegalValida = cedulaRepLegalValida;
    }

    public void validarCedulaRepLegal() {
        isCedulaRepLegalValida();
    }

    public boolean isPerNatural() {
        if (tipoPersona.equals("N")) {
            perNatural = true;
        } else if (tipoPersona.equals("J")) {
            perNatural = false;
        }
        return perNatural;
    }

    public void setPerNatural(boolean perNatural) {
        this.perNatural = perNatural;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void validarTipoPersona() {
        isPerNatural();
    }

    /*public List<SolicitudDetalle> getCoordenadasSolicitud() {
        //System.out.println("solicitud.getCodigoSolicitud(): " + solicitud.getCodigoSolicitud());
        System.out.println("coordenadasSolicitud: " + coordenadasSolicitud);
        if (coordenadasSolicitud == null) {
            coordenadasSolicitud = solicitudDetalleDao.findByCodigoSolicitud(solicitud.getCodigoSolicitud());
        }
        return coordenadasSolicitud;
    }

    public void setCoordenadasSolicitud(List<SolicitudDetalle> coordenadasSolicitud) {
        this.coordenadasSolicitud = coordenadasSolicitud;
    }*/

    public List<CoordenadaArea> getCoordenadasPorArea() {
        if (coordenadasPorArea == null) {
            System.out.println("codigoAreaMinera: " + areaMinera.getCodigoAreaMinera());
            coordenadasPorArea = coordenadaAreaServicio.findByCodigoArea(areaMinera.getCodigoAreaMinera());
        }
        return coordenadasPorArea;
    }

    public void setCoordenadasPorArea(List<CoordenadaArea> coordenadasPorArea) {
        this.coordenadasPorArea = coordenadasPorArea;
    }

    public void guardarCoordenadas() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        int orden = 0;
        if (coordenadasPorArea.size() > 0) {
            orden = coordenadasPorArea.size();
        }
        /*SolicitudDetalle sd = new SolicitudDetalle();
        sd.setTipoDetalle("INFCOOR");
        sd.setCoordenadaUtmEste(coordenadaX);
        sd.setCoordenadaUtmNorte(coordenadaY);
        sd.setNumeroCoordenada(BigInteger.valueOf(orden));
        if (orden == 0) {
            sd.setCoordenadaInicial(true);
        } else {
            sd.setCoordenadaInicial(false);
        }
        sd.setCodigoConcesion(concesionMinera.getCodigoConcesion().toString());
        sd.setCodigoSolicitud(solicitud);
        sd.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
        sd.setFechaCreacion(new Date());
        sd.setMigrada(true);
        sd.setEstadoRegistro(true);*/
        CoordenadaArea ca = new CoordenadaArea();
        ca.setUtmEste(coordenadaX);
        ca.setUtmNorte(coordenadaY);
        ca.setNumeroCoordenada(BigInteger.valueOf(orden));
        if (orden == 0) {
            ca.setInicial(true);
        } else {
            ca.setInicial(false);
        }
        ca.setCodigoArea(areaMinera);
        ca.setUsuarioCreacion(BigInteger.valueOf(-1));
        ca.setFechaCreacion(new Date());
        ca.setMigrada(true);
        ca.setEstadoRegistro(true);
        try {
            solicitudDetalleServicio.guardarTodo(null, ca);
            /*Auditoria auditoria = new Auditoria();
            auditoria.setAccion("INSERT");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(sd.toString());
            auditoriaServicio.create(auditoria);*/
            Auditoria auditoria2 = new Auditoria();
            auditoria2.setAccion("INSERT");
            auditoria2.setFecha(getCurrentTimeStamp());
            auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria2.setDetalleAnterior(ca.toString());
            auditoriaServicio.create(auditoria2);
            FacesContext
                    .getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Registro guardado correctamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
        }
        coordenadasPorArea = null;
        getCoordenadasPorArea();
        //return "concesionesmineras";
    }

    public void editarCoordenadas() {

    }

    public void eliminarCoordenadas() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            //SolicitudDetalle solicitudDetalleItem = (SolicitudDetalle) getExternalContext().getRequestMap().get("crd");
            // solicitudDetalleServicio.delete(solicitudDetalleItem.getCodigoSolicitudDetalle());
            CoordenadaArea ca = (CoordenadaArea) getExternalContext().getRequestMap().get("crd");
            //CoordenadaArea ca = coordenadaAreaServicio.findByCodigoAreaOrden(areaMinera.getCodigoAreaMinera(), solicitudDetalleItem.getNumeroCoordenada());
            solicitudDetalleServicio.eliminarTodo(null, ca.getCodigoCoordenada());
            /*Auditoria auditoria = new Auditoria();
            auditoria.setAccion("DELETE");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(solicitudDetalleItem.toString());
            auditoriaServicio.create(auditoria);*/
            Auditoria auditoria2 = new Auditoria();
            auditoria2.setAccion("DELETE");
            auditoria2.setFecha(getCurrentTimeStamp());
            auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria2.setDetalleAnterior(ca.toString());
            auditoriaServicio.create(auditoria2);
            //coordenadasSolicitud = null;
            coordenadasPorArea = null;
            getCoordenadasPorArea();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Coordenadas eliminadas", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo eliminar el registro", ex.getMessage()));
        }
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public boolean isMostrarCoordenadas() {
        return mostrarCoordenadas;
    }

    public void setMostrarCoordenadas(boolean mostrarCoordenadas) {
        this.mostrarCoordenadas = mostrarCoordenadas;
    }

    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void cambiaValor() {
        System.out.println("entra cambiaValor()");
        if (mostrarCoordenadas == false) {
            mostrarCoordenadas = true;
        }
        if (mostrarMaquinaria == false) {
            mostrarMaquinaria = true;
        }
        System.out.println("mostrarCoordenadas***** " + mostrarCoordenadas);
    }

    public Long getCodigoMaquinaria() {
        return codigoMaquinaria;
    }

    public void setCodigoMaquinaria(Long codigoMaquinaria) {
        this.codigoMaquinaria = codigoMaquinaria;
    }

    public List<MaquinariaConcesion> getMaquinariasPorConcesion() {
        if (maquinariasPorConcesion == null) {
            maquinariasPorConcesion = maquinariaConcesionServicio.obtenerMaquinariasPorConcesion(concesionMinera.getCodigoConcesion());
        }
        return maquinariasPorConcesion;
    }

    public void setMaquinariasPorConcesion(List<MaquinariaConcesion> maquinariasPorConcesion) {
        this.maquinariasPorConcesion = maquinariasPorConcesion;
    }

    public void guardarMaquinaria() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        System.out.println("codigoMaquinaria: " + codigoMaquinaria);
        try {
            MaquinariaConcesion maquinariaConcesion = new MaquinariaConcesion();
            ConcesionMinera cm = new ConcesionMinera();
            cm.setCodigoConcesion(concesionMinera.getCodigoConcesion());
            maquinariaConcesion.setCodigoConcesion(cm);
            maquinariaConcesion.setCodigoTipoMaquinaria(new TipoMaquinaria());
            maquinariaConcesion.getCodigoTipoMaquinaria().setCodigoTipoMaquinaria(codigoMaquinaria);
            maquinariaConcesion.setCantidadMaquinaria(cantidadMaquinaria);
            maquinariaConcesion.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
            maquinariaConcesion.setFechaCreacion(new Date());
            maquinariaConcesionServicio.create(maquinariaConcesion);
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("INSERT");
            auditoria.setFecha(getCurrentTimeStamp());
            auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
            auditoria.setDetalleAnterior(maquinariaConcesion.toString());
            auditoriaServicio.create(auditoria);
            maquinariasPorConcesion = null;
            getMaquinariasPorConcesion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Registro guardado correctamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
        }
    }

    public boolean isMostrarMaquinaria() {
        return mostrarMaquinaria;
    }

    public void setMostrarMaquinaria(boolean mostrarMaquinaria) {
        this.mostrarMaquinaria = mostrarMaquinaria;
    }

    public void eliminarMaquinaria() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        MaquinariaConcesion maquinariaConcesionItem = (MaquinariaConcesion) getExternalContext().getRequestMap().get("maq");
        maquinariaConcesionServicio.delete(maquinariaConcesionItem.getCodigoMaquinaria());
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion("DELETE");
        auditoria.setFecha(getCurrentTimeStamp());
        auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
        auditoria.setDetalleAnterior(maquinariaConcesionItem.toString());
        auditoriaServicio.create(auditoria);
        maquinariasPorConcesion = null;
        getMaquinariasPorConcesion();
    }

    public int getLongitudCoordenadas() {
        longitudCoordenadas = getCoordenadasPorArea().size();
        return longitudCoordenadas;
    }

    public void setLongitudCoordenadas(int longitudCoordenadas) {
        this.longitudCoordenadas = longitudCoordenadas;
    }

    public List<SelectItem> getFasesCodigoCatalogo() {
        if (fasesCodigoCatalogo == null) {
            fasesCodigoCatalogo = new ArrayList<>();
            List<Fase> fasesPorRegimen = null;
            if (concesionMinera.getCodigoRegimen().getCodigoRegimen() != null) {
                Regimen regimen = regimenServicio.findByPk(concesionMinera.getCodigoRegimen().getCodigoRegimen());
                if (regimen != null) {
                    if (regimen.getNemonico().equals(ConstantesEnum.GRAN_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.GRAN_MINERIA.getDescripcion());
                    } else if (regimen.getNemonico().equals(ConstantesEnum.MED_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.MED_MINERIA.getDescripcion());
                    } else if (regimen.getNemonico().equals(ConstantesEnum.PEQ_MINERIA.getNemonico())) {
                        fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion(ConstantesEnum.PEQ_MINERIA.getDescripcion());
                    }
                }
                if (concesionMinera.getCodigoRegimen().getCodigoRegimen().equals(1000L)) {
                    fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion("T");
                }
            } else {
                fasesPorRegimen = faseServicio.obtenerFasesLikeDescripcion("T");
            }
            if (fasesPorRegimen != null) {
                for (Fase f : fasesPorRegimen) {
                    fasesCodigoCatalogo.add(new SelectItem(f.getCodigoFase(), f.getNombreFase().toUpperCase()));
                }
            }
        }
        return fasesCodigoCatalogo;
    }

    public void setFasesCodigoCatalogo(List<SelectItem> fasesCodigoCatalogo) {
        this.fasesCodigoCatalogo = fasesCodigoCatalogo;
    }

    public void obtenerFasePorRegimen() {
        fasesCodigoCatalogo = null;
        getFasesCodigoCatalogo();
    }

    public boolean isOtorgado() {
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle() != null) {
            if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConstantesEnum.ESTCONC_OTORGADO.getCodigo())
                    || concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConstantesEnum.ESTCONC_INSCRITO.getCodigo())) {
                otorgado = true;
            } else {
                otorgado = false;
            }
        }
        return otorgado;
    }

    public void setOtorgado(boolean otorgado) {
        this.otorgado = otorgado;
    }

    public boolean isInscrito() {
        if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle() != null) {
            if (concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle().equals(ConstantesEnum.ESTCONC_INSCRITO.getCodigo())) {
                inscrito = true;
            } else {
                inscrito = false;
            }
        }
        return inscrito;
    }

    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }

    public boolean isTipSolConcMin() {
        return tipSolConcMin;
    }

    public void setTipSolConcMin(boolean tipSolConcMin) {
        this.tipSolConcMin = tipSolConcMin;
    }

    public void validarRegimenFase() {
        if (concesionMinera.getCodigoTipoMineria() != null 
                && concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria() != null) {
            if (concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())) {
                tipSolConcMin = true;
            } else {
                tipSolConcMin = false;
            }
        }
        muestraModalidadTrabajo();
    }

    public ConcesionMinera getConcesionMineraAnterior() {
        if (concesionMineraAnterior == null) {
            concesionMineraAnterior = concesionMineraDao.findByPk(concesionMinera.getCodigoConcesion());
        }
        return concesionMineraAnterior;
    }

    public void setConcesionMineraAnterior(ConcesionMinera concesionMineraAnterior) {
        this.concesionMineraAnterior = concesionMineraAnterior;
    }

    /*public Solicitud getSolicitudAnterior() {
        if (solicitudAnterior == null) {
            solicitudAnterior = solicitudServicio.obtenerPorCodigoArcom(concesionMineraAnterior.getCodigoArcom());
        }
        return solicitudAnterior;
    }

    public void setSolicitudAnterior(Solicitud solicitudAnterior) {
        this.solicitudAnterior = solicitudAnterior;
    }*/

    public AreaMinera getAreaMineraAnterior() {
        if (areaMineraAnterior == null) {
            areaMineraAnterior = areaMineraServicio.obtenerPorConcesionMinera(concesionMineraAnterior.getCodigoConcesion());
        }
        return areaMineraAnterior;
    }

    public void setAreaMineraAnterior(AreaMinera areaMineraAnterior) {
        this.areaMineraAnterior = areaMineraAnterior;
    }

    public void validarCedulaPersonaNatural() {
        String numDocumento = personaNatural.getNumeroDocumento();
        if (personaNatural.getNumeroDocumento().length() == 10) {
            if (!CedulaValidator.validate(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
                return;
            }
        } else if (personaNatural.getNumeroDocumento().length() == 13) {
            if (!CedulaValidator.validacionRUC(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de ruc inválido", null));
                return;
            }
        } else {
            //personaNatural.setNumeroDocumento(null);
            personaNatural = null;
            getPersonaNatural();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de documento inválido", null));
            return;
        }

        if (personaNatural.getNumeroDocumento() != null) {
            System.out.println("entra else");
            personaNatural = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
            System.out.println("personaNatural: " + personaNatural);
            if (personaNatural == null) {
                getPersonaNatural();
                personaNatural.setNumeroDocumento(numDocumento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de documento no existente", null));
                System.out.println("personaNatural.getNumeroDocumento(): " + personaNatural.getNumeroDocumento());
            }
            /*else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
             "Número de documento existente", null));
             }*/
        }
    }

    public void guardarPersonaNatural() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        concesionMinera.setDocumentoConcesionarioPrincipal(personaNatural.getNumeroDocumento());
        concesionMinera.setPersonaNaturalTransient(personaNatural);
        concesionMinera.getPersonaNaturalTransient();
        //solicitud.setNombreSolicitante(personaNatural.getNombre());
        //solicitud.setApellidoSolicitante(personaNatural.getApellido());
        //solicitud.setTelefonoCelularSolicitante(personaNatural.getCelular());
        //solicitud.setTelefonoConvencionalSolicitante(personaNatural.getTelefono());
        concesionMinera.setCasilleroJudicial(personaNatural.getCasilleroJudicial());
        //solicitud.setDireccionSolicitante(personaNatural.getDireccion());
        //solicitud.setEmailSolicitante(personaNatural.getEmail());
        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
        try {
            if (pn == null) {
                personaNatural.setFechaCreacion(new Date());
                personaNatural.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.create(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            } else {
                personaNatural.setFechaModificacion(new Date());
                personaNatural.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.actualizarPersonaNatural(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pn.toString());
                auditoria.setDetalleCambios(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
        }
    }

    public void llenaPopUpPersonaNatural() {
        getPersonaNatural();
        personaNatural.setNumeroDocumento(concesionMinera.getDocumentoConcesionarioPrincipal());
        /*personaNatural.setNombre(solicitud.getNombreSolicitante());
         personaNatural.setApellido(solicitud.getApellidoSolicitante());
         personaNatural.setCelular(solicitud.getTelefonoCelularSolicitante());
         personaNatural.setTelefono(solicitud.getTelefonoConvencionalSolicitante());
         personaNatural.setCasilleroJudicial(concesionMinera.getCasilleroJudicial());
         personaNatural.setDireccion(solicitud.getDireccionSolicitante());
         personaNatural.setEmail(solicitud.getEmailSolicitante());*/
    }

    public void validarCedulaPersonaJuridica() {
        String numRuc = personaJuridica.getRuc();
        if (personaJuridica.getRuc().length() != 13) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Se debe ingresar 13 dígitos para el ruc", null));
            //personaJuridica.setRuc(null);
            personaJuridica = null;
            getPersonaJuridica();
            return;
        }
        /*if (!CedulaValidator.validacionRUC(personaJuridica.getRuc())) {
         //personaJuridica.setRuc(null);
         personaJuridica = null;
         getPersonaJuridica();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
         "Número de ruc inválido", null));
         } else {*/
        System.out.println("entra else");
        personaJuridica = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        System.out.println("personaJuridica: " + personaJuridica);
        if (personaJuridica == null) {
            getPersonaJuridica();
            personaJuridica.setRuc(numRuc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Número de ruc no existente", null));
        }
        /*else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
         "Número de ruc existente", null));
         }*/
        //}
    }

    public void guardarPersonaJuridica() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        concesionMinera.setDocumentoConcesionarioPrincipal(personaJuridica.getRuc());
        concesionMinera.setPersonaJuridicaTransient(personaJuridica);
        concesionMinera.getPersonaJuridicaTransient();
        //solicitud.setNombreSolicitante(personaJuridica.getNombreLegal());
        //solicitud.setApellidoSolicitante(personaJuridica.getNombreComercial());
        //solicitud.setTelefonoCelularSolicitante(personaJuridica.getCelular());
        //solicitud.setTelefonoConvencionalSolicitante(personaJuridica.getTelefono());
        concesionMinera.setCasilleroJudicial(personaJuridica.getCasilleroJudicial());
        //solicitud.setDireccionSolicitante(personaJuridica.getDireccion());
        //solicitud.setEmailSolicitante(personaJuridica.getEmail());
        //solicitud.setRepresentanteLegal(personaJuridica.getDocumentoRepresentanteLegal());
        //solicitud.setNombreRepresentanteLegal(personaJuridica.getNombreRepresentanteLegal());
        //solicitud.setApellidoRepresentanteLegal(personaJuridica.getApellidoRepresentanteLegal());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        try {
            if (pj == null) {
                personaJuridica.setFechaCreacion(new Date());
                personaJuridica.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                localidad.setCodigoLocalidad(Long.valueOf("5"));
                personaJuridica.setCodigoLocalidad(localidad);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.create(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            } else {
                personaJuridica.setFechaModificacion(new Date());
                personaJuridica.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                //localidad.setCodigoLocalidad(Long.valueOf(concesionMinera.getCodigoParroquia().toString()));
                personaJuridica.setCodigoLocalidad(null);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.actualizarPersonaJuridica(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pj.toString());
                auditoria.setDetalleCambios(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
            System.out.println("ex.getMessage()" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void llenarPopUpPersonaJuridica() {
        getPersonaJuridica();
        personaJuridica.setRuc(concesionMinera.getDocumentoConcesionarioPrincipal());
    }

    public void validarCedulaRepresentanteLegal() {
        System.out.println("entra validarCedulaRepresentanteLegal");
        String numDocumento = personaJuridica.getDocumentoRepresentanteLegal();
        String nuevaCed = personaJuridica.getDocumentoRepresentanteLegal();
        if (personaJuridica.getDocumentoRepresentanteLegal().length() >= 10) {
            if (personaJuridica.getDocumentoRepresentanteLegal().length() == 13) {
                nuevaCed = personaJuridica.getDocumentoRepresentanteLegal().substring(0, personaJuridica.getDocumentoRepresentanteLegal().length() - 3);
            }
        }
        if (!CedulaValidator.validate(nuevaCed)) {
            personaJuridica.setDocumentoRepresentanteLegal(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de cédula inválido", null));
        }
    }

    public boolean isTipoSolMineriaArtesanal() {
        return tipoSolMineriaArtesanal;
    }

    public void setTipoSolMineriaArtesanal(boolean tipoSolMineriaArtesanal) {
        this.tipoSolMineriaArtesanal = tipoSolMineriaArtesanal;
    }

    public void muestraModalidadTrabajo() {
        System.out.println("entra muestraModalidadTrabajo");
        //System.out.println("solicitud.getTipoSolicitud(): " + solicitud.getTipoSolicitud());
        //if (solicitud.getTipoSolicitud() != null) {
        if (concesionMinera.getCodigoTipoMineria() != null
                && concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria() != null) {
            //System.out.println("ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getDescripcion(): " + ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getDescripcion());
            if (concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria()
                    .equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
                tipoSolMineriaArtesanal = true;
                concesionMinera.setCodigoModalidadTrabajo(null);
            } else {
                tipoSolMineriaArtesanal = false;
                if (concesionMinera.getCodigoModalidadTrabajo() == null) {
                    concesionMinera.setCodigoModalidadTrabajo(new CatalogoDetalle());
                }
            }
        }
    }

    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

    /*public Resolucion getResolucion() {
     return resolucion;
     }

     public void setResolucion(Resolucion resolucion) {
     this.resolucion = resolucion;
     }*/
}
