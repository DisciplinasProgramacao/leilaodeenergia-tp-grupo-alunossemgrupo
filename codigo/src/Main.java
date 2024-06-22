import entidades.Compradora;
import entidades.MelhorResultado;
import metodos.interfaces.Algoritmo;

import java.util.ArrayList;
import java.util.List;

import static enums.AlgoritmosEnums.*;
import static metodos.interfaces.Algoritmo.algoritmosImplementados;
import static utils.constantes.ConstantesExecucao.LIMITE_DE_TEMPO_PERMITIDO;
import static utils.constantes.ConstantesExecucao.QUANTIDADE_DE_TESTES_POR_MASSA;
import static utils.constantes.ConstantesNumeros.*;
import static utils.geradores.GeradorCompradoras.*;

public class Main {

    public static void main(String[] args) {

//        Conjuntos específicos disponibilizados pelo prof. Caram
        List<Compradora> compradorasConjuntoUm = gerarCompradorasConjuntoUm();
        List<Compradora> compradorasConjuntoDois = gerarCompradorasConjuntoDois();
        List<Compradora> compradoras = gerarCompradoras(DEZ);
        List<List<Compradora>> compradorasBacktracking = new ArrayList<>();
        List<List<Compradora>> compradorasGuloso = new ArrayList<>();
        List<List<Compradora>> compradorasDivisaoConquista = new ArrayList<>();
        List<List<Compradora>> compradorasProgramacaoDinamica = new ArrayList<>();

        for (Algoritmo algoritmo : algoritmosImplementados) {

            MelhorResultado melhorResultado;

//          Gerar conjuntos de teste de tamanho crescente, a partir de 10 interessadas e incrementando de 1 em 1, até atingir
//          um tamanho T que não consiga ser resolvido em até 30 segundos pelo algoritmo. Na busca do tempo limite de 30
//          segundos, faça o teste com 10 conjuntos de cada tamanho, contabilizando a média das execuções.
            if (algoritmo.algoritmo().equals(BACKTRACKING)) {

                boolean atingiuTempoLimite = false;

                while (!atingiuTempoLimite) {
                    for (int i = ZERO; i < QUANTIDADE_DE_TESTES_POR_MASSA; i++) {
                        melhorResultado = algoritmo.executarAlgoritmo(compradoras, algoritmo.algoritmo());
                        compradorasBacktracking.add(compradoras);
                        if (melhorResultado.getContador().getFim() - melhorResultado.getContador().getInicio() > LIMITE_DE_TEMPO_PERMITIDO)
                            atingiuTempoLimite = true;
                        compradoras = gerarCompradoras(compradoras.size());
                    }
                    compradoras = gerarCompradoras(compradoras.size() + UM);
                }
                algoritmo.executarAlgoritmo(compradorasConjuntoUm, algoritmo.algoritmo());
                algoritmo.executarAlgoritmo(compradorasConjuntoDois, algoritmo.algoritmo());

            }

//          Para este teste, utilize os mesmos conjuntos de tamanho T encontrados no backtracking. Em seguida, aumente os
//          tamanhos dos conjuntos de T em T até atingir o tamanho 10T, sempre executando 10 testes de cada tamanho para
//          utilizar a média.
            if (algoritmo.algoritmo().equals(GULOSO1) || algoritmo.algoritmo().equals(GULOSO2)) {

                compradorasGuloso = new ArrayList<>(compradorasBacktracking);
                compradorasBacktracking.forEach(listaCompradoras -> algoritmo.executarAlgoritmo(listaCompradoras, algoritmo.algoritmo()));

                int limiteBacktracking = compradorasBacktracking.size() / QUANTIDADE_DE_TESTES_POR_MASSA + DEZ;
                compradoras = gerarCompradoras(limiteBacktracking);

                while (compradorasGuloso.size() < (QUANTIDADE_DE_TESTES_POR_MASSA * limiteBacktracking * DEZ)) {
                    for (int i = ZERO; i < QUANTIDADE_DE_TESTES_POR_MASSA; i++) {
                        compradorasGuloso.add(compradoras);
                        algoritmo.executarAlgoritmo(compradoras, algoritmo.algoritmo());
                        compradoras = gerarCompradoras(compradoras.size());
                    }
                    compradoras = gerarCompradoras(compradoras.size() + UM);
                }
                algoritmo.executarAlgoritmo(compradorasConjuntoUm, algoritmo.algoritmo());
                algoritmo.executarAlgoritmo(compradorasConjuntoDois, algoritmo.algoritmo());
            }

//          Neste caso, utilize os mesmos conjuntos de tamanho T utilizados no backtracking.
            if (algoritmo.algoritmo().equals(DIVISAO_CONQUISTA)) {

                compradorasDivisaoConquista = new ArrayList<>(compradorasBacktracking);
                compradorasBacktracking.forEach(listaCompradoras -> algoritmo.executarAlgoritmo(listaCompradoras, algoritmo.algoritmo()));
                algoritmo.executarAlgoritmo(compradorasConjuntoUm, algoritmo.algoritmo());
                algoritmo.executarAlgoritmo(compradorasConjuntoDois, algoritmo.algoritmo());
            }

//          Aqui, utilize os mesmos conjuntos de teste do algoritmo guloso.
            if (algoritmo.algoritmo().equals(PROGRAMACAO_DINAMICA)) {

                compradorasProgramacaoDinamica = new ArrayList<>(compradorasGuloso);
                compradorasGuloso.forEach(listaCompradoras -> algoritmo.executarAlgoritmo(listaCompradoras, algoritmo.algoritmo()));
                algoritmo.executarAlgoritmo(compradorasConjuntoUm, algoritmo.algoritmo());
                algoritmo.executarAlgoritmo(compradorasConjuntoDois, algoritmo.algoritmo());
            }
        }
    }
}
