/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.converters;

import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
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
@Named(value = "catalogoDetalleConverter")
@RequestScoped
public class CatalogoDetalleConverter implements Converter{
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    
    /**
     * Creates a new instance of CatalogoDetalleConverter
     */
    public CatalogoDetalleConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null) {
            try {
                return catalogoDetalleServicio.findByPk(Long.parseLong(value));
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al convertir CatalogoDetalle a Objeto");
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((CatalogoDetalle) value).getCodigoCatalogoDetalle());
        }
        return null;
    }
}
