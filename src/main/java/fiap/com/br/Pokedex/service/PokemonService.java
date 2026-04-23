package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {
    
    private final PokemonRepository pokemonRepository;

    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> findAll(){
        return pokemonRepository.findAll();
    }

}
