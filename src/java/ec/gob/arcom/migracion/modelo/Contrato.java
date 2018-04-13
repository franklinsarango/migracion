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
@Table(name = "contrato", schema = "arcom")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_contrato")
    private Long codigoContrato;
    
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuario;
    
    @JoinColumn(name = "codigo_tipo_contrato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoContrato;
    
    @JoinColumn(name = "codigo_regimen_contratacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle regimenContratacion;
    
    @JoinColumn(name = "codigo_modalidad_contrato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle modalidadContrato;
    
    @JoinColumn(name = "codigo_tipo_cargo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoCargo;
    
    @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento")
    @ManyToOne
    private Departamento departamento;
    
    @Column(name = "fecha_ingreso")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIngreso;
    
    @Column(name = "fecha_salida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSalida;
    
    @Column(name = "saldo_vacaciones")
    private BigDecimal saldoVacaciones;
    
    @JoinColumn(name = "codigo_motivo_salida", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle motivoSalida;
    
    @JoinColumn(name = "codigo_horario_almuerzo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle horarioAlmuerzo;
    
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
    
    public Contrato() {
        
    }
    
    public Contrato(Contrato c) {
        this.codigoContrato= c.getCodigoContrato();
        this.usuario= c.getUsuario();
        this.tipoContrato= c.getTipoContrato();
        this.regimenContratacion= c.getRegimenContratacion();
        this.modalidadContrato= c.getModalidadContrato();
        this.tipoCargo= c.getTipoCargo();
        this.departamento= c.getDepartamento();
        this.fechaIngreso= c.getFechaIngreso();
        this.fechaSalida= c.getFechaSalida();
        this.saldoVacaciones= c.getSaldoVacaciones();
        this.motivoSalida= c.getMotivoSalida();
        this.horarioAlmuerzo= c.getHorarioAlmuerzo();
        this.estadoRegistro= c.getEstadoRegistro();
        this.fechaCreacion= c.getFechaCreacion();
        this.usuarioCreacion= c.getUsuarioCreacion();
        this.fechaModificacion= c.getFechaModificacion();
        this.usuarioModificacion= c.getUsuarioModificacion();
    }

    public Long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(Long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CatalogoDetalle getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(CatalogoDetalle tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public CatalogoDetalle getModalidadContrato() {
        return modalidadContrato;
    }

    public void setModalidadContrato(CatalogoDetalle modalidadContrato) {
        this.modalidadContrato = modalidadContrato;
    }

    public CatalogoDetalle getRegimenContratacion() {
        return regimenContratacion;
    }

    public void setRegimenContratacion(CatalogoDetalle regimenContratacion) {
        this.regimenContratacion = regimenContratacion;
    }

    public CatalogoDetalle getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(CatalogoDetalle tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public BigDecimal getSaldoVacaciones() {
        return saldoVacaciones;
    }

    public void setSaldoVacaciones(BigDecimal saldoVacaciones) {
        this.saldoVacaciones = saldoVacaciones;
    }

    public CatalogoDetalle getMotivoSalida() {
        return motivoSalida;
    }

    public void setMotivoSalida(CatalogoDetalle motivoSalida) {
        this.motivoSalida = motivoSalida;
    }

    public CatalogoDetalle getHorarioAlmuerzo() {
        return horarioAlmuerzo;
    }

    public void setHorarioAlmuerzo(CatalogoDetalle horarioAlmuerzo) {
        this.horarioAlmuerzo = horarioAlmuerzo;
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
        hash += (codigoContrato != null ? codigoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.codigoContrato == null && other.codigoContrato != null) || (this.codigoContrato != null && !this.codigoContrato.equals(other.codigoContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Contrato[ id=" + codigoContrato + " ]";
    }
    
}
