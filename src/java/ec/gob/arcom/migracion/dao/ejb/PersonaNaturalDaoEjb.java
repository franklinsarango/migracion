/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.PersonaNaturalDao;
import ec.gob.arcom.migracion.dto.PersonaDto;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PersonaNaturalDao")
public class PersonaNaturalDaoEjb extends GenericDaoEjbEl<PersonaNatural, String> implements
        PersonaNaturalDao {

    public PersonaNaturalDaoEjb() {
        super(PersonaNatural.class);
    }

    @Override
    public PersonaNatural findByNumeroDocumento(String numeroDocumento) {
        try {
            String hql = "select pn from PersonaNatural pn where pn.numeroDocumento = :numeroDocumento";
            Query query = em.createQuery(hql);
            query.setParameter("numeroDocumento", numeroDocumento);
            PersonaNatural pn = (PersonaNatural) query.getSingleResult();
            this.refresh(pn);
            return pn;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public void actualizarPersonaNatural(PersonaNatural personaNatural) throws Exception {
        String sql = "UPDATE\n"
                + "    catmin.persona_natural\n"
                + "SET\n";
        if (personaNatural.getNumeroDocumento() != null) {
            sql = sql + "    numero_documento = '" + personaNatural.getNumeroDocumento() + "',\n";
        }
        sql = sql + "    tipo_documento = " + personaNatural.getTipoDocumento() + ",\n";
        if (personaNatural.getNombre() != null) {
            sql = sql + "    nombre = '" + personaNatural.getNombre() + "',\n";
        }
        if (personaNatural.getApellido() != null) {
            sql = sql + "    apellido = '" + personaNatural.getApellido() + "',\n";
        }
        if (personaNatural.getRuc() != null) {
            sql = sql + "    ruc = '" + personaNatural.getRuc() + "',\n";
        }
        if (personaNatural.getGenero() != null) {
            sql = sql + "    genero = " + personaNatural.getGenero().getCodigoCatalogoDetalle() + ",\n";
        }
        if (personaNatural.getFechaNacimiento() != null) {
            sql = sql + "    fecha_nacimiento = '" + personaNatural.getFechaNacimiento() + "',\n";
        }
        if (personaNatural.getEdad() != null) {
            sql = sql + "    edad = " + personaNatural.getEdad() + ",\n";
        }
        if (personaNatural.getEmail() != null) {
            sql = sql + "    email = '" + personaNatural.getEmail() + "',\n";
        }
        if (personaNatural.getTelefono() != null) {
            sql = sql + "    telefono = '" + personaNatural.getTelefono() + "',\n";
        }
        if (personaNatural.getCelular() != null) {
            sql = sql + "    celular = '" + personaNatural.getCelular() + "',\n";
        }
        if (personaNatural.getDireccion() != null) {
            sql = sql + "    direccion = '" + personaNatural.getDireccion() + "',\n";
        }
        if (personaNatural.getCodigoLocalidad() != null) {
            sql = sql + "    codigo_localidad = " + personaNatural.getCodigoLocalidad().getCodigoLocalidad() + ",\n";
        }
        if (personaNatural.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + personaNatural.getCodigoProvincia() + ",\n";
        }
        if (personaNatural.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + personaNatural.getCodigoCanton() + ",\n";
        }
        if (personaNatural.getCodigoParroquia() != null) {
            sql = sql + "    codigo_parroquia = " + personaNatural.getCodigoParroquia() + ",\n";
        }
        if (personaNatural.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + personaNatural.getCampoReservado05() + "',\n";
        }
        if (personaNatural.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + personaNatural.getCampoReservado04() + "',\n";
        }
        if (personaNatural.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + personaNatural.getCampoReservado03() + "',\n";
        }
        if (personaNatural.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + personaNatural.getCampoReservado02() + "',\n";
        }
        if (personaNatural.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + personaNatural.getCampoReservado01() + "',\n";
        }
        if (personaNatural.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + personaNatural.getEstadoRegistro() + ",\n";
        }
        if (personaNatural.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + personaNatural.getFechaCreacion() + "',\n";
        }
        if (personaNatural.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + personaNatural.getUsuarioCreacion() + ",\n";
        }
        if (personaNatural.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + personaNatural.getFechaModificacion() + "',\n";
        }
        if (personaNatural.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + personaNatural.getUsuarioModificacion() + ",\n";
        }
        if (personaNatural.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + personaNatural.getCasilleroJudicial() + "'\n";
        }
        sql = sql + "WHERE\n"
                + "    numero_documento = '" + personaNatural.getNumeroDocumento() + "'";

        System.out.println("sql actualiza persona: " + sql);

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public PersonaDto obtenerPersonaPorNumIdentificacion(String numIdentificacion) {
        String sql = "select * from catmin.personas where numero_documento = '" + numIdentificacion + "'";
        Query query = em.createNativeQuery(sql);
        //query.setParameter("numIdentificacion", numIdentificacion);
        List<Object[]> listaTmp = query.getResultList();
        List<PersonaDto> listaFinal = new ArrayList<>();
        if (listaTmp != null && !listaTmp.isEmpty()) {
            for (Object[] fila : listaTmp) {
                PersonaDto personaDto = new PersonaDto();
                personaDto.setIdentificacion(fila[0] != null ? fila[0].toString() : null);
                personaDto.setNombres(fila[2] != null ? fila[2].toString() : null);
                personaDto.setApellidos(fila[3] != null ? fila[3].toString() : null);
                personaDto.setEmail(fila[12] != null ? fila[12].toString() : null);
                personaDto.setTelefono(fila[13] != null ? fila[13].toString() : null);
                personaDto.setDireccion(fila[15] != null ? fila[15].toString() : null);
                listaFinal.add(personaDto);
            }
        }
        if (listaFinal.isEmpty()) {
            return null;
        } else {
            return listaFinal.get(0);
        }
    }

}
