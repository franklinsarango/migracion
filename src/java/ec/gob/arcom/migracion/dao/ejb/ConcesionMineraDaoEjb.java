/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.FaseServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConcesionMineraDao")
public class ConcesionMineraDaoEjb extends GenericDaoEjbEl<ConcesionMinera, Long> implements
        ConcesionMineraDao {

    @EJB
    private RegionalServicio regionalServicio;
    @EJB
    private FaseServicio faseServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    public ConcesionMineraDaoEjb() {
        super(ConcesionMinera.class);
    }
    
    @Override
    public List<ConcesionMineraDto> obtenerRegistrosPorUsuario(String cedulaRuc, String codigoFiltro, String cedulaTitularFiltro, String nombreAreaFiltro) {
        if (codigoFiltro == null || codigoFiltro.trim().isEmpty()) {
            codigoFiltro = "-1";
        }
        if (cedulaTitularFiltro == null || cedulaTitularFiltro.trim().isEmpty()) {
            cedulaTitularFiltro = "-1";
        }
        if (nombreAreaFiltro == null || nombreAreaFiltro.trim().isEmpty()) {
            nombreAreaFiltro = "-1";
        }
        
        String sql = "SELECT\n"
                + "cm.codigo_concesion as id,                       cm.codigo_arcom as codigo_arcom,                cm.nombre_concesion as nombre_concesion,     cm.casillero_judicial,\n"
                + "\n"
                + "    \n"
                + "               cm.documento_concesionario_principal AS titular_documento,\n"
                + "               \n"
                + "            \n"
                + "               \n"
                + "    cm.plazo_concesion,                    cast(null as date) as fecha_informe,                                       \n"
                + "    \n"
                + "               cm.numero_hectareas_concesion as superficie,\n"
                + "               \n"
                + "                   (select nombre from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.estado_concesion) as estado,\n"
                + "        \n"
                + "                (select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase) as fase,\n"
                + "               \n"
                + "    (select nombre_tipo_mineria from catmin.tipo_mineria where cm.codigo_tipo_mineria = codigo_tipo_mineria) as tipo_solicitud,\n"
                + "    cm.fecha_otorga,                                     fecha_inscribe, \n"
                + "    \n"
                + "    \n"
                + "                 (select nombre_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as regional,\n"
                + "\n"
                + "    (select nombre from catmin.localidad where cm.codigo_provincia = codigo_localidad) as provincia,\n"
                + "    \n"
                + "    (select nombre from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                + "    \n"
                + "    (select nombre from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                + "	\n"
                + "               cast(null as date) as fecha_sustitucion,\n"
                + "\n"
                + "    'C' tipo_tabla \n"
                + "FROM\n"                
                + "    catmin.concesion_minera cm\n"
                + "    \n"
                + "where cm.estado_registro = true\n"
                + " and ('-1' = '" + cedulaRuc + "' or cm.codigo_provincia in (select lcr.codigo_localidad from catmin.localidad_regional lcr where lcr.codigo_regional = "
                + "(select r.codigo_regional from catmin.regional r, catmin.localidad_regional lr, catmin.usuario where numero_documento = '" + cedulaRuc + "'\n"
                + "                                 and r.codigo_regional = lr.codigo_regional and lr.codigo_localidad = codigo_provincia))) "                
                + "and ('-1' = '" + codigoFiltro + "' or cm.codigo_arcom like '%" + codigoFiltro + "%')\n"
                + "and ('-1' = '" + cedulaTitularFiltro + "' or cm.documento_concesionario_principal like '%" + cedulaTitularFiltro + "%')\n"
                + "and ('-1' = '" + nombreAreaFiltro + "' or lower(cm.nombre_concesion) like lower('%" + nombreAreaFiltro + "%'))\n"
                + "order by 2";
        System.out.println("sql concesion: " + sql);

        Query query = em.createNativeQuery(sql);

        List<Object[]> listaTmp = query.getResultList();
        List<ConcesionMineraDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            ConcesionMineraDto cmd = new ConcesionMineraDto();
            cmd.setCodigoConcesion(fila[0] != null ? Long.valueOf(fila[0].toString()) : null);
            cmd.setCodigoArcom(fila[1] != null ? fila[1].toString() : null);
            cmd.setNombreConcesion(fila[2] != null ? fila[2].toString() : null);
            cmd.setCasilleroJudicial(fila[3] != null ? fila[3].toString() : null);
            cmd.setTitularDocumento(fila[4] != null ? fila[4].toString() : null);
            cmd.setPlazoConcesion(fila[5] != null ? fila[5].toString() : null);
            cmd.setFechaInforme(fila[6] != null ? (Date) fila[6] : null);
            cmd.setSuperficie(fila[7] != null ? Double.valueOf(fila[7].toString()) : null);
            cmd.setEstadoConcesion(fila[8] != null ? fila[8].toString() : null);
            cmd.setFase(fila[9] != null ? fila[9].toString() : null);
            cmd.setTipoSolicitud(fila[10] != null ? fila[10].toString() : null);
            cmd.setFechaOtorgamiento(fila[11] != null ? (Date) fila[11] : null);
            cmd.setFechaInscripcion(fila[12] != null ? (Date) fila[12] : null);
            cmd.setNombreRegional(fila[13] != null ? fila[13].toString() : null);
            cmd.setProvincia(fila[14] != null ? fila[14].toString() : null);
            cmd.setCanton(fila[15] != null ? fila[15].toString() : null);
            cmd.setParroquia(fila[16] != null ? fila[16].toString() : null);
            cmd.setTipoTabla(fila[18] != null ? fila[18].toString() : null);
            listaFinal.add(cmd);
        }

        return listaFinal;
    }

    @Override
    public List<ConcesionMineraDto> obtenerAllAreas() {        
        String sql = "SELECT \n"
                + "cm.codigo_concesion,\n"
                + "cm.codigo_arcom,\n"
                + "catmin.format_gmadigital(cm.nombre_concesion) as nombre_concesion,\n"
                + "cm.documento_concesionario_principal AS titular_documento,\n"
                + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                + "p.telefono,\n"
                + "catmin.format_gmadigital(p.direccion) as direccion,\n"
                + "p.documento_representante_legal as rep_legal_documento,\n"
                + "case when p.apellido_representante_legal is null then catmin.format_gmadigital(p.nombre_representante_legal) else catmin.format_gmadigital(p.apellido_representante_legal || ' ' || p.nombre_representante_legal) end as rep_legal_nombre,\n"
                + "p.casillero_judicial,\n"
                + "cm.plazo_concesion,\n"
                + "cm.plazo_dias,\n"
                + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                + "tm.nombre_tipo_mineria as tipo_solicitud,\n"
                + "cm.fecha_otorga as fecha_otorgamiento,                                     \n"
                + "cm.fecha_inscribe as fecha_inscripcion,\n"
                + "cm.fecha_sustitucion,\n"
                + "cm.fecha_inscripcion_sustitucion,\n"
                + "est_.nombre as estado_concesion,\n"
                + "(select nombre_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                + "(select codigo_internacional from catmin.localidad where cm.codigo_provincia = codigo_localidad) as codigo_provincia,\n"
                + "catmin.format_gmadigital(prov.nombre) as provincia,\n"
                + "(select codigo_internacional from catmin.localidad where cm.codigo_canton = codigo_localidad) as codigo_canton,\n"
                + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                + "(select codigo_internacional from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as codigo_parroquia,\n"
                + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                + "\n"
                + "case when cm.codigo_zona = 11 then 15 \n"
                + "        when cm.codigo_zona = 12 then 16\n"
                + "        when cm.codigo_zona = 13 then 17\n"
                + "        when cm.codigo_zona = 14 then 18 else null end as zona,\n"
                + "(select upper(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_material_interes) as material_interes,        \n"
                + "(select upper(nombre) from catmin.catalogo where codigo_catalogo = cm.codigo_tipo_material) as mineral,\n"
                + "cm.numero_hectareas_concesion as superficie,\n"
                + "cm.fecha_creacion,\n"
                + "(select upper(nombre) from catmin.regimen where codigo_regimen = cm.codigo_regimen) as regimen,\n"
                + "(select upper(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_modalidad_trabajo) as modalidad_trabajo,\n"
                + "(select upper(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_forma_explotacion) as forma_explotacion,\n"
                + "cm.volumen_diario_explotacion,\n"
                + "cm.volumen_total_explotacion,\n"
                + "case when cm.litispendencia = true then 'S' else 'N' end as litispendencia\n"
                + "FROM catmin.concesion_minera cm, catmin.personas p, catmin.tipo_mineria tm,catmin.localidad prov, catmin.catalogo_detalle est_    \n"
                + "where prov.codigo_localidad = cm.codigo_provincia\n"
                + "and est_.codigo_catalogo_detalle = cm.estado_concesion\n"
                + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                + "and cm.documento_concesionario_principal = p.numero_documento\n"
                + "and cm.estado_registro = true";
        System.out.println("sql concesion: " + sql);

        Query query = em.createNativeQuery(sql);

        List<Object[]> listaTmp = query.getResultList();
        List<ConcesionMineraDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            ConcesionMineraDto cmd = new ConcesionMineraDto();
            cmd.setCodigoConcesion(fila[0] != null ? Long.valueOf(fila[0].toString()) : null);
            cmd.setCodigoArcom(fila[1] != null ? fila[1].toString() : null);
            cmd.setNombreConcesion(fila[2] != null ? fila[2].toString() : null);
            cmd.setTitularDocumento(fila[3] != null ? fila[3].toString() : null);            
            cmd.setTitularNombre(fila[4] != null ? fila[4].toString() : null);            
            cmd.setTelefono(fila[5] != null ? fila[5].toString() : null);
            cmd.setDireccion(fila[6] != null ? fila[6].toString() : null);
            cmd.setRepLegalDocumento(fila[7] != null ? fila[7].toString() : null);
            cmd.setRepLegalNombre(fila[8] != null ? fila[8].toString() : null);            
            cmd.setCasilleroJudicial(fila[9] != null ? fila[9].toString() : null);
            cmd.setPlazoConcesion(fila[10] != null ? fila[10].toString() : null);
            cmd.setPlazoDias(fila[11] != null ? fila[11].toString() : null);
            cmd.setFase(fila[12] != null ? fila[12].toString() : null);
            cmd.setTipoSolicitud(fila[13] != null ? fila[13].toString() : null);
            cmd.setFechaOtorgamiento(fila[14] != null ? (Date) fila[14] : null);
            cmd.setFechaInscripcion(fila[15] != null ? (Date) fila[15] : null);
            cmd.setFechaSustitucion(fila[16] != null ? (Date) fila[16] : null);
            cmd.setFechaInscripcionSustitucion(fila[17] != null ? (Date) fila[17] : null);
            cmd.setEstadoConcesion(fila[18] != null ? fila[18].toString() : null);
            cmd.setNombreRegional(fila[19] != null ? fila[19].toString() : null);
            cmd.setCodigoProvincia(fila[20] != null ? fila[20].toString() : null);
            cmd.setProvincia(fila[21] != null ? fila[21].toString() : null);
            cmd.setCodigoCanton(fila[22] != null ? fila[22].toString() : null);
            cmd.setCanton(fila[23] != null ? fila[23].toString() : null);
            cmd.setCodigoParroquia(fila[24] != null ? fila[24].toString() : null);
            cmd.setParroquia(fila[25] != null ? fila[25].toString() : null);
            cmd.setZona(fila[26] != null ? fila[26].toString() : null);
            cmd.setMaterialInteres(fila[27] != null ? fila[27].toString() : null);
            cmd.setMineral(fila[28] != null ? fila[28].toString() : null);
            cmd.setSuperficie(fila[29] != null ? Double.valueOf(fila[29].toString()) : null);            
            cmd.setFechaCreacion(fila[30] != null ? (Date) fila[30] : null);
            cmd.setRegimen(fila[31] != null ? fila[31].toString() : null);
            cmd.setModalidadTrabajo(fila[32] != null ? fila[32].toString() : null);
            cmd.setFormaExplotacion(fila[33] != null ? fila[33].toString() : null);
            cmd.setVolumenDiarioExplotacion(fila[34] != null ? fila[34].toString() : null);
            cmd.setVolumenTotalExplotacion(fila[35] != null ? fila[35].toString() : null);
            cmd.setLitispendencia(fila[36] != null ? fila[36].toString() : null);
            listaFinal.add(cmd);
        }

        return listaFinal;
    }
    
    
    @Override
    public List<ConcesionMineraDto> obtenerRegistrosPorFiltros(String codigoFiltro, String cedulaTitularFiltro, String nombreAreaFiltro) {
        if (codigoFiltro == null || codigoFiltro.trim().isEmpty()) {
            codigoFiltro = "-1";
        }
        if (cedulaTitularFiltro == null || cedulaTitularFiltro.trim().isEmpty()) {
            cedulaTitularFiltro = "-1";
        }
        if (nombreAreaFiltro == null || nombreAreaFiltro.trim().isEmpty()) {
            nombreAreaFiltro = "-1";
        }
        
        String sql = "SELECT\n"
                + "cm.codigo_concesion as id,                       cm.codigo_arcom as codigo_arcom,                cm.nombre_concesion as nombre_concesion,     cm.casillero_judicial,\n"
                + "\n"
                + "    \n"
                + "               cm.documento_concesionario_principal AS titular_documento,\n"
                + "               \n"
                + "            \n"
                + "               \n"
                + "    cm.plazo_concesion,                    cast(null as date) as fecha_informe,                                       \n"
                + "    \n"
                + "               (select superficie_area_minera from catmin.catalogo_detalle, catmin.area_minera where codigo_catalogo_detalle = estado_area \n"
                + "        and codigo_area_minera = (select max(codigo_area_minera) from catmin.area_minera where codigo_concesion = cm.codigo_concesion)) as superficie,\n"
                + "               \n"
                + "                   (select nombre from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.estado_concesion) as estado,\n"
                + "        \n"
                + "                (select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase) as fase,\n"
                + "               \n"
                + "    (select nombre_tipo_mineria from catmin.tipo_mineria where cm.codigo_tipo_mineria = codigo_tipo_mineria) as tipo_solicitud,\n"
                + "    cm.fecha_otorga,                                     fecha_inscribe, \n"
                + "    \n"
                + "    \n"
                + "                 (select nombre_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as regional,\n"
                + "\n"
                + "    (select nombre from catmin.localidad where cm.codigo_provincia = codigo_localidad) as provincia,\n"
                + "    \n"
                + "    (select nombre from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                + "    \n"
                + "    (select nombre from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                + "	\n"
                + "               cast(null as date) as fecha_sustitucion,\n"
                + "\n"
                + "    'C' tipo_tabla \n"
                + "FROM\n"
                + "    catmin.concesion_minera cm\n"
                + "    \n"
                + "where cm.estado_registro = true\n"
                + "and ('-1' = '" + codigoFiltro + "' or cm.codigo_arcom = '" + codigoFiltro + "')\n"
                + "and ('-1' = '" + cedulaTitularFiltro + "' or cm.documento_concesionario_principal like '%" + cedulaTitularFiltro + "%')\n"
                + "and ('-1' = '" + nombreAreaFiltro + "' or lower(cm.nombre_concesion) like lower('%" + nombreAreaFiltro + "%'))\n"
                + "order by 2";
        System.out.println("sql concesion: " + sql);

        Query query = em.createNativeQuery(sql);

        List<Object[]> listaTmp = query.getResultList();
        List<ConcesionMineraDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            ConcesionMineraDto cmd = new ConcesionMineraDto();
            cmd.setCodigoConcesion(fila[0] != null ? Long.valueOf(fila[0].toString()) : null);
            cmd.setCodigoArcom(fila[1] != null ? fila[1].toString() : null);
            cmd.setNombreConcesion(fila[2] != null ? fila[2].toString() : null);
            cmd.setCasilleroJudicial(fila[3] != null ? fila[3].toString() : null);
            //cmd.setDireccion(fila[4] != null ? fila[4].toString() : null);
            //cmd.setTelefono(fila[5] != null ? fila[5].toString() : null);
            //cmd.setTitularNombre(fila[6] != null ? fila[6].toString() : null);
            cmd.setTitularDocumento(fila[4] != null ? fila[4].toString() : null);
            //cmd.setRepLegalNombre(fila[8] != null ? fila[8].toString() : null);
            //cmd.setRepLegalDocumento(fila[9] != null ? fila[9].toString() : null);
            cmd.setPlazoConcesion(fila[5] != null ? fila[5].toString() : null);
            cmd.setFechaInforme(fila[6] != null ? (Date) fila[6] : null);
            //cmd.setZona(fila[12] != null ? fila[12].toString() : null);
            cmd.setSuperficie(fila[7] != null ? Double.valueOf(fila[7].toString()) : null);
            cmd.setEstadoConcesion(fila[8] != null ? fila[8].toString() : null);
            cmd.setFase(fila[9] != null ? fila[9].toString() : null);
            cmd.setTipoSolicitud(fila[10] != null ? fila[10].toString() : null);
            cmd.setFechaOtorgamiento(fila[11] != null ? (Date) fila[11] : null);
            cmd.setFechaInscripcion(fila[12] != null ? (Date) fila[12] : null);
            cmd.setNombreRegional(fila[13] != null ? fila[13].toString() : null);
            cmd.setProvincia(fila[14] != null ? fila[14].toString() : null);
            cmd.setCanton(fila[15] != null ? fila[15].toString() : null);
            cmd.setParroquia(fila[16] != null ? fila[16].toString() : null);
            //cmd.setMineral(fila[22] != null ? fila[22].toString() : null);
            //cmd.setNumeroCoordenada(fila[24] != null ? Integer.valueOf(fila[24].toString()) : null);
            //cmd.setCoordenadaUtmEste(fila[24] != null ? fila[24].toString() : null);
            //cmd.setCoordenadaUtmNorte(fila[25] != null ? fila[25].toString() : null);
            //cmd.setManifiesto(fila[27] != null ? Integer.valueOf(fila[27].toString()) : null);
            cmd.setTipoTabla(fila[18] != null ? fila[18].toString() : null);
            listaFinal.add(cmd);
        }

        return listaFinal;
    }
    
    @Override
    public Long obtenerSiguienteCodigoConcesion() {
        String sql = "select max(codigo_concesion + 1) from catmin.concesion_minera";

        Query query = em.createNativeQuery(sql);
        Long codigoConcesion = (Long) query.getSingleResult();
        return codigoConcesion;
    }

    @Override
    public ConcesionMinera findByPk(Long codigoConcesion) {
        try {
            System.out.println("entra findByPk ConcesionMinera");
            String jpql = "select cm from ConcesionMinera cm where cm.codigoConcesion = :codigoConcesion";
            Query query = em.createQuery(jpql);
            query.setParameter("codigoConcesion", codigoConcesion);
            ConcesionMinera concesionMinera = (ConcesionMinera) query.getSingleResult();
            this.refresh(concesionMinera);
            return concesionMinera;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public ConcesionMinera findByCodigoArcom(String codigoArcom) {
        try {
            String hql = "select cm from ConcesionMinera cm where cm.codigoArcom = :codigoArcom";
            Query query = em.createQuery(hql);
            query.setParameter("codigoArcom", codigoArcom);
            ConcesionMinera concesionMinera = (ConcesionMinera) query.getSingleResult();
            this.refresh(concesionMinera);
            return concesionMinera;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarConcesionMinera(ConcesionMinera concesionMinera) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.concesion_minera\n"
                + "SET\n";
        if (concesionMinera.getCodigoConcesion() != null) {
            sql = sql + "    codigo_concesion = " + concesionMinera.getCodigoConcesion() + ",\n";
        }
        if (concesionMinera.getCodigoArcom() != null) {
            sql = sql + "    codigo_arcom = '" + concesionMinera.getCodigoArcom() + "',\n";
        }
        if (concesionMinera.getCodigoCensal() != null) {
            sql = sql + "    codigo_censal = '" + concesionMinera.getCodigoCensal() + "',\n";
        }
        if (concesionMinera.getEstadoConcesion() != null && concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    estado_concesion = " + concesionMinera.getEstadoConcesion().getCodigoCatalogoDetalle() + ",\n";
        }
        if (concesionMinera.getCodigoFase() != null) {
            sql = sql + "    codigo_fase = " + concesionMinera.getCodigoFase().getCodigoFase() + ",\n";
        }
        if (concesionMinera.getNombreConcesion() != null) {
            sql = sql + "    nombre_concesion = '" + concesionMinera.getNombreConcesion() + "',\n";
        }
        if (concesionMinera.getCodigoTipoMineria() != null && concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria() != null) {
            sql = sql + "    codigo_tipo_mineria = " + concesionMinera.getCodigoTipoMineria().getCodigoTipoMineria() + ",\n";
        }
        if (concesionMinera.getNumeroHectareasConcesion() != null) {
            sql = sql + "    numero_hectareas_concesion = " + concesionMinera.getNumeroHectareasConcesion() + ",\n";
        }
        if (concesionMinera.getCodigoZona() != null && concesionMinera.getCodigoZona().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_zona = " + concesionMinera.getCodigoZona().getCodigoCatalogoDetalle() + ",\n";
        }
        if (concesionMinera.getCodigoRegional() != null && concesionMinera.getCodigoRegional().getCodigoRegional() != null) {
            sql = sql + "    codigo_regional = " + concesionMinera.getCodigoRegional().getCodigoRegional() + ",\n";
        }
        if (concesionMinera.getCodigoRegimen() != null) {
            sql = sql + "    codigo_regimen = " + concesionMinera.getCodigoRegimen().getCodigoRegimen() + ",\n";
        }
        if (concesionMinera.getPlazoConcesion() != null) {
            sql = sql + "    plazo_concesion = " + concesionMinera.getPlazoConcesion() + ",\n";
        }
        if (concesionMinera.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + concesionMinera.getCodigoProvincia() + ",\n";
        }
        if (concesionMinera.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + concesionMinera.getCodigoCanton() + ",\n";
        }
        if (concesionMinera.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + concesionMinera.getCodigoParroquia() + ",\n";
        }
        if (concesionMinera.getFechaInicioConcesion() != null) {
            sql = sql + "    fecha_inicio_concesion = '" + concesionMinera.getFechaInicioConcesion() + "',\n";
        }
        if (concesionMinera.getFechaFinConcesion() != null) {
            sql = sql + "    fecha_fin_concesion = '" + concesionMinera.getFechaFinConcesion() + "',\n";
        }
        if (concesionMinera.getDocumentoConcesionarioPrincipal() != null) {
            sql = sql + "    documento_concesionario_principal = '" + concesionMinera.getDocumentoConcesionarioPrincipal() + "',\n";
        }
        if (concesionMinera.getNombreConcesionarioPrincipal() != null) {
            sql = sql + "    nombre_concesionario_principal = '" + concesionMinera.getNombreConcesionarioPrincipal() + "',\n";
        }
        if (concesionMinera.getApellidoConcesionarioPrincipal() != null) {
            sql = sql + "    apellido_concesionario_principal = '" + concesionMinera.getApellidoConcesionarioPrincipal() + "',\n";
        }
        if (concesionMinera.getCodigoModalidadTrabajo() != null && concesionMinera.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_modalidad_trabajo = " + concesionMinera.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() + ",\n";
        } else {
            sql = sql + "    codigo_modalidad_trabajo = " + null + ",\n";
        }
        if (concesionMinera.getCodigoFormaExplotacion() != null && concesionMinera.getCodigoFormaExplotacion().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_forma_explotacion = " + concesionMinera.getCodigoFormaExplotacion().getCodigoCatalogoDetalle() + ",\n";
        } else {
            sql = sql + "    codigo_forma_explotacion = " + null + ",\n";
        }
        if (concesionMinera.getModalidad() != null) {
            sql = sql + "    modalidad = '" + concesionMinera.getModalidad() + "',\n";
        }
        if (concesionMinera.getTipoMaterial() != null) {
            sql = sql + "    tipo_material = '" + concesionMinera.getTipoMaterial() + "',\n";
        }
        if (concesionMinera.getFormaExplotacion() != null) {
            sql = sql + "    forma_explotacion = '" + concesionMinera.getFormaExplotacion() + "',\n";
        }
        if (concesionMinera.getMaterialInteres() != null) {
            sql = sql + "    material_interes = '" + concesionMinera.getMaterialInteres() + "',\n";
        } else {
            sql = sql + "    material_interes = " + null + ",\n";
        }
        if (concesionMinera.getVolumenDiarioExplotacion() != null) {
            sql = sql + "    volumen_diario_explotacion = " + concesionMinera.getVolumenDiarioExplotacion() + ",\n";
        }
        //if (concesionMinera.getVolumenTotalExplotacion() != null) {
        sql = sql + "    volumen_total_explotacion = " + concesionMinera.getVolumenTotalExplotacion() + ",\n";
        //} 
        if (concesionMinera.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + concesionMinera.getCasilleroJudicial() + "',\n";
        } else {
            sql = sql + "    casillero_judicial = " + null + ",\n";
        }
        if (concesionMinera.getAceptaCondiciones() != null) {
            sql = sql + "    acepta_condiciones = " + concesionMinera.getAceptaCondiciones() + ",\n";
        }
        if (concesionMinera.getCampoReservado10() != null) {
            sql = sql + "    campo_reservado_10 = '" + concesionMinera.getCampoReservado10() + "',\n";
        }
        if (concesionMinera.getCampoReservado09() != null) {
            sql = sql + "    campo_reservado_09 = '" + concesionMinera.getCampoReservado09() + "',\n";
        }
        if (concesionMinera.getCampoReservado08() != null) {
            sql = sql + "    campo_reservado_08 = '" + concesionMinera.getCampoReservado08() + "',\n";
        }
        if (concesionMinera.getCampoReservado07() != null) {
            sql = sql + "    campo_reservado_07 = '" + concesionMinera.getCampoReservado07() + "',\n";
        }
        if (concesionMinera.getCampoReservado06() != null) {
            sql = sql + "    campo_reservado_06 = '" + concesionMinera.getCampoReservado06() + "',\n";
        }
        if (concesionMinera.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + concesionMinera.getCampoReservado05() + "',\n";
        }
        if (concesionMinera.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + concesionMinera.getCampoReservado04() + "',\n";
        }
        if (concesionMinera.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + concesionMinera.getCampoReservado03() + "',\n";
        }
        if (concesionMinera.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + concesionMinera.getCampoReservado02() + "',\n";
        }
        if (concesionMinera.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + concesionMinera.getCampoReservado01() + "',\n";
        }
        if (concesionMinera.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + concesionMinera.getEstadoRegistro() + ",\n";
        }
        if (concesionMinera.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + concesionMinera.getFechaCreacion() + "',\n";
        }
        if (concesionMinera.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + concesionMinera.getUsuarioCreacion() + ",\n";
        }
        if (concesionMinera.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + concesionMinera.getFechaModificacion() + "',\n";
        }
        if (concesionMinera.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + concesionMinera.getUsuarioModificacion() + ",\n";
        }
        if (concesionMinera.getFechaOtorga() != null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_otorga = '" + formatoDelTexto.format(concesionMinera.getFechaOtorga()) + "',\n";
        } else {
            sql = sql + "    fecha_otorga = null" + ",\n";
        }
        if (concesionMinera.getFechaInscribe() != null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_inscribe = '" + formatoDelTexto.format(concesionMinera.getFechaInscribe()) + "',\n";
        } else {
            sql = sql + "    fecha_inscribe = null" + ",\n";
        }
        if (concesionMinera.getMae() != null) {
            sql = sql + "    mae = " + concesionMinera.getMae() + " ,\n";
        }
        if (concesionMinera.getSenagua() != null) {
            sql = sql + "    senagua = " + concesionMinera.getSenagua() + " ,\n";
        }
        if (concesionMinera.getLitispendencia()!= null) {
            sql = sql + "    litispendencia = " + concesionMinera.getLitispendencia()+ " ,\n";
        }
        if (concesionMinera.getLitispendencia()!= null) {
            sql = sql + "    declaracion_juramentada = " + concesionMinera.getDeclaracionJuramentada()+ " ,\n";
        }
        if (concesionMinera.getObsActosAdmPrevios() != null) {
            sql = sql + "    obs_actos_adm_previos = '" + concesionMinera.getObsActosAdmPrevios() + "',\n";
        }
        if (concesionMinera.getObservacionGeneral()!= null) {
            sql = sql + "    observacion_general = '" + concesionMinera.getObservacionGeneral()+ "',\n";
        }
        if (concesionMinera.getSector() != null) {
            sql = sql + "    sector = '" + concesionMinera.getSector() + "',\n";
        }
        if (concesionMinera.getFechaInforme() != null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_informe = '" + formatoDelTexto.format(concesionMinera.getFechaInforme()) + "',\n";
        } else {
            sql = sql + "    fecha_informe = null" + ",\n";
        }
        if (concesionMinera.getCodigoCasilleroLocalidad() != null) {
            sql = sql + "    codigo_casillero_localidad = " + concesionMinera.getCodigoCasilleroLocalidad().getCodigoLocalidad() + ",\n";
        }
        if (concesionMinera.getFechaSustitucion() != null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_sustitucion = '" + formatoDelTexto.format(concesionMinera.getFechaSustitucion()) + "',\n";
        } else {
            sql = sql + "    fecha_sustitucion = " + null + ",\n";
        }
        if (concesionMinera.getFechaInscripcionSustitucion() != null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_inscripcion_sustitucion = '" + formatoDelTexto.format(concesionMinera.getFechaInscripcionSustitucion()) + "',\n";
        } else {
            sql = sql + "    fecha_inscripcion_sustitucion = null ,\n";
        }
        if (concesionMinera.getCodigoTipoMaterial() != null) {
            sql += "    codigo_tipo_material = " + concesionMinera.getCodigoTipoMaterial().getCodigoCatalogo() + ",\n";
        }
        if (concesionMinera.getCodigoMaterialInteres() != null) {
            sql += "    codigo_material_interes = " + concesionMinera.getCodigoMaterialInteres().getCodigoCatalogoDetalle() + ",\n";
        }
        if (concesionMinera.getNumeroResolucionCambioRegimen()!= null) {
            sql = sql + "    numero_resolucion_cambio_regimen = '" + concesionMinera.getNumeroResolucionCambioRegimen()+ "',\n";
        }
        if (concesionMinera.getFechaOtorgamientoCambioRegimen()!= null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_otorgamiento_cambio_regimen = '" + formatoDelTexto.format(concesionMinera.getFechaOtorgamientoCambioRegimen()) + "',\n";
        } else {
            sql = sql + "    fecha_otorgamiento_cambio_regimen = null ,\n";
        }
        if (concesionMinera.getFechaInscripcionCambioRegimen()!= null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_inscripcion_cambio_regimen = '" + formatoDelTexto.format(concesionMinera.getFechaInscripcionCambioRegimen()) + "',\n";
        } else {
            sql = sql + "    fecha_inscripcion_cambio_regimen = null ,\n";
        }
        if (concesionMinera.getNumeroResolucionArchivo()!= null) {
            sql = sql + "    numero_resolucion_archivo = '" + concesionMinera.getNumeroResolucionArchivo()+ "',\n";
        }
        if (concesionMinera.getFechaResolucionArchivo()!= null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_resolucion_archivo = '" + formatoDelTexto.format(concesionMinera.getFechaResolucionArchivo()) + "',\n";
        } else {
            sql = sql + "    fecha_resolucion_archivo = null ,\n";
        }
        if (concesionMinera.getFechaArchivo()!= null) {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            sql = sql + "    fecha_archivo = '" + formatoDelTexto.format(concesionMinera.getFechaArchivo()) + "',\n";
        } else {
            sql = sql + "    fecha_archivo = null ,\n";
        }
        sql = sql + "    codigo_mae = " + concesionMinera.getCodigoMae() + ",\n";
        sql = sql + "    codigo_senagua = " + concesionMinera.getCodigoSenagua() + ",\n";

        sql = sql + "    plazo_dias = " + concesionMinera.getDias() + ",\n";
        sql = sql + "    migrada = " + concesionMinera.getMigrada() + "\n";
        sql = sql + "WHERE\n";
        sql = sql + "    codigo_concesion = " + concesionMinera.getCodigoConcesion();;

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<DerechoMineroDto> busquedaGeneralNacional(String codigo, String nombre, Long codigoRegional, Long codigoProvincia,
            Long codigoFase, Long codigoEstado, String tipoSolicitud, String beneficiarioPrincipal, String tipoPersona, Date fechaDesde,
            Date fechaHasta, String numDocumento) {
        String sql1 = "";
        if (tipoSolicitud == null || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getNemonico())
                || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getNemonico())
                || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getNemonico())
                || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_PEQ_MIN.getNemonico())
                || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_MA_PEQ_MIN.getNemonico())) {
            sql1 = "select concesiones.codigo_arcom, concesiones.nombre_concesion,\n"
                    + "concesiones.nombre_regional, concesiones.provincia, concesiones.fase,\n"
                    + "concesiones.estado, concesiones.nombre_tipo_mineria, concesiones.beneficiario_principal,\n"
                    + "concesiones.tipo_persona, concesiones.fecha_inscribe, 'C' as tipo_form, concesiones.codigo_concesion, \n"
                    + "concesiones.documento_concesionario_principal \n"
                    + "from\n"
                    + "(\n"
                    + "select cm.codigo_arcom as codigo_arcom, \n"
                    + "cm.nombre_concesion as nombre_concesion, \n"
                    + "(select r.codigo_regional from catmin.regional r, catmin.localidad_regional l "
                    + "where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as codigo_regional,\n"
                    + "--r.nombre_regional, \n"
                    + "prov.nombre as provincia, \n"
                    + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                    + "--COALESCE(f.nombre_fase, '') as fase, \n"
                    + "est.nombre as estado, \n"
                    + "tm.nombre_tipo_mineria,\n"
                    + "case when p.apellido is null then p.nombre else p.apellido || ' ' || p.nombre end as beneficiario_principal,\n"
                    + "--COALESCE(cm.nombre_concesionario_principal || ' ' || cm.apellido_concesionario_principal, '') as beneficiario_principal, \n"
                    + "--case when char_length (cm.documento_concesionario_principal) = 10 then 'PN' else 'PJ' end as tipo_persona, \n"
                    + "p.tipo_persona,\n"
                    + "cm.fecha_inscribe, \n"
                    + "'C' as tipo_form, \n"
                    + "cm.codigo_concesion,\n"
                    + "prov.codigo_localidad as codigo_provincia,\n"
                    + "(select codigo_fase from catmin.fase where codigo_fase = cm.codigo_fase) as codigo_fase,\n"
                    + "est.codigo_catalogo_detalle as codigo_estado,\n"
                    + "(select r.nombre_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional, \n"
                    + "cm.codigo_tipo_mineria, \n"
                    + "cm.documento_concesionario_principal \n"
                    + "from catmin.concesion_minera cm, catmin.localidad prov, catmin.catalogo_detalle est, catmin.tipo_mineria tm, catmin.personas p\n"
                    + "where prov.codigo_localidad = cm.codigo_provincia\n"
                    + "and est.codigo_catalogo_detalle = cm.estado_concesion\n"
                    + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                    + "and cm.documento_concesionario_principal = p.numero_documento\n"
                    //+ "and cm.codigo_arcom not like '%FALLIDA%'\n"
                    + "and cm.estado_registro = true\n"
                    + ") as concesiones where 1=1\n";
            if (codigo != null && !codigo.isEmpty()) {
                sql1 += "and concesiones.codigo_arcom = '" + codigo + "'\n";
            }
            if (nombre != null && !nombre.isEmpty()) {
                sql1 += "and concesiones.nombre_concesion ilike '%" + nombre + "%'\n";
            }
            if (codigoRegional != null) {
                sql1 += "and concesiones.codigo_regional = " + codigoRegional + "\n";
            }
            if (codigoProvincia != null) {
                sql1 += "and concesiones.codigo_provincia = " + codigoProvincia + "\n";
            }
            if (codigoFase != null) {
                sql1 += "and concesiones.codigo_fase = " + codigoFase + "\n";
            }
            if (codigoEstado != null) {
                sql1 += "and concesiones.codigo_estado = " + codigoEstado + "\n";
            }
            if (beneficiarioPrincipal != null && !beneficiarioPrincipal.isEmpty()) {
                sql1 += "and concesiones.beneficiario_principal ilike '%" + beneficiarioPrincipal + "%'\n";
            }
            if (tipoPersona != null && !tipoPersona.isEmpty()) {
                sql1 += "and concesiones.tipo_persona = '" + tipoPersona + "'\n";
            }
            if (numDocumento != null && !numDocumento.isEmpty()) {
                sql1 += "and concesiones.documento_concesionario_principal = '" + numDocumento + "'\n";
            }
            if (tipoSolicitud != null && !tipoSolicitud.isEmpty()) {
                if (tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getNemonico())) {
                    sql1 += "and concesiones.codigo_tipo_mineria = " + ConstantesEnum.TIPO_SOLICITUD_CONS_MIN.getCodigo() + "\n";
                }
                if (tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getNemonico())) {
                    sql1 += "and concesiones.codigo_tipo_mineria = " + ConstantesEnum.TIPO_SOLICITUD_LIB_APR.getCodigo() + "\n";
                }
                if (tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getNemonico())) {
                    sql1 += "and concesiones.codigo_tipo_mineria = " + ConstantesEnum.TIPO_SOLICITUD_MIN_ART.getCodigo() + "\n";
                }
                if (tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_PEQ_MIN.getNemonico())) {
                    sql1 += "and concesiones.codigo_tipo_mineria = " + ConstantesEnum.TIPO_SOLICITUD_PEQ_MIN.getCodigo() + "\n";
                }
                if (tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_MA_PEQ_MIN.getNemonico())) {
                    sql1 += "and concesiones.codigo_tipo_mineria = " + ConstantesEnum.TIPO_SOLICITUD_MA_PEQ_MIN.getCodigo() + "\n";
                }
            }
        }
        if (tipoSolicitud == null) {
            sql1 += "union\n";
        }
        if (tipoSolicitud == null || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_LIC_COM.getNemonico())) {
            sql1 += "select licencias.codigo_arcom, licencias.nombre_licencia, licencias.nombre_regional, licencias.provincia, \n"
                    + "licencias.fase, licencias.estado, licencias.tipo_solicitud, licencias.beneficiario_principal, \n"
                    + "licencias.tipo_persona, licencias.fecha_inscribe, 'L' as tipo_form, licencias.codigo_licencia_comercializacion, \n"
                    + "licencias.numero_documento \n"
                    + "from \n"
                    + "(select\n"
                    + "l.codigo_arcom, \n"
                    + "l.nombre as nombre_licencia, \n"
                    + "(select nombre_regional from catmin.regional r, catmin.localidad_regional lc where l.codigo_provincia = lc.codigo_localidad and r.codigo_regional = lc.codigo_regional) as nombre_regional,\n"
                    + "--licencias.nombre_regional, \n"
                    + "prov.nombre as provincia,\n"
                    + "'' as fase,\n"
                    + "--licencias.fase, \n"
                    + "est.nombre as estado,\n"
                    + "(select nombre_tipo_mineria from catmin.tipo_mineria where nemonico_tipo_mineria = 'LICCOM') as tipo_solicitud, \n"
                    + "case when p.apellido is null then p.nombre else p.apellido || ' ' || p.nombre end as beneficiario_principal,\n"
                    + "--licencias.beneficiario_principal,\n"
                    + "p.tipo_persona,\n"
                    + "--licencias.tipo_persona, \n"
                    + "l.fecha_inscribe, \n"
                    + "'L' as tipo_form, \n"
                    + "l.codigo_licencia_comercializacion, \n"
                    + "(select r.codigo_regional from catmin.regional r, catmin.localidad_regional lc where l.codigo_provincia = lc.codigo_localidad and r.codigo_regional = lc.codigo_regional) as codigo_regional, \n"
                    + "l.codigo_provincia as codigo_localidad, \n"
                    + "l.estado_licencia as codigo_catalogo_detalle, \n"
                    + "l.numero_documento \n"
                    + "from catmin.licencia_comercializacion l, catmin.localidad prov, catmin.catalogo_detalle est, catmin.personas p\n"
                    + "where prov.codigo_localidad = l.codigo_provincia \n"
                    + "and l.estado_registro = true \n"
                    + "and est.codigo_catalogo_detalle = l.estado_licencia\n"
                    + "and l.numero_documento = p.numero_documento\n"
                    + ") as licencias where 1=1\n";
            if (codigo != null && !codigo.isEmpty()) {
                sql1 += "and licencias.codigo_arcom = '" + codigo + "'\n";
            }
            if (nombre != null && !nombre.isEmpty()) {
                sql1 += "and licencias.nombre_licencia ilike '%" + nombre + "%'\n";
            }
            if (codigoRegional != null) {
                sql1 += "and licencias.codigo_regional = " + codigoRegional + "\n";
            }
            if (codigoProvincia != null) {
                sql1 += "and licencias.codigo_localidad = " + codigoProvincia + "\n";
            }
            if (codigoEstado != null) {
                sql1 += "and licencias.codigo_catalogo_detalle = " + codigoEstado + "\n";
            }
            if (beneficiarioPrincipal != null && !beneficiarioPrincipal.isEmpty()) {
                sql1 += "and licencias.beneficiario_principal ilike '%" + beneficiarioPrincipal + "%'\n";
            }
            if (tipoPersona != null && !tipoPersona.isEmpty()) {
                sql1 += "and licencias.tipo_persona = '" + tipoPersona + "'\n";
            }
            if (numDocumento != null && !numDocumento.isEmpty()) {
                sql1 += "and licencias.numero_documento = '" + numDocumento + "'\n";
            }
        }
        if (tipoSolicitud == null) {
            sql1 += "union\n";
        }
        if (tipoSolicitud == null || tipoSolicitud.equals(ConstantesEnum.TIPO_SOLICITUD_PLAN_BEN.getNemonico())) {
            sql1 += "select plantas.codigo_arcom, plantas.nombre_planta_beneficio, plantas.nombre_regional, plantas.provincia, plantas.fase, \n"
                    + "plantas.estado, plantas.tipo_solicitud, plantas.beneficiario_principal, plantas.tipo_persona, plantas.fecha_inscribe, 'P' as tipo_form, \n"
                    + "plantas.codigo_planta_beneficio, plantas.numero_documento_representante_legal \n"
                    + "from\n"
                    + "(select \n"
                    + "pb.codigo_arcom, \n"
                    + "pb.nombre_planta_beneficio, \n"
                    + "(select nombre_regional from catmin.regional r, catmin.localidad_regional lc where pb.codigo_provincia = lc.codigo_localidad and r.codigo_regional = lc.codigo_regional) as nombre_regional,\n"
                    + "--rl.nombre_regional, \n"
                    + "prov.nombre as provincia, \n"
                    + "'' as fase,\n"
                    + "est.nombre as estado, \n"
                    + "--cd.nombre as estado, \n"
                    + "(select nombre_tipo_mineria from catmin.tipo_mineria where nemonico_tipo_mineria = 'PLANBEN') as tipo_solicitud, \n"
                    + "--'PLANTA BENEFICIO' as tipo_derecho,\n"
                    + "--COALESCE(pb.nombre_representante_legal || ' ' || pb.apellido_representante_legal, '') as beneficiario_principal,\n"
                    + "case when p.apellido is null then p.nombre else p.apellido || ' ' || p.nombre end as beneficiario_principal,\n"
                    + "p.tipo_persona,\n"
                    + "--case when char_length (pb.numero_documento_representante_legal) = 10 then 'PN' else 'PJ' end as tipo_persona, \n"
                    + "pb.fecha_inscribe, \n"
                    + "'P' as tipo_form, \n"
                    + "pb.codigo_planta_beneficio, \n"
                    + "(select r.codigo_regional from catmin.regional r, catmin.localidad_regional lc where pb.codigo_provincia = lc.codigo_localidad and r.codigo_regional = lc.codigo_regional) as codigo_regional, \n"
                    + "pb.codigo_provincia as codigo_localidad, \n"
                    + "pb.estado_planta as codigo_catalogo_detalle, \n"
                    + "pb.numero_documento_representante_legal \n"
                    + "from catmin.planta_beneficio pb, catmin.localidad prov, catmin.catalogo_detalle est, catmin.personas p\n"
                    + "where prov.codigo_localidad = pb.codigo_provincia \n"
                    + "and pb.estado_registro = true \n"
                    + "and est.codigo_catalogo_detalle = pb.estado_planta\n"
                    + "and p.numero_documento = pb.numero_documento_representante_legal\n"
                    + "and pb.codigo_arcom is not null\n"
                    + ") as plantas where 1=1\n";
            if (codigo != null && !codigo.isEmpty()) {
                sql1 += "and plantas.codigo_arcom = '" + codigo + "'\n";
            }
            if (nombre != null && !nombre.isEmpty()) {
                sql1 += "and plantas.nombre_planta_beneficio ilike '%" + nombre + "%'\n";
            }
            if (codigoRegional != null) {
                sql1 += "and plantas.codigo_regional = " + codigoRegional + "\n";
            }
            if (codigoProvincia != null) {
                sql1 += "and plantas.codigo_localidad = " + codigoProvincia + "\n";
            }
            if (codigoEstado != null) {
                sql1 += "and plantas.codigo_catalogo_detalle = " + codigoEstado + "\n";
            }
            if (beneficiarioPrincipal != null && !beneficiarioPrincipal.isEmpty()) {
                sql1 += "and plantas.beneficiario_principal ilike '%" + beneficiarioPrincipal + "%'\n";
            }
            if (tipoPersona != null && !tipoPersona.isEmpty()) {
                sql1 += "and plantas.tipo_persona = '" + tipoPersona + "'\n";
            }
            if (numDocumento != null && !numDocumento.isEmpty()) {
                sql1 += "and plantas.numero_documento_representante_legal = '" + numDocumento + "'\n";
            }
        }
        
        if (tipoSolicitud == null) {
            sql1 += "union\n";
        }
        if (tipoSolicitud == null || tipoSolicitud.equals(ConstantesEnum.TIPO_CONTRATOS_OPERACION_REPORTE.getNemonico())) {
            sql1 += "select distinct concesiones.codigo_arcom, concesiones.nombre_concesion,\n"
                    + "concesiones.nombre_regional, concesiones.provincia, concesiones.fase,\n"
                    + "concesiones.estado, concesiones.nombre_tipo_mineria, concesiones.beneficiario_principal,\n"
                    + "concesiones.tipo_persona, concesiones.fecha_inscribe, 'C' as tipo_form, concesiones.codigo_concesion, \n"
                    + "concesiones.documento_concesionario_principal \n"
                    + "from\n"
                    + "(\n"
                    + "select cm.codigo_arcom as codigo_arcom, \n"
                    + "cm.nombre_concesion as nombre_concesion, \n"
                    + "(select r.codigo_regional from catmin.regional r, catmin.localidad_regional l "
                    + "where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as codigo_regional,\n"
                    + "--r.nombre_regional, \n"
                    + "prov.nombre as provincia, \n"
                    + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                    + "--COALESCE(f.nombre_fase, '') as fase, \n"
                    + "est.nombre as estado, \n"
                    + "tm.nombre_tipo_mineria,\n"
                    + "case when p.apellido is null then p.nombre else p.apellido || ' ' || p.nombre end as beneficiario_principal,\n"                                        
                    + "p.tipo_persona,\n"
                    + "cm.fecha_inscribe, \n"
                    + "'C' as tipo_form, \n"
                    + "cm.codigo_concesion,\n"
                    + "prov.codigo_localidad as codigo_provincia,\n"
                    + "(select codigo_fase from catmin.fase where codigo_fase = cm.codigo_fase) as codigo_fase,\n"
                    + "est.codigo_catalogo_detalle as codigo_estado,\n"
                    + "(select r.nombre_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional, \n"
                    + "cm.codigo_tipo_mineria, \n"
                    + "cm.documento_concesionario_principal \n"
                    + "from catmin.contrato_operacion co,catmin.concesion_minera cm , catmin.localidad prov, catmin.catalogo_detalle est, catmin.tipo_mineria tm, catmin.personas p\n"
                    + "where prov.codigo_localidad = cm.codigo_provincia "
                    + "and co.codigo_concesion = cm.codigo_concesion \n"
                    + "and co.estado_contrato = 243 \n"
                    + "and co.estado_registro = true \n"
                    + "and (co.codigo_arcom like  '%CO%' or co.codigo_arcom like  '%CD%') \n"
                    + "and est.codigo_catalogo_detalle = cm.estado_concesion\n"
                    + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                    + "and cm.documento_concesionario_principal = p.numero_documento\n"                    
                    + "and cm.estado_registro = true\n"
                    + ") as concesiones where 1=1\n";             
            if (codigo != null && !codigo.isEmpty()) {
                sql1 += "and concesiones.codigo_arcom = '" + codigo + "'\n";
            }            
            if (beneficiarioPrincipal != null && !beneficiarioPrincipal.isEmpty()) {
                sql1 += "and concesiones.codigo_concesion in (select co.codigo_concesion \n" +
            "	from catmin.contrato_operacion co, catmin.personas p \n" +
            "	where p.numero_documento = co.numero_documento \n" +
            "	and co.estado_contrato = 243 \n" +
            "	and co.estado_registro = true \n" +
            "	and (co.codigo_arcom like  '%CO%' or co.codigo_arcom like  '%CD%') \n" +
            "	and p.apellido || ' ' || p.nombre ilike '%" + beneficiarioPrincipal +"%') \n";
            }  
            if (numDocumento != null && !numDocumento.isEmpty()) {
                sql1 += "and concesiones.codigo_concesion in (\n" +
            "   select co.codigo_concesion \n" +
            "   from catmin.contrato_operacion co \n" +
            "	where co.estado_contrato = 243 \n" +
            "	and co.estado_registro = true \n" +
            "	and (co.codigo_arcom like  '%CO%' or co.codigo_arcom like  '%CD%') \n" +
            "	and co.numero_documento = '"+numDocumento+"') \n";
            }
            if (nombre != null && !nombre.isEmpty()) {
                sql1 += "and concesiones.nombre_concesion ilike '%" + nombre + "%'\n";
            }
            if (codigoRegional != null) {
                sql1 += "and concesiones.codigo_regional = " + codigoRegional + "\n";
            }
            if (codigoProvincia != null) {
                sql1 += "and concesiones.codigo_provincia = " + codigoProvincia + "\n";
            }
            if (codigoFase != null) {
                sql1 += "and concesiones.codigo_fase = " + codigoFase + "\n";
            }
            if (codigoEstado != null) {
                sql1 += "and concesiones.codigo_estado = " + codigoEstado + "\n";
            }
             if (tipoPersona != null && !tipoPersona.isEmpty()) {
                sql1 += "and concesiones.tipo_persona = '" + tipoPersona + "'\n";
            }
        }
        
        System.out.println("sql derechos mineros nacional: " + sql1);
        Query query = em.createNativeQuery(sql1);
        System.out.println("query: " + query);
        List<Object[]> listaTmp = query.getResultList();
        List<DerechoMineroDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            DerechoMineroDto dm = new DerechoMineroDto();
            dm.setCodigo(fila[0].toString());
            dm.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : "Sin nombre");
            dm.setRegional(fila[2] != null ? fila[2].toString() : "Sin regional");
            dm.setProvincia(fila[3] != null ? fila[3].toString() : "Sin provincia");
            dm.setFase(fila[4] != null ? fila[4].toString() : "Sin fase");
            dm.setEstado(fila[5] != null ? fila[5].toString() : "Sin estado");
            dm.setTipoSolicitud(fila[6] != null ? fila[6].toString() : "Sin tipo de solicitud");
            dm.setBeneficiarioPrincipal(fila[7] != null ? fila[7].toString() : "Sin beneficiario");
            dm.setTipoPersona(fila[8] != null ? (fila[8].toString().equals("PN") ? "Persona Natural" : "Persona Jurídica") : "Sin persona");
            dm.setFechaSolicitud(fila[9] != null ? fila[9].toString() : "");
            dm.setTipoDerechoMinero(fila[10].toString());
            dm.setId(Long.valueOf(fila[11].toString()));
            dm.setNumDocumentoRepLegal(fila[12] != null ? fila[12].toString() : "Sin número documento");
            listaFinal.add(dm);
        }
        return listaFinal;
    }

    @Override
    public List<ConcesionMinera> list() {
        try {
            Query query = em.createQuery("Select c from ConcesionMinera c order by c.codigoConcesion asc");
            List<ConcesionMinera> listaFinal = query.getResultList();
            for (ConcesionMinera cm : listaFinal) {
                this.refresh(cm);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public void update(ConcesionMinera cm) {
        em.merge(cm);
    }

    @Override
    public List<ConcesionMinera> findByCodigo(String codigo) {
        try {
            Query query = em.createQuery("Select c from ConcesionMinera c where c.codigoArcom= :codigo order by c.codigoConcesion asc");
            query.setParameter("codigo", codigo);
            List<ConcesionMinera> listaFinal = query.getResultList();
            for (ConcesionMinera cm : listaFinal) {
                this.refresh(cm);
            }
            return listaFinal;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public String obtenerNombreConcesion(Long codigo) {
        try {
            Query query = em.createQuery("Select c.nombreConcesion from ConcesionMinera c where c.codigoConcesion= :codigo");
            query.setParameter("codigo", codigo);
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al obtener el nombre de la concesión: " + ex.toString());
        }
        return null;
    }

    @Override
    public String obtenerDocumentoConcesionario(Long codigo) {
        try {
            Query query = em.createQuery("Select c.documentoConcesionarioPrincipal from ConcesionMinera c where c.codigoConcesion= :codigo");
            query.setParameter("codigo", codigo);
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al obtener el documento del concesionario: " + ex.toString());
        }
        return null;
    }

    @Override
    public String obtenerCodigoArcom(Long codigoConcesion) {
        try {
            Query query = em.createQuery("Select c.codigoArcom from ConcesionMinera c where c.codigoConcesion= :codigo");
            query.setParameter("codigo", codigoConcesion);
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al obtener el codigoArcom de la concesion: " + ex.toString());
        }
        return null;
    }

    @Override
    public String obtenerRegionalConcesion(Long codigo) {
        try {
            Query query = em.createQuery("Select c.codigoRegional from ConcesionMinera c where c.codigoConcesion= :codigo");
            query.setParameter("codigo", codigo);
            Regional r= (Regional) query.getSingleResult();
            return r.getNombreRegional();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al obtener la regional de la concesion: " + ex.toString());
        }
        return null;
    }
}
