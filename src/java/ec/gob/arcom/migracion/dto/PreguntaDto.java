/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.dto;

import ec.gob.arcom.migracion.modelo.Encuesta;
import ec.gob.arcom.migracion.modelo.Opcion;
import ec.gob.arcom.migracion.modelo.Pregunta;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Franklin Sarango
 */
public class PreguntaDto implements Serializable
{
    private Encuesta encuesta;
    private Usuario usuario;
    private Pregunta pregunta;
    private List<Opcion> listaOpciones;
    private Opcion opcionSeleccionada;
    private Opcion opcionSelectTable;
    private Long codigoOpcion;
    
    public PreguntaDto(){
        listaOpciones = new ArrayList<>();
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the listaOpciones
     */
    public List<Opcion> getListaOpciones() {
        return listaOpciones;
    }

    /**
     * @param listaOpciones the listaOpciones to set
     */
    public void setListaOpciones(List<Opcion> listaOpciones) {
        this.listaOpciones = listaOpciones;
    }

    /**
     * @return the opcionSeleccionada
     */
    public Opcion getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    /**
     * @param opcionSeleccionada the opcionSeleccionada to set
     */
    public void setOpcionSeleccionada(Opcion opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }

    /**
     * @return the codigoOpcion
     */
    public Long getCodigoOpcion() {
        return codigoOpcion;
    }

    /**
     * @param codigoOpcion the codigoOpcion to set
     */
    public void setCodigoOpcion(Long codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    /**
     * @return the opcionSelectTable
     */
    public Opcion getOpcionSelectTable() {
        return opcionSelectTable;
    }

    /**
     * @param opcionSelectTable the opcionSelectTable to set
     */
    public void setOpcionSelectTable(Opcion opcionSelectTable) {
        this.opcionSelectTable = opcionSelectTable;
    }

    
 
}
