/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.MaquinariaConcesion;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface MaquinariaConcesionDao extends GenericDao<MaquinariaConcesion, Long> {

    List<MaquinariaConcesion> obtenerMaquinariasPorConcesion(Long codigoConcesion);

    public List<MaquinariaConcesion> obtenerMaquinariasPorOperativo(Operativo operativo);

}
