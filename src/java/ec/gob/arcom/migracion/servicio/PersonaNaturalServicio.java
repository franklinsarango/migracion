/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface PersonaNaturalServicio extends GenericService<PersonaNatural, String> {

    PersonaNatural findByNumeroDocumento(String numeroDocumento);

    void actualizarPersonaNatural(PersonaNatural personaNatural) throws Exception;

    PersonaDto obtenerPersonaPorNumIdentificacion(String numIdentificacion);
    
}
