package co.gov.corantioquia.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class ListaTabla {

    private BigDecimal obligaLinea;
    private List<TablaArchivosDTO> archivos;

    public ListaTabla() {
    }

    public ListaTabla(BigDecimal obligaLinea, List<TablaArchivosDTO> archivos) {
        this.obligaLinea = obligaLinea;
        this.archivos = archivos;
    }

    public BigDecimal getObligaLinea() {
        return obligaLinea;
    }

    public void setObligaLinea(BigDecimal obligaLinea) {
        this.obligaLinea = obligaLinea;
    }

    public List<TablaArchivosDTO> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<TablaArchivosDTO> archivos) {
        this.archivos = archivos;
    }
}
