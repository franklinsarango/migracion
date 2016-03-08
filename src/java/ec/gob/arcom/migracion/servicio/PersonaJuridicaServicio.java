/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface PersonaJuridicaServicio extends GenericService<PersonaJuridica, String> {

    PersonaJuridica findByRuc(String ruc);

    void actualizarPersonaJuridica(PersonaJuridica personaJuridica) throws Exception;
    
}
