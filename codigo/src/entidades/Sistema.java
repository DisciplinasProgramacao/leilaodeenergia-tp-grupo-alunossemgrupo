package entidades;

import lombok.Builder;

@Builder
public record Sistema(int coresDisponiveis,
                      long memoriaTotal,
                      long memoriaMaxima,
                      long memoriaLiberada) {
}
