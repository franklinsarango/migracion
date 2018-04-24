/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.DenunciaDao;
import ec.gob.arcom.migracion.modelo.Denuncia;
import ec.gob.arcom.migracion.servicio.DenunciaServicio;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "DenunciaServicio")
public class DenunciaServicioImpl extends GenericServiceImpl<Denuncia, Long> implements DenunciaServicio {
    @EJB
    private DenunciaDao denunciaDao;
    
    @Override
    public GenericDao<Denuncia, Long> getDao() {
        return denunciaDao;
    }

}
