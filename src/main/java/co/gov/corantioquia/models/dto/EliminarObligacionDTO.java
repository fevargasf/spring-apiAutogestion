package co.gov.corantioquia.models.dto;

public class EliminarObligacionDTO extends BaseDTO {
    private Boolean result = true;
    public int niLinea;
    public  String voError;

    public EliminarObligacionDTO() {

    }

    public EliminarObligacionDTO(Boolean result, Integer niLinea,String voError){
        this.result = result;
        this.niLinea = niLinea;
        this.voError=voError;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getVoError() {
        return voError;
    }

    public void setVoError(String voError) {
        this.voError = voError;
    }

    public int getniLinea() {
        return niLinea;
    }

    public void setniLinea(Integer niLinea) {
        this.niLinea = niLinea;
    }
}
