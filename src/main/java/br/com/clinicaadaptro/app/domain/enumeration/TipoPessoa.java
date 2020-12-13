package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The TipoPessoa enumeration.
 */
public enum TipoPessoa {
    F("PF"),
    J("PJ");

    private final String value;


    TipoPessoa(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
