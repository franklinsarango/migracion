/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.SadminDataMaquinariaDao;
import ec.gob.arcom.migracion.modelo.SadminDataMaquinaria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SadminDataMaquinariaDao")
public class SadminDataMaquinariaDaoEjb extends GenericDaoEjbEl<SadminDataMaquinaria, Long> implements
        SadminDataMaquinariaDao {

    public SadminDataMaquinariaDaoEjb() {
        super(SadminDataMaquinaria.class);
    }

    @Override
    public List<SadminDataMaquinaria> obtenerMaquinariasPorCodigoSadmin(String codigoArcom) {
        String hql = "select sdm from SadminDataMaquinaria sdm where sdm.codigoArcom = :codigoArcom";
        Query query = em.createQuery(hql);
        query.setParameter("codigoArcom", codigoArcom);
        return query.getResultList();
    }

}
