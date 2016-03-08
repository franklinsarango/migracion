/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import ec.gob.arcom.migracion.dao.ConcesionMineraDaoLocal;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class ConcesionMineraDaoCoor implements ConcesionMineraDaoLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<ConcesionMinera> list() {
        try {
           Query query = em.createQuery("Select c from ConcesionMinera c order by c.codigoConcesion asc");
           return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public void update(ConcesionMinera cm) {
        em.merge(cm);
    }

    @Override
    public List<ConcesionMinera> findByCodigo(String codigo) {
        try {
            Query query= em.createQuery("Select c from ConcesionMinera c where c.codigoArcom= :codigo order by c.codigoConcesion asc");
            query.setParameter("codigo", codigo);
            List<ConcesionMinera> l= query.getResultList();
            for (ConcesionMinera l1 : l) {
                em.refresh(l1);
            }
            return l;
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
