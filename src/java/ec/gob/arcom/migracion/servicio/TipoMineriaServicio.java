/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.TipoMineria;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface TipoMineriaServicio extends GenericService<TipoMineria, Long> {
    
    TipoMineria findByNemonico(String nemonico);

    TipoMineria findByNombre(String nombre);
    
}
