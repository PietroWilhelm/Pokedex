package fiap.com.br.Pokedex.service;

import fiap.com.br.Pokedex.dto.TreinadorResponse;
import fiap.com.br.Pokedex.entity.Treinador;
import fiap.com.br.Pokedex.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository repository;

    // Pega todos os treinadores com paginação
    public Page<Treinador> getAllTreinadores(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // Adicona um novo treinador
    public Treinador addTreinador(Treinador treinador) {
        return repository.save(treinador);
    }

    // Exclusão do treinador
    public void deleteTreinador(Long id) {
        findTreinadorById(id);
        repository.deleteById(id);
    }

    // Atualização completa do treinador
    public Treinador updateTreinador(Long id, Treinador newTreinador) {
        findTreinadorById(id);
        newTreinador.setId(id);
        return repository.save(newTreinador);
    }

    public Treinador getTreinadorById(Long id) {
        return findTreinadorById(id);
    }

    // Busca específica por nome
    public Page<TreinadorResponse> findByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(TreinadorResponse::fromEntity);
    }

    // método auxiliar para encontrar treinador por ID ou lançar exceção
    private Treinador findTreinadorById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Treinador com id " + id + " não encontrado")
        );
    }
}