package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.PokemonResponse;
import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public Page<Pokemon> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pokemon addPokemon(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    public Pokemon getPokemonById(Long id) {
        return findPokemonById(id);
    }

    public void deletePokemon(Long id) {
        findPokemonById(id);
        repository.deleteById(id);
    }

    public Pokemon updatePokemon(Long id, Pokemon newPokemon) {
        findPokemonById(id);
        newPokemon.setId(id);
        return repository.save(newPokemon);
    }

    // Métodos de busca específicos
    public Page<PokemonResponse> findByTipo(String tipo, Pageable pageable) {
        return repository.findByTipoIgnoreCase(tipo, pageable)
                .map(PokemonResponse::fromEntity);
    }

    public Page<PokemonResponse> findByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(PokemonResponse::fromEntity);
    }

    private Pokemon findPokemonById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon com id " + id + " não encontrado")
        );
    }
}