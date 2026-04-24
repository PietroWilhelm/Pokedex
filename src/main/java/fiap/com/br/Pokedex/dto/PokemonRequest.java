package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.validation.NivelPokemon;
import jakarta.validation.constraints.NotBlank;


public class PokemonRequest {

    @NotBlank(message = "O nome do Pokémon é obrigatório")
    String nome;

    @NotBlank(message = "O tipo do Pokémon é obrigatório")
    String tipo;

    @NivelPokemon // Validação personalizada para garantir que o nível do Pokémon esteja entre 1 e 100
    Integer level;

    @NotBlank(message = "O ataque do Pokémon é obrigatório")
    String ataque;

    public Pokemon toEntity() {
        return Pokemon.builder()
                .nome(this.nome)
                .tipo(this.tipo)
                .level(this.level)
                .ataque(this.ataque)
                .build();
    }
}
