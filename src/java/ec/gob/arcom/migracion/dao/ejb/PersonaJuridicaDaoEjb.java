/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.PersonaJuridicaDao;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PersonaJuridicaDao")
public class PersonaJuridicaDaoEjb extends GenericDaoEjbEl<PersonaJuridica, String> implements
        PersonaJuridicaDao {

    public PersonaJuridicaDaoEjb() {
        super(PersonaJuridica.class);
    }

    @Override
    public PersonaJuridica findByRuc(String ruc) {
        try {
            String hql = "select pj from PersonaJuridica pj where pj.ruc = :ruc";
            Query query = em.createQuery(hql);
            query.setParameter("ruc", ruc);
            PersonaJuridica pj = (PersonaJuridica) query.getSingleResult();
            this.refresh(pj);
            return pj;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarPersonaJuridica(PersonaJuridica personaJuridica) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.persona_juridica\n"
                + "SET\n";
        if (personaJuridica.getRuc() != null) {
            sql = sql + "    ruc = '" + personaJuridica.getRuc() + "',\n";
        }
        if (personaJuridica.getNombreLegal() != null) {
            sql = sql + "    nombre_legal = '" + personaJuridica.getNombreLegal() + "',\n";
        }
        if (personaJuridica.getNombreComercial() != null) {
            sql = sql + "    nombre_comercial = '" + personaJuridica.getNombreComercial() + "',\n";
        }
        if (personaJuridica.getDocumentoRepresentanteLegal() != null) {
            sql = sql + "    documento_representante_legal = '" + personaJuridica.getDocumentoRepresentanteLegal() + "',\n";
        }
        if (personaJuridica.getNombreRepresentanteLegal() != null) {
            sql = sql + "    nombre_representante_legal = '" + personaJuridica.getNombreRepresentanteLegal() + "',\n";
        }
        if (personaJuridica.getApellidoRepresentanteLegal() != null) {
            sql = sql + "    apellido_representante_legal = '" + personaJuridica.getApellidoRepresentanteLegal() + "',\n";
        }
        if (personaJuridica.getFechaConstitucion() != null) {
            sql = sql + "    fecha_constitucion = '" + personaJuridica.getFechaConstitucion() + "',\n";
        }
        if (personaJuridica.getEsPublica() != null) {
            sql = sql + "    es_publica = " + personaJuridica.getEsPublica() + ",\n";
        }
        if (personaJuridica.getClasePersona() != null) {
            sql = sql + "    clase_persona = " + personaJuridica.getClasePersona().getCodigoCatalogoDetalle() + ",\n";
        }
        if (personaJuridica.getEmail() != null) {
            sql = sql + "    email = '" + personaJuridica.getEmail() + "',\n";
        }
        if (personaJuridica.getTelefono() != null) {
            sql = sql + "    telefono = '" + personaJuridica.getTelefono() + "',\n";
        }
        if (personaJuridica.getCelular() != null) {
            sql = sql + "    celular = '" + personaJuridica.getCelular() + "',\n";
        }
        if (personaJuridica.getDireccion() != null) {
            sql = sql + "    direccion = '" + personaJuridica.getDireccion() + "',\n";
        }
        if (personaJuridica.getCodigoLocalidad() != null) {
            sql = sql + "    codigo_localidad = " + personaJuridica.getCodigoLocalidad().getCodigoLocalidad() + ",\n";
        }
        if (personaJuridica.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + personaJuridica.getCodigoProvincia() + ",\n";
        }
        if (personaJuridica.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + personaJuridica.getCodigoCanton() + ",\n";
        }
        if (personaJuridica.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + personaJuridica.getCodigoParroquia() + ",\n";
        }
        if (personaJuridica.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + personaJuridica.getCampoReservado05() + "',\n";
        }
        if (personaJuridica.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + personaJuridica.getCampoReservado04() + "',\n";
        }
        if (personaJuridica.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + personaJuridica.getCampoReservado03() + "',\n";
        }
        if (personaJuridica.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + personaJuridica.getCampoReservado02() + "',\n";
        }
        if (personaJuridica.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + personaJuridica.getCampoReservado01() + "',\n";
        }
        if (personaJuridica.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + personaJuridica.getEstadoRegistro() + ",\n";
        }
        if (personaJuridica.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + personaJuridica.getFechaCreacion() + "',\n";
        }
        if (personaJuridica.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + personaJuridica.getUsuarioCreacion() + ",\n";
        }
        if (personaJuridica.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + personaJuridica.getFechaModificacion() + "',\n";
        }
        if (personaJuridica.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + personaJuridica.getUsuarioModificacion() + ",\n";
        }
        if (personaJuridica.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + personaJuridica.getCasilleroJudicial() + "'\n";
        }
        sql = sql + "WHERE\n"
                + "    ruc = '" + personaJuridica.getRuc() + "'";

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
