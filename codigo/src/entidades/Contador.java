package entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.time.Instant.now;

/**
 * Classe de contador de tempo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contador {

    private long inicio;
    private long fim;
    private long tempoExecucao;

    /**
     * Inicia contagem de tempo
     */
    public void iniciarContador() {
        this.inicio = now().getEpochSecond();
    }

    /**
     * Finaliza contagem de tempo
     */
    public void finalizarContator() {
        this.fim = now().getEpochSecond();
        this.tempoExecucao = this.fim - this.inicio;
    }
}
