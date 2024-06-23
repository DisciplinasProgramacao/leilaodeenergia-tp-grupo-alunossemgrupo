package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static enums.AlgoritmosEnums.BACKTRACKING;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
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
    public void executar(@NonNull MelhorResultado melhorResultado, @NotNull List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {

        int qtdeSelecionada = lancesSelecionados.stream()
                .mapToInt(Lance::quantidade)
                .sum();

        if (lucroAtual > melhorResultado.getLucroMaximizado()) {
            melhorResultado.setLucroMaximizado(lucroAtual);
            melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }
        if (indice >= todosLances.size() || qtdeSelecionada >= melhorResultado.getProdutora().quantidadeDisponivel()) {
            return;
        }
        int menorValor = MAX_VALUE;

        for (int i = indice; i < todosLances.size(); i++) {
            menorValor = min(menorValor, todosLances.get(i).quantidade());
        }
        if (qtdeSelecionada + menorValor > melhorResultado.getProdutora().quantidadeDisponivel()) {
            return;
        }
        for (int i = indice; i < todosLances.size(); i++) {
            Lance lance = todosLances.get(i);

            if (qtdeSelecionada + lance.quantidade() < melhorResultado.getProdutora().quantidadeDisponivel()) {
                lancesSelecionados.add(lance);
                executar(melhorResultado, todosLances, lancesSelecionados, i + UM, lucroAtual + lance.valor());
                lancesSelecionados.remove(lancesSelecionados.size() - UM);
            }
        }
    }


}
