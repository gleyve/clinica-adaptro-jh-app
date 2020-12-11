package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The Convenio enumeration.
 */
public enum Convenio {
    UNIMED_CE("UNIMED Ceará"),
    OUTRO("Outro");

    private final String value;


    Convenio(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
