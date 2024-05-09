package metodos.interfaces;

import entidades.Compradora;
import entidades.Lance;
import entidades.MelhorResultado;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static entidades.Compradora.encontrarPorId;
import static java.util.stream.Collectors.toSet;
import static util.constantes.ConstantesNumeros.UM;
import static util.geradores.GeradorCompradoras.gerarCompradoras;
import static util.geradores.GeradorLog.gerarLog;

/**
 * Interface relacionada a cada um dos algoritmos implementados no trabalho
 */
public interface Algoritmo {

    String algoritmo();

    void executar(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int indice, int lucroAtual);

    /**
     * Executa um algoritmo
     *
     * @param qtdeCompradoras              quantidade de empresas compradoras
     * @param qtdeMaximaLancePorCompradora quantidade máxima de lances que cada compradora pode ter
     * @return objeto do tipo MelhorResultado
     */
    default MelhorResultado executarAlgoritmo(int qtdeCompradoras, int qtdeMaximaLancePorCompradora) {
        MelhorResultado melhorResultado = new MelhorResultado();
        List<Compradora> compradoras = gerarCompradoras(qtdeCompradoras, qtdeMaximaLancePorCompradora);
        List<Lance> lancesRelacionados = compradoras.stream()
                .flatMap(compradora -> compradora.lances().stream())
                .toList();

//        Marca tempo de execução e executa o algoritmo de referência
        melhorResultado.getContador().iniciarContador();
        executar(melhorResultado, lancesRelacionados, new ArrayList<>(), UM, UM);
        melhorResultado.getContador().finalizarContator();

//        Relaciona campos para análise do melhor resultado
        relacionarCompradoras(melhorResultado, compradoras);
        relacionarQuantidadeVendida(melhorResultado);

        try {
            gerarLog(algoritmo(), lancesRelacionados.size(), melhorResultado.getContador().getTempoExecucao());
        } catch (Exception e) {
            Logger.getLogger(Algoritmo.class.getName()).warning(e.getLocalizedMessage());
        }

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
                        .mapToInt(Lance::quantidade).sum()
        );
    }
}
