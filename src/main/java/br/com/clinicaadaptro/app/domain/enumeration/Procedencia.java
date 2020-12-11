package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The Procedencia enumeration.
 */
public enum Procedencia {
    ENCAMINHAMENTO_MEDICO("Encaminhamento Médico"),
    INDICACAO_PACIENTE("Indicação de Paciente"),
    INDICACAO_MEDICO("Parente de um Paciente"),
    INTERNET("Conheceu pela Internet"),
    OUTRO("Outro");

    private final String value;


    Procedencia(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
