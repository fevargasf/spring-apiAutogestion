package co.gov.corantioquia.utils;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
       //System.out.println(PropApl.getInstance().get("HOST_DIR_DOCUNET"));
        //System.out.println(PropApl.getInstance().get("AUTOGESTOR_RUTA_DIR_DOCUNET"));//Singleton
        //System.out.println(DateFormatUtil.formatDate(new Date()));

       // testServer();
      //  System.out.println(FileUtils.isCompressed("archivo.rr"));
        //System.out.println(com.google.common.io.Files.getFileExtension("archio.JPG"));

    }



    public static void testServer() throws IOException {
        String fileName= "C:\\Users\\daniela bonilla\\Downloads\\file (9).pdf";
        File file = new File(fileName);
        byte[] bytes =  Files.readAllBytes(file.toPath());
        Documento documento = new Documento("file.pdf", bytes,"AUTOGESTOR_");

        try{
            UnidadRed ur = new UnidadRed();
            SmbFile archivo = new SmbFile(ur.apuntarPath("AUTOGESTOR_") + documento.getRuta(),ur.autenticar());
            ur.copiarBytesAUnidad(documento.getArchivo(),archivo);
        }catch(Exception e){
            //getUrl().setError(e);
            e.printStackTrace();
        }
    }
}
