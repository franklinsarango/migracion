/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import ec.gob.arcom.migracion.modelo.Denuncia;
import ec.gob.arcom.migracion.util.DateUtil;
import java.util.List;

/**
 *
 * @author mejiaw
 */
public class DenunciaDto {
    private String codigoDenuncia;
    private String nombreDenunciante;
    private String telefonoDenunciante;
    private String correoDenunciante;
    private String tipoDenuncia;
    private String nombreDenunciado;
    private String provincia;
    private String canton;
    private String parroquia;
    private String sector;
    private String regional;
    private String nombreInvolucrado;
    private String detalleDenuncia;
    private List<String> adjuntos;
    private String fechaHoraEnvio;
    
    public DenunciaDto() {
        
    }
    
    public DenunciaDto(Denuncia d) {
        this.codigoDenuncia= d.getCodigoDenuncia().toString();
        this.nombreDenunciante= (d.getNombreDenunciante() != null ? d.getNombreDenunciante() : "");
        this.telefonoDenunciante= (d.getTelefonoDenunciante() != null ? d.getTelefonoDenunciante() : "");
        this.correoDenunciante= d.getEmailDenunciante();
        this.tipoDenuncia= d.getTipoDenuncia().getNombre();
        this.nombreDenunciado= (d.getUsuarioInfractor() != null ? d.getUsuarioInfractor().getNombresCompletos().toUpperCase() : "");
        this.provincia= (d.getProvincia() != null ? d.getProvincia().getNombre() : "");
        this.canton= (d.getCanton() != null ? d.getCanton().getNombre() : "");
        this.parroquia= (d.getParroquia() != null ? d.getParroquia().getNombre() : "");
        this.sector= (d.getSector() != null ? d.getSector() : "");
        this.regional= (d.getRegional() != null ? d.getRegional().getDescripcionRegional() : "");
        this.nombreInvolucrado= (d.getNombreInfractor() != null ? d.getNombreInfractor() : "");
        this.detalleDenuncia= d.getDetalleDenuncia();
        this.fechaHoraEnvio= DateUtil.obtenerFechaHoraConFormato(d.getFechaCreacion());
    }

    public String getCodigoDenuncia() {
        return codigoDenuncia;
    }

    public void setCodigoDenuncia(String codigoDenuncia) {
        this.codigoDenuncia = codigoDenuncia;
    }
    
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
    
    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
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
