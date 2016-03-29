/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.AuditorTecnicoDao;
import ec.gob.arcom.migracion.modelo.AuditorTecnico;
import javax.ejb.Stateless;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "AuditorTecnicoDao")
public class AuditorTecnicoDaoEjb extends GenericDaoEjbEl<AuditorTecnico, String> implements AuditorTecnicoDao {
    public AuditorTecnicoDaoEjb() {
        super(AuditorTecnico.class);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public AuditorTecnico findByRuc(String ruc) {
        return em.find(AuditorTecnico.class, ruc);
    }
}
