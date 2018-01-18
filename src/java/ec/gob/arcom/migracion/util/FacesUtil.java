/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mejiaw
 */
public class FacesUtil {
    public static FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
    public static HttpSession getSession() {
        return (HttpSession) getFacesContext().
                getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().
                getExternalContext().getRequest();
    }
    
    public static void showInfoMessage(String msg, String msg2) {
        FacesContext context = getFacesContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg2));
    }
    
    public static void showWarnMessage(String msg, String msg2) {
        FacesContext context = getFacesContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg2));
    }
    
    public static void showErrorMessage(String msg, String msg2) {
        FacesContext context = getFacesContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg2));
    }
}
