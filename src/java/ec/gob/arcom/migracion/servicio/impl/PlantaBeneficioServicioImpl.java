/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dto.PlantaBeneficioDto;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PlantaBeneficioServicio")
public class PlantaBeneficioServicioImpl extends GenericServiceImpl<PlantaBeneficio, Long>
        implements PlantaBeneficioServicio {
    
    @EJB
    private PlantaBeneficioDao plantaBeneficioDao;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    
    @Override
    public GenericDao<PlantaBeneficio, Long> getDao() {
        return plantaBeneficioDao;
    }
    
    @Override
    public List<PlantaBeneficioDto> obtenerListaPlantas(String codigoArcom, String identificacion, String usuario) {
        return plantaBeneficioDao.obtenerListaPlantas(codigoArcom, identificacion, usuario);
    }
    
    @Override
    public void actualizarPlantaBeneficio(PlantaBeneficio plantaBeneficio) throws Exception {
//        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
//        PersonaJuridica pj = personaJuridicaServicio.findByRuc(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
//        if (pn != null) {
//            if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 10) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
//                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            } else if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 13) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
//                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            }
//            pn.setNombre(plantaBeneficio.getNombreRepresentanteLegal());
//            pn.setApellido(plantaBeneficio.getApellidoRepresentanteLegal());
//            pn.setEmail(plantaBeneficio.getCorreoElectronico());
//            pn.setTelefono(plantaBeneficio.getTelefonoPlanta());
//            pn.setCelular(plantaBeneficio.getTelefonoPlanta());
//            pn.setDireccion(plantaBeneficio.getDireccionPlanta());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
//            pn.setCodigoLocalidad(localidad);
//            pn.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
//            pn.setCodigoCanton(plantaBeneficio.getCodigoCanton());
//            pn.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
//            pn.setEstadoRegistro(true);
//            pn.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pn.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
//            pn.setFechaModificacion(new Date());
//            pn.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
//            personaNaturalServicio.actualizarPersonaNatural(pn);
//        } else {
//            PersonaNatural pnNuevo = new PersonaNatural();
//            if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 10) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
//                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            } else if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 13) {
//                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
//                pnNuevo.setTipoDocumento(cd.getCodigoCatalogoDetalle());
//            }
//            pnNuevo.setNombre(plantaBeneficio.getNombreRepresentanteLegal());
//            pnNuevo.setApellido(plantaBeneficio.getApellidoRepresentanteLegal());
//            pnNuevo.setEmail(plantaBeneficio.getCorreoElectronico());
//            pnNuevo.setTelefono(plantaBeneficio.getTelefonoPlanta());
//            pnNuevo.setCelular(plantaBeneficio.getTelefonoPlanta());
//            pnNuevo.setDireccion(plantaBeneficio.getDireccionPlanta());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
//            pnNuevo.setCodigoLocalidad(localidad);
//            pnNuevo.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
//            pnNuevo.setCodigoCanton(plantaBeneficio.getCodigoCanton());
//            pnNuevo.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
//            pnNuevo.setEstadoRegistro(true);
//            pnNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pnNuevo.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
//            pnNuevo.setFechaModificacion(new Date());
//            pnNuevo.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
//            personaNaturalServicio.create(pnNuevo);
//        }
//        
//        if (pj != null) {
//            pj.setRuc(plantaBeneficio.getNombreRepresentanteLegal());
//            pj.setNombreLegal("");
//            pj.setDocumentoRepresentanteLegal("");
//            pj.setNombreRepresentanteLegal(plantaBeneficio.getNombreRepresentanteLegal());
//            pj.setApellidoRepresentanteLegal(plantaBeneficio.getApellidoRepresentanteLegal());
//            pj.setEmail(plantaBeneficio.getCorreoElectronico());
//            pj.setTelefono(plantaBeneficio.getTelefonoPlanta());
//            pj.setCelular(plantaBeneficio.getTelefonoPlanta());
//            pj.setDireccion(plantaBeneficio.getDireccionPlanta());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
//            pj.setCodigoLocalidad(localidad);
//            pj.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
//            pj.setCodigoCanton(plantaBeneficio.getCodigoCanton());
//            pj.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
//            pj.setEstadoRegistro(true);
//            pj.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pj.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
//            pj.setFechaModificacion(new Date());
//            pj.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
//            personaJuridicaServicio.actualizarPersonaJuridica(pj);
//        } else {
//            PersonaJuridica pjNuevo = new PersonaJuridica();
//            pjNuevo.setRuc(plantaBeneficio.getNombreRepresentanteLegal());
//            pjNuevo.setNombreLegal("");
//            pjNuevo.setDocumentoRepresentanteLegal("");
//            pjNuevo.setNombreRepresentanteLegal(plantaBeneficio.getNombreRepresentanteLegal());
//            pjNuevo.setApellidoRepresentanteLegal(plantaBeneficio.getApellidoRepresentanteLegal());
//            pjNuevo.setEmail(plantaBeneficio.getCorreoElectronico());
//            pjNuevo.setTelefono(plantaBeneficio.getTelefonoPlanta());
//            pjNuevo.setCelular(plantaBeneficio.getTelefonoPlanta());
//            pjNuevo.setDireccion(plantaBeneficio.getDireccionPlanta());
//            Localidad localidad = new Localidad();
//            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
//            pjNuevo.setCodigoLocalidad(localidad);
//            pjNuevo.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
//            pjNuevo.setCodigoCanton(plantaBeneficio.getCodigoCanton());
//            pjNuevo.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
//            pjNuevo.setEstadoRegistro(true);
//            pjNuevo.setUsuarioCreacion(BigInteger.valueOf(-1));
//            pjNuevo.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
//            pjNuevo.setFechaModificacion(new Date());
//            pjNuevo.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
//        }
        plantaBeneficioDao.actualizarPlantaBeneficio(plantaBeneficio);
    }

    @Override
    public void guardarPlantaBeneficio(PlantaBeneficio plantaBeneficio) throws Exception {
        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
        if (pn != null) {
            if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 10) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("CED").get(0);
                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            } else if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 13) {
                CatalogoDetalle cd = catalogoDetalleServicio.obtenerPorNemonico("RUC").get(0);
                pn.setTipoDocumento(cd.getCodigoCatalogoDetalle());
            }
            pn.setNombre(plantaBeneficio.getNombreRepresentanteLegal());
            pn.setApellido(plantaBeneficio.getApellidoRepresentanteLegal());
            pn.setEmail(plantaBeneficio.getCorreoElectronico());
            pn.setTelefono(plantaBeneficio.getTelefonoPlanta());
            pn.setCelular(plantaBeneficio.getTelefonoPlanta());
            pn.setDireccion(plantaBeneficio.getDireccionPlanta());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
            pn.setCodigoLocalidad(localidad);
            pn.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
            pn.setCodigoCanton(plantaBeneficio.getCodigoCanton());
            pn.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
            pn.setEstadoRegistro(true);
            pn.setUsuarioCreacion(BigInteger.valueOf(-1));
            pn.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
            pn.setFechaModificacion(new Date());
            pn.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
            personaNaturalServicio.actualizarPersonaNatural(pn);
        } else if (pj != null) {
            pj.setRuc(plantaBeneficio.getNombreRepresentanteLegal());
            pj.setNombreLegal("");
            pj.setDocumentoRepresentanteLegal("");
            pj.setNombreRepresentanteLegal(plantaBeneficio.getNombreRepresentanteLegal());
            pj.setApellidoRepresentanteLegal(plantaBeneficio.getApellidoRepresentanteLegal());
            pj.setEmail(plantaBeneficio.getCorreoElectronico());
            pj.setTelefono(plantaBeneficio.getTelefonoPlanta());
            pj.setCelular(plantaBeneficio.getTelefonoPlanta());
            pj.setDireccion(plantaBeneficio.getDireccionPlanta());
            Localidad localidad = new Localidad();
            localidad.setCodigoLocalidad(Long.valueOf(plantaBeneficio.getCodigoParroquida().toString()));
            pj.setCodigoLocalidad(localidad);
            pj.setCodigoProvincia(plantaBeneficio.getCodigoProvincia());
            pj.setCodigoCanton(plantaBeneficio.getCodigoCanton());
            pj.setCodigoParroquia(plantaBeneficio.getCodigoParroquida());
            pj.setEstadoRegistro(true);
            pj.setUsuarioCreacion(BigInteger.valueOf(-1));
            pj.setUsuarioModificacion(plantaBeneficio.getUsuarioModificacion());
            pj.setFechaModificacion(new Date());
            pj.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
            personaJuridicaServicio.actualizarPersonaJuridica(pj);
        }
        this.create(plantaBeneficio);
    }

    @Override
    public PlantaBeneficio obtenerPorCodigoArcom(String codigoArcom) {
        return plantaBeneficioDao.findByCodigoArcom(codigoArcom);
    }

    @Override
    public ConcesionMinera buscarPlantaEnConcesion(String codigoArcom) {
        return plantaBeneficioDao.buscarPlantaEnConcesion(codigoArcom);
    }
    
}
