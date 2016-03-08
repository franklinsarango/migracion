/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.CostoServiciosDao;
import ec.gob.arcom.migracion.modelo.CostoServicios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "CostoServiciosDao")
public class CostoServiciosDaoEjb extends GenericDaoEjbEl<CostoServicios, Long> implements
        CostoServiciosDao {

    public CostoServiciosDaoEjb() {
        super(CostoServicios.class);
    }

    @Override
    public List<CostoServicios> obtenerPorTipoServicio(Long codigoTipoServicio) {
        String jpql = "select cs from CostoServicios cs where cs.tipoServicio.codigoCatalogoDetalle = :codigoTipoServicio";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoTipoServicio", codigoTipoServicio);
        return query.getResultList();
    }

    @Override
    public CostoServicios obtenerPorCodigoConceptoPago(Long codigoConceptoPago) {
        try {
            String jpql = "select cs from CostoServicios cs where cs.codigoConceptoPago.codigoConceptoPago = :codigoConceptoPago";
            Query query = em.createQuery(jpql);
            query.setParameter("codigoConceptoPago", codigoConceptoPago);
            CostoServicios costoServicios = (CostoServicios) query.getSingleResult();
            this.refresh(costoServicios);
            return costoServicios;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

}
