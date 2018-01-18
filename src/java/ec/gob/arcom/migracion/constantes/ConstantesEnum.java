/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.constantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier Coronel
 */
public enum ConstantesEnum {
    
    GRAN_MINERIA("GRAMIN", "G", null), MED_MINERIA("MEDMIN", "M", null), PEQ_MINERIA("PEQMIN", "P", null), REG_GENERAL("REGGENL", "T", null),
    EST_TRAMITE("ESTENTRA", "En trámite", 241L), EST_OTORGADO("ESTOTOR", "OTORGADA", 242L), EST_INSCRITO("ESTINSC", "INSCRITA", 243L),
    EST_SUSPENDIDO("ESTSUSP", "SUSPENDIDO", 244L), EST_ARCHIVADA("ESTARCHIV", "ARCHIVADA", 246L), EST_ACUMULADA("ACMLDA19", "ACUMULADA", 1459L),
    
    ESTCONC_TRAMITE("TRAM", "En tramite", 35L), ESTCONC_INSCRITO("CONCINS", "INSCRITA", 46L),
    ESTCONC_SUSPENDIDO("ESTSUSPAREA", "SUSPENDIDO", 7L), ESTCONC_ARCHIVADA("NOOTOR", "ARCHIVADA", 47L), ESTCONC_ACUMULADA("ACMLD4", "ARCHIVADA", 1459L),
    ESTCONC_OTORGADO("OTOR", "OTORGADA", 42L),
    
    //ESTADOS COMPROBANTES DE PAGO
    ESTCOMP_APROBADO("PAPROBA", "APROBADO", 574L), ESTCOMP_NOAPROBADO("PNOAPROBA", "NO APROBADO", 575L), ESTCOMP_ACEPTADO("PACET", "ACEPTADO", 571L),
    ESTCOMP_REGISTRADO("PAREG", "REGISTRADO", 573L),
    
    TIPO_SOLICITUD_CONS_MIN("CONCMIN", "CONCESION MINERA", 3L), TIPO_SOLICITUD_MIN_ART("MINART", "MINERIA ARTESANAL", 1L),
    TIPO_SOLICITUD_LIB_APR("LIBAPR", "LIBRE APROVECHAMIENTO", 4L), TIPO_SOLICITUD_LIC_COM("LICCOM", "LICENCIAS DE COMERCIALIZACION", 5L),
    TIPO_SOLICITUD_PLAN_BEN("PLANBEN", "PLANTA DE BENEFICIO", 6L), TIPO_SOLICITUD_PEQ_MIN("PEQMIN", "PEQUEÑA MINERIA", 8L), 
    TIPO_SOLICITUD_MA_PEQ_MIN("MAPEQMIN", "ARTESANAL A PEQUEÑA MINERIA", 2L), SUJETO_MINERO("SUJMIN", "SUJETO MINERO", 100L),
    TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO("NOAPLICA", "NO APLICA", 1000L), 
    TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS("DERMIN", "DERECHOS MINEROS VIG. SIN MAT. CONST.", 10000L),
    TIPO_OBLIGACIONES_ECONOMICAS("OBLIGECO", "OBLIGACIONES ECONOMICAS", 10001L),
    TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_PATENTES("OBLIGECOPAT", "OBLIG. ECON. RESUMEN PATENTES", 100011L),
    TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_UTILIDADES("OBLIGECOUTI", "OBLIG. ECON. RESUMEN UTILIDADES", 100012L),
    TIPO_OBLIGACIONES_ECONOMICAS_RESUMEN_REGALIAS("OBLIGECOREG", "OBLIG. ECON. RESUMEN REGALIAS", 100013L),
    TIPO_CONTRATOS_OPERACION_REPORTE("CONTRAOPERARPT", "CONTRATOS DE OPERACION", 10002L),
    TIPO_AUTOGESTION_REPORTE("AUTOGREP", "AUTOGESTION", 10003L),
    
    RPT_USUARIOS_SGM("RPTUSUARIOSSGM", "USUARIOS SGM", 10012L),
    RPT_CONSOLIDADO_CONCESIONES("RPTCONSCONCES", "RPT. CONSOLIDADO CONCESIONES", 10004L),
    RPT_CONSOLIDADO_MINERIA_ARTESANAL("RPTCONSMINART", "RPT. CONSOLIDADO MINERIA ARTESANAL", 10005L),
    RPT_CONSOLIDADO_LIBRE_APROVECHAMIENTO("RPTCONSLIBAPR", "RPT. CONSOLIDADO LIBRE APROVECHAMIENTO", 10006L),
    RPT_CONSOLIDADO_PLANTAS_BENEFICIO("RPTCONSPLANTBENF", "RPT. CONSOLIDADO PLANTAS DE BENEFICIO", 10007L),
    RPT_CONSOLIDADO_LICENCIAS_COMERCIALIZACION("RPTCONSLICENCOMER", "RPT. CONSOLIDADO LICENCIAS DE COMERCIALIZACION", 10008L),
//    RPT_CONSOLIDADO_PROVINCIA("RPTCONSPROVIC", "RPT. CONSOLIDADO POR PROVINCIA", 10009L),
//    RPT_CONSOLIDADO_REGIONAL("RPTCONSREGIONAL", "RPT. CONSOLIDADO REGIONAL", 10010L),
    RPT_MEDIANA_GRAN_MINERIA_COORDENADAS("RPTMEDIANAGRANMINCOOR", "MEDIANA GRAN MINERIA CON COORDENADAS", 10011L),
    RPT_CACASTRO_NACIONAL("RPTCATASTRONACIONAL", "CATASTRO NACIONAL", 10012L),
    
    RPT_OPERATIVO_MINERIA_ILEGAL("RPTOPEMINILE", "OPERATIVOS DE MINERIA ILEGAL", -15139L),
    RPT_OPERATIVO_MINERIA_ILEGAL_TOTAL("RPTOPEMINILETOTAL", "OPERATIVOS DE MINERIA ILEGAL TOTAL", -151391L),
    RPT_OPERATIVO_MINERIA_ILEGAL_MENSUAL("RPTOPEMINILEMENSUAL", "OPERATIVOS DE MINERIA ILEGAL POR MES", -151392L),
    RPT_OPERATIVO_MINERIA_ILEGAL_MAQ("RPTOPEMINILEMAQ", "MAQUINARIA DE OPERATIVOS DE MINERIA ILEGAL", -151393L),
    
    TIPO_CONTRATO_CESION_DERECHOS("CESDER", "CESIÓN DE DERECHOS", 1612L),
    TIPO_CONTRATO_CESION_ACCIONES("CESACC", "CESIÓN DE ACCIONES", 1613L),
    TIPO_CONTRATO_OPERACION("CONTOPER", "CONTRATO DE OPERACIÓN", 1611L),
    TIPO_CONTRATO_EXPLOTACION("EXPLTCN", "CONTRATO DE EXPLOTACIÓN", 1614L),
    
    PATUTIREG_PATENTE("TICALPATENTE", "PATENTE", 361L),
    PATUTIREG_REGALIA("TICALREGALIA", "REGALIA", 362L),
    PATUTIREG_UTILIDAD("TICALUTILIDAD", "UTILIDAD", 363L),
    
    TABLA_CONCESION_MINERA("TBCONCESIONMIN", "concesion_minera",-1L),
    TABLA_AREA_MINERA("TBAREAMIN", "area_minera",-1L),
    TABLA_COORDENADA_AREA("TBCOORDAREA", "coordenada_area",-1L),
    
    //URL_BASE("", "http://181.211.37.237:8380", null);
    URL_APP_LOCAL("", "http://localhost:8080", null),
    URL_APP_PROD("", "http://172.16.30.246:8381", null), 
    URL_BASE("", "http://181.211.37.233:8384", null), 
    URL_PROD_REPORTES("", "http://www.controlminero.gob.ec:8080", null),
    URL_BASE_DESARROLLO("", "http://181.211.37.237:8380", null),
    
    //PARA ENVIO DE CORREO ELECTRONICO
    IP_SERVIDOR_CORREO("", "10.10.6.6", null),
    PORT_SERVIDOR_CORREO("", "25", null),
    REMITENTE_CORREO_VACACIONES("", "talento.humano@controlminero.gob.ec", null);
    
    private String nemonico;
    private String descripcion;
    private Long codigo;
    
    private ConstantesEnum(String nemonico, String descripcion, Long codigo) {
        this.nemonico = nemonico;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }
    
    public String getNemonico() {
        return nemonico;
    }
    
    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public static List<ConstantesEnum> tipoSolicitudesPrincipales() {
        List<ConstantesEnum> tipoSolicitudesPrincipales = new ArrayList<>();
        for (ConstantesEnum ce : ConstantesEnum.values()) {
            if (ce.equals(TIPO_SOLICITUD_CONS_MIN) || ce.equals(TIPO_SOLICITUD_LIC_COM)
                    || ce.equals(TIPO_SOLICITUD_PLAN_BEN)) {
                tipoSolicitudesPrincipales.add(ce);
            }
        }
        return tipoSolicitudesPrincipales;
    }
    
    public static List<ConstantesEnum> tipoSolicitudesDeConcesionMinera() {
        List<ConstantesEnum> tipoSolicitudesDeConcesion = new ArrayList<>();
        for (ConstantesEnum ce : ConstantesEnum.values()) {
            if (ce.equals(TIPO_SOLICITUD_CONS_MIN) || ce.equals(TIPO_SOLICITUD_LIB_APR)
                    || ce.equals(TIPO_SOLICITUD_MIN_ART)) {
                tipoSolicitudesDeConcesion.add(ce);
            }
        }
        return tipoSolicitudesDeConcesion;
    }
    
    public static List<ConstantesEnum> tipoSolicitudes() {
        List<ConstantesEnum> tipoSolicitudes = new ArrayList<>();
        for (ConstantesEnum ce : ConstantesEnum.values()) {
            if (ce.equals(TIPO_SOLICITUD_CONS_MIN) || ce.equals(TIPO_SOLICITUD_LIC_COM)
                    || ce.equals(TIPO_SOLICITUD_PLAN_BEN) || ce.equals(TIPO_SOLICITUD_LIB_APR)
                    || ce.equals(TIPO_SOLICITUD_MIN_ART) || ce.equals(SUJETO_MINERO)
                    || ce.equals(TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO)) {
                tipoSolicitudes.add(ce);
            }
        }
        return tipoSolicitudes;
    }
}
