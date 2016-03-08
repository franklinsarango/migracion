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
import javax.persistence.CascadeType;
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
@Table(name = "tipo_mineria", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "TipoMineria.findAll", query = "SELECT t FROM TipoMineria t")})
public class TipoMineria implements Serializable {
    @OneToMany(mappedBy = "codigoTipoMineria")
    private List<ConceptoPago> conceptoPagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoMineria")
    private List<ConcesionMinera> concesionMineraList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_tipo_mineria")
    private Long codigoTipoMineria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nemonico_tipo_mineria")
    private String nemonicoTipoMineria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre_tipo_mineria")
    private String nombreTipoMineria;
    @Size(max = 200)
    @Column(name = "descripcion_tipo_mineria")
    private String descripcionTipoMineria;
    @Column(name = "es_tipo_derecho")
    private Boolean esTipoDerecho;
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

    public TipoMineria() {
    }

    public TipoMineria(Long codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    public TipoMineria(Long codigoTipoMineria, String nemonicoTipoMineria, String nombreTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
        this.nemonicoTipoMineria = nemonicoTipoMineria;
        this.nombreTipoMineria = nombreTipoMineria;
    }

    public Long getCodigoTipoMineria() {
        return codigoTipoMineria;
    }

    public void setCodigoTipoMineria(Long codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    public String getNemonicoTipoMineria() {
        return nemonicoTipoMineria;
    }

    public void setNemonicoTipoMineria(String nemonicoTipoMineria) {
        this.nemonicoTipoMineria = nemonicoTipoMineria;
    }

    public String getNombreTipoMineria() {
        return nombreTipoMineria;
    }

    public void setNombreTipoMineria(String nombreTipoMineria) {
        this.nombreTipoMineria = nombreTipoMineria;
    }

    public String getDescripcionTipoMineria() {
        return descripcionTipoMineria;
    }

    public void setDescripcionTipoMineria(String descripcionTipoMineria) {
        this.descripcionTipoMineria = descripcionTipoMineria;
    }

    public Boolean getEsTipoDerecho() {
        return esTipoDerecho;
    }

    public void setEsTipoDerecho(Boolean esTipoDerecho) {
        this.esTipoDerecho = esTipoDerecho;
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
        hash += (codigoTipoMineria != null ? codigoTipoMineria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMineria)) {
            return false;
        }
        TipoMineria other = (TipoMineria) object;
        if ((this.codigoTipoMineria == null && other.codigoTipoMineria != null) || (this.codigoTipoMineria != null && !this.codigoTipoMineria.equals(other.codigoTipoMineria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.TipoMineria[ codigoTipoMineria=" + codigoTipoMineria + " ]";
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }

    public List<ConceptoPago> getConceptoPagoList() {
        return conceptoPagoList;
    }

    public void setConceptoPagoList(List<ConceptoPago> conceptoPagoList) {
        this.conceptoPagoList = conceptoPagoList;
    }
    
}
