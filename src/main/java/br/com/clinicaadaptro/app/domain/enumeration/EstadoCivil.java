package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The EstadoCivil enumeration.
 */
public enum EstadoCivil {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    SEPARADO("Separado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viúvo"),
    UNIAO_ESTAVEL("União Estável"),
    OUTRO("Outro");

    private final String value;


    EstadoCivil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
