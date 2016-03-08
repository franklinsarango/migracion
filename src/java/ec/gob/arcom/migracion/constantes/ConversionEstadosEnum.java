/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.constantes;

/**
 *
 * @author Javier Coronel
 */
public enum ConversionEstadosEnum {

    OTORGADO(42L, 242L), INSCRITA(46L, 243L), 
    //NO_OTORGADO(47L, 755L),
    //CADUCADO(44L, 247L), 
    //SOLICITUD_EXPIRADA(45L, 248L), 
    TRAMITE(35L, 241L),
    ARCHIVADA(47L, 246L),
    //SUBSANACION(41L, 755L),
    SUSPENDIDO(7L, 244L),
    ACUMULADA(1459L, 1458L);

    private ConversionEstadosEnum(Long codigo4, Long codigo19) {
        this.codigo4 = codigo4;
        this.codigo19 = codigo19;
    }

    private Long codigo4;
    private Long codigo19;

    public Long getCodigo4() {
        return codigo4;
    }

    public void setCodigo4(Long codigo4) {
        this.codigo4 = codigo4;
    }

    public Long getCodigo19() {
        return codigo19;
    }

    public void setCodigo19(Long codigo19) {
        this.codigo19 = codigo19;
    }

}
