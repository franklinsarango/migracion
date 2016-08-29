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

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "encuesta_pregunta", catalog = "arcom_catmin", schema = "encmin")
@NamedQueries({
    @NamedQuery(name = "EncuestaPregunta.findAll", query = "SELECT r FROM EncuestaPregunta r"),
    @NamedQuery(name = "EncuestaPregunta.findByCodigoEncuesta", query = "SELECT ep FROM EncuestaPregunta ep WHERE ep.codigoEncuesta = :codigoEncuesta and ep.estadoRegistro = TRUE")})
public class EncuestaPregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_encuesta_pregunta")
    private Long codigoEncuestaPregunta;
    @JoinColumn(name = "codigo_encuesta", referencedColumnName = "codigo_encuesta")
    @ManyToOne(optional = false)
    private Encuesta codigoEncuesta;
    @JoinColumn(name = "codigo_pregunta", referencedColumnName = "codigo_pregunta")
    @ManyToOne(optional = false)
    private Pregunta codigoPregunta;
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

    public EncuestaPregunta() {
    }

    public EncuestaPregunta(Long codigoEncuestaPregunta) {
        this.codigoEncuestaPregunta = codigoEncuestaPregunta;
    }

    public Long getCodigoEncuestaPregunta() {
        return codigoEncuestaPregunta;
    }

    public void setCodigoEncuestaPregunta(Long codigoEncuestaPregunta) {
        this.codigoEncuestaPregunta = codigoEncuestaPregunta;
    }

        /**
     * @return the codigoEncuesta
     */
    public Encuesta getCodigoEncuesta() {
        return codigoEncuesta;
    }

    /**
     * @param codigoEncuesta the codigoEncuesta to set
     */
    public void setCodigoEncuesta(Encuesta codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
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
        hash += (codigoEncuestaPregunta != null ? codigoEncuestaPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaPregunta)) {
            return false;
        }
        EncuestaPregunta other = (EncuestaPregunta) object;
        if ((this.codigoEncuestaPregunta == null && other.codigoEncuestaPregunta != null) || (this.codigoEncuestaPregunta != null && !this.codigoEncuestaPregunta.equals(other.codigoEncuestaPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.ctrl.EncuestaPregunta[ codigoEncuestaPregunta=" + codigoEncuestaPregunta + " ]";
    }

}
