/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "localidad_regional", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "LocalidadRegional.findAll", query = "SELECT l FROM LocalidadRegional l")})
public class LocalidadRegional implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocalidadRegionalPK localidadRegionalPK;
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private BigInteger usuarioModificacion;
    @JoinColumn(name = "codigo_regional", referencedColumnName = "codigo_regional", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Regional regional;
    @JoinColumn(name = "codigo_localidad", referencedColumnName = "codigo_localidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Localidad localidad;

    public LocalidadRegional() {
    }

    public LocalidadRegional(LocalidadRegionalPK localidadRegionalPK) {
        this.localidadRegionalPK = localidadRegionalPK;
    }

    public LocalidadRegional(long codigoLocalidad, long codigoRegional) {
        this.localidadRegionalPK = new LocalidadRegionalPK(codigoLocalidad, codigoRegional);
    }

    public LocalidadRegionalPK getLocalidadRegionalPK() {
        return localidadRegionalPK;
    }

    public void setLocalidadRegionalPK(LocalidadRegionalPK localidadRegionalPK) {
        this.localidadRegionalPK = localidadRegionalPK;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(BigInteger usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localidadRegionalPK != null ? localidadRegionalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalidadRegional)) {
            return false;
        }
        LocalidadRegional other = (LocalidadRegional) object;
        if ((this.localidadRegionalPK == null && other.localidadRegionalPK != null) || (this.localidadRegionalPK != null && !this.localidadRegionalPK.equals(other.localidadRegionalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.LocalidadRegional[ localidadRegionalPK=" + localidadRegionalPK + " ]";
    }
    
}
