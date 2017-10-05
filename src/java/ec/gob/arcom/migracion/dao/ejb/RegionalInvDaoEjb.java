/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.RegionalInvDao;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.RegionalInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class RegionalInvDaoEjb extends GenericDaoEjbEl<RegionalInv, Long> implements RegionalInvDao {
    public RegionalInvDaoEjb() {
        super(RegionalInv.class);
    }

    @Override
    public List<RegionalInv> listaRegionales() {
        try {
            Query query= em.createQuery("SELECT r FROM RegionalInv r ORDER BY r.nombre");           
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

}
