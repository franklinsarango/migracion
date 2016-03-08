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
@Table(name = "registro_pago_detalle", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "RegistroPagoDetalle.findAll", query = "SELECT r FROM RegistroPagoDetalle r")})
public class RegistroPagoDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_registro_pago_detalle")
    private Long codigoRegistroPagoDetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "numero_tramite")
    private BigInteger numeroTramite;
    @Size(max = 1000)
    @Column(name = "entidad_tramite")
    private String entidadTramite;
    @Size(max = 1000)
    @Column(name = "campo_reservado_05")
    private String campoReservado05;
    @Size(max = 1000)
    @Column(name = "campo_reservado_04")
    private String campoReservado04;
    @Size(max = 1000)
    @Column(name = "campo_reservado_03")
    private String campoReservado03;
    @Size(max = 1000)
    @Column(name = "campo_reservado_02")
    private String campoReservado02;
    @Size(max = 1000)
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
    @JoinColumn(name = "codigo_registro_pago", referencedColumnName = "codigo_registro")
    @ManyToOne
    private RegistroPagoObligaciones codigoRegistroPago;
    @JoinColumn(name = "codigo_planta_beneficio", referencedColumnName = "codigo_planta_beneficio")
    @ManyToOne
    private PlantaBeneficio codigoPlantaBeneficio;
    @JoinColumn(name = "codigo_licencia_comercializacion", referencedColumnName = "codigo_licencia_comercializacion")
    @ManyToOne
    private LicenciaComercializacion codigoLicenciaComercializacion;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera codigoConcesion;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne
    private AreaMinera codigoArea;

    public RegistroPagoDetalle() {
    }

    public RegistroPagoDetalle(Long codigoRegistroPagoDetalle) {
        this.codigoRegistroPagoDetalle = codigoRegistroPagoDetalle;
    }

    public Long getCodigoRegistroPagoDetalle() {
        return codigoRegistroPagoDetalle;
    }

    public void setCodigoRegistroPagoDetalle(Long codigoRegistroPagoDetalle) {
        this.codigoRegistroPagoDetalle = codigoRegistroPagoDetalle;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigInteger getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(BigInteger numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public String getEntidadTramite() {
        return entidadTramite;
    }

    public void setEntidadTramite(String entidadTramite) {
        this.entidadTramite = entidadTramite;
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

    public RegistroPagoObligaciones getCodigoRegistroPago() {
        return codigoRegistroPago;
    }

    public void setCodigoRegistroPago(RegistroPagoObligaciones codigoRegistroPago) {
        this.codigoRegistroPago = codigoRegistroPago;
    }

    public PlantaBeneficio getCodigoPlantaBeneficio() {
        return codigoPlantaBeneficio;
    }

    public void setCodigoPlantaBeneficio(PlantaBeneficio codigoPlantaBeneficio) {
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    public LicenciaComercializacion getCodigoLicenciaComercializacion() {
        return codigoLicenciaComercializacion;
    }

    public void setCodigoLicenciaComercializacion(LicenciaComercializacion codigoLicenciaComercializacion) {
        this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
    }

    public ConcesionMinera getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRegistroPagoDetalle != null ? codigoRegistroPagoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPagoDetalle)) {
            return false;
        }
        RegistroPagoDetalle other = (RegistroPagoDetalle) object;
        if ((this.codigoRegistroPagoDetalle == null && other.codigoRegistroPagoDetalle != null) || (this.codigoRegistroPagoDetalle != null && !this.codigoRegistroPagoDetalle.equals(other.codigoRegistroPagoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.RegistroPagoDetalle[ codigoRegistroPagoDetalle=" + codigoRegistroPagoDetalle + " ]";
    }
    
}
