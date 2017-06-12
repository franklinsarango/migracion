/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.Folder;

/**
 * Bean para representar una carpeta del repositorio
 * @author cherrera
 */
public class AlfrescoFolderBean implements Serializable
{

    private String name;
    private String folderId;
    private String objectId;
    private List<AlfrescoFileBean> files;
    private AlfrescoFolderBean subFolder;
    private boolean root;
    private Map<String,String> additionalInfo;
    private Date date;
    private Folder folder;
    private boolean lastFolder;
    
     /**
     * Constructor
     * por defecto
     */
    public AlfrescoFolderBean()
    {
        files = new ArrayList<AlfrescoFileBean>();
        additionalInfo = new HashMap<String,String>();
        root = false;
        lastFolder = false;
    }
    
    /**
     * Constructor.
     * @param folder 
     */
    public AlfrescoFolderBean(Folder folder)
    {
        files = new ArrayList<AlfrescoFileBean>();
        additionalInfo = new HashMap<String,String>();
        root = false;
        this.folder=folder;
        this.folderId = folder.getId();
        lastFolder = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<AlfrescoFileBean> getFiles() {
        return files;
    }

    public void setFiles(List<AlfrescoFileBean> files) {
        this.files = files;
    }

    public AlfrescoFolderBean getSubFolder() {
        return subFolder;
    }

    public void setSubFolder(AlfrescoFolderBean subFolder) {
        this.subFolder = subFolder;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the folder
     */
    public Folder getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    /**
     * @return the lastFolder
     */
    public boolean isLastFolder() {
        return lastFolder;
    }

    /**
     * @param lastFolder the lastFolder to set
     */
    public void setLastFolder(boolean lastFolder) {
        this.lastFolder = lastFolder;
    }
}
