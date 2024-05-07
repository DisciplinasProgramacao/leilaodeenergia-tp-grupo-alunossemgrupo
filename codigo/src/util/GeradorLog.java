package util;

import entidades.Sistema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Math.pow;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static util.Constantes.*;

public class GeradorLog {
    /**
     * Gera o log de execução do algoritmo
     *
     * @param algoritmo algoritmo implementado
     * @param qtdeDados quantidade de dados utilizados
     * @param tempoExec tempo de execução
     * @throws IOException lança exceção caso ocorra erro na escrita do arquivo de logs
     */
    public static void gerarLog(String algoritmo, int qtdeDados, Sistema sistema, double tempoExec) throws IOException {
        try {
            File arquivo = new File(Constantes.CAMINHO_ARQUIVO);

            if (arquivo.exists()) {
                try (PrintWriter printWriter = new PrintWriter(new FileWriter(arquivo, true))) {
                    printWriter.println(format(CONFIGURACAO_COLUNAS,
                            algoritmo,
                            now(),
                            qtdeDados,
                            tempoExec,
                            sistema.coresDisponiveis(),
//                            todo: finalizar
                            sistema.memoriaTotal()/1000000,
                            sistema.memoriaMaxima()/1000000,
                            sistema.memoriaLiberada()/1000000));
                }
            } else {
                try (PrintWriter printWriter = new PrintWriter(CAMINHO_ARQUIVO)) {
                    printWriter.println(format(CONFIGURACAO_COLUNAS, (Object[]) COLUNAS));
                    printWriter.println(format(CONFIGURACAO_COLUNAS,
                      algoritmo,
                            now(),
                            qtdeDados,
                            tempoExec,
                            sistema.coresDisponiveis(),
                            sistema.memoriaTotal(),
                            sistema.memoriaMaxima(),
                            sistema.memoriaLiberada()));
                }
            }
        } catch (IOException e) {
            throw new IOException(MSG_ERRO_ARQUIVO);
        }
    }
}
