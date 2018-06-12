/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
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
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.context.RequestContext;

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
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;

    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private JasperPrint jasperPrint;
    private Long codigoTipoMineria;
    private List<SelectItem> tipoSolicitudesPrincipales;
    private List<SelectItem> tipoSolicitudesDeConcesionMinera;
    private List<ConcesionMineraDto> listaAreas;
    private Long codigoSubtipoMineria;
    private boolean concesionMinera;
    private boolean mostrarFiltroRegional;
    private boolean mostrarFiltroFecha;
    private List<SelectItem> tipoSolicitudes;
    private Date fechaDesdeFiltro;
    private Date fechaHastaFiltro;
    private List<SelectItem> regionales;
    private List<SelectItem> subTiposOperativo;
    private List<SelectItem> aniosOperativo;
    private String prefijoRegionalFiltro;
    private String urlReporte;
    private boolean mostrarSubTipoReporteOperativo;
    private boolean mostrarFiltroAnio;
    private Long codigoSubTipoOperativo;
    private String anioOperativo;

    @PostConstruct
    public void init() {
        fechaHastaFiltro = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        fechaDesdeFiltro = calendar.getTime();
        listaAreas = new ArrayList<>();
        try {            
             getRegionales();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public void generarReporteOperativoMineriaIlegalCoordenaBirt() {
        System.out.println("entra generarReporteOperativoMineriaIlegalCoordenaBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/operativomineriailegal/operativo_mineriailegal_coor.rptdesign" + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteRetencionesAbonosBirt() {
        System.out.println("entra generarReporteRetencionesAbonosBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/retenciones_abonos.rptdesign";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConcesionMineraBirt() {
        System.out.println("entra generarReporteConcesionMineraBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/concesionesMineras.rptdesign&codigoTipoMineria=" + codigoTipoMineria
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteLicenciaComercializacionBirt() {
        System.out.println("entra generarReporteLicenciaComercializacionBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/licenciasComercializacion.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReportePlantasBeneficioBirt() {
        System.out.println("entra generarReportePlantasBeneficioBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/plantasBeneficio.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteDerechosMinerosConsolidadosBirt() {
        System.out.println("entra generarReporteDerechosMinerosConsolidados");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                        + "/birt/frameset?__report=report/derechosMineros/consolidadoDerechosMinerosVig.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }

    public void generarReporteObligacionesEconomicasBirt() {
        System.out.println("entra generarReporteObligacionesEconomicas");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/Rpt-patentesutilidadesregalias.rptdesign&"
                + "regional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteObligacionesEconomicasResPatentesBirt() {
        System.out.println("entra generarReporteObligacionesEconomicasResPatentesBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/resumen_patentes.rptdesign&"
                + "codigoRegional=" + prefijoRegionalFiltro + "&__format=pdf";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteObligacionesEconomicasResUtilidadesBirt() {
        System.out.println("entra generarReporteObligacionesEconomicasResUtilidadesBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/resumen_utilidades.rptdesign&"
                + "codigoRegional=" + prefijoRegionalFiltro + "&__format=pdf";
        System.out.println("urlReporte: " + urlReporte);
    }
    public void generarReporteObligacionesEconomicasResRegaliasBirt() {
        System.out.println("entra generarReporteObligacionesEconomicasResRegaliasBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/ComprobatesPago/resumen_regalias.rptdesign&"
                + "codigoRegional=" + prefijoRegionalFiltro + "&__format=pdf";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteContratosOperacionBirt() {
        System.out.println("entra generarReporteContratosOperacionBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/contratos_operacion.rptdesign"
                + "&codigoRegional=" + prefijoRegionalFiltro + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoConcesionesBirt() {
        System.out.println("entra generarReporteConsolidadoConcesionesBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoConcesiones.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoMineriaArtesanalBirt() {
        System.out.println("entra generarReporteConsolidadoMineriaArtesanalBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoMineriaArtesanal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoLibreAprovBirt() {
        System.out.println("entra generarReporteConsolidadoLibreAprovBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoLibreAprovechamiento.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoPlantaBeneficioBirt() {
        System.out.println("entra generarReporteConsolidadoPlantaBeneficioBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoPlantasBeneficio.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteConsolidadoLicenciasComerBirt() {
        System.out.println("entra generarReporteConsolidadoLicenciasComerBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/consolidadoLicenciasComercializacion.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteMedianaGranMineriaCoordenadasBirt() {
        System.out.println("entra generarReporteMedianaGranMineriaCoordenadasBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt_v4.5/frameset?__report=report/derechosMineros/mediana_gran_mineria_con_coordenadas.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteCatastroNacionalBirt() {
        System.out.println("entra generarReporteCatastroNacionalBirt");
        listaAreas = concesionMineraServicio.obtenerAllAreas();
        RequestContext.getCurrentInstance().execute("PF('visorRptCatastroNacional').show()");
//        urlReporte = ConstantesEnum.URL_PROD_REPORTES_IPINTERNA.getDescripcion()
//                + "/birt_v4.5/frameset?__report=report/derechosMineros/catastroNacional.rptdesign&__format=xlsx";
//        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteUsuariosSGMBirt() {
        System.out.println("entra generarReporteUsuariosSGMBirt");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/derechosMineros/usuarios_sgm.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public void generarReporteOpeMinIleTotal() {
        System.out.println("entra generarReporteOpeMinIleTotal");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/operativomineriailegal/operativomineriailegal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    public void generarReporteOpeMinIleMensual() {
        System.out.println("entra generarReporteOpeMinIleMensual");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/operativomineriailegal/operativopormesanual.rptdesign"
                + "&anio_param=" + anioOperativo + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    public void generarReporteOpeMinIleMaq() {
        System.out.println("entra generarReporteOpeMinIleMaq");
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/operativomineriailegal/maquinariaoperativoporestadoanual.rptdesign"
                + "&anio_param=" + anioOperativo + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
    }
    
    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public boolean isMostrarSubTipoReporteOperativo() {
        return mostrarSubTipoReporteOperativo;
    }

    public void setMostrarSubTipoReporteOperativo(boolean mostrarSubTipoReporteOperativo) {
        this.mostrarSubTipoReporteOperativo = mostrarSubTipoReporteOperativo;
    }

    public Long getCodigoSubTipoOperativo() {
        return codigoSubTipoOperativo;
    }

    public void setCodigoSubTipoOperativo(Long codigoSubTipoOperativo) {
        this.codigoSubTipoOperativo = codigoSubTipoOperativo;
    }

    public boolean isMostrarFiltroAnio() {
        return mostrarFiltroAnio;
    }

    public void setMostrarFiltroAnio(boolean mostrarFiltroAnio) {
        this.mostrarFiltroAnio = mostrarFiltroAnio;
    }

    public String getAnioOperativo() {
        return anioOperativo;
    }

    public void setAnioOperativo(String anioOperativo) {
        this.anioOperativo = anioOperativo;
    }

    public List<SelectItem> getAniosOperativo() {
        if(aniosOperativo==null) {
            aniosOperativo= new ArrayList<>();
            int anioInicial= 2015;
            int anioFinal= Calendar.getInstance().get(Calendar.YEAR);
            for(int i=anioFinal; i>=anioInicial; i--) {
                aniosOperativo.add(new SelectItem(i, String.valueOf(i)));
            }
        }
        return aniosOperativo;
    }

    public void setAniosOperativo(List<SelectItem> aniosOperativo) {
        this.aniosOperativo = aniosOperativo;
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
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE.getCodigo())) {
            generarReporteAutogestion();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo())) {
            generarReporteObligacionesEconomicasBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES.getCodigo())) {
            generarReporteObligacionesEconomicasResPatentesBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES.getCodigo())) {
            generarReporteObligacionesEconomicasResUtilidadesBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS.getCodigo())) {
            generarReporteObligacionesEconomicasResRegaliasBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE.getCodigo())) {
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
        }  else if (codigoTipoMineria.equals(ConstantesEnum.RPT_MEDIANA_GRAN_MINERIA_COORDENADAS.getCodigo())) {
            generarReporteMedianaGranMineriaCoordenadasBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.RPT_CACASTRO_NACIONAL.getCodigo())) {
            generarReporteCatastroNacionalBirt();
        }else if (codigoTipoMineria.equals(ConstantesEnum.RPT_RETENCIONES_ABONOS.getCodigo())){
            generarReporteRetencionesAbonosBirt();
        } else if (codigoTipoMineria.equals(ConstantesEnum.RPT_USUARIOS_SGM.getCodigo())) {
            generarReporteUsuariosSGMBirt();
        } else if(codigoTipoMineria.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL.getCodigo()) && codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_TOTAL.getCodigo())) {
            generarReporteOpeMinIleTotal();
        } else if(codigoTipoMineria.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL.getCodigo()) && codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_MENSUAL.getCodigo())) {
            generarReporteOpeMinIleMensual();
        } else if(codigoTipoMineria.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL.getCodigo()) && codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_MAQ.getCodigo())) {
            generarReporteOpeMinIleMaq();
        } else if(codigoTipoMineria.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL.getCodigo()) && codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_COORDENADAS.getCodigo())) {
            generarReporteOperativoMineriaIlegalCoordenaBirt();
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
    
    public void listenerSubTipoOperativo() {
        if(codigoSubTipoOperativo!=null && !codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_TOTAL.getCodigo()) && !codigoSubTipoOperativo.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_COORDENADAS.getCodigo())) {
            mostrarFiltroAnio= true;
        } else {
            mostrarFiltroAnio= false;
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
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES.getCodigo())
                    || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS.getCodigo())
                    ) {

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
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES.getCodigo())
                || codigoTipoMineria.equals(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS.getCodigo())
                ) {
            setMostrarFiltroRegional(true);
            setMostrarSubTipoReporteOperativo(false);
            setMostrarFiltroAnio(false);
        } else {
            setMostrarFiltroRegional(false);
            setMostrarSubTipoReporteOperativo(false);
            setMostrarFiltroAnio(false);
        }

        //SE MUESTRA EL FILTRO FECHA DESDE HASTA
        if (codigoTipoMineria.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE.getCodigo())) {
            setMostrarFiltroFecha(true);
            setMostrarSubTipoReporteOperativo(false);
            setMostrarFiltroAnio(false);
        } else {
            setMostrarFiltroFecha(false);
            setMostrarSubTipoReporteOperativo(false);
            setMostrarFiltroAnio(false);
        }
        
        //MOSTRAR SUBTIPO DE OPERATIVO
        if(codigoTipoMineria.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL.getCodigo())) {
            setMostrarSubTipoReporteOperativo(true);
            setMostrarFiltroAnio(false);
        } else {
            setMostrarSubTipoReporteOperativo(false);
            setMostrarFiltroAnio(false);
        }
    }
    
    public List<SelectItem> getTipoSolicitudes() {
        if (tipoSolicitudes == null) {
            tipoSolicitudes = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.values()) {
                
                if (ce.equals(ConstantesEnum.RPT_CACASTRO_NACIONAL) && 
                        (login.isEconomicoNacional() || login.isEconomicoRegional() || login.isTecnicoCatastroNacional() || login.isCoordinadorRegional())) {
                    tipoSolicitudes.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
                if (ce.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN)
                        || ce.equals(ConstantesEnum.RPT_MEDIANA_GRAN_MINERIA_COORDENADAS)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART)
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN)
                        || ce.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE)
                        || ce.equals(ConstantesEnum.TIPO_AUTOGESTION_REPORTE)
                        || ce.equals(ConstantesEnum.RPT_USUARIOS_SGM)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_CONCESIONES)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_MINERIA_ARTESANAL)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_LIBRE_APROVECHAMIENTO)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_PLANTAS_BENEFICIO)
                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_LICENCIAS_COMERCIALIZACION)
                        || ce.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL)
//                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_PROVINCIA)
//                        || ce.equals(ConstantesEnum.RPT_CONSOLIDADO_REGIONAL)                
                        || ce.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS)) {
                    tipoSolicitudes.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
            }
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getCodigo(),ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS.getDescripcion()));
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES.getCodigo(),ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES.getDescripcion()));
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES.getCodigo(),ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES.getDescripcion()));
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS.getCodigo(),ConstantesEnum.TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS.getDescripcion()));
            tipoSolicitudes.add(new SelectItem(ConstantesEnum.RPT_RETENCIONES_ABONOS.getCodigo(), ConstantesEnum.RPT_RETENCIONES_ABONOS.getDescripcion()));
        }
        return tipoSolicitudes;
    }
    
    public List<SelectItem> getSubTiposOperativo() {
        if (subTiposOperativo == null) {
            subTiposOperativo = new ArrayList<>();
            for (ConstantesEnum ce : ConstantesEnum.values()) {
                if (ce.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_TOTAL)
                        || ce.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_MENSUAL)
                        || ce.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_MAQ)
                        || ce.equals(ConstantesEnum.RPT_OPERATIVO_MINERIA_ILEGAL_COORDENADAS)) {
                    subTiposOperativo.add(new SelectItem(ce.getCodigo(), ce.getDescripcion()));
                }
            }
        }
        return subTiposOperativo;
    }

    public void setSubTiposOperativo(List<SelectItem> subTiposOperativo) {
        this.subTiposOperativo = subTiposOperativo;
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
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
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
    
    //Reportes de fichas tecnicas de labores mineras
    public String generarReporteIlegalTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/ilegaltotal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteIlegalPorRegional(Long codigoRegional) {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/ilegalxregional.rptdesign&codigo_regional="
                    + codigoRegional + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informaltotal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalPorRegional(Long codigoRegional) {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalxregional.rptdesign&codigo_regional="
                    + codigoRegional + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgototal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoPorRegional(Long codigoRegional) {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgoxregional.rptdesign&codigo_regional="
                    + codigoRegional + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteConCodigoCensal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/laboresconcodigocensal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoInCmTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgoincmtotal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoInCmPorRegional(Long codigoRegional) {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgoincmxregional.rptdesign&codigo_regional="
                    + codigoRegional + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoOutCmTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgooutcmtotal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteInformalNoRiesgoOutCmPorRegional(Long codigoRegional) {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/informalnoriesgooutcmxregional.rptdesign&codigo_regional="
                    + codigoRegional + "&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }
    
    public String generarReporteLaboresMinerasTotal() {
        urlReporte = ConstantesEnum.URL_PROD_REPORTES.getDescripcion()
                + "/birt/frameset?__report=report/fichatecnica/laboresminerastotal.rptdesign&__format=xlsx";
        System.out.println("urlReporte: " + urlReporte);
        return urlReporte;
    }

    /**
     * @return the listaAreas
     */
    public List<ConcesionMineraDto> getListaAreas() {
        return listaAreas;
    }

    /**
     * @param listaAreas the listaAreas to set
     */
    public void setListaAreas(List<ConcesionMineraDto> listaAreas) {
        this.listaAreas = listaAreas;
    }
}
