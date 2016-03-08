/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.PlantaBeneficioDto;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface PlantaBeneficioServicio extends GenericService<PlantaBeneficio, Long> {

    List<PlantaBeneficioDto> obtenerListaPlantas(String codigoArcom, String identificacion, String usuario);

    void actualizarPlantaBeneficio(PlantaBeneficio plantaBeneficio) throws Exception;

    void guardarPlantaBeneficio(PlantaBeneficio plantaBeneficio) throws Exception;

    PlantaBeneficio obtenerPorCodigoArcom(String codigoArcom);

    ConcesionMinera buscarPlantaEnConcesion(String codigoArcom);

}
