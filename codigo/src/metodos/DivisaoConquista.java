package metodos;

import entidades.Lance;
import entidades.MelhorResultado;
import enums.AlgoritmosEnums;
import lombok.NonNull;
import metodos.interfaces.Algoritmo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static enums.AlgoritmosEnums.DIVISAO_CONQUISTA;

public class DivisaoConquista implements Algoritmo {

    @Override
    public AlgoritmosEnums algoritmo() {
        return DIVISAO_CONQUISTA;
    }

    @Override
    public void executar(@NonNull MelhorResultado resultado, @NotNull List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {
        // Criar uma cópia modificável da lista de lances
        List<Lance> lancesModificaveis = new ArrayList<>(todosLances);

        // Ordenar os lances por valor em ordem decrescente (para maximizar o lucro)
        Collections.sort(lancesModificaveis, (l1, l2) -> Integer.compare(l2.valor(), l1.valor()));

        // Inicializa a chamada recursiva para dividir e conquistar
        int melhorLucro = dividirConquistar(resultado, lancesModificaveis, new ArrayList<>(), 0, lancesModificaveis.size() - 1);
        resultado.setLucroMaximizado(melhorLucro);
        resultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
    }

    private int dividirConquistar(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int inicio, int fim) {
        if (inicio > fim) {
            return 0; // Caso base: nenhum lance para selecionar
        }

        // Caso base: apenas um lance disponível
        if (inicio == fim) {
            Lance lanceUnico = todosLances.get(inicio);
            if (lanceUnico.quantidade() <= 8000) {
                lancesSelecionados.add(lanceUnico);
                return lanceUnico.valor();
            } else {
                return 0;
            }
        }

        int meio = (inicio + fim) / 2;

        // Recursivamente calcular o lucro máximo para as duas metades
        int lucroEsquerda = dividirConquistar(resultado, todosLances, new ArrayList<>(lancesSelecionados), inicio, meio);
        int lucroDireita = dividirConquistar(resultado, todosLances, new ArrayList<>(lancesSelecionados), meio + 1, fim);

        // Calcular o lucro máximo considerando lances que cruzam a divisão
        int lucroCruzado = encontrarMaximoCruzado(resultado, todosLances, lancesSelecionados, inicio, meio, fim);

        // Determinar o lucro máximo global
        return Math.max(Math.max(lucroEsquerda, lucroDireita), lucroCruzado);
    }

    private int encontrarMaximoCruzado(MelhorResultado resultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int inicio, int meio, int fim) {
        // Encontrar o lucro máximo considerando lances que cruzam a divisão entre as duas metades
        // Percorrer da metade ao início
        int lucroMaximoEsquerda = 0;
        int lucroAtual = 0;
        int quantidadeEsquerda = 0;

        for (int i = meio; i >= inicio; i--) {
            quantidadeEsquerda += todosLances.get(i).quantidade();
            if (quantidadeEsquerda <= 8000) {
                lucroAtual += todosLances.get(i).valor();
                lancesSelecionados.add(todosLances.get(i));
                lucroMaximoEsquerda = Math.max(lucroMaximoEsquerda, lucroAtual);
            }
        }

        // Percorrer da metade + 1 ao fim
        int lucroMaximoDireita = 0;
        lucroAtual = 0;
        int quantidadeDireita = 0;

        for (int i = meio + 1; i <= fim; i++) {
            quantidadeDireita += todosLances.get(i).quantidade();
            if (quantidadeDireita <= 8000) {
                lucroAtual += todosLances.get(i).valor();
                lancesSelecionados.add(todosLances.get(i));
                lucroMaximoDireita = Math.max(lucroMaximoDireita, lucroAtual);
            }
        }

        // Combinar os resultados das duas metades
        int lucroMaximo = lucroMaximoEsquerda + lucroMaximoDireita;

        // Atualizar o resultado global se necessário
        if (lucroMaximo > resultado.getLucroMaximizado()) {
            resultado.setLucroMaximizado(lucroMaximo);
            resultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }

        return lucroMaximo;
    }
}
