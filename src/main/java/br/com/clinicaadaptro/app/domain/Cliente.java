package br.com.clinicaadaptro.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.clinicaadaptro.app.domain.enumeration.TipoCliente;

import br.com.clinicaadaptro.app.domain.enumeration.Sexo;

import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;

import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;

import br.com.clinicaadaptro.app.domain.enumeration.Convenio;

import br.com.clinicaadaptro.app.domain.enumeration.Procedencia;

import br.com.clinicaadaptro.app.domain.enumeration.Estado;

import br.com.clinicaadaptro.app.domain.enumeration.TipoParentesco;

/**
 * A Cliente.
 */
@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @Size(min = 6, max = 6)
    @Column(name = "matricula", length = 6, unique = true)
    private String matricula;

    @NotNull
    @Size(max = 100)
    @Column(name = "nome_completo", length = 100, nullable = false)
    private String nomeCompleto;

    
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(name = "escolaridade")
    private Escolaridade escolaridade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "convenio", nullable = false)
    private Convenio convenio;

    @Size(max = 20)
    @Column(name = "num_carteirinha_convenio", length = 20, unique = true)
    private String numCarteirinhaConvenio;

    @Column(name = "data_validade_convenio")
    private LocalDate dataValidadeConvenio;

    @Enumerated(EnumType.STRING)
    @Column(name = "procedencia")
    private Procedencia procedencia;

    @Size(min = 2, max = 70)
    @Column(name = "profissao", length = 70)
    private String profissao;

    @Size(max = 11)
    @Pattern(regexp = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Size(max = 15)
    @Column(name = "rg", length = 15, unique = true)
    private String rg;

    @Size(max = 15)
    @Pattern(regexp = "^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$")
    @Column(name = "telefone_1", length = 15)
    private String telefone1;

    @Size(max = 15)
    @Pattern(regexp = "^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{3,4}$")
    @Column(name = "telefone_2", length = 15)
    private String telefone2;

    @Size(max = 120)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", length = 120)
    private String email;

    @Size(min = 2, max = 70)
    @Column(name = "logradouro_nome", length = 70)
    private String logradouroNome;

    @Size(max = 5)
    @Column(name = "logradouro_numero", length = 5)
    private String logradouroNumero;

    @Size(max = 50)
    @Column(name = "logradouro_complemento", length = 50)
    private String logradouroComplemento;

    @Size(min = 2, max = 30)
    @Column(name = "bairro", length = 30)
    private String bairro;

    @Size(max = 9)
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    @Column(name = "cep", length = 9)
    private String cep;

    @Size(min = 2, max = 30)
    @Column(name = "cidade", length = 30)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "parentesco_responsavel")
    private TipoParentesco parentescoResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "parentesco_responsavel_financeiro")
    private TipoParentesco parentescoResponsavelFinanceiro;

    @NotNull
    @Column(name = "data_hora_cadastro", nullable = false)
    private Instant dataHoraCadastro;

    @Column(name = "ativo")
    private Boolean ativo;

    @Size(max = 1000)
    @Column(name = "observacao", length = 1000)
    private String observacao;

    @OneToMany(mappedBy = "cliente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PlanoEstrategico> planoEstrategicos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "clientes", allowSetters = true)
    private Cliente responsavel;

    @ManyToOne
    @JsonIgnoreProperties(value = "clientes", allowSetters = true)
    private Cliente responsavelFinanceiro;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public Cliente tipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
        return this;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public Cliente matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Cliente nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Cliente foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public Cliente fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Cliente dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Cliente sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public Cliente estadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public Cliente escolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
        return this;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public Cliente convenio(Convenio convenio) {
        this.convenio = convenio;
        return this;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getNumCarteirinhaConvenio() {
        return numCarteirinhaConvenio;
    }

    public Cliente numCarteirinhaConvenio(String numCarteirinhaConvenio) {
        this.numCarteirinhaConvenio = numCarteirinhaConvenio;
        return this;
    }

    public void setNumCarteirinhaConvenio(String numCarteirinhaConvenio) {
        this.numCarteirinhaConvenio = numCarteirinhaConvenio;
    }

    public LocalDate getDataValidadeConvenio() {
        return dataValidadeConvenio;
    }

    public Cliente dataValidadeConvenio(LocalDate dataValidadeConvenio) {
        this.dataValidadeConvenio = dataValidadeConvenio;
        return this;
    }

    public void setDataValidadeConvenio(LocalDate dataValidadeConvenio) {
        this.dataValidadeConvenio = dataValidadeConvenio;
    }

    public Procedencia getProcedencia() {
        return procedencia;
    }

    public Cliente procedencia(Procedencia procedencia) {
        this.procedencia = procedencia;
        return this;
    }

    public void setProcedencia(Procedencia procedencia) {
        this.procedencia = procedencia;
    }

    public String getProfissao() {
        return profissao;
    }

    public Cliente profissao(String profissao) {
        this.profissao = profissao;
        return this;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getCpf() {
        return cpf;
    }

    public Cliente cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public Cliente rg(String rg) {
        this.rg = rg;
        return this;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public Cliente telefone1(String telefone1) {
        this.telefone1 = telefone1;
        return this;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public Cliente telefone2(String telefone2) {
        this.telefone2 = telefone2;
        return this;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public Cliente email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouroNome() {
        return logradouroNome;
    }

    public Cliente logradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
        return this;
    }

    public void setLogradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
    }

    public String getLogradouroNumero() {
        return logradouroNumero;
    }

    public Cliente logradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
        return this;
    }

    public void setLogradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
    }

    public String getLogradouroComplemento() {
        return logradouroComplemento;
    }

    public Cliente logradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
        return this;
    }

    public void setLogradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
    }

    public String getBairro() {
        return bairro;
    }

    public Cliente bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public Cliente cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public Cliente cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Cliente estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoParentesco getParentescoResponsavel() {
        return parentescoResponsavel;
    }

    public Cliente parentescoResponsavel(TipoParentesco parentescoResponsavel) {
        this.parentescoResponsavel = parentescoResponsavel;
        return this;
    }

    public void setParentescoResponsavel(TipoParentesco parentescoResponsavel) {
        this.parentescoResponsavel = parentescoResponsavel;
    }

    public TipoParentesco getParentescoResponsavelFinanceiro() {
        return parentescoResponsavelFinanceiro;
    }

    public Cliente parentescoResponsavelFinanceiro(TipoParentesco parentescoResponsavelFinanceiro) {
        this.parentescoResponsavelFinanceiro = parentescoResponsavelFinanceiro;
        return this;
    }

    public void setParentescoResponsavelFinanceiro(TipoParentesco parentescoResponsavelFinanceiro) {
        this.parentescoResponsavelFinanceiro = parentescoResponsavelFinanceiro;
    }

    public Instant getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public Cliente dataHoraCadastro(Instant dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
        return this;
    }

    public void setDataHoraCadastro(Instant dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Cliente ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacao() {
        return observacao;
    }

    public Cliente observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Set<PlanoEstrategico> getPlanoEstrategicos() {
        return planoEstrategicos;
    }

    public Cliente planoEstrategicos(Set<PlanoEstrategico> planoEstrategicos) {
        this.planoEstrategicos = planoEstrategicos;
        return this;
    }

    public Cliente addPlanoEstrategico(PlanoEstrategico planoEstrategico) {
        this.planoEstrategicos.add(planoEstrategico);
        planoEstrategico.setCliente(this);
        return this;
    }

    public Cliente removePlanoEstrategico(PlanoEstrategico planoEstrategico) {
        this.planoEstrategicos.remove(planoEstrategico);
        planoEstrategico.setCliente(null);
        return this;
    }

    public void setPlanoEstrategicos(Set<PlanoEstrategico> planoEstrategicos) {
        this.planoEstrategicos = planoEstrategicos;
    }

    public Cliente getResponsavel() {
        return responsavel;
    }

    public Cliente responsavel(Cliente cliente) {
        this.responsavel = cliente;
        return this;
    }

    public void setResponsavel(Cliente cliente) {
        this.responsavel = cliente;
    }

    public Cliente getResponsavelFinanceiro() {
        return responsavelFinanceiro;
    }

    public Cliente responsavelFinanceiro(Cliente cliente) {
        this.responsavelFinanceiro = cliente;
        return this;
    }

    public void setResponsavelFinanceiro(Cliente cliente) {
        this.responsavelFinanceiro = cliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        return id != null && id.equals(((Cliente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", tipoCliente='" + getTipoCliente() + "'" +
            ", matricula='" + getMatricula() + "'" +
            ", nomeCompleto='" + getNomeCompleto() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", estadoCivil='" + getEstadoCivil() + "'" +
            ", escolaridade='" + getEscolaridade() + "'" +
            ", convenio='" + getConvenio() + "'" +
            ", numCarteirinhaConvenio='" + getNumCarteirinhaConvenio() + "'" +
            ", dataValidadeConvenio='" + getDataValidadeConvenio() + "'" +
            ", procedencia='" + getProcedencia() + "'" +
            ", profissao='" + getProfissao() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", telefone1='" + getTelefone1() + "'" +
            ", telefone2='" + getTelefone2() + "'" +
            ", email='" + getEmail() + "'" +
            ", logradouroNome='" + getLogradouroNome() + "'" +
            ", logradouroNumero='" + getLogradouroNumero() + "'" +
            ", logradouroComplemento='" + getLogradouroComplemento() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cep='" + getCep() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", parentescoResponsavel='" + getParentescoResponsavel() + "'" +
            ", parentescoResponsavelFinanceiro='" + getParentescoResponsavelFinanceiro() + "'" +
            ", dataHoraCadastro='" + getDataHoraCadastro() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", observacao='" + getObservacao() + "'" +
            "}";
    }
}
