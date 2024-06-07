package metodos;

import java.util.*;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import metodos.interfaces.Algoritmo;

import static enums.AlgoritmosEnums.GULOSO1;
import static enums.AlgoritmosEnums.GULOSO2;

public class AlgoritmoGuloso2 implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return GULOSO2;
    }

    @Override
    public void executar(MelhorResultado resultado, List<entidades.Lance> todosLances, List<entidades.Lance> lancesSelecionados, int indice, int lucroAtual) {
        List<Lance> lancesMutaveis = new ArrayList<>(todosLances);
        // Ordenar os lances em ordem decrescente de valor por megawatt
        System.out.println(lancesMutaveis.toArray().length);
        Collections.sort(lancesMutaveis, new Comparator<Lance>() {
            @Override
            public int compare(Lance l1, Lance l2) {
                return Double.compare(l1.quantidade(), l2.quantidade());
            }
        });

        int energiaRestante = 1000;

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
        int valorTotal = 0;
        resultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));

        for (Lance lance : lancesSelecionados) {
            System.out.println("Interessada: " + lance.idCompradora() + ", Megawatts: " + lance.quantidade() + ", Valor: " + lance.valor());
            valorTotal += lance.valor();
        }


        System.out.println("Valor total obtido: " + valorTotal + " Energia restante: " + energiaRestante + " MW");
    }
}
