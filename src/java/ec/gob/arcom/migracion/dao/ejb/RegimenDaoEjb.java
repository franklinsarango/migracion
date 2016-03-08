/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.RegimenDao;
import ec.gob.arcom.migracion.modelo.Regimen;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RegimenDao")
public class RegimenDaoEjb extends GenericDaoEjbEl<Regimen, Long> implements
        RegimenDao {

    public RegimenDaoEjb() {
        super(Regimen.class);
    }

}
