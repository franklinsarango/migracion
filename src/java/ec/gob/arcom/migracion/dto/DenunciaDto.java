/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import java.util.List;

/**
 *
 * @author mejiaw
 */
public class DenunciaDto {
    private String nombreDenunciante;
    private String telefonoDenunciante;
    private String correoDenunciante;
    private String tipoDenuncia;
    private String nombreDenunciado;
    private String provincia;
    private String canton;
    private String parroquia;
    private String sector;
    private String nombreInvolucrado;
    private String detalleDenuncia;
    private List<String> adjuntos;
    private String fechaHoraEnvio;

    public String getNombreDenunciante() {
        return nombreDenunciante;
    }

    public void setNombreDenunciante(String nombreDenunciante) {
        this.nombreDenunciante = nombreDenunciante;
    }

    public String getTelefonoDenunciante() {
        return telefonoDenunciante;
    }

    public void setTelefonoDenunciante(String telefonoDenunciante) {
        this.telefonoDenunciante = telefonoDenunciante;
    }

    public String getCorreoDenunciante() {
        return correoDenunciante;
    }

    public void setCorreoDenunciante(String correoDenunciante) {
        this.correoDenunciante = correoDenunciante;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getNombreDenunciado() {
        return nombreDenunciado;
    }

    public void setNombreDenunciado(String nombreDenunciado) {
        this.nombreDenunciado = nombreDenunciado;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNombreInvolucrado() {
        return nombreInvolucrado;
    }

    public void setNombreInvolucrado(String nombreInvolucrado) {
        this.nombreInvolucrado = nombreInvolucrado;
    }

    public String getDetalleDenuncia() {
        return detalleDenuncia;
    }

    public void setDetalleDenuncia(String detalleDenuncia) {
        this.detalleDenuncia = detalleDenuncia;
    }

    public List<String> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<String> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(String fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }
    
}
