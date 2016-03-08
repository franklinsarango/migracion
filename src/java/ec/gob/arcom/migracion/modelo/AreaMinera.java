/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "area_minera", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "AreaMinera.findAll", query = "SELECT a FROM AreaMinera a")})
public class AreaMinera implements Serializable {
    @Column(name = "limite_frontera")
    private Boolean limiteFrontera;
    @JoinColumn(name = "codigo_sistema_referencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoSistemaReferencia;
    @OneToMany(mappedBy = "codigoArea")
    private List<RegistroPagoDetalle> registroPagoDetalleList;
    @OneToMany(mappedBy = "codigoArea")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @Column(name = "migrada")
    private Boolean migrada;
    @OneToMany(mappedBy = "codigoArea")
    private List<Instrumento> instrumentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_area_minera")
    private Long codigoAreaMinera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_area_minera")
    private String nombreAreaMinera;
    @Size(max = 20)
    @Column(name = "nemonico_area_minera")
    private String nemonicoAreaMinera;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie_area_minera")
    private BigDecimal superficieAreaMinera;
    @Size(max = 350)
    @Column(name = "sector_referencia")
    private String sectorReferencia;
    @Size(max = 20)
    @Column(name = "sistema_referencia")
    private String sistemaReferencia;
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
    @Column(name = "fecha_otorga")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorga;
    @Column(name = "fecha_inscribe")
    @Temporal(TemporalType.DATE)
    private Date fechaInscribe;
    @JoinColumn(name = "codigo_localidad", referencedColumnName = "codigo_localidad")
    @ManyToOne(optional = false)
    private Localidad codigoLocalidad;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne(optional = false)
    private ConcesionMinera codigoConcesion;
    @JoinColumn(name = "estado_area", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoArea")
    private List<CoordenadaArea> coordenadaAreaList;

    public AreaMinera() {
    }

    public AreaMinera(Long codigoAreaMinera) {
        this.codigoAreaMinera = codigoAreaMinera;
    }

    public AreaMinera(Long codigoAreaMinera, String nombreAreaMinera) {
        this.codigoAreaMinera = codigoAreaMinera;
        this.nombreAreaMinera = nombreAreaMinera;
    }

    public Long getCodigoAreaMinera() {
        return codigoAreaMinera;
    }

    public void setCodigoAreaMinera(Long codigoAreaMinera) {
        this.codigoAreaMinera = codigoAreaMinera;
    }

    public String getNombreAreaMinera() {
        return nombreAreaMinera;
    }

    public void setNombreAreaMinera(String nombreAreaMinera) {
        this.nombreAreaMinera = nombreAreaMinera;
    }

    public String getNemonicoAreaMinera() {
        return nemonicoAreaMinera;
    }

    public void setNemonicoAreaMinera(String nemonicoAreaMinera) {
        this.nemonicoAreaMinera = nemonicoAreaMinera;
    }

    public BigDecimal getSuperficieAreaMinera() {
        return superficieAreaMinera;
    }

    public void setSuperficieAreaMinera(BigDecimal superficieAreaMinera) {
        this.superficieAreaMinera = superficieAreaMinera;
    }

    public String getSectorReferencia() {
        return sectorReferencia;
    }

    public void setSectorReferencia(String sectorReferencia) {
        this.sectorReferencia = sectorReferencia;
    }

    public String getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(String sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
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

    public Date getFechaOtorga() {
        return fechaOtorga;
    }

    public void setFechaOtorga(Date fechaOtorga) {
        this.fechaOtorga = fechaOtorga;
    }

    public Date getFechaInscribe() {
        return fechaInscribe;
    }

    public void setFechaInscribe(Date fechaInscribe) {
        this.fechaInscribe = fechaInscribe;
    }

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public ConcesionMinera getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public CatalogoDetalle getEstadoArea() {
        return estadoArea;
    }

    public void setEstadoArea(CatalogoDetalle estadoArea) {
        this.estadoArea = estadoArea;
    }

    public List<CoordenadaArea> getCoordenadaAreaList() {
        return coordenadaAreaList;
    }

    public void setCoordenadaAreaList(List<CoordenadaArea> coordenadaAreaList) {
        this.coordenadaAreaList = coordenadaAreaList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAreaMinera != null ? codigoAreaMinera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaMinera)) {
            return false;
        }
        AreaMinera other = (AreaMinera) object;
        if ((this.codigoAreaMinera == null && other.codigoAreaMinera != null) || (this.codigoAreaMinera != null && !this.codigoAreaMinera.equals(other.codigoAreaMinera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AreaMinera{" + "codigoAreaMinera=" + codigoAreaMinera + ", nombreAreaMinera=" + nombreAreaMinera + ", nemonicoAreaMinera=" + nemonicoAreaMinera + ", superficieAreaMinera="
                + superficieAreaMinera + ", sectorReferencia=" + sectorReferencia + ", sistemaReferencia=" + sistemaReferencia + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion="
                + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", fechaOtorga="
                + fechaOtorga + ", fechaInscribe=" + fechaInscribe + ", codigoLocalidad=" + (codigoLocalidad != null ? codigoLocalidad.getCodigoLocalidad() : null)
                + ", codigoConcesion=" + (codigoConcesion != null ? codigoConcesion.getCodigoConcesion() : null) + ", estadoArea=" + (estadoArea != null ? estadoArea.getCodigoCatalogoDetalle() : null) + ", migrada=" + migrada + '}';
    }

    public Boolean getMigrada() {
        return migrada;
    }

    public void setMigrada(Boolean migrada) {
        this.migrada = migrada;
    }

    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public Boolean getLimiteFrontera() {
        return limiteFrontera;
    }

    public void setLimiteFrontera(Boolean limiteFrontera) {
        this.limiteFrontera = limiteFrontera;
    }

    public CatalogoDetalle getCodigoSistemaReferencia() {
        return codigoSistemaReferencia;
    }

    public void setCodigoSistemaReferencia(CatalogoDetalle codigoSistemaReferencia) {
        this.codigoSistemaReferencia = codigoSistemaReferencia;
    }

    public List<RegistroPagoDetalle> getRegistroPagoDetalleList() {
        return registroPagoDetalleList;
    }

    public void setRegistroPagoDetalleList(List<RegistroPagoDetalle> registroPagoDetalleList) {
        this.registroPagoDetalleList = registroPagoDetalleList;
    }

}
