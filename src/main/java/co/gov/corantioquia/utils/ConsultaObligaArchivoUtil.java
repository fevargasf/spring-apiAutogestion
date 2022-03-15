package co.gov.corantioquia.utils;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.DataPdf;
import jcifs.smb.SmbFile;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaObligaArchivoUtil {
    public static List<ConsultaObligaArchivosDTO> objectToConsultaObligaArchivosDTO(List<Object> listaDesdeSP){
        List<ConsultaObligaArchivosDTO> lista = new ArrayList<>();

        for (Object object : listaDesdeSP) {
            Object[] archivoAux= (Object[]) object;

            ConsultaObligaArchivosDTO consulta = new ConsultaObligaArchivosDTO();

            consulta.setObligaLinea(((BigDecimal)archivoAux[0]).intValue());
            consulta.setExpediente((String) archivoAux[1]);
            consulta.setObligaDescripcion((String) archivoAux[2]);
            consulta.setObligaObservacion((String) archivoAux[3]);
            consulta.setObligaFechaCreacion((Date) archivoAux[4]);
            consulta.setDocSecResol(String.valueOf(archivoAux[5]));
            consulta.setComoTermina((String) archivoAux[6]);
            consulta.setViMotivoParcial((String) archivoAux[7]);
            consulta.setRadicadoResolucion((String) archivoAux[8]);
            consulta.setArchivoNro((BigDecimal) archivoAux[9]);
            consulta.setArchivoNombre((String) archivoAux[10]);
            consulta.setArchivo((Blob) archivoAux[11]);
            consulta.setFechaIngresoArchivo((Date) archivoAux[12]);
            consulta.setFechaFoto((String) archivoAux[13]);
            consulta.setCoorX((BigDecimal) archivoAux[14]);
            consulta.setCoorY((BigDecimal) archivoAux[15]);


            lista.add(consulta);
        }
        return lista;
    }

    /**
     * Metodo que realiza la carga de un archivo procedimiento almacenado terminar
     *
     **/
    public static void asociarArchivoDelDocumento(Documento documento) throws Exception {
        try{
            UnidadRed ur = new UnidadRed();
            SmbFile archivo = new SmbFile(ur.apuntarPath(documento.getPrefijoServidor()) + documento.getRuta(),ur.autenticar());
            ur.copiarBytesAUnidad(documento.getArchivo(),archivo);
        }catch(Exception e){
            //getUrl().setError(e);
            e.printStackTrace();
            throw e;
        }
    }
}
