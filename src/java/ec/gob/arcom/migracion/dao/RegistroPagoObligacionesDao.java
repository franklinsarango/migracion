/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.dto.AutoGestionDto;
import ec.gob.arcom.migracion.dto.RegistroPagoObligacionesDto;
import ec.gob.arcom.migracion.modelo.RegistroPagoObligaciones;
import java.math.BigInteger;
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
    
    List<RegistroPagoObligaciones> findByComprobanteElectronico(BigInteger comprobanteElectronico);

    void actualizarRegistroPagoObligaciones(RegistroPagoObligaciones registroPagoObligaciones) throws Exception;

    List<RegistroPagoObligaciones> obtenerPRUNacional();

    List<RegistroPagoObligaciones> obtenerPorCodigoComprobanteArcom(String numeroComprobanteArcom);
    
    List<RegistroPagoObligaciones> obtenerPorCodigoComprobanteBanco(String numeroComprobanteBanco);

    List<AutoGestionDto> obtenerListaAutogestion(Date fechaDesde, Date fechaHasta, String numeroComprobanteArcom, String numeroComprobanteBanco,String cedula, String codigoDerechoMinero, String prefijoRegionalParam, BigInteger numeroTramite, boolean usuarioEconomicoNacional, boolean editarComprobante);
    
    List<RegistroPagoObligacionesDto> obtenerListaPatUtiReg(Date fechaDesde, Date fechaHasta, String numeroComprobanteArcom, String cedula, String codigoDerechoMinero, String prefijoRegionalParam);

}
