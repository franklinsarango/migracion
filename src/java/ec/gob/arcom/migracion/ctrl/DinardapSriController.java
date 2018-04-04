package ec.gob.arcom.migracion.ctrl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.gob.arcom.dinardap_sri.client.DinardapClient;
import ec.gob.arcom.dinardap_sri.client.RequestFactory;
import ec.gob.arcom.migracion.modelo.ConcesionPagoSri;
import ec.gob.arcom.migracion.modelo.Usuario;
import ec.gob.arcom.migracion.servicio.ConcesionMineraServicio;
import ec.gob.arcom.migracion.servicio.ConcesionPagoSriServicio;
import ec.gob.dinardap.interoperabilidad.interoperador.Columna;
import ec.gob.dinardap.interoperabilidad.interoperador.Entidad;
import ec.gob.dinardap.interoperabilidad.interoperador.Fila;
import ec.gob.dinardap.interoperabilidad.interoperador.Paquete;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
    private List<Fila> filteredFilas;
    private List<Columna> columnas;
    private String consulta;
    private String valorBuscar;
    private String nombreEntidad;
    
    private boolean tabla625= false;
    private boolean tabla626= false;
    private boolean tabla627= false;
    private boolean tabla628= false;
    private boolean tabla833= false;
    
    private SelectItem[] aniosFiscales;
    private String anioFiscal;
    private String mensaje;
    private boolean razonSocialSelected= false;
    private List<SelectItem> codigosImpuesto;
    private List<SelectItem> aniosFiscal833;
    private List<SelectItem> mesesFiscal833;
    private List<SelectItem> aniosFiscal626;
    private List<SelectItem> mesesFiscal626;
    
    /**
     * Creates a new instance of DinardapSriController
     */
    
    public DinardapSriController() {
        
    }
    
    @PostConstruct
    public void inicializar() {
        
    }
    
    public void consultarPatentes() {
        pagos= concesionPagoSriServicio.findByAnio(anioFiscal);
    }
    
    public void actualizarPagos() {
        Integer result= concesionPagoSriServicio.ejecutarFuncion(anioFiscal);
        if(result==1) {
            pagos= concesionPagoSriServicio.findByAnio(anioFiscal);
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
            Long idConcesion= pago.getConcesionMinera().getCodigoConcesion();
            String ruc= pago.getConcesionMinera().getDocumentoConcesionarioPrincipal();
            String codigoArcom= pago.getConcesionMinera().getCodigoArcom();
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
                                        BigDecimal valorPatente= new BigDecimal(valor);
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
        if(consulta!=null && consulta.equals("626")) {
            tabla625= false;
            tabla626= true;
            tabla627= false;
            tabla628= false;
            tabla833= false;
            consulta626();
        } else if(consulta!=null && consulta.equals("627")) {
            tabla625= false;
            tabla626= false;
            tabla627= true;
            tabla628= false;
            tabla833= false;
            consulta627();
        } else if(consulta!=null && consulta.equals("628")) {
            tabla625= false;
            tabla626= false;
            tabla627= false;
            tabla628= true;
            tabla833= false;
            consulta628();
        } else if(consulta!=null && consulta.equals("833")) {
            tabla625= false;
            tabla626= false;
            tabla627= false;
            tabla628= false;
            tabla833= true;
            consulta833();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un formulario"));
        }
    }
    
    public void consultarRise() {
        tabla625= true;
        tabla626= false;
        tabla627= false;
        tabla628= false;
        tabla833= false;
        consulta625();
    }
    
    public void consulta625() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta625(), "625").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta626() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta626(valorBuscar), "626").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta627() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta627(valorBuscar), "627").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta628() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta628(valorBuscar), "628").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
    }
    
    public void consulta833() {
        entidades= DinardapClient.consultar(RequestFactory.generarConsulta833(valorBuscar), "833").getEntidades().getEntidad();
        nombreEntidad= entidades.get(0).getNombre();
        filas= entidades.get(0).getFilas().getFila();
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

    public List<Fila> getFilteredFilas() {
        return filteredFilas;
    }

    public void setFilteredFilas(List<Fila> filteredFilas) {
        this.filteredFilas = filteredFilas;
    }
    
    public List<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<Columna> columnas) {
        this.columnas = columnas;
    }
    
    public String obtenerValor(Columna c) {
        if(c!=null) {
            return c.getValor();
        }
        return "";
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

    public boolean isTabla833() {
        return tabla833;
    }

    public void setTabla833(boolean tabla833) {
        this.tabla833 = tabla833;
    }

    public String getAnioFiscal() {
        return anioFiscal;
    }

    public void setAnioFiscal(String anioFiscal) {
        this.anioFiscal = anioFiscal;
    }

    public SelectItem[] getAniosFiscales() {
        aniosFiscales= getAnios();
        return aniosFiscales;
    }

    public void setAniosFiscales(SelectItem[] aniosFiscales) {
        this.aniosFiscales = aniosFiscales;
    }

    public boolean isRazonSocialSelected() {
        return razonSocialSelected;
    }

    public void setRazonSocialSelected(boolean razonSocialSelected) {
        this.razonSocialSelected = razonSocialSelected;
    }

    public String getMensaje() {
        mensaje= "Se eliminaran los datos del año " + anioFiscal + 
                " y se volveran a consultar al SRI, este proceso tardará varios minutos "
                + "¿Desea continuar?";
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<ConcesionPagoSri> getPagos() {
        return pagos;
    }

    public void setPagos(List<ConcesionPagoSri> pagos) {
        this.pagos = pagos;
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
    
    public String obtenerFechaConFormato(Date fecha) {
        return obtenerFechaConFormato("dd-MM-yyyy", fecha);
    }
    
    public String obtenerFechaConFormato(String formato, Date fecha) {
        SimpleDateFormat sdf= new SimpleDateFormat(formato);
        if(fecha!=null) {
            return sdf.format(fecha);
        }
        return "";
    }
    
    private SelectItem[] getAnios() {
        int count= 4;
        SelectItem[] anios= new SelectItem[count];
        int anioActual= Calendar.getInstance().get(Calendar.YEAR);
        
        for (int i=0; i<count; i++) {
            anios[i]= new SelectItem(String.valueOf(anioActual),String.valueOf(anioActual));
            anioActual--;
        }
        return anios;
    }
    
    public String obtenerMensajeMarcaAgua() {
        if(razonSocialSelected) {
            return "Ingrese el texto a consultar";
        }
        return "Ingrese el número de RUC a consultar";
    }
    
    public String obtenerMensajeBusqueda() {
        if(razonSocialSelected) {
            return "Debe ingresar el texto a consultar";
        }
        return "Debe ingresar el número de RUC a consultar";
    }
    
    public void actualizarRazonSocialSelected() {
        if(consulta!=null && consulta.equals("628")) {
            razonSocialSelected= true;
            valorBuscar= "";
        } else {
            razonSocialSelected= false;
        }
    }
    
    public Date obtenerFechaOrdenar(String valor) throws ParseException {
        if(valor!=null && valor.length()>0) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valor);
            } catch(Exception ex) {
                System.out.println("Error al dar formato a la fecha: " + valor);
                System.out.println(ex.toString());
            }
        }
        return Calendar.getInstance().getTime();
    }
    
    public List<SelectItem> getCodigosImpuesto() {
        codigosImpuesto= new ArrayList<>();
        if(filas!=null) {
            for(Fila f : filas) {
                String v= f.getColumnas().getColumna().get(8).getValor();
                if(v!=null && v.length()>0) {
                    boolean agregar= true;
                    
                    for(SelectItem ci : codigosImpuesto) {
                        if(ci.getValue().equals(v)) {
                            agregar= false;
                            break;
                        }
                    }
                    if(agregar) {
                        codigosImpuesto.add(new SelectItem(v, v));
                    }
                }
            }
        }
        return codigosImpuesto;
    }
    
    public List<SelectItem> getAniosFiscal833() {
        aniosFiscal833= new ArrayList<>();
        if(filas!=null) {
            for(Fila f : filas) {
                String v= f.getColumnas().getColumna().get(6).getValor();
                if(v!=null && v.length()>0) {
                    boolean agregar= true;
                    
                    for(SelectItem ci : aniosFiscal833) {
                        if(ci.getValue().equals(v)) {
                            agregar= false;
                            break;
                        }
                    }
                    if(agregar) {
                        aniosFiscal833.add(new SelectItem(v, v));
                    }
                }
            }
            aniosFiscal833= ordenarLista(aniosFiscal833);
        }
        return aniosFiscal833;
    }
    public List<SelectItem> getMesesFiscal833() {
        mesesFiscal833= new ArrayList<>();
        if(filas!=null) {
            for(Fila f : filas) {
                String v= f.getColumnas().getColumna().get(7).getValor();
                if(v!=null && v.length()>0) {
                    boolean agregar= true;
                    
                    for(SelectItem ci : mesesFiscal833) {
                        if(ci.getValue().equals(v)) {
                            agregar= false;
                            break;
                        }
                    }
                    if(agregar) {
                        mesesFiscal833.add(new SelectItem(v, v));
                    }
                }
            }
            mesesFiscal833= ordenarLista(mesesFiscal833);
        }
        
        return mesesFiscal833;
    }
    
    public List<SelectItem> getAniosFiscal626() {
        aniosFiscal626= new ArrayList<>();
        if(filas!=null) {
            for(Fila f : filas) {
                String v= f.getColumnas().getColumna().get(5).getValor();
                if(v!=null && v.length()>0) {
                    boolean agregar= true;
                    
                    for(SelectItem ci : aniosFiscal626) {
                        if(ci.getValue().equals(v)) {
                            agregar= false;
                            break;
                        }
                    }
                    if(agregar) {
                        aniosFiscal626.add(new SelectItem(v, v));
                    }
                }
            }
            aniosFiscal626= ordenarLista(aniosFiscal626);
        }
        return aniosFiscal626;
    }
    public List<SelectItem> getMesesFiscal626() {
        mesesFiscal626= new ArrayList<>();
        if(filas!=null) {
            for(Fila f : filas) {
                String v= f.getColumnas().getColumna().get(6).getValor();
                if(v!=null && v.length()>0) {
                    boolean agregar= true;
                    
                    for(SelectItem ci : mesesFiscal626) {
                        if(ci.getValue().equals(v)) {
                            agregar= false;
                            break;
                        }
                    }
                    if(agregar) {
                        mesesFiscal626.add(new SelectItem(v, v));
                    }
                }
            }
            mesesFiscal626= ordenarLista(mesesFiscal626);
        }
        
        return mesesFiscal626;
    }
    
    // Inicio del metodo de ordenamiento de la Burbuja
    public List<SelectItem> ordenarLista(List<SelectItem> lista) {        
        for (int i = 1; i <= lista.size(); i++) {
            for (int j = 0; j < lista.size() - i; j++) {
                int valor = j + 1;
                if (lista.get(j).getLabel().compareTo(lista.get(valor).getLabel()) > 0) {
                    SelectItem item = new SelectItem();
                    item = lista.get(j);
                    SelectItem item2 = new SelectItem();
                    item2 = lista.get(valor);
                    lista.set(j, item2);
                    lista.set(valor, item);
                }
            }
        }
        return lista;
    }
}
