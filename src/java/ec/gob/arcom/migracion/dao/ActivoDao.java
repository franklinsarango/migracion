/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.Activo;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface ActivoDao extends GenericDao<Activo, Long> {

    public int listaActivosTotal(Long filtroId, String filtroDescripcion, String filtroTagArcom);
    
    public List<Activo> listaActivos(int tamanoPagina, int desplazamiento, Long filtroId, String filtroDescripcion, String filtroTagArcom);

    public List<Activo> findByTagArcom(String tagArcom);

    public void actualizar(Activo activo);
    
    public Long getMaxId();
    
}
