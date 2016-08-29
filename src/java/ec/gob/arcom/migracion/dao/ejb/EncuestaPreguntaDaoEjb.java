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
import ec.gob.arcom.migracion.dao.EncuestaPreguntaDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.EncuestaPregunta;
import ec.gob.arcom.migracion.modelo.RegistroPagoDetalle;
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
@Stateless(name = "EncuestaPreguntaDao")
public class EncuestaPreguntaDaoEjb extends GenericDaoEjbEl<EncuestaPregunta, Long> implements
        EncuestaPreguntaDao {

    /*@EJB
    private EncuestaDao encuestaDao;
    @EJB
    private EncuestaPreguntaDao preguntaDao;
    @EJB
    private OpcionDao OpcionDao;
*/
    public EncuestaPreguntaDaoEjb() {
        super(EncuestaPregunta.class);
    }

    @Override
    public List<EncuestaPregunta> findByCodigoEncuesta(Encuesta encuesta) {
        try {            
            Query query = em.createNamedQuery("EncuestaPregunta.findByCodigoEncuesta");
            query.setParameter("codigoEncuesta", encuesta);
            List<EncuestaPregunta> listaEncuestaPreguntas = query.getResultList();
            for(EncuestaPregunta p : listaEncuestaPreguntas){
                this.refresh(p);
            }    
            return listaEncuestaPreguntas;
        } catch (NoResultException nrException) {
            return null;
        }
    }

   
}
