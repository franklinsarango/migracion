/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.util;

import ec.gob.arcom.migracion.alfresco.bean.AlfrescoFileBean;
import ec.gob.arcom.migracion.alfresco.AlfrescoMimeType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import org.apache.commons.io.IOUtils;



/**
 *
 * @author Cristhian Herrera - Latinus
 */
public class AlfrescoFileUtil extends WordUtil implements Serializable
{
    
    

    
    /**
     * Convertir a arreglo de bytes un archivo
     * @param file
     * @return 
     */
    public static byte[] fileToArrayByte(File file)
    {
        try 
        {
            InputStream io = new  FileInputStream(file);
            
            return IOUtils.toByteArray(io);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return null;
    }        
    
    /**
     * Convertir de Stream a byte
     * @param stream
     * @return 
     */
    public static byte[] inputStreamToByte(InputStream stream)
    {
      
       
        try {
           return  IOUtils.toByteArray(stream);
        } catch (IOException ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return null;
    }
    
    /**
     * Convertir de file a InputStream
     * @param file
     * @return
     * @throws FileNotFoundException 
     */
    public static InputStream fileToInputStream(File file) throws FileNotFoundException
    {
      InputStream is = new FileInputStream (file);
      
      return is;
    }
    
    /**
     * 
     * @param mime
     * @return 
     */
    public static AlfrescoMimeType getAlfrescoMimeType(String mime)
    {
       AlfrescoMimeType mt = AlfrescoMimeType.dll;
       
       for(AlfrescoMimeType e : AlfrescoMimeType.values()){
             
            if(e.getMimeType().equals(mime)){
                mt=e;
                break;
            }
                    
        }
       
       return mt;
    }
    /**
     * Obtener el mimeType
     * @param fileName
     * @return 
     */
    public static String findMimeType(String fileName)
    {
        
       String[] filePart = fileName.split("\\.");
       String extension; 
       try
       {
         extension = filePart[1];
       }catch(Exception e)
       {
          extension = fileName;
       }
      
       return getMimeType(extension);
    }
    
    /**
     * 
     * @param extension
     * @return 
     */
    public static String getMimeType(String extension){
        
        extension = extension.replace("\\.", "");
        
        String mime = AlfrescoMimeType.dll.getMimeType();// default
        for(AlfrescoMimeType e : AlfrescoMimeType.values()){
             
            if(e.getCode().equals(extension)){
                mime=e.getMimeType();
                break;
            }
                    
        }
        
        return mime;
    }
    
   
   /**
    * 
    * @param in
    * @param fileName
    * @param fileExt
    * @return
    * @throws IOException 
    */ 
   public static File streamToFilePoint (InputStream in,String fileName, String fileExt) throws IOException 
   {
       return streamToFile(in,fileName,"."+fileExt);
   }
    
   /**
    * 
    * @param in
    * @param fileName
    * @param fileExt
    * @return
    * @throws IOException 
    */ 
   public static File streamToFile (InputStream in,String fileName, String fileExt) throws IOException 
   {            
//   System.out.println(" --> "+fileExt);
         final File tempFile = File.createTempFile(fileName, fileExt);
        tempFile.deleteOnExit();

        FileOutputStream out = new FileOutputStream(tempFile) ;
        
        IOUtils.copy(in, out);
        
        return tempFile;      

       
     
   }
   /**
    * 
    * @param in
    * @param fileName
    * @param fileExt
    * @return
    * @throws IOException 
    */
   public static File streamToFile2 (InputStream in,String fileName, String fileExt) throws IOException 
   {            

      
       File tempDir = new File(System.getProperty("java.io.tmpdir", null), "tempdir-old");
        
        if (!tempDir.exists() && !tempDir.mkdir())
            throw new IIOException("Failed to create temporary directory " + tempDir);
   
       File tempFile = File.createTempFile(fileName, fileExt,tempDir);
       tempFile.deleteOnExit();

        FileOutputStream out = new FileOutputStream(tempFile) ;
        
        IOUtils.copy(in, out);
        
        return tempFile;      
       
   
   }
   
   /**
    * 
    * @param file
    * @return
    * @throws IOException 
    */
   public static byte[] getBytesFromFile(final File file) throws IOException {
    final InputStream is = new FileInputStream(file);

    // Get the size of the file
    final long length = file.length();

    if (length > Integer.MAX_VALUE) {
      // File is too large
      throw new IOException("File too long");
    }

    // Create the byte array to hold the data
    final byte[] bytes = new byte[(int) length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
      offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    // Close the input stream and return bytes
    is.close();
    return bytes;
  }
   
   /**
    * Convertir un archivo (File) en AlfrescoFileBean
    * @param file
    * @return 
    */
   public static AlfrescoFileBean fileToAlfrescoFileBean(File file) throws FileNotFoundException
   {
      AlfrescoFileBean afb = new AlfrescoFileBean();
      
      afb.setFile(file);
      InputStream is = AlfrescoFileUtil.fileToInputStream(file);
      afb.setInputStream(is);
      String mimeType = AlfrescoFileUtil.findMimeType(file.getName());
      afb.setMimeType(mimeType);
      afb.setName(file.getName());
      
      
      return afb;
   }
   
   /**
    * 
    * @param stream
    * @param name
    * @param extension
    * @return
    * @throws IOException 
    */
   public static AlfrescoFileBean inputStreamToAlfrescoFileBean(InputStream stream, String name, String extension) throws IOException
   {
        File temp = streamToFile(stream,name,extension);
        return fileToAlfrescoFileBean(temp);
   }
    
}
