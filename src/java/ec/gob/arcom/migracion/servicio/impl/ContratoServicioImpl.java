/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ContratoDao;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.ContratoServicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "ContratoServicio")
public class ContratoServicioImpl extends GenericServiceImpl<Contrato, Long> implements ContratoServicio {
    @EJB
    private ContratoDao contratoDao;
    
    @Override
    public GenericDao<Contrato, Long> getDao() {
        return contratoDao;
    }

    @Override
    public Contrato contratoUsuarioEstado(Usuario usuario, Long estadoContrato) {
        return contratoDao.contratoUsuarioEstado(usuario, estadoContrato);
    }
    
    @Override
    public List<Contrato> listaContratoUsuarioEstado(Usuario usuario){
        return contratoDao.listaContratoUsuarioEstado(usuario);
    }
    
    @Override
    public List<Contrato> listar() {
        return contratoDao.listar();
    }
}
