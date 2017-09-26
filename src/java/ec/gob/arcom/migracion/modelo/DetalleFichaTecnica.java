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
@Table(name = "detalle_ficha_tecnica", schema = "actmin")
public class DetalleFichaTecnica implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_ficha_tecnica")
    private Long codigoDetalleFichaTecnica;

    @JoinColumn(name = "codigo_ficha_tecnica", referencedColumnName = "codigo_ficha_tecnica")
    @ManyToOne()
    private FichaTecnica fichaTecnica;
    
    @JoinColumn(name = "codigo_tipo_informacion_registro", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoTipoInformacionRegistro;
    
    @Column(name = "nombre_socio")
    private String nombreSocio;
    
    @Column(name = "apellido_socio")
    private String apellidoSocio;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "celular")
    private String celular;
    
    @JoinColumn(name = "codigo_catalogo", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoCatalogo;
    
    @Column(name = "codigo_opcion")
    private boolean codigoOpcion;
    
    @Column(name = "detalle_opcion")
    private String detalleOpcion;
    
    @Column(name = "cantidad")
    private Long cantidad;
    
    @Column(name = "numero_coordenada")
    private Long numeroCoordenada;
    
    @Column(name = "utm_este")
    private String utmEste;
    
    @Column(name = "utm_norte")
    private String utmNorte;
    
    @Column(name = "cota")
    private BigDecimal cota;
    
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

    public Long getCodigoDetalleFichaTecnica() {
        return codigoDetalleFichaTecnica;
    }

    public void setCodigoDetalleFichaTecnica(Long codigoDetalleFichaTecnica) {
        this.codigoDetalleFichaTecnica = codigoDetalleFichaTecnica;
    }

    public FichaTecnica getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(FichaTecnica fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public CatalogoDetalle getCodigoTipoInformacionRegistro() {
        return codigoTipoInformacionRegistro;
    }

    public void setCodigoTipoInformacionRegistro(CatalogoDetalle codigoTipoInformacionRegistro) {
        this.codigoTipoInformacionRegistro = codigoTipoInformacionRegistro;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getApellidoSocio() {
        return apellidoSocio;
    }

    public void setApellidoSocio(String apellidoSocio) {
        this.apellidoSocio = apellidoSocio;
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

    public CatalogoDetalle getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(CatalogoDetalle codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public boolean isCodigoOpcion() {
        return codigoOpcion;
    }

    public void setCodigoOpcion(boolean codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public String getDetalleOpcion() {
        return detalleOpcion;
    }

    public void setDetalleOpcion(String detalleOpcion) {
        this.detalleOpcion = detalleOpcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getNumeroCoordenada() {
        return numeroCoordenada;
    }

    public void setNumeroCoordenada(Long numeroCoordenada) {
        this.numeroCoordenada = numeroCoordenada;
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

    public BigDecimal getCota() {
        return cota;
    }

    public void setCota(BigDecimal cota) {
        this.cota = cota;
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
        return "ec.gob.arcom.migracion.modelo.DetalleFichaTecnica[ codigoDetalleFichaTecnica=" + codigoDetalleFichaTecnica + " ]";
    }
    
}
