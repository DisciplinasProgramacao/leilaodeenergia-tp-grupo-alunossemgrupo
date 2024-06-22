# Análise de desempenho de algoritmos

**Data de entrega:** 24 de junho de 2024

**Grupo:** Breno Rosa Almeida, Guilherme Lage da Costa, Marcos Paulo Freitas da Silva e Vinícius Dias

**Professor:** João Caram Santos de Oliveira

## Índice de conteúdos

1. [Sobre o problema](#sobre-o-problema)
2. [Introdução e objetivo do estudo](#introdução-e-objetivo-do-estudo)
3. [Algoritmo _backtracking_](#algoritmo-_backtracking_)
    1. [Dados de execução](#dados-de-execução)
    2. [Sobre o algoritmo](#sobre-o-algoritmo)
    3. [Algoritmo implementado](#algoritmo-implementado)
    4. [Resultados obtidos](#resultados-obtidos)
4. [Algoritmo guloso](#algoritmo-guloso) -> PENDENTE
5. [Divisão e conquista](#algoritmo-de-divisão-e-conquista) -> PENDENTE
6. [Algoritmo por programação dinâmica](#algoritmo-por-programação-dinâmica) -> PENDENTE
7. [Comparação dos resultados obtidos pelos algoritmos](#comparação-dos-resultados-obtidos-pelos-algoritmos)

## Sobre o problema

Uma empresa produtora de energia possui uma quantidade X de energia, medida em megawatts, para vender. Seu objetivo
é vender sua energia produzida, obtendo o maior valor possível no conjunto de suas vendas. As vendas serão realizadas
por leilão: cada empresa interessada E dará um lance por um lote de K megawatts, oferecendo um valor V por este lote. As
interessadas só comprarão um lote do tamanho exato da oferta.

## Introdução e objetivo do estudo

Este trabalho visa analisar e comparar a resolução de um mesmo problema a partir de algoritmos distintos, comparando o
seu desempenho, ganhos e perdas. Para fins de comparação, foram implementados os seguintes algoritmos:

- [Algoritmo _backtracking_](#algoritmo-_backtracking_);
- [Algoritmo guloso](#algoritmo-guloso);
- [Algoritmo de divisão e conquista](#algoritmo-de-divisão-e-conquista);
- [Algoritmo por programação dinâmica](#algoritmo-por-programação-dinâmica).

Nos tópicos a seguir, serão apresentados os dados de execução de cada um desses algoritmos, bem como os resultados
obtidos. Mais adiante, esses dados serão compilados e comparados, concluindo sobre o desempenho de cada um deles.

## Algoritmo _backtracking_

### Dados de execução

- **Responsável**: Guilherme Lage da Costa
- **Matrícula**: 792939
- **JDK**: Java 17
- **Processador**: AMD Ryzen 5 3600, 4.2 Ghz, 6 cores e 12 threads, 32mb de cache
- **RAM**: 16GB, 3000Ghz
- **Sistema Operacional**: Windows 11
- **IDE**: IntelliJ Ultimate

### Sobre o algoritmo

O ***backtracking*** é uma técnica de projetos de algoritmos também conhecida como *retrocesso* ou *tentativa e erro*.
Esse algoritmo é um refinamento da busca por força bruta, na qual algumas soluções podem ser descartadas sem que ao
menos sejam examinadas. Neste algoritmo, são testadas, metodicamente, várias sequências de decisões até encontrar uma
que seja aceitável, ou as descarta até encontrar a melhor solução possível.

De modo geral, esse algoritmo segue inicialmente o padrão de
uma [busca em profundidade](https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/dfs.html), ou seja, uma árvore é
percorrida sistematicamente. Quando essa busca falha, ou é encontrado uma folha da árvore (nós sem filhos), entra em
funcionamento o mecanismo que dá nome ao algoritmo de ***backtracking***, fazendo com que o algoritmo retorne pelo mesmo
caminho já percorrido, de modo a buscar soluções alternativas que atendam aos critérios pré-definidos.

### Algoritmo implementado

```java
public void executar(MelhorResultado melhorResultado, List<Lance> todosLances, List<Lance> lancesSelecionados, int indice, int lucroAtual) {

    int qtdeSelecionada = lancesSelecionados.stream()
            .mapToInt(Lance::quantidade)
            .sum();
//        Se a quantidade de lances selecionados somar uma quantidade maior do que a disponível, podar, pois não é um resultado aceitável
    if (qtdeSelecionada > melhorResultado.getProdutora().quantidadeDisponivel())
        return;
//        Se já percorreu todos os lances em uma determinada busca, e a quantidade selecionada não ultrapassa a disponível, verifica se o lucro aumentou
    if (indice == todosLances.size()) {
        if (lucroAtual > melhorResultado.getLucroMaximizado()) {
            melhorResultado.setLucroMaximizado(lucroAtual);
            melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }
        return;
    }
//        Se não percorreu ainda todos os lances, segue a busca adicionado o lance do índice atual à lista de lances selecionados
    Lance lanceAnalisado = todosLances.get(indice);

    if (!isNull(lanceAnalisado)) {
        lancesSelecionados.add(lanceAnalisado);
        executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual + lanceAnalisado.valor());
        lancesSelecionados.remove(lancesSelecionados.size() - UM);
    }
    executar(melhorResultado, todosLances, lancesSelecionados, indice + UM, lucroAtual);
}
```

No algoritmo implementado, a função responsável por executar o método de ***backtracking*** recebe cinco parâmetros,
sendo eles:

- melhorResultado: `MelhorResultado`;
- todosLances: `List<Lance>`;
- lancesSelecionados: `List<Lance>`;
- indice: `int`;
- lucroAtual: `int`.

O parâmetro de `melhorResultado` possui informações sobre a empresa produtora e compradoras, um
contador de tempo de execução, lista dos lances selecionados, lucro maximizado e a quantidade disponibilizada para venda
pela empresa produtora. O parâmetro `todosLances` relaciona todos os lances feitos por todas as empresas compradoras,
enquanto `lancesSelecionados` relaciona todos os lances selecionados que compõem a combinação em análise. O
parâmetro `indice` indica qual o índice da lista de lances `todosLances`
que será analisado, de modo a verificar se ele pode ser inserido na combinação atual.

O algoritmo se inicia quantificando qual é a quantidade total dos lances atualmente selecionados (em
megawatts) e o armazena na variável `qtdeSelecionada`. Caso a quantidade selecionada seja superior à de venda, essa
solução não é aceitável, sendo desconsiderada no ***return***. Esse critério de poda foi aplicado para garantir que o
melhor resultado sempre fosse encontrado, como não existiam muitas variáveis no problema, apenas valor e peso, optou-se
por essa abordagem, pois mesmo que poucas podas fossem feitas, a quantidade de variáveis ainda assim não seria alta,
garantindo um desempenho razoável do algoritmo.

Posteriormente, na condicional, é verificado se o índice passado como parâmetro é igual à quantidade de
lances total, se essa validação for verdadeira, significa não haver mais lances para avaliar nessa iteração, uma vez
que o índice chegou ao último lance. Em seguida, é verificado se o `lucroAtual` (soma do valor de todos
os `lancesSelecionados`) é maior que o valor do maior lucro registrado, armazenado no atributo `lucroMaximizado`
do `melhorResultado`, se for maior, atualiza-se o lucro maximizado e a lista de lances selecionados. Se o lucro atual
não for maior do que o lucro maximizado, essa solução é desconsiderada no **return**.

Em seguida, é selecionado um novo lance que será analisado, caso ele não seja `null`, prossegue para uma
nova sequência de instrução. A validação se o `lanceAnalisado` não é nulo é importante, pois, como o algoritmo executa
recursivamente sobre a lista na posição índice + 1, pode-se chegar em um ponto em que o próximo valor é nulo, como é o
caso quando o lance analisado é o último da lista, por exemplo.

Se o lance não for nulo, ele é adicionado á lista de `lancesSelecionados` e chama-se a função de *
*backtracking** recursivamente sobre essa nova lista de lances. Quando a execução chegar ao fim, esse lance
é removido da lista e, em seguida, chamado uma nova execução do algoritmo na próxima posição do índice.

Ao final da execução, o algoritmo retorna a melhor combinação de lances encontrada, buscando maximizar o lucro total.

### Massa de testes utilizada

A massa de testes utilizada seguiu os seguintes parâmetros:

- **Quantidade *mínima* p/ compradora = 10** → indica a quantidade mínima que uma determinada compradora poderia
  solicitar em um lote;
- **Quantidade *máxima* p/ compradora = 100** → indica a quantidade máxima que uma determinada compradora poderia
  solicitar em um lote;
- **Quantidade disponível pela produtora = 200** → indica a quantidade total (lote total) que a empresa produtora
  possui, ou seja, que disponibiliza para leilão;
- **Quantidade máxima de lances p/ compradora = 1** → indica a quantidade máxima de lances que cada compradora poderia
  fazer;
- **Quantidades de compradoras = [10, ..., 31]** → foram executados 10 testes para cada quantidade de lances,
  iniciado em 10 e incrementado de 1 a 1 até atingir um tamanho em que o problema não foi possível de ser resolvido em
  até 30 segundos pelo algoritmo. Quando isso aconteceu, foram executados mais 10 testes com essa massa e em seguida a
  execução foi finalizada. No caso da implementação realizada, o algoritmo conseguiu executar massas de testes com 10
  até
  31 lances. Para cada um desses cenários, foram criados novos conjuntos de testes para que a média de tempo fosse
  calculada, por exemplo, para executar 27 lances, foram criados 10 listas com 27 lances diferentes e em seguida
  calculado o tempo médio entre cada uma dessas execuções.

Os resultados gerados após cada execução do algoritmo foram armazenados automaticamente em dois
arquivos: `exec-log.xls` e `hist-log.xls`. O primeiro log guarda dados gerais da execução como o tempo despendido,
algoritmo utilizado, recursos computacionais disponíveis, dentre outros, enquanto o segundo log guarda os dados sobre os
lances que foram feitos e os que foram escolhidos para combinar o melhor resultado.

![trecho-arquivo-exec-log.png](../figuras/trecho-arquivo-exec-log.png)

<div style="text-align: center;">

*[Exemplo de arquivo de log `exec-log.xls`]*

</div>

![trecho-arquivo-hist-log.png](../figuras/trecho-arquivo-hist-log.png)

<div style="text-align: center;">

*[Exemplo de arquivo de log `hist-log.xls`]*

</div>


O arquivo de análises que compila as execuções realizadas pode ser verificado no
arquivo [analise backtracking](../../analises/analise-backtracking.xlsx).

### Resultados obtidos

Para o algoritmo de `Backtracking`, foram realizadas um total de **210** execuções, considerando 10 iterações para cada
cenário, que se iniciou com 10 lances (1 por compradora) e finalizou com 31. O tempo de execução médio (em segundos)
para cada um desses cenários é exposto na tabela a seguir. Os casos em que os tempos de execução foram menores que 1
segundo estão indicados como "-", ou seja, o tempo de execução para esses casos não foi significativo. Além disso, as
colunas da tabela indicam cada uma das 10 execuções realizadas para cada quantidade de lances.

| Quantidade lances | 1     | 2     | 3    | 4     | 5     | 6    | 7     | 8    | 9     | 10    | Tempo médio |
|-------------------|-------|-------|------|-------|-------|------|-------|------|-------|-------|-------------|
| 10                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 11                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 12                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 13                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 14                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 15                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 16                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 17                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 18                | -     | 1,00  | -    | -     | -     | -    | -     | -    | -     | -     | 0,10        |
| 19                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 20                | -     | -     | -    | -     | -     | -    | -     | -    | 1,00  | -     | 0,10        |
| 21                | -     | -     | -    | -     | -     | -    | -     | -    | -     | -     | -           |
| 22                | -     | 1,00  | -    | -     | -     | -    | -     | -    | 1,00  | -     | 0,20        |
| 23                | -     | -     | -    | 1,00  | -     | -    | 1,00  | -    | -     | -     | 0,20        |
| 24                | -     | 1,00  | -    | -     | -     | -    | 1,00  | 1,00 | 1,00  | 1,00  | 0,50        |
| 25                | -     | -     | -    | 1,00  | -     | 1,00 | 1,00  | -    | -     | -     | 0,30        |
| 26                | 1,00  | 1,00  | -    | 2,00  | 1,00  | -    | 1,00  | 2,00 | -     | 2,00  | 1,00        |
| 27                | 1,00  | 6,00  | -    | 2,00  | 1,00  | 4,00 | 1,00  | 2,00 | 1,00  | 1,00  | 1,90        |
| 28                | 5,00  | 3,00  | 4,00 | 1,00  | 6,00  | 3,00 | 2,00  | -    | -     | 2,00  | 2,60        |
| 29                | 1,00  | 12,00 | 1,00 | 3,00  | 5,00  | -    | 2,00  | 4,00 | 13,00 | 3,00  | 4,40        |
| 30                | 3,00  | 2,00  | 9,00 | 4,00  | 11,00 | 2,00 | 6,00  | 1,00 | 3,00  | 14,00 | 5,50        |
| 31                | 62,00 | 10,00 | 1,00 | 10,00 | 1,00  | 9,00 | 10,00 | 8,00 | 2,00  | 4,00  | 11,70       |

<div style="text-align: center;">

*[Tabela de tempos médios de execução - Backtracking]*

</div>

Os dados indicados na tabela acima são representados no gráfico abaixo.

![grafico-backtracking.png](../figuras/grafico-backtracking.png)

<div style="text-align: center;">

*[Gráfico dos tempos médios obtidos com o algoritmo de backtracking]*

</div>

A tabela a seguir relaciona cada um dos cenários de lances, e o melhor lucro obtido em cada um deles.

| Quantidade lances | 1     | 2     | 3     | 4   | 5     | 6     | 7     | 8     | 9     | 10    | Melhor lucro médio |
|-------------------|-------|-------|-------|-----|-------|-------|-------|-------|-------|-------|--------------------|
| 10                | 504   | 393   | 408   | 580 | 248   | 341   | 372   | 449   | 531   | 467   | 429                |
| 11                | 361   | 388   | 548   | 613 | 561   | 627   | 424   | 648   | 597   | 442   | 521                |
| 12                | 574   | 459   | 618   | 465 | 511   | 368   | 334   | 376   | 650   | 651   | 501                |
| 13                | 514   | 653   | 428   | 459 | 672   | 602   | 610   | 537   | 555   | 626   | 566                |
| 14                | 573   | 687   | 822   | 641 | 454   | 555   | 673   | 420   | 551   | 528   | 590                |
| 15                | 765   | 536   | 499   | 654 | 578   | 465   | 571   | 628   | 577   | 563   | 584                |
| 16                | 697   | 777   | 573   | 614 | 569   | 671   | 618   | 696   | 794   | 659   | 667                |
| 17                | 792   | 813   | 658   | 646 | 812   | 501   | 769   | 755   | 625   | 795   | 717                |
| 18                | 568   | 607   | 827   | 511 | 589   | 810   | 724   | 451   | 714   | 522   | 632                |
| 19                | 750   | 561   | 829   | 595 | 789   | 544   | 645   | 756   | 864   | 680   | 701                |
| 20                | 612   | 556   | 804   | 707 | 646   | 830   | 1.005 | 618   | 690   | 755   | 722                |
| 21                | 663   | 660   | 659   | 871 | 473   | 661   | 734   | 956   | 651   | 752   | 708                |
| 22                | 599   | 860   | 534   | 817 | 802   | 772   | 769   | 1.097 | 832   | 763   | 785                |
| 23                | 549   | 953   | 968   | 813 | 737   | 696   | 878   | 789   | 833   | 1.039 | 826                |
| 24                | 886   | 855   | 625   | 999 | 663   | 657   | 633   | 877   | 955   | 886   | 804                |
| 25                | 773   | 1.078 | 746   | 701 | 902   | 709   | 1.137 | 484   | 677   | 882   | 809                |
| 26                | 893   | 768   | 984   | 943 | 867   | 720   | 923   | 815   | 776   | 897   | 859                |
| 27                | 950   | 1.133 | 693   | 650 | 885   | 1.036 | 718   | 853   | 840   | 806   | 856                |
| 28                | 953   | 984   | 1.041 | 904 | 984   | 990   | 984   | 699   | 765   | 990   | 929                |
| 29                | 803   | 1.003 | 916   | 800 | 1.112 | 852   | 972   | 1.067 | 1.049 | 821   | 940                |
| 30                | 775   | 929   | 1.103 | 853 | 1.007 | 754   | 925   | 745   | 828   | 1.003 | 892                |
| 31                | 1.053 | 1.079 | 895   | 975 | 751   | 943   | 1.091 | 876   | 697   | 1.004 | 936                |

<div style="text-align: center;">

*[Tabela de maior lucro obtido - Backtracking]*

</div>

Conforme observado no gráfico acima, até a execução com 26 lances, o algoritmo de backtracking conseguia encontrar o
melhor lucro do problema em menos de 1 segundo. A partir desse ponto, os tempos de execução passaram a ser maiores que 1
segundo e começaram a crescer exponencialmente. Com apenas 2 execuções a mais, na execução com 28 lances, o tempo de
execução já estava próximo de 3 segundos, um aumento de 260%. Quando o algoritmo executou o cenário com 31 lances, foi
atingido o limite de 30 segundos, fazendo com que esse fosse o último cenário analisado. Neste cenário, o tempo médio de
execução foi de 11,7 segundos, ou seja, 1170% superior ao cenário com 28 lances, que demandou 1 segundo.

Os resultados obtidos indicam que, em cenários com baixa quantidade de dados a serem analisados, o backtracking pode se
mostrar uma opção viável, uma vez que o tempo de execução não será significativo. No entanto, à medida que a quantidade
de dados começa a crescer muito, ou uma poda pouco efetiva é utilizada, de modo que o algoritmo execute muitas operações
recursivamente sem conseguir "podar" muitos cenários, o tempo de execução deste algoritmo pode crescer
significativamente, deixando de ser uma opção interessante para resolver o problema.

Por se tratar de um refinamento do algoritmo de força bruta, em que algumas das combinações possíveis podem ser
descartadas por meio do critério de poda, é de suma importância que esse critério seja bem definido para a execução
satisfatória do algoritmo, uma vez que a quantidade de combinações possíveis, dado um conjunto de números, é da ordem de
2^n - 1, ou seja, para o caso de 31 lances, seriam 2^31 - 1 combinações possíveis. Conforme apresentado anteriormente, a
técnica de poda adotada neste algoritmo foi baseada na quantidade somada (em megawatts) pelos lances já escolhidos
durante a execução, podando posteriores iterações caso o valor da quantidade atual, mais o próximo lance, ultrapassassem
o total disponível pela empresa Produtora. Essa abordagem foi adotada de modo que eventuais combinações não fossem
descartadas precocemente, mas apenas quando realmente não fosse mais possível selecionar nenhum lance adicional,
buscando assim o maior lucro possível.

## Algoritmo guloso

[A SER DESENVOLVIDO]

## Algoritmo de divisão e conquista

[A SER DESENVOLVIDO]

## Algoritmo por programação dinâmica

### Dados de execução

- **Responsável**: Marcos Paulo Freitas Da Silva
- **Matrícula**: 746639
- **JDK**: Java 17
- **Processador**: Intel Core i5 8265U, 4.2 Ghz, 4 cores e 8 threads, 6mb de cache
- **RAM**: 12GB, 2400Ghz
- **Sistema Operacional**: Windows 11
- **IDE**: IntelliJ Ultimate

## Sobre o Algoritmo
A classe `ProgramacaoDinamica` implementa o algoritmo de Programação Dinâmica, uma técnica de otimização que resolve problemas complexos dividindo-os em subproblemas menores e resolvendo cada subproblema apenas uma vez, armazenando seus resultados para evitar cálculos repetidos. Este método é eficiente para problemas de otimização onde a solução é composta de subsoluções ótimas.

## Descrição da Classe
```@AllArgsConstructor
public class ProgramacaoDinamica implements Algoritmo {
    
    @Override
    public AlgoritmosEnums algoritmo() {
        return PROGRAMACAO_DINAMICA;
    }
    
    @Override
    public void executar(
            @NonNull MelhorResultado melhorResultado, List<Lance> todosLances, @NonNull List<Lance> lancesSelecionados, int indice, int lucroAtual) {
        
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

        resultado.add(lanceAnalisado);
        int incluir = lanceAnalisado.valor() + dp(melhorResultado, todosLances, indice + 1,
                qtdeSelecionada + lanceAnalisado.quantidade(), memo, resultado);
        resultado.remove(resultado.size() - 1);

        int excluir = dp(melhorResultado, todosLances, indice + 1, qtdeSelecionada, memo, resultado);

        int maxLucro = Math.max(incluir, excluir);

        memo.put(key, maxLucro);

        return maxLucro;
    }
}
```
No algoritmo implementado, a função responsável por executar o método de ***Programção Dinamica*** recebe cinco parâmetros,
sendo eles:

- melhorResultado: `MelhorResultado`;
- todosLances: `List<Lance>`;
- lancesSelecionados: `List<Lance>`;
- indice: `int`;
- lucroAtual: `int`.

O algoritmo começa verificando se a quantidade total dos lances atualmente selecionados `qtdeSelecionada` excede a quantidade disponível para venda.
Se for o caso, essa solução é descartada retornando o menor valor possível `Integer.MIN_VALUE`. Em seguida, verifica se todos os lances foram processados `indice == todosLances.size()`.
Se todos os lances foram processados, retorna 0, indicando que não há mais lucro a ser adicionado.

A chave do algoritmo é a memoização, onde cada subproblema é identificado por uma chave única composta pelo `indice` e `qtdeSelecionada`. 
Se um resultado para esse subproblema já estiver armazenado, ele é reutilizado, evitando cálculos redundantes.

O algoritmo então considera duas possibilidades para cada lance:

Incluir o lance atual: Adiciona o valor do lance ao lucro atual e chama recursivamente a função dp para o próximo índice, aumentando a quantidade selecionada.
Não incluir o lance atual: Chama recursivamente a função dp para o próximo índice sem aumentar a quantidade selecionada.
Finalmente, o algoritmo retorna o lucro máximo entre as duas opções e armazena o resultado na tabela de memoização.

## Comparação dos resultados obtidos pelos algoritmos

[A SER DESENVOLVIDO]
