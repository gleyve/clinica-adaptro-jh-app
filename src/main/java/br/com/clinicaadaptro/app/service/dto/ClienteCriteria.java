package br.com.clinicaadaptro.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import br.com.clinicaadaptro.app.domain.enumeration.TipoCliente;
import br.com.clinicaadaptro.app.domain.enumeration.Sexo;
import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;
import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;
import br.com.clinicaadaptro.app.domain.enumeration.Convenio;
import br.com.clinicaadaptro.app.domain.enumeration.Procedencia;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
import br.com.clinicaadaptro.app.domain.enumeration.TipoParentesco;
import br.com.clinicaadaptro.app.domain.enumeration.TipoParentesco;
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
 * Criteria class for the {@link br.com.clinicaadaptro.app.domain.Cliente} entity. This class is used
 * in {@link br.com.clinicaadaptro.app.web.rest.ClienteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /clientes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClienteCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TipoCliente
     */
    public static class TipoClienteFilter extends Filter<TipoCliente> {

        public TipoClienteFilter() {
        }

        public TipoClienteFilter(TipoClienteFilter filter) {
            super(filter);
        }

        @Override
        public TipoClienteFilter copy() {
            return new TipoClienteFilter(this);
        }

    }
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
     * Class for filtering Convenio
     */
    public static class ConvenioFilter extends Filter<Convenio> {

        public ConvenioFilter() {
        }

        public ConvenioFilter(ConvenioFilter filter) {
            super(filter);
        }

        @Override
        public ConvenioFilter copy() {
            return new ConvenioFilter(this);
        }

    }
    /**
     * Class for filtering Procedencia
     */
    public static class ProcedenciaFilter extends Filter<Procedencia> {

        public ProcedenciaFilter() {
        }

        public ProcedenciaFilter(ProcedenciaFilter filter) {
            super(filter);
        }

        @Override
        public ProcedenciaFilter copy() {
            return new ProcedenciaFilter(this);
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
    /**
     * Class for filtering TipoParentesco
     */
    public static class TipoParentescoFilter extends Filter<TipoParentesco> {

        public TipoParentescoFilter() {
        }

        public TipoParentescoFilter(TipoParentescoFilter filter) {
            super(filter);
        }

        @Override
        public TipoParentescoFilter copy() {
            return new TipoParentescoFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private TipoClienteFilter tipoCliente;

    private StringFilter matricula;

    private StringFilter nomeCompleto;

    private LocalDateFilter dataNascimento;

    private SexoFilter sexo;

    private EstadoCivilFilter estadoCivil;

    private EscolaridadeFilter escolaridade;

    private ConvenioFilter convenio;

    private StringFilter numCarteirinhaConvenio;

    private LocalDateFilter dataValidadeConvenio;

    private ProcedenciaFilter procedencia;

    private StringFilter profissao;

    private StringFilter cpf;

    private StringFilter rg;

    private StringFilter telefone1;

    private StringFilter telefone2;

    private StringFilter email;

    private StringFilter logradouroNome;

    private StringFilter logradouroNumero;

    private StringFilter logradouroComplemento;

    private StringFilter bairro;

    private StringFilter cep;

    private StringFilter cidade;

    private EstadoFilter estado;

    private TipoParentescoFilter parentescoResponsavel;

    private TipoParentescoFilter parentescoResponsavelFinanceiro;

    private InstantFilter dataHoraCadastro;

    private BooleanFilter ativo;

    private StringFilter observacao;

    private LongFilter planoEstrategicoId;

    private LongFilter responsavelId;

    private LongFilter responsavelFinanceiroId;

    public ClienteCriteria() {
    }

    public ClienteCriteria(ClienteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tipoCliente = other.tipoCliente == null ? null : other.tipoCliente.copy();
        this.matricula = other.matricula == null ? null : other.matricula.copy();
        this.nomeCompleto = other.nomeCompleto == null ? null : other.nomeCompleto.copy();
        this.dataNascimento = other.dataNascimento == null ? null : other.dataNascimento.copy();
        this.sexo = other.sexo == null ? null : other.sexo.copy();
        this.estadoCivil = other.estadoCivil == null ? null : other.estadoCivil.copy();
        this.escolaridade = other.escolaridade == null ? null : other.escolaridade.copy();
        this.convenio = other.convenio == null ? null : other.convenio.copy();
        this.numCarteirinhaConvenio = other.numCarteirinhaConvenio == null ? null : other.numCarteirinhaConvenio.copy();
        this.dataValidadeConvenio = other.dataValidadeConvenio == null ? null : other.dataValidadeConvenio.copy();
        this.procedencia = other.procedencia == null ? null : other.procedencia.copy();
        this.profissao = other.profissao == null ? null : other.profissao.copy();
        this.cpf = other.cpf == null ? null : other.cpf.copy();
        this.rg = other.rg == null ? null : other.rg.copy();
        this.telefone1 = other.telefone1 == null ? null : other.telefone1.copy();
        this.telefone2 = other.telefone2 == null ? null : other.telefone2.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.logradouroNome = other.logradouroNome == null ? null : other.logradouroNome.copy();
        this.logradouroNumero = other.logradouroNumero == null ? null : other.logradouroNumero.copy();
        this.logradouroComplemento = other.logradouroComplemento == null ? null : other.logradouroComplemento.copy();
        this.bairro = other.bairro == null ? null : other.bairro.copy();
        this.cep = other.cep == null ? null : other.cep.copy();
        this.cidade = other.cidade == null ? null : other.cidade.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.parentescoResponsavel = other.parentescoResponsavel == null ? null : other.parentescoResponsavel.copy();
        this.parentescoResponsavelFinanceiro = other.parentescoResponsavelFinanceiro == null ? null : other.parentescoResponsavelFinanceiro.copy();
        this.dataHoraCadastro = other.dataHoraCadastro == null ? null : other.dataHoraCadastro.copy();
        this.ativo = other.ativo == null ? null : other.ativo.copy();
        this.observacao = other.observacao == null ? null : other.observacao.copy();
        this.planoEstrategicoId = other.planoEstrategicoId == null ? null : other.planoEstrategicoId.copy();
        this.responsavelId = other.responsavelId == null ? null : other.responsavelId.copy();
        this.responsavelFinanceiroId = other.responsavelFinanceiroId == null ? null : other.responsavelFinanceiroId.copy();
    }

    @Override
    public ClienteCriteria copy() {
        return new ClienteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public TipoClienteFilter getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoClienteFilter tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public StringFilter getMatricula() {
        return matricula;
    }

    public void setMatricula(StringFilter matricula) {
        this.matricula = matricula;
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

    public ConvenioFilter getConvenio() {
        return convenio;
    }

    public void setConvenio(ConvenioFilter convenio) {
        this.convenio = convenio;
    }

    public StringFilter getNumCarteirinhaConvenio() {
        return numCarteirinhaConvenio;
    }

    public void setNumCarteirinhaConvenio(StringFilter numCarteirinhaConvenio) {
        this.numCarteirinhaConvenio = numCarteirinhaConvenio;
    }

    public LocalDateFilter getDataValidadeConvenio() {
        return dataValidadeConvenio;
    }

    public void setDataValidadeConvenio(LocalDateFilter dataValidadeConvenio) {
        this.dataValidadeConvenio = dataValidadeConvenio;
    }

    public ProcedenciaFilter getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(ProcedenciaFilter procedencia) {
        this.procedencia = procedencia;
    }

    public StringFilter getProfissao() {
        return profissao;
    }

    public void setProfissao(StringFilter profissao) {
        this.profissao = profissao;
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

    public TipoParentescoFilter getParentescoResponsavel() {
        return parentescoResponsavel;
    }

    public void setParentescoResponsavel(TipoParentescoFilter parentescoResponsavel) {
        this.parentescoResponsavel = parentescoResponsavel;
    }

    public TipoParentescoFilter getParentescoResponsavelFinanceiro() {
        return parentescoResponsavelFinanceiro;
    }

    public void setParentescoResponsavelFinanceiro(TipoParentescoFilter parentescoResponsavelFinanceiro) {
        this.parentescoResponsavelFinanceiro = parentescoResponsavelFinanceiro;
    }

    public InstantFilter getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(InstantFilter dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public BooleanFilter getAtivo() {
        return ativo;
    }

    public void setAtivo(BooleanFilter ativo) {
        this.ativo = ativo;
    }

    public StringFilter getObservacao() {
        return observacao;
    }

    public void setObservacao(StringFilter observacao) {
        this.observacao = observacao;
    }

    public LongFilter getPlanoEstrategicoId() {
        return planoEstrategicoId;
    }

    public void setPlanoEstrategicoId(LongFilter planoEstrategicoId) {
        this.planoEstrategicoId = planoEstrategicoId;
    }

    public LongFilter getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(LongFilter responsavelId) {
        this.responsavelId = responsavelId;
    }

    public LongFilter getResponsavelFinanceiroId() {
        return responsavelFinanceiroId;
    }

    public void setResponsavelFinanceiroId(LongFilter responsavelFinanceiroId) {
        this.responsavelFinanceiroId = responsavelFinanceiroId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClienteCriteria that = (ClienteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tipoCliente, that.tipoCliente) &&
            Objects.equals(matricula, that.matricula) &&
            Objects.equals(nomeCompleto, that.nomeCompleto) &&
            Objects.equals(dataNascimento, that.dataNascimento) &&
            Objects.equals(sexo, that.sexo) &&
            Objects.equals(estadoCivil, that.estadoCivil) &&
            Objects.equals(escolaridade, that.escolaridade) &&
            Objects.equals(convenio, that.convenio) &&
            Objects.equals(numCarteirinhaConvenio, that.numCarteirinhaConvenio) &&
            Objects.equals(dataValidadeConvenio, that.dataValidadeConvenio) &&
            Objects.equals(procedencia, that.procedencia) &&
            Objects.equals(profissao, that.profissao) &&
            Objects.equals(cpf, that.cpf) &&
            Objects.equals(rg, that.rg) &&
            Objects.equals(telefone1, that.telefone1) &&
            Objects.equals(telefone2, that.telefone2) &&
            Objects.equals(email, that.email) &&
            Objects.equals(logradouroNome, that.logradouroNome) &&
            Objects.equals(logradouroNumero, that.logradouroNumero) &&
            Objects.equals(logradouroComplemento, that.logradouroComplemento) &&
            Objects.equals(bairro, that.bairro) &&
            Objects.equals(cep, that.cep) &&
            Objects.equals(cidade, that.cidade) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(parentescoResponsavel, that.parentescoResponsavel) &&
            Objects.equals(parentescoResponsavelFinanceiro, that.parentescoResponsavelFinanceiro) &&
            Objects.equals(dataHoraCadastro, that.dataHoraCadastro) &&
            Objects.equals(ativo, that.ativo) &&
            Objects.equals(observacao, that.observacao) &&
            Objects.equals(planoEstrategicoId, that.planoEstrategicoId) &&
            Objects.equals(responsavelId, that.responsavelId) &&
            Objects.equals(responsavelFinanceiroId, that.responsavelFinanceiroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tipoCliente,
        matricula,
        nomeCompleto,
        dataNascimento,
        sexo,
        estadoCivil,
        escolaridade,
        convenio,
        numCarteirinhaConvenio,
        dataValidadeConvenio,
        procedencia,
        profissao,
        cpf,
        rg,
        telefone1,
        telefone2,
        email,
        logradouroNome,
        logradouroNumero,
        logradouroComplemento,
        bairro,
        cep,
        cidade,
        estado,
        parentescoResponsavel,
        parentescoResponsavelFinanceiro,
        dataHoraCadastro,
        ativo,
        observacao,
        planoEstrategicoId,
        responsavelId,
        responsavelFinanceiroId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClienteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tipoCliente != null ? "tipoCliente=" + tipoCliente + ", " : "") +
                (matricula != null ? "matricula=" + matricula + ", " : "") +
                (nomeCompleto != null ? "nomeCompleto=" + nomeCompleto + ", " : "") +
                (dataNascimento != null ? "dataNascimento=" + dataNascimento + ", " : "") +
                (sexo != null ? "sexo=" + sexo + ", " : "") +
                (estadoCivil != null ? "estadoCivil=" + estadoCivil + ", " : "") +
                (escolaridade != null ? "escolaridade=" + escolaridade + ", " : "") +
                (convenio != null ? "convenio=" + convenio + ", " : "") +
                (numCarteirinhaConvenio != null ? "numCarteirinhaConvenio=" + numCarteirinhaConvenio + ", " : "") +
                (dataValidadeConvenio != null ? "dataValidadeConvenio=" + dataValidadeConvenio + ", " : "") +
                (procedencia != null ? "procedencia=" + procedencia + ", " : "") +
                (profissao != null ? "profissao=" + profissao + ", " : "") +
                (cpf != null ? "cpf=" + cpf + ", " : "") +
                (rg != null ? "rg=" + rg + ", " : "") +
                (telefone1 != null ? "telefone1=" + telefone1 + ", " : "") +
                (telefone2 != null ? "telefone2=" + telefone2 + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (logradouroNome != null ? "logradouroNome=" + logradouroNome + ", " : "") +
                (logradouroNumero != null ? "logradouroNumero=" + logradouroNumero + ", " : "") +
                (logradouroComplemento != null ? "logradouroComplemento=" + logradouroComplemento + ", " : "") +
                (bairro != null ? "bairro=" + bairro + ", " : "") +
                (cep != null ? "cep=" + cep + ", " : "") +
                (cidade != null ? "cidade=" + cidade + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (parentescoResponsavel != null ? "parentescoResponsavel=" + parentescoResponsavel + ", " : "") +
                (parentescoResponsavelFinanceiro != null ? "parentescoResponsavelFinanceiro=" + parentescoResponsavelFinanceiro + ", " : "") +
                (dataHoraCadastro != null ? "dataHoraCadastro=" + dataHoraCadastro + ", " : "") +
                (ativo != null ? "ativo=" + ativo + ", " : "") +
                (observacao != null ? "observacao=" + observacao + ", " : "") +
                (planoEstrategicoId != null ? "planoEstrategicoId=" + planoEstrategicoId + ", " : "") +
                (responsavelId != null ? "responsavelId=" + responsavelId + ", " : "") +
                (responsavelFinanceiroId != null ? "responsavelFinanceiroId=" + responsavelFinanceiroId + ", " : "") +
            "}";
    }

}
