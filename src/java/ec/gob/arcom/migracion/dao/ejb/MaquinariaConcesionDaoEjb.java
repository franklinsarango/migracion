/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.MaquinariaConcesionDao;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "MaquinariaConcesionDao")
public class MaquinariaConcesionDaoEjb extends GenericDaoEjbEl<MaquinariaConcesion, Long> implements
        MaquinariaConcesionDao {

    public MaquinariaConcesionDaoEjb() {
        super(MaquinariaConcesion.class);
    }

    @Override
    public List<MaquinariaConcesion> obtenerMaquinariasPorConcesion(Long codigoConcesion) {
        String hql = "select mc from MaquinariaConcesion mc where mc.codigoConcesion.codigoConcesion = :codigoConcesion";
        Query query = em.createQuery(hql);
        query.setParameter("codigoConcesion", codigoConcesion);
        List<MaquinariaConcesion> maquinariasPorConcesion = query.getResultList();
        return maquinariasPorConcesion;
    }

    @Override
    public List<MaquinariaConcesion> obtenerMaquinariasPorOperativo(Operativo operativo) {
        try {
            Query query= em.createQuery("Select maq from MaquinariaConcesion maq where maq.estadoRegistro= :estado and maq.operativo= :operativo");
            query.setParameter("estado", true);
            query.setParameter("operativo", operativo);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

}
