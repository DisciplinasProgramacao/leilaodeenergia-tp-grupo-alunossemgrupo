package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static enums.AlgoritmosEnums.GULOSO_DECRESCENTE;
import static java.util.Comparator.comparingDouble;
import static utils.constantes.ConstantesNumeros.ZERO;

public class AlgoritmoGulosoDecrescente implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return GULOSO_DECRESCENTE;
    }

    @Override
    public void executar(@NotNull MelhorResultado resultado, List<entidades.Lance> todosLances, List<entidades.Lance> lancesSelecionados, int indice, int lucroAtual) {

        List<Lance> lancesMutaveis = new ArrayList<>(todosLances);
        lancesMutaveis.sort(comparingDouble(Lance::valorPorMegawatt).reversed());

        int energiaRestante = resultado.getProdutora().quantidadeDisponivel();

        for (Lance lance : lancesMutaveis) {
            lancesSelecionados.forEach(lanceSelecionado -> {
                if (Objects.equals(lance.id(), lanceSelecionado.id())) {
                    lancesSelecionados.remove(lanceSelecionado.id());
                }
            });
            if (lance.quantidade() <= energiaRestante) {
                lancesSelecionados.add(lance);
                energiaRestante -= lance.quantidade();
            }
        }
        int valorTotal = ZERO;
        resultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));

        for (Lance lance : lancesSelecionados) {
            valorTotal += lance.valor();
        }
        resultado.setLucroMaximizado(valorTotal);
        resultado.setQuantidadeVendida(resultado.getProdutora().quantidadeDisponivel() - energiaRestante);
    }
}
