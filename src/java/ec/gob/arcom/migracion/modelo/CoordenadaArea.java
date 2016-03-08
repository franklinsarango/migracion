/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
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
@Table(name = "coordenada_area", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "CoordenadaArea.findAll", query = "SELECT c FROM CoordenadaArea c")})
public class CoordenadaArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_coordenada")
    private Long codigoCoordenada;
    @Column(name = "numero_coordenada")
    private BigInteger numeroCoordenada;
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
    @JoinColumn(name = "sistema_referencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle sistemaReferencia;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne(optional = false)
    private AreaMinera codigoArea;
    @Column(name = "migrada")
    private boolean migrada;

    public CoordenadaArea() {
    }

    public CoordenadaArea(Long codigoCoordenada) {
        this.codigoCoordenada = codigoCoordenada;
    }

    public Long getCodigoCoordenada() {
        return codigoCoordenada;
    }

    public void setCodigoCoordenada(Long codigoCoordenada) {
        this.codigoCoordenada = codigoCoordenada;
    }

    public BigInteger getNumeroCoordenada() {
        return numeroCoordenada;
    }

    public void setNumeroCoordenada(BigInteger numeroCoordenada) {
        this.numeroCoordenada = numeroCoordenada;
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

    public CatalogoDetalle getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(CatalogoDetalle sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    public boolean isMigrada() {
        return migrada;
    }

    public void setMigrada(boolean migrada) {
        this.migrada = migrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCoordenada != null ? codigoCoordenada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoordenadaArea)) {
            return false;
        }
        CoordenadaArea other = (CoordenadaArea) object;
        if ((this.codigoCoordenada == null && other.codigoCoordenada != null) || (this.codigoCoordenada != null && !this.codigoCoordenada.equals(other.codigoCoordenada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CoordenadaArea{" + "codigoCoordenada=" + codigoCoordenada + ", numeroCoordenada=" + numeroCoordenada + ", ejeX=" + ejeX + ", ejeY=" + ejeY + ", ejeZ=" + ejeZ + ", latitud="
                + latitud + ", longitud=" + longitud + ", utmEste=" + utmEste + ", utmNorte=" + utmNorte + ", distancia=" + distancia + ", inicial=" + inicial + ", estadoRegistro=" + estadoRegistro
                + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion
                + ", sistemaReferencia=" + (sistemaReferencia != null ? sistemaReferencia.getCodigoCatalogoDetalle() : null) + ", codigoArea=" + (codigoArea != null ? codigoArea.getCodigoAreaMinera() : null) + '}';
    }

}
