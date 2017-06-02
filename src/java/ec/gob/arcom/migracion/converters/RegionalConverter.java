/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.converters;

import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
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
@Named(value = "regionalConverter")
@RequestScoped
public class RegionalConverter implements Converter{
    @EJB
    private RegionalServicio regionalServicio;
    
    /**
     * Creates a new instance of CatalogoDetalleConverter
     */
    public RegionalConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null) {
            try {
                return regionalServicio.findByPk(Long.parseLong(value));
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al convertir Regional a Objeto");
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((Regional) value).getCodigoRegional());
        }
        return null;
    }
}
