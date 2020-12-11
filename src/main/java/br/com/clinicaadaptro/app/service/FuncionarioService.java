package br.com.clinicaadaptro.app.service;

import br.com.clinicaadaptro.app.domain.Funcionario;
import br.com.clinicaadaptro.app.repository.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Funcionario}.
 */
@Service
@Transactional
public class FuncionarioService {

    private final Logger log = LoggerFactory.getLogger(FuncionarioService.class);

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Save a funcionario.
     *
     * @param funcionario the entity to save.
     * @return the persisted entity.
     */
    public Funcionario save(Funcionario funcionario) {
        log.debug("Request to save Funcionario : {}", funcionario);
        return funcionarioRepository.save(funcionario);
    }

    /**
     * Get all the funcionarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Funcionario> findAll(Pageable pageable) {
        log.debug("Request to get all Funcionarios");
        return funcionarioRepository.findAll(pageable);
    }


    /**
     * Get one funcionario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Funcionario> findOne(Long id) {
        log.debug("Request to get Funcionario : {}", id);
        return funcionarioRepository.findById(id);
    }

    /**
     * Delete the funcionario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Funcionario : {}", id);
        funcionarioRepository.deleteById(id);
    }
}
