/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import ec.gob.arcom.migracion.constantes.RolEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.modelo.UsuarioRol;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.UsuarioRolServicio;
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
    @EJB
    private UsuarioRolServicio usuarioRolServicio;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginCtrl() {
    }
    
    private Long codigoUsuario;
    private String userName;
    private String nombreUsuario;
    private String rolUsuario;
    private String userPassword;
    private boolean logged = false;
    private boolean admin = false;
    private String regional;
    private String prefijoRegional;
    private boolean usuarioLectura; //UL
    private boolean registroMinero; //RM
    private boolean registroMineroNacional; //RMN
    private boolean economicoRegional;  //UER
    private boolean economicoNacional;  //UEN
    private boolean usuarioCatastro;    //UC7
    
    //PERMISOS
    private boolean editarComprobante;  //UERR

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

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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
                session.setAttribute("codigoUsuario", codigoUsuario);
                session.setAttribute("username", userName);
                session.setAttribute("logged", logged);
                session.setAttribute("admin", admin);
                session.setAttribute("regional", regional);
                session.setAttribute("nombreUsuario", nombreUsuario);
                session.setAttribute("rolUsuario", rolUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombreUsuario));
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
                    UsuarioRol usRol = usuarioRolServicio.obtenerPorCodigoUsuuario(uBd.getCodigoUsuario());
                    this.nombreUsuario = uBd.getNombre() + " " + uBd.getApellido();
                    this.codigoUsuario= uBd.getCodigoUsuario();
                    this.rolUsuario = usRol.getRol().getDescripcion();
                    this.logged = true;
                    this.admin = true;
                    this.regional = regionalServicio.findByCedulaRucUsuario(userName)[0];
                    this.prefijoRegional = regionalServicio.findByCedulaRucUsuario(userName)[1];
                    this.editarComprobante = false;
                    if (uBd != null) {
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("UL")) {
                            this.usuarioLectura = true;
                        } else {
                            this.usuarioLectura = false;
                        }
                        if(usRol.getRol().getNemonico().equals(RolEnum.ROL_REGISTRADOR_REGIONAL.getNemonico())){
                            this.registroMinero = true;
                        } else {
                            this.registroMinero = false;
                        }
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("EDITAR_COMPROBANTE")) {
                            this.editarComprobante = true;
                        } else {
                            this.editarComprobante = false;
                        }
                        if(usRol.getRol().getNemonico().equals(RolEnum.ROL_ESPECIALISTA_ECONOMICO_REGIONAL.getNemonico())){
                            this.economicoRegional = true;
                            
                        } else {
                            this.economicoRegional = false;
                        }
                        if(usRol.getRol().getNemonico().equals(RolEnum.ROL_ESPECIALISTA_ECONOMICO_NACIONAL.getNemonico())){
                            this.economicoNacional = true;
                        } else {
                            this.economicoNacional = false;
                        }
                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("RMN")) {
                            this.registroMineroNacional = true;
                        } else {
                            this.registroMineroNacional = false;
                        }
//                        if (uBd.getCampoReservado01() != null && uBd.getCampoReservado01().equals("UC")) {
                        if(usRol.getRol().getNemonico().equals(RolEnum.ROL_TECNICO_CATASTRO.getNemonico()) ||
                               usRol.getRol().getNemonico().equals(RolEnum.ROL_TECNICO_CATASTRO_NACIONAL.getNemonico())){
                            this.usuarioCatastro = true;
                        } else {
                            this.usuarioCatastro = false;
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

    public boolean isEconomicoRegional() {
        return economicoRegional;
    }

    public void setEconomicoRegional(boolean economicoRegional) {
        this.economicoRegional = economicoRegional;
    }

    public boolean isEconomicoNacional() {
        return economicoNacional;
    }

    public void setEconomicoNacional(boolean economicoNacional) {
        this.economicoNacional = economicoNacional;
    }

    public boolean isUsuarioCatastro() {
        return usuarioCatastro;
    }

    public void setUsuarioCatastro(boolean usuarioCatastro) {
        this.usuarioCatastro = usuarioCatastro;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the rolUsuario
     */
    public String getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    /**
     * @return the registroMineroNacional
     */
    public boolean isRegistroMineroNacional() {
        return registroMineroNacional;
    }

    /**
     * @param registroMineroNacional the registroMineroNacional to set
     */
    public void setRegistroMineroNacional(boolean registroMineroNacional) {
        this.registroMineroNacional = registroMineroNacional;
    }

    /**
     * @return the editarComprobante
     */
    public boolean isEditarComprobante() {
        return editarComprobante;
    }

    /**
     * @param editarComprobante the editarComprobante to set
     */
    public void setEditarComprobante(boolean editarComprobante) {
        this.editarComprobante = editarComprobante;
    }

}
