/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.RolDao;
import ec.gob.arcom.migracion.modelo.Rol;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RolDao")
public class RolDaoEjb extends GenericDaoEjbEl<Rol, Long> implements
        RolDao {

    public RolDaoEjb() {
        super(Rol.class);
    }

    @Override
    public Rol findByNemonico(String nemonico) {
        try {
            Query query= em.createQuery("Select r from Rol r where r.estadoRegistro= :estado and r.nemonico= :nemonico");
            query.setParameter("estado", true);
            query.setParameter("nemonico", nemonico);
            return (Rol) query.getResultList().get(0);
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
