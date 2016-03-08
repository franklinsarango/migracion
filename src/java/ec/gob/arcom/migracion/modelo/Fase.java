/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "fase", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Fase.findAll", query = "SELECT f FROM Fase f")})
public class Fase implements Serializable {
    @OneToMany(mappedBy = "codigoFase")
    private List<ConcesionMinera> concesionMineraList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_fase")
    private Long codigoFase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nemonico_fase")
    private String nemonicoFase;
    @Size(max = 100)
    @Column(name = "nombre_fase")
    private String nombreFase;
    @Size(max = 150)
    @Column(name = "descripcion_fase")
    private String descripcionFase;
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

    public Fase() {
    }

    public Fase(Long codigoFase) {
        this.codigoFase = codigoFase;
    }

    public Fase(Long codigoFase, String nemonicoFase) {
        this.codigoFase = codigoFase;
        this.nemonicoFase = nemonicoFase;
    }

    public Long getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(Long codigoFase) {
        this.codigoFase = codigoFase;
    }

    public String getNemonicoFase() {
        return nemonicoFase;
    }

    public void setNemonicoFase(String nemonicoFase) {
        this.nemonicoFase = nemonicoFase;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getDescripcionFase() {
        return descripcionFase;
    }

    public void setDescripcionFase(String descripcionFase) {
        this.descripcionFase = descripcionFase;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFase != null ? codigoFase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fase)) {
            return false;
        }
        Fase other = (Fase) object;
        if ((this.codigoFase == null && other.codigoFase != null) || (this.codigoFase != null && !this.codigoFase.equals(other.codigoFase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Fase[ codigoFase=" + codigoFase + " ]";
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }
    
}
