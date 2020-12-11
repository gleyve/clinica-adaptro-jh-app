package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The Sexo enumeration.
 */
public enum Sexo {
    M("Masculino"),
    F("Feminino");

    private final String value;


    Sexo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
