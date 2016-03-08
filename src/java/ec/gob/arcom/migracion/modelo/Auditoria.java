/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "auditoria", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a")})
public class Auditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_auditoria")
    private Long codigoAuditoria;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario")
    private BigInteger usuario;
    @Size(max = 2147483647)
    @Column(name = "accion")
    private String accion;
    @Size(max = 100000)
    @Column(name = "detalle_anterior")
    private String detalleAnterior;
    @Size(max = 100000)
    @Column(name = "detalle_cambios")
    private String detalleCambios;

    public Auditoria() {
    }

    public Auditoria(Long codigoAuditoria) {
        this.codigoAuditoria = codigoAuditoria;
    }

    public Long getCodigoAuditoria() {
        return codigoAuditoria;
    }

    public void setCodigoAuditoria(Long codigoAuditoria) {
        this.codigoAuditoria = codigoAuditoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDetalleAnterior() {
        return detalleAnterior;
    }

    public void setDetalleAnterior(String detalleAnterior) {
        this.detalleAnterior = detalleAnterior;
    }

    public String getDetalleCambios() {
        return detalleCambios;
    }

    public void setDetalleCambios(String detalleCambios) {
        this.detalleCambios = detalleCambios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAuditoria != null ? codigoAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.codigoAuditoria == null && other.codigoAuditoria != null) || (this.codigoAuditoria != null && !this.codigoAuditoria.equals(other.codigoAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Auditoria[ codigoAuditoria=" + codigoAuditoria + " ]";
    }
    
}
