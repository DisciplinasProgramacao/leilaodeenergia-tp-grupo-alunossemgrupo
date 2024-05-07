package entidades;

import lombok.Builder;

@Builder
public record Lance(Long id,
                    Long idCompradora,
                    Integer quantidade,
                    Integer valor) {
}
