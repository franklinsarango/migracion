/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ConceptoPagoDao;
import ec.gob.arcom.migracion.modelo.ConceptoPago;
import ec.gob.arcom.migracion.servicio.ConceptoPagoServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConceptoPagoServicio")
public class ConceptoPagoServicioImpl extends GenericServiceImpl<ConceptoPago, Long>
        implements ConceptoPagoServicio {
    
    @EJB
    private ConceptoPagoDao conceptoPagoDao;

    @Override
    public GenericDao<ConceptoPago, Long> getDao() {
        return conceptoPagoDao;
    }

}
