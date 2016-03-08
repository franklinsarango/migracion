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
@Table(name = "regional", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Regional.findAll", query = "SELECT r FROM Regional r")})
public class Regional implements Serializable {
    @OneToMany(mappedBy = "codigoRegional")
    private List<UsuarioRol> usuarioRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regional")
    private Collection<LocalidadRegional> localidadRegionalCollection;
    @OneToMany(mappedBy = "codigoRegional")
    private List<ConcesionMinera> concesionMineraList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_regional")
    private Long codigoRegional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nemonico_regional")
    private String nemonicoRegional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre_regional")
    private String nombreRegional;
    @Size(max = 80)
    @Column(name = "descripcion_regional")
    private String descripcionRegional;
    @Size(max = 50)
    @Column(name = "ciudad_regional")
    private String ciudadRegional;
    @Size(max = 150)
    @Column(name = "direccion_regional")
    private String direccionRegional;
    @Column(name = "zona_arcom")
    private BigInteger zonaArcom;
    @Size(max = 10)
    @Column(name = "prefijo_codigo")
    private String prefijoCodigo;
    @Size(max = 10)
    @Column(name = "realm")
    private String realm;
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

    public Regional() {
    }

    public Regional(Long codigoRegional) {
        this.codigoRegional = codigoRegional;
    }

    public Regional(Long codigoRegional, String nemonicoRegional, String nombreRegional) {
        this.codigoRegional = codigoRegional;
        this.nemonicoRegional = nemonicoRegional;
        this.nombreRegional = nombreRegional;
    }

    public Long getCodigoRegional() {
        return codigoRegional;
    }

    public void setCodigoRegional(Long codigoRegional) {
        this.codigoRegional = codigoRegional;
    }

    public String getNemonicoRegional() {
        return nemonicoRegional;
    }

    public void setNemonicoRegional(String nemonicoRegional) {
        this.nemonicoRegional = nemonicoRegional;
    }

    public String getNombreRegional() {
        return nombreRegional;
    }

    public void setNombreRegional(String nombreRegional) {
        this.nombreRegional = nombreRegional;
    }

    public String getDescripcionRegional() {
        return descripcionRegional;
    }

    public void setDescripcionRegional(String descripcionRegional) {
        this.descripcionRegional = descripcionRegional;
    }

    public String getCiudadRegional() {
        return ciudadRegional;
    }

    public void setCiudadRegional(String ciudadRegional) {
        this.ciudadRegional = ciudadRegional;
    }

    public String getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(String direccionRegional) {
        this.direccionRegional = direccionRegional;
    }

    public BigInteger getZonaArcom() {
        return zonaArcom;
    }

    public void setZonaArcom(BigInteger zonaArcom) {
        this.zonaArcom = zonaArcom;
    }

    public String getPrefijoCodigo() {
        return prefijoCodigo;
    }

    public void setPrefijoCodigo(String prefijoCodigo) {
        this.prefijoCodigo = prefijoCodigo;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
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
        hash += (codigoRegional != null ? codigoRegional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regional)) {
            return false;
        }
        Regional other = (Regional) object;
        if ((this.codigoRegional == null && other.codigoRegional != null) || (this.codigoRegional != null && !this.codigoRegional.equals(other.codigoRegional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Regional[ codigoRegional=" + codigoRegional + " ]";
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }

    public Collection<LocalidadRegional> getLocalidadRegionalCollection() {
        return localidadRegionalCollection;
    }

    public void setLocalidadRegionalCollection(Collection<LocalidadRegional> localidadRegionalCollection) {
        this.localidadRegionalCollection = localidadRegionalCollection;
    }

    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }
    
}
