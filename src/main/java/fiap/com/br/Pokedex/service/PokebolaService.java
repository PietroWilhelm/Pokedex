package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.PokebolaRequest;
import fiap.com.br.Pokedex.dto.PokebolaResponse;
import fiap.com.br.Pokedex.entity.Pokebola;
import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.entity.Treinador;
import fiap.com.br.Pokedex.repository.PokebolaRepository;
import fiap.com.br.Pokedex.repository.PokemonRepository;
import fiap.com.br.Pokedex.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokebolaService {

    @Autowired
    private PokebolaRepository pokebolaRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    // Método para listar todas as pokébolas com paginação
    public Page<Pokebola> getAllPokebolas(Pageable pageable) {
        return pokebolaRepository.findAll(pageable);
    }

    // Metodo para criar uma nova pokébola a partir de um Request DTO
    public PokebolaResponse createPokemon(PokebolaRequest request) {

        var treinador = treinadorRepository.findById(request.treinadorId())
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        var pokemon = pokemonRepository.findById(request.pokemonId())
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        Pokebola pokebola = request.toEntity(treinador, pokemon);

        return PokebolaResponse.fromEntity(pokebolaRepository.save(pokebola));
    }

    // Método para obter uma pokébola por ID
    public Pokebola getPokebolaById(Long id) {
        return findPokebolaById(id);
    }

    // Método para deletar uma pokébola por ID
    public void deletePokebola(Long id) {
        findPokebolaById(id);
        pokebolaRepository.deleteById(id);
    }

    // Método para atualizar os dados de uma pokébola por ID
    public Pokebola updatePokebola(Long id, Pokebola newPokebola) {
       findPokebolaById(id);

        newPokebola.setId(id);
        return pokebolaRepository.save(newPokebola);
    }

    // Método para buscar pokébolas por treinador
    public Page<PokebolaResponse> findByTreinador(Long treinadorId, Pageable pageable) {
        return pokebolaRepository.findByTreinadorId(treinadorId, pageable)
                .map(PokebolaResponse::fromEntity);
    }

    // Método para buscar pokébolas por tipo
    public Page<PokebolaResponse> findByTipo(String tipo, Pageable pageable) {
        return pokebolaRepository.findByTipoIgnoreCase(tipo, pageable)
                .map(PokebolaResponse::fromEntity);
    }

    // Método auxiliar para encontrar uma pokébola por ID ou lançar uma exceção se não for encontrada
    private Pokebola findPokebolaById(Long id) {
        return pokebolaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokébola com id " + id + " não encontrada")
        );
    }
}