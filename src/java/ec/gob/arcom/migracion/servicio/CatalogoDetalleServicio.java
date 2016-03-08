/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface CatalogoDetalleServicio extends GenericService<CatalogoDetalle, Long> {
    
    List<CatalogoDetalle> obtenerPorCatalogo(Long codigoCatalogo);
    
    List<CatalogoDetalle> obtenerPorNemonico(String nemonico);

    CatalogoDetalle obtenerPorCodigoCatalogoDetalle(Long codigoCatalogoDetalle);

}
