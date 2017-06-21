/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.servicio.DetalleFichaTecnicaServicio;
import ec.gob.arcom.migracion.servicio.FichaTecnicaServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mejiaw
 */
@Named(value = "actividadMineraRepCtrl")
@RequestScoped
public class ActividadMineraRepCtrl {
    @EJB
    private FichaTecnicaServicio fichaTecnicaServicio;
    @EJB
    private DetalleFichaTecnicaServicio detalleFichaTecnicaServicio;
    
    private List<FichaTecnica> fichasTecnicas;
    
    /**
     * Creates a new instance of ActividadMineraRepCtrl
     */
    public ActividadMineraRepCtrl() {
        cargarFichasTecnicas();
    }
    
    @PostConstruct
    public void inicializar() {
        
    }

    public List<FichaTecnica> getFichasTecnicas() {
        return fichasTecnicas;
    }

    public void setFichasTecnicas(List<FichaTecnica> fichasTecnicas) {
        this.fichasTecnicas = fichasTecnicas;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    private void cargarFichasTecnicas() {
        fichasTecnicas= fichaTecnicaServicio.listar();
    }
}
