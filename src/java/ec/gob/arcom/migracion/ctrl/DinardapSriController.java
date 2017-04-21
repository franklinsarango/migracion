package ec.gob.arcom.migracion.ctrl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.gob.arcom.dinardap_sri.client.DinardapClient;
import ec.gob.arcom.dinardap_sri.client.RequestFactory;
import ec.gob.arcom.migracion.modelo.ConcesionMinera;
import ec.gob.arcom.migracion.modelo.ConcesionPagoSri;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ConcesionPagoSriServicio;
import ec.gob.dinardap.interoperabilidad.interoperador.Columna;
import ec.gob.dinardap.interoperabilidad.interoperador.Entidad;
import ec.gob.dinardap.interoperabilidad.interoperador.Fila;
import ec.gob.dinardap.interoperabilidad.interoperador.Paquete;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@ViewScoped
public class DinardapSriController {
    @EJB
    private ConcesionPagoSriServicio concesionPagoSriServicio;
    @EJB
    private ConcesionMineraServicio concesionMineraServicio;
    
    private List<ConcesionPagoSri> pagos;
    
    private List<Entidad> entidades;
    private List<Fila> filas;
    private List<Columna> columnas;
    private String consulta;
    private String valorBuscar;
    private String nombreEntidad;
    
    private boolean tabla625= false;
    private boolean tabla626= false;
    private boolean tabla627= false;
    private boolean tabla628= false;
    
    
    private String anioFiscal;
    /**
     * Creates a new instance of DinardapSriController
     */
    
    public DinardapSriController() {
        
    }
    
    @PostConstruct
    public void inicializar() {
        pagos= concesionPagoSriServicio.findAll();
    }
    
    public void consultarPatentes() {
        Integer result= concesionPagoSriServicio.ejecutarFuncion(anioFiscal);
        if(result==1) {
            pagos= concesionPagoSriServicio.findAll();
            consultarPagos();
        } else if(result==0) {
            System.out.println("Ocurrio un error al llenar la tabla");
        } else {
            System.out.println("No se logro conectar a la base");
        }
    }
    
    private void consultarPagos() {
        for(ConcesionPagoSri pago : pagos) {
            Paquete p= null;
            String ruc= obtenerDocumentoConcesionario(pago.getCodigoConcesion());
            String codigoArcom= obtenerCodigoArcom(pago.getCodigoConcesion());
            if(ruc!=null && ruc.length()>0) {
                try {
                    p= DinardapClient.consultar(RequestFactory.generarConsulta627(ruc), "627");
                } catch(Exception ex) {
                    System.out.println("Ocurrio un error al consultar la concesion: " + codigoArcom);
                }
                
                if(p!=null) {
                    entidades= p.getEntidades().getEntidad();
                    if(entidades.size()>0) {
                        filas= entidades.get(0).getFilas().getFila();
                        for(Fila f : filas) {
                            if(codigoArcom.equals(obtenerValor(f.getColumnas().getColumna().get(7)))) {
                                if(anioFiscal.equals(obtenerValor(f.getColumnas().getColumna().get(6)))) {
                                    String valor= f.getColumnas().getColumna().get(12).getValor();
                                    if(valor!=null) {
                                        System.out.println("valor: " + valor);
                                        BigDecimal valorPatente= new BigDecimal(valor);
                                        System.out.println("valor patente: " + valorPatente);
                                        pago.setValorPagoSri(valorPatente);
                                    }
                                    String comprobante= f.getColumnas().getColumna().get(0).getValor();
                                    if(comprobante!=null) {
                                        pago.setComprobanteElectronicoSri(comprobante);
                                    }
                                    concesionPagoSriServicio.update(pago);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void consultar() {
        if(consulta!=null && consulta.equals("625")) {
            tabla625= true;
            tabla626= false;
            tabla627= false;
            tabla628= false;
            consulta625();
        } else if(consulta!=null && consulta.equals("626")) {
            tabla625= false;
            tabla626= true;
            tabla627= false;
            tabla628= false;
            consulta626();
        } else if(consulta!=null && consulta.equals("627")) {
            tabla625= false;
            tabla626= false;
            tabla627= true;
            tabla628= false;
            consulta627();
        } else if(consulta!=null && consulta.equals("628")) {
            tabla625= false;
            tabla626= false;
            tabla627= false;
            tabla628= true;
            consulta628();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una opción"));
        }
    }
    
    public void consulta625() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta625(), "625").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta626() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta626(valorBuscar), "626").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }
    
    public void consulta627() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta627(valorBuscar), "627").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }
    
    public void consulta628() {
        if(valorBuscar.length()>0) {
            entidades= DinardapClient.consultar(RequestFactory.generarConsulta628(valorBuscar), "628").getEntidades().getEntidad();
            nombreEntidad= entidades.get(0).getNombre();
            filas= entidades.get(0).getFilas().getFila();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar el valor a buscar"));
        }
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidad> entidades) {
        this.entidades = entidades;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public List<Fila> getFilas() {
        return filas;
    }

    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }

    public List<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<Columna> columnas) {
        this.columnas = columnas;
    }
    
    public String obtenerValor(Columna c) {
        return c.getValor();
    }

    public boolean isTabla625() {
        return tabla625;
    }

    public void setTabla625(boolean tabla625) {
        this.tabla625 = tabla625;
    }

    public boolean isTabla626() {
        return tabla626;
    }

    public void setTabla626(boolean tabla626) {
        this.tabla626 = tabla626;
    }

    public boolean isTabla627() {
        return tabla627;
    }

    public void setTabla627(boolean tabla627) {
        this.tabla627 = tabla627;
    }

    public boolean isTabla628() {
        return tabla628;
    }

    public void setTabla628(boolean tabla628) {
        this.tabla628 = tabla628;
    }

    public String getAnioFiscal() {
        return anioFiscal;
    }

    public void setAnioFiscal(String anioFiscal) {
        this.anioFiscal = anioFiscal;
    }

    public List<ConcesionPagoSri> getPagos() {
        return pagos;
    }

    public void setPagos(List<ConcesionPagoSri> pagos) {
        this.pagos = pagos;
    }
    
    public ConcesionMinera obtenerConcesionMinera(Long codigo) {
        return concesionMineraServicio.findByPk(codigo);
    }
    
    public String obtenerNombreConcesion(Long codigoConcesion) {
        String nombre= concesionMineraServicio.obtenerNombreConcesion(codigoConcesion);
        if(nombre!=null) {
            return nombre;
        }
        return "";
    }
    
    public String obtenerRegionalConcesion(Long codigoConcesion) {
        String regional= concesionMineraServicio.obtenerRegionalConcesion(codigoConcesion);
        if(regional!=null) {
            return regional;
        }
        return "";
    }
    
    public String obtenerDocumentoConcesionario(Long codigoConcesion) {
        String documento= concesionMineraServicio.obtenerDocumentoConcesionario(codigoConcesion);
        if(documento!=null) {
            return documento;
        }
        return "";
    }

    private String obtenerCodigoArcom(Long codigoConcesion) {
        String codigoArcom= concesionMineraServicio.obtenerCodigoArcom(codigoConcesion);
        if(codigoArcom!=null) {
            return codigoArcom;
        }
        return "";
    }
    
    public String obtenerValorConFormato(BigDecimal valor) {
        return obtenerValorConFormato("##0.00", valor);
    }
    
    public String obtenerValorConFormato(String formato, BigDecimal valor) {
        DecimalFormat df= new DecimalFormat(formato);
        Double dv;
        if(valor!=null) {
            dv= valor.doubleValue();
            return df.format(dv);
        }
        return "";
    }
}
