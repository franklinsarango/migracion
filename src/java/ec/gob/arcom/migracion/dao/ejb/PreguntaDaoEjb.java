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
import ec.gob.arcom.migracion.dao.PreguntaDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.Pregunta;
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
@Stateless(name = "PreguntaDao")
public class PreguntaDaoEjb extends GenericDaoEjbEl<Pregunta, Long> implements
        PreguntaDao {

    /*@EJB
    private EncuestaDao encuestaDao;
    @EJB
    private PreguntaDao preguntaDao;
    @EJB
    private OpcionDao OpcionDao;
*/
    public PreguntaDaoEjb() {
        super(Pregunta.class);
    }

    @Override
    public Pregunta findByCodigoPregunta(Long codigoPregunta) {
        try {
            Query query = em.createNamedQuery("Pregunta.codigoPregunta");
            query.setParameter("codigoPregunta", codigoPregunta);
            List<Pregunta> listaPreguntas = query.getResultList();
            for (Pregunta p : listaPreguntas) {
                this.refresh(p);
            }
            if (listaPreguntas.size() > 0) {
                return listaPreguntas.get(0);
            }
            return null;
        } catch (NoResultException nrException) {
            return null;
        }
    }

    @Override
    public Pregunta findByNemonico(String nemonico) {
        try {
            Query query = em.createNamedQuery("Pregunta.findByNemonico");
            query.setParameter("nemonico", nemonico);
            List<Pregunta> listaPreguntas = query.getResultList();
            for (Pregunta p : listaPreguntas) {
                this.refresh(p);
            }
            if (listaPreguntas.size() > 0) {
                return listaPreguntas.get(0);
            }
            return null;
        } catch (NoResultException nrException) {
            return null;
        }
    }
}
