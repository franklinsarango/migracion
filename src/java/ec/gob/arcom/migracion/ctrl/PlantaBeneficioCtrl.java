/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.ctrl;

import ec.gob.arcom.migracion.constantes.ConstantesEnum;
import ec.gob.arcom.migracion.ctrl.base.BaseCtrl;
import ec.gob.arcom.migracion.dao.PlantaBeneficioDao;
import ec.gob.arcom.migracion.dao.UsuarioDao;
import ec.gob.arcom.migracion.dto.PlantaBeneficioDto;
import ec.gob.arcom.migracion.modelo.Auditoria;
import ec.gob.arcom.migracion.modelo.CatalogoDetalle;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficio;
import ec.gob.arcom.migracion.modelo.ConcesionPlantaBeneficioPK;
import ec.gob.arcom.migracion.modelo.Localidad;
import ec.gob.arcom.migracion.modelo.PersonaJuridica;
import ec.gob.arcom.migracion.modelo.PersonaNatural;
import ec.gob.arcom.migracion.modelo.PlantaBeneficio;
import ec.gob.arcom.migracion.modelo.Secuencia;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.AuditoriaServicio;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ConcesionPlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.LocalidadServicio;
import ec.gob.arcom.migracion.servicio.PersonaJuridicaServicio;
import ec.gob.arcom.migracion.servicio.PersonaNaturalServicio;
import ec.gob.arcom.migracion.servicio.PlantaBeneficioServicio;
import ec.gob.arcom.migracion.servicio.SecuenciaServicio;
import ec.gob.arcom.migracion.util.CedulaValidator;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Javier Coronel
 */
@ManagedBean
@ViewScoped
public class PlantaBeneficioCtrl extends BaseCtrl {

    @EJB
    private PlantaBeneficioServicio plantaBeneficioServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    @EJB
    private PlantaBeneficioDao plantaBeneficioDao;
    @EJB
    private LocalidadServicio localidadServicio;
    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private AuditoriaServicio auditoriaServicio;
    @EJB
    private PersonaNaturalServicio personaNaturalServicio;
    @EJB
    private PersonaJuridicaServicio personaJuridicaServicio;
    @EJB
    private ConcesionPlantaBeneficioServicio concesionPlantaBeneficioServicio;
    @EJB
    private SecuenciaServicio secuenciaServicio;
    @ManagedProperty(value = "#{loginCtrl}")
    private LoginCtrl login;

    private String codigoFiltro;
    private String codigoConcesionFiltro;
    private String cedulaTitularFiltro;
    private String nombreAreaFiltro;

    private List<SelectItem> provincias;
    private List<SelectItem> cantones;
    private List<SelectItem> parroquias;

    private List<ConcesionMinera> listaConcesiones;
    private ConcesionMinera concesionMineraPopup;
    private Localidad concesionProvincia;
    private Localidad concesionCanton;
    private Localidad concesionParroquia;
    
    private PlantaBeneficio plantaBeneficio;

    private List<PlantaBeneficioDto> listaRegistros;
    private boolean existeCodigoArcom = true;
    private boolean accionNuevaPB = true;
    private boolean mostrarPanelDatosPB = false;
    private boolean cedulaValida;

    private boolean codigoArcomNull;

    private boolean otorgado;
    private boolean inscrito;

    private PlantaBeneficio plantaBeneficioAnterior;
    private PersonaNatural personaNatural;

    private boolean perNatural;

    private PersonaJuridica personaJuridica;
    private String tipoMineria = "pb";
    private ConcesionMinera concesionMineraPB;
    private boolean concesionMinera;
    private boolean esEstadoArchivado;

    private Secuencia secuenciaConcesionMineraPB;
    
    public PlantaBeneficio getPlantaBeneficio() {
        if (plantaBeneficio == null) {
            String plantaBeneficioId = getHttpServletRequestParam("idItem");
            Long idPlantaBeneficio = null;
            if (plantaBeneficioId != null) {
                idPlantaBeneficio = Long.parseLong(plantaBeneficioId);
            }
            if (idPlantaBeneficio == null) {
                plantaBeneficio = new PlantaBeneficio();
                plantaBeneficio.setEstadoPlanta(new CatalogoDetalle());
                plantaBeneficio.setCodigoProcedenciaMaterial(new CatalogoDetalle());
                plantaBeneficio.setTipoPersona("PNA");
                plantaBeneficio.setCodigoMae(new CatalogoDetalle());
                plantaBeneficio.setCodigoSenagua(new CatalogoDetalle());
                codigoArcomNull = true;
            } else {
                plantaBeneficio = plantaBeneficioDao.findByPk(idPlantaBeneficio);
                plantaBeneficioAnterior = plantaBeneficioDao.findByPk(idPlantaBeneficio);
                if (plantaBeneficio.getCodigoProcedenciaMaterial() == null) {
                    plantaBeneficio.setCodigoProcedenciaMaterial(new CatalogoDetalle());
                }
                if (plantaBeneficio.getCategoriaPlanta() == null) {
                    plantaBeneficio.setCategoriaPlanta(new CatalogoDetalle());
                }
                if (plantaBeneficio.getEstadoPlanta() == null) {
                    plantaBeneficio.setEstadoPlanta(new CatalogoDetalle());
                }
                if (plantaBeneficio.getUnidadPeso() == null) {
                    plantaBeneficio.setUnidadPeso(new CatalogoDetalle());
                }
                if (plantaBeneficio.getCodigoArcom() == null || plantaBeneficio.getCodigoArcom().trim().isEmpty()) {
                    codigoArcomNull = true;
                } else {
                    codigoArcomNull = false;
                }
                if (plantaBeneficio.isConcesionMinera()) {
                    concesionMinera = true;
                    tipoMineria = "cm";
                } else {
                    concesionMinera = false;
                    tipoMineria = "pb";
                }
                if (plantaBeneficio.getCodigoMae()== null) {
                    plantaBeneficio.setCodigoMae(new CatalogoDetalle());
                }
                if (plantaBeneficio.getCodigoSenagua()== null) {
                    plantaBeneficio.setCodigoSenagua(new CatalogoDetalle());
                }
                if (plantaBeneficio.getCodigoConcesionUbicacionPlanta() != null) {
                    if (listaConcesiones == null) {
                        listaConcesiones = new ArrayList<>();
                    }
                    listaConcesiones.add(plantaBeneficio.getCodigoConcesionUbicacionPlanta());
                }
                accionNuevaPB = false;
                mostrarPanelDatosPB = true;
                validarEstadoPlantaBeneficio();
            }
        }
        return plantaBeneficio;
    }

    public void setPlantaBeneficio(PlantaBeneficio plantaBeneficio) {
        this.plantaBeneficio = plantaBeneficio;
    }

    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(String codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getCedulaTitularFiltro() {
        return cedulaTitularFiltro;
    }

    public void setCedulaTitularFiltro(String cedulaTitularFiltro) {
        this.cedulaTitularFiltro = cedulaTitularFiltro;
    }

    public String getNombreAreaFiltro() {
        return nombreAreaFiltro;
    }

    public void setNombreAreaFiltro(String nombreAreaFiltro) {
        this.nombreAreaFiltro = nombreAreaFiltro;
    }

    public void buscar() {
        listaRegistros = null;
        getListaRegistros();
    }

    public String editarRegistro() {
        PlantaBeneficioDto plantaBeneficioDtoItem = (PlantaBeneficioDto) getExternalContext().getRequestMap().get("reg");
        return "plantabeneficioform?faces-redirect=true&idItem=" + plantaBeneficioDtoItem.getCodigoPlantaBeneficio();
    }
    
    public String verRegistro() {
        PlantaBeneficioDto plantaBeneficioDtoItem = (PlantaBeneficioDto) getExternalContext().getRequestMap().get("reg");
        return "plantabeneficioformro?faces-redirect=true&idItem=" + plantaBeneficioDtoItem.getCodigoPlantaBeneficio();
    }

    public String guardarRegistro() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        
        if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() == null) {
            plantaBeneficio.setEstadoPlanta(null);
        }
        
        String mensajeError = validarCampos();
        if(mensajeError != null){           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, null));        
            return null;
        }
        
        try {
            //SE AGREGA LA CONCESION DONDE ESTA UBICADA LA PLANTA DE BENEFICIO
            plantaBeneficio.setCodigoConcesionUbicacionPlanta(null);
            if (listaConcesiones != null && listaConcesiones.size() > 0) {
                System.out.println("Concesion -----> :" + listaConcesiones.get(0).getCodigoArcom());
                plantaBeneficio.setCodigoConcesionUbicacionPlanta(listaConcesiones.get(0));
            }

            if (plantaBeneficio.getCodigoPlantaBeneficio() == null) {
                plantaBeneficio.setEstadoRegistro(true);
                plantaBeneficio.setFechaCreacion(new Date());
                plantaBeneficio.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                plantaBeneficio.setMigrada(true);
                plantaBeneficio.setTieneAutorizacion(true);
                
                if (tipoMineria.equals("cm")) {
                    //plantaBeneficio.setCodigoArcom(null);
                    generarCodigoArcomContrato();
                    plantaBeneficio.setTieneAutorizacion(false);
                    plantaBeneficio.setCodigoProvincia(concesionMineraPB.getCodigoProvincia());
                    plantaBeneficio.setCodigoCanton(concesionMineraPB.getCodigoCanton());
                    plantaBeneficio.setCodigoParroquida(concesionMineraPB.getCodigoParroquia());
                    plantaBeneficio.setFechaInscribe(concesionMineraPB.getFechaInscribe());
                    plantaBeneficio.setEstadoPlanta(concesionMineraPB.getEstadoConcesion());
                }
                plantaBeneficioServicio.create(plantaBeneficio);
                if (tipoMineria.equals("cm")) {
                    ConcesionPlantaBeneficioPK concesionPlantaBeneficioPk = new ConcesionPlantaBeneficioPK();
                    concesionPlantaBeneficioPk.setCodigoConcesion(concesionMineraPB.getCodigoConcesion());
                    concesionPlantaBeneficioPk.setCodigoPlantaBeneficio(plantaBeneficio.getCodigoPlantaBeneficio());
                    ConcesionPlantaBeneficio concesionPlantaBeneficio = new ConcesionPlantaBeneficio();
                    concesionPlantaBeneficio.setConcesionPlantaBeneficioPK(concesionPlantaBeneficioPk);
                    concesionPlantaBeneficio.setConcesionMinera(concesionMineraPB);
                    concesionPlantaBeneficio.setPlantaBeneficio(plantaBeneficio);
                    concesionPlantaBeneficio.setEstadoRegistro(true);
                    concesionPlantaBeneficio.setFechaCreacion(new Date());
                    concesionPlantaBeneficio.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                    concesionPlantaBeneficioServicio.create(concesionPlantaBeneficio);
                }
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setDetalleAnterior(plantaBeneficio.toString());
                auditoria.setDetalleCambios(null);
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro guardado con éxito", null));
            } else {
                System.out.println("entra actualizar pb");
                plantaBeneficio.setFechaModificacion(new Date());
                plantaBeneficio.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                plantaBeneficioServicio.actualizarPlantaBeneficio(plantaBeneficio);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setDetalleAnterior(plantaBeneficioAnterior.toString());
                auditoria.setDetalleCambios(plantaBeneficio.toString());
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro actualizado con éxito", null));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar el registro", ex.getMessage()));
            ex.printStackTrace();
        }
        return "plantasbeneficio";
    }
    
    public String validarCampos(){        
        String mensajeError = null;
        
        //SE VALIDA EL CAMPO FECHA DE OTORGAMIENTO
        if (plantaBeneficio.getEstadoPlanta() != null && plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_OTORGADO.getCodigo())) {
            if (!(plantaBeneficio.getFechaOtorga() != null)) {
                return "Fecha de Otorgamiento es Obligatorio";                
            }
        }
        
        //SE VALIDA EL CAMPO FECHA INSCRITO
        if (plantaBeneficio.getEstadoPlanta() != null && plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_INSCRITO.getCodigo())) {
            if (!(plantaBeneficio.getFechaOtorga() != null)) {
                return "Fecha de Otorgamiento es Obligatorio";                
            }
            if (!(plantaBeneficio.getFechaInscribe() != null)) {
                return "Fecha de Inscripción es Obligatorio";                
            }
            if (plantaBeneficio.getFechaOtorga().after(plantaBeneficio.getFechaInscribe())) {
                return "Fecha de Otorgamiento debe ser menor o igual a la fecha de Inscripción";                
            }
        }
                
        return mensajeError;        
    }

    public void generarCodigoArcomContrato() {
        secuenciaConcesionMineraPB = secuenciaServicio.obtenerPorNemonico("CONCESION_PLANTA_BENEFICIO_RGL" + login.getPrefijoRegional());
        plantaBeneficio.setCodigoArcom(formarCodigoConcesionMineraPB(secuenciaConcesionMineraPB.getValor()));
        long secuenciaSiguiente = secuenciaConcesionMineraPB.getValor() + 1;
        secuenciaConcesionMineraPB.setValor(secuenciaSiguiente);
        secuenciaServicio.update(secuenciaConcesionMineraPB);
    }
    
    protected String formarCodigoConcesionMineraPB(Long secuencial) {
        //prefijoComprobante es el prefijo de la regional
        String codigo = secuencial.toString();
        while (codigo.length() < 5) {
            codigo = "0" + codigo;
        }
        codigo = plantaBeneficio.getCodigoArcom() + "PB" + codigo;
        return codigo;
    }
    
    public List<SelectItem> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            List<Localidad> provinciasCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));

            for (Localidad provincia : provinciasCat) {
                provincias.add(new SelectItem(provincia.getCodigoLocalidad().toString(), provincia.getNombre().toUpperCase()));
            }
        }
        return provincias;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public List<SelectItem> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList<>();
            if (plantaBeneficio.getCodigoProvincia() == null) {
                return cantones;
            }
            Localidad catalogoCanton = localidadServicio.findByPk(Long.valueOf(plantaBeneficio.getCodigoProvincia().toString()));
            if (catalogoCanton == null || (catalogoCanton != null && catalogoCanton.getCodigoLocalidad() == null)) {
                return cantones;
            }
            List<Localidad> cantonCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoCanton.getCodigoLocalidad()));

            for (Localidad canton : cantonCat) {
                cantones.add(new SelectItem(canton.getCodigoLocalidad().toString(), canton.getNombre().toUpperCase()));
            }
        }
        return cantones;
    }

    public void setCantones(List<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        plantaBeneficio.setCodigoCanton(null);
        getCantones();
        getParroquias();
    }

    public List<SelectItem> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList<>();
            if (plantaBeneficio.getCodigoCanton() == null) {
                return parroquias;
            }
            Localidad catalogoParroquia = localidadServicio.findByPk(Long.valueOf(plantaBeneficio.getCodigoCanton().toString()));
            if (catalogoParroquia == null || (catalogoParroquia != null && catalogoParroquia.getCodigoInternacional() == null)) {
                return parroquias;
            }
            List<Localidad> parroquiaCat = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoParroquia.getCodigoLocalidad()));

            for (Localidad parroquia : parroquiaCat) {
                parroquias.add(new SelectItem(parroquia.getCodigoLocalidad().toString(), parroquia.getNombre().toUpperCase()));
            }
        }
        return parroquias;
    }

    public void setParroquias(List<SelectItem> parroquias) {
        this.parroquias = parroquias;
    }

    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }

    public LoginCtrl getLogin() {
        return login;
    }

    public void setLogin(LoginCtrl login) {
        this.login = login;
    }

    public List<PlantaBeneficioDto> getListaRegistros() {
        System.out.println("codigoFiltro: " + codigoFiltro);
        System.out.println("cedulaTitularFiltro: " + cedulaTitularFiltro);
        if (listaRegistros == null) {
            System.out.println("entra if");
            listaRegistros = plantaBeneficioServicio.obtenerListaPlantas(codigoFiltro, cedulaTitularFiltro, login.getUserName());
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<PlantaBeneficioDto> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public boolean isExisteCodigoArcom() {
        return existeCodigoArcom;
    }

    public void setExisteCodigoArcom(boolean existeCodigoArcom) {
        this.existeCodigoArcom = existeCodigoArcom;
    }

    public void validarCodigoArcom() {
        if (plantaBeneficio.getCodigoArcom() != null) {
            if (tipoMineria.equals("pb")) {
                concesionMinera = false;
                PlantaBeneficio pb = plantaBeneficioDao.findByCodigoArcom(plantaBeneficio.getCodigoArcom());
                if (pb == null) {
                    accionNuevaPB = false;
                    mostrarPanelDatosPB = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Código de planta beneficio existente, por favor ingrese uno nuevo", null));
                }
            } else if (tipoMineria.equals("cm")) {
                concesionMinera = true;
                concesionMineraPB = plantaBeneficioServicio.buscarPlantaEnConcesion(plantaBeneficio.getCodigoArcom());
                if (concesionMineraPB == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Código de concesión no existente, por favor ingrese uno nuevo", null));
                } else {
                    accionNuevaPB = false;
                    mostrarPanelDatosPB = true;
                    agregarDerechoMinero(concesionMineraPB);
                    return;
                }
            }
            //SE BORRA LA LISTA DE CONCESIONES
            listaConcesiones = new ArrayList<>();
        }
    }

    public boolean isCedulaValida() {
        if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal() != null) {
            if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() >= 10) {
                if (plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() == 13) {
                    String nuevaCed = plantaBeneficio.getNumeroDocumentoRepresentanteLegal().substring(0, plantaBeneficio.getNumeroDocumentoRepresentanteLegal().length() - 3);
                    //licenciaComercializacion.setNumeroDocumento(nuevaCed);
                    if (CedulaValidator.validate(nuevaCed)) {
                        return true;
                    } else {
                        plantaBeneficio.setNumeroDocumentoRepresentanteLegal(null);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Número de ruc inválido", null));
                        return false;
                    }
                }
                if (CedulaValidator.validate(plantaBeneficio.getNumeroDocumentoRepresentanteLegal())) {
                    cedulaValida = true;
                } else {
                    cedulaValida = false;
                    plantaBeneficio.setNumeroDocumentoRepresentanteLegal(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Número de cédula inválida", null));
                }
            } else {
                plantaBeneficio.setNumeroDocumentoRepresentanteLegal(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
            }
        }
        return cedulaValida;
    }

    public void setCedulaValida(boolean cedulaValida) {
        this.cedulaValida = cedulaValida;
    }

    public void validaCedula() {
        isCedulaValida();
    }

    public boolean isCodigoArcomNull() {
        return codigoArcomNull;
    }

    public void setCodigoArcomNull(boolean codigoArcomNull) {
        this.codigoArcomNull = codigoArcomNull;
    }

    public boolean isOtorgado() {
        if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() != null) {
            if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_OTORGADO.getCodigo())) {
                otorgado = true;
            } else {
                otorgado = false;
            }
            if (otorgado) {
                inscrito = true;
            }
        }
        return otorgado;
    }

    public void setOtorgado(boolean otorgado) {
        this.otorgado = otorgado;
    }

    public boolean isInscrito() {
        if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() != null) {
            if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_INSCRITO.getCodigo())
                    || plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_OTORGADO.getCodigo())) {
                inscrito = true;
            } else {
                inscrito = false;
            }
        }
        return inscrito;
    }

    public void agregarDerechoMinero(ConcesionMinera derechoMineroDto) {
        if (listaConcesiones == null) {
            listaConcesiones = new ArrayList<>();
        }
        listaConcesiones.add(derechoMineroDto);
    }

    public void eliminarDerechoMinero() {
        ConcesionMinera derechoMineroItem = (ConcesionMinera) getExternalContext().getRequestMap().get("reg");
        System.out.println("derechoMineroItem: " + derechoMineroItem.getCodigoArcom());
        listaConcesiones.remove(derechoMineroItem);
    }

    public List<ConcesionMinera> getDerechosMineros() {
        return listaConcesiones;
    }

    public void setDerechosMineros(List<ConcesionMinera> listaConcesiones) {
        this.listaConcesiones = listaConcesiones;
    }
    
    public void agregarConcesion() {
        if (listaConcesiones != null && listaConcesiones.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Solo se puede agregar una Concesión ", null));
            return;
        }
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').show()");
        codigoConcesionFiltro = null;
        concesionMineraPopup = null;
    }
    
    public void seleccionarConcesion() {
        agregarDerechoMinero(concesionMineraPopup);
        RequestContext.getCurrentInstance().execute("PF('dlgBusqCod').hide()");
        concesionMineraPopup = null;
    }
    
    public void buscarRegistro() {
        concesionMineraPopup = null;

        System.out.println("codigoConcesionFiltro: " + codigoConcesionFiltro);
        concesionMineraPopup = concesionMineraServicio.obtenerPorCodigoArcom(codigoConcesionFiltro);
        System.out.println("concesionMineraPopup: " + concesionMineraPopup);
        if (concesionMineraPopup != null) {
            concesionProvincia = localidadServicio.findByPk(concesionMineraPopup.getCodigoProvincia().longValue());
            concesionCanton = localidadServicio.findByPk(concesionMineraPopup.getCodigoCanton().longValue());
            concesionParroquia = localidadServicio.findByPk(concesionMineraPopup.getCodigoParroquia().longValue());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La concesión no existe", null));
        }
    }
    
    public ConcesionMinera getConcesionMineraPopup() {
        return concesionMineraPopup;
    }

    public void setConcesionMineraPopup(ConcesionMinera concesionMineraPopup) {
        this.concesionMineraPopup = concesionMineraPopup;
    }
    
    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }

    public PlantaBeneficio getPlantaBeneficioAnterior() {
        return plantaBeneficioAnterior;
    }

    public void setPlantaBeneficioAnterior(PlantaBeneficio plantaBeneficioAnterior) {
        this.plantaBeneficioAnterior = plantaBeneficioAnterior;
    }

    public void validarCedulaPersonaNatural() {
        String numDocumento = personaNatural.getNumeroDocumento();
        if (personaNatural.getNumeroDocumento().length() == 10) {
            if (!CedulaValidator.validate(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de cédula inválida", null));
                return;
            }
        } else if (personaNatural.getNumeroDocumento().length() == 13) {
            if (!CedulaValidator.validacionRUC(personaNatural.getNumeroDocumento())) {
                //personaNatural.setNumeroDocumento(null);
                personaNatural = null;
                getPersonaNatural();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Número de ruc inválido", null));
                return;
            }
        } else {
            //personaNatural.setNumeroDocumento(null);
            personaNatural = null;
            getPersonaNatural();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de documento inválido", null));
            return;
        }

        if (personaNatural.getNumeroDocumento() != null) {
            System.out.println("entra else");
            personaNatural = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
            System.out.println("personaNatural: " + personaNatural);
            if (personaNatural == null) {
                getPersonaNatural();
                personaNatural.setNumeroDocumento(numDocumento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de documento no existente", null));
                System.out.println("personaNatural.getNumeroDocumento(): " + personaNatural.getNumeroDocumento());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de documento existente", null));
            }
        }
    }

    public PersonaNatural getPersonaNatural() {
        if (personaNatural == null) {
            personaNatural = new PersonaNatural();
        }
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    public void guardarPersonaNatural() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());
        plantaBeneficio.setNumeroDocumentoRepresentanteLegal(personaNatural.getNumeroDocumento());
        plantaBeneficio.setNombreRepresentanteLegal(personaNatural.getNombre());
        plantaBeneficio.setApellidoRepresentanteLegal(personaNatural.getApellido());
        plantaBeneficio.setCasilleroJudicial(personaNatural.getCasilleroJudicial());
        plantaBeneficio.setTelefonoPlanta(personaNatural.getTelefono());
        plantaBeneficio.setDireccionPlanta(personaNatural.getDireccion());
        plantaBeneficio.setCorreoElectronico(personaNatural.getEmail());
        PersonaNatural pn = personaNaturalServicio.findByNumeroDocumento(personaNatural.getNumeroDocumento());
        try {
            if (pn == null) {
                personaNatural.setFechaCreacion(new Date());
                personaNatural.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.create(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            } else {
                personaNatural.setFechaModificacion(new Date());
                personaNatural.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                personaNaturalServicio.actualizarPersonaNatural(personaNatural);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pn.toString());
                auditoria.setDetalleCambios(personaNatural.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerNat').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
        }
    }

    public void llenaPopUpPersonaNatural() {
        getPersonaNatural();
        personaNatural.setNumeroDocumento(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
        /*personaNatural.setNombre(plantaBeneficio.getNombreRepresentanteLegal());
         personaNatural.setApellido(plantaBeneficio.getApellidoRepresentanteLegal());
         personaNatural.setCelular(plantaBeneficio.getTelefonoPlanta());
         personaNatural.setCasilleroJudicial(plantaBeneficio.getCasilleroJudicial());
         personaNatural.setDireccion(plantaBeneficio.getDireccionPlanta());
         personaNatural.setEmail(plantaBeneficio.getCorreoElectronico());*/
    }

    public boolean isPerNatural() {
        if (plantaBeneficio.getTipoPersona() != null) {
            if (plantaBeneficio.getTipoPersona().equals("PNA")) {
                perNatural = true;
            } else if (plantaBeneficio.getTipoPersona().equals("PJU")) {
                perNatural = false;
            }
        }
        return perNatural;
    }

    public void setPerNatural(boolean perNatural) {
        this.perNatural = perNatural;
    }

    public void validarTipoPersona() {
        isPerNatural();
    }

    public void validarCedulaPersonaJuridica() {
        String numRuc = personaJuridica.getRuc();
        if (personaJuridica.getRuc().length() != 13) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Se debe ingresar 13 dígitos para el ruc", null));
            //personaJuridica.setRuc(null);
            personaJuridica = null;
            getPersonaJuridica();
            return;
        }
        /*if (!CedulaValidator.validacionRUC(personaJuridica.getRuc())) {
            //personaJuridica.setRuc(null);
            personaJuridica = null;
            getPersonaJuridica();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de ruc inválido", null));
        } else {*/
            System.out.println("entra else");
            personaJuridica = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
            System.out.println("personaJuridica: " + personaJuridica);
            if (personaJuridica == null) {
                getPersonaJuridica();
                personaJuridica.setRuc(numRuc);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de ruc no existente", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Número de ruc existente", null));
            }
        //}
    }

    public PersonaJuridica getPersonaJuridica() {
        if (personaJuridica == null) {
            personaJuridica = new PersonaJuridica();
        }
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public void guardarPersonaJuridica() {
        Usuario us = usuarioDao.obtenerPorLogin(login.getUserName());

        plantaBeneficio.setNumeroDocumentoRepresentanteLegal(personaJuridica.getRuc());
        plantaBeneficio.setNombreRepresentanteLegal(personaJuridica.getNombreLegal());
        //plantaBeneficio.setApellidoRepresentanteLegal(personaJuridica.getNombreComercial());
        plantaBeneficio.setCasilleroJudicial(personaJuridica.getCasilleroJudicial());
        plantaBeneficio.setTelefonoPlanta(personaJuridica.getCelular());
        plantaBeneficio.setDireccionPlanta(personaJuridica.getDireccion());
        plantaBeneficio.setCorreoElectronico(personaJuridica.getEmail());
        PersonaJuridica pj = personaJuridicaServicio.findByRuc(personaJuridica.getRuc());
        try {
            if (pj == null) {
                personaJuridica.setFechaCreacion(new Date());
                personaJuridica.setUsuarioCreacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                localidad.setCodigoLocalidad(Long.valueOf("5"));
                personaJuridica.setCodigoLocalidad(null);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.create(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("INSERT");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha guardado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            } else {
                personaJuridica.setFechaModificacion(new Date());
                personaJuridica.setUsuarioModificacion(BigInteger.valueOf(us.getCodigoUsuario()));
                Localidad localidad = new Localidad();
                localidad.setCodigoLocalidad(Long.valueOf("5"));
                personaJuridica.setCodigoLocalidad(null);
                CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
                catalogoDetalle.setCodigoCatalogoDetalle(132L);
                personaJuridica.setClasePersona(catalogoDetalle);
                personaJuridicaServicio.actualizarPersonaJuridica(personaJuridica);
                Auditoria auditoria = new Auditoria();
                auditoria.setAccion("UPDATE");
                auditoria.setFecha(getCurrentTimeStamp());
                auditoria.setUsuario(BigInteger.valueOf(us.getCodigoUsuario()));
                auditoria.setDetalleAnterior(pj.toString());
                auditoria.setDetalleCambios(personaJuridica.toString());
                auditoriaServicio.create(auditoria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha actualizado la persona", null));
                RequestContext.getCurrentInstance().execute("PF('dlgPerJur').hide()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo guardar la persona", ex.getMessage()));
            System.out.println("ex.getMessage()" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void llenarPopUpPersonaJuridica() {
        getPersonaJuridica();
        personaJuridica.setRuc(plantaBeneficio.getNumeroDocumentoRepresentanteLegal());
    }

    public void validarCedulaRepresentanteLegal() {
        System.out.println("entra validarCedulaRepresentanteLegal");
        String numDocumento = personaJuridica.getDocumentoRepresentanteLegal();
        String nuevaCed = personaJuridica.getDocumentoRepresentanteLegal();
        if (personaJuridica.getDocumentoRepresentanteLegal().length() >= 10) {
            if (personaJuridica.getDocumentoRepresentanteLegal().length() == 13) {
                nuevaCed = personaJuridica.getDocumentoRepresentanteLegal().substring(0, personaJuridica.getDocumentoRepresentanteLegal().length() - 3);
            }
        }
        if (!CedulaValidator.validate(nuevaCed)) {
            personaJuridica.setDocumentoRepresentanteLegal(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Número de cédula inválido", null));
        }
    }

    public void validarEstadoPlantaBeneficio() {
        if (plantaBeneficio.getEstadoPlanta()!= null 
                && plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle() != null) {
            if (plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle().equals(ConstantesEnum.EST_ARCHIVADA.getCodigo())) {
                System.out.println("Estado Planta Beneficio: " + plantaBeneficio.getEstadoPlanta().getCodigoCatalogoDetalle());
                esEstadoArchivado = true;
            } else {
                esEstadoArchivado = false;
            }
        }
    }
    
    public String getTipoMineria() {
        return tipoMineria;
    }

    public void setTipoMineria(String tipoMineria) {
        this.tipoMineria = tipoMineria;
    }

    public boolean isConcesionMinera() {
        return concesionMinera;
    }

    public void setConcesionMinera(boolean concesionMinera) {
        this.concesionMinera = concesionMinera;
    }

    /**
     * @return the concesionProvincia
     */
    public Localidad getConcesionProvincia() {
        return concesionProvincia;
    }

    /**
     * @param concesionProvincia the concesionProvincia to set
     */
    public void setConcesionProvincia(Localidad concesionProvincia) {
        this.concesionProvincia = concesionProvincia;
    }

    /**
     * @return the concesionCanton
     */
    public Localidad getConcesionCanton() {
        return concesionCanton;
    }

    /**
     * @param concesionCanton the concesionCanton to set
     */
    public void setConcesionCanton(Localidad concesionCanton) {
        this.concesionCanton = concesionCanton;
    }

    /**
     * @return the concesionParroquia
     */
    public Localidad getConcesionParroquia() {
        return concesionParroquia;
    }

    /**
     * @param concesionParroquia the concesionParroquia to set
     */
    public void setConcesionParroquia(Localidad concesionParroquia) {
        this.concesionParroquia = concesionParroquia;
    }

    /**
     * @return the codigoConcesionFiltro
     */
    public String getCodigoConcesionFiltro() {
        return codigoConcesionFiltro;
    }

    /**
     * @param codigoConcesionFiltro the codigoConcesionFiltro to set
     */
    public void setCodigoConcesionFiltro(String codigoConcesionFiltro) {
        this.codigoConcesionFiltro = codigoConcesionFiltro;
    }

    /**
     * @return the mostrarPanelDatosPB
     */
    public boolean isMostrarPanelDatosPB() {
        return mostrarPanelDatosPB;
    }

    /**
     * @param mostrarPanelDatosPB the mostrarPanelDatosPB to set
     */
    public void setMostrarPanelDatosPB(boolean mostrarPanelDatosPB) {
        this.mostrarPanelDatosPB = mostrarPanelDatosPB;
    }

    /**
     * @return the accionNuevaPB
     */
    public boolean isAccionNuevaPB() {
        return accionNuevaPB;
    }

    /**
     * @param accionNuevaPB the accionNuevaPB to set
     */
    public void setAccionNuevaPB(boolean accionNuevaPB) {
        this.accionNuevaPB = accionNuevaPB;
    }

    public boolean isEsEstadoArchivado() {
        return esEstadoArchivado;
    }

    public void setEsEstadoArchivado(boolean esEstadoArchivado) {
        this.esEstadoArchivado = esEstadoArchivado;
    }
    
    

}
