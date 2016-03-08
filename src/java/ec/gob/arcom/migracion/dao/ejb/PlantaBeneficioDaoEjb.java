/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao.ejb;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dto.PlantaBeneficioDto;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "PlantaBeneficioDao")
public class PlantaBeneficioDaoEjb extends GenericDaoEjbEl<PlantaBeneficio, Long> implements
        PlantaBeneficioDao {

    public PlantaBeneficioDaoEjb() {
        super(PlantaBeneficio.class);
    }

    @Override
    public PlantaBeneficio findByPk(Long codigoPlanta) {
        try {
            String hql = "select pb from PlantaBeneficio pb where pb.codigoPlantaBeneficio = :codigoPlantaBeneficio";
            Query query = em.createQuery(hql);
            query.setParameter("codigoPlantaBeneficio", codigoPlanta);
            PlantaBeneficio plantaBeneficio = (PlantaBeneficio) query.getSingleResult();
            this.refresh(plantaBeneficio);
            return plantaBeneficio;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public PlantaBeneficio findByCodigoArcom(String codigoArcom) {
        try {
            String hql = "select pb from PlantaBeneficio pb where pb.codigoArcom = :codigoArcom";
            Query query = em.createQuery(hql);
            query.setParameter("codigoArcom", codigoArcom);
            PlantaBeneficio pb = (PlantaBeneficio) query.getSingleResult();
            this.refresh(pb);
            return pb;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    @Override
    public List<PlantaBeneficioDto> obtenerListaPlantas(String codigoArcom, String identificacion, String usuario) {
        System.out.println("codigoArcom dao: " + codigoArcom);
        System.out.println("identificacion dao: " + identificacion);
        String sql = "select\n"
                + "codigo_planta_beneficio,\n"
                + "codigo_arcom,\n"
                + "nombre_planta_beneficio,\n"
                + "categoria_planta,\n"
                + "numero_documento_representante_legal,\n"
                + "nombre_representante_legal,\n"
                + "apellido_representante_legal,\n"
                + "telefono_planta,\n"
                + "direccion_planta,\n"
                + "casillero_judicial,\n"
                + "codigo_provincia,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = p.codigo_provincia) as provincia_inec,\n"
                + "codigo_canton,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = p.codigo_canton) as canton_inec,\n"
                + "codigo_parroquida,\n"
                + "(select nombre from catmin.localidad where codigo_localidad = p.codigo_parroquida) as parroquia_inec,\n"
                + "material_a_explotar,\n"
                + "volumen_a_explotar,\n"
                + "(select nombre from catmin.catalogo_detalle where codigo_catalogo_detalle = estado_planta) as estado,\n"
                + "estado_planta as estado_codigo,\n"
                + "tipo_planta,\n"
                + "capacidad_instalada,\n"
                + "capacidad_operativa,\n"
                + "balance_metalurgico,\n"
                + "ley_cabeza,\n"
                + "ley_concentrado,\n"
                + "tonelaje_tratamiento,\n"
                + "tonelaje_concentrado,\n"
                + "tonelaje_colas,\n"
                + "tipo_producto_final,\n"
                + "peso_producto_final,\n"
                + "unidad_peso,\n"
                + "recuperacion,\n"
                + "relavera,\n"
                + "ancho_relavera,\n"
                + "longitud_relavera,\n"
                + "fecha_creacion,\n"
                + "fecha_otorga,\n"
                + "fecha_inscribe\n"
                + "from catmin.planta_beneficio p\n"
                + "where 1 = 1 and p.codigo_provincia in (select lcr.codigo_localidad from catmin.localidad_regional lcr where lcr.codigo_regional = (select r.codigo_regional from catmin.regional r, catmin.localidad_regional lr, catmin.usuario where numero_documento = '" + usuario +"'\n" +
"                                 and r.codigo_regional = lr.codigo_regional and lr.codigo_localidad = codigo_provincia)) and migrada = true\n";
        if (codigoArcom != null && !codigoArcom.isEmpty()) {
            sql = sql + "and codigo_arcom like '%" + codigoArcom + "%'\n";
            //sql.concat("and codigo_arcom like '%").concat(codigoArcom).concat("%'\n");
        }
        if (identificacion != null && !identificacion.isEmpty()) {
            sql = sql + "and numero_documento_representante_legal like '%" + identificacion + "%'\n";
            //sql.concat("and numero_documento_representante_legal like '%").concat(identificacion).concat("%'\n");
        }
        System.out.println("sql plantas: " + sql);
        Query query = em.createNativeQuery(sql);
        List<Object[]> listaTmp = query.getResultList();
        List<PlantaBeneficioDto> listaFinal = new ArrayList<>();

        for (Object[] fila : listaTmp) {
            PlantaBeneficioDto pb = new PlantaBeneficioDto();
            pb.setCodigoPlantaBeneficio(fila[0].toString());
            pb.setCodigoArcom(fila[1] != null ? fila[1].toString() : null);
            pb.setNombrePlantaBeneficio(fila[2] != null ? fila[2].toString() : null);
            pb.setNumDocumentoRepLegal(fila[4] != null ? fila[4].toString() : null);
            pb.setNombreRepLegal(fila[5] != null ? fila[5].toString() : null);
            pb.setApellidoRepLegal(fila[6] != null ? fila[6].toString() : null);
            pb.setTelefono(fila[7] != null ? fila[7].toString() : null);
            pb.setDireccion(fila[8] != null ? fila[8].toString() : null);
            pb.setCasilleroJudicial(fila[9] != null ? fila[9].toString() : null);
            pb.setProvincia(fila[11] != null ? fila[11].toString() : null);
            pb.setCanton(fila[13] != null ? fila[13].toString() : null);
            pb.setParroquia(fila[15] != null ? fila[15].toString() : null);
            pb.setEstado(fila[18] != null ? fila[18].toString() : null);
            listaFinal.add(pb);
        }
        return listaFinal;
    }

    @Override
    public void actualizarPlantaBeneficio(PlantaBeneficio plantaBeneficio) throws Exception {

        String sql = "UPDATE\n"
                + "    catmin.planta_beneficio\n"
                + "SET\n";
        if (plantaBeneficio.getCodigoPlantaBeneficio() != null) {
            sql = sql + "    codigo_planta_beneficio = " + plantaBeneficio.getCodigoPlantaBeneficio() + ",\n";
        }
        if (plantaBeneficio.getNombrePlantaBeneficio() != null) {
            sql = sql + "    nombre_planta_beneficio = '" + plantaBeneficio.getNombrePlantaBeneficio() + "',\n";
        }
        if (plantaBeneficio.getCategoriaPlanta() != null && plantaBeneficio.getCategoriaPlanta().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    categoria_planta = " + plantaBeneficio.getCategoriaPlanta().getCodigoCatalogoDetalle() + ",\n";
        }
        if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal() != null) {
            sql = sql + "    numero_documento_representante_legal = '" + plantaBeneficio.getNumeroDocumentoRepresentanteLegal() + "',\n";
        }
        if (plantaBeneficio.getNombreRepresentanteLegal() != null) {
            sql = sql + "    nombre_representante_legal = '" + plantaBeneficio.getNombreRepresentanteLegal() + "',\n";
        }
        if (plantaBeneficio.getApellidoRepresentanteLegal() != null) {
            sql = sql + "    apellido_representante_legal = '" + plantaBeneficio.getApellidoRepresentanteLegal() + "',\n";
        }
        if (plantaBeneficio.getTelefonoPlanta() != null) {
            sql = sql + "    telefono_planta = '" + plantaBeneficio.getTelefonoPlanta() + "',\n";
        }
        if (plantaBeneficio.getDireccionPlanta() != null) {
            sql = sql + "    direccion_planta = '" + plantaBeneficio.getDireccionPlanta() + "',\n";
        }
        if (plantaBeneficio.getCasilleroJudicial() != null) {
            sql = sql + "    casillero_judicial = '" + plantaBeneficio.getCasilleroJudicial() + "',\n";
        }
        if (plantaBeneficio.getCodigoProvincia() != null) {
            sql = sql + "    codigo_provincia = " + plantaBeneficio.getCodigoProvincia() + ",\n";
        }
        if (plantaBeneficio.getCodigoCanton() != null) {
            sql = sql + "    codigo_canton = " + plantaBeneficio.getCodigoCanton() + ",\n";
        }
        if (plantaBeneficio.getCodigoParroquida() != null) {
            sql = sql + "    codigo_parroquida = " + plantaBeneficio.getCodigoParroquida() + ",\n";
        }
        if (plantaBeneficio.getMaterialAExplotar() != null) {
            sql = sql + "    material_a_explotar = '" + plantaBeneficio.getMaterialAExplotar() + "',\n";
        }
        if (plantaBeneficio.getVolumenAExplotar() != null) {
            sql = sql + "    volumen_a_explotar = " + plantaBeneficio.getVolumenAExplotar() + ",\n";
        }
        if (plantaBeneficio.getEstadoPlanta() != null && plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    estado_planta = " + plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() + ",\n";
        }
        if (plantaBeneficio.getTipoPlanta() != null) {
            sql = sql + "    tipo_planta = '" + plantaBeneficio.getTipoPlanta() + "',\n";
        }
        if (plantaBeneficio.getCapacidadInstalada() != null) {
            sql = sql + "    capacidad_instalada = " + plantaBeneficio.getCapacidadInstalada() + ",\n";
        }
        if (plantaBeneficio.getCapacidadOperativa() != null) {
            sql = sql + "    capacidad_operativa = " + plantaBeneficio.getCapacidadOperativa() + ",\n";
        }
        if (plantaBeneficio.getBalanceMetalurgico() != null) {
            sql = sql + "    balance_metalurgico = '" + plantaBeneficio.getBalanceMetalurgico() + "',\n";
        }
        if (plantaBeneficio.getLeyCabeza() != null) {
            sql = sql + "    ley_cabeza = '" + plantaBeneficio.getLeyCabeza() + "',\n";
        }
        if (plantaBeneficio.getLeyConcentrado() != null) {
            sql = sql + "    ley_concentrado = '" + plantaBeneficio.getLeyConcentrado() + "',\n";
        }
        if (plantaBeneficio.getTonelajeTratamiento() != null) {
            sql = sql + "    tonelaje_tratamiento = " + plantaBeneficio.getTonelajeTratamiento() + ",\n";
        }
        if (plantaBeneficio.getTonelajeConcentrado() != null) {
            sql = sql + "    tonelaje_concentrado = " + plantaBeneficio.getTonelajeConcentrado() + ",\n";
        }
        if (plantaBeneficio.getTonelajeColas() != null) {
            sql = sql + "    tonelaje_colas = " + plantaBeneficio.getTonelajeColas() + ",\n";
        }
        if (plantaBeneficio.getTipoProductoFinal() != null) {
            sql = sql + "    tipo_producto_final = '" + plantaBeneficio.getTipoProductoFinal() + "',\n";
        }
        if (plantaBeneficio.getPesoProductoFinal() != null) {
            sql = sql + "    peso_producto_final = " + plantaBeneficio.getPesoProductoFinal() + ",\n";
        }
        if (plantaBeneficio.getUnidadPeso() != null && plantaBeneficio.getUnidadPeso().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    unidad_peso = " + plantaBeneficio.getUnidadPeso().getCodigoCatalogoDetalle() + ",\n";
        }
        if (plantaBeneficio.getRecuperacion() != null) {
            sql = sql + "    recuperacion = " + plantaBeneficio.getRecuperacion() + ",\n";
        }
        if (plantaBeneficio.getRelavera() != null) {
            sql = sql + "    relavera = '" + plantaBeneficio.getRelavera() + "',\n";
        }
        if (plantaBeneficio.getAnchoRelavera() != null) {
            sql = sql + "    ancho_relavera = " + plantaBeneficio.getAnchoRelavera() + ",\n";
        }
        if (plantaBeneficio.getLongitudRelavera() != null) {
            sql = sql + "    longitud_relavera = " + plantaBeneficio.getLongitudRelavera() + ",\n";
        }
        if (plantaBeneficio.getCampoReservado10() != null) {
            sql = sql + "    campo_reservado_10 = '" + plantaBeneficio.getCampoReservado10() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado09() != null) {
            sql = sql + "    campo_reservado_09 = '" + plantaBeneficio.getCampoReservado09() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado08() != null) {
            sql = sql + "    campo_reservado_08 = '" + plantaBeneficio.getCampoReservado08() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado07() != null) {
            sql = sql + "    campo_reservado_07 = '" + plantaBeneficio.getCampoReservado07() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado06() != null) {
            sql = sql + "    campo_reservado_06 = '" + plantaBeneficio.getCampoReservado06() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado05() != null) {
            sql = sql + "    campo_reservado_05 = '" + plantaBeneficio.getCampoReservado05() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado04() != null) {
            sql = sql + "    campo_reservado_04 = '" + plantaBeneficio.getCampoReservado04() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado03() != null) {
            sql = sql + "    campo_reservado_03 = '" + plantaBeneficio.getCampoReservado03() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado02() != null) {
            sql = sql + "    campo_reservado_02 = '" + plantaBeneficio.getCampoReservado02() + "',\n";
        }
        if (plantaBeneficio.getCampoReservado01() != null) {
            sql = sql + "    campo_reservado_01 = '" + plantaBeneficio.getCampoReservado01() + "',\n";
        }
        if (plantaBeneficio.getEstadoRegistro() != null) {
            sql = sql + "    estado_registro = " + plantaBeneficio.getEstadoRegistro() + ",\n";
        }
        if (plantaBeneficio.getFechaCreacion() != null) {
            sql = sql + "    fecha_creacion = '" + plantaBeneficio.getFechaCreacion() + "',\n";
        }
        if (plantaBeneficio.getUsuarioCreacion() != null) {
            sql = sql + "    usuario_creacion = " + plantaBeneficio.getUsuarioCreacion() + ",\n";
        }
        if (plantaBeneficio.getFechaModificacion() != null) {
            sql = sql + "    fecha_modificacion = '" + plantaBeneficio.getFechaModificacion() + "',\n";
        }
        if (plantaBeneficio.getUsuarioModificacion() != null) {
            sql = sql + "    usuario_modificacion = " + plantaBeneficio.getUsuarioModificacion() + ",\n";
        }
        if (plantaBeneficio.getCodigoArcom() != null) {
            sql = sql + "    codigo_arcom = '" + plantaBeneficio.getCodigoArcom() + "',\n";
        }
        if (plantaBeneficio.getFechaOtorga() != null) {
            sql = sql + "    fecha_otorga = '" + plantaBeneficio.getFechaOtorga() + "',\n";
        } else {
            sql = sql + "    fecha_otorga = " + null + ",\n";
        } 
        if (plantaBeneficio.getFechaInscribe() != null) {
            sql = sql + "    fecha_inscribe = '" + plantaBeneficio.getFechaInscribe() + "',\n";
        } else {
            sql = sql + "    fecha_inscribe = " + null + ",\n";
        }
        sql = sql + "    mae = " + plantaBeneficio.getMae() + ",\n";
        sql = sql + "    senagua = " + plantaBeneficio.getSenagua() + " ,\n";
        if (plantaBeneficio.getObsActoAdministrativo() != null) {
            sql = sql + "    obs_acto_administrativo = '" + plantaBeneficio.getObsActoAdministrativo() + "',\n";
        }
        if (plantaBeneficio.getCorreoElectronico() != null) {
            sql = sql + "    correo_electronico = '" + plantaBeneficio.getCorreoElectronico() + "',\n";
        }
        if (plantaBeneficio.getCodigoProcedenciaMaterial() != null && plantaBeneficio.getCodigoProcedenciaMaterial().getCodigoCatalogoDetalle() != null) {
            sql = sql + "    codigo_procedencia_material = " + plantaBeneficio.getCodigoProcedenciaMaterial().getCodigoCatalogoDetalle() + ",\n";
        }
        if (plantaBeneficio.getZona() != null) {
            sql = sql + "    zona = '" + plantaBeneficio.getZona() + "',\n";
        }
        if (plantaBeneficio.getPlazo() != null) {
            sql = sql + "    plazo = " + plantaBeneficio.getPlazo() + ",\n";
        }
        if (plantaBeneficio.getTipoPersona() != null) {
            sql = sql + "    tipo_persona = '" + plantaBeneficio.getTipoPersona() + "',\n";
        }
        sql = sql + "    migrada = true\n"
                + "WHERE\n"
                + "    codigo_planta_beneficio = " + plantaBeneficio.getCodigoPlantaBeneficio();

        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public ConcesionMinera buscarPlantaEnConcesion(String codigoArcom) {
        try {
            String hql = "select cm from ConcesionMinera cm where cm.codigoArcom = :codigoArcom and cm.codigoTipoMineria.codigoTipoMineria = 3";
            Query query = em.createQuery(hql);
            query.setParameter("codigoArcom", codigoArcom);
            ConcesionMinera cm = (ConcesionMinera) query.getSingleResult();
            return cm;
        } catch (NoResultException nre) {
            return null;
        }
    }

}
