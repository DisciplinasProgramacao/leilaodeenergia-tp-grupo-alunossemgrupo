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

import static enums.AlgoritmosEnums.PROGRAMACAO_DINAMICA;
import static java.util.Objects.isNull;

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
            @NonNull MelhorResultado melhorResultado, @NotNull List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {

        int quantidadeDisponivel = melhorResultado.getProdutora().quantidadeDisponivel();
        int n = todosLances.size();
        int capacidade = 8000;

        int[] dp = new int[capacidade + 1];
        int[] selecionados = new int[capacidade + 1];

        for (int i = 0; i < n; i++) {
            Lance lance = todosLances.get(i);
            int quantidade = lance.quantidade();
            int valor = lance.valor();

            for (int j = capacidade; j >= quantidade; j--) {
                if (dp[j - quantidade] + valor > dp[j]) {
                    dp[j] = dp[j - quantidade] + valor;
                    selecionados[j] = i;
                }
            }
        }

        int maxLucro = 0;
        int melhorCapacidade = 0;

        for (int i = 0; i <= capacidade; i++) {
            if (dp[i] > maxLucro) {
                maxLucro = dp[i];
                melhorCapacidade = i;
            }
        }

        List<Lance> melhoresLances = new ArrayList<>();
        int capacidadeAtual = melhorCapacidade;

        while (capacidadeAtual > 0 && selecionados[capacidadeAtual] != 0) {
            Lance lance = todosLances.get(selecionados[capacidadeAtual]);
            melhoresLances.add(lance);
            capacidadeAtual -= lance.quantidade();
        }

        melhorResultado.setLucroMaximizado(maxLucro);
        melhorResultado.setLancesSelecionados(melhoresLances);
    }
}
