package tech.ada.rflima.rpgassistant.dto;

import java.util.List;

public class CampanhaDTO {

    private String nomeCampanha;
    private String descricaoCampanha;
    private String localCampanha;
    private List<LocacaoDTO> locacoes;
    private List<PersonagemDTO> personagens;

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
    }

    public String getDescricaoCampanha() {
        return descricaoCampanha;
    }

    public void setDescricaoCampanha(String descricaoCampanha) {
        this.descricaoCampanha = descricaoCampanha;
    }

    public String getLocalCampanha() {
        return localCampanha;
    }

    public void setLocalCampanha(String localCampanha) {
        this.localCampanha = localCampanha;
    }

    public List<LocacaoDTO> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<LocacaoDTO> locacoes) {
        this.locacoes = locacoes;
    }

    public List<PersonagemDTO> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemDTO> personagens) {
        this.personagens = personagens;
    }

}
