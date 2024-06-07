package entidades;

import lombok.Builder;
import lombok.NonNull;
// import org.jetbrains.annotations.NotNull;

import java.util.List;

@Builder
public record Compradora(Long id,
                         String nome,
                         Integer quantidadeDisponivel,
                         List<Lance> lances) {

    /**
     * Encontra uma compradora a partir do seu id
     *
     * @param compradoras lista de compradoras
     * @param id          id da compradora
     * @return compradora com id correspondente
     */
    public static @NonNull Compradora encontrarPorId(@NonNull List<Compradora> compradoras, Long id) {
        return compradoras.stream()
                .filter(compradora -> compradora.id.equals(id))
                .findFirst()
                .get();
    }
}
