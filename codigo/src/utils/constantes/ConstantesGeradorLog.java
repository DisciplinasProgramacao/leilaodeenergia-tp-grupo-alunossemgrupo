package utils.constantes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantesGeradorLog {

    public static final String CONFIGURACAO_COLUNAS_CSV_EXECUCAO = "%s,%s,%d,%.2f,%d,%d,%d,%d";

    public static final String CONFIGURACAO_COLUNAS_CSV_HISTORICO = "%s,%s";

    public static final String[] COLUNAS_EXECUCAO = {
            "Algoritmo", "Data de execução", "Quantidade lances", "Tempo execução (seg)",
            "Cores disponíveis", "Memória total (Mb)", "Memória máxima (Mb)", "Memória liberada (Mb)"
    };

    public static final String[] COLUNAS_HISTORICO = {
            "Quantidade requisitada", "Valor ofertado"
    };

    public static final String CAMINHO_ARQUIVO_EXECUCAO = "logs/execucao/exec-log";

    public static final String CAMINHO_PASTA_HISTORICO = "logs/historico";

    public static final String CAMINHO_ARQUIVO_HISTORICO = "logs/historico/hist-%s";

    public static final String EXTENSAO_CSV = ".csv";

    public static final String VIRGULA = ",";

    public static final String REGEX_SPLIT = "[-.]";

    public static final String VALOR_TOTAL = "Valor total, %s";

    public static final String INICIO_ALGORITMO = "Executando algoritmo: %s | Quantidade compradoras: %s";

    public static final String FIM_ALGORITMO = "Algoritmo executado com sucesso!";

}
