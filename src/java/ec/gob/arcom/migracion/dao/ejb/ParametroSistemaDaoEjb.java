/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ParametroSistemaDao;
import ec.gob.arcom.migracion.modelo.ParametroSistema;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ParametroSistemaDao")
public class ParametroSistemaDaoEjb extends GenericDaoEjbEl<ParametroSistema, Long> implements
        ParametroSistemaDao {

    public ParametroSistemaDaoEjb() {
        super(ParametroSistema.class);
    }
/*
    @Override
    public ParametroSistema findByPk(Long id) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select c from ParametroSistema c where ");
        hql.append("c.codigoCatalogo = :id ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("id", id);

        ParametroSistema catalogo = (ParametroSistema) query.getSingleResult();
        this.refresh(catalogo);
        return catalogo;
    }

    @Override
    public ParametroSistema findByNemonico(String nemonico) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select c from ParametroSistema c where ");
        hql.append("c.nemonico = :nemonico ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nemonico", nemonico);

        ParametroSistema catalogo = (ParametroSistema) query.getSingleResult();
        this.refresh(catalogo);
        return catalogo;
    }
*/
    @Override
    public List<ParametroSistema> findByNemonicoLike(String nemonico) {
        String jpql = "select p from ParametroSistema p where 1=1 and p.nemonicoParametro like :nemonicoParametro \n";
        System.out.println("jpql: " + jpql + "Parametro" + nemonico);
        Query query = em.createQuery(jpql);
        query.setParameter("nemonicoParametro", "%" + nemonico + "%");
        List<ParametroSistema> catalogos = query.getResultList();
        return catalogos;
    }
    

    @Override
    public ParametroSistema findByNemonico(String nombre) {
        
        try {
            String jpql = "select p from ParametroSistema p where p.nemonicoParametro = :nemonicoParametro";
            Query query = em.createQuery(jpql);
            query.setParameter("nemonicoParametro", nombre);
            ParametroSistema parametroSistema = (ParametroSistema) query.getSingleResult();
            this.refresh(parametroSistema);
            return parametroSistema;
        } catch (NoResultException nrEx) {
            return null;
        }
    }
/*
    @Override
    public List<ParametroSistema> findByCatalogoPadre(Long catalogoPadre) {
        String jpql = "select c from ParametroSistema c where c.catalogoPadre = :catalogoPadre";
        Query query = em.createQuery(jpql);
        query.setParameter("catalogoPadre", catalogoPadre);
        List<ParametroSistema> catalogos = query.getResultList();
        return catalogos;
    }
*/
}
