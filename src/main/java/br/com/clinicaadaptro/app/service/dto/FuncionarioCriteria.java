package br.com.clinicaadaptro.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import br.com.clinicaadaptro.app.domain.enumeration.Sexo;
import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;
import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;
import br.com.clinicaadaptro.app.domain.enumeration.CategoriaFuncionario;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link br.com.clinicaadaptro.app.domain.Funcionario} entity. This class is used
 * in {@link br.com.clinicaadaptro.app.web.rest.FuncionarioResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /funcionarios?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FuncionarioCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Sexo
     */
    public static class SexoFilter extends Filter<Sexo> {

        public SexoFilter() {
        }

        public SexoFilter(SexoFilter filter) {
            super(filter);
        }

        @Override
        public SexoFilter copy() {
            return new SexoFilter(this);
        }

    }
    /**
     * Class for filtering EstadoCivil
     */
    public static class EstadoCivilFilter extends Filter<EstadoCivil> {

        public EstadoCivilFilter() {
        }

        public EstadoCivilFilter(EstadoCivilFilter filter) {
            super(filter);
        }

        @Override
        public EstadoCivilFilter copy() {
            return new EstadoCivilFilter(this);
        }

    }
    /**
     * Class for filtering Escolaridade
     */
    public static class EscolaridadeFilter extends Filter<Escolaridade> {

        public EscolaridadeFilter() {
        }

        public EscolaridadeFilter(EscolaridadeFilter filter) {
            super(filter);
        }

        @Override
        public EscolaridadeFilter copy() {
            return new EscolaridadeFilter(this);
        }

    }
    /**
     * Class for filtering CategoriaFuncionario
     */
    public static class CategoriaFuncionarioFilter extends Filter<CategoriaFuncionario> {

        public CategoriaFuncionarioFilter() {
        }

        public CategoriaFuncionarioFilter(CategoriaFuncionarioFilter filter) {
            super(filter);
        }

        @Override
        public CategoriaFuncionarioFilter copy() {
            return new CategoriaFuncionarioFilter(this);
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

    private StringFilter nomeCompleto;

    private LocalDateFilter dataNascimento;

    private StringFilter numeroConselhoProfissional;

    private StringFilter cpf;

    private StringFilter rg;

    private StringFilter cnh;

    private StringFilter telefone1;

    private StringFilter telefone2;

    private StringFilter email;

    private LocalDateFilter dataAdmissao;

    private LocalDateFilter dataDesligamento;

    private LongFilter salario;

    private SexoFilter sexo;

    private EstadoCivilFilter estadoCivil;

    private EscolaridadeFilter escolaridade;

    private CategoriaFuncionarioFilter funcao;

    private StringFilter descOutraFuncao;

    private StringFilter logradouroNome;

    private StringFilter logradouroNumero;

    private StringFilter logradouroComplemento;

    private StringFilter bairro;

    private StringFilter proximidadesLogradouro;

    private StringFilter cep;

    private StringFilter cidade;

    private EstadoFilter estado;

    private InstantFilter dataHoraCadastro;

    private StringFilter observacao;

    private LongFilter userId;

    private LongFilter especialidadeSaudeId;

    public FuncionarioCriteria() {
    }

    public FuncionarioCriteria(FuncionarioCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nomeCompleto = other.nomeCompleto == null ? null : other.nomeCompleto.copy();
        this.dataNascimento = other.dataNascimento == null ? null : other.dataNascimento.copy();
        this.numeroConselhoProfissional = other.numeroConselhoProfissional == null ? null : other.numeroConselhoProfissional.copy();
        this.cpf = other.cpf == null ? null : other.cpf.copy();
        this.rg = other.rg == null ? null : other.rg.copy();
        this.cnh = other.cnh == null ? null : other.cnh.copy();
        this.telefone1 = other.telefone1 == null ? null : other.telefone1.copy();
        this.telefone2 = other.telefone2 == null ? null : other.telefone2.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.dataAdmissao = other.dataAdmissao == null ? null : other.dataAdmissao.copy();
        this.dataDesligamento = other.dataDesligamento == null ? null : other.dataDesligamento.copy();
        this.salario = other.salario == null ? null : other.salario.copy();
        this.sexo = other.sexo == null ? null : other.sexo.copy();
        this.estadoCivil = other.estadoCivil == null ? null : other.estadoCivil.copy();
        this.escolaridade = other.escolaridade == null ? null : other.escolaridade.copy();
        this.funcao = other.funcao == null ? null : other.funcao.copy();
        this.descOutraFuncao = other.descOutraFuncao == null ? null : other.descOutraFuncao.copy();
        this.logradouroNome = other.logradouroNome == null ? null : other.logradouroNome.copy();
        this.logradouroNumero = other.logradouroNumero == null ? null : other.logradouroNumero.copy();
        this.logradouroComplemento = other.logradouroComplemento == null ? null : other.logradouroComplemento.copy();
        this.bairro = other.bairro == null ? null : other.bairro.copy();
        this.proximidadesLogradouro = other.proximidadesLogradouro == null ? null : other.proximidadesLogradouro.copy();
        this.cep = other.cep == null ? null : other.cep.copy();
        this.cidade = other.cidade == null ? null : other.cidade.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.dataHoraCadastro = other.dataHoraCadastro == null ? null : other.dataHoraCadastro.copy();
        this.observacao = other.observacao == null ? null : other.observacao.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.especialidadeSaudeId = other.especialidadeSaudeId == null ? null : other.especialidadeSaudeId.copy();
    }

    @Override
    public FuncionarioCriteria copy() {
        return new FuncionarioCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(StringFilter nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDateFilter getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateFilter dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public StringFilter getNumeroConselhoProfissional() {
        return numeroConselhoProfissional;
    }

    public void setNumeroConselhoProfissional(StringFilter numeroConselhoProfissional) {
        this.numeroConselhoProfissional = numeroConselhoProfissional;
    }

    public StringFilter getCpf() {
        return cpf;
    }

    public void setCpf(StringFilter cpf) {
        this.cpf = cpf;
    }

    public StringFilter getRg() {
        return rg;
    }

    public void setRg(StringFilter rg) {
        this.rg = rg;
    }

    public StringFilter getCnh() {
        return cnh;
    }

    public void setCnh(StringFilter cnh) {
        this.cnh = cnh;
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

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public LocalDateFilter getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDateFilter dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDateFilter getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(LocalDateFilter dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public LongFilter getSalario() {
        return salario;
    }

    public void setSalario(LongFilter salario) {
        this.salario = salario;
    }

    public SexoFilter getSexo() {
        return sexo;
    }

    public void setSexo(SexoFilter sexo) {
        this.sexo = sexo;
    }

    public EstadoCivilFilter getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilFilter estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public EscolaridadeFilter getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(EscolaridadeFilter escolaridade) {
        this.escolaridade = escolaridade;
    }

    public CategoriaFuncionarioFilter getFuncao() {
        return funcao;
    }

    public void setFuncao(CategoriaFuncionarioFilter funcao) {
        this.funcao = funcao;
    }

    public StringFilter getDescOutraFuncao() {
        return descOutraFuncao;
    }

    public void setDescOutraFuncao(StringFilter descOutraFuncao) {
        this.descOutraFuncao = descOutraFuncao;
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

    public StringFilter getProximidadesLogradouro() {
        return proximidadesLogradouro;
    }

    public void setProximidadesLogradouro(StringFilter proximidadesLogradouro) {
        this.proximidadesLogradouro = proximidadesLogradouro;
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

    public InstantFilter getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(InstantFilter dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public StringFilter getObservacao() {
        return observacao;
    }

    public void setObservacao(StringFilter observacao) {
        this.observacao = observacao;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getEspecialidadeSaudeId() {
        return especialidadeSaudeId;
    }

    public void setEspecialidadeSaudeId(LongFilter especialidadeSaudeId) {
        this.especialidadeSaudeId = especialidadeSaudeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FuncionarioCriteria that = (FuncionarioCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nomeCompleto, that.nomeCompleto) &&
            Objects.equals(dataNascimento, that.dataNascimento) &&
            Objects.equals(numeroConselhoProfissional, that.numeroConselhoProfissional) &&
            Objects.equals(cpf, that.cpf) &&
            Objects.equals(rg, that.rg) &&
            Objects.equals(cnh, that.cnh) &&
            Objects.equals(telefone1, that.telefone1) &&
            Objects.equals(telefone2, that.telefone2) &&
            Objects.equals(email, that.email) &&
            Objects.equals(dataAdmissao, that.dataAdmissao) &&
            Objects.equals(dataDesligamento, that.dataDesligamento) &&
            Objects.equals(salario, that.salario) &&
            Objects.equals(sexo, that.sexo) &&
            Objects.equals(estadoCivil, that.estadoCivil) &&
            Objects.equals(escolaridade, that.escolaridade) &&
            Objects.equals(funcao, that.funcao) &&
            Objects.equals(descOutraFuncao, that.descOutraFuncao) &&
            Objects.equals(logradouroNome, that.logradouroNome) &&
            Objects.equals(logradouroNumero, that.logradouroNumero) &&
            Objects.equals(logradouroComplemento, that.logradouroComplemento) &&
            Objects.equals(bairro, that.bairro) &&
            Objects.equals(proximidadesLogradouro, that.proximidadesLogradouro) &&
            Objects.equals(cep, that.cep) &&
            Objects.equals(cidade, that.cidade) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(dataHoraCadastro, that.dataHoraCadastro) &&
            Objects.equals(observacao, that.observacao) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(especialidadeSaudeId, that.especialidadeSaudeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nomeCompleto,
        dataNascimento,
        numeroConselhoProfissional,
        cpf,
        rg,
        cnh,
        telefone1,
        telefone2,
        email,
        dataAdmissao,
        dataDesligamento,
        salario,
        sexo,
        estadoCivil,
        escolaridade,
        funcao,
        descOutraFuncao,
        logradouroNome,
        logradouroNumero,
        logradouroComplemento,
        bairro,
        proximidadesLogradouro,
        cep,
        cidade,
        estado,
        dataHoraCadastro,
        observacao,
        userId,
        especialidadeSaudeId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FuncionarioCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nomeCompleto != null ? "nomeCompleto=" + nomeCompleto + ", " : "") +
                (dataNascimento != null ? "dataNascimento=" + dataNascimento + ", " : "") +
                (numeroConselhoProfissional != null ? "numeroConselhoProfissional=" + numeroConselhoProfissional + ", " : "") +
                (cpf != null ? "cpf=" + cpf + ", " : "") +
                (rg != null ? "rg=" + rg + ", " : "") +
                (cnh != null ? "cnh=" + cnh + ", " : "") +
                (telefone1 != null ? "telefone1=" + telefone1 + ", " : "") +
                (telefone2 != null ? "telefone2=" + telefone2 + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (dataAdmissao != null ? "dataAdmissao=" + dataAdmissao + ", " : "") +
                (dataDesligamento != null ? "dataDesligamento=" + dataDesligamento + ", " : "") +
                (salario != null ? "salario=" + salario + ", " : "") +
                (sexo != null ? "sexo=" + sexo + ", " : "") +
                (estadoCivil != null ? "estadoCivil=" + estadoCivil + ", " : "") +
                (escolaridade != null ? "escolaridade=" + escolaridade + ", " : "") +
                (funcao != null ? "funcao=" + funcao + ", " : "") +
                (descOutraFuncao != null ? "descOutraFuncao=" + descOutraFuncao + ", " : "") +
                (logradouroNome != null ? "logradouroNome=" + logradouroNome + ", " : "") +
                (logradouroNumero != null ? "logradouroNumero=" + logradouroNumero + ", " : "") +
                (logradouroComplemento != null ? "logradouroComplemento=" + logradouroComplemento + ", " : "") +
                (bairro != null ? "bairro=" + bairro + ", " : "") +
                (proximidadesLogradouro != null ? "proximidadesLogradouro=" + proximidadesLogradouro + ", " : "") +
                (cep != null ? "cep=" + cep + ", " : "") +
                (cidade != null ? "cidade=" + cidade + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (dataHoraCadastro != null ? "dataHoraCadastro=" + dataHoraCadastro + ", " : "") +
                (observacao != null ? "observacao=" + observacao + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (especialidadeSaudeId != null ? "especialidadeSaudeId=" + especialidadeSaudeId + ", " : "") +
            "}";
    }

}
