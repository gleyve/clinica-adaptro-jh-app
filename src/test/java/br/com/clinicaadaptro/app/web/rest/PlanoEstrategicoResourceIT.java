package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.ClinicaAdaptrojhApp;
import br.com.clinicaadaptro.app.domain.PlanoEstrategico;
import br.com.clinicaadaptro.app.domain.Cliente;
import br.com.clinicaadaptro.app.repository.PlanoEstrategicoRepository;

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

/**
 * Integration tests for the {@link PlanoEstrategicoResource} REST controller.
 */
@SpringBootTest(classes = ClinicaAdaptrojhApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlanoEstrategicoResourceIT {

    private static final LocalDate DEFAULT_DATA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_FIM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_FIM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DETALHAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DETALHAMENTO = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_HORA_CADASTRO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_HORA_CADASTRO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PlanoEstrategicoRepository planoEstrategicoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanoEstrategicoMockMvc;

    private PlanoEstrategico planoEstrategico;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanoEstrategico createEntity(EntityManager em) {
        PlanoEstrategico planoEstrategico = new PlanoEstrategico()
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataFim(DEFAULT_DATA_FIM)
            .detalhamento(DEFAULT_DETALHAMENTO)
            .dataHoraCadastro(DEFAULT_DATA_HORA_CADASTRO);
        // Add required entity
        Cliente cliente;
        if (TestUtil.findAll(em, Cliente.class).isEmpty()) {
            cliente = ClienteResourceIT.createEntity(em);
            em.persist(cliente);
            em.flush();
        } else {
            cliente = TestUtil.findAll(em, Cliente.class).get(0);
        }
        planoEstrategico.setCliente(cliente);
        return planoEstrategico;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanoEstrategico createUpdatedEntity(EntityManager em) {
        PlanoEstrategico planoEstrategico = new PlanoEstrategico()
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .detalhamento(UPDATED_DETALHAMENTO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO);
        // Add required entity
        Cliente cliente;
        if (TestUtil.findAll(em, Cliente.class).isEmpty()) {
            cliente = ClienteResourceIT.createUpdatedEntity(em);
            em.persist(cliente);
            em.flush();
        } else {
            cliente = TestUtil.findAll(em, Cliente.class).get(0);
        }
        planoEstrategico.setCliente(cliente);
        return planoEstrategico;
    }

    @BeforeEach
    public void initTest() {
        planoEstrategico = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanoEstrategico() throws Exception {
        int databaseSizeBeforeCreate = planoEstrategicoRepository.findAll().size();
        // Create the PlanoEstrategico
        restPlanoEstrategicoMockMvc.perform(post("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planoEstrategico)))
            .andExpect(status().isCreated());

        // Validate the PlanoEstrategico in the database
        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeCreate + 1);
        PlanoEstrategico testPlanoEstrategico = planoEstrategicoList.get(planoEstrategicoList.size() - 1);
        assertThat(testPlanoEstrategico.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testPlanoEstrategico.getDataFim()).isEqualTo(DEFAULT_DATA_FIM);
        assertThat(testPlanoEstrategico.getDetalhamento()).isEqualTo(DEFAULT_DETALHAMENTO);
        assertThat(testPlanoEstrategico.getDataHoraCadastro()).isEqualTo(DEFAULT_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void createPlanoEstrategicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planoEstrategicoRepository.findAll().size();

        // Create the PlanoEstrategico with an existing ID
        planoEstrategico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanoEstrategicoMockMvc.perform(post("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planoEstrategico)))
            .andExpect(status().isBadRequest());

        // Validate the PlanoEstrategico in the database
        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDetalhamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = planoEstrategicoRepository.findAll().size();
        // set the field null
        planoEstrategico.setDetalhamento(null);

        // Create the PlanoEstrategico, which fails.


        restPlanoEstrategicoMockMvc.perform(post("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planoEstrategico)))
            .andExpect(status().isBadRequest());

        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataHoraCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = planoEstrategicoRepository.findAll().size();
        // set the field null
        planoEstrategico.setDataHoraCadastro(null);

        // Create the PlanoEstrategico, which fails.


        restPlanoEstrategicoMockMvc.perform(post("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planoEstrategico)))
            .andExpect(status().isBadRequest());

        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanoEstrategicos() throws Exception {
        // Initialize the database
        planoEstrategicoRepository.saveAndFlush(planoEstrategico);

        // Get all the planoEstrategicoList
        restPlanoEstrategicoMockMvc.perform(get("/api/plano-estrategicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planoEstrategico.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].dataFim").value(hasItem(DEFAULT_DATA_FIM.toString())))
            .andExpect(jsonPath("$.[*].detalhamento").value(hasItem(DEFAULT_DETALHAMENTO)))
            .andExpect(jsonPath("$.[*].dataHoraCadastro").value(hasItem(DEFAULT_DATA_HORA_CADASTRO.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanoEstrategico() throws Exception {
        // Initialize the database
        planoEstrategicoRepository.saveAndFlush(planoEstrategico);

        // Get the planoEstrategico
        restPlanoEstrategicoMockMvc.perform(get("/api/plano-estrategicos/{id}", planoEstrategico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planoEstrategico.getId().intValue()))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.dataFim").value(DEFAULT_DATA_FIM.toString()))
            .andExpect(jsonPath("$.detalhamento").value(DEFAULT_DETALHAMENTO))
            .andExpect(jsonPath("$.dataHoraCadastro").value(DEFAULT_DATA_HORA_CADASTRO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPlanoEstrategico() throws Exception {
        // Get the planoEstrategico
        restPlanoEstrategicoMockMvc.perform(get("/api/plano-estrategicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanoEstrategico() throws Exception {
        // Initialize the database
        planoEstrategicoRepository.saveAndFlush(planoEstrategico);

        int databaseSizeBeforeUpdate = planoEstrategicoRepository.findAll().size();

        // Update the planoEstrategico
        PlanoEstrategico updatedPlanoEstrategico = planoEstrategicoRepository.findById(planoEstrategico.getId()).get();
        // Disconnect from session so that the updates on updatedPlanoEstrategico are not directly saved in db
        em.detach(updatedPlanoEstrategico);
        updatedPlanoEstrategico
            .dataInicio(UPDATED_DATA_INICIO)
            .dataFim(UPDATED_DATA_FIM)
            .detalhamento(UPDATED_DETALHAMENTO)
            .dataHoraCadastro(UPDATED_DATA_HORA_CADASTRO);

        restPlanoEstrategicoMockMvc.perform(put("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlanoEstrategico)))
            .andExpect(status().isOk());

        // Validate the PlanoEstrategico in the database
        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeUpdate);
        PlanoEstrategico testPlanoEstrategico = planoEstrategicoList.get(planoEstrategicoList.size() - 1);
        assertThat(testPlanoEstrategico.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testPlanoEstrategico.getDataFim()).isEqualTo(UPDATED_DATA_FIM);
        assertThat(testPlanoEstrategico.getDetalhamento()).isEqualTo(UPDATED_DETALHAMENTO);
        assertThat(testPlanoEstrategico.getDataHoraCadastro()).isEqualTo(UPDATED_DATA_HORA_CADASTRO);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanoEstrategico() throws Exception {
        int databaseSizeBeforeUpdate = planoEstrategicoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanoEstrategicoMockMvc.perform(put("/api/plano-estrategicos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planoEstrategico)))
            .andExpect(status().isBadRequest());

        // Validate the PlanoEstrategico in the database
        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanoEstrategico() throws Exception {
        // Initialize the database
        planoEstrategicoRepository.saveAndFlush(planoEstrategico);

        int databaseSizeBeforeDelete = planoEstrategicoRepository.findAll().size();

        // Delete the planoEstrategico
        restPlanoEstrategicoMockMvc.perform(delete("/api/plano-estrategicos/{id}", planoEstrategico.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanoEstrategico> planoEstrategicoList = planoEstrategicoRepository.findAll();
        assertThat(planoEstrategicoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
