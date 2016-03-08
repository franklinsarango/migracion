/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.ConcesionMineraDao;
import ec.gob.arcom.migracion.dto.ConcesionMineraDto;
import ec.gob.arcom.migracion.dto.DerechoMineroDto;
import ec.gob.arcom.migracion.modelo.AreaMinera;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Solicitud;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AreaMineraServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.SolicitudServicio;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "ConcesionMineraServicio")
public class ConcesionMineraServicioImpl extends GenericServiceImpl<ConcesionMinera, Long>
        implements ConcesionMineraServicio {

    @EJB
    private ConcesionMineraDao concesionMineraDao;
    @EJB
    private SolicitudServicio solicitudServicio;
    @EJB
    private AreaMineraServicio areaMineraServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @Override
    public GenericDao<ConcesionMinera, Long> getDao() {
        return concesionMineraDao;
    }

    @Override
    public List<ConcesionMineraDto> obtenerRegistrosPorUsuario(String cedulaRuc, String codigoFiltro, String cedulaTitularFiltro, String nombreAreaFiltro) {
        return concesionMineraDao.obtenerRegistrosPorUsuario(cedulaRuc, codigoFiltro, cedulaTitularFiltro, nombreAreaFiltro);
    }

    @Override
    public Long obtenerSiguienteCodigoConcesion() {
        return concesionMineraDao.obtenerSiguienteCodigoConcesion();
    }

    @Override
    public void guardarTodo(ConcesionMinera concesionMinera, Solicitud solicitud, AreaMinera areaMinera, Usuario login) throws Exception {
        /*PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(concesionMinera.getDocumentoConcesionarioPrincipal());
         PersonaJuridica pj = personaJuridicaServicio.findByRuc(solicitud.getRepresentanteLegal());
         if (pn == null) {
         PersonaNatural pnNuevo = new PersonaNatural();
         pnNuevo.setNumeroDocumento(concesionMinera.getDocumentoConcesionarioPrincipal());
         if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 10) {
         CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
         pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
         } else if (concesionMinera.getDocumentoConcesionarioPrincipal().length() == 13) {
         CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
         pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
         }
         pnNuevo.setNombre(solicitud.getNombreSolicitante());
         pnNuevo.setApellido(solicitud.getApellidoSolicitante());
         pnNuevo.setEmail(solicitud.getEmailSolicitante());
         pnNuevo.setTelefono(solicitud.getTelefonoConvencionalSolicitante());
         pnNuevo.setCelular(solicitud.getTelefonoCelularSolicitante());
         pnNuevo.setDireccion(solicitud.getDireccionSolicitante());
         Localidad localidad = new Localidad();
         localidad.setCodigoLocalidad(Long.valueOf(concesionMinera.getCodigoParroquia().toString()));
         pnNuevo.setCodigoLocalidad(localidad);
         pnNuevo.setCodigoProvincia(concesionMinera.getCodigoProvincia());
         pnNuevo.setCodigoCanton(concesionMinera.getCodigoCanton());
         pnNuevo.setCodigoParroquia(concesionMinera.getCodigoParroquia());
         pnNuevo.setEstadoRegistro(true);
         pnNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
         pnNuevo.setUsuarioModificacion(BigInteger.valueOf(login.getCodigoUsuario()));
         pnNuevo.setFechaModificacion(new Date());
         pnNuevo.setCasilleroJudicial(concesionMinera.getCasilleroJudicial());
         personaNaturalServicio.create(pnNuevo);
         }
         if (pj == null) {
         PersonaJuridica pjNuevo = new PersonaJuridica();
         System.out.println("solicitud.getRepresentanteLegal(): " + solicitud.getRepresentanteLegal());
         pjNuevo.setRuc("1722733027");
         pjNuevo.setNombreLegal("asdf");
         pjNuevo.setDocumentoRepresentanteLegal("1722733027");
         pjNuevo.setNombreRepresentanteLegal(solicitud.getNombreSolicitante());
         pjNuevo.setApellidoRepresentanteLegal(solicitud.getApellidoSolicitante());
         pjNuevo.setEmail(solicitud.getEmailSolicitante());
         pjNuevo.setTelefono(solicitud.getTelefonoConvencionalSolicitante());
         pjNuevo.setCelular(solicitud.getTelefonoCelularSolicitante());
         pjNuevo.setDireccion(solicitud.getDireccionSolicitante());
         Localidad localidad = new Localidad();
         System.out.println("concesionMinera.getCodigoParroquia(): " + concesionMinera.getCodigoParroquia());
         localidad.setCodigoLocalidad(Long.valueOf("5"));
         pjNuevo.setCodigoLocalidad(localidad);
         pjNuevo.setCodigoProvincia(concesionMinera.getCodigoProvincia());
         pjNuevo.setCodigoCanton(concesionMinera.getCodigoCanton());
         pjNuevo.setCodigoParroquia(concesionMinera.getCodigoParroquia());
         pjNuevo.setEstadoRegistro(true);
         pjNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
         pjNuevo.setUsuarioModificacion(BigInteger.valueOf(login.getCodigoUsuario()));
         pjNuevo.setFechaModificacion(new Date());
         pjNuevo.setCasilleroJudicial(concesionMinera.getCasilleroJudicial());
         CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
         catalogoDetalle.setCodigoCatalogoDetalle(132L);
         pjNuevo.setClasePersona(catalogoDetalle);
         personaJuridicaServicio.create(pjNuevo);
         }*/
        this.create(concesionMinera);
        //solicitudServicio.create(solicitud);
        areaMineraServicio.create(areaMinera);
    }

    @Override
    public void actualizarTodo(ConcesionMinera concesionMinera, Solicitud solicitud, AreaMinera AreaMinera) throws Exception {
        this.actualizarConcecionMinera(concesionMinera);
        //solicitudServicio.actualizarServicio(solicitud);
        areaMineraServicio.actualizarAreaMinera(AreaMinera);
    }

    @Override
    public void actualizarConcecionMinera(ConcesionMinera concesionMinera) throws Exception {
        concesionMineraDao.actualizarConcesionMinera(concesionMinera);
    }

    @Override
    public ConcesionMinera obtenerPorCodigoArcom(String codigoArcom) {
        return concesionMineraDao.findByCodigoArcom(codigoArcom);
    }

    /**
     * 
     * Método que obtiene todos los derechos mineros registrados en la base de datos
     * en base a todos los parámetros que se muestran a continuación.
     * 
     * @param codigo
     * @param nombreDerechoMinero
     * @param codigoRegional
     * @param codigoProvincia
     * @param codigoFase
     * @param codigoEstado
     * @param tipoSolicitud
     * @param beneficiarioPrincipal
     * @param tipoPersona
     * @param fecha
     * @return 
     */
    @Override
    public List<DerechoMineroDto> obtenerDerechosMinerosNacional(String codigo, String nombreDerechoMinero, Long codigoRegional, Long codigoProvincia, 
            Long codigoFase, Long codigoEstado, String tipoSolicitud, String beneficiarioPrincipal, String tipoPersona, Date fecha, String numDocumento) {
        return concesionMineraDao.busquedaGeneralNacional(codigo, nombreDerechoMinero, codigoRegional, codigoProvincia, codigoFase, codigoEstado, 
                tipoSolicitud, beneficiarioPrincipal, tipoPersona, fecha, fecha, numDocumento);
    }

}
