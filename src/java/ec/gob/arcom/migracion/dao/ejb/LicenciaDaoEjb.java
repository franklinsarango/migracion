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
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import java.math.BigDecimal;
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
    public String obtenerDiasDisponibles(Long codigoUsuario, String fechaSalida, String fechaRetorno) {
        try {
            Query query= em.createNativeQuery("select catmin.get_dias_disponibles_vacaciones(" + codigoUsuario + ",'" + fechaSalida + "','" + fechaRetorno + "')");
            return (String)query.getSingleResult();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public String obtenerPeriodos(Long codigoUsuario, Long dias) {
        try {
            Query query= em.createNativeQuery("select catmin.get_dias_disponibles_vacaciones(" + codigoUsuario + "," + dias + ")");
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
    public List<Licencia> listarTareasJefe(Long jefatura, CatalogoDetalle estadoLicencia) {
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
}
