import entidades.Compradora;
import entidades.MelhorResultado;

import java.util.List;

import static enums.AlgoritmosEnums.*;
import static metodos.interfaces.Algoritmo.algoritmosImplementados;
import static utils.constantes.ConstantesExecucao.QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA;
import static utils.constantes.ConstantesNumeros.*;
import static utils.geradores.GeradorCompradoras.gerarCompradoras;

public class Main {

    public static void main(String[] args) {

        algoritmosImplementados.forEach(algoritmo -> {
            List<Compradora> compradoras = gerarCompradoras(DEZ, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA);
            MelhorResultado melhorResultado;

//          Gerar conjuntos de teste de tamanho crescente, a partir de 10 interessadas e incrementando de 1 em 1, até atingir
//          um tamanho T que não consiga ser resolvido em até 30 segundos pelo algoritmo. Na busca do tempo limite de 30
//          segundos, faça o teste com 10 conjuntos de cada tamanho, contabilizando a média das execuções.
            if (algoritmo.algoritmo().equals(BACKTRACKING)) {
                boolean atingiuTempoLimite = false;

                while (!atingiuTempoLimite) {
                    for (int i = UM; i <= DEZ; i++) {
                        melhorResultado = algoritmo.executarAlgoritmo(compradoras, algoritmo.algoritmo());
                        if (melhorResultado.getContador().getFim() - melhorResultado.getContador().getInicio() > TRINTA)
                            atingiuTempoLimite = true;
                    }
                    compradoras.addAll(gerarCompradoras(UM, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA));
                }
            }

//          Para este teste, utilize os mesmos conjuntos de tamanho T encontrados no backtracking. Em seguida, aumente os
//          tamanhos dos conjuntos de T em T até atingir o tamanho 10T, sempre executando 10 testes de cada tamanho para
//          utilizar a média.
            if (algoritmo.algoritmo().equals(GULOSO)) {
//                todo: preencher com o seu código
            }

//          Neste caso, utilize os mesmos conjuntos de tamanho T utilizados no backtracking.
            if (algoritmo.algoritmo().equals(DIVISAO_CONQUISTA)) {
//                todo: preencher com o seu código
            }

//          Aqui, utilize os mesmos conjuntos de teste do algoritmo guloso
            if (algoritmo.algoritmo().equals(PROGRAMACAO_DINAMICA)) {
//                todo: preencher com o seu código
            }
        });
    }
}
