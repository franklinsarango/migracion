/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

/**
 *
 * @author Javier Coronel
 */
public class PlantaBeneficioDto {
    
    private String codigoPlantaBeneficio;
    private String codigoArcom;
    private String nombrePlantaBeneficio;
    private String numDocumentoRepLegal;
    private String nombreRepLegal;
    private String apellidoRepLegal;
    private String telefono;
    private String direccion;
    private String casilleroJudicial;
    private String provincia;
    private String canton;
    private String parroquia;
    private String estado;

    public String getCodigoPlantaBeneficio() {
        return codigoPlantaBeneficio;
    }

    public void setCodigoPlantaBeneficio(String codigoPlantaBeneficio) {
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public String getNombrePlantaBeneficio() {
        return nombrePlantaBeneficio;
    }

    public void setNombrePlantaBeneficio(String nombrePlantaBeneficio) {
        this.nombrePlantaBeneficio = nombrePlantaBeneficio;
    }

    public String getNumDocumentoRepLegal() {
        return numDocumentoRepLegal;
    }

    public void setNumDocumentoRepLegal(String numDocumentoRepLegal) {
        this.numDocumentoRepLegal = numDocumentoRepLegal;
    }

    public String getNombreRepLegal() {
        return nombreRepLegal;
    }

    public void setNombreRepLegal(String nombreRepLegal) {
        this.nombreRepLegal = nombreRepLegal;
    }

    public String getApellidoRepLegal() {
        return apellidoRepLegal;
    }

    public void setApellidoRepLegal(String apellidoRepLegal) {
        this.apellidoRepLegal = apellidoRepLegal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCasilleroJudicial() {
        return casilleroJudicial;
    }

    public void setCasilleroJudicial(String casilleroJudicial) {
        this.casilleroJudicial = casilleroJudicial;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
