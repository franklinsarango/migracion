/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
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
@Table(name = "licencia", schema = "arcom")
public class Licencia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_licencia")
    private Long codigoLicencia;
    
    @Column(name= "numero_solicitud")
    private String numeroSolicitud;
    
    @JoinColumn(name = "codigo_formulario", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoFormulario;
    
    @JoinColumn(name = "codigo_tipo_licencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoLicencia;
    
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuario;
    
    @Column(name = "fecha_solicitud")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSolicitud;
    
    @Column(name = "fecha_salida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSalida;
    
    @Column(name = "fecha_retorno")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRetorno;
    
    @Column(name = "horas_licencia")
    private Long horasLicencia;
    
    @Column(name = "dias_licencia")
    private Long diasLicencia;
    
    @Column(name = "dias_disponible")
    private Long diasDisponibles;
    
    @Column(name = "saldo_vacaciones")
    private Long saldoVacaciones;
    
    @JoinColumn(name = "estado_licencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoLicencia;
    
    @JoinColumn(name = "codigo_usuario_aprobacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioAprobacion;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "observaciones_jefatura")
    private String observacionesJefatura;
    
    @Column(name = "observaciones_rrhh")
    private String observacionesRecursoHumanos;
    
    @JoinColumn(name = "codigo_provincia_comision", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad provinciaComision;
    
    @JoinColumn(name = "codigo_canton_comision", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad cantonComision;
    
    @JoinColumn(name = "codigo_parroquia_comision", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad parroquiaComision;
    
    @Column(name = "asunto_institucional")
    private String asuntoInstitucional;
    
    @JoinColumn(name = "codigo_periodo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle periodo;
    
    
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
    
    
    public Long getCodigoLicencia() {
        return codigoLicencia;
    }

    public void setCodigoLicencia(Long codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public CatalogoDetalle getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(CatalogoDetalle tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public CatalogoDetalle getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(CatalogoDetalle tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public Long getHorasLicencia() {
        return horasLicencia;
    }

    public void setHorasLicencia(Long horasLicencia) {
        this.horasLicencia = horasLicencia;
    }

    public Long getDiasLicencia() {
        return diasLicencia;
    }

    public void setDiasLicencia(Long diasLicencia) {
        this.diasLicencia = diasLicencia;
    }

    public Long getDiasDisponibles() {
        return diasDisponibles;
    }

    public void setDiasDisponibles(Long diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    public Long getSaldoVacaciones() {
        return saldoVacaciones;
    }

    public void setSaldoVacaciones(Long saldoVacaciones) {
        this.saldoVacaciones = saldoVacaciones;
    }

    public CatalogoDetalle getEstadoLicencia() {
        return estadoLicencia;
    }

    public void setEstadoLicencia(CatalogoDetalle estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public Usuario getUsuarioAprobacion() {
        return usuarioAprobacion;
    }

    public void setUsuarioAprobacion(Usuario usuarioAprobacion) {
        this.usuarioAprobacion = usuarioAprobacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservacionesJefatura() {
        return observacionesJefatura;
    }

    public void setObservacionesJefatura(String observacionesJefatura) {
        this.observacionesJefatura = observacionesJefatura;
    }

    public String getObservacionesRecursoHumanos() {
        return observacionesRecursoHumanos;
    }

    public void setObservacionesRecursoHumanos(String observacionesRecursoHumanos) {
        this.observacionesRecursoHumanos = observacionesRecursoHumanos;
    }

    public Localidad getProvinciaComision() {
        return provinciaComision;
    }

    public void setProvinciaComision(Localidad provinciaComision) {
        this.provinciaComision = provinciaComision;
    }

    public Localidad getCantonComision() {
        return cantonComision;
    }

    public void setCantonComision(Localidad cantonComision) {
        this.cantonComision = cantonComision;
    }

    public Localidad getParroquiaComision() {
        return parroquiaComision;
    }

    public void setParroquiaComision(Localidad parroquiaComision) {
        this.parroquiaComision = parroquiaComision;
    }

    public String getAsuntoInstitucional() {
        return asuntoInstitucional;
    }

    public void setAsuntoInstitucional(String asuntoInstitucional) {
        this.asuntoInstitucional = asuntoInstitucional;
    }

    public CatalogoDetalle getPeriodo() {
        return periodo;
    }

    public void setPeriodo(CatalogoDetalle periodo) {
        this.periodo = periodo;
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
        hash += (codigoLicencia != null ? codigoLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licencia)) {
            return false;
        }
        Licencia other = (Licencia) object;
        if ((this.codigoLicencia == null && other.codigoLicencia != null)
                || (this.codigoLicencia != null && !this.codigoLicencia.equals(other.codigoLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Licencia[ id=" + codigoLicencia + " ]";
    }
}
