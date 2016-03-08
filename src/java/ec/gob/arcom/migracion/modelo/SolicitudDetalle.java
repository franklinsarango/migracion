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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "solicitud_detalle", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "SolicitudDetalle.findAll", query = "SELECT s FROM SolicitudDetalle s")})
public class SolicitudDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_solicitud_detalle")
    private Long codigoSolicitudDetalle;
    @Column(name = "secuencia_solicitud")
    private BigInteger secuenciaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_detalle")
    private String tipoDetalle;
    @Column(name = "numero_coordenada")
    private BigInteger numeroCoordenada;
    @Size(max = 50)
    @Column(name = "coordenada_utm_este")
    private String coordenadaUtmEste;
    @Size(max = 50)
    @Column(name = "coordenada_utm_norte")
    private String coordenadaUtmNorte;
    @Size(max = 50)
    @Column(name = "coordenada_distancia")
    private String coordenadaDistancia;
    @Column(name = "coordenada_inicial")
    private Boolean coordenadaInicial;
    @Column(name = "numero_asociado")
    private BigInteger numeroAsociado;
    @Size(max = 20)
    @Column(name = "numero_documento_asociado")
    private String numeroDocumentoAsociado;
    @Size(max = 80)
    @Column(name = "nombre_asociado")
    private String nombreAsociado;
    @Size(max = 80)
    @Column(name = "apellido_asociado")
    private String apellidoAsociado;
    @Size(max = 10)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "numero_maquinaria")
    private BigInteger numeroMaquinaria;
    @Size(max = 200)
    @Column(name = "tipo_maquinaria")
    private String tipoMaquinaria;
    @Size(max = 80)
    @Column(name = "modelo_maquinaria")
    private String modeloMaquinaria;
    @Size(max = 80)
    @Column(name = "numero_serie_maquinaria")
    private String numeroSerieMaquinaria;
    @Size(max = 80)
    @Column(name = "motor_maquinaria")
    private String motorMaquinaria;
    @Size(max = 80)
    @Column(name = "matricula_maquinaria")
    private String matriculaMaquinaria;
    @Size(max = 80)
    @Column(name = "placa_maquinaria")
    private String placaMaquinaria;
    @Size(max = 80)
    @Column(name = "capacidad_maquinaria")
    private String capacidadMaquinaria;
    @Size(max = 80)
    @Column(name = "numero_chip")
    private String numeroChip;
    @Column(name = "numero_infraestructura")
    private BigInteger numeroInfraestructura;
    @Size(max = 80)
    @Column(name = "descripcion_infraestructura")
    private String descripcionInfraestructura;
    @Size(max = 80)
    @Column(name = "valor_infraestructura")
    private String valorInfraestructura;
    @Size(max = 255)
    @Column(name = "campo_reservado_10")
    private String campoReservado10;
    @Size(max = 255)
    @Column(name = "campo_reservado_09")
    private String campoReservado09;
    @Size(max = 255)
    @Column(name = "campo_reservado_08")
    private String campoReservado08;
    @Size(max = 255)
    @Column(name = "campo_reservado_07")
    private String campoReservado07;
    @Size(max = 255)
    @Column(name = "campo_reservado_06")
    private String campoReservado06;
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
    @Size(max = 10)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 10)
    @Column(name = "canton")
    private String canton;
    @Size(max = 10)
    @Column(name = "parroquia")
    private String parroquia;
    @Size(max = 100)
    @Column(name = "codigo_concesion")
    private String codigoConcesion;
    @Size(max = 100)
    @Column(name = "codigo_planta_beneficio")
    private String codigoPlantaBeneficio;
    @Size(max = 100)
    @Column(name = "codigo_licencia_comercializacion")
    private String codigoLicenciaComercializacion;
    @JoinColumn(name = "codigo_solicitud", referencedColumnName = "codigo_solicitud")
    @ManyToOne(optional = false)
    private Solicitud codigoSolicitud;
    @Column(name = "migrada")
    private boolean migrada;

    public SolicitudDetalle() {
    }

    public SolicitudDetalle(Long codigoSolicitudDetalle) {
        this.codigoSolicitudDetalle = codigoSolicitudDetalle;
    }

    public SolicitudDetalle(Long codigoSolicitudDetalle, String tipoDetalle) {
        this.codigoSolicitudDetalle = codigoSolicitudDetalle;
        this.tipoDetalle = tipoDetalle;
    }

    public Long getCodigoSolicitudDetalle() {
        return codigoSolicitudDetalle;
    }

    public void setCodigoSolicitudDetalle(Long codigoSolicitudDetalle) {
        this.codigoSolicitudDetalle = codigoSolicitudDetalle;
    }

    public BigInteger getSecuenciaSolicitud() {
        return secuenciaSolicitud;
    }

    public void setSecuenciaSolicitud(BigInteger secuenciaSolicitud) {
        this.secuenciaSolicitud = secuenciaSolicitud;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public BigInteger getNumeroCoordenada() {
        return numeroCoordenada;
    }

    public void setNumeroCoordenada(BigInteger numeroCoordenada) {
        this.numeroCoordenada = numeroCoordenada;
    }

    public String getCoordenadaUtmEste() {
        return coordenadaUtmEste;
    }

    public void setCoordenadaUtmEste(String coordenadaUtmEste) {
        this.coordenadaUtmEste = coordenadaUtmEste;
    }

    public String getCoordenadaUtmNorte() {
        return coordenadaUtmNorte;
    }

    public void setCoordenadaUtmNorte(String coordenadaUtmNorte) {
        this.coordenadaUtmNorte = coordenadaUtmNorte;
    }

    public String getCoordenadaDistancia() {
        return coordenadaDistancia;
    }

    public void setCoordenadaDistancia(String coordenadaDistancia) {
        this.coordenadaDistancia = coordenadaDistancia;
    }

    public Boolean getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public void setCoordenadaInicial(Boolean coordenadaInicial) {
        this.coordenadaInicial = coordenadaInicial;
    }

    public BigInteger getNumeroAsociado() {
        return numeroAsociado;
    }

    public void setNumeroAsociado(BigInteger numeroAsociado) {
        this.numeroAsociado = numeroAsociado;
    }

    public String getNumeroDocumentoAsociado() {
        return numeroDocumentoAsociado;
    }

    public void setNumeroDocumentoAsociado(String numeroDocumentoAsociado) {
        this.numeroDocumentoAsociado = numeroDocumentoAsociado;
    }

    public String getNombreAsociado() {
        return nombreAsociado;
    }

    public void setNombreAsociado(String nombreAsociado) {
        this.nombreAsociado = nombreAsociado;
    }

    public String getApellidoAsociado() {
        return apellidoAsociado;
    }

    public void setApellidoAsociado(String apellidoAsociado) {
        this.apellidoAsociado = apellidoAsociado;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public BigInteger getNumeroMaquinaria() {
        return numeroMaquinaria;
    }

    public void setNumeroMaquinaria(BigInteger numeroMaquinaria) {
        this.numeroMaquinaria = numeroMaquinaria;
    }

    public String getTipoMaquinaria() {
        return tipoMaquinaria;
    }

    public void setTipoMaquinaria(String tipoMaquinaria) {
        this.tipoMaquinaria = tipoMaquinaria;
    }

    public String getModeloMaquinaria() {
        return modeloMaquinaria;
    }

    public void setModeloMaquinaria(String modeloMaquinaria) {
        this.modeloMaquinaria = modeloMaquinaria;
    }

    public String getNumeroSerieMaquinaria() {
        return numeroSerieMaquinaria;
    }

    public void setNumeroSerieMaquinaria(String numeroSerieMaquinaria) {
        this.numeroSerieMaquinaria = numeroSerieMaquinaria;
    }

    public String getMotorMaquinaria() {
        return motorMaquinaria;
    }

    public void setMotorMaquinaria(String motorMaquinaria) {
        this.motorMaquinaria = motorMaquinaria;
    }

    public String getMatriculaMaquinaria() {
        return matriculaMaquinaria;
    }

    public void setMatriculaMaquinaria(String matriculaMaquinaria) {
        this.matriculaMaquinaria = matriculaMaquinaria;
    }

    public String getPlacaMaquinaria() {
        return placaMaquinaria;
    }

    public void setPlacaMaquinaria(String placaMaquinaria) {
        this.placaMaquinaria = placaMaquinaria;
    }

    public String getCapacidadMaquinaria() {
        return capacidadMaquinaria;
    }

    public void setCapacidadMaquinaria(String capacidadMaquinaria) {
        this.capacidadMaquinaria = capacidadMaquinaria;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }

    public BigInteger getNumeroInfraestructura() {
        return numeroInfraestructura;
    }

    public void setNumeroInfraestructura(BigInteger numeroInfraestructura) {
        this.numeroInfraestructura = numeroInfraestructura;
    }

    public String getDescripcionInfraestructura() {
        return descripcionInfraestructura;
    }

    public void setDescripcionInfraestructura(String descripcionInfraestructura) {
        this.descripcionInfraestructura = descripcionInfraestructura;
    }

    public String getValorInfraestructura() {
        return valorInfraestructura;
    }

    public void setValorInfraestructura(String valorInfraestructura) {
        this.valorInfraestructura = valorInfraestructura;
    }

    public String getCampoReservado10() {
        return campoReservado10;
    }

    public void setCampoReservado10(String campoReservado10) {
        this.campoReservado10 = campoReservado10;
    }

    public String getCampoReservado09() {
        return campoReservado09;
    }

    public void setCampoReservado09(String campoReservado09) {
        this.campoReservado09 = campoReservado09;
    }

    public String getCampoReservado08() {
        return campoReservado08;
    }

    public void setCampoReservado08(String campoReservado08) {
        this.campoReservado08 = campoReservado08;
    }

    public String getCampoReservado07() {
        return campoReservado07;
    }

    public void setCampoReservado07(String campoReservado07) {
        this.campoReservado07 = campoReservado07;
    }

    public String getCampoReservado06() {
        return campoReservado06;
    }

    public void setCampoReservado06(String campoReservado06) {
        this.campoReservado06 = campoReservado06;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(String codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public String getCodigoPlantaBeneficio() {
        return codigoPlantaBeneficio;
    }

    public void setCodigoPlantaBeneficio(String codigoPlantaBeneficio) {
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    public String getCodigoLicenciaComercializacion() {
        return codigoLicenciaComercializacion;
    }

    public void setCodigoLicenciaComercializacion(String codigoLicenciaComercializacion) {
        this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
    }

    public Solicitud getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(Solicitud codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
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
        hash += (codigoSolicitudDetalle != null ? codigoSolicitudDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudDetalle)) {
            return false;
        }
        SolicitudDetalle other = (SolicitudDetalle) object;
        if ((this.codigoSolicitudDetalle == null && other.codigoSolicitudDetalle != null) || (this.codigoSolicitudDetalle != null && !this.codigoSolicitudDetalle.equals(other.codigoSolicitudDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SolicitudDetalle{" + "codigoSolicitudDetalle=" + codigoSolicitudDetalle + ", secuenciaSolicitud=" + secuenciaSolicitud + ", tipoDetalle=" + tipoDetalle + ", numeroCoordenada=" 
                + numeroCoordenada + ", coordenadaUtmEste=" + coordenadaUtmEste + ", coordenadaUtmNorte=" + coordenadaUtmNorte + ", coordenadaDistancia=" + coordenadaDistancia + ", coordenadaInicial=" 
                + coordenadaInicial + ", numeroAsociado=" + numeroAsociado + ", numeroDocumentoAsociado=" + numeroDocumentoAsociado + ", nombreAsociado=" + nombreAsociado + ", apellidoAsociado=" + apellidoAsociado 
                + ", tipoDocumento=" + tipoDocumento + ", numeroMaquinaria=" + numeroMaquinaria + ", tipoMaquinaria=" + tipoMaquinaria + ", modeloMaquinaria=" + modeloMaquinaria + ", numeroSerieMaquinaria=" 
                + numeroSerieMaquinaria + ", motorMaquinaria=" + motorMaquinaria + ", matriculaMaquinaria=" + matriculaMaquinaria + ", placaMaquinaria=" + placaMaquinaria + ", capacidadMaquinaria=" 
                + capacidadMaquinaria + ", numeroChip=" + numeroChip + ", numeroInfraestructura=" + numeroInfraestructura + ", descripcionInfraestructura=" + descripcionInfraestructura + ", valorInfraestructura=" 
                + valorInfraestructura + ", campoReservado10=" + campoReservado10 + ", campoReservado09=" + campoReservado09 + ", campoReservado08=" + campoReservado08 + ", campoReservado07=" + campoReservado07 
                + ", campoReservado06=" + campoReservado06 + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" 
                + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" 
                + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", provincia=" + provincia + ", canton=" + canton + ", parroquia=" + parroquia + ", codigoConcesion=" + codigoConcesion 
                + ", codigoPlantaBeneficio=" + codigoPlantaBeneficio + ", codigoLicenciaComercializacion=" + codigoLicenciaComercializacion + ", codigoSolicitud=" + codigoSolicitud + ", migrada=" + migrada + '}';
    }

}
