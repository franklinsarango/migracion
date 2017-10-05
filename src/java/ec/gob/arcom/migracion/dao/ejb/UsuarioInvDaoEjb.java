/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.UsuarioInvDao;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.UsuarioInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class UsuarioInvDaoEjb extends GenericDaoEjbEl<UsuarioInv, Long> implements UsuarioInvDao {
    public UsuarioInvDaoEjb() {
        super(UsuarioInv.class);
    }

    @Override
    public List<UsuarioInv> findByOperativo(Operativo o) {
        try {
            Query query= em.createQuery("Select adj from UsuarioInv adj where adj.tramite= :tramite and adj.codigoTramite= :codigo and adj.estadoRegistro= :estado");
            query.setParameter("tramite", "OPERATIVO");
            query.setParameter("codigo", o.getCodigoOperativo());
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<UsuarioInv> findByFichaTecnica(FichaTecnica ft) {
        try {
            Query query= em.createQuery("Select adj from UsuarioInv adj where adj.tramite= :tramite and adj.codigoTramite= :codigo and adj.estadoRegistro= :estado");
            query.setParameter("tramite", "ACTIVIDADMINERA");
            query.setParameter("codigo", ft.getCodigoFichaTecnica());
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
