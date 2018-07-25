/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.modelo.Licencia;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.dao.LicenciaDao;
import ec.gob.arcom.migracion.dto.LicenciaVacacionDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.Departamento;
import ec.gob.arcom.migracion.modelo.Localidad;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class LicenciaDaoEjb extends GenericDaoEjbEl<Licencia, Long> implements LicenciaDao {
    public LicenciaDaoEjb() {
        super(Licencia.class);
    }

    @Override
    public String obtenerDiasDisponibles(Long codigoUsuario, String fechaSalida, String fechaRetorno, Long codigoLicencia) {
        if(codigoLicencia==null) {
            codigoLicencia=-1L;
        }
        try {
            Query query= em.createNativeQuery("select catmin.get_dias_disponibles_vacaciones(" + codigoUsuario + ",'" + fechaSalida + "','" + fechaRetorno + "','" + codigoLicencia + "')");
            return (String)query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic, Contrato cont where lic.estadoRegistro= :estado and lic.codigo_contrato.codigoContrato = cont.codigoContrato and cont.estado_contrato = :estadoContrato and lic.usuario.codigoUsuario= :codigoUsuario and lic.estadoLicencia <> :estadoLicencia order by lic.numeroSolicitud DESC");
            query.setParameter("estado", true);
            query.setParameter("codigoUsuario", codigoUsuario);
            query.setParameter("estadoLicencia", estadoLicencia);
            query.setParameter("estadoContrato", estadoContrato);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Licencia> listarTareasJefePorDepartamento(Long jefatura, CatalogoDetalle estadoLicencia) {
        try {
            //Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.usuario.departamento.codigoDepartamento= :jefatura and lic.estadoLicencia= :estadoLicencia order by lic.numeroSolicitud ASC");
            Query query= em.createQuery("Select lic from Licencia lic, Contrato c where lic.estadoRegistro= :estado and c.estadoRegistro= :estado and c.usuario.codigoUsuario= lic.usuario.codigoUsuario and c.departamento.codigoDepartamento= :jefatura and lic.estadoLicencia= :estadoLicencia order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);
            query.setParameter("jefatura", jefatura);
            query.setParameter("estadoLicencia", estadoLicencia);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<LicenciaVacacionDto> listarTareasJefe(Long codigoJefe, String nemonico, CatalogoDetalle estadoContrato) {
        String sql1 = "";
        sql1 += "select codigo_licencia,\n" +
                "numero_solicitud,\n" +
                "upper(u.nombre || ' ' || u.apellido) as funcionario,\n" +
                "d.nombre as \"unidad_administrativa\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_formulario) as \"tipo_formulario\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_tipo_licencia) as \"motivo\",\n" +
                "cat.nombre as \"estado\",\n" +
                "fecha_solicitud,\n" +
                "dias_licencia, cat_cargo.nombre\n" +
                "from arcom.licencia lic, catmin.catalogo_detalle cat, arcom.contrato c, arcom.departamento d, catmin.usuario u, catmin.catalogo_detalle cat_cargo\n" +
                "where lic.estado_registro=true and lic.codigo_contrato = c.codigo_contrato and c.estado_contrato = "+estadoContrato.getCodigoCatalogoDetalle()+" and lic.estado_licencia=cat.codigo_catalogo_detalle and c.codigo_tipo_cargo = cat_cargo.codigo_catalogo_detalle and lic.codigo_usuario=u.codigo_usuario and lic.codigo_usuario=c.codigo_usuario\n" +
                "and cat.nemonico='" + nemonico + "'\n" +
                "and c.estado_registro=true\n" +
                "and d.codigo_departamento=c.codigo_departamento and d.codigo_jefe=" + codigoJefe + " order by lic.numero_solicitud desc;";
        Query query = em.createNativeQuery(sql1);
        List<Object[]> listaTmp = query.getResultList();
        List<LicenciaVacacionDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            LicenciaVacacionDto li = new LicenciaVacacionDto();
            li.setCodigoLicencia((Long)fila[0]);
            li.setNumeroSolicitud((Long)fila[1]);
            li.setFuncionario(fila[2] != null ? fila[2].toString() : "");
            li.setUnidadAdministrativa(fila[3] != null ? fila[3].toString() : "");
            li.setTipoFormulario(fila[4] != null ? fila[4].toString() : "");
            li.setMotivo(fila[5] != null ? fila[5].toString() : "");
            li.setEstado(fila[6] != null ? fila[6].toString() : "");
            li.setFechaSolicitud((Date)fila[7]);
            li.setDiasLicencia(fila[8] != null ? fila[8].toString() : "");
            li.setNombre_cargo(fila[9] != null ? fila[9].toString() : "");
            listaFinal.add(li);
        }
        return listaFinal;
    }

    @Override
    public List<Licencia> listarTareasFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.usuario.codigoUsuario= :codigoUsuario and lic.estadoLicencia= :estadoLicencia order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);
            query.setParameter("codigoUsuario", codigoUsuario);
            query.setParameter("estadoLicencia", estadoLicencia);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Licencia> listarTareasTH(CatalogoDetalle estadoLicencia) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.estadoLicencia= :estadoLicencia order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);
            query.setParameter("estadoLicencia", estadoLicencia);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, List<CatalogoDetalle> estadosExcluir) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.usuario.codigoUsuario= :codigoUsuario and lic.estadoLicencia not in :estadosExcluir order by lic.numeroSolicitud DESC");
            query.setParameter("estado", true);
            query.setParameter("codigoUsuario", codigoUsuario);
            query.setParameter("estadosExcluir", estadosExcluir);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<LicenciaVacacionDto> listarTramitesAtendidos(Long codigoUsuario) {
        try {            
            String sql1 = "Select * from arcom.licencia lic where lic.estado_registro= true and lic.codigo_usuario_aprobacion = "+codigoUsuario+" order by lic.numero_solicitud asc;";
            Query query = em.createNativeQuery(sql1);
            List<Object[]> listaTmp = query.getResultList();
            List<LicenciaVacacionDto> listaFinal = new ArrayList<>();

            for (Object[] fila : listaTmp) {
                LicenciaVacacionDto li = new LicenciaVacacionDto();
                li.setCodigoLicencia((Long)fila[0]);
                li.setNumeroSolicitud((Long)fila[1]);
                li.setFuncionario(fila[2] != null ? fila[2].toString() : "");
                li.setUnidadAdministrativa(fila[3] != null ? fila[3].toString() : "");
                li.setTipoFormulario(fila[4] != null ? fila[4].toString() : "");
                li.setMotivo(fila[5] != null ? fila[5].toString() : "");
                li.setEstado(fila[6] != null ? fila[6].toString() : "");
                li.setFechaSolicitud((Date)fila[7]);
                li.setDiasLicencia(fila[8] != null ? fila[8].toString() : "");
                li.setNombre_cargo(fila[9] != null ? fila[9].toString() : "");
                listaFinal.add(li);
            }
            return listaFinal;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }    
        return null;
    }

@Override
    public List<LicenciaVacacionDto> listarTramitesAtendidosTH(CatalogoDetalle estadoLicencia, String codigoLicencia, String documento, String nombre, String departamento) {
        try {
            if(codigoLicencia.equals("")){codigoLicencia = "-1";}
            if (documento.equals("")){documento = "-1";}            
            if (!nombre.equals(""))
            {
                nombre = nombre.toUpperCase();
            }
            if (departamento.equals("")){departamento = "-1";}            
            String sql1 = "select lic.codigo_licencia, lic.numero_solicitud, upper(u.nombre || ' ' || u.apellido) as funcionario, d.nombre as unidad_administrativa,\n" +
            "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_formulario) as tipo_formulario,\n" +
            "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_tipo_licencia) as motivo,\n" +
            "cat.nombre as estado, lic.fecha_solicitud, lic.dias_licencia\n" +
            "from arcom.licencia lic, catmin.usuario u, arcom.departamento d, arcom.contrato c, catmin.catalogo_detalle cat\n" +
            "where lic.estado_registro= true \n" +
            "and lic.estado_licencia  = cat.codigo_catalogo_detalle\n" +
            "and lic.estado_licencia= "+estadoLicencia.getCodigoCatalogoDetalle()+" \n" +
            "and d.codigo_departamento = c.codigo_departamento\n" +
            "and c.codigo_usuario = lic.codigo_usuario\n" +
            "and lic.codigo_usuario = u.codigo_usuario\n" +
            "and (-1 = "+codigoLicencia+" or lic.numero_solicitud = "+codigoLicencia+") \n" +
            "and (-1 = "+documento+" or u.numero_documento = '"+documento+"') \n" +
            "and (-1 = "+departamento+" or d.codigo_departamento = "+departamento+") \n" +
            "and ('noaplica' = '"+nombre+"' or upper(concat(u.nombre,' ',u.apellido)) like '%"+nombre+"%')  \n" +
            "order by lic.numero_solicitud desc;";
            
            Query query = em.createNativeQuery(sql1);
            List<Object[]> listaTmp = query.getResultList();
            List<LicenciaVacacionDto> listaFinal = new ArrayList<>();
            for (Object[] fila : listaTmp) {
                LicenciaVacacionDto li = new LicenciaVacacionDto();
                li.setCodigoLicencia((Long)fila[0]);
                li.setNumeroSolicitud((Long)fila[1]);
                li.setFuncionario(fila[2] != null ? fila[2].toString() : "");
                li.setUnidadAdministrativa(fila[3] != null ? fila[3].toString() : "");
                li.setTipoFormulario(fila[4] != null ? fila[4].toString() : "");
                li.setMotivo(fila[5] != null ? fila[5].toString() : "");
                li.setEstado(fila[6] != null ? fila[6].toString() : "");
                li.setFechaSolicitud((Date)fila[7]);
                li.setDiasLicencia(fila[8] != null ? fila[8].toString() : ""); 
                Licencia lic = new Licencia();
                lic.setCodigoLicencia(li.getCodigoLicencia());
                li.setLicencia(lic);
                listaFinal.add(li);
            }
            return listaFinal;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }    
        return null;
    }

    @Override
    public void inicializarVacaciones(Long codigoUsuario, BigDecimal saldoVacacionContrato) {
        try {
            Query query= em.createNativeQuery("select catmin.set_inicializa_vacaciones(" + codigoUsuario + "," + saldoVacacionContrato + ")");
            query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
    }

    @Override
    public List<Licencia> listarLicenciasFinalizadasPorFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato) {
        try {
           
            Query query= em.createQuery("Select lic from Licencia lic, Contrato cont where lic.estadoRegistro= :estado and lic.usuario.codigoUsuario= :codigo and lic.codigo_contrato.codigoContrato = cont.codigoContrato and cont.estado_contrato= :estadoContrato order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);            
            query.setParameter("codigo", codigoUsuario);
            query.setParameter("estadoContrato", estadoContrato);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<LicenciaVacacionDto> listarTareas(Long codigoUsuario, String nemonico, CatalogoDetalle estadoContrato , String codigoLicencia, String documento, String nombre, int departamento) {
        if(codigoUsuario == null){
            codigoUsuario = -1L;
        }        
        if(codigoLicencia.equals("")){codigoLicencia = "-1";}
        if (documento.equals("")){documento = "-1";}    
        if (nombre.equals("noaplica")){nombre = "noaplica";}
        if (departamento == 0){departamento = -1;} 
        String sql1 = "";
        sql1 += "select codigo_licencia,\n" +
                "numero_solicitud,\n" +
                "upper(u.nombre || ' ' || u.apellido) as funcionario,\n" +
                "(select d.nombre from arcom.departamento d, arcom.contrato c where c.estado_registro=true and d.codigo_departamento=c.codigo_departamento and c.codigo_usuario=lic.codigo_usuario) as \"unidad_administrativa\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_formulario) as \"tipo_formulario\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_tipo_licencia) as \"motivo\",\n" +
                "cat.nombre as \"estado\",\n" +
                "fecha_solicitud,\n" +
                "dias_licencia, cat_cargo.nombre\n" +
                "from arcom.licencia lic, catmin.catalogo_detalle cat, catmin.usuario u, arcom.contrato c, catmin.catalogo_detalle cat_cargo, arcom.departamento d\n" +
                "where lic.estado_licencia  = cat.codigo_catalogo_detalle and lic.codigo_contrato = c.codigo_contrato and c.estado_contrato = "+ estadoContrato.getCodigoCatalogoDetalle() +"\n" +
                "and (-1 = " + codigoUsuario + " or u.codigo_usuario = " + codigoUsuario + ") \n" +
                "and cat.nemonico='" + nemonico + "' and lic.estado_registro=true and lic.codigo_usuario=u.codigo_usuario and c.codigo_tipo_cargo = cat_cargo.codigo_catalogo_detalle and c.codigo_departamento = d.codigo_departamento and (-1 = "+codigoLicencia+" or lic.numero_solicitud = "+codigoLicencia+")and (-1 = "+documento+" or u.numero_documento = '"+documento+"') and (-1 = "+departamento+" or d.codigo_departamento = "+departamento+") and ('noaplica' = '"+nombre+"' or upper(concat(u.nombre,' ',u.apellido)) like '%"+nombre+"%') order by lic.codigo_licencia desc";
        Query query = em.createNativeQuery(sql1);
        List<Object[]> listaTmp = query.getResultList();
        List<LicenciaVacacionDto> listaFinal = new ArrayList<>();
        
        for (Object[] fila : listaTmp) {
            LicenciaVacacionDto li = new LicenciaVacacionDto();
            li.setCodigoLicencia((Long)fila[0]);
            li.setNumeroSolicitud((Long)fila[1]);
            li.setFuncionario(fila[2] != null ? fila[2].toString() : "");
            li.setUnidadAdministrativa(fila[3] != null ? fila[3].toString() : "");
            li.setTipoFormulario(fila[4] != null ? fila[4].toString() : "");
            li.setMotivo(fila[5] != null ? fila[5].toString() : "");
            li.setEstado(fila[6] != null ? fila[6].toString() : "");
            li.setFechaSolicitud((Date)fila[7]);
            li.setDiasLicencia(fila[8] != null ? fila[8].toString() : "");
            li.setNombre_cargo(fila[9] != null ? fila[9].toString() : "");
            listaFinal.add(li);
        }
        return listaFinal;
    }

    @Override
    public BigDecimal obtenerSaldoFinal(Contrato contrato) {
    
        try {
            Query query= em.createNativeQuery("select catmin.get_saldo_vacaciones_contrato(" + contrato.getUsuario().getCodigoUsuario() 
                    + ",'" + contrato.getCodigoContrato() + "','" + contrato.getFechaSalida() + "')");
            BigDecimal saldo= new BigDecimal((String)query.getSingleResult());
            System.out.println("EL SALDO FINAL ES: " + saldo);
            return saldo;
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
