/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name = "gestion_vacacion", schema = "arcom")
public class GestionVacacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_gestion_vacacion")
    private Long codigoGestionVacacion;
    
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuario;
    
    @JoinColumn(name = "codigo_contrato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle contrato;
    
    @JoinColumn(name = "codigo_licencia", referencedColumnName = "codigo_licencia")
    @ManyToOne
    private Licencia licencia;
    
    @Column(name = "saldo_anterior")
    private BigDecimal saldoAnterior;
    
    @Column(name = "saldo_actual")
    private BigDecimal saldoActual;
    
    @Column(name = "dias_incrementados")
    private BigDecimal diasIncrementados;
    
    @Column(name = "dias_decrementados")
    private BigDecimal diasDecrementados;
    
    @Column(name = "fecha_corte")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCorte;
    
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "usuario_creacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "usuario_modificacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioModificacion;

    public Long getCodigoGestionVacacion() {
        return codigoGestionVacacion;
    }

    public void setCodigoGestionVacacion(Long codigoGestionVacacion) {
        this.codigoGestionVacacion = codigoGestionVacacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CatalogoDetalle getContrato() {
        return contrato;
    }

    public void setContrato(CatalogoDetalle contrato) {
        this.contrato = contrato;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public BigDecimal getDiasIncrementados() {
        return diasIncrementados;
    }

    public void setDiasIncrementados(BigDecimal diasIncrementados) {
        this.diasIncrementados = diasIncrementados;
    }

    public BigDecimal getDiasDecrementados() {
        return diasDecrementados;
    }

    public void setDiasDecrementados(BigDecimal diasDecrementados) {
        this.diasDecrementados = diasDecrementados;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoGestionVacacion != null ? codigoGestionVacacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestionVacacion)) {
            return false;
        }
        GestionVacacion other = (GestionVacacion) object;
        if ((this.codigoGestionVacacion == null && other.codigoGestionVacacion != null) || (this.codigoGestionVacacion != null && !this.codigoGestionVacacion.equals(other.codigoGestionVacacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.GestionVacacion[ id=" + codigoGestionVacacion + " ]";
    }
    
}
