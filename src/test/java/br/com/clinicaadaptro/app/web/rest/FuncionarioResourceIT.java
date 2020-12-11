package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.ClinicaAdaptrojhApp;
import br.com.clinicaadaptro.app.domain.Funcionario;
import br.com.clinicaadaptro.app.domain.User;
import br.com.clinicaadaptro.app.domain.EspecialidadeSaude;
import br.com.clinicaadaptro.app.repository.FuncionarioRepository;
import br.com.clinicaadaptro.app.service.FuncionarioService;
import br.com.clinicaadaptro.app.service.dto.FuncionarioCriteria;
import br.com.clinicaadaptro.app.service.FuncionarioQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.clinicaadaptro.app.domain.enumeration.Sexo;
import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;
import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;
import br.com.clinicaadaptro.app.domain.enumeration.CategoriaFuncionario;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
/**
 * Integration tests for the {@link FuncionarioResource} REST controller.
 */
@SpringBootTest(classes = ClinicaAdaptrojhApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FuncionarioResourceIT {

    private static final String DEFAULT_NOME_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMPLETO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_NASCIMENTO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_NUMERO_CONSELHO_PROFISSIONAL = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONSELHO_PROFISSIONAL = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "40044036507";
    private static final String UPDATED_CPF = "56846961512";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final String DEFAULT_CNH = "AAAAAAAAAA";
    private static final String UPDATED_CNH = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_1 = " 541895";
    private static final String UPDATED_TELEFONE_1 = "925 2824014";

    private static final String DEFAULT_TELEFONE_2 = "948 1181-4189";
    private static final String UPDATED_TELEFONE_2 = "321382-603";

    private static final String DEFAULT_EMAIL = "Vt@.Ku\"\"%.[o}";
    private static final String UPDATED_EMAIL = "l$qC+K@C$OO.`";

    private static final LocalDate DEFAULT_DATA_ADMISSAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_ADMISSAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_ADMISSAO = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATA_DESLIGAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_DESLIGAMENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_DESLIGAMENTO = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_SALARIO = 1L;
    private static final Long UPDATED_SALARIO = 2L;
    private static final Long SMALLER_SALARIO = 1L - 1L;

    private static final Sexo DEFAULT_SEXO = Sexo.M;
    private static final Sexo UPDATED_SEXO = Sexo.F;

    private static final EstadoCivil DEFAULT_ESTADO_CIVIL = EstadoCivil.SOLTEIRO;
    private static final EstadoCivil UPDATED_ESTADO_CIVIL = EstadoCivil.CASADO;

    private static final Escolaridade DEFAULT_ESCOLARIDADE = Escolaridade.EFC;
    private static final Escolaridade UPDATED_ESCOLARIDADE = Escolaridade.EFI;

    private static final CategoriaFuncionario DEFAULT_FUNCAO = CategoriaFuncionario.PROFISSIONAL_SAUDE;
    private static final CategoriaFuncionario UPDATED_FUNCAO = CategoriaFuncionario.RECEPCAO;

    private static final String DEFAULT_DESC_OUTRA_FUNCAO = "AAAAAAAAAA";
    private static final String UPDATED_DESC_OUTRA_FUNCAO = "BBBBBBBBBB";

    private static final String DEFAULT_LOGRADOURO_NOME = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGRADOURO_NUMERO = "AAAAA";
    private static final String UPDATED_LOGRADOURO_NUMERO = "BBBBB";

    private static final String DEFAULT_LOGRADOURO_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_PROXIMIDADES_LOGRADOURO = "AAAAAAAAAA";
    private static final String UPDATED_PROXIMIDADES_LOGRADOURO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "11674-409";
    private static final String UPDATED_CEP = "70404-337";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.AC;
    private static final Estado UPDATED_ESTADO = Estado.AL;

    private static final Instant DEFAULT_DATA_HORA_CADASTRO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_HORA_CADASTRO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CURRICULO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CURRICULO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CURRICULO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CURRICULO_CONTENT_TYPE = "image/png";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioQueryService funcionarioQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFuncionarioMockMvc;

    private Funcionario funcionario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funcionario createEntity(EntityManager em) {
        Funcionario funcionario = new Funcionario()
            .nomeCompleto(DEFAULT_NOME_COMPLETO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .dataNascimento(DEFAULT_DATA_NASCIMENTO)
            .numeroConselhoProfissional(DEFAULT_NUMERO_CONSELHO_PROFISSIONAL)
            .cpf(DEFAULT_CPF)
            .rg(DEFAULT_RG)
            .cnh(DEFAULT_CNH)
            .telefone1(DEFAULT_TELEFONE_1)
            .telefone2(DEFAULT_TELEFONE_2)
            .email(DEFAULT_EMAIL)
            .dataAdmissao(DEFAULT_DATA_ADMISSAO)
            .dataDesligamento(DEFAULT_DATA_DESLIGAMENTO)
            .salario(DEFAULT_SALARIO)
            .sexo(DEFAULT_SEXO)
            .estadoCivil(DEFAULT_ESTADO_CIVIL)
            .escolaridade(DEFAULT_ESCOLARIDADE)
            .funcao(DEFAULT_FUNCAO)
            .descOutraFuncao(DEFAULT_DESC_OUTRA_FUNCAO)
            .logradouroNome(DEFAULT_LOGRADOURO_NOME)
            .logradouroNumero(DEFAULT_LOGRADOURO_NUMERO)
            .logradouroComplemento(DEFAULT_LOGRADOURO_COMPLEMENTO)
            .bairro(DEFAULT_BAIRRO)
            .proximidadesLogradouro(DEFAULT_PROXIMIDADES_LOGRADOURO)
            .cep(DEFAULT_CEP)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .dataHoraCadastro(DEFAULT_DATA_HORA_CADASTRO)
            .observacao(DEFAULT_OBSERVACAO)
            .curriculo(DEFAULT_CURRICULO)
            .curriculoContentType(DEFAULT_CURRICULO_CONTENT_TYPE);
        return funcionario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funcionario createUpdatedEntity(EntityManager em) {
        Funcionario funcionario = new Funcionario()
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .dataNascimento(UPDATED_DATA_NASCIMENTO)
            .numeroConselhoProfissional(UPDATED_NUMERO_CONSELHO_PROFISSIONAL)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .cnh(UPDATED_CNH)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .email(UPDATED_EMAIL)
            .dataAdmissao(UPDATED_DATA_ADMISSAO)
            .dataDesligamento(UPDATED_DATA_DESLIGAMENTO)
            .salario(UPDATED_SALARIO)
            .sexo(UPDATED_SEXO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .escolaridade(UPDATED_ESCOLARIDADE)
            .funcao(UPDATED_FUNCAO)
            .descOutraFuncao(UPDATED_DESC_OUTRA_FUNCAO)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .proximidadesLogradouro(UPDATED_PROXIMIDADES_LOGRADOURO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO)
            .observacao(UPDATED_OBSERVACAO)
            .curriculo(UPDATED_CURRICULO)
            .curriculoContentType(UPDATED_CURRICULO_CONTENT_TYPE);
        return funcionario;
    }

    @BeforeEach
    public void initTest() {
        funcionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuncionario() throws Exception {
        int databaseSizeBeforeCreate = funcionarioRepository.findAll().size();
        // Create the Funcionario
        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isCreated());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeCreate + 1);
        Funcionario testFuncionario = funcionarioList.get(funcionarioList.size() - 1);
        assertThat(testFuncionario.getNomeCompleto()).isEqualTo(DEFAULT_NOME_COMPLETO);
        assertThat(testFuncionario.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testFuncionario.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testFuncionario.getDataNascimento()).isEqualTo(DEFAULT_DATA_NASCIMENTO);
        assertThat(testFuncionario.getNumeroConselhoProfissional()).isEqualTo(DEFAULT_NUMERO_CONSELHO_PROFISSIONAL);
        assertThat(testFuncionario.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testFuncionario.getRg()).isEqualTo(DEFAULT_RG);
        assertThat(testFuncionario.getCnh()).isEqualTo(DEFAULT_CNH);
        assertThat(testFuncionario.getTelefone1()).isEqualTo(DEFAULT_TELEFONE_1);
        assertThat(testFuncionario.getTelefone2()).isEqualTo(DEFAULT_TELEFONE_2);
        assertThat(testFuncionario.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFuncionario.getDataAdmissao()).isEqualTo(DEFAULT_DATA_ADMISSAO);
        assertThat(testFuncionario.getDataDesligamento()).isEqualTo(DEFAULT_DATA_DESLIGAMENTO);
        assertThat(testFuncionario.getSalario()).isEqualTo(DEFAULT_SALARIO);
        assertThat(testFuncionario.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testFuncionario.getEstadoCivil()).isEqualTo(DEFAULT_ESTADO_CIVIL);
        assertThat(testFuncionario.getEscolaridade()).isEqualTo(DEFAULT_ESCOLARIDADE);
        assertThat(testFuncionario.getFuncao()).isEqualTo(DEFAULT_FUNCAO);
        assertThat(testFuncionario.getDescOutraFuncao()).isEqualTo(DEFAULT_DESC_OUTRA_FUNCAO);
        assertThat(testFuncionario.getLogradouroNome()).isEqualTo(DEFAULT_LOGRADOURO_NOME);
        assertThat(testFuncionario.getLogradouroNumero()).isEqualTo(DEFAULT_LOGRADOURO_NUMERO);
        assertThat(testFuncionario.getLogradouroComplemento()).isEqualTo(DEFAULT_LOGRADOURO_COMPLEMENTO);
        assertThat(testFuncionario.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testFuncionario.getProximidadesLogradouro()).isEqualTo(DEFAULT_PROXIMIDADES_LOGRADOURO);
        assertThat(testFuncionario.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testFuncionario.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testFuncionario.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testFuncionario.getDataHoraCadastro()).isEqualTo(DEFAULT_DATA_HORA_CADASTRO);
        assertThat(testFuncionario.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testFuncionario.getCurriculo()).isEqualTo(DEFAULT_CURRICULO);
        assertThat(testFuncionario.getCurriculoContentType()).isEqualTo(DEFAULT_CURRICULO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFuncionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = funcionarioRepository.findAll().size();

        // Create the Funcionario with an existing ID
        funcionario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeCompletoIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setNomeCompleto(null);

        // Create the Funcionario, which fails.


        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexoIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setSexo(null);

        // Create the Funcionario, which fails.


        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFuncaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setFuncao(null);

        // Create the Funcionario, which fails.


        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataHoraCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setDataHoraCadastro(null);

        // Create the Funcionario, which fails.


        restFuncionarioMockMvc.perform(post("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuncionarios() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList
        restFuncionarioMockMvc.perform(get("/api/funcionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numeroConselhoProfissional").value(hasItem(DEFAULT_NUMERO_CONSELHO_PROFISSIONAL)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].cnh").value(hasItem(DEFAULT_CNH)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].dataAdmissao").value(hasItem(DEFAULT_DATA_ADMISSAO.toString())))
            .andExpect(jsonPath("$.[*].dataDesligamento").value(hasItem(DEFAULT_DATA_DESLIGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].salario").value(hasItem(DEFAULT_SALARIO.intValue())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].escolaridade").value(hasItem(DEFAULT_ESCOLARIDADE.toString())))
            .andExpect(jsonPath("$.[*].funcao").value(hasItem(DEFAULT_FUNCAO.toString())))
            .andExpect(jsonPath("$.[*].descOutraFuncao").value(hasItem(DEFAULT_DESC_OUTRA_FUNCAO)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].proximidadesLogradouro").value(hasItem(DEFAULT_PROXIMIDADES_LOGRADOURO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].dataHoraCadastro").value(hasItem(DEFAULT_DATA_HORA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)))
            .andExpect(jsonPath("$.[*].curriculoContentType").value(hasItem(DEFAULT_CURRICULO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].curriculo").value(hasItem(Base64Utils.encodeToString(DEFAULT_CURRICULO))));
    }
    
    @Test
    @Transactional
    public void getFuncionario() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get the funcionario
        restFuncionarioMockMvc.perform(get("/api/funcionarios/{id}", funcionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(funcionario.getId().intValue()))
            .andExpect(jsonPath("$.nomeCompleto").value(DEFAULT_NOME_COMPLETO))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.dataNascimento").value(DEFAULT_DATA_NASCIMENTO.toString()))
            .andExpect(jsonPath("$.numeroConselhoProfissional").value(DEFAULT_NUMERO_CONSELHO_PROFISSIONAL))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.cnh").value(DEFAULT_CNH))
            .andExpect(jsonPath("$.telefone1").value(DEFAULT_TELEFONE_1))
            .andExpect(jsonPath("$.telefone2").value(DEFAULT_TELEFONE_2))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.dataAdmissao").value(DEFAULT_DATA_ADMISSAO.toString()))
            .andExpect(jsonPath("$.dataDesligamento").value(DEFAULT_DATA_DESLIGAMENTO.toString()))
            .andExpect(jsonPath("$.salario").value(DEFAULT_SALARIO.intValue()))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO.toString()))
            .andExpect(jsonPath("$.estadoCivil").value(DEFAULT_ESTADO_CIVIL.toString()))
            .andExpect(jsonPath("$.escolaridade").value(DEFAULT_ESCOLARIDADE.toString()))
            .andExpect(jsonPath("$.funcao").value(DEFAULT_FUNCAO.toString()))
            .andExpect(jsonPath("$.descOutraFuncao").value(DEFAULT_DESC_OUTRA_FUNCAO))
            .andExpect(jsonPath("$.logradouroNome").value(DEFAULT_LOGRADOURO_NOME))
            .andExpect(jsonPath("$.logradouroNumero").value(DEFAULT_LOGRADOURO_NUMERO))
            .andExpect(jsonPath("$.logradouroComplemento").value(DEFAULT_LOGRADOURO_COMPLEMENTO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.proximidadesLogradouro").value(DEFAULT_PROXIMIDADES_LOGRADOURO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.dataHoraCadastro").value(DEFAULT_DATA_HORA_CADASTRO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO))
            .andExpect(jsonPath("$.curriculoContentType").value(DEFAULT_CURRICULO_CONTENT_TYPE))
            .andExpect(jsonPath("$.curriculo").value(Base64Utils.encodeToString(DEFAULT_CURRICULO)));
    }


    @Test
    @Transactional
    public void getFuncionariosByIdFiltering() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        Long id = funcionario.getId();

        defaultFuncionarioShouldBeFound("id.equals=" + id);
        defaultFuncionarioShouldNotBeFound("id.notEquals=" + id);

        defaultFuncionarioShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFuncionarioShouldNotBeFound("id.greaterThan=" + id);

        defaultFuncionarioShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFuncionarioShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto equals to DEFAULT_NOME_COMPLETO
        defaultFuncionarioShouldBeFound("nomeCompleto.equals=" + DEFAULT_NOME_COMPLETO);

        // Get all the funcionarioList where nomeCompleto equals to UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldNotBeFound("nomeCompleto.equals=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto not equals to DEFAULT_NOME_COMPLETO
        defaultFuncionarioShouldNotBeFound("nomeCompleto.notEquals=" + DEFAULT_NOME_COMPLETO);

        // Get all the funcionarioList where nomeCompleto not equals to UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldBeFound("nomeCompleto.notEquals=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto in DEFAULT_NOME_COMPLETO or UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldBeFound("nomeCompleto.in=" + DEFAULT_NOME_COMPLETO + "," + UPDATED_NOME_COMPLETO);

        // Get all the funcionarioList where nomeCompleto equals to UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldNotBeFound("nomeCompleto.in=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto is not null
        defaultFuncionarioShouldBeFound("nomeCompleto.specified=true");

        // Get all the funcionarioList where nomeCompleto is null
        defaultFuncionarioShouldNotBeFound("nomeCompleto.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto contains DEFAULT_NOME_COMPLETO
        defaultFuncionarioShouldBeFound("nomeCompleto.contains=" + DEFAULT_NOME_COMPLETO);

        // Get all the funcionarioList where nomeCompleto contains UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldNotBeFound("nomeCompleto.contains=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNomeCompletoNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where nomeCompleto does not contain DEFAULT_NOME_COMPLETO
        defaultFuncionarioShouldNotBeFound("nomeCompleto.doesNotContain=" + DEFAULT_NOME_COMPLETO);

        // Get all the funcionarioList where nomeCompleto does not contain UPDATED_NOME_COMPLETO
        defaultFuncionarioShouldBeFound("nomeCompleto.doesNotContain=" + UPDATED_NOME_COMPLETO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento equals to DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.equals=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento equals to UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.equals=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento not equals to DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.notEquals=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento not equals to UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.notEquals=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento in DEFAULT_DATA_NASCIMENTO or UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.in=" + DEFAULT_DATA_NASCIMENTO + "," + UPDATED_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento equals to UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.in=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento is not null
        defaultFuncionarioShouldBeFound("dataNascimento.specified=true");

        // Get all the funcionarioList where dataNascimento is null
        defaultFuncionarioShouldNotBeFound("dataNascimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento is greater than or equal to DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.greaterThanOrEqual=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento is greater than or equal to UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.greaterThanOrEqual=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento is less than or equal to DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.lessThanOrEqual=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento is less than or equal to SMALLER_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.lessThanOrEqual=" + SMALLER_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsLessThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento is less than DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.lessThan=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento is less than UPDATED_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.lessThan=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataNascimentoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataNascimento is greater than DEFAULT_DATA_NASCIMENTO
        defaultFuncionarioShouldNotBeFound("dataNascimento.greaterThan=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the funcionarioList where dataNascimento is greater than SMALLER_DATA_NASCIMENTO
        defaultFuncionarioShouldBeFound("dataNascimento.greaterThan=" + SMALLER_DATA_NASCIMENTO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional equals to DEFAULT_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.equals=" + DEFAULT_NUMERO_CONSELHO_PROFISSIONAL);

        // Get all the funcionarioList where numeroConselhoProfissional equals to UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.equals=" + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional not equals to DEFAULT_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.notEquals=" + DEFAULT_NUMERO_CONSELHO_PROFISSIONAL);

        // Get all the funcionarioList where numeroConselhoProfissional not equals to UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.notEquals=" + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional in DEFAULT_NUMERO_CONSELHO_PROFISSIONAL or UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.in=" + DEFAULT_NUMERO_CONSELHO_PROFISSIONAL + "," + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);

        // Get all the funcionarioList where numeroConselhoProfissional equals to UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.in=" + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional is not null
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.specified=true");

        // Get all the funcionarioList where numeroConselhoProfissional is null
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional contains DEFAULT_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.contains=" + DEFAULT_NUMERO_CONSELHO_PROFISSIONAL);

        // Get all the funcionarioList where numeroConselhoProfissional contains UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.contains=" + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByNumeroConselhoProfissionalNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where numeroConselhoProfissional does not contain DEFAULT_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldNotBeFound("numeroConselhoProfissional.doesNotContain=" + DEFAULT_NUMERO_CONSELHO_PROFISSIONAL);

        // Get all the funcionarioList where numeroConselhoProfissional does not contain UPDATED_NUMERO_CONSELHO_PROFISSIONAL
        defaultFuncionarioShouldBeFound("numeroConselhoProfissional.doesNotContain=" + UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByCpfIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf equals to DEFAULT_CPF
        defaultFuncionarioShouldBeFound("cpf.equals=" + DEFAULT_CPF);

        // Get all the funcionarioList where cpf equals to UPDATED_CPF
        defaultFuncionarioShouldNotBeFound("cpf.equals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCpfIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf not equals to DEFAULT_CPF
        defaultFuncionarioShouldNotBeFound("cpf.notEquals=" + DEFAULT_CPF);

        // Get all the funcionarioList where cpf not equals to UPDATED_CPF
        defaultFuncionarioShouldBeFound("cpf.notEquals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCpfIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf in DEFAULT_CPF or UPDATED_CPF
        defaultFuncionarioShouldBeFound("cpf.in=" + DEFAULT_CPF + "," + UPDATED_CPF);

        // Get all the funcionarioList where cpf equals to UPDATED_CPF
        defaultFuncionarioShouldNotBeFound("cpf.in=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCpfIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf is not null
        defaultFuncionarioShouldBeFound("cpf.specified=true");

        // Get all the funcionarioList where cpf is null
        defaultFuncionarioShouldNotBeFound("cpf.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByCpfContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf contains DEFAULT_CPF
        defaultFuncionarioShouldBeFound("cpf.contains=" + DEFAULT_CPF);

        // Get all the funcionarioList where cpf contains UPDATED_CPF
        defaultFuncionarioShouldNotBeFound("cpf.contains=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCpfNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cpf does not contain DEFAULT_CPF
        defaultFuncionarioShouldNotBeFound("cpf.doesNotContain=" + DEFAULT_CPF);

        // Get all the funcionarioList where cpf does not contain UPDATED_CPF
        defaultFuncionarioShouldBeFound("cpf.doesNotContain=" + UPDATED_CPF);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByRgIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg equals to DEFAULT_RG
        defaultFuncionarioShouldBeFound("rg.equals=" + DEFAULT_RG);

        // Get all the funcionarioList where rg equals to UPDATED_RG
        defaultFuncionarioShouldNotBeFound("rg.equals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByRgIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg not equals to DEFAULT_RG
        defaultFuncionarioShouldNotBeFound("rg.notEquals=" + DEFAULT_RG);

        // Get all the funcionarioList where rg not equals to UPDATED_RG
        defaultFuncionarioShouldBeFound("rg.notEquals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByRgIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg in DEFAULT_RG or UPDATED_RG
        defaultFuncionarioShouldBeFound("rg.in=" + DEFAULT_RG + "," + UPDATED_RG);

        // Get all the funcionarioList where rg equals to UPDATED_RG
        defaultFuncionarioShouldNotBeFound("rg.in=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByRgIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg is not null
        defaultFuncionarioShouldBeFound("rg.specified=true");

        // Get all the funcionarioList where rg is null
        defaultFuncionarioShouldNotBeFound("rg.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByRgContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg contains DEFAULT_RG
        defaultFuncionarioShouldBeFound("rg.contains=" + DEFAULT_RG);

        // Get all the funcionarioList where rg contains UPDATED_RG
        defaultFuncionarioShouldNotBeFound("rg.contains=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByRgNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where rg does not contain DEFAULT_RG
        defaultFuncionarioShouldNotBeFound("rg.doesNotContain=" + DEFAULT_RG);

        // Get all the funcionarioList where rg does not contain UPDATED_RG
        defaultFuncionarioShouldBeFound("rg.doesNotContain=" + UPDATED_RG);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByCnhIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh equals to DEFAULT_CNH
        defaultFuncionarioShouldBeFound("cnh.equals=" + DEFAULT_CNH);

        // Get all the funcionarioList where cnh equals to UPDATED_CNH
        defaultFuncionarioShouldNotBeFound("cnh.equals=" + UPDATED_CNH);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCnhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh not equals to DEFAULT_CNH
        defaultFuncionarioShouldNotBeFound("cnh.notEquals=" + DEFAULT_CNH);

        // Get all the funcionarioList where cnh not equals to UPDATED_CNH
        defaultFuncionarioShouldBeFound("cnh.notEquals=" + UPDATED_CNH);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCnhIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh in DEFAULT_CNH or UPDATED_CNH
        defaultFuncionarioShouldBeFound("cnh.in=" + DEFAULT_CNH + "," + UPDATED_CNH);

        // Get all the funcionarioList where cnh equals to UPDATED_CNH
        defaultFuncionarioShouldNotBeFound("cnh.in=" + UPDATED_CNH);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCnhIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh is not null
        defaultFuncionarioShouldBeFound("cnh.specified=true");

        // Get all the funcionarioList where cnh is null
        defaultFuncionarioShouldNotBeFound("cnh.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByCnhContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh contains DEFAULT_CNH
        defaultFuncionarioShouldBeFound("cnh.contains=" + DEFAULT_CNH);

        // Get all the funcionarioList where cnh contains UPDATED_CNH
        defaultFuncionarioShouldNotBeFound("cnh.contains=" + UPDATED_CNH);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCnhNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cnh does not contain DEFAULT_CNH
        defaultFuncionarioShouldNotBeFound("cnh.doesNotContain=" + DEFAULT_CNH);

        // Get all the funcionarioList where cnh does not contain UPDATED_CNH
        defaultFuncionarioShouldBeFound("cnh.doesNotContain=" + UPDATED_CNH);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByTelefone1IsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 equals to DEFAULT_TELEFONE_1
        defaultFuncionarioShouldBeFound("telefone1.equals=" + DEFAULT_TELEFONE_1);

        // Get all the funcionarioList where telefone1 equals to UPDATED_TELEFONE_1
        defaultFuncionarioShouldNotBeFound("telefone1.equals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 not equals to DEFAULT_TELEFONE_1
        defaultFuncionarioShouldNotBeFound("telefone1.notEquals=" + DEFAULT_TELEFONE_1);

        // Get all the funcionarioList where telefone1 not equals to UPDATED_TELEFONE_1
        defaultFuncionarioShouldBeFound("telefone1.notEquals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone1IsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 in DEFAULT_TELEFONE_1 or UPDATED_TELEFONE_1
        defaultFuncionarioShouldBeFound("telefone1.in=" + DEFAULT_TELEFONE_1 + "," + UPDATED_TELEFONE_1);

        // Get all the funcionarioList where telefone1 equals to UPDATED_TELEFONE_1
        defaultFuncionarioShouldNotBeFound("telefone1.in=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone1IsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 is not null
        defaultFuncionarioShouldBeFound("telefone1.specified=true");

        // Get all the funcionarioList where telefone1 is null
        defaultFuncionarioShouldNotBeFound("telefone1.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByTelefone1ContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 contains DEFAULT_TELEFONE_1
        defaultFuncionarioShouldBeFound("telefone1.contains=" + DEFAULT_TELEFONE_1);

        // Get all the funcionarioList where telefone1 contains UPDATED_TELEFONE_1
        defaultFuncionarioShouldNotBeFound("telefone1.contains=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone1NotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone1 does not contain DEFAULT_TELEFONE_1
        defaultFuncionarioShouldNotBeFound("telefone1.doesNotContain=" + DEFAULT_TELEFONE_1);

        // Get all the funcionarioList where telefone1 does not contain UPDATED_TELEFONE_1
        defaultFuncionarioShouldBeFound("telefone1.doesNotContain=" + UPDATED_TELEFONE_1);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByTelefone2IsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 equals to DEFAULT_TELEFONE_2
        defaultFuncionarioShouldBeFound("telefone2.equals=" + DEFAULT_TELEFONE_2);

        // Get all the funcionarioList where telefone2 equals to UPDATED_TELEFONE_2
        defaultFuncionarioShouldNotBeFound("telefone2.equals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 not equals to DEFAULT_TELEFONE_2
        defaultFuncionarioShouldNotBeFound("telefone2.notEquals=" + DEFAULT_TELEFONE_2);

        // Get all the funcionarioList where telefone2 not equals to UPDATED_TELEFONE_2
        defaultFuncionarioShouldBeFound("telefone2.notEquals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone2IsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 in DEFAULT_TELEFONE_2 or UPDATED_TELEFONE_2
        defaultFuncionarioShouldBeFound("telefone2.in=" + DEFAULT_TELEFONE_2 + "," + UPDATED_TELEFONE_2);

        // Get all the funcionarioList where telefone2 equals to UPDATED_TELEFONE_2
        defaultFuncionarioShouldNotBeFound("telefone2.in=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone2IsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 is not null
        defaultFuncionarioShouldBeFound("telefone2.specified=true");

        // Get all the funcionarioList where telefone2 is null
        defaultFuncionarioShouldNotBeFound("telefone2.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByTelefone2ContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 contains DEFAULT_TELEFONE_2
        defaultFuncionarioShouldBeFound("telefone2.contains=" + DEFAULT_TELEFONE_2);

        // Get all the funcionarioList where telefone2 contains UPDATED_TELEFONE_2
        defaultFuncionarioShouldNotBeFound("telefone2.contains=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByTelefone2NotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where telefone2 does not contain DEFAULT_TELEFONE_2
        defaultFuncionarioShouldNotBeFound("telefone2.doesNotContain=" + DEFAULT_TELEFONE_2);

        // Get all the funcionarioList where telefone2 does not contain UPDATED_TELEFONE_2
        defaultFuncionarioShouldBeFound("telefone2.doesNotContain=" + UPDATED_TELEFONE_2);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email equals to DEFAULT_EMAIL
        defaultFuncionarioShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the funcionarioList where email equals to UPDATED_EMAIL
        defaultFuncionarioShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email not equals to DEFAULT_EMAIL
        defaultFuncionarioShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the funcionarioList where email not equals to UPDATED_EMAIL
        defaultFuncionarioShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultFuncionarioShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the funcionarioList where email equals to UPDATED_EMAIL
        defaultFuncionarioShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email is not null
        defaultFuncionarioShouldBeFound("email.specified=true");

        // Get all the funcionarioList where email is null
        defaultFuncionarioShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByEmailContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email contains DEFAULT_EMAIL
        defaultFuncionarioShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the funcionarioList where email contains UPDATED_EMAIL
        defaultFuncionarioShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where email does not contain DEFAULT_EMAIL
        defaultFuncionarioShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the funcionarioList where email does not contain UPDATED_EMAIL
        defaultFuncionarioShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao equals to DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.equals=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao equals to UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.equals=" + UPDATED_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao not equals to DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.notEquals=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao not equals to UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.notEquals=" + UPDATED_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao in DEFAULT_DATA_ADMISSAO or UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.in=" + DEFAULT_DATA_ADMISSAO + "," + UPDATED_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao equals to UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.in=" + UPDATED_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao is not null
        defaultFuncionarioShouldBeFound("dataAdmissao.specified=true");

        // Get all the funcionarioList where dataAdmissao is null
        defaultFuncionarioShouldNotBeFound("dataAdmissao.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao is greater than or equal to DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.greaterThanOrEqual=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao is greater than or equal to UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.greaterThanOrEqual=" + UPDATED_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao is less than or equal to DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.lessThanOrEqual=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao is less than or equal to SMALLER_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.lessThanOrEqual=" + SMALLER_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsLessThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao is less than DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.lessThan=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao is less than UPDATED_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.lessThan=" + UPDATED_DATA_ADMISSAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataAdmissaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataAdmissao is greater than DEFAULT_DATA_ADMISSAO
        defaultFuncionarioShouldNotBeFound("dataAdmissao.greaterThan=" + DEFAULT_DATA_ADMISSAO);

        // Get all the funcionarioList where dataAdmissao is greater than SMALLER_DATA_ADMISSAO
        defaultFuncionarioShouldBeFound("dataAdmissao.greaterThan=" + SMALLER_DATA_ADMISSAO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento equals to DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.equals=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento equals to UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.equals=" + UPDATED_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento not equals to DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.notEquals=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento not equals to UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.notEquals=" + UPDATED_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento in DEFAULT_DATA_DESLIGAMENTO or UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.in=" + DEFAULT_DATA_DESLIGAMENTO + "," + UPDATED_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento equals to UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.in=" + UPDATED_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento is not null
        defaultFuncionarioShouldBeFound("dataDesligamento.specified=true");

        // Get all the funcionarioList where dataDesligamento is null
        defaultFuncionarioShouldNotBeFound("dataDesligamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento is greater than or equal to DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.greaterThanOrEqual=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento is greater than or equal to UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.greaterThanOrEqual=" + UPDATED_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento is less than or equal to DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.lessThanOrEqual=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento is less than or equal to SMALLER_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.lessThanOrEqual=" + SMALLER_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento is less than DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.lessThan=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento is less than UPDATED_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.lessThan=" + UPDATED_DATA_DESLIGAMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataDesligamentoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataDesligamento is greater than DEFAULT_DATA_DESLIGAMENTO
        defaultFuncionarioShouldNotBeFound("dataDesligamento.greaterThan=" + DEFAULT_DATA_DESLIGAMENTO);

        // Get all the funcionarioList where dataDesligamento is greater than SMALLER_DATA_DESLIGAMENTO
        defaultFuncionarioShouldBeFound("dataDesligamento.greaterThan=" + SMALLER_DATA_DESLIGAMENTO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario equals to DEFAULT_SALARIO
        defaultFuncionarioShouldBeFound("salario.equals=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario equals to UPDATED_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.equals=" + UPDATED_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario not equals to DEFAULT_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.notEquals=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario not equals to UPDATED_SALARIO
        defaultFuncionarioShouldBeFound("salario.notEquals=" + UPDATED_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario in DEFAULT_SALARIO or UPDATED_SALARIO
        defaultFuncionarioShouldBeFound("salario.in=" + DEFAULT_SALARIO + "," + UPDATED_SALARIO);

        // Get all the funcionarioList where salario equals to UPDATED_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.in=" + UPDATED_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario is not null
        defaultFuncionarioShouldBeFound("salario.specified=true");

        // Get all the funcionarioList where salario is null
        defaultFuncionarioShouldNotBeFound("salario.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario is greater than or equal to DEFAULT_SALARIO
        defaultFuncionarioShouldBeFound("salario.greaterThanOrEqual=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario is greater than or equal to UPDATED_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.greaterThanOrEqual=" + UPDATED_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario is less than or equal to DEFAULT_SALARIO
        defaultFuncionarioShouldBeFound("salario.lessThanOrEqual=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario is less than or equal to SMALLER_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.lessThanOrEqual=" + SMALLER_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsLessThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario is less than DEFAULT_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.lessThan=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario is less than UPDATED_SALARIO
        defaultFuncionarioShouldBeFound("salario.lessThan=" + UPDATED_SALARIO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySalarioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where salario is greater than DEFAULT_SALARIO
        defaultFuncionarioShouldNotBeFound("salario.greaterThan=" + DEFAULT_SALARIO);

        // Get all the funcionarioList where salario is greater than SMALLER_SALARIO
        defaultFuncionarioShouldBeFound("salario.greaterThan=" + SMALLER_SALARIO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosBySexoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where sexo equals to DEFAULT_SEXO
        defaultFuncionarioShouldBeFound("sexo.equals=" + DEFAULT_SEXO);

        // Get all the funcionarioList where sexo equals to UPDATED_SEXO
        defaultFuncionarioShouldNotBeFound("sexo.equals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySexoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where sexo not equals to DEFAULT_SEXO
        defaultFuncionarioShouldNotBeFound("sexo.notEquals=" + DEFAULT_SEXO);

        // Get all the funcionarioList where sexo not equals to UPDATED_SEXO
        defaultFuncionarioShouldBeFound("sexo.notEquals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySexoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where sexo in DEFAULT_SEXO or UPDATED_SEXO
        defaultFuncionarioShouldBeFound("sexo.in=" + DEFAULT_SEXO + "," + UPDATED_SEXO);

        // Get all the funcionarioList where sexo equals to UPDATED_SEXO
        defaultFuncionarioShouldNotBeFound("sexo.in=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosBySexoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where sexo is not null
        defaultFuncionarioShouldBeFound("sexo.specified=true");

        // Get all the funcionarioList where sexo is null
        defaultFuncionarioShouldNotBeFound("sexo.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoCivilIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estadoCivil equals to DEFAULT_ESTADO_CIVIL
        defaultFuncionarioShouldBeFound("estadoCivil.equals=" + DEFAULT_ESTADO_CIVIL);

        // Get all the funcionarioList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultFuncionarioShouldNotBeFound("estadoCivil.equals=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoCivilIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estadoCivil not equals to DEFAULT_ESTADO_CIVIL
        defaultFuncionarioShouldNotBeFound("estadoCivil.notEquals=" + DEFAULT_ESTADO_CIVIL);

        // Get all the funcionarioList where estadoCivil not equals to UPDATED_ESTADO_CIVIL
        defaultFuncionarioShouldBeFound("estadoCivil.notEquals=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoCivilIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estadoCivil in DEFAULT_ESTADO_CIVIL or UPDATED_ESTADO_CIVIL
        defaultFuncionarioShouldBeFound("estadoCivil.in=" + DEFAULT_ESTADO_CIVIL + "," + UPDATED_ESTADO_CIVIL);

        // Get all the funcionarioList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultFuncionarioShouldNotBeFound("estadoCivil.in=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoCivilIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estadoCivil is not null
        defaultFuncionarioShouldBeFound("estadoCivil.specified=true");

        // Get all the funcionarioList where estadoCivil is null
        defaultFuncionarioShouldNotBeFound("estadoCivil.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEscolaridadeIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where escolaridade equals to DEFAULT_ESCOLARIDADE
        defaultFuncionarioShouldBeFound("escolaridade.equals=" + DEFAULT_ESCOLARIDADE);

        // Get all the funcionarioList where escolaridade equals to UPDATED_ESCOLARIDADE
        defaultFuncionarioShouldNotBeFound("escolaridade.equals=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEscolaridadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where escolaridade not equals to DEFAULT_ESCOLARIDADE
        defaultFuncionarioShouldNotBeFound("escolaridade.notEquals=" + DEFAULT_ESCOLARIDADE);

        // Get all the funcionarioList where escolaridade not equals to UPDATED_ESCOLARIDADE
        defaultFuncionarioShouldBeFound("escolaridade.notEquals=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEscolaridadeIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where escolaridade in DEFAULT_ESCOLARIDADE or UPDATED_ESCOLARIDADE
        defaultFuncionarioShouldBeFound("escolaridade.in=" + DEFAULT_ESCOLARIDADE + "," + UPDATED_ESCOLARIDADE);

        // Get all the funcionarioList where escolaridade equals to UPDATED_ESCOLARIDADE
        defaultFuncionarioShouldNotBeFound("escolaridade.in=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEscolaridadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where escolaridade is not null
        defaultFuncionarioShouldBeFound("escolaridade.specified=true");

        // Get all the funcionarioList where escolaridade is null
        defaultFuncionarioShouldNotBeFound("escolaridade.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByFuncaoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where funcao equals to DEFAULT_FUNCAO
        defaultFuncionarioShouldBeFound("funcao.equals=" + DEFAULT_FUNCAO);

        // Get all the funcionarioList where funcao equals to UPDATED_FUNCAO
        defaultFuncionarioShouldNotBeFound("funcao.equals=" + UPDATED_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByFuncaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where funcao not equals to DEFAULT_FUNCAO
        defaultFuncionarioShouldNotBeFound("funcao.notEquals=" + DEFAULT_FUNCAO);

        // Get all the funcionarioList where funcao not equals to UPDATED_FUNCAO
        defaultFuncionarioShouldBeFound("funcao.notEquals=" + UPDATED_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByFuncaoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where funcao in DEFAULT_FUNCAO or UPDATED_FUNCAO
        defaultFuncionarioShouldBeFound("funcao.in=" + DEFAULT_FUNCAO + "," + UPDATED_FUNCAO);

        // Get all the funcionarioList where funcao equals to UPDATED_FUNCAO
        defaultFuncionarioShouldNotBeFound("funcao.in=" + UPDATED_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByFuncaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where funcao is not null
        defaultFuncionarioShouldBeFound("funcao.specified=true");

        // Get all the funcionarioList where funcao is null
        defaultFuncionarioShouldNotBeFound("funcao.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao equals to DEFAULT_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldBeFound("descOutraFuncao.equals=" + DEFAULT_DESC_OUTRA_FUNCAO);

        // Get all the funcionarioList where descOutraFuncao equals to UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.equals=" + UPDATED_DESC_OUTRA_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao not equals to DEFAULT_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.notEquals=" + DEFAULT_DESC_OUTRA_FUNCAO);

        // Get all the funcionarioList where descOutraFuncao not equals to UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldBeFound("descOutraFuncao.notEquals=" + UPDATED_DESC_OUTRA_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao in DEFAULT_DESC_OUTRA_FUNCAO or UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldBeFound("descOutraFuncao.in=" + DEFAULT_DESC_OUTRA_FUNCAO + "," + UPDATED_DESC_OUTRA_FUNCAO);

        // Get all the funcionarioList where descOutraFuncao equals to UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.in=" + UPDATED_DESC_OUTRA_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao is not null
        defaultFuncionarioShouldBeFound("descOutraFuncao.specified=true");

        // Get all the funcionarioList where descOutraFuncao is null
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao contains DEFAULT_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldBeFound("descOutraFuncao.contains=" + DEFAULT_DESC_OUTRA_FUNCAO);

        // Get all the funcionarioList where descOutraFuncao contains UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.contains=" + UPDATED_DESC_OUTRA_FUNCAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDescOutraFuncaoNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where descOutraFuncao does not contain DEFAULT_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldNotBeFound("descOutraFuncao.doesNotContain=" + DEFAULT_DESC_OUTRA_FUNCAO);

        // Get all the funcionarioList where descOutraFuncao does not contain UPDATED_DESC_OUTRA_FUNCAO
        defaultFuncionarioShouldBeFound("descOutraFuncao.doesNotContain=" + UPDATED_DESC_OUTRA_FUNCAO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome equals to DEFAULT_LOGRADOURO_NOME
        defaultFuncionarioShouldBeFound("logradouroNome.equals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the funcionarioList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldNotBeFound("logradouroNome.equals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome not equals to DEFAULT_LOGRADOURO_NOME
        defaultFuncionarioShouldNotBeFound("logradouroNome.notEquals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the funcionarioList where logradouroNome not equals to UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldBeFound("logradouroNome.notEquals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome in DEFAULT_LOGRADOURO_NOME or UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldBeFound("logradouroNome.in=" + DEFAULT_LOGRADOURO_NOME + "," + UPDATED_LOGRADOURO_NOME);

        // Get all the funcionarioList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldNotBeFound("logradouroNome.in=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome is not null
        defaultFuncionarioShouldBeFound("logradouroNome.specified=true");

        // Get all the funcionarioList where logradouroNome is null
        defaultFuncionarioShouldNotBeFound("logradouroNome.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome contains DEFAULT_LOGRADOURO_NOME
        defaultFuncionarioShouldBeFound("logradouroNome.contains=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the funcionarioList where logradouroNome contains UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldNotBeFound("logradouroNome.contains=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNomeNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNome does not contain DEFAULT_LOGRADOURO_NOME
        defaultFuncionarioShouldNotBeFound("logradouroNome.doesNotContain=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the funcionarioList where logradouroNome does not contain UPDATED_LOGRADOURO_NOME
        defaultFuncionarioShouldBeFound("logradouroNome.doesNotContain=" + UPDATED_LOGRADOURO_NOME);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero equals to DEFAULT_LOGRADOURO_NUMERO
        defaultFuncionarioShouldBeFound("logradouroNumero.equals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the funcionarioList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldNotBeFound("logradouroNumero.equals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero not equals to DEFAULT_LOGRADOURO_NUMERO
        defaultFuncionarioShouldNotBeFound("logradouroNumero.notEquals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the funcionarioList where logradouroNumero not equals to UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldBeFound("logradouroNumero.notEquals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero in DEFAULT_LOGRADOURO_NUMERO or UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldBeFound("logradouroNumero.in=" + DEFAULT_LOGRADOURO_NUMERO + "," + UPDATED_LOGRADOURO_NUMERO);

        // Get all the funcionarioList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldNotBeFound("logradouroNumero.in=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero is not null
        defaultFuncionarioShouldBeFound("logradouroNumero.specified=true");

        // Get all the funcionarioList where logradouroNumero is null
        defaultFuncionarioShouldNotBeFound("logradouroNumero.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero contains DEFAULT_LOGRADOURO_NUMERO
        defaultFuncionarioShouldBeFound("logradouroNumero.contains=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the funcionarioList where logradouroNumero contains UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldNotBeFound("logradouroNumero.contains=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroNumeroNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroNumero does not contain DEFAULT_LOGRADOURO_NUMERO
        defaultFuncionarioShouldNotBeFound("logradouroNumero.doesNotContain=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the funcionarioList where logradouroNumero does not contain UPDATED_LOGRADOURO_NUMERO
        defaultFuncionarioShouldBeFound("logradouroNumero.doesNotContain=" + UPDATED_LOGRADOURO_NUMERO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldBeFound("logradouroComplemento.equals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the funcionarioList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.equals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento not equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.notEquals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the funcionarioList where logradouroComplemento not equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldBeFound("logradouroComplemento.notEquals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento in DEFAULT_LOGRADOURO_COMPLEMENTO or UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldBeFound("logradouroComplemento.in=" + DEFAULT_LOGRADOURO_COMPLEMENTO + "," + UPDATED_LOGRADOURO_COMPLEMENTO);

        // Get all the funcionarioList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.in=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento is not null
        defaultFuncionarioShouldBeFound("logradouroComplemento.specified=true");

        // Get all the funcionarioList where logradouroComplemento is null
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento contains DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldBeFound("logradouroComplemento.contains=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the funcionarioList where logradouroComplemento contains UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.contains=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByLogradouroComplementoNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where logradouroComplemento does not contain DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldNotBeFound("logradouroComplemento.doesNotContain=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the funcionarioList where logradouroComplemento does not contain UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFuncionarioShouldBeFound("logradouroComplemento.doesNotContain=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro equals to DEFAULT_BAIRRO
        defaultFuncionarioShouldBeFound("bairro.equals=" + DEFAULT_BAIRRO);

        // Get all the funcionarioList where bairro equals to UPDATED_BAIRRO
        defaultFuncionarioShouldNotBeFound("bairro.equals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro not equals to DEFAULT_BAIRRO
        defaultFuncionarioShouldNotBeFound("bairro.notEquals=" + DEFAULT_BAIRRO);

        // Get all the funcionarioList where bairro not equals to UPDATED_BAIRRO
        defaultFuncionarioShouldBeFound("bairro.notEquals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByBairroIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro in DEFAULT_BAIRRO or UPDATED_BAIRRO
        defaultFuncionarioShouldBeFound("bairro.in=" + DEFAULT_BAIRRO + "," + UPDATED_BAIRRO);

        // Get all the funcionarioList where bairro equals to UPDATED_BAIRRO
        defaultFuncionarioShouldNotBeFound("bairro.in=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro is not null
        defaultFuncionarioShouldBeFound("bairro.specified=true");

        // Get all the funcionarioList where bairro is null
        defaultFuncionarioShouldNotBeFound("bairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByBairroContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro contains DEFAULT_BAIRRO
        defaultFuncionarioShouldBeFound("bairro.contains=" + DEFAULT_BAIRRO);

        // Get all the funcionarioList where bairro contains UPDATED_BAIRRO
        defaultFuncionarioShouldNotBeFound("bairro.contains=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByBairroNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where bairro does not contain DEFAULT_BAIRRO
        defaultFuncionarioShouldNotBeFound("bairro.doesNotContain=" + DEFAULT_BAIRRO);

        // Get all the funcionarioList where bairro does not contain UPDATED_BAIRRO
        defaultFuncionarioShouldBeFound("bairro.doesNotContain=" + UPDATED_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro equals to DEFAULT_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.equals=" + DEFAULT_PROXIMIDADES_LOGRADOURO);

        // Get all the funcionarioList where proximidadesLogradouro equals to UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.equals=" + UPDATED_PROXIMIDADES_LOGRADOURO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro not equals to DEFAULT_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.notEquals=" + DEFAULT_PROXIMIDADES_LOGRADOURO);

        // Get all the funcionarioList where proximidadesLogradouro not equals to UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.notEquals=" + UPDATED_PROXIMIDADES_LOGRADOURO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro in DEFAULT_PROXIMIDADES_LOGRADOURO or UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.in=" + DEFAULT_PROXIMIDADES_LOGRADOURO + "," + UPDATED_PROXIMIDADES_LOGRADOURO);

        // Get all the funcionarioList where proximidadesLogradouro equals to UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.in=" + UPDATED_PROXIMIDADES_LOGRADOURO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro is not null
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.specified=true");

        // Get all the funcionarioList where proximidadesLogradouro is null
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro contains DEFAULT_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.contains=" + DEFAULT_PROXIMIDADES_LOGRADOURO);

        // Get all the funcionarioList where proximidadesLogradouro contains UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.contains=" + UPDATED_PROXIMIDADES_LOGRADOURO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByProximidadesLogradouroNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where proximidadesLogradouro does not contain DEFAULT_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldNotBeFound("proximidadesLogradouro.doesNotContain=" + DEFAULT_PROXIMIDADES_LOGRADOURO);

        // Get all the funcionarioList where proximidadesLogradouro does not contain UPDATED_PROXIMIDADES_LOGRADOURO
        defaultFuncionarioShouldBeFound("proximidadesLogradouro.doesNotContain=" + UPDATED_PROXIMIDADES_LOGRADOURO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByCepIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep equals to DEFAULT_CEP
        defaultFuncionarioShouldBeFound("cep.equals=" + DEFAULT_CEP);

        // Get all the funcionarioList where cep equals to UPDATED_CEP
        defaultFuncionarioShouldNotBeFound("cep.equals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep not equals to DEFAULT_CEP
        defaultFuncionarioShouldNotBeFound("cep.notEquals=" + DEFAULT_CEP);

        // Get all the funcionarioList where cep not equals to UPDATED_CEP
        defaultFuncionarioShouldBeFound("cep.notEquals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCepIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep in DEFAULT_CEP or UPDATED_CEP
        defaultFuncionarioShouldBeFound("cep.in=" + DEFAULT_CEP + "," + UPDATED_CEP);

        // Get all the funcionarioList where cep equals to UPDATED_CEP
        defaultFuncionarioShouldNotBeFound("cep.in=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep is not null
        defaultFuncionarioShouldBeFound("cep.specified=true");

        // Get all the funcionarioList where cep is null
        defaultFuncionarioShouldNotBeFound("cep.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByCepContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep contains DEFAULT_CEP
        defaultFuncionarioShouldBeFound("cep.contains=" + DEFAULT_CEP);

        // Get all the funcionarioList where cep contains UPDATED_CEP
        defaultFuncionarioShouldNotBeFound("cep.contains=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCepNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cep does not contain DEFAULT_CEP
        defaultFuncionarioShouldNotBeFound("cep.doesNotContain=" + DEFAULT_CEP);

        // Get all the funcionarioList where cep does not contain UPDATED_CEP
        defaultFuncionarioShouldBeFound("cep.doesNotContain=" + UPDATED_CEP);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade equals to DEFAULT_CIDADE
        defaultFuncionarioShouldBeFound("cidade.equals=" + DEFAULT_CIDADE);

        // Get all the funcionarioList where cidade equals to UPDATED_CIDADE
        defaultFuncionarioShouldNotBeFound("cidade.equals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade not equals to DEFAULT_CIDADE
        defaultFuncionarioShouldNotBeFound("cidade.notEquals=" + DEFAULT_CIDADE);

        // Get all the funcionarioList where cidade not equals to UPDATED_CIDADE
        defaultFuncionarioShouldBeFound("cidade.notEquals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade in DEFAULT_CIDADE or UPDATED_CIDADE
        defaultFuncionarioShouldBeFound("cidade.in=" + DEFAULT_CIDADE + "," + UPDATED_CIDADE);

        // Get all the funcionarioList where cidade equals to UPDATED_CIDADE
        defaultFuncionarioShouldNotBeFound("cidade.in=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade is not null
        defaultFuncionarioShouldBeFound("cidade.specified=true");

        // Get all the funcionarioList where cidade is null
        defaultFuncionarioShouldNotBeFound("cidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByCidadeContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade contains DEFAULT_CIDADE
        defaultFuncionarioShouldBeFound("cidade.contains=" + DEFAULT_CIDADE);

        // Get all the funcionarioList where cidade contains UPDATED_CIDADE
        defaultFuncionarioShouldNotBeFound("cidade.contains=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where cidade does not contain DEFAULT_CIDADE
        defaultFuncionarioShouldNotBeFound("cidade.doesNotContain=" + DEFAULT_CIDADE);

        // Get all the funcionarioList where cidade does not contain UPDATED_CIDADE
        defaultFuncionarioShouldBeFound("cidade.doesNotContain=" + UPDATED_CIDADE);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estado equals to DEFAULT_ESTADO
        defaultFuncionarioShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the funcionarioList where estado equals to UPDATED_ESTADO
        defaultFuncionarioShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estado not equals to DEFAULT_ESTADO
        defaultFuncionarioShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the funcionarioList where estado not equals to UPDATED_ESTADO
        defaultFuncionarioShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultFuncionarioShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the funcionarioList where estado equals to UPDATED_ESTADO
        defaultFuncionarioShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where estado is not null
        defaultFuncionarioShouldBeFound("estado.specified=true");

        // Get all the funcionarioList where estado is null
        defaultFuncionarioShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataHoraCadastroIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataHoraCadastro equals to DEFAULT_DATA_HORA_CADASTRO
        defaultFuncionarioShouldBeFound("dataHoraCadastro.equals=" + DEFAULT_DATA_HORA_CADASTRO);

        // Get all the funcionarioList where dataHoraCadastro equals to UPDATED_DATA_HORA_CADASTRO
        defaultFuncionarioShouldNotBeFound("dataHoraCadastro.equals=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataHoraCadastroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataHoraCadastro not equals to DEFAULT_DATA_HORA_CADASTRO
        defaultFuncionarioShouldNotBeFound("dataHoraCadastro.notEquals=" + DEFAULT_DATA_HORA_CADASTRO);

        // Get all the funcionarioList where dataHoraCadastro not equals to UPDATED_DATA_HORA_CADASTRO
        defaultFuncionarioShouldBeFound("dataHoraCadastro.notEquals=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataHoraCadastroIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataHoraCadastro in DEFAULT_DATA_HORA_CADASTRO or UPDATED_DATA_HORA_CADASTRO
        defaultFuncionarioShouldBeFound("dataHoraCadastro.in=" + DEFAULT_DATA_HORA_CADASTRO + "," + UPDATED_DATA_HORA_CADASTRO);

        // Get all the funcionarioList where dataHoraCadastro equals to UPDATED_DATA_HORA_CADASTRO
        defaultFuncionarioShouldNotBeFound("dataHoraCadastro.in=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByDataHoraCadastroIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where dataHoraCadastro is not null
        defaultFuncionarioShouldBeFound("dataHoraCadastro.specified=true");

        // Get all the funcionarioList where dataHoraCadastro is null
        defaultFuncionarioShouldNotBeFound("dataHoraCadastro.specified=false");
    }

    @Test
    @Transactional
    public void getAllFuncionariosByObservacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao equals to DEFAULT_OBSERVACAO
        defaultFuncionarioShouldBeFound("observacao.equals=" + DEFAULT_OBSERVACAO);

        // Get all the funcionarioList where observacao equals to UPDATED_OBSERVACAO
        defaultFuncionarioShouldNotBeFound("observacao.equals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByObservacaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao not equals to DEFAULT_OBSERVACAO
        defaultFuncionarioShouldNotBeFound("observacao.notEquals=" + DEFAULT_OBSERVACAO);

        // Get all the funcionarioList where observacao not equals to UPDATED_OBSERVACAO
        defaultFuncionarioShouldBeFound("observacao.notEquals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByObservacaoIsInShouldWork() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao in DEFAULT_OBSERVACAO or UPDATED_OBSERVACAO
        defaultFuncionarioShouldBeFound("observacao.in=" + DEFAULT_OBSERVACAO + "," + UPDATED_OBSERVACAO);

        // Get all the funcionarioList where observacao equals to UPDATED_OBSERVACAO
        defaultFuncionarioShouldNotBeFound("observacao.in=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByObservacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao is not null
        defaultFuncionarioShouldBeFound("observacao.specified=true");

        // Get all the funcionarioList where observacao is null
        defaultFuncionarioShouldNotBeFound("observacao.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuncionariosByObservacaoContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao contains DEFAULT_OBSERVACAO
        defaultFuncionarioShouldBeFound("observacao.contains=" + DEFAULT_OBSERVACAO);

        // Get all the funcionarioList where observacao contains UPDATED_OBSERVACAO
        defaultFuncionarioShouldNotBeFound("observacao.contains=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFuncionariosByObservacaoNotContainsSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList where observacao does not contain DEFAULT_OBSERVACAO
        defaultFuncionarioShouldNotBeFound("observacao.doesNotContain=" + DEFAULT_OBSERVACAO);

        // Get all the funcionarioList where observacao does not contain UPDATED_OBSERVACAO
        defaultFuncionarioShouldBeFound("observacao.doesNotContain=" + UPDATED_OBSERVACAO);
    }


    @Test
    @Transactional
    public void getAllFuncionariosByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        funcionario.setUser(user);
        funcionarioRepository.saveAndFlush(funcionario);
        Long userId = user.getId();

        // Get all the funcionarioList where user equals to userId
        defaultFuncionarioShouldBeFound("userId.equals=" + userId);

        // Get all the funcionarioList where user equals to userId + 1
        defaultFuncionarioShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllFuncionariosByEspecialidadeSaudeIsEqualToSomething() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);
        EspecialidadeSaude especialidadeSaude = EspecialidadeSaudeResourceIT.createEntity(em);
        em.persist(especialidadeSaude);
        em.flush();
        funcionario.setEspecialidadeSaude(especialidadeSaude);
        funcionarioRepository.saveAndFlush(funcionario);
        Long especialidadeSaudeId = especialidadeSaude.getId();

        // Get all the funcionarioList where especialidadeSaude equals to especialidadeSaudeId
        defaultFuncionarioShouldBeFound("especialidadeSaudeId.equals=" + especialidadeSaudeId);

        // Get all the funcionarioList where especialidadeSaude equals to especialidadeSaudeId + 1
        defaultFuncionarioShouldNotBeFound("especialidadeSaudeId.equals=" + (especialidadeSaudeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFuncionarioShouldBeFound(String filter) throws Exception {
        restFuncionarioMockMvc.perform(get("/api/funcionarios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].numeroConselhoProfissional").value(hasItem(DEFAULT_NUMERO_CONSELHO_PROFISSIONAL)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].cnh").value(hasItem(DEFAULT_CNH)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].dataAdmissao").value(hasItem(DEFAULT_DATA_ADMISSAO.toString())))
            .andExpect(jsonPath("$.[*].dataDesligamento").value(hasItem(DEFAULT_DATA_DESLIGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].salario").value(hasItem(DEFAULT_SALARIO.intValue())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].escolaridade").value(hasItem(DEFAULT_ESCOLARIDADE.toString())))
            .andExpect(jsonPath("$.[*].funcao").value(hasItem(DEFAULT_FUNCAO.toString())))
            .andExpect(jsonPath("$.[*].descOutraFuncao").value(hasItem(DEFAULT_DESC_OUTRA_FUNCAO)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].proximidadesLogradouro").value(hasItem(DEFAULT_PROXIMIDADES_LOGRADOURO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].dataHoraCadastro").value(hasItem(DEFAULT_DATA_HORA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)))
            .andExpect(jsonPath("$.[*].curriculoContentType").value(hasItem(DEFAULT_CURRICULO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].curriculo").value(hasItem(Base64Utils.encodeToString(DEFAULT_CURRICULO))));

        // Check, that the count call also returns 1
        restFuncionarioMockMvc.perform(get("/api/funcionarios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFuncionarioShouldNotBeFound(String filter) throws Exception {
        restFuncionarioMockMvc.perform(get("/api/funcionarios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFuncionarioMockMvc.perform(get("/api/funcionarios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFuncionario() throws Exception {
        // Get the funcionario
        restFuncionarioMockMvc.perform(get("/api/funcionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuncionario() throws Exception {
        // Initialize the database
        funcionarioService.save(funcionario);

        int databaseSizeBeforeUpdate = funcionarioRepository.findAll().size();

        // Update the funcionario
        Funcionario updatedFuncionario = funcionarioRepository.findById(funcionario.getId()).get();
        // Disconnect from session so that the updates on updatedFuncionario are not directly saved in db
        em.detach(updatedFuncionario);
        updatedFuncionario
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .dataNascimento(UPDATED_DATA_NASCIMENTO)
            .numeroConselhoProfissional(UPDATED_NUMERO_CONSELHO_PROFISSIONAL)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .cnh(UPDATED_CNH)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .email(UPDATED_EMAIL)
            .dataAdmissao(UPDATED_DATA_ADMISSAO)
            .dataDesligamento(UPDATED_DATA_DESLIGAMENTO)
            .salario(UPDATED_SALARIO)
            .sexo(UPDATED_SEXO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .escolaridade(UPDATED_ESCOLARIDADE)
            .funcao(UPDATED_FUNCAO)
            .descOutraFuncao(UPDATED_DESC_OUTRA_FUNCAO)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .proximidadesLogradouro(UPDATED_PROXIMIDADES_LOGRADOURO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO)
            .observacao(UPDATED_OBSERVACAO)
            .curriculo(UPDATED_CURRICULO)
            .curriculoContentType(UPDATED_CURRICULO_CONTENT_TYPE);

        restFuncionarioMockMvc.perform(put("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuncionario)))
            .andExpect(status().isOk());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeUpdate);
        Funcionario testFuncionario = funcionarioList.get(funcionarioList.size() - 1);
        assertThat(testFuncionario.getNomeCompleto()).isEqualTo(UPDATED_NOME_COMPLETO);
        assertThat(testFuncionario.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testFuncionario.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testFuncionario.getDataNascimento()).isEqualTo(UPDATED_DATA_NASCIMENTO);
        assertThat(testFuncionario.getNumeroConselhoProfissional()).isEqualTo(UPDATED_NUMERO_CONSELHO_PROFISSIONAL);
        assertThat(testFuncionario.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testFuncionario.getRg()).isEqualTo(UPDATED_RG);
        assertThat(testFuncionario.getCnh()).isEqualTo(UPDATED_CNH);
        assertThat(testFuncionario.getTelefone1()).isEqualTo(UPDATED_TELEFONE_1);
        assertThat(testFuncionario.getTelefone2()).isEqualTo(UPDATED_TELEFONE_2);
        assertThat(testFuncionario.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFuncionario.getDataAdmissao()).isEqualTo(UPDATED_DATA_ADMISSAO);
        assertThat(testFuncionario.getDataDesligamento()).isEqualTo(UPDATED_DATA_DESLIGAMENTO);
        assertThat(testFuncionario.getSalario()).isEqualTo(UPDATED_SALARIO);
        assertThat(testFuncionario.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testFuncionario.getEstadoCivil()).isEqualTo(UPDATED_ESTADO_CIVIL);
        assertThat(testFuncionario.getEscolaridade()).isEqualTo(UPDATED_ESCOLARIDADE);
        assertThat(testFuncionario.getFuncao()).isEqualTo(UPDATED_FUNCAO);
        assertThat(testFuncionario.getDescOutraFuncao()).isEqualTo(UPDATED_DESC_OUTRA_FUNCAO);
        assertThat(testFuncionario.getLogradouroNome()).isEqualTo(UPDATED_LOGRADOURO_NOME);
        assertThat(testFuncionario.getLogradouroNumero()).isEqualTo(UPDATED_LOGRADOURO_NUMERO);
        assertThat(testFuncionario.getLogradouroComplemento()).isEqualTo(UPDATED_LOGRADOURO_COMPLEMENTO);
        assertThat(testFuncionario.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testFuncionario.getProximidadesLogradouro()).isEqualTo(UPDATED_PROXIMIDADES_LOGRADOURO);
        assertThat(testFuncionario.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testFuncionario.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testFuncionario.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testFuncionario.getDataHoraCadastro()).isEqualTo(UPDATED_DATA_HORA_CADASTRO);
        assertThat(testFuncionario.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testFuncionario.getCurriculo()).isEqualTo(UPDATED_CURRICULO);
        assertThat(testFuncionario.getCurriculoContentType()).isEqualTo(UPDATED_CURRICULO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFuncionario() throws Exception {
        int databaseSizeBeforeUpdate = funcionarioRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFuncionarioMockMvc.perform(put("/api/funcionarios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFuncionario() throws Exception {
        // Initialize the database
        funcionarioService.save(funcionario);

        int databaseSizeBeforeDelete = funcionarioRepository.findAll().size();

        // Delete the funcionario
        restFuncionarioMockMvc.perform(delete("/api/funcionarios/{id}", funcionario.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
