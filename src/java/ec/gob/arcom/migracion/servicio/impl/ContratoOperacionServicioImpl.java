/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ContratoOperacionDao;
import ec.gob.arcom.migracion.dto.ContratoOperacionDTO;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ContratoOperacion;
import ec.gob.arcom.migracion.servicio.ContratoOperacionServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ContratoOperacionServicio")
public class ContratoOperacionServicioImpl extends GenericServiceImpl<ContratoOperacion, Long>
        implements ContratoOperacionServicio {
    
    @EJB
    private ContratoOperacionDao contratoOperacionDao;

    @Override
    public GenericDao<ContratoOperacion, Long> getDao() {
        return contratoOperacionDao;
    }

    @Override
    public ContratoOperacion findByPk(Long codigoContratoOperacion) {
        return contratoOperacionDao.findByPk(codigoContratoOperacion);
    }

    @Override
    public void guardarTodo(ContratoOperacion contratoOperacion) {
        this.create(contratoOperacion);
        System.out.println("contratoOperacion.getCodigoContratoOperacion(): " + contratoOperacion.getCodigoContratoOperacion());
        //contratoOperacion.setCodigoArcom(contratoOperacion.getCodigoConcesion().getCodigoArcom() + contratoOperacion.getCodigoContratoOperacion());
        //contratoOperacionDao.actualizarContratoOperacion(contratoOperacion);
    }

    @Override
    public void actualizarContratoOperacion(ContratoOperacion contratoOperacion) {
        contratoOperacionDao.actualizarContratoOperacion(contratoOperacion);
    }

    @Override
    public List<ContratoOperacion> obtenerContratosOperacion(String codigoArcom, String numDocumento, String loginDocumento) {
        return contratoOperacionDao.obtenerContratosOperacion(codigoArcom, numDocumento, loginDocumento);
    }

    @Override
    public List<ContratoOperacionDTO> getContratosOperacionAll(String cedulaRuc, String codigoArcom, String numDocumento, String beneficiarioPrincipal) {
        return contratoOperacionDao.getContratosOperacionAll(cedulaRuc, codigoArcom, numDocumento, beneficiarioPrincipal);
    }
    
    @Override
    public List<ContratoOperacionDTO> getContratoOperacionCodigoArcomConcesion(String codigoArcom) {    
        return contratoOperacionDao.getContratoOperacionCodigoArcomConcesion(codigoArcom);
    }
    
    @Override
    public List<ContratoOperacion> obtenerCotitulares(ConcesionMinera concesionMinera) {
        return contratoOperacionDao.obtenerCotitulares(concesionMinera);
    }

    @Override
    public List<ContratoOperacion> listarPorCodigoConcesion(Long codigoConcesion) {
        return contratoOperacionDao.listarPorCodigoConcesion(codigoConcesion);
    }
}
