package entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

import static util.constantes.ConstantesProdutoraVendedora.*;

@Data
@Builder
@AllArgsConstructor
public class MelhorResultado {

    private Produtora produtora;
    private Contador contador;
    private Set<Compradora> compradoras;
    private Set<Lance> lancesSelecionados;
    private int lucroMaximizado;
    private int quantidadeVendida;

    public MelhorResultado() {
        this.produtora = Produtora.builder()
                .id(ID_PRODUTORA)
                .nome(NOME_PRODUTORA)
                .quantidadeDisponivel(QUANTIDADE_DISPONIVEL_PRODUTORA)
                .build();
        this.contador = Contador.builder().build();
    }
}
