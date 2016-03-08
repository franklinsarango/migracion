/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.TipoMaquinariaDao;
import ec.gob.arcom.migracion.modelo.TipoMaquinaria;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "TipoMaquinariaDao")
public class TipoMaquinariaDaoEjb extends GenericDaoEjbEl<TipoMaquinaria, Long> implements
        TipoMaquinariaDao {

    public TipoMaquinariaDaoEjb() {
        super(TipoMaquinaria.class);
    }

}
