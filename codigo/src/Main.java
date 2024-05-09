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
         * - Para cada algoritmo criado, basta adicionar ele à lista abaixo, exemplo: asList(new Backtracking(), new Guloso())
         * - Caso deseje executar apenas um algoritmo, basta usar o construtor: Algoritmo algoritmo = new Backtracking(), e chamar o método algoritmo.executar()
         */

        List<Algoritmo> algoritmosImplementados = asList(new Backtracking());

        algoritmosImplementados.forEach(algoritmo ->
                algoritmo.executarAlgoritmo(QUANTIDADE_COMPRADORAS, QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA));

    }
}
