package co.gov.corantioquia.controller;

import co.gov.corantioquia.utils.Main;
import co.gov.corantioquia.utils.UtilFilesRed;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/geo-database")
public class GeodatabaseController {

    @GetMapping("/")
    public List<String> listDatabases(@RequestParam String dir) throws Exception {
        //String dir  = "AN1-2017-238\\OBLIGACION-1/";
        return UtilFilesRed.listFiles(dir);
    }

    @GetMapping("/dir")
    public void getDatabase(HttpServletResponse response, @RequestParam String file) throws Exception {
        String mimeType = "application/octet-stream";//"application/pdf";
        response.setContentType(mimeType);
        //file = "AN1-2017-238\\OBLIGACION-1/";
        //fileNames [] = file.split("/");
        //String separadorParaPhyton = "__";
        //nombreArchivo = fileNames[0] + separadorParaPhyton + "E" + separadorParaPhyton + filesNames[2] + "C" + ".gdb.zip";
        String nombreArchivo = "reporte.zip";
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", nombreArchivo));


       //file = "AN1-2017-238\\OBLIGACION-1\\"+file;
        byte [] inStream = UtilFilesRed.testFileGMD(file);;
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }

}
