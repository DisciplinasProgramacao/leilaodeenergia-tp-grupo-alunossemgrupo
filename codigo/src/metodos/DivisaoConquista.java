package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import metodos.interfaces.Algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DivisaoConquista implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return AlgoritmosEnums.DIVISAO_CONQUISTA;
    }

    @Override
    public void executar(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int indice, int lucroAtual) {
        int quantidadeEnergiaDisponivel = resultado.getProdutora().quantidadeDisponivel();

        List<Lance> melhorSelecao = resolverDivisaoEConquista(todosLances, quantidadeEnergiaDisponivel);

        resultado.setLancesSelecionados(melhorSelecao);
        resultado.setLucroMaximizado(melhorSelecao.stream().mapToInt(Lance::valor).sum());
    }

    private List<Lance> resolverDivisaoEConquista(List<Lance> lances, int quantidadeEnergiaDisponivel) {
        if (lances.isEmpty() || quantidadeEnergiaDisponivel <= 0) {
            return new ArrayList<>();
        }

        if (lances.size() == 1) {
            Lance lance = lances.get(0);
            if (lance.quantidade() <= quantidadeEnergiaDisponivel) {
                List<Lance> result = new ArrayList<>();
                result.add(lance);
                return result;
            } else {
                return new ArrayList<>();
            }
        }

        int meio = lances.size() / 2;
        List<Lance> esquerda = resolverDivisaoEConquista(new ArrayList<>(lances.subList(0, meio)), quantidadeEnergiaDisponivel);
        List<Lance> direita = resolverDivisaoEConquista(new ArrayList<>(lances.subList(meio, lances.size())), quantidadeEnergiaDisponivel);

        return combinarListas(direita, esquerda, quantidadeEnergiaDisponivel);
    }

    private List<Lance> combinarListas(List<Lance> direita, List<Lance> esquerda, int quantidadeEnergiaDisponivel) {
        List<Lance> lancesSelecionados = new ArrayList<>();
        List<Lance> todosLances = new ArrayList<>(direita);
        todosLances.addAll(esquerda);

        // Ordenar por valor/quantidade (eficiência) para maximizar o lucro
        todosLances.sort((l1, l2) -> {
            double efficiency1 = (double) l1.valor() / l1.quantidade();
            double efficiency2 = (double) l2.valor() / l2.quantidade();
            return Double.compare(efficiency2, efficiency1);
        });

        final int[] quantidadeTotalInserida = {0};

        for (Lance lance : todosLances) {
            if (quantidadeTotalInserida[0] + lance.quantidade() <= quantidadeEnergiaDisponivel) {
                lancesSelecionados.add(lance);
                quantidadeTotalInserida[0] += lance.quantidade();
            }
        }

        // Tentar encontrar melhorias ao remover lances de menor eficiência e adicionar de maior eficiência
        boolean melhoria = true;
        while (melhoria) {
            melhoria = false;
            List<Lance> possiveisAdicionar = todosLances.stream()
                    .filter(l -> !lancesSelecionados.contains(l) && l.quantidade() + quantidadeTotalInserida[0] <= quantidadeEnergiaDisponivel)
                    .collect(Collectors.toList());

            for (Lance lanceRemover : new ArrayList<>(lancesSelecionados)) {
                for (Lance lanceAdicionar : possiveisAdicionar) {
                    if (lanceAdicionar.valor() > lanceRemover.valor() &&
                            quantidadeTotalInserida[0] - lanceRemover.quantidade() + lanceAdicionar.quantidade() <= quantidadeEnergiaDisponivel) {
                        lancesSelecionados.remove(lanceRemover);
                        lancesSelecionados.add(lanceAdicionar);
                        quantidadeTotalInserida[0] = quantidadeTotalInserida[0] - lanceRemover.quantidade() + lanceAdicionar.quantidade();
                        melhoria = true;
                        break;
                    }
                }
                if (melhoria) {
                    break;
                }
            }
        }

        // Tentar adicionar qualquer lance restante que ainda caiba na capacidade
        List<Lance> lancesNaoSelecionados = todosLances.stream()
                .filter(l -> !lancesSelecionados.contains(l))
                .collect(Collectors.toList());

        for (Lance lance : lancesNaoSelecionados) {
            if (quantidadeTotalInserida[0] + lance.quantidade() <= quantidadeEnergiaDisponivel) {
                lancesSelecionados.add(lance);
                quantidadeTotalInserida[0] += lance.quantidade();
            }
        }

        return lancesSelecionados;
    }



}
