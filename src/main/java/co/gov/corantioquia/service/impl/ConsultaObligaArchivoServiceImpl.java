package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.*;
import co.gov.corantioquia.repository.ConsultaArchivoRepository;
import co.gov.corantioquia.service.ConsultaObligaArchivoService;
import co.gov.corantioquia.utils.CoMerge;
import co.gov.corantioquia.utils.ConsultaObligaArchivoUtil;
import co.gov.corantioquia.utils.Documento;
import co.gov.corantioquia.utils.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConsultaObligaArchivoServiceImpl implements ConsultaObligaArchivoService {
    @Autowired
    private ConsultaArchivoRepository consultaArchivoRepository;

    public ConsultaObligaArchivoServiceImpl(ConsultaArchivoRepository consultaArchivoRepository) {
        this.consultaArchivoRepository = consultaArchivoRepository;
    }
    @Override
    public byte[] listarArchivos(Integer sequenceThirdParty, Integer sequenceThirdParty2)  {
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        ListaArchivo listaArchivo = new ListaArchivo ();

        List<Object> listaDesdeSP = consultaArchivoRepository.consultaObligaArchivos(sequenceThirdParty, sequenceThirdParty2);

        List<ConsultaObligaArchivosDTO> lista = ConsultaObligaArchivoUtil.objectToConsultaObligaArchivosDTO(listaDesdeSP);
        listaArchivo.setConsultaObligaArchivos(lista);

        return CoMerge.merge(lista);
    }

    @Override
    public ListaArchivo listaArchivo(Integer sequenceThirdParty, Integer sequenceThirdParty2) {
        return null;
    }

    @Override
    public List<ListaTabla> listaTabla(Integer sequenceThirdParty, Integer sequenceThirdParty2) {
        List<ListaTabla> listaTablaList = new ArrayList<>();

        List<Object> objects =consultaArchivoRepository.consultaObligaArchivos(sequenceThirdParty, sequenceThirdParty2);
        List<TablaArchivosDTO> archivos = new ArrayList<>();

        for(Object archivo : objects){
            Object[] value =(Object[]) archivo;
            TablaArchivosDTO  dto = new TablaArchivosDTO();

            dto.setObligaLinea((BigDecimal) value[0]);
            dto.setExpediente((String) value[1]);
            dto.setObligaDescripcion((String) value[2]);
            dto.setObligaObservacion((String) value[3]);
            dto.setObligaFechaCreacion((Date) value[4]);
            dto.setDocSecResol((BigDecimal) value[5]);
            dto.setComoTermina((String) value[6]);
            dto.setViMotivoParcial((String) value[7]);
            dto.setRadicadoResolucion((String) value[8]);
            dto.setArchivoNro((BigDecimal) value[9]);
            dto.setArchivoNombre((String) value[10]);
           // dto.setArchivo((Blob) value[9]);
            dto.setFechaIngresoArchivo(String.valueOf(value[12]));
            dto.setFechaFoto(String.valueOf(value[13]));
            dto.setCoorX((BigDecimal) value[14]);
            dto.setCoorY((BigDecimal) value[15]);

            archivos.add(dto);
        }

        Map<BigDecimal, List<TablaArchivosDTO>> collect = archivos
                .stream()
                .collect(Collectors.groupingBy(TablaArchivosDTO::getObligaLinea));
        collect.entrySet().stream().sorted((o1, o2) -> {
            return o1.getKey().compareTo(o2.getKey());
        }).forEach(item -> {
            listaTablaList.add(new ListaTabla(item.getKey(), item.getValue()));
        });

        return listaTablaList;
    }

    @Override
    public String guardarArchivo(GuardarArchivoDTO guardarArchivoDTO) throws Exception {
        //Validar que no sea zip
        if(FileUtils.isCompressed(guardarArchivoDTO.getNombreArchivo())){
            //Guardar zip en servidor
            String fileName = guardarZip(guardarArchivoDTO);
            //Quitar blob de DTO
            guardarArchivoDTO.setArchivo(null);
            guardarArchivoDTO.setNombreArchivo(fileName);
        }
        return consultaArchivoRepository.guardarArchivo(guardarArchivoDTO);
    }

    private String guardarZip(GuardarArchivoDTO guardarArchivoDTO) throws Exception {
        //Generar nombre de archivo (ruta de archivo)
        // I-ABC/OBL-1/archivo.zip
        String fileName = guardarArchivoDTO.getExpediente()  + "/OBLIGACION-" + guardarArchivoDTO.getLine() +  "/" + guardarArchivoDTO.getNombreArchivo();

        Documento documento = new Documento(fileName, IOUtils.toByteArray(guardarArchivoDTO.getArchivo().getBinaryStream()),"AUTOGESTOR_");
        ConsultaObligaArchivoUtil.asociarArchivoDelDocumento(documento);

        return fileName;
    }

    @Override
    public String eliminarArchivo(EliminarArchivoDTO eliminarArchivoDTO) {
        String o = (String) consultaArchivoRepository.eliminarArchivo(eliminarArchivoDTO);
        return o;
    }

}
