package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The CategoriaFuncionario enumeration.
 */
public enum CategoriaFuncionario {
    PROFISSIONAL_SAUDE("Profissional da Saúde"),
    RECEPCAO("Recepcionista"),
    ADMIN("Administrador"),
    ESTAGIARIO("Estagiário"),
    OUTRO("Outro");

    private final String value;


    CategoriaFuncionario(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
