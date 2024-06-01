package utils.constantes;

import lombok.experimental.UtilityClass;

import static utils.constantes.ConstantesNumeros.*;

@UtilityClass
public class ConstantesExecucao {

    public static final String NOME_COMPRADORA = "Compradora %s";

    public static final String NOME_PRODUTORA = "Produtora";

    public static final Long ID_PRODUTORA = UM_L;

    //    Representa a capacidade mínima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MINIMA_COMPRADORA = DEZ;

    //    Representa a capacidade máxima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MAXIMA_COMPRADORA = CEM;

    //    Representa a capacidade que a produtora vai ter de quantidade
    public static final Integer QUANTIDADE_DISPONIVEL_PRODUTORA = DUZENTOS;

    // Quantidade máxima de lances que uma compradora pode ter
    public static final Integer QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA = UM;

}
