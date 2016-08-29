/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Javier Coronel
 */
public class RegistroPagoObligacionesDto {
    
    private Long codigoRegistro;
    private String tipoPago;
    private String periodo;
    private String documentoPersonaPago;
    private String personaPago;
    private Date fechaRevisionAnalistaEconomico;
    private String comprobanteElectronico;
    private BigDecimal valorPagadoUsuario;
    private BigDecimal valorCalculadoArcom;
    private Date fechaEmisionPago;
    private String codigoArcom;
    private String numeroTramite;
    private String entidadTramite;
    private String estadoPago;

    /**
     * @return the codigoRegistro
     */
    public Long getCodigoRegistro() {
        return codigoRegistro;
    }

    /**
     * @param codigoRegistro the codigoRegistro to set
     */
    public void setCodigoRegistro(Long codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the documentoPersonaPago
     */
    public String getDocumentoPersonaPago() {
        return documentoPersonaPago;
    }

    /**
     * @param documentoPersonaPago the documentoPersonaPago to set
     */
    public void setDocumentoPersonaPago(String documentoPersonaPago) {
        this.documentoPersonaPago = documentoPersonaPago;
    }

    /**
     * @return the personaPago
     */
    public String getPersonaPago() {
        return personaPago;
    }

    /**
     * @param personaPago the personaPago to set
     */
    public void setPersonaPago(String personaPago) {
        this.personaPago = personaPago;
    }

    /**
     * @return the fechaRevisionAnalistaEconomico
     */
    public Date getFechaRevisionAnalistaEconomico() {
        return fechaRevisionAnalistaEconomico;
    }

    /**
     * @param fechaRevisionAnalistaEconomico the fechaRevisionAnalistaEconomico to set
     */
    public void setFechaRevisionAnalistaEconomico(Date fechaRevisionAnalistaEconomico) {
        this.fechaRevisionAnalistaEconomico = fechaRevisionAnalistaEconomico;
    }

    /**
     * @return the comprobanteElectronico
     */
    public String getComprobanteElectronico() {
        return comprobanteElectronico;
    }

    /**
     * @param comprobanteElectronico the comprobanteElectronico to set
     */
    public void setComprobanteElectronico(String comprobanteElectronico) {
        this.comprobanteElectronico = comprobanteElectronico;
    }

    /**
     * @return the valorPagadoUsuario
     */
    public BigDecimal getValorPagadoUsuario() {
        return valorPagadoUsuario;
    }

    /**
     * @param valorPagadoUsuario the valorPagadoUsuario to set
     */
    public void setValorPagadoUsuario(BigDecimal valorPagadoUsuario) {
        this.valorPagadoUsuario = valorPagadoUsuario;
    }

    /**
     * @return the valorCalculadoArcom
     */
    public BigDecimal getValorCalculadoArcom() {
        return valorCalculadoArcom;
    }

    /**
     * @param valorCalculadoArcom the valorCalculadoArcom to set
     */
    public void setValorCalculadoArcom(BigDecimal valorCalculadoArcom) {
        this.valorCalculadoArcom = valorCalculadoArcom;
    }

    /**
     * @return the fechaEmisionPago
     */
    public Date getFechaEmisionPago() {
        return fechaEmisionPago;
    }

    /**
     * @param fechaEmisionPago the fechaEmisionPago to set
     */
    public void setFechaEmisionPago(Date fechaEmisionPago) {
        this.fechaEmisionPago = fechaEmisionPago;
    }

    /**
     * @return the codigoArcom
     */
    public String getCodigoArcom() {
        return codigoArcom;
    }

    /**
     * @param codigoArcom the codigoArcom to set
     */
    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    /**
     * @return the numeroTramite
     */
    public String getNumeroTramite() {
        return numeroTramite;
    }

    /**
     * @param numeroTramite the numeroTramite to set
     */
    public void setNumeroTramite(String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    /**
     * @return the entidadTramite
     */
    public String getEntidadTramite() {
        return entidadTramite;
    }

    /**
     * @param entidadTramite the entidadTramite to set
     */
    public void setEntidadTramite(String entidadTramite) {
        this.entidadTramite = entidadTramite;
    }

    /**
     * @return the estadoPago
     */
    public String getEstadoPago() {
        return estadoPago;
    }

    /**
     * @param estadoPago the estadoPago to set
     */
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
