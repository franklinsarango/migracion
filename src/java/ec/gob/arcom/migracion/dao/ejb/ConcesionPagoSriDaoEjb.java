/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.ConcesionPagoSri;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.ConcesionPagoSriDao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class ConcesionPagoSriDaoEjb extends GenericDaoEjbEl<ConcesionPagoSri, Long> implements ConcesionPagoSriDao {
    public ConcesionPagoSriDaoEjb() {
        super(ConcesionPagoSri.class);
    }

    @Override
    public Integer ejecutarFuncion(String anio) {
        try {
            Query query= em.createNativeQuery("select catmin.llenar_tabla_concesion_pago_sri('" + anio + "')");
            return Integer.parseInt((String)query.getSingleResult());
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return -1;
    }

    @Override
    public List<ConcesionPagoSri> findByAnio(String anioFiscal) {
        try {
            Query query= em.createQuery("Select cps from ConcesionPagoSri cps where cps.estadoRegistro= :estado and cps.anioFiscal= :anio order by cps.fechaHora DESC");
            query.setParameter("estado", true);
            query.setParameter("anio", anioFiscal);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
