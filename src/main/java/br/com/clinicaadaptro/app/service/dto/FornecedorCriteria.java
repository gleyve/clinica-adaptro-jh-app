package br.com.clinicaadaptro.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import br.com.clinicaadaptro.app.domain.enumeration.TipoPessoa;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link br.com.clinicaadaptro.app.domain.Fornecedor} entity. This class is used
 * in {@link br.com.clinicaadaptro.app.web.rest.FornecedorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fornecedors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FornecedorCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TipoPessoa
     */
    public static class TipoPessoaFilter extends Filter<TipoPessoa> {

        public TipoPessoaFilter() {
        }

        public TipoPessoaFilter(TipoPessoaFilter filter) {
            super(filter);
        }

        @Override
        public TipoPessoaFilter copy() {
            return new TipoPessoaFilter(this);
        }

    }
    /**
     * Class for filtering Estado
     */
    public static class EstadoFilter extends Filter<Estado> {

        public EstadoFilter() {
        }

        public EstadoFilter(EstadoFilter filter) {
            super(filter);
        }

        @Override
        public EstadoFilter copy() {
            return new EstadoFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private TipoPessoaFilter tipoPessoa;

    private StringFilter numeroCPF;

    private StringFilter numeroCNPJ;

    private StringFilter numeroInscricaoEstadual;

    private StringFilter nome;

    private StringFilter nomeFantasia;

    private StringFilter email;

    private StringFilter telefone1;

    private StringFilter telefone2;

    private StringFilter logradouroNome;

    private StringFilter logradouroNumero;

    private StringFilter logradouroComplemento;

    private StringFilter bairro;

    private StringFilter cep;

    private StringFilter cidade;

    private EstadoFilter estado;

    private StringFilter observacao;

    public FornecedorCriteria() {
    }

    public FornecedorCriteria(FornecedorCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tipoPessoa = other.tipoPessoa == null ? null : other.tipoPessoa.copy();
        this.numeroCPF = other.numeroCPF == null ? null : other.numeroCPF.copy();
        this.numeroCNPJ = other.numeroCNPJ == null ? null : other.numeroCNPJ.copy();
        this.numeroInscricaoEstadual = other.numeroInscricaoEstadual == null ? null : other.numeroInscricaoEstadual.copy();
        this.nome = other.nome == null ? null : other.nome.copy();
        this.nomeFantasia = other.nomeFantasia == null ? null : other.nomeFantasia.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.telefone1 = other.telefone1 == null ? null : other.telefone1.copy();
        this.telefone2 = other.telefone2 == null ? null : other.telefone2.copy();
        this.logradouroNome = other.logradouroNome == null ? null : other.logradouroNome.copy();
        this.logradouroNumero = other.logradouroNumero == null ? null : other.logradouroNumero.copy();
        this.logradouroComplemento = other.logradouroComplemento == null ? null : other.logradouroComplemento.copy();
        this.bairro = other.bairro == null ? null : other.bairro.copy();
        this.cep = other.cep == null ? null : other.cep.copy();
        this.cidade = other.cidade == null ? null : other.cidade.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.observacao = other.observacao == null ? null : other.observacao.copy();
    }

    @Override
    public FornecedorCriteria copy() {
        return new FornecedorCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public TipoPessoaFilter getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaFilter tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public StringFilter getNumeroCPF() {
        return numeroCPF;
    }

    public void setNumeroCPF(StringFilter numeroCPF) {
        this.numeroCPF = numeroCPF;
    }

    public StringFilter getNumeroCNPJ() {
        return numeroCNPJ;
    }

    public void setNumeroCNPJ(StringFilter numeroCNPJ) {
        this.numeroCNPJ = numeroCNPJ;
    }

    public StringFilter getNumeroInscricaoEstadual() {
        return numeroInscricaoEstadual;
    }

    public void setNumeroInscricaoEstadual(StringFilter numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(StringFilter nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(StringFilter telefone1) {
        this.telefone1 = telefone1;
    }

    public StringFilter getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(StringFilter telefone2) {
        this.telefone2 = telefone2;
    }

    public StringFilter getLogradouroNome() {
        return logradouroNome;
    }

    public void setLogradouroNome(StringFilter logradouroNome) {
        this.logradouroNome = logradouroNome;
    }

    public StringFilter getLogradouroNumero() {
        return logradouroNumero;
    }

    public void setLogradouroNumero(StringFilter logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
    }

    public StringFilter getLogradouroComplemento() {
        return logradouroComplemento;
    }

    public void setLogradouroComplemento(StringFilter logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
    }

    public StringFilter getBairro() {
        return bairro;
    }

    public void setBairro(StringFilter bairro) {
        this.bairro = bairro;
    }

    public StringFilter getCep() {
        return cep;
    }

    public void setCep(StringFilter cep) {
        this.cep = cep;
    }

    public StringFilter getCidade() {
        return cidade;
    }

    public void setCidade(StringFilter cidade) {
        this.cidade = cidade;
    }

    public EstadoFilter getEstado() {
        return estado;
    }

    public void setEstado(EstadoFilter estado) {
        this.estado = estado;
    }

    public StringFilter getObservacao() {
        return observacao;
    }

    public void setObservacao(StringFilter observacao) {
        this.observacao = observacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FornecedorCriteria that = (FornecedorCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tipoPessoa, that.tipoPessoa) &&
            Objects.equals(numeroCPF, that.numeroCPF) &&
            Objects.equals(numeroCNPJ, that.numeroCNPJ) &&
            Objects.equals(numeroInscricaoEstadual, that.numeroInscricaoEstadual) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(nomeFantasia, that.nomeFantasia) &&
            Objects.equals(email, that.email) &&
            Objects.equals(telefone1, that.telefone1) &&
            Objects.equals(telefone2, that.telefone2) &&
            Objects.equals(logradouroNome, that.logradouroNome) &&
            Objects.equals(logradouroNumero, that.logradouroNumero) &&
            Objects.equals(logradouroComplemento, that.logradouroComplemento) &&
            Objects.equals(bairro, that.bairro) &&
            Objects.equals(cep, that.cep) &&
            Objects.equals(cidade, that.cidade) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(observacao, that.observacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tipoPessoa,
        numeroCPF,
        numeroCNPJ,
        numeroInscricaoEstadual,
        nome,
        nomeFantasia,
        email,
        telefone1,
        telefone2,
        logradouroNome,
        logradouroNumero,
        logradouroComplemento,
        bairro,
        cep,
        cidade,
        estado,
        observacao
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FornecedorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tipoPessoa != null ? "tipoPessoa=" + tipoPessoa + ", " : "") +
                (numeroCPF != null ? "numeroCPF=" + numeroCPF + ", " : "") +
                (numeroCNPJ != null ? "numeroCNPJ=" + numeroCNPJ + ", " : "") +
                (numeroInscricaoEstadual != null ? "numeroInscricaoEstadual=" + numeroInscricaoEstadual + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (nomeFantasia != null ? "nomeFantasia=" + nomeFantasia + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (telefone1 != null ? "telefone1=" + telefone1 + ", " : "") +
                (telefone2 != null ? "telefone2=" + telefone2 + ", " : "") +
                (logradouroNome != null ? "logradouroNome=" + logradouroNome + ", " : "") +
                (logradouroNumero != null ? "logradouroNumero=" + logradouroNumero + ", " : "") +
                (logradouroComplemento != null ? "logradouroComplemento=" + logradouroComplemento + ", " : "") +
                (bairro != null ? "bairro=" + bairro + ", " : "") +
                (cep != null ? "cep=" + cep + ", " : "") +
                (cidade != null ? "cidade=" + cidade + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (observacao != null ? "observacao=" + observacao + ", " : "") +
            "}";
    }

}
