/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
//import ec.gob.arcom.migracion.dto.EncuestaDto;
import ec.gob.arcom.migracion.modelo.Encuesta;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface EncuestaServicio extends GenericService<Encuesta, Long> {

    Encuesta obtenerPorCodigoEncuesta(Long codigoRegistroPagoObligaciones);

    Encuesta findByCodigoEncuesta(Long codigoEncuesta);
}
