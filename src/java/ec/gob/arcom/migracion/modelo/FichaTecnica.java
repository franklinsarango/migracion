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
@Table(name = "ficha_tecnica", schema = "actmin")
public class FichaTecnica implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_ficha_tecnica")
    private Long codigoFichaTecnica;
    
    @JoinColumn(name = "codigo_usuario_elaboracion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioElaboracion;
    
    @Column(name = "fecha_visita")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVisita;
    
    @Column(name = "razon_social_labor_minera")
    private String rasonSocialLaborMinera;
    
    @Column(name = "nombre_representante_labor_minera")
    private String nombreRepresentanteLaborMinera;
    
    @Column(name = "apellido_representante_labor_minera")
    private String apellidoRepresentanteLaborMinera;
    
    @Column(name = "documento_representante_labor_minera")
    private String documentoRepresentanteLaborMinera;
    
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
    
    @Column(name = "utm_este")
    private String utmEste;
    
    @Column(name = "utm_norte")
    private String utmNorte;
    
    @Column(name = "cota")
    private Long cota;
    
    @JoinColumn(name = "codigo_zona", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle zona;
    
    @Column(name = "tiempo_labor_minera")
    private Long tiempoLaborMinera;
    
    @JoinColumn(name = "codigo_etnina", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle etnia;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "correo_electronico")
    private String correoElectronico;
    
    @JoinColumn(name = "codigo_condicion_labor_minera", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle condicionLaborMinera;
    
    @Column(name = "codigo_censal")
    private String codigoCensal;
    
    @Column(name = "zona_riesgo")
    private boolean zonaRiesgo;
    
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera concesionMinera;
    
    @Column(name = "observaciones_derecho_minera")
    private String observacionesDerechoMinera;
    
    @JoinColumn(name = "codigo_modalidad_trabajo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle modalidadTrabajo;
    
    @Column(name = "numero_socios")
    private Long numeroSocios;
    
    @Column(name = "numero_personas_labor_minera")
    private Long numeroPersonasLaborMinera;
    
    @Column(name = "numero_personas_mayor_edad")
    private Long numeroPersonasMayorEdad;
    
    @Column(name = "dias_trabajo_mes")
    private Long diasTrabajoMes;
    
    @Column(name = "trabajadores_asegurados")
    private boolean trabajadoresAsegurados;
    
    @JoinColumn(name = "codigo_tipo_energia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoEnergia;
    
    @JoinColumn(name = "codigo_tipo_agua_utiliza", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoAguaUtiliza;
    
    @Column(name = "cantidad_combustible")
    private BigDecimal cantidadCombustible;
    
    @JoinColumn(name = "codigo_tipo_material", referencedColumnName = "codigo_catalogo")
    @ManyToOne
    private Catalogo tipoMaterial;
    
    @JoinColumn(name = "codigo_material_interes", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle materialInteres;
    
    @JoinColumn(name = "codigo_forma_explotacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle formaExplotacion;
    
    @JoinColumn(name = "codigo_sistema_explotacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle sistemaExplotacion;
    
    @Column(name = "longitud_subterranea")
    private BigDecimal longitudSubterranea;
    
    @Column(name = "ancho_subterranea")
    private BigDecimal anchoSubterranea;
    
    @Column(name = "alto_subterranea")
    private BigDecimal altoSubterranea;
    
    @Column(name = "rumbo_subterranea")
    private BigDecimal rumboSubterranea;
    
    @Column(name = "longitud_cielo_abierto")
    private BigDecimal longitudCieloAbierto;
    
    @Column(name = "ancho_cielo_abierto")
    private BigDecimal anchoCieloAbierto;
    
    @Column(name = "alto_cielo_abierto")
    private BigDecimal altoCieloAbierto;
    
    @JoinColumn(name = "codigo_tipo_terreno", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoTerreno;
    
    @Column(name = "detalle_tipo_terreno")
    private String detalleTipoTerreno;
    
    @Column(name = "observaciones_ficha_tecnica")
    private String observacionesFichaTecnica;
    
    @JoinColumn(name = "codigo_regional", referencedColumnName = "codigo_regional")
    @ManyToOne
    private Regional regional;
    
    @Column(name = "numero_formulario")
    private String numeroFormulario;
    
    @JoinColumn(name = "codigo_tipo_informacion_registro", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInformacionRegistro;
    
    @JoinColumn(name = "codigo_estado_legal", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoLegal;
    
    @JoinColumn(name = "codigo_tipo_inspeccion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoInspeccion;
    
    /////////////////////////////////////////////
    
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
    
    /////////////////////////////////////////////
    
    public Long getCodigoFichaTecnica() {
        return codigoFichaTecnica;
    }

    public void setCodigoFichaTecnica(Long codigoFichaTecnica) {
        this.codigoFichaTecnica = codigoFichaTecnica;
    }

    public Usuario getUsuarioElaboracion() {
        return usuarioElaboracion;
    }

    public void setUsuarioElaboracion(Usuario usuarioElaboracion) {
        this.usuarioElaboracion = usuarioElaboracion;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getRasonSocialLaborMinera() {
        return rasonSocialLaborMinera;
    }

    public void setRasonSocialLaborMinera(String rasonSocialLaborMinera) {
        this.rasonSocialLaborMinera = rasonSocialLaborMinera;
    }

    public String getNombreRepresentanteLaborMinera() {
        return nombreRepresentanteLaborMinera;
    }

    public void setNombreRepresentanteLaborMinera(String nombreRepresentanteLaborMinera) {
        this.nombreRepresentanteLaborMinera = nombreRepresentanteLaborMinera;
    }

    public String getApellidoRepresentanteLaborMinera() {
        return apellidoRepresentanteLaborMinera;
    }

    public void setApellidoRepresentanteLaborMinera(String apellidoRepresentanteLaborMinera) {
        this.apellidoRepresentanteLaborMinera = apellidoRepresentanteLaborMinera;
    }

    public String getDocumentoRepresentanteLaborMinera() {
        return documentoRepresentanteLaborMinera;
    }

    public void setDocumentoRepresentanteLaborMinera(String documentoRepresentanteLaborMinera) {
        this.documentoRepresentanteLaborMinera = documentoRepresentanteLaborMinera;
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

    public Long getCota() {
        return cota;
    }

    public void setCota(Long cota) {
        this.cota = cota;
    }

    public CatalogoDetalle getZona() {
        return zona;
    }

    public void setZona(CatalogoDetalle zona) {
        this.zona = zona;
    }

    public Long getTiempoLaborMinera() {
        return tiempoLaborMinera;
    }

    public void setTiempoLaborMinera(Long tiempoLaborMinera) {
        this.tiempoLaborMinera = tiempoLaborMinera;
    }

    public CatalogoDetalle getEtnia() {
        return etnia;
    }

    public void setEtnia(CatalogoDetalle etnia) {
        this.etnia = etnia;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public CatalogoDetalle getCondicionLaborMinera() {
        return condicionLaborMinera;
    }

    public void setCondicionLaborMinera(CatalogoDetalle condicionLaborMinera) {
        this.condicionLaborMinera = condicionLaborMinera;
    }

    public String getCodigoCensal() {
        return codigoCensal;
    }

    public void setCodigoCensal(String codigoCensal) {
        this.codigoCensal = codigoCensal;
    }

    public boolean isZonaRiesgo() {
        return zonaRiesgo;
    }

    public void setZonaRiesgo(boolean zonaRiesgo) {
        this.zonaRiesgo = zonaRiesgo;
    }

    public ConcesionMinera getConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(ConcesionMinera concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public String getObservacionesDerechoMinera() {
        return observacionesDerechoMinera;
    }

    public void setObservacionesDerechoMinera(String observacionesDerechoMinera) {
        this.observacionesDerechoMinera = observacionesDerechoMinera;
    }

    public CatalogoDetalle getModalidadTrabajo() {
        return modalidadTrabajo;
    }

    public void setModalidadTrabajo(CatalogoDetalle modalidadTrabajo) {
        this.modalidadTrabajo = modalidadTrabajo;
    }

    public Long getNumeroSocios() {
        return numeroSocios;
    }

    public void setNumeroSocios(Long numeroSocios) {
        this.numeroSocios = numeroSocios;
    }

    public Long getNumeroPersonasLaborMinera() {
        return numeroPersonasLaborMinera;
    }

    public void setNumeroPersonasLaborMinera(Long numeroPersonasLaborMinera) {
        this.numeroPersonasLaborMinera = numeroPersonasLaborMinera;
    }

    public Long getNumeroPersonasMayorEdad() {
        return numeroPersonasMayorEdad;
    }

    public void setNumeroPersonasMayorEdad(Long numeroPersonasMayorEdad) {
        this.numeroPersonasMayorEdad = numeroPersonasMayorEdad;
    }

    public Long getDiasTrabajoMes() {
        return diasTrabajoMes;
    }

    public void setDiasTrabajoMes(Long diasTrabajoMes) {
        this.diasTrabajoMes = diasTrabajoMes;
    }

    public boolean isTrabajadoresAsegurados() {
        return trabajadoresAsegurados;
    }

    public void setTrabajadoresAsegurados(boolean trabajadoresAsegurados) {
        this.trabajadoresAsegurados = trabajadoresAsegurados;
    }

    public CatalogoDetalle getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(CatalogoDetalle tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public CatalogoDetalle getTipoAguaUtiliza() {
        return tipoAguaUtiliza;
    }

    public void setTipoAguaUtiliza(CatalogoDetalle tipoAguaUtiliza) {
        this.tipoAguaUtiliza = tipoAguaUtiliza;
    }

    public BigDecimal getCantidadCombustible() {
        return cantidadCombustible;
    }

    public void setCantidadCombustible(BigDecimal cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }

    public Catalogo getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(Catalogo tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public CatalogoDetalle getMaterialInteres() {
        return materialInteres;
    }

    public void setMaterialInteres(CatalogoDetalle materialInteres) {
        this.materialInteres = materialInteres;
    }

    public CatalogoDetalle getFormaExplotacion() {
        return formaExplotacion;
    }

    public void setFormaExplotacion(CatalogoDetalle formaExplotacion) {
        this.formaExplotacion = formaExplotacion;
    }

    public CatalogoDetalle getSistemaExplotacion() {
        return sistemaExplotacion;
    }

    public void setSistemaExplotacion(CatalogoDetalle sistemaExplotacion) {
        this.sistemaExplotacion = sistemaExplotacion;
    }

    public BigDecimal getLongitudSubterranea() {
        return longitudSubterranea;
    }

    public void setLongitudSubterranea(BigDecimal longitudSubterranea) {
        this.longitudSubterranea = longitudSubterranea;
    }

    public BigDecimal getAnchoSubterranea() {
        return anchoSubterranea;
    }

    public void setAnchoSubterranea(BigDecimal anchoSubterranea) {
        this.anchoSubterranea = anchoSubterranea;
    }

    public BigDecimal getAltoSubterranea() {
        return altoSubterranea;
    }

    public void setAltoSubterranea(BigDecimal altoSubterranea) {
        this.altoSubterranea = altoSubterranea;
    }

    public BigDecimal getRumboSubterranea() {
        return rumboSubterranea;
    }

    public void setRumboSubterranea(BigDecimal rumboSubterranea) {
        this.rumboSubterranea = rumboSubterranea;
    }

    public BigDecimal getLongitudCieloAbierto() {
        return longitudCieloAbierto;
    }

    public void setLongitudCieloAbierto(BigDecimal longitudCieloAbierto) {
        this.longitudCieloAbierto = longitudCieloAbierto;
    }

    public BigDecimal getAnchoCieloAbierto() {
        return anchoCieloAbierto;
    }

    public void setAnchoCieloAbierto(BigDecimal anchoCieloAbierto) {
        this.anchoCieloAbierto = anchoCieloAbierto;
    }

    public BigDecimal getAltoCieloAbierto() {
        return altoCieloAbierto;
    }

    public void setAltoCieloAbierto(BigDecimal altoCieloAbierto) {
        this.altoCieloAbierto = altoCieloAbierto;
    }

    public CatalogoDetalle getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(CatalogoDetalle tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public String getDetalleTipoTerreno() {
        return detalleTipoTerreno;
    }

    public void setDetalleTipoTerreno(String detalleTipoTerreno) {
        this.detalleTipoTerreno = detalleTipoTerreno;
    }

    public String getObservacionesFichaTecnica() {
        return observacionesFichaTecnica;
    }

    public void setObservacionesFichaTecnica(String observacionesFichaTecnica) {
        this.observacionesFichaTecnica = observacionesFichaTecnica;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public CatalogoDetalle getTipoInformacionRegistro() {
        return tipoInformacionRegistro;
    }

    public void setTipoInformacionRegistro(CatalogoDetalle tipoInformacionRegistro) {
        this.tipoInformacionRegistro = tipoInformacionRegistro;
    }

    public CatalogoDetalle getEstadoLegal() {
        return estadoLegal;
    }

    public void setEstadoLegal(CatalogoDetalle estadoLegal) {
        this.estadoLegal = estadoLegal;
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

    public CatalogoDetalle getTipoInspeccion() {
        return tipoInspeccion;
    }

    public void setTipoInspeccion(CatalogoDetalle tipoInspeccion) {
        this.tipoInspeccion = tipoInspeccion;
    }
    
    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.FichaTecnica[ codigoFichaTecnica=" + codigoFichaTecnica + " ]";
    }
    
}
