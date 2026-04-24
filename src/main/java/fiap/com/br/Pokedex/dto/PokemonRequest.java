package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.validation.NivelPokemon;
import jakarta.validation.constraints.NotBlank;


public record PokemonRequest (
    @NotBlank(message = "O nome do Pokémon é obrigatório")
    String nome,

    @NotBlank(message = "O tipo do Pokémon é obrigatório")
    String tipo,

    @NivelPokemon
    int level,

    @NotBlank(message = "O ataque do Pokémon é obrigatório")
    String ataque
) {
    public Pokemon toEntity() {
        return Pokemon.builder()
                .nome(nome)
                .tipo(tipo)
                .level(level)
                .ataque(ataque)
                .build();
    }
}