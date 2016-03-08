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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "resolucion", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Resolucion.findAll", query = "SELECT r FROM Resolucion r")})
public class Resolucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_resolucion")
    private Long codigoResolucion;
    @Column(name = "codigo_tramite")
    private BigInteger codigoTramite;
    @Size(max = 60)
    @Column(name = "entidad_tramite")
    private String entidadTramite;
    @Column(name = "codigo_concesion")
    private BigInteger codigoConcesion;
    @Column(name = "codigo_area")
    private BigInteger codigoArea;
    @Size(max = 13)
    @Column(name = "numero_documento_sujeto_minero")
    private String numeroDocumentoSujetoMinero;
    @Size(max = 13)
    @Column(name = "ruc_sujeto_minero")
    private String rucSujetoMinero;
    @Size(max = 2000)
    @Column(name = "antecedentes")
    private String antecedentes;
    @Size(max = 2000)
    @Column(name = "considerandos")
    private String considerandos;
    @Size(max = 2000)
    @Column(name = "parte_resolutiva")
    private String parteResolutiva;
    @Column(name = "fecha_elaboracion_resolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracionResolucion;
    @Column(name = "fecha_aprobacion_resolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacionResolucion;
    @Column(name = "fecha_notificacion_sujeto_derecho")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacionSujetoDerecho;
    @Column(name = "fecha_otorgamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorgamiento;
    @Size(max = 2000)
    @Column(name = "texto_notificacion")
    private String textoNotificacion;
    @Column(name = "otorga_derecho")
    private Boolean otorgaDerecho;
    @Size(max = 2000)
    @Column(name = "observacion_resolucion")
    private String observacionResolucion;
    @Column(name = "cumple_validacion")
    private Boolean cumpleValidacion;
    @Column(name = "funcionario")
    private BigInteger funcionario;
    @Size(max = 40)
    @Column(name = "nombre_funcionario")
    private String nombreFuncionario;
    @Size(max = 40)
    @Column(name = "apellido_funcionario")
    private String apellidoFuncionario;
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
    @Column(name = "cumple_transferencia")
    private Boolean cumpleTransferencia;
    @Column(name = "cumple_caducado")
    private Boolean cumpleCaducado;
    @JoinColumn(name = "tipo_resolucion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoResolucion;
    @JoinColumn(name = "estado_resolucion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoResolucion;
    @JoinColumn(name = "codigo_concesion_2", referencedColumnName = "codigo_concesion")
    @OneToOne(optional = false)
    private ConcesionMinera concesionMinera;
    @JoinColumn(name = "sadmin_data_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private SadminData sadminData;
    @JoinColumn(name = "codigo_licencia_comercializacion", referencedColumnName = "codigo_licencia_comercializacion")
    @OneToOne(optional = false)
    private LicenciaComercializacion licenciaComercializacion;
    @JoinColumn(name = "codigo_planta_beneficio", referencedColumnName = "codigo_planta_beneficio")
    @OneToOne(optional = false)
    private PlantaBeneficio plantaBeneficio;

    public Resolucion() {
    }

    public Resolucion(Long codigoResolucion) {
        this.codigoResolucion = codigoResolucion;
    }

    public Long getCodigoResolucion() {
        return codigoResolucion;
    }

    public void setCodigoResolucion(Long codigoResolucion) {
        this.codigoResolucion = codigoResolucion;
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

    public String getNumeroDocumentoSujetoMinero() {
        return numeroDocumentoSujetoMinero;
    }

    public void setNumeroDocumentoSujetoMinero(String numeroDocumentoSujetoMinero) {
        this.numeroDocumentoSujetoMinero = numeroDocumentoSujetoMinero;
    }

    public String getRucSujetoMinero() {
        return rucSujetoMinero;
    }

    public void setRucSujetoMinero(String rucSujetoMinero) {
        this.rucSujetoMinero = rucSujetoMinero;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getConsiderandos() {
        return considerandos;
    }

    public void setConsiderandos(String considerandos) {
        this.considerandos = considerandos;
    }

    public String getParteResolutiva() {
        return parteResolutiva;
    }

    public void setParteResolutiva(String parteResolutiva) {
        this.parteResolutiva = parteResolutiva;
    }

    public Date getFechaElaboracionResolucion() {
        return fechaElaboracionResolucion;
    }

    public void setFechaElaboracionResolucion(Date fechaElaboracionResolucion) {
        this.fechaElaboracionResolucion = fechaElaboracionResolucion;
    }

    public Date getFechaAprobacionResolucion() {
        return fechaAprobacionResolucion;
    }

    public void setFechaAprobacionResolucion(Date fechaAprobacionResolucion) {
        this.fechaAprobacionResolucion = fechaAprobacionResolucion;
    }

    public Date getFechaNotificacionSujetoDerecho() {
        return fechaNotificacionSujetoDerecho;
    }

    public void setFechaNotificacionSujetoDerecho(Date fechaNotificacionSujetoDerecho) {
        this.fechaNotificacionSujetoDerecho = fechaNotificacionSujetoDerecho;
    }

    public Date getFechaOtorgamiento() {
        return fechaOtorgamiento;
    }

    public void setFechaOtorgamiento(Date fechaOtorgamiento) {
        this.fechaOtorgamiento = fechaOtorgamiento;
    }

    public String getTextoNotificacion() {
        return textoNotificacion;
    }

    public void setTextoNotificacion(String textoNotificacion) {
        this.textoNotificacion = textoNotificacion;
    }

    public Boolean getOtorgaDerecho() {
        return otorgaDerecho;
    }

    public void setOtorgaDerecho(Boolean otorgaDerecho) {
        this.otorgaDerecho = otorgaDerecho;
    }

    public String getObservacionResolucion() {
        return observacionResolucion;
    }

    public void setObservacionResolucion(String observacionResolucion) {
        this.observacionResolucion = observacionResolucion;
    }

    public Boolean getCumpleValidacion() {
        return cumpleValidacion;
    }

    public void setCumpleValidacion(Boolean cumpleValidacion) {
        this.cumpleValidacion = cumpleValidacion;
    }

    public BigInteger getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(BigInteger funcionario) {
        this.funcionario = funcionario;
    }

    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    public String getApellidoFuncionario() {
        return apellidoFuncionario;
    }

    public void setApellidoFuncionario(String apellidoFuncionario) {
        this.apellidoFuncionario = apellidoFuncionario;
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

    public Boolean getCumpleTransferencia() {
        return cumpleTransferencia;
    }

    public void setCumpleTransferencia(Boolean cumpleTransferencia) {
        this.cumpleTransferencia = cumpleTransferencia;
    }

    public Boolean getCumpleCaducado() {
        return cumpleCaducado;
    }

    public void setCumpleCaducado(Boolean cumpleCaducado) {
        this.cumpleCaducado = cumpleCaducado;
    }

    public CatalogoDetalle getTipoResolucion() {
        return tipoResolucion;
    }

    public void setTipoResolucion(CatalogoDetalle tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    public CatalogoDetalle getEstadoResolucion() {
        return estadoResolucion;
    }

    public void setEstadoResolucion(CatalogoDetalle estadoResolucion) {
        this.estadoResolucion = estadoResolucion;
    }

    public ConcesionMinera getConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public SadminData getSadminData() {
        return sadminData;
    }

    public void setSadminData(SadminData sadminData) {
        this.sadminData = sadminData;
    }

    public LicenciaComercializacion getLicenciaComercializacion() {
        return licenciaComercializacion;
    }

    public void setLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) {
        this.licenciaComercializacion = licenciaComercializacion;
    }

    public PlantaBeneficio getPlantaBeneficio() {
        return plantaBeneficio;
    }

    public void setPlantaBeneficio(PlantaBeneficio plantaBeneficio) {
        this.plantaBeneficio = plantaBeneficio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoResolucion != null ? codigoResolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resolucion)) {
            return false;
        }
        Resolucion other = (Resolucion) object;
        if ((this.codigoResolucion == null && other.codigoResolucion != null) || (this.codigoResolucion != null && !this.codigoResolucion.equals(other.codigoResolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Resolucion{" + "codigoResolucion=" + codigoResolucion + ", codigoTramite=" + codigoTramite + ", entidadTramite=" + entidadTramite 
                + ", codigoArea=" + codigoArea + ", numeroDocumentoSujetoMinero=" + numeroDocumentoSujetoMinero 
                + ", rucSujetoMinero=" + rucSujetoMinero + ", antecedentes=" + antecedentes + ", considerandos=" + considerandos 
                + ", parteResolutiva=" + parteResolutiva + ", fechaElaboracionResolucion=" + fechaElaboracionResolucion 
                + ", fechaAprobacionResolucion=" + fechaAprobacionResolucion + ", fechaNotificacionSujetoDerecho=" + fechaNotificacionSujetoDerecho 
                + ", fechaOtorgamiento=" + fechaOtorgamiento + ", textoNotificacion=" + textoNotificacion + ", otorgaDerecho=" + otorgaDerecho 
                + ", observacionResolucion=" + observacionResolucion + ", cumpleValidacion=" + cumpleValidacion + ", funcionario=" + funcionario 
                + ", nombreFuncionario=" + nombreFuncionario + ", apellidoFuncionario=" + apellidoFuncionario + ", campoReservado05=" + campoReservado05 
                + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 
                + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion 
                + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion 
                + ", cumpleTransferencia=" + cumpleTransferencia + ", cumpleCaducado=" + cumpleCaducado 
                + ", tipoResolucion=" + (tipoResolucion != null ? tipoResolucion.getCodigoCatalogoDetalle() : null)
                + ", estadoResolucion=" + (estadoResolucion != null ? estadoResolucion.getCodigoCatalogoDetalle() : null) 
                + ", concesion_minera=" + (concesionMinera != null ? concesionMinera.getCodigoConcesion() : null) 
                + ", licencia_comercializacion=" + (licenciaComercializacion != null ? licenciaComercializacion.getCodigoLicenciaComercializacion() : null) 
                + ", planta_beneficio=" + (plantaBeneficio != null ? plantaBeneficio.getCodigoPlantaBeneficio() : null) 
                + ", sadmin_data=" + (sadminData != null ? sadminData.getId() : null) + '}';
    }
}
