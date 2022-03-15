package co.gov.corantioquia.models.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ConsultaObligaArchivosDTO {
    private Integer obligaLinea;
    private String expediente;
    private String obligaDescripcion;
    private String obligaObservacion;
    private Date obligaFechaCreacion;
    private String docSecResol;
    private String comoTermina;
    private String ViMotivoParcial;
    private String radicadoResolucion;
    private BigDecimal archivoNro;
    private String archivoNombre;
    private Blob archivo;
    private Date fechaIngresoArchivo;
    private String fechaFoto;
    private BigDecimal coorX;
    private BigDecimal coorY;
    private List<ConsultaObligaArchivosDTO> archivos;

    public List<ConsultaObligaArchivosDTO> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ConsultaObligaArchivosDTO> archivos) {
        this.archivos = archivos;
    }

    public String getViMotivoParcial() {
        return ViMotivoParcial;
    }

    public void setViMotivoParcial(String viMotivoParcial) {
        ViMotivoParcial = viMotivoParcial;
    }

    public String getComoTermina() {
        return comoTermina;
    }

    public void setComoTermina(String comoTermina) {
        this.comoTermina = comoTermina;
    }

    public Integer getObligaLinea() {
        return obligaLinea;
    }

    public void setObligaLinea(Integer obligaLinea) {
        this.obligaLinea = obligaLinea;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getObligaDescripcion() {
        return obligaDescripcion;
    }

    public void setObligaDescripcion(String obligaDescripcion) {
        this.obligaDescripcion = obligaDescripcion;
    }

    public String getObligaObservacion() {
        return obligaObservacion;
    }

    public void setObligaObservacion(String obligaObservacion) {
        this.obligaObservacion = obligaObservacion;
    }
    public Date getObligaFechaCreacion() {
        return obligaFechaCreacion;
    }

    public void setObligaFechaCreacion(Date obligaFechaCreacion) {
        this.obligaFechaCreacion = obligaFechaCreacion;
    }

    public BigDecimal getArchivoNro() {
        return archivoNro;
    }

    public void setArchivoNro(BigDecimal archivoNro) {
        this.archivoNro = archivoNro;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public Blob getArchivo() {
        return archivo;
    }

    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }

    public Date getFechaIngresoArchivo() {
        return fechaIngresoArchivo;
    }

    public void setFechaIngresoArchivo(Date fechaIngresoArchivo) {
        this.fechaIngresoArchivo = fechaIngresoArchivo;
    }

    public String getDocSecResol() {
        return docSecResol;
    }

    public void setDocSecResol(String docSecResol) {
        this.docSecResol = docSecResol;
    }

    public String getRadicadoResolucion() {
        return radicadoResolucion;
    }

    public void setRadicadoResolucion(String radicadoResolucion) {
        this.radicadoResolucion = radicadoResolucion;
    }

    public String getFechaFoto() {
        return fechaFoto;
    }

    public void setFechaFoto(String fechaFoto) {
        this.fechaFoto = fechaFoto;
    }

    public BigDecimal getCoorX() {
        return coorX==null? BigDecimal.valueOf(0) :coorX;
    }

    public void setCoorX(BigDecimal coorX) {
        this.coorX = coorX;
    }

    public BigDecimal getCoorY() {
        return coorY==null?   BigDecimal.valueOf(0) : coorY;
    }

    public void setCoorY(BigDecimal coorY) {
        this.coorY = coorY;
    }


}

