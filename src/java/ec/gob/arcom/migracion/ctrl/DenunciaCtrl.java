/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dto.DenunciaDto;
import ec.gob.arcom.migracion.mail.MailSender;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.DateUtil;
import ec.gob.arcom.migracion.util.FacesUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@ViewScoped
public class DenunciaCtrl {
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private RegionalServicio regionalServicio;
    
    private boolean showMineriaIlegal;
    private boolean showInfraccion;
    private boolean showCohecho;
    private String tipoDenuncia;
    private List<Usuario> funcionarios;
    private Usuario funcionario;
    private List<UploadedFile> archivosParaCargar;
    private DenunciaDto denuncia;
    private List<Localidad> provincias;
    private List<Localidad> cantones;
    private List<Localidad> parroquias;
    
    private List<Regional> regionales;
    private Regional regional;
    
    private Localidad provincia;
    private Localidad canton;
    private Localidad parroquia;

    /**
     * Creates a new instance of DenunciaController
     */
    public DenunciaCtrl() {
    }
    
    @PostConstruct
    public void init() {
        showMineriaIlegal= false;
        showInfraccion= false;
        showCohecho=false;
        denuncia= new DenunciaDto();
    }

    public boolean isShowMineriaIlegal() {
        return showMineriaIlegal;
    }

    public void setShowMineriaIlegal(boolean showMineriaIlegal) {
        this.showMineriaIlegal = showMineriaIlegal;
    }

    public boolean isShowInfraccion() {
        return showInfraccion;
    }

    public void setShowInfraccion(boolean showInfraccion) {
        this.showInfraccion = showInfraccion;
    }

    public boolean isShowCohecho() {
        return showCohecho;
    }

    public void setShowCohecho(boolean showCohecho) {
        this.showCohecho = showCohecho;
    }
    
    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public DenunciaDto getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaDto denuncia) {
        this.denuncia = denuncia;
    }
    
    public List<Usuario> getFuncionarios() {
        if (funcionarios == null) {
            funcionarios= new ArrayList<Usuario>();
            if(regional==null) {
                return funcionarios;
            }
            funcionarios= usuarioServicio.listarUsuariosInternos(regional.getCodigoRegional());
        }
        return funcionarios;
    }

    public List<UploadedFile> getArchivosParaCargar() {
        return archivosParaCargar;
    }

    public void setArchivosParaCargar(List<UploadedFile> archivosParaCargar) {
        this.archivosParaCargar = archivosParaCargar;
    }

    public void showFields() {
        if(tipoDenuncia.equals("Mineria ilegal")) {
            showMineriaIlegal= true;
            showInfraccion= false;
            showCohecho= false;
        } else if (tipoDenuncia.equals("Infracciones de funcionarios")) {
            showMineriaIlegal= false;
            showInfraccion= true;
            showCohecho= false;
        } else {
            showMineriaIlegal= false;
            showInfraccion= false;
            showCohecho= true;
        }
    }

    public List<Regional> getRegionales() {
        if (regionales == null) {
            regionales = regionalServicio.findActivos();
        }
        return regionales;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }
    
    public Localidad getProvincia() {
        return provincia;
    }

    public void setProvincia(Localidad provincia) {
        this.provincia = provincia;
    }

    public Localidad getCanton() {
        return canton;
    }

    public void setCanton(Localidad canton) {
        this.canton = canton;
    }

    public Localidad getParroquia() {
        return parroquia;
    }

    public void setParroquia(Localidad parroquia) {
        this.parroquia = parroquia;
    }
    
    public List<Localidad> getProvincias() {
        if (provincias == null) {
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            provincias = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));
        }
        return provincias;
    }
    
    public List<Localidad> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList();
            if (provincia == null) {
                return cantones;
            }
            cantones = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(provincia.getCodigoLocalidad()));
        }
        return cantones;
    }
    
    public List<Localidad> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList();
            if (canton == null) {
                return parroquias;
            }
            parroquias = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(canton.getCodigoLocalidad()));
        }
        return parroquias;
    }
    
    public void cargaFuncionarios() {
        funcionarios= null;
        getFuncionarios();
    }
    
    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        getCantones();
        getParroquias();
    }
    
    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }
    
    public void addArchivos(FileUploadEvent event) {
        boolean existe= false;
        if(archivosParaCargar==null) {
            archivosParaCargar= new ArrayList<UploadedFile>();
        }
        for(UploadedFile f : archivosParaCargar) {
            if(f.getFileName().equals(event.getFile().getFileName())) {
                existe= true;
            }
        }
        if(!existe) {
            archivosParaCargar.add(event.getFile());
        } else {
            FacesUtil.showErrorMessage("Error", "El archivo ya se encuentra en la lista");
        }
        RequestContext.getCurrentInstance().execute("PF('archivosfrmwg').hide();");
    }
    
    public void sendDenuncia() {
        denuncia.setTipoDenuncia(tipoDenuncia);
        if(tipoDenuncia.compareTo("Mineria ilegal")==0) {
            denuncia.setProvincia(provincia.getNombre());
            denuncia.setCanton(canton.getNombre());
            denuncia.setParroquia(parroquia.getNombre());
            denuncia.setNombreDenunciado("");
            denuncia.setNombreInvolucrado("");
        } else if(tipoDenuncia.compareTo("Infracciones de funcionarios")==0) {
            denuncia.setProvincia("");
            denuncia.setCanton("");
            denuncia.setParroquia("");
            denuncia.setSector("");
            denuncia.setNombreDenunciado(funcionario.getNombresCompletos());
            denuncia.setNombreInvolucrado("");
        } else {
            denuncia.setProvincia("");
            denuncia.setCanton("");
            denuncia.setParroquia("");
            denuncia.setSector("");
            denuncia.setNombreDenunciado("");
        }
        
        List<String> lista= new ArrayList<String>();
        if(archivosParaCargar!=null) {
            for(UploadedFile f : archivosParaCargar) {
                lista.add(f.getFileName());
            }
        }
        denuncia.setAdjuntos(lista);
        denuncia.setFechaHoraEnvio(DateUtil.obtenerFechaHoraConFormato(Calendar.getInstance().getTime()));
        sendMail(denuncia);
        RequestContext.getCurrentInstance().showMessageInDialog(
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Denuncia enviada", "Su denuncia ha sido enviada correctamente"));
        reset();
    }
    
    private void reset() {
        denuncia= new DenunciaDto();
        tipoDenuncia= "";
        showMineriaIlegal= false;
        showInfraccion= false;
        funcionario= null;
        funcionarios= null;
        provincia= null;
        canton= null;
        parroquia= null;
        provincias= null;
        cantones= null;
        parroquias= null;
        regional= null;
        archivosParaCargar= null;
    }
    
    private void sendMail(DenunciaDto d) {
        sendNewDenunciaMsg(ConstantesEnum.BUZON_DENUNCIAS.getDescripcion(), d);
    }
    
    private void sendNewDenunciaMsg(String destinatario, DenunciaDto d) {
        MailSender ms= new MailSender();
        try {
            ms.sendMailHTML("Notificación nueva denuncia", getNewDenunciaMsg(d), destinatario, ConstantesEnum.REMITENTE_BUZON_DENUNCIAS.getDescripcion());
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al enviar el correo: " + ex.toString());
        }
    }
    
    public String getPhoto(String numeroDocumento) {
        if(numeroDocumento==null || numeroDocumento.length()==0) {
            return "default.jpg";
        }
        return numeroDocumento + ".jpg";
    }
    
    public String getNewDenunciaMsg(DenunciaDto d) {
        String textHTML= "<p>";
        if(d.getTipoDenuncia().equals("Mineria ilegal")) {
            textHTML+="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
                    + "<tr>"
                    + "<td> <strong>Nombre denunciante: </strong> </td>"
                    + "<td> <em> " + d.getNombreDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Telefono denunciante: </strong> </td>"
                    + "<td> <em> " + d.getTelefonoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Correo denunciante: </strong> </td>"
                    + "<td> <em> " + d.getCorreoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Tipo denuncia: </strong> </td>"
                    + "<td> <em> " + d.getTipoDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Provincia: </strong> </td>"
                    + "<td> <em> " + d.getProvincia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Cantón: </strong> </td>"
                    + "<td> <em> " + d.getCanton() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Parroquia: </strong> </td>"
                    + "<td> <em> " + d.getParroquia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Sector: </strong> </td>"
                    + "<td> <em> " + d.getSector().toUpperCase() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Detalle denuncia: </strong> </td>"
                    + "<td> <em> " + d.getDetalleDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Fecha: </strong> </td>"
                    + "<td> <em> " + d.getFechaHoraEnvio() +" </em> </td>"
                    + "</tr>"
                    + "</table>"
                    + "<br>"
                    + "<strong>Adjuntos: </strong><br>" + listarAdjuntos(d.getAdjuntos())
                    + "<br>";
        } else if(d.getTipoDenuncia().equals("Infracciones de funcionarios")) {
            textHTML+="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
                    + "<tr>"
                    + "<td> <strong>Nombre denunciante: </strong> </td>"
                    + "<td> <em> " + d.getNombreDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Telefono denunciante: </strong> </td>"
                    + "<td> <em> " + d.getTelefonoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Correo denunciante: </strong> </td>"
                    + "<td> <em> " + d.getCorreoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Tipo denuncia: </strong> </td>"
                    + "<td> <em> " + d.getTipoDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Nombre denunciado: </strong> </td>"
                    + "<td> <em> " + d.getNombreDenunciado() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Detalle denuncia: </strong> </td>"
                    + "<td> <em> " + d.getDetalleDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Fecha: </strong> </td>"
                    + "<td> <em> " + d.getFechaHoraEnvio() +" </em> </td>"
                    + "</tr>"
                    + "</table>"
                    + "<br>"
                    + "<strong>Adjuntos: </strong><br>" + listarAdjuntos(d.getAdjuntos())
                    + "<br>";
        } else {
            textHTML+="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
                    + "<tr>"
                    + "<td> <strong>Nombre denunciante: </strong> </td>"
                    + "<td> <em> " + d.getNombreDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Telefono denunciante: </strong> </td>"
                    + "<td> <em> " + d.getTelefonoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Correo denunciante: </strong> </td>"
                    + "<td> <em> " + d.getCorreoDenunciante() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Tipo denuncia: </strong> </td>"
                    + "<td> <em> " + d.getTipoDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Nombre persona/empresa involucrada: </strong> </td>"
                    + "<td> <em> " + d.getNombreInvolucrado() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Detalle denuncia: </strong> </td>"
                    + "<td> <em> " + d.getDetalleDenuncia() +" </em> </td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td> <strong>Fecha: </strong> </td>"
                    + "<td> <em> " + d.getFechaHoraEnvio() +" </em> </td>"
                    + "</tr>"
                    + "</table>"
                    + "<br>"
                    + "<strong>Adjuntos: </strong><br>" + listarAdjuntos(d.getAdjuntos())
                    + "<br>";
        }
        return textHTML;
    }
    
    private String listarAdjuntos(List<String> adjuntos) {
        String lista= "Ninguno";
        if(adjuntos!=null) {
            for(String s : adjuntos) {
                lista+= s + "; <br>";
            }
        }
        return lista;
    }
}
