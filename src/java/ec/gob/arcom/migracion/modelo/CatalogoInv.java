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
@Table(name = "catalogo_inv", catalog = "arcom_catmin", schema = "inventario")
@NamedQueries({
    @NamedQuery(name = "CatalogoInv.findAll", query = "SELECT c FROM CatalogoInv c")})
public class CatalogoInv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "fk_catalogo")
    private BigInteger fkCatalogo;       
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nemonico")
    private String nemonico;
    @Column(name = "vigencia")
    private BigInteger vigencia;   

    public CatalogoInv() {
    }

    public CatalogoInv(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getFkCatalogo() {
        return fkCatalogo;
    }

    public void setFkCatalogo(BigInteger fkCatalogo) {
        this.fkCatalogo = fkCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
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
        if (!(object instanceof CatalogoInv)) {
            return false;
        }
        CatalogoInv other = (CatalogoInv) object;
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
        return "CatalogoInv{" + "id=" + id + ", fkCatalogo=" + fkCatalogo + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", nemonico=" + nemonico + ", vigencia=" + vigencia + '}';
    }

}
