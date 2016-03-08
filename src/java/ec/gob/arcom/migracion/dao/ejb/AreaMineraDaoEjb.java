/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.AreaMineraDao;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "AreaMineraDao")
public class AreaMineraDaoEjb extends GenericDaoEjbEl<AreaMinera, Long> implements
        AreaMineraDao {

    public AreaMineraDaoEjb() {
        super(AreaMinera.class);
    }

    @Override
    public AreaMinera obtenerPorConcesionMinera(Long codigoConcesion) {
        try {
            String sql = "select am from AreaMinera am where am.codigoConcesion.codigoConcesion = :codigoConcesion";
            Query query = em.createQuery(sql);
            query.setParameter("codigoConcesion", codigoConcesion);
            AreaMinera areaMinera = (AreaMinera) query.getSingleResult();
            this.refresh(areaMinera);
            return areaMinera;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarAreaMinera(AreaMinera areaMinera) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.area_minera\n"
                + "SET\n";
        if (areaMinera.getCodigoAreaMinera() != null) {
            sql = sql + "    codigo_area_minera = " + areaMinera.getCodigoAreaMinera() + ",\n";
        }
        if (areaMinera.getCodigoConcesion() != null) {
            sql = sql + "    codigo_concesion = " + areaMinera.getCodigoConcesion().getCodigoConcesion() + ",\n";
        }
        if (areaMinera.getNombreAreaMinera() != null) {
            sql = sql + "    nombre_area_minera = '" + areaMinera.getNombreAreaMinera() + "',\n";
        }
        if (areaMinera.getNemonicoAreaMinera() != null) {
            sql = sql + "    nemonico_area_minera = '" + areaMinera.getNemonicoAreaMinera() + "',\n";
        }
        if (areaMinera.getSuperficieAreaMinera() != null) {
            sql = sql + "    superficie_area_minera = " + areaMinera.getSuperficieAreaMinera() + ",\n";
        }
        if (areaMinera.getCodigoLocalidad() != null) {
            sql = sql + "    codigo_localidad = " + areaMinera.getCodigoLocalidad().getCodigoLocalidad() + ",\n";
        }
        if (areaMinera.getSectorReferencia() != null) {
            sql = sql + "    sector_referencia = '" + areaMinera.getSectorReferencia() + "',\n";
        }
        if (areaMinera.getSistemaReferencia() != null) {
            sql = sql + "    sistema_referencia = '" + areaMinera.getSistemaReferencia() + "',\n";
        }
        if (areaMinera.getEstadoArea() != null) {
            sql = sql + "    estado_area = " + areaMinera.getEstadoArea().getCodigoCatalogoDetalle() + ",\n";
        }
        if (areaMinera.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + areaMinera.getEstadoRegistro() + " ,\n";
        }
        if (areaMinera.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + areaMinera.getFechaCreacion() + "',\n";
        }
        if (areaMinera.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + areaMinera.getUsuarioCreacion() + ",\n";
        }
        if (areaMinera.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + areaMinera.getFechaModificacion() + "',\n";
        }
        if (areaMinera.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + areaMinera.getUsuarioModificacion() + ",\n";
        }
        if (areaMinera.getFechaOtorga() != null) {
            sql = sql + "    fecha_otorga = '" + areaMinera.getFechaOtorga() + "',\n";
        }
        if (areaMinera.getFechaInscribe() != null) {
            sql = sql + "    fecha_inscribe = '" + areaMinera.getFechaInscribe() + "',\n";
        }
        sql = sql + "    migrada = true\n"
                + "WHERE\n"
                + "    codigo_area_minera = " + areaMinera.getCodigoAreaMinera();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
