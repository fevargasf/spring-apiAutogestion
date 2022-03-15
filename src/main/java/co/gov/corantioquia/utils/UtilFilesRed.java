package co.gov.corantioquia.utils;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UtilFilesRed {

    public static byte[] testFileGMD(String fichero) throws Exception {
        SmbFile archivo = null;

        UnidadRed ur = new UnidadRed();
        archivo = new SmbFile(ur.apuntarPath("AUTOGESTOR_") + fichero,ur.autenticar());

        try
        {
            //UnidadRed unidadRed = new UnidadRed();
            //unidadRed.copiarFicheroAUnidad(new File("C:/Nicolas/0018.tiff"),new SmbFile(unidadRed.apuntarPath() + fichero));
            //System.out.println(unidadRed.apuntarPath() + fichero);
            // archivo = new SmbFile(unidadRed.apuntarPath() + fichero);

            if(!archivo.isDirectory()){
                ByteArrayOutputStream fos = new ByteArrayOutputStream();

                SmbFileInputStream fis = null;
                try
                {
                    fis = new SmbFileInputStream(archivo);
                    byte[] buf = new byte[1024];

                    int i = 0;
                    while ((i=fis.read(buf)) != -1)
                    {
                        fos.write(buf, 0, i);
                    }
                }
                catch (Exception e) {}
                finally
                {
                    try
                    {
                        fis.close();
                        fos.close();
                    }
                    catch (Exception e) {};
                }
                return fos.toByteArray();
            }else{
                for(String name: archivo.list()){
                    System.out.println(name);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> listFiles(String directory) throws Exception {
        SmbFile archivo = null;

        UnidadRed ur = new UnidadRed();
        archivo = new SmbFile(ur.apuntarPath("AUTOGESTOR_") + directory,ur.autenticar());

        List<String> files = new ArrayList<String>();
        try
        {
            if(archivo.isDirectory()){
                for(String name: archivo.list()){
                    //TODO: validar extension .zip
                    files.add(name);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return files;
    }



}
