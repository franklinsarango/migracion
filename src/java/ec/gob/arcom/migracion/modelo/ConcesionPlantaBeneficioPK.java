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
 * @author Usuario
 */
@Embeddable
public class ConcesionPlantaBeneficioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_concesion")
    private long codigoConcesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_planta_beneficio")
    private long codigoPlantaBeneficio;

    public ConcesionPlantaBeneficioPK() {
    }

    public ConcesionPlantaBeneficioPK(long codigoConcesion, long codigoPlantaBeneficio) {
        this.codigoConcesion = codigoConcesion;
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    public long getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(long codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public long getCodigoPlantaBeneficio() {
        return codigoPlantaBeneficio;
    }

    public void setCodigoPlantaBeneficio(long codigoPlantaBeneficio) {
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoConcesion;
        hash += (int) codigoPlantaBeneficio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConcesionPlantaBeneficioPK)) {
            return false;
        }
        ConcesionPlantaBeneficioPK other = (ConcesionPlantaBeneficioPK) object;
        if (this.codigoConcesion != other.codigoConcesion) {
            return false;
        }
        if (this.codigoPlantaBeneficio != other.codigoPlantaBeneficio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK[ codigoConcesion=" + codigoConcesion + ", codigoPlantaBeneficio=" + codigoPlantaBeneficio + " ]";
    }
    
}
