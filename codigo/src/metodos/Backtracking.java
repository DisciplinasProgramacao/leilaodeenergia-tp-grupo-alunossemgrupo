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
        @NonNull MelhorResultado melhorResultado, @NotNull List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {

    List<Lance> lancesValidos = new ArrayList<>();

    int qtdeSelecionada = lancesSelecionados.stream()
            .mapToInt(Lance::quantidade)
            .sum();

    Integer menorValor = Integer.MAX_VALUE;

    for (int i = indice; i < todosLances.size(); i++) {
        if (todosLances.get(i).quantidade() < menorValor) {
            menorValor = todosLances.get(i).quantidade();
        }
        if (qtdeSelecionada + todosLances.get(i).quantidade() < 8000) {
            lancesValidos.add(todosLances.get(i));
        }
    }

    if (indice == todosLances.size()
        || qtdeSelecionada > melhorResultado.getProdutora().quantidadeDisponivel()
        || menorValor > (8000 - qtdeSelecionada)
        || lancesValidos.isEmpty()) {

        if (lucroAtual > melhorResultado.getLucroMaximizado()) {
            melhorResultado.setLucroMaximizado(lucroAtual);
            melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }
        return;
    }

    for (Lance lance : lancesValidos) {
        lancesSelecionados.add(lance);
        executar(melhorResultado, todosLances, lancesSelecionados, todosLances.indexOf(lance) + 1, lucroAtual + lance.valor());
        lancesSelecionados.remove(lancesSelecionados.size() - 1);
    }
}

}
