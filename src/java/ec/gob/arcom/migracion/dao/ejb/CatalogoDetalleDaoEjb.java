/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.CatalogoDetalleDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CatalogoDetalleDao")
public class CatalogoDetalleDaoEjb extends GenericDaoEjbEl<CatalogoDetalle, Long> implements
        CatalogoDetalleDao {

    public CatalogoDetalleDaoEjb() {
        super(CatalogoDetalle.class);
    }

    @Override
    public List<CatalogoDetalle> findByCatalogo(Long codigoCatalogo) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select cd from CatalogoDetalle cd where ");
        hql.append("cd.codigoCatalogo.codigoCatalogo = :codigoCatalogo and cd.estadoRegistro = true");

        Query query = em.createQuery(hql.toString());
        query.setParameter("codigoCatalogo", codigoCatalogo);

        List<CatalogoDetalle> resp = query.getResultList();
        return resp;
    }

    @Override
    public List<CatalogoDetalle> obtenerPorNemonico(String nemonico) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select cd from CatalogoDetalle cd where ");
        hql.append("cd.nemonico = :nemonico ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nemonico", nemonico);

        List<CatalogoDetalle> resp = query.getResultList();

        return resp;
    }

    @Override
    public CatalogoDetalle obtenerPorValor(String valor) {
        try {
            String hql = "select c from CatalogoDetalle c where c.valor = :valor";
            Query query = em.createQuery(hql);
            query.setParameter("valor", valor);
            CatalogoDetalle cd = (CatalogoDetalle) query.getSingleResult();
            this.refresh(cd);
            return cd;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public CatalogoDetalle obtenerPorCodigoCatalogoDetalle(Long codigoCatalogoDetalle) {
        try {
            String hql = "select cd from CatalogoDetalle cd where cd.codigoCatalogoDetalle = :codigoCatalogoDetalle";
            Query query = em.createQuery(hql);
            query.setParameter("codigoCatalogoDetalle", codigoCatalogoDetalle);
            CatalogoDetalle cd = (CatalogoDetalle) query.getSingleResult();
            this.refresh(cd);
            return cd;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

}
