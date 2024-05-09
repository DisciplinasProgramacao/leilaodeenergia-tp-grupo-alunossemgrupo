package util.geradores;

import entidades.Sistema;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static entidades.Sistema.instancia;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.time.LocalDateTime.now;
import static java.util.Locale.US;
import static util.constantes.ConstantesErros.MSG_ERRO_ARQUIVO;
import static util.constantes.ConstantesFormatos.FORMATO_DATA;
import static util.constantes.ConstantesGeradorLog.*;

@UtilityClass
public class GeradorLog {

    /**
     * Gera o log de execução do algoritmo
     *
     * @param algoritmo algoritmo implementado
     * @param qtdeDados quantidade de dados utilizados
     * @param tempoExec tempo de execução
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    public static void gerarLog(String algoritmo, int qtdeDados, double tempoExec) throws IOException {
        File arquivo = new File(CAMINHO_ARQUIVO + EXTENSAO_CSV);
        Sistema sistema = instancia();

        try {
            if (!arquivo.exists()) {
                criarArquivoECabecalho();
            }

            escreverNoArquivo(algoritmo, qtdeDados, tempoExec, sistema);
        } catch (IOException e) {
            throw new IOException(MSG_ERRO_ARQUIVO);
        }
    }

    /**
     * Cria cabeçalho do arquivo de logs
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    private static void criarArquivoECabecalho() throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(CAMINHO_ARQUIVO + EXTENSAO_CSV)) {
            escritorArquivo.println(join(VIRGULA, COLUNAS));
        }
    }

    /**
     * Escreve uma nova linha de dados no arquivo de logs
     *
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    private static void escreverNoArquivo(String algoritmo, int qtdeDados, double tempoExec, @NotNull Sistema sistema) throws IOException {
        try (PrintWriter escritorArquivo = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO + EXTENSAO_CSV, true))) {
            escritorArquivo.println(format(US, CONFIGURACAO_COLUNAS_CSV,
                    algoritmo,
                    now().format(FORMATO_DATA),
                    qtdeDados,
                    tempoExec,
                    sistema.getCoresDisponiveis(),
                    sistema.getMemoriaTotal(),
                    sistema.getMemoriaMaxima(),
                    sistema.getMemoriaLiberada()
            ));
        }
    }
}
