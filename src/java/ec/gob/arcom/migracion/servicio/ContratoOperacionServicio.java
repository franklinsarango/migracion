/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface ContratoOperacionServicio extends GenericService<ContratoOperacion, Long> {

    ContratoOperacion findByPk(Long codigoContratoOperacion);

    void guardarTodo(ContratoOperacion contratoOperacion);

    void actualizarContratoOperacion(ContratoOperacion contratoOperacion);

    List<ContratoOperacion> obtenerContratosOperacion(String codigoArcom, String numDocumento);

}
