/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.dto.RegistroPagoObligacionesDto;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.Respuesta;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface RespuestaDao extends GenericDao<Respuesta, Long> {

    List<Respuesta> findByEncuesta(Encuesta encuesta);

}
