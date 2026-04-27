package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Pokebola;
import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.entity.Treinador;
import fiap.com.br.Pokedex.validation.CapacidadeMochila;
import fiap.com.br.Pokedex.validation.NivelParaCaptura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NivelParaCaptura
public record PokebolaRequest (

        @NotBlank(message = "O nome da pokébola é obrigatório")
        String nome,

        @NotBlank(message = "O tipo da pokébola (COMUM, GREAT, ULTRA, MASTER) é obrigatório")
        String tipo,

        @NotNull(message = "O nível do pokémon é necessário para validar a captura")
        Integer nivelPokemon,

        @NotNull(message = "ID do treinador é obrigatório")
        @CapacidadeMochila
        Long treinadorId,

        @NotNull(message = "ID do pokémon é obrigatório")
        Long pokemonId
){
    public Pokebola toEntity(Treinador treinador, Pokemon pokemon) {
        return Pokebola.builder()
                .nome(nome)
                .tipo(tipo)
                .treinador(treinador)
                .pokemon(pokemon)
                .build();
    }
}