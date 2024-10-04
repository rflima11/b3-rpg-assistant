package tech.ada.rflima.rpgassistant.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CAMPANHA")
public class CampanhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tema;
    private String nomeCampanha;
    @Column(name = "DESCRICAO_CAMPANHA", columnDefinition = "BLOB")
    private String descricaoCampanha;
    private String localCampanha;

    private LocalDateTime dataHoraCriacao;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<LocacaoEntity> locacoes;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PersonagemEntity> personagens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

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

    public List<LocacaoEntity> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<LocacaoEntity> locacoes) {
        this.locacoes = locacoes;
    }

    public List<PersonagemEntity> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemEntity> personagens) {
        this.personagens = personagens;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
