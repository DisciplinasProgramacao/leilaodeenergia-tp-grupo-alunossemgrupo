package entidades;

import lombok.Builder;

@Builder
public record   Produtora(Long id,
                        String nome,
                        Integer quantidadeDisponivel) {
}
