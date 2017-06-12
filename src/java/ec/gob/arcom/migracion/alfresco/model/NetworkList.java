/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.model;

import com.google.api.client.util.Key;
import java.io.Serializable;


/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class NetworkList implements Serializable
{
   @Key
   public ModelList<NetworkEntry> list;   
   
  
 }
