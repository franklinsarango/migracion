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
@Table(name = "parametro_sistema", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "ParametroSistema.findAll", query = "SELECT p FROM ParametroSistema p")})
public class ParametroSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_parametro")
    private Long codigoParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nemonico_parametro")
    private String nemonicoParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_parametro")
    private String nombreParametro;
    @Size(max = 200)
    @Column(name = "descripcion_parametro")
    private String descripcionParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "valor_parametro")
    private String valorParametro;
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
    @OneToMany(mappedBy = "codigoPeriodo")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @JoinColumn(name = "tipo_parametro", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle tipoParametro;
    @JoinColumn(name = "tipo_dato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoDato;

    public ParametroSistema() {
    }

    public ParametroSistema(Long codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public ParametroSistema(Long codigoParametro, String nemonicoParametro, String nombreParametro, String valorParametro) {
        this.codigoParametro = codigoParametro;
        this.nemonicoParametro = nemonicoParametro;
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
    }

    public Long getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(Long codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public String getNemonicoParametro() {
        return nemonicoParametro;
    }

    public void setNemonicoParametro(String nemonicoParametro) {
        this.nemonicoParametro = nemonicoParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
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

    public CatalogoDetalle getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(CatalogoDetalle tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public CatalogoDetalle getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(CatalogoDetalle tipoDato) {
        this.tipoDato = tipoDato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoParametro != null ? codigoParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroSistema)) {
            return false;
        }
        ParametroSistema other = (ParametroSistema) object;
        if ((this.codigoParametro == null && other.codigoParametro != null) || (this.codigoParametro != null && !this.codigoParametro.equals(other.codigoParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.ParametroSistema[ codigoParametro=" + codigoParametro + " ]";
    }
    
}
