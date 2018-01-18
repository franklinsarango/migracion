/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.LicenciaDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Licencia;
import javax.ejb.Stateless;
import ec.gob.arcom.migracion.servicio.LicenciaServicio;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author mejiaw
 */
@Stateless(name = "LicenciaServicio")
public class LicenciaServicioImpl extends GenericServiceImpl<Licencia, Long> implements LicenciaServicio {
    @EJB
    private LicenciaDao licenciaDao;
    
    @Override
    public GenericDao<Licencia, Long> getDao() {
        return licenciaDao;
    }

    @Override
    public String obtenerDiasDisponibles(Long codigoUsuario, String fechaSalida, String fechaRetorno) {
        return licenciaDao.obtenerDiasDisponibles(codigoUsuario, fechaSalida, fechaRetorno);
    }

    @Override
    public String obtenerPeriodos(Long codigoUsuario, Long dias) {
        return licenciaDao.obtenerPeriodos(codigoUsuario, dias);
    }

    @Override
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarSolicitudesExcluyendoEstado(codigoUsuario, estadoLicencia);
    }

    @Override
    public List<Licencia> listarTareasJefe(Long jefatura, CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarTareasJefe(jefatura, estadoLicencia);
    }

    @Override
    public List<Licencia> listarTareasFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarTareasFuncionario(codigoUsuario, estadoLicencia);
    }

    @Override
    public List<Licencia> listarTareasTH(CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarTareasTH(estadoLicencia);
    }

    @Override
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, List<CatalogoDetalle> estadosExcluir) {
        return licenciaDao.listarSolicitudesExcluyendoEstado(codigoUsuario, estadosExcluir);
    }

    @Override
    public List<Licencia> listarTramitesAtendidos(Long codigoUsuario) {
        return licenciaDao.listarTramitesAtendidos(codigoUsuario);
    }

    @Override
    public List<Licencia> listarTramitesAtendidosTH(CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarTramitesAtendidosTH(estadoLicencia);
    }

    @Override
    public void inicializarVacaciones(Long codigoUsuario, BigDecimal saldoVacacionContrato) {
        licenciaDao.inicializarVacaciones(codigoUsuario, saldoVacacionContrato);
    }

    @Override
    public List<Licencia> listarLicenciasFinalizadasPorFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarLicenciasFinalizadasPorFuncionario(codigoUsuario, estadoLicencia);
    }
    
}
