package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Pokebola;
import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.entity.Treinador;

public record PokebolaResponse(
    Long id,
    String nome,
    String tipo,
    Treinador treinador,
    Pokemon pokemon

) {
    public static PokebolaResponse fromEntity(Pokebola pokebola) {
        return new PokebolaResponse(
               pokebola.getId(),
               pokebola.getNome(),
               pokebola.getTipo(),
               pokebola.getTreinador(),
               pokebola.getPokemon()
        );
    }
}