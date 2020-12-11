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

import br.com.clinicaadaptro.app.domain.Funcionario;
import br.com.clinicaadaptro.app.domain.*; // for static metamodels
import br.com.clinicaadaptro.app.repository.FuncionarioRepository;
import br.com.clinicaadaptro.app.service.dto.FuncionarioCriteria;

/**
 * Service for executing complex queries for {@link Funcionario} entities in the database.
 * The main input is a {@link FuncionarioCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Funcionario} or a {@link Page} of {@link Funcionario} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FuncionarioQueryService extends QueryService<Funcionario> {

    private final Logger log = LoggerFactory.getLogger(FuncionarioQueryService.class);

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioQueryService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Return a {@link List} of {@link Funcionario} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Funcionario> findByCriteria(FuncionarioCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Funcionario> specification = createSpecification(criteria);
        return funcionarioRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Funcionario} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Funcionario> findByCriteria(FuncionarioCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Funcionario> specification = createSpecification(criteria);
        return funcionarioRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FuncionarioCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Funcionario> specification = createSpecification(criteria);
        return funcionarioRepository.count(specification);
    }

    /**
     * Function to convert {@link FuncionarioCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Funcionario> createSpecification(FuncionarioCriteria criteria) {
        Specification<Funcionario> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Funcionario_.id));
            }
            if (criteria.getNomeCompleto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomeCompleto(), Funcionario_.nomeCompleto));
            }
            if (criteria.getDataNascimento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataNascimento(), Funcionario_.dataNascimento));
            }
            if (criteria.getNumeroConselhoProfissional() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroConselhoProfissional(), Funcionario_.numeroConselhoProfissional));
            }
            if (criteria.getCpf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCpf(), Funcionario_.cpf));
            }
            if (criteria.getRg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRg(), Funcionario_.rg));
            }
            if (criteria.getCnh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCnh(), Funcionario_.cnh));
            }
            if (criteria.getTelefone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone1(), Funcionario_.telefone1));
            }
            if (criteria.getTelefone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone2(), Funcionario_.telefone2));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Funcionario_.email));
            }
            if (criteria.getDataAdmissao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataAdmissao(), Funcionario_.dataAdmissao));
            }
            if (criteria.getDataDesligamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataDesligamento(), Funcionario_.dataDesligamento));
            }
            if (criteria.getSalario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalario(), Funcionario_.salario));
            }
            if (criteria.getSexo() != null) {
                specification = specification.and(buildSpecification(criteria.getSexo(), Funcionario_.sexo));
            }
            if (criteria.getEstadoCivil() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoCivil(), Funcionario_.estadoCivil));
            }
            if (criteria.getEscolaridade() != null) {
                specification = specification.and(buildSpecification(criteria.getEscolaridade(), Funcionario_.escolaridade));
            }
            if (criteria.getFuncao() != null) {
                specification = specification.and(buildSpecification(criteria.getFuncao(), Funcionario_.funcao));
            }
            if (criteria.getDescOutraFuncao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescOutraFuncao(), Funcionario_.descOutraFuncao));
            }
            if (criteria.getLogradouroNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNome(), Funcionario_.logradouroNome));
            }
            if (criteria.getLogradouroNumero() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroNumero(), Funcionario_.logradouroNumero));
            }
            if (criteria.getLogradouroComplemento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogradouroComplemento(), Funcionario_.logradouroComplemento));
            }
            if (criteria.getBairro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBairro(), Funcionario_.bairro));
            }
            if (criteria.getProximidadesLogradouro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProximidadesLogradouro(), Funcionario_.proximidadesLogradouro));
            }
            if (criteria.getCep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCep(), Funcionario_.cep));
            }
            if (criteria.getCidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCidade(), Funcionario_.cidade));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), Funcionario_.estado));
            }
            if (criteria.getDataHoraCadastro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataHoraCadastro(), Funcionario_.dataHoraCadastro));
            }
            if (criteria.getObservacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservacao(), Funcionario_.observacao));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Funcionario_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getEspecialidadeSaudeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEspecialidadeSaudeId(),
                    root -> root.join(Funcionario_.especialidadeSaude, JoinType.LEFT).get(EspecialidadeSaude_.id)));
            }
        }
        return specification;
    }
}
