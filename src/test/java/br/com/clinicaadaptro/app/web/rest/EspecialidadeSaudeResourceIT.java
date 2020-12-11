package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.ClinicaAdaptrojhApp;
import br.com.clinicaadaptro.app.domain.EspecialidadeSaude;
import br.com.clinicaadaptro.app.repository.EspecialidadeSaudeRepository;

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

/**
 * Integration tests for the {@link EspecialidadeSaudeResource} REST controller.
 */
@SpringBootTest(classes = ClinicaAdaptrojhApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EspecialidadeSaudeResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ATIVO = false;
    private static final Boolean UPDATED_ATIVO = true;

    @Autowired
    private EspecialidadeSaudeRepository especialidadeSaudeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEspecialidadeSaudeMockMvc;

    private EspecialidadeSaude especialidadeSaude;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EspecialidadeSaude createEntity(EntityManager em) {
        EspecialidadeSaude especialidadeSaude = new EspecialidadeSaude()
            .descricao(DEFAULT_DESCRICAO)
            .sigla(DEFAULT_SIGLA)
            .ativo(DEFAULT_ATIVO);
        return especialidadeSaude;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EspecialidadeSaude createUpdatedEntity(EntityManager em) {
        EspecialidadeSaude especialidadeSaude = new EspecialidadeSaude()
            .descricao(UPDATED_DESCRICAO)
            .sigla(UPDATED_SIGLA)
            .ativo(UPDATED_ATIVO);
        return especialidadeSaude;
    }

    @BeforeEach
    public void initTest() {
        especialidadeSaude = createEntity(em);
    }

    @Test
    @Transactional
    public void createEspecialidadeSaude() throws Exception {
        int databaseSizeBeforeCreate = especialidadeSaudeRepository.findAll().size();
        // Create the EspecialidadeSaude
        restEspecialidadeSaudeMockMvc.perform(post("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadeSaude)))
            .andExpect(status().isCreated());

        // Validate the EspecialidadeSaude in the database
        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeCreate + 1);
        EspecialidadeSaude testEspecialidadeSaude = especialidadeSaudeList.get(especialidadeSaudeList.size() - 1);
        assertThat(testEspecialidadeSaude.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testEspecialidadeSaude.getSigla()).isEqualTo(DEFAULT_SIGLA);
        assertThat(testEspecialidadeSaude.isAtivo()).isEqualTo(DEFAULT_ATIVO);
    }

    @Test
    @Transactional
    public void createEspecialidadeSaudeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = especialidadeSaudeRepository.findAll().size();

        // Create the EspecialidadeSaude with an existing ID
        especialidadeSaude.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEspecialidadeSaudeMockMvc.perform(post("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadeSaude)))
            .andExpect(status().isBadRequest());

        // Validate the EspecialidadeSaude in the database
        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = especialidadeSaudeRepository.findAll().size();
        // set the field null
        especialidadeSaude.setDescricao(null);

        // Create the EspecialidadeSaude, which fails.


        restEspecialidadeSaudeMockMvc.perform(post("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadeSaude)))
            .andExpect(status().isBadRequest());

        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSiglaIsRequired() throws Exception {
        int databaseSizeBeforeTest = especialidadeSaudeRepository.findAll().size();
        // set the field null
        especialidadeSaude.setSigla(null);

        // Create the EspecialidadeSaude, which fails.


        restEspecialidadeSaudeMockMvc.perform(post("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadeSaude)))
            .andExpect(status().isBadRequest());

        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEspecialidadeSaudes() throws Exception {
        // Initialize the database
        especialidadeSaudeRepository.saveAndFlush(especialidadeSaude);

        // Get all the especialidadeSaudeList
        restEspecialidadeSaudeMockMvc.perform(get("/api/especialidade-saudes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(especialidadeSaude.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEspecialidadeSaude() throws Exception {
        // Initialize the database
        especialidadeSaudeRepository.saveAndFlush(especialidadeSaude);

        // Get the especialidadeSaude
        restEspecialidadeSaudeMockMvc.perform(get("/api/especialidade-saudes/{id}", especialidadeSaude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(especialidadeSaude.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA))
            .andExpect(jsonPath("$.ativo").value(DEFAULT_ATIVO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEspecialidadeSaude() throws Exception {
        // Get the especialidadeSaude
        restEspecialidadeSaudeMockMvc.perform(get("/api/especialidade-saudes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEspecialidadeSaude() throws Exception {
        // Initialize the database
        especialidadeSaudeRepository.saveAndFlush(especialidadeSaude);

        int databaseSizeBeforeUpdate = especialidadeSaudeRepository.findAll().size();

        // Update the especialidadeSaude
        EspecialidadeSaude updatedEspecialidadeSaude = especialidadeSaudeRepository.findById(especialidadeSaude.getId()).get();
        // Disconnect from session so that the updates on updatedEspecialidadeSaude are not directly saved in db
        em.detach(updatedEspecialidadeSaude);
        updatedEspecialidadeSaude
            .descricao(UPDATED_DESCRICAO)
            .sigla(UPDATED_SIGLA)
            .ativo(UPDATED_ATIVO);

        restEspecialidadeSaudeMockMvc.perform(put("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEspecialidadeSaude)))
            .andExpect(status().isOk());

        // Validate the EspecialidadeSaude in the database
        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeUpdate);
        EspecialidadeSaude testEspecialidadeSaude = especialidadeSaudeList.get(especialidadeSaudeList.size() - 1);
        assertThat(testEspecialidadeSaude.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testEspecialidadeSaude.getSigla()).isEqualTo(UPDATED_SIGLA);
        assertThat(testEspecialidadeSaude.isAtivo()).isEqualTo(UPDATED_ATIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingEspecialidadeSaude() throws Exception {
        int databaseSizeBeforeUpdate = especialidadeSaudeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEspecialidadeSaudeMockMvc.perform(put("/api/especialidade-saudes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadeSaude)))
            .andExpect(status().isBadRequest());

        // Validate the EspecialidadeSaude in the database
        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEspecialidadeSaude() throws Exception {
        // Initialize the database
        especialidadeSaudeRepository.saveAndFlush(especialidadeSaude);

        int databaseSizeBeforeDelete = especialidadeSaudeRepository.findAll().size();

        // Delete the especialidadeSaude
        restEspecialidadeSaudeMockMvc.perform(delete("/api/especialidade-saudes/{id}", especialidadeSaude.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EspecialidadeSaude> especialidadeSaudeList = especialidadeSaudeRepository.findAll();
        assertThat(especialidadeSaudeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
