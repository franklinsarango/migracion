/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dto.ContratoOperacionDTO;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author CuencaL
 */
@ManagedBean
@ViewScoped
public class contratoOPCtrl extends BaseCtrl {

    @EJB
    private ContratoOperacionServicio contratoOperacionServicio;
    
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;
    
    
    private List<ContratoOperacionDTO> contratosOperacion;
    private String codigoArcomFiltro;
    private String numDocumentoFiltro;
    private String beneficiarioPrincipal;
    protected int numeroPagina = 0;
    protected int desplazamiento;
    private boolean registrosPorRegional;
    protected static final int tamanoPagina = 10;

    public List<ContratoOperacionDTO> getContratosOperacion() {        
        String contratoId = getHttpServletRequestParam("idItem");
        Long idContrato = null;
        if (contratoId != null) {
            idContrato = Long.parseLong(contratoId);
        }
        if (idContrato == null) {
            contratosOperacion = new ArrayList<>();            
        } else {
            contratosOperacion = contratoOperacionServicio.countByContratoOperacionTabla(login.getUserName(), contratoId, numDocumentoFiltro, registrosPorRegional, tamanoPagina, desplazamiento, beneficiarioPrincipal);            
        }
        return contratosOperacion;
    }
            
    public contratoOPCtrl() {
        contratosOperacion = new ArrayList<ContratoOperacionDTO>();
    }

    public ContratoOperacionServicio getContratoOperacionServicio() {
        return contratoOperacionServicio;
    }

    public void setContratoOperacionServicio(ContratoOperacionServicio contratoOperacionServicio) {
        this.contratoOperacionServicio = contratoOperacionServicio;
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
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

    public String getBeneficiarioPrincipal() {
        return beneficiarioPrincipal;
    }

    public void setBeneficiarioPrincipal(String beneficiarioPrincipal) {
        this.beneficiarioPrincipal = beneficiarioPrincipal;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public boolean isRegistrosPorRegional() {
        return registrosPorRegional;
    }

    public void setRegistrosPorRegional(boolean registrosPorRegional) {
        this.registrosPorRegional = registrosPorRegional;
    }
 
    
}
