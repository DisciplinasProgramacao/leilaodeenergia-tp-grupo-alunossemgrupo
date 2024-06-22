package entidades;

import lombok.Builder;

/**
 * Empresa que produz (disponibiliza) os megawatts para leilão
 *
 * @param id                   id da empresa
 * @param nome                 nome da empresa
 * @param quantidadeDisponivel quantidade disponibilizada para leilão
 */
@Builder
public record Produtora(Long id,
                        String nome,
                        Integer quantidadeDisponivel) {
}
