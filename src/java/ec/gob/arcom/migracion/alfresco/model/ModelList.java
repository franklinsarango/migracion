/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.model;

import com.google.api.client.util.Key;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class ModelList<T extends Entry> implements Serializable
{
    @Key
    public ArrayList<T> entries;
        
    @Key
    public Pagination pagination;
}
