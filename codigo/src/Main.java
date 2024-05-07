import entidades.Sistema;

import java.io.IOException;

import static java.lang.Runtime.getRuntime;
import static util.GeradorLog.gerarLog;

public class Main {
    public static void main(String[] args) throws IOException {

//        Obtém as configurações do hardware em que o algoritmo está sendo executado
        Sistema sistema = Sistema.builder()
                .coresDisponiveis(getRuntime().availableProcessors())
                .memoriaTotal(getRuntime().totalMemory())
                .memoriaMaxima(getRuntime().maxMemory())
                .memoriaLiberada(getRuntime().freeMemory())
                .build();

        gerarLog("Backtrackiong", 10, sistema, 10.0);

    }
}
