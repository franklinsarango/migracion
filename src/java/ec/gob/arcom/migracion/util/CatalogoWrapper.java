/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.util;

import ec.gob.arcom.migracion.modelo.CatalogoDetalle;

/**
 *
 * @author mejiaw
 */
public class CatalogoWrapper {
    private CatalogoDetalle catalogoDetalle;
    private boolean opcion;
    private Long cantidad;

    public CatalogoDetalle getCatalogoDetalle() {
        return catalogoDetalle;
    }

    public void setCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
        this.catalogoDetalle = catalogoDetalle;
    }

    public boolean isOpcion() {
        return opcion;
    }

    public void setOpcion(boolean opcion) {
        this.opcion = opcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
