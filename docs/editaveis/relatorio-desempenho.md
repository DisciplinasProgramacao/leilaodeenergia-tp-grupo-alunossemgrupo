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

    if (lucroAtual > melhorResultado.getLucroMaximizado()) {
        melhorResultado.setLucroMaximizado(lucroAtual);
        melhorResultado.setLancesSelecionados(new ArrayList<>(lancesSelecionados));
    }
    if (indice >= todosLances.size() || qtdeSelecionada >= melhorResultado.getProdutora().quantidadeDisponivel()) {
        return;
    }
    int menorValor = MAX_VALUE;

    for (int i = indice; i < todosLances.size(); i++) {
        menorValor = min(menorValor, todosLances.get(i).quantidade());
    }
    if (qtdeSelecionada + menorValor > melhorResultado.getProdutora().quantidadeDisponivel()) {
        return;
    }
    for (int i = indice; i < todosLances.size(); i++) {
        Lance lance = todosLances.get(i);

        if (qtdeSelecionada + lance.quantidade() < melhorResultado.getProdutora().quantidadeDisponivel()) {
            lancesSelecionados.add(lance);
            executar(melhorResultado, todosLances, lancesSelecionados, i + UM, lucroAtual + lance.valor());
            lancesSelecionados.remove(lancesSelecionados.size() - UM);
        }
    }
}
```

No algoritmo implementado, a função responsável por executar o método de ***backtracking*** recebe cinco parâmetros,
sendo eles:

- `melhorResultado`: `MelhorResultado`
- `todosLances`: `List<Lance>`
- `lancesSelecionados`: `List<Lance>`
- `indice`: `int`
- `lucroAtual`: `int`

O parâmetro `melhorResultado` contém informações sobre a empresa produtora e compradoras, um contador de tempo de
execução, a lista dos lances selecionados, o lucro maximizado e a quantidade disponibilizada para venda pela empresa
produtora. O parâmetro `todosLances` relaciona todos os lances feitos por todas as empresas compradoras,
enquanto `lancesSelecionados` armazena todos os lances selecionados que compõem a combinação em análise. O
parâmetro `indice` indica qual o índice da lista de lances `todosLances` que será analisado, de modo a verificar se ele
pode ser inserido na combinação atual.

O algoritmo se inicia quantificando a quantidade total dos lances atualmente selecionados (em megawatts) e armazena esse
valor na variável `qtdeSelecionada`.

```java
int qtdeSelecionada = lancesSelecionados.stream()
        .mapToInt(Lance::quantidade)
        .sum();
```

Se o lucro atual (`lucroAtual`) for maior do que o valor do maior lucro registrado, armazenado no
atributo `lucroMaximizado` do `melhorResultado`, o algoritmo atualiza o lucro maximizado e a lista de lances
selecionados.

```java
if(lucroAtual >melhorResultado.

getLucroMaximizado()){
        melhorResultado.

setLucroMaximizado(lucroAtual);
    melhorResultado.

setLancesSelecionados(new ArrayList<>(lancesSelecionados));
        }
```

O algoritmo verifica se o índice passado como parâmetro é igual ou maior à quantidade total de lances, ou se a
quantidade selecionada é maior ou igual à quantidade disponível para leilão pela produtora. Se qualquer dessas condições
for verdadeira, a execução termina.

```java
if(indice >=todosLances.

size() ||qtdeSelecionada >=melhorResultado.

getProdutora().

quantidadeDisponivel()){
        return;
        }
```

Em seguida, o algoritmo calcula o menor valor de lance a partir do índice atual para garantir que há espaço suficiente
para adicionar um novo lance sem exceder a capacidade de venda.

```java
int menorValor = MAX_VALUE;
for(
int i = indice; i <todosLances.

size();

i++){
menorValor =

min(menorValor, todosLances.get(i).

quantidade());
        }
        if(qtdeSelecionada +menorValor >melhorResultado.

getProdutora().

quantidadeDisponivel()){
        return;
        }
```

Para cada lance válido a partir do índice atual, o algoritmo adiciona o lance à lista de lances selecionados e chama
recursivamente a função `executar` com o próximo índice e o lucro atualizado.

```java
for(int i = indice; i <todosLances.

size();

i++){
Lance lance = todosLances.get(i);
    
    if(qtdeSelecionada +lance.

quantidade() <melhorResultado.

getProdutora().

quantidadeDisponivel()){
        lancesSelecionados.

add(lance);

executar(melhorResultado, todosLances, lancesSelecionados, i +UM, lucroAtual +lance.valor());
        lancesSelecionados.

remove(lancesSelecionados.size() -UM);
        }
        }
```

De modo geral, o algoritmo de backtracking implementado busca encontrar a combinação de lances que maximiza o lucro
total, respeitando a capacidade de venda da empresa produtora. Para melhorar o seu desempenho, foram realizadas podas em
três pontos distintos, (i) quando o índice atual é maior do que o índice final da lista `todosLances` ou a quantidade
selecionar for superior ao
total disponibilizado para leilão pela produtora; (ii) quando o menor valor de lance disponível for maior do que o
espaço
restante para leilão; e (iii) quando a quantidade selecionada mais a quantidade de um próximo lance for maior do que a
disponibilizada, e dessa forma o algoritmo não executa essa iteração. Ele realiza as seguintes etapas:

1. Calcula a quantidade total dos lances selecionados.
2. Atualiza o melhor resultado encontrado, se o lucro atual for maior que o lucro maximizado.
3. Verifica condições de terminação com base no índice e na quantidade selecionada.
4. Calcula o menor valor de lance a partir do índice atual.
5. Itera sobre os lances válidos, adiciona-os à lista de lances selecionados, e chama recursivamente a
   função `executar`.

Ao final da execução, o algoritmo retorna a melhor combinação de lances encontrada, maximizando o lucro total.

### Massa de testes utilizada

A massa de testes utilizada seguiu os seguintes parâmetros:

- **Quantidade *mínima* p/ compradora = 1000** → indica a quantidade mínima que uma determinada compradora poderia
  solicitar em um lote;
- **Quantidade *máxima* p/ compradora = 1500** → indica a quantidade máxima que uma determinada compradora poderia
  solicitar em um lote;
- **Quantidade disponível pela produtora = 8000** → indica a quantidade total (lote de megawatts) que a empresa
  produtora
  possui, ou seja, que disponibiliza para leilão;
- **Quantidade máxima de lances p/ compradora = 1** → indica a quantidade máxima de lances que cada compradora poderia
  fazer;
- **Quantidades de compradoras = [10, ..., 33]** → foram executados 10 testes para cada quantidade de lances,
  iniciado em 10 e incrementado de 1 a 1 até atingir um tamanho em que o problema não foi possível de ser resolvido em
  até 30 segundos pelo algoritmo. Quando isso aconteceu, foram executados os 10 testes com essa massa e em seguida a
  execução foi finalizada. Na implementação realizada, o algoritmo conseguiu executar massas de testes com **10
  ** até **33** lances. Para cada um desses cenários, foram criados novos conjuntos de testes para que a média de tempo
  fosse calculada, por exemplo, para executar 27 lances, foram criados 10 listas com 27 lances diferentes e em seguida
  calculado o tempo médio entre cada uma dessas execuções. Além desses conjuntos de compradoras e lances, gerados
  aleatoriamente, foram executados dois conjuntos de testes específicos encaminhados pelo prof. Caram, com 25 lances
  cada um.

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

Conforme descrito acima, os cenários de testes variaram de 10 a 33 lances, para cada um desses cenários, foram
executados 10 iterações com conjuntos distintos de lances, gerados em tempo de execução, de modo que foram executadas *
*240** execuções ao total (ou seja, (33 - 10) * 10 + 10). Na tabela a seguir, estão relacionados cada um dos cenários de
testes (linhas), e o tempo médio despendido em cada uma das suas iterações (colunas). Os casos em que o tempo de
execução foi inferior a 1 segundo estão indicados com o símbolo "-", ou seja, o tempo de execução não é significativo.

| Quantidade lances | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | Tempo médio (seg) |
|-------------------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------------------|
| 10                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 11                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 12                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 13                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 14                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 15                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 16                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 17                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 18                | -     | -     | -     | -     | -     | -     | -     | -     | -     | -     | -                 |
| 19                | -     | 1,00  | -     | -     | -     | -     | -     | -     | -     | -     | 0,10              |
| 20                | -     | -     | -     | -     | -     | -     | -     | -     | 1,00  | -     | 0,10              |
| 21                | -     | -     | -     | -     | -     | -     | -     | -     | 1,00  | -     | 0,10              |
| 22                | -     | -     | -     | -     | 1,00  | -     | -     | -     | -     | -     | 0,10              |
| 23                | 1,00  | -     | -     | 1,00  | -     | -     | -     | 1,00  | -     | -     | 0,30              |
| 24                | 1,00  | -     | -     | 1,00  | -     | 1,00  | -     | 1,00  | -     | 1,00  | 0,50              |
| 25                | -     | 1,00  | -     | -     | 1,00  | 1,00  | 1,00  | 1,00  | 1,00  | -     | 0,60              |
| 26                | 1,00  | 1,00  | 1,00  | 1,00  | 1,00  | 1,00  | 2,00  | -     | 1,00  | 1,00  | 1,00              |
| 27                | 3,00  | 1,00  | 2,00  | 1,00  | 1,00  | 1,00  | 2,00  | 1,00  | -     | 2,00  | 1,40              |
| 28                | 4,00  | 3,00  | 2,00  | 1,00  | 1,00  | 2,00  | 2,00  | 3,00  | 2,00  | 3,00  | 2,30              |
| 29                | 6,00  | 4,00  | 7,00  | 4,00  | 6,00  | 4,00  | 1,00  | 12,00 | 2,00  | 4,00  | 5,00              |
| 30                | 15,00 | 9,00  | 2,00  | 7,00  | 11,00 | 3,00  | 4,00  | 16,00 | 5,00  | 4,00  | 7,60              |
| 31                | 12,00 | 6,00  | 10,00 | 12,00 | 7,00  | 6,00  | 10,00 | 12,00 | 7,00  | 6,00  | 8,80              |
| 32                | 19,00 | 12,00 | 18,00 | 8,00  | 11,00 | 26,00 | 15,00 | 5,00  | 7,00  | 6,00  | 12,70             |
| 33                | 27,00 | 11,00 | 12,00 | 11,00 | 6,00  | 14,00 | 34,00 | 42,00 | 11,00 | 21,00 | 18,90             |

<div style="text-align: center;">

*[Tabela de tempos médios de execução - Backtracking]*

</div>

Os valores de lucro máximo obtido em cada um dos cenários de testes, e respectivas iterações, são apresentados na tabela
a
seguir.

| Quantidade lances | 1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10     | Melhor lucro médio |
|-------------------|--------|--------|--------|--------|--------|--------|--------|--------|--------|--------|--------------------|
| 10                | 11.373 | 8.394  | 9.690  | 9.040  | 10.005 | 8.368  | 10.421 | 10.152 | 10.625 | 11.697 | 9.977              |
| 11                | 11.045 | 9.075  | 12.153 | 10.521 | 8.854  | 9.458  | 11.361 | 10.629 | 10.105 | 10.587 | 10.379             |
| 12                | 11.395 | 12.706 | 9.367  | 10.763 | 11.212 | 9.854  | 12.350 | 11.432 | 9.855  | 11.932 | 11.087             |
| 13                | 11.059 | 9.279  | 9.575  | 11.873 | 9.421  | 9.821  | 11.747 | 12.300 | 10.670 | 10.045 | 10.579             |
| 14                | 9.274  | 14.021 | 10.814 | 14.062 | 11.814 | 11.063 | 11.309 | 13.356 | 12.220 | 11.493 | 11.943             |
| 15                | 13.535 | 8.200  | 13.383 | 13.756 | 11.483 | 14.188 | 13.611 | 11.064 | 12.690 | 10.956 | 12.287             |
| 16                | 12.160 | 13.591 | 12.249 | 13.478 | 11.905 | 12.510 | 12.011 | 12.417 | 12.924 | 11.580 | 12.483             |
| 17                | 13.424 | 12.573 | 14.009 | 12.169 | 14.769 | 13.004 | 16.050 | 12.196 | 12.516 | 11.776 | 13.249             |
| 18                | 14.208 | 11.297 | 15.407 | 11.440 | 14.408 | 10.804 | 15.238 | 16.301 | 13.775 | 13.227 | 13.611             |
| 19                | 12.453 | 14.352 | 13.737 | 13.724 | 13.662 | 12.215 | 12.236 | 14.697 | 11.784 | 13.330 | 13.219             |
| 20                | 14.831 | 14.268 | 15.035 | 11.828 | 13.712 | 13.552 | 14.871 | 13.827 | 13.986 | 15.356 | 14.127             |
| 21                | 11.951 | 16.218 | 13.216 | 13.626 | 15.327 | 14.926 | 13.176 | 14.002 | 13.501 | 13.957 | 13.990             |
| 22                | 14.270 | 13.253 | 13.694 | 15.215 | 13.076 | 11.853 | 14.362 | 14.574 | 16.733 | 12.014 | 13.904             |
| 23                | 14.160 | 14.795 | 15.002 | 13.760 | 13.577 | 16.308 | 14.030 | 14.619 | 15.402 | 14.902 | 14.656             |
| 24                | 14.003 | 13.505 | 14.812 | 15.435 | 14.425 | 15.916 | 15.243 | 14.105 | 15.037 | 14.274 | 14.676             |
| 25                | 15.837 | 14.717 | 13.223 | 13.437 | 15.213 | 15.876 | 16.253 | 17.029 | 14.090 | 14.621 | 15.030             |
| 26                | 15.217 | 14.726 | 16.423 | 17.017 | 13.700 | 14.073 | 15.667 | 12.722 | 15.887 | 14.232 | 14.966             |
| 27                | 16.934 | 15.958 | 17.389 | 14.517 | 14.770 | 14.705 | 16.279 | 15.783 | 13.216 | 16.202 | 15.575             |
| 28                | 16.909 | 15.472 | 15.263 | 14.260 | 13.573 | 14.049 | 13.686 | 17.431 | 15.361 | 15.299 | 15.130             |
| 29                | 15.956 | 16.563 | 16.741 | 17.101 | 16.104 | 14.728 | 14.132 | 17.076 | 15.299 | 15.806 | 15.951             |
| 30                | 17.738 | 17.273 | 14.179 | 16.732 | 16.281 | 14.321 | 16.194 | 18.770 | 14.250 | 15.564 | 16.130             |
| 31                | 16.662 | 14.927 | 16.779 | 14.237 | 15.641 | 14.458 | 17.013 | 18.222 | 14.349 | 16.191 | 15.848             |
| 32                | 17.290 | 15.307 | 17.305 | 15.826 | 13.945 | 17.748 | 14.959 | 14.481 | 14.309 | 14.393 | 15.556             |
| 33                | 17.028 | 16.308 | 15.647 | 14.528 | 16.205 | 17.425 | 17.212 | 18.868 | 15.911 | 15.793 | 16.493             |

<div style="text-align: center;">

*[Tabela de maior lucro obtido - Backtracking]*

</div>

Os dados indicados na tabela de tempos médios acima são consolidados no gráfico abaixo, que apresenta a evolução do
tempo médio despendido em cada cenário de teste.

![grafico-backtracking.png](../figuras/grafico-backtracking.png)

<div style="text-align: center;">

*[Gráfico dos tempos médios obtidos com o algoritmo de backtracking]*

</div>

Além dos cenários apresentados, foram executados dois conjuntos adicionais fornecidos pelo prof. Caram, conjunto um e
dois. A relação das informações obtidas com a execução desses conjuntos são elencadas na tabela abaixo.

| Conjunto | Quantidade lances | Lances selecionados | Lucro máximo | Tempo execução (seg) |
|----------|-------------------|---------------------|--------------|----------------------|
| Um       | 25                | 19                  | R$ 26.725,00 | 4                    |
| Dois     | 25                | 21                  | R$ 40.348,00 | 3                    |

Conforme observado no gráfico apresentado, até a execução com 26 lances, o algoritmo de backtracking conseguia encontrar
o melhor lucro do problema em menos de 1 segundo. A partir desse ponto, os tempos de execução passaram a ser maiores que
1 segundo e começaram a crescer exponencialmente. Com apenas 2 lances adicionais (28 lances), o tempo de
execução foi de 2,3 segundos, um aumento de 130%. Quando o algoritmo executou o cenário com 33 lances, foi
atingido o limite de 30 segundos, fazendo com que esse fosse o último cenário analisado. Neste caso, o tempo médio de
execução foi de 18,9 segundos, ou seja, 722% superior ao cenário com 28 lances.

Os resultados obtidos indicam que, em cenários com baixa quantidade de dados, o backtracking pode se
mostrar uma opção viável, uma vez que o tempo de execução não será significativo. No entanto, à medida que a quantidade
de dados começar a crescer muito, ou uma poda pouco efetiva é utilizada (de modo que o algoritmo execute muitas
operações recursivamente, sem conseguir "podar" muitos cenários), o tempo de execução deste algoritmo pode crescer
significativamente, deixando de ser uma opção interessante para resolver o problema.

Por se tratar de um refinamento do algoritmo de força bruta, em que algumas das combinações possíveis podem ser
descartadas por meio do critério de poda, é de suma importância que esse critério seja bem definido para a execução
satisfatória do algoritmo, uma vez que a quantidade de combinações possíveis, dado um conjunto "n", é da ordem de
2^n - 1, ou seja, para o caso de 33 lances, existem 2^33 - 1 combinações possíveis. Conforme apresentado anteriormente,
foram aplicados três critérios distintos de poda, de modo que o algoritmo não executasse combinações desnecessárias, e,
simultaneamente, conseguisse encontrar o maior lucro possível em um tempo razoável.

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

A classe `ProgramacaoDinamica` implementa o algoritmo de Programação Dinâmica, uma técnica de otimização que resolve
problemas complexos dividindo-os em subproblemas menores e resolvendo cada subproblema apenas uma vez, armazenando seus
resultados para evitar cálculos repetidos. Este método é eficiente para problemas de otimização onde a solução é
composta de subsoluções ótimas.

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

No algoritmo implementado, a função responsável por executar o método de ***Programção Dinamica*** recebe cinco
parâmetros,
sendo eles:

- melhorResultado: `MelhorResultado`;
- todosLances: `List<Lance>`;
- lancesSelecionados: `List<Lance>`;
- indice: `int`;
- lucroAtual: `int`.

O algoritmo começa verificando se a quantidade total dos lances atualmente selecionados `qtdeSelecionada` excede a
quantidade disponível para venda.
Se for o caso, essa solução é descartada retornando o menor valor possível `Integer.MIN_VALUE`. Em seguida, verifica se
todos os lances foram processados `indice == todosLances.size()`.
Se todos os lances foram processados, retorna 0, indicando que não há mais lucro a ser adicionado.

A chave do algoritmo é a memoização, onde cada subproblema é identificado por uma chave única composta pelo `indice`
e `qtdeSelecionada`.
Se um resultado para esse subproblema já estiver armazenado, ele é reutilizado, evitando cálculos redundantes.

O algoritmo então considera duas possibilidades para cada lance:

Incluir o lance atual: Adiciona o valor do lance ao lucro atual e chama recursivamente a função dp para o próximo
índice, aumentando a quantidade selecionada.
Não incluir o lance atual: Chama recursivamente a função dp para o próximo índice sem aumentar a quantidade selecionada.
Finalmente, o algoritmo retorna o lucro máximo entre as duas opções e armazena o resultado na tabela de memoização.

## Comparação dos resultados obtidos pelos algoritmos

[A SER DESENVOLVIDO]
