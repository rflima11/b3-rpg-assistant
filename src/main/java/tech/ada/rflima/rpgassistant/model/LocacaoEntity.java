package tech.ada.rflima.rpgassistant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCACAO")
public class LocacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(name = "DESCRICAO_LOCACAO", columnDefinition = "BLOB")
    private String descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CampanhaEntity getCampanha() {
        return campanha;
    }

    public void setCampanha(CampanhaEntity campanha) {
        this.campanha = campanha;
    }
}
