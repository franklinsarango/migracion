/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.converters;

import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.servicio.TipoMaquinariaServicio;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author mejiaw
 */
@Named(value = "tipoMaquinariaConverter")
@RequestScoped
public class TipoMaquinariaConverter implements Converter{
    @EJB
    private TipoMaquinariaServicio tipoMaquinariaServicio;
    
    /**
     * Creates a new instance of CatalogoDetalleConverter
     */
    public TipoMaquinariaConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null) {
            try {
                return tipoMaquinariaServicio.findByPk(Long.parseLong(value));
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al convertir TipoMaquinaria a Objeto");
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((TipoMaquinaria) value).getCodigoTipoMaquinaria());
        }
        return null;
    }
}
