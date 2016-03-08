/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tipo_maquinaria", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "TipoMaquinaria.findAll", query = "SELECT t FROM TipoMaquinaria t")})
public class TipoMaquinaria implements Serializable {
    @OneToMany(mappedBy = "codigoTipoMaquinaria")
    private Collection<MaquinariaConcesion> maquinariaConcesionCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_tipo_maquinaria")
    private Long codigoTipoMaquinaria;
    @Size(max = 15)
    @Column(name = "nemonico")
    private String nemonico;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "forma_explotacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle formaExplotacion;
    @JoinColumn(name = "tipo_modalidad", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoModalidad;
    @JoinColumn(name = "tipo_material", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoMaterial;

    public TipoMaquinaria() {
    }

    public TipoMaquinaria(Long codigoTipoMaquinaria) {
        this.codigoTipoMaquinaria = codigoTipoMaquinaria;
    }

    public Long getCodigoTipoMaquinaria() {
        return codigoTipoMaquinaria;
    }

    public void setCodigoTipoMaquinaria(Long codigoTipoMaquinaria) {
        this.codigoTipoMaquinaria = codigoTipoMaquinaria;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public CatalogoDetalle getFormaExplotacion() {
        return formaExplotacion;
    }

    public void setFormaExplotacion(CatalogoDetalle formaExplotacion) {
        this.formaExplotacion = formaExplotacion;
    }

    public CatalogoDetalle getTipoModalidad() {
        return tipoModalidad;
    }

    public void setTipoModalidad(CatalogoDetalle tipoModalidad) {
        this.tipoModalidad = tipoModalidad;
    }

    public CatalogoDetalle getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(CatalogoDetalle tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoMaquinaria != null ? codigoTipoMaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaquinaria)) {
            return false;
        }
        TipoMaquinaria other = (TipoMaquinaria) object;
        if ((this.codigoTipoMaquinaria == null && other.codigoTipoMaquinaria != null) || (this.codigoTipoMaquinaria != null && !this.codigoTipoMaquinaria.equals(other.codigoTipoMaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.TipoMaquinaria[ codigoTipoMaquinaria=" + codigoTipoMaquinaria + " ]";
    }

    public Collection<MaquinariaConcesion> getMaquinariaConcesionCollection() {
        return maquinariaConcesionCollection;
    }

    public void setMaquinariaConcesionCollection(Collection<MaquinariaConcesion> maquinariaConcesionCollection) {
        this.maquinariaConcesionCollection = maquinariaConcesionCollection;
    }
    
}
