/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.SadminDataDao;
import ec.gob.arcom.migracion.modelo.SadminData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SadminDataDaoEjb")
public class SadminDataDaoEjb extends GenericDaoEjbEl<SadminData, Long> implements
        SadminDataDao {

    public SadminDataDaoEjb() {
        super(SadminData.class);
    }

    @Override
    public List<SadminData> obtenerRegistrosPorUsuario(String cedulaRuc, String codigoFiltro, String cedulaTitularFiltro, String nombreAreaFiltro) {
        StringBuilder sql = new StringBuilder("select * from catmin.sadmin_data_ where orden = 0 and regional = ");
        sql.append("(select nombre_regional from catmin.localidad_regional l, catmin.usuario, catmin.regional r where numero_documento = '").append(cedulaRuc).append("' ");
        if (codigoFiltro != null && !codigoFiltro.trim().isEmpty()) {
            sql.append("and codigo like '%").append(codigoFiltro).append("%' ");
        }
        if (cedulaTitularFiltro != null && !cedulaTitularFiltro.trim().isEmpty()) {
            sql.append("and cedula___ruc like '%").append(cedulaTitularFiltro).append("%' ");
        }
        if (nombreAreaFiltro != null && !nombreAreaFiltro.trim().isEmpty()) {
            sql.append("and nombre like '%").append(nombreAreaFiltro).append("%' ");
        }
        sql.append("and codigo_provincia = l.codigo_localidad and l.codigo_regional = r.codigo_regional) ");
        sql.append("and codigo not in (select codigo_arcom from catmin.concesion_minera c where c.usuario_creacion = '-1') ");
        sql.append("and codigo not in (select codigo_arcom from catmin.licencia_comercializacion l where l.usuario_creacion = '-1') ");
        sql.append("and codigo not in (select codigo_arcom from catmin.planta_beneficio p where p.usuario_creacion = '-1') order by codigo");
        System.out.println("sql: " + sql);
        Query query = em.createNativeQuery(sql.toString());

        List<Object[]> listaTmp = query.getResultList();
        List<SadminData> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            SadminData sd = new SadminData();
            sd.setId(Long.valueOf(fila[0].toString()));
            sd.setF1(Double.parseDouble(fila[1].toString()));
            sd.setCodigo(fila[2] != null ? fila[2].toString() : null);
            sd.setNombre(fila[3] != null ? fila[3].toString() : null);
            sd.setCasillero(fila[4] != null ? fila[4].toString() : null);
            sd.setCodPersona(fila[5] != null ? Double.parseDouble(fila[5].toString()) : null);
            sd.setDireccion(fila[6].toString());
            sd.setTelefono(fila[7] != null ? fila[7].toString() : null);
            sd.setTitular(fila[8] != null ? fila[8].toString() : null);
            sd.setRepresentanteLegal(fila[9] != null ? fila[9].toString() : null);
            sd.setCedulaRepresentante(fila[10] != null ? fila[10].toString() : null);
            sd.setPlazo(fila[11] != null ? Double.parseDouble(fila[11].toString()) : null);
            sd.setFechaInforme(fila[12] != null ? (Date) fila[12] : null);
            sd.setZona(Double.parseDouble(fila[13].toString()));
            sd.setSuperficie(fila[14] != null ? Double.parseDouble(fila[14].toString()) : null);
            sd.setEstado(fila[15].toString());
            sd.setFase(fila[16].toString());
            sd.setTipoSolicitud(fila[17].toString());
            //System.out.println("(Date) fila[18]: " + (Date) fila[18]);
            sd.setFechaOtorga(fila[18] != null ? (Date) fila[18] : null);
            //System.out.println("(Date) fila[19]: " + (Date) fila[19]);
            sd.setFechaInscribe(fila[19] != null ? (Date) fila[19] : null);
            sd.setProvincia(fila[20].toString());
            sd.setRegional(fila[21].toString());
            sd.setProvincia1(fila[22] != null ? fila[22].toString() : null);
            sd.setProvincia2(fila[23].toString());
            sd.setCanton(fila[24] != null ? fila[24].toString() : null);
            sd.setCanton1(fila[25] != null ? fila[25].toString() : null);
            sd.setParroquia(fila[26] != null ? fila[26].toString() : null);
            sd.setParroquia1(fila[27] != null ? fila[27].toString() : null);
            sd.setMineral(fila[28] != null ? fila[28].toString() : null);
            sd.setOrden(Double.parseDouble(fila[29].toString()));
            sd.setCoordX(fila[30] != null ? fila[30].toString() : null);
            sd.setCoordY(fila[31] != null ? fila[31].toString() : null);
            sd.setManifiestos(Double.parseDouble(fila[32].toString()));
            sd.setMineralManifiesto(fila[33] != null ? fila[33].toString() : null);
            sd.setCedulaRuc(fila[34] != null ? fila[34].toString().trim() : null);
            sd.setFechaSustitucion(fila[35] != null ? (Date) fila[35] : null);
            listaFinal.add(sd);
        }

        return listaFinal;
    }

    @Override
    public SadminData findByPk(Long id) {

        String hql = "select sd from SadminData sd where sd.id = :id";
        Query query = em.createQuery(hql);
        query.setParameter("id", id);
        SadminData sadminData = (SadminData) query.getSingleResult();
        /*if (sadminData.getCedulaRuc() == null && sadminData.getCedulaRuc().trim().isEmpty()) {
         sadminData.setCedulaRuc(null);
         }*/
        this.refresh(sadminData);
        return sadminData;
    }

    @Override
    public void actualizarRegistros(SadminData sadminData) throws Exception {
        System.out.println("antes sql update");
        StringBuilder sql = new StringBuilder("update catmin.sadmin_data_ set f1 = ").append(sadminData.getF1()).append(", nombre = '").append(sadminData.getNombre()).append("', casillero = '").append(sadminData.getCasillero() != null ? sadminData.getCasillero() : null);
        sql.append("', cod_persona = ").append(sadminData.getCodPersona()).append(", direccion = '").append(sadminData.getDireccion()).append("', telefono = '").append(sadminData.getTelefono()).append("', titular = '");
        sql.append(sadminData.getTitular()).append("', representante_legal = '").append(sadminData.getRepresentanteLegal()).append("', cedula_representante = '").append(sadminData.getCedulaRepresentante()).append("', ");
        sql.append("plazo = ").append(sadminData.getPlazo());
        if (sadminData.getFechaInforme() != null) {
            sql.append(", fecha_informe = '").append(sadminData.getFechaInforme()).append("'");
        } else {
            sql.append(", fecha_informe = null");
        }
        sql.append(", zona = ").append(sadminData.getZona()).append(", superficie = ").append(sadminData.getSuperficie());
        sql.append(", estado = '").append(sadminData.getEstado());
        if (sadminData.getFase() != null) {
            sql.append("', fase = '").append(sadminData.getFase()).append("'");
        } else {
            sql.append("', fase = null");
        }
        sql.append(", tipo_solicitud = '").append(sadminData.getTipoSolicitud()).append("', metodo_explotacion = '");
        sql.append(sadminData.getMetodoExplotacion()).append("', mae = ").append(sadminData.isMae()).append(", senagua = ").append(sadminData.isSenagua()).append(", obs_actos_adm_previos = '").append(sadminData.getObsActosAdmPrevios());
        sql.append("', sector = '").append(sadminData.getSector()).append("', codigo_regimen = ").append(sadminData.getCodigoRegimen().getCodigoRegimen()).append(", email = '").append(sadminData.getEmail()).append("', tipo_persona = '");
        sql.append(sadminData.getTipoPersona()).append("' ");
        if (sadminData.getFechaOtorga() != null) {
            sql.append(", fecha_otorga = '").append(sadminData.getFechaOtorga()).append("'");
        } else {
            sql.append(", fecha_otorga = null");
        }
        if (sadminData.getFechaInscribe() != null) {
            sql.append(", fecha_inscribe = '").append(sadminData.getFechaInscribe()).append("'");
        } else {
            sql.append(", fecha_inscribe = null");
        }
        sql.append(", regional = '").append(sadminData.getRegional()).append("', provincia1 = '").append(sadminData.getProvincia1());
        sql.append("', canton = '").append(sadminData.getCanton()).append("', parroquia = '").append(sadminData.getParroquia()).append("', mineral = '").append(sadminData.getMineral()).append("', cedula___ruc = '").append(sadminData.getCedulaRuc());
        sql.append("', tipo_mineral = '").append(sadminData.getTipoMineral()).append("', codigo_modalidad_trabajo = ");
        sql.append(sadminData.getCodigoModalidadTrabajo() != null && sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() != null ? sadminData.getCodigoModalidadTrabajo().getCodigoCatalogoDetalle() : null);
        sql.append(", codigo_casillero_localidad = ").append(sadminData.getCodigoCasilleroLocalidad().getCodigoLocalidad());
        if (sadminData.getFechaSustitucion() != null) {
            sql.append(", fecha_sustitucion = '").append(sadminData.getFechaSustitucion()).append("'");
        } else {
            sql.append(", fecha_sustitucion = null");
        }
        if (sadminData.getFechaInscripcionSustitucion() != null) {
            sql.append(", fecha_inscripcion_sustitucion = '").append(sadminData.getFechaInscripcionSustitucion()).append("'");
        } else {
            sql.append(", fecha_inscripcion_sustitucion = null");
        }
        sql.append(", volumen_total_explotacion = ").append(sadminData.getVolumenTotalExplotacion());
        sql.append(", plazo_dias = ").append(sadminData.getDias());
        sql.append(" where codigo = '").append(sadminData.getCodigo()).append("'");
        System.out.println("sql update: " + sql);
        Query query = em.createNativeQuery(sql.toString());
        query.executeUpdate();
    }

}
