/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dao.LicenciaComercializacionDao;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dao.RespuestaDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.Respuesta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "RespuestaDao")
public class RespuestaDaoEjb extends GenericDaoEjbEl<Respuesta, Long> implements
        RespuestaDao {

    /*@EJB
    private EncuestaDao encuestaDao;
    @EJB
    private PreguntaDao preguntaDao;
    @EJB
    private OpcionDao OpcionDao;
*/
    public RespuestaDaoEjb() {
        super(Respuesta.class);
    }

    @Override
    public List<Respuesta> findByEncuesta(Encuesta encuesta) {
        try {
            Query query = em.createNamedQuery("Respuesta.findByEncuesta");
            query.setParameter("encuesta", encuesta);
            List<Respuesta> listaRespuesta = query.getResultList();
            for(Respuesta eop : listaRespuesta){
                this.refresh(eop);
            }    
            return listaRespuesta;
        } catch (NoResultException nrException) {
            return null;
        }
    }

}
