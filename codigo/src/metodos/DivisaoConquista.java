package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.AllArgsConstructor;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static enums.AlgoritmosEnums.DIVISAO_CONQUISTA;

@AllArgsConstructor
public class DivisaoConquista implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return DIVISAO_CONQUISTA;
    }

    @Override
    public void executar(
            @NotNull MelhorResultado melhorResultado, List<Lance> todosLances, @NotNull List<Lance> lancesSelecionados, int inicio, int fim) {

        // Condição de término da recursão
        if (inicio > fim) {
            return;
        }

        // Se há apenas um lance no intervalo, avalie diretamente
        if (inicio == fim) {
            avaliaLance(melhorResultado, lancesSelecionados, todosLances.get(inicio));
            return;
        }

        int meio = inicio + (fim - inicio) / 2;

        // Conquista a parte esquerda
        executar(melhorResultado, todosLances, lancesSelecionados, inicio, meio);

        // Conquista a parte direita
        executar(melhorResultado, todosLances, lancesSelecionados, meio + 1, fim);
    }

    private void avaliaLance(@NotNull MelhorResultado melhorResultado, List<Lance> lancesSelecionados, Lance lanceAtual) {
        lancesSelecionados.add(lanceAtual);
        int lucroAtual = lancesSelecionados.stream().mapToInt(Lance::valor).sum();
        int qtdeAtual = lancesSelecionados.stream().mapToInt(Lance::quantidade).sum();
        if (qtdeAtual <= melhorResultado.getProdutora().quantidadeDisponivel() && lucroAtual > melhorResultado.getLucroMaximizado()) {
            melhorResultado.setLucroMaximizado(lucroAtual);
            melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }
        lancesSelecionados.remove(lanceAtual);
    }
}
