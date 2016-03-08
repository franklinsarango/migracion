/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gob.arcom.migracion.modelo;

import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Informe;
import ec.gob.arcom.migracion.modelo.Localidad;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "manifiesto_produccion", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "ManifiestoProduccion.findAll", query = "SELECT m FROM ManifiestoProduccion m")})
public class ManifiestoProduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_manifiesto")
    private Long codigoManifiesto;
    @Size(max = 100)
    @Column(name = "sector")
    private String sector;
    @Size(max = 13)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 255)
    @Column(name = "campo_reservado_05")
    private String campoReservado05;
    @Size(max = 255)
    @Column(name = "campo_reservado_04")
    private String campoReservado04;
    @Size(max = 255)
    @Column(name = "campo_reservado_03")
    private String campoReservado03;
    @Size(max = 255)
    @Column(name = "campo_reservado_02")
    private String campoReservado02;
    @Size(max = 255)
    @Column(name = "campo_reservado_01")
    private String campoReservado01;
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
    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoProvincia;
    @JoinColumn(name = "codigo_parroquia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoParroquia;
    @JoinColumn(name = "codigo_canton", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoCanton;
    @JoinColumn(name = "codigo_informe", referencedColumnName = "codigo_informe")
    @ManyToOne
    private Informe codigoInforme;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera codigoConcesion;
    @JoinColumn(name = "estado_manifiesto", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoManifiesto;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne
    private AreaMinera codigoArea;
    @OneToMany(mappedBy = "codigoManifiesto")
    private List<CoordenadaCota> coordenadaCotaList;

    public ManifiestoProduccion() {
    }

    public ManifiestoProduccion(Long codigoManifiesto) {
        this.codigoManifiesto = codigoManifiesto;
    }

    public Long getCodigoManifiesto() {
        return codigoManifiesto;
    }

    public void setCodigoManifiesto(Long codigoManifiesto) {
        this.codigoManifiesto = codigoManifiesto;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCampoReservado05() {
        return campoReservado05;
    }

    public void setCampoReservado05(String campoReservado05) {
        this.campoReservado05 = campoReservado05;
    }

    public String getCampoReservado04() {
        return campoReservado04;
    }

    public void setCampoReservado04(String campoReservado04) {
        this.campoReservado04 = campoReservado04;
    }

    public String getCampoReservado03() {
        return campoReservado03;
    }

    public void setCampoReservado03(String campoReservado03) {
        this.campoReservado03 = campoReservado03;
    }

    public String getCampoReservado02() {
        return campoReservado02;
    }

    public void setCampoReservado02(String campoReservado02) {
        this.campoReservado02 = campoReservado02;
    }

    public String getCampoReservado01() {
        return campoReservado01;
    }

    public void setCampoReservado01(String campoReservado01) {
        this.campoReservado01 = campoReservado01;
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

    public Localidad getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Localidad codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Localidad getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(Localidad codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public Localidad getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(Localidad codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public Informe getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(Informe codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public ConcesionMinera getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public CatalogoDetalle getEstadoManifiesto() {
        return estadoManifiesto;
    }

    public void setEstadoManifiesto(CatalogoDetalle estadoManifiesto) {
        this.estadoManifiesto = estadoManifiesto;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    public List<CoordenadaCota> getCoordenadaCotaList() {
        return coordenadaCotaList;
    }

    public void setCoordenadaCotaList(List<CoordenadaCota> coordenadaCotaList) {
        this.coordenadaCotaList = coordenadaCotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoManifiesto != null ? codigoManifiesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManifiestoProduccion)) {
            return false;
        }
        ManifiestoProduccion other = (ManifiestoProduccion) object;
        if ((this.codigoManifiesto == null && other.codigoManifiesto != null) || (this.codigoManifiesto != null && !this.codigoManifiesto.equals(other.codigoManifiesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.ctrl.ManifiestoProduccion[ codigoManifiesto=" + codigoManifiesto + " ]";
    }

}
