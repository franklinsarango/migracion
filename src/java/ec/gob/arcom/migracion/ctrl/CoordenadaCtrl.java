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
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@SessionScoped
public class CoordenadaCtrl extends BaseCtrl {
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
    private CoordenadaAreaServicio coordenadaAreaServicio;
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
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    private ConcesionMinera concesionMinera;
    private ConcesionMinera concesionMineraAnterior;
    private AreaMinera areaMinera;
    private AreaMinera areaMineraAnterior;
    private List<ConcesionMinera> concesiones;
    private List<CoordenadaArea> coordEditadas;
    private String textoCoordenadas;
    private List<CoordenadaArea> listaCoordenadas;
    private List<CoordenadaArea> listaCoordenadasAnterior;
    private List<ConcesionMineraDto> listaRegistros;
    private boolean coordenadasEditadas;
    
    private String codigoFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;
    
    @PostConstruct
    private void cargarListas() {
        cargarConcesiones();
    }
    
    public CoordenadaCtrl() {
        concesiones= new ArrayList<>();
        coordEditadas= new ArrayList<>();
        this.listaCoordenadas = new ArrayList<CoordenadaArea>();
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
    
    public String editarRegistro() {
        ConcesionMineraDto concesionMineraDtoItem = (ConcesionMineraDto) getExternalContext().getRequestMap().get("reg");
        if (concesionMineraDtoItem.getTipoTabla().equals("C")) {
            return "coordenadaform?faces-redirect=true&idItem=" + concesionMineraDtoItem.getCodigoConcesion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se encuentra el registro seleccionado", null));
            return null;
        }
    }
    
    public String editAction(String codigoArcomArea) {
        coordenadasEditadas = false;
        textoCoordenadas = null;
        System.out.println("codigoArcomArea:" + codigoArcomArea);
        this.concesionMinera = cmDao.findByCodigo(codigoArcomArea).get(0);
        this.concesionMineraAnterior = cmDao.findByCodigo(codigoArcomArea).get(0);
        this.areaMinera = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
        this.areaMineraAnterior = areaMineraServicio.obtenerPorConcesionMinera(concesionMinera.getCodigoConcesion());
        this.listaCoordenadas = coordenadaAreaServicio.findByCodigoArea(areaMinera.getCodigoAreaMinera());
        this.listaCoordenadasAnterior = coordenadaAreaServicio.findByCodigoArea(areaMinera.getCodigoAreaMinera());
        return "coordenadaform";
    }
    
    public ConcesionMinera getConcesionMinera() {
        /*if (concesionMinera == null) {
            String concesionMineraId = getHttpServletRequestParam("idItem");
            Long idconcesionMinera = null;
            if (concesionMineraId != null) {
                idconcesionMinera = Long.parseLong(concesionMineraId);
            }
            if (idconcesionMinera != null) {
                System.out.println("idconcesionMinera: " + idconcesionMinera);
                concesionMinera = concesionMineraDao.findByPk(idconcesionMinera);
            }
        }*/
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

    /**
     * Metodo que aniade coordenadas en la matriz
     */
    public void cargarCoordenadas() {
        System.out.println("metodo cargarCoordenadas");
        if (textoCoordenadas.isEmpty()) {
            return;
        }

        String texto = textoCoordenadas;
        String[] array_coordenadas = null;
        String[] parts = texto.split("\n");
        for (String coordenadas : parts) {
            System.out.println(coordenadas);
            array_coordenadas = coordenadas.split("-");

            //Validaciones
            System.out.println(array_coordenadas);
            if (array_coordenadas == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Por Favor Ingrese Coordenadas Válidas", null));
                return;
            }

            if (array_coordenadas.length != 3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error: debe Ingresar la coordenada con los datos: PP-X-Y ", null));
                System.out.println("Error: debe Ingresar la coordenada con los datos: PP-X-Y ");
                return;
            }

            if (array_coordenadas[0].contains(",") || array_coordenadas[1].contains(",")|| array_coordenadas[2].contains(",")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error: Las coordenadas con decimales deben utilizar el signo (.)", null));
                return;
            }
            
            //SE VALIDA QUE SOLO ESTEN INGRESADOS NUMEROS
            try{
                double numeroCoordenada = Double.parseDouble(array_coordenadas[0]);
                double coordenadaEste = Double.parseDouble(array_coordenadas[1]);
                double coordenadaNorte = Double.parseDouble(array_coordenadas[2]);
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error: Las coordenadas solo deben tener numeros", null));
                return;
            }
            

            /*// Validar en Límite de Frontera
            System.out.println("Limite de Frontera: " + areaMineraControlador.getLimiteFrontera());
            if (areaMineraControlador.getLimiteFrontera()) {
                if (!(SoloNumerosValidator.validaCoordenadaConLimiteFrontera(array_coordenadas[0])
                        && SoloNumerosValidator.validaCoordenadaConLimiteFrontera(array_coordenadas[1]))) {
                    controladorBase.ponerMensajeInfo("", "Las coordenadas pueden tener máximo cuatro decimales.");
                    return;
                }
            } else {
                // Validar fuera del Límite de Frontera
                if (!(SoloNumerosValidator.validaCoordenadaSinLimiteFrontera(array_coordenadas[0])
                        && SoloNumerosValidator.validaCoordenadaSinLimiteFrontera(array_coordenadas[1]))) {
                    controladorBase.ponerMensajeInfo("", "Las coordenadas deben ser múltiplos de 100.");
                    return;
                }
            }*/
        }

        this.getListaCoordenadas().clear();
        Integer tamano = 0;
        for (String coordenadas : parts) {
            array_coordenadas = coordenadas.split("-");
            CoordenadaArea c = new CoordenadaArea();
            c.setNumeroCoordenada(new BigInteger(array_coordenadas[0]));
            c.setUtmEste(array_coordenadas[1]);
            c.setUtmNorte(array_coordenadas[2]);
            getListaCoordenadas().add(c);
            tamano++;
        }
        System.out.println("TAMAÑO LISTA COORDENADAS : " + getListaCoordenadas());
        textoCoordenadas = "";
        coordenadasEditadas = true;
    }
    
    public void onRowEditTablaCoordenadas(RowEditEvent event){
        coordenadasEditadas = true;
    }
    public void editarTodasCoordenadas() {
        textoCoordenadas = null;
        if (listaCoordenadas != null) {
            for (CoordenadaArea coordenadaArea : listaCoordenadas) {
                if (coordenadaArea != null) {
                    if (textoCoordenadas == null) {
                        textoCoordenadas = coordenadaArea.getNumeroCoordenada() + "-" + coordenadaArea.getUtmEste() + "-" + coordenadaArea.getUtmNorte();
                    } else {
                        textoCoordenadas = textoCoordenadas + "\n" + coordenadaArea.getNumeroCoordenada() + "-" + coordenadaArea.getUtmEste() + "-" + coordenadaArea.getUtmNorte();
                    }
                }
            }
        }
    }
    
    public String actualizarCoordenadas() {
        if (listaCoordenadas != null) {
            Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
            
            if(coordenadasEditadas == true){
            //SE ELIMINA LAS COORDENADAS ANTERIORES
            for (CoordenadaArea ca : listaCoordenadasAnterior) {
                System.out.println("listaCoordenadasAnterior.ca.getNumeroCoordenada()" + ca.getNumeroCoordenada());
                coordenadaAreaServicio.delete(ca.getCodigoCoordenada());
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("DELETE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(ca.toString());
                auditoria.setNombreTabla(ConstantesEnum.TABLA_COORDENADA_AREA.getDescripcion());
                auditoria.setCodigoTabla(ca.getCodigoCoordenada().toString());
                auditoriaServicio.create(auditoria);
            }

            //SE INSERTA LAS NUEVAS COORDENADAS
            boolean coordenadaInicial = true;
            for (CoordenadaArea ca : listaCoordenadas) {
                System.out.println("listaCoordenadas.ca.getNumeroCoordenada()" + ca.getNumeroCoordenada());
                ca.setInicial(coordenadaInicial);
                coordenadaInicial = false;
                ca.setCodigoArea(areaMinera);
                ca.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                ca.setFechaCreacion(new Date());
                ca.setMigrada(false);
                ca.setEstadoRegistro(true);

                try {
                    coordenadaAreaServicio.create(ca);
                    Auditoria auditoria = new Auditoria();
                    auditoria.setAccion("INSERT");
                    auditoria.setFecha(getCurrentTimeStamp());
                    auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                    auditoria.setDetalleAnterior(ca.toString());
                    auditoria.setNombreTabla(ConstantesEnum.TABLA_COORDENADA_AREA.getDescripcion());
                    auditoria.setCodigoTabla(ca.getCodigoCoordenada().toString());
                    auditoriaServicio.create(auditoria);
                } catch (Exception ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No se pudo guardar el registro", ex.getMessage()));
                    return null;

                }
            }
            }
            
            //SE ACTUALIZA LA CONCESION MINERA
            System.out.println("concesionMinera.getNumeroHectareasConcesion()"+concesionMinera.getNumeroHectareasConcesion().toString());
            System.out.println("concesionMineraAnterior.getNumeroHectareasConcesion()"+concesionMineraAnterior.getNumeroHectareasConcesion().toString());
            if(!concesionMinera.getNumeroHectareasConcesion().equals(concesionMineraAnterior.getNumeroHectareasConcesion())){
                concesionMineraServicio.update(concesionMinera);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(concesionMineraAnterior.toString());
                auditoria.setDetalleCambios(concesionMinera.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setNombreTabla(ConstantesEnum.TABLA_CONCESION_MINERA.getDescripcion());
                auditoria.setCodigoTabla(concesionMinera.getCodigoConcesion().toString());
                auditoriaServicio.create(auditoria);
            }
            
            //SE ACTUALIZA EL AREA MINERA
            if(!concesionMinera.getNumeroHectareasConcesion().equals(concesionMineraAnterior.getNumeroHectareasConcesion())
                    || !areaMinera.getLimiteFrontera().equals(areaMineraAnterior.getLimiteFrontera())){
                areaMinera.setSuperficieAreaMinera(BigDecimal.valueOf(concesionMinera.getNumeroHectareasConcesion()));
                areaMinera.setFechaModificacion(getCurrentTimeStamp());
                areaMinera.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                areaMineraServicio.update(areaMinera);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(areaMineraAnterior.toString());
                auditoria.setDetalleCambios(areaMinera.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setNombreTabla(ConstantesEnum.TABLA_AREA_MINERA.getDescripcion());
                auditoria.setCodigoTabla(areaMinera.getCodigoAreaMinera().toString());
                auditoriaServicio.create(auditoria);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Datos Guardados Correctamente", null));
            return "concesiones";

        }
        return null;
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
     * @return the textoCoordenadas
     */
    public String getTextoCoordenadas() {
        return textoCoordenadas;
    }

    /**
     * @param textoCoordenadas the textoCoordenadas to set
     */
    public void setTextoCoordenadas(String textoCoordenadas) {
        this.textoCoordenadas = textoCoordenadas;
    }

    /**
     * @return the listaCoordenadas
     */
    public List<CoordenadaArea> getListaCoordenadas() {
        return listaCoordenadas;
    }

    /**
     * @param listaCoordenadas the listaCoordenadas to set
     */
    public void setListaCoordenadas(List<CoordenadaArea> listaCoordenadas) {
        this.listaCoordenadas = listaCoordenadas;
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
     * @return the listaCoordenadasAnterior
     */
    public List<CoordenadaArea> getListaCoordenadasAnterior() {
        return listaCoordenadasAnterior;
    }

    /**
     * @param listaCoordenadasAnterior the listaCoordenadasAnterior to set
     */
    public void setListaCoordenadasAnterior(List<CoordenadaArea> listaCoordenadasAnterior) {
        this.listaCoordenadasAnterior = listaCoordenadasAnterior;
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
