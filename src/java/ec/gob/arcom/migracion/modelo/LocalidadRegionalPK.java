/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Javier Coronel
 */
@Embeddable
public class LocalidadRegionalPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_localidad")
    private long codigoLocalidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_regional")
    private long codigoRegional;

    public LocalidadRegionalPK() {
    }

    public LocalidadRegionalPK(long codigoLocalidad, long codigoRegional) {
        this.codigoLocalidad = codigoLocalidad;
        this.codigoRegional = codigoRegional;
    }

    public long getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(long codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public long getCodigoRegional() {
        return codigoRegional;
    }

    public void setCodigoRegional(long codigoRegional) {
        this.codigoRegional = codigoRegional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoLocalidad;
        hash += (int) codigoRegional;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalidadRegionalPK)) {
            return false;
        }
        LocalidadRegionalPK other = (LocalidadRegionalPK) object;
        if (this.codigoLocalidad != other.codigoLocalidad) {
            return false;
        }
        if (this.codigoRegional != other.codigoRegional) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.LocalidadRegionalPK[ codigoLocalidad=" + codigoLocalidad + ", codigoRegional=" + codigoRegional + " ]";
    }
    
}
