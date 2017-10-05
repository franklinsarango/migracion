/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.MovimientoDao;
import ec.gob.arcom.migracion.modelo.Activo;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.Movimiento;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class MovimientoDaoEjb extends GenericDaoEjbEl<Movimiento, Long> implements MovimientoDao {
    public MovimientoDaoEjb() {
        super(Movimiento.class);
    }

    @Override
    public List<Movimiento> listaMovimientos(Activo activo) {
        try {
            Query query= em.createQuery("SELECT m FROM Movimiento m WHERE m.fkActivo = :activo ORDER BY m.id");
            query.setParameter("activo", activo);            
            List<Movimiento> listaFinal = query.getResultList();
            for (Movimiento m : listaFinal) {
                this.refresh(m);
            }
            return listaFinal;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public void actualizar(Movimiento movimiento) {
        try {
            String sql = "UPDATE\n"
                    + "    inventario.movimiento\n"
                    + "SET\n";
            if (movimiento.getFkActivo()!= null) {
                sql = sql + "    fk_activo = " + movimiento.getFkActivo().getId() + ",\n";
            } else {
                sql = sql + "    fk_activo = null ,\n";
            }
            if (movimiento.getFecha() != null) {
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                sql = sql + "    fecha = '" + formatoDelTexto.format(movimiento.getFecha()) + "',\n";
            } else {
                sql = sql + "    fecha = null" + ",\n";
            }
            if (movimiento.getObservacion()!= null) {
                sql = sql + "    observacion = '" + movimiento.getObservacion() + "',\n";
            }else{
                sql = sql + "    observacion = null,\n";
            }                
            if (movimiento.getPersonaEntrega()!= null) {
                sql = sql + "    persona_entrega = '" + movimiento.getPersonaEntrega()+ "',\n";
            }else{
                sql = sql + "    persona_entrega = null,\n";
            }
            if (movimiento.getPersonaRecibe()!= null) {
                sql = sql + "    persona_recibe = '" + movimiento.getPersonaRecibe() + "'\n";
            }else{
                sql = sql + "    persona_recibe = null\n";
            }

            sql = sql + "WHERE id = " + movimiento.getId() ;

            System.out.println("sql actualiza actuvo: " + sql);

            Query query = em.createNativeQuery(sql);
            query.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    @Override
    public Long getMaxId() {
        try {
            Query query= em.createQuery("SELECT max(m.id) FROM Movimiento m ");                            
            return (Long)query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

 
}
