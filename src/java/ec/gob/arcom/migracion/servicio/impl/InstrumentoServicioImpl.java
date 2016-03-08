/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import ec.gob.arcom.migracion.dao.InstrumentoDao;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.Instrumento;
import ec.gob.arcom.migracion.modelo.LicenciaComercializacion;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.SujetoMinero;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.InstrumentoServicio;
import ec.gob.arcom.migracion.servicio.LicenciaComercializacionServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.SujetoMineroServicio;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "InstrumentoServicio")
public class InstrumentoServicioImpl extends GenericServiceImpl<Instrumento, Long>
        implements InstrumentoServicio {

    @EJB
    private InstrumentoDao instrumentoDao;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private LicenciaComercializacionServicio licenciaComercializacionServicio;
    @EJB
    private PlantaBeneficioServicio plantaBeneficioServicio;
    @EJB
    private SujetoMineroServicio sujetoMineroServicio;

    @Override
    public GenericDao<Instrumento, Long> getDao() {
        return instrumentoDao;
    }

    @Override
    public Instrumento obtenerPorCodigoInstrumento(long codigoInstrumento) {
        return instrumentoDao.obtenerPorCodigoInstrumento(codigoInstrumento);
    }

    @Override
    public void actualizarInstrumento(Instrumento instrumento) throws Exception {
        instrumentoDao.actualizarInstrumento(instrumento);
    }

    @Override
    public void guardarTodo(Instrumento instrumento, ConcesionMinera concesionMinera,
            LicenciaComercializacion licenciaComercializacion, PlantaBeneficio plantaBeneficio,
            SujetoMinero sujetoMinero) throws Exception {
        this.create(instrumento);
        this.actualizarTiposDeRegistro(concesionMinera, licenciaComercializacion, plantaBeneficio, instrumento, sujetoMinero);
    }

    @Override
    public void actualizarTiposDeRegistro(ConcesionMinera concesionMinera, LicenciaComercializacion licenciaComercializacion, 
            PlantaBeneficio plantaBeneficio, Instrumento instrumento, SujetoMinero sujetoMinero) throws Exception {
        if (instrumento.getConcesionMinera() != null) {
            concesionMineraServicio.actualizarConcecionMinera(concesionMinera);
        } else if (instrumento.getLicenciaComercializacion() != null) {
            licenciaComercializacionServicio.actualizarLicenciaComercializacion(licenciaComercializacion);
        } else if (instrumento.getPlantaBeneficio() != null) {
            plantaBeneficioServicio.actualizarPlantaBeneficio(plantaBeneficio);
        } else if (instrumento.getSujetoMinero() != null) {
            sujetoMineroServicio.actualizarSujetoMinero(sujetoMinero);
        }
    }

    @Override
    public void actualizarTodo(Instrumento instrumento, ConcesionMinera concesionMinera, LicenciaComercializacion licenciaComercializacion, 
            PlantaBeneficio plantaBeneficio, SujetoMinero sujetoMinero) throws Exception {
        this.actualizarInstrumento(instrumento);
        this.actualizarTiposDeRegistro(concesionMinera, licenciaComercializacion, plantaBeneficio, instrumento, sujetoMinero);
    }

}
