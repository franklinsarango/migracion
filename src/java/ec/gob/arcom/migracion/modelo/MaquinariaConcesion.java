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
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "maquinaria_concesion", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "MaquinariaConcesion.findAll", query = "SELECT m FROM MaquinariaConcesion m")})
public class MaquinariaConcesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_maquinaria")
    private Long codigoMaquinaria;
    @Size(max = 100)
    @Column(name = "marca_maquinaria")
    private String marcaMaquinaria;
    @Size(max = 650)
    @Column(name = "descripcion_maquinaria")
    private String descripcionMaquinaria;
    @Column(name = "estado_maquinaria")
    private BigInteger estadoMaquinaria;
    @Size(max = 150)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 80)
    @Column(name = "numero_serie")
    private String numeroSerie;
    @Size(max = 150)
    @Column(name = "motor")
    private String motor;
    @Size(max = 150)
    @Column(name = "matricula")
    private String matricula;
    @Size(max = 20)
    @Column(name = "placa")
    private String placa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "capacidad_carga")
    private BigDecimal capacidadCarga;
    @Size(max = 80)
    @Column(name = "numero_chip")
    private String numeroChip;
    @Size(max = 650)
    @Column(name = "observacion_maquinaria")
    private String observacionMaquinaria;
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
    @JoinColumn(name = "codigo_tipo_maquinaria", referencedColumnName = "codigo_tipo_maquinaria")
    @ManyToOne
    private TipoMaquinaria codigoTipoMaquinaria;
    @JoinColumn(name = "codigo_informe", referencedColumnName = "codigo_informe")
    @ManyToOne
    private Informe codigoInforme;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera codigoConcesion;
    @Column(name = "cantidad_maquinaria")
    private int cantidadMaquinaria;

    public MaquinariaConcesion() {
    }

    public MaquinariaConcesion(Long codigoMaquinaria) {
        this.codigoMaquinaria = codigoMaquinaria;
    }

    public Long getCodigoMaquinaria() {
        return codigoMaquinaria;
    }

    public void setCodigoMaquinaria(Long codigoMaquinaria) {
        this.codigoMaquinaria = codigoMaquinaria;
    }

    public String getMarcaMaquinaria() {
        return marcaMaquinaria;
    }

    public void setMarcaMaquinaria(String marcaMaquinaria) {
        this.marcaMaquinaria = marcaMaquinaria;
    }

    public String getDescripcionMaquinaria() {
        return descripcionMaquinaria;
    }

    public void setDescripcionMaquinaria(String descripcionMaquinaria) {
        this.descripcionMaquinaria = descripcionMaquinaria;
    }

    public BigInteger getEstadoMaquinaria() {
        return estadoMaquinaria;
    }

    public void setEstadoMaquinaria(BigInteger estadoMaquinaria) {
        this.estadoMaquinaria = estadoMaquinaria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(BigDecimal capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }

    public String getObservacionMaquinaria() {
        return observacionMaquinaria;
    }

    public void setObservacionMaquinaria(String observacionMaquinaria) {
        this.observacionMaquinaria = observacionMaquinaria;
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

    public TipoMaquinaria getCodigoTipoMaquinaria() {
        return codigoTipoMaquinaria;
    }

    public void setCodigoTipoMaquinaria(TipoMaquinaria codigoTipoMaquinaria) {
        this.codigoTipoMaquinaria = codigoTipoMaquinaria;
    }

    public Informe getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(Informe codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public ConcesionMinera getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMaquinaria != null ? codigoMaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinariaConcesion)) {
            return false;
        }
        MaquinariaConcesion other = (MaquinariaConcesion) object;
        if ((this.codigoMaquinaria == null && other.codigoMaquinaria != null) || (this.codigoMaquinaria != null && !this.codigoMaquinaria.equals(other.codigoMaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MaquinariaConcesion{" + "codigoMaquinaria=" + codigoMaquinaria + ", marcaMaquinaria=" + marcaMaquinaria + ", descripcionMaquinaria=" + descripcionMaquinaria + ", estadoMaquinaria=" + estadoMaquinaria
                + ", modelo=" + modelo + ", numeroSerie=" + numeroSerie + ", motor=" + motor + ", matricula=" + matricula + ", placa=" + placa + ", capacidadCarga=" + capacidadCarga + ", numeroChip=" + numeroChip
                + ", observacionMaquinaria=" + observacionMaquinaria + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion="
                + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", codigoTipoMaquinaria=" + (codigoTipoMaquinaria != null ? codigoTipoMaquinaria.getCodigoTipoMaquinaria() : null) 
                + ", codigoInforme=" + codigoInforme + ", codigoConcesion=" + (codigoConcesion != null ? codigoConcesion.getCodigoConcesion() : null) + ", cantidadMaquinaria=" + cantidadMaquinaria + '}';
    }

}
