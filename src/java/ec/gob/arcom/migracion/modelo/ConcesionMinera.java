/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
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
@Table(name = "concesion_minera", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "ConcesionMinera.findAll", query = "SELECT c FROM ConcesionMinera c")})
public class ConcesionMinera implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concesionMinera")
    private List<ConcesionPlantaBeneficio> concesionPlantaBeneficioList;

    @OneToMany(mappedBy = "codigoConcesion")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @OneToMany(mappedBy = "concesionMinera")
    private List<Instrumento> instrumentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo_concesion")
    private Long codigoConcesion;
    @Size(max = 25)
    @Column(name = "codigo_arcom")
    private String codigoArcom;
    @Size(max = 30)
    @Column(name = "codigo_censal")
    private String codigoCensal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_concesion")
    private String nombreConcesion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numero_hectareas_concesion")
    private Double numeroHectareasConcesion;
    @Column(name = "plazo_concesion")
    private BigInteger plazoConcesion;
    @Column(name = "codigo_provincia")
    private BigInteger codigoProvincia;
    @Column(name = "codigo_canton")
    private BigInteger codigoCanton;
    @Column(name = "codigo_parroquia")
    private BigInteger codigoParroquia;
    @Column(name = "fecha_inicio_concesion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioConcesion;
    @Column(name = "fecha_fin_concesion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinConcesion;
    @Size(max = 15)
    @Column(name = "documento_concesionario_principal")
    private String documentoConcesionarioPrincipal;
    @Size(max = 80)
    @Column(name = "nombre_concesionario_principal")
    private String nombreConcesionarioPrincipal;
    @Size(max = 80)
    @Column(name = "apellido_concesionario_principal")
    private String apellidoConcesionarioPrincipal;
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
    @Column(name = "fecha_otorga")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorga;
    @Column(name = "fecha_inscribe")
    @Temporal(TemporalType.DATE)
    private Date fechaInscribe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoConcesion")
    private List<AreaMinera> areaMineraList;
    @JoinColumn(name = "codigo_tipo_mineria", referencedColumnName = "codigo_tipo_mineria")
    @ManyToOne(optional = false)
    private TipoMineria codigoTipoMineria;
    @JoinColumn(name = "codigo_regional", referencedColumnName = "codigo_regional")
    @ManyToOne
    private Regional codigoRegional;
    @JoinColumn(name = "codigo_regimen", referencedColumnName = "codigo_regimen")
    @ManyToOne
    private Regimen codigoRegimen;
    @JoinColumn(name = "codigo_fase", referencedColumnName = "codigo_fase")
    @ManyToOne
    private Fase codigoFase;
    @JoinColumn(name = "codigo_zona", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoZona;
    @JoinColumn(name = "codigo_forma_explotacion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoFormaExplotacion;
    @JoinColumn(name = "codigo_modalidad_trabajo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoModalidadTrabajo;
    @JoinColumn(name = "estado_concesion", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoConcesion;
    @Column(name = "obs_actos_adm_previos")
    private String obsActosAdmPrevios;
    @Column(name = "sector")
    private String sector;
    @Column(name = "mae")
    private Boolean mae;
    @Column(name = "senagua")
    private Boolean senagua;
    @OneToMany(mappedBy = "codigoConcesion")
    private Collection<MaquinariaConcesion> maquinariaConcesionCollection;
    @Column(name = "migrada")
    private Boolean migrada;
    @Column(name = "fecha_informe")
    @Temporal(TemporalType.DATE)
    private Date fechaInforme;
    @JoinColumn(name = "codigo_casillero_localidad", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoCasilleroLocalidad;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "concesionMinera")
    //private Resolucion resolucion;
    @Column(name = "fecha_sustitucion")
    @Temporal(TemporalType.DATE)
    private Date fechaSustitucion;
    @Column(name = "fecha_inscripcion_sustitucion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcionSustitucion;
    @Column(name = "plazo_dias")
    private Integer dias;
    @Transient
    private PersonaNatural personaNaturalTransient;
    @Transient
    private PersonaJuridica personaJuridicaTransient;
    @Transient
    private String nombreTitular;
    @Transient
    private String apellidoTitular;
    @Transient
    private String correoElectronico;
    @Transient
    private String celular;
    @Transient
    private String convencional;
    @Transient
    private String direccion;
    @Transient
    private String cedulaRucRepLegal;
    @Transient
    private String nombreRepLegal;
    @Transient
    private String apellidoRepLegal;
    @JoinColumn(name = "codigo_tipo_material", referencedColumnName = "codigo_catalogo")
    @ManyToOne(optional = true)
    private Catalogo codigoTipoMaterial;
    @JoinColumn(name = "codigo_material_interes", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne(optional = true)
    private CatalogoDetalle codigoMaterialInteres;
    @Transient
    private String tipoPersona;
    @Transient
    private String provinciaString;
    @Transient
    private String cantonString;
    @Transient
    private String parroquiaString;

    public ConcesionMinera() {
    }

    public ConcesionMinera(Long codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public ConcesionMinera(Long codigoConcesion, String nombreConcesion) {
        this.codigoConcesion = codigoConcesion;
        this.nombreConcesion = nombreConcesion;
    }

    public Long getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(Long codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public String getCodigoCensal() {
        return codigoCensal;
    }

    public void setCodigoCensal(String codigoCensal) {
        this.codigoCensal = codigoCensal;
    }

    public String getNombreConcesion() {
        return nombreConcesion;
    }

    public void setNombreConcesion(String nombreConcesion) {
        this.nombreConcesion = nombreConcesion;
    }

    public Double getNumeroHectareasConcesion() {
        return numeroHectareasConcesion;
    }

    public void setNumeroHectareasConcesion(Double numeroHectareasConcesion) {
        this.numeroHectareasConcesion = numeroHectareasConcesion;
    }

    public BigInteger getPlazoConcesion() {
        return plazoConcesion;
    }

    public void setPlazoConcesion(BigInteger plazoConcesion) {
        this.plazoConcesion = plazoConcesion;
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

    public Date getFechaInicioConcesion() {
        return fechaInicioConcesion;
    }

    public void setFechaInicioConcesion(Date fechaInicioConcesion) {
        this.fechaInicioConcesion = fechaInicioConcesion;
    }

    public Date getFechaFinConcesion() {
        return fechaFinConcesion;
    }

    public void setFechaFinConcesion(Date fechaFinConcesion) {
        this.fechaFinConcesion = fechaFinConcesion;
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

    public List<AreaMinera> getAreaMineraList() {
        return areaMineraList;
    }

    public void setAreaMineraList(List<AreaMinera> areaMineraList) {
        this.areaMineraList = areaMineraList;
    }

    public TipoMineria getCodigoTipoMineria() {
        return codigoTipoMineria;
    }

    public void setCodigoTipoMineria(TipoMineria codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    public Regional getCodigoRegional() {
        return codigoRegional;
    }

    public void setCodigoRegional(Regional codigoRegional) {
        this.codigoRegional = codigoRegional;
    }

    public Regimen getCodigoRegimen() {
        if (codigoRegimen == null) {
            codigoRegimen = new Regimen();
        }
        return codigoRegimen;
    }

    public void setCodigoRegimen(Regimen codigoRegimen) {
        this.codigoRegimen = codigoRegimen;
    }

    public Fase getCodigoFase() {
        if (codigoFase == null) {
            codigoFase = new Fase();
        }
        return codigoFase;
    }

    public void setCodigoFase(Fase codigoFase) {
        this.codigoFase = codigoFase;
    }

    public CatalogoDetalle getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(CatalogoDetalle codigoZona) {
        this.codigoZona = codigoZona;
    }

    public CatalogoDetalle getCodigoFormaExplotacion() {
        if (codigoFormaExplotacion == null) {
            codigoFormaExplotacion = new CatalogoDetalle();
        }
        return codigoFormaExplotacion;
    }

    public void setCodigoFormaExplotacion(CatalogoDetalle codigoFormaExplotacion) {
        this.codigoFormaExplotacion = codigoFormaExplotacion;
    }

    public CatalogoDetalle getCodigoModalidadTrabajo() {
        if (codigoModalidadTrabajo == null) {
            codigoModalidadTrabajo = new CatalogoDetalle();
        }
        return codigoModalidadTrabajo;
    }

    public void setCodigoModalidadTrabajo(CatalogoDetalle codigoModalidadTrabajo) {
        this.codigoModalidadTrabajo = codigoModalidadTrabajo;
    }

    public CatalogoDetalle getEstadoConcesion() {
        return estadoConcesion;
    }

    public void setEstadoConcesion(CatalogoDetalle estadoConcesion) {
        this.estadoConcesion = estadoConcesion;
    }

    public String getObsActosAdmPrevios() {
        return obsActosAdmPrevios;
    }

    public void setObsActosAdmPrevios(String obsActosAdmPrevios) {
        this.obsActosAdmPrevios = obsActosAdmPrevios;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Boolean getMigrada() {
        return migrada;
    }

    public void setMigrada(Boolean migrada) {
        this.migrada = migrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoConcesion != null ? codigoConcesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConcesionMinera)) {
            return false;
        }
        ConcesionMinera other = (ConcesionMinera) object;
        if ((this.codigoConcesion == null
                && other.codigoConcesion != null)
                || (this.codigoConcesion != null
                && !this.codigoConcesion.equals(other.codigoConcesion))) {
            return false;
        }
        return true;
    }

    public Boolean getMae() {
        return mae;
    }

    public void setMae(Boolean mae) {
        this.mae = mae;
    }

    public Boolean getSenagua() {
        return senagua;
    }

    public void setSenagua(Boolean senagua) {
        this.senagua = senagua;
    }

    public Collection<MaquinariaConcesion> getMaquinariaConcesionCollection() {
        return maquinariaConcesionCollection;
    }

    public void setMaquinariaConcesionCollection(Collection<MaquinariaConcesion> maquinariaConcesionCollection) {
        this.maquinariaConcesionCollection = maquinariaConcesionCollection;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    @Override
    public String toString() {
        return "ConcesionMinera{" + "codigoConcesion=" + codigoConcesion + ", codigoArcom=" + codigoArcom + ", codigoCensal=" + codigoCensal + ", nombreConcesion=" + nombreConcesion
                + ", numeroHectareasConcesion=" + numeroHectareasConcesion + ", plazoConcesion=" + plazoConcesion + ", codigoProvincia=" + codigoProvincia + ", codigoCanton=" + codigoCanton
                + ", codigoParroquia=" + codigoParroquia + ", fechaInicioConcesion=" + fechaInicioConcesion + ", fechaFinConcesion=" + fechaFinConcesion + ", documentoConcesionarioPrincipal="
                + documentoConcesionarioPrincipal + ", nombreConcesionarioPrincipal=" + nombreConcesionarioPrincipal + ", apellidoConcesionarioPrincipal=" + apellidoConcesionarioPrincipal
                + ", modalidad=" + modalidad + ", tipoMaterial=" + tipoMaterial + ", formaExplotacion=" + formaExplotacion + ", materialInteres=" + materialInteres + ", volumenDiarioExplotacion="
                + volumenDiarioExplotacion + ", volumenTotalExplotacion=" + volumenTotalExplotacion + ", casilleroJudicial=" + casilleroJudicial + ", aceptaCondiciones=" + aceptaCondiciones
                + ", campoReservado10=" + campoReservado10 + ", campoReservado09=" + campoReservado09 + ", campoReservado08=" + campoReservado08 + ", campoReservado07=" + campoReservado07
                + ", campoReservado06=" + campoReservado06 + ", campoReservado05=" + campoReservado05 + ", campoReservado04=" + campoReservado04 + ", campoReservado03=" + campoReservado03
                + ", campoReservado02=" + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion="
                + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", fechaOtorga=" + fechaOtorga + ", fechaInscribe=" + fechaInscribe
                + ", codigoTipoMineria=" + (codigoTipoMineria != null ? codigoTipoMineria.getCodigoTipoMineria() : null) + ", codigoRegional=" + (codigoRegional != null ? codigoRegional.getCodigoRegional() : null)
                + ", codigoRegimen=" + (codigoRegimen != null ? codigoRegimen.getCodigoRegimen() : null) + ", codigoFase=" + (codigoFase != null ? codigoFase.getCodigoFase() : null)
                + ", codigoZona=" + (codigoZona != null ? codigoZona.getCodigoCatalogoDetalle() : null) + ", codigoFormaExplotacion=" + (codigoFormaExplotacion != null ? codigoFormaExplotacion.getCodigoCatalogoDetalle() : null)
                + ", codigoModalidadTrabajo=" + (codigoModalidadTrabajo != null ? codigoModalidadTrabajo.getCodigoCatalogoDetalle() : null) + ", estadoConcesion=" + (estadoConcesion != null ? estadoConcesion.getCodigoCatalogoDetalle() : null)
                + ", obsActosAdmPrevios=" + obsActosAdmPrevios + ", sector=" + sector + ", mae=" + mae + ", senagua=" + senagua
                + ", migrada=" + migrada + ", fechaInforme=" + fechaInforme + ", codigoCasilleroLocalidad=" + (codigoCasilleroLocalidad != null ? codigoCasilleroLocalidad.getCodigoLocalidad() : null) + '}';
    }

    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public Localidad getCodigoCasilleroLocalidad() {
        return codigoCasilleroLocalidad;
    }

    public void setCodigoCasilleroLocalidad(Localidad codigoCasilleroLocalidad) {
        this.codigoCasilleroLocalidad = codigoCasilleroLocalidad;
    }

    public List<ConcesionPlantaBeneficio> getConcesionPlantaBeneficioList() {
        return concesionPlantaBeneficioList;
    }

    public void setConcesionPlantaBeneficioList(List<ConcesionPlantaBeneficio> concesionPlantaBeneficioList) {
        this.concesionPlantaBeneficioList = concesionPlantaBeneficioList;
    }

    /*public Resolucion getResolucion() {
     return resolucion;
     }

     public void setResolucion(Resolucion resolucion) {
     this.resolucion = resolucion;
     }*/
    public Date getFechaSustitucion() {
        return fechaSustitucion;
    }

    public void setFechaSustitucion(Date fechaSustitucion) {
        this.fechaSustitucion = fechaSustitucion;
    }

    public Date getFechaInscripcionSustitucion() {
        return fechaInscripcionSustitucion;
    }

    public void setFechaInscripcionSustitucion(Date fechaInscripcionSustitucion) {
        this.fechaInscripcionSustitucion = fechaInscripcionSustitucion;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public PersonaNatural getPersonaNaturalTransient() {
        if (personaNaturalTransient != null) {
            nombreTitular = personaNaturalTransient.getNombre();
            apellidoTitular = personaNaturalTransient.getApellido();
            correoElectronico = personaNaturalTransient.getEmail();
            celular = personaNaturalTransient.getCelular();
            convencional = personaNaturalTransient.getTelefono();
            direccion = personaNaturalTransient.getDireccion();
        }
        return personaNaturalTransient;
    }

    public void setPersonaNaturalTransient(PersonaNatural personaNaturalTransient) {
        this.personaNaturalTransient = personaNaturalTransient;
    }

    public PersonaJuridica getPersonaJuridicaTransient() {
        nombreTitular = personaJuridicaTransient.getNombreComercial();
        //apellidoTitular = personaNaturalTransient.getApellido();
        correoElectronico = personaJuridicaTransient.getEmail();
        celular = personaJuridicaTransient.getCelular();
        convencional = personaJuridicaTransient.getTelefono();
        direccion = personaJuridicaTransient.getDireccion();
        cedulaRucRepLegal = personaJuridicaTransient.getDocumentoRepresentanteLegal();
        nombreRepLegal = personaJuridicaTransient.getNombreRepresentanteLegal();
        apellidoRepLegal = personaJuridicaTransient.getApellidoRepresentanteLegal();
        return personaJuridicaTransient;
    }

    public void setPersonaJuridicaTransient(PersonaJuridica personaJuridicaTransient) {
        this.personaJuridicaTransient = personaJuridicaTransient;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getConvencional() {
        return convencional;
    }

    public void setConvencional(String convencional) {
        this.convencional = convencional;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedulaRucRepLegal() {
        return cedulaRucRepLegal;
    }

    public void setCedulaRucRepLegal(String cedulaRucRepLegal) {
        this.cedulaRucRepLegal = cedulaRucRepLegal;
    }

    public String getNombreRepLegal() {
        return nombreRepLegal;
    }

    public void setNombreRepLegal(String nombreRepLegal) {
        this.nombreRepLegal = nombreRepLegal;
    }

    public String getApellidoRepLegal() {
        return apellidoRepLegal;
    }

    public void setApellidoRepLegal(String apellidoRepLegal) {
        this.apellidoRepLegal = apellidoRepLegal;
    }

    public Catalogo getCodigoTipoMaterial() {
        return codigoTipoMaterial;
    }

    public void setCodigoTipoMaterial(Catalogo codigoTipoMaterial) {
        this.codigoTipoMaterial = codigoTipoMaterial;
    }

    public CatalogoDetalle getCodigoMaterialInteres() {
        return codigoMaterialInteres;
    }

    public void setCodigoMaterialInteres(CatalogoDetalle codigoMaterialInteres) {
        this.codigoMaterialInteres = codigoMaterialInteres;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

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

}
