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
 * @author Usuario
 */
@Entity
@Table(name = "concesion_planta_beneficio", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "ConcesionPlantaBeneficio.findAll", query = "SELECT c FROM ConcesionPlantaBeneficio c")})
public class ConcesionPlantaBeneficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConcesionPlantaBeneficioPK concesionPlantaBeneficioPK;
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
    @JoinColumn(name = "codigo_planta_beneficio", referencedColumnName = "codigo_planta_beneficio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlantaBeneficio plantaBeneficio;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConcesionMinera concesionMinera;

    public ConcesionPlantaBeneficio() {
    }

    public ConcesionPlantaBeneficio(ConcesionPlantaBeneficioPK concesionPlantaBeneficioPK) {
        this.concesionPlantaBeneficioPK = concesionPlantaBeneficioPK;
    }

    public ConcesionPlantaBeneficio(long codigoConcesion, long codigoPlantaBeneficio) {
        this.concesionPlantaBeneficioPK = new ConcesionPlantaBeneficioPK(codigoConcesion, codigoPlantaBeneficio);
    }

    public ConcesionPlantaBeneficioPK getConcesionPlantaBeneficioPK() {
        return concesionPlantaBeneficioPK;
    }

    public void setConcesionPlantaBeneficioPK(ConcesionPlantaBeneficioPK concesionPlantaBeneficioPK) {
        this.concesionPlantaBeneficioPK = concesionPlantaBeneficioPK;
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

    public PlantaBeneficio getPlantaBeneficio() {
        return plantaBeneficio;
    }

    public void setPlantaBeneficio(PlantaBeneficio plantaBeneficio) {
        this.plantaBeneficio = plantaBeneficio;
    }

    public ConcesionMinera getConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (concesionPlantaBeneficioPK != null ? concesionPlantaBeneficioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConcesionPlantaBeneficio)) {
            return false;
        }
        ConcesionPlantaBeneficio other = (ConcesionPlantaBeneficio) object;
        if ((this.concesionPlantaBeneficioPK == null && other.concesionPlantaBeneficioPK != null) || (this.concesionPlantaBeneficioPK != null && !this.concesionPlantaBeneficioPK.equals(other.concesionPlantaBeneficioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio[ concesionPlantaBeneficioPK=" + concesionPlantaBeneficioPK + " ]";
    }
    
}
