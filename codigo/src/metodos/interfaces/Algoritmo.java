package metodos.interfaces;

import entidades.Compradora;
import entidades.Lance;
import entidades.MelhorResultado;
import metodos.Backtracking;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static entidades.Compradora.encontrarPorId;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static util.constantes.ConstantesGeradorLog.FIM_ALGORITMO;
import static util.constantes.ConstantesGeradorLog.INICIO_ALGORITMO;
import static util.constantes.ConstantesNumeros.ZERO;
import static util.constantes.ConstantesProdutoraVendedora.QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA;
import static util.geradores.GeradorCompradoras.gerarCompradoras;
import static util.geradores.GeradorLogExecucao.gerarLogExecucao;
import static util.geradores.GeradorLogHistorico.gerarLogHistorico;

/**
 * Interface relacionada a cada um dos algoritmos implementados no trabalho
 */
public interface Algoritmo {

    List<Algoritmo> algoritmosImplementados = asList(new Backtracking());

    String algoritmo();

    //    Caso a implementação de algum algoritmo não use os mesmos parâmetros para execução, me chame que ajudo a ver como adaptar essa interface
    void executar(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int indice, int lucroAtual);

    /**
     * Executa um algoritmo
     *
     * @return objeto do tipo MelhorResultado
     */
    default MelhorResultado executarAlgoritmo(int qtdeCompradoras, String algoritmo) {

        Logger logger = Logger.getLogger(Algoritmo.class.getName());
        logger.info(format(INICIO_ALGORITMO, algoritmo, qtdeCompradoras));

        MelhorResultado melhorResultado = new MelhorResultado();
        List<Compradora> compradoras = gerarCompradoras(qtdeCompradoras, QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA);
        List<Lance> lancesRelacionados = compradoras.stream()
                .flatMap(compradora -> compradora.lances().stream())
                .toList();
//        Marca tempo de execução e executa o algoritmo de referência
        melhorResultado.getContador().iniciarContador();
        executar(melhorResultado, lancesRelacionados, new ArrayList<>(), ZERO, ZERO);
        melhorResultado.getContador().finalizarContator();
//        Relaciona campos para análise do melhor resultado
        relacionarCompradoras(melhorResultado, compradoras);
        relacionarQuantidadeVendida(melhorResultado);
//        Gera os logs de análise. Esses logs ficam registrados no caminho logs/execucao e logs/historico
        try {
            gerarLogHistorico(lancesRelacionados, melhorResultado);
            gerarLogExecucao(algoritmo(), lancesRelacionados.size(), melhorResultado.getContador().getTempoExecucao());
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
                        .mapToInt(Lance::quantidade).sum()
        );
    }
}
