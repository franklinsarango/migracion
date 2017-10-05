/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.ActivoDao;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.modelo.Activo;
import ec.gob.arcom.migracion.modelo.CatalogoInv;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import ec.gob.arcom.migracion.modelo.RegionalInv;
import ec.gob.arcom.migracion.servicio.CatalogoInvServicio;
import ec.gob.arcom.migracion.servicio.RegionalInvServicio;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless
public class ActivoDaoEjb extends GenericDaoEjbEl<Activo, Long> implements ActivoDao {
    
//    @EJB
//    private CatalogoInvServicio catalogoInvServicio;
//    @EJB
//    private RegionalInvServicio regionalInvServicio;
    
    public ActivoDaoEjb() {
        super(Activo.class);
    }
    
    @Override
    public int listaActivosTotal(Long filtroId, String filtroDescripcion, String filtroTagArcom) {        
        Long intId = -1L;
        String strDescripcion = "-1";
        String strTagArcom = "-1";        
        int count = 0;

        List<Activo> lista = new ArrayList<>();

        intId = filtroId == null || filtroId.equals(-1L) || filtroId.equals(0L) ? -1L : filtroId;
        strDescripcion = filtroDescripcion == null || filtroDescripcion.isEmpty() ? "-1" : filtroDescripcion;
        strTagArcom = filtroTagArcom == null || filtroTagArcom.isEmpty() ? "-1" : filtroTagArcom;

        try {   
            //SE CONSULTA EL TOTAL DE REGISTROS
            String sql = "select a.id  from inventario.activo a where \n";
            sql = sql + "(-1 = " + intId + " or a.id = " + intId + ") \n";
            sql = sql + "and ('-1' = '" + strDescripcion + "' or a.descripcion like '%" + strDescripcion + "%') \n";
            sql = sql + "and ('-1' = '" + strTagArcom + "' or a.tag_arcom like '%" + strTagArcom + "%') \n";           

            System.out.println("sql count activo: " + sql);

            Query query = em.createNativeQuery(sql);
            count = query.getResultList().size();
                       
            
//            String sql = "SELECT a.id FROM Activo a WHERE (-1 = :intId or a.id = :intId) "
//                    + " and ('-1' = :strDescripcion or a.descripcion like :strDescripcion)"
//                    + " and ('-1' = :strTagArcom or a.tagArcom like :strTagArcom)";
//            Query query= em.createQuery(sql);
//            query.setParameter("intId", intId);
//            query.setParameter("strDescripcion", strDescripcion);
//            query.setParameter("strTagArcom", strTagArcom);                              
//            System.out.println("sql concesion: " + sql);
//            count = query.getResultList().size();                                             
                       
        } catch (Exception e) {
            System.out.println("Captura de error: " + e);
            e.printStackTrace();
        }
        return count;
    }
    
    
    @Override
    public List<Activo> listaActivos(int tamanoPagina, int desplazamiento, Long filtroId, String filtroDescripcion, String filtroTagArcom) {

        Long intId = -1L;
        String strDescripcion = "-1";
        String strTagArcom = "-1";
        List<Activo> lista = new ArrayList<>();

        intId = filtroId == null || filtroId.equals(-1L) || filtroId.equals(0L) ? -1L : filtroId;
        strDescripcion = filtroDescripcion == null || filtroDescripcion.isEmpty() ? "-1" : filtroDescripcion;
        strTagArcom = filtroTagArcom == null || filtroTagArcom.isEmpty() ? "-1" : filtroTagArcom;

        try {
            String sql = "select a.*, \n"
                    + "(select nombre from inventario.catalogo_inv where id = a.fk_tipoactivo) as tipo_activo_nombre, \n"
                    + "(select nombre from inventario.catalogo_inv where id = a.fk_marca) as marca_nombre, \n"
                    + "(select nombre from inventario.catalogo_inv where id = a.fk_modelo) as modelo_nombre, \n"
                    + "(select nombre from inventario.regional_inv where id = a.fk_regional) as regional_nombre, \n"
                    + "(select nombre from inventario.catalogo_inv where id = a.fk_ubicacion) as ubicacion_nombre, \n"
                    + "(select nombre from inventario.catalogo_inv where id = a.fk_estado) as estado_nombre \n"
                    + " from inventario.activo a where \n";
            sql = sql + "(-1 = " + intId + " or a.id = " + intId + ") \n";
            sql = sql + "and ('-1' = '" + strDescripcion + "' or a.descripcion like '%" + strDescripcion + "%') \n";
            sql = sql + "and ('-1' = '" + strTagArcom + "' or a.tag_arcom like '%" + strTagArcom + "%') \n";
            sql = sql + "order by a.id desc LIMIT " + tamanoPagina + " OFFSET " + desplazamiento + " \n";

            System.out.println("sql count activo: " + sql);

            Query query = em.createNativeQuery(sql);
            List<Object[]> listaTmp = query.getResultList();
            List<Activo> listaFinal = new ArrayList<>();

            for (Object[] fila : listaTmp) {
                Activo a = new Activo();
                a.setId(Long.valueOf(fila[0].toString()));

                CatalogoInv tipoActivo = new CatalogoInv();
                tipoActivo.setId(Long.valueOf(fila[1].toString()));
                a.setTipoactivo(tipoActivo);

                CatalogoInv marca = new CatalogoInv();
                marca.setId(Long.valueOf(fila[2].toString()));
                a.setMarca(marca);
                
                CatalogoInv modelo = new CatalogoInv();
                modelo.setId(Long.valueOf(fila[3].toString()));
                a.setModelo(modelo);

                a.setTagArcom(fila[4].toString());
                a.setNumSerie(fila[5].toString());
                a.setDescripcion(fila[6].toString());

                RegionalInv regional = new RegionalInv();
                regional.setId(Long.valueOf(fila[7].toString()));
                a.setRegional(regional);

                CatalogoInv ubicacion = new CatalogoInv();
                ubicacion.setId(Long.valueOf(fila[8].toString()));
                a.setUbicacion(ubicacion);
                
                CatalogoInv estado = new CatalogoInv();
                estado.setId(Long.valueOf(fila[9].toString()));
                a.setEstado(estado);

                a.setVigencia(new BigInteger(fila[10].toString()));
                
                a.getTipoactivo().setNombre(fila[11].toString());
                a.getMarca().setNombre(fila[12].toString());
                a.getModelo().setNombre(fila[13].toString());
                a.getRegional().setNombre(fila[14].toString());
                a.getUbicacion().setNombre(fila[15].toString());
                a.getEstado().setNombre(fila[16].toString());

                listaFinal.add(a);
            }

//            for (Activo a : listaFinal) {
//                this.refresh(a);
//            }
            return listaFinal;

            
//            String sql = "SELECT a FROM Activo a WHERE (-1 = :intId or a.id = :intId) "
//                    + " and ('-1' = :strDescripcion or a.descripcion like :strDescripcion)"
//                    + " and ('-1' = :strTagArcom or a.tagArcom like :strTagArcom)"
//                    + "  order by a.id LIMIT 0,1";
//            Query query_= em.createQuery(sql);
//            query_.setParameter("intId", intId);
//            query_.setParameter("strDescripcion", strDescripcion);
//            query_.setParameter("strTagArcom", strTagArcom);    
//            System.out.println("sql concesion: " + sql);
//            query_.setFirstResult(desplazamiento);
//            query_.setMaxResults(tamanoPagina);            
            
//            List<Activo> listaFinal = query_.getResultList();
//            for (Activo a : listaFinal) {
//                this.refresh(a);
//            }
//            return listaFinal;
            
        } catch (Exception e) {
            System.out.println("Captura de error: " + e);
            e.printStackTrace();
        } 

        return lista;
    }
    
    @Override
    public List<Activo> findByTagArcom(String tagArcom) {
        List<Activo> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("Select a from Activo a where a.tagArcom = :tagArcom");
            query.setParameter("tagArcom", tagArcom);
            List<Activo> listaFinal = query.getResultList();
            for (Activo a : listaFinal) {
                this.refresh(a);
            }
            return listaFinal;

        } catch (Exception e) {
            System.out.println("Captura de error: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public void actualizar(Activo activo) {
        try {
            String sql = "UPDATE\n"
                    + "    inventario.activo\n"
                    + "SET\n";
            if (activo.getTipoactivo() != null) {
                sql = sql + "    fk_tipoactivo = " + activo.getTipoactivo().getId() + ",\n";
            } else {
                sql = sql + "    fk_tipoactivo = null ,\n";
            }
            if (activo.getMarca() != null) {
                sql = sql + "    fk_marca = " + activo.getMarca().getId() + ",\n";
            } else {
                sql = sql + "    fk_marca = '" + activo.getMarca().getId() + "',\n";
            }
            if (activo.getModelo() != null) {
                sql = sql + "    fk_modelo = " + activo.getModelo().getId() + ",\n";
            } else {
                sql = sql + "    fk_modelo = '" + activo.getModelo().getId() + "',\n";
            }
            if (activo.getTagArcom() != null) {
                sql = sql + "    tag_arcom = '" + activo.getTagArcom() + "',\n";
            }
            if (activo.getNumSerie() != null) {
                sql = sql + "    num_serie = '" + activo.getNumSerie() + "',\n";
            }
            if (activo.getDescripcion() != null) {
                sql = sql + "    descripcion = '" + activo.getDescripcion() + "',\n";
            }
            if (activo.getRegional() != null) {
                sql = sql + "    fk_regional = " + activo.getRegional().getId() + ",\n";
            } else {
                sql = sql + "    fk_regional = null ,\n";
            }
            if (activo.getUbicacion() != null) {
                sql = sql + "    fk_ubicacion = " + activo.getUbicacion().getId() + ",\n";
            } else {
                sql = sql + "    fk_ubicacion = null ,\n";
            }
            if (activo.getEstado() != null) {
                sql = sql + "    fk_estado = " + activo.getEstado().getId() + ",\n";
            } else {
                sql = sql + "    fk_estado = null ,\n";
            }

            sql = sql + "    vigencia = " + activo.getVigencia() + "\n";

            sql = sql + "WHERE id = " + activo.getId() ;

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
            Query query= em.createQuery("SELECT max(a.id) FROM Activo a ");                            
            return (Long)query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
