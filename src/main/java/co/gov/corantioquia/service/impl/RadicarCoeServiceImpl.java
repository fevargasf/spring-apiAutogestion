package co.gov.corantioquia.service.impl;


import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.RadicarDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import co.gov.corantioquia.models.dto.SoporteDTO;
import co.gov.corantioquia.repository.ConsultaArchivoRepository;
import co.gov.corantioquia.repository.Enviar_correo_soporteRepository;
import co.gov.corantioquia.repository.RadicarCoeRepository;
import co.gov.corantioquia.service.RadicarCoeService;
import co.gov.corantioquia.utils.CoMerge;
import co.gov.corantioquia.utils.ConsultaObligaArchivoUtil;
import co.gov.corantioquia.utils.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadicarCoeServiceImpl implements RadicarCoeService {

    private RadicarCoeRepository radicarCoeRepository;

    @Autowired
    private ConsultaArchivoRepository consultaArchivoRepository;

    @Autowired
    private Enviar_correo_soporteRepository enviarCorreo;

    public RadicarCoeServiceImpl(RadicarCoeRepository radicarCoeRepository) {
        this.radicarCoeRepository = radicarCoeRepository;
    }


    public RadicarDTO radicar(RadicarDTO radicarDTO) {
        radicarDTO = radicarCoeRepository.radicar(radicarDTO);
        return radicarDTO;
    }

    public byte[] construirPdfRadicado(RadicarPdfDTO radicarPdfDTO) throws Exception {
        //Consultar archivos
        List<Object> listaDesdeSP = consultaArchivoRepository.consultaObligaArchivos(radicarPdfDTO.getSequenceThirdParty(), radicarPdfDTO.getSequenceThirdParty2());
        List<ConsultaObligaArchivosDTO> lista = ConsultaObligaArchivoUtil.objectToConsultaObligaArchivosDTO(listaDesdeSP);

        radicarPdfDTO.setArchivos(lista);

        try {
            byte[] pdfRadicado = CoMerge.merge(radicarPdfDTO);

            if (pdfRadicado.length > 4) {
                //Realizar copia a servidor
                Documento documento = new Documento(radicarPdfDTO.getRuta(), pdfRadicado);
                try {
                    ConsultaObligaArchivoUtil.asociarArchivoDelDocumento(documento);
                } catch (Exception e) {
                    //No copio el archivo
                    //Llamar SP para enviar correo
                }
            } else {
                //Posible error al armar PDF
                String error = enviarCorreo(radicarPdfDTO);
                throw new Exception(error);
            }
            return pdfRadicado;
        } catch (Exception e) {
            enviarCorreo(radicarPdfDTO);
            throw e;
        }


    }

    private String enviarCorreo(RadicarPdfDTO radicarPdfDTO) {
        String error = "No ha sido posible armar el PDF de radicado";
        SoporteDTO soporteDTO = new SoporteDTO();
        soporteDTO.setViError(error);
        soporteDTO.setNiSecExpe(radicarPdfDTO.getSequenceThirdParty2());
        //Llamar SP para enviar correo porque no se guarda archivo en servidor
        enviarCorreo.soporte(soporteDTO);
        return error;
    }

    public byte[] construirPdfRadicadoDescarga(RadicarPdfDTO radicarPdfDTO) {
        //Consultar archivos
        List<Object> listaDesdeSP = consultaArchivoRepository.consultaObligaArchivos(radicarPdfDTO.getSequenceThirdParty(), radicarPdfDTO.getSequenceThirdParty2());
        List<ConsultaObligaArchivosDTO> lista = ConsultaObligaArchivoUtil.objectToConsultaObligaArchivosDTO(listaDesdeSP);

        radicarPdfDTO.setArchivos(lista);

        byte[] pdfRadicado = CoMerge.merge(radicarPdfDTO);

        return pdfRadicado;
    }
}
