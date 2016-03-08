/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sadmin_data_", catalog = "arcom_catmin", schema = "catmin")
public class SadminData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "f1")
    private Double f1;
    @Size(max = 255)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "casillero")
    private String casillero;
    @Column(name = "cod_persona")
    private Double codPersona;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 255)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "titular")
    private String titular;
    @Size(max = 255)
    @Column(name = "representante_legal")
    private String representanteLegal;
    @Size(max = 255)
    @Column(name = "cedula_representante")
    private String cedulaRepresentante;
    @Column(name = "plazo")
    private Double plazo;
    @Column(name = "fecha_informe")
    @Temporal(TemporalType.DATE)
    private Date fechaInforme;
    @Column(name = "zona")
    private Double zona;
    @Column(name = "superficie")
    private Double superficie;
    @Size(max = 255)
    @Column(name = "estado")
    private String estado;
    @Size(max = 255)
    @Column(name = "fase")
    private String fase;
    @Size(max = 255)
    @Column(name = "tipo_solicitud")
    private String tipoSolicitud;
    @Column(name = "fecha_otorga")
    @Temporal(TemporalType.DATE)
    private Date fechaOtorga;
    @Column(name = "fecha_inscribe")
    @Temporal(TemporalType.DATE)
    private Date fechaInscribe;
    @Size(max = 255)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 255)
    @Column(name = "regional")
    private String regional;
    @Size(max = 255)
    @Column(name = "provincia1")
    private String provincia1;
    @Size(max = 255)
    @Column(name = "provincia2")
    private String provincia2;
    @Size(max = 255)
    @Column(name = "canton")
    private String canton;
    @Size(max = 255)
    @Column(name = "canton1")
    private String canton1;
    @Size(max = 255)
    @Column(name = "parroquia")
    private String parroquia;
    @Size(max = 255)
    @Column(name = "parroquia1")
    private String parroquia1;
    @Size(max = 255)
    @Column(name = "mineral")
    private String mineral;
    @Column(name = "orden")
    private Double orden;
    @Size(max = 255)
    @Column(name = "coord_x")
    private String coordX;
    @Size(max = 255)
    @Column(name = "coord_y")
    private String coordY;
    @Column(name = "manifiestos")
    private Double manifiestos;
    @Size(max = 255)
    @Column(name = "mineral_manifiesto")
    private String mineralManifiesto;
    @Size(max = 255)
    @Column(name = "cedula___ruc")
    private String cedulaRuc;
    @Column(name = "fecha_sustitucion")
    @Temporal(TemporalType.DATE)
    private Date fechaSustitucion;
    @Column(name = "tipo_mineral")
    private String tipoMineral;
    @Column(name = "mae")
    private boolean mae;
    @Column(name = "senagua")
    private boolean senagua;
    @Column(name = "obs_actos_adm_previos")
    private String obsActosAdmPrevios;
    @Column(name = "metodo_explotacion")
    private String metodoExplotacion; //catalogo id 23 - sistema de explotacion
    @Column(name = "sector")
    private String sector;
    @JoinColumn(name = "codigo_regimen", referencedColumnName = "codigo_regimen")
    @ManyToOne
    private Regimen codigoRegimen;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "tipo_persona", length = 3)
    private String tipoPersona;
    @JoinColumn(name = "codigo_modalidad_trabajo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoModalidadTrabajo;
    @JoinColumn(name = "codigo_casillero_localidad", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoCasilleroLocalidad;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "sadminData")
    //private Resolucion resolucion;
    @Column(name = "fecha_inscripcion_sustitucion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcionSustitucion;
    @Column(name = "volumen_total_explotacion")
    private Double volumenTotalExplotacion;
    @Column(name = "plazo_dias")
    private Integer dias;
    @Transient
    private String regimen;
    @Transient
    private String tipoPersonaString;
    @Transient
    private String zonaString;
    @Transient
    private String provinciaString;
    @Transient
    private String cantonString;
    @Transient
    private String parroquiaString;

    public SadminData() {
    }

    public SadminData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getF1() {
        return f1;
    }

    public void setF1(Double f1) {
        this.f1 = f1;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCasillero() {
        return casillero;
    }

    public void setCasillero(String casillero) {
        this.casillero = casillero;
    }

    public Double getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Double codPersona) {
        this.codPersona = codPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public Double getPlazo() {
        return plazo;
    }

    public void setPlazo(Double plazo) {
        this.plazo = plazo;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public Double getZona() {
        if (zona != null) {
            if (zona.equals(Double.valueOf("15"))) {
                zonaString = "Zona 15";
            } else if (zona.equals(Double.valueOf("16"))) {
                zonaString = "Zona 16";
            } else if (zona.equals(Double.valueOf("17"))) {
                zonaString = "Zona 17";
            } else if (zona.equals(Double.valueOf("18"))) {
                zonaString = "Zona 18";
            } else {
                zonaString = "Sin zona";
            }
        }
        return zona;
    }

    public void setZona(Double zona) {
        this.zona = zona;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getProvincia1() {
        return provincia1;
    }

    public void setProvincia1(String provincia1) {
        this.provincia1 = provincia1;
    }

    public String getProvincia2() {
        return provincia2;
    }

    public void setProvincia2(String provincia2) {
        this.provincia2 = provincia2;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCanton1() {
        return canton1;
    }

    public void setCanton1(String canton1) {
        this.canton1 = canton1;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getParroquia1() {
        return parroquia1;
    }

    public void setParroquia1(String parroquia1) {
        this.parroquia1 = parroquia1;
    }

    public String getMineral() {
        return mineral;
    }

    public void setMineral(String mineral) {
        this.mineral = mineral;
    }

    public Double getOrden() {
        return orden;
    }

    public void setOrden(Double orden) {
        this.orden = orden;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public Double getManifiestos() {
        return manifiestos;
    }

    public void setManifiestos(Double manifiestos) {
        this.manifiestos = manifiestos;
    }

    public String getMineralManifiesto() {
        return mineralManifiesto;
    }

    public void setMineralManifiesto(String mineralManifiesto) {
        this.mineralManifiesto = mineralManifiesto;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public Date getFechaSustitucion() {
        return fechaSustitucion;
    }

    public void setFechaSustitucion(Date fechaSustitucion) {
        this.fechaSustitucion = fechaSustitucion;
    }

    public String getTipoMineral() {
        return tipoMineral;
    }

    public void setTipoMineral(String tipoMineral) {
        this.tipoMineral = tipoMineral;
    }

    public boolean isMae() {
        return mae;
    }

    public void setMae(boolean mae) {
        this.mae = mae;
    }

    public boolean isSenagua() {
        return senagua;
    }

    public void setSenagua(boolean senagua) {
        this.senagua = senagua;
    }

    public String getObsActosAdmPrevios() {
        return obsActosAdmPrevios;
    }

    public void setObsActosAdmPrevios(String obsActosAdmPrevios) {
        this.obsActosAdmPrevios = obsActosAdmPrevios;
    }

    public String getMetodoExplotacion() {
        return metodoExplotacion;
    }

    public void setMetodoExplotacion(String metodoExplotacion) {
        this.metodoExplotacion = metodoExplotacion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    /**
     * @return the codigoModalidadTrabajo
     */
    public CatalogoDetalle getCodigoModalidadTrabajo() {
        if (codigoModalidadTrabajo == null) {
            codigoModalidadTrabajo = new CatalogoDetalle();
        }
        return codigoModalidadTrabajo;
    }

    /**
     * @param codigoModalidadTrabajo the codigoModalidadTrabajo to set
     */
    public void setCodigoModalidadTrabajo(CatalogoDetalle codigoModalidadTrabajo) {
        this.codigoModalidadTrabajo = codigoModalidadTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPersona() {
        if (tipoPersona != null) {
            if (tipoPersona.equals("PNA")) {
                tipoPersonaString = "Persona Natural";
            } else if (tipoPersona.equals("PJU")) {
                tipoPersonaString = "Persona Jurídica";
            }
        }
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Localidad getCodigoCasilleroLocalidad() {
        return codigoCasilleroLocalidad;
    }

    public void setCodigoCasilleroLocalidad(Localidad codigoCasilleroLocalidad) {
        this.codigoCasilleroLocalidad = codigoCasilleroLocalidad;
    }

    public Date getFechaInscripcionSustitucion() {
        return fechaInscripcionSustitucion;
    }

    public void setFechaInscripcionSustitucion(Date fechaInscripcionSustitucion) {
        this.fechaInscripcionSustitucion = fechaInscripcionSustitucion;
    }

    public Double getVolumenTotalExplotacion() {
        return volumenTotalExplotacion;
    }

    public void setVolumenTotalExplotacion(Double volumenTotalExplotacion) {
        this.volumenTotalExplotacion = volumenTotalExplotacion;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getTipoPersonaString() {
        return tipoPersonaString;
    }

    public void setTipoPersonaString(String tipoPersonaString) {
        this.tipoPersonaString = tipoPersonaString;
    }

    public String getZonaString() {
        return zonaString;
    }

    public void setZonaString(String zonaString) {
        this.zonaString = zonaString;
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

    /*public Resolucion getResolucion() {
    return resolucion;
    }
    public void setResolucion(Resolucion resolucion) {
    this.resolucion = resolucion;
    }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SadminData)) {
            return false;
        }
        SadminData other = (SadminData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SadminData{" + "id=" + id + ", f1=" + f1 + ", codigo=" + codigo + ", nombre=" + nombre + ", casillero=" + casillero + ", codPersona="
                + codPersona + ", direccion=" + direccion + ", telefono=" + telefono + ", titular=" + titular + ", representanteLegal=" + representanteLegal
                + ", cedulaRepresentante=" + cedulaRepresentante + ", plazo=" + plazo + ", fechaInforme=" + fechaInforme + ", zona=" + zona + ", superficie="
                + superficie + ", estado=" + estado + ", fase=" + fase + ", tipoSolicitud=" + tipoSolicitud + ", fechaOtorga=" + fechaOtorga + ", fechaInscribe="
                + fechaInscribe + ", provincia=" + provincia + ", regional=" + regional + ", provincia1=" + provincia1 + ", provincia2=" + provincia2 + ", canton="
                + canton + ", canton1=" + canton1 + ", parroquia=" + parroquia + ", parroquia1=" + parroquia1 + ", mineral=" + mineral + ", orden=" + orden + ", coordX="
                + coordX + ", coordY=" + coordY + ", manifiestos=" + manifiestos + ", mineralManifiesto=" + mineralManifiesto + ", cedulaRuc=" + cedulaRuc + ", fechaSustitucion="
                + fechaSustitucion + ", tipoMineral=" + tipoMineral + ", mae=" + mae + ", senagua=" + senagua + ", obsActosAdmPrevios=" + obsActosAdmPrevios + ", metodoExplotacion="
                + metodoExplotacion + ", sector=" + sector + ", codigoRegimen=" + (codigoRegimen != null ? codigoRegimen.getCodigoRegimen() : null) + ", email=" + email + ", tipoPersona=" + tipoPersona
                + "codigoModalidadTrabajo=" + (codigoModalidadTrabajo != null ? codigoModalidadTrabajo.getCodigoCatalogoDetalle() : null) + ", codigo_casillero_localidad="
                + (codigoCasilleroLocalidad != null ? codigoCasilleroLocalidad.getCodigoLocalidad() : null) + '}';
    }

}
