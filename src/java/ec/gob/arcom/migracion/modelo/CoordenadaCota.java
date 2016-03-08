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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "coordenada_cota", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "CoordenadaCota.findAll", query = "SELECT c FROM CoordenadaCota c")})
public class CoordenadaCota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_coordenada_cota")
    private Long codigoCoordenadaCota;
    @Column(name = "sistema_referencia")
    private BigInteger sistemaReferencia;
    @Column(name = "orden")
    private BigInteger orden;
    @Size(max = 30)
    @Column(name = "eje_x")
    private String ejeX;
    @Size(max = 30)
    @Column(name = "eje_y")
    private String ejeY;
    @Size(max = 30)
    @Column(name = "eje_z")
    private String ejeZ;
    @Size(max = 30)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 30)
    @Column(name = "longitud")
    private String longitud;
    @Size(max = 50)
    @Column(name = "utm_este")
    private String utmEste;
    @Size(max = 50)
    @Column(name = "utm_norte")
    private String utmNorte;
    @Size(max = 50)
    @Column(name = "distancia")
    private String distancia;
    @Column(name = "inicial")
    private Boolean inicial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cota_superior")
    private BigDecimal cotaSuperior;
    @Column(name = "cota_inferior")
    private BigDecimal cotaInferior;
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
    @JoinColumn(name = "codigo_manifiesto", referencedColumnName = "codigo_manifiesto")
    @ManyToOne
    private ManifiestoProduccion codigoManifiesto;
    @JoinColumn(name = "codigo_contrato_operacion", referencedColumnName = "codigo_contrato_operacion")
    @ManyToOne
    private ContratoOperacion codigoContratoOperacion;

    public CoordenadaCota() {
    }

    public CoordenadaCota(Long codigoCoordenadaCota) {
        this.codigoCoordenadaCota = codigoCoordenadaCota;
    }

    public Long getCodigoCoordenadaCota() {
        return codigoCoordenadaCota;
    }

    public void setCodigoCoordenadaCota(Long codigoCoordenadaCota) {
        this.codigoCoordenadaCota = codigoCoordenadaCota;
    }

    public BigInteger getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(BigInteger sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public String getEjeX() {
        return ejeX;
    }

    public void setEjeX(String ejeX) {
        this.ejeX = ejeX;
    }

    public String getEjeY() {
        return ejeY;
    }

    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }

    public String getEjeZ() {
        return ejeZ;
    }

    public void setEjeZ(String ejeZ) {
        this.ejeZ = ejeZ;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUtmEste() {
        return utmEste;
    }

    public void setUtmEste(String utmEste) {
        this.utmEste = utmEste;
    }

    public String getUtmNorte() {
        return utmNorte;
    }

    public void setUtmNorte(String utmNorte) {
        this.utmNorte = utmNorte;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Boolean getInicial() {
        return inicial;
    }

    public void setInicial(Boolean inicial) {
        this.inicial = inicial;
    }

    public BigDecimal getCotaSuperior() {
        return cotaSuperior;
    }

    public void setCotaSuperior(BigDecimal cotaSuperior) {
        this.cotaSuperior = cotaSuperior;
    }

    public BigDecimal getCotaInferior() {
        return cotaInferior;
    }

    public void setCotaInferior(BigDecimal cotaInferior) {
        this.cotaInferior = cotaInferior;
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

    public ManifiestoProduccion getCodigoManifiesto() {
        return codigoManifiesto;
    }

    public void setCodigoManifiesto(ManifiestoProduccion codigoManifiesto) {
        this.codigoManifiesto = codigoManifiesto;
    }

    public ContratoOperacion getCodigoContratoOperacion() {
        return codigoContratoOperacion;
    }

    public void setCodigoContratoOperacion(ContratoOperacion codigoContratoOperacion) {
        this.codigoContratoOperacion = codigoContratoOperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCoordenadaCota != null ? codigoCoordenadaCota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoordenadaCota)) {
            return false;
        }
        CoordenadaCota other = (CoordenadaCota) object;
        if ((this.codigoCoordenadaCota == null && other.codigoCoordenadaCota != null) || (this.codigoCoordenadaCota != null && !this.codigoCoordenadaCota.equals(other.codigoCoordenadaCota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.ctrl.CoordenadaCota[ codigoCoordenadaCota=" + codigoCoordenadaCota + " ]";
    }

}
