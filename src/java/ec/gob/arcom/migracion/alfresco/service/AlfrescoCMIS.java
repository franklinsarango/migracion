/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.service;


import ec.gob.arcom.migracion.alfresco.AlfrescoConstants;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoDocumentBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFileBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFolderBean;
import ec.gob.arcom.migracion.alfresco.bean.UrlBean;
import ec.gob.arcom.migracion.alfresco.service.base.CMISBase;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static ec.gob.arcom.migracion.alfresco.service.base.CMISBase.getDocumentUrl;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConstraintException;
import org.apache.log4j.Logger;

/**
 * Clase que implementa las llamadas al api CMIS de Alfresco
 * @author cherrera
 */
public class AlfrescoCMIS extends CMISBase implements Serializable
{

    private static Logger logger = Logger.getLogger(AlfrescoCMIS.class);
    
    /**
     * Crear carpetas
     * @param siteFolderName
     * @param folderList
     * @return 
     */
    public static List<AlfrescoFolderBean> createFolders(String siteFolderName, List<String> folderList)
    {
         Session session = getCmisSession();
         String folderId = getFolderId(session,siteFolderName);
         Folder siteFolder = getFolder(session,folderId);
         
         return createFolders(siteFolder,folderList);
    }
    
    /**
     * Crear Carpetas
     * @param siteFolder
     * @param folderList
     * @return 
     */
    public static List<AlfrescoFolderBean> createFolders(Folder siteFolder, List<String> folderList)
    {
         
         List<AlfrescoFolderBean> resultList = new ArrayList<AlfrescoFolderBean>();
         Folder parent = siteFolder;
         boolean existe;
         for(String folderName:folderList)
         {
             existe = false;
             AlfrescoFolderBean afb = new AlfrescoFolderBean();
             for(CmisObject cmo:parent.getChildren())
             {
                  if(cmo.getName().equals(folderName) && (AlfrescoConstants.CMIS_TYPE_FOLDER.equals(cmo.getBaseTypeId().value())))
                  {
                     
                     parent = (Folder)cmo;
                     afb.setFolder((Folder)cmo);
                     afb.setFolderId(cmo.getId());
                     afb.setName(cmo.getName());
                     existe = true;
                     break;
                  }
                  else
                  {
                     continue;
                  }
             }
             
             if(!existe)
             {
               Folder folder = createFolder(parent,folderName);
               parent=folder;
               afb.setFolder(folder);
               afb.setName(folderName);
               afb.setFolderId(folder.getId());
             }
             
             resultList.add(afb);
         }
         
         
         return resultList;
    }
    
    /**
     * Crear una carpeta en Alfresco
     * @param parent
     * @param folderName
     * @return 
     */
    public static Folder createFolder(Folder parent,String folderName)
    {
        
        Date today = new Date();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, AlfrescoConstants.CMIS_TYPE_FOLDER);
        properties.put(PropertyIds.NAME,folderName);
        
        properties.put(PropertyIds.CREATION_DATE,today.toString());
        
        // create a major version
        Folder newFolder = null;
        
        try {
            	   newFolder = parent.createFolder(properties);
        }
        catch(CmisConstraintException cce)
        {
             logger.info("Error creando carpeta "+folderName+" "+cce.getMessage());
        }
        
        return newFolder;
    }
    
    /**
     * Subir un documento en la ultima carpeta de la lista
     * @param siteFolderName
     * @param folderList
     * @param fileBean
     * @return 
     */
    public static AlfrescoDocumentBean uploadDocument(String siteFolderName, List<String> folderList, AlfrescoFileBean fileBean) throws IOException, Exception
    {
       List<AlfrescoFolderBean> listAlfrescoFolders = createFolders(siteFolderName,folderList);
       
       Folder lastFolder = null;
       
       for(AlfrescoFolderBean afb:listAlfrescoFolders)
       {
           lastFolder = afb.getFolder();
       }
       
       return uploadDocument(lastFolder,fileBean);
    }
   
    
    /**
     * 
     * @param folder
     * @param fileBean
     * @return 
     */
    public static AlfrescoDocumentBean uploadDocument(Folder folder, AlfrescoFileBean fileBean) throws IOException, Exception
    {
         AlfrescoDocumentBean adb = new AlfrescoDocumentBean();
         Session session = getCmisSession();
        
         Document document;
         boolean existe=false;
         for(CmisObject cmo:folder.getChildren())
         {
           if(cmo.getName().equals(fileBean.getName()) && cmo.getBaseTypeId().value().equals(AlfrescoConstants.CMIS_TYPE_DOCUMENT))
           {
              existe = true;
              document = (Document)cmo;
              
              adb.setDocument(document);
              adb.setDocumentId(cmo.getId());
              adb.setDocumentName(cmo.getName());
              String url = getDocumentUrl(document,session);
              if(url!=null)
                {    
//                  System.out.println("--> url1 " +url);  
                  
                  UrlBean ub = buildGenericUrl(url);
//                  System.out.println("--> gu1 " +ub.getBuildUrl()); 
                  adb.setGenericUrl(ub.getGenericUrl());
                  adb.setUrlBean(ub);
                  adb.setDocumentUrl(ub.getUrl());
                }
              
              
              break;
           } 
           else
           {
              existe = false;  
              continue;
           }
         }
         
         if(!existe)
         {
           Map<String, Object> properties = new HashMap<String, Object>();
           properties.put(PropertyIds.OBJECT_TYPE_ID, AlfrescoConstants.CMIS_TYPE_DOCUMENT);
           properties.put(AlfrescoConstants.CMIS_PROPERTY_NAME,fileBean.getName() ); 
           ContentStream contentStream=session.getObjectFactory().createContentStream(fileBean.getFile().getName(),fileBean.getFile().length(), fileBean.getMimeType(),new FileInputStream(fileBean.getFile()));
//           System.out.println("--> contentStream " +contentStream.getLength());
//           System.out.println("--> folder " +folder.getName());
           document = folder.createDocument(properties, contentStream, null);
           adb.setDocument(document);
           adb.setDocumentId(document.getId());
           adb.setDocumentName(document.getName());
//           System.out.println("-->antes de sleep " + new Date());
           TimeUnit.SECONDS.sleep(2);
//           System.out.println("-->despues de sleep " + new Date());
           String url = getDocumentUrl(document,session);
           if(url!=null)
           {    

             UrlBean ub = buildGenericUrl(url);
             adb.setGenericUrl(ub.getGenericUrl());
             adb.setUrlBean(ub);
             adb.setDocumentUrl(ub.getUrl());
           }
       
         }
         
         return adb;
    }
    
   
 
}
