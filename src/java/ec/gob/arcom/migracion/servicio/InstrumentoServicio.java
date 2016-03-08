/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Instrumento;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface InstrumentoServicio extends GenericService<Instrumento, Long> {

    Instrumento obtenerPorCodigoInstrumento(long codigoInstrumento);

    void actualizarInstrumento(Instrumento instrumento) throws Exception;

    void guardarTodo(Instrumento instrumento, ConcesionMinera concesionMinera,
            LicenciaComercializacion licenciaComercializacion, PlantaBeneficio plantaBeneficio,
            SujetoMinero sujetoMinero) throws Exception;

    void actualizarTiposDeRegistro(ConcesionMinera concesionMinera, LicenciaComercializacion licenciaComercializacion, PlantaBeneficio plantaBeneficio, Instrumento instrumento, SujetoMinero sujetoMinero) throws Exception;

    void actualizarTodo(Instrumento instrumento, ConcesionMinera concesionMinera, LicenciaComercializacion licenciaComercializacion, PlantaBeneficio plantaBeneficio, SujetoMinero sujetoMinero) throws Exception;

}
