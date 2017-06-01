/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.Operativo;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.OperativoDao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class OperativoDaoEjb extends GenericDaoEjbEl<Operativo, Long> implements OperativoDao {
    public OperativoDaoEjb() {
        super(Operativo.class);
    }

    @Override
    public List<Operativo> list() {
        try {
            Query query= em.createQuery("Select op from Operativo op where op.estadoRegistro= :estado order by op.fechaCreacion ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
