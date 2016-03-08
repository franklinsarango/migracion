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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "costo_servicios", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "CostoServicios.findAll", query = "SELECT c FROM CostoServicios c")})
public class CostoServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_costo")
    private Long codigoCosto;
    @Column(name = "codigo_parametro_calculo")
    private BigInteger codigoParametroCalculo;
    @Size(max = 100)
    @Column(name = "formula_calculo_entrega_impresa")
    private String formulaCalculoEntregaImpresa;
    @Size(max = 100)
    @Column(name = "formula_calculo_entrega_medio_magnetico")
    private String formulaCalculoEntregaMedioMagnetico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_referencia_entrega_impresa")
    private BigDecimal valorReferenciaEntregaImpresa;
    @Column(name = "valor_referencia_entrega_medio_magnetico")
    private BigDecimal valorReferenciaEntregaMedioMagnetico;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    //@Column(name = "xml_data")
    //private Serializable xmlData;
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
    @JoinColumn(name = "codigo_concepto_pago", referencedColumnName = "codigo_concepto_pago")
    @ManyToOne
    private ConceptoPago codigoConceptoPago;
    @JoinColumn(name = "tipo_servicio", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoServicio;

    public CostoServicios() {
    }

    public CostoServicios(Long codigoCosto) {
        this.codigoCosto = codigoCosto;
    }

    public Long getCodigoCosto() {
        return codigoCosto;
    }

    public void setCodigoCosto(Long codigoCosto) {
        this.codigoCosto = codigoCosto;
    }

    public BigInteger getCodigoParametroCalculo() {
        return codigoParametroCalculo;
    }

    public void setCodigoParametroCalculo(BigInteger codigoParametroCalculo) {
        this.codigoParametroCalculo = codigoParametroCalculo;
    }

    public String getFormulaCalculoEntregaImpresa() {
        return formulaCalculoEntregaImpresa;
    }

    public void setFormulaCalculoEntregaImpresa(String formulaCalculoEntregaImpresa) {
        this.formulaCalculoEntregaImpresa = formulaCalculoEntregaImpresa;
    }

    public String getFormulaCalculoEntregaMedioMagnetico() {
        return formulaCalculoEntregaMedioMagnetico;
    }

    public void setFormulaCalculoEntregaMedioMagnetico(String formulaCalculoEntregaMedioMagnetico) {
        this.formulaCalculoEntregaMedioMagnetico = formulaCalculoEntregaMedioMagnetico;
    }

    public BigDecimal getValorReferenciaEntregaImpresa() {
        return valorReferenciaEntregaImpresa;
    }

    public void setValorReferenciaEntregaImpresa(BigDecimal valorReferenciaEntregaImpresa) {
        this.valorReferenciaEntregaImpresa = valorReferenciaEntregaImpresa;
    }

    public BigDecimal getValorReferenciaEntregaMedioMagnetico() {
        return valorReferenciaEntregaMedioMagnetico;
    }

    public void setValorReferenciaEntregaMedioMagnetico(BigDecimal valorReferenciaEntregaMedioMagnetico) {
        this.valorReferenciaEntregaMedioMagnetico = valorReferenciaEntregaMedioMagnetico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*public Serializable getXmlData() {
        return xmlData;
    }

    public void setXmlData(Serializable xmlData) {
        this.xmlData = xmlData;
    }*/

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

    public ConceptoPago getCodigoConceptoPago() {
        return codigoConceptoPago;
    }

    public void setCodigoConceptoPago(ConceptoPago codigoConceptoPago) {
        this.codigoConceptoPago = codigoConceptoPago;
    }

    public CatalogoDetalle getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(CatalogoDetalle tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCosto != null ? codigoCosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoServicios)) {
            return false;
        }
        CostoServicios other = (CostoServicios) object;
        if ((this.codigoCosto == null && other.codigoCosto != null) || (this.codigoCosto != null && !this.codigoCosto.equals(other.codigoCosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.constantes.CostoServicios[ codigoCosto=" + codigoCosto + " ]";
    }
    
}
