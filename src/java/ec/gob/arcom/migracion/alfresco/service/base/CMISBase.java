/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.service.base;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static ec.gob.arcom.migracion.alfresco.service.AlfrescoCMIS.find;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.bindings.spi.atompub.AbstractAtomPubService;
import org.apache.chemistry.opencmis.client.bindings.spi.atompub.AtomPubParser;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;
import org.apache.log4j.Logger;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import ec.gob.arcom.migracion.alfresco.AlfrescoConstants;
import ec.gob.arcom.migracion.alfresco.bean.UrlBean;
import ec.gob.arcom.migracion.alfresco.model.NetworkEntry;
import ec.gob.arcom.migracion.alfresco.model.NetworkList;
import ec.gob.arcom.migracion.alfresco.util.AlfrescoProperties;
import ec.gob.arcom.migracion.alfresco.util.JSONUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.simple.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class CMISBase implements Serializable {

    private static final Logger logger = Logger.getLogger(CMISBase.class);
    public static final String CMIS_URL = "/public/cmis/versions/1.1/atom";
    public static final String SITES_URL = "/public/alfresco/versions/1/sites/";
    public static final String NODES_URL = "/public/alfresco/versions/1/nodes/";
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static String homeNetwork;
    private static HttpRequestFactory requestFactory;

    /**
     * Devolver URL Alfresco
     *
     * @return
     */
    public static String getAlfrescoAPIUrl() {
        String host = AlfrescoProperties.getString("alfresco.cmis.host");
        return host + "/api/";
    }

    /**
     * Uses basic authentication to create an HTTP request factory.
     *
     * @return HttpRequestFactory
     */
    public static HttpRequestFactory getRequestFactory() {
//                if (requestFactory == null) {
        requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                request.setParser(new JsonObjectParser(new JacksonFactory()));
                request.getHeaders().setBasicAuthentication(AlfrescoProperties.getString("alfresco.adm.user"), AlfrescoProperties.getString("alfresco.adm.password"));
            }
        });
//                }
        return requestFactory;
    }

    /**
     *
     * @return @throws IOException
     */
    public static String getHomeNetwork() throws IOException {
        if (homeNetwork == null) {
            GenericUrl url = new GenericUrl(getAlfrescoAPIUrl());

            HttpRequest request = getRequestFactory().buildGetRequest(url);

            NetworkList networkList = request.execute().parseAs(NetworkList.class);
//                System.out.println("Found " + networkList.list.pagination.totalItems + " networks.");
            for (NetworkEntry networkEntry : networkList.list.entries) {
                if (networkEntry.entry.homeNetwork) {
                    homeNetwork = networkEntry.entry.id;
                }
            }

            if (homeNetwork == null) {
                homeNetwork = "-default-";
            }

//                System.out.println("Your home network appears to be: " + homeNetwork);
        }
        return homeNetwork;
    }

    /**
     *
     * @param requestFactory
     * @return
     */
    public static String getAtomPubURL(HttpRequestFactory requestFactory) {
        String alfrescoAPIUrl = getAlfrescoAPIUrl();
        String atomPubURL;

        try {
            atomPubURL = alfrescoAPIUrl + getHomeNetwork() + CMIS_URL;
        } catch (IOException ioe) {
            System.out.println("Warning: Couldn't determine home network, defaulting to -default-");
            atomPubURL = alfrescoAPIUrl + "-default-" + CMIS_URL;
        }

        return atomPubURL;
    }

    /**
     * Obtener una session al servidor Alfreso
     *
     * @param properties
     * @return
     */
    public static Session getSession(Properties properties) {
        if (properties == null) {
            return getSession();
        } else {
            String loc = properties.getProperty("alfresco.cmis.url");

            SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
            Map<String, String> parameter = new HashMap<String, String>();

            // Connection settings.
            parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
            parameter.put(SessionParameter.ATOMPUB_URL, loc); // URL to your CMIS server.

            // User credentials.
            parameter.put(SessionParameter.USER, properties.getProperty("alfresco.adm.user"));
            parameter.put(SessionParameter.PASSWORD, properties.getProperty("alfresco.adm.password"));

            // Create session.
            Session session = null;

            try {
                // This supposes only one repository is available at the URL.
                Repository soleRepository = sessionFactory.getRepositories(parameter).get(0);
                session = soleRepository.createSession();
            } catch (CmisConnectionException e) {
                // The server is unreachable
                //System.out.println("-->> CmisConnectionException");
                //e.printStackTrace();
                logger.error("getSession->CmisConnectionException", e);
            } catch (CmisRuntimeException e) {
                // The user/password have probably been rejected by the server.
                //System.out.println("-->> CmisRuntimeException");
                //e.printStackTrace();
                logger.error("getSession->CmisRuntimeException", e);
            }

            return session;

        }
    }

    /**
     * Obtener una session al servidor Alfreso
     *
     * @return
     */
    public static Session getSession() {

        String loc = AlfrescoProperties.getString("alfresco.cmis.url");

        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // Connection settings.
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.ATOMPUB_URL, loc); // URL to your CMIS server.
        parameter.put(SessionParameter.AUTH_HTTP_BASIC, "true");
        parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

        // User credentials.
        parameter.put(SessionParameter.USER, AlfrescoProperties.getString("alfresco.adm.user"));
        parameter.put(SessionParameter.PASSWORD, AlfrescoProperties.getString("alfresco.adm.password"));

        // Create session.
        Session session = null;

        try {
            // This supposes only one repository is available at the URL.
            Repository soleRepository = sessionFactory.getRepositories(parameter).get(0);
            session = soleRepository.createSession();
        } catch (CmisConnectionException e) {
                // The server is unreachable
            //System.out.println("-->> CmisConnectionException");
            //e.printStackTrace();
            logger.error("getSession->CmisConnectionException", e);
        } catch (CmisRuntimeException e) {
                // The user/password have probably been rejected by the server.
            //System.out.println("-->> CmisRuntimeException");
            //e.printStackTrace();
            logger.error("getSession->CmisRuntimeException", e);
        }

        return session;

    }

    /**
     * Obtener una session al servidor Alfreso
     *
     * @return
     */
    public static Session getCmisSession() {

        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();

        // Connection settings.
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.ATOMPUB_URL, getAtomPubURL(getRequestFactory())); // URL to your CMIS server.
        parameter.put(SessionParameter.AUTH_HTTP_BASIC, "true");
        parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

        // User credentials.
        parameter.put(SessionParameter.USER, AlfrescoProperties.getString("alfresco.adm.user"));
        parameter.put(SessionParameter.PASSWORD, AlfrescoProperties.getString("alfresco.adm.password"));

        // Create session.
        Session session = null;

        try {
            // This supposes only one repository is available at the URL.
            Repository soleRepository = sessionFactory.getRepositories(parameter).get(0);
            session = soleRepository.createSession();
        } catch (CmisConnectionException e) {
                // The server is unreachable
            //System.out.println("-->> CmisConnectionException");
            //e.printStackTrace();
            logger.error("getSession->CmisConnectionException", e);
        } catch (CmisRuntimeException e) {
                // The user/password have probably been rejected by the server.
            //System.out.println("-->> CmisRuntimeException");
            //e.printStackTrace();
            logger.error("getSession->CmisRuntimeException", e);
        }

        return session;

    }

    /**
     * Método que, dado el nombre de un espacio (carpeta) devuelve su Id
     * (nodeRef)
     *
     * @param session
     * @param folderName
     * @return nodeRef (id)
     */
    public static String getFolderId(Session session, String folderName) {
        String queryString = "SELECT cmis:objectId FROM cmis:folder WHERE cmis:name = '" + folderName + "'";

        ItemIterable<QueryResult> results = session.query(queryString, false);

        String id = "";

        for (QueryResult qResult : results) {
            String objectId = qResult.getPropertyValueByQueryName("cmis:objectId");
            Folder folder = (Folder) session.getObject(session.createObjectId(objectId));
            id = folder.getId();
            logger.info("Listado de carpetas: " + folder.getId());
        }

        return id;

    }

    /**
     * Obtener el id de un "sitio" en Alfresco
     *
     * @param session
     * @param siteName
     * @return
     */
    public static String getSiteId(Session session, String siteName) {
        String queryString = "SELECT cmis:objectId FROM cmis:folder WHERE cmis:name = '" + siteName + "'";

        ItemIterable<QueryResult> results = session.query(queryString, false);

        String id = "";

        for (QueryResult qResult : results) {
            String objectId = qResult.getPropertyValueByQueryName("cmis:objectId");
            Folder folder = (Folder) session.getObject(session.createObjectId(objectId));
            id = folder.getId();
//               logger.info("Listado de carpetas: "+folder.getId());
            if (folder.getFolderParent().getName().equals(AlfrescoConstants.CMIS_SITES)) {
                break;
            }
        }

        return id;

    }

    /**
     * Obtener un ID de documento desde la sesion, se asume en este caso que el
     * nombre de documento es unico
     *
     * @param session
     * @param name
     * @return
     */
    public static String getDocumentId(Session session, String name) {
        String queryString = "SELECT cmis:objectId FROM cmis:document WHERE cmis:name = '" + name + "'";

        ItemIterable<QueryResult> results = session.query(queryString, false);
        String id = "";

        for (QueryResult qResult : results) {
            String objectId = qResult.getPropertyValueByQueryName("cmis:objectId");
            Document doc = (Document) session.getObject(session.createObjectId(objectId));
            id = doc.getId();
            logger.info("Listado de Objectos: " + doc.getId());
        }

        return id;

    }

    /**
     * Método que elimina un objeto del repositorio
     *
     * @param doc
     */
    public static void deleteContent(CmisObject doc) {
        try {
            doc.delete(true);
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al intentar eliminar un elemento:" + e.getMessage());
        }

    }

    /**
     * Método que construye una query cmis y devuelve un set de resultados.
     *
     * @param session
     * @param fields
     * @param locations
     * @param conditions
     * @return
     */
    public static ItemIterable<QueryResult> find(Session session, String[] fields, String[] locations, String[] conditions) {
        //Ej: Select * from cmis:object where in_folder(id)
        //select cmis:objectId from cmis:document where in_folder(/cabinet/folder)

        int i;
        String field, location, condition, select = "", queryString = "SELECT ";

        for (i = 0; i < fields.length; i++) {
            field = fields[i];
            if (i > 0) { //agregar comas a la clausula
                select = select + "," + field;
            } else if (i == 0) {
                select = field;
            }
        }

        if (locations != null) {
            queryString = queryString + select + " FROM ";
	        //construccion del for

            select = "";
            for (i = 0; i < locations.length; i++) {

                location = locations[i];
                if (i > 0) { //agregar comas a la clausula
                    select = select + "," + location;
                } else if (i == 0) {
                    select = location;
                }
            }
        }

        select = "";
        if (conditions != null) {
            queryString = queryString + select + " WHERE ";
                //construccion del where

            for (i = 0; i < conditions.length; i++) {

                condition = conditions[i];
                if (i > 0) { //agregar comas a la clausula
                    select = select + "," + condition;
                } else if (i == 0) {
                    select = condition;
                }
            }
        }

        queryString = queryString + select;
        logger.info("queryString: " + queryString);

        // execute query
        ItemIterable<QueryResult> results = session.query(queryString, false);

        return results;
    }

    /**
     * Obtener todos los objetos del repositorio
     *
     * @param session
     * @return
     */
    public static ItemIterable<QueryResult> find(Session session) {
        return find(session, null, null, null);
    }

    /**
     * Obtener un documento del repositorio dado su object id
     *
     * @param properties
     * @param objectId
     * @return
     */
    public static Document getDocument(Properties properties, String objectId) {
        Session session = getSession(properties);

        return getDocument(session, objectId);
    }

    /**
     * Devolver objeto Documento desde el objeto de sesion
     *
     * @param session
     * @param objectId
     * @return
     */
    public static Document getDocument(Session session, String objectId) {
        Document document = (Document) session.getObject(session.createObjectId(objectId));

        return document;
    }

    /**
     * Buscar un documento dado su objectId
     *
     * @param session
     * @param objectId
     * @return
     */
    public static Document findDocument(Session session, String objectId) {
        Document document = null;

        ItemIterable<QueryResult> results = find(session);

        for (QueryResult qResult : results) {
            String sobjectId = qResult.getPropertyValueByQueryName(AlfrescoConstants.CMIS_OBJECT_ID);

            if (sobjectId.equals(objectId)) {
                document = (Document) session.getObject(session.createObjectId(objectId));
                break;
            }
        }

        return document;
    }

    /**
     * Buscar un documento en una carpeta
     *
     * @param properties
     * @param folderName
     * @param documentName
     * @return
     */
    public static Document findDocumentInsideFolder(Properties properties, String folderName, String documentName) {
        Session session = getSession(properties);

        return findDocumentInsideFolder(session, folderName, documentName);
    }

    /**
     * Obtener un documento en una carpeta
     *
     * @param session
     * @param folderName
     * @param documentName
     * @return
     */
    public static Document findDocumentInsideFolder(Session session, String folderName, String documentName) {
        Document document = null;
        String[] fields = {AlfrescoConstants.CMIS_FIND_ALL_FIELDS};
        String[] locations = {AlfrescoConstants.CMIS_TYPE_DOCUMENT};

        String folderId = getFolderId(session, folderName);
        String documentId = getDocumentId(session, documentName);

        String inFolder = AlfrescoConstants.CMIS_IN_FOLDER_CONDITION;
        inFolder = inFolder.replace(AlfrescoConstants.CMIS_ID_TOKEN, folderId);

        String[] conditions = {inFolder};

        ItemIterable<QueryResult> results = find(session, fields, locations, conditions);

        for (QueryResult qResult : results) {
            String sobjectId = qResult.getPropertyValueByQueryName(AlfrescoConstants.CMIS_OBJECT_ID);

            if (sobjectId.equals(documentId)) {
                document = (Document) session.getObject(session.createObjectId(documentId));
                break;
            }
        }

        return document;

    }

    /**
     * Dada una carpeta obtener el hijo (folder o file) dado su nombre
     *
     * @param folder
     * @param name
     * @return
     */
    public static CmisObject getChildFolder(Folder folder, String name) {
        CmisObject co = null;

   //System.out.println("Folder a analizar " + folder.getName());
        for (CmisObject cmo : folder.getChildren()) {
       //folder.
            //System.out.println("Hijo de " + folder.getName() + " " + cmo.getName());
            if (cmo.getName().equals(name)) {
                co = cmo;
                break;
            }
        }

        return co;
    }

    /**
     * Dado su objectId obtener una carpeta de session
     *
     * @param session
     * @param folderId
     * @return
     */
    public static Folder getFolder(Session session, String folderId) {
        Folder folder = (Folder) session.getObject(folderId);

        return folder;
    }

    /**
     * Método que modifica las propiedades de un Objeto
     *
     * @param session
     * @param doc
     * @param properties
     */
    public static void updateContent(Session session, CmisObject doc, Map<String, Object> properties) {

        properties.put(AlfrescoConstants.CMIS_PROPERTY_NAME, doc.getName());

        try {

            doc.updateProperties(properties);
                    //doc.

        } catch (Exception e) {
            logger.error("Ha ocurrido un error modificando propiedades:" + e.getMessage());
        }

    }

    /**
     * see:
     * http://forums.alfresco.com/forum/developer-discussions/alfresco-api/using-cmis-get-list-document-urls-11302011-2029
     * Obtener el url / link de un documento
     *
     * @param document
     * @param session
     * @return
     *
     */
    @SuppressWarnings("UseSpecificCatch")
    public static String getDocumentUrl(Document document, Session session) {
        String link = null;
        try {
            Method loadLink = AbstractAtomPubService.class.getDeclaredMethod("loadLink",
                    new Class[]{String.class, String.class, String.class, String.class});

            loadLink.setAccessible(true);

            link = (String) loadLink.invoke(session.getBinding().getObjectService(), session.getRepositoryInfo().getId(),
                    document.getId(), AtomPubParser.LINK_REL_CONTENT, null);
        } catch (Exception e) {
//       e.printStackTrace();
            logger.error("Ha ocurrido un error obteniendo URL de documento: " + e.getMessage());

        }
        return link;
    }

    /**
     *
     * @param link
     * @return
     * @throws IOException
     */
    protected static UrlBean buildGenericUrl(String link) throws IOException, Exception {
        UrlBean urlBean = new UrlBean();

        GenericUrl url = new GenericUrl(link);
        urlBean.setGenericUrl(url);

        HttpRequest request = getRequestFactory().buildGetRequest(url);

        urlBean.setHttpRequest(request);
        urlBean.setBuildUrl(request.getUrl().build());

        String ticket = getAuthenticationTicket(AlfrescoProperties.getString("alfresco.adm.user"), AlfrescoProperties.getString("alfresco.adm.password"));

        String urlTicket = request.getUrl().build() + "&alf_ticket=" + ticket;
        urlBean.setUrl(urlTicket);
        return urlBean;
    }

    /**
     * Obtener ticekt de logeo con Alfresco
     *
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public static String getAuthenticationTicket(String userName, String password) throws Exception {
        PostMethod loginMethod = null;
        try {
            loginMethod = new PostMethod(AlfrescoProperties.getString("alfresco.cmis.login.api"));
            loginMethod.setRequestHeader("Accept", "application/json");

            // Populate resuest body
            JSONObject requestBody = new JSONObject();
            requestBody.put("username", userName);
            requestBody.put("password", password);

            try {
                loginMethod.setRequestEntity(new StringRequestEntity(requestBody.toJSONString(), "application/json", "UTF-8"));
            } catch (UnsupportedEncodingException error) {
                throw new RuntimeException("All hell broke loose, a JVM that doesn't have UTF-8 encoding...");
            }

            HttpClient client = new HttpClient();

			// Since no authentication info is available yet, no need to use a
            // custom HostConfiguration for the login-call
            client.executeMethod(loginMethod);

            if (loginMethod.getStatusCode() == HttpStatus.SC_OK) {
                // Extract the ticket
                JSONObject data = JSONUtil.getDataFromResponse(loginMethod);
                if (data == null) {
                    throw new RuntimeException("Failed to login to Alfresco with user " + userName + " (No JSON-data found in response)");
                }

                // Extract the actual ticket
                String ticket = JSONUtil.getString(data, "ticket", null);
                if (ticket == null) {
                    throw new RuntimeException("Failed to login to Alfresco with user " + userName + "(No ticket found in JSON-response)");
                }
                return ticket;
            } else {
                // Unable to login
                throw new RuntimeException("Failed to login to Alfresco with user " + userName + " (" + loginMethod.getStatusCode() + loginMethod.getStatusLine().getReasonPhrase() + ")");
            }
        } catch (IOException ioe) {
            // Something went wrong when sending request
            throw new RuntimeException("Failed to login to Alfresco with user " + userName, ioe);
        } finally {
            if (loginMethod != null) {
                try {
                    loginMethod.releaseConnection();
                } catch (Throwable t) {
                    // Ignore this to prevent swallowing potential original exception
                }
            }
        }
    }

}
