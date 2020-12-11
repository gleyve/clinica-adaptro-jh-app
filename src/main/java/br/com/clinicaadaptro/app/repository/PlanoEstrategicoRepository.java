package br.com.clinicaadaptro.app.repository;

import br.com.clinicaadaptro.app.domain.PlanoEstrategico;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PlanoEstrategico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanoEstrategicoRepository extends JpaRepository<PlanoEstrategico, Long> {

    @Query("select planoEstrategico from PlanoEstrategico planoEstrategico where planoEstrategico.user.login = ?#{principal.username}")
    List<PlanoEstrategico> findByUserIsCurrentUser();
}
