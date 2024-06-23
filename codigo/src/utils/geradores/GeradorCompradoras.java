package utils.geradores;

import entidades.Compradora;
import entidades.Lance;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.concurrent.ThreadLocalRandom.current;
import static utils.constantes.ConstantesExecucao.*;
import static utils.constantes.ConstantesNumeros.UM;
import static utils.constantes.ConstantesNumeros.ZERO;
import static utils.geradores.GeradorLances.gerarLances;

@UtilityClass
public class GeradorCompradoras {

    /**
     * Gera compradoras aleatórias
     *
     * @param qtdeCompradoras quantidade de compradoras
     * @return lista de compradoras
     */
    public static @NonNull List<Compradora> gerarCompradoras(int qtdeCompradoras) {
        List<Compradora> compradoras = new ArrayList<>();
        do {
            Long id = (long) compradoras.size();
            Integer qtdeDisponivel = current().nextInt(QUANTIDADE_MINIMA_COMPRADORA, QUANTIDADE_MAXIMA_COMPRADORA + UM);
            compradoras.add(
                    Compradora.builder()
                            .id(id)
                            .nome(format(NOME_COMPRADORA, compradoras.size()))
                            .quantidadeDisponivel(qtdeDisponivel)
                            .lances(gerarLances(id, qtdeDisponivel, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA))
                            .build()
            );
        } while (compradoras.size() != qtdeCompradoras);
        return compradoras;
    }

    /**
     * Gera compradoras com base com conjunto um disponibilizado pelo prof. Caram
     *
     * @return lista de compradoras
     */
    public static @NotNull List<Compradora> gerarCompradorasConjuntoUm() {
        List<Compradora> compradoras = new ArrayList<>();
        var ref = new Object() {
            int i = ZERO;
        };

        quantidadesConjuntoUm.forEach(quantidade -> {
            compradoras.add(
                    Compradora.builder()
                            .id((long) (ref.i + UM))
                            .nome(format("E%s", (ref.i)))
                            .quantidadeDisponivel(quantidade)
                            .lances(singletonList(
                                    Lance.builder()
                                            .id((long) ref.i + UM)
                                            .idCompradora((long) ref.i + UM)
                                            .quantidade(quantidade)
                                            .valor(valoresConjuntoUm.get(ref.i))
                                            .build()))
                            .build());
            ref.i++;
        });
        return compradoras;
    }

    /**
     * Gera compradoras com base com conjunto dois disponibilizado pelo prof. Caram
     *
     * @return lista de compradoras
     */
    public static @NotNull List<Compradora> gerarCompradorasConjuntoDois() {
        List<Compradora> compradoras = new ArrayList<>();
        var ref = new Object() {
            int i = ZERO;
        };

        quantidadesConjuntoDois.forEach(quantidade -> {
            compradoras.add(
                    Compradora.builder()
                            .id((long) (ref.i + UM))
                            .nome(format("E%s", (ref.i)))
                            .quantidadeDisponivel(quantidade)
                            .lances(singletonList(
                                    Lance.builder()
                                            .id((long) ref.i + UM)
                                            .idCompradora((long) ref.i + UM)
                                            .quantidade(quantidade)
                                            .valor(valoresConjuntoDois.get(ref.i))
                                            .build()))
                            .build());
            ref.i++;
        });
        return compradoras;
    }
}
