package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.PokebolaRequest;
import fiap.com.br.Pokedex.dto.PokebolaResponse;
import fiap.com.br.Pokedex.entity.Pokebola;
import fiap.com.br.Pokedex.repository.PokebolaRepository;
import fiap.com.br.Pokedex.repository.PokemonRepository;
import fiap.com.br.Pokedex.repository.TreinadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // O Lombok cria o construtor para os campos 'final'
public class PokebolaService {

    private final PokebolaRepository pokebolaRepository;
    private final TreinadorRepository treinadorRepository;
    private final PokemonRepository pokemonRepository;

    // CREATE (Seguindo seu modelo, mas incluindo busca de relações e DTO)
    public PokebolaResponse create(PokebolaRequest request) {
        var treinador = treinadorRepository.findById(request.treinadorId())
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        var pokemon = pokemonRepository.findById(request.pokemonId())
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        Pokebola pokebola = request.toEntity(treinador, pokemon);
        return PokebolaResponse.fromEntity(pokebolaRepository.save(pokebola));
    }

    // FIND ALL com Paginação
    public Page<PokebolaResponse> findAll(Pageable pageable) {
        return pokebolaRepository.findAll(pageable)
                .map(PokebolaResponse::fromEntity);
    }

    public PokebolaResponse findById(Long id) {
        return pokebolaRepository.findById(id)
                .map(PokebolaResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Pokébola não encontrada com o ID: " + id));
    }

    // DELETE
    public void delete(Long id) {
        pokebolaRepository.deleteById(id);
    }

    // UPDATE
    public PokebolaResponse update(Long id, PokebolaRequest request) {
        Pokebola existingPokebola = pokebolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokébola não encontrada"));

        existingPokebola.setNome(request.nome());
        existingPokebola.setTipo(request.tipo());

        return PokebolaResponse.fromEntity(pokebolaRepository.save(existingPokebola));
    }

    public Page<PokebolaResponse> findByTreinador(Long treinadorId, Pageable pageable) {
        return pokebolaRepository.findByTreinadorId(treinadorId, pageable)
                .map(PokebolaResponse::fromEntity);
    }

    public Page<PokebolaResponse> findByTipo(String tipo, Pageable pageable) {
        return pokebolaRepository.findByTipoIgnoreCase(tipo, pageable)
                .map(PokebolaResponse::fromEntity);
    }
}