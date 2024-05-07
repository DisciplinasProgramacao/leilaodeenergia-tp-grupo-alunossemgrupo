package util.constantes;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;
import static util.constantes.ConstantesNumeros.CEM;
import static util.constantes.ConstantesNumeros.DEZ;

@UtilityClass
public class ConstantesProdutoraVendedora {

    public static final String NOME_COMPRADORA = "Compradora %s";

    public static final int QUANTIDADE_MINIMA = DEZ;

    public static final int QUANTIDADE_MAXIMA = CEM;
}
