/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ContratoDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class ContratoDaoEjb extends GenericDaoEjbEl<Contrato, Long> implements ContratoDao {
    public ContratoDaoEjb() {
        super(Contrato.class);
    }

    @Override
    public Contrato contratoUsuarioEstado(Usuario usuario, Long estadoContrato) {
        try {
            CatalogoDetalle estadoCont = new CatalogoDetalle();
            estadoCont.setCodigoCatalogoDetalle(estadoContrato);
            Query query= em.createQuery("Select c from Contrato c where c.estadoRegistro= :estado and c.usuario= :usuario and c.estado_contrato= :estadoContrato order by c.codigoContrato ASC");
            query.setParameter("estado", true);
            query.setParameter("usuario", usuario);
            query.setParameter("estadoContrato", estadoCont);
            return (Contrato) query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<Contrato> listaContratoUsuarioEstado(Usuario usuario) {
        try {
            Query query= em.createQuery("Select c from Contrato c where c.estadoRegistro= :estado and c.usuario= :usuario order by c.codigoContrato ASC");
            query.setParameter("estado", true);
            query.setParameter("usuario", usuario);            
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Contrato> listar() {
        try {
            Query query= em.createQuery("Select c from Contrato c where c.estadoRegistro= :estado order by c.codigoContrato ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
