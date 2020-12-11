package br.com.clinicaadaptro.app.web.rest;

import br.com.clinicaadaptro.app.domain.PlanoEstrategico;
import br.com.clinicaadaptro.app.repository.PlanoEstrategicoRepository;
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
 * REST controller for managing {@link br.com.clinicaadaptro.app.domain.PlanoEstrategico}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlanoEstrategicoResource {

    private final Logger log = LoggerFactory.getLogger(PlanoEstrategicoResource.class);

    private static final String ENTITY_NAME = "planoEstrategico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanoEstrategicoRepository planoEstrategicoRepository;

    public PlanoEstrategicoResource(PlanoEstrategicoRepository planoEstrategicoRepository) {
        this.planoEstrategicoRepository = planoEstrategicoRepository;
    }

    /**
     * {@code POST  /plano-estrategicos} : Create a new planoEstrategico.
     *
     * @param planoEstrategico the planoEstrategico to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planoEstrategico, or with status {@code 400 (Bad Request)} if the planoEstrategico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plano-estrategicos")
    public ResponseEntity<PlanoEstrategico> createPlanoEstrategico(@Valid @RequestBody PlanoEstrategico planoEstrategico) throws URISyntaxException {
        log.debug("REST request to save PlanoEstrategico : {}", planoEstrategico);
        if (planoEstrategico.getId() != null) {
            throw new BadRequestAlertException("A new planoEstrategico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanoEstrategico result = planoEstrategicoRepository.save(planoEstrategico);
        return ResponseEntity.created(new URI("/api/plano-estrategicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plano-estrategicos} : Updates an existing planoEstrategico.
     *
     * @param planoEstrategico the planoEstrategico to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planoEstrategico,
     * or with status {@code 400 (Bad Request)} if the planoEstrategico is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planoEstrategico couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plano-estrategicos")
    public ResponseEntity<PlanoEstrategico> updatePlanoEstrategico(@Valid @RequestBody PlanoEstrategico planoEstrategico) throws URISyntaxException {
        log.debug("REST request to update PlanoEstrategico : {}", planoEstrategico);
        if (planoEstrategico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanoEstrategico result = planoEstrategicoRepository.save(planoEstrategico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planoEstrategico.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plano-estrategicos} : get all the planoEstrategicos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planoEstrategicos in body.
     */
    @GetMapping("/plano-estrategicos")
    public ResponseEntity<List<PlanoEstrategico>> getAllPlanoEstrategicos(Pageable pageable) {
        log.debug("REST request to get a page of PlanoEstrategicos");
        Page<PlanoEstrategico> page = planoEstrategicoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /plano-estrategicos/:id} : get the "id" planoEstrategico.
     *
     * @param id the id of the planoEstrategico to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planoEstrategico, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plano-estrategicos/{id}")
    public ResponseEntity<PlanoEstrategico> getPlanoEstrategico(@PathVariable Long id) {
        log.debug("REST request to get PlanoEstrategico : {}", id);
        Optional<PlanoEstrategico> planoEstrategico = planoEstrategicoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planoEstrategico);
    }

    /**
     * {@code DELETE  /plano-estrategicos/:id} : delete the "id" planoEstrategico.
     *
     * @param id the id of the planoEstrategico to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plano-estrategicos/{id}")
    public ResponseEntity<Void> deletePlanoEstrategico(@PathVariable Long id) {
        log.debug("REST request to delete PlanoEstrategico : {}", id);
        planoEstrategicoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
