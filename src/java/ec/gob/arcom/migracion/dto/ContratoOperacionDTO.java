/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Localidad;
import java.util.Date;

/**
 *
 * @author CuencaL
 */
public class ContratoOperacionDTO {
    private Long codigoContratoOperacion;    
    private String codigoArcomConcesion;
    private String codigoArcomContrato;
    private String numeroDocumento;    
    private String provinica;
    private String canton;
    private String parroquia;
    private String estado;
    private Date fechaInscripcion;    
    private String titularContrato;
    private String prefijoCodigo;

    public Long getCodigoContratoOperacion() {
        return codigoContratoOperacion;
    }

    public void setCodigoContratoOperacion(Long codigoContratoOperacion) {
        this.codigoContratoOperacion = codigoContratoOperacion;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getProvinica() {
        return provinica;
    }

    public void setProvinica(String provinica) {
        this.provinica = provinica;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    public String getTitularContrato() {
        return titularContrato;
    }

    public void setTitularContrato(String titularContrato) {
        this.titularContrato = titularContrato;
    }

    public String getCodigoArcomConcesion() {
        return codigoArcomConcesion;
    }

    public void setCodigoArcomConcesion(String codigoArcomConcesion) {
        this.codigoArcomConcesion = codigoArcomConcesion;
    }

    public String getCodigoArcomContrato() {
        return codigoArcomContrato;
    }

    public void setCodigoArcomContrato(String codigoArcomContrato) {
        this.codigoArcomContrato = codigoArcomContrato;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getPrefijoCodigo() {
        return prefijoCodigo;
    }

    public void setPrefijoCodigo(String prefijoCodigo) {
        this.prefijoCodigo = prefijoCodigo;
    }
    
    
}
