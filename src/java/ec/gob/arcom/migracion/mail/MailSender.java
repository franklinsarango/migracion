/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.mail;

import ec.gob.arcom.migracion.dto.DenunciaDto;
import ec.gob.arcom.migracion.modelo.Usuario;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mejiaw
 */
public class MailSender {
    
    private Properties getProperties(ConnectionData datos) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", datos.getHost());
        props.put("mail.smtp.port", datos.getPort());
        return props;
    }
    
    private Session getSession(final ConnectionData datos) {
        Session session = Session.getInstance(getProperties(datos),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(datos.getUserName(), datos.getPassword());
                    }
                });
        return session;
    }
    
    public String getNewTaskMsg(String userName, String numSolicitud, String solicitante) {
        String textHTML= "<p>Estimad@ " + userName + ",</p>"
                + "<p>Usted tiene una <strong>nueva tarea </strong>en el <em>Sistema de Gesti&oacute;n de Permisos y Vacaciones</em>, la misma que debe ser atendida a la brevedad posible.</p>"
                + "<p><strong>Referencia</strong> <br/>\n" 
                + "<strong>Solicitud Nro:</strong> " + numSolicitud + "<br/>\n"
                + "<strong>Solicitante:</strong> " + solicitante + "</p>";
        return textHTML;
    }
    
    public String getNotificationMsg(String userName, String numSolicitud, String estadoSolicitud){
        String textHTML= "<p>Estimad@ " + userName + ",</p>"
                + "<p>Su solicitud Nro. <strong>" + numSolicitud + "</strong> ha sido " + estadoSolicitud + ", <br/>"
                + "puede revisarla ingresando al <em>Sistema de Gesti&oacute;n de Permisos y Vacaciones</em>.</p>";
        return textHTML;
    }
    
    public Long sendMailHTML(String asunto, String contenido, String destinatario, String remitente) {
        Long result= (long) 0;
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public Long sendMailHTML(String asunto, String contenido, Usuario destinatario) {
        Long result= (long) 0;
        
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(datos.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario.getCorreoElectronico()));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public Long sendMailHTML(String asunto, String contenido, List<Usuario> destinatarios) {
        Long result= (long) 0;
        
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(datos.getFrom()));
            message.setRecipients(Message.RecipientType.TO, obtenerDestinatarios(destinatarios));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    private InternetAddress[] obtenerDestinatarios(List<Usuario> destinatarios) {
        String correos= "";
        for(Usuario u : destinatarios) {
            correos+= u.getCorreoElectronico() + ",";
        }
        
        if(correos.length()>0) {
            try {
                return InternetAddress.parse(correos);
            } catch (AddressException ex) {
                System.out.println("Ocurrio un error al obtener las direcciones de correo");
            }
        }
        return null;
    }
}