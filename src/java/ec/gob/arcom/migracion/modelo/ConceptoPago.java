/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "concepto_pago", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "ConceptoPago.findAll", query = "SELECT c FROM ConceptoPago c")})
public class ConceptoPago implements Serializable {
    @OneToMany(mappedBy = "codigoConceptoPago")
    private List<CostoServicios> costoServiciosList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_concepto_pago")
    private Long codigoConceptoPago;
    @Size(max = 20)
    @Column(name = "nemonico_concepto_pago")
    private String nemonicoConceptoPago;
    @Size(max = 80)
    @Column(name = "nombre_concepto_pago")
    private String nombreConceptoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion_concepto_pago")
    private String descripcionConceptoPago;
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
    @OneToMany(mappedBy = "codigoConceptoPago")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @JoinColumn(name = "codigo_tipo_mineria", referencedColumnName = "codigo_tipo_mineria")
    @ManyToOne
    private TipoMineria codigoTipoMineria;

    public ConceptoPago() {
    }

    public ConceptoPago(Long codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public ConceptoPago(Long codigoConceptoPago, String descripcionConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
        this.descripcionConceptoPago = descripcionConceptoPago;
    }

    public Long getCodigoConceptoPago() {
        return codigoConceptoPago;
    }

    public void setCodigoConceptoPago(Long codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public String getNemonicoConceptoPago() {
        return nemonicoConceptoPago;
    }

    public void setNemonicoConceptoPago(String nemonicoConceptoPago) {
        this.nemonicoConceptoPago = nemonicoConceptoPago;
    }

    public String getNombreConceptoPago() {
        return nombreConceptoPago;
    }

    public void setNombreConceptoPago(String nombreConceptoPago) {
        this.nombreConceptoPago = nombreConceptoPago;
    }

    public String getDescripcionConceptoPago() {
        return descripcionConceptoPago;
    }

    public void setDescripcionConceptoPago(String descripcionConceptoPago) {
        this.descripcionConceptoPago = descripcionConceptoPago;
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

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public TipoMineria getCodigoTipoMineria() {
        return codigoTipoMineria;
    }

    public void setCodigoTipoMineria(TipoMineria codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoConceptoPago != null ? codigoConceptoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoPago)) {
            return false;
        }
        ConceptoPago other = (ConceptoPago) object;
        if ((this.codigoConceptoPago == null && other.codigoConceptoPago != null) || (this.codigoConceptoPago != null && !this.codigoConceptoPago.equals(other.codigoConceptoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.ConceptoPago[ codigoConceptoPago=" + codigoConceptoPago + " ]";
    }

    public List<CostoServicios> getCostoServiciosList() {
        return costoServiciosList;
    }

    public void setCostoServiciosList(List<CostoServicios> costoServiciosList) {
        this.costoServiciosList = costoServiciosList;
    }
    
}
