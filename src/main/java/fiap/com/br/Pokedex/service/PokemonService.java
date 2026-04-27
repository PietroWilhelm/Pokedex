package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.PokemonRequest;
import fiap.com.br.Pokedex.dto.PokemonResponse;
import fiap.com.br.Pokedex.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository repository;

    public PokemonResponse create(PokemonRequest request) {
        var pokemon = repository.save(request.toEntity());
        return PokemonResponse.fromEntity(pokemon);
    }

    public Page<PokemonResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(PokemonResponse::fromEntity);
    }

    public Page<PokemonResponse> findByTipo(String tipo, Pageable pageable) {
        return repository.findByTipoIgnoreCase(tipo, pageable)
                .map(PokemonResponse::fromEntity);
    }

    public Page<PokemonResponse> findByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(PokemonResponse::fromEntity);
    }

    public PokemonResponse update(Long id, PokemonRequest request) {
        var pokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        pokemon.setNome(request.nome());
        pokemon.setLevel(request.level());

        return PokemonResponse.fromEntity(repository.save(pokemon));
    }

    public PokemonResponse findById(Long id) {
        return repository.findById(id)
                .map(PokemonResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado com o ID: " + id));
    }
}