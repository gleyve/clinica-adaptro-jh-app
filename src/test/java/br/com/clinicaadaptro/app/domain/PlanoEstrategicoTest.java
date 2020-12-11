package br.com.clinicaadaptro.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.clinicaadaptro.app.web.rest.TestUtil;

public class PlanoEstrategicoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanoEstrategico.class);
        PlanoEstrategico planoEstrategico1 = new PlanoEstrategico();
        planoEstrategico1.setId(1L);
        PlanoEstrategico planoEstrategico2 = new PlanoEstrategico();
        planoEstrategico2.setId(planoEstrategico1.getId());
        assertThat(planoEstrategico1).isEqualTo(planoEstrategico2);
        planoEstrategico2.setId(2L);
        assertThat(planoEstrategico1).isNotEqualTo(planoEstrategico2);
        planoEstrategico1.setId(null);
        assertThat(planoEstrategico1).isNotEqualTo(planoEstrategico2);
    }
}
