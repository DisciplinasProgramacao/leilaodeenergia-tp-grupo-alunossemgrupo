package utils.geradores;

import entidades.Lance;
import entidades.MelhorResultado;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Collections.max;
import static java.util.Locale.US;
import static utils.constantes.ConstantesErros.MSG_ARQUIVO_NAO_ENCONTRADO;
import static utils.constantes.ConstantesGeradorLog.*;
import static utils.constantes.ConstantesNumeros.*;

@UtilityClass
public class GeradorLogHistorico {

    private static final Logger logger = Logger.getLogger(GeradorLogHistorico.class.getName());

    /**
     * Gera o log de histórico do algoritmo
     *
     * @param lances          todos os lances feitos
     * @param melhorResultado objeto do tipo MelhorResultado
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    public static void gerarHistorico(List<Lance> lances, MelhorResultado melhorResultado) throws IOException {
        try {
            Integer maiorIndice = buscarMaiorIndice(listarHistoricos());
            criarArquivoECabecalho();
            escreverNoArquivo(maiorIndice, lances, melhorResultado);
        } catch (IOException e) {
            throw new IOException(MSG_ARQUIVO_NAO_ENCONTRADO);
        }
    }

    /**
     * Cria cabeçalho do arquivo de log de histórico
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de log
     */
    private static void criarArquivoECabecalho() throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO_HISTORICO + EXTENSAO_CSV, true))) {
            escritorArquivo.println(join(VIRGULA, COLUNAS_HISTORICO));
        }
    }

    /**
     * Escreve uma nova linha de dados no arquivo de log de histórico
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de log
     */
    private static void escreverNoArquivo(Integer maiorIndice, @NotNull List<Lance> lances, @NotNull MelhorResultado melhorResultado) throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO_HISTORICO + EXTENSAO_CSV, true))) {
            lances.forEach(lance ->
                    escritorArquivo.println(format(US, CONFIGURACAO_COLUNAS_CSV_HISTORICO,
                            lance.quantidade(),
                            lance.valor(),
                            format(NOME_HISTORICO, maiorIndice + UM))));
            escritorArquivo.println(SEPARADOR_HISTORICOS);
            melhorResultado.getLancesSelecionados().forEach(lance ->
                    escritorArquivo.println(format(US, CONFIGURACAO_COLUNAS_CSV_HISTORICO,
                            lance.quantidade(),
                            lance.valor(),
                            format(NOME_HISTORICO, maiorIndice + UM))));
            escritorArquivo.println(format(VALOR_TOTAL, melhorResultado.getLucroMaximizado()));
        }
    }

    /**
     * Lista todos os arquivos de uma pasta
     *
     * @return lista de arquivos na pasta
     */
    private static @NotNull Set<String> listarHistoricos() throws IOException {

        Set<String> historicos = new HashSet<>();
        String colunasCabecalho = Arrays.toString(COLUNAS_HISTORICO)
                .replaceAll(REGEX_REPLACE_BARRAS, SEM_ESPACO)
                .replaceAll(REGEX_REPLACE_VIRGULA, VIRGULA);

        try (BufferedReader leitorArquivo = new BufferedReader(new FileReader(CAMINHO_ARQUIVO_HISTORICO + EXTENSAO_CSV))) {
            leitorArquivo.lines()
                    .filter(linha -> linha != null && !linha.equals(colunasCabecalho) && !linha.equals(SEPARADOR_HISTORICOS) && !linha.contains(REGEX_VALOR_TOTAL))
                    .map(linha -> linha.split(VIRGULA)[DOIS])
                    .forEach(historicos::add);
        } catch (Exception e) {
            logger.info(format(MSG_ARQUIVO_NAO_ENCONTRADO, e.getLocalizedMessage()));
        }
        return historicos;
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
