/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
@Table(name = "detalle_operativo", schema = "catmin")
public class DetalleOperativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_operativo")
    private Long codigoDetalleOperativo;
    
    @JoinColumn(name = "codigo_operativo", referencedColumnName = "codigo_operativo")
    @ManyToOne()
    private Operativo operativo;
    
    @JoinColumn(name = "codigo_tipo_informacion_registro", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInformacionRegistro;
    @JoinColumn(name = "codigo_tipo_institucion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInstitucion;
    @Column(name = "numero_personas")
    private Long numeroPersonas;
    @Column(name = "descripcion_institucion")
    private String descripcionInstitucion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @JoinColumn(name = "codigo_tipo_depositario", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoDepositario;
    @Column(name = "descripcion_depositario")
    private String descripcionDepositario;
    
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
    
    public Long getCodigoDetalleOperativo() {
        return codigoDetalleOperativo;
    }

    public void setCodigoDetalleOperativo(Long codigoDetalleOperativo) {
        this.codigoDetalleOperativo = codigoDetalleOperativo;
    }

    public CatalogoDetalle getTipoInformacionRegistro() {
        return tipoInformacionRegistro;
    }

    public void setTipoInformacionRegistro(CatalogoDetalle tipoInformacionRegistro) {
        this.tipoInformacionRegistro = tipoInformacionRegistro;
    }

    public CatalogoDetalle getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setTipoInstitucion(CatalogoDetalle tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public Long getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Long numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getDescripcionInstitucion() {
        return descripcionInstitucion;
    }

    public void setDescripcionInstitucion(String descripcionInstitucion) {
        this.descripcionInstitucion = descripcionInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public CatalogoDetalle getTipoDepositario() {
        return tipoDepositario;
    }

    public void setTipoDepositario(CatalogoDetalle tipoDepositario) {
        this.tipoDepositario = tipoDepositario;
    }

    public String getDescripcionDepositario() {
        return descripcionDepositario;
    }

    public void setDescripcionDepositario(String descripcionDepositario) {
        this.descripcionDepositario = descripcionDepositario;
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
    
    public Operativo getOperativo() {
        return operativo;
    }

    public void setOperativo(Operativo operativo) {
        this.operativo = operativo;
    }
    
    @Override
    public String toString() {
        String response;
        response= "DetalleOperativo{" + 
                "codigoDetalleOperativo=" + codigoDetalleOperativo + ", operativo=" + operativo.getCodigoOperativo() +
                ", tipoInformacionRegistro=" + tipoInformacionRegistro.getCodigoCatalogoDetalle() + ", tipoInstitucion=" + obtenerTipoInstitucion() +
                ", numeroPersonas=" + numeroPersonas + ", descripcionInstitucion=" + descripcionInstitucion + ", nombre" + nombre + ", apellido=" + apellido + 
                ", numeroDocumento=" + numeroDocumento + ", tipoDepositario=" + obtenerTipoDepositario() + ", descripcionDepositario=" + descripcionDepositario +
                ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + 
                ", fechaModificacion=" + obtenerFechaModificacion() + ", usuarioModificacion=" + obtenerUsuarioModificacion() +
                 "}";
        return response;
    }
    
    private String obtenerTipoInstitucion() {
        if(tipoInstitucion==null) {
            return "";
        } else {
            return tipoInstitucion.getCodigoCatalogoDetalle() + "";
        }
    }
    
    private String obtenerTipoDepositario() {
        if(tipoDepositario==null) {
            return "";
        } else {
            return tipoDepositario.getCodigoCatalogoDetalle() + "";
        }
    }
    
    private String obtenerFechaModificacion() {
        if(fechaModificacion==null) {
            return "";
        } else {
            return fechaModificacion.toString();
        }
    }
    
    private String obtenerUsuarioModificacion() {
        if(usuarioModificacion==null) {
            return "";
        } else {
            return usuarioModificacion.getNumeroDocumento();
        }
    }
}
