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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "persona_juridica", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "PersonaJuridica.findAll", query = "SELECT p FROM PersonaJuridica p")})
public class PersonaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_legal")
    private String nombreLegal;
    @Size(max = 255)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento_representante_legal")
    private String documentoRepresentanteLegal;
    @Size(max = 80)
    @Column(name = "nombre_representante_legal")
    private String nombreRepresentanteLegal;
    @Size(max = 80)
    @Column(name = "apellido_representante_legal")
    private String apellidoRepresentanteLegal;
    @Column(name = "fecha_constitucion")
    @Temporal(TemporalType.DATE)
    private Date fechaConstitucion;
    @Column(name = "es_publica")
    private Boolean esPublica;
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
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Size(max = 255)
    @Column(name = "campo_reservado_05")
    private String campoReservado05;
    @Size(max = 255)
    @Column(name = "campo_reservado_04")
    private String campoReservado04;
    @Size(max = 255)
    @Column(name = "campo_reservado_03")
    private String campoReservado03;
    @Size(max = 255)
    @Column(name = "campo_reservado_02")
    private String campoReservado02;
    @Size(max = 255)
    @Column(name = "campo_reservado_01")
    private String campoReservado01;
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
    @Size(max = 100)
    @Column(name = "casillero_judicial")
    private String casilleroJudicial;
    @JoinColumn(name = "codigo_localidad", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoLocalidad;
    @JoinColumn(name = "clase_persona", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle clasePersona;

    public PersonaJuridica() {
    }

    public PersonaJuridica(String ruc) {
        this.ruc = ruc;
    }

    public PersonaJuridica(String ruc, String nombreLegal, String documentoRepresentanteLegal) {
        this.ruc = ruc;
        this.nombreLegal = nombreLegal;
        this.documentoRepresentanteLegal = documentoRepresentanteLegal;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombreLegal() {
        return nombreLegal;
    }

    public void setNombreLegal(String nombreLegal) {
        this.nombreLegal = nombreLegal;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDocumentoRepresentanteLegal() {
        return documentoRepresentanteLegal;
    }

    public void setDocumentoRepresentanteLegal(String documentoRepresentanteLegal) {
        this.documentoRepresentanteLegal = documentoRepresentanteLegal;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getApellidoRepresentanteLegal() {
        return apellidoRepresentanteLegal;
    }

    public void setApellidoRepresentanteLegal(String apellidoRepresentanteLegal) {
        this.apellidoRepresentanteLegal = apellidoRepresentanteLegal;
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public Boolean getEsPublica() {
        return esPublica;
    }

    public void setEsPublica(Boolean esPublica) {
        this.esPublica = esPublica;
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

    public String getCampoReservado05() {
        return campoReservado05;
    }

    public void setCampoReservado05(String campoReservado05) {
        this.campoReservado05 = campoReservado05;
    }

    public String getCampoReservado04() {
        return campoReservado04;
    }

    public void setCampoReservado04(String campoReservado04) {
        this.campoReservado04 = campoReservado04;
    }

    public String getCampoReservado03() {
        return campoReservado03;
    }

    public void setCampoReservado03(String campoReservado03) {
        this.campoReservado03 = campoReservado03;
    }

    public String getCampoReservado02() {
        return campoReservado02;
    }

    public void setCampoReservado02(String campoReservado02) {
        this.campoReservado02 = campoReservado02;
    }

    public String getCampoReservado01() {
        return campoReservado01;
    }

    public void setCampoReservado01(String campoReservado01) {
        this.campoReservado01 = campoReservado01;
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

    public String getCasilleroJudicial() {
        return casilleroJudicial;
    }

    public void setCasilleroJudicial(String casilleroJudicial) {
        this.casilleroJudicial = casilleroJudicial;
    }

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public CatalogoDetalle getClasePersona() {
        return clasePersona;
    }

    public void setClasePersona(CatalogoDetalle clasePersona) {
        this.clasePersona = clasePersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaJuridica)) {
            return false;
        }
        PersonaJuridica other = (PersonaJuridica) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonaJuridica{" + "ruc=" + ruc + ", nombreLegal=" + nombreLegal + ", nombreComercial=" + nombreComercial + ", documentoRepresentanteLegal=" + documentoRepresentanteLegal
                + ", nombreRepresentanteLegal=" + nombreRepresentanteLegal + ", apellidoRepresentanteLegal=" + apellidoRepresentanteLegal + ", fechaConstitucion=" + fechaConstitucion
                + ", esPublica=" + esPublica + ", email=" + email + ", telefono=" + telefono + ", celular=" + celular + ", direccion=" + direccion + ", codigoProvincia=" + codigoProvincia
                + ", codigoCanton=" + codigoCanton + ", codigoParroquia=" + codigoParroquia + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04
                + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro
                + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion
                + ", casilleroJudicial=" + casilleroJudicial + ", codigoLocalidad=" + (codigoLocalidad != null ? codigoLocalidad.getCodigoLocalidad() : null) + ", clasePersona=" + (clasePersona != null ? clasePersona.getCodigoCatalogoDetalle() : null) + '}';
    }

}
