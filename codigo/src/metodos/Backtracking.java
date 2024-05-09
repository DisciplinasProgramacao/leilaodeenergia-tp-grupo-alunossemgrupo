package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import lombok.AllArgsConstructor;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;

import static enums.AlgoritmosEnums.BACKTRACKING;
import static java.util.Objects.isNull;
import static util.constantes.ConstantesNumeros.UM;

@AllArgsConstructor
public class Backtracking implements Algoritmo {

    /**
     * @return algoritmo de referência
     */
    @Override
    public String algoritmo() {
        return BACKTRACKING.toString();
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
            @NotNull MelhorResultado melhorResultado, List<Lance> todosLances, @NotNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {

        int qtdeSelecionada = lancesSelecionados.stream()
                .mapToInt(Lance::quantidade).sum();
//        Se a quantidade de lances selecionados somar uma quantidade maior do que a disponível, podar, pois não é um resultado aceitável
        if (qtdeSelecionada >= melhorResultado.getProdutora().quantidadeDisponivel())
            return;
//        Se já percorreu todos os lances em uma determinada busca, e a quantidade selecionada não ultrapassa a disponível, verifica se o lucro aumentou
        if (indice == todosLances.size()) {
            if (lucroAtual > melhorResultado.getLucroMaximizado()) {
                melhorResultado.setLucroMaximizado(lucroAtual);
                melhorResultado.setLancesSelecionados(new HashSet<>(lancesSelecionados));
            }
            return;
        }
//        Se não percorreu ainda todos os lances, segue a busca adicionado o lance do índice atual à lista de lances selecionados
        Lance lanceAnalisado = todosLances.get(indice);

        if (!isNull(lanceAnalisado)) {
            lancesSelecionados.add(lanceAnalisado);
            executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual + lanceAnalisado.valor());
            lancesSelecionados.remove(lancesSelecionados.size() - UM);
        }
        executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual);
    }
}
