/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name = "operativo", schema = "catmin")
public class Operativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_operativo")
    private Long codigoOperativo;
    
    @JoinColumn(name = "codigo_tipo_operativo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoOperativo;
    @JoinColumn(name = "codigo_regional", referencedColumnName = "codigo_regional")
    @ManyToOne
    private Regional regional;
    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad provincia;
    @JoinColumn(name = "codigo_canton", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad canton;
    @JoinColumn(name = "codigo_parroquia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad parroquia;
    @Column(name = "sector")
    private String sector;
    @JoinColumn(name = "codigo_zona", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle zona;
    @Column(name = "utm_este")
    private String utmEste;
    @Column(name = "utm_norte")
    private String utmNorte;
    @JoinColumn(name = "codigo_tipo_sello", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoSello;
    @Column(name = "numero_sello")
    private String numeroSello;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_informe_mae")
    private Date fechaInformeMae;
    @Column(name = "numero_documento_informe_mae")
    private String numeroDocumentoInformeMae;
    @JoinColumn(name = "codigo_responsable_tecnico", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario responsableTecnico;
    @JoinColumn(name = "codigo_responsable_legal", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario responsableLegal;
    @Column(name = "numero_informe_tecnico")
    private String numeroInformeTecnico;
    @Column(name = "observaciones_operativo")
    private String observacionesOperativo;
    @Column(name = "fecha_operativo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaOperativo;
    @JoinColumn(name = "codigo_tipo_material", referencedColumnName = "codigo_catalogo")
    @ManyToOne
    private Catalogo tipoMaterial;
    @JoinColumn(name = "codigo_forma_explotacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle formaExplotacion;
    @Column(name = "descripcion_acciones_realizadas")
    private String descripcionAccionesRealizadas;
    @Column(name = "multa_proceso_administrativo")
    private BigDecimal multaProcesoAdministrativo;
    @Column(name = "acciones_realizar")
    private String accionesRealizar;
    @Column(name = "expediente_administrativo")
    private Boolean expedienteAdministrativo;
    @Column(name = "numero_expediente_administrativo")
    private String numeroExpedienteAdministrativo;
    @JoinColumn(name = "codigo_estado_administrativo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoAdministrativo;
    @Column(name = "numero_resolucion_administrativo")
    private String numeroResolucionAdministrativo;
    @Column(name = "fecha_resolucion_administrativo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaResolucionAdministrativo;
    
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "usuario_creacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "usuario_modificacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioModificacion;
    
    public Long getCodigoOperativo() {
        return codigoOperativo;
    }

    public void setCodigoOperativo(Long codigoOperativo) {
        this.codigoOperativo = codigoOperativo;
    }

    public CatalogoDetalle getTipoOperativo() {
        return tipoOperativo;
    }

    public void setTipoOperativo(CatalogoDetalle tipoOperativo) {
        this.tipoOperativo = tipoOperativo;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Localidad getProvincia() {
        return provincia;
    }

    public void setProvincia(Localidad provincia) {
        this.provincia = provincia;
    }

    public Localidad getCanton() {
        return canton;
    }

    public void setCanton(Localidad canton) {
        this.canton = canton;
    }

    public Localidad getParroquia() {
        return parroquia;
    }

    public void setParroquia(Localidad parroquia) {
        this.parroquia = parroquia;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public CatalogoDetalle getZona() {
        return zona;
    }

    public void setZona(CatalogoDetalle zona) {
        this.zona = zona;
    }

    public String getUtmEste() {
        return utmEste;
    }

    public void setUtmEste(String utmEste) {
        this.utmEste = utmEste;
    }

    public String getUtmNorte() {
        return utmNorte;
    }

    public void setUtmNorte(String utmNorte) {
        this.utmNorte = utmNorte;
    }

    public CatalogoDetalle getTipoSello() {
        return tipoSello;
    }

    public void setTipoSello(CatalogoDetalle tipoSello) {
        this.tipoSello = tipoSello;
    }

    public String getNumeroSello() {
        return numeroSello;
    }

    public void setNumeroSello(String numeroSello) {
        this.numeroSello = numeroSello;
    }

    public Date getFechaInformeMae() {
        return fechaInformeMae;
    }

    public void setFechaInformeMae(Date fechaInformeMae) {
        this.fechaInformeMae = fechaInformeMae;
    }

    public String getNumeroDocumentoInformeMae() {
        return numeroDocumentoInformeMae;
    }

    public void setNumeroDocumentoInformeMae(String numeroDocumentoInformeMae) {
        this.numeroDocumentoInformeMae = numeroDocumentoInformeMae;
    }

    public Usuario getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(Usuario responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public Usuario getResponsableLegal() {
        return responsableLegal;
    }

    public void setResponsableLegal(Usuario responsableLegal) {
        this.responsableLegal = responsableLegal;
    }

    public String getNumeroInformeTecnico() {
        return numeroInformeTecnico;
    }

    public void setNumeroInformeTecnico(String numeroInformeTecnico) {
        this.numeroInformeTecnico = numeroInformeTecnico;
    }

    public String getObservacionesOperativo() {
        return observacionesOperativo;
    }

    public void setObservacionesOperativo(String observacionesOperativo) {
        this.observacionesOperativo = observacionesOperativo;
    }

    public Date getFechaOperativo() {
        return fechaOperativo;
    }

    public void setFechaOperativo(Date fechaOperativo) {
        this.fechaOperativo = fechaOperativo;
    }

    public Catalogo getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(Catalogo tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public CatalogoDetalle getFormaExplotacion() {
        return formaExplotacion;
    }

    public void setFormaExplotacion(CatalogoDetalle formaExplotacion) {
        this.formaExplotacion = formaExplotacion;
    }

    public String getDescripcionAccionesRealizadas() {
        return descripcionAccionesRealizadas;
    }

    public void setDescripcionAccionesRealizadas(String descripcionAccionesRealizadas) {
        this.descripcionAccionesRealizadas = descripcionAccionesRealizadas;
    }

    public BigDecimal getMultaProcesoAdministrativo() {
        return multaProcesoAdministrativo;
    }

    public void setMultaProcesoAdministrativo(BigDecimal multaProcesoAdministrativo) {
        this.multaProcesoAdministrativo = multaProcesoAdministrativo;
    }

    public String getAccionesRealizar() {
        return accionesRealizar;
    }

    public void setAccionesRealizar(String accionesRealizar) {
        this.accionesRealizar = accionesRealizar;
    }

    public Boolean getExpedienteAdministrativo() {
        return expedienteAdministrativo;
    }

    public void setExpedienteAdministrativo(Boolean expedienteAdministrativo) {
        this.expedienteAdministrativo = expedienteAdministrativo;
    }

    public String getNumeroExpedienteAdministrativo() {
        return numeroExpedienteAdministrativo;
    }

    public void setNumeroExpedienteAdministrativo(String numeroExpedienteAdministrativo) {
        this.numeroExpedienteAdministrativo = numeroExpedienteAdministrativo;
    }

    public CatalogoDetalle getEstadoAdministrativo() {
        return estadoAdministrativo;
    }

    public void setEstadoAdministrativo(CatalogoDetalle estadoAdministrativo) {
        this.estadoAdministrativo = estadoAdministrativo;
    }

    public String getNumeroResolucionAdministrativo() {
        return numeroResolucionAdministrativo;
    }

    public void setNumeroResolucionAdministrativo(String numeroResolucionAdministrativo) {
        this.numeroResolucionAdministrativo = numeroResolucionAdministrativo;
    }

    public Date getFechaResolucionAdministrativo() {
        return fechaResolucionAdministrativo;
    }

    public void setFechaResolucionAdministrativo(Date fechaResolucionAdministrativo) {
        this.fechaResolucionAdministrativo = fechaResolucionAdministrativo;
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

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Override
    public String toString() {
        String response;
        response= "Operativo{" + 
                "codigoOperativo=" + codigoOperativo + ", tipoOperativo=" + tipoOperativo.getCodigoCatalogoDetalle() + 
                ", regional=" + regional.getCodigoRegional() + ", provincia=" + provincia.getCodigoLocalidad() + ", canton=" + canton.getCodigoLocalidad() + 
                ", parroquia=" + parroquia.getCodigoLocalidad() + ", sector=" + sector + ", utmEste=" + utmEste + ", utmNorte=" + utmNorte + 
                ", tipoSello=" + obtenerTipoSello() + 
                ", numeroSello=" + obtenerValorNulo(numeroSello) + 
                ", fechaInformeMae=" + obtenerValorNulo(fechaInformeMae) + 
                ", numeroDocumentoInformeMae=" + numeroDocumentoInformeMae + 
                ", responsableTecnico=" + responsableTecnico.getNumeroDocumento() +
                ", responsableLegal=" + responsableLegal.getNumeroDocumento() + 
                ", numeroInformeTecnico=" + numeroInformeTecnico +
                ", observacionesOperativo=" + observacionesOperativo + 
                ", fechaOperativo=" + obtenerValorNulo(fechaOperativo) + 
                ", tipoMaterial=" + obtenerTipoMaterial() + 
                ", formaExplotacion=" + formaExplotacion.getCodigoCatalogoDetalle() + 
                ", descripcionAccionesRealizadas=" + descripcionAccionesRealizadas +
                ", multaProcesoAdministrativo=" + multaProcesoAdministrativo + 
                ", accionesRealizar=" + accionesRealizar + 
                ", expedienteAdministrativo=" + expedienteAdministrativo + 
                ", numeroExpedienteAdministrativo=" + numeroExpedienteAdministrativo +
                ", estadoAdministrativo=" + obtenerEstadoAdministrativo() + 
                ", numeroResolucionAdministrativo=" + numeroResolucionAdministrativo +
                ", fechaResolucionAdministrativo=" + obtenerValorNulo(fechaResolucionAdministrativo) + 
                ", estadoRegistro=" + estadoRegistro + 
                ", fechaCreacion=" + fechaCreacion + 
                ", usuarioCreacion=" + usuarioCreacion + 
                ", fechaModificacion=" + fechaModificacion + 
                ", usuarioModificacion=" + obtenerUsuarioModificacion() +
                 "}";
        return response;
    }
    
    private String obtenerTipoMaterial() {
        if(tipoMaterial==null) {
            return "";
        } else {
            return tipoMaterial.getCodigoCatalogo().toString();
        }
    }
    
    private String obtenerTipoSello() {
        if(tipoSello==null) {
            return "";
        } else {
            return tipoSello.getCodigoCatalogoDetalle().toString();
        }
    }
    
    private String obtenerUsuarioModificacion() {
        if(usuarioModificacion==null) {
            return "";
        } else {
            return usuarioModificacion.getNumeroDocumento();
        }
    }
    
    private String obtenerEstadoAdministrativo() {
        try {
            return estadoAdministrativo.getCodigoCatalogoDetalle().toString();
        } catch(Exception ex) {
            return "";
        }
    }
    
    private String obtenerValorNulo(Object value) {
        if(value==null) {
            return "";
        } else {
            return value.toString();
        }
    }
}
