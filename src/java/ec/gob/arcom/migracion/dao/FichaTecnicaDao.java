/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.FichaTecnica;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface FichaTecnicaDao extends GenericDao<FichaTecnica, Long> {

    public List<FichaTecnica> listar();

    public List<FichaTecnica> listarPorUsuarioCreacion(Long codigoUsuario);

    public List<Usuario> listarPorUsuariosDistintos();

    public Long contarPorRegional(Regional r);

    public Long contarPorUsuarioCreacion(Usuario u);

    public Long contarPorProvincia(Localidad p);

    public List<Localidad> listarProvinciasDistintas();

    public List<Localidad> listarCantonesDistintosPorProvincia(Localidad l);

    public Long contarPorCanton(Localidad c);

    public List<FichaTecnica> listarPorUsuarioCreacion(Long codigoUsuario, CatalogoDetalle catdet);
    
}
