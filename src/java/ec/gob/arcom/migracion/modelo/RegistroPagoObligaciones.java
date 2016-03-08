/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import ec.gob.arcom.migracion.util.Comunes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@Table(name = "registro_pago_obligaciones", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "RegistroPagoObligaciones.findAll", query = "SELECT r FROM RegistroPagoObligaciones r")})
public class RegistroPagoObligaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_registro")
    private Long codigoRegistro;
    @Column(name = "codigo_informe")
    private BigInteger codigoInforme;
    //@Column(name = "codigo_licencia_comercializacion")
    //private BigInteger codigoLicenciaComercializacion;
    //@Column(name = "codigo_planta_beneficio")
    //private BigInteger codigoPlantaBeneficio;
    @Column(name = "fecha_emision_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaEmisionPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pago")
    private BigDecimal valorPago;
    @Size(max = 20)
    @Column(name = "numero_comprobante_banco")
    private String numeroComprobanteBanco;
    @Size(max = 20)
    @Column(name = "documento_persona_pago")
    private String documentoPersonaPago;
    @Size(max = 60)
    @Column(name = "nombre_persona_pago")
    private String nombrePersonaPago;
    @Size(max = 40)
    @Column(name = "apellido_persona_pago")
    private String apellidoPersonaPago;
    @Size(max = 20)
    @Column(name = "numero_comprobante_arcom")
    private String numeroComprobanteArcom;
    //@Column(name = "xml_data")
    //private Serializable xmlData;
    @Column(name = "codigo_solicitud")
    private BigInteger codigoSolicitud;
    @Column(name = "numero_tramite")
    private BigInteger numeroTramite;
    @Size(max = 80)
    @Column(name = "entidad_tramite")
    private String entidadTramite;
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
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "costo")
    private BigDecimal costo;
    @Size(max = 800)
    @Column(name = "observacion_analista_economico")
    private String observacionAnalistaEconomico;
    @Column(name = "fecha_revision_analista_economico")
    @Temporal(TemporalType.DATE)
    private Date fechaRevisionAnalistaEconomico;
    @Column(name = "aprobado_analista_economico")
    private Boolean aprobadoAnalistaEconomico;
    @Size(max = 800)
    @Column(name = "observacion_coordinador_regional")
    private String observacionCoordinadorRegional;
    @Column(name = "fecha_revision_coordinador_regional")
    @Temporal(TemporalType.DATE)
    private Date fechaRevisionCoordinadorRegional;
    @Column(name = "aprobado_coordinador_regional")
    private Boolean aprobadoCoordinadorRegional;
    @Column(name = "valor_adicional")
    private BigDecimal valorAdicional;
    @Size(max = 800)
    @Column(name = "descripcion_motivo_valor_adicional")
    private String descripcionMotivoValorAdicional;
    @Column(name = "valor_total_pago")
    private BigDecimal valorTotalPago;
    @Column(name = "valor_pagado_usuario")
    private BigDecimal valorPagadoUsuario;
    @Column(name = "valor_concepto_pago")
    private BigDecimal valorConceptoPago;
    @Column(name = "subsanar_pago")
    private Boolean subsanarPago;
    @Column(name = "presenta_documentos")
    private Boolean presentaDocumentos;
    @Size(max = 500)
    @Column(name = "codigos_concesiones")
    private String codigosConcesiones;
    @Column(name = "recaudacion_sri")
    private BigDecimal recaudacionSri;
    @Column(name = "valor_subsanar")
    private BigDecimal valorSubsanar;
    @Column(name = "comprobante_electronico")
    private BigInteger comprobanteElectronico;
    @Column(name = "codigo_impuesto")
    private BigInteger codigoImpuesto;
    @Size(max = 1000)
    @Column(name = "observacion_recepcionista")
    private String observacionRecepcionista;
    @Column(name = "numero_formulario")
    private BigInteger numeroFormulario;
    @Size(max = 500)
    @Column(name = "codigos_plantas")
    private String codigosPlantas;
    @Size(max = 500)
    @Column(name = "codigos_licencias")
    private String codigosLicencias;
    @Column(name = "fecha_presentacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacion;
    @Column(name = "valor_calculado_arcom")
    private BigDecimal valorCalculadoArcom;
    @Column(name = "tiene_derecho_minero")
    private Boolean tieneDerechoMinero;
    @Column(name = "codigo_usuario_delegado")
    private BigInteger codigoUsuarioDelegado;
    @OneToMany(mappedBy = "pagoPadre")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @JoinColumn(name = "pago_padre", referencedColumnName = "codigo_registro")
    @ManyToOne
    private RegistroPagoObligaciones pagoPadre;
    @JoinColumn(name = "codigo_periodo", referencedColumnName = "codigo_parametro")
    @ManyToOne
    private ParametroSistema codigoPeriodo;
    @JoinColumn(name = "lugar_emision_pago", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad lugarEmisionPago;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera codigoConcesion;
    @JoinColumn(name = "codigo_concepto_pago", referencedColumnName = "codigo_concepto_pago")
    @ManyToOne
    private ConceptoPago codigoConceptoPago;
    @JoinColumn(name = "codigo_tipo_servicio", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoTipoServicio;
    @JoinColumn(name = "estado_pago", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoPago;
    @JoinColumn(name = "tipo_pago", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoPago;
    @JoinColumn(name = "codigo_banco", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoBanco;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne
    private AreaMinera codigoArea;
    @Column(name = "codigo_tipo_registro")
    private Long codigoTipoRegistro;
    @JoinColumn(name = "codigo_licencia_comercializacion", referencedColumnName = "codigo_licencia_comercializacion")
    @ManyToOne
    private LicenciaComercializacion codigoLicenciaComercializacion;
    @JoinColumn(name = "codigo_planta_beneficio", referencedColumnName = "codigo_planta_beneficio")
    @ManyToOne
    private PlantaBeneficio codigoPlantaBeneficio;
    //@JoinColumn(name = "codigo_sujeto_minero", referencedColumnName = "codigo_sujeto_minero")
    //@ManyToOne
    @Transient
    private SujetoMinero codigoSujetoMinero;
    @Transient
    private Long codigoDerechoMinero;
    @Transient
    private String nombreDerechoMinero;
    @Transient
    private Double superficie;
    @Transient
    private BigInteger codigoProvincia;
    @Transient
    private BigInteger codigoCanton;
    @Transient
    private BigInteger codigoParroquia;
    @Transient
    private Long codigoRegimen;
    @Transient
    private Long codigoFase;
    @Transient
    private Long codigoTipoMineral;
    @Transient
    private String numeroRuc;
    @Transient
    private String concesionario;
    @Transient
    private BigDecimal valorReferenciaEntregaImpresa;

    public RegistroPagoObligaciones() {
    }

    public RegistroPagoObligaciones(Long codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public Long getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(Long codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public BigInteger getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(BigInteger codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    /*public BigInteger getCodigoLicenciaComercializacion() {
     return codigoLicenciaComercializacion;
     }

     public void setCodigoLicenciaComercializacion(BigInteger codigoLicenciaComercializacion) {
     this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
     }

     public BigInteger getCodigoPlantaBeneficio() {
     return codigoPlantaBeneficio;
     }

     public void setCodigoPlantaBeneficio(BigInteger codigoPlantaBeneficio) {
     this.codigoPlantaBeneficio = codigoPlantaBeneficio;
     }*/
    public Date getFechaEmisionPago() {
        return fechaEmisionPago;
    }

    public void setFechaEmisionPago(Date fechaEmisionPago) {
        this.fechaEmisionPago = fechaEmisionPago;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getNumeroComprobanteBanco() {
        return numeroComprobanteBanco;
    }

    public void setNumeroComprobanteBanco(String numeroComprobanteBanco) {
        this.numeroComprobanteBanco = numeroComprobanteBanco;
    }

    public String getDocumentoPersonaPago() {
        return documentoPersonaPago;
    }

    public void setDocumentoPersonaPago(String documentoPersonaPago) {
        this.documentoPersonaPago = documentoPersonaPago;
    }

    public String getNombrePersonaPago() {
        return nombrePersonaPago;
    }

    public void setNombrePersonaPago(String nombrePersonaPago) {
        this.nombrePersonaPago = nombrePersonaPago;
    }

    public String getApellidoPersonaPago() {
        return apellidoPersonaPago;
    }

    public void setApellidoPersonaPago(String apellidoPersonaPago) {
        this.apellidoPersonaPago = apellidoPersonaPago;
    }

    public String getNumeroComprobanteArcom() {
        return numeroComprobanteArcom;
    }

    public void setNumeroComprobanteArcom(String numeroComprobanteArcom) {
        this.numeroComprobanteArcom = numeroComprobanteArcom;
    }

    /*public Serializable getXmlData() {
     return xmlData;
     }

     public void setXmlData(Serializable xmlData) {
     this.xmlData = xmlData;
     }*/
    public BigInteger getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(BigInteger codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public BigInteger getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(BigInteger numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public String getEntidadTramite() {
        return entidadTramite;
    }

    public void setEntidadTramite(String entidadTramite) {
        this.entidadTramite = entidadTramite;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getObservacionAnalistaEconomico() {
        return observacionAnalistaEconomico;
    }

    public void setObservacionAnalistaEconomico(String observacionAnalistaEconomico) {
        this.observacionAnalistaEconomico = observacionAnalistaEconomico;
    }

    public Date getFechaRevisionAnalistaEconomico() {
        return fechaRevisionAnalistaEconomico;
    }

    public void setFechaRevisionAnalistaEconomico(Date fechaRevisionAnalistaEconomico) {
        this.fechaRevisionAnalistaEconomico = fechaRevisionAnalistaEconomico;
    }

    public Boolean getAprobadoAnalistaEconomico() {
        return aprobadoAnalistaEconomico;
    }

    public void setAprobadoAnalistaEconomico(Boolean aprobadoAnalistaEconomico) {
        this.aprobadoAnalistaEconomico = aprobadoAnalistaEconomico;
    }

    public String getObservacionCoordinadorRegional() {
        return observacionCoordinadorRegional;
    }

    public void setObservacionCoordinadorRegional(String observacionCoordinadorRegional) {
        this.observacionCoordinadorRegional = observacionCoordinadorRegional;
    }

    public Date getFechaRevisionCoordinadorRegional() {
        return fechaRevisionCoordinadorRegional;
    }

    public void setFechaRevisionCoordinadorRegional(Date fechaRevisionCoordinadorRegional) {
        this.fechaRevisionCoordinadorRegional = fechaRevisionCoordinadorRegional;
    }

    public Boolean getAprobadoCoordinadorRegional() {
        return aprobadoCoordinadorRegional;
    }

    public void setAprobadoCoordinadorRegional(Boolean aprobadoCoordinadorRegional) {
        this.aprobadoCoordinadorRegional = aprobadoCoordinadorRegional;
    }

    public BigDecimal getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(BigDecimal valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public String getDescripcionMotivoValorAdicional() {
        return descripcionMotivoValorAdicional;
    }

    public void setDescripcionMotivoValorAdicional(String descripcionMotivoValorAdicional) {
        this.descripcionMotivoValorAdicional = descripcionMotivoValorAdicional;
    }

    public BigDecimal getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(BigDecimal valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public BigDecimal getValorPagadoUsuario() {
        return valorPagadoUsuario;
    }

    public void setValorPagadoUsuario(BigDecimal valorPagadoUsuario) {
        this.valorPagadoUsuario = valorPagadoUsuario;
    }

    public BigDecimal getValorConceptoPago() {
        return valorConceptoPago;
    }

    public void setValorConceptoPago(BigDecimal valorConceptoPago) {
        this.valorConceptoPago = valorConceptoPago;
    }

    public Boolean getSubsanarPago() {
        return subsanarPago;
    }

    public void setSubsanarPago(Boolean subsanarPago) {
        this.subsanarPago = subsanarPago;
    }

    public Boolean getPresentaDocumentos() {
        return presentaDocumentos;
    }

    public void setPresentaDocumentos(Boolean presentaDocumentos) {
        this.presentaDocumentos = presentaDocumentos;
    }

    public String getCodigosConcesiones() {
        return codigosConcesiones;
    }

    public void setCodigosConcesiones(String codigosConcesiones) {
        this.codigosConcesiones = codigosConcesiones;
    }

    public BigDecimal getRecaudacionSri() {
        return recaudacionSri;
    }

    public void setRecaudacionSri(BigDecimal recaudacionSri) {
        this.recaudacionSri = recaudacionSri;
    }

    public BigDecimal getValorSubsanar() {
        return valorSubsanar;
    }

    public void setValorSubsanar(BigDecimal valorSubsanar) {
        this.valorSubsanar = valorSubsanar;
    }

    public BigInteger getComprobanteElectronico() {
        return comprobanteElectronico;
    }

    public void setComprobanteElectronico(BigInteger comprobanteElectronico) {
        this.comprobanteElectronico = comprobanteElectronico;
    }

    public BigInteger getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(BigInteger codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public String getObservacionRecepcionista() {
        return observacionRecepcionista;
    }

    public void setObservacionRecepcionista(String observacionRecepcionista) {
        this.observacionRecepcionista = observacionRecepcionista;
    }

    public BigInteger getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(BigInteger numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public String getCodigosPlantas() {
        return codigosPlantas;
    }

    public void setCodigosPlantas(String codigosPlantas) {
        this.codigosPlantas = codigosPlantas;
    }

    public String getCodigosLicencias() {
        return codigosLicencias;
    }

    public void setCodigosLicencias(String codigosLicencias) {
        this.codigosLicencias = codigosLicencias;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public BigDecimal getValorCalculadoArcom() {
        return valorCalculadoArcom;
    }

    public void setValorCalculadoArcom(BigDecimal valorCalculadoArcom) {
        this.valorCalculadoArcom = valorCalculadoArcom;
    }

    public Boolean getTieneDerechoMinero() {
        return tieneDerechoMinero;
    }

    public void setTieneDerechoMinero(Boolean tieneDerechoMinero) {
        this.tieneDerechoMinero = tieneDerechoMinero;
    }

    public BigInteger getCodigoUsuarioDelegado() {
        return codigoUsuarioDelegado;
    }

    public void setCodigoUsuarioDelegado(BigInteger codigoUsuarioDelegado) {
        this.codigoUsuarioDelegado = codigoUsuarioDelegado;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public RegistroPagoObligaciones getPagoPadre() {
        return pagoPadre;
    }

    public void setPagoPadre(RegistroPagoObligaciones pagoPadre) {
        this.pagoPadre = pagoPadre;
    }

    public ParametroSistema getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(ParametroSistema codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public Localidad getLugarEmisionPago() {
        return lugarEmisionPago;
    }

    public void setLugarEmisionPago(Localidad lugarEmisionPago) {
        this.lugarEmisionPago = lugarEmisionPago;
    }

    public ConcesionMinera getCodigoConcesion() {
        if (codigoConcesion != null) {
            codigoDerechoMinero = Long.valueOf(codigoConcesion.getCodigoArcom());
            nombreDerechoMinero = codigoConcesion.getNombreConcesion();
            superficie = codigoConcesion.getNumeroHectareasConcesion();
            codigoProvincia = codigoConcesion.getCodigoProvincia();
            codigoCanton = codigoConcesion.getCodigoCanton();
            codigoParroquia = codigoConcesion.getCodigoParroquia();
            if (codigoConcesion.getCodigoRegimen() != null) {
                codigoRegimen = codigoConcesion.getCodigoRegimen().getCodigoRegimen();
            }
            if (codigoConcesion.getCodigoFase() != null) {
                codigoFase = codigoConcesion.getCodigoFase().getCodigoFase();
            }
            if (Comunes.isNumeric(codigoConcesion.getTipoMaterial())) {
                codigoTipoMineral = Long.valueOf(codigoConcesion.getTipoMaterial());
            }
            numeroRuc = codigoConcesion.getDocumentoConcesionarioPrincipal();
            concesionario = codigoConcesion.getNombreConcesionarioPrincipal() + " " + codigoConcesion.getApellidoConcesionarioPrincipal();
            documentoPersonaPago = codigoConcesion.getDocumentoConcesionarioPrincipal();
            //nombrePersonaPago = codigoConcesion.getNombreConcesionarioPrincipal();
            //apellidoPersonaPago = codigoConcesion.getApellidoConcesionarioPrincipal();
        }
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public ConceptoPago getCodigoConceptoPago() {
        return codigoConceptoPago;
    }

    public void setCodigoConceptoPago(ConceptoPago codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public CatalogoDetalle getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(CatalogoDetalle codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public CatalogoDetalle getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(CatalogoDetalle estadoPago) {
        this.estadoPago = estadoPago;
    }

    public CatalogoDetalle getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(CatalogoDetalle tipoPago) {
        this.tipoPago = tipoPago;
    }

    public CatalogoDetalle getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(CatalogoDetalle codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Long getCodigoTipoRegistro() {
        return codigoTipoRegistro;
    }

    public void setCodigoTipoRegistro(Long codigoTipoRegistro) {
        this.codigoTipoRegistro = codigoTipoRegistro;
    }

    public Long getCodigoDerechoMinero() {
        return codigoDerechoMinero;
    }

    public void setCodigoDerechoMinero(Long codigoDerechoMinero) {
        this.codigoDerechoMinero = codigoDerechoMinero;
    }

    public LicenciaComercializacion getCodigoLicenciaComercializacion() {
        if (codigoLicenciaComercializacion != null) {
            codigoDerechoMinero = Long.valueOf(codigoLicenciaComercializacion.getCodigoArcom());
            nombreDerechoMinero = codigoLicenciaComercializacion.getNombre();
            //superficie = codigoLicenciaComercializacion.get;
            codigoProvincia = codigoLicenciaComercializacion.getCodigoProvincia();
            codigoCanton = codigoLicenciaComercializacion.getCodigoCanton();
            codigoParroquia = codigoLicenciaComercializacion.getCodigoParroquida();
            /*if (codigoConcesion.getCodigoRegimen() != null) {
             codigoRegimen = codigoConcesion.getCodigoRegimen().getCodigoRegimen();
             }
             codigoFase = codigoConcesion.getCodigoFase().getCodigoFase();
             codigoTipoMineral = Long.valueOf(codigoConcesion.getTipoMaterial());*/
            numeroRuc = codigoLicenciaComercializacion.getNumeroDocumento();
            concesionario = codigoLicenciaComercializacion.getNombre() + " " + codigoLicenciaComercializacion.getApellido();
            
            documentoPersonaPago = codigoLicenciaComercializacion.getNumeroDocumento();
            nombrePersonaPago = codigoLicenciaComercializacion.getNombre();
            apellidoPersonaPago = codigoLicenciaComercializacion.getApellido();
        }
        return codigoLicenciaComercializacion;
    }

    public void setCodigoLicenciaComercializacion(LicenciaComercializacion codigoLicenciaComercializacion) {
        this.codigoLicenciaComercializacion = codigoLicenciaComercializacion;
    }

    public PlantaBeneficio getCodigoPlantaBeneficio() {
        if (codigoPlantaBeneficio != null) {
            codigoDerechoMinero = Long.valueOf(codigoPlantaBeneficio.getCodigoArcom());
            nombreDerechoMinero = codigoPlantaBeneficio.getNombrePlantaBeneficio();
            codigoDerechoMinero = Long.valueOf(codigoPlantaBeneficio.getCodigoArcom());
            nombreDerechoMinero = codigoPlantaBeneficio.getNombrePlantaBeneficio();
            //superficie = codigoLicenciaComercializacion.get;
            codigoProvincia = codigoPlantaBeneficio.getCodigoProvincia();
            codigoCanton = codigoPlantaBeneficio.getCodigoCanton();
            codigoParroquia = codigoPlantaBeneficio.getCodigoParroquida();
            /*if (codigoConcesion.getCodigoRegimen() != null) {
             codigoRegimen = codigoConcesion.getCodigoRegimen().getCodigoRegimen();
             }
             codigoFase = codigoConcesion.getCodigoFase().getCodigoFase();
             codigoTipoMineral = Long.valueOf(codigoConcesion.getTipoMaterial());*/
            numeroRuc = codigoPlantaBeneficio.getNumeroDocumentoRepresentanteLegal();
            concesionario = codigoPlantaBeneficio.getNombreRepresentanteLegal() + " " + codigoPlantaBeneficio.getApellidoRepresentanteLegal();
            
            documentoPersonaPago = codigoPlantaBeneficio.getNumeroDocumentoRepresentanteLegal();
            nombrePersonaPago = codigoPlantaBeneficio.getNombreRepresentanteLegal();
            apellidoPersonaPago = codigoPlantaBeneficio.getApellidoRepresentanteLegal();
        }
        return codigoPlantaBeneficio;
    }

    public void setCodigoPlantaBeneficio(PlantaBeneficio codigoPlantaBeneficio) {
        this.codigoPlantaBeneficio = codigoPlantaBeneficio;
    }

    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    public void setNombreDerechoMinero(String nombreDerechoMinero) {
        this.nombreDerechoMinero = nombreDerechoMinero;
    }

    public SujetoMinero getCodigoSujetoMinero() {
        if (codigoSujetoMinero != null) {
            codigoDerechoMinero = Long.valueOf(codigoSujetoMinero.getNumeroDocumento());
        }
        return codigoSujetoMinero;
    }

    public void setCodigoSujetoMinero(SujetoMinero codigoSujetoMinero) {
        this.codigoSujetoMinero = codigoSujetoMinero;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
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

    public Long getCodigoRegimen() {
        return codigoRegimen;
    }

    public void setCodigoRegimen(Long codigoRegimen) {
        this.codigoRegimen = codigoRegimen;
    }

    public Long getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(Long codigoFase) {
        this.codigoFase = codigoFase;
    }

    public Long getCodigoTipoMineral() {
        return codigoTipoMineral;
    }

    public void setCodigoTipoMineral(Long codigoTipoMineral) {
        this.codigoTipoMineral = codigoTipoMineral;
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(String concesionario) {
        this.concesionario = concesionario;
    }

    public BigDecimal getValorReferenciaEntregaImpresa() {
        return valorReferenciaEntregaImpresa;
    }

    public void setValorReferenciaEntregaImpresa(BigDecimal valorReferenciaEntregaImpresa) {
        this.valorReferenciaEntregaImpresa = valorReferenciaEntregaImpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRegistro != null ? codigoRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPagoObligaciones)) {
            return false;
        }
        RegistroPagoObligaciones other = (RegistroPagoObligaciones) object;
        if ((this.codigoRegistro == null && other.codigoRegistro != null) || (this.codigoRegistro != null && !this.codigoRegistro.equals(other.codigoRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones[ codigoRegistro=" + codigoRegistro + " ]";
    }

}
