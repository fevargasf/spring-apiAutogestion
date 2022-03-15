package co.gov.corantioquia.models.dto;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

public class GuardarArchivoDTO extends BaseDTO {

    private String expediente;
    private Integer line;
    private String nombreObligacion;
    private String nombreArchivo;
    private Blob archivo;
    private Date fechaIngresoArchivo;
    private LocalDate fechaFoto;
    private Double coorX;
    private Double coorY;

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getNombreObligacion() {
        return nombreObligacion;
    }

    public void setNombreObligacion(String nombreObligacion) {
        this.nombreObligacion = nombreObligacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public LocalDate getFechaFoto() {
        return fechaFoto;
    }

    public void setFechaFoto(LocalDate fechaFoto) {
        this.fechaFoto = fechaFoto;
    }

    public Double getCoorX() {
        return coorX;
    }

    public void setCoorX(Double coorX) {
        this.coorX = coorX;
    }

    public Double getCoorY() {
        return coorY;
    }

    public void setCoorY(Double coorY) {
        this.coorY = coorY;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }
}
