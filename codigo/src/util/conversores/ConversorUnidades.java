package util.conversores;

import lombok.experimental.UtilityClass;

import static util.constantes.ConstantesNumeros.UM_MILHAO_L;

@UtilityClass
public class ConversorUnidades {

    /**
     * Converte dados de bytes para megabytes
     *
     * @param bytes quantidade de bytes
     * @return valor de bytes convertido em megabytes
     */
    public static long bytesParaMegabytes(long bytes) {
        return bytes / UM_MILHAO_L;
    }
}
