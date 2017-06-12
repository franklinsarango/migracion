/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.bean;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import java.io.Serializable;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class UrlBean implements Serializable
{
    private GenericUrl genericUrl;
    private HttpRequest httpRequest;
    private String url;
    private String buildUrl;
    
    public UrlBean()
    {
    
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
     * @return the httpRequest
     */
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    /**
     * @param httpRequest the httpRequest to set
     */
    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the buildUrl
     */
    public String getBuildUrl() {
        return buildUrl;
    }

    /**
     * @param buildUrl the buildUrl to set
     */
    public void setBuildUrl(String buildUrl) {
        this.buildUrl = buildUrl;
    }
    
    
}
