package br.com.clinicaadaptro.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.clinicaadaptro.app.web.rest.TestUtil;

public class EspecialidadeSaudeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EspecialidadeSaude.class);
        EspecialidadeSaude especialidadeSaude1 = new EspecialidadeSaude();
        especialidadeSaude1.setId(1L);
        EspecialidadeSaude especialidadeSaude2 = new EspecialidadeSaude();
        especialidadeSaude2.setId(especialidadeSaude1.getId());
        assertThat(especialidadeSaude1).isEqualTo(especialidadeSaude2);
        especialidadeSaude2.setId(2L);
        assertThat(especialidadeSaude1).isNotEqualTo(especialidadeSaude2);
        especialidadeSaude1.setId(null);
        assertThat(especialidadeSaude1).isNotEqualTo(especialidadeSaude2);
    }
}
