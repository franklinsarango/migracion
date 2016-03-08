/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.ConcesionMineraDaoLocal;
import ec.gob.arcom.migracion.dao.LocalidadDao;
import ec.gob.arcom.migracion.dao.PersonaNaturalDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.CoordenadaAreaServicio;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    private ConcesionMinera concesion;
    private List<ConcesionMinera> concesiones;
    //private List<CoordenadaArea> coordenadas;
    private int longitudCoordenadas;
    private String codigoFiltro= "";
    private List<CoordenadaArea> coordEditadas;
    private CoordenadaArea coordenada;
    
    public CoordenadaCtrl() {
        concesiones= new ArrayList<>();
        coordEditadas= new ArrayList<>();
    }
    
    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }
    
    public ConcesionMinera getConcesion() {
        return concesion;
    }

    public void setConcesion(ConcesionMinera concesion) {
        this.concesion = concesion;
    }

    public List<ConcesionMinera> getConcesiones() {
        //cargarConcesiones();
        return concesiones;
    }

    public void setConcesiones(List<ConcesionMinera> concesiones) {
        this.concesiones = concesiones;
    }

    public List<CoordenadaArea> devolverCoordenadas() {
        return this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList();
    }

    /*public List<CoordenadaArea> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<CoordenadaArea> coordenadas) {
        this.coordenadas = coordenadas;
    }*/

    public int getLongitudCoordenadas() {
        longitudCoordenadas= this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList().size();
        return longitudCoordenadas;
    }

    public void setLongitudCoordenadas(int longitudCoordenadas) {
        this.longitudCoordenadas = longitudCoordenadas;
    }
    
    
    public String editAction(Integer row) {
        this.concesion= cmDao.findByCodigo(concesiones.get(row).getCodigoArcom()).get(0);
        this.concesion.getAreaMineraList().get(0).setCoordenadaAreaList(coordenadaAreaServicio.list(this.concesion.getAreaMineraList().get(0).getCodigoAreaMinera()));
        return "coordenadaform";
    }
    
    public String actualizarCoordenadas() {
        if(comprobarCoordenadas(this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList())) {
            cmDao.update(concesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Actualización correcta", null));
            return "concesiones";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Debe llenar todos los campos de coordenadas", null));
        return null;
    }
    
    //Comprobar que las coordenadas no esten vacias
    private boolean comprobarCoordenadas(List<CoordenadaArea> coords) {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        for (CoordenadaArea coord : coords) {
            //Colocar la fecha y usuario de modificacion para las coordenadas ya existentes
            //y que fueron editadas
            if(coord.getCodigoCoordenada() != null && editada(coord)) {
                coord.setFechaModificacion(Calendar.getInstance().getTime());
                coord.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
            }
            //Aqui se comprueba que las coordenadas no esten vacias
            if(coord.getUtmEste()==null || coord.getUtmNorte() == null)
                return false;
        }
        return true;
    }
    
    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }
    
    public void eliminarCoordenadas(Integer row) {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        try {
            CoordenadaArea ca= this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList().get(row);
            
            try {
                coordenadaAreaServicio.delete(ca.getCodigoCoordenada());
                Auditoria auditoria2 = new Auditoria();
                auditoria2.setAccion("DELETE");
                auditoria2.setFecha(getCurrentTimeStamp());
                auditoria2.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria2.setDetalleAnterior(ca.toString());
                auditoriaServicio.create(auditoria2);
            } catch (Exception e) {
                System.out.println("La coordenada no existia aun en la base de datos");
            }
            this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList().remove(row.intValue());
            if(coordEditadas.size()>0) {
                coordEditadas.remove(ca);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Coordenadas eliminadas", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo eliminar el registro", ex.getMessage()));
        }
    }
    
    private boolean repetida() {
        for (CoordenadaArea coordEditada : coordEditadas) {
            if(coordenada.equals(coordEditada))
                return true;
        }
        return false;
    }
    
    private boolean editada(CoordenadaArea c) {
        for (CoordenadaArea coordEditada : coordEditadas) {
            if(c.equals(coordEditada))
                return true;
        }
        return false;
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
    
    public String obtenerNombreApellidoTitular() {
        PersonaDto persona= pNaturalDao.obtenerPersonaPorNumIdentificacion(concesion.getDocumentoConcesionarioPrincipal());
        
        String result= "";
        if(persona.getNombres() != null)
            result+= persona.getNombres();
        if(persona.getApellidos() != null)
            result+= " " + persona.getApellidos();
        return result;
    }
    
    //Accion del boton agregar
    public void agregarCoordenada() {
        CoordenadaArea crd= new CoordenadaArea();
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        
        crd.setNumeroCoordenada(new BigInteger(this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList().size() + ""));
        crd.setCodigoArea(this.concesion.getAreaMineraList().get(0));
        if(crd.getNumeroCoordenada().compareTo(BigInteger.ZERO)== 0) {
            crd.setInicial(Boolean.TRUE);
        } else {
            crd.setInicial(Boolean.FALSE);
        }
        crd.setEstadoRegistro(Boolean.TRUE);
        crd.setFechaCreacion(Calendar.getInstance().getTime());
        crd.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
        
        this.concesion.getAreaMineraList().get(0).getCoordenadaAreaList().add(crd);
    }
    
    public void buscar() {
        if(codigoFiltro.length()>0) {
            this.concesiones= cmDao.findByCodigo(codigoFiltro);
            codigoFiltro= "";
        } else {
            cargarConcesiones();
        }
    }
    
    //Listener para guardar las coordenadas que han sido editadas
    public void onRowEdit(RowEditEvent event) {
        coordenada= (CoordenadaArea) event.getObject();
        if(coordenada.getCodigoCoordenada() != null && !repetida()) {
            coordEditadas.add(coordenada);
        }
    }
    
    @PostConstruct
    private void cargarListas() {
        cargarConcesiones();
    }
}
