package co.gov.corantioquia.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class TablaArchivosDTO extends BaseDTO implements Serializable {

    private BigDecimal obligaLinea;
    private String expediente;
    private String obligaDescripcion;
    private String obligaObservacion;
    private Date obligaFechaCreacion;
    private BigDecimal docSecResol;
    private String comoTermina;
    private String viMotivoParcial;
    private String radicadoResolucion;
    private BigDecimal archivoNro;
    private String archivoNombre;
    private Blob archivo;
    private String fechaIngresoArchivo;
    private String fechaFoto;
    private BigDecimal coorX;
    private BigDecimal coorY;

    public String getComoTermina() {
        return comoTermina;
    }

    public void setComoTermina(String comoTermina) {
        this.comoTermina = comoTermina;
    }

    public String getViMotivoParcial() {
        return viMotivoParcial;
    }

    public void setViMotivoParcial(String viMotivoParcial) {
        this.viMotivoParcial = viMotivoParcial;
    }

    public BigDecimal getObligaLinea() {
        return obligaLinea;
    }

    public void setObligaLinea(BigDecimal obligaLinea) {
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

    public BigDecimal getDocSecResol() {
        return docSecResol;
    }

    public void setDocSecResol(BigDecimal docSecResol) {
        this.docSecResol = docSecResol;
    }

    public String getRadicadoResolucion() {
        return radicadoResolucion;
    }

    public void setRadicadoResolucion(String radicadoResolucion) {
        this.radicadoResolucion = radicadoResolucion;
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

    public String getFechaIngresoArchivo() {
        return fechaIngresoArchivo;
    }

    public void setFechaIngresoArchivo(String fechaIngresoArchivo) {
        this.fechaIngresoArchivo = fechaIngresoArchivo;
    }

    public String getFechaFoto() {
        return fechaFoto;
    }

    public void setFechaFoto(String fechaFoto) {
        this.fechaFoto = fechaFoto;
    }

    public BigDecimal getCoorX() {
        return coorX;
    }

    public void setCoorX(BigDecimal coorX) {
        this.coorX = coorX;
    }

    public BigDecimal getCoorY() {
        return coorY;
    }

    public void setCoorY(BigDecimal coorY) {
        this.coorY = coorY;
    }
}
