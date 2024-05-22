package metodos.interfaces;

import entidades.Compradora;
import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import metodos.Backtracking;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static entidades.Compradora.encontrarPorId;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static utils.constantes.ConstantesGeradorLog.FIM_ALGORITMO;
import static utils.constantes.ConstantesGeradorLog.INICIO_ALGORITMO;
import static utils.constantes.ConstantesNumeros.ZERO;
import static utils.geradores.GeradorLogExecucao.gerarLogExecucao;
import static utils.geradores.GeradorLogHistorico.gerarHistorico;

/**
 * Interface relacionada a cada um dos algoritmos implementados no trabalho
 */
public interface Algoritmo {

    List<Algoritmo> algoritmosImplementados = asList(new Backtracking());

    AlgoritmosEnums algoritmo();

    void executar(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int indice, int lucroAtual);

    /**
     * Executa um algoritmo
     *
     * @return objeto do tipo MelhorResultado
     */
    default MelhorResultado executarAlgoritmo(@NotNull List<Compradora> compradoras, int qtdeCompradoras, AlgoritmosEnums algoritmo, boolean limitarTempo) {

        Logger logger = Logger.getLogger(Algoritmo.class.getName());
        logger.info(format(INICIO_ALGORITMO, algoritmo, qtdeCompradoras));

        List<Lance> lancesRelacionados = compradoras.stream()
                .flatMap(compradora -> compradora.lances().stream())
                .toList();

        MelhorResultado melhorResultado = new MelhorResultado();

        melhorResultado.getContador().iniciarContador();
        executar(melhorResultado, lancesRelacionados, new ArrayList<>(), ZERO, ZERO);
        melhorResultado.getContador().finalizarContator();

        relacionarCompradoras(melhorResultado, compradoras);
        relacionarQuantidadeVendida(melhorResultado);

        try {
            gerarHistorico(lancesRelacionados, melhorResultado);
            gerarLogExecucao(melhorResultado, algoritmo(), lancesRelacionados.size());
        } catch (Exception e) {
            logger.warning(e.getLocalizedMessage());
        }
        logger.info(FIM_ALGORITMO);
        return melhorResultado;
    }

    /**
     * Relaciona as compradoras de um melhor resultado a partir da sua lista de lances
     *
     * @param melhorResultado objeto do tipo MelhorResultado
     * @param compradoras     lista de compradoras cadastradas
     */
    private void relacionarCompradoras(@NotNull MelhorResultado melhorResultado, List<Compradora> compradoras) {
        melhorResultado.setCompradoras(
                melhorResultado.getLancesSelecionados().stream()
                        .map(lance -> encontrarPorId(compradoras, lance.idCompradora()))
                        .collect(toSet())
        );
    }

    /**
     * Relaciona a quantidade vendida de um melhor resultado a partir da sua lista de lances
     *
     * @param melhorResultado objeto do tipo MelhorResultado
     */
    private void relacionarQuantidadeVendida(@NotNull MelhorResultado melhorResultado) {
        melhorResultado.setQuantidadeVendida(
                melhorResultado.getLancesSelecionados().stream()
                        .mapToInt(Lance::quantidade)
                        .sum()
        );
    }
}
