/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "instrumento", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Instrumento.findAll", query = "SELECT i FROM Instrumento i")})
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_instrumento")
    private Long codigoInstrumento;
    @Column(name = "numero_tramite")
    private BigInteger numeroTramite;
    @Column(name = "numero_repertorio")
    private BigInteger numeroRepertorio;
    @Column(name = "codigo_solicitud")
    private BigInteger codigoSolicitud;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Column(name = "codigo_localidad")
    private BigInteger codigoLocalidad;
    @Column(name = "resolucion_id")
    private BigInteger resolucionId;
    @Size(max = 13)
    @Column(name = "numero_documento_compareciente")
    private String numeroDocumentoCompareciente;
    @Size(max = 30)
    @Column(name = "nombre_compareciente")
    private String nombreCompareciente;
    @Size(max = 30)
    @Column(name = "apellido_compareciente")
    private String apellidoCompareciente;
    @Size(max = 13)
    @Column(name = "numero_documento_otorgante")
    private String numeroDocumentoOtorgante;
    @Size(max = 30)
    @Column(name = "nombre_otorgante")
    private String nombreOtorgante;
    @Size(max = 30)
    @Column(name = "apellido_otorgante")
    private String apellidoOtorgante;
    @Column(name = "fecha_otorgamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorgamiento;
    @Size(max = 80)
    @Column(name = "notaria")
    private String notaria;
    @Column(name = "fecha_protocolizacion_notaria")
    @Temporal(TemporalType.DATE)
    private Date fechaProtocolizacionNotaria;
    @Size(max = 400)
    @Column(name = "naturaleza_acto")
    private String naturalezaActo;
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @JoinColumn(name = "libro_inscripcion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle libroInscripcion;
    @Column(name = "tomo_inscripcion")
    private BigInteger tomoInscripcion;
    @Column(name = "numero_inscripcion")
    private BigInteger numeroInscripcion;
    @Column(name = "folio_inscripcion")
    private BigInteger folioInscripcion;
    @Size(max = 1000)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 1000)
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
    @Column(name = "codigo_tramite")
    private BigInteger codigoTramite;
    @Size(max = 50)
    @Column(name = "entidad_tramite")
    private String entidadTramite;
    @Column(name = "aplica_pago")
    private Boolean aplicaPago;
    @Column(name = "codigo_pago")
    private BigInteger codigoPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_instrumento")
    private BigDecimal valorInstrumento;
    @Column(name = "codigo_costo_servicios")
    private BigInteger codigoCostoServicios;
    @Column(name = "codigo_concepto_pago")
    private BigInteger codigoConceptoPago;
    @JoinColumn(name = "tipo_instrumento", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInstrumento;
    @JoinColumn(name = "tipo_documento_otorgamiento", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoDocumentoOtorgamiento;
    @JoinColumn(name = "clase_inscripcion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle claseInscripcion;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne
    private AreaMinera codigoArea;
    @JoinColumn(name = "codigo_tipo_mineria", referencedColumnName = "codigo_tipo_mineria")
    @ManyToOne
    private TipoMineria codigoTipoMineria;
    @JoinColumn(name = "codigo_concesion_minera", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera concesionMinera;
    @JoinColumn(name = "codigo_planta_beneficio", referencedColumnName = "codigo_planta_beneficio")
    @ManyToOne
    private PlantaBeneficio plantaBeneficio;
    @JoinColumn(name = "codigo_licencia_comercializacion", referencedColumnName = "codigo_licencia_comercializacion")
    @ManyToOne
    private LicenciaComercializacion licenciaComercializacion;
    @Column(name = "codigo_usuario")
    private Long codigoUsuario;
    @Column(name = "documento_titular", length = 13)
    private String documentoTitular;
    @Column(name = "institucion_otorgante")
    private boolean institucionOtorgante;
    @Column(name = "nombre_institucion_otorgante", length = 255)
    private String nombreInstitucionOtorgante;
    @Column(name = "datos_actualizados")
    private boolean datosActualizados;
    @JoinColumn(name = "codigo_sujeto_minero", referencedColumnName = "codigo_sujeto_minero")
    @ManyToOne
    private SujetoMinero sujetoMinero;
    @Transient
    private String codigoArcomTransient;

    public Instrumento() {
    }

    public Instrumento(Long codigoInstrumento) {
        this.codigoInstrumento = codigoInstrumento;
    }

    public Long getCodigoInstrumento() {
        return codigoInstrumento;
    }

    public void setCodigoInstrumento(Long codigoInstrumento) {
        this.codigoInstrumento = codigoInstrumento;
    }

    public BigInteger getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(BigInteger numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public BigInteger getNumeroRepertorio() {
        return numeroRepertorio;
    }

    public void setNumeroRepertorio(BigInteger numeroRepertorio) {
        this.numeroRepertorio = numeroRepertorio;
    }

    public BigInteger getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(BigInteger codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
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

    public BigInteger getResolucionId() {
        return resolucionId;
    }

    public void setResolucionId(BigInteger resolucionId) {
        this.resolucionId = resolucionId;
    }

    public String getNumeroDocumentoCompareciente() {
        return numeroDocumentoCompareciente;
    }

    public void setNumeroDocumentoCompareciente(String numeroDocumentoCompareciente) {
        this.numeroDocumentoCompareciente = numeroDocumentoCompareciente;
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

    public String getNumeroDocumentoOtorgante() {
        return numeroDocumentoOtorgante;
    }

    public void setNumeroDocumentoOtorgante(String numeroDocumentoOtorgante) {
        this.numeroDocumentoOtorgante = numeroDocumentoOtorgante;
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

    public Date getFechaOtorgamiento() {
        return fechaOtorgamiento;
    }

    public void setFechaOtorgamiento(Date fechaOtorgamiento) {
        this.fechaOtorgamiento = fechaOtorgamiento;
    }

    public String getNotaria() {
        return notaria;
    }

    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    public Date getFechaProtocolizacionNotaria() {
        return fechaProtocolizacionNotaria;
    }

    public void setFechaProtocolizacionNotaria(Date fechaProtocolizacionNotaria) {
        this.fechaProtocolizacionNotaria = fechaProtocolizacionNotaria;
    }

    public String getNaturalezaActo() {
        return naturalezaActo;
    }

    public void setNaturalezaActo(String naturalezaActo) {
        this.naturalezaActo = naturalezaActo;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public CatalogoDetalle getLibroInscripcion() {
        return libroInscripcion;
    }

    public void setLibroInscripcion(CatalogoDetalle libroInscripcion) {
        this.libroInscripcion = libroInscripcion;
    }

    public BigInteger getTomoInscripcion() {
        return tomoInscripcion;
    }

    public void setTomoInscripcion(BigInteger tomoInscripcion) {
        this.tomoInscripcion = tomoInscripcion;
    }

    public BigInteger getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public void setNumeroInscripcion(BigInteger numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public BigInteger getFolioInscripcion() {
        return folioInscripcion;
    }

    public void setFolioInscripcion(BigInteger folioInscripcion) {
        this.folioInscripcion = folioInscripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public BigInteger getCodigoTramite() {
        return codigoTramite;
    }

    public void setCodigoTramite(BigInteger codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

    public String getEntidadTramite() {
        return entidadTramite;
    }

    public void setEntidadTramite(String entidadTramite) {
        this.entidadTramite = entidadTramite;
    }

    public Boolean getAplicaPago() {
        return aplicaPago;
    }

    public void setAplicaPago(Boolean aplicaPago) {
        this.aplicaPago = aplicaPago;
    }

    public BigInteger getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(BigInteger codigoPago) {
        this.codigoPago = codigoPago;
    }

    public BigDecimal getValorInstrumento() {
        return valorInstrumento;
    }

    public void setValorInstrumento(BigDecimal valorInstrumento) {
        this.valorInstrumento = valorInstrumento;
    }

    public BigInteger getCodigoCostoServicios() {
        return codigoCostoServicios;
    }

    public void setCodigoCostoServicios(BigInteger codigoCostoServicios) {
        this.codigoCostoServicios = codigoCostoServicios;
    }

    public BigInteger getCodigoConceptoPago() {
        return codigoConceptoPago;
    }

    public void setCodigoConceptoPago(BigInteger codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public CatalogoDetalle getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(CatalogoDetalle tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }

    public CatalogoDetalle getTipoDocumentoOtorgamiento() {
        return tipoDocumentoOtorgamiento;
    }

    public void setTipoDocumentoOtorgamiento(CatalogoDetalle tipoDocumentoOtorgamiento) {
        this.tipoDocumentoOtorgamiento = tipoDocumentoOtorgamiento;
    }

    public CatalogoDetalle getClaseInscripcion() {
        return claseInscripcion;
    }

    public void setClaseInscripcion(CatalogoDetalle claseInscripcion) {
        this.claseInscripcion = claseInscripcion;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    public TipoMineria getCodigoTipoMineria() {
        return codigoTipoMineria;
    }

    public void setCodigoTipoMineria(TipoMineria codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    public ConcesionMinera getConcesionMinera() {
        if (concesionMinera != null) {
            codigoArcomTransient = concesionMinera.getCodigoArcom();
            documentoTitular = concesionMinera.getDocumentoConcesionarioPrincipal();
        }
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public PlantaBeneficio getPlantaBeneficio() {
        if (plantaBeneficio != null) {
            codigoArcomTransient = plantaBeneficio.getCodigoArcom();
            documentoTitular = plantaBeneficio.getNumeroDocumentoRepresentanteLegal();
        }
        return plantaBeneficio;
    }

    public void setPlantaBeneficio(PlantaBeneficio plantaBeneficio) {
        this.plantaBeneficio = plantaBeneficio;
    }

    public LicenciaComercializacion getLicenciaComercializacion() {
        if (licenciaComercializacion != null) {
            codigoArcomTransient = licenciaComercializacion.getCodigoArcom();
            documentoTitular = licenciaComercializacion.getNumeroDocumento();
        }
        return licenciaComercializacion;
    }

    public void setLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) {
        this.licenciaComercializacion = licenciaComercializacion;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getDocumentoTitular() {
        return documentoTitular;
    }

    public void setDocumentoTitular(String documentoTitular) {
        this.documentoTitular = documentoTitular;
    }

    public boolean isInstitucionOtorgante() {
        return institucionOtorgante;
    }

    public void setInstitucionOtorgante(boolean institucionOtorgante) {
        this.institucionOtorgante = institucionOtorgante;
    }

    public String getNombreInstitucionOtorgante() {
        return nombreInstitucionOtorgante;
    }

    public void setNombreInstitucionOtorgante(String nombreInstitucionOtorgante) {
        this.nombreInstitucionOtorgante = nombreInstitucionOtorgante;
    }

    public SujetoMinero getSujetoMinero() {
        return sujetoMinero;
    }

    public void setSujetoMinero(SujetoMinero sujetoMinero) {
        this.sujetoMinero = sujetoMinero;
    }

    public boolean isDatosActualizados() {
        return datosActualizados;
    }

    public void setDatosActualizados(boolean datosActualizados) {
        this.datosActualizados = datosActualizados;
    }

    public String getCodigoArcomTransient() {
        return codigoArcomTransient;
    }

    public void setCodigoArcomTransient(String codigoArcomTransient) {
        this.codigoArcomTransient = codigoArcomTransient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoInstrumento != null ? codigoInstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.codigoInstrumento == null && other.codigoInstrumento != null) || (this.codigoInstrumento != null && !this.codigoInstrumento.equals(other.codigoInstrumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Instrumento{" + "codigoInstrumento=" + codigoInstrumento + ", numeroTramite=" + numeroTramite + ", numeroRepertorio=" + numeroRepertorio + ", codigoSolicitud=" + codigoSolicitud 
                + ", codigoProvincia=" + codigoProvincia + ", codigoCanton=" + codigoCanton + ", codigoParroquia=" + codigoParroquia + ", codigoLocalidad=" + codigoLocalidad + ", resolucionId=" + resolucionId 
                + ", numeroDocumentoCompareciente=" + numeroDocumentoCompareciente + ", nombreCompareciente=" + nombreCompareciente + ", apellidoCompareciente=" + apellidoCompareciente + ", numeroDocumentoOtorgante=" 
                + numeroDocumentoOtorgante + ", nombreOtorgante=" + nombreOtorgante + ", apellidoOtorgante=" + apellidoOtorgante + ", fechaOtorgamiento=" + fechaOtorgamiento + ", notaria=" + notaria 
                + ", fechaProtocolizacionNotaria=" + fechaProtocolizacionNotaria + ", naturalezaActo=" + naturalezaActo + ", fechaInscripcion=" + fechaInscripcion + ", libroInscripcion=" + libroInscripcion 
                + ", tomoInscripcion=" + tomoInscripcion + ", numeroInscripcion=" + numeroInscripcion + ", folioInscripcion=" + folioInscripcion + ", observaciones=" + observaciones 
                + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 
                + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion 
                + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", codigoTramite=" + codigoTramite + ", entidadTramite=" + entidadTramite 
                + ", aplicaPago=" + aplicaPago + ", codigoPago=" + codigoPago + ", valorInstrumento=" + valorInstrumento + ", codigoCostoServicios=" + codigoCostoServicios 
                + ", codigoConceptoPago=" + codigoConceptoPago  
                + ", tipoInstrumento=" + (tipoInstrumento != null ? tipoInstrumento.getCodigoCatalogoDetalle() : null) + ", tipoDocumentoOtorgamiento=" + (tipoDocumentoOtorgamiento != null ? tipoDocumentoOtorgamiento.getCodigoCatalogoDetalle() : null) 
                + ", claseInscripcion=" + (claseInscripcion != null ? claseInscripcion.getCodigoCatalogoDetalle() : null) + ", codigoArea=" + (codigoArea != null ? codigoArea.getCodigoAreaMinera() : null) 
                + ", codigoTipoMineria=" + (codigoTipoMineria != null ? codigoTipoMineria.getCodigoTipoMineria() : null) + ", concesionMinera=" + (concesionMinera != null ? concesionMinera.getCodigoConcesion() : null) 
                + ", plantaBeneficio=" + (plantaBeneficio != null ? plantaBeneficio.getCodigoPlantaBeneficio() : null) 
                + ", licenciaComercializacion=" + (licenciaComercializacion != null ? licenciaComercializacion.getCodigoLicenciaComercializacion() : null) + ", codigoUsuario=" + codigoUsuario + ", documentoTitular=" + documentoTitular + ", institucionOtorgante=" + institucionOtorgante 
                + ", nombreInstitucionOtorgante=" + nombreInstitucionOtorgante + ", datosActualizados=" + datosActualizados + ", sujetoMinero=" + (sujetoMinero != null ? sujetoMinero.getCodigoSujetoMinero() : null) + ", codigoArcomTransient=" + codigoArcomTransient + '}';
    }
}
