package utils.geradores;

import entidades.Lance;
import entidades.MelhorResultado;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Collections.max;
import static java.util.Locale.US;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static utils.constantes.ConstantesErros.MSG_ERRO_ARQUIVO;
import static utils.constantes.ConstantesGeradorLog.*;
import static utils.constantes.ConstantesNumeros.UM;
import static utils.constantes.ConstantesNumeros.ZERO;

@UtilityClass
public class GeradorLogHistorico {

    /**
     * Gera o log de histórico do algoritmo
     *
     * @param lances          todos os lances feitos
     * @param melhorResultado objeto do tipo MelhorResultado
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    public static void gerarLogHistorico(List<Lance> lances, MelhorResultado melhorResultado) throws IOException {
        try {
            Integer maiorIndice = buscarMaiorIndice(listarArquivos());
            criarArquivoECabecalho(maiorIndice);
            escreverNoArquivo(maiorIndice, lances, melhorResultado);
        } catch (IOException e) {
            throw new IOException(MSG_ERRO_ARQUIVO);
        }
    }

    /**
     * Cria cabeçalho do arquivo de log de histórico
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de log
     */
    private static void criarArquivoECabecalho(Integer maiorIndice) throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(format(CAMINHO_ARQUIVO_HISTORICO + EXTENSAO_CSV, maiorIndice + UM))) {
            escritorArquivo.println(join(VIRGULA, COLUNAS_HISTORICO));
        }
    }

    /**
     * Escreve uma nova linha de dados no arquivo de log de histórico
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de log
     */
    private static void escreverNoArquivo(Integer maiorIndice, @NotNull List<Lance> lances, @NotNull MelhorResultado melhorResultado) throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(new FileWriter(format(CAMINHO_ARQUIVO_HISTORICO + EXTENSAO_CSV, maiorIndice + UM), true))) {
            lances.forEach(lance -> escritorArquivo.println(format(US, CONFIGURACAO_COLUNAS_CSV_HISTORICO, lance.quantidade(), lance.valor())));
            escritorArquivo.println(format(VALOR_TOTAL, melhorResultado.getLucroMaximizado()));
        }
    }

    /**
     * Lista todos os arquivos de uma pasta
     *
     * @return lista de arquivos na pasta
     */
    private static Set<String> listarArquivos() {
        return of(requireNonNull(new File(CAMINHO_PASTA_HISTORICO).listFiles()))
                .filter(arquivo -> !arquivo.isDirectory())
                .map(File::getName)
                .collect(toSet());
    }

    /**
     * Retorna o índice do último histórico feito, ex: hist-1 retorna 1, hist-2 retorna 2
     *
     * @param listaArquivos lista de arquivos na pasta
     * @return maior índice contido na lista de arquivos na pasta
     */
    private static Integer buscarMaiorIndice(@NotNull Set<String> listaArquivos) {
        Set<Integer> indices = new HashSet<>();
        listaArquivos.forEach(arquivo -> indices.add(valueOf(arquivo.split(REGEX_SPLIT)[UM])));
        return indices.isEmpty()
                ? ZERO
                : max(indices);
    }
}
