/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.CoordenadaCotaDao;
import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CoordenadaCotaDao")
public class CoordenadaCotaDaoEjb extends GenericDaoEjbEl<CoordenadaCota, Long> implements
        CoordenadaCotaDao {

    public CoordenadaCotaDaoEjb() {
        super(CoordenadaCota.class);
    }

    @Override
    public List<CoordenadaCota> obtenerPorContratoOrden(Long codigoContrato, BigInteger orden) {
        String jpql = "select cc from CoordenadaCota cc where cc.codigoContratoOperacion.codigoContratoOperacion = :codigoContrato and cc.orden = :orden";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoContrato", codigoContrato);
        query.setParameter("orden", orden);
        return query.getResultList();
    }

    @Override
    public List<CoordenadaCota> findByCodigoContrato(Long codigoContratoOperacion) {
        String jpql = "select cc from CoordenadaCota cc where cc.codigoContratoOperacion.codigoContratoOperacion = :codigoContratoOperacion";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoContratoOperacion", codigoContratoOperacion);
        return query.getResultList();
    }

}
