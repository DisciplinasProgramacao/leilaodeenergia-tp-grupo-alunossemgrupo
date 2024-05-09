import metodos.Backtracking;
import metodos.interfaces.Algoritmo;

import java.util.List;

import static java.util.Arrays.asList;
import static util.constantes.ConstantesProdutoraVendedora.QUANTIDADE_COMPRADORAS;
import static util.constantes.ConstantesProdutoraVendedora.QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA;

public class Main {
    public static void main(String[] args) {

        /*
         * INSTRUÇÕES:
         * - Para ver as configurações nas quais os algoritmos vão ser executados, acessar arquivo: ConstantesProdutoraVendedora
         *   Caminho: src > util > constantes > ConstantesProdutoraVendedora
         *
         * - Para cada algoritmo criado, basta adicionar ele à lista abaixo, exemplo: asList(new Backtracking(), new Guloso())
         *
         * - Caso deseje executar apenas um algoritmo, basta usar o construtor: Algoritmo algoritmo = new Backtracking(), e chamar o método algoritmo.executar()
         *
         * - Para criar um algoritmo, basta criar uma nova classe no package 'metodos' que implemente a interface Algoritmo e seguir a sua definição de contrato
         *   Para cada algoritmo criado, verificar se o seu nome já consta no enum AlgoritmosEnums, caso não, adicionar nome do algoritmo
         *   O nome do algoritmo é importante para definir esse dado no log gerado após a execução
         *
         * - Quando implementar o algoritmo, lembrar de colocar o nome correto no método algoritmo() do Algoritmo implementado, para exemplo, ver arquivo Backtracking.java
         *
         * - Qualquer dúvida, pode me chamar no whatsapp
         */

        List<Algoritmo> algoritmosImplementados = asList(new Backtracking());

        algoritmosImplementados.forEach(algoritmo ->
                algoritmo.executarAlgoritmo(QUANTIDADE_COMPRADORAS, QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA));

    }
}
