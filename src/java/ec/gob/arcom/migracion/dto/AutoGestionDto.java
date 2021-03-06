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
 * @author CuencaL
 */
public class AutoGestionDto {
    private Date fechaCreacion;
    private Date fechaDeposito;
    private String numeroComprobanteBanco;
    private Long codigoConceptoPago;
    private String estadoPago;
    private BigDecimal valorPagadoUsuario;
    private String numeroComprobanteArcom;    
    private String entidadTramite;
    private String numeroTramite;
    private Date fechaEmisionPago;
    private Long codigoRegistro;
    private boolean editar1;
    private boolean editar2;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public BigDecimal getValorPagadoUsuario() {
        return valorPagadoUsuario;
    }

    public void setValorPagadoUsuario(BigDecimal valorPagadoUsuario) {
        this.valorPagadoUsuario = valorPagadoUsuario;
    }

    public String getNumeroComprobanteArcom() {
        return numeroComprobanteArcom;
    }

    public void setNumeroComprobanteArcom(String numeroComprobanteArcom) {
        this.numeroComprobanteArcom = numeroComprobanteArcom;
    }

    public Long getCodigoConceptoPago() {
        return codigoConceptoPago;
    }

    public void setCodigoConceptoPago(Long codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public String getNumeroComprobanteBanco() {
        return numeroComprobanteBanco;
    }

    public void setNumeroComprobanteBanco(String numeroComprobanteBanco) {
        this.numeroComprobanteBanco = numeroComprobanteBanco;
    }

    public String getEntidadTramite() {
        return entidadTramite;
    }

    public void setEntidadTramite(String entidadTramite) {
        this.entidadTramite = entidadTramite;
    }

    public String getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public Date getFechaEmisionPago() {
        return fechaEmisionPago;
    }

    public void setFechaEmisionPago(Date fechaEmisionPago) {
        this.fechaEmisionPago = fechaEmisionPago;
    }

    public Long getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(Long codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public boolean isEditar1() {
        return editar1;
    }

    public void setEditar1(boolean editar1) {
        this.editar1 = editar1;
    }

    public boolean isEditar2() {
        return editar2;
    }

    public void setEditar2(boolean editar2) {
        this.editar2 = editar2;
    }
    
}

