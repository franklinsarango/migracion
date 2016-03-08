/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.SadminDataDao;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.SadminData;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class DerechosMinerosCtrl extends BaseCtrl {

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

    private boolean mostrarLista = false;
    private List<DerechoMineroDto> listaRegistros;

    private List<SelectItem> provincias;

    private SadminData sadminData;
    private ConcesionMinera concesionMinera;
    private LicenciaComercializacion licenciaComercializacion;
    private PlantaBeneficio plantaBeneficio;

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

    public void verSadminData() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        sadminData = sadminDataDao.findByPk(derechoMineroDtoItem.getId());
        /*if (sadminData.getCodigoRegimen() != null && sadminData.getCodigoRegimen().getCodigoRegimen() != null) {
         Regimen regimen = regimenServicio.findByPk(sadminData.getCodigoRegimen().getCodigoRegimen());
         if (regimen != null) {
         sadminData.setRegimen(regimen.getNombre());
         }
         }*/
        if (sadminData.getCodigoModalidadTrabajo() == null) {
            sadminData.setCodigoModalidadTrabajo(new CatalogoDetalle());
        } else if (sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() == null) {
            sadminData.getCodigoModalidadTrabajo().setCodigoCatalogoDetalle(1000L);
        }
        if (sadminData.getCodigoCasilleroLocalidad() == null) {
            sadminData.setCodigoCasilleroLocalidad(new Localidad());
        }
        if (sadminData.getProvincia1() != null && sadminData.getCodigoCasilleroLocalidad().getCodigoLocalidad() == null) {
            Localidad provincia = localidadServicio.findByCodInternacional(sadminData.getProvincia1());
            sadminData.setCodigoCasilleroLocalidad(provincia);
        }
        if (sadminData.getProvincia1() != null) {
            Localidad provincia = localidadServicio.findByPk(Long.valueOf(sadminData.getProvincia1()));
            if (provincia != null) {
                sadminData.setProvinciaString(provincia.getNombre());
            }
        }
        if (sadminData.getCanton() != null) {
            Localidad canton = localidadServicio.findByPk(Long.valueOf(sadminData.getCanton()));
            if (canton != null) {
                sadminData.setCantonString(canton.getNombre());
            }
        }
        if (sadminData.getParroquia() != null) {
            Localidad parroquia = localidadServicio.findByPk(Long.valueOf(sadminData.getParroquia()));
            if (parroquia != null) {
                sadminData.setParroquiaString(parroquia.getNombre());
            }
        }
        RequestContext.getCurrentInstance().execute("PF('dlgSadmin').show()");
    }

    public void verConcesionMinera() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        concesionMinera = concesionMineraDao.findByPk(derechoMineroDtoItem.getId());
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

    public void verLicenciaComercializacion() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        licenciaComercializacion = licenciaComercializacionServicio.findByPk(derechoMineroDtoItem.getId());
        if (licenciaComercializacion.getCodigoProvincia() != null) {
            Localidad provincia = localidadServicio.findByPk(licenciaComercializacion.getCodigoProvincia().longValue());
            if (provincia != null && provincia.getNombre() != null) {
                licenciaComercializacion.setProvinciaString(provincia.getNombre());
            }
        }
        if (licenciaComercializacion.getCodigoCanton() != null) {
            Localidad canton = localidadServicio.findByPk(licenciaComercializacion.getCodigoCanton().longValue());
            if (canton != null && canton.getNombre() != null) {
                licenciaComercializacion.setCantonString(canton.getNombre());
            }
        }
        if (licenciaComercializacion.getCodigoParroquida() != null) {
            Localidad parroquia = localidadServicio.findByPk(licenciaComercializacion.getCodigoParroquida().longValue());
            if (parroquia != null && parroquia.getNombre() != null) {
                licenciaComercializacion.setParroquiaString(parroquia.getNombre());
            }
        }
        RequestContext.getCurrentInstance().execute("PF('dlgLicenciasComercializacion').show()");
    }

    public void verPlantaBeneficio() {
        DerechoMineroDto derechoMineroDtoItem = (DerechoMineroDto) getExternalContext().getRequestMap().get("reg");
        plantaBeneficio = plantaBeneficioServicio.findByPk(derechoMineroDtoItem.getId());
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
        if (plantaBeneficio.getMae() != null && plantaBeneficio.getMae()) {
            plantaBeneficio.setMaeString("SI");
        } else {
            plantaBeneficio.setMaeString("NO");
        }
        if (plantaBeneficio.getSenagua() != null && plantaBeneficio.getSenagua()) {
            plantaBeneficio.setSenaguaString("SI");
        } else {
            plantaBeneficio.setSenaguaString("NO");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgPlantaBeneficio').show()");
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

}
