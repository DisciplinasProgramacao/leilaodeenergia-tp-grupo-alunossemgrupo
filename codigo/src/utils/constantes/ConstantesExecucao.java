package utils.constantes;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static utils.constantes.ConstantesNumeros.*;

@UtilityClass
public class ConstantesExecucao {

    public static final String NOME_COMPRADORA = "Compradora %s";

    public static final String NOME_PRODUTORA = "Produtora";

    public static final Long ID_PRODUTORA = UM_L;

    // Quantidade de vezes que cada cenário de teste (quantidade de lances) será testado
    public static final Integer QUANTIDADE_DE_TESTES_POR_MASSA = DEZ;

    // Limite de tempo em segundos para o algoritmo de Backtracking
    public static final Integer LIMITE_DE_TEMPO_PERMITIDO = TRINTA;

    //    Representa a capacidade mínima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MINIMA_COMPRADORA = MIL;

    // Representa a quantidade mínima que um lance pode ter
    public static final Integer QUANTIDADE_MINIMA_LANCE = TREZENTOS;

    //    Representa a capacidade máxima que uma compradora pode ter de quantidade
    public static final Integer QUANTIDADE_MAXIMA_COMPRADORA = MIL_E_QUINHENTOS;

    //    Representa a capacidade que a produtora vai ter de quantidade
    public static final Integer QUANTIDADE_DISPONIVEL_PRODUTORA = OITO_MIL;

    // Quantidade máxima de lances que uma compradora pode ter
    public static final Integer QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA = UM;

    // Valor mínimo por um lance de uma compradora
    public static final Integer VALOR_MINIMO_COMPRADORA = QUINHENTOS;

    // Valor máximo por um lance de uma compradora
    public static final Integer VALOR_MAXIMO_COMPRADORA = MIL_E_QUINHENTOS;

    // Conjunto um de quantidades passadas pelo prof. Caram
    public static List<Integer> quantidadesConjuntoUm = new ArrayList<>();

    // Conjunto um de valores passados pelo prof. Caram
    public static List<Integer> valoresConjuntoUm = new ArrayList<>();

    // Conjunto dois de quantidades passadas pelo prof. Caram
    public static List<Integer> quantidadesConjuntoDois = new ArrayList<>();

    // Conjunto dois de valores passados pelo prof. Caram
    public static List<Integer> valoresConjuntoDois = new ArrayList<>();

    static {
        // Conjunto um
        quantidadesConjuntoUm.add(428);
        quantidadesConjuntoUm.add(430);
        quantidadesConjuntoUm.add(410);
        quantidadesConjuntoUm.add(385);
        quantidadesConjuntoUm.add(399);
        quantidadesConjuntoUm.add(382);
        quantidadesConjuntoUm.add(416);
        quantidadesConjuntoUm.add(436);
        quantidadesConjuntoUm.add(416);
        quantidadesConjuntoUm.add(423);
        quantidadesConjuntoUm.add(400);
        quantidadesConjuntoUm.add(406);
        quantidadesConjuntoUm.add(403);
        quantidadesConjuntoUm.add(390);
        quantidadesConjuntoUm.add(387);
        quantidadesConjuntoUm.add(390);
        quantidadesConjuntoUm.add(430);
        quantidadesConjuntoUm.add(397);
        quantidadesConjuntoUm.add(392);
        quantidadesConjuntoUm.add(393);
        quantidadesConjuntoUm.add(439);
        quantidadesConjuntoUm.add(403);
        quantidadesConjuntoUm.add(415);
        quantidadesConjuntoUm.add(387);
        quantidadesConjuntoUm.add(417);
        valoresConjuntoUm.add(1188);
        valoresConjuntoUm.add(1043);
        valoresConjuntoUm.add(1565);
        valoresConjuntoUm.add(1333);
        valoresConjuntoUm.add(1214);
        valoresConjuntoUm.add(1498);
        valoresConjuntoUm.add(1540);
        valoresConjuntoUm.add(1172);
        valoresConjuntoUm.add(1386);
        valoresConjuntoUm.add(1097);
        valoresConjuntoUm.add(1463);
        valoresConjuntoUm.add(1353);
        valoresConjuntoUm.add(1568);
        valoresConjuntoUm.add(1228);
        valoresConjuntoUm.add(1542);
        valoresConjuntoUm.add(1206);
        valoresConjuntoUm.add(1175);
        valoresConjuntoUm.add(1492);
        valoresConjuntoUm.add(1293);
        valoresConjuntoUm.add(1533);
        valoresConjuntoUm.add(1149);
        valoresConjuntoUm.add(1277);
        valoresConjuntoUm.add(1624);
        valoresConjuntoUm.add(1280);
        valoresConjuntoUm.add(1330);
        // Conjunto dois
        quantidadesConjuntoDois.add(313);
        quantidadesConjuntoDois.add(398);
        quantidadesConjuntoDois.add(240);
        quantidadesConjuntoDois.add(433);
        quantidadesConjuntoDois.add(301);
        quantidadesConjuntoDois.add(297);
        quantidadesConjuntoDois.add(232);
        quantidadesConjuntoDois.add(614);
        quantidadesConjuntoDois.add(558);
        quantidadesConjuntoDois.add(495);
        quantidadesConjuntoDois.add(310);
        quantidadesConjuntoDois.add(213);
        quantidadesConjuntoDois.add(213);
        quantidadesConjuntoDois.add(346);
        quantidadesConjuntoDois.add(385);
        quantidadesConjuntoDois.add(240);
        quantidadesConjuntoDois.add(483);
        quantidadesConjuntoDois.add(487);
        quantidadesConjuntoDois.add(709);
        quantidadesConjuntoDois.add(358);
        quantidadesConjuntoDois.add(467);
        quantidadesConjuntoDois.add(363);
        quantidadesConjuntoDois.add(279);
        quantidadesConjuntoDois.add(589);
        quantidadesConjuntoDois.add(476);
        valoresConjuntoDois.add(1496);
        valoresConjuntoDois.add(1768);
        valoresConjuntoDois.add(1210);
        valoresConjuntoDois.add(2327);
        valoresConjuntoDois.add(1263);
        valoresConjuntoDois.add(1499);
        valoresConjuntoDois.add(1209);
        valoresConjuntoDois.add(2342);
        valoresConjuntoDois.add(2983);
        valoresConjuntoDois.add(2259);
        valoresConjuntoDois.add(1381);
        valoresConjuntoDois.add(961);
        valoresConjuntoDois.add(1115);
        valoresConjuntoDois.add(1552);
        valoresConjuntoDois.add(2023);
        valoresConjuntoDois.add(1234);
        valoresConjuntoDois.add(2828);
        valoresConjuntoDois.add(2617);
        valoresConjuntoDois.add(2328);
        valoresConjuntoDois.add(1847);
        valoresConjuntoDois.add(2038);
        valoresConjuntoDois.add(2007);
        valoresConjuntoDois.add(1311);
        valoresConjuntoDois.add(3164);
        valoresConjuntoDois.add(2480);
    }
}
