package br.com.clinicaadaptro.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import br.com.clinicaadaptro.app.domain.enumeration.TipoPessoa;

import br.com.clinicaadaptro.app.domain.enumeration.Estado;

/**
 * A Fornecedor.
 */
@Entity
@Table(name = "fornecedor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    @Size(max = 11)
    @Pattern(regexp = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
    @Column(name = "numero_cpf", length = 11, unique = true)
    private String numeroCPF;

    @Size(max = 15)
    @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})")
    @Column(name = "numero_cnpj", length = 15, unique = true)
    private String numeroCNPJ;

    @Size(max = 9)
    @Column(name = "numero_inscricao_estadual", length = 9, unique = true)
    private String numeroInscricaoEstadual;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;

    @Size(max = 120)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", length = 120)
    private String email;

    @Size(max = 15)
    @Pattern(regexp = "^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$")
    @Column(name = "telefone_1", length = 15)
    private String telefone1;

    @Size(max = 15)
    @Pattern(regexp = "^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$")
    @Column(name = "telefone_2", length = 15)
    private String telefone2;

    @Size(min = 2, max = 70)
    @Column(name = "logradouro_nome", length = 70)
    private String logradouroNome;

    @Size(max = 4)
    @Column(name = "logradouro_numero", length = 4)
    private String logradouroNumero;

    @Size(max = 50)
    @Column(name = "logradouro_complemento", length = 50)
    private String logradouroComplemento;

    @Size(min = 2, max = 30)
    @Column(name = "bairro", length = 30)
    private String bairro;

    @Size(max = 8)
    //@Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    @Pattern(regexp = "(\\\\d{8})")
    @Column(name = "cep", length = 8)
    private String cep;

    @Size(min = 2, max = 30)
    @Column(name = "cidade", length = 30)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Size(max = 1000)
    @Column(name = "observacao", length = 1000)
    private String observacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public Fornecedor tipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        return this;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNumeroCPF() {
        return numeroCPF;
    }

    public Fornecedor numeroCPF(String numeroCPF) {
        this.numeroCPF = numeroCPF;
        return this;
    }

    public void setNumeroCPF(String numeroCPF) {
        this.numeroCPF = numeroCPF;
    }

    public String getNumeroCNPJ() {
        return numeroCNPJ;
    }

    public Fornecedor numeroCNPJ(String numeroCNPJ) {
        this.numeroCNPJ = numeroCNPJ;
        return this;
    }

    public void setNumeroCNPJ(String numeroCNPJ) {
        this.numeroCNPJ = numeroCNPJ;
    }

    public String getNumeroInscricaoEstadual() {
        return numeroInscricaoEstadual;
    }

    public Fornecedor numeroInscricaoEstadual(String numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
        return this;
    }

    public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
    }

    public String getNome() {
        return nome;
    }

    public Fornecedor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public Fornecedor nomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public Fornecedor email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public Fornecedor telefone1(String telefone1) {
        this.telefone1 = telefone1;
        return this;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public Fornecedor telefone2(String telefone2) {
        this.telefone2 = telefone2;
        return this;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getLogradouroNome() {
        return logradouroNome;
    }

    public Fornecedor logradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
        return this;
    }

    public void setLogradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
    }

    public String getLogradouroNumero() {
        return logradouroNumero;
    }

    public Fornecedor logradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
        return this;
    }

    public void setLogradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
    }

    public String getLogradouroComplemento() {
        return logradouroComplemento;
    }

    public Fornecedor logradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
        return this;
    }

    public void setLogradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
    }

    public String getBairro() {
        return bairro;
    }

    public Fornecedor bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public Fornecedor cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public Fornecedor cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Fornecedor estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getObservacao() {
        return observacao;
    }

    public Fornecedor observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fornecedor)) {
            return false;
        }
        return id != null && id.equals(((Fornecedor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fornecedor{" +
            "id=" + getId() +
            ", tipoPessoa='" + getTipoPessoa() + "'" +
            ", numeroCPF='" + getNumeroCPF() + "'" +
            ", numeroCNPJ='" + getNumeroCNPJ() + "'" +
            ", numeroInscricaoEstadual='" + getNumeroInscricaoEstadual() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefone1='" + getTelefone1() + "'" +
            ", telefone2='" + getTelefone2() + "'" +
            ", logradouroNome='" + getLogradouroNome() + "'" +
            ", logradouroNumero='" + getLogradouroNumero() + "'" +
            ", logradouroComplemento='" + getLogradouroComplemento() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cep='" + getCep() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", observacao='" + getObservacao() + "'" +
            "}";
    }
}
