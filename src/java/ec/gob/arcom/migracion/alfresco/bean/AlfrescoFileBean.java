/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.bean;


import ec.gob.arcom.migracion.alfresco.AlfrescoConstants;
import org.apache.chemistry.opencmis.client.api.Document;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cherrera
 */
public class AlfrescoFileBean implements Serializable
{
    private String objectId;
    private String name;
    private String extension;
    private String fullName;
    private String path;
    private File file;
    private Long len;
    private byte[] data;
    private InputStream stream;
    private Date date;
    private String mime;
    private String mimeType;
    private Map<String,String> additionalInfo;
    private InputStream inputStream;
    private Document document;
    private String downloadName = AlfrescoConstants.DOWNLOAD_NAME;
    private String downloadId = AlfrescoConstants.DOWNLOAD_ID;
    private String description;
    
     /**
     * Constructor 
     * por defecto
     */
    public AlfrescoFileBean()
    {
       additionalInfo = new HashMap<String,String>();
       name           = "";
       extension      = "";
       
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.setData(data);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDownloadName() {
        return downloadName;
    }

    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    public String getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

    

    /**
     * @return the stream
     */
    public InputStream getStream() {
        return stream;
    }

    /**
     * @param stream the stream to set
     */
    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    /**
     * @return the description
     */
    public String getDescription() 
    {
        if(description==null)
        {
           description=name;
        }
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
   
    
}
