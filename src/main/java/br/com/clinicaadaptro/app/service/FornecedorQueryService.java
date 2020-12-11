package br.com.clinicaadaptro.app.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import br.com.clinicaadaptro.app.domain.Fornecedor;
import br.com.clinicaadaptro.app.domain.*; // for static metamodels
import br.com.clinicaadaptro.app.repository.FornecedorRepository;
import br.com.clinicaadaptro.app.service.dto.FornecedorCriteria;

/**
 * Service for executing complex queries for {@link Fornecedor} entities in the database.
 * The main input is a {@link FornecedorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Fornecedor} or a {@link Page} of {@link Fornecedor} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FornecedorQueryService extends QueryService<Fornecedor> {

    private final Logger log = LoggerFactory.getLogger(FornecedorQueryService.class);

    private final FornecedorRepository fornecedorRepository;

    public FornecedorQueryService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    /**
     * Return a {@link List} of {@link Fornecedor} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Fornecedor> findByCriteria(FornecedorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Fornecedor> specification = createSpecification(criteria);
        return fornecedorRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Fornecedor} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Fornecedor> findByCriteria(FornecedorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Fornecedor> specification = createSpecification(criteria);
        return fornecedorRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FornecedorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Fornecedor> specification = createSpecification(criteria);
        return fornecedorRepository.count(specification);
    }

    /**
     * Function to convert {@link FornecedorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Fornecedor> createSpecification(FornecedorCriteria criteria) {
        Specification<Fornecedor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Fornecedor_.id));
            }
            if (criteria.getTipoPessoa() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoPessoa(), Fornecedor_.tipoPessoa));
            }
            if (criteria.getNumeroCPF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCPF(), Fornecedor_.numeroCPF));
            }
            if (criteria.getNumeroCNPJ() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCNPJ(), Fornecedor_.numeroCNPJ));
            }
            if (criteria.getNumeroInscricaoEstadual() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroInscricaoEstadual(), Fornecedor_.numeroInscricaoEstadual));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Fornecedor_.nome));
            }
            if (criteria.getNomeFantasia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomeFantasia(), Fornecedor_.nomeFantasia));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Fornecedor_.email));
            }
            if (criteria.getTelefone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone1(), Fornecedor_.telefone1));
            }
            if (criteria.getTelefone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone2(), Fornecedor_.telefone2));
            }
            if (criteria.getLogradouroNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNome(), Fornecedor_.logradouroNome));
            }
            if (criteria.getLogradouroNumero() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNumero(), Fornecedor_.logradouroNumero));
            }
            if (criteria.getLogradouroComplemento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroComplemento(), Fornecedor_.logradouroComplemento));
            }
            if (criteria.getBairro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBairro(), Fornecedor_.bairro));
            }
            if (criteria.getCep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCep(), Fornecedor_.cep));
            }
            if (criteria.getCidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCidade(), Fornecedor_.cidade));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), Fornecedor_.estado));
            }
            if (criteria.getObservacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservacao(), Fornecedor_.observacao));
            }
        }
        return specification;
    }
}
