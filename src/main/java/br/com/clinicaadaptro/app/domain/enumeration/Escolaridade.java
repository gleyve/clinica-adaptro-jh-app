package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The Escolaridade enumeration.
 */
public enum Escolaridade {
    EFC("Ensino Fundamental Completo"),
    EFI("Ensino Fundamental Incompleto"),
    ESC("Ensino Superior Completo"),
    ESI("Ensino Superior Incompleto"),
    PGC("Pós-Graduação Completo"),
    PGI("Pós-Graduação Incompleto"),
    MES("Mestrado"),
    DOU("Doutorado");

    private final String value;


    Escolaridade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
