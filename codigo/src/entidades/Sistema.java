package entidades;

import lombok.Builder;
import lombok.Data;

import static java.lang.Runtime.getRuntime;
import static java.util.Objects.isNull;
import static util.ConversorUnidades.bytesParaMegabytes;

@Data
@Builder
public class Sistema {

//    Utilizado padrão Singleton, pois apenas uma instância de Sistema deve ser utilizado por vez
    private static Sistema INSTANCIA;

    private int coresDisponiveis;
    private long memoriaTotal;
    private long memoriaMaxima;
    private long memoriaLiberada;

    public static Sistema instancia() {
        if (isNull(INSTANCIA))
            INSTANCIA = Sistema.builder()
                    .coresDisponiveis(getRuntime().availableProcessors())
                    .memoriaTotal(bytesParaMegabytes(getRuntime().totalMemory()))
                    .memoriaMaxima(bytesParaMegabytes(getRuntime().maxMemory()))
                    .memoriaLiberada(bytesParaMegabytes(getRuntime().freeMemory()))
                    .build();
        return INSTANCIA;
    }
}
