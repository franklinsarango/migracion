/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ResolucionDao;
import ec.gob.arcom.migracion.modelo.Resolucion;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ResolucionDao")
public class ResolucionDaoEjb extends GenericDaoEjbEl<Resolucion, Long> implements
        ResolucionDao {

    public ResolucionDaoEjb() {
        super(Resolucion.class);
    }

}
