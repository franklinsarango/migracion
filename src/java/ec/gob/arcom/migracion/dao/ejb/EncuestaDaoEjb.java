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
import ec.gob.arcom.migracion.dao.EncuestaDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.Encuesta;
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
@Stateless(name = "EncuestaDao")
public class EncuestaDaoEjb extends GenericDaoEjbEl<Encuesta, Long> implements
        EncuestaDao {

    /*@EJB
    private EncuestaDao encuestaDao;
    @EJB
    private PreguntaDao preguntaDao;
    @EJB
    private OpcionDao OpcionDao;
*/
    public EncuestaDaoEjb() {
        super(Encuesta.class);
    }

    @Override
    public Encuesta obtenerPorCodigoEncuesta(Long codigoEncuesta) {
        try {
            String hql = "select rpo from Encuesta rpo where rpo.codigoRegistro = :codigoEncuesta";
            Query query = em.createQuery(hql);
            query.setParameter("codigoEncuesta", codigoEncuesta);
            Encuesta registroPagoObligaciones = (Encuesta) query.getSingleResult();
            this.refresh(registroPagoObligaciones);
            return registroPagoObligaciones;
        } catch (NoResultException nrException) {
            return null;
        }
    }

    @Override
    public Encuesta findByCodigoEncuesta(Long codigoEncuesta) {
        try {
            Query query = em.createNamedQuery("Encuesta.findByCodigoEncuesta");
            query.setParameter("codigoEncuesta", codigoEncuesta);
            Encuesta encuesta = (Encuesta) query.getSingleResult();
            return encuesta;
        } catch (Exception e) {
            return null;
        }
    }
   
}
