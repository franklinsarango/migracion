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
import javax.persistence.Temporal;

/**
 *
 * @author mejiaw
 */
@Entity
public class DepartamentoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_departamento_usuario")
    private Long codigoDepartamentoUsuario;
    
    @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento")
    @ManyToOne
    private Departamento departamento;
    
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuario;
    
    
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
    
    
    public Long getCodigoDepartamentoUsuario() {
        return codigoDepartamentoUsuario;
    }

    public void setCodigoDepartamentoUsuario(Long codigoDepartamentoUsuario) {
        this.codigoDepartamentoUsuario = codigoDepartamentoUsuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        hash += (codigoDepartamentoUsuario != null ? codigoDepartamentoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoUsuario)) {
            return false;
        }
        DepartamentoUsuario other = (DepartamentoUsuario) object;
        if ((this.codigoDepartamentoUsuario == null && other.codigoDepartamentoUsuario != null) 
                || (this.codigoDepartamentoUsuario != null && !this.codigoDepartamentoUsuario.equals(other.codigoDepartamentoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.DepartamentoUsuario[ id=" + codigoDepartamentoUsuario + " ]";
    }
}
