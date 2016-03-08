/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.PersonaNaturalDao;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PersonaNaturalServicio")
public class PersonaNaturalServicioImpl extends GenericServiceImpl<PersonaNatural, String>
        implements PersonaNaturalServicio {
    
    @EJB
    private PersonaNaturalDao personaNaturalDao;

    @Override
    public GenericDao<PersonaNatural, String> getDao() {
        return personaNaturalDao;
    }

    @Override
    public PersonaNatural findByNumeroDocumento(String numeroDocumento) {
        return personaNaturalDao.findByNumeroDocumento(numeroDocumento);
    }

    @Override
    public void actualizarPersonaNatural(PersonaNatural personaNatural) throws Exception {
        personaNaturalDao.actualizarPersonaNatural(personaNatural);
    }

    @Override
    public PersonaDto obtenerPersonaPorNumIdentificacion(String numIdentificacion) {
        return personaNaturalDao.obtenerPersonaPorNumIdentificacion(numIdentificacion);
    }

}
