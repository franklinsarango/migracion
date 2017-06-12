/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco;

/**
 * Constantes para alfresco
 * @author cherrera
 */
public class AlfrescoConstants 
{

    
     public static String CMIS_PROPERTY_NAME            = "cmis:name";
     public static String CMIS_PROPERTY_DESCRIPTION     = "cm:description";
     public static String CMIS_TYPE_DOCUMENT            = "cmis:document";
     public static String CMIS_TYPE_DOCUMENT_TITLE      = "cmis:document,P:cm:titled";
     public static String CMIS_TYPE_DOCUMENT_REQUEST    = "cmis:document,D:my:request";
     public static String CMIS_TYPE_FOLDER              = "cmis:folder";
     public static String CONTENT_TYPE_TEXT             = "text/plain";
     public static String CMIS_OBJECT_ID                = "cmis:objectId";
     public static String CMIS_FIND_ALL_FIELDS          = "*";
     public static String CMIS_IN_FOLDER_CONDITION      = "IN_FOLDER('ID_TOKEN')";
     public static String CMIS_ID_TOKEN                 = "ID_TOKEN";
     public static String CMIS_SITES                    = "Sites";
     public static String documentLibrary               = "documentLibrary";
     public static String helpFolder                    = "help";
     public static String DOCUMENTLIBRARY_ID            = "DOCUMENTLIBRARY.ID";
     public static String DOWNLOAD_NAME                 = "fileName";
     public static String DOWNLOAD_ID                   = "documentId";
     public static String SITE                          = "SITE";
     public static String ARCOM_SITE                    = "Sites";
     public static String ARCOM_SITE_CHILD              = "Arcom";
     public static String PROCESS                       = "PROCESS";
     public static String ROADMAP                       = "ROADMAP";
     public static String CREATEDBY                     = "CREATEDBY";
     public static String CMIS_MESSAGE_PUSH_DOCUMENT    =  "<entry xmlns=\"http://www.w3.org/2005/Atom \" xmlns:cmis=\"http://www.cmis.org/2008/05 \">\n"
                                                        + "\t<title>FILE_NAME</title>\n"
                                                        + "\t<content type=\"MIME_TYPE\">CONTENT</content>\n"
                                                        + "\t<cmis:object>\n"
                                                        + "\t\t<cmis:properties>\n"
                                                        + "\t\t\t<cmis:propertyString cmis:name=\"ObjectTypeId\"><cmis:value>document</cmis:value></cmis:propertyString>\n"
                                                        + "\t\t</cmis:properties>\n" + "\t</cmis:object>\n" + "</entry>\n";
    
    
}
