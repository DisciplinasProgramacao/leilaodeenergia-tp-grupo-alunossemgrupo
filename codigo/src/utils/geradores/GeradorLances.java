package utils.geradores;

import entidades.Lance;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
// import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.ThreadLocalRandom.current;
import static utils.constantes.ConstantesNumeros.*;

@UtilityClass
public class GeradorLances {

    /**
     * Gera lances aleatórios conforme a quantidade solicitada pelo usuario
     *
     * @return lista de lances
     */
    public static @NonNull List<Lance> gerarLances(Long idCompradora, Integer qtdeDisponivel, int qtdeMaximaLances) {
        List<Lance> lances = new ArrayList<>();
        Integer qtdeRemanescente = qtdeDisponivel;
        do {
            lances.add(
                    Lance.builder()
                            .id((long) lances.size())
                            .idCompradora(idCompradora)
                            .quantidade(current().nextInt(UM, qtdeRemanescente + UM))
                            .valor(current().nextInt(UM, CEM + UM))
                            .build()
            );
            qtdeRemanescente -= lances.get(lances.size() - UM).quantidade();
        } while (lances.size() != qtdeMaximaLances && qtdeRemanescente > ZERO);
        return lances;
    }
}
