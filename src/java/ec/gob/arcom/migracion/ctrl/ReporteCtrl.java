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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private List<SelectItem> tipoSolicitudes;
    private Date fechaDesdeFiltro;
    private Date fechaHastaFiltro;
    private List<SelectItem> regionales;
    private String prefijoRegionalFiltro;
    private String urlReporte;

    public void generarReporteConcesionMineraXls() {
        System.out.println("entra generarReporteConcesionMineraXls");
        String nombreReporte = null;
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo())) {
            nombreReporte = "Concesiones_Mineras.xlsx";
            //codigoTipoMineria = Long.valueOf("11L");
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())) {
            nombreReporte = "Libres_Aprovechamientos.xlsx";
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo())) {
            nombreReporte = "Mineria_Artesanal.xlsx";
        }
        System.out.println("codigoTipoMineria: " + codigoTipoMineria);
        try {
            String pathReporte = "/reportes/concesionMinera2.jasper";
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReporte);
            System.out.println("recursoServicio: " + recursoServicio);
            System.out.println("reportPath: " + reportPath);
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("usuario", login.getUserName());
            parameters.put("codigoTipoMineria", codigoTipoMineria);
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, recursoServicio.obtenerConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nombreReporte);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JRXlsxExporter docxExporter = new JRXlsxExporter();
            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            docxExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException | JRException | IOException sqlEx) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al generar el reporte", sqlEx.getMessage()));
        }
    }
    
    public void generarReporteConcesionMineraBirt() {
        System.out.println("entra generarReporteConcesionMineraBirt");
        urlReporte = ConstantesEnum.URL_BASE_DESARROLLO.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/concesionesMineras.rptdesign&codigoTipoMineria="
                        + codigoTipoMineria + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }

    public void generarReporteLicenciaComercializacion() {
        String nombreReporte = "Licencia_Comercializacion.xlsx";
        try {
            String pathReporte = "/reportes/licenciaComercializacion.jasper";
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReporte);
            System.out.println("recursoServicio: " + recursoServicio);
            System.out.println("reportPath: " + reportPath);
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("usuario", login.getUserName());
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, recursoServicio.obtenerConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nombreReporte);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JRXlsxExporter docxExporter = new JRXlsxExporter();
            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            docxExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException | JRException | IOException sqlEx) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al generar el reporte", sqlEx.getMessage()));
        }
    }
    
    public void generarReporteLicenciaComercializacionBirt() {
        System.out.println("entra generarReporteLicenciaComercializacionBirt");
        urlReporte = ConstantesEnum.URL_BASE_DESARROLLO.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/licenciasComercializacion.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }

    public void generarReportePlantaBeneficio() {
        String nombreReporte = "Planta_Beneficio.xlsx";
        try {
            String pathReporte = "/reportes/plantaBeneficio.jasper";
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReporte);
            System.out.println("recursoServicio: " + recursoServicio);
            System.out.println("reportPath: " + reportPath);
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("usuario", login.getUserName());
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, recursoServicio.obtenerConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nombreReporte);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JRXlsxExporter docxExporter = new JRXlsxExporter();
            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            docxExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException | JRException | IOException sqlEx) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al generar el reporte", sqlEx.getMessage()));
        }
    }
    
    public void generarReportePlantasBeneficioBirt() {
        System.out.println("entra generarReportePlantasBeneficioBirt");
        urlReporte = ConstantesEnum.URL_BASE_DESARROLLO.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/plantasBeneficio.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteDerechosMinerosConsolidadosBirt() {
        System.out.println("entra generarReporteDerechosMinerosConsolidados");
        urlReporte = ConstantesEnum.URL_BASE_DESARROLLO.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/consolidadoDerechosMinerosVig.rptdesign&__format=xlsx";
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
            //generarReporteConcesionMineraXls();
            generarReporteConcesionMineraBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getCodigo())) {
            //generarReporteLicenciaComercializacion();
            generarReporteLicenciaComercializacionBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getCodigo())) {
            //generarReportePlantaBeneficio();
            generarReportePlantasBeneficioBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS.getCodigo())) {
            generarReporteDerechosMinerosConsolidadosBirt();
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

    public List<SelectItem> getTipoSolicitudes() {
        if (tipoSolicitudes == null) {
            tipoSolicitudes = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.tipoSolicitudes()) {
                if (ce.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS)) {
                    tipoSolicitudes.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
            }
        }
        return tipoSolicitudes;
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
        fechaDesdeFiltro = new Date();
        fechaHastaFiltro = new Date();
        urlReporte = ConstantesEnum.URL_BASE.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/Rpt-autogestion.rptdesign&fecha_desde="
                + sdf.format(fechaDesdeFiltro) + "&fecha_hasta=" + sdf.format(fechaHastaFiltro)
                + "&regional=" + prefijoRegionalFiltro + "&__format=html";
        System.out.println("URL del Comprobante: " + this.urlReporte);
    }

    public String getUrlReporte() {
        return urlReporte;
    }

    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

}
