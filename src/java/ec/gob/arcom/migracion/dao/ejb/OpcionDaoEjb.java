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
import ec.gob.arcom.migracion.dao.OpcionDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.Opcion;
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
@Stateless(name = "OpcionDao")
public class OpcionDaoEjb extends GenericDaoEjbEl<Opcion, Long> implements
        OpcionDao {

    public OpcionDaoEjb() {
        super(Opcion.class);
    }

    @Override
    public List<Opcion> findByCodigoPregunta(Pregunta pregunta) {
        try {           
            Query query = em.createNamedQuery("Opcion.findByCodigoPregunta");
            query.setParameter("codigoPregunta", pregunta);
            List<Opcion> listaOpciones = query.getResultList();
            for(Opcion o : listaOpciones){
                this.refresh(o);
            }    
            return listaOpciones;
        } catch (NoResultException nrException) {
            return null;
        }
    }

   
}
