package util.constantes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantesGeradorLog {

    public static final String CONFIGURACAO_COLUNAS_TXT = "%-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s";

    public static final String CONFIGURACAO_COLUNAS_CSV = "%s,%s,%d,%.2f,%d,%d,%d,%d";

    public static final String[] COLUNAS = {
            "Algoritmo", "Data de execução", "Quantidade lances", "Tempo execução (seg)",
            "Cores disponíveis", "Memória total (Mb)", "Memória máxima (Mb)", "Memória liberada (Mb)"
    };

    public static final String CAMINHO_ARQUIVO = "logs/exec-log";

    public static final String EXTENSAO_CSV = ".csv";

    public static final String VIRGULA = ",";

}
