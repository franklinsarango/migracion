/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.RegionalInvDao;
import ec.gob.arcom.migracion.modelo.RegionalInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.RegionalInvServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "RegionalInvServicio")
public class RegionalInvServicioImpl extends GenericServiceImpl<RegionalInv, Long> implements RegionalInvServicio {
    @EJB
    private RegionalInvDao regionalInvDao;
    
    @Override
    public GenericDao<RegionalInv, Long> getDao() {
        return regionalInvDao;
    }

    @Override
    public List<RegionalInv> listaRegionales() {
        return regionalInvDao.listaRegionales();
    }

}
