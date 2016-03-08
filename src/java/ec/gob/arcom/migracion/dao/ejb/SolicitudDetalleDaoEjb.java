/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.SolicitudDetalleDao;
import ec.gob.arcom.migracion.modelo.SolicitudDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SolicitudDetalleDao")
public class SolicitudDetalleDaoEjb extends GenericDaoEjbEl<SolicitudDetalle, Long> implements
        SolicitudDetalleDao {

    public SolicitudDetalleDaoEjb() {
        super(SolicitudDetalle.class);
    }

    @Override
    public List<SolicitudDetalle> findByCodigoSolicitud(Long codigoSolicitud) {
        String hql = "select sd from SolicitudDetalle sd where sd.codigoSolicitud.codigoSolicitud = :codigoSolicitud order by sd.numeroCoordenada";
        Query query = em.createQuery(hql);
        query.setParameter("codigoSolicitud", codigoSolicitud);
        List<SolicitudDetalle> detalleSolicitud = query.getResultList();
        return detalleSolicitud;
    }

}
