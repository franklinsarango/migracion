/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.DetalleFichaTecnicaDao;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import ec.gob.arcom.migracion.servicio.DetalleFichaTecnicaServicio;
import javax.ejb.Stateless;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "DetalleFichaTecnicaServicio")
public class DetalleFichaTecnicaServicioImpl extends GenericServiceImpl<DetalleFichaTecnica, Long> implements DetalleFichaTecnicaServicio {
    @EJB
    private DetalleFichaTecnicaDao detalleFichaTecnicaDao;
    
    @Override
    public GenericDao<DetalleFichaTecnica, Long> getDao() {
        return detalleFichaTecnicaDao;
    }

    @Override
    public List<DetalleFichaTecnica> listar() {
        return detalleFichaTecnicaDao.listar();
    }

    @Override
    public List listarPorFichaTecnica(Long codigoFichaTecnica) {
        return detalleFichaTecnicaDao.listarPorFichaTecnica(codigoFichaTecnica);
    }

    @Override
    public List<DetalleFichaTecnica> listarSociosPorFichaTecnica(Long codigoFichaTecnica) {
        return detalleFichaTecnicaDao.listarSociosPorFichaTecnica(codigoFichaTecnica);
    }
}
