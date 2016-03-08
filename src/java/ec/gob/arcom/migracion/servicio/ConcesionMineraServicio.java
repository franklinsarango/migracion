/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Solicitud;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface ConcesionMineraServicio extends GenericService<ConcesionMinera, Long> {

    List<ConcesionMineraDto> obtenerRegistrosPorUsuario(String cedulaRuc, String codigoFiltro, String cedulaTitularFiltro, String nombreAreaFiltro);

    Long obtenerSiguienteCodigoConcesion();

    void guardarTodo(ConcesionMinera concesionMinera, Solicitud solicitud, AreaMinera areaMinera, Usuario login) throws Exception;

    void actualizarTodo(ConcesionMinera concesionMinera, Solicitud solicitud, AreaMinera AreaMinera) throws Exception;

    void actualizarConcecionMinera(ConcesionMinera concesionMinera) throws Exception;

    ConcesionMinera obtenerPorCodigoArcom(String codigoArcom);

    /**
     * 
     * Método que obtiene todos los derechos mineros registrados en la base de datos
     * en base a todos los parámetros que se muestran a continuación.
     * 
     * @param codigo
     * @param nombreDerechoMinero
     * @param codigoRegional
     * @param codigoProvincia
     * @param codigoFase
     * @param codigoEstado
     * @param tipoSolicitud
     * @param beneficiarioPrincipal
     * @param tipoPersona
     * @param fecha
     * @return 
     */
    List<DerechoMineroDto> obtenerDerechosMinerosNacional(String codigo, String nombreDerechoMinero, Long codigoRegional, Long codigoProvincia, Long codigoFase, Long codigoEstado, String tipoSolicitud, String beneficiarioPrincipal, String tipoPersona, Date fecha, String numDocumento);
    
}
