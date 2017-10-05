/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "usuario_inv", catalog = "arcom_catmin", schema = "inventario")
@NamedQueries({
    @NamedQuery(name = "UsuarioInv.findAll", query = "SELECT c FROM UsuarioInv c")})
public class UsuarioInv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;
         
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "vigencia")
    private BigInteger vigencia;   

    public UsuarioInv() {
    }

    public UsuarioInv(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    public BigInteger getVigencia() {
        return vigencia;
    }

    public void setVigencia(BigInteger vigencia) {
        this.vigencia = vigencia;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioInv)) {
            return false;
        }
        UsuarioInv other = (UsuarioInv) object;
        if ((this.id == null
                && other.id != null)
                || (this.id != null
                && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "UsuarioInv{" + "id=" + id + ", nombre=" + nombre + ", nombres=" + nombres + ", apellido=" + apellido
                + ", login=" + login + ", password=" + password + ", vigencia=" + vigencia + '}';
    }

}
