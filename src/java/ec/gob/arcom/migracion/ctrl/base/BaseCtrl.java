/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl.base;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Javier Coronel
 */
public class BaseCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    protected FacesContext getFacesContext() {
        return (FacesContext.getCurrentInstance());
    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }
    
    protected String getHttpServletRequestParam(String paramName) {
        HttpServletRequest r = ((HttpServletRequest) getExternalContext().getRequest());
        return r.getParameter(paramName);
    }
    
    protected String getLoggedUsername() {
        String remoteUser = getExternalContext().getRemoteUser();
        if (remoteUser != null) {
            return remoteUser;
        }
        return "invitado";
    }
    
    protected Timestamp getCurrentTimeStamp() {
        java.util.Date date = new java.util.Date();
        Timestamp fechaHora;
        fechaHora = new Timestamp(date.getTime());
        return fechaHora;
    }

}
