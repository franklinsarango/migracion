package ec.gob.arcom.migracion.ctrl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.gob.arcom.dinardap_sri.client.DinardapClient;
import ec.gob.arcom.dinardap_sri.client.RequestFactory;
import ec.gob.dinardap.interoperabilidad.interoperador.Columna;
import ec.gob.dinardap.interoperabilidad.interoperador.Entidad;
import ec.gob.dinardap.interoperabilidad.interoperador.Fila;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@RequestScoped
public class DinardapSriController {
    private List<Entidad> entidades;
    private List<Fila> filas;
    private List<Columna> columnas;
    private String consulta;
    private String valorBuscar;
    private String nombreEntidad;
    
    private boolean tabla625= false;
    private boolean tabla626= false;
    private boolean tabla627= false;
    private boolean tabla628= false;
    
    /**
     * Creates a new instance of DinardapSriController
     */
    public DinardapSriController() {
        
    }
    
    public void consultar() {
        if(consulta!=null && consulta.equals("625")) {
            tabla625= true;
            tabla626= false;
            tabla627= false;
            tabla628= false;
            consulta625();
        } else if(consulta!=null && consulta.equals("626")) {
            tabla625= false;
            tabla626= true;
            tabla627= false;
            tabla628= false;
            consulta626();
        } else if(consulta!=null && consulta.equals("627")) {
            tabla625= false;
            tabla626= false;
            tabla627= true;
            tabla628= false;
            consulta627();
        } else if(consulta!=null && consulta.equals("628")) {
            tabla625= false;
            tabla626= false;
            tabla627= false;
            tabla628= true;
            consulta628();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una opción"));
        }
    }
    
    public void consulta625() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta625(), "625").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta626() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta626(valorBuscar), "626").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }
    
    public void consulta627() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta627(valorBuscar), "627").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }
    
    public void consulta628() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta628(valorBuscar), "628").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidad> entidades) {
        this.entidades = entidades;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public List<Fila> getFilas() {
        return filas;
    }

    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }

    public List<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<Columna> columnas) {
        this.columnas = columnas;
    }
    
    public String obtenerValor(Columna c) {
        return c.getValor();
    }

    public boolean isTabla625() {
        return tabla625;
    }

    public void setTabla625(boolean tabla625) {
        this.tabla625 = tabla625;
    }

    public boolean isTabla626() {
        return tabla626;
    }

    public void setTabla626(boolean tabla626) {
        this.tabla626 = tabla626;
    }

    public boolean isTabla627() {
        return tabla627;
    }

    public void setTabla627(boolean tabla627) {
        this.tabla627 = tabla627;
    }

    public boolean isTabla628() {
        return tabla628;
    }

    public void setTabla628(boolean tabla628) {
        this.tabla628 = tabla628;
    }
}
