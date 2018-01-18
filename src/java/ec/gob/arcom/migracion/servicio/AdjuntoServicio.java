/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.Adjunto;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Licencia;
import ec.gob.arcom.migracion.modelo.Operativo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface AdjuntoServicio extends GenericService<Adjunto, Long> {

    public List<Adjunto> findByOperativo(Operativo o);

    public List<Adjunto> findByFichaTecnica(FichaTecnica ft);

    public List<Adjunto> findByLicencia(Licencia l);
    
}
