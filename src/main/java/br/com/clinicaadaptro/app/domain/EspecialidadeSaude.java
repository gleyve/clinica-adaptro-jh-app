package br.com.clinicaadaptro.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EspecialidadeSaude.
 */
@Entity
@Table(name = "especialidade_saude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EspecialidadeSaude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3, max = 70)
    @Column(name = "descricao", length = 70, nullable = false, unique = true)
    private String descricao;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "sigla", length = 10, nullable = false, unique = true)
    private String sigla;

    @Column(name = "ativo")
    private Boolean ativo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public EspecialidadeSaude descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public EspecialidadeSaude sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public EspecialidadeSaude ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EspecialidadeSaude)) {
            return false;
        }
        return id != null && id.equals(((EspecialidadeSaude) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EspecialidadeSaude{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }
}
