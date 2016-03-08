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
    
    GRAN_MINERIA("GRAMIN", "G", null), MED_MINERIA("MEDMIN", "M", null), PEQ_MINERIA("PEQMIN", "P", null),
    EST_TRAMITE("ESTENTRA", "En trámite", 241L), EST_OTORGADO("ESTOTOR", "OTORGADA", 242L), EST_INSCRITO("ESTINSC", "INSCRITA", 243L),
    EST_SUSPENDIDO("ESTSUSP", "SUSPENDIDO", 244L), EST_ARCHIVADA("ESTARCHIV", "ARCHIVADA", 246L), EST_ACUMULADA("ACMLDA19", "ACUMULADA", 1459L),
    
    ESTCONC_TRAMITE("TRAM", "En tramite", 35L), ESTCONC_INSCRITO("CONCINS", "INSCRITA", 46L),
    ESTCONC_SUSPENDIDO("ESTSUSPAREA", "SUSPENDIDO", 7L), ESTCONC_ARCHIVADA("NOOTOR", "ARCHIVADA", 47L), ESTCONC_ACUMULADA("ACMLD4", "ARCHIVADA", 1459L),
    ESTCONC_OTORGADO("OTOR", "OTORGADA", 42L),
    
    TIPO_SOLICITUD_CONS_MIN("CONCMIN", "CONCESION MINERA", 3L), TIPO_SOLICITUD_MIN_ART("MINART", "MINERIA ARTESANAL", 1L),
    TIPO_SOLICITUD_LIB_APR("LIBAPR", "LIBRE APROVECHAMIENTO", 4L), TIPO_SOLICITUD_LIC_COM("LICCOM", "LICENCIAS DE COMERCIALIZACION", 5L),
    TIPO_SOLICITUD_PLAN_BEN("PLANBEN", "PLANTA DE BENEFICIO", 6L), TIPO_SOLICITUD_PEQ_MIN("PEQMIN", "PEQUEÑA MINERIA", 8L), 
    TIPO_SOLICITUD_MA_PEQ_MIN("MAPEQMIN", "ARTESANAL A PEQUEÑA MINERIA", 2L), SUJETO_MINERO("SUJMIN", "SUJETO MINERO", 100L),
    TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO("NOAPLICA", "NO APLICA", 1000L), 
    TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS("DERMIN", "DERECHOS MINEROS VIG. SIN MAT. CONST.", 10000L),
    
    TIPO_CONTRATO_CESION_DERECHOS("CESDER", "CESIÓN DE DERECHOS", 1612L),
    TIPO_CONTRATO_CESION_ACCIONES("CESACC", "CESIÓN DE ACCIONES", 1613L),
    TIPO_CONTRATO_OPERACION("CONTOPER", "CONTRATO DE OPERACIÓN", 1611L),
    TIPO_CONTRATO_EXPLOTACION("EXPLTCN", "CONTRATO DE EXPLOTACIÓN", 1614L),
    
    //URL_BASE("", "http://181.211.37.237:8380", null);
    URL_BASE("", "http://10.10.6.131:8384", null), URL_BASE_DESARROLLO("", "http://181.211.37.237:8380", null);
    
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
                    || ce.equals(TIPO_SOLICITUD_NO_APLICA_DERECHO_MINERO)
                    || ce.equals(ConstantesEnum.TIPO_SOLICITUD_DERECHOS_MINEROS_CONSOLIDADOS)) {
                tipoSolicitudes.add(ce);
            }
        }
        return tipoSolicitudes;
    }
}
