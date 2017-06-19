/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.DetalleFichaTecnica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface DetalleFichaTecnicaDao extends GenericDao<DetalleFichaTecnica, Long> {

    public List<DetalleFichaTecnica> listar();

    public List listarPorFichaTecnica(Long codigoFichaTecnica);

    public List<DetalleFichaTecnica> listarSociosPorFichaTecnica(Long codigoFichaTecnica);
    
}
