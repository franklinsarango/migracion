/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.RecursoServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean
@ViewScoped
public class ReporteCtrl extends BaseCtrl {

    @EJB
    private RecursoServicio recursoServicio;
    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;

    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private JasperPrint jasperPrint;
    private Long codigoTipoMineria;
    private List<SelectItem> tipoSolicitudesPrincipales;
    private List<SelectItem> tipoSolicitudesDeConcesionMinera;
    private Long codigoSubtipoMineria;
    private boolean concesionMinera;
    private boolean mostrarFiltroRegional;
    private boolean mostrarFiltroFecha;
    private List<SelectItem> tipoSolicitudes;
    private Date fechaDesdeFiltro;
    private Date fechaHastaFiltro;
    private List<SelectItem> regionales;
    private String prefijoRegionalFiltro;
    private String urlReporte;

    @PostConstruct
    public void init() {
        fechaHastaFiltro = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        fechaDesdeFiltro = calendar.getTime();

        try {            
             getRegionales();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public void generarReporteConcesionMineraBirt() {
        System.out.println("entra generarReporteConcesionMineraBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/concesionesMineras.rptdesign&codigoTipoMineria=" + codigoTipoMineria
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteLicenciaComercializacionBirt() {
        System.out.println("entra generarReporteLicenciaComercializacionBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/licenciasComercializacion.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReportePlantasBeneficioBirt() {
        System.out.println("entra generarReportePlantasBeneficioBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/plantasBeneficio.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteDerechosMinerosConsolidadosBirt() {
        System.out.println("entra generarReporteDerechosMinerosConsolidados");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/consolidadoDerechosMinerosVig.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }

    public void generarReporteObligacionesEconomicasBirt() {
        System.out.println("entra generarReporteObligacionesEconomicas");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/Rpt-patentesutilidadesregalias.rptdesign&"
                + "regional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteContratosOperacionBirt() {
        System.out.println("entra generarReporteContratosOperacionBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/contratos_operacion.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoConcesionesBirt() {
        System.out.println("entra generarReporteConsolidadoConcesionesBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoConcesiones.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoMineriaArtesanalBirt() {
        System.out.println("entra generarReporteConsolidadoMineriaArtesanalBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoMineriaArtesanal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoLibreAprovBirt() {
        System.out.println("entra generarReporteConsolidadoLibreAprovBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoLibreAprovechamiento.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoPlantaBeneficioBirt() {
        System.out.println("entra generarReporteConsolidadoPlantaBeneficioBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoPlantasBeneficio.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoLicenciasComerBirt() {
        System.out.println("entra generarReporteConsolidadoLicenciasComerBirt");
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoLicenciasComercializacion.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public Long getCodigoTipoMineria() {
        return codigoTipoMineria;
    }

    public void setCodigoTipoMineria(Long codigoTipoMineria) {
        this.codigoTipoMineria = codigoTipoMineria;
    }

    public List<SelectItem> getTipoSolicitudesPrincipales() {
        if (tipoSolicitudesPrincipales == null) {
            tipoSolicitudesPrincipales = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.tipoSolicitudesPrincipales()) {
                tipoSolicitudesPrincipales.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
            }
        }
        return tipoSolicitudesPrincipales;
    }

    public void setTipoSolicitudesPrincipales(List<SelectItem> tipoSolicitudesPrincipales) {
        this.tipoSolicitudesPrincipales = tipoSolicitudesPrincipales;
    }

    public List<SelectItem> getTipoSolicitudesDeConcesionMinera() {
        if (tipoSolicitudesDeConcesionMinera == null) {
            tipoSolicitudesDeConcesionMinera = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.tipoSolicitudesDeConcesionMinera()) {
                tipoSolicitudesDeConcesionMinera.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
            }
        }
        return tipoSolicitudesDeConcesionMinera;
    }

    public void setTipoSolicitudesDeConcesionMinera(List<SelectItem> tipoSolicitudesDeConcesionMinera) {
        this.tipoSolicitudesDeConcesionMinera = tipoSolicitudesDeConcesionMinera;
    }

    public Long getCodigoSubtipoMineria() {
        return codigoSubtipoMineria;
    }

    public void setCodigoSubtipoMineria(Long codigoSubtipoMineria) {
        this.codigoSubtipoMineria = codigoSubtipoMineria;
    }

    public boolean isConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(boolean concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    public void generarReporte() {
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            generarReporteConcesionMineraBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
            generarReporteLicenciaComercializacionBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
            generarReportePlantasBeneficioBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS.getCodigo())) {
            generarReporteDerechosMinerosConsolidadosBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo())) {
            generarReporteObligacionesEconomicasBirt();
        }  else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE.getCodigo())) {
            generarReporteContratosOperacionBirt();
        }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CONSOLIDADO_CONCESIONES.getCodigo())) {
            generarReporteConsolidadoConcesionesBirt();
        }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CONSOLIDADO_MINERIA_ARTESANAL.getCodigo())) {
            generarReporteConsolidadoMineriaArtesanalBirt();
            }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CONSOLIDADO_LIBRE_APROVECHAMIENTO.getCodigo())) {
            generarReporteConsolidadoLibreAprovBirt();
        }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CONSOLIDADO_PLANTAS_BENEFICIO.getCodigo())) {
            generarReporteConsolidadoPlantaBeneficioBirt();
        }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CONSOLIDADO_LICENCIAS_COMERCIALIZACION.getCodigo())) {
            generarReporteConsolidadoLicenciasComerBirt();
        }
    }

    public void habilitarSubTipoReporte() {
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            concesionMinera = true;
        } else {
            concesionMinera = false;
        }
    }

    public void listenerTipoReporte() {
        if (codigoTipoMineria != null) {
            if(regionales == null){
                getRegionales();
            }
            
            //SE AGREGA LA OPCION TODAS LAS REGIONALES
            if (codigoTipoMineria.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())) {

                boolean encontrado = false;
                for (SelectItem selectItem : regionales) {
                    if (selectItem.getValue().equals("-1")) {
                        encontrado = true;
                    }
                }
                if (encontrado == false) {
                    regionales.add(new SelectItem("-1", "TODAS LAS REGIONALES"));
                }
            } else {
                regionales = null;
                getRegionales();
            }
        }
        //SE MUESTRA LA LISTA DE REGIONALES
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo())) {
            setMostrarFiltroRegional(true);
        } else {
            setMostrarFiltroRegional(false);
        }

        //SE MUESTRA EL FILTRO FECHA DESDE HASTA
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE.getCodigo())) {
            setMostrarFiltroFecha(true);
        } else {
            setMostrarFiltroFecha(false);
        }
    }
    
    public List<SelectItem> getTipoSolicitudes() {
        if (tipoSolicitudes == null) {
            tipoSolicitudes = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.values()) {
                if (ce.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN)
                        || ce.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE)
                        || ce.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_CONCESIONES)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_MINERIA_ARTESANAL)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_LIBRE_APROVECHAMIENTO)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_PLANTAS_BENEFICIO)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_LICENCIAS_COMERCIALIZACION)
//                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_PROVINCIA)
//                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_REGIONAL)                
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS)) {
                    tipoSolicitudes.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
            }
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo(),ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getDescripcion()));
        }
        return tipoSolicitudes;
    }

    public boolean validarFechas(Date fechaDesde_, Date fechaHasta_){
        if ((fechaDesde_ == null) && (fechaHasta_ == null)) {
            ponerMensajeInfo("", "Debe ingresar los campos Fecha desde y Fceha hasta");
            return false;
        }
        if ((fechaDesde_ != null) && (fechaHasta_ == null)) {
            ponerMensajeInfo("", "Ingrese el campo de fecha hasta.");
            return false;
        }
        if ((fechaDesde_ == null) && (fechaHasta_ != null)) {
            ponerMensajeInfo("", "Ingrese el campo de fecha desde.");
            return false;
        }
        if (fechaDesde_.after(fechaHasta_)) {
            ponerMensajeInfo("", "La fecha desde debe ser menor.");
            return false;
        }
        return true;
    }
    
    public void setTipoSolicitudes(List<SelectItem> tipoSolicitudes) {
        this.tipoSolicitudes = tipoSolicitudes;
    }

    public Date getFechaDesdeFiltro() {
        return fechaDesdeFiltro;
    }

    public void setFechaDesdeFiltro(Date fechaDesdeFiltro) {
        this.fechaDesdeFiltro = fechaDesdeFiltro;
    }

    public Date getFechaHastaFiltro() {
        return fechaHastaFiltro;
    }

    public void setFechaHastaFiltro(Date fechaHastaFiltro) {
        this.fechaHastaFiltro = fechaHastaFiltro;
    }

    public List<SelectItem> getRegionales() {
        if (regionales == null) {
            regionales = new ArrayList<>();
            List<Regional> rgnls = regionalServicio.findActivos();
            for (Regional rgnl : rgnls) {
                regionales.add(new SelectItem(rgnl.getPrefijoCodigo(), rgnl.getNombreRegional()));
            }
        }
        return regionales;
    }

    public void setRegionales(List<SelectItem> regionales) {
        this.regionales = regionales;
    }

    public String getPrefijoRegionalFiltro() {
        return prefijoRegionalFiltro;
    }

    public void setPrefijoRegionalFiltro(String prefijoRegionalFiltro) {
        this.prefijoRegionalFiltro = prefijoRegionalFiltro;
    }

    public void generarReporteAutogestion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (validarFechas(this.fechaDesdeFiltro, this.fechaHastaFiltro)){
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/Rpt-autogestion.rptdesign&fecha_desde="
                + sdf.format(fechaDesdeFiltro) + "&fecha_hasta=" + sdf.format(fechaHastaFiltro)
                + "&regional=" + prefijoRegionalFiltro + "&__format=html";
        System.out.println("URL del Comprobante: " + this.urlReporte);
        }
    }

    public String getUrlReporte() {
        return urlReporte;
    }

    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

    /**
     * @return the mostrarFiltroRegional
     */
    public boolean isMostrarFiltroRegional() {
        return mostrarFiltroRegional;
    }

    /**
     * @param mostrarFiltroRegional the mostrarFiltroRegional to set
     */
    public void setMostrarFiltroRegional(boolean mostrarFiltroRegional) {
        this.mostrarFiltroRegional = mostrarFiltroRegional;
    }

    public boolean isMostrarFiltroFecha() {
        return mostrarFiltroFecha;
    }

    public void setMostrarFiltroFecha(boolean mostrarFiltroFecha) {
        this.mostrarFiltroFecha = mostrarFiltroFecha;
    }

}
