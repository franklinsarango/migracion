/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.ConcesionPagoSri;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface ConcesionPagoSriServicio extends GenericService<ConcesionPagoSri, Long>{
    Integer ejecutarFuncion(String anio);

    public List<ConcesionPagoSri> findByAnio(String anioFiscal);
}
