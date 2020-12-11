package br.com.clinicaadaptro.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import br.com.clinicaadaptro.app.domain.enumeration.Sexo;

import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;

import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;

import br.com.clinicaadaptro.app.domain.enumeration.CategoriaFuncionario;

import br.com.clinicaadaptro.app.domain.enumeration.Estado;

/**
 * A Funcionario.
 */
@Entity
@Table(name = "funcionario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

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

    @Size(max = 20)
    @Column(name = "numero_conselho_profissional", length = 20)
    private String numeroConselhoProfissional;

    @Size(max = 11)
    @Pattern(regexp = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Size(max = 15)
    @Column(name = "rg", length = 15)
    private String rg;

    @Size(max = 15)
    @Column(name = "cnh", length = 15)
    private String cnh;

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

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "data_desligamento")
    private LocalDate dataDesligamento;

    @Column(name = "salario")
    private Long salario;

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
    @Column(name = "funcao", nullable = false)
    private CategoriaFuncionario funcao;

    @Size(max = 40)
    @Column(name = "desc_outra_funcao", length = 40)
    private String descOutraFuncao;

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

    @Size(max = 50)
    @Column(name = "proximidades_logradouro", length = 50)
    private String proximidadesLogradouro;

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

    @NotNull
    @Column(name = "data_hora_cadastro", nullable = false)
    private Instant dataHoraCadastro;

    @Size(max = 1000)
    @Column(name = "observacao", length = 1000)
    private String observacao;

    @Lob
    @Column(name = "curriculo")
    private byte[] curriculo;

    @Column(name = "curriculo_content_type")
    private String curriculoContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "funcionarios", allowSetters = true)
    private EspecialidadeSaude especialidadeSaude;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Funcionario nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Funcionario foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public Funcionario fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Funcionario dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroConselhoProfissional() {
        return numeroConselhoProfissional;
    }

    public Funcionario numeroConselhoProfissional(String numeroConselhoProfissional) {
        this.numeroConselhoProfissional = numeroConselhoProfissional;
        return this;
    }

    public void setNumeroConselhoProfissional(String numeroConselhoProfissional) {
        this.numeroConselhoProfissional = numeroConselhoProfissional;
    }

    public String getCpf() {
        return cpf;
    }

    public Funcionario cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public Funcionario rg(String rg) {
        this.rg = rg;
        return this;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public Funcionario cnh(String cnh) {
        this.cnh = cnh;
        return this;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public Funcionario telefone1(String telefone1) {
        this.telefone1 = telefone1;
        return this;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public Funcionario telefone2(String telefone2) {
        this.telefone2 = telefone2;
        return this;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public Funcionario email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public Funcionario dataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
        return this;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDesligamento() {
        return dataDesligamento;
    }

    public Funcionario dataDesligamento(LocalDate dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
        return this;
    }

    public void setDataDesligamento(LocalDate dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Long getSalario() {
        return salario;
    }

    public Funcionario salario(Long salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Funcionario sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public Funcionario estadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public Funcionario escolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
        return this;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public CategoriaFuncionario getFuncao() {
        return funcao;
    }

    public Funcionario funcao(CategoriaFuncionario funcao) {
        this.funcao = funcao;
        return this;
    }

    public void setFuncao(CategoriaFuncionario funcao) {
        this.funcao = funcao;
    }

    public String getDescOutraFuncao() {
        return descOutraFuncao;
    }

    public Funcionario descOutraFuncao(String descOutraFuncao) {
        this.descOutraFuncao = descOutraFuncao;
        return this;
    }

    public void setDescOutraFuncao(String descOutraFuncao) {
        this.descOutraFuncao = descOutraFuncao;
    }

    public String getLogradouroNome() {
        return logradouroNome;
    }

    public Funcionario logradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
        return this;
    }

    public void setLogradouroNome(String logradouroNome) {
        this.logradouroNome = logradouroNome;
    }

    public String getLogradouroNumero() {
        return logradouroNumero;
    }

    public Funcionario logradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
        return this;
    }

    public void setLogradouroNumero(String logradouroNumero) {
        this.logradouroNumero = logradouroNumero;
    }

    public String getLogradouroComplemento() {
        return logradouroComplemento;
    }

    public Funcionario logradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
        return this;
    }

    public void setLogradouroComplemento(String logradouroComplemento) {
        this.logradouroComplemento = logradouroComplemento;
    }

    public String getBairro() {
        return bairro;
    }

    public Funcionario bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getProximidadesLogradouro() {
        return proximidadesLogradouro;
    }

    public Funcionario proximidadesLogradouro(String proximidadesLogradouro) {
        this.proximidadesLogradouro = proximidadesLogradouro;
        return this;
    }

    public void setProximidadesLogradouro(String proximidadesLogradouro) {
        this.proximidadesLogradouro = proximidadesLogradouro;
    }

    public String getCep() {
        return cep;
    }

    public Funcionario cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public Funcionario cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Funcionario estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Instant getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public Funcionario dataHoraCadastro(Instant dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
        return this;
    }

    public void setDataHoraCadastro(Instant dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public Funcionario observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte[] getCurriculo() {
        return curriculo;
    }

    public Funcionario curriculo(byte[] curriculo) {
        this.curriculo = curriculo;
        return this;
    }

    public void setCurriculo(byte[] curriculo) {
        this.curriculo = curriculo;
    }

    public String getCurriculoContentType() {
        return curriculoContentType;
    }

    public Funcionario curriculoContentType(String curriculoContentType) {
        this.curriculoContentType = curriculoContentType;
        return this;
    }

    public void setCurriculoContentType(String curriculoContentType) {
        this.curriculoContentType = curriculoContentType;
    }

    public User getUser() {
        return user;
    }

    public Funcionario user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EspecialidadeSaude getEspecialidadeSaude() {
        return especialidadeSaude;
    }

    public Funcionario especialidadeSaude(EspecialidadeSaude especialidadeSaude) {
        this.especialidadeSaude = especialidadeSaude;
        return this;
    }

    public void setEspecialidadeSaude(EspecialidadeSaude especialidadeSaude) {
        this.especialidadeSaude = especialidadeSaude;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Funcionario)) {
            return false;
        }
        return id != null && id.equals(((Funcionario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Funcionario{" +
            "id=" + getId() +
            ", nomeCompleto='" + getNomeCompleto() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", numeroConselhoProfissional='" + getNumeroConselhoProfissional() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", cnh='" + getCnh() + "'" +
            ", telefone1='" + getTelefone1() + "'" +
            ", telefone2='" + getTelefone2() + "'" +
            ", email='" + getEmail() + "'" +
            ", dataAdmissao='" + getDataAdmissao() + "'" +
            ", dataDesligamento='" + getDataDesligamento() + "'" +
            ", salario=" + getSalario() +
            ", sexo='" + getSexo() + "'" +
            ", estadoCivil='" + getEstadoCivil() + "'" +
            ", escolaridade='" + getEscolaridade() + "'" +
            ", funcao='" + getFuncao() + "'" +
            ", descOutraFuncao='" + getDescOutraFuncao() + "'" +
            ", logradouroNome='" + getLogradouroNome() + "'" +
            ", logradouroNumero='" + getLogradouroNumero() + "'" +
            ", logradouroComplemento='" + getLogradouroComplemento() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", proximidadesLogradouro='" + getProximidadesLogradouro() + "'" +
            ", cep='" + getCep() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", dataHoraCadastro='" + getDataHoraCadastro() + "'" +
            ", observacao='" + getObservacao() + "'" +
            ", curriculo='" + getCurriculo() + "'" +
            ", curriculoContentType='" + getCurriculoContentType() + "'" +
            "}";
    }
}
