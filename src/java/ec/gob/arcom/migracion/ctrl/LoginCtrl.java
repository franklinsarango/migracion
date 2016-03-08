/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.util.LDAPConexion;
import ec.gob.arcom.migracion.util.SSHA;
import ec.gob.arcom.migracionsadmin.utils.FacesUtil;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean(name = "loginCtrl")
@SessionScoped
public class LoginCtrl extends BaseCtrl {

    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private RegionalServicio regionalServicio;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginCtrl() {
    }

    private String userName;
    private String userPassword;
    private boolean logged = false;
    private boolean admin = false;
    private String regional;
    private String prefijoRegional;
    private boolean usuarioLectura;
    private boolean registroMinero;
    private boolean economico;
    private boolean economicoNacional;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isLogged() {
        return logged;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String loginAction() {
        boolean result;

        if (userName == null || userName.isEmpty() || userPassword == null || userPassword.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "¡ERROR!", "Ingrese usuario y clave"));
            return null;
        }

        try {
            result = this.obtenerUsuario(userName, userPassword);
            if (result) {
                HttpSession session = FacesUtil.getSession();
                session.setAttribute("username", userName);
                session.setAttribute("logged", logged);
                session.setAttribute("admin", admin);
                session.setAttribute("regional", regional);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", userName));
                return "index.xhtml?faces-redirect=true";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "¡ERROR!", "Usuario o clave incorrectos"));
        } catch (LDAPException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "¡ERROR!", ex.getMessage()));
            ex.printStackTrace();
        }
        return null;
    }

    public String logoutAction() {
        HttpSession session = FacesUtil.getSession();
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }

    @SuppressWarnings("null")
    private boolean obtenerUsuario(String userName, String userPassword) throws LDAPException {
        System.out.println("username: " + userName);
        System.out.println("userpwd: " + userPassword);
        //Usuario usr = this.usuarioDao.obtenerPorLogin(userName);
        //System.out.println("usr: " + usr.getApellido() + " " + usr.getNombre());

        LDAPEntry usr = null;
        try {
            usr = LDAPConexion.buscarUsuario(LDAPConexion.conectar(), userName);
            if (usr != null) {
                if (validarCredenciales(usr.getAttribute("userPassword").getStringValue(), userPassword)) {
                    Usuario uBd = usuarioDao.obtenerPorLogin(userName);
                    this.logged = true;
                    this.admin = true;
                    this.regional = regionalServicio.findByCedulaRucUsuario(userName)[0];
                    this.prefijoRegional = regionalServicio.findByCedulaRucUsuario(userName)[1];
                    if (uBd != null) {
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("UL")) {
                            this.usuarioLectura = true;
                        } else {
                            this.usuarioLectura = false;
                        }
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("RM")) {
                            this.registroMinero = true;
                        } else {
                            this.registroMinero = false;
                        }
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("UE")) {
                            this.economico = true;
                        } else {
                            this.economico = false;
                        }
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("UEN")) {
                            this.economicoNacional = true;
                        } else {
                            this.economicoNacional = false;
                        }
                    }
                    return true;
                }
            }
        } catch (LDAPException ex) {
            ex.printStackTrace();
            throw new LDAPException();
        }
        /*if (usr != null && usr.getCampoReservado05() != null) {
         if (usr.getCampoReservado05().equals(Crypt.cryptMD5(userPassword))) {
         this.logged = true;
         this.admin = true;
         this.regional = regionalServicio.findByCedulaRucUsuario(userName)[0];
         this.prefijoRegional = regionalServicio.findByCedulaRucUsuario(userName)[1];
         if (usr.getCampoReservado01() != null && usr.getCampoReservado01().equals("UL")) {
         this.usuarioLectura = true;
         } else {
         this.usuarioLectura = false;
         }
         return true;
         }
         }*/
        return false;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getPrefijoRegional() {
        return prefijoRegional;
    }

    public void setPrefijoRegional(String prefijoRegional) {
        this.prefijoRegional = prefijoRegional;
    }

    public boolean isUsuarioLectura() {
        return usuarioLectura;
    }

    public void setUsuarioLectura(boolean usuarioLectura) {
        this.usuarioLectura = usuarioLectura;
    }

    private boolean validarCredenciales(String ldapPassword, String userPassword) {
        SSHA ssha = SSHA.getInstance();
        if (ssha.checkDigest(ldapPassword, userPassword)) {
            return true;
        }
        return false;
    }

    public boolean isRegistroMinero() {
        return registroMinero;
    }

    public void setRegistroMinero(boolean registroMinero) {
        this.registroMinero = registroMinero;
    }

    public boolean isEconomico() {
        return economico;
    }

    public void setEconomico(boolean economico) {
        this.economico = economico;
    }

    public boolean isEconomicoNacional() {
        return economicoNacional;
    }

    public void setEconomicoNacional(boolean economicoNacional) {
        this.economicoNacional = economicoNacional;
    }

}
