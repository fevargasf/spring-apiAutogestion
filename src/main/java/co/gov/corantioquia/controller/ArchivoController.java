package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.*;
import co.gov.corantioquia.service.ConsultaObligaArchivoService;
/*import co.gov.corantioquia.utils.CloudMersiveFiles;*/
import co.gov.corantioquia.utils.ITextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/listar-archivos")
public class ArchivoController {

    @Autowired
    private ConsultaObligaArchivoService consultaObligaArchivoService;

    @GetMapping(value ="/consulta-archivos")
    @ResponseStatus(HttpStatus.OK)
    public void listarArchivos(
            HttpServletResponse response,
            @RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2
    ) throws IOException {

        String mimeType = "application/octet-stream";//"application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        byte[] inStream =  consultaObligaArchivoService.listarArchivos(sequenceThirdParty, sequenceThirdParty2);
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }

    @GetMapping(value = "/consultar-listarchivos")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<ListaTabla> listaTabla(
            @RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2
    ) {
        return consultaObligaArchivoService.listaTabla(sequenceThirdParty,sequenceThirdParty2);
    }

    @PostMapping(value = "/guardar-archivo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String guardarArchivo(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("sequenceThirdParty2") Integer sequenceThirdParty2,
            @RequestParam("sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam("line") String line,
            @RequestParam(name = "coorX", required = false) Double coorX,
            @RequestParam(name = "coorY", required = false) Double coorY,
            @RequestParam(value = "fechaFoto", required = false) String fechaFoto,
            @RequestParam(value = "expediente", required = false) String expediente
            ) throws Exception {
        GuardarArchivoDTO dto = new GuardarArchivoDTO();
        dto.setArchivo(new SerialBlob(file.getBytes()));
        try {
            dto.setCoorX(Double.parseDouble(String.valueOf(coorX)));
            dto.setCoorY(Double.parseDouble(String.valueOf(coorY)));
        } catch (NumberFormatException ignored) {}
        try {
            dto.setFechaFoto(LocalDate.parse(fechaFoto));
        } catch (DateTimeParseException ignored) {}
        dto.setLine(Integer.parseInt(line));
        dto.setSequenceThirdParty(sequenceThirdParty);
        dto.setSequenceThirdParty2(sequenceThirdParty2);
        dto.setNombreArchivo(file.getOriginalFilename());

        dto.setExpediente(expediente);

        return this.consultaObligaArchivoService.guardarArchivo(dto);
    }

    @DeleteMapping(value = "/borrar-archivo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String eliminarArchivo(
            @RequestParam(name = "niLineaObligacion") int niLineaObligacion,
            @RequestParam(name="niNroArchivo") int niNroArchivo,
            @RequestParam(name = "sequenceThirdParty") int sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") int sequenceThirdParty2
           ) {
            EliminarArchivoDTO dto = new EliminarArchivoDTO();
            dto.setNiLineaObligacion(niLineaObligacion);
            dto.setNiNroArchivo(niNroArchivo);
            dto.setSequenceThirdParty(sequenceThirdParty);
            dto.setSequenceThirdParty2(sequenceThirdParty2);
        return consultaObligaArchivoService.eliminarArchivo(dto);
    }

    @GetMapping("/cloud")
    public void cloud(@RequestParam("file") MultipartFile file,HttpServletResponse response
                      ) throws Exception {
        String mimeType = "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        //byte[] inStream = CloudMersiveFiles.httpCloudMersive(file.getOriginalFilename(),file.getBytes());
        //byte[] inStream = ITextUtil.generatePDFFromImage(file.getBytes(),file.getOriginalFilename());
        byte[] inStream = ITextUtil.generatePDFFromDocAspose(file.getBytes(),file.getOriginalFilename());
        //generatePDFFromExcel
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }

}

