/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.TipoMineriaDao;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "TipoMineriaDao")
public class TipoMineriaDaoEjb extends GenericDaoEjbEl<TipoMineria, Long> implements
        TipoMineriaDao {

    public TipoMineriaDaoEjb() {
        super(TipoMineria.class);
    }

    @Override
    public TipoMineria findByPk(Long id) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select tm from TipoMineria tm where ");
        hql.append("tm.codigoTipoMineria = :id ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("id", id);

        TipoMineria tipoMineria = (TipoMineria) query.getSingleResult();
        this.refresh(tipoMineria);
        return tipoMineria;
    }

    @Override
    public TipoMineria findByNemonico(String nemonico) {
        System.out.println("nemonico tipoMineria: " + nemonico);
        try {
            StringBuilder hql = new StringBuilder(100);
            hql.append("select tm from TipoMineria tm where ");
            hql.append("tm.nemonicoTipoMineria = :nemonico ");
            Query query = em.createQuery(hql.toString());
            query.setParameter("nemonico", nemonico);
            TipoMineria tipoMineria = (TipoMineria) query.getSingleResult();
            this.refresh(tipoMineria);
            return tipoMineria;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public TipoMineria findByNombre(String nombre) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select tm from TipoMineria tm where ");
        hql.append("tm.nombreTipoMineria = :nombre ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nombre", nombre);

        TipoMineria tipoMineria = (TipoMineria) query.getSingleResult();
        this.refresh(tipoMineria);
        return tipoMineria;
    }

}
