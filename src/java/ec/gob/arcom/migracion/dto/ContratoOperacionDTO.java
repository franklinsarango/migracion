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
    private String codigoArcom;
    private String numeroDocumento;    
    private String provinica;
    private String canton;
    private String parroquia;
    private String estado;
    private Date fecha_inscripcion;
    private String CodigoContrato;
    private String titularContrato;
    private String codigo_regional;

    public Long getCodigoContratoOperacion() {
        return codigoContratoOperacion;
    }

    public void setCodigoContratoOperacion(Long codigoContratoOperacion) {
        this.codigoContratoOperacion = codigoContratoOperacion;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
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

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public String getCodigoContrato() {
        return CodigoContrato;
    }

    public void setCodigoContrato(String CodigoContrato) {
        this.CodigoContrato = CodigoContrato;
    }

    public String getTitularContrato() {
        return titularContrato;
    }

    public void setTitularContrato(String titularContrato) {
        this.titularContrato = titularContrato;
    }

    public String getCodigo_regional() {
        return codigo_regional;
    }

    public void setCodigo_regional(String codigo_regional) {
        this.codigo_regional = codigo_regional;
    }


    
}
