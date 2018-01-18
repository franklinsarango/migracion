/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.DetalleLicenciaDao;
import ec.gob.arcom.migracion.modelo.DetalleLicencia;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class DetalleLicenciaDaoEjb extends GenericDaoEjbEl<DetalleLicencia, Long> implements DetalleLicenciaDao {
    public DetalleLicenciaDaoEjb() {
        super(DetalleLicencia.class);
    }

    @Override
    public List<DetalleLicencia> listarDetallesLicencia(Long codigoLicencia) {
        try {
            Query query= em.createQuery("Select dl from DetalleLicencia dl where dl.estadoRegistro= :estado and dl.licencia.codigoLicencia= :codigoLicencia order by dl.codigoDetalleLicencia ASC");
            query.setParameter("estado", true);
            query.setParameter("codigoLicencia", codigoLicencia);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
