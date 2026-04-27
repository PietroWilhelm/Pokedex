package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.PokemonRequest;
import fiap.com.br.Pokedex.dto.PokemonResponse;
import fiap.com.br.Pokedex.dto.TreinadorRequest;
import fiap.com.br.Pokedex.dto.TreinadorResponse;
import fiap.com.br.Pokedex.entity.Treinador;
import fiap.com.br.Pokedex.repository.TreinadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreinadorService {

    private final TreinadorRepository repository;

    public TreinadorResponse create(TreinadorRequest request) {
        var treinador = repository.save(request.toEntity());
        return TreinadorResponse.fromEntity(treinador);
    }

    public Page<TreinadorResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(TreinadorResponse::fromEntity);
    }

    public TreinadorResponse findById(Long id) {
        return repository.findById(id)
                .map(TreinadorResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<TreinadorResponse> findByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(TreinadorResponse::fromEntity);
    }

    public TreinadorResponse update(Long id, TreinadorRequest request) {
        // Busca o treinador ou lança erro se não existir
        Treinador treinadorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado com o ID: " + id));

        // Atualiza os campos
        treinadorExistente.setNome(request.nome());
        treinadorExistente.setCapacidadeMochila(request.capacidadeMochila());

        // Salva a entidade atualizada
        Treinador treinadorAtualizado = repository.save(treinadorExistente);

        // Retorna o DTO de resposta
        return TreinadorResponse.fromEntity(treinadorAtualizado);
    }


}