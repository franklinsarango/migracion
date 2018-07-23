/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.dto.LicenciaVacacionDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Licencia;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface LicenciaServicio extends GenericService<Licencia, Long> {

    public String obtenerDiasDisponibles(Long codigoUsuario, String fechaSalida, String fechaRetorno, Long codigoLicencia);

    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato);

    public List<Licencia> listarTareasJefePorDepartamento(Long jefatura, CatalogoDetalle estadoLicencia);
    
    public List<LicenciaVacacionDto> listarTareasJefe(Long codigoJefe, String nemonico, CatalogoDetalle estadoContrato);    

    public List<Licencia> listarTareasFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia);

    public List<Licencia> listarTareasTH(CatalogoDetalle estadoLicencia);
    
    public List<LicenciaVacacionDto> listarTareas(Long codigoUsuario, String nemonico, CatalogoDetalle estadoContrato, String codigoLicencia, String documento, String nombre, int departamento);

    public List<Licencia> listarSolicitudesExcluyendoEstado(Long codigoUsuario, List<CatalogoDetalle> estadosExcluir);

    public List<LicenciaVacacionDto> listarTramitesAtendidos(Long codigoUsuario);

    public List<LicenciaVacacionDto> listarTramitesAtendidosTH(CatalogoDetalle estadoLicencia, String codigoLicencia, String documento, String nombre, String departamento);

    public void inicializarVacaciones(Long codigoUsuario, BigDecimal saldoVacacionContrato);

    public List<Licencia> listarLicenciasFinalizadasPorFuncionario(Long codigoUsuario, CatalogoDetalle estadoLicencia, CatalogoDetalle estadoContrato);

    public BigDecimal obtenerSaldoFinal(Contrato contrato);
    
}
