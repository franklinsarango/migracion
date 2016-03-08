/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracionsadmin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mejiaw
 */
public class Crypt {
    private static final char[] HEXADECIMALES = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    public static String cryptMD5(String textoPlano) {
        try {
            MessageDigest msgdgt= MessageDigest.getInstance("MD5");
            
            byte[] bytes = msgdgt.digest(textoPlano.getBytes());
            StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
            
            for (int i = 0; i < bytes.length; i++) {
                int low = (int)(bytes[i] & 0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                strCryptMD5.append(HEXADECIMALES[high]);
                strCryptMD5.append(HEXADECIMALES[low]);
            }
            return strCryptMD5.toString();
        }catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
