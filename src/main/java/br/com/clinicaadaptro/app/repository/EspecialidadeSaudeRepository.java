package br.com.clinicaadaptro.app.repository;

import br.com.clinicaadaptro.app.domain.EspecialidadeSaude;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EspecialidadeSaude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadeSaudeRepository extends JpaRepository<EspecialidadeSaude, Long> {
}
