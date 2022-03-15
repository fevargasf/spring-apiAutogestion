package co.gov.corantioquia.service;

import co.gov.corantioquia.models.dto.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ConsultaObligaArchivoService {

   String eliminarArchivo(EliminarArchivoDTO eliminarArchivoDTO);

    byte[] listarArchivos(Integer sequenceThirdParty, Integer sequenceThirdParty2);
    ListaArchivo listaArchivo(Integer sequenceThirdParty, Integer sequenceThirdParty2);
    String guardarArchivo(GuardarArchivoDTO guardarArchivoDTO) throws Exception;



    List<ListaTabla> listaTabla(Integer sequenceThirdParty, Integer sequenceThirdParty2);
}
