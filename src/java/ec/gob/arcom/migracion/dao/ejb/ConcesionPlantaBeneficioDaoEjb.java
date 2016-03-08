/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ConcesionPlantaBeneficioDao;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConcesionPlantaBeneficioDao")
public class ConcesionPlantaBeneficioDaoEjb extends GenericDaoEjbEl<ConcesionPlantaBeneficio, ConcesionPlantaBeneficioPK> implements
        ConcesionPlantaBeneficioDao {

    public ConcesionPlantaBeneficioDaoEjb() {
        super(ConcesionPlantaBeneficio.class);
    }


}
