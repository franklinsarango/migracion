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
@Table(name = "detalle_licencia", schema = "arcom")
public class DetalleLicencia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_licencia")
    private Long codigoDetalleLicencia;
    
    @JoinColumn(name = "codigo_licencia", referencedColumnName = "codigo_licencia")
    @ManyToOne()
    private Licencia licencia;
    
    @JoinColumn(name = "codigo_tipo_informacion_registro", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInformacionRegistro;
    
    @JoinColumn(name = "codigo_periodo", referencedColumnName = "codigo_parametro")
    @ManyToOne
    private ParametroSistema periodo;
    
    @Column(name= "dias_solicita_periodo")
    private BigDecimal diasSolicitaPeriodo;
    
    @Column(name= "dias_total_periodo")
    private BigDecimal diasTotalPeriodo;
    
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

    public Long getCodigoDetalleLicencia() {
        return codigoDetalleLicencia;
    }

    public void setCodigoDetalleLicencia(Long codigoDetalleLicencia) {
        this.codigoDetalleLicencia = codigoDetalleLicencia;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public CatalogoDetalle getTipoInformacionRegistro() {
        return tipoInformacionRegistro;
    }

    public void setTipoInformacionRegistro(CatalogoDetalle tipoInformacionRegistro) {
        this.tipoInformacionRegistro = tipoInformacionRegistro;
    }

    public ParametroSistema getPeriodo() {
        return periodo;
    }

    public void setPeriodo(ParametroSistema periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getDiasSolicitaPeriodo() {
        return diasSolicitaPeriodo;
    }

    public void setDiasSolicitaPeriodo(BigDecimal diasSolicitaPeriodo) {
        this.diasSolicitaPeriodo = diasSolicitaPeriodo;
    }

    public BigDecimal getDiasTotalPeriodo() {
        return diasTotalPeriodo;
    }

    public void setDiasTotalPeriodo(BigDecimal diasTotalPeriodo) {
        this.diasTotalPeriodo = diasTotalPeriodo;
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
        hash += (codigoDetalleLicencia != null ? codigoDetalleLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleLicencia)) {
            return false;
        }
        DetalleLicencia other = (DetalleLicencia) object;
        if ((this.codigoDetalleLicencia == null && other.codigoDetalleLicencia != null) || (this.codigoDetalleLicencia != null && !this.codigoDetalleLicencia.equals(other.codigoDetalleLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.DetalleLicencia[ id=" + codigoDetalleLicencia + " ]";
    }
    
}
