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
@Table(name = "localidad", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l")})
public class Localidad implements Serializable {

    @OneToMany(mappedBy = "codigoCasilleroLocalidad")
    private List<ConcesionMinera> concesionMineraList;
    @OneToMany(mappedBy = "lugarEmisionPago")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localidad")
    private Collection<LocalidadRegional> localidadRegionalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoLocalidad")
    private List<AreaMinera> areaMineraList;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<PersonaJuridica> personaJuridicaList;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<PersonaNatural> personaNaturalList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_localidad")
    private Long codigoLocalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 8)
    @Column(name = "region")
    private String region;
    @Column(name = "localidad_padre")
    private BigInteger localidadPadre;
    @Size(max = 20)
    @Column(name = "nemonico")
    private String nemonico;
    @Size(max = 10)
    @Column(name = "codigo_internacional")
    private String codigoInternacional;
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
    @JoinColumn(name = "tipo_localidad", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle tipoLocalidad;
    @OneToMany(mappedBy = "codigoLocalidad")
    private Collection<Usuario> usuarioCollection;

    public Localidad() {
    }

    public Localidad(Long codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public Localidad(Long codigoLocalidad, String nombre, boolean estadoRegistro, Date fechaCreacion) {
        this.codigoLocalidad = codigoLocalidad;
        this.nombre = nombre;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Long codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigInteger getLocalidadPadre() {
        return localidadPadre;
    }

    public void setLocalidadPadre(BigInteger localidadPadre) {
        this.localidadPadre = localidadPadre;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public String getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(String codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
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

    public CatalogoDetalle getTipoLocalidad() {
        return tipoLocalidad;
    }

    public void setTipoLocalidad(CatalogoDetalle tipoLocalidad) {
        this.tipoLocalidad = tipoLocalidad;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLocalidad != null ? codigoLocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.codigoLocalidad == null && other.codigoLocalidad != null) || (this.codigoLocalidad != null && !this.codigoLocalidad.equals(other.codigoLocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Localidad[ codigoLocalidad=" + codigoLocalidad + " ]";
    }

    public List<AreaMinera> getAreaMineraList() {
        return areaMineraList;
    }

    public void setAreaMineraList(List<AreaMinera> areaMineraList) {
        this.areaMineraList = areaMineraList;
    }

    public List<PersonaJuridica> getPersonaJuridicaList() {
        return personaJuridicaList;
    }

    public void setPersonaJuridicaList(List<PersonaJuridica> personaJuridicaList) {
        this.personaJuridicaList = personaJuridicaList;
    }

    public List<PersonaNatural> getPersonaNaturalList() {
        return personaNaturalList;
    }

    public void setPersonaNaturalList(List<PersonaNatural> personaNaturalList) {
        this.personaNaturalList = personaNaturalList;
    }

    public Collection<LocalidadRegional> getLocalidadRegionalCollection() {
        return localidadRegionalCollection;
    }

    public void setLocalidadRegionalCollection(Collection<LocalidadRegional> localidadRegionalCollection) {
        this.localidadRegionalCollection = localidadRegionalCollection;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }

}
