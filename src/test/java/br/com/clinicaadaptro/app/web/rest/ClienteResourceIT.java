package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.ClinicaAdaptrojhApp;
import br.com.clinicaadaptro.app.domain.Cliente;
import br.com.clinicaadaptro.app.domain.PlanoEstrategico;
import br.com.clinicaadaptro.app.domain.Cliente;
import br.com.clinicaadaptro.app.repository.ClienteRepository;
import br.com.clinicaadaptro.app.service.ClienteService;
import br.com.clinicaadaptro.app.service.dto.ClienteCriteria;
import br.com.clinicaadaptro.app.service.ClienteQueryService;

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

import br.com.clinicaadaptro.app.domain.enumeration.TipoCliente;
import br.com.clinicaadaptro.app.domain.enumeration.Sexo;
import br.com.clinicaadaptro.app.domain.enumeration.EstadoCivil;
import br.com.clinicaadaptro.app.domain.enumeration.Escolaridade;
import br.com.clinicaadaptro.app.domain.enumeration.Convenio;
import br.com.clinicaadaptro.app.domain.enumeration.Procedencia;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
import br.com.clinicaadaptro.app.domain.enumeration.TipoParentesco;
import br.com.clinicaadaptro.app.domain.enumeration.TipoParentesco;
/**
 * Integration tests for the {@link ClienteResource} REST controller.
 */
@SpringBootTest(classes = ClinicaAdaptrojhApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClienteResourceIT {

    private static final TipoCliente DEFAULT_TIPO_CLIENTE = TipoCliente.PACIENTE;
    private static final TipoCliente UPDATED_TIPO_CLIENTE = TipoCliente.OUTRO;

    private static final String DEFAULT_MATRICULA = "AAAAAA";
    private static final String UPDATED_MATRICULA = "BBBBBB";

    private static final String DEFAULT_NOME_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMPLETO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_NASCIMENTO = LocalDate.ofEpochDay(-1L);

    private static final Sexo DEFAULT_SEXO = Sexo.M;
    private static final Sexo UPDATED_SEXO = Sexo.F;

    private static final EstadoCivil DEFAULT_ESTADO_CIVIL = EstadoCivil.SOLTEIRO;
    private static final EstadoCivil UPDATED_ESTADO_CIVIL = EstadoCivil.CASADO;

    private static final Escolaridade DEFAULT_ESCOLARIDADE = Escolaridade.EFC;
    private static final Escolaridade UPDATED_ESCOLARIDADE = Escolaridade.EFI;

    private static final Convenio DEFAULT_CONVENIO = Convenio.UNIMED_CE;
    private static final Convenio UPDATED_CONVENIO = Convenio.OUTRO;

    private static final String DEFAULT_NUM_CARTEIRINHA_CONVENIO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CARTEIRINHA_CONVENIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_VALIDADE_CONVENIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_VALIDADE_CONVENIO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_VALIDADE_CONVENIO = LocalDate.ofEpochDay(-1L);

    private static final Procedencia DEFAULT_PROCEDENCIA = Procedencia.ENCAMINHAMENTO_MEDICO;
    private static final Procedencia UPDATED_PROCEDENCIA = Procedencia.INDICACAO_PACIENTE;

    private static final String DEFAULT_PROFISSAO = "AAAAAAAAAA";
    private static final String UPDATED_PROFISSAO = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "66441785577";
    private static final String UPDATED_CPF = "72521367319";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_1 = "(96)259584";
    private static final String UPDATED_TELEFONE_1 = " 7413-779";

    private static final String DEFAULT_TELEFONE_2 = " 21720176";
    private static final String UPDATED_TELEFONE_2 = "(681)8144284";

    private static final String DEFAULT_EMAIL = "N@CE9v;.'3-Qx\"";
    private static final String UPDATED_EMAIL = "+y@^.N\\3=Qb";

    private static final String DEFAULT_LOGRADOURO_NOME = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGRADOURO_NUMERO = "AAAAA";
    private static final String UPDATED_LOGRADOURO_NUMERO = "BBBBB";

    private static final String DEFAULT_LOGRADOURO_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "27656-361";
    private static final String UPDATED_CEP = "54871-463";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.AC;
    private static final Estado UPDATED_ESTADO = Estado.AL;

    private static final TipoParentesco DEFAULT_PARENTESCO_RESPONSAVEL = TipoParentesco.PAI;
    private static final TipoParentesco UPDATED_PARENTESCO_RESPONSAVEL = TipoParentesco.MAE;

    private static final TipoParentesco DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO = TipoParentesco.PAI;
    private static final TipoParentesco UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO = TipoParentesco.MAE;

    private static final Instant DEFAULT_DATA_HORA_CADASTRO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_HORA_CADASTRO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ATIVO = false;
    private static final Boolean UPDATED_ATIVO = true;

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteQueryService clienteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClienteMockMvc;

    private Cliente cliente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cliente createEntity(EntityManager em) {
        Cliente cliente = new Cliente()
            .tipoCliente(DEFAULT_TIPO_CLIENTE)
            .matricula(DEFAULT_MATRICULA)
            .nomeCompleto(DEFAULT_NOME_COMPLETO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .dataNascimento(DEFAULT_DATA_NASCIMENTO)
            .sexo(DEFAULT_SEXO)
            .estadoCivil(DEFAULT_ESTADO_CIVIL)
            .escolaridade(DEFAULT_ESCOLARIDADE)
            .convenio(DEFAULT_CONVENIO)
            .numCarteirinhaConvenio(DEFAULT_NUM_CARTEIRINHA_CONVENIO)
            .dataValidadeConvenio(DEFAULT_DATA_VALIDADE_CONVENIO)
            .procedencia(DEFAULT_PROCEDENCIA)
            .profissao(DEFAULT_PROFISSAO)
            .cpf(DEFAULT_CPF)
            .rg(DEFAULT_RG)
            .telefone1(DEFAULT_TELEFONE_1)
            .telefone2(DEFAULT_TELEFONE_2)
            .email(DEFAULT_EMAIL)
            .logradouroNome(DEFAULT_LOGRADOURO_NOME)
            .logradouroNumero(DEFAULT_LOGRADOURO_NUMERO)
            .logradouroComplemento(DEFAULT_LOGRADOURO_COMPLEMENTO)
            .bairro(DEFAULT_BAIRRO)
            .cep(DEFAULT_CEP)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .parentescoResponsavel(DEFAULT_PARENTESCO_RESPONSAVEL)
            .parentescoResponsavelFinanceiro(DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO)
            .dataHoraCadastro(DEFAULT_DATA_HORA_CADASTRO)
            .ativo(DEFAULT_ATIVO)
            .observacao(DEFAULT_OBSERVACAO);
        return cliente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cliente createUpdatedEntity(EntityManager em) {
        Cliente cliente = new Cliente()
            .tipoCliente(UPDATED_TIPO_CLIENTE)
            .matricula(UPDATED_MATRICULA)
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .dataNascimento(UPDATED_DATA_NASCIMENTO)
            .sexo(UPDATED_SEXO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .escolaridade(UPDATED_ESCOLARIDADE)
            .convenio(UPDATED_CONVENIO)
            .numCarteirinhaConvenio(UPDATED_NUM_CARTEIRINHA_CONVENIO)
            .dataValidadeConvenio(UPDATED_DATA_VALIDADE_CONVENIO)
            .procedencia(UPDATED_PROCEDENCIA)
            .profissao(UPDATED_PROFISSAO)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .email(UPDATED_EMAIL)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .parentescoResponsavel(UPDATED_PARENTESCO_RESPONSAVEL)
            .parentescoResponsavelFinanceiro(UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO)
            .ativo(UPDATED_ATIVO)
            .observacao(UPDATED_OBSERVACAO);
        return cliente;
    }

    @BeforeEach
    public void initTest() {
        cliente = createEntity(em);
    }

    @Test
    @Transactional
    public void createCliente() throws Exception {
        int databaseSizeBeforeCreate = clienteRepository.findAll().size();
        // Create the Cliente
        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isCreated());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeCreate + 1);
        Cliente testCliente = clienteList.get(clienteList.size() - 1);
        assertThat(testCliente.getTipoCliente()).isEqualTo(DEFAULT_TIPO_CLIENTE);
        assertThat(testCliente.getMatricula()).isEqualTo(DEFAULT_MATRICULA);
        assertThat(testCliente.getNomeCompleto()).isEqualTo(DEFAULT_NOME_COMPLETO);
        assertThat(testCliente.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testCliente.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testCliente.getDataNascimento()).isEqualTo(DEFAULT_DATA_NASCIMENTO);
        assertThat(testCliente.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testCliente.getEstadoCivil()).isEqualTo(DEFAULT_ESTADO_CIVIL);
        assertThat(testCliente.getEscolaridade()).isEqualTo(DEFAULT_ESCOLARIDADE);
        assertThat(testCliente.getConvenio()).isEqualTo(DEFAULT_CONVENIO);
        assertThat(testCliente.getNumCarteirinhaConvenio()).isEqualTo(DEFAULT_NUM_CARTEIRINHA_CONVENIO);
        assertThat(testCliente.getDataValidadeConvenio()).isEqualTo(DEFAULT_DATA_VALIDADE_CONVENIO);
        assertThat(testCliente.getProcedencia()).isEqualTo(DEFAULT_PROCEDENCIA);
        assertThat(testCliente.getProfissao()).isEqualTo(DEFAULT_PROFISSAO);
        assertThat(testCliente.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testCliente.getRg()).isEqualTo(DEFAULT_RG);
        assertThat(testCliente.getTelefone1()).isEqualTo(DEFAULT_TELEFONE_1);
        assertThat(testCliente.getTelefone2()).isEqualTo(DEFAULT_TELEFONE_2);
        assertThat(testCliente.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCliente.getLogradouroNome()).isEqualTo(DEFAULT_LOGRADOURO_NOME);
        assertThat(testCliente.getLogradouroNumero()).isEqualTo(DEFAULT_LOGRADOURO_NUMERO);
        assertThat(testCliente.getLogradouroComplemento()).isEqualTo(DEFAULT_LOGRADOURO_COMPLEMENTO);
        assertThat(testCliente.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testCliente.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testCliente.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testCliente.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCliente.getParentescoResponsavel()).isEqualTo(DEFAULT_PARENTESCO_RESPONSAVEL);
        assertThat(testCliente.getParentescoResponsavelFinanceiro()).isEqualTo(DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO);
        assertThat(testCliente.getDataHoraCadastro()).isEqualTo(DEFAULT_DATA_HORA_CADASTRO);
        assertThat(testCliente.isAtivo()).isEqualTo(DEFAULT_ATIVO);
        assertThat(testCliente.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
    }

    @Test
    @Transactional
    public void createClienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clienteRepository.findAll().size();

        // Create the Cliente with an existing ID
        cliente.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTipoClienteIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setTipoCliente(null);

        // Create the Cliente, which fails.


        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeCompletoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setNomeCompleto(null);

        // Create the Cliente, which fails.


        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setSexo(null);

        // Create the Cliente, which fails.


        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConvenioIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setConvenio(null);

        // Create the Cliente, which fails.


        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataHoraCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setDataHoraCadastro(null);

        // Create the Cliente, which fails.


        restClienteMockMvc.perform(post("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClientes() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList
        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoCliente").value(hasItem(DEFAULT_TIPO_CLIENTE.toString())))
            .andExpect(jsonPath("$.[*].matricula").value(hasItem(DEFAULT_MATRICULA)))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].escolaridade").value(hasItem(DEFAULT_ESCOLARIDADE.toString())))
            .andExpect(jsonPath("$.[*].convenio").value(hasItem(DEFAULT_CONVENIO.toString())))
            .andExpect(jsonPath("$.[*].numCarteirinhaConvenio").value(hasItem(DEFAULT_NUM_CARTEIRINHA_CONVENIO)))
            .andExpect(jsonPath("$.[*].dataValidadeConvenio").value(hasItem(DEFAULT_DATA_VALIDADE_CONVENIO.toString())))
            .andExpect(jsonPath("$.[*].procedencia").value(hasItem(DEFAULT_PROCEDENCIA.toString())))
            .andExpect(jsonPath("$.[*].profissao").value(hasItem(DEFAULT_PROFISSAO)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].parentescoResponsavel").value(hasItem(DEFAULT_PARENTESCO_RESPONSAVEL.toString())))
            .andExpect(jsonPath("$.[*].parentescoResponsavelFinanceiro").value(hasItem(DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO.toString())))
            .andExpect(jsonPath("$.[*].dataHoraCadastro").value(hasItem(DEFAULT_DATA_HORA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)));
    }
    
    @Test
    @Transactional
    public void getCliente() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", cliente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cliente.getId().intValue()))
            .andExpect(jsonPath("$.tipoCliente").value(DEFAULT_TIPO_CLIENTE.toString()))
            .andExpect(jsonPath("$.matricula").value(DEFAULT_MATRICULA))
            .andExpect(jsonPath("$.nomeCompleto").value(DEFAULT_NOME_COMPLETO))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.dataNascimento").value(DEFAULT_DATA_NASCIMENTO.toString()))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO.toString()))
            .andExpect(jsonPath("$.estadoCivil").value(DEFAULT_ESTADO_CIVIL.toString()))
            .andExpect(jsonPath("$.escolaridade").value(DEFAULT_ESCOLARIDADE.toString()))
            .andExpect(jsonPath("$.convenio").value(DEFAULT_CONVENIO.toString()))
            .andExpect(jsonPath("$.numCarteirinhaConvenio").value(DEFAULT_NUM_CARTEIRINHA_CONVENIO))
            .andExpect(jsonPath("$.dataValidadeConvenio").value(DEFAULT_DATA_VALIDADE_CONVENIO.toString()))
            .andExpect(jsonPath("$.procedencia").value(DEFAULT_PROCEDENCIA.toString()))
            .andExpect(jsonPath("$.profissao").value(DEFAULT_PROFISSAO))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.telefone1").value(DEFAULT_TELEFONE_1))
            .andExpect(jsonPath("$.telefone2").value(DEFAULT_TELEFONE_2))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.logradouroNome").value(DEFAULT_LOGRADOURO_NOME))
            .andExpect(jsonPath("$.logradouroNumero").value(DEFAULT_LOGRADOURO_NUMERO))
            .andExpect(jsonPath("$.logradouroComplemento").value(DEFAULT_LOGRADOURO_COMPLEMENTO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.parentescoResponsavel").value(DEFAULT_PARENTESCO_RESPONSAVEL.toString()))
            .andExpect(jsonPath("$.parentescoResponsavelFinanceiro").value(DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO.toString()))
            .andExpect(jsonPath("$.dataHoraCadastro").value(DEFAULT_DATA_HORA_CADASTRO.toString()))
            .andExpect(jsonPath("$.ativo").value(DEFAULT_ATIVO.booleanValue()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO));
    }


    @Test
    @Transactional
    public void getClientesByIdFiltering() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        Long id = cliente.getId();

        defaultClienteShouldBeFound("id.equals=" + id);
        defaultClienteShouldNotBeFound("id.notEquals=" + id);

        defaultClienteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultClienteShouldNotBeFound("id.greaterThan=" + id);

        defaultClienteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultClienteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllClientesByTipoClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where tipoCliente equals to DEFAULT_TIPO_CLIENTE
        defaultClienteShouldBeFound("tipoCliente.equals=" + DEFAULT_TIPO_CLIENTE);

        // Get all the clienteList where tipoCliente equals to UPDATED_TIPO_CLIENTE
        defaultClienteShouldNotBeFound("tipoCliente.equals=" + UPDATED_TIPO_CLIENTE);
    }

    @Test
    @Transactional
    public void getAllClientesByTipoClienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where tipoCliente not equals to DEFAULT_TIPO_CLIENTE
        defaultClienteShouldNotBeFound("tipoCliente.notEquals=" + DEFAULT_TIPO_CLIENTE);

        // Get all the clienteList where tipoCliente not equals to UPDATED_TIPO_CLIENTE
        defaultClienteShouldBeFound("tipoCliente.notEquals=" + UPDATED_TIPO_CLIENTE);
    }

    @Test
    @Transactional
    public void getAllClientesByTipoClienteIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where tipoCliente in DEFAULT_TIPO_CLIENTE or UPDATED_TIPO_CLIENTE
        defaultClienteShouldBeFound("tipoCliente.in=" + DEFAULT_TIPO_CLIENTE + "," + UPDATED_TIPO_CLIENTE);

        // Get all the clienteList where tipoCliente equals to UPDATED_TIPO_CLIENTE
        defaultClienteShouldNotBeFound("tipoCliente.in=" + UPDATED_TIPO_CLIENTE);
    }

    @Test
    @Transactional
    public void getAllClientesByTipoClienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where tipoCliente is not null
        defaultClienteShouldBeFound("tipoCliente.specified=true");

        // Get all the clienteList where tipoCliente is null
        defaultClienteShouldNotBeFound("tipoCliente.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByMatriculaIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula equals to DEFAULT_MATRICULA
        defaultClienteShouldBeFound("matricula.equals=" + DEFAULT_MATRICULA);

        // Get all the clienteList where matricula equals to UPDATED_MATRICULA
        defaultClienteShouldNotBeFound("matricula.equals=" + UPDATED_MATRICULA);
    }

    @Test
    @Transactional
    public void getAllClientesByMatriculaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula not equals to DEFAULT_MATRICULA
        defaultClienteShouldNotBeFound("matricula.notEquals=" + DEFAULT_MATRICULA);

        // Get all the clienteList where matricula not equals to UPDATED_MATRICULA
        defaultClienteShouldBeFound("matricula.notEquals=" + UPDATED_MATRICULA);
    }

    @Test
    @Transactional
    public void getAllClientesByMatriculaIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula in DEFAULT_MATRICULA or UPDATED_MATRICULA
        defaultClienteShouldBeFound("matricula.in=" + DEFAULT_MATRICULA + "," + UPDATED_MATRICULA);

        // Get all the clienteList where matricula equals to UPDATED_MATRICULA
        defaultClienteShouldNotBeFound("matricula.in=" + UPDATED_MATRICULA);
    }

    @Test
    @Transactional
    public void getAllClientesByMatriculaIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula is not null
        defaultClienteShouldBeFound("matricula.specified=true");

        // Get all the clienteList where matricula is null
        defaultClienteShouldNotBeFound("matricula.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByMatriculaContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula contains DEFAULT_MATRICULA
        defaultClienteShouldBeFound("matricula.contains=" + DEFAULT_MATRICULA);

        // Get all the clienteList where matricula contains UPDATED_MATRICULA
        defaultClienteShouldNotBeFound("matricula.contains=" + UPDATED_MATRICULA);
    }

    @Test
    @Transactional
    public void getAllClientesByMatriculaNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where matricula does not contain DEFAULT_MATRICULA
        defaultClienteShouldNotBeFound("matricula.doesNotContain=" + DEFAULT_MATRICULA);

        // Get all the clienteList where matricula does not contain UPDATED_MATRICULA
        defaultClienteShouldBeFound("matricula.doesNotContain=" + UPDATED_MATRICULA);
    }


    @Test
    @Transactional
    public void getAllClientesByNomeCompletoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto equals to DEFAULT_NOME_COMPLETO
        defaultClienteShouldBeFound("nomeCompleto.equals=" + DEFAULT_NOME_COMPLETO);

        // Get all the clienteList where nomeCompleto equals to UPDATED_NOME_COMPLETO
        defaultClienteShouldNotBeFound("nomeCompleto.equals=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllClientesByNomeCompletoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto not equals to DEFAULT_NOME_COMPLETO
        defaultClienteShouldNotBeFound("nomeCompleto.notEquals=" + DEFAULT_NOME_COMPLETO);

        // Get all the clienteList where nomeCompleto not equals to UPDATED_NOME_COMPLETO
        defaultClienteShouldBeFound("nomeCompleto.notEquals=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllClientesByNomeCompletoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto in DEFAULT_NOME_COMPLETO or UPDATED_NOME_COMPLETO
        defaultClienteShouldBeFound("nomeCompleto.in=" + DEFAULT_NOME_COMPLETO + "," + UPDATED_NOME_COMPLETO);

        // Get all the clienteList where nomeCompleto equals to UPDATED_NOME_COMPLETO
        defaultClienteShouldNotBeFound("nomeCompleto.in=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllClientesByNomeCompletoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto is not null
        defaultClienteShouldBeFound("nomeCompleto.specified=true");

        // Get all the clienteList where nomeCompleto is null
        defaultClienteShouldNotBeFound("nomeCompleto.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByNomeCompletoContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto contains DEFAULT_NOME_COMPLETO
        defaultClienteShouldBeFound("nomeCompleto.contains=" + DEFAULT_NOME_COMPLETO);

        // Get all the clienteList where nomeCompleto contains UPDATED_NOME_COMPLETO
        defaultClienteShouldNotBeFound("nomeCompleto.contains=" + UPDATED_NOME_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllClientesByNomeCompletoNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where nomeCompleto does not contain DEFAULT_NOME_COMPLETO
        defaultClienteShouldNotBeFound("nomeCompleto.doesNotContain=" + DEFAULT_NOME_COMPLETO);

        // Get all the clienteList where nomeCompleto does not contain UPDATED_NOME_COMPLETO
        defaultClienteShouldBeFound("nomeCompleto.doesNotContain=" + UPDATED_NOME_COMPLETO);
    }


    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento equals to DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.equals=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento equals to UPDATED_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.equals=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento not equals to DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.notEquals=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento not equals to UPDATED_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.notEquals=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento in DEFAULT_DATA_NASCIMENTO or UPDATED_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.in=" + DEFAULT_DATA_NASCIMENTO + "," + UPDATED_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento equals to UPDATED_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.in=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento is not null
        defaultClienteShouldBeFound("dataNascimento.specified=true");

        // Get all the clienteList where dataNascimento is null
        defaultClienteShouldNotBeFound("dataNascimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento is greater than or equal to DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.greaterThanOrEqual=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento is greater than or equal to UPDATED_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.greaterThanOrEqual=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento is less than or equal to DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.lessThanOrEqual=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento is less than or equal to SMALLER_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.lessThanOrEqual=" + SMALLER_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsLessThanSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento is less than DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.lessThan=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento is less than UPDATED_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.lessThan=" + UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataNascimentoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataNascimento is greater than DEFAULT_DATA_NASCIMENTO
        defaultClienteShouldNotBeFound("dataNascimento.greaterThan=" + DEFAULT_DATA_NASCIMENTO);

        // Get all the clienteList where dataNascimento is greater than SMALLER_DATA_NASCIMENTO
        defaultClienteShouldBeFound("dataNascimento.greaterThan=" + SMALLER_DATA_NASCIMENTO);
    }


    @Test
    @Transactional
    public void getAllClientesBySexoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where sexo equals to DEFAULT_SEXO
        defaultClienteShouldBeFound("sexo.equals=" + DEFAULT_SEXO);

        // Get all the clienteList where sexo equals to UPDATED_SEXO
        defaultClienteShouldNotBeFound("sexo.equals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllClientesBySexoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where sexo not equals to DEFAULT_SEXO
        defaultClienteShouldNotBeFound("sexo.notEquals=" + DEFAULT_SEXO);

        // Get all the clienteList where sexo not equals to UPDATED_SEXO
        defaultClienteShouldBeFound("sexo.notEquals=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllClientesBySexoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where sexo in DEFAULT_SEXO or UPDATED_SEXO
        defaultClienteShouldBeFound("sexo.in=" + DEFAULT_SEXO + "," + UPDATED_SEXO);

        // Get all the clienteList where sexo equals to UPDATED_SEXO
        defaultClienteShouldNotBeFound("sexo.in=" + UPDATED_SEXO);
    }

    @Test
    @Transactional
    public void getAllClientesBySexoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where sexo is not null
        defaultClienteShouldBeFound("sexo.specified=true");

        // Get all the clienteList where sexo is null
        defaultClienteShouldNotBeFound("sexo.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoCivilIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estadoCivil equals to DEFAULT_ESTADO_CIVIL
        defaultClienteShouldBeFound("estadoCivil.equals=" + DEFAULT_ESTADO_CIVIL);

        // Get all the clienteList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultClienteShouldNotBeFound("estadoCivil.equals=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoCivilIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estadoCivil not equals to DEFAULT_ESTADO_CIVIL
        defaultClienteShouldNotBeFound("estadoCivil.notEquals=" + DEFAULT_ESTADO_CIVIL);

        // Get all the clienteList where estadoCivil not equals to UPDATED_ESTADO_CIVIL
        defaultClienteShouldBeFound("estadoCivil.notEquals=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoCivilIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estadoCivil in DEFAULT_ESTADO_CIVIL or UPDATED_ESTADO_CIVIL
        defaultClienteShouldBeFound("estadoCivil.in=" + DEFAULT_ESTADO_CIVIL + "," + UPDATED_ESTADO_CIVIL);

        // Get all the clienteList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultClienteShouldNotBeFound("estadoCivil.in=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoCivilIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estadoCivil is not null
        defaultClienteShouldBeFound("estadoCivil.specified=true");

        // Get all the clienteList where estadoCivil is null
        defaultClienteShouldNotBeFound("estadoCivil.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByEscolaridadeIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where escolaridade equals to DEFAULT_ESCOLARIDADE
        defaultClienteShouldBeFound("escolaridade.equals=" + DEFAULT_ESCOLARIDADE);

        // Get all the clienteList where escolaridade equals to UPDATED_ESCOLARIDADE
        defaultClienteShouldNotBeFound("escolaridade.equals=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByEscolaridadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where escolaridade not equals to DEFAULT_ESCOLARIDADE
        defaultClienteShouldNotBeFound("escolaridade.notEquals=" + DEFAULT_ESCOLARIDADE);

        // Get all the clienteList where escolaridade not equals to UPDATED_ESCOLARIDADE
        defaultClienteShouldBeFound("escolaridade.notEquals=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByEscolaridadeIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where escolaridade in DEFAULT_ESCOLARIDADE or UPDATED_ESCOLARIDADE
        defaultClienteShouldBeFound("escolaridade.in=" + DEFAULT_ESCOLARIDADE + "," + UPDATED_ESCOLARIDADE);

        // Get all the clienteList where escolaridade equals to UPDATED_ESCOLARIDADE
        defaultClienteShouldNotBeFound("escolaridade.in=" + UPDATED_ESCOLARIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByEscolaridadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where escolaridade is not null
        defaultClienteShouldBeFound("escolaridade.specified=true");

        // Get all the clienteList where escolaridade is null
        defaultClienteShouldNotBeFound("escolaridade.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByConvenioIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where convenio equals to DEFAULT_CONVENIO
        defaultClienteShouldBeFound("convenio.equals=" + DEFAULT_CONVENIO);

        // Get all the clienteList where convenio equals to UPDATED_CONVENIO
        defaultClienteShouldNotBeFound("convenio.equals=" + UPDATED_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByConvenioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where convenio not equals to DEFAULT_CONVENIO
        defaultClienteShouldNotBeFound("convenio.notEquals=" + DEFAULT_CONVENIO);

        // Get all the clienteList where convenio not equals to UPDATED_CONVENIO
        defaultClienteShouldBeFound("convenio.notEquals=" + UPDATED_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByConvenioIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where convenio in DEFAULT_CONVENIO or UPDATED_CONVENIO
        defaultClienteShouldBeFound("convenio.in=" + DEFAULT_CONVENIO + "," + UPDATED_CONVENIO);

        // Get all the clienteList where convenio equals to UPDATED_CONVENIO
        defaultClienteShouldNotBeFound("convenio.in=" + UPDATED_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByConvenioIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where convenio is not null
        defaultClienteShouldBeFound("convenio.specified=true");

        // Get all the clienteList where convenio is null
        defaultClienteShouldNotBeFound("convenio.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio equals to DEFAULT_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldBeFound("numCarteirinhaConvenio.equals=" + DEFAULT_NUM_CARTEIRINHA_CONVENIO);

        // Get all the clienteList where numCarteirinhaConvenio equals to UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.equals=" + UPDATED_NUM_CARTEIRINHA_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio not equals to DEFAULT_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.notEquals=" + DEFAULT_NUM_CARTEIRINHA_CONVENIO);

        // Get all the clienteList where numCarteirinhaConvenio not equals to UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldBeFound("numCarteirinhaConvenio.notEquals=" + UPDATED_NUM_CARTEIRINHA_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio in DEFAULT_NUM_CARTEIRINHA_CONVENIO or UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldBeFound("numCarteirinhaConvenio.in=" + DEFAULT_NUM_CARTEIRINHA_CONVENIO + "," + UPDATED_NUM_CARTEIRINHA_CONVENIO);

        // Get all the clienteList where numCarteirinhaConvenio equals to UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.in=" + UPDATED_NUM_CARTEIRINHA_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio is not null
        defaultClienteShouldBeFound("numCarteirinhaConvenio.specified=true");

        // Get all the clienteList where numCarteirinhaConvenio is null
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio contains DEFAULT_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldBeFound("numCarteirinhaConvenio.contains=" + DEFAULT_NUM_CARTEIRINHA_CONVENIO);

        // Get all the clienteList where numCarteirinhaConvenio contains UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.contains=" + UPDATED_NUM_CARTEIRINHA_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByNumCarteirinhaConvenioNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where numCarteirinhaConvenio does not contain DEFAULT_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldNotBeFound("numCarteirinhaConvenio.doesNotContain=" + DEFAULT_NUM_CARTEIRINHA_CONVENIO);

        // Get all the clienteList where numCarteirinhaConvenio does not contain UPDATED_NUM_CARTEIRINHA_CONVENIO
        defaultClienteShouldBeFound("numCarteirinhaConvenio.doesNotContain=" + UPDATED_NUM_CARTEIRINHA_CONVENIO);
    }


    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio equals to DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.equals=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio equals to UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.equals=" + UPDATED_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio not equals to DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.notEquals=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio not equals to UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.notEquals=" + UPDATED_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio in DEFAULT_DATA_VALIDADE_CONVENIO or UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.in=" + DEFAULT_DATA_VALIDADE_CONVENIO + "," + UPDATED_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio equals to UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.in=" + UPDATED_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio is not null
        defaultClienteShouldBeFound("dataValidadeConvenio.specified=true");

        // Get all the clienteList where dataValidadeConvenio is null
        defaultClienteShouldNotBeFound("dataValidadeConvenio.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio is greater than or equal to DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.greaterThanOrEqual=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio is greater than or equal to UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.greaterThanOrEqual=" + UPDATED_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio is less than or equal to DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.lessThanOrEqual=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio is less than or equal to SMALLER_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.lessThanOrEqual=" + SMALLER_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsLessThanSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio is less than DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.lessThan=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio is less than UPDATED_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.lessThan=" + UPDATED_DATA_VALIDADE_CONVENIO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataValidadeConvenioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataValidadeConvenio is greater than DEFAULT_DATA_VALIDADE_CONVENIO
        defaultClienteShouldNotBeFound("dataValidadeConvenio.greaterThan=" + DEFAULT_DATA_VALIDADE_CONVENIO);

        // Get all the clienteList where dataValidadeConvenio is greater than SMALLER_DATA_VALIDADE_CONVENIO
        defaultClienteShouldBeFound("dataValidadeConvenio.greaterThan=" + SMALLER_DATA_VALIDADE_CONVENIO);
    }


    @Test
    @Transactional
    public void getAllClientesByProcedenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where procedencia equals to DEFAULT_PROCEDENCIA
        defaultClienteShouldBeFound("procedencia.equals=" + DEFAULT_PROCEDENCIA);

        // Get all the clienteList where procedencia equals to UPDATED_PROCEDENCIA
        defaultClienteShouldNotBeFound("procedencia.equals=" + UPDATED_PROCEDENCIA);
    }

    @Test
    @Transactional
    public void getAllClientesByProcedenciaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where procedencia not equals to DEFAULT_PROCEDENCIA
        defaultClienteShouldNotBeFound("procedencia.notEquals=" + DEFAULT_PROCEDENCIA);

        // Get all the clienteList where procedencia not equals to UPDATED_PROCEDENCIA
        defaultClienteShouldBeFound("procedencia.notEquals=" + UPDATED_PROCEDENCIA);
    }

    @Test
    @Transactional
    public void getAllClientesByProcedenciaIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where procedencia in DEFAULT_PROCEDENCIA or UPDATED_PROCEDENCIA
        defaultClienteShouldBeFound("procedencia.in=" + DEFAULT_PROCEDENCIA + "," + UPDATED_PROCEDENCIA);

        // Get all the clienteList where procedencia equals to UPDATED_PROCEDENCIA
        defaultClienteShouldNotBeFound("procedencia.in=" + UPDATED_PROCEDENCIA);
    }

    @Test
    @Transactional
    public void getAllClientesByProcedenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where procedencia is not null
        defaultClienteShouldBeFound("procedencia.specified=true");

        // Get all the clienteList where procedencia is null
        defaultClienteShouldNotBeFound("procedencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByProfissaoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao equals to DEFAULT_PROFISSAO
        defaultClienteShouldBeFound("profissao.equals=" + DEFAULT_PROFISSAO);

        // Get all the clienteList where profissao equals to UPDATED_PROFISSAO
        defaultClienteShouldNotBeFound("profissao.equals=" + UPDATED_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllClientesByProfissaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao not equals to DEFAULT_PROFISSAO
        defaultClienteShouldNotBeFound("profissao.notEquals=" + DEFAULT_PROFISSAO);

        // Get all the clienteList where profissao not equals to UPDATED_PROFISSAO
        defaultClienteShouldBeFound("profissao.notEquals=" + UPDATED_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllClientesByProfissaoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao in DEFAULT_PROFISSAO or UPDATED_PROFISSAO
        defaultClienteShouldBeFound("profissao.in=" + DEFAULT_PROFISSAO + "," + UPDATED_PROFISSAO);

        // Get all the clienteList where profissao equals to UPDATED_PROFISSAO
        defaultClienteShouldNotBeFound("profissao.in=" + UPDATED_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllClientesByProfissaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao is not null
        defaultClienteShouldBeFound("profissao.specified=true");

        // Get all the clienteList where profissao is null
        defaultClienteShouldNotBeFound("profissao.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByProfissaoContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao contains DEFAULT_PROFISSAO
        defaultClienteShouldBeFound("profissao.contains=" + DEFAULT_PROFISSAO);

        // Get all the clienteList where profissao contains UPDATED_PROFISSAO
        defaultClienteShouldNotBeFound("profissao.contains=" + UPDATED_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllClientesByProfissaoNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where profissao does not contain DEFAULT_PROFISSAO
        defaultClienteShouldNotBeFound("profissao.doesNotContain=" + DEFAULT_PROFISSAO);

        // Get all the clienteList where profissao does not contain UPDATED_PROFISSAO
        defaultClienteShouldBeFound("profissao.doesNotContain=" + UPDATED_PROFISSAO);
    }


    @Test
    @Transactional
    public void getAllClientesByCpfIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf equals to DEFAULT_CPF
        defaultClienteShouldBeFound("cpf.equals=" + DEFAULT_CPF);

        // Get all the clienteList where cpf equals to UPDATED_CPF
        defaultClienteShouldNotBeFound("cpf.equals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllClientesByCpfIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf not equals to DEFAULT_CPF
        defaultClienteShouldNotBeFound("cpf.notEquals=" + DEFAULT_CPF);

        // Get all the clienteList where cpf not equals to UPDATED_CPF
        defaultClienteShouldBeFound("cpf.notEquals=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllClientesByCpfIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf in DEFAULT_CPF or UPDATED_CPF
        defaultClienteShouldBeFound("cpf.in=" + DEFAULT_CPF + "," + UPDATED_CPF);

        // Get all the clienteList where cpf equals to UPDATED_CPF
        defaultClienteShouldNotBeFound("cpf.in=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllClientesByCpfIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf is not null
        defaultClienteShouldBeFound("cpf.specified=true");

        // Get all the clienteList where cpf is null
        defaultClienteShouldNotBeFound("cpf.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByCpfContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf contains DEFAULT_CPF
        defaultClienteShouldBeFound("cpf.contains=" + DEFAULT_CPF);

        // Get all the clienteList where cpf contains UPDATED_CPF
        defaultClienteShouldNotBeFound("cpf.contains=" + UPDATED_CPF);
    }

    @Test
    @Transactional
    public void getAllClientesByCpfNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cpf does not contain DEFAULT_CPF
        defaultClienteShouldNotBeFound("cpf.doesNotContain=" + DEFAULT_CPF);

        // Get all the clienteList where cpf does not contain UPDATED_CPF
        defaultClienteShouldBeFound("cpf.doesNotContain=" + UPDATED_CPF);
    }


    @Test
    @Transactional
    public void getAllClientesByRgIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg equals to DEFAULT_RG
        defaultClienteShouldBeFound("rg.equals=" + DEFAULT_RG);

        // Get all the clienteList where rg equals to UPDATED_RG
        defaultClienteShouldNotBeFound("rg.equals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllClientesByRgIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg not equals to DEFAULT_RG
        defaultClienteShouldNotBeFound("rg.notEquals=" + DEFAULT_RG);

        // Get all the clienteList where rg not equals to UPDATED_RG
        defaultClienteShouldBeFound("rg.notEquals=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllClientesByRgIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg in DEFAULT_RG or UPDATED_RG
        defaultClienteShouldBeFound("rg.in=" + DEFAULT_RG + "," + UPDATED_RG);

        // Get all the clienteList where rg equals to UPDATED_RG
        defaultClienteShouldNotBeFound("rg.in=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllClientesByRgIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg is not null
        defaultClienteShouldBeFound("rg.specified=true");

        // Get all the clienteList where rg is null
        defaultClienteShouldNotBeFound("rg.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByRgContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg contains DEFAULT_RG
        defaultClienteShouldBeFound("rg.contains=" + DEFAULT_RG);

        // Get all the clienteList where rg contains UPDATED_RG
        defaultClienteShouldNotBeFound("rg.contains=" + UPDATED_RG);
    }

    @Test
    @Transactional
    public void getAllClientesByRgNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where rg does not contain DEFAULT_RG
        defaultClienteShouldNotBeFound("rg.doesNotContain=" + DEFAULT_RG);

        // Get all the clienteList where rg does not contain UPDATED_RG
        defaultClienteShouldBeFound("rg.doesNotContain=" + UPDATED_RG);
    }


    @Test
    @Transactional
    public void getAllClientesByTelefone1IsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 equals to DEFAULT_TELEFONE_1
        defaultClienteShouldBeFound("telefone1.equals=" + DEFAULT_TELEFONE_1);

        // Get all the clienteList where telefone1 equals to UPDATED_TELEFONE_1
        defaultClienteShouldNotBeFound("telefone1.equals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 not equals to DEFAULT_TELEFONE_1
        defaultClienteShouldNotBeFound("telefone1.notEquals=" + DEFAULT_TELEFONE_1);

        // Get all the clienteList where telefone1 not equals to UPDATED_TELEFONE_1
        defaultClienteShouldBeFound("telefone1.notEquals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone1IsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 in DEFAULT_TELEFONE_1 or UPDATED_TELEFONE_1
        defaultClienteShouldBeFound("telefone1.in=" + DEFAULT_TELEFONE_1 + "," + UPDATED_TELEFONE_1);

        // Get all the clienteList where telefone1 equals to UPDATED_TELEFONE_1
        defaultClienteShouldNotBeFound("telefone1.in=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone1IsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 is not null
        defaultClienteShouldBeFound("telefone1.specified=true");

        // Get all the clienteList where telefone1 is null
        defaultClienteShouldNotBeFound("telefone1.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByTelefone1ContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 contains DEFAULT_TELEFONE_1
        defaultClienteShouldBeFound("telefone1.contains=" + DEFAULT_TELEFONE_1);

        // Get all the clienteList where telefone1 contains UPDATED_TELEFONE_1
        defaultClienteShouldNotBeFound("telefone1.contains=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone1NotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone1 does not contain DEFAULT_TELEFONE_1
        defaultClienteShouldNotBeFound("telefone1.doesNotContain=" + DEFAULT_TELEFONE_1);

        // Get all the clienteList where telefone1 does not contain UPDATED_TELEFONE_1
        defaultClienteShouldBeFound("telefone1.doesNotContain=" + UPDATED_TELEFONE_1);
    }


    @Test
    @Transactional
    public void getAllClientesByTelefone2IsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 equals to DEFAULT_TELEFONE_2
        defaultClienteShouldBeFound("telefone2.equals=" + DEFAULT_TELEFONE_2);

        // Get all the clienteList where telefone2 equals to UPDATED_TELEFONE_2
        defaultClienteShouldNotBeFound("telefone2.equals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 not equals to DEFAULT_TELEFONE_2
        defaultClienteShouldNotBeFound("telefone2.notEquals=" + DEFAULT_TELEFONE_2);

        // Get all the clienteList where telefone2 not equals to UPDATED_TELEFONE_2
        defaultClienteShouldBeFound("telefone2.notEquals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone2IsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 in DEFAULT_TELEFONE_2 or UPDATED_TELEFONE_2
        defaultClienteShouldBeFound("telefone2.in=" + DEFAULT_TELEFONE_2 + "," + UPDATED_TELEFONE_2);

        // Get all the clienteList where telefone2 equals to UPDATED_TELEFONE_2
        defaultClienteShouldNotBeFound("telefone2.in=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone2IsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 is not null
        defaultClienteShouldBeFound("telefone2.specified=true");

        // Get all the clienteList where telefone2 is null
        defaultClienteShouldNotBeFound("telefone2.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByTelefone2ContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 contains DEFAULT_TELEFONE_2
        defaultClienteShouldBeFound("telefone2.contains=" + DEFAULT_TELEFONE_2);

        // Get all the clienteList where telefone2 contains UPDATED_TELEFONE_2
        defaultClienteShouldNotBeFound("telefone2.contains=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllClientesByTelefone2NotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where telefone2 does not contain DEFAULT_TELEFONE_2
        defaultClienteShouldNotBeFound("telefone2.doesNotContain=" + DEFAULT_TELEFONE_2);

        // Get all the clienteList where telefone2 does not contain UPDATED_TELEFONE_2
        defaultClienteShouldBeFound("telefone2.doesNotContain=" + UPDATED_TELEFONE_2);
    }


    @Test
    @Transactional
    public void getAllClientesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email equals to DEFAULT_EMAIL
        defaultClienteShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the clienteList where email equals to UPDATED_EMAIL
        defaultClienteShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email not equals to DEFAULT_EMAIL
        defaultClienteShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the clienteList where email not equals to UPDATED_EMAIL
        defaultClienteShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultClienteShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the clienteList where email equals to UPDATED_EMAIL
        defaultClienteShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email is not null
        defaultClienteShouldBeFound("email.specified=true");

        // Get all the clienteList where email is null
        defaultClienteShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByEmailContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email contains DEFAULT_EMAIL
        defaultClienteShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the clienteList where email contains UPDATED_EMAIL
        defaultClienteShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllClientesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where email does not contain DEFAULT_EMAIL
        defaultClienteShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the clienteList where email does not contain UPDATED_EMAIL
        defaultClienteShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllClientesByLogradouroNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome equals to DEFAULT_LOGRADOURO_NOME
        defaultClienteShouldBeFound("logradouroNome.equals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the clienteList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultClienteShouldNotBeFound("logradouroNome.equals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome not equals to DEFAULT_LOGRADOURO_NOME
        defaultClienteShouldNotBeFound("logradouroNome.notEquals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the clienteList where logradouroNome not equals to UPDATED_LOGRADOURO_NOME
        defaultClienteShouldBeFound("logradouroNome.notEquals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNomeIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome in DEFAULT_LOGRADOURO_NOME or UPDATED_LOGRADOURO_NOME
        defaultClienteShouldBeFound("logradouroNome.in=" + DEFAULT_LOGRADOURO_NOME + "," + UPDATED_LOGRADOURO_NOME);

        // Get all the clienteList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultClienteShouldNotBeFound("logradouroNome.in=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome is not null
        defaultClienteShouldBeFound("logradouroNome.specified=true");

        // Get all the clienteList where logradouroNome is null
        defaultClienteShouldNotBeFound("logradouroNome.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByLogradouroNomeContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome contains DEFAULT_LOGRADOURO_NOME
        defaultClienteShouldBeFound("logradouroNome.contains=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the clienteList where logradouroNome contains UPDATED_LOGRADOURO_NOME
        defaultClienteShouldNotBeFound("logradouroNome.contains=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNomeNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNome does not contain DEFAULT_LOGRADOURO_NOME
        defaultClienteShouldNotBeFound("logradouroNome.doesNotContain=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the clienteList where logradouroNome does not contain UPDATED_LOGRADOURO_NOME
        defaultClienteShouldBeFound("logradouroNome.doesNotContain=" + UPDATED_LOGRADOURO_NOME);
    }


    @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero equals to DEFAULT_LOGRADOURO_NUMERO
        defaultClienteShouldBeFound("logradouroNumero.equals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the clienteList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldNotBeFound("logradouroNumero.equals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero not equals to DEFAULT_LOGRADOURO_NUMERO
        defaultClienteShouldNotBeFound("logradouroNumero.notEquals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the clienteList where logradouroNumero not equals to UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldBeFound("logradouroNumero.notEquals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero in DEFAULT_LOGRADOURO_NUMERO or UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldBeFound("logradouroNumero.in=" + DEFAULT_LOGRADOURO_NUMERO + "," + UPDATED_LOGRADOURO_NUMERO);

        // Get all the clienteList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldNotBeFound("logradouroNumero.in=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero is not null
        defaultClienteShouldBeFound("logradouroNumero.specified=true");

        // Get all the clienteList where logradouroNumero is null
        defaultClienteShouldNotBeFound("logradouroNumero.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero contains DEFAULT_LOGRADOURO_NUMERO
        defaultClienteShouldBeFound("logradouroNumero.contains=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the clienteList where logradouroNumero contains UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldNotBeFound("logradouroNumero.contains=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroNumeroNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroNumero does not contain DEFAULT_LOGRADOURO_NUMERO
        defaultClienteShouldNotBeFound("logradouroNumero.doesNotContain=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the clienteList where logradouroNumero does not contain UPDATED_LOGRADOURO_NUMERO
        defaultClienteShouldBeFound("logradouroNumero.doesNotContain=" + UPDATED_LOGRADOURO_NUMERO);
    }


    @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldBeFound("logradouroComplemento.equals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the clienteList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldNotBeFound("logradouroComplemento.equals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento not equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldNotBeFound("logradouroComplemento.notEquals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the clienteList where logradouroComplemento not equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldBeFound("logradouroComplemento.notEquals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento in DEFAULT_LOGRADOURO_COMPLEMENTO or UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldBeFound("logradouroComplemento.in=" + DEFAULT_LOGRADOURO_COMPLEMENTO + "," + UPDATED_LOGRADOURO_COMPLEMENTO);

        // Get all the clienteList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldNotBeFound("logradouroComplemento.in=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento is not null
        defaultClienteShouldBeFound("logradouroComplemento.specified=true");

        // Get all the clienteList where logradouroComplemento is null
        defaultClienteShouldNotBeFound("logradouroComplemento.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento contains DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldBeFound("logradouroComplemento.contains=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the clienteList where logradouroComplemento contains UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldNotBeFound("logradouroComplemento.contains=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllClientesByLogradouroComplementoNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where logradouroComplemento does not contain DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldNotBeFound("logradouroComplemento.doesNotContain=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the clienteList where logradouroComplemento does not contain UPDATED_LOGRADOURO_COMPLEMENTO
        defaultClienteShouldBeFound("logradouroComplemento.doesNotContain=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }


    @Test
    @Transactional
    public void getAllClientesByBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro equals to DEFAULT_BAIRRO
        defaultClienteShouldBeFound("bairro.equals=" + DEFAULT_BAIRRO);

        // Get all the clienteList where bairro equals to UPDATED_BAIRRO
        defaultClienteShouldNotBeFound("bairro.equals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllClientesByBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro not equals to DEFAULT_BAIRRO
        defaultClienteShouldNotBeFound("bairro.notEquals=" + DEFAULT_BAIRRO);

        // Get all the clienteList where bairro not equals to UPDATED_BAIRRO
        defaultClienteShouldBeFound("bairro.notEquals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllClientesByBairroIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro in DEFAULT_BAIRRO or UPDATED_BAIRRO
        defaultClienteShouldBeFound("bairro.in=" + DEFAULT_BAIRRO + "," + UPDATED_BAIRRO);

        // Get all the clienteList where bairro equals to UPDATED_BAIRRO
        defaultClienteShouldNotBeFound("bairro.in=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllClientesByBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro is not null
        defaultClienteShouldBeFound("bairro.specified=true");

        // Get all the clienteList where bairro is null
        defaultClienteShouldNotBeFound("bairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByBairroContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro contains DEFAULT_BAIRRO
        defaultClienteShouldBeFound("bairro.contains=" + DEFAULT_BAIRRO);

        // Get all the clienteList where bairro contains UPDATED_BAIRRO
        defaultClienteShouldNotBeFound("bairro.contains=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllClientesByBairroNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where bairro does not contain DEFAULT_BAIRRO
        defaultClienteShouldNotBeFound("bairro.doesNotContain=" + DEFAULT_BAIRRO);

        // Get all the clienteList where bairro does not contain UPDATED_BAIRRO
        defaultClienteShouldBeFound("bairro.doesNotContain=" + UPDATED_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllClientesByCepIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep equals to DEFAULT_CEP
        defaultClienteShouldBeFound("cep.equals=" + DEFAULT_CEP);

        // Get all the clienteList where cep equals to UPDATED_CEP
        defaultClienteShouldNotBeFound("cep.equals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllClientesByCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep not equals to DEFAULT_CEP
        defaultClienteShouldNotBeFound("cep.notEquals=" + DEFAULT_CEP);

        // Get all the clienteList where cep not equals to UPDATED_CEP
        defaultClienteShouldBeFound("cep.notEquals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllClientesByCepIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep in DEFAULT_CEP or UPDATED_CEP
        defaultClienteShouldBeFound("cep.in=" + DEFAULT_CEP + "," + UPDATED_CEP);

        // Get all the clienteList where cep equals to UPDATED_CEP
        defaultClienteShouldNotBeFound("cep.in=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllClientesByCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep is not null
        defaultClienteShouldBeFound("cep.specified=true");

        // Get all the clienteList where cep is null
        defaultClienteShouldNotBeFound("cep.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByCepContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep contains DEFAULT_CEP
        defaultClienteShouldBeFound("cep.contains=" + DEFAULT_CEP);

        // Get all the clienteList where cep contains UPDATED_CEP
        defaultClienteShouldNotBeFound("cep.contains=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllClientesByCepNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cep does not contain DEFAULT_CEP
        defaultClienteShouldNotBeFound("cep.doesNotContain=" + DEFAULT_CEP);

        // Get all the clienteList where cep does not contain UPDATED_CEP
        defaultClienteShouldBeFound("cep.doesNotContain=" + UPDATED_CEP);
    }


    @Test
    @Transactional
    public void getAllClientesByCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade equals to DEFAULT_CIDADE
        defaultClienteShouldBeFound("cidade.equals=" + DEFAULT_CIDADE);

        // Get all the clienteList where cidade equals to UPDATED_CIDADE
        defaultClienteShouldNotBeFound("cidade.equals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade not equals to DEFAULT_CIDADE
        defaultClienteShouldNotBeFound("cidade.notEquals=" + DEFAULT_CIDADE);

        // Get all the clienteList where cidade not equals to UPDATED_CIDADE
        defaultClienteShouldBeFound("cidade.notEquals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade in DEFAULT_CIDADE or UPDATED_CIDADE
        defaultClienteShouldBeFound("cidade.in=" + DEFAULT_CIDADE + "," + UPDATED_CIDADE);

        // Get all the clienteList where cidade equals to UPDATED_CIDADE
        defaultClienteShouldNotBeFound("cidade.in=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade is not null
        defaultClienteShouldBeFound("cidade.specified=true");

        // Get all the clienteList where cidade is null
        defaultClienteShouldNotBeFound("cidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByCidadeContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade contains DEFAULT_CIDADE
        defaultClienteShouldBeFound("cidade.contains=" + DEFAULT_CIDADE);

        // Get all the clienteList where cidade contains UPDATED_CIDADE
        defaultClienteShouldNotBeFound("cidade.contains=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllClientesByCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where cidade does not contain DEFAULT_CIDADE
        defaultClienteShouldNotBeFound("cidade.doesNotContain=" + DEFAULT_CIDADE);

        // Get all the clienteList where cidade does not contain UPDATED_CIDADE
        defaultClienteShouldBeFound("cidade.doesNotContain=" + UPDATED_CIDADE);
    }


    @Test
    @Transactional
    public void getAllClientesByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estado equals to DEFAULT_ESTADO
        defaultClienteShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the clienteList where estado equals to UPDATED_ESTADO
        defaultClienteShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estado not equals to DEFAULT_ESTADO
        defaultClienteShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the clienteList where estado not equals to UPDATED_ESTADO
        defaultClienteShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultClienteShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the clienteList where estado equals to UPDATED_ESTADO
        defaultClienteShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllClientesByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where estado is not null
        defaultClienteShouldBeFound("estado.specified=true");

        // Get all the clienteList where estado is null
        defaultClienteShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavel equals to DEFAULT_PARENTESCO_RESPONSAVEL
        defaultClienteShouldBeFound("parentescoResponsavel.equals=" + DEFAULT_PARENTESCO_RESPONSAVEL);

        // Get all the clienteList where parentescoResponsavel equals to UPDATED_PARENTESCO_RESPONSAVEL
        defaultClienteShouldNotBeFound("parentescoResponsavel.equals=" + UPDATED_PARENTESCO_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavel not equals to DEFAULT_PARENTESCO_RESPONSAVEL
        defaultClienteShouldNotBeFound("parentescoResponsavel.notEquals=" + DEFAULT_PARENTESCO_RESPONSAVEL);

        // Get all the clienteList where parentescoResponsavel not equals to UPDATED_PARENTESCO_RESPONSAVEL
        defaultClienteShouldBeFound("parentescoResponsavel.notEquals=" + UPDATED_PARENTESCO_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavel in DEFAULT_PARENTESCO_RESPONSAVEL or UPDATED_PARENTESCO_RESPONSAVEL
        defaultClienteShouldBeFound("parentescoResponsavel.in=" + DEFAULT_PARENTESCO_RESPONSAVEL + "," + UPDATED_PARENTESCO_RESPONSAVEL);

        // Get all the clienteList where parentescoResponsavel equals to UPDATED_PARENTESCO_RESPONSAVEL
        defaultClienteShouldNotBeFound("parentescoResponsavel.in=" + UPDATED_PARENTESCO_RESPONSAVEL);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavel is not null
        defaultClienteShouldBeFound("parentescoResponsavel.specified=true");

        // Get all the clienteList where parentescoResponsavel is null
        defaultClienteShouldNotBeFound("parentescoResponsavel.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelFinanceiroIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavelFinanceiro equals to DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldBeFound("parentescoResponsavelFinanceiro.equals=" + DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO);

        // Get all the clienteList where parentescoResponsavelFinanceiro equals to UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldNotBeFound("parentescoResponsavelFinanceiro.equals=" + UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelFinanceiroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavelFinanceiro not equals to DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldNotBeFound("parentescoResponsavelFinanceiro.notEquals=" + DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO);

        // Get all the clienteList where parentescoResponsavelFinanceiro not equals to UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldBeFound("parentescoResponsavelFinanceiro.notEquals=" + UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelFinanceiroIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavelFinanceiro in DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO or UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldBeFound("parentescoResponsavelFinanceiro.in=" + DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO + "," + UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO);

        // Get all the clienteList where parentescoResponsavelFinanceiro equals to UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO
        defaultClienteShouldNotBeFound("parentescoResponsavelFinanceiro.in=" + UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO);
    }

    @Test
    @Transactional
    public void getAllClientesByParentescoResponsavelFinanceiroIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where parentescoResponsavelFinanceiro is not null
        defaultClienteShouldBeFound("parentescoResponsavelFinanceiro.specified=true");

        // Get all the clienteList where parentescoResponsavelFinanceiro is null
        defaultClienteShouldNotBeFound("parentescoResponsavelFinanceiro.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByDataHoraCadastroIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataHoraCadastro equals to DEFAULT_DATA_HORA_CADASTRO
        defaultClienteShouldBeFound("dataHoraCadastro.equals=" + DEFAULT_DATA_HORA_CADASTRO);

        // Get all the clienteList where dataHoraCadastro equals to UPDATED_DATA_HORA_CADASTRO
        defaultClienteShouldNotBeFound("dataHoraCadastro.equals=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataHoraCadastroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataHoraCadastro not equals to DEFAULT_DATA_HORA_CADASTRO
        defaultClienteShouldNotBeFound("dataHoraCadastro.notEquals=" + DEFAULT_DATA_HORA_CADASTRO);

        // Get all the clienteList where dataHoraCadastro not equals to UPDATED_DATA_HORA_CADASTRO
        defaultClienteShouldBeFound("dataHoraCadastro.notEquals=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataHoraCadastroIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataHoraCadastro in DEFAULT_DATA_HORA_CADASTRO or UPDATED_DATA_HORA_CADASTRO
        defaultClienteShouldBeFound("dataHoraCadastro.in=" + DEFAULT_DATA_HORA_CADASTRO + "," + UPDATED_DATA_HORA_CADASTRO);

        // Get all the clienteList where dataHoraCadastro equals to UPDATED_DATA_HORA_CADASTRO
        defaultClienteShouldNotBeFound("dataHoraCadastro.in=" + UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void getAllClientesByDataHoraCadastroIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where dataHoraCadastro is not null
        defaultClienteShouldBeFound("dataHoraCadastro.specified=true");

        // Get all the clienteList where dataHoraCadastro is null
        defaultClienteShouldNotBeFound("dataHoraCadastro.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByAtivoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where ativo equals to DEFAULT_ATIVO
        defaultClienteShouldBeFound("ativo.equals=" + DEFAULT_ATIVO);

        // Get all the clienteList where ativo equals to UPDATED_ATIVO
        defaultClienteShouldNotBeFound("ativo.equals=" + UPDATED_ATIVO);
    }

    @Test
    @Transactional
    public void getAllClientesByAtivoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where ativo not equals to DEFAULT_ATIVO
        defaultClienteShouldNotBeFound("ativo.notEquals=" + DEFAULT_ATIVO);

        // Get all the clienteList where ativo not equals to UPDATED_ATIVO
        defaultClienteShouldBeFound("ativo.notEquals=" + UPDATED_ATIVO);
    }

    @Test
    @Transactional
    public void getAllClientesByAtivoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where ativo in DEFAULT_ATIVO or UPDATED_ATIVO
        defaultClienteShouldBeFound("ativo.in=" + DEFAULT_ATIVO + "," + UPDATED_ATIVO);

        // Get all the clienteList where ativo equals to UPDATED_ATIVO
        defaultClienteShouldNotBeFound("ativo.in=" + UPDATED_ATIVO);
    }

    @Test
    @Transactional
    public void getAllClientesByAtivoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where ativo is not null
        defaultClienteShouldBeFound("ativo.specified=true");

        // Get all the clienteList where ativo is null
        defaultClienteShouldNotBeFound("ativo.specified=false");
    }

    @Test
    @Transactional
    public void getAllClientesByObservacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao equals to DEFAULT_OBSERVACAO
        defaultClienteShouldBeFound("observacao.equals=" + DEFAULT_OBSERVACAO);

        // Get all the clienteList where observacao equals to UPDATED_OBSERVACAO
        defaultClienteShouldNotBeFound("observacao.equals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllClientesByObservacaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao not equals to DEFAULT_OBSERVACAO
        defaultClienteShouldNotBeFound("observacao.notEquals=" + DEFAULT_OBSERVACAO);

        // Get all the clienteList where observacao not equals to UPDATED_OBSERVACAO
        defaultClienteShouldBeFound("observacao.notEquals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllClientesByObservacaoIsInShouldWork() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao in DEFAULT_OBSERVACAO or UPDATED_OBSERVACAO
        defaultClienteShouldBeFound("observacao.in=" + DEFAULT_OBSERVACAO + "," + UPDATED_OBSERVACAO);

        // Get all the clienteList where observacao equals to UPDATED_OBSERVACAO
        defaultClienteShouldNotBeFound("observacao.in=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllClientesByObservacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao is not null
        defaultClienteShouldBeFound("observacao.specified=true");

        // Get all the clienteList where observacao is null
        defaultClienteShouldNotBeFound("observacao.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientesByObservacaoContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao contains DEFAULT_OBSERVACAO
        defaultClienteShouldBeFound("observacao.contains=" + DEFAULT_OBSERVACAO);

        // Get all the clienteList where observacao contains UPDATED_OBSERVACAO
        defaultClienteShouldNotBeFound("observacao.contains=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllClientesByObservacaoNotContainsSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clienteList where observacao does not contain DEFAULT_OBSERVACAO
        defaultClienteShouldNotBeFound("observacao.doesNotContain=" + DEFAULT_OBSERVACAO);

        // Get all the clienteList where observacao does not contain UPDATED_OBSERVACAO
        defaultClienteShouldBeFound("observacao.doesNotContain=" + UPDATED_OBSERVACAO);
    }


    @Test
    @Transactional
    public void getAllClientesByPlanoEstrategicoIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);
        PlanoEstrategico planoEstrategico = PlanoEstrategicoResourceIT.createEntity(em);
        em.persist(planoEstrategico);
        em.flush();
        cliente.addPlanoEstrategico(planoEstrategico);
        clienteRepository.saveAndFlush(cliente);
        Long planoEstrategicoId = planoEstrategico.getId();

        // Get all the clienteList where planoEstrategico equals to planoEstrategicoId
        defaultClienteShouldBeFound("planoEstrategicoId.equals=" + planoEstrategicoId);

        // Get all the clienteList where planoEstrategico equals to planoEstrategicoId + 1
        defaultClienteShouldNotBeFound("planoEstrategicoId.equals=" + (planoEstrategicoId + 1));
    }


    @Test
    @Transactional
    public void getAllClientesByResponsavelIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);
        Cliente responsavel = ClienteResourceIT.createEntity(em);
        em.persist(responsavel);
        em.flush();
        cliente.setResponsavel(responsavel);
        clienteRepository.saveAndFlush(cliente);
        Long responsavelId = responsavel.getId();

        // Get all the clienteList where responsavel equals to responsavelId
        defaultClienteShouldBeFound("responsavelId.equals=" + responsavelId);

        // Get all the clienteList where responsavel equals to responsavelId + 1
        defaultClienteShouldNotBeFound("responsavelId.equals=" + (responsavelId + 1));
    }


    @Test
    @Transactional
    public void getAllClientesByResponsavelFinanceiroIsEqualToSomething() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);
        Cliente responsavelFinanceiro = ClienteResourceIT.createEntity(em);
        em.persist(responsavelFinanceiro);
        em.flush();
        cliente.setResponsavelFinanceiro(responsavelFinanceiro);
        clienteRepository.saveAndFlush(cliente);
        Long responsavelFinanceiroId = responsavelFinanceiro.getId();

        // Get all the clienteList where responsavelFinanceiro equals to responsavelFinanceiroId
        defaultClienteShouldBeFound("responsavelFinanceiroId.equals=" + responsavelFinanceiroId);

        // Get all the clienteList where responsavelFinanceiro equals to responsavelFinanceiroId + 1
        defaultClienteShouldNotBeFound("responsavelFinanceiroId.equals=" + (responsavelFinanceiroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultClienteShouldBeFound(String filter) throws Exception {
        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoCliente").value(hasItem(DEFAULT_TIPO_CLIENTE.toString())))
            .andExpect(jsonPath("$.[*].matricula").value(hasItem(DEFAULT_MATRICULA)))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].escolaridade").value(hasItem(DEFAULT_ESCOLARIDADE.toString())))
            .andExpect(jsonPath("$.[*].convenio").value(hasItem(DEFAULT_CONVENIO.toString())))
            .andExpect(jsonPath("$.[*].numCarteirinhaConvenio").value(hasItem(DEFAULT_NUM_CARTEIRINHA_CONVENIO)))
            .andExpect(jsonPath("$.[*].dataValidadeConvenio").value(hasItem(DEFAULT_DATA_VALIDADE_CONVENIO.toString())))
            .andExpect(jsonPath("$.[*].procedencia").value(hasItem(DEFAULT_PROCEDENCIA.toString())))
            .andExpect(jsonPath("$.[*].profissao").value(hasItem(DEFAULT_PROFISSAO)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].parentescoResponsavel").value(hasItem(DEFAULT_PARENTESCO_RESPONSAVEL.toString())))
            .andExpect(jsonPath("$.[*].parentescoResponsavelFinanceiro").value(hasItem(DEFAULT_PARENTESCO_RESPONSAVEL_FINANCEIRO.toString())))
            .andExpect(jsonPath("$.[*].dataHoraCadastro").value(hasItem(DEFAULT_DATA_HORA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)));

        // Check, that the count call also returns 1
        restClienteMockMvc.perform(get("/api/clientes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultClienteShouldNotBeFound(String filter) throws Exception {
        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restClienteMockMvc.perform(get("/api/clientes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCliente() throws Exception {
        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCliente() throws Exception {
        // Initialize the database
        clienteService.save(cliente);

        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();

        // Update the cliente
        Cliente updatedCliente = clienteRepository.findById(cliente.getId()).get();
        // Disconnect from session so that the updates on updatedCliente are not directly saved in db
        em.detach(updatedCliente);
        updatedCliente
            .tipoCliente(UPDATED_TIPO_CLIENTE)
            .matricula(UPDATED_MATRICULA)
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .dataNascimento(UPDATED_DATA_NASCIMENTO)
            .sexo(UPDATED_SEXO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .escolaridade(UPDATED_ESCOLARIDADE)
            .convenio(UPDATED_CONVENIO)
            .numCarteirinhaConvenio(UPDATED_NUM_CARTEIRINHA_CONVENIO)
            .dataValidadeConvenio(UPDATED_DATA_VALIDADE_CONVENIO)
            .procedencia(UPDATED_PROCEDENCIA)
            .profissao(UPDATED_PROFISSAO)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .email(UPDATED_EMAIL)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .parentescoResponsavel(UPDATED_PARENTESCO_RESPONSAVEL)
            .parentescoResponsavelFinanceiro(UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO)
            .ativo(UPDATED_ATIVO)
            .observacao(UPDATED_OBSERVACAO);

        restClienteMockMvc.perform(put("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCliente)))
            .andExpect(status().isOk());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
        Cliente testCliente = clienteList.get(clienteList.size() - 1);
        assertThat(testCliente.getTipoCliente()).isEqualTo(UPDATED_TIPO_CLIENTE);
        assertThat(testCliente.getMatricula()).isEqualTo(UPDATED_MATRICULA);
        assertThat(testCliente.getNomeCompleto()).isEqualTo(UPDATED_NOME_COMPLETO);
        assertThat(testCliente.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testCliente.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testCliente.getDataNascimento()).isEqualTo(UPDATED_DATA_NASCIMENTO);
        assertThat(testCliente.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testCliente.getEstadoCivil()).isEqualTo(UPDATED_ESTADO_CIVIL);
        assertThat(testCliente.getEscolaridade()).isEqualTo(UPDATED_ESCOLARIDADE);
        assertThat(testCliente.getConvenio()).isEqualTo(UPDATED_CONVENIO);
        assertThat(testCliente.getNumCarteirinhaConvenio()).isEqualTo(UPDATED_NUM_CARTEIRINHA_CONVENIO);
        assertThat(testCliente.getDataValidadeConvenio()).isEqualTo(UPDATED_DATA_VALIDADE_CONVENIO);
        assertThat(testCliente.getProcedencia()).isEqualTo(UPDATED_PROCEDENCIA);
        assertThat(testCliente.getProfissao()).isEqualTo(UPDATED_PROFISSAO);
        assertThat(testCliente.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testCliente.getRg()).isEqualTo(UPDATED_RG);
        assertThat(testCliente.getTelefone1()).isEqualTo(UPDATED_TELEFONE_1);
        assertThat(testCliente.getTelefone2()).isEqualTo(UPDATED_TELEFONE_2);
        assertThat(testCliente.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCliente.getLogradouroNome()).isEqualTo(UPDATED_LOGRADOURO_NOME);
        assertThat(testCliente.getLogradouroNumero()).isEqualTo(UPDATED_LOGRADOURO_NUMERO);
        assertThat(testCliente.getLogradouroComplemento()).isEqualTo(UPDATED_LOGRADOURO_COMPLEMENTO);
        assertThat(testCliente.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testCliente.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testCliente.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testCliente.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCliente.getParentescoResponsavel()).isEqualTo(UPDATED_PARENTESCO_RESPONSAVEL);
        assertThat(testCliente.getParentescoResponsavelFinanceiro()).isEqualTo(UPDATED_PARENTESCO_RESPONSAVEL_FINANCEIRO);
        assertThat(testCliente.getDataHoraCadastro()).isEqualTo(UPDATED_DATA_HORA_CADASTRO);
        assertThat(testCliente.isAtivo()).isEqualTo(UPDATED_ATIVO);
        assertThat(testCliente.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCliente() throws Exception {
        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClienteMockMvc.perform(put("/api/clientes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCliente() throws Exception {
        // Initialize the database
        clienteService.save(cliente);

        int databaseSizeBeforeDelete = clienteRepository.findAll().size();

        // Delete the cliente
        restClienteMockMvc.perform(delete("/api/clientes/{id}", cliente.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
