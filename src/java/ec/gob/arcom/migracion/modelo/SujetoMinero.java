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
@Table(name = "sujeto_minero", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "SujetoMinero.findAll", query = "SELECT s FROM SujetoMinero s")})
public class SujetoMinero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_sujeto_minero")
    private Long codigoSujetoMinero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "apellido")
    private String apellido;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 80)
    @Column(name = "email")
    private String email;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_otorgacion_derecho")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorgacionDerecho;
    @Column(name = "fecha_protocolizacion_derecho")
    @Temporal(TemporalType.DATE)
    private Date fechaProtocolizacionDerecho;
    @Size(max = 20)
    @Column(name = "numero_repertorio")
    private String numeroRepertorio;
    @Size(max = 50)
    @Column(name = "nombre_compareciente")
    private String nombreCompareciente;
    @Size(max = 50)
    @Column(name = "apellido_compareciente")
    private String apellidoCompareciente;
    @Size(max = 20)
    @Column(name = "numero_documento_compareciente")
    private String numeroDocumentoCompareciente;
    @Size(max = 20)
    @Column(name = "ruc_compareciente")
    private String rucCompareciente;
    @Size(max = 50)
    @Column(name = "nombre_otorgante")
    private String nombreOtorgante;
    @Size(max = 50)
    @Column(name = "apellido_otorgante")
    private String apellidoOtorgante;
    @Size(max = 20)
    @Column(name = "numero_documento_otorgante")
    private String numeroDocumentoOtorgante;
    @Size(max = 20)
    @Column(name = "ruc_otorgante")
    private String rucOtorgante;
    @Size(max = 100)
    @Column(name = "notaria")
    private String notaria;
    @Size(max = 200)
    @Column(name = "naturaleza_acto_contrato")
    private String naturalezaActoContrato;
    @Size(max = 200)
    @Column(name = "clase_inscripcion")
    private String claseInscripcion;
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @Size(max = 30)
    @Column(name = "libro_inscripcion")
    private String libroInscripcion;
    @Size(max = 30)
    @Column(name = "numero_inscripcion")
    private String numeroInscripcion;
    @Size(max = 30)
    @Column(name = "tomo_inscripcion")
    private String tomoInscripcion;
    @Size(max = 30)
    @Column(name = "folio_inscripcion")
    private String folioInscripcion;
    @Size(max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Column(name = "codigo_localidad")
    private BigInteger codigoLocalidad;
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
    @JoinColumn(name = "estado_sujeto_minero", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoSujetoMinero;
    @JoinColumn(name = "tipo_sujeto_minero", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoSujetoMinero;

    public SujetoMinero() {
    }

    public SujetoMinero(Long codigoSujetoMinero) {
        this.codigoSujetoMinero = codigoSujetoMinero;
    }

    public SujetoMinero(Long codigoSujetoMinero, String numeroDocumento) {
        this.codigoSujetoMinero = codigoSujetoMinero;
        this.numeroDocumento = numeroDocumento;
    }

    public Long getCodigoSujetoMinero() {
        return codigoSujetoMinero;
    }

    public void setCodigoSujetoMinero(Long codigoSujetoMinero) {
        this.codigoSujetoMinero = codigoSujetoMinero;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaOtorgacionDerecho() {
        return fechaOtorgacionDerecho;
    }

    public void setFechaOtorgacionDerecho(Date fechaOtorgacionDerecho) {
        this.fechaOtorgacionDerecho = fechaOtorgacionDerecho;
    }

    public Date getFechaProtocolizacionDerecho() {
        return fechaProtocolizacionDerecho;
    }

    public void setFechaProtocolizacionDerecho(Date fechaProtocolizacionDerecho) {
        this.fechaProtocolizacionDerecho = fechaProtocolizacionDerecho;
    }

    public String getNumeroRepertorio() {
        return numeroRepertorio;
    }

    public void setNumeroRepertorio(String numeroRepertorio) {
        this.numeroRepertorio = numeroRepertorio;
    }

    public String getNombreCompareciente() {
        return nombreCompareciente;
    }

    public void setNombreCompareciente(String nombreCompareciente) {
        this.nombreCompareciente = nombreCompareciente;
    }

    public String getApellidoCompareciente() {
        return apellidoCompareciente;
    }

    public void setApellidoCompareciente(String apellidoCompareciente) {
        this.apellidoCompareciente = apellidoCompareciente;
    }

    public String getNumeroDocumentoCompareciente() {
        return numeroDocumentoCompareciente;
    }

    public void setNumeroDocumentoCompareciente(String numeroDocumentoCompareciente) {
        this.numeroDocumentoCompareciente = numeroDocumentoCompareciente;
    }

    public String getRucCompareciente() {
        return rucCompareciente;
    }

    public void setRucCompareciente(String rucCompareciente) {
        this.rucCompareciente = rucCompareciente;
    }

    public String getNombreOtorgante() {
        return nombreOtorgante;
    }

    public void setNombreOtorgante(String nombreOtorgante) {
        this.nombreOtorgante = nombreOtorgante;
    }

    public String getApellidoOtorgante() {
        return apellidoOtorgante;
    }

    public void setApellidoOtorgante(String apellidoOtorgante) {
        this.apellidoOtorgante = apellidoOtorgante;
    }

    public String getNumeroDocumentoOtorgante() {
        return numeroDocumentoOtorgante;
    }

    public void setNumeroDocumentoOtorgante(String numeroDocumentoOtorgante) {
        this.numeroDocumentoOtorgante = numeroDocumentoOtorgante;
    }

    public String getRucOtorgante() {
        return rucOtorgante;
    }

    public void setRucOtorgante(String rucOtorgante) {
        this.rucOtorgante = rucOtorgante;
    }

    public String getNotaria() {
        return notaria;
    }

    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    public String getNaturalezaActoContrato() {
        return naturalezaActoContrato;
    }

    public void setNaturalezaActoContrato(String naturalezaActoContrato) {
        this.naturalezaActoContrato = naturalezaActoContrato;
    }

    public String getClaseInscripcion() {
        return claseInscripcion;
    }

    public void setClaseInscripcion(String claseInscripcion) {
        this.claseInscripcion = claseInscripcion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getLibroInscripcion() {
        return libroInscripcion;
    }

    public void setLibroInscripcion(String libroInscripcion) {
        this.libroInscripcion = libroInscripcion;
    }

    public String getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public void setNumeroInscripcion(String numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public String getTomoInscripcion() {
        return tomoInscripcion;
    }

    public void setTomoInscripcion(String tomoInscripcion) {
        this.tomoInscripcion = tomoInscripcion;
    }

    public String getFolioInscripcion() {
        return folioInscripcion;
    }

    public void setFolioInscripcion(String folioInscripcion) {
        this.folioInscripcion = folioInscripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigInteger getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(BigInteger codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public BigInteger getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(BigInteger codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public BigInteger getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(BigInteger codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public BigInteger getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(BigInteger codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
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

    public CatalogoDetalle getEstadoSujetoMinero() {
        return estadoSujetoMinero;
    }

    public void setEstadoSujetoMinero(CatalogoDetalle estadoSujetoMinero) {
        this.estadoSujetoMinero = estadoSujetoMinero;
    }

    public CatalogoDetalle getTipoSujetoMinero() {
        return tipoSujetoMinero;
    }

    public void setTipoSujetoMinero(CatalogoDetalle tipoSujetoMinero) {
        this.tipoSujetoMinero = tipoSujetoMinero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSujetoMinero != null ? codigoSujetoMinero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SujetoMinero)) {
            return false;
        }
        SujetoMinero other = (SujetoMinero) object;
        if ((this.codigoSujetoMinero == null && other.codigoSujetoMinero != null) || (this.codigoSujetoMinero != null && !this.codigoSujetoMinero.equals(other.codigoSujetoMinero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.SujetoMinero[ codigoSujetoMinero=" + codigoSujetoMinero + " ]";
    }

}
