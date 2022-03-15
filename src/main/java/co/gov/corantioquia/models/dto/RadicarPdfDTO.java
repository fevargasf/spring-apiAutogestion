package co.gov.corantioquia.models.dto;

import java.util.Date;
import java.util.List;

public class RadicarPdfDTO extends BaseDTO {


   private  List<ConsultaObligaArchivosDTO> archivos;
    private ExpedientDTO expedientDTO;
    private RadicarDTO radicarDTO;
    private ConsultaObligaArchivosDTO consultaObligaArchivosDTO;
    private String radicado;
    private String fechaRadicado;
    private String ruta;

    public RadicarDTO getRadicarDTO() {return radicarDTO;}

    public void setRadicarDTO(RadicarDTO radicarDTO) {
        this.radicarDTO = radicarDTO;
    }

    public ConsultaObligaArchivosDTO getConsultaObligaArchivosDTO() {
        return consultaObligaArchivosDTO;
    }

    public void setConsultaObligaArchivosDTO(ConsultaObligaArchivosDTO consultaObligaArchivosDTO) {
        this.consultaObligaArchivosDTO = consultaObligaArchivosDTO;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<ConsultaObligaArchivosDTO> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ConsultaObligaArchivosDTO> archivos) {
        this.archivos = archivos;
    }

    public ExpedientDTO getExpedientDTO() {
        return expedientDTO;
    }

    public void setExpedientDTO(ExpedientDTO expedientDTO) {
        this.expedientDTO = expedientDTO;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public String getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(String fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }
}
