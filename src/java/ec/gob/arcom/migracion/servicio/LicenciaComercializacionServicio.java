/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.LicenciaComercializacionDto;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface LicenciaComercializacionServicio extends GenericService<LicenciaComercializacion, Long> {

    List<LicenciaComercializacionDto> obtenerListaLicencias(String codigoArcom, String identificacion, String usuario);

    void actualizarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception;

    void guardarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception;

    LicenciaComercializacion obtenerPorCodigoArcom(String codigoarcom);

}
