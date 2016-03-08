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
@Table(name = "catalogo", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c")})
public class Catalogo implements Serializable {
    @OneToMany(mappedBy = "codigoTipoMaterial")
    private List<ConcesionMinera> concesionMineraList;
    @Column(name = "catalogo_padre")
    private BigInteger catalogoPadre;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_catalogo")
    private Long codigoCatalogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nemonico")
    private String nemonico;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_registro")
    private boolean estadoRegistro;
    @Basic(optional = false)
    @NotNull
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCatalogo")
    private Collection<CatalogoDetalle> catalogoDetalleCollection;

    public Catalogo() {
    }

    public Catalogo(Long codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public Catalogo(Long codigoCatalogo, String nombre, String nemonico, boolean estadoRegistro, Date fechaCreacion) {
        this.codigoCatalogo = codigoCatalogo;
        this.nombre = nombre;
        this.nemonico = nemonico;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(Long codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
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

    public Collection<CatalogoDetalle> getCatalogoDetalleCollection() {
        return catalogoDetalleCollection;
    }

    public void setCatalogoDetalleCollection(Collection<CatalogoDetalle> catalogoDetalleCollection) {
        this.catalogoDetalleCollection = catalogoDetalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCatalogo != null ? codigoCatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.codigoCatalogo == null && other.codigoCatalogo != null) || (this.codigoCatalogo != null && !this.codigoCatalogo.equals(other.codigoCatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Catalogo[ codigoCatalogo=" + codigoCatalogo + " ]";
    }

    public BigInteger getCatalogoPadre() {
        return catalogoPadre;
    }

    public void setCatalogoPadre(BigInteger catalogoPadre) {
        this.catalogoPadre = catalogoPadre;
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }
    
}
