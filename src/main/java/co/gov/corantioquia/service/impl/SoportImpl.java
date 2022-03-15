package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.SoporteDTO;
import co.gov.corantioquia.repository.Enviar_correo_soporteRepository;
import co.gov.corantioquia.service.SoportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoportImpl implements SoportService {
    @Autowired
    private Enviar_correo_soporteRepository enviar_correo_soporteRepository;

    public SoportImpl(Enviar_correo_soporteRepository enviar_correo_soporteRepository){
        this.enviar_correo_soporteRepository=enviar_correo_soporteRepository;
    }

    @Override
    public String soporte(SoporteDTO soporteDTO){
        String viError =enviar_correo_soporteRepository.soporte(soporteDTO );
        return viError;
    }


}
