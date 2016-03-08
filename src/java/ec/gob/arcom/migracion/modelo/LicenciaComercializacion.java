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
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "licencia_comercializacion", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "LicenciaComercializacion.findAll", query = "SELECT l FROM LicenciaComercializacion l")})
public class LicenciaComercializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_licencia_comercializacion")
    private Long codigoLicenciaComercializacion;
    @Column(name = "codigo_informe")
    private BigInteger codigoInforme;
    @Size(max = 20)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 60)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 30)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "casillero_judicial")
    private String casilleroJudicial;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquida")
    private BigInteger codigoParroquida;
    @Size(max = 300)
    @Column(name = "descripcion_material_comercializar")
    private String descripcionMaterialComercializar;
    @Column(name = "fecha_otorgamiento_licencia")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorgamientoLicencia;
    @Size(max = 255)
    @Column(name = "observaciones_licencia")
    private String observacionesLicencia;
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
    @Column(name = "xml_data")
    private Serializable xmlData;
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
    @Column(name = "codigo_arcom")
    private String codigoArcom;
    @Column(name = "fecha_otorga")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorga;
    @Column(name = "fecha_inscribe")
    @Temporal(TemporalType.DATE)
    private Date fechaInscribe;
    @JoinColumn(name = "estado_licencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoLicencia;
    @JoinColumn(name = "tipo_licencia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoLicencia;
    @JoinColumn(name = "codigo_tipo_mineral", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoTipoMineral;
    @JoinColumn(name = "codigo_mineral_interes", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoMineralInteres;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "tipo_persona")
    private String tipoPersona;
    @Column(name = "migrada")
    private boolean migrada;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "licenciaComercializacion")
    //private Resolucion resolucion;
    @Transient
    private String tipoPersonaString;
    @Transient
    private String provinciaString;
    @Transient
    private String cantonString;
    @Transient
    private String parroquiaString;

    public LicenciaComercializacion() {
    }

    public LicenciaComercializacion(Long codigoLicenciaComercializacion) {
        this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
    }

    public Long getCodigoLicenciaComercializacion() {
        return codigoLicenciaComercializacion;
    }

    public void setCodigoLicenciaComercializacion(Long codigoLicenciaComercializacion) {
        this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
    }

    public BigInteger getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(BigInteger codigoInforme) {
        this.codigoInforme = codigoInforme;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCasilleroJudicial() {
        return casilleroJudicial;
    }

    public void setCasilleroJudicial(String casilleroJudicial) {
        this.casilleroJudicial = casilleroJudicial;
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

    public BigInteger getCodigoParroquida() {
        return codigoParroquida;
    }

    public void setCodigoParroquida(BigInteger codigoParroquida) {
        this.codigoParroquida = codigoParroquida;
    }

    public String getDescripcionMaterialComercializar() {
        return descripcionMaterialComercializar;
    }

    public void setDescripcionMaterialComercializar(String descripcionMaterialComercializar) {
        this.descripcionMaterialComercializar = descripcionMaterialComercializar;
    }

    public Date getFechaOtorgamientoLicencia() {
        return fechaOtorgamientoLicencia;
    }

    public void setFechaOtorgamientoLicencia(Date fechaOtorgamientoLicencia) {
        this.fechaOtorgamientoLicencia = fechaOtorgamientoLicencia;
    }

    public String getObservacionesLicencia() {
        return observacionesLicencia;
    }

    public void setObservacionesLicencia(String observacionesLicencia) {
        this.observacionesLicencia = observacionesLicencia;
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

    public Serializable getXmlData() {
        return xmlData;
    }

    public void setXmlData(Serializable xmlData) {
        this.xmlData = xmlData;
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

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public Date getFechaOtorga() {
        return fechaOtorga;
    }

    public void setFechaOtorga(Date fechaOtorga) {
        this.fechaOtorga = fechaOtorga;
    }

    public Date getFechaInscribe() {
        return fechaInscribe;
    }

    public void setFechaInscribe(Date fechaInscribe) {
        this.fechaInscribe = fechaInscribe;
    }

    public CatalogoDetalle getEstadoLicencia() {
        return estadoLicencia;
    }

    public void setEstadoLicencia(CatalogoDetalle estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public CatalogoDetalle getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(CatalogoDetalle tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public CatalogoDetalle getCodigoTipoMineral() {
        return codigoTipoMineral;
    }

    public void setCodigoTipoMineral(CatalogoDetalle codigoTipoMineral) {
        this.codigoTipoMineral = codigoTipoMineral;
    }

    public CatalogoDetalle getCodigoMineralInteres() {
        return codigoMineralInteres;
    }

    public void setCodigoMineralInteres(CatalogoDetalle codigoMineralInteres) {
        this.codigoMineralInteres = codigoMineralInteres;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoPersona() {
        if (tipoPersona != null && !tipoPersona.isEmpty()) {
            if (tipoPersona.equals("PN")) {
                tipoPersonaString = "Persona Natural";
            } else if (tipoPersona.equals("PJ")) {
                tipoPersonaString = "Persona Jurídica";
            }
        }
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isMigrada() {
        return migrada;
    }

    public void setMigrada(boolean migrada) {
        this.migrada = migrada;
    }

    public String getTipoPersonaString() {
        return tipoPersonaString;
    }

    public void setTipoPersonaString(String tipoPersonaString) {
        this.tipoPersonaString = tipoPersonaString;
    }
    /*public Resolucion getResolucion() {
    return resolucion;
    }
    public void setResolucion(Resolucion resolucion) {
    this.resolucion = resolucion;
    }*/

    public String getProvinciaString() {
        return provinciaString;
    }

    public void setProvinciaString(String provinciaString) {
        this.provinciaString = provinciaString;
    }

    public String getCantonString() {
        return cantonString;
    }

    public void setCantonString(String cantonString) {
        this.cantonString = cantonString;
    }

    public String getParroquiaString() {
        return parroquiaString;
    }

    public void setParroquiaString(String parroquiaString) {
        this.parroquiaString = parroquiaString;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLicenciaComercializacion != null ? codigoLicenciaComercializacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenciaComercializacion)) {
            return false;
        }
        LicenciaComercializacion other = (LicenciaComercializacion) object;
        if ((this.codigoLicenciaComercializacion == null && other.codigoLicenciaComercializacion != null) || (this.codigoLicenciaComercializacion != null && !this.codigoLicenciaComercializacion.equals(other.codigoLicenciaComercializacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LicenciaComercializacion{" + "codigoLicenciaComercializacion=" + codigoLicenciaComercializacion + ", codigoInforme=" + codigoInforme + ", numeroDocumento=" + numeroDocumento + ", nombre="
                + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", direccion=" + direccion + ", casilleroJudicial=" + casilleroJudicial + ", codigoProvincia=" + codigoProvincia + ", codigoCanton="
                + codigoCanton + ", codigoParroquida=" + codigoParroquida + ", descripcionMaterialComercializar=" + descripcionMaterialComercializar + ", fechaOtorgamientoLicencia=" + fechaOtorgamientoLicencia
                + ", observacionesLicencia=" + observacionesLicencia + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03 + ", campoReservado02="
                + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion="
                + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", codigoArcom=" + codigoArcom + ", fechaOtorga=" + fechaOtorga + ", fechaInscribe=" + fechaInscribe + ", estadoLicencia="
                + (estadoLicencia != null ? estadoLicencia.getCodigoCatalogoDetalle() : null) + ", tipoLicencia=" + (tipoLicencia != null ? tipoLicencia.getCodigoCatalogoDetalle() : null)
                + ", codigoTipoMineral=" + (codigoTipoMineral != null ? codigoTipoMineral.getCodigoCatalogoDetalle() : null) + ", codigoMineralInteres="
                + (codigoMineralInteres != null ? codigoMineralInteres.getCodigoCatalogoDetalle() : null) + ", correoElectronico=" + correoElectronico + ", observaciones=" + observaciones + ", tipoPersona=" + tipoPersona + ", migrada=" + migrada +'}';
    }

    public Boolean getMigrada() {
        return migrada;
    }

    public void setMigrada(Boolean migrada) {
        this.migrada = migrada;
    }

}
