/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.dto.LicenciaComercializacionDto;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface LicenciaComercializacionDao extends GenericDao<LicenciaComercializacion, Long> {

    LicenciaComercializacion findByPk(Long codigoLicencia);

    LicenciaComercializacion findByCodigoArcom(String codigoArcom);

    //void update(LicenciaComercializacion licenciaComercializacion);

    List<LicenciaComercializacionDto> obtenerListaLicencias(String codigoArcom, String identificacion, String usuario);

    void actualizarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception;

}
