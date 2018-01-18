/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.mail;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;

/**
 *
 * @author mejiaw
 */
public class ConnectionData {
    private String userName;
    private String password;
    private String from;
    private String host;
    private String port;
    
    public ConnectionData() {
        from= ConstantesEnum.REMITENTE_CORREO_VACACIONES.getDescripcion();
        host= ConstantesEnum.IP_SERVIDOR_CORREO.getDescripcion();
        port= ConstantesEnum.PORT_SERVIDOR_CORREO.getDescripcion();
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
