/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.LicenciaComercializacionDao;
import ec.gob.arcom.migracion.dto.LicenciaComercializacionDto;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LicenciaComercializacionDao")
public class LicenciaComercializacionDaoEjb extends GenericDaoEjbEl<LicenciaComercializacion, Long> implements
        LicenciaComercializacionDao {

    public LicenciaComercializacionDaoEjb() {
        super(LicenciaComercializacion.class);
    }

    @Override
    public LicenciaComercializacion findByPk(Long codigoLicencia) {
        try {
            String hql = "select lc from LicenciaComercializacion lc where lc.codigoLicenciaComercializacion = :codigoLicencia";
            Query query = em.createQuery(hql);
            query.setParameter("codigoLicencia", codigoLicencia);
            LicenciaComercializacion licenciaComercializacion = (LicenciaComercializacion) query.getSingleResult();
            this.refresh(licenciaComercializacion);
            return licenciaComercializacion;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public LicenciaComercializacion findByCodigoArcom(String codigoArcom) {
        try {
            String hql = "select lc from LicenciaComercializacion lc where lc.codigoArcom = :codigoArcom";
            Query query = em.createQuery(hql);
            query.setParameter("codigoArcom", codigoArcom);
            LicenciaComercializacion licenciaComercializacion = (LicenciaComercializacion) query.getSingleResult();
            this.refresh(licenciaComercializacion);
            return licenciaComercializacion;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

//    @Override
//    public void update(LicenciaComercializacion licenciaComercializacion) {
//        String hql = "update LicenciaComercializacion lc set lc = :licenciaComercializacion where lc.codigoLicenciaComercializacion = :codigoLicenciaComercializacion";
//        Query query = em.createQuery(hql);
//        query.setParameter("licenciaComercializacion", licenciaComercializacion);
//        query.setParameter("codigoLicenciaComercializacion", licenciaComercializacion.getCodigoLicenciaComercializacion());
//        query.executeUpdate();
//    }
    @Override
    public List<LicenciaComercializacionDto> obtenerListaLicencias(String codigoArcom, String identificacion, String usuario) {
        String sql = "select codigo_licencia_comercializacion,\n"
                + "codigo_arcom,\n"
                + "numero_documento,\n"
                + "nombre,\n"
                + "apellido,\n"
                + "telefono,\n"
                + "direccion,\n"
                + "casillero_judicial,\n"
                + "codigo_provincia,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = l.codigo_provincia) as provincia_inec,\n"
                + "codigo_canton,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = l.codigo_canton) as canton_inec,\n"
                + "codigo_parroquida,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = l.codigo_parroquida) as parroquia_inec,\n"
                + "descripcion_material_comercializar,\n"
                + "(select nombre from catmin.catalogo_detalle where codigo_catalogo_detalle = estado_licencia) as estado_nombre,\n"
                + "estado_licencia as estado_codigo,\n"
                + "tipo_licencia,\n"
                + "observaciones_licencia,\n"
                + "fecha_creacion,\n"
                + "fecha_otorga,\n"
                + "fecha_inscribe\n"
                + "from catmin.licencia_comercializacion l\n"
                + "where 1=1 and l.codigo_provincia in (select lcr.codigo_localidad from catmin.localidad_regional lcr where lcr.codigo_regional = (select r.codigo_regional from catmin.regional r, catmin.localidad_regional lr, catmin.usuario where numero_documento = '" + usuario + "'\n" +
"                                 and r.codigo_regional = lr.codigo_regional and lr.codigo_localidad = codigo_provincia)) \n";
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            sql = sql + "and codigo_arcom like '%" + codigoArcom + "%'\n";
            //sql.concat("and codigo_arcom like '%").concat(codigoArcom).concat("%'\n");
        }
        if (identificacion != null && !identificacion.isEmpty()) {
            sql = sql + "and numero_documento like '%" + identificacion + "%'\n";
            //sql.concat("and numero_documento like '%").concat(identificacion).concat("%'\n");
        }
        System.out.println("sql licencias: " + sql);
        Query query = em.createNativeQuery(sql);
        List<Object[]> listaTmp = query.getResultList();
        List<LicenciaComercializacionDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            LicenciaComercializacionDto lc = new LicenciaComercializacionDto();
            lc.setCodigoLicenciaComercializacion(fila[0].toString());
            lc.setCodigoArcom(fila[1] != null ? fila[1].toString() : null);
            lc.setNumeroDocumento(fila[2] != null ? fila[2].toString() : null);
            lc.setNombre(fila[3] != null ? fila[3].toString() : null);
            lc.setApellido(fila[4] != null ? fila[4].toString() : null);
            lc.setTelefono(fila[5] != null ? fila[5].toString() : null);
            lc.setDireccion(fila[6] != null ? fila[6].toString() : null);
            lc.setCasilleroJudicial(fila[7] != null ? fila[7].toString() : null);
            lc.setProvincia(fila[9] != null ? fila[9].toString() : null);
            lc.setCanton(fila[11] != null ? fila[11].toString() : null);
            lc.setParroquia(fila[13] != null ? fila[13].toString() : null);
            lc.setEstado(fila[15] != null ? fila[15].toString() : null);
            listaFinal.add(lc);
        }
        return listaFinal;
    }

    @Override
    public void actualizarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.licencia_comercializacion\n"
                + "SET\n";
        if (licenciaComercializacion.getCodigoLicenciaComercializacion() != null) {
            sql = sql + "    codigo_licencia_comercializacion = " + licenciaComercializacion.getCodigoLicenciaComercializacion() + ",\n";
        }
        if (licenciaComercializacion.getCodigoInforme() != null) {
            sql = sql + "    codigo_informe = " + licenciaComercializacion.getCodigoInforme() + ",\n";
        }
        if (licenciaComercializacion.getNumeroDocumento() != null) {
            sql = sql + "    numero_documento = '" + licenciaComercializacion.getNumeroDocumento() + "',\n";
        }
        if (licenciaComercializacion.getNombre() != null) {
            sql = sql + "    nombre = '" + licenciaComercializacion.getNombre() + "',\n";
        }
        if (licenciaComercializacion.getApellido() != null) {
            sql = sql + "    apellido = '" + licenciaComercializacion.getApellido() + "',\n";
        }
        if (licenciaComercializacion.getTelefono() != null) {
            sql = sql + "    telefono = '" + licenciaComercializacion.getTelefono() + "',\n";
        } else {
            sql = sql + "    telefono = null" + ",\n";
        }
        if (licenciaComercializacion.getDireccion() != null) {
            sql = sql + "    direccion = '" + licenciaComercializacion.getDireccion() + "',\n";
        } else {
            sql = sql + "    direccion = null" + ",\n";
        }
        if (licenciaComercializacion.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + licenciaComercializacion.getCasilleroJudicial() + "',\n";
        }
        if (licenciaComercializacion.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + licenciaComercializacion.getCodigoProvincia() + ",\n";
        }
        if (licenciaComercializacion.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + licenciaComercializacion.getCodigoCanton() + ",\n";
        }
        if (licenciaComercializacion.getCodigoParroquida() != null) {
            sql = sql + "    codigo_parroquida = " + licenciaComercializacion.getCodigoParroquida() + ",\n";
        }
        if (licenciaComercializacion.getDescripcionMaterialComercializar() != null) {
            sql = sql + "    descripcion_material_comercializar = '" + licenciaComercializacion.getDescripcionMaterialComercializar() + "',\n";
        }
        if (licenciaComercializacion.getEstadoLicencia() != null && licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    estado_licencia = " + licenciaComercializacion.getEstadoLicencia().getCodigoCatalogoDetalle() + ",\n";
        }
        if (licenciaComercializacion.getTipoLicencia() != null && licenciaComercializacion.getTipoLicencia().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    tipo_licencia = " + licenciaComercializacion.getTipoLicencia().getCodigoCatalogoDetalle() + ",\n";
        }
        if (licenciaComercializacion.getFechaOtorgamientoLicencia() != null) {
            sql = sql + "    fecha_otorgamiento_licencia = '" + licenciaComercializacion.getFechaOtorgamientoLicencia() + "',\n";
        } else {
            sql = sql + "    fecha_otorgamiento_licencia = null" + ",\n";
        }
        if (licenciaComercializacion.getObservacionesLicencia() != null) {
            sql = sql + "    observaciones_licencia = '" + licenciaComercializacion.getObservacionesLicencia() + "',\n";
        }
        if (licenciaComercializacion.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + licenciaComercializacion.getCampoReservado05() + "',\n";
        }
        if (licenciaComercializacion.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + licenciaComercializacion.getCampoReservado04() + "',\n";
        }
        if (licenciaComercializacion.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + licenciaComercializacion.getCampoReservado03() + "',\n";
        }
        if (licenciaComercializacion.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + licenciaComercializacion.getCampoReservado02() + "',\n";
        }
        if (licenciaComercializacion.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + licenciaComercializacion.getCampoReservado01() + "',\n";
        }
        if (licenciaComercializacion.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + licenciaComercializacion.getEstadoRegistro() + ",\n";
        }
        if (licenciaComercializacion.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + licenciaComercializacion.getFechaCreacion() + "',\n";
        }
        if (licenciaComercializacion.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + licenciaComercializacion.getUsuarioCreacion() + ",\n";
        }
        if (licenciaComercializacion.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + licenciaComercializacion.getFechaModificacion() + "',\n";
        }
        if (licenciaComercializacion.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + licenciaComercializacion.getUsuarioModificacion() + ",\n";
        }
        if (licenciaComercializacion.getCodigoArcom() != null) {
            sql = sql + "    codigo_arcom = '" + licenciaComercializacion.getCodigoArcom() + "',\n";
        }
        if (licenciaComercializacion.getFechaOtorga() != null) {
            sql = sql + "    fecha_otorga = '" + licenciaComercializacion.getFechaOtorga() + "',\n";
        } else {
            sql = sql + "    fecha_otorga = null ,\n";
        }
        if (licenciaComercializacion.getFechaInscribe() != null) {
            sql = sql + "    fecha_inscribe = '" + licenciaComercializacion.getFechaInscribe() + "',\n";
        } else {
            sql = sql + "    fecha_inscribe = null ,\n";
        }
        if (licenciaComercializacion.getCodigoTipoMineral() != null && licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_tipo_mineral = " + licenciaComercializacion.getCodigoTipoMineral().getCodigoCatalogoDetalle() + ",\n";
        }
        if (licenciaComercializacion.getCodigoMineralInteres() != null && licenciaComercializacion.getCodigoMineralInteres().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_mineral_interes = " + licenciaComercializacion.getCodigoMineralInteres().getCodigoCatalogoDetalle() + ",\n";
        } else {
            sql = sql + "    codigo_mineral_interes = null" + ",\n";
        }
        if (licenciaComercializacion.getCorreoElectronico() != null) {
            sql = sql + "    correo_electronico = '" + licenciaComercializacion.getCorreoElectronico() + "',\n";
        } else {
            sql = sql + "    correo_electronico = null" + ",\n";
        }
        if (licenciaComercializacion.getObservaciones() != null) {
            sql = sql + "    observaciones = '" + licenciaComercializacion.getObservaciones() + "',\n";
        } else {
            sql = sql + "    observaciones = null" + ",\n";
        }
        if (licenciaComercializacion.getTipoPersona() != null) {
            sql = sql + "    tipo_persona = '" + licenciaComercializacion.getTipoPersona() + "',\n";
        }
        sql = sql + "    migrada = true\n"
                + "WHERE\n"
                + "    codigo_licencia_comercializacion = " + licenciaComercializacion.getCodigoLicenciaComercializacion();
        System.out.println("sql actualiza licencia com: " + sql);
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
