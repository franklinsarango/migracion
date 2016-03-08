/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.util;

/**
 *
 * @author Javier Coronel
 */
public class CedulaValidator {

    static final int MULT = 2;
    static final int TOTAL_PROVINCES = 24;
    private static final int NUM_PROVINCIAS = 24;
    private static int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static int constante = 11;

    /**
     * Valida el n?mero de c?dula de Ecuador
     *
     * @param identificationNumber Solo d?gitos, sin guiones.
     *
     * @return
     */
    public static boolean validate(String identificationNumber) {

        boolean valid = false;

        // si no tiene 10 d?gitos es inv?lida
        if (identificationNumber.length() != 10) {
            return valid;
        }

        String province = identificationNumber.substring(0, 2);

        // si sus dos primeros d?gitos son inv?lidos
        if (Integer.parseInt(province) > TOTAL_PROVINCES) {
            return valid;
        }

        int totalEven = 0; // pares
        int totalOdd = 0; // impares

        // la ?ltima posici?n no cuenta solo es verificador
        int totalValidNumbers = identificationNumber.length() - 1;
        int verifier = Integer.parseInt(identificationNumber.charAt(9) + "");

        for (int i = 0; i < totalValidNumbers; i++) {
            int digit = Integer.parseInt(identificationNumber.charAt(i) + "");
            // System.out.println("digit:"+digit);
            if (i % 2 == 0) {// si son impares
                int product = digit * MULT;
                // System.out.println("product:"+product);
                if (product > 9) {
                    product = product - 9;
                }
                totalEven += product;
            } else { // si son pares
                totalOdd += digit;
                // System.out.println(">>>"+totalOdd);
            }
        }

        int total = totalOdd + totalEven;

        String totalString = String.valueOf(total + 10);

        // se verifica la decena superior
        if (totalString.length() > 1) {
            int first = Integer.parseInt(totalString.charAt(0) + "");
            total = Integer.parseInt(first + "0") - total;
            if (total == 10) {
                total = 0;
            }
        }

        int result = total;

		// si el n?mero verificador es igual al resultado del algoritmo
        // entonces es una c?dula v?lida
        if (result == verifier) {
            valid = true;
        }

        return valid;
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
