/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface ConcesionPlantaBeneficioServicio extends GenericService<ConcesionPlantaBeneficio, ConcesionPlantaBeneficioPK> {

}
