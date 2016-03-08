/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.PersonaJuridicaDao;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PersonaJuridicaServicio")
public class PersonaJuridicaServicioImpl extends GenericServiceImpl<PersonaJuridica, String>
        implements PersonaJuridicaServicio {
    
    @EJB
    private PersonaJuridicaDao personaJuridicaDao;

    @Override
    public GenericDao<PersonaJuridica, String> getDao() {
        return personaJuridicaDao;
    }

    @Override
    public PersonaJuridica findByRuc(String ruc) {
        return personaJuridicaDao.findByRuc(ruc);
    }

    @Override
    public void actualizarPersonaJuridica(PersonaJuridica personaJuridica) throws Exception {
        personaJuridicaDao.actualizarPersonaJuridica(personaJuridica);
    }

}
