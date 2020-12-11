package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.ClinicaAdaptrojhApp;
import br.com.clinicaadaptro.app.domain.Fornecedor;
import br.com.clinicaadaptro.app.repository.FornecedorRepository;
import br.com.clinicaadaptro.app.service.FornecedorService;
import br.com.clinicaadaptro.app.service.dto.FornecedorCriteria;
import br.com.clinicaadaptro.app.service.FornecedorQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.clinicaadaptro.app.domain.enumeration.TipoPessoa;
import br.com.clinicaadaptro.app.domain.enumeration.Estado;
/**
 * Integration tests for the {@link FornecedorResource} REST controller.
 */
@SpringBootTest(classes = ClinicaAdaptrojhApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FornecedorResourceIT {

    private static final TipoPessoa DEFAULT_TIPO_PESSOA = TipoPessoa.F;
    private static final TipoPessoa UPDATED_TIPO_PESSOA = TipoPessoa.J;

    private static final String DEFAULT_NUMERO_CPF = "54726423942";
    private static final String UPDATED_NUMERO_CPF = "25466845517";

    private static final String DEFAULT_NUMERO_CNPJ = "11333344959199";
    private static final String UPDATED_NUMERO_CNPJ = "65127336386104";

    private static final String DEFAULT_NUMERO_INSCRICAO_ESTADUAL = "AAAAAAAAA";
    private static final String UPDATED_NUMERO_INSCRICAO_ESTADUAL = "BBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_FANTASIA = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "W/0A@vm<.mlC";
    private static final String UPDATED_EMAIL = "=\\@0OZ.cM$RbK";

    private static final String DEFAULT_TELEFONE_1 = " 71462243";
    private static final String UPDATED_TELEFONE_1 = "(861)8857-4954";

    private static final String DEFAULT_TELEFONE_2 = " 6525754";
    private static final String UPDATED_TELEFONE_2 = "26 4420-5639";

    private static final String DEFAULT_LOGRADOURO_NOME = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGRADOURO_NUMERO = "AAAA";
    private static final String UPDATED_LOGRADOURO_NUMERO = "BBBB";

    private static final String DEFAULT_LOGRADOURO_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "88672-389";
    private static final String UPDATED_CEP = "50785-109";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.AC;
    private static final Estado UPDATED_ESTADO = Estado.AL;

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private FornecedorQueryService fornecedorQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFornecedorMockMvc;

    private Fornecedor fornecedor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fornecedor createEntity(EntityManager em) {
        Fornecedor fornecedor = new Fornecedor()
            .tipoPessoa(DEFAULT_TIPO_PESSOA)
            .numeroCPF(DEFAULT_NUMERO_CPF)
            .numeroCNPJ(DEFAULT_NUMERO_CNPJ)
            .numeroInscricaoEstadual(DEFAULT_NUMERO_INSCRICAO_ESTADUAL)
            .nome(DEFAULT_NOME)
            .nomeFantasia(DEFAULT_NOME_FANTASIA)
            .email(DEFAULT_EMAIL)
            .telefone1(DEFAULT_TELEFONE_1)
            .telefone2(DEFAULT_TELEFONE_2)
            .logradouroNome(DEFAULT_LOGRADOURO_NOME)
            .logradouroNumero(DEFAULT_LOGRADOURO_NUMERO)
            .logradouroComplemento(DEFAULT_LOGRADOURO_COMPLEMENTO)
            .bairro(DEFAULT_BAIRRO)
            .cep(DEFAULT_CEP)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .observacao(DEFAULT_OBSERVACAO);
        return fornecedor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fornecedor createUpdatedEntity(EntityManager em) {
        Fornecedor fornecedor = new Fornecedor()
            .tipoPessoa(UPDATED_TIPO_PESSOA)
            .numeroCPF(UPDATED_NUMERO_CPF)
            .numeroCNPJ(UPDATED_NUMERO_CNPJ)
            .numeroInscricaoEstadual(UPDATED_NUMERO_INSCRICAO_ESTADUAL)
            .nome(UPDATED_NOME)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .email(UPDATED_EMAIL)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .observacao(UPDATED_OBSERVACAO);
        return fornecedor;
    }

    @BeforeEach
    public void initTest() {
        fornecedor = createEntity(em);
    }

    @Test
    @Transactional
    public void createFornecedor() throws Exception {
        int databaseSizeBeforeCreate = fornecedorRepository.findAll().size();
        // Create the Fornecedor
        restFornecedorMockMvc.perform(post("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fornecedor)))
            .andExpect(status().isCreated());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeCreate + 1);
        Fornecedor testFornecedor = fornecedorList.get(fornecedorList.size() - 1);
        assertThat(testFornecedor.getTipoPessoa()).isEqualTo(DEFAULT_TIPO_PESSOA);
        assertThat(testFornecedor.getNumeroCPF()).isEqualTo(DEFAULT_NUMERO_CPF);
        assertThat(testFornecedor.getNumeroCNPJ()).isEqualTo(DEFAULT_NUMERO_CNPJ);
        assertThat(testFornecedor.getNumeroInscricaoEstadual()).isEqualTo(DEFAULT_NUMERO_INSCRICAO_ESTADUAL);
        assertThat(testFornecedor.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testFornecedor.getNomeFantasia()).isEqualTo(DEFAULT_NOME_FANTASIA);
        assertThat(testFornecedor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFornecedor.getTelefone1()).isEqualTo(DEFAULT_TELEFONE_1);
        assertThat(testFornecedor.getTelefone2()).isEqualTo(DEFAULT_TELEFONE_2);
        assertThat(testFornecedor.getLogradouroNome()).isEqualTo(DEFAULT_LOGRADOURO_NOME);
        assertThat(testFornecedor.getLogradouroNumero()).isEqualTo(DEFAULT_LOGRADOURO_NUMERO);
        assertThat(testFornecedor.getLogradouroComplemento()).isEqualTo(DEFAULT_LOGRADOURO_COMPLEMENTO);
        assertThat(testFornecedor.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testFornecedor.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testFornecedor.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testFornecedor.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testFornecedor.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
    }

    @Test
    @Transactional
    public void createFornecedorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fornecedorRepository.findAll().size();

        // Create the Fornecedor with an existing ID
        fornecedor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFornecedorMockMvc.perform(post("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fornecedor)))
            .andExpect(status().isBadRequest());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTipoPessoaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setTipoPessoa(null);

        // Create the Fornecedor, which fails.


        restFornecedorMockMvc.perform(post("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fornecedor)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setNome(null);

        // Create the Fornecedor, which fails.


        restFornecedorMockMvc.perform(post("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fornecedor)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFornecedors() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fornecedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPessoa").value(hasItem(DEFAULT_TIPO_PESSOA.toString())))
            .andExpect(jsonPath("$.[*].numeroCPF").value(hasItem(DEFAULT_NUMERO_CPF)))
            .andExpect(jsonPath("$.[*].numeroCNPJ").value(hasItem(DEFAULT_NUMERO_CNPJ)))
            .andExpect(jsonPath("$.[*].numeroInscricaoEstadual").value(hasItem(DEFAULT_NUMERO_INSCRICAO_ESTADUAL)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)));
    }
    
    @Test
    @Transactional
    public void getFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", fornecedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fornecedor.getId().intValue()))
            .andExpect(jsonPath("$.tipoPessoa").value(DEFAULT_TIPO_PESSOA.toString()))
            .andExpect(jsonPath("$.numeroCPF").value(DEFAULT_NUMERO_CPF))
            .andExpect(jsonPath("$.numeroCNPJ").value(DEFAULT_NUMERO_CNPJ))
            .andExpect(jsonPath("$.numeroInscricaoEstadual").value(DEFAULT_NUMERO_INSCRICAO_ESTADUAL))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.nomeFantasia").value(DEFAULT_NOME_FANTASIA))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telefone1").value(DEFAULT_TELEFONE_1))
            .andExpect(jsonPath("$.telefone2").value(DEFAULT_TELEFONE_2))
            .andExpect(jsonPath("$.logradouroNome").value(DEFAULT_LOGRADOURO_NOME))
            .andExpect(jsonPath("$.logradouroNumero").value(DEFAULT_LOGRADOURO_NUMERO))
            .andExpect(jsonPath("$.logradouroComplemento").value(DEFAULT_LOGRADOURO_COMPLEMENTO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO));
    }


    @Test
    @Transactional
    public void getFornecedorsByIdFiltering() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        Long id = fornecedor.getId();

        defaultFornecedorShouldBeFound("id.equals=" + id);
        defaultFornecedorShouldNotBeFound("id.notEquals=" + id);

        defaultFornecedorShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFornecedorShouldNotBeFound("id.greaterThan=" + id);

        defaultFornecedorShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFornecedorShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByTipoPessoaIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where tipoPessoa equals to DEFAULT_TIPO_PESSOA
        defaultFornecedorShouldBeFound("tipoPessoa.equals=" + DEFAULT_TIPO_PESSOA);

        // Get all the fornecedorList where tipoPessoa equals to UPDATED_TIPO_PESSOA
        defaultFornecedorShouldNotBeFound("tipoPessoa.equals=" + UPDATED_TIPO_PESSOA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTipoPessoaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where tipoPessoa not equals to DEFAULT_TIPO_PESSOA
        defaultFornecedorShouldNotBeFound("tipoPessoa.notEquals=" + DEFAULT_TIPO_PESSOA);

        // Get all the fornecedorList where tipoPessoa not equals to UPDATED_TIPO_PESSOA
        defaultFornecedorShouldBeFound("tipoPessoa.notEquals=" + UPDATED_TIPO_PESSOA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTipoPessoaIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where tipoPessoa in DEFAULT_TIPO_PESSOA or UPDATED_TIPO_PESSOA
        defaultFornecedorShouldBeFound("tipoPessoa.in=" + DEFAULT_TIPO_PESSOA + "," + UPDATED_TIPO_PESSOA);

        // Get all the fornecedorList where tipoPessoa equals to UPDATED_TIPO_PESSOA
        defaultFornecedorShouldNotBeFound("tipoPessoa.in=" + UPDATED_TIPO_PESSOA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTipoPessoaIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where tipoPessoa is not null
        defaultFornecedorShouldBeFound("tipoPessoa.specified=true");

        // Get all the fornecedorList where tipoPessoa is null
        defaultFornecedorShouldNotBeFound("tipoPessoa.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF equals to DEFAULT_NUMERO_CPF
        defaultFornecedorShouldBeFound("numeroCPF.equals=" + DEFAULT_NUMERO_CPF);

        // Get all the fornecedorList where numeroCPF equals to UPDATED_NUMERO_CPF
        defaultFornecedorShouldNotBeFound("numeroCPF.equals=" + UPDATED_NUMERO_CPF);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF not equals to DEFAULT_NUMERO_CPF
        defaultFornecedorShouldNotBeFound("numeroCPF.notEquals=" + DEFAULT_NUMERO_CPF);

        // Get all the fornecedorList where numeroCPF not equals to UPDATED_NUMERO_CPF
        defaultFornecedorShouldBeFound("numeroCPF.notEquals=" + UPDATED_NUMERO_CPF);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF in DEFAULT_NUMERO_CPF or UPDATED_NUMERO_CPF
        defaultFornecedorShouldBeFound("numeroCPF.in=" + DEFAULT_NUMERO_CPF + "," + UPDATED_NUMERO_CPF);

        // Get all the fornecedorList where numeroCPF equals to UPDATED_NUMERO_CPF
        defaultFornecedorShouldNotBeFound("numeroCPF.in=" + UPDATED_NUMERO_CPF);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF is not null
        defaultFornecedorShouldBeFound("numeroCPF.specified=true");

        // Get all the fornecedorList where numeroCPF is null
        defaultFornecedorShouldNotBeFound("numeroCPF.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF contains DEFAULT_NUMERO_CPF
        defaultFornecedorShouldBeFound("numeroCPF.contains=" + DEFAULT_NUMERO_CPF);

        // Get all the fornecedorList where numeroCPF contains UPDATED_NUMERO_CPF
        defaultFornecedorShouldNotBeFound("numeroCPF.contains=" + UPDATED_NUMERO_CPF);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCPFNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCPF does not contain DEFAULT_NUMERO_CPF
        defaultFornecedorShouldNotBeFound("numeroCPF.doesNotContain=" + DEFAULT_NUMERO_CPF);

        // Get all the fornecedorList where numeroCPF does not contain UPDATED_NUMERO_CPF
        defaultFornecedorShouldBeFound("numeroCPF.doesNotContain=" + UPDATED_NUMERO_CPF);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ equals to DEFAULT_NUMERO_CNPJ
        defaultFornecedorShouldBeFound("numeroCNPJ.equals=" + DEFAULT_NUMERO_CNPJ);

        // Get all the fornecedorList where numeroCNPJ equals to UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldNotBeFound("numeroCNPJ.equals=" + UPDATED_NUMERO_CNPJ);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ not equals to DEFAULT_NUMERO_CNPJ
        defaultFornecedorShouldNotBeFound("numeroCNPJ.notEquals=" + DEFAULT_NUMERO_CNPJ);

        // Get all the fornecedorList where numeroCNPJ not equals to UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldBeFound("numeroCNPJ.notEquals=" + UPDATED_NUMERO_CNPJ);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ in DEFAULT_NUMERO_CNPJ or UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldBeFound("numeroCNPJ.in=" + DEFAULT_NUMERO_CNPJ + "," + UPDATED_NUMERO_CNPJ);

        // Get all the fornecedorList where numeroCNPJ equals to UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldNotBeFound("numeroCNPJ.in=" + UPDATED_NUMERO_CNPJ);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ is not null
        defaultFornecedorShouldBeFound("numeroCNPJ.specified=true");

        // Get all the fornecedorList where numeroCNPJ is null
        defaultFornecedorShouldNotBeFound("numeroCNPJ.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ contains DEFAULT_NUMERO_CNPJ
        defaultFornecedorShouldBeFound("numeroCNPJ.contains=" + DEFAULT_NUMERO_CNPJ);

        // Get all the fornecedorList where numeroCNPJ contains UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldNotBeFound("numeroCNPJ.contains=" + UPDATED_NUMERO_CNPJ);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroCNPJNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroCNPJ does not contain DEFAULT_NUMERO_CNPJ
        defaultFornecedorShouldNotBeFound("numeroCNPJ.doesNotContain=" + DEFAULT_NUMERO_CNPJ);

        // Get all the fornecedorList where numeroCNPJ does not contain UPDATED_NUMERO_CNPJ
        defaultFornecedorShouldBeFound("numeroCNPJ.doesNotContain=" + UPDATED_NUMERO_CNPJ);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual equals to DEFAULT_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.equals=" + DEFAULT_NUMERO_INSCRICAO_ESTADUAL);

        // Get all the fornecedorList where numeroInscricaoEstadual equals to UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.equals=" + UPDATED_NUMERO_INSCRICAO_ESTADUAL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual not equals to DEFAULT_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.notEquals=" + DEFAULT_NUMERO_INSCRICAO_ESTADUAL);

        // Get all the fornecedorList where numeroInscricaoEstadual not equals to UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.notEquals=" + UPDATED_NUMERO_INSCRICAO_ESTADUAL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual in DEFAULT_NUMERO_INSCRICAO_ESTADUAL or UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.in=" + DEFAULT_NUMERO_INSCRICAO_ESTADUAL + "," + UPDATED_NUMERO_INSCRICAO_ESTADUAL);

        // Get all the fornecedorList where numeroInscricaoEstadual equals to UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.in=" + UPDATED_NUMERO_INSCRICAO_ESTADUAL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual is not null
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.specified=true");

        // Get all the fornecedorList where numeroInscricaoEstadual is null
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual contains DEFAULT_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.contains=" + DEFAULT_NUMERO_INSCRICAO_ESTADUAL);

        // Get all the fornecedorList where numeroInscricaoEstadual contains UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.contains=" + UPDATED_NUMERO_INSCRICAO_ESTADUAL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumeroInscricaoEstadualNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numeroInscricaoEstadual does not contain DEFAULT_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldNotBeFound("numeroInscricaoEstadual.doesNotContain=" + DEFAULT_NUMERO_INSCRICAO_ESTADUAL);

        // Get all the fornecedorList where numeroInscricaoEstadual does not contain UPDATED_NUMERO_INSCRICAO_ESTADUAL
        defaultFornecedorShouldBeFound("numeroInscricaoEstadual.doesNotContain=" + UPDATED_NUMERO_INSCRICAO_ESTADUAL);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome equals to DEFAULT_NOME
        defaultFornecedorShouldBeFound("nome.equals=" + DEFAULT_NOME);

        // Get all the fornecedorList where nome equals to UPDATED_NOME
        defaultFornecedorShouldNotBeFound("nome.equals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome not equals to DEFAULT_NOME
        defaultFornecedorShouldNotBeFound("nome.notEquals=" + DEFAULT_NOME);

        // Get all the fornecedorList where nome not equals to UPDATED_NOME
        defaultFornecedorShouldBeFound("nome.notEquals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome in DEFAULT_NOME or UPDATED_NOME
        defaultFornecedorShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);

        // Get all the fornecedorList where nome equals to UPDATED_NOME
        defaultFornecedorShouldNotBeFound("nome.in=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome is not null
        defaultFornecedorShouldBeFound("nome.specified=true");

        // Get all the fornecedorList where nome is null
        defaultFornecedorShouldNotBeFound("nome.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByNomeContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome contains DEFAULT_NOME
        defaultFornecedorShouldBeFound("nome.contains=" + DEFAULT_NOME);

        // Get all the fornecedorList where nome contains UPDATED_NOME
        defaultFornecedorShouldNotBeFound("nome.contains=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nome does not contain DEFAULT_NOME
        defaultFornecedorShouldNotBeFound("nome.doesNotContain=" + DEFAULT_NOME);

        // Get all the fornecedorList where nome does not contain UPDATED_NOME
        defaultFornecedorShouldBeFound("nome.doesNotContain=" + UPDATED_NOME);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia equals to DEFAULT_NOME_FANTASIA
        defaultFornecedorShouldBeFound("nomeFantasia.equals=" + DEFAULT_NOME_FANTASIA);

        // Get all the fornecedorList where nomeFantasia equals to UPDATED_NOME_FANTASIA
        defaultFornecedorShouldNotBeFound("nomeFantasia.equals=" + UPDATED_NOME_FANTASIA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia not equals to DEFAULT_NOME_FANTASIA
        defaultFornecedorShouldNotBeFound("nomeFantasia.notEquals=" + DEFAULT_NOME_FANTASIA);

        // Get all the fornecedorList where nomeFantasia not equals to UPDATED_NOME_FANTASIA
        defaultFornecedorShouldBeFound("nomeFantasia.notEquals=" + UPDATED_NOME_FANTASIA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia in DEFAULT_NOME_FANTASIA or UPDATED_NOME_FANTASIA
        defaultFornecedorShouldBeFound("nomeFantasia.in=" + DEFAULT_NOME_FANTASIA + "," + UPDATED_NOME_FANTASIA);

        // Get all the fornecedorList where nomeFantasia equals to UPDATED_NOME_FANTASIA
        defaultFornecedorShouldNotBeFound("nomeFantasia.in=" + UPDATED_NOME_FANTASIA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia is not null
        defaultFornecedorShouldBeFound("nomeFantasia.specified=true");

        // Get all the fornecedorList where nomeFantasia is null
        defaultFornecedorShouldNotBeFound("nomeFantasia.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia contains DEFAULT_NOME_FANTASIA
        defaultFornecedorShouldBeFound("nomeFantasia.contains=" + DEFAULT_NOME_FANTASIA);

        // Get all the fornecedorList where nomeFantasia contains UPDATED_NOME_FANTASIA
        defaultFornecedorShouldNotBeFound("nomeFantasia.contains=" + UPDATED_NOME_FANTASIA);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNomeFantasiaNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nomeFantasia does not contain DEFAULT_NOME_FANTASIA
        defaultFornecedorShouldNotBeFound("nomeFantasia.doesNotContain=" + DEFAULT_NOME_FANTASIA);

        // Get all the fornecedorList where nomeFantasia does not contain UPDATED_NOME_FANTASIA
        defaultFornecedorShouldBeFound("nomeFantasia.doesNotContain=" + UPDATED_NOME_FANTASIA);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email equals to DEFAULT_EMAIL
        defaultFornecedorShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the fornecedorList where email equals to UPDATED_EMAIL
        defaultFornecedorShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email not equals to DEFAULT_EMAIL
        defaultFornecedorShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the fornecedorList where email not equals to UPDATED_EMAIL
        defaultFornecedorShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultFornecedorShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the fornecedorList where email equals to UPDATED_EMAIL
        defaultFornecedorShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email is not null
        defaultFornecedorShouldBeFound("email.specified=true");

        // Get all the fornecedorList where email is null
        defaultFornecedorShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByEmailContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email contains DEFAULT_EMAIL
        defaultFornecedorShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the fornecedorList where email contains UPDATED_EMAIL
        defaultFornecedorShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email does not contain DEFAULT_EMAIL
        defaultFornecedorShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the fornecedorList where email does not contain UPDATED_EMAIL
        defaultFornecedorShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByTelefone1IsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 equals to DEFAULT_TELEFONE_1
        defaultFornecedorShouldBeFound("telefone1.equals=" + DEFAULT_TELEFONE_1);

        // Get all the fornecedorList where telefone1 equals to UPDATED_TELEFONE_1
        defaultFornecedorShouldNotBeFound("telefone1.equals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 not equals to DEFAULT_TELEFONE_1
        defaultFornecedorShouldNotBeFound("telefone1.notEquals=" + DEFAULT_TELEFONE_1);

        // Get all the fornecedorList where telefone1 not equals to UPDATED_TELEFONE_1
        defaultFornecedorShouldBeFound("telefone1.notEquals=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone1IsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 in DEFAULT_TELEFONE_1 or UPDATED_TELEFONE_1
        defaultFornecedorShouldBeFound("telefone1.in=" + DEFAULT_TELEFONE_1 + "," + UPDATED_TELEFONE_1);

        // Get all the fornecedorList where telefone1 equals to UPDATED_TELEFONE_1
        defaultFornecedorShouldNotBeFound("telefone1.in=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone1IsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 is not null
        defaultFornecedorShouldBeFound("telefone1.specified=true");

        // Get all the fornecedorList where telefone1 is null
        defaultFornecedorShouldNotBeFound("telefone1.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByTelefone1ContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 contains DEFAULT_TELEFONE_1
        defaultFornecedorShouldBeFound("telefone1.contains=" + DEFAULT_TELEFONE_1);

        // Get all the fornecedorList where telefone1 contains UPDATED_TELEFONE_1
        defaultFornecedorShouldNotBeFound("telefone1.contains=" + UPDATED_TELEFONE_1);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone1NotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone1 does not contain DEFAULT_TELEFONE_1
        defaultFornecedorShouldNotBeFound("telefone1.doesNotContain=" + DEFAULT_TELEFONE_1);

        // Get all the fornecedorList where telefone1 does not contain UPDATED_TELEFONE_1
        defaultFornecedorShouldBeFound("telefone1.doesNotContain=" + UPDATED_TELEFONE_1);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByTelefone2IsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 equals to DEFAULT_TELEFONE_2
        defaultFornecedorShouldBeFound("telefone2.equals=" + DEFAULT_TELEFONE_2);

        // Get all the fornecedorList where telefone2 equals to UPDATED_TELEFONE_2
        defaultFornecedorShouldNotBeFound("telefone2.equals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 not equals to DEFAULT_TELEFONE_2
        defaultFornecedorShouldNotBeFound("telefone2.notEquals=" + DEFAULT_TELEFONE_2);

        // Get all the fornecedorList where telefone2 not equals to UPDATED_TELEFONE_2
        defaultFornecedorShouldBeFound("telefone2.notEquals=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone2IsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 in DEFAULT_TELEFONE_2 or UPDATED_TELEFONE_2
        defaultFornecedorShouldBeFound("telefone2.in=" + DEFAULT_TELEFONE_2 + "," + UPDATED_TELEFONE_2);

        // Get all the fornecedorList where telefone2 equals to UPDATED_TELEFONE_2
        defaultFornecedorShouldNotBeFound("telefone2.in=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone2IsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 is not null
        defaultFornecedorShouldBeFound("telefone2.specified=true");

        // Get all the fornecedorList where telefone2 is null
        defaultFornecedorShouldNotBeFound("telefone2.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByTelefone2ContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 contains DEFAULT_TELEFONE_2
        defaultFornecedorShouldBeFound("telefone2.contains=" + DEFAULT_TELEFONE_2);

        // Get all the fornecedorList where telefone2 contains UPDATED_TELEFONE_2
        defaultFornecedorShouldNotBeFound("telefone2.contains=" + UPDATED_TELEFONE_2);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByTelefone2NotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where telefone2 does not contain DEFAULT_TELEFONE_2
        defaultFornecedorShouldNotBeFound("telefone2.doesNotContain=" + DEFAULT_TELEFONE_2);

        // Get all the fornecedorList where telefone2 does not contain UPDATED_TELEFONE_2
        defaultFornecedorShouldBeFound("telefone2.doesNotContain=" + UPDATED_TELEFONE_2);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome equals to DEFAULT_LOGRADOURO_NOME
        defaultFornecedorShouldBeFound("logradouroNome.equals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the fornecedorList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldNotBeFound("logradouroNome.equals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome not equals to DEFAULT_LOGRADOURO_NOME
        defaultFornecedorShouldNotBeFound("logradouroNome.notEquals=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the fornecedorList where logradouroNome not equals to UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldBeFound("logradouroNome.notEquals=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome in DEFAULT_LOGRADOURO_NOME or UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldBeFound("logradouroNome.in=" + DEFAULT_LOGRADOURO_NOME + "," + UPDATED_LOGRADOURO_NOME);

        // Get all the fornecedorList where logradouroNome equals to UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldNotBeFound("logradouroNome.in=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome is not null
        defaultFornecedorShouldBeFound("logradouroNome.specified=true");

        // Get all the fornecedorList where logradouroNome is null
        defaultFornecedorShouldNotBeFound("logradouroNome.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome contains DEFAULT_LOGRADOURO_NOME
        defaultFornecedorShouldBeFound("logradouroNome.contains=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the fornecedorList where logradouroNome contains UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldNotBeFound("logradouroNome.contains=" + UPDATED_LOGRADOURO_NOME);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNomeNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNome does not contain DEFAULT_LOGRADOURO_NOME
        defaultFornecedorShouldNotBeFound("logradouroNome.doesNotContain=" + DEFAULT_LOGRADOURO_NOME);

        // Get all the fornecedorList where logradouroNome does not contain UPDATED_LOGRADOURO_NOME
        defaultFornecedorShouldBeFound("logradouroNome.doesNotContain=" + UPDATED_LOGRADOURO_NOME);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero equals to DEFAULT_LOGRADOURO_NUMERO
        defaultFornecedorShouldBeFound("logradouroNumero.equals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the fornecedorList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldNotBeFound("logradouroNumero.equals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero not equals to DEFAULT_LOGRADOURO_NUMERO
        defaultFornecedorShouldNotBeFound("logradouroNumero.notEquals=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the fornecedorList where logradouroNumero not equals to UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldBeFound("logradouroNumero.notEquals=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero in DEFAULT_LOGRADOURO_NUMERO or UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldBeFound("logradouroNumero.in=" + DEFAULT_LOGRADOURO_NUMERO + "," + UPDATED_LOGRADOURO_NUMERO);

        // Get all the fornecedorList where logradouroNumero equals to UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldNotBeFound("logradouroNumero.in=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero is not null
        defaultFornecedorShouldBeFound("logradouroNumero.specified=true");

        // Get all the fornecedorList where logradouroNumero is null
        defaultFornecedorShouldNotBeFound("logradouroNumero.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero contains DEFAULT_LOGRADOURO_NUMERO
        defaultFornecedorShouldBeFound("logradouroNumero.contains=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the fornecedorList where logradouroNumero contains UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldNotBeFound("logradouroNumero.contains=" + UPDATED_LOGRADOURO_NUMERO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroNumeroNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroNumero does not contain DEFAULT_LOGRADOURO_NUMERO
        defaultFornecedorShouldNotBeFound("logradouroNumero.doesNotContain=" + DEFAULT_LOGRADOURO_NUMERO);

        // Get all the fornecedorList where logradouroNumero does not contain UPDATED_LOGRADOURO_NUMERO
        defaultFornecedorShouldBeFound("logradouroNumero.doesNotContain=" + UPDATED_LOGRADOURO_NUMERO);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldBeFound("logradouroComplemento.equals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the fornecedorList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldNotBeFound("logradouroComplemento.equals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento not equals to DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldNotBeFound("logradouroComplemento.notEquals=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the fornecedorList where logradouroComplemento not equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldBeFound("logradouroComplemento.notEquals=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento in DEFAULT_LOGRADOURO_COMPLEMENTO or UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldBeFound("logradouroComplemento.in=" + DEFAULT_LOGRADOURO_COMPLEMENTO + "," + UPDATED_LOGRADOURO_COMPLEMENTO);

        // Get all the fornecedorList where logradouroComplemento equals to UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldNotBeFound("logradouroComplemento.in=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento is not null
        defaultFornecedorShouldBeFound("logradouroComplemento.specified=true");

        // Get all the fornecedorList where logradouroComplemento is null
        defaultFornecedorShouldNotBeFound("logradouroComplemento.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento contains DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldBeFound("logradouroComplemento.contains=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the fornecedorList where logradouroComplemento contains UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldNotBeFound("logradouroComplemento.contains=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByLogradouroComplementoNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where logradouroComplemento does not contain DEFAULT_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldNotBeFound("logradouroComplemento.doesNotContain=" + DEFAULT_LOGRADOURO_COMPLEMENTO);

        // Get all the fornecedorList where logradouroComplemento does not contain UPDATED_LOGRADOURO_COMPLEMENTO
        defaultFornecedorShouldBeFound("logradouroComplemento.doesNotContain=" + UPDATED_LOGRADOURO_COMPLEMENTO);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro equals to DEFAULT_BAIRRO
        defaultFornecedorShouldBeFound("bairro.equals=" + DEFAULT_BAIRRO);

        // Get all the fornecedorList where bairro equals to UPDATED_BAIRRO
        defaultFornecedorShouldNotBeFound("bairro.equals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro not equals to DEFAULT_BAIRRO
        defaultFornecedorShouldNotBeFound("bairro.notEquals=" + DEFAULT_BAIRRO);

        // Get all the fornecedorList where bairro not equals to UPDATED_BAIRRO
        defaultFornecedorShouldBeFound("bairro.notEquals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByBairroIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro in DEFAULT_BAIRRO or UPDATED_BAIRRO
        defaultFornecedorShouldBeFound("bairro.in=" + DEFAULT_BAIRRO + "," + UPDATED_BAIRRO);

        // Get all the fornecedorList where bairro equals to UPDATED_BAIRRO
        defaultFornecedorShouldNotBeFound("bairro.in=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro is not null
        defaultFornecedorShouldBeFound("bairro.specified=true");

        // Get all the fornecedorList where bairro is null
        defaultFornecedorShouldNotBeFound("bairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByBairroContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro contains DEFAULT_BAIRRO
        defaultFornecedorShouldBeFound("bairro.contains=" + DEFAULT_BAIRRO);

        // Get all the fornecedorList where bairro contains UPDATED_BAIRRO
        defaultFornecedorShouldNotBeFound("bairro.contains=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByBairroNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where bairro does not contain DEFAULT_BAIRRO
        defaultFornecedorShouldNotBeFound("bairro.doesNotContain=" + DEFAULT_BAIRRO);

        // Get all the fornecedorList where bairro does not contain UPDATED_BAIRRO
        defaultFornecedorShouldBeFound("bairro.doesNotContain=" + UPDATED_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByCepIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep equals to DEFAULT_CEP
        defaultFornecedorShouldBeFound("cep.equals=" + DEFAULT_CEP);

        // Get all the fornecedorList where cep equals to UPDATED_CEP
        defaultFornecedorShouldNotBeFound("cep.equals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep not equals to DEFAULT_CEP
        defaultFornecedorShouldNotBeFound("cep.notEquals=" + DEFAULT_CEP);

        // Get all the fornecedorList where cep not equals to UPDATED_CEP
        defaultFornecedorShouldBeFound("cep.notEquals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCepIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep in DEFAULT_CEP or UPDATED_CEP
        defaultFornecedorShouldBeFound("cep.in=" + DEFAULT_CEP + "," + UPDATED_CEP);

        // Get all the fornecedorList where cep equals to UPDATED_CEP
        defaultFornecedorShouldNotBeFound("cep.in=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep is not null
        defaultFornecedorShouldBeFound("cep.specified=true");

        // Get all the fornecedorList where cep is null
        defaultFornecedorShouldNotBeFound("cep.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByCepContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep contains DEFAULT_CEP
        defaultFornecedorShouldBeFound("cep.contains=" + DEFAULT_CEP);

        // Get all the fornecedorList where cep contains UPDATED_CEP
        defaultFornecedorShouldNotBeFound("cep.contains=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCepNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cep does not contain DEFAULT_CEP
        defaultFornecedorShouldNotBeFound("cep.doesNotContain=" + DEFAULT_CEP);

        // Get all the fornecedorList where cep does not contain UPDATED_CEP
        defaultFornecedorShouldBeFound("cep.doesNotContain=" + UPDATED_CEP);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade equals to DEFAULT_CIDADE
        defaultFornecedorShouldBeFound("cidade.equals=" + DEFAULT_CIDADE);

        // Get all the fornecedorList where cidade equals to UPDATED_CIDADE
        defaultFornecedorShouldNotBeFound("cidade.equals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade not equals to DEFAULT_CIDADE
        defaultFornecedorShouldNotBeFound("cidade.notEquals=" + DEFAULT_CIDADE);

        // Get all the fornecedorList where cidade not equals to UPDATED_CIDADE
        defaultFornecedorShouldBeFound("cidade.notEquals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade in DEFAULT_CIDADE or UPDATED_CIDADE
        defaultFornecedorShouldBeFound("cidade.in=" + DEFAULT_CIDADE + "," + UPDATED_CIDADE);

        // Get all the fornecedorList where cidade equals to UPDATED_CIDADE
        defaultFornecedorShouldNotBeFound("cidade.in=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade is not null
        defaultFornecedorShouldBeFound("cidade.specified=true");

        // Get all the fornecedorList where cidade is null
        defaultFornecedorShouldNotBeFound("cidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByCidadeContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade contains DEFAULT_CIDADE
        defaultFornecedorShouldBeFound("cidade.contains=" + DEFAULT_CIDADE);

        // Get all the fornecedorList where cidade contains UPDATED_CIDADE
        defaultFornecedorShouldNotBeFound("cidade.contains=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where cidade does not contain DEFAULT_CIDADE
        defaultFornecedorShouldNotBeFound("cidade.doesNotContain=" + DEFAULT_CIDADE);

        // Get all the fornecedorList where cidade does not contain UPDATED_CIDADE
        defaultFornecedorShouldBeFound("cidade.doesNotContain=" + UPDATED_CIDADE);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where estado equals to DEFAULT_ESTADO
        defaultFornecedorShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the fornecedorList where estado equals to UPDATED_ESTADO
        defaultFornecedorShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where estado not equals to DEFAULT_ESTADO
        defaultFornecedorShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the fornecedorList where estado not equals to UPDATED_ESTADO
        defaultFornecedorShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultFornecedorShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the fornecedorList where estado equals to UPDATED_ESTADO
        defaultFornecedorShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where estado is not null
        defaultFornecedorShouldBeFound("estado.specified=true");

        // Get all the fornecedorList where estado is null
        defaultFornecedorShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByObservacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao equals to DEFAULT_OBSERVACAO
        defaultFornecedorShouldBeFound("observacao.equals=" + DEFAULT_OBSERVACAO);

        // Get all the fornecedorList where observacao equals to UPDATED_OBSERVACAO
        defaultFornecedorShouldNotBeFound("observacao.equals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByObservacaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao not equals to DEFAULT_OBSERVACAO
        defaultFornecedorShouldNotBeFound("observacao.notEquals=" + DEFAULT_OBSERVACAO);

        // Get all the fornecedorList where observacao not equals to UPDATED_OBSERVACAO
        defaultFornecedorShouldBeFound("observacao.notEquals=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByObservacaoIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao in DEFAULT_OBSERVACAO or UPDATED_OBSERVACAO
        defaultFornecedorShouldBeFound("observacao.in=" + DEFAULT_OBSERVACAO + "," + UPDATED_OBSERVACAO);

        // Get all the fornecedorList where observacao equals to UPDATED_OBSERVACAO
        defaultFornecedorShouldNotBeFound("observacao.in=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByObservacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao is not null
        defaultFornecedorShouldBeFound("observacao.specified=true");

        // Get all the fornecedorList where observacao is null
        defaultFornecedorShouldNotBeFound("observacao.specified=false");
    }
                @Test
    @Transactional
    public void getAllFornecedorsByObservacaoContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao contains DEFAULT_OBSERVACAO
        defaultFornecedorShouldBeFound("observacao.contains=" + DEFAULT_OBSERVACAO);

        // Get all the fornecedorList where observacao contains UPDATED_OBSERVACAO
        defaultFornecedorShouldNotBeFound("observacao.contains=" + UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByObservacaoNotContainsSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where observacao does not contain DEFAULT_OBSERVACAO
        defaultFornecedorShouldNotBeFound("observacao.doesNotContain=" + DEFAULT_OBSERVACAO);

        // Get all the fornecedorList where observacao does not contain UPDATED_OBSERVACAO
        defaultFornecedorShouldBeFound("observacao.doesNotContain=" + UPDATED_OBSERVACAO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFornecedorShouldBeFound(String filter) throws Exception {
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fornecedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPessoa").value(hasItem(DEFAULT_TIPO_PESSOA.toString())))
            .andExpect(jsonPath("$.[*].numeroCPF").value(hasItem(DEFAULT_NUMERO_CPF)))
            .andExpect(jsonPath("$.[*].numeroCNPJ").value(hasItem(DEFAULT_NUMERO_CNPJ)))
            .andExpect(jsonPath("$.[*].numeroInscricaoEstadual").value(hasItem(DEFAULT_NUMERO_INSCRICAO_ESTADUAL)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefone1").value(hasItem(DEFAULT_TELEFONE_1)))
            .andExpect(jsonPath("$.[*].telefone2").value(hasItem(DEFAULT_TELEFONE_2)))
            .andExpect(jsonPath("$.[*].logradouroNome").value(hasItem(DEFAULT_LOGRADOURO_NOME)))
            .andExpect(jsonPath("$.[*].logradouroNumero").value(hasItem(DEFAULT_LOGRADOURO_NUMERO)))
            .andExpect(jsonPath("$.[*].logradouroComplemento").value(hasItem(DEFAULT_LOGRADOURO_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)));

        // Check, that the count call also returns 1
        restFornecedorMockMvc.perform(get("/api/fornecedors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFornecedorShouldNotBeFound(String filter) throws Exception {
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFornecedorMockMvc.perform(get("/api/fornecedors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFornecedor() throws Exception {
        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFornecedor() throws Exception {
        // Initialize the database
        fornecedorService.save(fornecedor);

        int databaseSizeBeforeUpdate = fornecedorRepository.findAll().size();

        // Update the fornecedor
        Fornecedor updatedFornecedor = fornecedorRepository.findById(fornecedor.getId()).get();
        // Disconnect from session so that the updates on updatedFornecedor are not directly saved in db
        em.detach(updatedFornecedor);
        updatedFornecedor
            .tipoPessoa(UPDATED_TIPO_PESSOA)
            .numeroCPF(UPDATED_NUMERO_CPF)
            .numeroCNPJ(UPDATED_NUMERO_CNPJ)
            .numeroInscricaoEstadual(UPDATED_NUMERO_INSCRICAO_ESTADUAL)
            .nome(UPDATED_NOME)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .email(UPDATED_EMAIL)
            .telefone1(UPDATED_TELEFONE_1)
            .telefone2(UPDATED_TELEFONE_2)
            .logradouroNome(UPDATED_LOGRADOURO_NOME)
            .logradouroNumero(UPDATED_LOGRADOURO_NUMERO)
            .logradouroComplemento(UPDATED_LOGRADOURO_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .observacao(UPDATED_OBSERVACAO);

        restFornecedorMockMvc.perform(put("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFornecedor)))
            .andExpect(status().isOk());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeUpdate);
        Fornecedor testFornecedor = fornecedorList.get(fornecedorList.size() - 1);
        assertThat(testFornecedor.getTipoPessoa()).isEqualTo(UPDATED_TIPO_PESSOA);
        assertThat(testFornecedor.getNumeroCPF()).isEqualTo(UPDATED_NUMERO_CPF);
        assertThat(testFornecedor.getNumeroCNPJ()).isEqualTo(UPDATED_NUMERO_CNPJ);
        assertThat(testFornecedor.getNumeroInscricaoEstadual()).isEqualTo(UPDATED_NUMERO_INSCRICAO_ESTADUAL);
        assertThat(testFornecedor.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testFornecedor.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testFornecedor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFornecedor.getTelefone1()).isEqualTo(UPDATED_TELEFONE_1);
        assertThat(testFornecedor.getTelefone2()).isEqualTo(UPDATED_TELEFONE_2);
        assertThat(testFornecedor.getLogradouroNome()).isEqualTo(UPDATED_LOGRADOURO_NOME);
        assertThat(testFornecedor.getLogradouroNumero()).isEqualTo(UPDATED_LOGRADOURO_NUMERO);
        assertThat(testFornecedor.getLogradouroComplemento()).isEqualTo(UPDATED_LOGRADOURO_COMPLEMENTO);
        assertThat(testFornecedor.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testFornecedor.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testFornecedor.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testFornecedor.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testFornecedor.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingFornecedor() throws Exception {
        int databaseSizeBeforeUpdate = fornecedorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFornecedorMockMvc.perform(put("/api/fornecedors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fornecedor)))
            .andExpect(status().isBadRequest());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFornecedor() throws Exception {
        // Initialize the database
        fornecedorService.save(fornecedor);

        int databaseSizeBeforeDelete = fornecedorRepository.findAll().size();

        // Delete the fornecedor
        restFornecedorMockMvc.perform(delete("/api/fornecedors/{id}", fornecedor.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
