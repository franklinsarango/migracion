/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.RegionalDao;
import ec.gob.arcom.migracion.modelo.Regional;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegionalDao")
public class RegionalDaoEjb extends GenericDaoEjbEl<Regional, Long> implements
        RegionalDao {

    public RegionalDaoEjb() {
        super(Regional.class);
    }

    @Override
    public Regional findByPk(Long id) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select r from Regional r where ");
        hql.append("r.codigoRegional = :id ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("id", id);

        Regional regional = (Regional) query.getSingleResult();
        this.refresh(regional);
        return regional;
    }

    @Override
    public Regional findByNemonico(String nemonico) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select r from Regional r where ");
        hql.append("r.nemonicoRegional = :nemonico ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nemonico", nemonico);

        Regional regional = (Regional) query.getSingleResult();
        this.refresh(regional);
        return regional;
    }

    @Override
    public Regional findByNombre(String nombre) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select r from Regional r where ");
        hql.append("c.nombreRegional = :nombre ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nombre", nombre);

        Regional regional = (Regional) query.getSingleResult();
        this.refresh(regional);
        return regional;
    }

    @Override
    public String[] findByCedulaRuc(String cedulaRuc) {
        String sql = "select nombre_regional, prefijo_codigo from catmin.regional r, catmin.localidad_regional lr\n"
                + "where lr.codigo_localidad =  (select codigo_provincia from catmin.usuario where numero_documento = '" + cedulaRuc + "') \n"
                + "and lr.codigo_regional = r.codigo_regional";
        System.out.println("sql usuario findByCedulaRuc: " + sql);
        try {
            String[] resultado = new String[2]; 
            Query query = em.createNativeQuery(sql);
            List<Object[]> listaTmp = query.getResultList();
            if (listaTmp != null && !listaTmp.isEmpty()) {
                resultado[0] = listaTmp.get(0)[0].toString();
                resultado[1] = listaTmp.get(0)[1].toString();
            }
            return resultado;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public List<Regional> findActivos() {
        String jpql = "select r from Regional r where r.estadoRegistro = true";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

}
