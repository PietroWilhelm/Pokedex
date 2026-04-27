package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Pokemon;

public record PokemonResponse (
    Long id,
    String nome,
    String tipo,
    int level,
    String ataque
) {
    public static PokemonResponse fromEntity(Pokemon pokemon) {
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getNome(),
                pokemon.getTipo(),
                pokemon.getLevel(),
                pokemon.getAtaque()
        );
    }
}
