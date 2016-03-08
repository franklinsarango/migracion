/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.SujetoMineroDao;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "SujetoMineroDao")
public class SujetoMineroDaoEjb extends GenericDaoEjbEl<SujetoMinero, Long> implements
        SujetoMineroDao {

    public SujetoMineroDaoEjb() {
        super(SujetoMinero.class);
    }

    @Override
    public SujetoMinero obtenerPorIdentificacion(String numeroIdentificacion) {
        try {
            String hql = "select sm from SujetoMinero sm where sm.numeroDocumento = :numeroIdentificacion";
            Query query = em.createQuery(hql);
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            SujetoMinero sujetoMinero = (SujetoMinero) query.getSingleResult();
            this.refresh(sujetoMinero);
            return sujetoMinero;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarSujetoMinero(SujetoMinero sujetoMinero) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.sujeto_minero\n"
                + "SET\n";
        if (sujetoMinero.getCodigoSujetoMinero() != null) {
            sql = sql + "    codigo_sujeto_minero = " + sujetoMinero.getCodigoSujetoMinero() + ",\n";
        }
        if (sujetoMinero.getNumeroDocumento() != null) {
            sql = sql + "    numero_documento = '" + sujetoMinero.getNumeroDocumento() + "',\n";
        }
        if (sujetoMinero.getNombre() != null) {
            sql = sql + "    nombre = '" + sujetoMinero.getNombre() + "',\n";
        }
        if (sujetoMinero.getApellido() != null) {
            sql = sql + "    apellido = '" + sujetoMinero.getApellido() + "',\n";
        }
        if (sujetoMinero.getTipoSujetoMinero() != null && sujetoMinero.getTipoSujetoMinero().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    tipo_sujeto_minero = " + sujetoMinero.getTipoSujetoMinero().getCodigoCatalogoDetalle() + ",\n";
        }
        if (sujetoMinero.getEstadoSujetoMinero() != null && sujetoMinero.getEstadoSujetoMinero().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    estado_sujeto_minero = " + sujetoMinero.getEstadoSujetoMinero().getCodigoCatalogoDetalle() + ",\n";
        }
        if (sujetoMinero.getEmail() != null) {
            sql = sql + "    email = '" + sujetoMinero.getEmail() + "',\n";
        }
        if (sujetoMinero.getTelefono() != null) {
            sql = sql + "    telefono = '" + sujetoMinero.getTelefono() + "',\n";
        }
        if (sujetoMinero.getCelular() != null) {
            sql = sql + "    celular = '" + sujetoMinero.getCelular() + "',\n";
        }
        if (sujetoMinero.getDireccion() != null) {
            sql = sql + "    direccion = '" + sujetoMinero.getDireccion() + "',\n";
        }
        if (sujetoMinero.getFechaOtorgacionDerecho() != null) {
            sql = sql + "    fecha_otorgacion_derecho = '" + sujetoMinero.getFechaOtorgacionDerecho() + "',\n";
        }
        if (sujetoMinero.getFechaProtocolizacionDerecho() != null) {
            sql = sql + "    fecha_protocolizacion_derecho = '" + sujetoMinero.getFechaProtocolizacionDerecho() + "',\n";
        }
        if (sujetoMinero.getNumeroRepertorio() != null) {
            sql = sql + "    numero_repertorio = '" + sujetoMinero.getNumeroRepertorio() + "',\n";
        }
        if (sujetoMinero.getNombreCompareciente() != null) {
            sql = sql + "    nombre_compareciente = '" + sujetoMinero.getNombreCompareciente() + "',\n";
        }
        if (sujetoMinero.getApellidoCompareciente() != null) {
            sql = sql + "    apellido_compareciente = '" + sujetoMinero.getApellidoCompareciente() + "',\n";
        }
        if (sujetoMinero.getNumeroDocumentoCompareciente() != null) {
            sql = sql + "    numero_documento_compareciente = '" + sujetoMinero.getNumeroDocumentoCompareciente() + "',\n";
        }
        if (sujetoMinero.getRucCompareciente() != null) {
            sql = sql + "    ruc_compareciente = '" + sujetoMinero.getRucCompareciente() + "',\n";
        }
        if (sujetoMinero.getNombreOtorgante() != null) {
            sql = sql + "    nombre_otorgante = '" + sujetoMinero.getNombreOtorgante() + "',\n";
        }
        if (sujetoMinero.getApellidoOtorgante() != null) {
            sql = sql + "    apellido_otorgante = '" + sujetoMinero.getApellidoOtorgante() + "',\n";
        }
        if (sujetoMinero.getNumeroDocumentoOtorgante() != null) {
            sql = sql + "    numero_documento_otorgante = '" + sujetoMinero.getNumeroDocumentoOtorgante() + "',\n";
        }
        if (sujetoMinero.getRucOtorgante() != null) {
            sql = sql + "    ruc_otorgante = '" + sujetoMinero.getRucOtorgante() + "',\n";
        }
        if (sujetoMinero.getNotaria() != null) {
            sql = sql + "    notaria = '" + sujetoMinero.getNotaria() + "',\n";
        }
        if (sujetoMinero.getNaturalezaActoContrato() != null) {
            sql = sql + "    naturaleza_acto_contrato = '" + sujetoMinero.getNaturalezaActoContrato() + "',\n";
        }
        if (sujetoMinero.getClaseInscripcion() != null) {
            sql = sql + "    clase_inscripcion = '" + sujetoMinero.getClaseInscripcion() + "',\n";
        }
        if (sujetoMinero.getFechaInscripcion() != null) {
            sql = sql + "    fecha_inscripcion = '" + sujetoMinero.getFechaInscripcion() + "',\n";
        }
        if (sujetoMinero.getLibroInscripcion() != null) {
            sql = sql + "    libro_inscripcion = '" + sujetoMinero.getLibroInscripcion() + "',\n";
        }
        if (sujetoMinero.getNumeroInscripcion() != null) {
            sql = sql + "    numero_inscripcion = '" + sujetoMinero.getNumeroInscripcion() + "',\n";
        }
        if (sujetoMinero.getTomoInscripcion() != null) {
            sql = sql + "    tomo_inscripcion = '" + sujetoMinero.getTomoInscripcion() + "',\n";
        }
        if (sujetoMinero.getFolioInscripcion() != null) {
            sql = sql + "    folio_inscripcion = '" + sujetoMinero.getFolioInscripcion() + "',\n";
        }
        if (sujetoMinero.getObservaciones() != null) {
            sql = sql + "    observaciones = '" + sujetoMinero.getObservaciones() + "',\n";
        }
        if (sujetoMinero.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + sujetoMinero.getCodigoProvincia() + ",\n";
        }
        if (sujetoMinero.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + sujetoMinero.getCodigoCanton() + ",\n";
        }
        if (sujetoMinero.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + sujetoMinero.getCodigoParroquia() + ",\n";
        }
        if (sujetoMinero.getCodigoLocalidad() != null) {
            sql = sql + "    codigo_localidad = " + sujetoMinero.getCodigoLocalidad() + ",\n";
        }
        if (sujetoMinero.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + sujetoMinero.getEstadoRegistro() + ",\n";
        }
        if (sujetoMinero.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + sujetoMinero.getFechaCreacion() + "',\n";
        }
        if (sujetoMinero.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + sujetoMinero.getUsuarioCreacion() + ",\n";
        }
        if (sujetoMinero.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + sujetoMinero.getFechaModificacion() + "',\n";
        }
        if (sujetoMinero.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + sujetoMinero.getUsuarioModificacion() + "\n";
        }
        sql = sql + "WHERE\n"
                + "    codigo_sujeto_minero = " + sujetoMinero.getCodigoSujetoMinero();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
