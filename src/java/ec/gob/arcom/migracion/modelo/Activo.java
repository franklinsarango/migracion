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
@Table(name = "activo", catalog = "arcom_catmin", schema = "inventario")
@NamedQueries({
    @NamedQuery(name = "Activo.findAll", query = "SELECT c FROM Activo c")})
public class Activo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;
    
    @JoinColumn(name = "fk_tipoactivo", referencedColumnName = "id")
    @ManyToOne
    private CatalogoInv tipoactivo;   
    @JoinColumn(name = "fk_marca", referencedColumnName = "id")
    @ManyToOne
    private CatalogoInv marca;   
    @JoinColumn(name = "fk_modelo", referencedColumnName = "id")
    @ManyToOne
    private CatalogoInv modelo;    
    @Column(name = "tag_arcom")
    private String tagArcom;
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "fk_regional", referencedColumnName = "id")
    @ManyToOne
    private RegionalInv regional;   
    @JoinColumn(name = "fk_ubicacion", referencedColumnName = "id")
    @ManyToOne
    private CatalogoInv ubicacion;   
    @JoinColumn(name = "fk_estado", referencedColumnName = "id")
    @ManyToOne
    private CatalogoInv estado;   
    @Column(name = "vigencia")
    private BigInteger vigencia;   
   
    public Activo() {
    }

    public Activo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogoInv getTipoactivo() {
        return tipoactivo;
    }

    public void setTipoactivo(CatalogoInv tipoactivo) {
        this.tipoactivo = tipoactivo;
    }

    public CatalogoInv getMarca() {
        return marca;
    }

    public void setMarca(CatalogoInv marca) {
        this.marca = marca;
    }

    public CatalogoInv getModelo() {
        return modelo;
    }

    public void setModelo(CatalogoInv modelo) {
        this.modelo = modelo;
    }    

    public String getTagArcom() {
        return tagArcom;
    }

    public void setTagArcom(String tagArcom) {
        this.tagArcom = tagArcom;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RegionalInv getRegional() {
        return regional;
    }

    public void setRegional(RegionalInv regional) {
        this.regional = regional;
    }

    public CatalogoInv getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(CatalogoInv ubicacion) {
        this.ubicacion = ubicacion;
    }

    public CatalogoInv getEstado() {
        return estado;
    }

    public void setEstado(CatalogoInv estado) {
        this.estado = estado;
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
        if (!(object instanceof Activo)) {
            return false;
        }
        Activo other = (Activo) object;
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
        return "Activo{" + "id=" + id + ", tipoactivo=" + tipoactivo + ", marca=" + marca + ", modelo=" + modelo
                + ", tagArcom=" + tagArcom + ", numSerie=" + numSerie + ", descripcion=" + descripcion + ", regional=" + regional
                + ", ubicacion=" + ubicacion + ", estado=" + estado + ", vigencia=" + vigencia + '}';
    }

}
