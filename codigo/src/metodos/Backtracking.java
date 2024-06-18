package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import metodos.interfaces.Algoritmo;

import java.util.ArrayList;
import java.util.List;

import static enums.AlgoritmosEnums.BACKTRACKING;
import static java.util.Objects.isNull;
import static utils.constantes.ConstantesNumeros.UM;

@AllArgsConstructor
public class Backtracking implements Algoritmo {

    /**
     * @return algoritmo de referência
     */
    @Override
    public AlgoritmosEnums algoritmo() {
        return BACKTRACKING;
    }

    /**
     * Executa método de backtracking
     *
     * @param melhorResultado    objeto do tipo MelhorResultado
     * @param todosLances        lista de lances feitos
     * @param lancesSelecionados lista de lances selecionados pelo método
     * @param indice             índice de referência
     * @param lucroAtual         lucro atual obtido pelo algoritmo
     */
    @Override
    public void executar(
            @NonNull MelhorResultado melhorResultado, List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {

        int qtdeSelecionada = lancesSelecionados.stream()
                .mapToInt(Lance::quantidade)
                .sum();
        if (qtdeSelecionada > melhorResultado.getProdutora().quantidadeDisponivel())
            return;

        if (indice == todosLances.size()) {
            if (lucroAtual > melhorResultado.getLucroMaximizado()) {
                melhorResultado.setLucroMaximizado(lucroAtual);
                melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
            }
            return;
        }
        Lance lanceAnalisado = todosLances.get(indice);

        if (!isNull(lanceAnalisado)) {
            lancesSelecionados.add(lanceAnalisado);
            executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual + lanceAnalisado.valor());
            lancesSelecionados.remove(lancesSelecionados.size() - UM);
        }
        executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual);
    }
}
