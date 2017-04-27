/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name = "concesion_pago_sri", schema = "catmin")
public class ConcesionPagoSri implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "codigo_concesion_pago_sri")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera concesionMinera;
    @Column(name = "valor_calculado_arcom")
    private BigDecimal valorCalculadoArcom;
    @Column(name = "valor_pago_arcom")
    private BigDecimal valorPagoArcom;
    @Column(name = "comprobante_electronico_arcom")
    private String comprobanteElectronicoArcom;
    @Column(name = "anio_fiscal")
    private String anioFiscal;
    @Column(name = "valor_pago_sri")
    private BigDecimal valorPagoSri;
    @Column(name = "comprobante_electronico_sri")
    private String comprobanteElectronicoSri;
    @Column(name = "estado_registro")
    private boolean estadoRegistro;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConcesionMinera getConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public BigDecimal getValorCalculadoArcom() {
        return valorCalculadoArcom;
    }

    public void setValorCalculadoArcom(BigDecimal valorCalculadoArcom) {
        this.valorCalculadoArcom = valorCalculadoArcom;
    }

    public BigDecimal getValorPagoArcom() {
        return valorPagoArcom;
    }

    public void setValorPagoArcom(BigDecimal valorPagoArcom) {
        this.valorPagoArcom = valorPagoArcom;
    }

    public String getComprobanteElectronicoArcom() {
        return comprobanteElectronicoArcom;
    }

    public void setComprobanteElectronicoArcom(String comprobanteElectronicoArcom) {
        this.comprobanteElectronicoArcom = comprobanteElectronicoArcom;
    }

    public BigDecimal getValorPagoSri() {
        return valorPagoSri;
    }

    public void setValorPagoSri(BigDecimal valorPagoSri) {
        this.valorPagoSri = valorPagoSri;
    }

    public String getAnioFiscal() {
        return anioFiscal;
    }

    public void setAnioFiscal(String anioFiscal) {
        this.anioFiscal = anioFiscal;
    }
    
    public String getComprobanteElectronicoSri() {
        return comprobanteElectronicoSri;
    }

    public void setComprobanteElectronicoSri(String comprobanteElectronicoSri) {
        this.comprobanteElectronicoSri = comprobanteElectronicoSri;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
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
        if (!(object instanceof ConcesionPagoSri)) {
            return false;
        }
        ConcesionPagoSri other = (ConcesionPagoSri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.ConcesionPagoSri[ id=" + id + " ]";
    }
}
