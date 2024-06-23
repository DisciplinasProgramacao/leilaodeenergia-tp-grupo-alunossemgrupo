package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.NonNull;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static enums.AlgoritmosEnums.DIVISAO_CONQUISTA;
import static java.util.Objects.isNull;

public class DivisaoConquista implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return DIVISAO_CONQUISTA;
    }

    @Override
    public void executar(@NonNull MelhorResultado resultado, @NotNull List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {
        int qtdeSelecionada = lancesSelecionados.stream()
                .mapToInt(Lance::quantidade)
                .sum();

        if (qtdeSelecionada > resultado.getProdutora().quantidadeDisponivel() || indice >= todosLances.size()) {
            if (lucroAtual > resultado.getLucroMaximizado()) {
                resultado.setLucroMaximizado(lucroAtual);
                resultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
            }
            return;
        }

        Lance lanceAtual = todosLances.get(indice);

        if (qtdeSelecionada + lanceAtual.quantidade() <= 8000) {
            // Considerar o lance atual
            lancesSelecionados.add(lanceAtual);
            executar(resultado, todosLances, lancesSelecionados, indice + 1, lucroAtual + lanceAtual.valor());
            lancesSelecionados.remove(lanceAtual);
        }

        // Não considerar o lance atual
        executar(resultado, todosLances, lancesSelecionados, indice + 1, lucroAtual);
    }
}
