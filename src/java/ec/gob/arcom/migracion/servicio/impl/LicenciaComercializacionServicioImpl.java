/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.LicenciaComercializacionDao;
import ec.gob.arcom.migracion.dto.LicenciaComercializacionDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LicenciaComercializacionServicio")
public class LicenciaComercializacionServicioImpl extends GenericServiceImpl<LicenciaComercializacion, Long>
        implements LicenciaComercializacionServicio {

    @EJB
    private LicenciaComercializacionDao licenciaComercializacionDao;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @Override
    public GenericDao<LicenciaComercializacion, Long> getDao() {
        return licenciaComercializacionDao;
    }

    @Override
    public List<LicenciaComercializacionDto> obtenerListaLicencias(String codigoArcom, String identificacion, String usuario) {
        return licenciaComercializacionDao.obtenerListaLicencias(codigoArcom, identificacion, usuario);
    }

    @Override
    public void actualizarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception {
//        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(licenciaComercializacion.getNumeroDocumento());
//        PersonaJuridica pj = personaJuridicaServicio.findByRuc(licenciaComercializacion.getNumeroDocumento());
//        if (pn != null) {
//            if (licenciaComercializacion.getNumeroDocumento().length() == 10) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
//                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            } else if (licenciaComercializacion.getNumeroDocumento().length() == 13) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
//                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            }
//            pn.setNombre(licenciaComercializacion.getNombre());
//            pn.setApellido(licenciaComercializacion.getApellido());
//            pn.setEmail(licenciaComercializacion.getCorreoElectronico());
//            pn.setTelefono(licenciaComercializacion.getTelefono());
//            pn.setCelular(licenciaComercializacion.getTelefono());
//            pn.setDireccion(licenciaComercializacion.getDireccion());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
//            pn.setCodigoLocalidad(localidad);
//            pn.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
//            pn.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
//            pn.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
//            pn.setEstadoRegistro(true);
//            pn.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pn.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
//            pn.setFechaModificacion(new Date());
//            pn.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
//            personaNaturalServicio.actualizarPersonaNatural(pn);
//        } else {
//            PersonaNatural pnNuevo = new PersonaNatural();
//            if (licenciaComercializacion.getNumeroDocumento().length() == 10) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
//                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            } else if (licenciaComercializacion.getNumeroDocumento().length() == 13) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
//                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            }
//            pnNuevo.setNombre(licenciaComercializacion.getNombre());
//            pnNuevo.setApellido(licenciaComercializacion.getApellido());
//            pnNuevo.setEmail(licenciaComercializacion.getCorreoElectronico());
//            pnNuevo.setTelefono(licenciaComercializacion.getTelefono());
//            pnNuevo.setCelular(licenciaComercializacion.getTelefono());
//            pnNuevo.setDireccion(licenciaComercializacion.getDireccion());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
//            pnNuevo.setCodigoLocalidad(localidad);
//            pnNuevo.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
//            pnNuevo.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
//            pnNuevo.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
//            pnNuevo.setEstadoRegistro(true);
//            pnNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pnNuevo.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
//            pnNuevo.setFechaModificacion(new Date());
//            pnNuevo.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
//            personaNaturalServicio.create(pnNuevo);
//        }
//        
//        if (pj != null) {
//            pj.setRuc(licenciaComercializacion.getNumeroDocumento());
//            pj.setNombreLegal("");
//            pj.setDocumentoRepresentanteLegal("");
//            pj.setNombreRepresentanteLegal(licenciaComercializacion.getNombre());
//            pj.setApellidoRepresentanteLegal(licenciaComercializacion.getApellido());
//            pj.setEmail(licenciaComercializacion.getCorreoElectronico());
//            pj.setTelefono(licenciaComercializacion.getTelefono());
//            pj.setCelular(licenciaComercializacion.getTelefono());
//            pj.setDireccion(licenciaComercializacion.getDireccion());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
//            pj.setCodigoLocalidad(localidad);
//            pj.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
//            pj.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
//            pj.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
//            pj.setEstadoRegistro(true);
//            pj.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pj.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
//            pj.setFechaModificacion(new Date());
//            pj.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
//            personaJuridicaServicio.actualizarPersonaJuridica(pj);
//        } else {
//            PersonaJuridica pjNuevo = new PersonaJuridica();
//            pjNuevo.setRuc(licenciaComercializacion.getNumeroDocumento());
//            pjNuevo.setNombreLegal("");
//            pjNuevo.setDocumentoRepresentanteLegal("");
//            pjNuevo.setNombreRepresentanteLegal(licenciaComercializacion.getNombre());
//            pjNuevo.setApellidoRepresentanteLegal(licenciaComercializacion.getApellido());
//            pjNuevo.setEmail(licenciaComercializacion.getCorreoElectronico());
//            pjNuevo.setTelefono(licenciaComercializacion.getTelefono());
//            pjNuevo.setCelular(licenciaComercializacion.getTelefono());
//            pjNuevo.setDireccion(licenciaComercializacion.getDireccion());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
//            pjNuevo.setCodigoLocalidad(localidad);
//            pjNuevo.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
//            pjNuevo.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
//            pjNuevo.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
//            pjNuevo.setEstadoRegistro(true);
//            pjNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pjNuevo.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
//            pjNuevo.setFechaModificacion(new Date());
//            pjNuevo.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
//        }
        licenciaComercializacionDao.actualizarLicenciaComercializacion(licenciaComercializacion);
    }

    @Override
    public void guardarLicenciaComercializacion(LicenciaComercializacion licenciaComercializacion) throws Exception {
        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(licenciaComercializacion.getNumeroDocumento());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(licenciaComercializacion.getNumeroDocumento());
        if (pn != null) {
            if (licenciaComercializacion.getNumeroDocumento().length() == 10) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            } else if (licenciaComercializacion.getNumeroDocumento().length() == 13) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            }
            pn.setNombre(licenciaComercializacion.getNombre());
            pn.setApellido(licenciaComercializacion.getApellido());
            pn.setEmail(licenciaComercializacion.getCorreoElectronico());
            pn.setTelefono(licenciaComercializacion.getTelefono());
            pn.setCelular(licenciaComercializacion.getTelefono());
            pn.setDireccion(licenciaComercializacion.getDireccion());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
            pn.setCodigoLocalidad(localidad);
            pn.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
            pn.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
            pn.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
            pn.setEstadoRegistro(true);
            pn.setUsuarioCreacion(BigInteger.valueOf(-1));
            pn.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
            pn.setFechaModificacion(new Date());
            pn.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
            personaNaturalServicio.actualizarPersonaNatural(pn);
        } else {
            PersonaNatural pnNuevo = new PersonaNatural();
            if (licenciaComercializacion.getNumeroDocumento().length() == 10) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            } else if (licenciaComercializacion.getNumeroDocumento().length() == 13) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            }
            pnNuevo.setNombre(licenciaComercializacion.getNombre());
            pnNuevo.setApellido(licenciaComercializacion.getApellido());
            pnNuevo.setEmail(licenciaComercializacion.getCorreoElectronico());
            pnNuevo.setTelefono(licenciaComercializacion.getTelefono());
            pnNuevo.setCelular(licenciaComercializacion.getTelefono());
            pnNuevo.setDireccion(licenciaComercializacion.getDireccion());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
            pnNuevo.setCodigoLocalidad(localidad);
            pnNuevo.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
            pnNuevo.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
            pnNuevo.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
            pnNuevo.setEstadoRegistro(true);
            pnNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
            pnNuevo.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
            pnNuevo.setFechaModificacion(new Date());
            pnNuevo.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
            personaNaturalServicio.create(pnNuevo);
        }
        
        if (pj != null) {
            pj.setRuc(licenciaComercializacion.getNumeroDocumento());
            pj.setNombreLegal("");
            pj.setDocumentoRepresentanteLegal("");
            pj.setNombreRepresentanteLegal(licenciaComercializacion.getNombre());
            pj.setApellidoRepresentanteLegal(licenciaComercializacion.getApellido());
            pj.setEmail(licenciaComercializacion.getCorreoElectronico());
            pj.setTelefono(licenciaComercializacion.getTelefono());
            pj.setCelular(licenciaComercializacion.getTelefono());
            pj.setDireccion(licenciaComercializacion.getDireccion());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
            pj.setCodigoLocalidad(localidad);
            pj.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
            pj.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
            pj.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
            pj.setEstadoRegistro(true);
            pj.setUsuarioCreacion(BigInteger.valueOf(-1));
            pj.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
            pj.setFechaModificacion(new Date());
            pj.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
            personaJuridicaServicio.actualizarPersonaJuridica(pj);
        } else {
            PersonaJuridica pjNuevo = new PersonaJuridica();
            pjNuevo.setRuc(licenciaComercializacion.getNumeroDocumento());
            pjNuevo.setNombreLegal("");
            pjNuevo.setDocumentoRepresentanteLegal("");
            pjNuevo.setNombreRepresentanteLegal(licenciaComercializacion.getNombre());
            pjNuevo.setApellidoRepresentanteLegal(licenciaComercializacion.getApellido());
            pjNuevo.setEmail(licenciaComercializacion.getCorreoElectronico());
            pjNuevo.setTelefono(licenciaComercializacion.getTelefono());
            pjNuevo.setCelular(licenciaComercializacion.getTelefono());
            pjNuevo.setDireccion(licenciaComercializacion.getDireccion());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(licenciaComercializacion.getCodigoParroquida().toString()));
            pjNuevo.setCodigoLocalidad(localidad);
            pjNuevo.setCodigoProvincia(licenciaComercializacion.getCodigoProvincia());
            pjNuevo.setCodigoCanton(licenciaComercializacion.getCodigoCanton());
            pjNuevo.setCodigoParroquia(licenciaComercializacion.getCodigoParroquida());
            pjNuevo.setEstadoRegistro(true);
            pjNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
            pjNuevo.setUsuarioModificacion(licenciaComercializacion.getUsuarioModificacion());
            pjNuevo.setFechaModificacion(new Date());
            pjNuevo.setCasilleroJudicial(licenciaComercializacion.getCasilleroJudicial());
        }
        this.create(licenciaComercializacion);
    }

    @Override
    public LicenciaComercializacion obtenerPorCodigoArcom(String codigoarcom) {
        return licenciaComercializacionDao.findByCodigoArcom(codigoarcom);
    }

}
