/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.bean;

import com.google.api.client.http.GenericUrl;
import java.io.Serializable;
import org.apache.chemistry.opencmis.client.api.Document;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class AlfrescoDocumentBean implements Serializable
{
    private String documentUrl;
    private String documentId;
    private String documentName;
    private Document document;
    private GenericUrl genericUrl;
    private UrlBean urlBean;
    
    public AlfrescoDocumentBean()
    {
      //NOTHING TODO.
        urlBean = new UrlBean();
    }
    
    /**
     * Constructor
     * @param document
     */
    public AlfrescoDocumentBean(Document document)
    {
       this.document   = document;
       //this.documentId = document.getId();
    }

    /**
     * @return the documentUrl
     */
    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * @param documentUrl the documentUrl to set
     */
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    /**
     * @return the documentId
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * @return the documentName
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName the documentName to set
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * @return the genericUrl
     */
    public GenericUrl getGenericUrl() {
        return genericUrl;
    }

    /**
     * @param genericUrl the genericUrl to set
     */
    public void setGenericUrl(GenericUrl genericUrl) {
        this.genericUrl = genericUrl;
    }

    /**
     * @return the urlBean
     */
    public UrlBean getUrlBean() {
        return urlBean;
    }

    /**
     * @param urlBean the urlBean to set
     */
    public void setUrlBean(UrlBean urlBean) {
        this.urlBean = urlBean;
    }
}
