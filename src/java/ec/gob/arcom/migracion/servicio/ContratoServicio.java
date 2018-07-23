/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.servicio;

import com.saviasoft.persistence.util.service.GenericService;
import ec.gob.arcom.migracion.modelo.Contrato;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface ContratoServicio extends GenericService<Contrato, Long> {

    public Contrato contratoUsuarioEstado(Usuario usuario, Long estadoContrato);
    
    public List<Contrato> listaContratoUsuarioEstado(Usuario usuario);

    public List<Contrato> listar();
    
}
