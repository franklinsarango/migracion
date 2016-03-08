/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.test;

import ec.gob.arcom.migracion.util.CedulaValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier Coronel
 */
public class testMain {
    
    private static final int NUM_PROVINCIAS = 24;
    // public static String rucPrueba = “1790011674001″;
    private static int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static int constante = 11;
    
    public static void main(String[] args) {
        String fechaStr1 = "2015-08-16";
        String fechaStr3 = "2015-09-03";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = null;
        Date fecha2 = new Date();
        Date fecha3 = null;
        try {
            fecha1 = sdf.parse(fechaStr1);
            fecha3 = sdf.parse(fechaStr3);
        } catch (ParseException ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fecha1.before(fecha2)) {
            System.out.println("fecha1 menor a fecha2");
        }
        if (fecha3.after(fecha2)) {
            System.out.println("fecha3 mayor a fecha2");
        }
        
        System.out.println("1722733027 válida??: " + CedulaValidator.validate("1722733027"));
        
        System.out.println("1792119588001 válido??: " + validacionRUC("1792119588001"));
    }
    
    public static Boolean validacionRUC(String ruc) {
        boolean resp_dato = false;
        final int prov = Integer.parseInt(ruc.substring(0, 2));
        if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
            resp_dato = false;
        }
        
        int[] d = new int[10];
        int suma = 0;
        
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(ruc.charAt(i) + "");
        }
        
        for (int i = 0; i < d.length - 1; i++) {
            d[i] = d[i] * coeficientes[i];
            suma += d[i];
        }
        
        int aux, resp;
        
        aux = suma % constante;
        resp = constante - aux;
        
        resp = (aux == 0) ? 0 : resp;
        
        if (resp == d[9]) {
            resp_dato = true;
        } else {
            resp_dato = false;
        }
        return resp_dato;
    }
    
}
