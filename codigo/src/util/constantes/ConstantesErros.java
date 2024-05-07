package util.constantes;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;
import static util.constantes.ConstantesGeradorLog.CAMINHO_ARQUIVO;

@UtilityClass
public class ConstantesErros {

    public static final String MSG_ERRO_ARQUIVO = format("erro ao salvar o arquivo no caminho: %s", CAMINHO_ARQUIVO);
}
