/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.constantes;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public enum RolEnum 
{

    ROL_USUARIO_INTERNO("UINT","Usuario Interno","INT",1L),
    ROL_USUARIO_EXTERNO("UEXT","Usuario Externo","EXT",2L),
    ROL_ADMINISTRADOR("ADMIN","Administrador","INT",3L),
    ROL_USUARIO("USUARIO","Usuario","INT",4L),
    ROL_ABOGADO("ABOGADO","Abogado","INT",5L),
    ROL_COORDINADOR_REGIONAL("COORDREGIO","Coordinador Regional","INT",6L),
    ROL_ESPECIALISTA_ECONOMICO_REGIONAL("ESPECOREG","Especialista Economico Regional","INT",7L),
    ROL_ESPECIALISTA_ECONOMICO_NACIONAL("ECONAC","Especialista Economico Nacional","INT",30L),
    ROL_RECEPCIONISTA("RECEP","Recepcionista","INT",28L),
    ROL_SUBSECRETARIA_REGIONAL_MINAS("SUBSECREGION","Subsecretaria Regional de Minas","INT",9L),
    ROL_TECNICO("TECNICO","Tecnico","INT",10L),
    ROL_TECNICO_CATASTRO("TECNICOCAT","Técnico de Catastro","INT",11L),
    ROL_TECNICO_CATASTRO_NACIONAL("TECCATNAC","Tecnico de Catastro Nacional","INT",29L),
    ROL_SUBSECRETA_NACIONAL_DESARROLLO("SNDESA","Subsecretaría de Minería Artesanal y Pequeña Minería","INT",69L),      
    ROL_REGISTRADOR_REGIONAL("RGRGNL","Registrador Regional","INT",24L),
    ROL_REGISTRADOR_NACIONAL("RGRNAC","Registrador Nacional","INT",23L),
    ROL_ABOGADO_NACIONAL("ABOGNACIO","Abogado Nacional","INT",25L),
    ROL_DIRECTOR_EJECUTIVO("DIRECEJECUT","Director Ejecutivo","INT",26L),
    ROL_DIRECTOR_JURIDICO("DIJRD","Director Jurídico","INT",15L),
    ROL_RECEPCIONISTA_SUBSECRETARIA_GENERAL("RECEPSUBREG","Recepcionista Subsecretaría General","INT",16L),
    ROL_ABOGADO_SUBSECRETARIA_MINAS("ABGSRMN","Abogado de la Subsecretaría de Minas","INT",22L),
    ROL_ASISTENTE_SUBSECRETARIA("ASISUBSE","Asistente de la Subsecretaría","INT",29L),
    ROL_ASISTENTE_TALENTO_HUMANO("ASISTH","Asistente Talento Humano","INT",32L);
   
    
    
    private String nemonico;
    private String nombre;
    private String tipo;
    private Long id;
    
    RolEnum (String nemonico,String nombre,String tipo, Long id)
    {
       this.nemonico=nemonico;
       this.nombre=nombre;
       this.tipo=tipo;
       this.id = id;
    }

    /**
     * @return the nemonico
     */
    public String getNemonico() {
        return nemonico;
    }

    /**
     * @param nemonico the nemonico to set
     */
    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
