/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.LicenciaDao;
import ec.gob.arcom.migracion.dto.LicenciaVacacionDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Contrato;
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
    public String obtenerDiasDisponibles(Long codigoUsuario, String fechaSalida, String fechaRetorno, Long codigoLicencia) {
        return licenciaDao.obtenerDiasDisponibles(codigoUsuario, fechaSalida, fechaRetorno, codigoLicencia);
    }

    @Override
    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato) {    
        return licenciaDao.listarSolicitudesExcluyendoEstado(codigoUsuario, estadoLicencia, estadoContrato);
    }

    @Override
    public List<Licencia> listarTareasJefePorDepartamento(Long jefatura, CatalogoDetalle estadoLicencia) {
        return licenciaDao.listarTareasJefePorDepartamento(jefatura, estadoLicencia);
    }
    
    @Override
    public List<LicenciaVacacionDto> listarTareasJefe(Long codigoJefe, String nemonico, CatalogoDetalle estadoContrato) {
        return licenciaDao.listarTareasJefe(codigoJefe, nemonico, estadoContrato);
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
    public List<LicenciaVacacionDto> listarTramitesAtendidos(Long codigoUsuario) {
        return licenciaDao.listarTramitesAtendidos(codigoUsuario);
    }

    @Override
    public List<LicenciaVacacionDto> listarTramitesAtendidosTH(CatalogoDetalle estadoLicencia, String codigoLicencia, String documento, String nombre, String departamento) {
        return licenciaDao.listarTramitesAtendidosTH(estadoLicencia, codigoLicencia, documento, nombre, departamento);
    }

    @Override
    public void inicializarVacaciones(Long codigoUsuario, BigDecimal saldoVacacionContrato) {
        licenciaDao.inicializarVacaciones(codigoUsuario, saldoVacacionContrato);
    }

    @Override
    public List<Licencia> listarLicenciasFinalizadasPorFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato) {
        return licenciaDao.listarLicenciasFinalizadasPorFuncionario(codigoUsuario, estadoLicencia, estadoContrato);
    }

    @Override
    public List<LicenciaVacacionDto> listarTareas(Long codigoUsuario, String nemonico, CatalogoDetalle estadoContrato, String codigoLicencia, String documento, String nombre, int departamento) {
        return licenciaDao.listarTareas(codigoUsuario, nemonico, estadoContrato, codigoLicencia, documento, nombre, departamento);
    }

    @Override
    public BigDecimal obtenerSaldoFinal(Contrato contrato) {
        return licenciaDao.obtenerSaldoFinal(contrato);
    }
    
}
