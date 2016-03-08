/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.LocalidadRegionalDao;
import ec.gob.arcom.migracion.modelo.LocalidadRegional;
import ec.gob.arcom.migracion.modelo.LocalidadRegionalPK;
import ec.gob.arcom.migracion.servicio.LocalidadRegionalServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LocalidadRegionalServicio")
public class LocalidadRegionalServicioImpl extends GenericServiceImpl<LocalidadRegional, LocalidadRegionalPK>
        implements LocalidadRegionalServicio {
    
    @EJB
    private LocalidadRegionalDao localidadRegionalDao;

    @Override
    public GenericDao<LocalidadRegional, LocalidadRegionalPK> getDao() {
        return localidadRegionalDao;
    }

    @Override
    public LocalidadRegional obtenerPorCodigoLocalidad(Long codigoLocalidad) {
        return localidadRegionalDao.obtenerPorCodigoLocalidad(codigoLocalidad);
    }

}
