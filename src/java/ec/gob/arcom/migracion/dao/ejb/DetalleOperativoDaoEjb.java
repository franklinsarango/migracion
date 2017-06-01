/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.DetalleOperativo;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.DetalleOperativoDao;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class DetalleOperativoDaoEjb extends GenericDaoEjbEl<DetalleOperativo, Long> implements DetalleOperativoDao {
    public DetalleOperativoDaoEjb() {
        super(DetalleOperativo.class);
    }

    @Override
    public List<DetalleOperativo> listarPorOperativo(Operativo operativo) {
        try {
            Query query= em.createQuery("Select detope from DetalleOperativo detope where detope.estadoRegistro= :estado and detope.operativo= :operativo");
            query.setParameter("estado", true);
            query.setParameter("operativo", operativo);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
