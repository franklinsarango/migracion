/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.LocalidadDao;
import ec.gob.arcom.migracion.modelo.Localidad;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LocalidadDao")
public class LocalidadDaoEjb extends GenericDaoEjbEl<Localidad, Long> implements
        LocalidadDao {

    public LocalidadDaoEjb() {
        super(Localidad.class);
    }

    @Override
    public Localidad findByPk(Long id) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select l from Localidad l where ");
        hql.append("l.codigoLocalidad = :id ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("id", id);

        Localidad localidad = (Localidad) query.getSingleResult();
        this.refresh(localidad);
        return localidad;
    }

    @Override
    public List<Localidad> findByNemonico(String nemonico) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select l from Localidad l where ");
        hql.append("l.nemonico = :nemonico ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nemonico", nemonico);

        List<Localidad> resp = query.getResultList();

        if (resp != null && !resp.isEmpty()) {
            return resp;
        }

        return null;
    }

    @Override
    public Localidad findByCodInternacional(String codInternacional) {
        try {
            StringBuilder hql = new StringBuilder(100);
            hql.append("select l from Localidad l where ");
            hql.append("l.codigoInternacional = :codInternacional ");
            Query query = em.createQuery(hql.toString());
            query.setParameter("codInternacional", codInternacional);
            Localidad localidad = (Localidad) query.getSingleResult();
            this.refresh(localidad);
            return localidad;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public List<Localidad> findByLocalidadPadre(BigInteger localidadPadre) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select l from Localidad l where ");
        hql.append("l.localidadPadre = :localidadPadre ");
        hql.append("order by l.nombre ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("localidadPadre", localidadPadre);

        List<Localidad> resp = query.getResultList();

        return resp;
    }

}
