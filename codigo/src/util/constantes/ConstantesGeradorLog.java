package util.constantes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantesGeradorLog {

    public static final String CONFIGURACAO_COLUNAS = "%-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s";

    public static final String[] COLUNAS = {
            "Algoritmo", "Data de execução", "Quantidade dados", "Tempo execução (seg)",
            "Cores disponíveis", "Memória total (Mb)", "Memória máxima (Mb)", "Memória liberada (Mb)"
    };

    public static final String CAMINHO_ARQUIVO = "logs/exec-log.txt";

}
