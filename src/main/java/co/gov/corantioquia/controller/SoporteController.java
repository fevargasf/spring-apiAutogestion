package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.SoporteDTO;
import co.gov.corantioquia.repository.Enviar_correo_soporteRepository;
import co.gov.corantioquia.service.SoportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soporte")
public class SoporteController {
    @Autowired
    private SoportService soportService;

    @PostMapping(value = "/evidencia")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String soporte(
            @RequestParam("sequenceThirdParty2") Integer sequenceThirdParty2,
            @RequestParam("viError") String viError
    ){
        SoporteDTO pdf = new SoporteDTO();
        pdf.setNiSecExpe(sequenceThirdParty2);
        pdf.setViError(viError);
        return soportService.soporte(pdf);
    }


}
