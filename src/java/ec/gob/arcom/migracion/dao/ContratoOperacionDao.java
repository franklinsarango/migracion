/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface ContratoOperacionDao extends GenericDao<ContratoOperacion, Long> {

    ContratoOperacion findByPk(Long codigoContratoOperacion);

    void actualizarContratoOperacion(ContratoOperacion contratoOperacion);

    List<ContratoOperacion> obtenerContratosOperacion(String codigoConcesion, String numDocumneto, String loginDocumento);
    
    List<ContratoOperacion> countByContratoOperacionTabla(String codigoArcom, String numDocumento, int paramLimit, int paramOffset);
    int countByContratoOperacionTablaTotal(String codigoArcom, String numDocumento);

    List<ContratoOperacion> obtenerCotitulares(ConcesionMinera concesionMinera);
}
