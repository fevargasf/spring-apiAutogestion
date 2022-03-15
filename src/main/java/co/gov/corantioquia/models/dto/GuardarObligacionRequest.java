package co.gov.corantioquia.models.dto;

public class GuardarObligacionRequest extends BaseDTO{
    private int secResol;
    private String descripcion;
    private String observaciones;
    private String comoTermina;
    private String viMotivoParcial;
    private int nioLinea;

    public int getSecResol() {
        return secResol;
    }

    public void setSecResol(int secResol) {
        this.secResol = secResol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

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

    public int getNioLinea() {
        return nioLinea;
    }

    public void setNioLinea(int nioLinea) {
        this.nioLinea = nioLinea;
    }
}
