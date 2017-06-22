/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import ec.gob.arcom.migracion.servicio.DetalleFichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.FichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.DateUtil;
import ec.gob.arcom.migracion.util.DetalleFichaTecnicaWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author mejiaw
 */
@ManagedBean(name= "actividadMineraRepCtrl")
@SessionScoped
public class ActividadMineraRepCtrl {
    @EJB
    private FichaTecnicaServicio fichaTecnicaServicio;
    @EJB
    private DetalleFichaTecnicaServicio detalleFichaTecnicaServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private CoordenadaAreaServicio coordenadaAreaServicio;
    @EJB
    private ContratoOperacionServicio contratoOperacionServicio;
    
    private List<FichaTecnica> fichasTecnicas;
    private List<FichaTecnica> filteredFichasTecnicas;
    private boolean filtroAplicado;
    private List<SelectItem> regionales;
    private List<SelectItem> usuarios;
    private FichaTecnica fichaTecnica;
    private boolean codigoCensal;
    private boolean showDerechoMinero;
    private String codigoArcom;
    private boolean showDetalleTipoTerreno;
    private String tipoPersona;
    private List<CoordenadaArea> coordenadas;
    private List<ContratoOperacion> contratos;
    private List<DetalleFichaTecnica> sociosLaborMinera;
    private List<DetalleFichaTecnicaWrapper> infraestruturasWrapper;
    private List<DetalleFichaTecnicaWrapper> maquinariasWrapper;
    private boolean showSubterraneo;
    private boolean showCieloAbierto;
    private List<DetalleFichaTecnicaWrapper> operacionesMinerasWrapper;
    
    /**
     * Creates a new instance of ActividadMineraRepCtrl
     */
    public ActividadMineraRepCtrl() {
        filtroAplicado= false;
        codigoCensal= false;
        codigoArcom= "";
        showDetalleTipoTerreno= false;
        tipoPersona= "";
        coordenadas= new ArrayList();
        contratos= new ArrayList();
        sociosLaborMinera= new ArrayList();
        infraestruturasWrapper= new ArrayList();
        maquinariasWrapper= new ArrayList();
        showSubterraneo= false;
        showCieloAbierto= false;
        operacionesMinerasWrapper= new ArrayList();
    }
    
    @PostConstruct
    public void inicializar() {
        cargarFichasTecnicas();
        regionales= obtenerRegionales();
        usuarios= obtenerUsuarios();
    }

    public List<FichaTecnica> getFichasTecnicas() {
        return fichasTecnicas;
    }

    public void setFichasTecnicas(List<FichaTecnica> fichasTecnicas) {
        this.fichasTecnicas = fichasTecnicas;
    }

    public List<FichaTecnica> getFilteredFichasTecnicas() {
        return filteredFichasTecnicas;
    }

    public void setFilteredFichasTecnicas(List<FichaTecnica> filteredFichasTecnicas) {
        this.filteredFichasTecnicas = filteredFichasTecnicas;
    }

    public boolean isFiltroAplicado() {
        return filtroAplicado;
    }

    public void setFiltroAplicado(boolean filtroAplicado) {
        this.filtroAplicado = filtroAplicado;
    }

    public List<SelectItem> getRegionales() {
        return regionales;
    }

    public void setRegionales(List<SelectItem> regionales) {
        this.regionales = regionales;
    }

    public List<SelectItem> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<SelectItem> usuarios) {
        this.usuarios = usuarios;
    }

    public FichaTecnica getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(FichaTecnica fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public boolean isCodigoCensal() {
        return codigoCensal;
    }

    public void setCodigoCensal(boolean codigoCensal) {
        this.codigoCensal = codigoCensal;
    }

    public boolean isShowDerechoMinero() {
        return showDerechoMinero;
    }

    public void setShowDerechoMinero(boolean showDerechoMinero) {
        this.showDerechoMinero = showDerechoMinero;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public boolean isShowDetalleTipoTerreno() {
        return showDetalleTipoTerreno;
    }

    public void setShowDetalleTipoTerreno(boolean showDetalleTipoTerreno) {
        this.showDetalleTipoTerreno = showDetalleTipoTerreno;
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

    public List<DetalleFichaTecnica> getSociosLaborMinera() {
        return sociosLaborMinera;
    }

    public void setSociosLaborMinera(List<DetalleFichaTecnica> sociosLaborMinera) {
        this.sociosLaborMinera = sociosLaborMinera;
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

    public List<DetalleFichaTecnicaWrapper> getOperacionesMinerasWrapper() {
        return operacionesMinerasWrapper;
    }

    public void setOperacionesMinerasWrapper(List<DetalleFichaTecnicaWrapper> operacionesMinerasWrapper) {
        this.operacionesMinerasWrapper = operacionesMinerasWrapper;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    private void cargarFichasTecnicas() {
        fichasTecnicas= fichaTecnicaServicio.listar();
    }
    
    public String obtenerFecha(Date fecha) {
        if(fecha!=null) {
            return DateUtil.obtenerFechaConFormato(fecha);
        }
        return "";
    }
    
    public void changeFiltroAplicado() {
        this.filtroAplicado= true;
    }
    
    public List<SelectItem> obtenerRegionales() {
        List<Regional> regionalesList= regionalServicio.findActivos();
        List<SelectItem> regionalesSI= new ArrayList<>();
        if(regionalesList!=null) {
            for(Regional r : regionalesList) {
                regionalesSI.add(new SelectItem(r.getCodigoRegional(), r.getNombreRegional()));
            }
        }
        return regionalesSI;
    }
    
    public List<SelectItem> obtenerUsuarios() {
        List<Usuario> usrs= fichaTecnicaServicio.obtenerPorUsuariosDistintos();
        List<SelectItem> usuariosSI= new ArrayList<>();
        if(usrs!=null) {
            for(Usuario usr : usrs) {
                usuariosSI.add(new SelectItem(usr.getCodigoUsuario(), usr.getNombre() + " " + usr.getApellido()));
            }
        }
        return usuariosSI;
    }
    
    public String transformBoolean(Boolean valor) {
        if(valor!=null && valor)
            return "SI";
        return "NO";
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
    
    public String returnAction() {
        return "actividadesminerasreport";
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
    
    public String viewAction(FichaTecnica ft) {
        this.fichaTecnica= ft;
        if(fichaTecnica.getCodigoCensal()!=null && fichaTecnica.getCodigoCensal().length()>0) {
            this.codigoCensal= true;
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
        return "viewfichafrm";
    }
}
