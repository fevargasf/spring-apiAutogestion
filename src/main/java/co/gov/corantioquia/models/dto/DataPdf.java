package co.gov.corantioquia.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DataPdf {

    private Integer anexos;
    private String radicado;
    private Date obligaFechaCreacion;
    private String fechaRadicado;
    private String respon_nombre;
    private String respon_correo;
    private String ter_celular;
    private String respon_cargo;
    private String ter_dir_municipio;
    private String expediente;
    private String docSecResol;
    private String archivoNombre;
    private String respon_telefono_celu;
    private String viMotivoParcial;
    private String ter_nombres;
    private String ter_email;

    public String getTer_email() {
        return ter_email;
    }

    public void setTer_email(String ter_email) {
        this.ter_email = ter_email;
    }

    public String getTer_nombres() {
        return ter_nombres;
    }

    public void setTer_nombres(String ter_nombres) {
        this.ter_nombres = ter_nombres;
    }

    public String getViMotivoParcial() {
        return viMotivoParcial.equals("N/A")? "TOTAL" :viMotivoParcial;
    }

    public void setViMotivoParcial(String viMotivoParcial) {
        this.viMotivoParcial = viMotivoParcial;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public Date getObligaFechaCreacion() {
        return obligaFechaCreacion;
    }

    public void setObligaFechaCreacion(Date obligaFechaCreacion) {
        this.obligaFechaCreacion = obligaFechaCreacion;
    }

    public String getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(String fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public String getRespon_nombre() {
        return respon_nombre;
    }

    public void setRespon_nombre(String respon_nombre) {
        this.respon_nombre = respon_nombre;
    }

    public String getRespon_correo() {
        return respon_correo;
    }

    public void setRespon_correo(String respon_correo) {
        this.respon_correo = respon_correo;
    }

    public String getTer_celular() {
        return ter_celular;
    }

    public void setTer_celular(String ter_celular) {
        this.ter_celular = ter_celular;
    }

    public String getRespon_cargo() {
        return respon_cargo;
    }

    public void setRespon_cargo(String respon_cargo) {
        this.respon_cargo = respon_cargo;
    }

    public String getTer_dir_municipio() {
        return ter_dir_municipio;
    }

    public void setTer_dir_municipio(String ter_dir_municipio) {
        this.ter_dir_municipio = ter_dir_municipio;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getDocSecResol() {
        return docSecResol;
    }

    public void setDocSecResol(String docSecResol) {
        this.docSecResol = docSecResol;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getRespon_telefono_celu() {
        return respon_telefono_celu;
    }

    public void setRespon_telefono_celu(String respon_telefono_celu) {
        this.respon_telefono_celu = respon_telefono_celu;
    }

    public Integer getAnexos() {
        return anexos;
    }

    public void setAnexos(Integer anexos) {
        this.anexos = anexos;
    }
}
