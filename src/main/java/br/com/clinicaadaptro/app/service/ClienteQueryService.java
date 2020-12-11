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

import br.com.clinicaadaptro.app.domain.Cliente;
import br.com.clinicaadaptro.app.domain.*; // for static metamodels
import br.com.clinicaadaptro.app.repository.ClienteRepository;
import br.com.clinicaadaptro.app.service.dto.ClienteCriteria;

/**
 * Service for executing complex queries for {@link Cliente} entities in the database.
 * The main input is a {@link ClienteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Cliente} or a {@link Page} of {@link Cliente} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClienteQueryService extends QueryService<Cliente> {

    private final Logger log = LoggerFactory.getLogger(ClienteQueryService.class);

    private final ClienteRepository clienteRepository;

    public ClienteQueryService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Return a {@link List} of {@link Cliente} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Cliente> findByCriteria(ClienteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cliente> specification = createSpecification(criteria);
        return clienteRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Cliente} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Cliente> findByCriteria(ClienteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cliente> specification = createSpecification(criteria);
        return clienteRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClienteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cliente> specification = createSpecification(criteria);
        return clienteRepository.count(specification);
    }

    /**
     * Function to convert {@link ClienteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Cliente> createSpecification(ClienteCriteria criteria) {
        Specification<Cliente> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Cliente_.id));
            }
            if (criteria.getTipoCliente() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoCliente(), Cliente_.tipoCliente));
            }
            if (criteria.getMatricula() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricula(), Cliente_.matricula));
            }
            if (criteria.getNomeCompleto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomeCompleto(), Cliente_.nomeCompleto));
            }
            if (criteria.getDataNascimento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataNascimento(), Cliente_.dataNascimento));
            }
            if (criteria.getSexo() != null) {
                specification = specification.and(buildSpecification(criteria.getSexo(), Cliente_.sexo));
            }
            if (criteria.getEstadoCivil() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoCivil(), Cliente_.estadoCivil));
            }
            if (criteria.getEscolaridade() != null) {
                specification = specification.and(buildSpecification(criteria.getEscolaridade(), Cliente_.escolaridade));
            }
            if (criteria.getConvenio() != null) {
                specification = specification.and(buildSpecification(criteria.getConvenio(), Cliente_.convenio));
            }
            if (criteria.getNumCarteirinhaConvenio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumCarteirinhaConvenio(), Cliente_.numCarteirinhaConvenio));
            }
            if (criteria.getDataValidadeConvenio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataValidadeConvenio(), Cliente_.dataValidadeConvenio));
            }
            if (criteria.getProcedencia() != null) {
                specification = specification.and(buildSpecification(criteria.getProcedencia(), Cliente_.procedencia));
            }
            if (criteria.getProfissao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfissao(), Cliente_.profissao));
            }
            if (criteria.getCpf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCpf(), Cliente_.cpf));
            }
            if (criteria.getRg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRg(), Cliente_.rg));
            }
            if (criteria.getTelefone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone1(), Cliente_.telefone1));
            }
            if (criteria.getTelefone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone2(), Cliente_.telefone2));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Cliente_.email));
            }
            if (criteria.getLogradouroNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNome(), Cliente_.logradouroNome));
            }
            if (criteria.getLogradouroNumero() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNumero(), Cliente_.logradouroNumero));
            }
            if (criteria.getLogradouroComplemento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroComplemento(), Cliente_.logradouroComplemento));
            }
            if (criteria.getBairro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBairro(), Cliente_.bairro));
            }
            if (criteria.getCep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCep(), Cliente_.cep));
            }
            if (criteria.getCidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCidade(), Cliente_.cidade));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), Cliente_.estado));
            }
            if (criteria.getParentescoResponsavel() != null) {
                specification = specification.and(buildSpecification(criteria.getParentescoResponsavel(), Cliente_.parentescoResponsavel));
            }
            if (criteria.getParentescoResponsavelFinanceiro() != null) {
                specification = specification.and(buildSpecification(criteria.getParentescoResponsavelFinanceiro(), Cliente_.parentescoResponsavelFinanceiro));
            }
            if (criteria.getDataHoraCadastro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataHoraCadastro(), Cliente_.dataHoraCadastro));
            }
            if (criteria.getAtivo() != null) {
                specification = specification.and(buildSpecification(criteria.getAtivo(), Cliente_.ativo));
            }
            if (criteria.getObservacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservacao(), Cliente_.observacao));
            }
            if (criteria.getPlanoEstrategicoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanoEstrategicoId(),
                    root -> root.join(Cliente_.planoEstrategicos, JoinType.LEFT).get(PlanoEstrategico_.id)));
            }
            if (criteria.getResponsavelId() != null) {
                specification = specification.and(buildSpecification(criteria.getResponsavelId(),
                    root -> root.join(Cliente_.responsavel, JoinType.LEFT).get(Cliente_.id)));
            }
            if (criteria.getResponsavelFinanceiroId() != null) {
                specification = specification.and(buildSpecification(criteria.getResponsavelFinanceiroId(),
                    root -> root.join(Cliente_.responsavelFinanceiro, JoinType.LEFT).get(Cliente_.id)));
            }
        }
        return specification;
    }
}
