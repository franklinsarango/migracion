/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.AuditoriaDao;
import ec.gob.arcom.migracion.modelo.Auditoria;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "AuditoriaDao")
public class AuditoriaDaoEjb extends GenericDaoEjbEl<Auditoria, Long> implements
        AuditoriaDao {

    public AuditoriaDaoEjb() {
        super(Auditoria.class);
    }

}
