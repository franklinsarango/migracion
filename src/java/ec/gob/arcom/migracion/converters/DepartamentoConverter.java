/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.converters;

import ec.gob.arcom.migracion.modelo.Departamento;
import ec.gob.arcom.migracion.servicio.DepartamentoServicio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@Named(value = "departamentoConverter")
@RequestScoped
public class DepartamentoConverter implements Converter {
    @EJB
    private DepartamentoServicio departamentoServicio;
    
    /**
     * Creates a new instance of CatalogoDetalleConverter
     */
    public DepartamentoConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null) {
            try {
                return departamentoServicio.findByPk(Long.parseLong(value));
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al convertir Departamento a Objeto");
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((Departamento) value).getCodigoDepartamento());
        }
        return null;
    }
}
