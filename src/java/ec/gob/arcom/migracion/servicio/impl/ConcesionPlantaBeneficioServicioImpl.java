/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ConcesionPlantaBeneficioDao;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK;
import ec.gob.arcom.migracion.servicio.ConcesionPlantaBeneficioServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConcesionPlantaBeneficioServicio")
public class ConcesionPlantaBeneficioServicioImpl extends GenericServiceImpl<ConcesionPlantaBeneficio, ConcesionPlantaBeneficioPK>
        implements ConcesionPlantaBeneficioServicio {
    
    @EJB
    private ConcesionPlantaBeneficioDao concesionPlantaBeneficioDao;

    @Override
    public GenericDao<ConcesionPlantaBeneficio, ConcesionPlantaBeneficioPK> getDao() {
        return concesionPlantaBeneficioDao;
    }

}
