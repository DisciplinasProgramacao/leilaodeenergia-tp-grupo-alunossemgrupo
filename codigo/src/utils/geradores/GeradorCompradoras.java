package utils.geradores;

import entidades.Compradora;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
// import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.concurrent.ThreadLocalRandom.current;
import static utils.constantes.ConstantesExecucao.*;
import static utils.geradores.GeradorLances.gerarLances;
import static utils.constantes.ConstantesNumeros.UM;

@UtilityClass
public class GeradorCompradoras {

    /**
     * Gera compradoras aleatórias
     *
     * @param qtdeCompradoras              quantidade de compradoras
     * @param qtdeMaximaLancePorCompradora quantidade máxima de lances por compradora
     * @return lista de compradoras
     */
    public static @NonNull List<Compradora> gerarCompradoras(int qtdeCompradoras, int qtdeMaximaLancePorCompradora) {
        List<Compradora> compradoras = new ArrayList<>();
        do {
            Long id = (long) compradoras.size();
            Integer qtdeDisponivel = current().nextInt(QUANTIDADE_MINIMA_COMPRADORA, QUANTIDADE_MAXIMA_COMPRADORA + UM);
            compradoras.add(
                    Compradora.builder()
                            .id(id)
                            .nome(format(NOME_COMPRADORA, compradoras.size()))
                            .quantidadeDisponivel(qtdeDisponivel)
                            .lances(gerarLances(id, qtdeDisponivel, qtdeMaximaLancePorCompradora))
                            .build()
            );
        } while (compradoras.size() != qtdeCompradoras);
        return compradoras;
    }
}
