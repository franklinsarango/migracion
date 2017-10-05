/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.CatalogoInvDao;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.CatalogoInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class CatalogoInvDaoEjb extends GenericDaoEjbEl<CatalogoInv, Long> implements CatalogoInvDao {
    public CatalogoInvDaoEjb() {
        super(CatalogoInv.class);
    }

    @Override
    public List<CatalogoInv> findByNemonico(String nemonico) {
        try {
            String sql = "SELECT * FROM inventario.catalogo_inv WHERE fk_catalogo =(SELECT id FROM inventario.catalogo_inv WHERE \n";
            sql = sql + " nemonico = '" + nemonico + "') ORDER BY nombre ";
            
            System.out.println("sql: " + sql);
            Query query = em.createNativeQuery(sql);
            List<Object[]> listaTmp = query.getResultList();
            List<CatalogoInv> listaFinal = new ArrayList<>();

            for (Object[] fila : listaTmp) {
                CatalogoInv catalogo_ = new CatalogoInv();
                catalogo_.setId(Long.valueOf(fila[0].toString()));                
                catalogo_.setFkCatalogo(new BigInteger(fila[1].toString()));                
                catalogo_.setNombre(fila[2].toString());
                catalogo_.setDescripcion(fila[3].toString());
                if (fila[4] != null) {
                    catalogo_.setNemonico(fila[4].toString());
                }
                catalogo_.setVigencia(new BigInteger(fila[5].toString()));              

                listaFinal.add(catalogo_);
            }

//            for (Activo a : listaFinal) {
//                this.refresh(a);
//            }
            return listaFinal;
            
//            Query query= em.createQuery("SELECT c FROM CatalogoInv c WHERE c.fkCatalogo = (SELECT ci.id FROM CatalogoInv ci WHERE ci.nemonico = :nemonico) ORDER BY c.nombre");            
//            query.setParameter("nemonico", nemonico);     
            
//            List<CatalogoInv> listaFinal = query.getResultList();
//            for (CatalogoInv c : listaFinal) {
//                this.refresh(c);
//            }
//            return listaFinal;
                        
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public Long getMaxId() {
        try {
            Query query= em.createQuery("SELECT max(c.id) FROM CatalogoInv c ");                            
            return (Long)query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
    
}
