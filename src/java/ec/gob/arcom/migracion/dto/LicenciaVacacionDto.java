/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import ec.gob.arcom.migracion.modelo.Licencia;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author mejiaw
 */
public class LicenciaVacacionDto {
    Long codigoLicencia;
    Long numeroSolicitud;
    String funcionario;
    String unidadAdministrativa;
    String tipoFormulario;
    String motivo;
    String estado;
    Date fechaSolicitud;
    String diasLicencia;
    
    public LicenciaVacacionDto() {
        
    }
    
    public Long getCodigoLicencia() {
        return codigoLicencia;
    }

    public void setCodigoLicencia(Long codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }

    public Long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getDiasLicencia() {
        return diasLicencia;
    }

    public void setDiasLicencia(String diasLicencia) {
        this.diasLicencia = diasLicencia;
    }
    
    
}
