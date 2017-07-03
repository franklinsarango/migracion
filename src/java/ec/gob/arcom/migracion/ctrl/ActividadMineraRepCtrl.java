/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
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
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    
    private List<FichaTecnica> fichasTecnicas;
    private List<FichaTecnica> filteredFichasTecnicas;
    private boolean filtroAplicado;
    private List<SelectItem> regionales;
    private List<SelectItem> usuarios;
    private List<SelectItem> condicionesLaborMinera;
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
    private boolean byRegional;
    
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
        byRegional= true;
        System.out.println(" ############## se vuelve a ejecutar el constructor");
    }
        
    @PostConstruct
    public void inicializar() {
        cargarFichasTecnicas();
        regionales= obtenerRegionales();
        usuarios= obtenerUsuarios();
        condicionesLaborMinera= obtenerCondicionesLaborMinera();
        System.out.println(" ######## se vuelve a ejecutar el postconstruct");
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

    public List<SelectItem> getCondicionesLaborMinera() {
        return condicionesLaborMinera;
    }

    public void setCondicionesLaborMinera(List<SelectItem> condicionesLaborMinera) {
        this.condicionesLaborMinera = condicionesLaborMinera;
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

    public boolean isByRegional() {
        return byRegional;
    }

    public void setByRegional(boolean byRegional) {
        this.byRegional = byRegional;
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
                usuariosSI.add(new SelectItem(usr.getCodigoUsuario(), usr.getNombre().toUpperCase() + " " + usr.getApellido().toUpperCase()));
            }
        }
        return usuariosSI;
    }
    
    public List<SelectItem> obtenerCondicionesLaborMinera() {
        Catalogo catalogo= catalogoServicio.findByNemonico("TIPOCON");
        List<SelectItem> condicionesSI= new ArrayList<>();
        if (catalogo != null) {
            List<CatalogoDetalle> condiciones = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
            for (CatalogoDetalle condicion : condiciones) {
                condicionesSI.add(new SelectItem(condicion.getCodigoCatalogoDetalle(), condicion.getNombre().toUpperCase()));
            }
        }
        return condicionesSI;
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
        cargarFichasTecnicas();
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
            System.out.println("##### Codigo censal: " + fichaTecnica.getCodigoCensal());
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
        return "viewfichafrm";
    }
    
    public void showByRegional() {
        byRegional= true;
    }
    
    public void showByUsuario() {
        byRegional= false;
    }
    
    public String labels() {
        String l= "";
        List<String> labels;
        if(byRegional) {
            labels= obtenerLabelsRegional(regionalServicio.findActivos());
        } else {
            labels= obtenerLabelsUsuario(fichaTecnicaServicio.obtenerPorUsuariosDistintos());
        }
        
        if(labels!=null && labels.size()>0) {
            l= labels.get(0);
            for (int i=1; i<labels.size(); i++) {
                l+= ";" + labels.get(i);
            }
        }
        return l;
    }
    
    public String values() {
        String v= "";
        List<Long> values;
        if(byRegional) {
            values= obtenerValuesRegional(regionalServicio.findActivos());
        } else {
            values= obtenerValuesUsuario(fichaTecnicaServicio.obtenerPorUsuariosDistintos());
        }
                
        if(values!=null && values.size()>0) {
            v= values.get(0).toString();
            for (int i=1; i<values.size(); i++) {
                v+= ";" + values.get(i);
            }
        }
        return v;
    }
    
    private List<String> obtenerLabelsRegional(List<Regional> regs) {
        List<String> labelsRegional= new ArrayList<>();
        for(Regional r : regs) {
            labelsRegional.add(r.getNombreRegional());
        }
        return labelsRegional;
    }
    
    private List<Long> obtenerValuesRegional(List<Regional> regs) {
        List<Long> valuesRegional= new ArrayList<>();
        for(Regional r : regs) {
            Long v= fichaTecnicaServicio.contarPorRegional(r);
            if(v!=null) {
                valuesRegional.add(v);
            }
        }
        return valuesRegional;
    }
    
    private List<String> obtenerLabelsUsuario(List<Usuario> usrs) {
        List<String> labelsUsuario= new ArrayList<>();
        for(Usuario u : usrs) {
            labelsUsuario.add(u.getNombre().toUpperCase() + " " + u.getApellido().toUpperCase());
        }
        return labelsUsuario;
    }
    
    private List<Long> obtenerValuesUsuario(List<Usuario> usrs) {
        List<Long> valuesUsuario= new ArrayList<>();
        for(Usuario u : usrs) {
            Long v= fichaTecnicaServicio.contarPorUsuarioCreacion(u);
            if(v!=null) {
                valuesUsuario.add(v);
            }  
        }
        return valuesUsuario;
    }
}
