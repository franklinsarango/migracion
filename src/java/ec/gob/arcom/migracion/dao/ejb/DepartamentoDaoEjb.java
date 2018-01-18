/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.Departamento;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.DepartamentoDao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class DepartamentoDaoEjb extends GenericDaoEjbEl<Departamento, Long> implements DepartamentoDao {
    public DepartamentoDaoEjb() {
        super(Departamento.class);
    }

    @Override
    public List<Departamento> listar() {
        try {
            Query query= em.createQuery("Select dep from Departamento dep where dep.estadoRegistro= :estado order by dep.nombre ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
