/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConceptoPago;
import ec.gob.arcom.migracion.modelo.Fase;
import ec.gob.arcom.migracion.modelo.Regimen;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.ConceptoPagoServicio;
import ec.gob.arcom.migracion.servicio.FaseServicio;
import ec.gob.arcom.migracion.servicio.RegimenServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.TipoMaquinariaServicio;
import ec.gob.arcom.migracion.servicio.TipoMineriaServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean(name = "selectItemCtrl")
public class SelectItemCtrl {

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private FaseServicio faseServicio;
    @EJB
    private TipoMineriaServicio tipoMineriaServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private RegimenServicio regimenServicio;
    @EJB
    private TipoMaquinariaServicio tipoMaquinariaServicio;
    @EJB
    private ConceptoPagoServicio conceptoPagoServicio;

    private List<SelectItem> estadosCatalogo;
    private List<SelectItem> sistemasExplotacion;
    private List<SelectItem> fases;
    private List<SelectItem> tiposMineria;
    private List<SelectItem> regionales;
    private List<SelectItem> estadosCodigoCatalogo19;
    private List<SelectItem> fasesCodigoCatalogo;
    private List<SelectItem> tipoMineriaNemonicoCatalogo;
    private List<SelectItem> regionalesCodigoCatalogo;
    private List<SelectItem> regimenes;
    private List<SelectItem> modalidadesTrabajo;
    private List<SelectItem> formasExplotacion;
    private List<SelectItem> metodosExplotacionCodigo;
    private List<SelectItem> formasExplotacionNombre;
    private List<SelectItem> tipoMaquinarias;
    private List<SelectItem> procedenciaMaterial;
    private List<SelectItem> estadosCodigoCatalogo4;
    private List<SelectItem> clasesInscripcion;
    private List<SelectItem> tiposDeRegistro;
    private List<SelectItem> tiposSolicitud;
    private List<SelectItem> librosInscripcion;
    private List<SelectItem> conceptosDePago;
    private List<SelectItem> tiposMaterial;
    private List<SelectItem> entidadesBancarias;
    private List<SelectItem> tipoPago;
    private List<SelectItem> zonas;
    private List<SelectItem> tiposMineriaCodigo;
    private List<SelectItem> tipoServicios;
    private List<SelectItem> tipoContratos;

    public List<SelectItem> getEstadosCatalogo() {
        if (estadosCatalogo == null) {
            estadosCatalogo = new ArrayList<>();
            Catalogo catalogoEstado = catalogoServicio.findByNemonico("ESTAREA");
            List<CatalogoDetalle> estadosCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoEstado.getCodigoCatalogo());

            for (CatalogoDetalle estado : estadosCat) {
                if (estado.getNemonico().equals(ConstantesEnum.EST_INSCRITO.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.EST_TRAMITE.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.EST_SUSPENDIDO.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.EST_ARCHIVADA.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.EST_ACUMULADA.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.EST_OTORGADO.getNemonico())) {
                    estadosCatalogo.add(new SelectItem(estado.getNombre().toUpperCase(), estado.getNombre().toUpperCase()));
                }
            }
        }
        return estadosCatalogo;
    }

    public void setEstadosCatalogo(List<SelectItem> estadosCatalogo) {
        this.estadosCatalogo = estadosCatalogo;
    }

    public List<SelectItem> getSistemasExplotacion() {
        if (sistemasExplotacion == null) {
            sistemasExplotacion = new ArrayList<>();
            Catalogo catalogoSisExp = catalogoServicio.findByNemonico("SISTEXPLOTA");
            List<CatalogoDetalle> sisExp = catalogoDetalleServicio.obtenerPorCatalogo(catalogoSisExp.getCodigoCatalogo());

            for (CatalogoDetalle exp : sisExp) {
                sistemasExplotacion.add(new SelectItem(exp.getNombre().toUpperCase(), exp.getNombre().toUpperCase()));
            }
        }
        return sistemasExplotacion;
    }

    public void setSistemasExplotacion(List<SelectItem> sistemasExplotacion) {
        this.sistemasExplotacion = sistemasExplotacion;
    }

    public List<SelectItem> getFases() {
        if (fases == null) {
            fases = new ArrayList<>();

            for (Fase f : faseServicio.findAll()) {
                fases.add(new SelectItem(f.getNombreFase().toUpperCase(), f.getNombreFase().toUpperCase()));
            }
        }
        return fases;
    }

    public void setFases(List<SelectItem> fases) {
        this.fases = fases;
    }

    public List<SelectItem> getTiposMineria() {
        if (tiposMineria == null) {
            tiposMineria = new ArrayList<>();

            for (TipoMineria tm : tipoMineriaServicio.findAll()) {
                tiposMineria.add(new SelectItem(tm.getNombreTipoMineria().toUpperCase(),
                        tm.getNombreTipoMineria().toUpperCase()));
            }
        }
        return tiposMineria;
    }

    public void setTiposMineria(List<SelectItem> tiposMineria) {
        this.tiposMineria = tiposMineria;
    }

    public List<SelectItem> getRegionales() {
        if (regionales == null) {
            regionales = new ArrayList<>();

            for (Regional r : regionalServicio.findAll()) {
                regionales.add(new SelectItem(r.getNombreRegional().toUpperCase(), r.getNombreRegional().toUpperCase()));
            }
        }
        return regionales;
    }

    public void setRegionales(List<SelectItem> regionales) {
        this.regionales = regionales;
    }

    public List<SelectItem> getEstadosCodigoCatalogo19() {
        if (estadosCodigoCatalogo19 == null) {
            estadosCodigoCatalogo19 = new ArrayList<>();
            Catalogo catalogoEstado = catalogoServicio.findByNemonico("ESTAREA");
            List<CatalogoDetalle> estadosCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoEstado.getCodigoCatalogo());

            for (CatalogoDetalle estado : estadosCat) {
                estadosCodigoCatalogo19.add(new SelectItem(estado.getCodigoCatalogoDetalle(), estado.getNombre().toUpperCase()));
            }
        }
        return estadosCodigoCatalogo19;
    }

    public void setEstadosCodigoCatalogo19(List<SelectItem> estadosCodigoCatalogo19) {
        this.estadosCodigoCatalogo19 = estadosCodigoCatalogo19;
    }

    public List<SelectItem> getFasesCodigoCatalogo() {
        if (fasesCodigoCatalogo == null) {
            fasesCodigoCatalogo = new ArrayList<>();

            for (Fase f : faseServicio.findAll()) {
                fasesCodigoCatalogo.add(new SelectItem(f.getCodigoFase(), f.getNombreFase().toUpperCase()));
            }
        }
        return fasesCodigoCatalogo;
    }

    public void setFasesCodigoCatalogo(List<SelectItem> fasesCodigoCatalogo) {
        this.fasesCodigoCatalogo = fasesCodigoCatalogo;
    }

    public List<SelectItem> getTipoMineriaNemonicoCatalogo() {
        if (tipoMineriaNemonicoCatalogo == null) {
            tipoMineriaNemonicoCatalogo = new ArrayList<>();

            for (TipoMineria tm : tipoMineriaServicio.findAll()) {
                tipoMineriaNemonicoCatalogo.add(new SelectItem(tm.getNemonicoTipoMineria(), tm.getNombreTipoMineria().toUpperCase()));
            }
        }
        return tipoMineriaNemonicoCatalogo;
    }

    public void setTipoMineriaNemonicoCatalogo(List<SelectItem> tipoMineriaNemonicoCatalogo) {
        this.tipoMineriaNemonicoCatalogo = tipoMineriaNemonicoCatalogo;
    }

    public List<SelectItem> getRegionalesCodigoCatalogo() {
        if (regionalesCodigoCatalogo == null) {
            regionalesCodigoCatalogo = new ArrayList<>();

            for (Regional r : regionalServicio.findAll()) {
                regionalesCodigoCatalogo.add(new SelectItem(r.getCodigoRegional(), r.getNombreRegional().toUpperCase()));
            }
        }
        return regionalesCodigoCatalogo;
    }

    public void setRegionalesCodigoCatalogo(List<SelectItem> regionalesCodigoCatalogo) {
        this.regionalesCodigoCatalogo = regionalesCodigoCatalogo;
    }

    public List<SelectItem> getRegimenes() {
        if (regimenes == null) {
            regimenes = new ArrayList<>();

            for (Regimen r : regimenServicio.findAll()) {
                regimenes.add(new SelectItem(r.getCodigoRegimen(), r.getNombre()));
            }
        }
        return regimenes;
    }

    public void setRegimenes(List<SelectItem> regimenes) {
        this.regimenes = regimenes;
    }

    public List<SelectItem> getModalidadesTrabajo() {
        if (modalidadesTrabajo == null) {
            modalidadesTrabajo = new ArrayList<>();

            Catalogo catalogoModTrabajo = catalogoServicio.findByNemonico("MODT");
            List<CatalogoDetalle> modTrabajoCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoModTrabajo.getCodigoCatalogo());
            System.out.println("modTrabajo " + modTrabajoCat.size());
            for (CatalogoDetalle estado : modTrabajoCat) {
                System.out.println("Catalogo Detalle Código: " + estado.getCodigoCatalogoDetalle());
                System.out.println("Catalogo Detalle Nombre: " + estado.getNombre().toUpperCase());
                modalidadesTrabajo.add(new SelectItem(estado.getCodigoCatalogoDetalle(), estado.getNombre().toUpperCase()));
                System.out.println("Tamaño: " + modalidadesTrabajo.size());
            }
        }
        return modalidadesTrabajo;
    }

    public void setModalidadesTrabajo(List<SelectItem> modalidadesTrabajo) {
        this.modalidadesTrabajo = modalidadesTrabajo;
    }

    public List<SelectItem> getFormasExplotacion() {
        if (formasExplotacion == null) {
            formasExplotacion = new ArrayList<>();

            Catalogo catalogoFormExp = catalogoServicio.findByNemonico("FOREXP");
            List<CatalogoDetalle> formExpCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoFormExp.getCodigoCatalogo());

            for (CatalogoDetalle estado : formExpCat) {
                formasExplotacion.add(new SelectItem(estado.getCodigoCatalogoDetalle(), estado.getNombre().toUpperCase()));
            }
        }
        return formasExplotacion;
    }

    public void setFormasExplotacion(List<SelectItem> formasExplotacion) {
        this.formasExplotacion = formasExplotacion;
    }

    public List<SelectItem> getMetodosExplotacionCodigo() {
        if (metodosExplotacionCodigo == null) {
            metodosExplotacionCodigo = new ArrayList<>();

            Catalogo catalogoMetodoExp = catalogoServicio.findByNemonico("SISTEXPLOTA");
            List<CatalogoDetalle> metExpCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoMetodoExp.getCodigoCatalogo());

            for (CatalogoDetalle metodoExplotacion : metExpCat) {
                metodosExplotacionCodigo.add(new SelectItem(metodoExplotacion.getCodigoCatalogoDetalle(), metodoExplotacion.getNombre().toUpperCase()));
            }
        }
        return metodosExplotacionCodigo;
    }

    public void setMetodosExplotacionCodigo(List<SelectItem> metodosExplotacionCodigo) {
        this.metodosExplotacionCodigo = metodosExplotacionCodigo;
    }

    public List<SelectItem> getFormasExplotacionNombre() {
        if (formasExplotacionNombre == null) {
            formasExplotacionNombre = new ArrayList<>();

            Catalogo catalogoFormExp = catalogoServicio.findByNemonico("FOREXP");
            List<CatalogoDetalle> formExpCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoFormExp.getCodigoCatalogo());

            for (CatalogoDetalle formaExplotacion : formExpCat) {
                formasExplotacionNombre.add(new SelectItem(formaExplotacion.getNombre().toUpperCase(), formaExplotacion.getNombre().toUpperCase()));
            }
        }
        return formasExplotacionNombre;
    }

    public void setFormasExplotacionNombre(List<SelectItem> formasExplotacionNombre) {
        this.formasExplotacionNombre = formasExplotacionNombre;
    }

    public List<SelectItem> getTipoMaquinarias() {
        if (tipoMaquinarias == null) {
            tipoMaquinarias = new ArrayList<>();

            for (TipoMaquinaria tm : tipoMaquinariaServicio.findAll()) {
                tipoMaquinarias.add(new SelectItem(tm.getCodigoTipoMaquinaria(), tm.getDescripcion()));
            }
        }
        return tipoMaquinarias;
    }

    public void setTipoMaquinarias(List<SelectItem> tipoMaquinarias) {
        this.tipoMaquinarias = tipoMaquinarias;
    }

    public List<SelectItem> getProcedenciaMaterial() {
        if (procedenciaMaterial == null) {
            procedenciaMaterial = new ArrayList<>();

            Catalogo catalogoProcMaterial = catalogoServicio.findByNemonico("PROCMAT");

            List<CatalogoDetalle> procedenciaMaterialItems = catalogoDetalleServicio.obtenerPorCatalogo(catalogoProcMaterial.getCodigoCatalogo());

            for (CatalogoDetalle procMat : procedenciaMaterialItems) {
                procedenciaMaterial.add(new SelectItem(procMat.getCodigoCatalogoDetalle(), procMat.getNombre()));
            }
        }
        return procedenciaMaterial;
    }

    public void setProcedenciaMaterial(List<SelectItem> procedenciaMaterial) {
        this.procedenciaMaterial = procedenciaMaterial;
    }

    public List<SelectItem> getEstadosCodigoCatalogo4() {
        if (estadosCodigoCatalogo4 == null) {
            estadosCodigoCatalogo4 = new ArrayList<>();
            Catalogo catalogoEstado = catalogoServicio.findByNemonico("ESCON");
            List<CatalogoDetalle> estadosCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoEstado.getCodigoCatalogo());

            for (CatalogoDetalle estado : estadosCat) {
                System.out.println("estados4: " + estado.getNombre() + " " + estado.getNemonico());
                if (estado.getNemonico().equals(ConstantesEnum.ESTCONC_INSCRITO.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.ESTCONC_TRAMITE.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.ESTCONC_SUSPENDIDO.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.ESTCONC_ARCHIVADA.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.ESTCONC_ACUMULADA.getNemonico())
                        || estado.getNemonico().equals(ConstantesEnum.ESTCONC_OTORGADO.getNemonico())) {
                    estadosCodigoCatalogo4.add(new SelectItem(estado.getCodigoCatalogoDetalle(), estado.getNombre().toUpperCase()));
                }
            }
        }
        return estadosCodigoCatalogo4;
    }

    public void setEstadosCodigoCatalogo4(List<SelectItem> estadosCodigoCatalogo4) {
        this.estadosCodigoCatalogo4 = estadosCodigoCatalogo4;
    }

    public List<SelectItem> getClasesInscripcion() {
        if (clasesInscripcion == null) {
            clasesInscripcion = new ArrayList<>();
            Catalogo catalogoClasesInscripcion = catalogoServicio.findByNemonico("CLAINSCRIP");
            List<CatalogoDetalle> clasesInscripcionCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoClasesInscripcion.getCodigoCatalogo());

            for (CatalogoDetalle clasesInscrip : clasesInscripcionCat) {
                clasesInscripcion.add(new SelectItem(clasesInscrip.getCodigoCatalogoDetalle(), clasesInscrip.getNombre().toUpperCase()));
            }
        }
        return clasesInscripcion;
    }

    public void setClasesInscripcion(List<SelectItem> clasesInscripcion) {
        this.clasesInscripcion = clasesInscripcion;
    }

    public List<SelectItem> getTiposDeRegistro() {
        if (tiposDeRegistro == null) {
            tiposDeRegistro = new ArrayList<>();
            Catalogo catalogoTiposRegistro = catalogoServicio.findByNemonico("TIPSREGS");
            List<CatalogoDetalle> tiposRegistroCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTiposRegistro.getCodigoCatalogo());

            for (CatalogoDetalle tiposRegs : tiposRegistroCat) {
                tiposDeRegistro.add(new SelectItem(tiposRegs.getCodigoCatalogoDetalle(), tiposRegs.getNombre().toUpperCase()));
            }
        }
        return tiposDeRegistro;
    }

    public void setTiposDeRegistro(List<SelectItem> tiposDeRegistro) {
        this.tiposDeRegistro = tiposDeRegistro;
    }

    public List<SelectItem> getTiposSolicitud() {
        if (tiposSolicitud == null) {
            tiposSolicitud = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.tipoSolicitudes()) {
                tiposSolicitud.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
            }
        }
        return tiposSolicitud;
    }

    public void setTiposSolicitud(List<SelectItem> tiposSolicitud) {
        this.tiposSolicitud = tiposSolicitud;
    }

    public List<SelectItem> getLibrosInscripcion() {
        if (librosInscripcion == null) {
            librosInscripcion = new ArrayList<>();
            Catalogo catalogoLibrosInscripcion = catalogoServicio.findByNemonico("TIPLIB");
            List<CatalogoDetalle> librosInscCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoLibrosInscripcion.getCodigoCatalogo());

            for (CatalogoDetalle libros : librosInscCat) {
                librosInscripcion.add(new SelectItem(libros.getCodigoCatalogoDetalle(), libros.getNombre().toUpperCase()));
            }
        }
        return librosInscripcion;
    }

    public void setLibrosInscripcion(List<SelectItem> librosInscripcion) {
        this.librosInscripcion = librosInscripcion;
    }

    public List<SelectItem> getConceptosDePago() {
        if (conceptosDePago == null) {
            conceptosDePago = new ArrayList<>();

            for (ConceptoPago cp : conceptoPagoServicio.findAll()) {
                conceptosDePago.add(new SelectItem(cp.getCodigoConceptoPago(), cp.getDescripcionConceptoPago()));
            }
        }
        return conceptosDePago;
    }

    public void setConceptosDePago(List<SelectItem> conceptosDePago) {
        this.conceptosDePago = conceptosDePago;
    }

    public List<SelectItem> getTiposMaterial() {
        if (tiposMaterial == null) {
            tiposMaterial = new ArrayList<>();
            Catalogo catalogoTipoMaterial = catalogoServicio.findByNemonico("MATEXP");
            List<CatalogoDetalle> tipMatCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoMaterial.getCodigoCatalogo());

            for (CatalogoDetalle tipMat : tipMatCat) {
                tiposMaterial.add(new SelectItem(tipMat.getCodigoCatalogoDetalle(), tipMat.getNombre().toUpperCase()));
            }
        }
        return tiposMaterial;
    }

    public void setTiposMaterial(List<SelectItem> tiposMaterial) {
        this.tiposMaterial = tiposMaterial;
    }

    public List<SelectItem> getEntidadesBancarias() {
        if (entidadesBancarias == null) {
            entidadesBancarias = new ArrayList<>();
            Catalogo catalogoBanco = catalogoServicio.findByNemonico("BANCO");
            List<CatalogoDetalle> bancoCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoBanco.getCodigoCatalogo());

            for (CatalogoDetalle banco : bancoCat) {
                entidadesBancarias.add(new SelectItem(banco.getCodigoCatalogoDetalle(), banco.getNombre().toUpperCase()));
            }
        }
        return entidadesBancarias;
    }

    public void setEntidadesBancarias(List<SelectItem> entidadesBancarias) {
        this.entidadesBancarias = entidadesBancarias;
    }

    public List<SelectItem> getTipoPago() {
        if (tipoPago == null) {
            tipoPago = new ArrayList<>();
            Catalogo catalogoTipoPago = catalogoServicio.findByNemonico("TIPCAL");
            List<CatalogoDetalle> tipoPagoCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoTipoPago.getCodigoCatalogo());

            for (CatalogoDetalle tipPg : tipoPagoCat) {
                tipoPago.add(new SelectItem(tipPg.getCodigoCatalogoDetalle(), tipPg.getNombre().toUpperCase()));
            }
        }
        return tipoPago;
    }

    public void setTipoPago(List<SelectItem> tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<SelectItem> getZonas() {
        if (zonas == null) {
            zonas = new ArrayList<>();
            Catalogo catalogoZona = catalogoServicio.findByNemonico("ZONGEO");
            List<CatalogoDetalle> zonaCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogoZona.getCodigoCatalogo());

            for (CatalogoDetalle zona : zonaCat) {
                zonas.add(new SelectItem(zona.getCodigoCatalogoDetalle(), zona.getNombre().toUpperCase()));
            }
        }
        return zonas;
    }

    public void setZonas(List<SelectItem> zonas) {
        this.zonas = zonas;
    }

    public List<SelectItem> getTiposMineriaCodigo() {
        if (tiposMineriaCodigo == null) {
            tiposMineriaCodigo = new ArrayList<>();

            for (TipoMineria tm : tipoMineriaServicio.findAll()) {
                if (tm.getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                        || tm.getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                        || tm.getCodigoTipoMineria().equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo()))
                tiposMineriaCodigo.add(new SelectItem(tm.getCodigoTipoMineria(), 
                        tm.getNombreTipoMineria().toUpperCase()));
            }
        }
        return tiposMineriaCodigo;
    }

    public void setTiposMineriaCodigo(List<SelectItem> tiposMineriaCodigo) {
        this.tiposMineriaCodigo = tiposMineriaCodigo;
    }

    public List<SelectItem> getTipoServicios() {
        if (tipoServicios == null) {
            tipoServicios = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("LISTSERV");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoServCat = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoServCat) {
                    tipoServicios.add(new SelectItem(catDet.getCodigoCatalogoDetalle(), catDet.getNombre()));
                }
            }
        }
        return tipoServicios;
    }

    public void setTipoServicios(List<SelectItem> tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public List<SelectItem> getTipoContratos() {
        if (tipoContratos == null) {
            tipoContratos = new ArrayList<>();
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPSCONTR");
            if (catalogo != null) {
                List<CatalogoDetalle> tipoCont = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
                for (CatalogoDetalle catDet : tipoCont) {
                    tipoContratos.add(new SelectItem(catDet.getCodigoCatalogoDetalle(), catDet.getNombre()));
                }
            }
        }
        return tipoContratos;
    }

    public void setTipoContratos(List<SelectItem> tipoContratos) {
        this.tipoContratos = tipoContratos;
    }

}
