/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.CoordenadaCota;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface CoordenadaCotaDao extends GenericDao<CoordenadaCota, Long> {

    List<CoordenadaCota> obtenerPorContratoOrden(Long codigoContrato, BigInteger orden);

    List<CoordenadaCota> findByCodigoContrato(Long codigoCoordenadaCota);

}
