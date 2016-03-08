/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ConceptoPagoDao;
import ec.gob.arcom.migracion.modelo.ConceptoPago;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConceptoPagoDao")
public class ConceptoPagoDaoEjb extends GenericDaoEjbEl<ConceptoPago, Long> implements
        ConceptoPagoDao {

    public ConceptoPagoDaoEjb() {
        super(ConceptoPago.class);
    }

}
