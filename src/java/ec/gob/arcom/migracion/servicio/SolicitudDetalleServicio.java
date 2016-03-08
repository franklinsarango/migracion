/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.CoordenadaArea;
import ec.gob.arcom.migracion.modelo.SolicitudDetalle;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface SolicitudDetalleServicio extends GenericService<SolicitudDetalle, Long> {

    void guardarTodo(SolicitudDetalle solicitudDetalle, CoordenadaArea coordenadaArea) throws Exception;

    void eliminarTodo(Long solicitudDetalle, Long coordenadaArea);

}
