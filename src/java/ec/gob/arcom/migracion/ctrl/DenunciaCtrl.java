/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.dto.DenunciaDto;
import ec.gob.arcom.migracion.mail.MailSender;
import ec.gob.arcom.migracion.modelo.Adjunto;
import ec.gob.arcom.migracion.modelo.Catalogo;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.Denuncia;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.Regional;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AdjuntoServicio;
import ec.gob.arcom.migracion.servicio.CatalogoDetalleServicio;
import ec.gob.arcom.migracion.servicio.CatalogoServicio;
import ec.gob.arcom.migracion.servicio.DenunciaServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.RegionalServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.servicio.UsuarioServicio;
import ec.gob.arcom.migracion.util.FacesUtil;
import ec.gob.arcom.migracion.util.UploadFileManager;
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
    @EJB
    private CatalogoServicio catalogoServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private DenunciaServicio denunciaServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @EJB
    private AdjuntoServicio adjuntoServicio;
    
    private boolean showMineriaIlegal;
    private boolean showInfraccion;
    private boolean showCohecho;
    private List<Usuario> funcionarios;
    private List<UploadedFile> archivosParaCargar;
    private Denuncia denuncia;
    private List<Denuncia> denuncias;
    private List<CatalogoDetalle> tiposDenuncia;
    private List<Localidad> provincias;
    private List<Localidad> cantones;
    private List<Localidad> parroquias;
    private List<Regional> regionales;
    private List<String> listaAdjuntosHtml;

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
        denuncia= new Denuncia();
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

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(List<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }
    
    public List<CatalogoDetalle> getTiposDenuncia() {
        if (tiposDenuncia == null) {
            Catalogo catalogo = catalogoServicio.findByNemonico("TIPODENUN");
            if (catalogo != null) {
                tiposDenuncia = catalogoDetalleServicio.obtenerPorCatalogo(catalogo.getCodigoCatalogo());
            }
        }
        return tiposDenuncia;
    }
    
    public List<Usuario> getFuncionarios() {
        if (funcionarios == null) {
            funcionarios= new ArrayList<Usuario>();
            if(denuncia.getRegional()==null) {
                return funcionarios;
            }
            funcionarios= usuarioServicio.listarUsuariosInternos(denuncia.getRegional().getCodigoRegional());
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
        if(denuncia.getTipoDenuncia().getNemonico().equals("DENMINILE")) {
            showMineriaIlegal= true;
            showInfraccion= false;
            showCohecho= false;
        } else if (denuncia.getTipoDenuncia().getNemonico().equals("DENINFFUN")) {
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
            if (denuncia.getProvincia() == null) {
                return cantones;
            }
            cantones = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(denuncia.getProvincia().getCodigoLocalidad()));
        }
        return cantones;
    }
    
    public List<Localidad> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList();
            if (denuncia.getCanton() == null) {
                return parroquias;
            }
            parroquias = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(denuncia.getCanton().getCodigoLocalidad()));
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
    
    public void saveDenuncia() {
        denuncia.setEstadoRegistro(true);
        denuncia.setFechaCreacion(Calendar.getInstance().getTime());
        denuncia.setUsuarioCreacion((long)1);
        denunciaServicio.create(denuncia);
        
        if(archivosParaCargar!=null && archivosParaCargar.size()>0) {
            guardarAdjuntos(denuncia.getCodigoDenuncia());
        }
        
        String msg= "Su denuncia ha sido enviada correctamente <br><br> Número de denuncia: <strong>" + denuncia.getCodigoDenuncia() + "</strong> <br> Teléfono de contacto: 07-3703400 ext. 4146";
        RequestContext.getCurrentInstance().showMessageInDialog(
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Denuncia enviada", msg));
        
        DenunciaDto dto= new DenunciaDto(denuncia);
        dto.setAdjuntos(listaAdjuntosHtml);
        sendMail(dto);
        reset();
    }
    
    private void reset() {
        denuncia= new Denuncia();
        showMineriaIlegal= false;
        showInfraccion= false;
        funcionarios= null;
        provincias= null;
        cantones= null;
        parroquias= null;
        archivosParaCargar= null;
    }
    
    private void sendMail(DenunciaDto d) {
        sendNewDenunciaMsg(ConstantesEnum.BUZON_DENUNCIAS.getDescripcion(), d);
    }
    
    private void sendNewDenunciaMsg(String destinatario, DenunciaDto d) {
        MailSender ms= new MailSender();
        String msg= getNewDenunciaMsg(d);
        try {
            ms.sendMailHTML("Notificación nueva denuncia", getNewDenunciaMsg(d), destinatario, ConstantesEnum.REMITENTE_BUZON_DENUNCIAS.getDescripcion());
            ms.sendMailHTML("Denuncia recibida", getNewDenunciaMsg(d), d.getCorreoDenunciante(), ConstantesEnum.REMITENTE_BUZON_DENUNCIAS.getDescripcion());
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
        if(d.getTipoDenuncia().equals("MINERIA ILEGAL")) {
            textHTML+="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
                    + "<tr>"
                    + "<td> <strong>Número denuncia: </strong> </td>"
                    + "<td> <em> " + d.getCodigoDenuncia() +" </em> </td>"
                    + "</tr>"
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
        } else if(d.getTipoDenuncia().equals("INFRACCIONES DE FUNCIONARIOS")) {
            textHTML+="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
                    + "<tr>"
                    + "<td> <strong>Número denuncia: </strong> </td>"
                    + "<td> <em> " + d.getCodigoDenuncia() +" </em> </td>"
                    + "</tr>"
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
                    + "<td> <strong>Regional: </strong> </td>"
                    + "<td> <em> " + d.getRegional() +" </em> </td>"
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
                    + "<td> <strong>Número denuncia: </strong> </td>"
                    + "<td> <em> " + d.getCodigoDenuncia() +" </em> </td>"
                    + "</tr>"
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
    
    private void listarAdjuntosHTML(List<Adjunto> adjuntos) {
        listaAdjuntosHtml= new ArrayList<>();
        if(adjuntos!=null) {
            for(Adjunto s : adjuntos) {
                String link=  "<a href=\" " + s.getUrlDocumento() +  "\">" + s.getNombreAdjunto() + "</a>";
                listaAdjuntosHtml.add(link);
            }
        }
        
    }
    
    private String listarAdjuntos(List<String> adjuntos) {
        String lista= "Ninguno";
        if(adjuntos!=null) {
            lista= "";
            for(String s : adjuntos) {
                lista+= s + "; <br>";
            }
        }
        return lista;
    }
    
    private void guardarAdjuntos(Long codigoDenuncia) {
        List<Adjunto> filesForSave= new ArrayList();
        for(UploadedFile f : archivosParaCargar) {
            Adjunto adj= UploadFileManager.subirArchivoRepositorio(f, "DENUNCIA", String.valueOf(codigoDenuncia), "DENUNCIA", (long)1);
            if(adj!=null) {
                filesForSave.add(adj);
            }
        }
        if(filesForSave.size()>0) {
            guardarAdjuntos(filesForSave);
            listarAdjuntosHTML(filesForSave);
        }
    }
    
    private void guardarAdjuntos(List<Adjunto> files) {
        for(Adjunto a : files) {
            Secuencia secuenciaAdjunto= secuenciaServicio.obtenerPorTabla("ADJUNTO");
            Long value= secuenciaAdjunto.getValor();
            a.setCodigoAdjunto(value);
            adjuntoServicio.create(a);
            secuenciaAdjunto.setValor(value + 1);
            secuenciaServicio.update(secuenciaAdjunto);
        }
    }
}
