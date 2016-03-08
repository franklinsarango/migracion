/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface ConcesionMineraDaoLocal {

    List<ConcesionMinera> list();

    void update(ConcesionMinera cm);

    List<ConcesionMinera> findByCodigo(String codigo);
    
}
