/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.GestionVacacion;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.GestionVacacionDao;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class GestionVacacionDaoEjb extends GenericDaoEjbEl<GestionVacacion, Long> implements GestionVacacionDao {
    public GestionVacacionDaoEjb() {
        super(GestionVacacion.class);
    }

    @Override
    public GestionVacacion findByUser(Long codigoUsuario) {
        try {
            Query query= em.createQuery("Select gv from GestionVacacion gv where gv.estadoRegistro= :estado and gv.usuario.codigoUsuario= :codigo");
            query.setParameter("estado", true);
            query.setParameter("codigo", codigoUsuario);
            return (GestionVacacion) query.getResultList().get(0);
        } catch(Exception ex) {
            //Controlado en caso de que la lista de resultados sea nula, no se presenta errores para mejorar la presentación
           //System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public GestionVacacion findByUserAndFechaCorte(Long codigoUsuario, Date fechaSalida) {
        try {
            Query query= em.createQuery("Select gv from GestionVacacion gv where gv.usuario.codigoUsuario= :codigo and gv.fechaCorte= :fechaCorte");
            query.setParameter("codigo", codigoUsuario);
            query.setParameter("fechaCorte", fechaSalida);
            return (GestionVacacion) query.getResultList().get(0);
        } catch(Exception ex) {
            //Controlado en caso de que la lista de resultados sea nula, no se presenta errores para mejorar la presentación
           //System.out.println(ex.toString());
        }
        return null;
    }
}
