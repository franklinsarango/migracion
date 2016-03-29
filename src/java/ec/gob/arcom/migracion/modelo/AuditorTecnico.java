/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name = "auditores", catalog = "arcom_catmin", schema = "audmin")
public class AuditorTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String PERSONA_NATURAL= "PN";
    public static final String PERSONA_JURIDICA= "PJ";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ruc;
    private String razonsocial;
    private String cedularepresentantelegal;
    private String apellidorepresentantelegal;
    private String nombrerepresentantelegal;
    private String email;
    private String telefono;
    private String celular;
    private String direccion;
    private String provincia;
    private String tipopersona;
    private String codigoinec;

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getCedularepresentantelegal() {
        return cedularepresentantelegal;
    }

    public void setCedularepresentantelegal(String cedularepresentantelegal) {
        this.cedularepresentantelegal = cedularepresentantelegal;
    }

    public String getApellidorepresentantelegal() {
        return apellidorepresentantelegal;
    }

    public void setApellidorepresentantelegal(String apellidorepresentantelegal) {
        this.apellidorepresentantelegal = apellidorepresentantelegal;
    }

    public String getNombrerepresentantelegal() {
        return nombrerepresentantelegal;
    }

    public void setNombrerepresentantelegal(String nombrerepresentantelegal) {
        this.nombrerepresentantelegal = nombrerepresentantelegal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(String tipopersona) {
        this.tipopersona = tipopersona;
    }

    public String getCodigoinec() {
        return codigoinec;
    }

    public void setCodigoinec(String codigoinec) {
        this.codigoinec = codigoinec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditorTecnico)) {
            return false;
        }
        AuditorTecnico other = (AuditorTecnico) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result= "";
        result+= "Auditor Tecnico Minero \n";
        result+= this.ruc;
        result+= "\n";
        result+= this.razonsocial;
        result+= "\n";
        result+= this.cedularepresentantelegal;
        result+= "\n";
        result+= this.apellidorepresentantelegal;
        result+= "\n";
        result+= this.nombrerepresentantelegal;
        result+= "\n";
        result+= this.email;
        result+= "\n";
        return result;
    }
    
}
