/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.FaseDao;
import ec.gob.arcom.migracion.modelo.Fase;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "FaseDao")
public class FaseDaoEjb extends GenericDaoEjbEl<Fase, Long> implements
        FaseDao {

    public FaseDaoEjb() {
        super(Fase.class);
    }

    @Override
    public Fase findByPk(Long id) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select f from Fase f where ");
        hql.append("f.codigoFase = :id ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("id", id);

        Fase fase = (Fase) query.getSingleResult();
        this.refresh(fase);
        return fase;
    }

    @Override
    public Fase findByNemonico(String nemonico) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select f from Fase f where ");
        hql.append("f.nemonicoFase = :nemonico ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nemonico", nemonico);

        Fase fase = (Fase) query.getSingleResult();
        this.refresh(fase);
        return fase;
    }

    @Override
    public Fase findByNombre(String nombre) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("select f from Fase f where ");
        hql.append("f.nombreFase = :nombre ");

        Query query = em.createQuery(hql.toString());
        query.setParameter("nombre", nombre);

        Fase fase = (Fase) query.getSingleResult();
        this.refresh(fase);
        return fase;
    }

    @Override
    public List<Fase> obtenerFaseLikeDescripcion(String descripcion) {
        System.out.println("entra obtenerFaseLikeDescripcion");
        String hql = "select f from Fase f where f.descripcionFase like '%" + descripcion + "%'";
        Query query = em.createQuery(hql);
        List<Fase> fases = query.getResultList();
        System.out.println("fases size: " + fases.size());
        return fases;
    }

}
