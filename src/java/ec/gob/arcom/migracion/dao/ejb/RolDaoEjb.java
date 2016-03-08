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

}
