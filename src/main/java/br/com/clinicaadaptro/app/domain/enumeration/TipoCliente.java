package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The TipoCliente enumeration.
 */
public enum TipoCliente {
    PACIENTE("Paciente"),
    OUTRO("Responsável por paciente ou Outros");

    private final String value;


    TipoCliente(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
