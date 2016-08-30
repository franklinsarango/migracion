/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dto.PreguntaDto;
import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.EncuestaPregunta;
import ec.gob.arcom.migracion.modelo.Respuesta;
import ec.gob.arcom.migracion.modelo.Opcion;
import ec.gob.arcom.migracion.modelo.Pregunta;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.EncuestaPreguntaServicio;
import ec.gob.arcom.migracion.servicio.RegistroPagoDetalleServicio;
import ec.gob.arcom.migracion.servicio.RespuestaServicio;
import ec.gob.arcom.migracion.servicio.EncuestaServicio;
import ec.gob.arcom.migracion.servicio.OpcionServicio;
import ec.gob.arcom.migracion.servicio.PreguntaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.OPTIONS;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Franklin Sarango
 */
@ManagedBean
@ViewScoped
public class EncuestaCtrl extends BaseCtrl {

    @EJB
    private RespuestaServicio respuestaServicio;
    @EJB
    private EncuestaServicio encuestaServicio;
    @EJB
    private EncuestaPreguntaServicio encuestaPreguntaServicio;
    @EJB
    private PreguntaServicio preguntaServicio;
    @EJB
    private OpcionServicio opcionServicio;
    @EJB
    private UsuarioServicio usurioServicio;

    //@ManagedProperty(value = "#{loginCtrl}")
    //private LoginCtrl login;
    private List<PreguntaDto> listaPreguntasDto;
    public Long codigoOpcionSelect;
    private Opcion opcionSelect;
    private String codigoOpcionPreguntaSelec;
    private Encuesta encuesta;
    private String numeroDocumento;
    private String sugerencia;

    @PostConstruct
    public void init() {
        numeroDocumento = getHttpServletRequestParam("numero_documento");
        String codigoEncuesta = getHttpServletRequestParam("id_encuesta");
        
        if (!(numeroDocumento == null || numeroDocumento.isEmpty())) {
            System.out.println("Numero Documento: " + numeroDocumento);
            listaPreguntasDto = new ArrayList<>();

            this.encuesta = encuestaServicio.findByCodigoEncuesta(1L);
            List<EncuestaPregunta> listaPreguntas = encuestaPreguntaServicio.findByCodigoEncuesta(encuesta);
            for (EncuestaPregunta pregunta : listaPreguntas) {
                //NO SE INSERTAN LAS PREGUNTAS TIPO TEXTO
                if (!pregunta.getCodigoPregunta().getNemonico().contains("PREGTEXTO")) {
                    PreguntaDto preguntaDto = new PreguntaDto();
                    preguntaDto.setPregunta(pregunta.getCodigoPregunta());
                    listaPreguntasDto.add(preguntaDto);
                }
            }

            //SE CARGA LAS OPCIONES A LAS PREGUNTAS
            if (listaPreguntasDto != null) {
                for (int i = 0; i < listaPreguntasDto.size(); i++) {
                    List<Opcion> listaOpciones = opcionServicio.findByCodigoPregunta(listaPreguntasDto.get(i).getPregunta());
                    listaPreguntasDto.get(i).setListaOpciones(listaOpciones);
                }
            }

        } else {
            FacesMessage msg = new FacesMessage("Error No Existe Numero De Cedula..");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("No Existe Numero Documento");
        }
    }

    public void onRowSelect(SelectEvent event) {
        Opcion opcion = ((Opcion) event.getObject());
        //FacesMessage msg = new FacesMessage("Car Selected", opcion.getDescripcion() + "|" + opcion.getCodigoPregunta().getDescripcion());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Car Selected: " + opcion.getDescripcion() + "|" + opcion.getCodigoPregunta().getDescripcion());

        if (listaPreguntasDto != null) {
            for (int i = 0; i < listaPreguntasDto.size(); i++) {
                if (listaPreguntasDto.get(i).getPregunta().getCodigoPregunta().equals(opcion.getCodigoPregunta().getCodigoPregunta())) {
                    listaPreguntasDto.get(i).setOpcionSeleccionada(opcion);
                    System.out.println("Se agrega la Opción: " + opcion.getDescripcion() + "|" + opcion.getCodigoPregunta().getDescripcion());
                }
            }
        }
    }

    public String enviarEncuesta() {

        //SE VERFICA QUE ESTEN SELECCIONADAS TODAS LAS PREGUNTAS
        boolean validacionError = false;
        for (PreguntaDto p : listaPreguntasDto) {
            if (p.getOpcionSeleccionada() == null) {
                System.out.println("Pregunta: " + p.getPregunta().getDescripcion());
                FacesMessage msg = new FacesMessage("Debe seleccionar una Opción en Todas las Preguntas","");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        }

        if (listaPreguntasDto != null) {
            for (int i = 0; i < listaPreguntasDto.size(); i++) {
                //for (Opcion opcion : listaPreguntasDto.get(i).getListaOpciones()) {
                Respuesta respuesta = new Respuesta();
                respuesta.setNumeroDocumento(numeroDocumento);
                respuesta.setCodigoEncuesta(encuesta);
                respuesta.setCodigoPregunta(listaPreguntasDto.get(i).getPregunta());
                respuesta.setCodigoOpcion(listaPreguntasDto.get(i).getOpcionSeleccionada());
                respuesta.setEstadoRegistro(true);
                respuesta.setFechaCreacion(getCurrentTimeStamp());
                respuestaServicio.create(respuesta);

                //}
            }
            
            //SE AGREGA LA PREGUNTA TIPO TEXTO SUGERENCIA
            Pregunta preguntaSugerencia = preguntaServicio.findByNemonico("PREGTEXTO5");
            System.out.println("Ingresa 1");
            if (preguntaSugerencia != null) {
                System.out.println("Ingresa 2");
                //SE CARGA LA OPCION DE LA PREGUNTA
                List<Opcion> listaOpciones = opcionServicio.findByCodigoPregunta(preguntaSugerencia);
                if (listaOpciones != null && listaOpciones.size() > 0) {
                    System.out.println("TEXTO SUGERENCIA:" + sugerencia);
                    System.out.println("TEXTO codigoOpcionPreguntaSelec:" + codigoOpcionPreguntaSelec);
                    
                    Respuesta respuesta = new Respuesta();
                    respuesta.setNumeroDocumento(numeroDocumento);
                    respuesta.setCodigoEncuesta(encuesta);
                    respuesta.setCodigoPregunta(preguntaSugerencia);
                    respuesta.setCodigoOpcion(listaOpciones.get(0));
                    respuesta.setObservaciones(sugerencia);
                    respuesta.setEstadoRegistro(true);
                    respuesta.setFechaCreacion(getCurrentTimeStamp());
                    respuestaServicio.create(respuesta);
                }
            }
            
            Usuario usuario = new Usuario();
            usuario.setNumeroDocumento(numeroDocumento);
            usuario.setCampoReservado02("1");
            usurioServicio.actualizarUsuario(usuario);
        }
        return "encuestaTerminada?faces-redirect=true";
    }
    
    public void seleccionarOpcion(Long codigoOpcion){
        Opcion opcion = opcionServicio.findByPk(codigoOpcion);
        System.out.println("Car Selected: " + opcion.getDescripcion() + "|" + opcion.getCodigoPregunta().getDescripcion());

        if (listaPreguntasDto != null) {
            for (int i = 0; i < listaPreguntasDto.size(); i++) {
                if (listaPreguntasDto.get(i).getPregunta().getCodigoPregunta().equals(opcion.getCodigoPregunta().getCodigoPregunta())) {
                    listaPreguntasDto.get(i).setOpcionSeleccionada(opcion);
                    System.out.println("Se agrega la Opción: " + opcion.getDescripcion() + "|" + opcion.getCodigoPregunta().getDescripcion());
                }
            }
        }
    }
    public List<PreguntaDto> getListaPreguntasDto() {
        return listaPreguntasDto;
    }

    /**
     * @param listaPreguntasDto the listaPreguntasDto to set
     */
    public void setListaPreguntasDto(List<PreguntaDto> listaPreguntasDto) {
        this.listaPreguntasDto = listaPreguntasDto;
    }

    /**
     * @return the codigoOpcionSelect
     */
    public Long getCodigoOpcionSelect() {
        return codigoOpcionSelect;
    }

    /**
     * @param codigoOpcionSelect the codigoOpcionSelect to set
     */
    public void setCodigoOpcionSelect(Long codigoOpcionSelect) {
        this.codigoOpcionSelect = codigoOpcionSelect;
    }

    /**
     * @return the opcionSelect
     */
    public Opcion getOpcionSelect() {
        return opcionSelect;
    }

    /**
     * @param opcionSelect the opcionSelect to set
     */
    public void setOpcionSelect(Opcion opcionSelect) {
        this.opcionSelect = opcionSelect;
    }

    /**
     * @return the codigoOpcionPreguntaSelec
     */
    public String getCodigoOpcionPreguntaSelec() {
        return codigoOpcionPreguntaSelec;
    }

    /**
     * @param codigoOpcionPreguntaSelec the codigoOpcionPreguntaSelec to set
     */
    public void setCodigoOpcionPreguntaSelec(String codigoOpcionPreguntaSelec) {
        this.codigoOpcionPreguntaSelec = codigoOpcionPreguntaSelec;
    }

    /**
     * @return the encuesta
     */
    public Encuesta getEncuesta() {
        return encuesta;
    }

    /**
     * @param encuesta the encuesta to set
     */
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the sugerencia
     */
    public String getSugerencia() {
        return sugerencia;
    }

    /**
     * @param sugerencia the sugerencia to set
     */
    public void setSugerencia(String sugerencia) {
        this.sugerencia = sugerencia;
    }

}
