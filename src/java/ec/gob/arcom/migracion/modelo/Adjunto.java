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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author latinusprogramador
 */
@Entity
@Table(name = "adjunto", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "Adjunto.findAll", query = "SELECT a FROM Adjunto a"),
    @NamedQuery(name = "Adjunto.findByCodigoAdjunto", query = "SELECT a FROM Adjunto a WHERE a.codigoAdjunto = :codigoAdjunto"),
    @NamedQuery(name = "Adjunto.findByNombreAdjunto", query = "SELECT a FROM Adjunto a WHERE a.nombreAdjunto = :nombreAdjunto"),
    //named creado
    @NamedQuery(name = "Adjunto.findByCodigoTramiteTipoDocumento", query = "SELECT a FROM Adjunto a WHERE a.codigoTramite = :codigoTramite and a.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Adjunto.findByExtensionAdjunto", query = "SELECT a FROM Adjunto a WHERE a.extensionAdjunto = :extensionAdjunto"),
    @NamedQuery(name = "Adjunto.findByIdDocumento", query = "SELECT a FROM Adjunto a WHERE a.idDocumento = :idDocumento"),
    @NamedQuery(name = "Adjunto.findByUrlDocumento", query = "SELECT a FROM Adjunto a WHERE a.urlDocumento = :urlDocumento"),
    @NamedQuery(name = "Adjunto.findByTipoDocumento", query = "SELECT a FROM Adjunto a WHERE a.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Adjunto.findByCodigoTramite", query = "SELECT a FROM Adjunto a WHERE a.codigoTramite = :codigoTramite"),
    @NamedQuery(name = "Adjunto.findByTramite", query = "SELECT a FROM Adjunto a WHERE a.tramite = :tramite"),
    @NamedQuery(name = "Adjunto.findByCodigoTramiteTramite", query = "SELECT a FROM Adjunto a WHERE a.codigoTramite = :codigoTramite and a.tramite = :tramite"),
    @NamedQuery(name = "Adjunto.findByCampoReservado05", query = "SELECT a FROM Adjunto a WHERE a.campoReservado05 = :campoReservado05"),
    @NamedQuery(name = "Adjunto.findByCampoReservado04", query = "SELECT a FROM Adjunto a WHERE a.campoReservado04 = :campoReservado04"),
    @NamedQuery(name = "Adjunto.findByCampoReservado03", query = "SELECT a FROM Adjunto a WHERE a.campoReservado03 = :campoReservado03"),
    @NamedQuery(name = "Adjunto.findByCampoReservado02", query = "SELECT a FROM Adjunto a WHERE a.campoReservado02 = :campoReservado02"),
    @NamedQuery(name = "Adjunto.findByCampoReservado01", query = "SELECT a FROM Adjunto a WHERE a.campoReservado01 = :campoReservado01"),
    @NamedQuery(name = "Adjunto.findByEstadoRegistro", query = "SELECT a FROM Adjunto a WHERE a.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "Adjunto.findByFechaCreacion", query = "SELECT a FROM Adjunto a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Adjunto.findByUsuarioCreacion", query = "SELECT a FROM Adjunto a WHERE a.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "Adjunto.findByFechaModificacion", query = "SELECT a FROM Adjunto a WHERE a.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Adjunto.findByUsuarioModificacion", query = "SELECT a FROM Adjunto a WHERE a.usuarioModificacion = :usuarioModificacion")})
public class Adjunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo_adjunto")
    private Long codigoAdjunto;
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;
    @Column(name = "extension_adjunto")
    private String extensionAdjunto;
    @Column(name = "id_documento")
    private String idDocumento;
    @Column(name = "url_documento")
    private String urlDocumento;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Lob
    @Column(name = "documento")
    private byte[] documento;
    @Column(name = "codigo_tramite")
    private Long codigoTramite;
    @Column(name = "tramite")
    private String tramite;
    @Column(name = "campo_reservado_05")
    private String campoReservado05;
    @Column(name = "campo_reservado_04")
    private String campoReservado04;
    @Column(name = "campo_reservado_03")
    private String campoReservado03;
    @Column(name = "campo_reservado_02")
    private String campoReservado02;
    @Column(name = "campo_reservado_01")
    private String campoReservado01;
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private Long usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private Long usuarioModificacion;
    @JoinColumn(name = "etapa_documento", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle etapaDocumento;
    
    @JoinColumn(name = "codigo_requisito", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle codigoRequisito;

    public Adjunto() {
    }

    public Adjunto(Long codigoAdjunto) {
        this.codigoAdjunto = codigoAdjunto;
    }

    public Long getCodigoAdjunto() {
        return codigoAdjunto;
    }

    public void setCodigoAdjunto(Long codigoAdjunto) {
        this.codigoAdjunto = codigoAdjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public String getExtensionAdjunto() {
        return extensionAdjunto;
    }

    public void setExtensionAdjunto(String extensionAdjunto) {
        this.extensionAdjunto = extensionAdjunto;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public Long getCodigoTramite() {
        return codigoTramite;
    }

    public void setCodigoTramite(Long codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
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

    public Long getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Long usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public CatalogoDetalle getEtapaDocumento() {
        return etapaDocumento;
    }

    public void setEtapaDocumento(CatalogoDetalle etapaDocumento) {
        this.etapaDocumento = etapaDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAdjunto != null ? codigoAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adjunto)) {
            return false;
        }
        Adjunto other = (Adjunto) object;
        if ((this.codigoAdjunto == null && other.codigoAdjunto != null) || (this.codigoAdjunto != null && !this.codigoAdjunto.equals(other.codigoAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.latinus.arcom.comun.persistencia.entidades.Adjunto[ codigoAdjunto=" + codigoAdjunto + " ]";
    }
    
    /**
     * @return the codigoRequisito
     */
    public CatalogoDetalle getCodigoRequisito() {
        return codigoRequisito;
    }

    /**
     * @param codigoRequisito the codigoRequisito to set
     */
    public void setCodigoRequisito(CatalogoDetalle codigoRequisito) {
        this.codigoRequisito = codigoRequisito;
    }
}
