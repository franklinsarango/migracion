/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.model;

import java.io.Serializable;
import com.google.api.client.util.Key;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class NetworkEntry extends Entry implements Serializable
{
      @Key
      public Network entry;
      
      
}
