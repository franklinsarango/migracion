/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "sadmin_data_maquinaria", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "SadminDataMaquinaria.findAll", query = "SELECT s FROM SadminDataMaquinaria s")})
public class SadminDataMaquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_sadmin_data_maq")
    private Long codigoSadminDataMaq;
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "codigo_arcom")
    private String codigoArcom;
    @JoinColumn(name = "codigo_tipo_maquinaria", referencedColumnName = "codigo_tipo_maquinaria")
    @ManyToOne
    private TipoMaquinaria codigoTipoMaquinaria;
    @Column(name = "cantidad_maquinaria")
    private int cantidadMaquinaria;

    public SadminDataMaquinaria() {
    }

    public SadminDataMaquinaria(Long codigoSadminDataMaq) {
        this.codigoSadminDataMaq = codigoSadminDataMaq;
    }

    public SadminDataMaquinaria(Long codigoSadminDataMaq, String codigoArcom) {
        this.codigoSadminDataMaq = codigoSadminDataMaq;
        this.codigoArcom = codigoArcom;
    }

    public Long getCodigoSadminDataMaq() {
        return codigoSadminDataMaq;
    }

    public void setCodigoSadminDataMaq(Long codigoSadminDataMaq) {
        this.codigoSadminDataMaq = codigoSadminDataMaq;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public TipoMaquinaria getCodigoTipoMaquinaria() {
        return codigoTipoMaquinaria;
    }

    public void setCodigoTipoMaquinaria(TipoMaquinaria codigoTipoMaquinaria) {
        this.codigoTipoMaquinaria = codigoTipoMaquinaria;
    }

    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSadminDataMaq != null ? codigoSadminDataMaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SadminDataMaquinaria)) {
            return false;
        }
        SadminDataMaquinaria other = (SadminDataMaquinaria) object;
        if ((this.codigoSadminDataMaq == null && other.codigoSadminDataMaq != null) || (this.codigoSadminDataMaq != null && !this.codigoSadminDataMaq.equals(other.codigoSadminDataMaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SadminDataMaquinaria{" + "codigoSadminDataMaq=" + codigoSadminDataMaq + ", codigoArcom=" + codigoArcom 
                + ", codigoTipoMaquinaria=" + (codigoTipoMaquinaria != null ? codigoTipoMaquinaria.getCodigoTipoMaquinaria() : null) + ", cantidadMaquinaria=" + cantidadMaquinaria + '}';
    }
    
}
