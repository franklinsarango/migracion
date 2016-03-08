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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "catalogo_detalle", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "CatalogoDetalle.findAll", query = "SELECT c FROM CatalogoDetalle c")})
public class CatalogoDetalle implements Serializable {
    @OneToMany(mappedBy = "tipoServicio")
    private List<CostoServicios> costoServiciosList;

    @OneToMany(mappedBy = "tipoResolucion")
    private Collection<Resolucion> resolucionCollection;
    @OneToMany(mappedBy = "estadoResolucion")
    private Collection<Resolucion> resolucionCollection1;
    @OneToMany(mappedBy = "codigoTipoServicio")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList;
    @OneToMany(mappedBy = "estadoPago")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList1;
    @OneToMany(mappedBy = "tipoPago")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList2;
    @OneToMany(mappedBy = "codigoBanco")
    private List<RegistroPagoObligaciones> registroPagoObligacionesList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoParametro")
    private List<ParametroSistema> parametroSistemaList;
    @OneToMany(mappedBy = "tipoDato")
    private List<ParametroSistema> parametroSistemaList1;
    @OneToMany(mappedBy = "estadoSujetoMinero")
    private List<SujetoMinero> sujetoMineroList;
    @OneToMany(mappedBy = "tipoSujetoMinero")
    private List<SujetoMinero> sujetoMineroList1;
    @OneToMany(mappedBy = "tipoInstrumento")
    private List<Instrumento> instrumentoList;
    @OneToMany(mappedBy = "tipoDocumentoOtorgamiento")
    private List<Instrumento> instrumentoList1;
    @OneToMany(mappedBy = "claseInscripcion")
    private List<Instrumento> instrumentoList2;
    @OneToMany(mappedBy = "estadoInforme")
    private Collection<Informe> informeCollection;
    @OneToMany(mappedBy = "estadoPeriodo")
    private Collection<Periodo> periodoCollection;
    @OneToMany(mappedBy = "frecuenciaPeriodo")
    private Collection<Periodo> periodoCollection1;
    @OneToMany(mappedBy = "formaExplotacion")
    private Collection<TipoMaquinaria> tipoMaquinariaCollection;
    @OneToMany(mappedBy = "tipoModalidad")
    private Collection<TipoMaquinaria> tipoMaquinariaCollection1;
    @OneToMany(mappedBy = "tipoMaterial")
    private Collection<TipoMaquinaria> tipoMaquinariaCollection2;
    @OneToMany(mappedBy = "estadoLicencia")
    private Collection<LicenciaComercializacion> licenciaComercializacionCollection;
    @OneToMany(mappedBy = "tipoLicencia")
    private Collection<LicenciaComercializacion> licenciaComercializacionCollection1;
    @OneToMany(mappedBy = "categoriaPlanta")
    private Collection<PlantaBeneficio> plantaBeneficioCollection;
    @OneToMany(mappedBy = "estadoPlanta")
    private Collection<PlantaBeneficio> plantaBeneficioCollection1;
    @OneToMany(mappedBy = "unidadPeso")
    private Collection<PlantaBeneficio> plantaBeneficioCollection2;
    @OneToMany(mappedBy = "estadoArea")
    private List<AreaMinera> areaMineraList;
    @OneToMany(mappedBy = "clasePersona")
    private List<PersonaJuridica> personaJuridicaList;
    @OneToMany(mappedBy = "codigoZona")
    private List<ConcesionMinera> concesionMineraList;
    @OneToMany(mappedBy = "codigoFormaExplotacion")
    private List<ConcesionMinera> concesionMineraList1;
    @OneToMany(mappedBy = "codigoModalidadTrabajo")
    private List<ConcesionMinera> concesionMineraList2;
    @OneToMany(mappedBy = "estadoConcesion")
    private List<ConcesionMinera> concesionMineraList3;
    @OneToMany(mappedBy = "sistemaReferencia")
    private List<CoordenadaArea> coordenadaAreaList;
    @OneToMany(mappedBy = "genero")
    private List<PersonaNatural> personaNaturalList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_catalogo_detalle")
    private Long codigoCatalogoDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 400)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nemonico")
    private String nemonico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "valor")
    private String valor;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLocalidad")
    private Collection<Localidad> localidadCollection;
    @JoinColumn(name = "codigo_catalogo", referencedColumnName = "codigo_catalogo")
    @ManyToOne(optional = false)
    private Catalogo codigoCatalogo;

    public CatalogoDetalle() {
    }

    public CatalogoDetalle(Long codigoCatalogoDetalle) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
    }

    public CatalogoDetalle(Long codigoCatalogoDetalle, String nombre, String nemonico, String valor) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
        this.nombre = nombre;
        this.nemonico = nemonico;
        this.valor = valor;
    }

    public Long getCodigoCatalogoDetalle() {
        return codigoCatalogoDetalle;
    }

    public void setCodigoCatalogoDetalle(Long codigoCatalogoDetalle) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public Collection<Localidad> getLocalidadCollection() {
        return localidadCollection;
    }

    public void setLocalidadCollection(Collection<Localidad> localidadCollection) {
        this.localidadCollection = localidadCollection;
    }

    public Catalogo getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(Catalogo codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCatalogoDetalle != null ? codigoCatalogoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDetalle)) {
            return false;
        }
        CatalogoDetalle other = (CatalogoDetalle) object;
        if ((this.codigoCatalogoDetalle == null && other.codigoCatalogoDetalle != null) || (this.codigoCatalogoDetalle != null && !this.codigoCatalogoDetalle.equals(other.codigoCatalogoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.CatalogoDetalle[ codigoCatalogoDetalle=" + codigoCatalogoDetalle + " ]";
    }

    public List<AreaMinera> getAreaMineraList() {
        return areaMineraList;
    }

    public void setAreaMineraList(List<AreaMinera> areaMineraList) {
        this.areaMineraList = areaMineraList;
    }

    public List<PersonaJuridica> getPersonaJuridicaList() {
        return personaJuridicaList;
    }

    public void setPersonaJuridicaList(List<PersonaJuridica> personaJuridicaList) {
        this.personaJuridicaList = personaJuridicaList;
    }

    public List<ConcesionMinera> getConcesionMineraList() {
        return concesionMineraList;
    }

    public void setConcesionMineraList(List<ConcesionMinera> concesionMineraList) {
        this.concesionMineraList = concesionMineraList;
    }

    public List<ConcesionMinera> getConcesionMineraList1() {
        return concesionMineraList1;
    }

    public void setConcesionMineraList1(List<ConcesionMinera> concesionMineraList1) {
        this.concesionMineraList1 = concesionMineraList1;
    }

    public List<ConcesionMinera> getConcesionMineraList2() {
        return concesionMineraList2;
    }

    public void setConcesionMineraList2(List<ConcesionMinera> concesionMineraList2) {
        this.concesionMineraList2 = concesionMineraList2;
    }

    public List<ConcesionMinera> getConcesionMineraList3() {
        return concesionMineraList3;
    }

    public void setConcesionMineraList3(List<ConcesionMinera> concesionMineraList3) {
        this.concesionMineraList3 = concesionMineraList3;
    }

    public List<CoordenadaArea> getCoordenadaAreaList() {
        return coordenadaAreaList;
    }

    public void setCoordenadaAreaList(List<CoordenadaArea> coordenadaAreaList) {
        this.coordenadaAreaList = coordenadaAreaList;
    }

    public List<PersonaNatural> getPersonaNaturalList() {
        return personaNaturalList;
    }

    public void setPersonaNaturalList(List<PersonaNatural> personaNaturalList) {
        this.personaNaturalList = personaNaturalList;
    }

    public Collection<LicenciaComercializacion> getLicenciaComercializacionCollection() {
        return licenciaComercializacionCollection;
    }

    public void setLicenciaComercializacionCollection(Collection<LicenciaComercializacion> licenciaComercializacionCollection) {
        this.licenciaComercializacionCollection = licenciaComercializacionCollection;
    }

    public Collection<LicenciaComercializacion> getLicenciaComercializacionCollection1() {
        return licenciaComercializacionCollection1;
    }

    public void setLicenciaComercializacionCollection1(Collection<LicenciaComercializacion> licenciaComercializacionCollection1) {
        this.licenciaComercializacionCollection1 = licenciaComercializacionCollection1;
    }

    public Collection<PlantaBeneficio> getPlantaBeneficioCollection() {
        return plantaBeneficioCollection;
    }

    public void setPlantaBeneficioCollection(Collection<PlantaBeneficio> plantaBeneficioCollection) {
        this.plantaBeneficioCollection = plantaBeneficioCollection;
    }

    public Collection<PlantaBeneficio> getPlantaBeneficioCollection1() {
        return plantaBeneficioCollection1;
    }

    public void setPlantaBeneficioCollection1(Collection<PlantaBeneficio> plantaBeneficioCollection1) {
        this.plantaBeneficioCollection1 = plantaBeneficioCollection1;
    }

    public Collection<PlantaBeneficio> getPlantaBeneficioCollection2() {
        return plantaBeneficioCollection2;
    }

    public void setPlantaBeneficioCollection2(Collection<PlantaBeneficio> plantaBeneficioCollection2) {
        this.plantaBeneficioCollection2 = plantaBeneficioCollection2;
    }

    public Collection<TipoMaquinaria> getTipoMaquinariaCollection() {
        return tipoMaquinariaCollection;
    }

    public void setTipoMaquinariaCollection(Collection<TipoMaquinaria> tipoMaquinariaCollection) {
        this.tipoMaquinariaCollection = tipoMaquinariaCollection;
    }

    public Collection<TipoMaquinaria> getTipoMaquinariaCollection1() {
        return tipoMaquinariaCollection1;
    }

    public void setTipoMaquinariaCollection1(Collection<TipoMaquinaria> tipoMaquinariaCollection1) {
        this.tipoMaquinariaCollection1 = tipoMaquinariaCollection1;
    }

    public Collection<TipoMaquinaria> getTipoMaquinariaCollection2() {
        return tipoMaquinariaCollection2;
    }

    public void setTipoMaquinariaCollection2(Collection<TipoMaquinaria> tipoMaquinariaCollection2) {
        this.tipoMaquinariaCollection2 = tipoMaquinariaCollection2;
    }

    public Collection<Informe> getInformeCollection() {
        return informeCollection;
    }

    public void setInformeCollection(Collection<Informe> informeCollection) {
        this.informeCollection = informeCollection;
    }

    public Collection<Periodo> getPeriodoCollection() {
        return periodoCollection;
    }

    public void setPeriodoCollection(Collection<Periodo> periodoCollection) {
        this.periodoCollection = periodoCollection;
    }

    public Collection<Periodo> getPeriodoCollection1() {
        return periodoCollection1;
    }

    public void setPeriodoCollection1(Collection<Periodo> periodoCollection1) {
        this.periodoCollection1 = periodoCollection1;
    }

    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }

    public List<Instrumento> getInstrumentoList1() {
        return instrumentoList1;
    }

    public void setInstrumentoList1(List<Instrumento> instrumentoList1) {
        this.instrumentoList1 = instrumentoList1;
    }

    public List<Instrumento> getInstrumentoList2() {
        return instrumentoList2;
    }

    public void setInstrumentoList2(List<Instrumento> instrumentoList2) {
        this.instrumentoList2 = instrumentoList2;
    }

    public List<SujetoMinero> getSujetoMineroList() {
        return sujetoMineroList;
    }

    public void setSujetoMineroList(List<SujetoMinero> sujetoMineroList) {
        this.sujetoMineroList = sujetoMineroList;
    }

    public List<SujetoMinero> getSujetoMineroList1() {
        return sujetoMineroList1;
    }

    public void setSujetoMineroList1(List<SujetoMinero> sujetoMineroList1) {
        this.sujetoMineroList1 = sujetoMineroList1;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList() {
        return registroPagoObligacionesList;
    }

    public void setRegistroPagoObligacionesList(List<RegistroPagoObligaciones> registroPagoObligacionesList) {
        this.registroPagoObligacionesList = registroPagoObligacionesList;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList1() {
        return registroPagoObligacionesList1;
    }

    public void setRegistroPagoObligacionesList1(List<RegistroPagoObligaciones> registroPagoObligacionesList1) {
        this.registroPagoObligacionesList1 = registroPagoObligacionesList1;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList2() {
        return registroPagoObligacionesList2;
    }

    public void setRegistroPagoObligacionesList2(List<RegistroPagoObligaciones> registroPagoObligacionesList2) {
        this.registroPagoObligacionesList2 = registroPagoObligacionesList2;
    }

    public List<RegistroPagoObligaciones> getRegistroPagoObligacionesList3() {
        return registroPagoObligacionesList3;
    }

    public void setRegistroPagoObligacionesList3(List<RegistroPagoObligaciones> registroPagoObligacionesList3) {
        this.registroPagoObligacionesList3 = registroPagoObligacionesList3;
    }

    public List<ParametroSistema> getParametroSistemaList() {
        return parametroSistemaList;
    }

    public void setParametroSistemaList(List<ParametroSistema> parametroSistemaList) {
        this.parametroSistemaList = parametroSistemaList;
    }

    public List<ParametroSistema> getParametroSistemaList1() {
        return parametroSistemaList1;
    }

    public void setParametroSistemaList1(List<ParametroSistema> parametroSistemaList1) {
        this.parametroSistemaList1 = parametroSistemaList1;
    }

    public Collection<Resolucion> getResolucionCollection() {
        return resolucionCollection;
    }

    public void setResolucionCollection(Collection<Resolucion> resolucionCollection) {
        this.resolucionCollection = resolucionCollection;
    }

    public Collection<Resolucion> getResolucionCollection1() {
        return resolucionCollection1;
    }

    public void setResolucionCollection1(Collection<Resolucion> resolucionCollection1) {
        this.resolucionCollection1 = resolucionCollection1;
    }

    public List<CostoServicios> getCostoServiciosList() {
        return costoServiciosList;
    }

    public void setCostoServiciosList(List<CostoServicios> costoServiciosList) {
        this.costoServiciosList = costoServiciosList;
    }

}
