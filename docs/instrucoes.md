# Instruções de uso

- Quando o arquivo Main for executado, todos os algoritmos serão inicializados, um após o outro, para toda a malha de
  teste configurada.

- A malha de testes é composta por cinco valores de referência:
    - QUANTIDADE_MINIMA_COMPRADORA = 10: quantidade mínima de MegaWatts que a empresa Compradora pode ter para comprar;
    - QUANTIDADE_MAXIMA_COMPRADORA = 100: quantidade máxima de MegaWatts que a empresa Compradora pode ter para comprar;
    - QUANTIDADE_DISPONIVEL_PRODUTORA = 100: quantidade de MegaWatts que a empresa Produtora pode ter para vender;
    - QUANTIDADE_COMPRADORAS = [5, 10, 15, 20]: quantidade de empresas Compradoras que serão geradas;
    - QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA = 3: quantidade máxima de lances que uma única Compradora pode fazer.

- O array de QUANTIDADE_COMPRADORAS foi feito para, ao executar os algoritmos, sejam executados cenários diferentes, de
  modo a avaliar o crescimento do tempo demandado, os valores configurados por padrão são 5, 10, 15 e 20. Se desejar
  executar algum cenário diferente, basta modificar essa constante, e deixar apenas o(s) valor(es) desejados, por
  exemplo: [7] => executar testes com 7 Compradoras.

- O valor da constante QUANTIDADE_MAXIMA_LANCE_PRO_COMPRADORA define a quantidade máxima de lances que uma única
  Compradora por ter no total, ou seja, nem todas as compradoras terão a mesma quantidade de lances. Como para cada
  Compradora é definido um valor aleatório de quantidade, assim como em cada lance, a quantidade de lances que uma
  Compradora pode ter é variável. Por exemplo, caso uma Compradora tenha quantidade disponível de venda igual a 50, e
  gerar lances de quantidades 10, 15, 20, ela só terá esses 3 lances, se outra Compradora com a mesma quantidade, tiver
  lances de quantidades 10, 30, 70, ela só terá 2 lances ao total, pois o 70 ultrapassa o valor máximo disponível (50).
