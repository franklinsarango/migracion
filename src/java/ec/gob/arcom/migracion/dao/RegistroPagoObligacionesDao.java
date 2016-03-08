/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface RegistroPagoObligacionesDao extends GenericDao<RegistroPagoObligaciones, Long> {

    RegistroPagoObligaciones obtenerPorCodigoRegistroPagoObligaciones(Long codigoRegistroPagoObligaciones);

    void actualizarRegistroPagoObligaciones(RegistroPagoObligaciones registroPagoObligaciones) throws Exception;

    List<RegistroPagoObligaciones> obtenerPRUNacional();

    List<RegistroPagoObligaciones> obtenerPorCodigoComprobanteArcom(String numeroComprobanteArcom);

    List<RegistroPagoObligaciones> obtenerListaAutogestion(Date fechaDesde, Date fechaHasta, String numeroComprobanteArcom, String cedula, String codigoDerechoMinero, String prefijoRegionalParam);

}
