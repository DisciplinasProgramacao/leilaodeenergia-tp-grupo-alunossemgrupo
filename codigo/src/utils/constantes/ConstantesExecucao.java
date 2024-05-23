package utils.constantes;

import lombok.experimental.UtilityClass;

import static utils.constantes.ConstantesNumeros.*;

@UtilityClass
public class ConstantesExecucao {

    public static final String NOME_COMPRADORA = "Compradora %s";

    public static final String NOME_PRODUTORA = "Produtora";

    public static final Long ID_PRODUTORA = UM_L;

    //    Número de execuções que serão feitas para cada quantidade de compradoras, a fim de se obter o tempo médio de execução
    public static final Integer EXECUCOES_POR_MASSA = DEZ;

    //    Representa a capacidade mínima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MINIMA_COMPRADORA = DEZ;

    //    Representa a capacidade máxima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MAXIMA_COMPRADORA = CEM;

    //    Representa a capacidade que a produtora vai ter de quantidade
    public static final Integer QUANTIDADE_DISPONIVEL_PRODUTORA = DUZENTOS;

    // Quantidade de compradoras que serão geradas
    public static final Integer[] QUANTIDADE_COMPRADORAS = {DEZ, VINTE, TRINTA, QUARENTA, CINQUENTA};

    // Quantidade máxima de lances que uma compradora pode ter
    public static final Integer QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA = UM;

}