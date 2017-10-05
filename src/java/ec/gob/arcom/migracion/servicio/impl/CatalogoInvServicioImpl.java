/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.CatalogoInvDao;
import ec.gob.arcom.migracion.modelo.CatalogoInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.servicio.CatalogoInvServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "CatalogoInvServicio")
public class CatalogoInvServicioImpl extends GenericServiceImpl<CatalogoInv, Long> implements CatalogoInvServicio {
    @EJB
    private CatalogoInvDao catalogoInvDao;
    
    @Override
    public GenericDao<CatalogoInv, Long> getDao() {
        return catalogoInvDao;
    }

    @Override
    public List<CatalogoInv> findByNemonico(String nemonico) {
        return catalogoInvDao.findByNemonico(nemonico);
    }

    @Override
    public void registrar(CatalogoInv catalogo) {
        Long id = catalogoInvDao.getMaxId() + 1L;
        catalogo.setId(id);
        this.create(catalogo);
    }
}
