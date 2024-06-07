package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import metodos.interfaces.Algoritmo;
// import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static enums.AlgoritmosEnums.PROGRAMACAO_DINAMICA;
import static utils.constantes.ConstantesNumeros.UM;

@AllArgsConstructor
public class ProgramacaoDinamica implements Algoritmo {

    /**
     * @return algoritmo de referência
     */
    @Override
    public AlgoritmosEnums algoritmo() {
        return PROGRAMACAO_DINAMICA;
    }

    /**
     * Executa método de programação dinâmica
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

        // Mapa para armazenar resultados intermediários
        Map<String, Integer> memo = new HashMap<>();
        List<Lance> resultado = new ArrayList<>();
        int lucroMaximizado = dp(melhorResultado, todosLances, indice, 0, memo, resultado);

        melhorResultado.setLucroMaximizado(lucroMaximizado);
        melhorResultado.setLancesSelecionados(new ArrayList<>(resultado));
    }

    private int dp(
            @NonNull MelhorResultado melhorResultado, List<Lance> todosLances, int indice, int qtdeSelecionada,
            Map<String, Integer> memo, List<Lance> resultado) {

        if (qtdeSelecionada > melhorResultado.getProdutora().quantidadeDisponivel())
            return Integer.MIN_VALUE;

        if (indice == todosLances.size()) {
            return 0;
        }

        String key = indice + "-" + qtdeSelecionada;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        Lance lanceAnalisado = todosLances.get(indice);

        // Escolhe o lance atual
        resultado.add(lanceAnalisado);
        int incluir = lanceAnalisado.valor() + dp(melhorResultado, todosLances, indice + 1,
                qtdeSelecionada + lanceAnalisado.quantidade(), memo, resultado);
        resultado.remove(resultado.size() - 1);

        // Não escolhe o lance atual
        int excluir = dp(melhorResultado, todosLances, indice + 1, qtdeSelecionada, memo, resultado);

        int maxLucro = Math.max(incluir, excluir);

        memo.put(key, maxLucro);

        return maxLucro;
    }
}
