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
public class UsuarioRolPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_usuario")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_rol")
    private long codigoRol;

    public UsuarioRolPK() {
    }

    public UsuarioRolPK(long codigoUsuario, long codigoRol) {
        this.codigoUsuario = codigoUsuario;
        this.codigoRol = codigoRol;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(long codigoRol) {
        this.codigoRol = codigoRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoUsuario;
        hash += (int) codigoRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolPK)) {
            return false;
        }
        UsuarioRolPK other = (UsuarioRolPK) object;
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        if (this.codigoRol != other.codigoRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.UsuarioRolPK[ codigoUsuario=" + codigoUsuario + ", codigoRol=" + codigoRol + " ]";
    }
    
}
