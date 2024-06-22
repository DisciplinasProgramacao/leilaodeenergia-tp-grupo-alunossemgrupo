package entidades;

import lombok.Builder;

/**
 * Lance gerado por cada empresa compradora
 *
 * @param id           id do lance
 * @param idCompradora id da empresa compradora
 * @param quantidade   quantidade (em megawatts) do lance
 * @param valor        valor do lance
 */
@Builder
public record Lance(Long id,
                    Long idCompradora,
                    Integer quantidade,
                    Integer valor) {
    public double valorPorMegawatt() {
        return (double) valor / quantidade;
    }
}
