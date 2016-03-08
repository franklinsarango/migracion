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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "solicitud", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_solicitud")
    private Long codigoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia_solicitud")
    private long secuenciaSolicitud;
    @Column(name = "codigo_informe")
    private BigInteger codigoInforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipo_solicitud")
    private String tipoSolicitud;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "codigo_usuario")
    private BigInteger codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento_solicitante")
    private String documentoSolicitante;
    @Size(max = 20)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Size(max = 20)
    @Column(name = "tipo_persona")
    private String tipoPersona;
    @Size(max = 200)
    @Column(name = "nombre_solicitante")
    private String nombreSolicitante;
    @Size(max = 200)
    @Column(name = "apellido_solicitante")
    private String apellidoSolicitante;
    @Column(name = "fecha_nacimiento_solicitante")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoSolicitante;
    @Column(name = "edad_solicitante")
    private Integer edadSolicitante;
    @Size(max = 200)
    @Column(name = "direccion_solicitante")
    private String direccionSolicitante;
    @Size(max = 200)
    @Column(name = "email_solicitante")
    private String emailSolicitante;
    @Size(max = 30)
    @Column(name = "telefono_convencional_solicitante")
    private String telefonoConvencionalSolicitante;
    @Size(max = 30)
    @Column(name = "telefono_celular_solicitante")
    private String telefonoCelularSolicitante;
    @Size(max = 30)
    @Column(name = "ciudad_solicitante")
    private String ciudadSolicitante;
    @Size(max = 20)
    @Column(name = "ruc_solicitante")
    private String rucSolicitante;
    @Size(max = 200)
    @Column(name = "nombre_area")
    private String nombreArea;
    @Size(max = 200)
    @Column(name = "nombre_razon_social")
    private String nombreRazonSocial;
    @Size(max = 200)
    @Column(name = "representante_legal")
    private String representanteLegal;
    @Size(max = 200)
    @Column(name = "nombre_representante_legal")
    private String nombreRepresentanteLegal;
    @Size(max = 200)
    @Column(name = "apellido_representante_legal")
    private String apellidoRepresentanteLegal;
    @Size(max = 40)
    @Column(name = "zona")
    private String zona;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Size(max = 20)
    @Column(name = "tipo_coordenada")
    private String tipoCoordenada;
    @Column(name = "plazo_concesion")
    private BigInteger plazoConcesion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numero_hectareas")
    private Double numeroHectareas;
    @Size(max = 100)
    @Column(name = "modalidad")
    private String modalidad;
    @Size(max = 100)
    @Column(name = "tipo_material")
    private String tipoMaterial;
    @Size(max = 100)
    @Column(name = "forma_explotacion")
    private String formaExplotacion;
    @Size(max = 200)
    @Column(name = "material_interes")
    private String materialInteres;
    @Column(name = "volumen_diario_explotacion")
    private Double volumenDiarioExplotacion;
    @Column(name = "volumen_total_explotacion")
    private Double volumenTotalExplotacion;
    @Size(max = 30)
    @Column(name = "casillero_judicial")
    private String casilleroJudicial;
    @Size(max = 15)
    @Column(name = "documento_concesionario_principal")
    private String documentoConcesionarioPrincipal;
    @Size(max = 200)
    @Column(name = "nombre_concesionario_principal")
    private String nombreConcesionarioPrincipal;
    @Size(max = 200)
    @Column(name = "apellido_concesionario_principal")
    private String apellidoConcesionarioPrincipal;
    @Size(max = 30)
    @Column(name = "codigo_censal")
    private String codigoCensal;
    @Column(name = "acepta_condiciones")
    private Boolean aceptaCondiciones;
    @Size(max = 255)
    @Column(name = "campo_reservado_10")
    private String campoReservado10;
    @Size(max = 255)
    @Column(name = "campo_reservado_09")
    private String campoReservado09;
    @Size(max = 255)
    @Column(name = "campo_reservado_08")
    private String campoReservado08;
    @Size(max = 255)
    @Column(name = "campo_reservado_07")
    private String campoReservado07;
    @Size(max = 255)
    @Column(name = "campo_reservado_06")
    private String campoReservado06;
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
    @Column(name = "estado_solicitud")
    private BigInteger estadoSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSolicitud")
    private List<SolicitudDetalle> solicitudDetalleList;
    @Column(name = "migrada")
    private boolean migrada;

    public Solicitud() {
    }

    public Solicitud(Long codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public Solicitud(Long codigoSolicitud, long secuenciaSolicitud, String tipoSolicitud, String documentoSolicitante) {
        this.codigoSolicitud = codigoSolicitud;
        this.secuenciaSolicitud = secuenciaSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.documentoSolicitante = documentoSolicitante;
    }

    public Long getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(Long codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public long getSecuenciaSolicitud() {
        return secuenciaSolicitud;
    }

    public void setSecuenciaSolicitud(long secuenciaSolicitud) {
        this.secuenciaSolicitud = secuenciaSolicitud;
    }

    public BigInteger getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(BigInteger codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public BigInteger getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(BigInteger codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getApellidoSolicitante() {
        return apellidoSolicitante;
    }

    public void setApellidoSolicitante(String apellidoSolicitante) {
        this.apellidoSolicitante = apellidoSolicitante;
    }

    public Date getFechaNacimientoSolicitante() {
        return fechaNacimientoSolicitante;
    }

    public void setFechaNacimientoSolicitante(Date fechaNacimientoSolicitante) {
        this.fechaNacimientoSolicitante = fechaNacimientoSolicitante;
    }

    public Integer getEdadSolicitante() {
        return edadSolicitante;
    }

    public void setEdadSolicitante(Integer edadSolicitante) {
        this.edadSolicitante = edadSolicitante;
    }

    public String getDireccionSolicitante() {
        return direccionSolicitante;
    }

    public void setDireccionSolicitante(String direccionSolicitante) {
        this.direccionSolicitante = direccionSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getTelefonoConvencionalSolicitante() {
        return telefonoConvencionalSolicitante;
    }

    public void setTelefonoConvencionalSolicitante(String telefonoConvencionalSolicitante) {
        this.telefonoConvencionalSolicitante = telefonoConvencionalSolicitante;
    }

    public String getTelefonoCelularSolicitante() {
        return telefonoCelularSolicitante;
    }

    public void setTelefonoCelularSolicitante(String telefonoCelularSolicitante) {
        this.telefonoCelularSolicitante = telefonoCelularSolicitante;
    }

    public String getCiudadSolicitante() {
        return ciudadSolicitante;
    }

    public void setCiudadSolicitante(String ciudadSolicitante) {
        this.ciudadSolicitante = ciudadSolicitante;
    }

    public String getRucSolicitante() {
        return rucSolicitante;
    }

    public void setRucSolicitante(String rucSolicitante) {
        this.rucSolicitante = rucSolicitante;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
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

    public String getTipoCoordenada() {
        return tipoCoordenada;
    }

    public void setTipoCoordenada(String tipoCoordenada) {
        this.tipoCoordenada = tipoCoordenada;
    }

    public BigInteger getPlazoConcesion() {
        return plazoConcesion;
    }

    public void setPlazoConcesion(BigInteger plazoConcesion) {
        this.plazoConcesion = plazoConcesion;
    }

    public Double getNumeroHectareas() {
        return numeroHectareas;
    }

    public void setNumeroHectareas(Double numeroHectareas) {
        this.numeroHectareas = numeroHectareas;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getFormaExplotacion() {
        return formaExplotacion;
    }

    public void setFormaExplotacion(String formaExplotacion) {
        this.formaExplotacion = formaExplotacion;
    }

    public String getMaterialInteres() {
        return materialInteres;
    }

    public void setMaterialInteres(String materialInteres) {
        this.materialInteres = materialInteres;
    }

    public Double getVolumenDiarioExplotacion() {
        return volumenDiarioExplotacion;
    }

    public void setVolumenDiarioExplotacion(Double volumenDiarioExplotacion) {
        this.volumenDiarioExplotacion = volumenDiarioExplotacion;
    }

    public Double getVolumenTotalExplotacion() {
        return volumenTotalExplotacion;
    }

    public void setVolumenTotalExplotacion(Double volumenTotalExplotacion) {
        this.volumenTotalExplotacion = volumenTotalExplotacion;
    }

    public String getCasilleroJudicial() {
        return casilleroJudicial;
    }

    public void setCasilleroJudicial(String casilleroJudicial) {
        this.casilleroJudicial = casilleroJudicial;
    }

    public String getDocumentoConcesionarioPrincipal() {
        return documentoConcesionarioPrincipal;
    }

    public void setDocumentoConcesionarioPrincipal(String documentoConcesionarioPrincipal) {
        this.documentoConcesionarioPrincipal = documentoConcesionarioPrincipal;
    }

    public String getNombreConcesionarioPrincipal() {
        return nombreConcesionarioPrincipal;
    }

    public void setNombreConcesionarioPrincipal(String nombreConcesionarioPrincipal) {
        this.nombreConcesionarioPrincipal = nombreConcesionarioPrincipal;
    }

    public String getApellidoConcesionarioPrincipal() {
        return apellidoConcesionarioPrincipal;
    }

    public void setApellidoConcesionarioPrincipal(String apellidoConcesionarioPrincipal) {
        this.apellidoConcesionarioPrincipal = apellidoConcesionarioPrincipal;
    }

    public String getCodigoCensal() {
        return codigoCensal;
    }

    public void setCodigoCensal(String codigoCensal) {
        this.codigoCensal = codigoCensal;
    }

    public Boolean getAceptaCondiciones() {
        return aceptaCondiciones;
    }

    public void setAceptaCondiciones(Boolean aceptaCondiciones) {
        this.aceptaCondiciones = aceptaCondiciones;
    }

    public String getCampoReservado10() {
        return campoReservado10;
    }

    public void setCampoReservado10(String campoReservado10) {
        this.campoReservado10 = campoReservado10;
    }

    public String getCampoReservado09() {
        return campoReservado09;
    }

    public void setCampoReservado09(String campoReservado09) {
        this.campoReservado09 = campoReservado09;
    }

    public String getCampoReservado08() {
        return campoReservado08;
    }

    public void setCampoReservado08(String campoReservado08) {
        this.campoReservado08 = campoReservado08;
    }

    public String getCampoReservado07() {
        return campoReservado07;
    }

    public void setCampoReservado07(String campoReservado07) {
        this.campoReservado07 = campoReservado07;
    }

    public String getCampoReservado06() {
        return campoReservado06;
    }

    public void setCampoReservado06(String campoReservado06) {
        this.campoReservado06 = campoReservado06;
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

    public BigInteger getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(BigInteger estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public List<SolicitudDetalle> getSolicitudDetalleList() {
        return solicitudDetalleList;
    }

    public void setSolicitudDetalleList(List<SolicitudDetalle> solicitudDetalleList) {
        this.solicitudDetalleList = solicitudDetalleList;
    }

    public boolean isMigrada() {
        return migrada;
    }

    public void setMigrada(boolean migrada) {
        this.migrada = migrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSolicitud != null ? codigoSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.codigoSolicitud == null && other.codigoSolicitud != null) || (this.codigoSolicitud != null && !this.codigoSolicitud.equals(other.codigoSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "codigoSolicitud=" + codigoSolicitud + ", secuenciaSolicitud=" + secuenciaSolicitud + ", codigoInforme=" + codigoInforme + ", tipoSolicitud=" + tipoSolicitud 
                + ", fechaSolicitud=" + fechaSolicitud + ", codigoUsuario=" + codigoUsuario + ", documentoSolicitante=" + documentoSolicitante + ", tipoDocumento=" + tipoDocumento + ", tipoPersona=" 
                + tipoPersona + ", nombreSolicitante=" + nombreSolicitante + ", apellidoSolicitante=" + apellidoSolicitante + ", fechaNacimientoSolicitante=" + fechaNacimientoSolicitante 
                + ", edadSolicitante=" + edadSolicitante + ", direccionSolicitante=" + direccionSolicitante + ", emailSolicitante=" + emailSolicitante + ", telefonoConvencionalSolicitante=" 
                + telefonoConvencionalSolicitante + ", telefonoCelularSolicitante=" + telefonoCelularSolicitante + ", ciudadSolicitante=" + ciudadSolicitante + ", rucSolicitante=" + rucSolicitante 
                + ", nombreArea=" + nombreArea + ", nombreRazonSocial=" + nombreRazonSocial + ", representanteLegal=" + representanteLegal + ", nombreRepresentanteLegal=" + nombreRepresentanteLegal 
                + ", apellidoRepresentanteLegal=" + apellidoRepresentanteLegal + ", zona=" + zona + ", codigoProvincia=" + codigoProvincia + ", codigoCanton=" + codigoCanton + ", codigoParroquia=" 
                + codigoParroquia + ", tipoCoordenada=" + tipoCoordenada + ", plazoConcesion=" + plazoConcesion + ", numeroHectareas=" + numeroHectareas + ", modalidad=" + modalidad + ", tipoMaterial=" 
                + tipoMaterial + ", formaExplotacion=" + formaExplotacion + ", materialInteres=" + materialInteres + ", volumenDiarioExplotacion=" + volumenDiarioExplotacion + ", volumenTotalExplotacion=" 
                + volumenTotalExplotacion + ", casilleroJudicial=" + casilleroJudicial + ", documentoConcesionarioPrincipal=" + documentoConcesionarioPrincipal + ", nombreConcesionarioPrincipal=" 
                + nombreConcesionarioPrincipal + ", apellidoConcesionarioPrincipal=" + apellidoConcesionarioPrincipal + ", codigoCensal=" + codigoCensal + ", aceptaCondiciones=" + aceptaCondiciones 
                + ", campoReservado10=" + campoReservado10 + ", campoReservado09=" + campoReservado09 + ", campoReservado08=" + campoReservado08 + ", campoReservado07=" + campoReservado07 + ", campoReservado06=" 
                + campoReservado06 + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 
                + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" 
                + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", estadoSolicitud=" + estadoSolicitud + ", migrada=" + migrada + '}';
    }

}
