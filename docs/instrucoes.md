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
  modo a avaliar o crescimento do tempo demandado. 
