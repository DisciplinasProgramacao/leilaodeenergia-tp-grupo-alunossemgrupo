package entidades;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

/**
 * É a empresa que compra a energia
 *
 * @param id                   id da empresa
 * @param nome                 nome da empresa
 * @param quantidadeDisponivel quantidade de megawatts que a empresa possui para ofertas
 * @param lances               quantidade de lances
 */
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
