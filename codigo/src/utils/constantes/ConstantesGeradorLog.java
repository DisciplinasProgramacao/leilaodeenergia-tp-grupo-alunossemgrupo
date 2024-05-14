package utils.constantes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantesGeradorLog {

    public static final String CONFIGURACAO_COLUNAS_CSV_EXECUCAO = "%s,%s,%d,%d,%d,%2d,%d,%d,%d,%d";

    public static final String CONFIGURACAO_COLUNAS_CSV_HISTORICO = "%s,%s,%s";

    public static final String[] COLUNAS_EXECUCAO = {
            "Algoritmo", "Data de execucao", "Qtde lances", "Qtde lances selecionados", "Melhor resultado", "Tempo execucao (seg)",
            "Cores disponiveis", "Memoria total (Mb)", "Memoria maxima (Mb)", "Memoria liberada (Mb)"
    };

    public static final String[] COLUNAS_HISTORICO = {
            "Quantidade requisitada", "Valor ofertado", "Historico"
    };

    public static final String SEPARADOR_HISTORICOS = "-----,------";

    public static final String CAMINHO_ARQUIVO_EXECUCAO = "logs/execucao/exec-log";

    public static final String CAMINHO_ARQUIVO_HISTORICO = "logs/historico/hist-log";

    public static final String NOME_HISTORICO = "hist-%d";

    public static final String EXTENSAO_CSV = ".xls";

    public static final String VIRGULA = ",";

    public static final String REGEX_SPLIT = "[-.]";

    public static final String REGEX_REPLACE_BARRAS = "[\\[\\]]";

    public static final String REGEX_VALOR_TOTAL = "Valor total";

    public static final String REGEX_REPLACE_VIRGULA = ", ";

    public static final String SEM_ESPACO = "";

    public static final String VALOR_TOTAL = "Valor total, %s";

    public static final String INICIO_ALGORITMO = "Executando algoritmo: %s | Quantidade compradoras: %s";

    public static final String FIM_ALGORITMO = "Algoritmo executado com sucesso!";

}
