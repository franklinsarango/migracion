/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.InstrumentoDao;
import ec.gob.arcom.migracion.modelo.Instrumento;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "InstrumentoDao")
public class InstrumentoDaoEjb extends GenericDaoEjbEl<Instrumento, Long> implements
        InstrumentoDao {

    public InstrumentoDaoEjb() {
        super(Instrumento.class);
    }

    @Override
    public Instrumento obtenerPorCodigoInstrumento(Long codigoInstrumento) {
        String hql = "select i from Instrumento i where i.codigoInstrumento = :codigoInstrumento";
        Query query = em.createQuery(hql);
        query.setParameter("codigoInstrumento", codigoInstrumento);
        Instrumento instrumento = (Instrumento) query.getSingleResult();
        this.refresh(instrumento);
        return instrumento;
    }

    @Override
    public void actualizarInstrumento(Instrumento instrumento) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.instrumento\n"
                + "SET\n";
        if (instrumento.getCodigoInstrumento() != null) {
            sql = sql + "    codigo_instrumento = " + instrumento.getCodigoInstrumento() + ",\n";
        }
        if (instrumento.getNumeroTramite() != null) {
            sql = sql + "    numero_tramite = " + instrumento.getNumeroTramite() + ",\n";
        }
        if (instrumento.getNumeroRepertorio() != null) {
            sql = sql + "    numero_repertorio = " + instrumento.getNumeroRepertorio() + ",\n";
        }
        if (instrumento.getTipoInstrumento() != null && instrumento.getTipoInstrumento().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    tipo_instrumento = " + instrumento.getTipoInstrumento().getCodigoCatalogoDetalle() + ",\n";
        }
        if (instrumento.getCodigoSolicitud() != null) {
            sql = sql + "    codigo_solicitud = " + instrumento.getCodigoSolicitud() + ",\n";
        }
        if (instrumento.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + instrumento.getCodigoProvincia() + ",\n";
        }
        if (instrumento.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + instrumento.getCodigoCanton() + ",\n";
        }
        if (instrumento.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + instrumento.getCodigoParroquia() + ",\n";
        }
        if (instrumento.getCodigoLocalidad() != null) {
            sql = sql + "    codigo_localidad = " + instrumento.getCodigoLocalidad() + ",\n";
        }
        if (instrumento.getCodigoArea() != null && instrumento.getCodigoArea().getCodigoAreaMinera() != null) {
            sql = sql + "    codigo_area = " + instrumento.getCodigoArea().getCodigoAreaMinera() + ",\n";
        }
        if (instrumento.getResolucionId() != null) {
            sql = sql + "    resolucion_id = " + instrumento.getResolucionId() + " ,\n";
        }
        if (instrumento.getNumeroDocumentoCompareciente() != null) {
            sql = sql + "    numero_documento_compareciente = '" + instrumento.getNumeroDocumentoCompareciente() + "',\n";
        }
        if (instrumento.getNombreCompareciente() != null) {
            sql = sql + "    nombre_compareciente = '" + instrumento.getNombreCompareciente() + "',\n";
        }
        if (instrumento.getApellidoCompareciente() != null) {
            sql = sql + "    apellido_compareciente = '" + instrumento.getApellidoCompareciente() + "',\n";
        }
        if (instrumento.getNumeroDocumentoOtorgante() != null) {
            sql = sql + "    numero_documento_otorgante = '" + instrumento.getNumeroDocumentoOtorgante() + "',\n";
        }
        if (instrumento.getNombreOtorgante() != null) {
            sql = sql + "    nombre_otorgante = '" + instrumento.getNombreOtorgante() + "',\n";
        }
        if (instrumento.getApellidoOtorgante() != null) {
            sql = sql + "    apellido_otorgante = '" + instrumento.getApellidoOtorgante() + "',\n";
        }
        if (instrumento.getFechaOtorgamiento() != null) {
            sql = sql + "    fecha_otorgamiento = '" + instrumento.getFechaOtorgamiento() + "',\n";
        }
        if (instrumento.getNotaria() != null) {
            sql = sql + "    notaria = '" + instrumento.getNotaria() + "',\n";
        }
        if (instrumento.getFechaProtocolizacionNotaria() != null) {
            sql = sql + "    fecha_protocolizacion_notaria = '" + instrumento.getFechaProtocolizacionNotaria() + "',\n";
        }
        if (instrumento.getNaturalezaActo() != null) {
            sql = sql + "    naturaleza_acto = '" + instrumento.getNaturalezaActo() + "',\n";
        }
        if (instrumento.getFechaInscripcion() != null) {
            sql = sql + "    fecha_inscripcion = '" + instrumento.getFechaInscripcion() + "',\n";
        }
        if (instrumento.getLibroInscripcion() != null && instrumento.getLibroInscripcion().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    libro_inscripcion = '" + instrumento.getLibroInscripcion().getCodigoCatalogoDetalle() + "',\n";
        }
        if (instrumento.getTomoInscripcion() != null) {
            sql = sql + "    tomo_inscripcion = " + instrumento.getTomoInscripcion() + ",\n";
        }
        if (instrumento.getNumeroInscripcion() != null) {
            sql = sql + "    numero_inscripcion = " + instrumento.getNumeroInscripcion() + ",\n";
        }
        if (instrumento.getFolioInscripcion() != null) {
            sql = sql + "    folio_inscripcion = " + instrumento.getFolioInscripcion() + ",\n";
        }
        if (instrumento.getObservaciones() != null) {
            sql = sql + "    observaciones = '" + instrumento.getObservaciones() + "',\n";
        }
        if (instrumento.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + instrumento.getCampoReservado05() + "',\n";
        }
        if (instrumento.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + instrumento.getCampoReservado04() + "',\n";
        }
        if (instrumento.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + instrumento.getCampoReservado03() + "',\n";
        }
        if (instrumento.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + instrumento.getCampoReservado02() + "',\n";
        }
        if (instrumento.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + instrumento.getCampoReservado01() + "',\n";
        }
        if (instrumento.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + instrumento.getEstadoRegistro() + ",\n";
        }
        if (instrumento.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + instrumento.getFechaCreacion() + "',\n";
        }
        if (instrumento.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + instrumento.getUsuarioCreacion() + ",\n";
        }
        if (instrumento.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + instrumento.getFechaModificacion() + "',\n";
        }
        if (instrumento.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + instrumento.getUsuarioModificacion() + ",\n";
        }
        if (instrumento.getCodigoTramite() != null) {
            sql = sql + "    codigo_tramite = " + instrumento.getCodigoTramite() + ",\n";
        }
        if (instrumento.getEntidadTramite() != null) {
            sql = sql + "    entidad_tramite = '" + instrumento.getEntidadTramite() + "',\n";
        }
        if (instrumento.getAplicaPago() != null) {
            sql = sql + "    aplica_pago = " + instrumento.getAplicaPago() + ",\n";
        }
        if (instrumento.getCodigoPago() != null) {
            sql = sql + "    codigo_pago = " + instrumento.getCodigoPago() + ",\n";
        }
        if (instrumento.getValorInstrumento() != null) {
            sql = sql + "    valor_instrumento = " + instrumento.getValorInstrumento() + ",\n";
        }
        if (instrumento.getCodigoCostoServicios() != null) {
            sql = sql + "    codigo_costo_servicios = " + instrumento.getCodigoCostoServicios() + ",\n";
        }
        if (instrumento.getCodigoConceptoPago() != null) {
            sql = sql + "    codigo_concepto_pago = " + instrumento.getCodigoConceptoPago() + ",\n";
        }
        if (instrumento.getTipoDocumentoOtorgamiento() != null && instrumento.getTipoDocumentoOtorgamiento().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    tipo_documento_otorgamiento = " + instrumento.getTipoDocumentoOtorgamiento().getCodigoCatalogoDetalle() + ",\n";
        }
        if (instrumento.getClaseInscripcion() != null && instrumento.getClaseInscripcion().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    clase_inscripcion = " + instrumento.getClaseInscripcion().getCodigoCatalogoDetalle() + ",\n";
        }
        if (instrumento.getCodigoTipoMineria() != null && instrumento.getCodigoTipoMineria().getCodigoTipoMineria() != null) {
            sql = sql + "    codigo_tipo_mineria = " + instrumento.getCodigoTipoMineria().getCodigoTipoMineria() + ",\n";
        }
        if (instrumento.getConcesionMinera() != null && instrumento.getConcesionMinera().getCodigoConcesion() != null) {
            sql = sql + "    codigo_concesion_minera = " +  instrumento.getConcesionMinera().getCodigoConcesion() + ",\n";
        }
        if (instrumento.getPlantaBeneficio() != null && instrumento.getPlantaBeneficio().getCodigoPlantaBeneficio() != null) {
            sql = sql + "    codigo_planta_beneficio = " + instrumento.getPlantaBeneficio().getCodigoPlantaBeneficio() + ",\n";
        }
        if (instrumento.getLicenciaComercializacion() != null && instrumento.getLicenciaComercializacion().getCodigoLicenciaComercializacion() != null) {
            sql = sql + "    codigo_licencia_comercializacion = " + instrumento.getLicenciaComercializacion().getCodigoLicenciaComercializacion() + ",\n";
        }
        if (instrumento.getCodigoUsuario() != null) {
            sql = sql + "    codigo_usuario = " + instrumento.getCodigoUsuario() + ",\n";
        }
        if (instrumento.getDocumentoTitular() != null) {
            sql = sql + "    documento_titular = " + instrumento.getDocumentoTitular() + ",\n";
        }
        sql = sql + "    institucion_otorgante = " + instrumento.isInstitucionOtorgante() + ",\n";
        if (instrumento.getNombreInstitucionOtorgante() != null) {
            sql = sql + "    nombre_institucion_otorgante = " + instrumento.getNombreInstitucionOtorgante() + ",\n";
        }
        if (instrumento.getSujetoMinero() != null && instrumento.getSujetoMinero().getCodigoSujetoMinero() != null) {
            sql = sql + "    codigo_sujeto_minero = " + instrumento.getSujetoMinero().getCodigoSujetoMinero() + ",\n";
        }
        sql = sql + "    datos_actualizados = " + instrumento.isDatosActualizados() + "\n";
        sql = sql + "WHERE\n"
                + "    codigo_instrumento = " + instrumento.getCodigoInstrumento();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
