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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "opcion", catalog = "arcom_catmin", schema = "encmin")
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByCodigoPregunta", query = "SELECT o FROM Opcion o WHERE o.codigoPregunta = :codigoPregunta and o.estadoRegistro = TRUE ")})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_opcion")
    private Long codigoOpcion;
    @JoinColumn(name = "codigo_pregunta", referencedColumnName = "codigo_pregunta")
    @ManyToOne(optional = false)
    private Pregunta codigoPregunta;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private BigInteger usuarioModificacion;
    
    @Transient
    private String codigoOpcionPregunta;

    public Opcion() {
    }

    public Opcion(Long codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public Long getCodigoOpcion() {
        return codigoOpcion;
    }

    public void setCodigoOpcion(Long codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(BigInteger usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOpcion != null ? codigoOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.codigoOpcion == null && other.codigoOpcion != null) || (this.codigoOpcion != null && !this.codigoOpcion.equals(other.codigoOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.ctrl.Opcion[ codigoOpcion=" + codigoOpcion + " ]";
    }

    /**
     * @return the codigoOpcionPregunta
     */
    public String getCodigoOpcionPregunta() {
        return codigoOpcionPregunta;
    }

    /**
     * @param codigoOpcionPregunta the codigoOpcionPregunta to set
     */
    public void setCodigoOpcionPregunta(String codigoOpcionPregunta) {
        this.codigoOpcionPregunta = codigoOpcionPregunta;
    }

    /**
     * @return the codigoPregunta
     */
    public Pregunta getCodigoPregunta() {
        return codigoPregunta;
    }

    /**
     * @param codigoPregunta the codigoPregunta to set
     */
    public void setCodigoPregunta(Pregunta codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }

}
