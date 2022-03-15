package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.RadicarDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import co.gov.corantioquia.service.RadicarCoeService;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value="/finish")
public class RadicarCoeController {

    private RadicarCoeService radicarCoeService;
    public RadicarCoeController(RadicarCoeService radicarCoeService) {
        this.radicarCoeService = radicarCoeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    RadicarDTO radicar(@RequestBody RadicarDTO radicarDTO){
        return radicarCoeService.radicar(radicarDTO);
    }


    @PostMapping("/construir-pdf-radicado")
    public void construirPdfRadicado(HttpServletResponse response, @RequestBody RadicarPdfDTO radicarPdfDTO) throws Exception {
        //Consultar archivos
        //Consultar reponsable
        //Armar pdf con numero de radicado
        String origin = response.getHeader("Origin");

        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        String mimeType = "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        byte[] inStream =  radicarCoeService.construirPdfRadicado(radicarPdfDTO);
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }



}
