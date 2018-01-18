/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ContratoDao;
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
    public List<Contrato> listarPorUsuario(Usuario usuario) {
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
}
