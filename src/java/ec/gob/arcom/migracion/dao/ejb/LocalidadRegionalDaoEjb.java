/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.LocalidadRegionalDao;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.LocalidadRegionalPK;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LocalidadRegionalDao")
public class LocalidadRegionalDaoEjb extends GenericDaoEjbEl<LocalidadRegional, LocalidadRegionalPK> implements
        LocalidadRegionalDao {

    public LocalidadRegionalDaoEjb() {
        super(LocalidadRegional.class);
    }

    @Override
    public LocalidadRegional obtenerPorCodigoLocalidad(Long codigoLocalidad) {
        String hql = "select lr from LocalidadRegional lr where lr.localidadRegionalPK.codigoLocalidad = :codigoLocalidad";
        Query query = em.createQuery(hql);
        query.setParameter("codigoLocalidad", codigoLocalidad);
        LocalidadRegional localidadRegional = (LocalidadRegional) query.getSingleResult();
        this.refresh(localidadRegional);
        return  localidadRegional;
    }

}
