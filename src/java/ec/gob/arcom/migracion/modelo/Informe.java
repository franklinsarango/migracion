/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "informe", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Informe.findAll", query = "SELECT i FROM Informe i")})
public class Informe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_informe")
    private Long codigoInforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_informe")
    private String tipoInforme;
    @Size(max = 50)
    @Column(name = "clase_informe")
    private String claseInforme;
    @Column(name = "codigo_solicitud")
    private BigInteger codigoSolicitud;
    @Column(name = "secuencia_solicitud")
    private BigInteger secuenciaSolicitud;
    @Size(max = 30)
    @Column(name = "tipo_solicitud")
    private String tipoSolicitud;
    @Column(name = "numero_guia")
    private BigInteger numeroGuia;
    @Size(max = 30)
    @Column(name = "numero_memorando")
    private String numeroMemorando;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Column(name = "codigo_concesion")
    private BigInteger codigoConcesion;
    @Column(name = "codigo_area")
    private BigInteger codigoArea;
    @Size(max = 15)
    @Column(name = "numero_documento_persona")
    private String numeroDocumentoPersona;
    @Column(name = "codigo_tramite")
    private BigInteger codigoTramite;
    @Size(max = 80)
    @Column(name = "entidad_tramite")
    private String entidadTramite;
    @Column(name = "fecha_informe")
    @Temporal(TemporalType.DATE)
    private Date fechaInforme;
    @Column(name = "emite_clausula")
    private Boolean emiteClausula;
    @Column(name = "cumple")
    private Boolean cumple;
    @Size(max = 1000)
    @Column(name = "antecedentes")
    private String antecedentes;
    @Size(max = 1000)
    @Column(name = "consideraciones")
    private String consideraciones;
    @Size(max = 1000)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 1000)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    @Column(name = "codigo_adjunto")
    private BigInteger codigoAdjunto;
    @Size(max = 300)
    @Column(name = "url_documento_adjunto")
    private String urlDocumentoAdjunto;
    @Column(name = "xml_data")
    private Serializable xmlData;
    @Column(name = "fecha_validacion")
    @Temporal(TemporalType.DATE)
    private Date fechaValidacion;
    @Size(max = 1000)
    @Column(name = "observacion_validacion")
    private String observacionValidacion;
    @Column(name = "validado")
    private Boolean validado;
    @Column(name = "codigo_pago")
    private BigInteger codigoPago;
    @Column(name = "confirma_pago")
    private Boolean confirmaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_patente")
    private BigDecimal costoPatente;
    @Column(name = "valor_regalia")
    private BigDecimal valorRegalia;
    @Column(name = "valor_utilidad")
    private BigDecimal valorUtilidad;
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
    @Size(max = 50)
    @Column(name = "destinatario")
    private String destinatario;
    @Size(max = 50)
    @Column(name = "remitente")
    private String remitente;
    @Size(max = 100)
    @Column(name = "cargo_remitente")
    private String cargoRemitente;
    @JoinColumn(name = "codigo_periodo", referencedColumnName = "codigo_periodo")
    @ManyToOne
    private Periodo codigoPeriodo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "informe1")
    private Informe informe;
    @JoinColumn(name = "codigo_informe", referencedColumnName = "codigo_informe", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Informe informe1;
    @JoinColumn(name = "estado_informe", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoInforme;
    @OneToMany(mappedBy = "codigoInforme")
    private Collection<MaquinariaConcesion> maquinariaConcesionCollection;

    public Informe() {
    }

    public Informe(Long codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public Informe(Long codigoInforme, String tipoInforme) {
        this.codigoInforme = codigoInforme;
        this.tipoInforme = tipoInforme;
    }

    public Long getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(Long codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public String getClaseInforme() {
        return claseInforme;
    }

    public void setClaseInforme(String claseInforme) {
        this.claseInforme = claseInforme;
    }

    public BigInteger getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(BigInteger codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public BigInteger getSecuenciaSolicitud() {
        return secuenciaSolicitud;
    }

    public void setSecuenciaSolicitud(BigInteger secuenciaSolicitud) {
        this.secuenciaSolicitud = secuenciaSolicitud;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public BigInteger getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(BigInteger numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getNumeroMemorando() {
        return numeroMemorando;
    }

    public void setNumeroMemorando(String numeroMemorando) {
        this.numeroMemorando = numeroMemorando;
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

    public BigInteger getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(BigInteger codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public BigInteger getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(BigInteger codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumeroDocumentoPersona() {
        return numeroDocumentoPersona;
    }

    public void setNumeroDocumentoPersona(String numeroDocumentoPersona) {
        this.numeroDocumentoPersona = numeroDocumentoPersona;
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

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public Boolean getEmiteClausula() {
        return emiteClausula;
    }

    public void setEmiteClausula(Boolean emiteClausula) {
        this.emiteClausula = emiteClausula;
    }

    public Boolean getCumple() {
        return cumple;
    }

    public void setCumple(Boolean cumple) {
        this.cumple = cumple;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getConsideraciones() {
        return consideraciones;
    }

    public void setConsideraciones(String consideraciones) {
        this.consideraciones = consideraciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public BigInteger getCodigoAdjunto() {
        return codigoAdjunto;
    }

    public void setCodigoAdjunto(BigInteger codigoAdjunto) {
        this.codigoAdjunto = codigoAdjunto;
    }

    public String getUrlDocumentoAdjunto() {
        return urlDocumentoAdjunto;
    }

    public void setUrlDocumentoAdjunto(String urlDocumentoAdjunto) {
        this.urlDocumentoAdjunto = urlDocumentoAdjunto;
    }

    public Serializable getXmlData() {
        return xmlData;
    }

    public void setXmlData(Serializable xmlData) {
        this.xmlData = xmlData;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public String getObservacionValidacion() {
        return observacionValidacion;
    }

    public void setObservacionValidacion(String observacionValidacion) {
        this.observacionValidacion = observacionValidacion;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public BigInteger getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(BigInteger codigoPago) {
        this.codigoPago = codigoPago;
    }

    public Boolean getConfirmaPago() {
        return confirmaPago;
    }

    public void setConfirmaPago(Boolean confirmaPago) {
        this.confirmaPago = confirmaPago;
    }

    public BigDecimal getCostoPatente() {
        return costoPatente;
    }

    public void setCostoPatente(BigDecimal costoPatente) {
        this.costoPatente = costoPatente;
    }

    public BigDecimal getValorRegalia() {
        return valorRegalia;
    }

    public void setValorRegalia(BigDecimal valorRegalia) {
        this.valorRegalia = valorRegalia;
    }

    public BigDecimal getValorUtilidad() {
        return valorUtilidad;
    }

    public void setValorUtilidad(BigDecimal valorUtilidad) {
        this.valorUtilidad = valorUtilidad;
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

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getCargoRemitente() {
        return cargoRemitente;
    }

    public void setCargoRemitente(String cargoRemitente) {
        this.cargoRemitente = cargoRemitente;
    }

    public Periodo getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(Periodo codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public Informe getInforme1() {
        return informe1;
    }

    public void setInforme1(Informe informe1) {
        this.informe1 = informe1;
    }

    public CatalogoDetalle getEstadoInforme() {
        return estadoInforme;
    }

    public void setEstadoInforme(CatalogoDetalle estadoInforme) {
        this.estadoInforme = estadoInforme;
    }

    public Collection<MaquinariaConcesion> getMaquinariaConcesionCollection() {
        return maquinariaConcesionCollection;
    }

    public void setMaquinariaConcesionCollection(Collection<MaquinariaConcesion> maquinariaConcesionCollection) {
        this.maquinariaConcesionCollection = maquinariaConcesionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoInforme != null ? codigoInforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informe)) {
            return false;
        }
        Informe other = (Informe) object;
        if ((this.codigoInforme == null && other.codigoInforme != null) || (this.codigoInforme != null && !this.codigoInforme.equals(other.codigoInforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.Informe[ codigoInforme=" + codigoInforme + " ]";
    }

}
