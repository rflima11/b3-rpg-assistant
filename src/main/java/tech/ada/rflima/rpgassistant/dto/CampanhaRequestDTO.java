package tech.ada.rflima.rpgassistant.dto;

public class CampanhaRequestDTO {

    private String tema;

    public CampanhaRequestDTO(String tema) {
        this.tema = tema;
    }

    public CampanhaRequestDTO() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
