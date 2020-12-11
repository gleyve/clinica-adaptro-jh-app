package br.com.clinicaadaptro.app.domain.enumeration;

/**
 * The TipoParentesco enumeration.
 */
public enum TipoParentesco {
    PAI("Pai"),
    MAE("Mãe"),
    AVO("Avô(ó"),
    TIO("Tio(a"),
    AMIGO("Amigo(a"),
    CONJUGE("Esposo(a"),
    OUTURO("Outro");

    private final String value;


    TipoParentesco(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
