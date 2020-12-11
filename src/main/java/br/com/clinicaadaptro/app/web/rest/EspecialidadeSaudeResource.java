package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.domain.EspecialidadeSaude;
import br.com.clinicaadaptro.app.repository.EspecialidadeSaudeRepository;
import br.com.clinicaadaptro.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.clinicaadaptro.app.domain.EspecialidadeSaude}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EspecialidadeSaudeResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadeSaudeResource.class);

    private static final String ENTITY_NAME = "especialidadeSaude";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspecialidadeSaudeRepository especialidadeSaudeRepository;

    public EspecialidadeSaudeResource(EspecialidadeSaudeRepository especialidadeSaudeRepository) {
        this.especialidadeSaudeRepository = especialidadeSaudeRepository;
    }

    /**
     * {@code POST  /especialidade-saudes} : Create a new especialidadeSaude.
     *
     * @param especialidadeSaude the especialidadeSaude to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new especialidadeSaude, or with status {@code 400 (Bad Request)} if the especialidadeSaude has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/especialidade-saudes")
    public ResponseEntity<EspecialidadeSaude> createEspecialidadeSaude(@Valid @RequestBody EspecialidadeSaude especialidadeSaude) throws URISyntaxException {
        log.debug("REST request to save EspecialidadeSaude : {}", especialidadeSaude);
        if (especialidadeSaude.getId() != null) {
            throw new BadRequestAlertException("A new especialidadeSaude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspecialidadeSaude result = especialidadeSaudeRepository.save(especialidadeSaude);
        return ResponseEntity.created(new URI("/api/especialidade-saudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /especialidade-saudes} : Updates an existing especialidadeSaude.
     *
     * @param especialidadeSaude the especialidadeSaude to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated especialidadeSaude,
     * or with status {@code 400 (Bad Request)} if the especialidadeSaude is not valid,
     * or with status {@code 500 (Internal Server Error)} if the especialidadeSaude couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/especialidade-saudes")
    public ResponseEntity<EspecialidadeSaude> updateEspecialidadeSaude(@Valid @RequestBody EspecialidadeSaude especialidadeSaude) throws URISyntaxException {
        log.debug("REST request to update EspecialidadeSaude : {}", especialidadeSaude);
        if (especialidadeSaude.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspecialidadeSaude result = especialidadeSaudeRepository.save(especialidadeSaude);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, especialidadeSaude.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /especialidade-saudes} : get all the especialidadeSaudes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of especialidadeSaudes in body.
     */
    @GetMapping("/especialidade-saudes")
    public ResponseEntity<List<EspecialidadeSaude>> getAllEspecialidadeSaudes(Pageable pageable) {
        log.debug("REST request to get a page of EspecialidadeSaudes");
        Page<EspecialidadeSaude> page = especialidadeSaudeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /especialidade-saudes/:id} : get the "id" especialidadeSaude.
     *
     * @param id the id of the especialidadeSaude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the especialidadeSaude, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/especialidade-saudes/{id}")
    public ResponseEntity<EspecialidadeSaude> getEspecialidadeSaude(@PathVariable Long id) {
        log.debug("REST request to get EspecialidadeSaude : {}", id);
        Optional<EspecialidadeSaude> especialidadeSaude = especialidadeSaudeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(especialidadeSaude);
    }

    /**
     * {@code DELETE  /especialidade-saudes/:id} : delete the "id" especialidadeSaude.
     *
     * @param id the id of the especialidadeSaude to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/especialidade-saudes/{id}")
    public ResponseEntity<Void> deleteEspecialidadeSaude(@PathVariable Long id) {
        log.debug("REST request to delete EspecialidadeSaude : {}", id);
        especialidadeSaudeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
