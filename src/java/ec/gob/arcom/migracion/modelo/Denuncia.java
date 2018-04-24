/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.modelo;

import java.io.Serializable;
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
@Table(name = "denuncia", schema = "encmin")
public class Denuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_denuncia")
    private Long codigoDenuncia;
    
    @Column(name = "nombre_denunciante")
    private String nombreDenunciante;
    
    @Column(name = "telefono_denunciante")
    private String telefonoDenunciante;
    
    @Column(name = "email_denunciante")
    private String emailDenunciante;
    
    @JoinColumn(name = "codigo_tipo_denuncia", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoDenuncia;
    
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
    
    @JoinColumn(name = "codigo_regional", referencedColumnName = "codigo_regional")
    @ManyToOne
    private Regional regional;
    
    @JoinColumn(name = "codigo_usuario_infractor", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioInfractor;
    
    @Column(name = "nombre_infractor")
    private String nombreInfractor;
    
    @Column(name = "detalle_denuncia")
    private String detalleDenuncia;
    
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private Long usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "usuario_modificacion", referencedColumnName = "codigo_usuario")
    @ManyToOne
    private Usuario usuarioModificacion;
    
    public Denuncia() {
        
    }
    
    public Denuncia(Denuncia d) {
        this.codigoDenuncia= d.getCodigoDenuncia();
        this.nombreDenunciante= d.getNombreDenunciante();
        this.telefonoDenunciante= d.getTelefonoDenunciante();
        this.emailDenunciante= d.getEmailDenunciante();
        this.tipoDenuncia= d.getTipoDenuncia();
        this.provincia= d.getProvincia();
        this.canton= d.getCanton();
        this.parroquia= d.getParroquia();
        this.sector= d.getSector();
        this.regional= d.getRegional();
        this.usuarioInfractor= d.getUsuarioInfractor();
        this.nombreInfractor= d.getNombreInfractor();
        this.detalleDenuncia= d.getDetalleDenuncia();
        this.estadoRegistro= d.getEstadoRegistro();
        this.fechaCreacion= d.getFechaCreacion();
        this.usuarioCreacion= d.getUsuarioCreacion();
        this.fechaModificacion= d.getFechaModificacion();
        this.usuarioModificacion= d.getUsuarioModificacion();
    }

    public Long getCodigoDenuncia() {
        return codigoDenuncia;
    }

    public void setCodigoDenuncia(Long codigoDenuncia) {
        this.codigoDenuncia = codigoDenuncia;
    }

    public String getNombreDenunciante() {
        return nombreDenunciante;
    }

    public void setNombreDenunciante(String nombreDenunciante) {
        this.nombreDenunciante = nombreDenunciante;
    }

    public String getTelefonoDenunciante() {
        return telefonoDenunciante;
    }

    public void setTelefonoDenunciante(String telefonoDenunciante) {
        this.telefonoDenunciante = telefonoDenunciante;
    }

    public String getEmailDenunciante() {
        return emailDenunciante;
    }

    public void setEmailDenunciante(String emailDenunciante) {
        this.emailDenunciante = emailDenunciante;
    }

    public CatalogoDetalle getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(CatalogoDetalle tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
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

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Usuario getUsuarioInfractor() {
        return usuarioInfractor;
    }

    public void setUsuarioInfractor(Usuario usuarioInfractor) {
        this.usuarioInfractor = usuarioInfractor;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getDetalleDenuncia() {
        return detalleDenuncia;
    }

    public void setDetalleDenuncia(String detalleDenuncia) {
        this.detalleDenuncia = detalleDenuncia;
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

    public Long getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Long usuarioCreacion) {
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
    public int hashCode() {
        int hash = 0;
        hash += (codigoDenuncia != null ? codigoDenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denuncia)) {
            return false;
        }
        Denuncia other = (Denuncia) object;
        if ((this.codigoDenuncia == null && other.codigoDenuncia != null)
                || (this.codigoDenuncia != null && !this.codigoDenuncia.equals(other.codigoDenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String response;
        response= "Denuncia{" + 
                "codigoDenuncia=" + codigoDenuncia + 
                ", nombreDenunciante=" + nombreDenunciante + 
                ", telefonoDenunciante=" + telefonoDenunciante + 
                ", emailDenunciante=" + emailDenunciante + 
                ", tipoDenuncia=" + (tipoDenuncia != null ? tipoDenuncia.getCodigoCatalogoDetalle() : null) +
                ", provincia=" + (provincia != null ? provincia.getCodigoLocalidad() : null) +
                ", canton=" + (canton != null ? canton.getCodigoLocalidad() : null) +
                ", parroquia=" + (parroquia != null ? parroquia.getCodigoLocalidad() : null) +
                ", sector=" + sector + 
                ", regional=" + (regional != null ? regional.getCodigoRegional() : null) +
                ", usuarioInfractor=" + (usuarioInfractor != null ? usuarioInfractor.getCodigoUsuario() : null) +
                ", nombreInfractor=" + nombreInfractor + 
                ", detalleDenuncia=" + detalleDenuncia + 
                ", estadoRegistro=" + estadoRegistro + 
                ", fechaCreacion=" + fechaCreacion + 
                ", usuarioCreacion=" + usuarioCreacion + 
                ", fechaModificacion=" + fechaModificacion + 
                ", usuarioModificacion=" + usuarioModificacion +
                 "}";
        return response;
    }
}
