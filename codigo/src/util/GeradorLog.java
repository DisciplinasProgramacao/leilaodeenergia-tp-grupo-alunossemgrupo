package util;

import entidades.Sistema;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static entidades.Sistema.instancia;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static util.constantes.ConstantesFormatos.FORMATO_DATA;
import static util.constantes.ConstantesErros.MSG_ERRO_ARQUIVO;
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
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            Sistema sistema = instancia();

            if (arquivo.exists()) {
                try (PrintWriter escritorArquivo = new PrintWriter(new FileWriter(arquivo, true))) {
                    escritorArquivo.println(format(CONFIGURACAO_COLUNAS,
                                    algoritmo,
                                    now().format(FORMATO_DATA),
                                    qtdeDados,
                                    tempoExec,
                                    sistema.getCoresDisponiveis(),
                                    sistema.getMemoriaTotal(),
                                    sistema.getMemoriaMaxima(),
                                    sistema.getMemoriaLiberada()
                            )
                    );
                }
            } else {
                try (PrintWriter escritorArquivo = new PrintWriter(CAMINHO_ARQUIVO)) {
                    escritorArquivo.println(format(CONFIGURACAO_COLUNAS, (Object[]) COLUNAS));
                    escritorArquivo.println(format(CONFIGURACAO_COLUNAS,
                                    algoritmo,
                                    now().format(FORMATO_DATA),
                                    qtdeDados,
                                    tempoExec,
                                    sistema.getCoresDisponiveis(),
                                    sistema.getMemoriaTotal(),
                                    sistema.getMemoriaMaxima(),
                                    sistema.getMemoriaLiberada()
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new IOException(MSG_ERRO_ARQUIVO);
        }
    }
}
