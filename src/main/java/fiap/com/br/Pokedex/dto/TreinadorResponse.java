package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Treinador;

public record TreinadorResponse (
        Long id,
        String nome,
        int capacidadeMochila

) {
    public static TreinadorResponse fromEntity(Treinador treinador) {
        return new TreinadorResponse(
                treinador.getId(),
                treinador.getNome(),
                treinador.getCapacidadeMochila()
        );
    }
}
