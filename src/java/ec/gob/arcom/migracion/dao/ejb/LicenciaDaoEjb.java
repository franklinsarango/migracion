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
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.usuario.codigoUsuario= :codigoUsuario and lic.estadoLicencia <> :estadoLicencia order by lic.numeroSolicitud DESC");
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
    public List<LicenciaVacacionDto> listarTareasJefe(Long codigoJefe, String nemonico) {
        String sql1 = "";
        sql1 += "select codigo_licencia,\n" +
                "numero_solicitud,\n" +
                "upper(u.nombre || ' ' || u.apellido) as funcionario,\n" +
                "d.nombre as \"unidad_administrativa\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_formulario) as \"tipo_formulario\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_tipo_licencia) as \"motivo\",\n" +
                "cat.nombre as \"estado\",\n" +
                "fecha_solicitud,\n" +
                "dias_licencia\n" +
                "from arcom.licencia lic, catmin.catalogo_detalle cat, arcom.contrato c, arcom.departamento d, catmin.usuario u\n" +
                "where lic.estado_registro=true and lic.estado_licencia=cat.codigo_catalogo_detalle and lic.codigo_usuario=u.codigo_usuario and lic.codigo_usuario=c.codigo_usuario\n" +
                "and cat.nemonico='" + nemonico + "'\n" +
                "and c.estado_registro=true\n" +
                "and d.codigo_departamento=c.codigo_departamento and d.codigo_jefe=" + codigoJefe + ";";
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
    public List<Licencia> listarTramitesAtendidos(Long codigoUsuario) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.usuarioAprobacion.codigoUsuario= :codigoUsuario order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);
            query.setParameter("codigoUsuario", codigoUsuario);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<Licencia> listarTramitesAtendidosTH(CatalogoDetalle estadoLicencia) {
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
    public void inicializarVacaciones(Long codigoUsuario, BigDecimal saldoVacacionContrato) {
        try {
            Query query= em.createNativeQuery("select catmin.set_inicializa_vacaciones(" + codigoUsuario + "," + saldoVacacionContrato + ")");
            query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
    }

    @Override
    public List<Licencia> listarLicenciasFinalizadasPorFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        try {
            Query query= em.createQuery("Select lic from Licencia lic where lic.estadoRegistro= :estado and lic.estadoLicencia= :estadoLicencia and lic.usuario.codigoUsuario= :codigo order by lic.numeroSolicitud ASC");
            query.setParameter("estado", true);
            query.setParameter("estadoLicencia", estadoLicencia);
            query.setParameter("codigo", codigoUsuario);
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<LicenciaVacacionDto> listarTareas(Long codigoUsuario, String nemonico) {
        if(codigoUsuario == null){
            codigoUsuario = -1L;
        }
        String sql1 = "";
        sql1 += "select codigo_licencia,\n" +
                "numero_solicitud,\n" +
                "upper(u.nombre || ' ' || u.apellido) as funcionario,\n" +
                "(select d.nombre from arcom.departamento d, arcom.contrato c where c.estado_registro=true and d.codigo_departamento=c.codigo_departamento and c.codigo_usuario=lic.codigo_usuario) as \"unidad_administrativa\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_formulario) as \"tipo_formulario\",\n" +
                "(select cat.nombre from catmin.catalogo_detalle cat where cat.codigo_catalogo_detalle=lic.codigo_tipo_licencia) as \"motivo\",\n" +
                "cat.nombre as \"estado\",\n" +
                "fecha_solicitud,\n" +
                "dias_licencia\n" +
                "from arcom.licencia lic, catmin.catalogo_detalle cat, catmin.usuario u\n" +
                "where lic.estado_licencia  = cat.codigo_catalogo_detalle\n" +
                "and (-1 = " + codigoUsuario + " or u.codigo_usuario = " + codigoUsuario + ") \n" +
                "and cat.nemonico='" + nemonico + "' and lic.estado_registro=true and lic.codigo_usuario=u.codigo_usuario;";
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
