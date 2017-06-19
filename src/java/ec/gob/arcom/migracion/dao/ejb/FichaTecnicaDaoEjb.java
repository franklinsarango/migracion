/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.FichaTecnicaDao;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class FichaTecnicaDaoEjb extends GenericDaoEjbEl<FichaTecnica, Long> implements FichaTecnicaDao {
    public FichaTecnicaDaoEjb() {
        super(FichaTecnica.class);
    }

    @Override
    public List<FichaTecnica> listar() {
        try {
            Query query= em.createQuery("Select ficha from FichaTecnica ficha where ficha.estadoRegistro= :estado order by ficha.fechaCreacion ASC");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<FichaTecnica> listarPorUsuarioCreacion(Long codigoUsuario) {
        try {
            Query query= em.createQuery("Select ficha from FichaTecnica ficha where ficha.estadoRegistro= :estado and ficha.usuarioCreacion.codigoUsuario= :codigo order by ficha.fechaCreacion ASC");
            query.setParameter("estado", true);
            query.setParameter("codigo", codigoUsuario);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }
}
