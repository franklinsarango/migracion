/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.modelo.Regional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface OperativoDao extends GenericDao<Operativo, Long> {

    public List<Operativo> list();

    public List<Operativo> obtenerPorRegional(Regional regional);
    
}
