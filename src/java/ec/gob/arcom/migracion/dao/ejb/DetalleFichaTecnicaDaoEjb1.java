/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.DetalleFichaTecnicaDao;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class DetalleFichaTecnicaDaoEjb1 extends GenericDaoEjbEl<DetalleFichaTecnica, Long> implements DetalleFichaTecnicaDao {
    public DetalleFichaTecnicaDaoEjb1() {
        super(DetalleFichaTecnica.class);
    }

    @Override
    public List<DetalleFichaTecnica> listar() {
        try {
            Query query= em.createQuery("Select detalleFicha from DetalleFichaTecnica detalleFicha where detalleFicha.estadoRegistro= :estado");
            query.setParameter("estado", true);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List listarPorFichaTecnica(Long codigoFichaTecnica) {
        try {
            Query query= em.createQuery("Select detalleFicha from DetalleFichaTecnica detalleFicha where detalleFicha.estadoRegistro= :estado and detalleFicha.fichaTecnica.codigoFichaTecnica= :codigo");
            query.setParameter("estado", true);
            query.setParameter("codigo", codigoFichaTecnica);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error:");
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<DetalleFichaTecnica> listarSociosPorFichaTecnica(Long codigoFichaTecnica) {
        try {
            Query query= em.createQuery("Select detalleFicha from DetalleFichaTecnica detalleFicha where detalleFicha.estadoRegistro= :estado and detalleFicha.fichaTecnica.codigoFichaTecnica= :codigo and detalleFicha.codigoTipoInformacionRegistro.nemonico= :nemonico");
            query.setParameter("estado", true);
            query.setParameter("codigo", codigoFichaTecnica);
            query.setParameter("nemonico", "TIPOINFSOCLAB");
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al listar los socios:");
            System.out.println(ex.toString());
        }
        return null;
    }
}
