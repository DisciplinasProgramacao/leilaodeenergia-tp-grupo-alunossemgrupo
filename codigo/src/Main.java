import entidades.Compradora;
import entidades.Lance;
import entidades.MelhorResultado;

import java.util.List;

import static enums.AlgoritmosEnums.BACKTRACKING;
import static java.util.Arrays.stream;
import static metodos.interfaces.Algoritmo.algoritmosImplementados;
import static utils.constantes.ConstantesExecucao.*;
import static utils.constantes.ConstantesNumeros.DEZ;
import static utils.geradores.GeradorCompradoras.gerarCompradoras;

public class Main {

    public static void main(String[] args) {

        algoritmosImplementados.forEach(algoritmo -> {

            List<Compradora> compradoras = gerarCompradoras(DEZ, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA);

            if (algoritmo.algoritmo().equals(BACKTRACKING)) {
                int contador = 0;
                boolean atingiuLimiteDeTempo = false;

                do {
                    contador = 0;
                    for (int i = 0; i <= 10; i++) {
                        MelhorResultado melhorResultado = algoritmo.executarAlgoritmo(compradoras, compradoras.size(), algoritmo.algoritmo(), true);
                        contador++;
                        if (melhorResultado.getContador().getFim() - melhorResultado.getContador().getInicio() > 30)
                            atingiuLimiteDeTempo = true;
                    }
                } while (contador <= DEZ && !atingiuLimiteDeTempo);
            }
        });


//        stream(QUANTIDADE_COMPRADORAS).forEach(qtde -> {
//                    List<Compradora> compradoras = gerarCompradoras(qtde, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA);
//                    algoritmosImplementados.forEach(algoritmo -> algoritmo.executarAlgoritmo(compradoras, qtde, algoritmo.algoritmo(), true));
//                }
//        );
    }
}
