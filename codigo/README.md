# Código do Projeto

Nesse diretório está armazenado o código-fonte do projeto.

O hierarquia de pacotes do código está segregado da seguinte forma:

- `logs`: arquivos de logs, gerados a cada execução do código.
    - `exec-log`: logs de execução, guarda a informação sobre o algoritmo executado, data de execução, quantidade de
      lances utilizados, tempo de execução em segundos, cores disponível do SO, memória total (mb), memória máxima (mb)
      e memória liberada (mb).
    - `hist-log`: logs de histórico de execução, registra a quantidade relacionada a cada lance, e o valor ofertado.
      Informa também os lances selecionados para encontrar o maior lucro, e o seu valor.
- `src`: código raiz do projeto.
    - `entidades`: armazena todas as entidades do projeto (Compradora, Contador, Lance, dentre outras).
    - `enums`: enumerações do projeto.
    - `metodos`: métodos implementados,
        - `interfaces`: armazena as interfaces dos algoritmos.
    - `utils`: classes utilitárias do código.
        - `constantes`: constantes do projeto.
        - `conversores`: conversores de unidades.
        - `geradores`: geradores de execução.
