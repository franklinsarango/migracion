package ec.gob.arcom.migracion.alfresco.service;

import ec.gob.arcom.migracion.alfresco.AlfrescoConstants;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoDocumentBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFileBean;
import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFolderBean;
import ec.gob.arcom.migracion.alfresco.util.AlfrescoFileUtil;
import ec.gob.arcom.migracion.alfresco.util.AlfrescoProperties;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import sun.misc.BASE64Encoder;

/**
 * Clase que expone los servicios de alfresco
 *
 * @author cherrera
 */
public class AlfrescoService implements Serializable {

    /**
     * Obtener una carpeta de Alfresco
     *
     * @param folderName
     * @return
     */
    public static Folder getFolder(String folderName) {
        Session session = AlfrescoCMIS.getSession();
        String folderId = AlfrescoCMIS.getFolderId(session, folderName);

        return AlfrescoCMIS.getFolder(session, folderId);
    }

    private static List<String> reBuiltFolderList(List<String> folderList) {
        List<String> result = new ArrayList<String>();

        result.add(AlfrescoProperties.getString("alfresco.module.name"));

        for (String str : folderList) {
            result.add(str);
        }

        return result;
    }

    /**
     * Crear carpetas
     *
     * @param folderList
     * @return
     */
    public static List<AlfrescoFolderBean> createFolders(List<String> folderList) {
        String siteFolderName = AlfrescoProperties.getString("alfresco.root.folder");
        return AlfrescoCMIS.createFolders(siteFolderName, reBuiltFolderList(folderList));
    }

    /**
     * Subir un documento en la ultima carpeta de la lista
     *
     * @param folderList
     * @param fileBean
     * @return
     * @throws java.io.IOException
     */
    public static AlfrescoDocumentBean uploadDocument(List<String> folderList, AlfrescoFileBean fileBean) throws IOException, Exception {
        String siteFolderName = AlfrescoProperties.getString("alfresco.root.folder");

        return AlfrescoCMIS.uploadDocument(siteFolderName, reBuiltFolderList(folderList), fileBean);
    }

    /**
     * Obtener un ticket para Alfresco
     *
     * @return
     * @throws Exception
     */
    public static String getAuthenticationTicket() throws Exception {
        return AlfrescoCMIS.getAuthenticationTicket(AlfrescoProperties.getString("alfresco.adm.user"), AlfrescoProperties.getString("alfresco.adm.password"));
    }

    /**
     * Obtener un documento
     *
     * @param properties
     * @param folderName
     * @param documentName
     * @return
     */
    public static AlfrescoDocumentBean getDocument(Properties properties, String folderName, String documentName) {
        AlfrescoDocumentBean adb = new AlfrescoDocumentBean();

        //Abrir una sesion de Alfresco
        Session session = AlfrescoCMIS.getSession(properties);
        String folderId = AlfrescoCMIS.getFolderId(session, folderName);
        Folder folder = AlfrescoCMIS.getFolder(session, folderId);
        Document doc = (Document) AlfrescoCMIS.getChildFolder(folder, documentName);
        adb.setDocument(doc);
        adb.setDocumentId(doc.getId());
        adb.setDocumentName(documentName);
        String docUrl = AlfrescoCMIS.getDocumentUrl(doc, session);
        adb.setDocumentUrl(docUrl);
        return adb;
    }

    /**
     * Buscar un documento
     *
     * @param properties
     * @param folderName
     * @param documentName
     * @return
     */
    public static AlfrescoDocumentBean findDocument(Properties properties, String folderName, String documentName) {
        //Abrir una sesion de Alfresco
        Session session = AlfrescoCMIS.getSession(properties);
        Document doc = AlfrescoCMIS.findDocumentInsideFolder(session, folderName, documentName);

        return new AlfrescoDocumentBean(doc);
    }

    /**
     * Obtener el contenido de una carpeta conociendo su ID en el repositorio
     *
     * @param folderId
     * @return
     */
    public static List<AlfrescoFileBean> getFolderContent(String folderId) {
        List<AlfrescoFileBean> alfrescoFileList = new ArrayList<AlfrescoFileBean>();

        //Abrir una sesion de Alfresco
        Session session = AlfrescoCMIS.getSession();

        Folder folder = AlfrescoCMIS.getFolder(session, folderId);

        int index = 0;
        for (CmisObject co : folder.getChildren()) {
            if (co instanceof Document) {
                AlfrescoFileBean alfrescoFile = new AlfrescoFileBean();
                Document doc = (Document) co;
                alfrescoFile.setName(doc.getName());
                alfrescoFile.setObjectId(doc.getId());
                alfrescoFile.setInputStream(doc.getContentStream().getStream());
                alfrescoFile.setMimeType(doc.getContentStreamMimeType());
                alfrescoFile.setDownloadName(AlfrescoConstants.DOWNLOAD_NAME + index);
                alfrescoFile.setDownloadId(AlfrescoConstants.DOWNLOAD_ID + index);
                alfrescoFile.setDocument(doc);
                alfrescoFileList.add(alfrescoFile);
                index++;

            }
        }

        return alfrescoFileList;
    }

    /**
     * Obtener un documento conociendo su ObjectId
     *
     * @param properties
     * @param documentId
     * @return
     */
    public static Document getDocument(Properties properties, String documentId) {
        //Abrir una sesion de Alfresco
        Session session = AlfrescoCMIS.getSession(properties);

        return AlfrescoCMIS.getDocument(session, documentId);
    }

    /**
     * Obtener el URL de un documento
     *
     * @param properties
     * @param folderName
     * @param documentName
     * @return
     */
    public static String getDocumentURL(Properties properties, String folderName, String documentName) {
        //Abrir una sesion de Alfresco
        Session session = AlfrescoCMIS.getSession(properties);

        Document document = getDocument(properties, documentName);

        return AlfrescoCMIS.getDocumentUrl(document, session);

    }

    
    
    
    /**
     *
     * @param document
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static AlfrescoDocumentBean pushDocument(AlfrescoFileBean document) throws FileNotFoundException, IOException {
        AlfrescoDocumentBean result = new AlfrescoDocumentBean();

        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream fis = new FileInputStream(document.getFile());
        DataInputStream dis = new DataInputStream(fis);
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) document.getFile().length()];
        dis.readFully(bytes);

        String user = AlfrescoProperties.getString("alfresco.adm.user");
        String password = AlfrescoProperties.getString("alfresco.adm.password");
        URL url = new URL(AlfrescoProperties.getString("alfresco.cmis.rest.url"));

        String mimeType = AlfrescoFileUtil.getMimeType(document.getExtension());

        String data = AlfrescoConstants.CMIS_MESSAGE_PUSH_DOCUMENT;
        data = data.replaceFirst("FILE_NAME", document.getFile().getName());
        data = data.replaceFirst("MIME_TYPE", mimeType);
        data = data.replaceFirst("CONTENT", encoder.encode(bytes));

        String auth = encoder.encode((user + ":" + password).getBytes());

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "Basic " + auth);
        conn.setRequestProperty("Content-Type",
                "application/atom+xml;type=entry");
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        out.write(data);
        out.close();

        // A ver que nos cuentan.
        String decodedString;

        BufferedReader in = new BufferedReader(new InputStreamReader(conn
                .getInputStream()));

        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();

        return result;

    }
}
