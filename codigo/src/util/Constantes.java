package util;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class Constantes {

    public static final String CONFIGURACAO_COLUNAS = "%-25s | %-35s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s";

    public static final String[] COLUNAS = {
            "Algoritmo", "Data de execução", "Quantidade dados", "Tempo execução (seg)", "Cores disponíveis", "Memória total (GB)", "Memória máxima (GB)", "Memória liberada (GB)"
    };

    public static final String CAMINHO_ARQUIVO = "logs/exec-log.txt";

    public static final double NANO_PARA_MS = 1_000_000d;

    public static final double NANO_PARA_SEG = 1_000d;

    public static final String MSG_ERRO_ARQUIVO = format("erro ao salvar o arquivo no caminho: %s", CAMINHO_ARQUIVO);

    public static final String MSG_ERRO_PESSOA = "erro ao criar objeto Pessoa";

    public static final String NOME = "Nome";
}
