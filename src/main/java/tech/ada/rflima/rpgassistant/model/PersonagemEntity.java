package tech.ada.rflima.rpgassistant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONAGEM")
public class PersonagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(name = "DESCRICAO_PERSONAGEM", columnDefinition = "BLOB")
    private String descricao;
    private String classe;
    private String raca;
    @ManyToOne
    @JoinColumn(name = "id_campanha", nullable = false)
    private CampanhaEntity campanha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public CampanhaEntity getCampanha() {
        return campanha;
    }

    public void setCampanha(CampanhaEntity campanha) {
        this.campanha = campanha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
