package entidades;

import lombok.Builder;

import java.util.List;

@Builder
public record Compradora(Long id,
                         String nome,
                         Integer quantidadeDisponivel,
                         List<Lance> lances) {
}
