package utils.constantes;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;
import static utils.constantes.ConstantesGeradorLog.CAMINHO_ARQUIVO_EXECUCAO;

@UtilityClass
public class ConstantesErros {

    public static final String MSG_ERRO_ARQUIVO = format("erro ao salvar o arquivo no caminho: %s", CAMINHO_ARQUIVO_EXECUCAO);
}
