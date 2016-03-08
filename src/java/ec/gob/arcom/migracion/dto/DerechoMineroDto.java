/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import java.math.BigDecimal;

/**
 *
 * @author Javier Coronel
 */
public class DerechoMineroDto {
    
    private String codigo;
    private String nombreDerechoMinero;
    private String regional;
    private String provincia;
    private String fase;
    private String estado;
    private String tipoSolicitud;
    private String beneficiarioPrincipal;
    private String tipoPersona;
    private String fechaSolicitud;
    private String tipoDerechoMinero;
    private Long id;
    private BigDecimal valorPagoDerechoMinero;
    private Long codigoTipoSolicitud;
    private String numDocumentoRepLegal;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    public void setNombreDerechoMinero(String nombreDerechoMinero) {
        this.nombreDerechoMinero = nombreDerechoMinero;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getBeneficiarioPrincipal() {
        return beneficiarioPrincipal;
    }

    public void setBeneficiarioPrincipal(String beneficiarioPrincipal) {
        this.beneficiarioPrincipal = beneficiarioPrincipal;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTipoDerechoMinero() {
        return tipoDerechoMinero;
    }

    public void setTipoDerechoMinero(String tipoDerechoMinero) {
        this.tipoDerechoMinero = tipoDerechoMinero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorPagoDerechoMinero() {
        return valorPagoDerechoMinero;
    }

    public void setValorPagoDerechoMinero(BigDecimal valorPagoDerechoMinero) {
        this.valorPagoDerechoMinero = valorPagoDerechoMinero;
    }

    public Long getCodigoTipoSolicitud() {
        return codigoTipoSolicitud;
    }

    public void setCodigoTipoSolicitud(Long codigoTipoSolicitud) {
        this.codigoTipoSolicitud = codigoTipoSolicitud;
    }

    public String getNumDocumentoRepLegal() {
        return numDocumentoRepLegal;
    }

    public void setNumDocumentoRepLegal(String numDocumentoRepLegal) {
        this.numDocumentoRepLegal = numDocumentoRepLegal;
    }
    
}
