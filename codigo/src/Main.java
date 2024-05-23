import entidades.Compradora;
import entidades.Lance;
import entidades.MelhorResultado;

import java.util.List;

import static enums.AlgoritmosEnums.BACKTRACKING;
import static java.util.Arrays.stream;
import static metodos.interfaces.Algoritmo.algoritmosImplementados;
import static utils.constantes.ConstantesExecucao.*;
import static utils.constantes.ConstantesNumeros.*;
import static utils.geradores.GeradorCompradoras.gerarCompradoras;

public class Main {

    public static void main(String[] args) {

        algoritmosImplementados.forEach(algoritmo -> {
            List<Compradora> compradoras = gerarCompradoras(DEZ, QUANTIDADE_MAXIMA_LANCE_POR_COMPRADORA);
            MelhorResultado melhorResultado;
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
        });

    }
}
